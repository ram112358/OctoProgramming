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
		bothDuckJoy = new Joystick(RobotMap.bothDuckJoyPort),
		intakeJoy = new Joystick(RobotMap.intakeJoyPort),
		rightJoy = new Joystick(RobotMap.rightJoyPort),
		leftJoy = new Joystick(RobotMap.leftJoyPort);
	
	Button
		bothDuckButton = new JoystickButton(bothDuckJoy, RobotMap.bothDuckButtonPort),
	    bottomDuckButton = new JoystickButton(bothDuckJoy, RobotMap.bothDuckButtonPort),
	    topDuckButton = new JoystickButton(bothDuckJoy, RobotMap.bothDuckButtonPort);
	
	public OI(){
		//button.whenPressed(new ExampleCommand());
		//button.whileHeld(new ExampleCommand());
		//button.whenReleased(new ExampleCommand());
		bothDuckButton.whenPressed(new BothDrive());
		topDuckButton.whenPressed(new BottomDrive());
		topDuckButton.whenPressed(new TopDrive());
	}

	public double getDuckY(){
		return bothDuckJoy.getY();
	}

	public double getIntakeY(){
		return intakeJoy.getY();
	}

	public double getRightY(){
		return rightJoy.getY();
	}
	
	public double getLeftY(){
		return leftJoy.getY();
	}
}
