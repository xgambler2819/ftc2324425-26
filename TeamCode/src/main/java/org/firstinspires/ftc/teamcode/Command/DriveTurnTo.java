package org.firstinspires.ftc.teamcode.Command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.SubSystem.DriveTrain;


public class DriveTurnTo  extends CommandBase {

    private final DriveTrain m_drive;
    private final Telemetry m_telemetry;

    private final long m_duration;
    private final long m_startTime;

    private final double m_turnAngle;
    private final double m_startHeading; 
    private final double m_targetHeading;
    private double m_headingError;

    // Calculate the COUNTS_PER_INCH for your specific drive train.
    // Go to your motor vendor website to determine your motor's COUNTS_PER_MOTOR_REV
    // For external drive gearing, set DRIVE_GEAR_REDUCTION as needed.
    // For example, use a value of 2.0 for a 12-tooth spur gear driving a 24-tooth spur gear.
    // This is gearing DOWN for less speed and more torque.
    // For gearing UP, use a gear ratio less than 1.0. Note this will affect the direction of wheel rotation.
    static final double     COUNTS_PER_MOTOR_REV    = 537.7 ;   // eg: GoBILDA 312 RPM Yellow Jacket
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // No External Gearing.
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
                                                      (WHEEL_DIAMETER_INCHES * 3.1415);

    // These constants define the desired driving/control characteristics
    // They can/should be tweaked to suit the specific robot drive train.
    static final double     DRIVE_SPEED             = 0.4;     // Max driving speed for better distance accuracy.
    static final double     TURN_SPEED              = 0.2;     // Max turn speed to limit turn rate.
    static final double     HEADING_THRESHOLD       = 1.0 ;    // How close must the heading get to the target before moving to next step.
                                                               // Requiring more accuracy (a smaller number) will often make the turn take longer to get into the final position.
    // Define the Proportional control coefficient (or GAIN) for "heading control".
    // We define one value when Turning (larger errors), and the other is used when Driving straight (smaller errors).
    // Increase these numbers if the heading does not correct strongly enough (eg: a heavy robot or using tracks)
    // Decrease these numbers if the heading does not settle on the correct value (eg: very agile robot with omni wheels)
    static final double     P_TURN_GAIN            = 0.02;     // Larger is more responsive, but also less stable.
    static final double     P_DRIVE_GAIN           = 0.03;     // Larger is more responsive, but also less stable.

    public DriveTurnTo(DriveTrain driveTrain, Telemetry telemetry, double turnAngle, long duration) {
        m_drive = driveTrain;
        m_telemetry = telemetry;
        addRequirements(m_drive);

        m_turnAngle = turnAngle;
        m_startHeading = m_drive.readHeading();
        m_targetHeading = m_startHeading + m_turnAngle;

        m_duration = duration;
        m_startTime = System.currentTimeMillis();
    }

    @Override
    public void execute() {

        m_headingError = m_targetHeading - m_drive.getHeading();

        // Normalize the error to be within +/- 180 degrees
        while (m_headingError > 180)  m_headingError -= 360;
        while (m_headingError <= -180) m_headingError += 360;

        double rawTurnSpeed = m_headingError * P_TURN_GAIN;
        double turnSpeed = Range.clip(rawTurnSpeed, -1, 1);

        m_drive.driveRobotCentric(0, 0, turnSpeed);
        m_telemetry.addData("Heading Error", m_headingError);
        m_telemetry.addData("RawTurnSpeed", rawTurnSpeed);
        m_telemetry.update();
    }

    @Override
    public boolean isFinished() {
        return Math.abs(m_drive.readHeading() - m_targetHeading) < 1.0 // degree tolerance
            || (System.currentTimeMillis() - m_startTime) > m_duration;
    }

    @Override
    public void end(boolean interrupted) {
        m_drive.stop();
    }


    /**
     * Use a Proportional Controller to determine how much steering correction is required.
     *
     * @return                      Turning power needed to get to required heading.
     */
    public double getSteeringCorrection() {
        // Determine the heading current error
        m_headingError = m_targetHeading - m_drive.getHeading();

        // Normalize the error to be within +/- 180 degrees
        while (m_headingError > 180)  m_headingError -= 360;
        while (m_headingError <= -180) m_headingError += 360;

        // Multiply the error by the gain to determine the required steering correction/  Limit the result to +/- 1.0
        return Range.clip(m_headingError * P_TURN_GAIN, -TURN_SPEED, TURN_SPEED);
    }

}
