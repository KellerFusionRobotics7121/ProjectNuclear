/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.commands.Wrist.WristControl;;

/**
 * Add your docs here.
 */

public class Wrist extends SubsystemBase {
  
  private Talon wristL;
  private Talon wristR;
  public Wrist() {
    wristL = new Talon(RobotMap.Motors.WRIST_L);
    wristR = new Talon(RobotMap.Motors.WRIST_R);
    wristR.setSafetyEnabled(true);
    wristL.setSafetyEnabled(true);
    setDefaultCommand(new WristControl(this));
  }
  
  public void setWristPwr(double val) {
    wristL.set(val);
    wristR.set(-1 * val);
  }
}
