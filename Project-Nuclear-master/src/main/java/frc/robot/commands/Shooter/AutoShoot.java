package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotMap.Constants;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

public class AutoShoot extends CommandBase{
    private final Limelight limelight;
    private final Shooter shooter;

    public double d;         //distance between robot and goal
    public double velocity;  //initial velocity (inches/second)
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

        deltaH = Constants.Field.GOAL_HEIGHT 
               - Constants.Shooter.HEIGHT;
        theta = Math.toRadians(Constants.Shooter.ANGLE);
        limelight.setCamMode(Constants.Limelight.VISION_PROCESSOR);

    }

    @Override
    public void execute() {
        if(!limelight.hasValidTarget()) return;
        
        d = limelight.calculateDistance() + 29.25; 

        //hopefully this math works
        double radicalDenominator = 2 * (d * Math.tan(theta) - deltaH);
        velocity = d/Math.cos(theta) * Math.sqrt(Constants.Field.GRAVITY/radicalDenominator);

        velocity = velocity / Constants.Shooter.WHEEL_RADIUS * 30 / Math.PI; //Convert from in/s to rev/min
        velocity /= Constants.Shooter.MAX_RPM; //convert to percentage


        shooter.setShooterPwr(velocity);
    }

    public boolean isFinished(){
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        limelight.setCamMode(Constants.Limelight.DRIVER_CAM);
        shooter.setShooterPwr(0);
    }
}