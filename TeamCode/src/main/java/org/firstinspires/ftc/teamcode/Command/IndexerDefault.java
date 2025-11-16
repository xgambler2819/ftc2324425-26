package org.firstinspires.ftc.teamcode.Command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.SubSystem.Indexer;


import java.util.function.DoubleSupplier;

/**
 * A command to drive the robot with joystick input (passed in as {@link DoubleSupplier}s). Written
 * explicitly for pedagogical purposes.
 */
public class IndexerDefault extends CommandBase {

    private final Indexer m_indexer;
    private final GamepadEx m_gpad;

    public IndexerDefault(Indexer indexer, GamepadEx gpad) {
        m_indexer = indexer;
        m_gpad = gpad;
        addRequirements(m_indexer);
    }

    @Override
    public void execute() {
        m_indexer.move(m_gpad.getLeftY());
    }

}
