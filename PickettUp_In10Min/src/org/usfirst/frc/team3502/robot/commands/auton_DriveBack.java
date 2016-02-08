package org.usfirst.frc.team3502.robot.commands;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class auton_DriveBack extends CommandGroup {
    
Timer timer = new Timer();
    
    public  auton_DriveBack() {
    	timer.start();
    	if(timer.get()<=15){
	    	addSequential(new setDrivePneumatic(false));
	    	
	    	//drive backward
	    	addParallel(new setDrivePositionLeft(-1.5));
	    	addSequential(new setDrivePositionRight(-1.5));
    	}
    }
}
