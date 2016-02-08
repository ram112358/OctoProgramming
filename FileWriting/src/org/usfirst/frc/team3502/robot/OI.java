package org.usfirst.frc.team3502.robot;

import org.usfirst.frc.team3502.robot.commands.FileTester;
import org.usfirst.frc.team3502.robot.commands.RecordMoving;
import org.usfirst.frc.team3502.robot.commands.RecordMovingTimeOnly;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	Joystick manStick = new Joystick(RobotMap.manJoyPort);
	Button writeFileButton = new JoystickButton(manStick, RobotMap.manJoyTrigger);
	
	public OI(){
		// button.whenPressed(new ExampleCommand());
	    // button.whileHeld(new ExampleCommand());
	    // button.whenReleased(new ExampleCommand());
		
		// writeFileButton.whileHeld(new FileTester());
		// writeFileButton.whileHeld(new RecordMoving());
		// writeFileButton.whenPressed(new RecordMoving());
		writeFileButton.whileHeld(new RecordMovingTimeOnly());
	}

    public double getManY(){
    	return manStick.getY();
    }
 
    public boolean getWriteFileButton(){
    	return writeFileButton.get();
    }
}