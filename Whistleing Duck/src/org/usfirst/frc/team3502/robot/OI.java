package org.usfirst.frc.team3502.robot;

import org.usfirst.frc.team3502.robot.commands.BothDuckIt;
import org.usfirst.frc.team3502.robot.commands.BottomDuckIt;
import org.usfirst.frc.team3502.robot.commands.BottomFullUp;
import org.usfirst.frc.team3502.robot.commands.BottomTimedFullUp;
import org.usfirst.frc.team3502.robot.commands.TopDuckIt;

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
		bothDuckButton = new JoystickButton(duckJoy, RobotMap.bothDuckButtonPort),
	    bottomDuckButton = new JoystickButton(duckJoy, RobotMap.bottomDuckButtonPort),
	    topDuckButton = new JoystickButton(duckJoy, RobotMap.topDuckButtonPort),
	    intakeInButton = new JoystickButton(duckJoy, RobotMap.intakeInButtonPort),
	    intakeOutButton = new JoystickButton(duckJoy, RobotMap.intakeOutButtonPort),
	    bottomFullUpButton = new JoystickButton(duckJoy, RobotMap.bottomFullUpButtonPort),
	    bottomTimedFullUpButton = new JoystickButton(duckJoy, RobotMap.bottomTimedFullUpButtonPort);
	
	public OI(){
		//button.whenPressed(new ExampleCommand());
		//button.whileHeld(new ExampleCommand());
		//button.whenReleased(new ExampleCommand());
		bothDuckButton.whenPressed(new BothDuckIt());
		topDuckButton.whenPressed(new TopDuckIt());
		bottomDuckButton.whenPressed(new BottomDuckIt());
		bottomFullUpButton.whileHeld(new BottomFullUp());
		bottomTimedFullUpButton.whenPressed(new BottomTimedFullUp());
		
	}
	
	
	public double getDuckY(){
		return duckJoy.getY();
	}

	public double getRightY(){
		return rightJoy.getY();
	}
	
	public double getLeftY(){
		return leftJoy.getY();
	}

	public boolean getIntakeInButton(){
		return intakeInButton.get();
	}

	public boolean getIntakeOutButton(){
		return intakeOutButton.get();
	}
	
	public double getIntakeThrottle(){
		return duckJoy.getThrottle();
	}
}
