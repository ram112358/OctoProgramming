package org.usfirst.frc.team3502.robot;

import org.usfirst.frc.team3502.robot.commands.DriveClimb.DriveOMatic;
import org.usfirst.frc.team3502.robot.commands.DriveClimb.SineDriving;
import org.usfirst.frc.team3502.robot.commands.Duck.BothDuckIt;
import org.usfirst.frc.team3502.robot.commands.Duck.BottomDuckIt;
import org.usfirst.frc.team3502.robot.commands.Duck.BottomThrottle;
import org.usfirst.frc.team3502.robot.commands.Duck.ClearDuckEncoders;
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
		duckJoy = new Joystick(RobotMap.duckJoyPort),
		rightJoy = new Joystick(RobotMap.rightJoyPort),
		leftJoy = new Joystick(RobotMap.leftJoyPort);
		
	
	Button
		bothDuckButton = new JoystickButton(duckJoy, RobotMap.bothDuckButton),
	    bottomDuckButton = new JoystickButton(duckJoy, RobotMap.bottomDuckButton),
	    topDuckButton = new JoystickButton(duckJoy, RobotMap.topDuckButton),
	    intakeInButton = new JoystickButton(duckJoy, RobotMap.intakeInButton),
	    intakeOutButton = new JoystickButton(duckJoy, RobotMap.intakeOutButton),
	    bottomFullUpButton = new JoystickButton(duckJoy, RobotMap.bottomFullUpButton),
	    bottomTimedFullUpButton = new JoystickButton(duckJoy, RobotMap.bottomTimedFullUpButton),
    	climbingButton = new JoystickButton(rightJoy, RobotMap.climbingButton),
    	rightIntakeInButton = new JoystickButton(rightJoy, RobotMap.rightIntakeInButton),
    	rightIntakeOutButton = new JoystickButton(rightJoy, RobotMap.rightIntakeOutButton),
		clearEncButton = new JoystickButton(duckJoy, RobotMap.clearEncButton),
		regDriveButton = new JoystickButton(leftJoy, RobotMap.regDriveButton),
		sineDriveButton = new JoystickButton(leftJoy, RobotMap.sineDriveButton),
		
		topThrottle = new JoystickButton(duckJoy, 6),
		bottomThrottle = new JoystickButton(duckJoy, 4);
		
	
	public OI(){
		// button.whenPressed(new ExampleCommand());
		// button.whileHeld(new ExampleCommand());
		// button.whenReleased(new ExampleCommand());
		
		bothDuckButton.whenPressed(new BothDuckIt());
		topDuckButton.whenPressed(new TopDuckIt());
		bottomDuckButton.whenPressed(new BottomDuckIt());
		clearEncButton.whenPressed(new ClearDuckEncoders());
		regDriveButton.whenPressed(new DriveOMatic());
		sineDriveButton.whenPressed(new SineDriving());
		
		// bottomTimedFullUpButton.whenPressed(new BottomTimedFullUp());
		
		//climbingButton.whileHeld(new ClimbingMode());
		// bottomFullUpButton.whileHeld(new BottomFullUp());
		
		topThrottle.whenPressed(new TopThrottle());
		bottomThrottle.whenPressed(new BottomThrottle());
	}
	
	
	public double getDuckY(){
		if (duckJoy.getY() > 0.025 || duckJoy.getY() < -0.025)
			return duckJoy.getY();
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
		if (intakeInButton.get() || rightIntakeInButton.get())
			return true;
		return false;
	}

	public boolean getIntakeOutButton(){
		if (intakeOutButton.get() || rightIntakeOutButton.get())
			return true;
		return false;
	}
	
	public double getIntakeThrottle(){
		return -(duckJoy.getThrottle())/2 + 0.5;
	}
}
