package org.usfirst.frc.team3502.robot;

import org.usfirst.frc.team3502.robot.commands.DriveClimb.ClimbMode;
import org.usfirst.frc.team3502.robot.commands.DriveClimb.DriveMode;
import org.usfirst.frc.team3502.robot.commands.DriveClimb.DriveOMatic;
import org.usfirst.frc.team3502.robot.commands.DriveClimb.SineDriving;
import org.usfirst.frc.team3502.robot.commands.Duck.BothDuckIt;
import org.usfirst.frc.team3502.robot.commands.Duck.BottomDuckIt;
import org.usfirst.frc.team3502.robot.commands.Duck.BottomThrottle;
import org.usfirst.frc.team3502.robot.commands.Duck.ClearDuckEncoders;
import org.usfirst.frc.team3502.robot.commands.Duck.DontDuckIt;
import org.usfirst.frc.team3502.robot.commands.Duck.TopDuckIt;
import org.usfirst.frc.team3502.robot.commands.Duck.TopThrottle;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	Joystick
		opJoy = new Joystick(RobotMap.opJoyPort),
		rightJoy = new Joystick(RobotMap.rightJoyPort),
		leftJoy = new Joystick(RobotMap.leftJoyPort);
		
	
	Button
		dontDuckButton = new JoystickButton(opJoy, RobotMap.dontDuckButton),
		bothDuckButton = new JoystickButton(opJoy, RobotMap.bothDuckButton),
		topDuckButton = new JoystickButton(opJoy, RobotMap.topDuckButton),
		bottomDuckButton = new JoystickButton(opJoy, RobotMap.bottomDuckButton),
	    topThrottle = new JoystickButton(opJoy, RobotMap.topDuckThrottleButton),
	    bottomThrottle = new JoystickButton(opJoy, RobotMap.bottomDuckThrottleButton),
	    
	    bottomFullUpButton = new JoystickButton(opJoy, RobotMap.bottomFullUpButton),
	    bottomTimedFullUpButton = new JoystickButton(opJoy, RobotMap.bottomTimedFullUpButton),
	    clearEncButton = new JoystickButton(opJoy, RobotMap.clearEncButton),

	    manualBrakeOnButton = new JoystickButton(opJoy, RobotMap.manualBrakeOnButton),
    	manualBrakeOffButton = new JoystickButton(opJoy, RobotMap.manualBrakeOffButton),
		
	    regDriveButton = new JoystickButton(leftJoy, RobotMap.regDriveButton),
		sineDriveButton = new JoystickButton(leftJoy, RobotMap.sineDriveButton),
		
		driveShiftButton = new JoystickButton(leftJoy, RobotMap.driveShiftButton),
		climbShiftButton = new JoystickButton(leftJoy, RobotMap.climbShiftButton),
		climbingButton = new JoystickButton(rightJoy, RobotMap.climbingButton),
		
		rightIntakeInButton = new JoystickButton(rightJoy, RobotMap.rightIntakeInButton),
		rightIntakeOutButton = new JoystickButton(rightJoy, RobotMap.rightIntakeOutButton);
	
	public OI(){
		// button.whenPressed(new ExampleCommand());
		// button.whileHeld(new ExampleCommand());
		// button.whenReleased(new ExampleCommand());
		
		dontDuckButton.whenPressed(new DontDuckIt());
		// bothDuckButton.whenPressed(new BothDuckIt());
		// topDuckButton.whenPressed(new TopDuckIt());
		// bottomDuckButton.whenPressed(new BottomDuckIt());
		topThrottle.whenPressed(new TopThrottle());
		bottomThrottle.whenPressed(new BottomThrottle());

		// bottomFullUpButton.whileHeld(new BottomFullUp());
		// bottomTimedFullUpButton.whenPressed(new BottomTimedFullUp());
		clearEncButton.whenPressed(new ClearDuckEncoders());
		
		regDriveButton.whenPressed(new DriveOMatic());
		sineDriveButton.whenPressed(new SineDriving());
		
		driveShiftButton.whenPressed(new DriveMode());
		climbShiftButton.whenPressed(new ClimbMode());
		//climbingButton.whenPressed(new ClimbMode());
	}
	
	
	public double getDuckY(){
		if (opJoy.getY() > 0.025 || opJoy.getY() < -0.025)
			return opJoy.getY();
		else
			return 0.0;
	}

	public double getRightY(){
		if (rightJoy.getY() > 0.05 || rightJoy.getY() < -0.05)
			return rightJoy.getY();
		else
			return 0.0;
	}
	
	public double getLeftY(){
		if (leftJoy.getY() > 0.05 || leftJoy.getY() < -0.05)
			return leftJoy.getY();
		else
			return 0.0;
	}

	public boolean getIntakeInButton(){
		if (rightIntakeInButton.get())
			return true;
		return false;
	}

	public boolean getIntakeOutButton(){
		if (rightIntakeOutButton.get())
			return true;
		return false;
	}
	
	public int getOpPOV(){
		return opJoy.getPOV();
	}
	
	public double getIntakeThrottle(){
		return -(opJoy.getThrottle())/2 + 0.5;
	}

	public boolean getManualBrakeOnButton() {
		return manualBrakeOnButton.get();
	}
	
	public boolean getManualBrakeOffButton() {
		return manualBrakeOffButton.get();
	}
}
