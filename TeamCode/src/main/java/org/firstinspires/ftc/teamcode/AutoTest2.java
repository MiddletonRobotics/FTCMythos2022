package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="AutoTest2")
public class AutoTest2 extends LinearOpMode
{
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;
    private DcMotor duckWheel = null;

    @Override
    public void runOpMode() {
        frontLeft = hardwareMap.get(DcMotor.class, "FL");
        frontRight = hardwareMap.get(DcMotor.class, "FR");
        backLeft = hardwareMap.get(DcMotor.class, "BL");
        backRight = hardwareMap.get(DcMotor.class, "BR");
        duckWheel = hardwareMap.get(DcMotor.class, "DuckWheel");

        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        duckWheel.setDirection(DcMotorSimple.Direction.FORWARD);

        telemetry.addData("Status", "Initialized");

        waitForStart();
        runtime.reset();

        frontLeft.setPower(-0.4);
        backLeft.setPower(-0.4);
        frontRight.setPower(-0.4);
        backRight.setPower(-0.4);

        sleep(320);

        frontLeft.setPower(0.5);
        backLeft.setPower(0.5);
        frontRight.setPower(-0.5);
        backRight.setPower(-0.5);

        sleep(750);

        frontLeft.setPower(-0.2);
        backLeft.setPower(-0.2);
        frontRight.setPower(-0.2);
        backRight.setPower(-0.2);

        sleep(1830);

        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);

        duckWheel.setPower(-0.2);
        sleep(2600);
        duckWheel.setPower(0);

        frontLeft.setPower(0.2);
        backLeft.setPower(0.2);
        frontRight.setPower(0.2);
        backRight.setPower(0.2);

        sleep(400);

        frontLeft.setPower(-0.5);
        backLeft.setPower(-0.5);
        frontRight.setPower(0.5);
        backRight.setPower(0.5);

        sleep(630);

        frontLeft.setPower(-0.2);
        backLeft.setPower(-0.2);
        frontRight.setPower(-0.2);
        backRight.setPower(-0.2);

        sleep(1900);
    }
}