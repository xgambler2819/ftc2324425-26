package org.firstinspires.ftc.teamcode.Command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.SubSystem.DriveTrain;

import java.util.function.DoubleSupplier;

/**
 * A command to drive the robot with joystick input (passed in as {@link DoubleSupplier}s). Written
 * explicitly for pedagogical purposes.
 */
public class DriveTimedMove extends CommandBase {

    private final DriveTrain m_drive;
    private final double m_strafe;
    private final double m_forward;
    private final double m_turn;
    private final long m_duration;

    private long startTime;

    public DriveTimedMove(DriveTrain driveTrain, double strafe, double forward, double turn, long duration) {
        m_drive = driveTrain;
        m_strafe = strafe;
        m_forward = forward;
        m_turn = turn;
        m_duration = duration;
        addRequirements(m_drive);
    }

    @Override
    public void initialize () {
        startTime = System.currentTimeMillis();
    }
    @Override
    public void execute() {
        m_drive.driveRobotCentric(m_strafe, m_forward, m_turn);
    }
    @Override
    public boolean isFinished() {
        return System.currentTimeMillis() - startTime > m_duration;
    }

    @Override
    public void end(boolean interrupted) {
        m_drive.stop();
    }
}