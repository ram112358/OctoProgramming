package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class TopDuck extends Subsystem {
	
	public double
		JoySet = 0.0;
	
	//ducked
	private final double
		p = 0.96,
		i = 0.0,
		d = 0.0,
		f = 0.0,
		closeLoopRampRate = 0.0;
	public final int
		izone = 0;
	private final int
		profile = 0;
	
	// unducked
	private final double
		pUn = 0.43,
		iUn = 0.0002,
		dUn = 0.0,
		fUn = 0.0,
		closeLoopRampRateUn = 0.0;
	public final int
		izoneUn = 150;
	private final int
		profileUn = 1;

	private final CANTalon topTalon = new CANTalon(RobotMap.topDuckPort);
	private final CANTalon topAuxTalon = new CANTalon(RobotMap.topDuckAuxPort);
	
	private final DoubleSolenoid duckBrake = new DoubleSolenoid(33, RobotMap.BrakeForward, RobotMap.BrakeReverse);
	
	public TopDuck(){
		topTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		topTalon.enableLimitSwitch(true, true);
		topTalon.changeControlMode(TalonControlMode.PercentVbus);
		topTalon.enableBrakeMode(true);
    	// topTalon.setPID(pUn, iUn, dUn, fUn, izoneUn, closeLoopRampRateUn, profileUn);
    	topTalon.setPID(p, i, d, f, izone, closeLoopRampRate, profile);
    	
    	topAuxTalon.changeControlMode(TalonControlMode.Follower);
    	topAuxTalon.set(RobotMap.topDuckPort);
	}
	
    public void initDefaultCommand() {
    }

    public void set(double outputValue){
    	topTalon.set(outputValue);
    }

    public void setSlow(double outputValue){
    	topTalon.set(outputValue * 0.4);
    }

    public void JoySetDrive(double joystickValue){
    	if (!topTalon.isFwdLimitSwitchClosed() && joystickValue >= 0.0 || !topTalon.isRevLimitSwitchClosed() && joystickValue <= 0.0)
    		JoySet += joystickValue / 4096 * 100;
    	topTalon.set(JoySet);
    }

    public void setJoySet(double JoySet){
    	this.JoySet = JoySet;
    }
    
    public double getJoySet(){
    	return JoySet;
    }

    public double getSpeed(){
    	return topTalon.getSpeed();
    }
    
    public int getEncPosition(){
    	return topTalon.getEncPosition();
    }
    
    public void setEncPosition(int pos){
    	topTalon.setEncPosition(pos);
    }
    
    public void setPositionMode(){
    	topTalon.changeControlMode(TalonControlMode.Position);
    	setNotBrakeMode();
    	setJoySet((double)getEncPosition() / 4096);
    }
    
    public void setThrottleMode(){
    	topTalon.changeControlMode(TalonControlMode.PercentVbus);
    	setNotBrakeMode();
    	topTalon.set(0);
    }
    
    public void setVoltageMode(){
    	topTalon.changeControlMode(TalonControlMode.Voltage);
    }
    
    public int getClosedLoopError(){
    	return topTalon.getClosedLoopError();
    }
    
    public double getSetpoint(){
    	return topTalon.getSetpoint();
    }
    
    public void setBrakeMode(){
    	duckBrake.set(DoubleSolenoid.Value.kForward);
    }
    
    public void setNotBrakeMode(){
    	duckBrake.set(DoubleSolenoid.Value.kReverse);
    }
    
    public boolean getBrakeMode() {
    	if (duckBrake.get() == DoubleSolenoid.Value.kForward)
    		return true;
    	return false;
    }
}