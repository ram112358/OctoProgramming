package org.usfirst.frc.team3502.robot;

import org.usfirst.frc.team3502.robot.commands.FileTester;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	Joystick manStick = new Joystick(RobotMap.manJoyPort);
	Button writeFile = new JoystickButton(manStick, RobotMap.manJoyTrigger);
	
	public OI(){
		// button.whenPressed(new ExampleCommand());
	    // button.whileHeld(new ExampleCommand());
	    // button.whenReleased(new ExampleCommand());		
		writeFile.whileHeld(new FileTester());
	}

    public double getManY(){
    	return manStick.getY();
    }
    
}

