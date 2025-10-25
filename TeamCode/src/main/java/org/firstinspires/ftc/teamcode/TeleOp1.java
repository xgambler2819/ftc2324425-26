package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.command.CommandOpMode;

import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.JavaUtil;

import com.qualcomm.robotcore.hardware.CRServo;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Command.*;
import org.firstinspires.ftc.teamcode.SubSystem.*;


@TeleOp(name = "TeleOp1")
public class TeleOp1 extends CommandOpMode {

    private Indexer m_indexer;
    private Shooter m_shooter;
    private DriveTrain m_drivetrain;
    private GamepadEx m_gamepad1;
    private DriveDefault m_driveDefault;    

    @Override
    public void initialize() {
        m_drivetrain = new DriveTrain(hardwareMap, telemetry);
        m_indexer = new Indexer(hardwareMap);
        m_shooter = new Shooter(hardwareMap, telemetry);

        m_gamepad1 = new GamepadEx(gamepad1);
        Button pad1_y = new GamepadButton(m_gamepad1, GamepadKeys.Button.Y);
        pad1_y.whenPressed(new ShooterTargetHigh(m_shooter));
        Button pad1_a = new GamepadButton(m_gamepad1, GamepadKeys.Button.A);
        pad1_a.whenPressed(new ShooterTargetLow(m_shooter));
        Button pad1_b = new GamepadButton(m_gamepad1, GamepadKeys.Button.B);
        pad1_b.whenPressed(new ShooterStop(m_shooter));

        Button pad1_start = new GamepadButton(m_gamepad1, GamepadKeys.Button.START);
        pad1_start.whenPressed(new IndexerRollForward(m_indexer));
        Button pad1_back = new GamepadButton(m_gamepad1, GamepadKeys.Button.BACK);
        pad1_back.whenPressed(new IndexerRollBack(m_indexer));
        Button pad1_x = new GamepadButton(m_gamepad1, GamepadKeys.Button.X);
        pad1_x.whenPressed(new IndexerStop(m_indexer));

        m_driveDefault = new DriveDefault(m_drivetrain, () -> 0.1, () -> 0.3, () -> 0.02);
        m_drivetrain.setDefaultCommand(m_driveDefault);

        register(m_drivetrain, m_indexer, m_shooter);
    }
 }