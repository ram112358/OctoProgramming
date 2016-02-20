package org.usfirst.frc.team3502.robot.commands;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveOMatic extends Command {

    public DriveOMatic() {
    	requires(Robot.drive);
    }

    protected void initialize() {
    }

    protected void execute() {
		Robot.drive.setRight(Robot.oi.getRightY());
		Robot.drive.setLeft(Robot.oi.getLeftY());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
