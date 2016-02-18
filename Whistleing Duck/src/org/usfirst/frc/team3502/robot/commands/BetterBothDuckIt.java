package org.usfirst.frc.team3502.robot.commands;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BetterBothDuckIt extends Command {
	
	private double setpoint;
	
    public BetterBothDuckIt() {
    	requires(Robot.topDuck);
    	requires(Robot.bottomDuck);
    }

    protected void initialize() {
    	Robot.topDuck.setPositionMode();
    	Robot.bottomDuck.setPositionMode();
    	
    	Robot.topDuck.setEncPosition(0);
    	Robot.bottomDuck.setEncPosition(0);
    	
    	setpoint = 0.0;
    	// Robot.topDuck.set(5);
    	// Robot.bottomDuck.set(5);
}

    protected void execute() {
    	setpoint += Robot.oi.getDuckY()/4096*100;
    	SmartDashboard.putNumber("setpoint", setpoint*4096);
    	Robot.topDuck.set(setpoint);
    	Robot.bottomDuck.set(setpoint);
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
