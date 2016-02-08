package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.RobotMap;
import org.usfirst.frc.team3502.robot.commands.manualIntake;
import org.usfirst.frc.team3502.robot.commands.manualIntakeFull;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Intake extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private static final CANTalon
		leftCrabMotor = RobotMap.leftCrabMotor,
		rightCrabMotor = RobotMap.rightCrabMotor;
	private static final DoubleSolenoid crab = RobotMap.crab;
	private static final DigitalInput toteStopper = RobotMap.toteStopper;
	private boolean toteStopperValue;
	private static boolean toteStopperPrevValue;
	public static boolean toteStopperReturnValue;
	public static boolean openCrab;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new manualIntake());
    }
    
    public void setCrabMotors(double outputValueLeft, double outputValueRight){
    	toteStopperValue = !toteStopper.get();
    	if((toteStopperValue)&&((outputValueLeft<0)||(outputValueRight > 0))){ //out- left:1, right:-1
    		outputValueLeft = 0;
    		outputValueRight = 0;
    	}
    	leftCrabMotor.set(outputValueLeft);
    	rightCrabMotor.set(outputValueRight);
    }
    
    public boolean getTote(){
    	return !toteStopper.get();
    }
    
    public void setCrabPiston(boolean open){
    	if(open){
    		crab.set(DoubleSolenoid.Value.kForward);
    		openCrab = true;
    	}
    	else{
    		crab.set(DoubleSolenoid.Value.kReverse);
    		openCrab = false;
    	}
    	SmartDashboard.putBoolean("Crabs Open", openCrab);
    }
}

