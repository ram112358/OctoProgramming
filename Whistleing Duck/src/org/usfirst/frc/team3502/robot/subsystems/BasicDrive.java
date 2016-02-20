package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.RobotMap;
import org.usfirst.frc.team3502.robot.commands.DriveOMatic;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BasicDrive extends Subsystem {

	private static final CANTalon rightTalon = new CANTalon(RobotMap.rightPort);
	private static final CANTalon rightAuxTalon = new CANTalon(RobotMap.rightAuxPort);
	private static final CANTalon leftTalon = new CANTalon(RobotMap.leftPort);
	private static final CANTalon leftAuxTalon = new CANTalon(RobotMap.leftAuxPort);
	
	public BasicDrive(){
		rightTalon.changeControlMode(TalonControlMode.PercentVbus);
		leftTalon.changeControlMode(TalonControlMode.PercentVbus);

		rightTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rightTalon.configEncoderCodesPerRev(512);
		leftTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		leftTalon.configEncoderCodesPerRev(512);
		
		rightTalon.enableBrakeMode(true);
		leftTalon.enableBrakeMode(true);
		
		rightAuxTalon.changeControlMode(TalonControlMode.Follower);
		rightAuxTalon.set(RobotMap.rightPort);
		leftAuxTalon.changeControlMode(TalonControlMode.Follower);
		leftAuxTalon.set(RobotMap.leftPort);

		rightTalon.enable();
		rightAuxTalon.enable();
		leftTalon.enable();
		leftAuxTalon.enable();
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new DriveOMatic());
    }
    
    public void setLeft(double outputValue){
    	rightTalon.set(-outputValue);
    }
    
    public void setRight(double outputValue){
    	leftTalon.set(outputValue);
    }
    
    public int getRightEnc(){
    	return rightTalon.getEncPosition();
    }
    
    public int getLeftEnc(){
    	return leftTalon.getEncPosition();
    }
}

