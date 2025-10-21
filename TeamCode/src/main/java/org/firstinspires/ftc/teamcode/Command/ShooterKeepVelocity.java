package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

// Shooter default command to keep the shooter at target velocity
public class ShooterKeepVelocity extends CommandBase {
    private Shooter m_shooter;

    public ShooterKeepVelocity(Shooter shooter) {
        m_shooter = shooter;
        addRequirements(shooter);
    }
    
    @Override
    public void execute() {
        if(m_shooter.getKeepVelocity()){
            m_shooter.setVelocity(m_shooter.getTargetVelocity());
        }
    }
}
