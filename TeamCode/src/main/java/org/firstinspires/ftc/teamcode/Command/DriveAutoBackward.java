package org.firstinspires.ftc.teamcode.Command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.SubSystem.DriveTrain;

import java.util.function.DoubleSupplier;

/**
 * A command to drive the robot with joystick input (passed in as {@link DoubleSupplier}s). Written
 * explicitly for pedagogical purposes.
 */
public class DriveAutoBackward extends CommandBase {

    private final DriveTrain m_drive;
    private static final long DURATION = 3000;
    private long startTime;
    private double m_strafe;
    public DriveAutoBackward(DriveTrain subsystem, double strafe) {
        m_drive = subsystem;
        m_strafe = strafe;
        addRequirements(m_drive);
    }

    @Override
    public void initialize () {
        startTime = System.currentTimeMillis();
    }
    @Override
    public void execute() {
        m_drive.driveRobotCentric(m_strafe, .7, 0);
    }
    @Override
    public boolean isFinished() {
        return System.currentTimeMillis() - startTime > DURATION;
    }

    @Override
    public void end(boolean interrupted) {
        m_drive.driveRobotCentric(0,0,0);
    }
}