package org.usfirst.frc.team3502.robot.commands;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BottomFullUp extends Command {

    public BottomFullUp() {
    	requires(Robot.topDuck);
    	requires(Robot.bottomDuck);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.bottomDuck.set(Math.abs(Robot.oi.getIntakeThrottle()));
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.bottomDuck.set(0);
    }
 
    protected void interrupted() {
    	Robot.bottomDuck.set(0);
    }
}
