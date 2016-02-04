package org.usfirst.frc.team3502.robot;

import org.usfirst.frc.team3502.robot.commands.BothDrive;
import org.usfirst.frc.team3502.robot.commands.BottomDrive;
import org.usfirst.frc.team3502.robot.commands.TopDrive;

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
		duckButton = new JoystickButton(duckJoy, RobotMap.bothDuckButtonPort),
	    bottomDuckButton = new JoystickButton(duckJoy, RobotMap.bottomDuckButtonPort),
	    topDuckButton = new JoystickButton(duckJoy, RobotMap.topDuckButtonPort),
	    intakeInButton = new JoystickButton(duckJoy, RobotMap.intakeInButtonPort),
	    intakeOutButton = new JoystickButton(duckJoy, RobotMap.intakeOutButtonPort);
	
	public OI(){
		//button.whenPressed(new ExampleCommand());
		//button.whileHeld(new ExampleCommand());
		//button.whenReleased(new ExampleCommand());
		duckButton.whenPressed(new BothDrive());
		topDuckButton.whenPressed(new BottomDrive());
		topDuckButton.whenPressed(new TopDrive());
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
