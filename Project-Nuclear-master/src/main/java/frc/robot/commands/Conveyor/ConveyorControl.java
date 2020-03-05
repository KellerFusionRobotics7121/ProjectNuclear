/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Conveyor;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Wrist;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Conveyor;

public class ConveyorControl extends CommandBase {
  private final Conveyor conveyor;

  public ConveyorControl(Conveyor conveyor) {
    this.conveyor = conveyor;
    addRequirements(this.conveyor);
  }

// Called just before this Command runs the first time
@Override
public void initialize() {
}

// Called repeatedly when this Command is scheduled to run
@Override
public void execute() {
    conveyor.setConveyorPwr(RobotContainer.logitechF310.leftTrigger()*RobotMap.Constants.Conveyor.OUT_PWR + RobotMap.Constants.Conveyor.IN_PWR*RobotContainer.logitechF310.buttonlb());
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
