package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="MechaDrive")
public class MechaDrive extends OpMode
{

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;
    private DcMotor duckWheel = null;
    private DcMotor spool = null;
    private Servo claw = null;

    private boolean aPressed = false;
    private boolean clawClosed = false;

    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "FL");
        frontRight = hardwareMap.get(DcMotor.class, "FR");
        backLeft = hardwareMap.get(DcMotor.class, "BL");
        backRight = hardwareMap.get(DcMotor.class, "BR");
        duckWheel = hardwareMap.get(DcMotor.class, "DuckWheel");
        spool = hardwareMap.get(DcMotor.class,"spool");
        claw = hardwareMap.get(Servo.class, "claw");

        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        duckWheel.setDirection(DcMotorSimple.Direction.FORWARD);
        spool.setDirection(DcMotorSimple.Direction.FORWARD);
        claw.setDirection(Servo.Direction.FORWARD);
        spool.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void start(){ runtime.reset();}

    @Override
    public void loop(){
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        frontLeft.setPower(frontLeftPower);
        backLeft.setPower(backLeftPower);
        frontRight.setPower(frontRightPower);
        backRight.setPower(backRightPower);

        double spinSpool = ((gamepad1.y ? 1 : 0) - (gamepad1.x ? 1 : 0));
        spool.setPower(spinSpool);

        double duckWheelLeft = (gamepad1.left_trigger - gamepad1.right_trigger)/5;
        duckWheel.setPower(duckWheelLeft);

        if (gamepad1.a && !aPressed) {
            clawClosed = !clawClosed;
        }

        claw.setPosition(clawClosed ? 0.8 : 0.6);

        // Show motor power on telemetry
        telemetry.addData("FL Power", frontLeftPower);
        telemetry.addData("FR Power", frontRightPower);
        telemetry.addData("BL Power", backLeftPower);
        telemetry.addData("BR Power", backRightPower);
        telemetry.addData("Claw Position", clawClosed);
        telemetry.update();

        aPressed = gamepad1.a;
    }

    @Override
    public void stop() {
    }

}