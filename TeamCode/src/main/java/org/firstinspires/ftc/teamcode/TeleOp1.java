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

    //Drive Motors
    private DcMotor leftBack;
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor rightBack;

    //Servos for Indexers
    private CRServo bottomroller;
    private CRServo upperroller;

    //Shooter
    private DcMotor ballroller;

    //Intake
    private DcMotor intake;

    //Sub systems
    private Indexer m_indexer;
    private Shooter m_shooter;


  //  private IndexerRollBackCommandndexercommand servocommand;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void initialize() {

        // move to drive train subsystem
         ballroller = hardwareMap.get(DcMotor.class, "ballroller");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        leftFront.setDirection(DcMotor.Direction.REVERSE);

        // Intake subsystem
        intake = hardwareMap.get(DcMotor.class, "intake");

        m_shooter = new Shooter(hardwareMap, telemetry);
        m_shooter.setdefaultCommand(new ShooterKeepVelocity(m_shooter));

        m_indexer = new Indexer(hardwareMap);

        // add key bindings
    }
 }