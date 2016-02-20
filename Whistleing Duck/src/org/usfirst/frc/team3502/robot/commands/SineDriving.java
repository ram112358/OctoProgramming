package org.usfirst.frc.team3502.robot.commands;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SineDriving extends Command {

	double
		rightJoy,
		leftJoy,
		rightOut,
		leftOut;
	
    public SineDriving() {
    	requires(Robot.drive);
    }

    protected void initialize() {
    }

    protected void execute() {
    	rightJoy = Robot.oi.getRightY();
    	leftJoy = Robot.oi.getLeftY();
    	
    	if (rightJoy > 0.0)
    		rightOut = (Math.sin((rightJoy * Math.PI) - (Math.PI / 2)) / 2) + .5;
    	else if (rightJoy < 0.0)
    		rightOut = -((Math.sin((rightJoy * Math.PI) - (Math.PI / 2)) / 2) + .5);
    	else
    		rightOut = 0.0;
    	
    	if (leftJoy > 0.0)
    		leftOut = (Math.sin((leftJoy * Math.PI) - (Math.PI / 2)) / 2) + .5;
    	else if (leftJoy < 0.0)
    		leftOut = -((Math.sin((leftJoy * Math.PI) - (Math.PI / 2)) / 2) + .5);
    	else
    		leftOut = 0.0;
    	
    	Robot.drive.setRight(rightOut);
    	Robot.drive.setLeft(leftOut);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
