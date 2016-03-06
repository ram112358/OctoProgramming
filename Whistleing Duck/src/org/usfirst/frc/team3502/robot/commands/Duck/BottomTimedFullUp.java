package org.usfirst.frc.team3502.robot.commands.Duck;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class BottomTimedFullUp extends Command {
	
	private static final Timer timer = new Timer();
	private double
		time,
		timeToRun,
		timeToBrake;
	private boolean isDone;

    public BottomTimedFullUp() {
    	requires(Robot.topDuck);
    	requires(Robot.bottomDuck);
    }

    protected void initialize() {
    	Robot.topDuck.setVoltageMode();
    	Robot.bottomDuck.setVoltageMode();
    	// Robot.topDuck.setThrottleMode();
    	// Robot.bottomDuck.setThrottleMode();
    	
    	timer.start();
    	isDone = false;
    	
    	timeToRun = NetworkTable.getTable("Preferences").getNumber("Sec Run", 0);
    	timeToBrake = NetworkTable.getTable("Preferences").getNumber("Sec Brake", 0) + timeToRun;
    }

    protected void execute() {
    	time = timer.get();
    	if (time < timeToRun)
        	Robot.bottomDuck.set(Robot.oi.getIntakeThrottle() * 12);
        	// Robot.bottomDuck.set(Robot.oi.getIntakeThrottle());
    	else if (time < timeToBrake)
    		Robot.bottomDuck.set(- 0.1);
    	else
    		isDone = true;
    }

    protected boolean isFinished() {
    	if (isDone)
    		return true;
    	return false;
    }

    protected void end() {
    	Robot.bottomDuck.set(0);
    	timer.reset();
    }
 
    protected void interrupted() {
    	Robot.bottomDuck.set(0);
    	timer.reset();
    }
}