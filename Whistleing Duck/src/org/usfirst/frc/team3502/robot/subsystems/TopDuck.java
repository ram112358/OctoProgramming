package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TopDuck extends Subsystem {
	
	private static final CANTalon topTalon = new CANTalon(RobotMap.topDuckPort);
	
	public TopDuck(){
		topTalon.enableLimitSwitch(false, false);
		topTalon.changeControlMode(TalonControlMode.PercentVbus);
		topTalon.enableBrakeMode(true);
	}
	
    public void initDefaultCommand() {
    	
    }

    public void setSlow(double outputValue){
    	topTalon.set(outputValue * .4);
    }
    
    public void set(double outputValue){
    	topTalon.set(outputValue);
    }
}