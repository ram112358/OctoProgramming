package org.usfirst.frc.team3502.robot.commands;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BothDuckIt extends Command {
	
	public double duckYPos;
	
    public BothDuckIt() {
    	requires(Robot.topDuck);
    	requires(Robot.bottomDuck);
    }

    protected void initialize() {
    	Robot.topDuck.setThrottleMode();
    	Robot.bottomDuck.setPositionMode();
    	
    	Robot.bottomDuck.setEncPosition(0);
    	Robot.topDuck.setEncPosition(0);
    	
    	//Robot.bottomDuck.set(10);
    }

    protected void execute() {
    	Robot.topDuck.setSlow(Robot.oi.getDuckY());
    	SmartDashboard.putBoolean("just drove", true);
    	Robot.bottomDuck.set(Robot.topDuck.getEncPosition() / 4096);
    	SmartDashboard.putBoolean("just drove", true);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
