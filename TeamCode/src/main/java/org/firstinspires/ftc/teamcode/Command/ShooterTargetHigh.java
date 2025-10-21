package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ShooterTargetHigh extends ShooterReachTarget {
    final int highVelocity = 100;
    public ShooterTargetHigh(Shooter shooter, int targetVelocity) {
        super(shooter, targetVelocity);
        shooter.SetState(highVelocity, true);
    }
}
