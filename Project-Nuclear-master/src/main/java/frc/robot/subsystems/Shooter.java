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

  public float g;    //gravity (inches/second^2)
  //gravity is in inches/second^2 since the units of the other variables are in inches
  public float h1;   //height between ground and exit point of shooter (inches)
  public float h2;   //height between ground and goal (inches)
  public float deltaH;   //difference in between h2 and h1 (inches)
  public float theta;    //angle of shooter (radians)
  public float d; //distance between robot and goal
  public float velocity; //initial velocity (inches/second)
  public float denominator;
  
  public Shooter() {
    shooterTop = new WPI_TalonSRX(RobotMap.Motors.SHOOTER_TOP);
    shooterBot = new WPI_TalonSRX(RobotMap.Motors.SHOOTER_BOT);
    setDefaultCommand(new ShootIdle(this));
    g = 386.09f;
    h1 = 25;
    h2 = 98.25f;
    deltaH = h2 - h1;
    theta = (float) Math.toRadians(66);
    denominator = (float) ((deltaH - Math.tan(theta)) * 2 * Math.cos(theta));
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