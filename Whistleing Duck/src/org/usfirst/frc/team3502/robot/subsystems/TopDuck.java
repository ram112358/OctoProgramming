package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.Constants;
import org.usfirst.frc.team3502.robot.RobotMap;
import org.usfirst.frc.team3502.robot.commands.Duck.DontDuckIt;
import org.usfirst.frc.team3502.robot.commands.Duck.TopDuckIt;
import org.usfirst.frc.team3502.robot.commands.Duck.TopThrottle;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class TopDuck extends Subsystem {
	
	public double
		JoySet = 0.0;

	private final CANTalon duckTalon = new CANTalon(RobotMap.topDuckPort);
	private final CANTalon topAuxTalon = new CANTalon(RobotMap.topDuckAuxPort);
	
	private final DoubleSolenoid duckBrake = new DoubleSolenoid(RobotMap.PCMPort, RobotMap.BrakeForward, RobotMap.BrakeReverse);
	
	public TopDuck() {
		duckTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		duckTalon.enableLimitSwitch(true, true);
		
		duckTalon.changeControlMode(TalonControlMode.PercentVbus);
		duckTalon.enableBrakeMode(true);
    	
		// duckTalon.setPID(Constants.kTopPUn, Constants.kTopIUn, Constants.kTopDUn, Constants.kTopFUn, Constants.kTopIzoneUn, Constants.kTopCloseLoopRampRateUn, Constants.kTopProfileUn);
    	duckTalon.setPID(Constants.kTopP, Constants.kTopI, Constants.kTopD, Constants.kTopF, Constants.kTopIzone, Constants.kTopCloseLoopRampRate, Constants.kTopProfile);
    	duckTalon.setVoltageCompensationRampRate(Integer.MAX_VALUE);
    	
    	topAuxTalon.changeControlMode(TalonControlMode.Follower);
    	topAuxTalon.set(RobotMap.topDuckPort);
	}
	
    public void initDefaultCommand() {;
    }

    public void set(double outputValue) {
    	duckTalon.set(outputValue);
    }

    public void setSlow(double outputValue) {
    	duckTalon.set(outputValue * 0.4);
    }

    public void JoySetDrive(double joystickValue) {
    	if (getTopLimit() && joystickValue >= 0.0 || 
    			getTopLimit() && joystickValue <= 0.0)
    		JoySet += joystickValue / 4096 * 100;
    	duckTalon.set(JoySet);
    }

    public void setJoySet(double JoySet) {
    	this.JoySet = JoySet;
    }
    
    public double getJoySet() {
    	return JoySet;
    }

    public double getSpeed() {
    	return duckTalon.getSpeed();
    }
    
    public int getEncPosition() {
    	return duckTalon.getEncPosition();
    }
    
    public void setEncPosition(int pos) {
    	duckTalon.setEncPosition(pos);
    }
    
    public void setPositionMode() {
    	duckTalon.changeControlMode(TalonControlMode.Position);
    	setNotBrakeMode();
    	setJoySet((double)getEncPosition() / 4096);
    }
    
    public void setThrottleMode() {
    	duckTalon.changeControlMode(TalonControlMode.PercentVbus);
    	setNotBrakeMode();
    	duckTalon.set(0);
    }
    
    public void setVoltageMode() {
    	duckTalon.changeControlMode(TalonControlMode.Voltage);
    }
    
    public int getClosedLoopError() {
    	return duckTalon.getClosedLoopError();
    }
    
    public double getSetpoint() {
    	return duckTalon.getSetpoint();
    }
    
    public boolean getTopLimit() {
    	return !duckTalon.isRevLimitSwitchClosed();
    }
    
    public boolean getBottomLimit() {
    	return !duckTalon.isFwdLimitSwitchClosed();
    }
    
    public void setBrakeMode() {
    	duckBrake.set(DoubleSolenoid.Value.kForward);
    }
    
    public void setNotBrakeMode() {
    	duckBrake.set(DoubleSolenoid.Value.kReverse);
    }
    
    public boolean getBrakeMode() {
    	if (duckBrake.get() == DoubleSolenoid.Value.kForward)
    		return true;
    	return false;
    }
}