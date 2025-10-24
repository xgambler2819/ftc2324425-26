package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;

import java.util.function.DoubleSupplier;

/**
 * A command to drive the robot with joystick input (passed in as {@link DoubleSupplier}s). Written
 * explicitly for pedagogical purposes.
 */
public class DefaultDrive extends CommandBase {

    private final DriveSubsystem m_drive;
    private final DoubleSupplier m_strafe;
    private final DoubleSupplier m_forward;
    private final DoubleSupplier m_turn;

    public DefaultDrive(DriveSubsystem subsystem,
        DoubleSupplier strafe, DoubleSupplier forward, DoubleSupplier turn) {
        m_drive = subsystem;
        m_strafe = strafe;
        m_forward = forward;
        m_turn = turn;
        addRequirements(m_drive);
    }

    @Override
    public void execute() {
        m_drive.driveRobotCentric(m_strafe.getAsDouble(), m_forward.getAsDouble(), m_turn.getAsDouble());
    }

}