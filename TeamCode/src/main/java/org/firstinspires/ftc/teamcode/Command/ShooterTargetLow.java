package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ShooterTargetLow extends ShooterReachTarget {
    final int lowVelocity = 60;
    public ShooterTargetLow(Shooter shooter, int targetVelocity) {
        super(shooter, targetVelocity);
        shooter.SetState(lowVelocity, true);
    }
}
