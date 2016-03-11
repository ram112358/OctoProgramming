package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.Constants;
import org.usfirst.frc.team3502.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BottomDuck extends Subsystem {
	
	public double
		JoySet = 0.0;

	private final CANTalon duckTalon = new CANTalon(RobotMap.bottomDuckPort);
	private final CANTalon bottomAuxTalon = new CANTalon(RobotMap.bottomDuckAuxPort);
	
	public BottomDuck() {
		duckTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		duckTalon.setEncPosition(duckTalon.getPulseWidthPosition());
		
		duckTalon.enableLimitSwitch(true, true);
		
		duckTalon.changeControlMode(TalonControlMode.PercentVbus);
		duckTalon.enableBrakeMode(true);
    	
		// duckTalon.setPID(Constants.kBottomPUn, Constants.kBottomIUn, Constants.kBottomDUn, Constants.kBottomFUn, Constants.kBottomIzoneUn, Constants.kBottomCloseLoopRampRateUn, Constants.kBottomProfileUn);
    	duckTalon.setPID(Constants.kBottomP, Constants.kBottomI, Constants.kBottomD, Constants.kBottomF, Constants.kBottomIzone, Constants.kBottomCloseLoopRampRate, Constants.kBottomProfile);
    	duckTalon.setVoltageCompensationRampRate(Integer.MAX_VALUE);
    	
    	bottomAuxTalon.changeControlMode(TalonControlMode.Follower);
    	bottomAuxTalon.set(RobotMap.bottomDuckPort);
	}
	
    public void initDefaultCommand() {
    }

    public void set(double outputValue) {
    	duckTalon.set(- outputValue);
    }

    public void setSlow(double outputValue) {
    	set(outputValue * 0.3);
    }

    public void JoySetDrive(double joystickValue) {
    	if (getTopLimit() && joystickValue <= 0.0 || 
    			getBottomLimit() && joystickValue >= 0.0)
    		JoySet += joystickValue / 4096 * 100;
    	duckTalon.set(- JoySet);
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
    	setJoySet(- (double)getEncPosition() / 4096);
    }
    
    public void setThrottleMode() {
    	duckTalon.changeControlMode(TalonControlMode.PercentVbus);
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
    	return !duckTalon.isFwdLimitSwitchClosed();
    }
    
    public boolean getBottomLimit() {
    	return !duckTalon.isRevLimitSwitchClosed();
    }
    
    public TalonControlMode getTalonMode() {
    	return duckTalon.getControlMode();
    }
}