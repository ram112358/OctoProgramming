package org.usfirst.frc.team3502.robot.commands.DriveClimb;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class KeepHooker extends Command {

    public KeepHooker() {
    	requires(Robot.hooker);
    }

    protected void initialize() {
    }

    protected void execute() {
		Robot.hooker.setServoPosition(90);
	}

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}