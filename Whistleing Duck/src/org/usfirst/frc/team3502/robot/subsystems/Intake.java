package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.RobotMap;
import org.usfirst.frc.team3502.robot.commands.IntakeDrive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	
	private static final CANTalon intakeMotor = new CANTalon(RobotMap.intakePort);
	
    public void initDefaultCommand() {
    	setDefaultCommand(new IntakeDrive());
    }
    
    public Intake(){
    	intakeMotor.changeControlMode(TalonControlMode.PercentVbus);
    	intakeMotor.enable();
    }
    
    public void set(double outputValue){
    	// intakeMotor.set(outputValue);
    }
}