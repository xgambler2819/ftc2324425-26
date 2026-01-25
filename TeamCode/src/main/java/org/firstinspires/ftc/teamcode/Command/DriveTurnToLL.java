package org.firstinspires.ftc.teamcode.Command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import java.util.function.DoubleSupplier;
import org.firstinspires.ftc.teamcode.SubSystem.DriveTrain;
import org.firstinspires.ftc.teamcode.SubSystem.LimeLightSubSystem;

/**
 * A command to drive the robot with joystick input (passed in as {@link DoubleSupplier}s). Written
 * explicitly for pedagogical purposes.
 */
public class DriveTurnToLL extends CommandBase {

    private final DriveTrain m_drive;
    private LimeLightSubSystem m_limeLight;
    private final GamepadEx m_gpad;
    private boolean m_finished = false;


    public DriveTurnToLL(LimeLightSubSystem limelight, DriveTrain subsystem, GamepadEx gpad) {
        m_drive = subsystem;
        m_limeLight = limelight;
        m_gpad = gpad;
        addRequirements(m_limeLight, m_drive);
    }

    @Override
    public void execute() {
       if (m_limeLight.getTx()>=3) {
           m_finished = false;
           m_drive.driveRobotCentric(0, 0, -0.4);
       } else if (m_limeLight.getTx()<=-3){
           m_finished = false;
           m_drive.driveRobotCentric(0, 0, 0.4);

       } else if ((m_limeLight.getTx()>-3) && (m_limeLight.getTx()<3)) {
           m_drive.driveRobotCentric(0, 0, 0);
           m_finished = true;
       }

    }
    @Override
    public boolean isFinished(){
        return m_finished;
    }

}