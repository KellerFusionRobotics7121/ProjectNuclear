/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drive;

// import java.lang.module.ModuleDescriptor.Requires;

// import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.*;

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


public class DriveControl extends CommandBase {
  private final Drive drive;
  private double effectiveFVA = 0.0f;
  private double effectiveFHA = 0.0f;
  private double effectiveSVA = 0.0f;
  private double effectiveSHA = 0.0f;

  public DriveControl(Drive drive) {
    this.drive = drive;
    addRequirements(this.drive);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    drive.setRamp(0.5);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    if(drive.INVERT) {
      effectiveFHA = -RobotContainer.Axes.driveHorizontalHighAxis();
      effectiveSHA = -RobotContainer.Axes.driveHorizontalLowAxis();
    }
    else {
      effectiveFHA = RobotContainer.Axes.driveHorizontalHighAxis();
      effectiveSHA = RobotContainer.Axes.driveHorizontalLowAxis();
    }
    effectiveFVA = RobotContainer.Axes.driveVerticalHighAxis();
    effectiveSVA = RobotContainer.Axes.driveVerticalLowAxis();
    double forwardVal = effectiveFVA * RobotMap.Constants.Drive.HIGH
                      + effectiveSVA  * RobotMap.Constants.Drive.LOW;
    
    double rotVal = effectiveFHA * RobotMap.Constants.Drive.HIGH
                  + effectiveSHA  * RobotMap.Constants.Drive.LOW;
    drive.setArcade(forwardVal, rotVal);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    drive.setRaw(0, 0);
  }
}