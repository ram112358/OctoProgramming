package org.usfirst.frc.team3502.robot.commands.DriveClimb;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveOMatic extends Command {

    public DriveOMatic() {
    	requires(Robot.rightDrive);
    	requires(Robot.leftDrive);
    }

    protected void initialize() {
    }

    protected void execute() {
		Robot.rightDrive.set(Robot.oi.getRightY());
		Robot.leftDrive.set(Robot.oi.getLeftY());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
