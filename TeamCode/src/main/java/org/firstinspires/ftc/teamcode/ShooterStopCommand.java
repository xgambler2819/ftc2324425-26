package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ShooterStopCommand extends CommandBase {
    private BigBallShooter m_shooter;
    private Telemetry m_telemetry;
    private int targetvelocity = 0;
    public ShooterStopCommand(BigBallShooter subsystem, int velocity, Telemetry telemetry) {
        m_shooter = subsystem;
        targetvelocity = velocity;
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
        m_shooter.setVelcotiy(targetvelocity);
        m_telemetry.addData("Execute:", "%.2f", mystopWatch.time());

       double curvelocity= m_shooter.getvelocity();
        if((Math.abs(curvelocity-targetvelocity)/targetvelocity)<0.3 || curvelocity>targetvelocity){
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
