package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Limelight;
import frc.robot.RobotMap.Constants;

public class AutoTarget extends CommandBase {

    private final Limelight limelight;
    private final Drive drive;

    private static float KpAim;         //Proportional control constant for angle
    private static float minAimCommand; //minimum command to make robot turn
    private static float targetHOffset; //desired horizontal offset of target
    private static double error;  //Error = Target - Actual
    private static double steeringAdjust;

    private double kP, kI, kD, kF;
    private double integral, derivative;
    private static double timeStep;

    public AutoTarget(Limelight limelight, Drive drive) {
        this.limelight = limelight;
        this.drive = drive;
        addRequirements(this.limelight);
        addRequirements(this.drive);
    }

    @Override
    public void initialize(){
        KpAim = 0.80f;
        minAimCommand = 0.05f;
        steeringAdjust = 0.00f;
        kP = RobotMap.Constants.Drive.kPID.kP;
        kI = RobotMap.Constants.Drive.kPID.kI;
        kD = RobotMap.Constants.Drive.kPID.kD;
        kF = RobotMap.Constants.Drive.kPID.kF;
        timeStep = RobotMap.Constants.Drive.kPID.TIME_STEP;

        // maxDistance = 144;
        // minDistance = 48;
        limelight.setLEDMode(Constants.Limelight.DEFAULT_LED);
        limelight.setCamMode(Constants.Limelight.VISION_PROCESSOR);
        drive.setRamp(0.00);
    }

    @Override
    public void execute() {
        error = limelight.getHOffset()/29.8; //Error as a percent
        integral += (error * timeStep); // increase by area under curve (dist * time)
        derivative = (error - drive.aimPreviousError) / timeStep; // velocity
        if (error != 0)
            steeringAdjust = (kP * error) + (kI * integral) + (kD * derivative) + error/Math.abs(error)*kF;
        else 
            steeringAdjust = 0;
        if(steeringAdjust >= 0.5) steeringAdjust = 0.5;
        else if(steeringAdjust <= -0.5) steeringAdjust = -0.5;
        steeringAdjust = drive.INVERT ? -steeringAdjust : steeringAdjust;
        drive.setArcade(0, steeringAdjust);
    
        drive.aimPreviousError = error;
    }

    @Override
    public boolean isFinished(){
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        limelight.setCamMode(Constants.Limelight.DRIVER_CAM);
        limelight.setLEDMode(Constants.Limelight.LED_OFF);

        drive.setRaw(0, 0);
    }
}