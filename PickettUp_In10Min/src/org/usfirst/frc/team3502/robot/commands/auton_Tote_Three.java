package org.usfirst.frc.team3502.robot.commands;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class auton_Tote_Three extends CommandGroup {
    Timer timer = new Timer();
    
    public  auton_Tote_Three() {
    	timer.start();
    	if(timer.get()<=15){
    		addSequential(new setDrivePneumatic(true));
	    	//pull tote in
	    	//addSequential(new autonIntake_Tote(true));
    		addSequential(new autonIntake(-1,1,false,false));
	    	
	    	addSequential(new setLifterBottom());
	    	addSequential(new setLifterTop());
	    	
	    	addParallel(new autonIntake(-1,-1, false, true));
	    	//drive forward
	    	addParallel(new setDrivePositionLeft(2));
	    	addSequential(new setDrivePositionRight(2));
	    	
	    	
	    	addParallel(new autonIntake(-1,1,true,true));
	    	//drive forward
	    	addParallel(new setDrivePositionLeft(3));
	    	addSequential(new setDrivePositionRight(3));
	    	
	    	
	    	
	    	
	    	
	    	//pull tote in
	    	//addSequential(new autonIntake_Tote(true));
    		addSequential(new autonIntake(-1,1,false,false));
	    	
	    	addSequential(new setLifterBottom());
	    	addSequential(new setLifterTop());
	    	
	    	addParallel(new autonIntake(-1,-1, false, true));
	    	//drive forward
	    	addParallel(new setDrivePositionLeft(2));
	    	addSequential(new setDrivePositionRight(2));
	    	
	    	
	    	addParallel(new autonIntake(-1,1,true,true));
	    	//drive forward
	    	addParallel(new setDrivePositionLeft(3));
	    	addSequential(new setDrivePositionRight(3));
	    	
	    	
	    	
	    	//pull tote in
	    	//addSequential(new autonIntake_Tote(true));
    		addSequential(new autonIntake(-1,1,false,false));
	    	
	    	addSequential(new setLifterBottom());
	    	addSequential(new setLifterTop());
	    	
	    	addParallel(new autonIntake(-1,-1, false, true));
	    	//drive forward
	    	addParallel(new setDrivePositionLeft(2));
	    	addSequential(new setDrivePositionRight(2));
	    	
	    	
	    	addParallel(new autonIntake(-1,1,true,true));
	    	//drive forward
	    	addParallel(new setDrivePositionLeft(3));
	    	addSequential(new setDrivePositionRight(3));
	    	
	    	
	    	
	    	
	    	
	    	//spin 90 degrees
	    	addParallel(new setDrivePositionLeft(1.675*2.2));
	    	addSequential(new setDrivePositionRight(-1));
	    	
	    	addSequential(new setDrivePneumatic(false));
	    	//drive forward
	    	addParallel(new setDrivePositionLeft(9));
	    	addSequential(new setDrivePositionRight(9));
	    	
	    	addSequential(new setLifterPosition(Robot.lifter.BOTTOM));
	    	
	    	//shoot tote out
	    	addSequential(new autonIntake_Tote(false));
	    	
	    	
    	}
    }
}
