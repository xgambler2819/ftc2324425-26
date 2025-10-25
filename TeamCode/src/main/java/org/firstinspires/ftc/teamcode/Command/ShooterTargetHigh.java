package org.firstinspires.ftc.teamcode.Command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.SubSystem.Shooter;

public class ShooterTargetHigh extends CommandBase {
    private Shooter m_shooter;
    public ShooterTargetHigh(Shooter shooter) {
        m_shooter = shooter;
        addRequirements(shooter);
    }
    @Override
    public void initialize() {
        m_shooter.setHigh();
    }
}
