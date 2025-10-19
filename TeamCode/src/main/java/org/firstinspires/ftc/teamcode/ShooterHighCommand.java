package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ShooterHighCommand extends CommandBase {
    private BigBallShooter m_shooter;
    private Telemetry m_telemetry;
    private int m_velocity = 200;
    public ShooterHighCommand(BigBallShooter subsystem, Telemetry telemetry) {
        m_shooter = subsystem;
        m_telemetry = telemetry;
    }

    private static ElapsedTime mystopWatch = new ElapsedTime();
    @Override
    public void initialize () {
    }
    @Override
    public void execute() {
    }
    @Override
    public boolean isFinished( ){
        m_shooter.setVelcotiy(m_velocity);
        m_telemetry.addData("Execute:", "%.2f", mystopWatch.time());

       double curvelocity= m_shooter.getvelocity();
        if((Math.abs(curvelocity-m_velocity)/m_velocity)<0.3 || curvelocity>m_velocity){
            m_telemetry.addLine("i finsished");
            //m_telemetry.update();
            return true;
        }else {
            m_telemetry.addLine("i not finsished");
//m_telemetry.update();
            return false;
        }

    }
    @Override
    public void end(boolean interupted){
    //m_shooter.stop();
    }

}
