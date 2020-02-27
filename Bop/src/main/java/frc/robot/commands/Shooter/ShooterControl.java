/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.RobotMap;
import frc.robot.subsystems.*;
import frc.robot.commands.Intake.*;

public class ShooterControl extends CommandBase {
    private Shooter shooter;

    public ShooterControl(Shooter shooter) {
        this.shooter = shooter;
        addRequirements(this.shooter);
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {  
        double power;
        if      (RobotContainer.logitechF310.y.get())   power = RobotMap.Constants.Shooter.IN_SHOOT;
        else if (RobotContainer.logitechF310.x.get())   power = RobotMap.Constants.Shooter.OUT_SHOOT;
        else                                            power = RobotMap.Constants.Shooter.IDLE_PWR;
        
        shooter.setShooterPwr(power);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        shooter.setShooterPwr(RobotMap.Constants.Shooter.IDLE_PWR);
    }
}
