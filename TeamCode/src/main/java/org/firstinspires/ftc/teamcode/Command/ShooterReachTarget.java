package org.firstinspires.ftc.teamcode.Command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.SubSystem.Shooter;

public class ShooterReachTarget extends CommandBase {
    private Shooter m_shooter;
    private Telemetry m_telemetry;
    public ShooterReachTarget(Shooter shooter, Telemetry telemetry) {
        m_shooter = shooter;
        m_telemetry = telemetry;
        addRequirements(shooter);
    }
    @Override
    public boolean isFinished( ){
        boolean reachTarget = m_shooter.getReachTarget();
        m_telemetry.addData("ShooterReachTarget reachTarget:", reachTarget);
        return reachTarget;
    }
}

