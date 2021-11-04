package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.Robot;

@TeleOp(name="Driver Controlled")
public class MainTeleOpMode extends OpMode {

    // Declare Hardware object
    Robot robot;

    // Constructor
    public MainTeleOpMode() {

        // Instantiate Objects
        robot = new Robot();

    }

    @Override
    public void init() {

        // Initialize Hardware
        robot.init(hardwareMap);

    }

    @Override
    public void loop() {

        /* Drivetrain */
        // read inputs from gamepad
        double Y1 = gamepad1.left_stick_y;
        double Y2 = gamepad1.right_stick_y;
        double X = gamepad1.right_stick_x;
        // calculate drive vector from gamepad input
        double forward = -(Y1+Y2)/2;
        double right = X;
        double clockwise = -(Y1-Y2)/2;
        // Send drive vector to the robot object
        robot.vector(forward, right, clockwise);

        /* Intake */
        boolean rb = gamepad2.right_bumper;
        boolean lb = gamepad2.left_bumper;
        // if right bumper is pressed, rb value = true
        if(rb) robot.collect();
        // if left bumper is pressed, lb value = true
        if(lb) robot.eject();
        // if both rb & lb are false, neither bumper is pressed, so stop intake
        if(!(rb && lb)) robot.stopIntake();

        /* Slider */
        double sliderUp = gamepad2.right_trigger;
        double sliderDown = gamepad2.left_trigger;
        robot.setSlidePower(sliderUp - sliderDown);

        /* Dumper */
        boolean y = gamepad2.y;
        boolean a = gamepad2.a;
        boolean b = gamepad2.b;
        if(a) robot.dump();
        if(y) robot.unDump();
        if(b) robot.carry();

        /* Wheel of Fortune */
        double x = -gamepad2.left_stick_x;
        robot.setWOFPower(x);

    }

}
