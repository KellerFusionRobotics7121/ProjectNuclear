package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

public class AutoShoot extends CommandBase{
    private final Limelight limelight;
    private final Shooter shooter;

    float g;    //gravity (inches/second^2)
    //gravity is in nches/second^2 since the units of the other variables are in inches
    float h1;   //height between ground and exit point of shooter (inches)
    float h2;   //height between ground and goal (inches)
    float deltaH;   //difference in between h2 and h1 (inches)
    float theta;    //angle of shooter (radians)
    float d; //distance between robot and goal
    float velocity; //initial velocity (inches/second)
    
    
    public AutoShoot(Limelight limelight, Shooter shooter) {
        this.limelight = limelight;
        this.shooter = shooter;
        addRequirements(this.limelight);
        addRequirements(this.shooter);
    }

    @Override
    public void initialize(){
        g = 386.09f;
        h1 = 25;
        h2 = 98.25f;
        deltaH = h2 - h1;
        theta = (float) Math.toRadians(66);
        
    }

    @Override
    public void execute() {
        d = (float) limelight.calculateDistance(); 

        //hopefully this math works
        float denominator = (float) ((deltaH - Math.tan(theta)) * 2 * Math.cos(theta));
        velocity = (float) Math.sqrt(g*d / denominator);

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