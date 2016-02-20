package org.usfirst.frc.team3502.robot.commands.Duck;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TopDuckIt extends Command {

    public TopDuckIt() {
    	requires(Robot.topDuck);
    	requires(Robot.bottomDuck);
    }

    protected void initialize() {
    	Robot.topDuck.setPositionMode();
    	Robot.bottomDuck.setThrottleMode();
    }

    protected void execute() {
    	Robot.topDuck.JoySetDrive(Robot.oi.getDuckY());
    	Robot.bottomDuck.set(0.0);
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