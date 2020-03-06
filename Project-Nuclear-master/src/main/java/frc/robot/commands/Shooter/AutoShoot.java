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

        limelight.setLEDMode(Constants.Limelight.DEFAULT_LED);
        limelight.setCamMode(Constants.Limelight.VISION_PROCESSOR);

    }

    @Override
    public void execute() {
        if(!limelight.hasValidTarget()) return;
        
        d = limelight.calculateDistance() - 15; 

        //hopefully this math works
        double radicalDenominator = 2 * (d * Math.tan(theta) - deltaH);
        velocity = d/Math.cos(theta) * Math.sqrt(Constants.Field.GRAVITY/radicalDenominator);

        velocity = velocity / Constants.Shooter.WHEEL_RADIUS * 30 / Math.PI; //Convert from in/s to rev/min
        velocity = (velocity/Constants.Shooter.MAX_RPM) + Constants.Shooter.VEL_CONST; //convert to percentage
        velocity = d<48 ? velocity + Constants.Shooter.CLOSE_CONST : velocity;
        shooter.setShooterPwr(velocity>0.8 ? 0.8 : velocity);
    }

    public boolean isFinished(){
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        limelight.setCamMode(Constants.Limelight.DRIVER_CAM);
        limelight.setLEDMode(Constants.Limelight.LED_OFF);

        shooter.setShooterPwr(0);
    }
}