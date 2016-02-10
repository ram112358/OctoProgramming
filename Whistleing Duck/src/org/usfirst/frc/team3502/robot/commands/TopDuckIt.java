package org.usfirst.frc.team3502.robot.commands;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TopDuckIt extends Command {

    public TopDuckIt() {
    	requires(Robot.topDuck);
    	requires(Robot.bottomDuck);
    }

    protected void initialize() {
    	Robot.topDuck.setThrottleMode();
    	Robot.bottomDuck.setThrottleMode();
    }

    protected void execute() {
    	Robot.topDuck.setSlow(Robot.oi.getDuckY());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    	Robot.topDuck.set(0);
    }
}
