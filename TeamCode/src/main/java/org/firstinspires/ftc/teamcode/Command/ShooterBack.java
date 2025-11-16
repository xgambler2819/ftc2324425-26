package org.firstinspires.ftc.teamcode.Command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.SubSystem.Shooter;


public class ShooterBack extends CommandBase {
    private Shooter m_shooter;
    private long startTime;
    private static final long DURATION = 1000;

    public ShooterBack(Shooter shooter) {
        m_shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
        m_shooter.setBack();
    }

    @Override
    public boolean isFinished() {
        return System.currentTimeMillis() - startTime > DURATION;
    }

    @Override
    public void end(boolean interrupted) {
        m_shooter.setStop();
    }
}