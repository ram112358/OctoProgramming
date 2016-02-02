package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.RobotMap;
import org.usfirst.frc.team3502.robot.commands.RegulatedDrive;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

public class CurrentDrive extends Subsystem {
	//current in mA
	public final int
		MAXCURRENT = 3000;
	
	//Closed-loop parameters.
	public static final double
		p = 0.0,
		i = 0.0,
		d = 0.0,
		f = 0.0,
		closeLoopRampRate = 0.0;
	
	public static final int
		izone = 0,
		profile = 0;
	
	//CANTalon creation
	private static final CANTalon driveMotor = new CANTalon(RobotMap.driveMotorPort);
	private static final CANTalon driveMotorFollow = new CANTalon(RobotMap.driveMotorFollowPort);
    
	//Consturctor, should set up internal PID stuffs
	public CurrentDrive(){
		driveMotor.enableLimitSwitch(true, true);
		driveMotor.ConfigRevLimitSwitchNormallyOpen(false);
		driveMotor.ConfigFwdLimitSwitchNormallyOpen(true);
		//setting PID values, this does update when I look on the roboRIO web interface
		driveMotor.setPID(p, i, d, f, izone, closeLoopRampRate, profile);
		//set the controlMode
		driveMotor.changeControlMode(TalonControlMode.Current);
		
		//set up the second drive motor to follow the other one
		driveMotorFollow.changeControlMode(TalonControlMode.Follower);
		driveMotorFollow.set(RobotMap.driveMotorPort);
	}
	// Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	this.setDefaultCommand(new RegulatedDrive());
    }
    
    public void set(double out){
    	driveMotor.set(out);
    }
    
    public void setCurrent(double out, int maxCurrent){
    	driveMotor.set(out * maxCurrent);
    }
    
	public int getCurrent() {
		return (int)(driveMotor.getOutputCurrent()/1000);
	}
	
	public int getError(){
		return driveMotor.getClosedLoopError();
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