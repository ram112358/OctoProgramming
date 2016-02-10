package org.usfirst.frc.team3502.robot.commands;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;

public class ProfileExecuter extends Command {

	private final int numPoints = 0;
	private CANTalon.MotionProfileStatus talonStatus = new CANTalon.MotionProfileStatus();
	
    public ProfileExecuter() {
    	requires(Robot.drive);
    }

    protected void initialize() {
    	Robot.drive.setProfileMode();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}