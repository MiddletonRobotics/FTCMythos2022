package org.firstinspires.ftc.teamcode;

import static MiddletonLibrary.Robot.OpMode.AUTO;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import MiddletonLibrary.Resources.MiddletonLinearOpMode;

@Autonomous(name="Auto Red")
public class AutoRed extends MiddletonLinearOpMode {
    private final Phoenix robot = new Phoenix();


    @Override
    public void runLinearOpMode() {
        dash.create("Status", "Initialized");
        robot.init(AUTO);

        waitForStart();
        clock.reset();

        robot.drive(12, 30, 75);

    }
}