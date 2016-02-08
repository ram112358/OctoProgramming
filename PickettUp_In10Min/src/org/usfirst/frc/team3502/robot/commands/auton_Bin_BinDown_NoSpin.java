package org.usfirst.frc.team3502.robot.commands;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class auton_Bin_BinDown_NoSpin extends CommandGroup {
    Timer timer = new Timer();
    
    public  auton_Bin_BinDown_NoSpin() {
    	timer.start();
    	if(timer.get()<=15){
    		addSequential(new setDrivePneumatic(false));
	    	//pick up bin
    		addSequential(new setLifterLower());
    		addSequential(new setLifterPosition(Robot.lifter.BINONTOTE));
    		
	    	//drive forward
	    	addParallel(new setDrivePositionLeft(8));
	    	addSequential(new setDrivePositionRight(8));
	    	
	    	//lower bin
	    	addSequential(new setLifterPosition(Robot.lifter.BOTTOM));
	    	
	    	//drive forward
	    	addParallel(new setDrivePositionLeft(1));
	    	addSequential(new setDrivePositionRight(1));
    	}
    }
}
