package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.RobotMap;
import org.usfirst.frc.team3502.robot.commands.RegulatedDrive;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

public class CurrentDrive extends Subsystem {
	//tolerance value
	public static final int
		TOLERANCE = 0;
	
	//predefined positions for the lifter
	public static final double
		MID = 5,
		BOTTOM = 0.0;
	
	//Closed-loop parameters.
	public static final double
		P = 5.0,
		I = 0.0,
		D = 0.0;
	
	//CANTalon creation
	private static final CANTalon driveMotor = new CANTalon(RobotMap.driveMotorPort);
	private static final CANTalon driveMotorFollow = new CANTalon(RobotMap.driveMotorFollowPort);
    
	//Consturctor, should set up internal PID stuffs
	public CurrentDrive(){
		driveMotor.enableLimitSwitch(true, true);
		driveMotor.ConfigRevLimitSwitchNormallyOpen(false);
		driveMotor.ConfigFwdLimitSwitchNormallyOpen(true);
		//setting PID values, this does update when I look on the roboRIO web interface
		driveMotor.setPID(P, I, D, 0, 0, 0, 0);
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
    
    //sets the position of the lifter using the CANTalon's set() function, hopefully this does PID
    public void set(double out){
    	driveMotor.set(out);
    }
    
    public void setCurrent(double out, double maxCurrent){
    	driveMotor.set(out);
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