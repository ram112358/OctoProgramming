package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DrivePneumatics extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private static DoubleSolenoid driveMode = RobotMap.driveMode;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void switchDriveMode(boolean omni){
    	String mode;
    	if(omni){
    		driveMode.set(DoubleSolenoid.Value.kReverse);
    		mode = "Omni";
    		//setEncoderOmniValues();
    	}
    	else{
    		driveMode.set(DoubleSolenoid.Value.kForward);
    		mode = "Traction";
    		//setEncoderTractionValues();
    	}
    	//SmartDashboard.putString("Drive Mode", mode);
    }
}

