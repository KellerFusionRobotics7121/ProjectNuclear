package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Limelight;
import frc.robot.RobotMap;

public class AutoTarget extends CommandBase {

    private final Limelight limelight;
    private final Drive drive;

    private static float KpAim;         //Proportional control constant for angle
    private static float minAimCommand; //minimum command to make robot turn
    private static float targetHOffset; //desired horizontal offset of target
    private static float headingError;  //Error = Target - Actual
    private static float steeringAdjust;

    private static float maxDistance;   //maximum distance the robot can shoot from
    private static float minDistance;   //minimum distance the robot can shoot from

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
        maxDistance = 144;
        minDistance = 48;
        steeringAdjust = 0.00f;
    }

    @Override
    public void execute() {
        //this.adjustAngle();
        float a = (float) (0.50*(0.60 -  RobotMap.Constants.Drive.MIN));
        headingError = (float) limelight.getHOffset();
        steeringAdjust = (float)(-a*Math.cos(2*Math.PI/29.8*headingError)+a+RobotMap.Constants.Drive.MIN);
        if (headingError > 0)
            drive.setArcade(0, steeringAdjust);
        else   
            drive.setArcade(0, -steeringAdjust);
    }

    /**
     * moves robot forwards or backwards until it is within the range the robot can shoot from
     * @return true if the robot is within range. false otherwise
     */
    private boolean getWithinRange(){ 
        Double kPDrive = RobotMap.Constants.Limelight.kpDrive;
        float distance = (float) limelight.calculateDistance();
        if(distance < minDistance){
            drive.setArcade((distance - minDistance) * kPDrive, 0);
            return false;
        }
        else if(distance > maxDistance){
            drive.setArcade((distance - maxDistance) * kPDrive,0);
            return false;
        }
        return true;
    }

    /**
     * Adjusts the angle of the robot
     */
    private boolean adjustAngle(){
        double d2 = Math.abs(RobotMap.Constants.Limelight.X - RobotMap.Constants.Shooter.X);   //horizontal distance between limelight and shooter (inches)
        targetHOffset = (float) Math.atan(Math.toRadians(d2/limelight.calculateDistance()));
        headingError = (float) limelight.getHOffset() - targetHOffset;
        drive.setArcade(0, KpAim*headingError/29.8);
        //hopefully this math is correct
 
        // headingError = targetHOffset - (float) limelight.getHOffset();  //Error = Target - actual
        // float steeringAdjust = 0.0f;
        // if (Math.abs(headingError) > 5.0)
        // {
        //     steeringAdjust = KpAim*headingError;
        // }
        // else if (Math.abs(headingError) > 2.0)
        // {
        //     steeringAdjust = KpAim*headingError + minAimCommand;
        // }
        // //retrieve rotation value and manipulate it with steeringAdjust


        return Math.abs(targetHOffset - (float) limelight.getHOffset()) < 3;
    }

    @Override
    public boolean isFinished(){
        // float distance = (float) limelight.calculateDistance();
        // return Math.abs(headingError) < 3 &&
        // distance > minDistance && distance < maxDistance;
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        drive.setRaw(0, 0);
    }
}