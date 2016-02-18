package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BottomDuck extends Subsystem {
	
	private final double
		p = 0.09,
		i = 0.0,
		d = 0.0,
		f = 0.0,
		closeLoopRampRate = 0.0;
	
	private final int
		izone = 0;

	private static final CANTalon bottomTalon = new CANTalon(RobotMap.bottomDuckOnePort);
	private static final CANTalon bottomAuxTalon = new CANTalon(RobotMap.bottomDuckTwoPort);
	
	public BottomDuck(){
		bottomTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		bottomTalon.enableLimitSwitch(false, false);
		bottomTalon.changeControlMode(TalonControlMode.PercentVbus);
		bottomTalon.enableBrakeMode(true);
    	bottomTalon.setPID(p, i, d, f, izone, closeLoopRampRate, 0);
    	
    	bottomAuxTalon.changeControlMode(TalonControlMode.Follower);
    	bottomAuxTalon.set(RobotMap.bottomDuckOnePort);
	}
	
    public void initDefaultCommand() {
    	
    }

    public void setSlow(double outputValue){
    	bottomTalon.set(- outputValue * 0.3);
    }
    
    public void set(double outputValue){
    	bottomTalon.set(- outputValue);
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
    }
    
    public void setThrottleMode(){
    	bottomTalon.changeControlMode(TalonControlMode.PercentVbus);
    	bottomTalon.set(0);
    }
    
    public int getClosedLoopError(){
    	return bottomTalon.getClosedLoopError();
    }
}