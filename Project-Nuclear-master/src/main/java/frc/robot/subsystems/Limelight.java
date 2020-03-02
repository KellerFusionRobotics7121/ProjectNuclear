package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.RobotMap;

public class Limelight extends SubsystemBase{
    
    


    private NetworkTable table;

    NetworkTableEntry tx;

    public Limelight() {
        table = NetworkTableInstance.getDefault().getTable("limelight");
        tx = table.getEntry("tx");
    }

    // Best Contour information

    /**
     * If the Limelight has any targets
     * @return true if the limelight has targets. False oterwise.
     */
    public boolean hasValidTarget() {
        return table.getEntry("tv").getDouble(0) == 1 ? true : false;
    }

     /**
     * Returns horizontal offset to target
     * @return horizontal offset (-29.8 to 29.8 degrees)
     */
    public double getHOffset() {
        return table.getEntry("tx").getDouble(0);
    }

    /**
     * Returns vertical offset to target
     * @return vertical offset (-24.85 to 24.85 degrees)
     */
    public double getVOffset() {
        return table.getEntry("ty").getDouble(0);
    }

    /**
     * Returns percent of image that is the target area
     * @return target area (0% to 100%)
     */
    public double getTargetArea() {
        return table.getEntry("ta").getDouble(0);
    }

    /**
     * Returns the skew of the target
     * @return the skew of the target in degrees (-90, 0)
     */
    public double getSkew() {
        return table.getEntry("ts").getDouble(0);
    }

    /**
     * Returns shortest sidelength of rough bounding box
     * @return shortest sidelength in pixels
     */
    public double getShortSide() {
        return table.getEntry("tshort").getDouble(0);
    }

    /**
     * Returns longest sidelength of rough bounding box
     * @return longest sidelength in pixels
     */
    public double getLongSide() {
        return table.getEntry("tlong").getDouble(0);
    }

    /**
     * Returns horizontal sidelength of rough bounding box
     * @return horizontal sidelength in pixels
     */
    public double getHSide() {
        return table.getEntry("thor").getDouble(0);
    }

    /**
     * Returns vertical sidelength of rough bounding box
     * @return vertical sidelength in pixels
     */
    public double getVSide() {
        return table.getEntry("tvert").getDouble(0);
    }

    /**
     * Returns the current pipline of the limelight
     * @return the limelight's pipeline
     */
    public double getPipeline() {
        return table.getEntry("getpipe").getDouble(0);
    }

    // Camera Controls

    /**
     * Sets the LED mode for the limelight to use
     * @param mode  the LED mode
     *              0 = default LED mode. Given by pipeline.
     *              1 = force LED off
     *              2 = force LED to blink
     *              3 = force LED on
     */ 
    public void setLEDMode(int mode) {
        if (mode < 0) {
            mode = 0;
            throw new IllegalArgumentException("LED mode can't be less than 0");
        } else if (mode > 3) {
            mode = 3;
            throw new IllegalArgumentException("LED mode can't be greater than 3");
        }
        table.getEntry("ledMode").setNumber(mode);
    }

    /**
     * Sets the cameramode for the limelight to use
     * @param mode  the camera mode
     *              0 = vision processor
     *              1 = driver camera
     */
    public void setCamMode(int mode) {
        if (mode < 0) {
            mode = 0;
            throw new IllegalArgumentException("Cam mode can't be less than 0");
        } else if (mode > 1) {
            mode = 1;
            throw new IllegalArgumentException("Cam mode can't be greater than 1");
        }
        table.getEntry("camMode").setNumber(mode);
    }

    /**
     * Sets the pipeline that the limelight will use
     * @param pipeline the pipeline for the limelight to use
     */
    public void setPipeline(int pipeline) {
        if (pipeline < 0) {
            pipeline = 0;
            throw new IllegalArgumentException("Pipeline can't be less than 0");
        } else if (pipeline > 9) {
            pipeline = 9;
            throw new IllegalArgumentException("Pipeline can't be greater than 9");
        }
        table.getEntry("ledMode").setNumber(pipeline);
    }

    // Other methods
    /**
     * Calculates and returns the distance between the tobot and the target
     * @return distance between robot and target in inches
     */
    public double calculateDistance() {
        double angle = RobotMap.Constants.Limelight.ANGLE;      // angle between horizontal and limelight (degrees)
        double height = RobotMap.Constants.Limelight.HEIGHT;    // height between ground and limelight (inches)
        double h2 = RobotMap.Constants.Field.GOAL_HEIGHT;       // height between ground and middle of goal (inches)
        double a2 = getVOffset();     // angle between limelight and target (degrees)

        return  (h2-height) / Math.tan(Math.toRadians(angle+a2));
    }
}