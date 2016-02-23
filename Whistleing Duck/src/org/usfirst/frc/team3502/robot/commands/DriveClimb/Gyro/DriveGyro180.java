package org.usfirst.frc.team3502.robot.commands.DriveClimb.Gyro;

import org.usfirst.frc.team3502.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveGyro180 extends CommandGroup {
    
    public  DriveGyro180() {
		addSequential(new DriveToGyroHeading(180 + RobotMap.gyro.getAngle()));
    }
}
