package org.usfirst.frc.team3502.robot.commands;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class setDrivePneumatic extends Command {
	private boolean omniMode;

    public setDrivePneumatic(boolean omni) {
        // Use requires() here to declare subsystem dependencies
    	this.omniMode = omni;
        requires(Robot.dPneumatics);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.dPneumatics.switchDriveMode(omniMode);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
