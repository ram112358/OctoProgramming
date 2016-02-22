package org.usfirst.frc.team3502.robot.commands.Duck;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClearDuckEncoders extends Command {
	
	public double duckYPos;
	
    public ClearDuckEncoders() {
    	requires(Robot.topDuck);
    	requires(Robot.bottomDuck);
    }

    protected void initialize() {
    	Robot.topDuck.setThrottleMode();
    	Robot.bottomDuck.setThrottleMode();
    }

    protected void execute() {
    	Robot.topDuck.setEncPosition(0);
    	Robot.bottomDuck.setEncPosition(0);
    	Robot.topDuck.setJoySet(0);
    	Robot.bottomDuck.setJoySet(0);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}