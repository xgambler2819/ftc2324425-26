package org.firstinspires.ftc.teamcode.Command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.SubSystem.LimeLightSubSystem;
import org.firstinspires.ftc.teamcode.SubSystem.Shooter;

public class ShooterProportional extends CommandBase {
    private Shooter m_shooter;
    private LimeLightSubSystem m_limelight;
    public ShooterProportional(Shooter shooter, LimeLightSubSystem limelight) {
        m_shooter = shooter;
        m_limelight = limelight;
        addRequirements(shooter, limelight);
    }
    @Override
    public void initialize() {
        Pose yay = m_limelight.getPedroPose();
        double x =  yay.getX();
        double y =  yay.getY();
        double predist = Math.pow((134-x), 2) + Math.pow((134-y), 2);
        double distance = Math.sqrt(predist);
        double speed = 4.91*distance + 1006;
        m_shooter.setProportionalVelocity((int) speed);

    }

    @Override
    public boolean isFinished() { return true;}
}

