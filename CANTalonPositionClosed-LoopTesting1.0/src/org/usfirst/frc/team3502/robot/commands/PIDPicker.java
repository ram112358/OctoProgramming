package org.usfirst.frc.team3502.robot.commands;

import org.usfirst.frc.team3502.robot.Robot;
import org.usfirst.frc.team3502.robot.subsystems.PickettUpper;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PIDPicker extends Command {
	
	private int lifterPosition;
	private static final Timer timer = new Timer();
	private double time;
	
    public PIDPicker() {
    	requires(Robot.lifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.lifter.positionMode();
    	Robot.lifter.enable();
    	//set the desired location for the lifter
    	Robot.lifter.set(3);
    	//start the timer for the time limit
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	lifterPosition = Robot.lifter.getPos();
    	//set time value for time limit
    	time = timer.get();
    	SmartDashboard.putNumber("encoderValue", Robot.lifter.getPos());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//tolerance check
    	//if (lifterPosition < 15 + PickettUpper.TOLERANCE && lifterPosition > 15 - PickettUpper.TOLERANCE)
    	//	return true;
    	//time limit
    	//if (time > 3)
    	//	return true;
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.lifter.disable();
    	timer.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.lifter.disable();
    	timer.reset();
    }
}