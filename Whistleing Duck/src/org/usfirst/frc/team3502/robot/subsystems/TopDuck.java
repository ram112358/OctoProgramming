package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TopDuck extends Subsystem {
	
	private static final CANTalon TopTalon = new CANTalon(RobotMap.topDuckPort);
	
	public TopDuck(){
		TopTalon.enableLimitSwitch(false, false);
		TopTalon.changeControlMode(TalonControlMode.PercentVbus);
	}
	
    public void initDefaultCommand() {
    	
    }
    
    public void set(double outputValue){
    	TopTalon.set(outputValue * .25);
    }
}