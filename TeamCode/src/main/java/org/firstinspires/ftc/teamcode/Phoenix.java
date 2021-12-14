package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

import static MiddletonLibrary.Resources.Utils.driveController;
import static MiddletonLibrary.Resources.Utils.turnController;

import MiddletonLibrary.Motion.Motor;
import MiddletonLibrary.Robot;

public class Phoenix extends Robot {
    public Motor leftFront, rightFront, leftBack, rightBack, duckWheel;


    @Override
    public void mapHardware() {
        leftFront = new Motor("FL");
        rightFront = new Motor("FR", REVERSE);
        leftBack = new Motor("BL");
        rightBack = new Motor("BR", REVERSE);
        duckWheel = new Motor("DuckWheel");
    }

    @Override
    public void init(OpMode opmode) {
        mapHardware();

        turnController.setKp(0.01);
        driveController.setKp(0.04);
    }
}
