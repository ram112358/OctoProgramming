package org.usfirst.frc.team3502.robot.commands.Duck;

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
    	Robot.topDuck.setThrottleMode();
    	Robot.bottomDuck.setThrottleMode();
    }

    protected void execute() {
    	Robot.topDuck.setBrakeMode();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
