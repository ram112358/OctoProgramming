package org.usfirst.frc.team3502.robot.commands;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class setIntake extends Command {
	private boolean done, in;
	private double scaler;
	private static final Timer timer = new Timer();

    public setIntake(boolean direction, double scaledOutput) {
    	this.in = direction;
    	this.scaler = scaledOutput;
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intake.setCrabPiston(true);
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(in){
    		Robot.intake.setCrabMotors(-1*scaler, 1*scaler);
    	}
    	else{
    		Robot.intake.setCrabMotors(1*scaler, -1*scaler);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.setCrabPiston(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
