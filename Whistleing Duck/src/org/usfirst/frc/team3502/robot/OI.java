package org.usfirst.frc.team3502.robot;

import org.usfirst.frc.team3502.robot.commands.DriveClimb.BasicClimbMode;
import org.usfirst.frc.team3502.robot.commands.DriveClimb.ClimbMode;
import org.usfirst.frc.team3502.robot.commands.DriveClimb.DriveMode;
import org.usfirst.frc.team3502.robot.commands.DriveClimb.DriveToGyroHeading;
import org.usfirst.frc.team3502.robot.commands.DriveClimb.RegDriveAttackEnd;
import org.usfirst.frc.team3502.robot.commands.DriveClimb.RegDriveDuckEnd;
import org.usfirst.frc.team3502.robot.commands.DriveClimb.SineDriveAttackEnd;
import org.usfirst.frc.team3502.robot.commands.DriveClimb.SineDriveDuckEnd;
import org.usfirst.frc.team3502.robot.commands.Duck.BothDuckIt;
import org.usfirst.frc.team3502.robot.commands.Duck.BottomDuckIt;
import org.usfirst.frc.team3502.robot.commands.Duck.BottomFullUp;
import org.usfirst.frc.team3502.robot.commands.Duck.BottomThrottle;
import org.usfirst.frc.team3502.robot.commands.Duck.BottomTimedFullUp;
import org.usfirst.frc.team3502.robot.commands.Duck.DontDuckIt;
import org.usfirst.frc.team3502.robot.commands.Duck.HoldPosition;
import org.usfirst.frc.team3502.robot.commands.Duck.IntakePosition;
import org.usfirst.frc.team3502.robot.commands.Duck.SetBothDucksGround;
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
		opJoy = new Joystick(DriverStationMap.opJoyPort),
		rightJoy = new Joystick(DriverStationMap.rightJoyPort),
		leftJoy = new Joystick(DriverStationMap.leftJoyPort);
		
	
	Button
		// Op Joystick Buttons
		dontDuckButton = new JoystickButton(opJoy, DriverStationMap.dontDuckButton),
		bothDuckButton = new JoystickButton(opJoy, DriverStationMap.bothDuckButton),
		topDuckButton = new JoystickButton(opJoy, DriverStationMap.topDuckButton),
		bottomDuckButton = new JoystickButton(opJoy, DriverStationMap.bottomDuckButton),
	    topThrottle = new JoystickButton(opJoy, DriverStationMap.topDuckThrottleButton),
	    bottomThrottle = new JoystickButton(opJoy, DriverStationMap.bottomDuckThrottleButton),
	    
	    // bottomFullUpButton = new JoystickButton(opJoy, DriverStationMap.bottomFullUpButton),
	    // bottomTimedFullUpButton = new JoystickButton(opJoy, DriverStationMap.bottomTimedFullUpButton),
	    setBothHoldButton = new JoystickButton(opJoy, DriverStationMap.setBothHoldButton),
	    setBothIntakeButton = new JoystickButton(opJoy, DriverStationMap.setBothIntakeButton),
	    
    	//Right Joystick Buttons
    	rightIntakeInButton = new JoystickButton(rightJoy, DriverStationMap.rightIntakeInButton),
    	rightIntakeOutButton = new JoystickButton(rightJoy, DriverStationMap.rightIntakeOutButton),
    	
    	climbingButton = new JoystickButton(rightJoy, DriverStationMap.climbingButton),
    	driveModeButton = new JoystickButton(rightJoy, DriverStationMap.driveModeButton),
    	gyroDriveStraightButton = new JoystickButton(leftJoy, DriverStationMap.gyroDriveStraightButton),
    	
    	//Left Joystick Buttons    	
    	// regDriveDuckEndButton = new JoystickButton(leftJoy, DriverStationMap.regDriveDuckEndButton),
    	// regDriveAttackEndButton = new JoystickButton(leftJoy, DriverStationMap.regDriveAttackEndButton),
    	sineDriveDuckEndButton = new JoystickButton(rightJoy, DriverStationMap.sineDriveDuckEndButton),
    	sineDriveAttackEndButton = new JoystickButton(leftJoy, DriverStationMap.sineDriveAttackEndButton),
    	
		turn180Button = new JoystickButton(leftJoy, DriverStationMap.turn180Button),
		resetGyroButton = new JoystickButton(leftJoy, DriverStationMap.resetGyroButton);
	
	public OI() {
		// button.whenPressed(new ExampleCommand());
		// button.whileHeld(new ExampleCommand());
		// button.whenReleased(new ExampleCommand());
		
		dontDuckButton.whenPressed(new DontDuckIt());
		bothDuckButton.whenPressed(new BothDuckIt());
		topDuckButton.whenPressed(new TopDuckIt());
		bottomDuckButton.whenPressed(new BottomDuckIt());
		topThrottle.whenPressed(new TopThrottle());
		bottomThrottle.whenPressed(new BottomThrottle());

		// bottomFullUpButton.whileHeld(new BottomFullUp());
		// bottomTimedFullUpButton.whenPressed(new BottomTimedFullUp());
		setBothHoldButton.whenPressed(new HoldPosition());
		setBothIntakeButton.whenPressed(new IntakePosition());
			    
		// regDriveDuckEndButton.whenPressed(new RegDriveDuckEnd());
		// regDriveAttackEndButton.whenPressed(new RegDriveAttackEnd());
		sineDriveDuckEndButton.whenPressed(new SineDriveDuckEnd());
		sineDriveAttackEndButton.whenPressed(new SineDriveAttackEnd());
		
		climbingButton.whenPressed(new BasicClimbMode());
		driveModeButton.whenPressed(new DriveMode());

		gyroDriveStraightButton.whileHeld(new DriveToGyroHeading(false, 0));  //The 2nd argument is only used for autonomous
		turn180Button.whileHeld(new DriveToGyroHeading(false, 0));
	}
	
	
	public double getDuckY() {
		if (opJoy.getY() > 0.025 || opJoy.getY() < -0.025)
			return opJoy.getY();
		else
			return 0.0;
	}

	public double getRightY() {
		if (rightJoy.getY() > 0.05 || rightJoy.getY() < -0.05)
			return rightJoy.getY();
		else
			return 0.0;
	}
	
	public double getLeftY() {
		if (leftJoy.getY() > 0.05 || leftJoy.getY() < -0.05)
			return leftJoy.getY();
		else
			return 0.0;
	}

	public boolean getIntakeInButton() {
		if (rightIntakeInButton.get())
			return true;
		return false;
	}

	public boolean getIntakeOutButton() {
		if (rightIntakeOutButton.get())
			return true;
		return false;
	}
	
	public int getOpPOV() {
		return opJoy.getPOV();
	}
	
	public double getIntakeThrottle() {
		return -(opJoy.getThrottle())/2 + 0.5;
	}
	
    public boolean getResetGyro() {
    	return resetGyroButton.get();
    }
    
    public boolean getGyroDriveStraightButton() {
    	return gyroDriveStraightButton.get();
    }
    
    public boolean getTurn180Button() {
    	return turn180Button.get();
    }
}
