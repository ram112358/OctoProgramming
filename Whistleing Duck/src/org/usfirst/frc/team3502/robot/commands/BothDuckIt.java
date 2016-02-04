package org.usfirst.frc.team3502.robot.commands;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BothDuckIt extends Command {

    public BothDuckIt() {
    	requires(Robot.topDuck);
    	requires(Robot.bottomDuck);
    }

    protected void initialize() {
    }

    protected void execute() {
    	//Robot.topDuck.set(Robot.oi.getDuckY());
    	//Robot.bottomDuck.set(Robot.oi.getDuckY());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
