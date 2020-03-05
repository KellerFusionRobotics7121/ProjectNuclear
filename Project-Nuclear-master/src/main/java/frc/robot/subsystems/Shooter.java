/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
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
  
  private WPI_TalonFX shooterBot, shooterTop;
  public double shooterSpeed = 0.0;
  public Shooter() {
    shooterTop = new WPI_TalonFX(RobotMap.Motors.SHOOTER_TOP);
    shooterBot = new WPI_TalonFX(RobotMap.Motors.SHOOTER_BOT);

    shooterTop.configFactoryDefault();
    shooterBot.configFactoryDefault();

    shooterTop.setInverted(true);
    shooterBot.setInverted(true);

    shooterTop.setSensorPhase(true);
    shooterBot.setSensorPhase(true);

    setDefaultCommand(new ShootIdle(this));
  }

  // shooter controls
  
  public void setShooterPwr(double val) {
    shooterBot.set(ControlMode.PercentOutput, val);
    shooterTop.set(ControlMode.PercentOutput, val);
    shooterSpeed = val;
  }

  public void setOnePointShooterPwr(double val){
    shooterBot.set(ControlMode.PercentOutput, val);
    shooterTop.set(ControlMode.PercentOutput, val*0.45);
    shooterSpeed = val;
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