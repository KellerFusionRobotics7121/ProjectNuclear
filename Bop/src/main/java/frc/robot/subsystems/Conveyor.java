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
import frc.robot.commands.Conveyor.ConveyorStop;

/**
 * Add your docs here.
 */
public class Conveyor extends SubsystemBase {
  private Talon conveyorbk;
  private Talon conveyorFr;

  public Conveyor() {
    conveyorbk = new Talon(RobotMap.Motors.CONVEYOR_BACK);
    conveyorFr = new Talon(RobotMap.Motors.CONVEYOR_FRONT);

    conveyorbk.setSafetyEnabled(true);
    conveyorFr.setSafetyEnabled(true);
    setDefaultCommand(new ConveyorStop(this));
  }

  public void setConveyorPwr(double val) {
    conveyorbk.set(val);
    conveyorFr.set(-1 * val);
  }
}