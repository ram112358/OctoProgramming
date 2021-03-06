package org.usfirst.frc.team3502.robot.commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class setLifterBottom extends Command {
	
	private double time;
	private static final Timer timer = new Timer();
	private boolean killPID;

    public setLifterBottom() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.lifter);
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {/*
    	try {
			writeFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	//SmartDashboard.putNumber("Previous Lift Setpoint", Robot.lift.getSetpoint());
    	Robot.lifter.setBrakeOff();
    	Robot.intake.setCrabPiston(true);
    	// Robot.lift.setVoltageRamp();
    	Robot.lifter.disable();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//SmartDashboard.putNumber("Current Lift Position (PID)", position);
    	//Kirby (>'.')>
    	time = timer.get();
    	killPID = Robot.oi.getKillPID();
    	Robot.lifter.setLiftMotor(-.35);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(killPID||Robot.lifter.getLiftBottomStopper()||(time>1.75)){
    		return true;
    	}
    	else{
    		return false;
    	}
    	//TODO PID position/command to lift BIN with back lifter.
    	//TODO AUTONOMOUS
    	//TODO Automatically lift when red button is pressed?  Try it out.
    	//TODO SmartDashboard large display says lifter height  relative to important things:  Bottom, Tote Height, Step Height, Bin lift height, bin on tote height
    	//TODO Option to reverse controls so that the rear of the robot is driven as the front
    }

    // Called once after isFinished returns true
    protected void end() {
    	timer.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
