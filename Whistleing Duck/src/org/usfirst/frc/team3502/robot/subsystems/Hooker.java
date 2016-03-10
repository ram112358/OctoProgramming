package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.RobotMap;
import org.usfirst.frc.team3502.robot.commands.DriveClimb.KeepHooker;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Hooker extends Subsystem {
	
	private static final Servo servo = new Servo(RobotMap.HookerReleaseServo);

    public void initDefaultCommand() {
        setDefaultCommand(new KeepHooker());
    }
    
    public double getServoPosition() {
    	return servo.getAngle();
    }
    
    public void setServoPosition(double position) {
    	servo.setAngle(position);
    }
}