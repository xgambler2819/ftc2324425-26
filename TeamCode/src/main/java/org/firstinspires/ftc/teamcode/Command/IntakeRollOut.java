package org.firstinspires.ftc.teamcode.Command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.SubSystem.Indexer;

public class IntakeRollOut extends CommandBase {
    private Intake m_intake;

    public IntakeRollOut(Intake intake) {
        m_intake = intake;
        addRequirements(m_intake);
    }

    @Override
    public void initialize () {
        m_intake.RollOut();
    }

    @Override
    public boolean isFinished() {
        return true
    }
}
