package org.firstinspires.ftc.teamcode.hardware;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.function.BooleanSupplier;

public class Arm {

    private DcMotor joint1 = null;
    private Servo joint2 = null;
    public static final double INTAKE_POWER = 0.9d;

    private DcMotor intake = null;

    public void init(HardwareMap hardwareMap, Telemetry telemetry) {

        joint1 = hardwareMap.get(DcMotor.class, "joint1");
        joint2 = hardwareMap.get(Servo.class, "joint2");

        //Temp Commented out to test only Intake, remove this later
        //joint1.setPower(0d);
        //joint1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //joint2.setPosition(0.0d);

        // Initialize intake motor
        intake = hardwareMap.get(DcMotor.class, "intake");

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setJoint1(Telemetry tm, int target) {
        joint1.setTargetPosition(target);
        joint1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (joint1.isBusy()) {
            joint1.setPower(0.5d);
        }
        joint1.setPower(0d);
        joint1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void setJoint2(double target) {
        joint2.setPosition(target);
    }

    public void set(double pwr) {
        joint1.setPower(0.4d);
    }

    public int getJoint1() {
        return joint1.getCurrentPosition();
    }

    public double getJoint2() {
        return joint2.getPosition();
    }

    public void collect() {intake.setPower(INTAKE_POWER); }

    public void eject() {
        intake.setPower(-INTAKE_POWER);
    }

    public void stopIntake() {
        intake.setPower(0.0);
    }

}
