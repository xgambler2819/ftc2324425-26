package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ShooterReachTarget extends CommandBase {
    private Shooter m_shooter;
    public ShooterReachTarget(Shooter subsystem) {
        m_shooter = subsystem;
    }
    @Override
    public boolean isFinished( ){
        return m_shooter.reachTargetVelocity();
    }
}

