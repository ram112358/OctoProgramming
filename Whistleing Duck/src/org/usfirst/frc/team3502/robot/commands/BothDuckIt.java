package org.usfirst.frc.team3502.robot.commands;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BothDuckIt extends Command {
	
	public double duckYPos;
	
    public BothDuckIt() {
    	requires(Robot.topDuck);
    	requires(Robot.bottomDuck);
    }

    protected void initialize() {
    	Robot.topDuck.setThrottleMode();
    	Robot.bottomDuck.setThrottleMode();

    	Robot.topDuck.setEncPosition(0.0);
    	Robot.bottomDuck.setEncPosition(0.0);
    }

    protected void execute() {
    	duckYPos = Robot.oi.getDuckY();
    	Robot.topDuck.setSlow(duckYPos);
    	Robot.topDuck.getEncPosition();
    	if (Robot.bottomDuck.getEncPosition() - Robot.topDuck.getEncPosition() > 0){
    		// if (duckYPos <= 0.05 && duckYPos >= -0.05)
    		// 	Robot.bottomDuck.setSlow(0.05);
    		// else
    			Robot.bottomDuck.set(duckYPos * .7);
    	}
    	else if(Robot.bottomDuck.getEncPosition() - Robot.topDuck.getEncPosition() < 0 && duckYPos > .1){
    		// if (duckYPos <= 0.05 && duckYPos >= -0.05)
    		// 	Robot.bottomDuck.setSlow(-0.05);
    		// else
    			Robot.bottomDuck.set(-duckYPos * .7);
    	}
    	else{
    		Robot.bottomDuck.set(duckYPos);
    	}
    		
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
