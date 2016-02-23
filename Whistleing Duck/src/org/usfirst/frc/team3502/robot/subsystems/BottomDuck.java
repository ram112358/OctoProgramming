package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BottomDuck extends Subsystem {
	
	public double
		JoySet = 0.0;
	
	//ducked
	private final double
		p = 0.0001,
		i = 0.0,
		d = 0.0,
		f = 0.0,
		closeLoopRampRate = 0.0;
	private final int
		izone = 0,
		profile = 0;
	
	// unducked
	private final double
		pUn = 0.09,
		iUn = 0.0002,
		dUn = 0.0,
		fUn = 0.0,
		closeLoopRampRateUn = 0.0;
	private final int
		izoneUn = 150,
		profileUn = 1;

	private final CANTalon bottomTalon = new CANTalon(RobotMap.bottomDuckPort);
	private final CANTalon bottomAuxTalon = new CANTalon(RobotMap.bottomDuckAuxPort);
	
	public BottomDuck(){
		bottomTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		bottomTalon.enableLimitSwitch(true, true);
		bottomTalon.changeControlMode(TalonControlMode.PercentVbus);
		bottomTalon.enableBrakeMode(true);
    	// bottomTalon.setPID(pUn, iUn, dUn, fUn, izoneUn, closeLoopRampRateUn, profileUn);
    	bottomTalon.setPID(p, i, d, f, izone, closeLoopRampRate, profile);
    	
    	bottomAuxTalon.changeControlMode(TalonControlMode.Follower);
    	bottomAuxTalon.set(RobotMap.bottomDuckPort);
	}
	
    public void initDefaultCommand() {
    }

    public void set(double outputValue){
    	bottomTalon.set(- outputValue);
    }

    public void setSlow(double outputValue){
    	bottomTalon.set(- outputValue * 0.3);
    }

    public void JoySetDrive(double joystickValue){
    	if (!bottomTalon.isFwdLimitSwitchClosed() && joystickValue <= 0.0 || !bottomTalon.isRevLimitSwitchClosed() && joystickValue >= 0.0)
    		JoySet += joystickValue / 4096 * 100;
    	bottomTalon.set(- JoySet);
    }

    public void setJoySet(double JoySet){
    	this.JoySet = JoySet;
    }
    
    public double getJoySet(){
    	return JoySet;
    }

    public double getSpeed(){
    	return bottomTalon.getSpeed();
    }

    public int getEncPosition(){
    	return bottomTalon.getEncPosition();
    }
    
    public void setEncPosition(int pos){
    	bottomTalon.setEncPosition(pos);
    }
    
    public void setPositionMode(){
    	bottomTalon.changeControlMode(TalonControlMode.Position);
    	setJoySet(- (double)getEncPosition() / 4096);
    }
    
    public void setThrottleMode(){
    	bottomTalon.changeControlMode(TalonControlMode.PercentVbus);
    	bottomTalon.set(0);
    }
    
    public int getClosedLoopError(){
    	return bottomTalon.getClosedLoopError();
    }
    
    public double getSetpoint(){
    	return bottomTalon.getSetpoint();
    }
}