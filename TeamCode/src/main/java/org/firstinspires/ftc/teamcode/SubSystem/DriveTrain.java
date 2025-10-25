package org.firstinspires.ftc.teamcode.SubSystem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DriveTrain extends SubsystemBase {

    private MecanumDrive m_drive;
    private Telemetry m_telemetry;
    final Motor.GoBILDA MotorType = Motor.GoBILDA.RPM_312;
    public DriveTrain(final HardwareMap hmap, final Telemetry telemetry) {
        m_telemetry = telemetry;
        Motor frontLeft =  new Motor(hmap,  "leftFront", MotorType);
        Motor frontRight =  new Motor(hmap,  "rightFront", MotorType);
        Motor backLeft =  new Motor(hmap,  "leftBack", MotorType);
        Motor backRight =  new Motor(hmap,  "rightBack", MotorType);
        m_drive = new MecanumDrive(true, frontLeft, frontRight, backLeft, backRight);
    }

    public void driveRobotCentric(double strafeSpeed, double forwardSpeed, double turnSpeed) {
        m_drive.driveRobotCentric(strafeSpeed, forwardSpeed, turnSpeed);
        m_telemetry.addData("strafe", strafeSpeed);
        m_telemetry.addData("forward", forwardSpeed);
        m_telemetry.addData("turn", turnSpeed);
    }
}


