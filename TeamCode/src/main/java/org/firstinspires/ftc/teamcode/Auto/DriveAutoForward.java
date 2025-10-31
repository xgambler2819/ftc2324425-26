package org.firstinspires.ftc.teamcode.Auto;

import org.firstinspires.ftc.teamcode.Command.DriveTimedMove;
import org.firstinspires.ftc.teamcode.SubSystem.DriveTrain;

import java.util.function.DoubleSupplier;

/**
 * A command to drive the robot with joystick input (passed in as {@link DoubleSupplier}s). Written
 * explicitly for pedagogical purposes.
 */
public class DriveAutoForward extends DriveTimedMove {
    public DriveAutoForward(DriveTrain driveTrain) {
        super(driveTrain, 0, -0.5, 0, 2500);
    }
}