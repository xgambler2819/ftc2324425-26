package org.firstinspires.ftc.teamcode.Command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import java.util.function.DoubleSupplier;
import org.firstinspires.ftc.teamcode.SubSystem.DriveTrain;
import org.firstinspires.ftc.teamcode.SubSystem.Intake;

/**
 * A command to drive the robot with joystick input (passed in as {@link DoubleSupplier}s). Written
 * explicitly for pedagogical purposes.
 */
public class IntakeDefault extends CommandBase {

    private final Intake m_intake;
    private final GamepadEx m_gpad;


    public IntakeDefault(Intake intake, GamepadEx gpad) {
        m_intake = intake;
        m_gpad = gpad;
        addRequirements(m_intake);
    }

    @Override
    public void execute() {
        m_intake.move(m_gpad.getRightY());
    }

}
