package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.RobotMap;
import org.usfirst.frc.team3502.robot.commands.DriveClimb.DriveMode;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PTOShifting extends Subsystem {
    
	private DoubleSolenoid PTOShifters = new DoubleSolenoid(RobotMap.PCMPort, RobotMap.PTOForward, RobotMap.PTOReverse);
	private BuiltInAccelerometer accel = new BuiltInAccelerometer();

    public void initDefaultCommand() {
    	new DriveMode();
    }

    public void setDriveMode() {
    	PTOShifters.set(DoubleSolenoid.Value.kForward);
    }
    
    public void setClimbMode() {
    	PTOShifters.set(DoubleSolenoid.Value.kReverse);
    }
    
    public double getAccelX() {
    	return accel.getX();
    }
    public double getAccelY() {
    	return accel.getY();
    }
    public double getAccelZ() {
    	return accel.getZ();
    }
}