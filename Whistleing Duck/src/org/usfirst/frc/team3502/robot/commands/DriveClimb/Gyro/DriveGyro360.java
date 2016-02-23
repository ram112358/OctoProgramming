package org.usfirst.frc.team3502.robot.commands.DriveClimb.Gyro;

import org.usfirst.frc.team3502.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveGyro360 extends CommandGroup {
    
    public  DriveGyro360() {
		addSequential(new DriveToGyroHeading(180 + RobotMap.gyro.getAngle()));
    }
}
