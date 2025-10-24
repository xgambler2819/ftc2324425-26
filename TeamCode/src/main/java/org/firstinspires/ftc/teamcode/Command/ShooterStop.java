package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ShooterStop extends ShooterReachTarget {
    public ShooterStop(Shooter shooter) {
        super(shooter);
        shooter.setState(0, false);
    }
}
