package org.firstinspires.ftc.teamcode.Command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import java.util.function.DoubleSupplier;
import org.firstinspires.ftc.teamcode.SubSystem.DriveTrain;
/**
 * A command to drive the robot with joystick input (passed in as {@link DoubleSupplier}s). Written
 * explicitly for pedagogical purposes.
 */
public class DriveDefault extends CommandBase {

    private final DriveTrain m_drive;
    private final GamepadEx m_gpad;


    public DriveDefault(DriveTrain subsystem, GamepadEx gpad) {
        m_drive = subsystem;
        m_gpad = gpad;
        addRequirements(m_drive);
    }

    @Override
    public void execute() {

        //m_drive.driveRobotCentric(m_strafe.getAsDouble(), m_forward.getAsDouble(), m_turn.getAsDouble());
        m_drive.driveRobotCentric(-m_gpad.getLeftX(), -m_gpad.getLeftY(), -m_gpad.getRightX());
    }

}