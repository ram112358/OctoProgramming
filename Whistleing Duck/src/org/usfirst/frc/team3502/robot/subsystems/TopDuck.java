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
		p = 0.09,
		i = 0.0002,
		d = 0.0,
		f = 0.0,
		closeLoopRampRate = 0.0;
	private final int
		izone = 150;
	
	// unducked
	private final double
		pUn = 0.09,
		iUn = 0.0002,
		dUn = 0.0,
		fUn = 0.0,
		closeLoopRampRateUn = 0.0;
	private final int
		izoneUn = 150;

	private final CANTalon topTalon = new CANTalon(RobotMap.topDuckPort);
	private final CANTalon topAuxTalon = new CANTalon(RobotMap.topDuckAuxPort);
	
	private final DoubleSolenoid duckBrake = new DoubleSolenoid(33, RobotMap.BrakeForward, RobotMap.BrakeReverse);
	
	public TopDuck(){
		topTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		topTalon.enableLimitSwitch(true, true);
		topTalon.changeControlMode(TalonControlMode.PercentVbus);
		topTalon.enableBrakeMode(true);
    	topTalon.setPID(pUn, iUn, dUn, fUn, izoneUn, closeLoopRampRateUn, 0);
    	topTalon.setPID(p, i, d, f, izone, closeLoopRampRate, 1);
    	
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
    		JoySet += joystickValue/4096*100;
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
    	topTalon.disable();
    	topTalon.changeControlMode(TalonControlMode.Position);
    	topTalon.enable();
    	setNotBrakeMode();
    	//setJoySet(getEncPosition());
    	set(getEncPosition());
    }
    
    public void setThrottleMode(){
    	topTalon.changeControlMode(TalonControlMode.PercentVbus);
    	topTalon.set(0);
    }
    
    public int getClosedLoopError(){
    	return topTalon.getClosedLoopError();
    }
    
    public void setBrakeMode(){
    	duckBrake.set(DoubleSolenoid.Value.kForward);
    }
    
    public void setNotBrakeMode(){
    	duckBrake.set(DoubleSolenoid.Value.kReverse);
    }
    
    public double getSetpoint(){
    	return topTalon.getSetpoint();
    }
    
    public boolean getBrakeMode() {
    	if (duckBrake.get() == DoubleSolenoid.Value.kForward)
    		return true;
    	return false;
    }
}