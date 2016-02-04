package org.usfirst.frc.team3502.robot.commands;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeDrive extends Command {

    public IntakeDrive() {
    	requires(Robot.intake);
    }

    protected void initialize() {
    }

    protected void execute() {
    	if (Robot.oi.getIntakeInButton()){
    		Robot.intake.set(Robot.oi.getIntakeThrottle());
    	}
    	else if(Robot.oi.getIntakeOutButton()){
    		Robot.intake.set(-Robot.oi.getIntakeThrottle());
    	}
    	else{
    		Robot.intake.set(0.0);
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
