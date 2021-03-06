package org.usfirst.frc.team3502.robot.commands;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ManualPicker extends Command {
	
    public ManualPicker() {
    	requires(Robot.lifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.lifter.throttleMode();
    	Robot.lifter.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.lifter.set(Robot.oi.getManY());
    	SmartDashboard.putNumber("encoderValue", Robot.lifter.getPos());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.lifter.disable();
    }
}