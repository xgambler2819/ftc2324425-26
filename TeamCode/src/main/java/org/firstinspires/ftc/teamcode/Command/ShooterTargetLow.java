package org.firstinspires.ftc.teamcode.Command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.SubSystem.Shooter;

public class ShooterTargetLow extends CommandBase {
    final int lowVelocity = 400;
    private Shooter m_shooter;
    public ShooterTargetLow(Shooter shooter) {
        m_shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        m_shooter.setState(lowVelocity, true);
    }
}