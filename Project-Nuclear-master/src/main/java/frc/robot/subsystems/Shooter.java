/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.commands.Shooter.*;
import frc.robot.commands.Intake.*;

/**
 * Add your docs here.
 */

public class Shooter extends SubsystemBase {

  public enum IntakeState {IN, OUT, STOPPED};
  
  private WPI_TalonSRX shooterBot, shooterTop;

  public Shooter() {
    shooterTop = new WPI_TalonSRX(RobotMap.Motors.SHOOTER_TOP);
    shooterBot = new WPI_TalonSRX(RobotMap.Motors.SHOOTER_BOT);
    setDefaultCommand(new ShootIdle(this));
  }

  // shooter controls
  
  public void setShooterPwr(double val) {
    shooterBot.set(val);
    shooterTop.set(-1 * val);
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