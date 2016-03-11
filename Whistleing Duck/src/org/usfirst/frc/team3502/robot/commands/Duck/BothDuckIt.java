package org.usfirst.frc.team3502.robot.commands.Duck;

import org.usfirst.frc.team3502.robot.Constants;
import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class BothDuckIt extends Command {
	
	private double duckY;
	
    public BothDuckIt() {
    	requires(Robot.topDuck);
    	requires(Robot.bottomDuck);
    }

    protected void initialize() {
    	Robot.topDuck.setPositionMode();
    	Robot.bottomDuck.setPositionMode();
}

    protected void execute() {
    	duckY = Robot.oi.getDuckY();
    	if (!Robot.topDuck.getTopLimit() && !Robot.bottomDuck.getBottomLimit()) {
    		Robot.topDuck.JoySetDrive(duckY);
    		Robot.bottomDuck.JoySetDrive(duckY);
    	}
    	else if (duckY > 0.0 && !Robot.bottomDuck.getBottomLimit()) {
    		Robot.topDuck.JoySetDrive(duckY);
    		Robot.bottomDuck.JoySetDrive(duckY);
    	}
    	else if (duckY < 0.0 && !Robot.topDuck.getTopLimit()) {
    		Robot.topDuck.JoySetDrive(duckY);
    		Robot.bottomDuck.JoySetDrive(duckY);
    	}
    	else{
    		Robot.topDuck.JoySetDrive(0.0);
    		Robot.bottomDuck.JoySetDrive(0.0);
    	}
    }

    protected boolean isFinished() {
    	if (Constants.killPID) {
    		Constants.killPID = true;
    		return true;
    	}
        return false;
    }

    protected void end() {
    	Robot.topDuck.setThrottleMode();
    	Robot.bottomDuck.setThrottleMode();
    }

    protected void interrupted() {
    	Robot.topDuck.setThrottleMode();
    	Robot.bottomDuck.setThrottleMode();
    }
}