package org.usfirst.frc.team3502.robot;

import org.usfirst.frc.team3502.robot.commands.FileTester;
import org.usfirst.frc.team3502.robot.commands.ProfileExecuter;
import org.usfirst.frc.team3502.robot.commands.ReadFile;
import org.usfirst.frc.team3502.robot.commands.RecordMoving;
import org.usfirst.frc.team3502.robot.commands.RecordMovingFancy;
import org.usfirst.frc.team3502.robot.commands.RecordMovingTimeOnly;
import org.usfirst.frc.team3502.robot.commands.PrintProfile;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	Joystick manStick = new Joystick(RobotMap.manJoyPort);
	Button writeFileButton = new JoystickButton(manStick, RobotMap.manJoyFour);
	Button playFileButton = new JoystickButton(manStick, RobotMap.manJoyTrigger);
	Button startFileButton = new JoystickButton(manStick, RobotMap.manJoyThumb);
	Button readFileButton = new JoystickButton(manStick, RobotMap.manJoyThree);
	Button printFileButton = new JoystickButton(manStick, 5);
	
	public OI(){
		// button.whenPressed(new ExampleCommand());
	    // button.whileHeld(new ExampleCommand());
	    // button.whenReleased(new ExampleCommand());
		
		// writeFileButton.whileHeld(new FileTester());
		// writeFileButton.whileHeld(new RecordMoving());
		// writeFileButton.whenPressed(new RecordMoving());
		// writeFileButton.whileHeld(new RecordMovingTimeOnly());
		
		writeFileButton.whenPressed(new RecordMovingFancy());
		
		playFileButton.whenPressed(new ProfileExecuter());
		
		readFileButton.whenPressed(new ReadFile());
		
		printFileButton.whenPressed(new PrintProfile());
	}

    public double getManY(){
    	return manStick.getY();
    }

    public boolean getWriteFileButton(){
    	return writeFileButton.get();
    }
    
    public boolean getStartFileButton(){
    	return startFileButton.get();
    }
}