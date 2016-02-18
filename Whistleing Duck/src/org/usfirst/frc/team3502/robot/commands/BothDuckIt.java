package org.usfirst.frc.team3502.robot.commands;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class BothDuckIt extends Command {
	
	public BothDuckIt() {
    	requires(Robot.topDuck);
    	requires(Robot.bottomDuck);
    }

    protected void initialize() {
    	Robot.topDuck.setThrottleMode();
    	Robot.bottomDuck.setPositionMode();
    	
    	Robot.bottomDuck.setEncPosition(0);
    	Robot.topDuck.setEncPosition(0);
    }

    protected void execute() {
    	Robot.topDuck.setSlow(Robot.oi.getDuckY());
    	Robot.bottomDuck.set((double)Robot.topDuck.getEncPosition() / 4096);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.bottomDuck.setThrottleMode();
    }

    protected void interrupted() {
    	Robot.bottomDuck.setThrottleMode();
    }
}
