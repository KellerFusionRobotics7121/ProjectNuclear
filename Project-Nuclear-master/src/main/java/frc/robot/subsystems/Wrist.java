/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.commands.Wrist.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;
/**
 * Add your docs here.
 */

public class Wrist extends SubsystemBase {
  
  private VictorSPX wristL;
  private VictorSPX wristR;
  public Wrist() {
    wristL = new VictorSPX(RobotMap.Motors.WRIST_L);
    wristR = new VictorSPX(RobotMap.Motors.WRIST_R);
    // wristR.setSafetyEnabled(true);
    // wristL.setSafetyEnabled(true);
    setDefaultCommand(new WristControl(this));
  }

  // intake controls
  
  public void setWristPwr(double val) {
    wristL.set(ControlMode.PercentOutput, val);
    wristR.set(ControlMode.PercentOutput, -1 * val);
  }

  // NOTE: do not use - slow refresh rate
  // public void setIntakePwr(IntakeState state) {
  //   switch(state) {
  //     case IN: setIntakePwr(RobotMap.Constants.INTAKE_IN_PWR);
  //     case OUT: setIntakePwr(RobotMap.Constants.INTAKE_OUT_PWR);
  //     case STOPPED: setIntakePwr(0.0f);
  //   }

  //   intakeState = state;
  // }
}
