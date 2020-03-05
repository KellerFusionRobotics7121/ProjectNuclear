/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.Controller.CustomGamepad;

import frc.robot.subsystems.Drive;
import frc.robot.commands.Drive.DriveControl;
import frc.robot.commands.Drive.Invert;
import frc.robot.commands.Drive.AutoTarget;

import frc.robot.subsystems.Intake;
import frc.robot.commands.Intake.IntakeIn;
import frc.robot.commands.Intake.IntakeOut;

import frc.robot.subsystems.Conveyor;
import frc.robot.commands.Conveyor.ConveyorControl;

import frc.robot.subsystems.Wrist;
import frc.robot.commands.Wrist.WristControl;

import frc.robot.subsystems.Shooter;
import frc.robot.commands.Shooter.*;

import frc.robot.subsystems.StageOne;
import frc.robot.subsystems.StageTwo;
import frc.robot.commands.Elevator.*;

import frc.robot.subsystems.Limelight;

import frc.robot.subsystems.ColorSpinner;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  public static CustomGamepad logitechF310;
  public static Drive drive = new Drive();
  public static Intake intake = new Intake();
  public static Shooter shooter = new Shooter();
  public static Wrist wrist = new Wrist();
  public static Conveyor conveyor = new Conveyor();
  public static Limelight limelight = new Limelight();
  public static StageOne stageOne = new StageOne();
  public static StageTwo stageTwo = new StageTwo();
  // public static ColorSpinner colorSpinner = new ColorSpinner();
  // The robot's subsystems and commands are defined here...




  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    logitechF310 = new CustomGamepad(0);
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    logitechF310.y.whileHeld(new AutoTarget(limelight, drive));
    logitechF310.b.whileHeld(new AutoShoot(limelight, shooter));
    // logitechF310.x.whileHeld(new Shoot(shooter));
    // logitechF310.y.whileHeld(new AutoShoot(limelight, shooter));
    logitechF310.righJoystickButton.whileHeld(new IntakeIn(intake));
    logitechF310.lefJoystickButton.whileHeld(new IntakeOut(intake));
    logitechF310.a.whileHeld(new StageTwoUp(stageTwo));
    logitechF310.start.whileHeld(new StageOneUp(stageOne));
    logitechF310.back.whenReleased(new StageOneDown(stageOne));


    // logitechF310.start.whenPressed(new Invert(drive));
    // if (logitechF310.rightTriggerPressed()) new WristControl(wrist);
    // logitechF310.rb.whileHeld(new WristControl(wrist));
    // if (logitechF310.leftTriggerPressed())  new StageTwoUp(stageTwo);
    // logitechF310.b.whileHeld(new AutoTarget(limelight, drive));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  //   // An ExampleCommand will run in autonomous
  //   //  return new DriveControl();
  // }

  public static class Axes {

    public static double driveVerticalLowAxis() {
    return RobotContainer.logitechF310.rightStickY() 
         - RobotContainer.logitechF310.dpad.vertical() * RobotMap.Constants.Drive.DPAD;
    }

    public static double driveVerticalHighAxis() {
      return RobotContainer.logitechF310.leftStickY();
    }

    public static double driveHorizontalLowAxis() {
      return RobotContainer.logitechF310.rightStickX() 
           + RobotContainer.logitechF310.dpad.horizontal() * RobotMap.Constants.Drive.DPAD;
    }

    public static double driveHorizontalHighAxis() {
      return  RobotContainer.logitechF310.leftStickX();
    }
  }
}
