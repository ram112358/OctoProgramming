package org.usfirst.frc.team3502.robot.commands;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class auton_Tote extends CommandGroup {
    Timer timer = new Timer();
    
    public  auton_Tote() {
    	timer.start();
    	if(timer.get()<=15){
    		addParallel(new setDrivePneumatic(true));
    		addSequential(new setLifterBottom());
	    	//pull tote in
	    	addSequential(new autonIntake_Tote(true));
	    	
	    	addSequential(new setLifterPosition(1));
	    	
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
