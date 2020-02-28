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
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;
/**
 * Add your docs here.
 */

public class StageTwo extends SubsystemBase {
  CANCoder elePos = new CANCoder(RobotMap.Motors.STAGETWO);

  private VictorSPX stageTwo;
  private VictorSPX stageTwo_Follower;
 
  public StageTwo() {
    stageTwo = new VictorSPX(RobotMap.Motors.STAGETWO);
    stageTwo_Follower = new VictorSPX(RobotMap.Motors.STAGETWO_FOLLOW);
    //stageTwo.setSafetyEnabled(true);
  }

  // intake controls
  
  public void setPwr(double val) {
    stageTwo.set(ControlMode.PercentOutput,val);
    stageTwo.set(ControlMode.PercentOutput, -1*val);
  }
}
