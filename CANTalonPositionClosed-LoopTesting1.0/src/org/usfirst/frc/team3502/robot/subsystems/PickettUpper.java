package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.RobotMap;
import org.usfirst.frc.team3502.robot.commands.ManualPicker;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

public class PickettUpper extends Subsystem {
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
	private static final CANTalon liftMotor = new CANTalon(RobotMap.lifterMotorPort);
	private static final CANTalon liftMotorFollow = new CANTalon(RobotMap.lifterMotorFollowPort);
    
	//Consturctor, should set up internal PID stuffs
	public PickettUpper(){
		//set the feedback device to a quad encoder
		liftMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		//configure the codes per revolution
		liftMotor.configEncoderCodesPerRev(360);
		//not using the limit switches since I'm running the code on half of our drive base 
		liftMotor.enableLimitSwitch(true, true);
		liftMotor.ConfigRevLimitSwitchNormallyOpen(false);
		liftMotor.ConfigFwdLimitSwitchNormallyOpen(true);
		//setting PID values, this does update when I look on the roboRIO web interface
		liftMotor.setPID(P, I, D, 0, 0, 0, 0);
		//set the controlMode
		liftMotor.changeControlMode(TalonControlMode.PercentVbus);
		
		//set up the second drive motor to follow the other one
		liftMotorFollow.changeControlMode(TalonControlMode.Follower);
		liftMotorFollow.set(RobotMap.lifterMotorPort);
	}
	// Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	this.setDefaultCommand(new ManualPicker());
    }
    
    //sets the position of the lifter using the CANTalon's set() function, hopefully this does PID
    public void set(double out){
    	liftMotor.set(out);
    }
    
    //returns the position on the encoder in encoder ticks
	public int getPos() {
		return liftMotor.getEncPosition();
	}
	
	//enables the motor to be moved
	public void enable() {
		liftMotor.enableControl();
	}
	
	//disables the motor from moving
	public void disable() {
		liftMotor.disableControl();
	}
	
	//sets the talon to throttle mode ("normal mode")
	public void throttleMode() {
		liftMotor.changeControlMode(TalonControlMode.PercentVbus);
		liftMotor.set(0);
	}
	
	//sets the talon to closed loop position mode
	public void positionMode() {
		liftMotor.changeControlMode(TalonControlMode.Position);
		liftMotor.setPID(P, I, D);
	}
}