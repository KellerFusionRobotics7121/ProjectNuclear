package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

public class AutoShoot extends CommandBase{
    private final Limelight limelight;
    private final Shooter shooter;

    public float d;         //distance between robot and goal
    public float velocity;  //initial velocity (inches/second)
    //constant based on RobotMap
    public double deltaH;   //difference in between h2 and h1 (inches)
    public double theta;    //angle of shooter (radians)

    public AutoShoot(Limelight limelight, Shooter shooter) {
        this.limelight = limelight;
        this.shooter = shooter;
        addRequirements(this.limelight);
        addRequirements(this.shooter);
    }

    @Override
    public void initialize(){
        
    }

    @Override
    public void execute() {
        d = (float) limelight.calculateDistance(); 

        //hopefully this math works
        deltaH = RobotMap.Constants.Field.GOAL_HEIGHT 
               - RobotMap.Constants.Shooter.HEIGHT;
        theta = (float) Math.toRadians(RobotMap.Constants.Shooter.ANGLE);
        velocity = (float) Math.sqrt(RobotMap.Constants.Field.GRAVITY*Math.pow(d, 2) / (2*(deltaH - d * Math.tan(theta))));

        shooter.setShooterPwr(velocity);
    }

    public boolean isFinished(){
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        shooter.setShooterPwr(0);
    }
}