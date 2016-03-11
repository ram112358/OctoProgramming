package org.usfirst.frc.team3502.robot.commands.Duck;

import org.usfirst.frc.team3502.robot.Constants;
import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class BottomDuckIt extends Command {

    public BottomDuckIt() {
    	requires(Robot.topDuck);
    	requires(Robot.bottomDuck);
    }

    protected void initialize() {
    	Robot.topDuck.setThrottleMode();
    	Robot.bottomDuck.setPositionMode();
    }

    protected void execute() {
    	Robot.bottomDuck.JoySetDrive(Robot.oi.getDuckY());
    	Robot.topDuck.set(0.0);
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