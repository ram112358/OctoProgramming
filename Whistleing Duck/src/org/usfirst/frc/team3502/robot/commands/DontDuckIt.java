package org.usfirst.frc.team3502.robot.commands;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DontDuckIt extends Command {

    public DontDuckIt() {
    	requires(Robot.topDuck);
    	requires(Robot.bottomDuck);
    }

    protected void initialize() {
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
