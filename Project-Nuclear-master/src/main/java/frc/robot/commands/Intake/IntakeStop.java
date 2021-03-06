/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Intake.IntakeState;
import frc.robot.subsystems.Intake;

public class IntakeStop extends CommandBase {
  private final Intake intake;

  public IntakeStop(Intake intake) {
    this.intake = intake;
    addRequirements(this.intake);
  }

// Called just before this Command runs the first time
@Override
public void initialize() {
  intake.intakeState = IntakeState.STOPPED;
}

// Called repeatedly when this Command is scheduled to run
@Override
public void execute() {
  //Robot.intake.setIntakePwr(IntakeState.IN);
    intake.setIntakePwr(RobotMap.Constants.Intake.DEAD_PWR);
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
