/*package org.usfirst.frc.team3502.robot.commands.machOne;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;

public class ProfileExecuter extends Command {
	
	private CANTalon.SetValueMotionProfile setMode;
	
    public ProfileExecuter() {
    	requires(Robot.drive);
    }

    protected void initialize() {
    	Robot.drive.setProfileMode();
    	Robot.drive.reset();
    }

    protected void execute() {
    	//check for button to start process
    	if (Robot.oi.getStartFileButton())
    		Robot.drive.startMotionProfile();
    	//run process every cycle
    	Robot.drive.control();
    	//do the set thing
    	setMode = Robot.drive.getSetValue();
    	Robot.drive.set(setMode.value);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.drive.reset();
    	Robot.drive.setThrottleMode();
    }

    protected void interrupted() {
    	Robot.drive.reset();
    	Robot.drive.setThrottleMode();
    }
}*/