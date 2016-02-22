package org.usfirst.frc.team3502.robot.commands.Duck;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TopThrottle extends Command {

    public TopThrottle() {
    	requires(Robot.topDuck);
    	requires(Robot.bottomDuck);
    }

    protected void initialize() {
    	Robot.topDuck.setThrottleMode();
    	Robot.bottomDuck.setThrottleMode();
    }

    protected void execute() {
    	if (Robot.topDuck.getBrakeMode())
    		Robot.topDuck.setSlow(Robot.oi.getDuckY());
    	else
    		Robot.topDuck.setSlow(0.0);
    	
    	if (Robot.oi.getManualBrakeOffButton())
    		Robot.topDuck.setBrakeMode();
    	else if (Robot.oi.getManualBrakeOnButton())
    		Robot.topDuck.setNotBrakeMode();
    }

    protected boolean isFinished() {
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
