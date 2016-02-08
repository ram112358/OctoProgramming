package org.usfirst.frc.team3502.robot.commands;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class autonIntake extends Command {
	
	private double leftCrabSpeed, rightCrabSpeed;
	private boolean startCrabPosition, endCrabPosition, done;
	private final Timer timer = new Timer();

    public autonIntake(double left, double right, boolean startPosition, boolean endPosition) {
        // Use requires() here to declare subsystem dependencies
    	this.leftCrabSpeed = left;
    	this.rightCrabSpeed = right;
    	this.startCrabPosition = startPosition;
    	this.endCrabPosition = endPosition;
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    	Robot.intake.setCrabPiston(startCrabPosition);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.setCrabMotors(leftCrabSpeed, rightCrabSpeed);
    	done = Robot.intake.getTote();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(done || (timer.get()>1.5)){
    		return true;
    	}
    	else{
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.setCrabPiston(endCrabPosition);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
