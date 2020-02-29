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
import frc.robot.commands.Color.IdleColor;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
/**
 * Add your docs here.
 */

public class ColorSpinner extends SubsystemBase {
  public String colorValue; //desired color value
  private VictorSPX colorArm;
  private VictorSPX colorWheel;

  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  private final ColorMatch m_colorMatcher = new ColorMatch();

  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.179, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  public ColorSpinner() {
    colorArm = new VictorSPX(RobotMap.Motors.COLOR_ARM);
    colorWheel = new VictorSPX(RobotMap.Motors.COLOR_WHEEL);
    // wristR.setSafetyEnabled(true);
    // wristL.setSafetyEnabled(true);

    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);

    setDefaultCommand(new IdleColor(this));
  }

  // intake controls
  
  public void setArmPwr(double val) {
    colorArm.set(ControlMode.PercentOutput, val);
  }

  public void setWheelPwr(double val){
    colorWheel.set(ControlMode.PercentOutput, val);
  }

  public boolean getArmLimit() {
    //limitswitch code
    return false;
  }
  public void setDesiredColor(String color) {
    colorValue = color;
  }
  public String getColor(){
    Color detectedColor = m_colorSensor.getColor();
    String colorString;
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    if (match.color == kBlueTarget) {
      colorString = "blue";
    } else if (match.color == kRedTarget) {
      colorString = "red";
    } else if (match.color == kGreenTarget) {
      colorString = "green";
    } else if (match.color == kYellowTarget) {
      colorString = "yellow";
    } else {
      colorString = "unknown";
    }
    return colorString;
  }
}
