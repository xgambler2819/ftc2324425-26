package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DriveTrain extends SubsystemBase {

    private MecanumDrive m_drive;
    final GoBILDA MotorType = GoBILDA.RPM_312;
    public DriveTrain(final HardwareMap hmap) {

        var frontLeft =  new Motor(hmap,  "leftFront", MotorType);
        var frontRight =  new Motor(hmap,  "rightFront", MotorType);
        var backLeft =  new Motor(hmap,  "leftBack", MotorType);
        var backRight =  new Motor(hmap,  "rightBack", MotorType);
        m_drive = new MecanumDrive(true, frontLeft, frontRight, backLeft, backRight);
    }

    public void driveRobotCentric(double strafeSpeed, double forwardSpeed, double turnSpeed) {
        m_drive.driveRobotCentric(strafeSpeed, forwardSpeed, turnSpeed);
    }
}


