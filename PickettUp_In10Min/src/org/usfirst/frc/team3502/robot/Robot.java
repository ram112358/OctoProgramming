
package org.usfirst.frc.team3502.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;

import org.usfirst.frc.team3502.robot.commands.autonDefault;
import org.usfirst.frc.team3502.robot.commands.auton_BinAndTote;
import org.usfirst.frc.team3502.robot.commands.auton_Bin_BinDown_NoSpin;
import org.usfirst.frc.team3502.robot.commands.auton_Bin_BinDown_Spin;
import org.usfirst.frc.team3502.robot.commands.auton_Bin_NoSpin;
import org.usfirst.frc.team3502.robot.commands.auton_Bin_Spin;
import org.usfirst.frc.team3502.robot.commands.auton_DriveBack;
import org.usfirst.frc.team3502.robot.commands.auton_Tote;
import org.usfirst.frc.team3502.robot.commands.auton_Tote_Three;
import org.usfirst.frc.team3502.robot.subsystems.DrivePneumatics;
import org.usfirst.frc.team3502.robot.subsystems.DriveTrainCenter_PID;
import org.usfirst.frc.team3502.robot.subsystems.DriveTrainLeft_PID;
import org.usfirst.frc.team3502.robot.subsystems.DriveTrainRight_PID;
import org.usfirst.frc.team3502.robot.subsystems.Intake;
import org.usfirst.frc.team3502.robot.subsystems.Lifter_PID;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

//TODO AUTONOMOUS
//TODO Automatically lift when red button is pressed?  Try it out.
//TODO hat toggle
//TODO SmartDashboard large display says lifter height  relative to important things:  Bottom, Tote Height, Step Height, Bin lift height, bin on tote height

public class Robot extends IterativeRobot {
	
	public static final DriveTrainLeft_PID driveLeft = new DriveTrainLeft_PID();
	public static final DriveTrainRight_PID driveRight = new DriveTrainRight_PID();
	public static final DriveTrainCenter_PID driveCenter = new DriveTrainCenter_PID();
	public static final Lifter_PID lifter = new Lifter_PID();
	public static final Intake intake = new Intake();
	public static final DrivePneumatics dPneumatics = new DrivePneumatics();
	public static OI oi;
	private static String cameraName = "cam0";
	private BuiltInAccelerometer accel = new BuiltInAccelerometer();

    Command autonomousCommand;
    SendableChooser autonChooser, intakeChooser;
    
    public static int intakeControl;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
        
		//autonomous selection
        autonChooser = new SendableChooser();
        //autonChooser.addDefault("Tote (first) and Bin (second)", new autonDefault());
        //autonChooser.addObject("Bin (first) and Tote (second)", new auton_BinAndTote());
        //autonChooser.addObject("Bin (spin, AWAY)", new auton_Bin_BinDown_Spin());
        //autonChooser.addObject("Bin (NO spin, AWAY)", new auton_Bin_BinDown_NoSpin());
        //autonChooser.addObject("Bin (spin, keep)", new auton_Bin_Spin());
        autonChooser.addDefault("Bin (NO spin, keep)", new auton_Bin_NoSpin());
        //autonChooser.addObject("Tote", new auton_Tote());
        autonChooser.addObject("Drive Back", new auton_DriveBack());
        //autonChooser.addObject("3 Tote", new auton_Tote_Three());
        SmartDashboard.putData("Auton Mode Chooser", autonChooser);
        
      //intake selection
        intakeChooser = new SendableChooser();
        intakeChooser.addDefault("Default (Dual)", 0);
        intakeChooser.addObject("Driver", 1);
        intakeChooser.addObject("Operator", 2);
        SmartDashboard.putData("Intake Mode Chooser", intakeChooser);
        
        //Initialize Camera
        CameraServer camera = CameraServer.getInstance();
        camera.setQuality(50);
        camera.startAutomaticCapture(cameraName);
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	autonomousCommand = (Command)(autonChooser.getSelected());
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
        if (autonomousCommand != null) autonomousCommand.cancel();
        
        intakeControl = (int)(intakeChooser.getSelected());
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	SmartDashboard.putData(lifter);
    	SmartDashboard.putData(intake);
    	SmartDashboard.putData(driveLeft);
    	SmartDashboard.putData(driveCenter);
    	SmartDashboard.putData(driveRight);
    	
        Scheduler.getInstance().run();
        /*SmartDashboard.putNumber("AccelX", accel.getX());
        SmartDashboard.putNumber("AccelY", accel.getY());
        SmartDashboard.putNumber("AccelZ", accel.getZ());*/
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
