package org.usfirst.frc.team3502.robot.commands.DriveClimb;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SineDriveAttackEnd extends Command {
	
    public SineDriveAttackEnd() {
    	requires(Robot.rightDrive);
    	requires(Robot.leftDrive);
    }

    protected void initialize() {
    }

    protected void execute() {
		Robot.rightDrive.setSineScaling(- Robot.oi.getLeftY());
		Robot.leftDrive.setSineScaling(- Robot.oi.getRightY());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}