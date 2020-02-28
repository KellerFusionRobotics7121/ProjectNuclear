package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

public class AutoShoot extends CommandBase{
    private final Limelight limelight;
    private final Shooter shooter;

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
        shooter.d = (float) limelight.calculateDistance(); 

        //hopefully this math works
        shooter.velocity = (float) Math.sqrt(shooter.g*shooter.d / shooter.denominator);

        shooter.setShooterPwr(shooter.velocity);
    }

    public boolean isFinished(){
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        shooter.setShooterPwr(0);
    }
}