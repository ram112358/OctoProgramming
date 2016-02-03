package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.RobotMap;
import org.usfirst.frc.team3502.robot.commands.DriveOMatic;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BasicDrive extends Subsystem {

	private static final CANTalon rightOne = new CANTalon(RobotMap.rightOnePort);
	private static final CANTalon rightTwo = new CANTalon(RobotMap.rightTwoPort);
	private static final CANTalon leftOne = new CANTalon(RobotMap.leftOnePort);
	private static final CANTalon leftTwo = new CANTalon(RobotMap.leftTwoPort);
	
	public BasicDrive(){
		rightOne.changeControlMode(TalonControlMode.PercentVbus);
		leftOne.changeControlMode(TalonControlMode.PercentVbus);
		rightTwo.changeControlMode(TalonControlMode.Follower);
		rightTwo.set(RobotMap.rightOnePort);
		leftTwo.changeControlMode(TalonControlMode.Follower);
		leftTwo.set(RobotMap.leftOnePort);
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new DriveOMatic());
    }
}

