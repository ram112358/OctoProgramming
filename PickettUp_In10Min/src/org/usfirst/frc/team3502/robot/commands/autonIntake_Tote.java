package org.usfirst.frc.team3502.robot.commands;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class autonIntake_Tote extends Command {
	private boolean done, in;
	private static final Timer timer = new Timer();

    public autonIntake_Tote(boolean direction) {
    	this.in = direction;
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intake.setCrabPiston(false);
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(in){
    		Robot.intake.setCrabMotors(-1,1);
    	}
    	else{
    		Robot.intake.setCrabMotors(1,-1);
    	}
    	done = Robot.intake.getTote();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(done||(timer.get()>2)){
    		return true;
    	}
    	else{
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.setCrabPiston(true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
