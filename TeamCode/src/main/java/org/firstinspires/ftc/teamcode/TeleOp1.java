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
    private DriveTrain m_drivetrain;
    private GamepadEx m_gamepad1;
    private GamepadEx m_gamepad2;

    @Override
    public void initialize() {
        m_drivetrain = new DriveTrain(hardwareMap);
        m_indexer = new Indexer(hardwareMap);
        m_shooter = new Shooter(hardwareMap);
        m_shooter.setdefaultCommand(new ShooterKeepVelocity(m_shooter));

        m_gamepad1 = new GamepadEx(gamepad1);
        m_gamepad2 = new GamepadEx(gamepad2);
        Button pad1_a = new GamepadButton(m_gamepad1, GamepadKeys.Button.A);
        Button pad1_b = new GamepadButton(m_gamepad1, GamepadKeys.Button.B);
        pad1_a.whenPressed(new ShooterTargetLow(m_shooter));
        pad1_b.whenPressed(new IndexerRollForward(m_indexer));
    }
 }