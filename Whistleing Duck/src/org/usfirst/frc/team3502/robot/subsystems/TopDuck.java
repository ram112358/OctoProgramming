package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TopDuck extends Subsystem {
	
	private final double
		p = 0.1,
		i = 0.0,
		d = 0.0;
	
	private static final CANTalon topTalon = new CANTalon(RobotMap.topDuckPort);
	
	public TopDuck(){
		topTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		topTalon.enableLimitSwitch(false, false);
		topTalon.changeControlMode(TalonControlMode.PercentVbus);
		topTalon.enableBrakeMode(true);
    	topTalon.setPID(p, i, d);
	}
	
    public void initDefaultCommand() {
    	
    }

    public void setSlow(double outputValue){
    	topTalon.set(outputValue * .4);
    }
    
    public void set(double outputValue){
    	topTalon.set(outputValue);
    }
    
    public double getSpeed(){
    	return topTalon.getSpeed();
    }
    
    public int getEncPosition(){
    	return topTalon.getEncPosition();
    }
    
    public void setEncPosition(double pos){
    	topTalon.setPosition(pos);
    }
    
    
    public void setPositionMode(){
    	topTalon.changeControlMode(TalonControlMode.Position);
    	topTalon.setPID(p, i, d);
    }
    
    public void setThrottleMode(){
    	topTalon.changeControlMode(TalonControlMode.PercentVbus);
    	topTalon.set(0);
    }
}