package org.usfirst.frc.team3502.robot.commands.DriveClimb;

import org.usfirst.frc.team3502.robot.Robot;
import org.usfirst.frc.team3502.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class RegDriveDuckEnd extends Command {

    public RegDriveDuckEnd() {
    	requires(Robot.rightDrive);
    	requires(Robot.leftDrive);
    }

    protected void initialize() {
    }

    protected void execute() {
		Robot.rightDrive.setBrown(Robot.oi.getRightY());
		Robot.leftDrive.setBrown(Robot.oi.getLeftY());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	System.out.println("1");
    }

    protected void interrupted() {
    	System.out.println("2");
    }
}
