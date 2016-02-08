package org.usfirst.frc.team3502.robot.commands;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class auton_Bin_Spin extends CommandGroup {
    Timer timer = new Timer();
    
    public  auton_Bin_Spin() {
    	timer.start();
    	if(timer.get()<=15){
    		addSequential(new setDrivePneumatic(true));
	    	//pick up bin
    		addSequential(new setLifterLower());
    		addSequential(new setLifterPosition(Robot.lifter.BINONTOTE));
	    	
	    	//spin 90 degrees
	    	addParallel(new setDrivePositionLeft(1.675*2));
	    	addSequential(new setDrivePositionRight(-1));
	    	
	    	addSequential(new setDrivePneumatic(false));
	    	//drive forward
	    	addParallel(new setDrivePositionLeft(8));
	    	addSequential(new setDrivePositionRight(8));
    	}
    }
}
