package org.usfirst.frc.team3502.robot.commands;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BottomDuckIt extends Command {

    public BottomDuckIt() {
    	requires(Robot.topDuck);
    	requires(Robot.bottomDuck);
    }

    protected void initialize() {
    	Robot.topDuck.setPositionMode();
    	Robot.bottomDuck.setPositionMode();
    	
    	Robot.topDuck.setEncPosition(0);
    	Robot.bottomDuck.setEncPosition(0);
    }

    protected void execute() {
    	Robot.bottomDuck.setpointDrive(Robot.oi.getDuckY());
    	Robot.topDuck.set(0.0);
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
