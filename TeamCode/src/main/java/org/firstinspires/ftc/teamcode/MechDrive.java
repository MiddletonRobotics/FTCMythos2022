package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.hypot;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;


@TeleOp(name="Mech Drive")
public class MechDrive extends OpMode
{
    public static double max(double... vals) {
        double max = Double.MIN_VALUE;
        for (double d: vals) if (max < d) max = d;
        return max;
    }

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // Initialize hardware variables
        frontLeft = hardwareMap.get(DcMotor.class, "FL");
        frontRight = hardwareMap.get(DcMotor.class, "FR");
        backLeft = hardwareMap.get(DcMotor.class, "BL");
        backRight = hardwareMap.get(DcMotor.class, "BR");

        // Set motor directions
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        // Get controller input
        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double xR = gamepad1.right_stick_x;

        double angle = atan2(x, y);
        double speed = hypot(x, y);

        angle += PI / 4;
        double speedMultiplier = speed * sqrt(2);

        double leftFrontPower = (sin(angle) * speedMultiplier) + xR;
        double leftBackPower = (cos(angle) * speedMultiplier) + xR;
        double rightFrontPower = (cos(angle) * speedMultiplier) - xR;
        double rightBackPower = (sin(angle) * speedMultiplier) - xR;

        double max = max(abs(leftFrontPower), abs(leftBackPower), abs(rightFrontPower), abs(rightBackPower));
        if (max > 1) {
            leftFrontPower /= max;
            leftBackPower /= max;
            rightFrontPower /= max;
            rightBackPower /= max;
        }

        frontLeft.setPower(leftFrontPower);
        backLeft.setPower(leftBackPower);
        frontRight.setPower(rightFrontPower);
        backRight.setPower(rightBackPower);

        telemetry.addData("Angle", angle);
        telemetry.addData("Speed", speed);
        telemetry.addData("Turn", xR);
        telemetry.addData("Status", "Run Time: " + runtime.toString());
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
