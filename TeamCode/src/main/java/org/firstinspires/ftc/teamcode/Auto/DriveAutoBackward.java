package org.firstinspires.ftc.teamcode.Auto;

import org.firstinspires.ftc.teamcode.Command.DriveTimedMove;
import org.firstinspires.ftc.teamcode.SubSystem.DriveTrain;

import java.util.function.DoubleSupplier;

/**
 * A command to drive the robot with joystick input (passed in as {@link DoubleSupplier}s). Written
 * explicitly for pedagogical purposes.
 */
public class DriveAutoBackward extends DriveTimedMove {
    public DriveAutoBackward(DriveTrain driveTrain) {
       super(driveTrain, 0, .7, 0, 1800);
    }
}