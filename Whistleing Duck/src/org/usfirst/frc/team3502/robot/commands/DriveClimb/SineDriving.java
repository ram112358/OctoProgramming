package org.usfirst.frc.team3502.robot.commands.DriveClimb;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SineDriving extends Command {
	
    public SineDriving() {
    	requires(Robot.rightDrive);
    	requires(Robot.leftDrive);
    }

    protected void initialize() {
    }

    protected void execute() {
		Robot.rightDrive.setSineScaling(Robot.oi.getRightY());
		Robot.leftDrive.setSineScaling(Robot.oi.getLeftY());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}