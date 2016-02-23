package org.usfirst.frc.team3502.robot.commands.DriveClimb.Gyro;

import org.usfirst.frc.team3502.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveGyroForward extends CommandGroup {
    
    public  DriveGyroForward() {
    	addSequential(new DriveToGyroHeading(RobotMap.gyro.getAngle()));
    }
}
