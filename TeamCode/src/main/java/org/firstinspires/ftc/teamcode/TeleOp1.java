package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.gamepad.GamepadEx;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.JavaUtil;

import com.qualcomm.robotcore.hardware.CRServo;

import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "TeleOp1")
public class TeleOp1 extends CommandOpMode {

    private Indexer m_indexer;
    private Shooter m_shooter;
    private Intake m_intake;
    private DriveTrain m_drivetrain;

    @Override
    public void initialize() {
        m_intake = new Intake(hardwareMap);
        m_drivetrain = new DriveTrain(hardwareMap);
        m_indexer = new Indexer(hardwareMap);
        m_shooter = new Shooter(hardwareMap);
        m_shooter.setdefaultCommand(new ShooterKeepVelocity(m_shooter));



    }
 }