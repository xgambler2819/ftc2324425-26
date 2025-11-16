package org.firstinspires.ftc.teamcode.Command;

import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.SubSystem.Intake;

public class IntakeRollIn extends CommandBase {
    private Intake m_intake;

    public IntakeRollIn(Intake intake) {
        m_intake = intake;
        addRequirements(m_intake);
    }

    @Override
    public void initialize () {
        m_intake.RollIn();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
