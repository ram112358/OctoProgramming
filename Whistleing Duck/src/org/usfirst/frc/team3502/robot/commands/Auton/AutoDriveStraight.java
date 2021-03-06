package org.usfirst.frc.team3502.robot.commands.Auton;

import org.usfirst.frc.team3502.robot.commands.DriveClimb.DriveToGyroHeading;

import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDriveStraight extends CommandGroup {

	public  AutoDriveStraight(double throttle, double timeOut) {
		addSequential(new DriveToGyroHeading(true, throttle), timeOut);
		//addSequential(new DriveToGyroHeading(true, 0), 2);
		//addSequential(new DriveToGyroHeading(true, throttle), timeOut);
    	
    	//TODO: Add code to drive to wall to shoot ball
    	
    	
    	
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
