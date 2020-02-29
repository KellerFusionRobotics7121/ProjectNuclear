/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Color;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.ColorSpinner;
import frc.robot.subsystems.Wrist;
import frc.robot.RobotContainer;

public class SetDesiredColor extends CommandBase {
  private final ColorSpinner colorSpinner;
  private final String color;

  public SetDesiredColor(ColorSpinner colorSpinner, String color) {
    this.colorSpinner = colorSpinner;
    this.color = color;
    addRequirements(this.colorSpinner);

  }

// Called just before this Command runs the first time
@Override
public void initialize() {
}

// Called repeatedly when this Command is scheduled to run
@Override
public void execute() {
    colorSpinner.setDesiredColor(color);
  
}


  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
  }
}
