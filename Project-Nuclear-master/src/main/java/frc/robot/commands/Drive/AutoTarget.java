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
    private static double previousError;
    private static double steeringAdjust;

    private double kP, kI, kD;
    private double integral, derivative;
    private static double timeStep = 0.02f;

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
        kP = 0.45f;
        kI = 0.54f;
        kD = 0.00f;

        // maxDistance = 144;
        // minDistance = 48;
        limelight.setCamMode(Constants.Limelight.VISION_PROCESSOR);

    }

    @Override
    public void execute() {
<<<<<<< HEAD
        //this.adjustAngle();
        float a = (float) (0.50*(0.45 -  RobotMap.Constants.Drive.MIN));
        headingError = (float) limelight.getHOffset();
        steeringAdjust = (float)(-a*Math.cos(2*Math.PI/29.8*headingError)+a+RobotMap.Constants.Drive.MIN);
        if (headingError > 0.05)
            drive.setArcade(0, steeringAdjust);
        else if (headingError < 0.05)
            drive.setArcade(0, -steeringAdjust);
        else {
            drive.setNeutral("Brake");
            drive.setRaw(0, 0);
        }
    }
=======
        if(!limelight.hasValidTarget()) return;
        // float a = (float) (0.50*(0.60 -  RobotMap.Constants.Drive.MIN));
        // error = (float) limelight.getHOffset();
        // steeringAdjust = (float)(-a*Math.cos(2*Math.PI/29.8*error)+a+RobotMap.Constants.Drive.MIN);
        // if (error > 0)
        //     drive.setArcade(0, steeringAdjust);
        // else   
        //     drive.setArcade(0, -steeringAdjust);
>>>>>>> 7bec52e15c712504891aff1d7de59beac65ebbdd

        error = limelight.getHOffset()/29.8; //Error as a percent
        integral += (error * timeStep); // increase by area under curve (dist * time)
        derivative = (error - previousError) / timeStep; // velocity

        steeringAdjust = (kP * error) + (kI * integral) + (kD * derivative);

        if(steeringAdjust >= 0.8) steeringAdjust = 0.8;
        else if(steeringAdjust <= -.08) steeringAdjust = -.08;

        drive.setArcade(0, steeringAdjust);
    
        previousError = error;
    }

    // /**
    //  * moves robot forwards or backwards until it is within the range the robot can shoot from
    //  * @return true if the robot is within range. false otherwise
    //  */
    // private boolean getWithinRange(){ 
    //     Double kPDrive = RobotMap.Constants.Limelight.kpDrive;
    //     float distance = (float) limelight.calculateDistance();
    //     if(distance < minDistance){
    //         drive.setArcade((distance - minDistance) * kPDrive, 0);
    //         return false;
    //     }
    //     else if(distance > maxDistance){
    //         drive.setArcade((distance - maxDistance) * kPDrive,0);
    //         return false;
    //     }
    //     return true;
    // }

    /**
     * Adjusts the angle of the robot
     */
    private boolean adjustAngle(){
        double d2 = Math.abs(RobotMap.Constants.Limelight.X - RobotMap.Constants.Shooter.X);   //horizontal distance between limelight and shooter (inches)
        targetHOffset = (float) Math.atan(Math.toRadians(d2/limelight.calculateDistance()));
        error = (float) limelight.getHOffset() - targetHOffset;
        drive.setArcade(0, KpAim*error/29.8);
        //hopefully this math is correct
 
        // error = targetHOffset - (float) limelight.getHOffset();  //Error = Target - actual
        // float steeringAdjust = 0.0f;
        // if (Math.abs(error) > 5.0)
        // {
        //     steeringAdjust = KpAim*error;
        // }
        // else if (Math.abs(error) > 2.0)
        // {
        //     steeringAdjust = KpAim*error + minAimCommand;
        // }
        // //retrieve rotation value and manipulate it with steeringAdjust


        return Math.abs(targetHOffset - (float) limelight.getHOffset()) < 3;
    }

    @Override
    public boolean isFinished(){
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        limelight.setCamMode(Constants.Limelight.DRIVER_CAM);
        drive.setRaw(0, 0);
    }
}