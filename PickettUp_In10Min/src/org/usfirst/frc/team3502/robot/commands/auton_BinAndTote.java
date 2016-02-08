package org.usfirst.frc.team3502.robot.commands;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class auton_BinAndTote extends CommandGroup {
	Timer timer = new Timer();
    
    public  auton_BinAndTote() {
    	timer.start();
    	if(timer.get()<=15){
    		addSequential(new setDrivePneumatic(true));
	    	//Pick Up Bin
	    	addSequential(new setLifterPosition(Robot.lifter.BINONTOTE));
	    	
	    	//Drive Forward, spin 180 (so don't bump tote)
	    	addParallel(new setDrivePositionLeft(1));
	    	addSequential(new setDrivePositionRight(1));
	    	addParallel(new setDrivePositionLeft(1.675*4));
	    	addSequential(new setDrivePositionRight(-1*2));
	    	
	    	//start crab motors and drive forward
	    	addParallel(new autonIntake_Tote(true));
	    	addParallel(new setDrivePositionLeft(1.5));
	    	addSequential(new setDrivePositionRight(1.5));
	    	
	    	//spin 90 degrees
	    	addParallel(new setDrivePositionLeft(1.675*2));
	    	addSequential(new setDrivePositionRight(-1));
	    	
	    	addSequential(new setDrivePneumatic(false));
	    	//drive forward
	    	addParallel(new setDrivePositionLeft(8));
	    	addSequential(new setDrivePositionRight(8));
	    	
	    	//lower bin
	    	addSequential(new setLifterPosition(Robot.lifter.BOTTOM));
	    	
	    	//drive forward
	    	addParallel(new setDrivePositionLeft(1));
	    	addSequential(new setDrivePositionRight(1));
	    	
	    	//spit out tote
	    	addParallel(new autonIntake_Tote(false));
	    	
	    	//addParallel(new autonIntake());
    	}
    }
}
