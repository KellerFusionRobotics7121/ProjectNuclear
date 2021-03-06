/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Wrist;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Wrist;
import frc.robot.RobotContainer;

public class WristControl extends CommandBase {
  private final Wrist wrist;

  public WristControl(Wrist wrist) {
    this.wrist = wrist;
    addRequirements(this.wrist);
  }

// Called just before this Command runs the first time
@Override
public void initialize() {
}

// Called repeatedly when this Command is scheduled to run
@Override
public void execute() {
  wrist.setWristPwr(RobotContainer.logitechF310.rightTrigger()*RobotMap.Constants.Wrist.OUT_PWR + RobotMap.Constants.Wrist.IN_PWR*RobotContainer.logitechF310.buttonrb());
}

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
  }
}
