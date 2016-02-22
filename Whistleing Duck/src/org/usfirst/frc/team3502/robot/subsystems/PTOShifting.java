package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.RobotMap;
import org.usfirst.frc.team3502.robot.commands.DriveClimb.DriveMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PTOShifting extends Subsystem {
    
	private DoubleSolenoid PTOShifters = new DoubleSolenoid(33, RobotMap.PTOForward, RobotMap.PTOReverse);

    public void initDefaultCommand() {
    	new DriveMode();
    }

    public void setDriveMode(){
    	PTOShifters.set(DoubleSolenoid.Value.kForward);
    }
    
    public void setClimbMode(){
    	PTOShifters.set(DoubleSolenoid.Value.kReverse);
    }
}