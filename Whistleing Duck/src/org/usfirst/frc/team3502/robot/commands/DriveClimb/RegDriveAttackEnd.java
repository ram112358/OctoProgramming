package org.usfirst.frc.team3502.robot.commands.DriveClimb;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RegDriveAttackEnd extends Command {

    public RegDriveAttackEnd() {
    	requires(Robot.rightDrive);
    	requires(Robot.leftDrive);
    }

    protected void initialize() {
    }

    protected void execute() {
		Robot.rightDrive.setBrown(- Robot.oi.getLeftY());
		Robot.leftDrive.setBrown(- Robot.oi.getRightY());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	System.out.println("3");
    }

    protected void interrupted() {
    	System.out.println("4");
    }
}
