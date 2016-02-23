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
		Robot.rightDrive.set(Robot.oi.getRightY());
		Robot.leftDrive.set(Robot.oi.getLeftY());
		
		if (Robot.oi.getResetGyro())
			RobotMap.gyro.reset();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
