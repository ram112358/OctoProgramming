package org.usfirst.frc.team3502.robot.commands;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class autonDefault extends CommandGroup {
	Timer timer = new Timer();
    
    public  autonDefault() {
    	timer.start();
    	if(timer.get()<=15){
    		//addSequential(new PickUpTote(Robot.lifter.BOTTOM, true, 0));
    		addSequential(new setDrivePneumatic(true));
	    	//pull tote in
	    	addSequential(new autonIntake_Tote(true));
	    	
    		//addSequential(new setLifterPosition(Robot.lifter.BINONTOTE));
	    	addSequential(new setLifterTop());
	    	
	    	//drive forward
	    	addParallel(new setDrivePositionLeft(3));
	    	addSequential(new setDrivePositionRight(3));
	    	
	    	//pull bin in
	    	//addSequential(new autonIntake_Bin(true));
	    	
	    	//spin 90 degrees
	    	addParallel(new setDrivePositionLeft(1.675*2.2));
	    	addSequential(new setDrivePositionRight(-1));
	    	
	    	//drive forward
	    	addSequential(new setDrivePneumatic(false));
	    	addParallel(new setDrivePositionLeft(13));
	    	addSequential(new setDrivePositionRight(13));
	    	
	    	//shoot Bin out
	    	//addSequential(new autonIntake_Bin(false));
	    	
	    	//drive Back
	    	addParallel(new setDrivePositionLeft(-2));
	    	addSequential(new setDrivePositionRight(-2));
	    	
	    	addSequential(new setDrivePneumatic(true));
	    	//spin 90 degrees
	    	addParallel(new setDrivePositionLeft(1.675*2.2));
	    	addSequential(new setDrivePositionRight(-1));
	    	
	    	addSequential(new setLifterPosition(Robot.lifter.BOTTOM));
	    	
	    	//shoot tote out
	    	addSequential(new autonIntake_Tote(false));
	    	
	    	
    	}
    }
}
