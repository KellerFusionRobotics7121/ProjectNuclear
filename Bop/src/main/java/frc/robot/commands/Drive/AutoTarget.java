package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Limelight;

public class AutoTarget extends CommandBase {

    private final Limelight limelight;
    private final Drive drive;

    private static float KpAim;         //Propoartional control constant for angle
    private static float minAimCommand; //minimum command to make robot turn
    private static float targetHOffset; //desired horizontal offset of target
    private static float headingError;  //Error = Target - Actual

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
        KpAim = 0.1f;
        minAimCommand = 0.05f;
        maxDistance = 144;
        minDistance = 48;
    }

    @Override
    public void execute() {
        if(this.adjustAngle())
            this.getWithinRange();
    }

    /**
     * moves robot forwards or backwards until it is within the range the robot can shoot from
     * @return true if the robot is within range. false otherwise
     */
    private boolean getWithinRange(){ 
        float kPDrive = 0.5f;
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
        float d2 = 8;   //horizontal distance between robot and shooter (inches)
        //hopefully this math is correct
        targetHOffset = (float) Math.atan(Math.toRadians(d2/limelight.calculateDistance())); 
        headingError = targetHOffset - (float) limelight.getHOffset();  //Error = Target - actual
        float steeringAdjust = 0.0f;
        if (Math.abs(headingError) > 5.0)
        {
            steeringAdjust = KpAim*headingError;
        }
        else if (Math.abs(headingError) > 2.0)
        {
            steeringAdjust = KpAim*headingError + minAimCommand;
        }
        //retrieve rotation value and manipulate it with steeringAdjust
        drive.setRaw(steeringAdjust, -steeringAdjust);

        return Math.abs(targetHOffset - (float) limelight.getHOffset()) < 3;
    }

    @Override
    public boolean isFinished(){
        float distance = (float) limelight.calculateDistance();
        return Math.abs(headingError) < 3 &&
        distance > minDistance && distance < maxDistance;
    }

    @Override
    public void end(boolean interrupted) {
        drive.setRaw(0, 0);
    }
    
}