package org.firstinspires.ftc.teamcode.SubSystem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DriveTrain extends SubsystemBase {

    private MecanumDrive m_drive;
    private Telemetry m_telemetry;

    private IMU m_imu;  // Control/Expansion Hub IMU
    private double  m_heading = 0;

    final Motor.GoBILDA MotorType = Motor.GoBILDA.RPM_312;
    public DriveTrain(final HardwareMap hmap, final Telemetry telemetry) {
        m_telemetry = telemetry;
        Motor frontLeft =  new Motor(hmap,  "leftFront", MotorType);
        Motor frontRight =  new Motor(hmap,  "rightFront", MotorType);
        Motor backLeft =  new Motor(hmap,  "leftBack", MotorType);
        Motor backRight =  new Motor(hmap,  "rightBack", MotorType);
        m_drive = new MecanumDrive(true, frontLeft, frontRight, backLeft, backRight);

        /* The next two lines define Hub orientation.
         * The Default Orientation (shown) is when a hub is mounted horizontally with the printed logo pointing UP and the USB port pointing FORWARD.
         *
         * To Do:  EDIT these two lines to match YOUR mounting configuration.
         */
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.UP;
        RevHubOrientationOnRobot.UsbFacingDirection  usbDirection  = RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);

        // Now initialize the IMU with this mounting orientation
        // This sample expects the IMU to be in a REV Hub and named "imu".
        m_imu = hardwareMap.get(IMU.class, "imu");
        m_imu.initialize(new IMU.Parameters(orientationOnRobot));
    }

    public void driveRobotCentric(double strafeSpeed, double forwardSpeed, double turnSpeed) {
        m_drive.driveRobotCentric(strafeSpeed, forwardSpeed, turnSpeed, true);
        m_heading = readHeading();
        m_telemetry.addData("strafe", strafeSpeed);
        m_telemetry.addData("forward", forwardSpeed);
        m_telemetry.addData("turn", turnSpeed);
        m_telemetry.addData("heading", m_heading);
    }

    public void stop() {
        m_drive.stop();
    }
    
    public double getHeading() {
        return m_heading;
    }

    // read the Robot heading directly from the IMU (in degrees)
    public double readHeading() {
        YawPitchRollAngles orientation = m_imu.getRobotYawPitchRollAngles();
        return orientation.getYaw(AngleUnit.DEGREES);
    }
}


