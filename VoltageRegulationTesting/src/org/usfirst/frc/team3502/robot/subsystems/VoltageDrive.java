package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.RobotMap;
import org.usfirst.frc.team3502.robot.commands.RegulatedDrive;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

public class VoltageDrive extends Subsystem {
	
	//CANTalon creation
	private static final CANTalon driveMotor = new CANTalon(RobotMap.driveMotorPort);
	private static final CANTalon driveMotorFollow = new CANTalon(RobotMap.driveMotorFollowPort);
    
	//Consturctor, should set up internal PID stuffs
	public VoltageDrive(){
		driveMotor.changeControlMode(TalonControlMode.PercentVbus);
		
		driveMotorFollow.changeControlMode(TalonControlMode.Follower);
		driveMotorFollow.set(RobotMap.driveMotorPort);
	}

    public void initDefaultCommand() {
    	this.setDefaultCommand(new RegulatedDrive());
    }
    
    public void set(double out){
    	driveMotor.set(out);
    }

	public int getCurrent() {
		return (int)(driveMotor.getOutputCurrent()/1000);
	}
	
	public double getVoltage() {
		return DriverStation.getInstance().getBatteryVoltage();
	}
	
	//enables the motor to be moved
	public void enable() {
		driveMotor.enableControl();
	}
	
	//disables the motor from moving
	public void disable() {
		driveMotor.disableControl();
	}
}