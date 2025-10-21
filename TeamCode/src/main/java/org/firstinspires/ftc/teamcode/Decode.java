package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.gamepad.GamepadEx;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.JavaUtil;

import com.qualcomm.robotcore.hardware.CRServo;

import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "DecodeLinearOpMode")
public class Decode extends LinearOpMode {

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
    private IndexerSubsystem m_Indexer;
    private BigBallShooter m_Shooter;


    //Commands
    private ShooterHighCommand m_ShootHighCmd;
    private ShooterLowCommand m_ShootLowCmd;
    private ShooterStopCommand m_ShootStopCmd;

    private IndexerRollBackCommand m_IndexerRollBackCmd;
    private IndexerRollForwardCommand m_IndexForwardCmd;
    private IndexerStopCommand m_IndexStopCmd;
  //  private IndexerRollBackCommandndexercommand servocommand;
    private ElapsedTime runtime = new ElapsedTime();

    public void initmotors() {
        ballroller = hardwareMap.get(DcMotor.class, "ballroller");
        upperroller = hardwareMap.get(CRServo.class, "upperroller");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        bottomroller = hardwareMap.get(CRServo.class, "bottomroller");
        intake = hardwareMap.get(DcMotor.class, "intake");
        m_Shooter = new BigBallShooter(hardwareMap, "ballroller",telemetry);
        m_Indexer = new IndexerSubsystem(hardwareMap, "bottomroller", "upperroller");



        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");


        leftBack.setDirection(DcMotor.Direction.REVERSE);
        leftFront.setDirection(DcMotor.Direction.REVERSE);


    }
    @Override
    public void runOpMode() {
        initmotors();
        waitForStart();
        while (opModeIsActive()) {
        if (gamepad2.x) {

            ShooterHighCommand cmd = new ShooterHighCommand(m_Shooter, telemetry);
            cmd.schedule();
            CommandScheduler.getInstance().run();
        }
        if (gamepad2.y) {
            ShooterStopCommand cmd= new ShooterStopCommand(m_Shooter, 0, telemetry);
            cmd.schedule();
            CommandScheduler.getInstance().run();
        }

            telemetry.addData("velocity", m_Shooter.getvelocity());
            telemetry.update();

        }
        while (opModeIsActive()) {
            float y;
            double x;
            double rx;
            double denominator;
            y = -gamepad1.left_stick_y;
            x = gamepad1.left_stick_x * 1.1;
            rx = gamepad1.right_stick_x / 1.76;
            denominator = JavaUtil.maxOfList(JavaUtil.createListWith(JavaUtil.sumOfList(JavaUtil.createListWith(Math.abs(y), Math.abs(x), Math.abs(rx))), 1));
            leftFront.setPower(((y + x + rx) / denominator) / 1);
            leftBack.setPower((((y - x) + rx) / denominator) / 1);
            rightFront.setPower((((y - x) - rx) / denominator) / 1);
            rightBack.setPower((((y + x) - rx) / denominator) / 1);
            if (gamepad2.a) {
                m_Shooter.setVelcotiy(1800);
            }
            if (gamepad2.b) {
                m_Shooter.setVelcotiy(0);
            }
           // telemetry.addData("velocity", myshooter.getVelocity());
            telemetry.update();
        }
    }
 }