/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.Drive.DriveControl;



/**
 * Add your docs here.
 */
public class Drive extends SubsystemBase {

  private Talon leftPWM;
  private Talon rightPWM;
  private DifferentialDrive drive;
  public Boolean INVERT = false;

  public Drive() {
    leftPWM = new Talon(RobotMap.Motors.LEFT_DRIVE);
    rightPWM = new Talon(RobotMap.Motors.RIGHT_DRIVE);

    leftPWM.setSafetyEnabled(true);
    rightPWM.setSafetyEnabled(true);

    drive = new DifferentialDrive(leftPWM, rightPWM);
    setDefaultCommand(new DriveControl(this));
  }

  public void setRaw(double leftVal, double rightVal) {
    if(!RobotMap.Config.ENABLE_MOTORS) return;
    
    drive.tankDrive(leftVal, rightVal);
  }

  public void setArcade(double xSpeed, double zRotation) {
    if(!RobotMap.Config.ENABLE_MOTORS) return;

    drive.arcadeDrive(xSpeed, zRotation);
  }
}