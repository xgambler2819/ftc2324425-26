package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ShooterTargetHigh extends CommandReachTarget {
    final int highVelocity =100;
    public ShooterTargetHigh(Shooter subsystem, int targetVelocity) {
        super(subsystem, targetVelocity);
        subsystem.SetState(highVelocity, true);
    }
}
