/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public class RobotMap {

  public class Config {
    public static final boolean ENABLE_PNEUMATICS = true; 
    public static final boolean ENABLE_MOTORS     = true;
    
    public static final boolean LIFT_PID_ENABLED  = true;

    public static final boolean LIFT_REVERSED     = true;
    public static final boolean WRIST_REVERSED    = true;
  }

  public class Constants {

    public class Field {
      public static final double GRAVITY        = 386.088f;
      public static final double GOAL_HEIGHT    = 90.75f;
    }
    public class Intake {
      public static final double DEAD_PWR  = 0.00f;
      public static final double IN_PWR    = 0.60f;
      public static final double OUT_PWR   =-0.80f;
    }

    public class Color{
      public static final double ARM_PWR = 0.05f;
      public static final double WHEEL_PWR = 0.50f;
      public static final double DEAD_PWR = 0.00f;
      public static final double IDLE_PWR = 0.00f;
    }

    public class Shooter {
        public static final double IDLE_PWR       = 0.22f;
        public static final double SHOOT_PWR      = 0.50f;
        public static final double SHOOT_PWR_FAST = 0.70f;
        public static final double SHOOT_PWR_SLOW = 0.20f;
        public static final double SHOOT_IN_PWR   = 0.80f;
        public static final double DEAD_PWR       = 0.00f;

        public static final double HEIGHT         = 25.00f;//Inches
        public static final double ANGLE          = 66.00f;//Degrees
        public static final double X              = 0.00f; //Inches
        public static final double WHEEL_RADIUS   = 2.125f; //Inches
        public static final double MAX_RPM        = 5840.00; //(+/- 10%)
      }
    public class Limelight {
        public static final double HEIGHT     = 26.5f; //Inches
        public static final double ANGLE      = 37.00f; //Degrees
        public static final double X          = 0.00f;  //Inches
        public static final double kpDrive    = 0.01f;
        
        public static final int DEFAULT_LED    = 0;
        public static final int LED_OFF        = 1;
        public static final int LED_BLINK      = 2;
        public static final int LED_ON         = 3;

        public static final int VISION_PROCESSOR   = 0;
        public static final int DRIVER_CAM         = 1;
      }
    public class Wrist {
      public static final double IN_PWR       = 0.50f;
      public static final double OUT_PWR      =-0.20f;
      public static final double DEAD_PWR     = 0.08f;
    }
    public class Conveyor {
      public static final double IN_PWR       = 0.50f;
      public static final double OUT_PWR      =-0.80f;
      public static final double DEAD_PWR     = 0.00f;
    }
    public class Elevator {
      public static final double UP_POS       = 100f;
      public static final double DEAD_PWR     = 0.08f;
      public static final double UP_PWR       = 0.08f;
      public static final double DOWN_PWR     =-0.08f;

      public static final double TWO_UP_PWR   = 0.08f;
    }

    public class Drive {
      public static final double HIGH = 0.70f;
      public static final double LOW  = 0.60f;
      public static final double k    = 1;
      public static final double DPAD = 0.80f;
      public static final double MIN = 0.25f;
      public static final double AIM_MAX = 0.50f;
      public class kPID {
        public static final double kF = 0.20f;
        public static final double kP = 0.80f;
        public static final double kI = 0.10f;
        public static final double kD = 0.00f;
        public static final double TIME_STEP = 0.02f;
      }
    }
  }

  public class Sensors {

    public static final int CAMERA_WRIST = 0;
    public static final int CAMERA_LIFT = 1;

    public static final int LIFT_SWTICH_BOTTOM = 0;

    public static final int LED_STRIP = 2;
  }

  public class Motors {
    // CAN wired, so ports refer to Device ID
    public static final int LEFT_FRONT = 14;
    public static final int RIGHT_FRONT = 12;
    public static final int LEFT_FOLLOW = 15;
    public static final int RIGHT_FOLLOW = 13;

    public static final int WRIST_R = 10;
    public static final int WRIST_L = 11;

    public static final int INTAKE = 7;

    public static final int CONVEYOR_BACK = 4;

    public static final int SHOOTER_TOP = 3;
    public static final int SHOOTER_BOT = 2;

    public static final int STAGEONE = 6;
    public static final int STAGETWO = 1;
    public static final int STAGETWO_FOLLOW = 62;

    public static final int COLOR_ARM = 32;
    public static final int COLOR_WHEEL = 33;
  }

  public class Pneumatics {
    public static final int HATCH_UPWARD = 0;
    public static final int HATCH_DOWNWARD = 1;

    public static final int HATCH_FORWARD = 2;
    public static final int HATCH_REVERSE = 3;

    public static final int FRONT_JACK_FORWARD = 6;
    public static final int FRONT_JACK_REVERSE = 7;
    
    public static final int BACK_JACK_FORWARD = 4;
    public static final int BACK_JACK_REVERSE = 5;
  }
  
  public class xInputGamepad {

    public static final int LEFT_X_AXIS = 0;
    public static final int LEFT_Y_AXIS = 1;

    public static final int RIGHT_X_AXIS = 4;
    public static final int RIGHT_Y_AXIS = 5;
    
    public static final int LEFT_TRIGGER = 2;
    public static final int RIGHT_TRIGGER = 3;

    public static final int BTN_A = 1;
    public static final int BTN_B = 2;
    public static final int BTN_X = 3;
    public static final int BTN_Y = 4;
    public static final int BTN_LB = 5;
    public static final int BTN_RB = 6;
    public static final int BTN_BACK = 7;
    public static final int BTN_START = 8;
    public static final int BTN_LEFT_JOYSTICK = 9;
    public static final int BTN_RIGHT_JOYSTICK = 10;
  }

  public class FlightJoystick {
    public static final int X_AXIS = 0;
    public static final int Y_AXIS = 1;
    public static final int Z_AXIS = 2;
    public static final int SLIDER_AXIS = 3;
  }

  public class ButtonBoard {

    public static final int LEFT_Y_AXIS = 0;
    public static final int LEFT_X_AXIS = 1;
    
    public static final int LEFT_TRIGGER = 2;
    public static final int RIGHT_TRIGGER = 3;

    public static final int BTN_A = 1;
    public static final int BTN_B = 2;
    public static final int BTN_X = 3;
    public static final int BTN_Y = 4;  
    
    public static final int BTN_LB = 5;
    public static final int BTN_RB = 6;

    public static final int BTN_SHARE = 7;
    public static final int BTN_OPTIONS = 8;

    public static final int BTN_SL = 9;
    public static final int BTN_SR = 10;
  }
}