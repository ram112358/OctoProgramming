package org.usfirst.frc.team3502.robot.commands.Duck;

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
    	Robot.topDuck.setVoltageMode();
    	Robot.bottomDuck.setVoltageMode();
    }

    protected void execute() {
    	Robot.bottomDuck.set(Robot.oi.getIntakeThrottle() * 12);
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