package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BottomDuck extends Subsystem {
	
	private final double
		p = 0.1,
		i = 0.0,
		d = 0.0;
	
	private static final CANTalon bottomTalon = new CANTalon(RobotMap.bottomDuckPort);
	
	public BottomDuck(){
		bottomTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		bottomTalon.reverseSensor(true);
		bottomTalon.enableLimitSwitch(false, false);
		bottomTalon.changeControlMode(TalonControlMode.PercentVbus);
		bottomTalon.enableBrakeMode(true);
    	bottomTalon.setPID(p, i, d);
	}
	
    public void initDefaultCommand() {
    	
    }

    public void setSlow(double outputValue){
    	bottomTalon.set(outputValue * .35);
    }
    
    public void set(double outputValue){
    	bottomTalon.set(outputValue);
    }
    
    public double getSpeed(){
    	return bottomTalon.getSpeed();
    }

    public int getEncPosition(){
    	return bottomTalon.getEncPosition();
    }
    
    public void setEncPosition(double pos){
    	bottomTalon.setPosition(pos);
    }
    
    public void setPositionMode(){
    	bottomTalon.changeControlMode(TalonControlMode.Position);
    	bottomTalon.setPID(p, i, d);
    }
    
    public void setThrottleMode(){
    	bottomTalon.changeControlMode(TalonControlMode.PercentVbus);
    	bottomTalon.set(0);
    }
}