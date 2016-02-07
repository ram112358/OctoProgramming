package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RecordingDrive extends Subsystem {
	
	private static final CANTalon driveMotor = new CANTalon(RobotMap.driveMotorPort);
	private static final CANTalon driveMotorFollow = new CANTalon(RobotMap.driveMotorFollowPort);

	public RecordingDrive(){
		driveMotor.changeControlMode(TalonControlMode.PercentVbus);
		driveMotorFollow.changeControlMode(TalonControlMode.Follower);
		driveMotorFollow.set(RobotMap.driveMotorPort);
	}
	
    public void initDefaultCommand() {
    }
    
    public void set(double outputValue){
    	driveMotor.set(outputValue);
    }
}

