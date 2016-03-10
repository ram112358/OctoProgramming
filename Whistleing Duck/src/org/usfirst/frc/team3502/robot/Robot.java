
package org.usfirst.frc.team3502.robot;

import org.usfirst.frc.team3502.robot.commands.Auton.autoSetGyroAndThrottle;
import org.usfirst.frc.team3502.robot.commands.Duck.BothDuckIt;
import org.usfirst.frc.team3502.robot.subsystems.BottomDuck;
import org.usfirst.frc.team3502.robot.subsystems.Hooker;
import org.usfirst.frc.team3502.robot.subsystems.Intake;
import org.usfirst.frc.team3502.robot.subsystems.LeftDrive;
import org.usfirst.frc.team3502.robot.subsystems.PTOShifting;
import org.usfirst.frc.team3502.robot.subsystems.RightDrive;
import org.usfirst.frc.team3502.robot.subsystems.TopDuck;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final BottomDuck bottomDuck = new BottomDuck();
	public static final Hooker hooker = new Hooker();
	public static final Intake intake = new Intake();
	public static final LeftDrive leftDrive = new LeftDrive();
	public static final PTOShifting shifting = new PTOShifting();
	public static final RightDrive rightDrive = new RightDrive();
	public static final TopDuck topDuck = new TopDuck();
	public static OI oi;
	public static final String cameraName = "cam0";

    Command autonomousCommand;
    SendableChooser chooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
        chooser = new SendableChooser();
        
        chooser.addDefault("Don't Go", new autoSetGyroAndThrottle(0.0, 0.0));
        chooser.addObject("Low Bar (Slow w/ Gyro)", new autoSetGyroAndThrottle(0.0, 0.5));
        chooser.addObject("Ramparts or Rough Terrain (Fast w/ Gyro)", new autoSetGyroAndThrottle(0.0, 1.0));
        chooser.addObject("Portcullus", new autoSetGyroAndThrottle(0.0, 0.0)); //Change this command when the right one is written
        SmartDashboard.putData("Auto mode", chooser);

    	NetworkTable.getTable("Preferences").putNumber("Sec Run", 0.11);
    	NetworkTable.getTable("Preferences").putNumber("Sec Brake", 0.0);
    	NetworkTable.getTable("Preferences").putNumber("kP", 0.5);
    	NetworkTable.getTable("Preferences").putNumber("groundTopPos", 0);
    	NetworkTable.getTable("Preferences").putNumber("groundBottomPos", 0);
    	NetworkTable.getTable("Preferences").putNumber("upTopPos", 0);
    	NetworkTable.getTable("Preferences").putNumber("upBottomPos", 0);
    	
    	// Gyro Handleing
    	RobotMap.gyro.initGyro();
    	RobotMap.gyro.reset();
    	
        // Initialize Camera
        // CameraServer camera = CameraServer.getInstance();
        // camera.setQuality(50);
        // camera.startAutomaticCapture(cameraName);
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit() {

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		
		SmartDashboard.putNumber("topEnc", Robot.topDuck.getEncPosition());
    	SmartDashboard.putNumber("bottomEnc", Robot.bottomDuck.getEncPosition());
    	SmartDashboard.putNumber("topError", Robot.topDuck.getClosedLoopError());
    	SmartDashboard.putNumber("bottomError", Robot.bottomDuck.getClosedLoopError());
    	SmartDashboard.putNumber("topJoySet", Robot.topDuck.getJoySet());
    	SmartDashboard.putNumber("bottomJoySet", Robot.bottomDuck.getJoySet());
    	SmartDashboard.putNumber("topSetpoint", Robot.topDuck.getSetpoint());
    	SmartDashboard.putNumber("bottomSetpoint", Robot.bottomDuck.getSetpoint());
    	
    	SmartDashboard.putNumber("Gyro", RobotMap.gyro.getAngle());
    	SmartDashboard.putNumber("Throttle scaled to 12", Robot.oi.getIntakeThrottle() * 12);
    	SmartDashboard.putNumber("Throttle", Robot.oi.getIntakeThrottle());
    	SmartDashboard.putNumber("Z-accel", Robot.shifting.getAccelZ());
    	
    	SmartDashboard.putNumber("rightEnc", Robot.rightDrive.getEncPosition());
    	SmartDashboard.putNumber("leftEnc", Robot.leftDrive.getEncPosition());
    	SmartDashboard.putNumber("rightError", Robot.rightDrive.getClosedLoopError());
    	SmartDashboard.putNumber("leftError", Robot.leftDrive.getClosedLoopError());
    	SmartDashboard.putNumber("rightpSetpoint", Robot.rightDrive.getSetpoint());
    	SmartDashboard.putNumber("leftSetpoint", Robot.leftDrive.getSetpoint());
    	
    
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
    	
        autonomousCommand = (Command) chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.

    	RobotMap.gyro.reset();
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        topDuck.izoneBraker();
        topDuck.errorExceder();
        bottomDuck.errorExceder();
        
        SmartDashboard.putData(shifting); 
        SmartDashboard.putData(topDuck);
        SmartDashboard.putData(bottomDuck);
        SmartDashboard.putData(intake);
        SmartDashboard.putData(leftDrive);
        SmartDashboard.putData(rightDrive);
        SmartDashboard.putData(hooker);
        
        SmartDashboard.putNumber("topEnc", Robot.topDuck.getEncPosition());
    	SmartDashboard.putNumber("bottomEnc", Robot.bottomDuck.getEncPosition());
    	SmartDashboard.putNumber("topError", Robot.topDuck.getClosedLoopError());
    	SmartDashboard.putNumber("bottomError", Robot.bottomDuck.getClosedLoopError());
    	SmartDashboard.putNumber("topJoySet", Robot.topDuck.getJoySet());
    	SmartDashboard.putNumber("bottomJoySet", Robot.bottomDuck.getJoySet());
    	SmartDashboard.putNumber("topSetpoint", Robot.topDuck.getSetpoint());
    	SmartDashboard.putNumber("bottomSetpoint", Robot.bottomDuck.getSetpoint());
    	
    	SmartDashboard.putNumber("Gyro", RobotMap.gyro.getAngle());
    	SmartDashboard.putNumber("Throttle scaled to 12", Robot.oi.getIntakeThrottle() * 12);
    	SmartDashboard.putNumber("Throttle", Robot.oi.getIntakeThrottle());
    	SmartDashboard.putNumber("Z-accel", Robot.shifting.getAccelZ());
    	
    	SmartDashboard.putNumber("rightEnc", Robot.rightDrive.getEncPosition());
    	SmartDashboard.putNumber("leftEnc", Robot.leftDrive.getEncPosition());
    	SmartDashboard.putNumber("rightError", Robot.rightDrive.getClosedLoopError());
    	SmartDashboard.putNumber("leftError", Robot.leftDrive.getClosedLoopError());
    	SmartDashboard.putNumber("rightpSetpoint", Robot.rightDrive.getSetpoint());
    	SmartDashboard.putNumber("leftSetpoint", Robot.leftDrive.getSetpoint());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
