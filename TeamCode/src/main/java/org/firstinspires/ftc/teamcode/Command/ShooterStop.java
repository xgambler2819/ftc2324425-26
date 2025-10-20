package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ShooterTargetLow extends CommandReachTarget {
    public ShooterTargetLow(Shooter subsystem, int targetVelocity) {
        super(subsystem, targetVelocity);
        subsystem.SetState(0, false);
    }
}
