package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BottomDuck extends Subsystem {
	
	private static final CANTalon bottomTalon = new CANTalon(RobotMap.bottomDuckPort);
	
	public BottomDuck(){
		bottomTalon.enableLimitSwitch(false, false);
		bottomTalon.changeControlMode(TalonControlMode.PercentVbus);
	}
	
    public void initDefaultCommand() {
    	
    }
    
    public void set(double outputValue){
    	bottomTalon.set(outputValue * .25);
    }
}