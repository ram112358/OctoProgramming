package org.usfirst.frc.team3502.robot;

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
import org.usfirst.frc.team3502.robot.commands.Duck.ClearDuckEncoders;
import org.usfirst.frc.team3502.robot.commands.Duck.DontDuckIt;
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
	    clearEncButton = new JoystickButton(opJoy, DriverStationMap.clearEncButton),
	    setBothUpButton = new JoystickButton(opJoy, DriverStationMap.setBothUpButton),
	    setBothGroundButton = new JoystickButton(opJoy, DriverStationMap.setBothGroundButton),
	    
	    manualBrakeOnButton = new JoystickButton(opJoy, DriverStationMap.manualBrakeOnButton),
    	manualBrakeOffButton = new JoystickButton(opJoy, DriverStationMap.manualBrakeOffButton),

    	//Right Joystick Buttons
    	rightIntakeInButton = new JoystickButton(rightJoy, DriverStationMap.rightIntakeInButton),
    	rightIntakeOutButton = new JoystickButton(rightJoy, DriverStationMap.rightIntakeOutButton),
    	
    	climbingButton = new JoystickButton(rightJoy, DriverStationMap.climbingButton),
    	driveShiftButton = new JoystickButton(rightJoy, DriverStationMap.driveShiftButton),
    	climbShiftButton = new JoystickButton(rightJoy, DriverStationMap.climbShiftButton),
    	gyroDriveStraightButton = new JoystickButton(rightJoy, DriverStationMap.gyroDriveStraightButton),
    	
    	//Left Joystick Buttons    	
    	regDriveDuckEndButton = new JoystickButton(leftJoy, DriverStationMap.regDriveDuckEndButton),
    	regDriveAttackEndButton = new JoystickButton(leftJoy, DriverStationMap.regDriveAttackEndButton),
    	sineDriveDuckEndButton = new JoystickButton(leftJoy, DriverStationMap.sineDriveDuckEndButton),
    	sineDriveAttackEndButton = new JoystickButton(leftJoy, DriverStationMap.sineDriveAttackEndButton),
    	
		turn180Button = new JoystickButton(leftJoy, DriverStationMap.turn180Button),
		turn360Button = new JoystickButton(leftJoy, DriverStationMap.turn360Button),
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
		clearEncButton.whenPressed(new ClearDuckEncoders());
		setBothUpButton.whenPressed(new SetBothDucksGround());
		setBothGroundButton.whenPressed(new SetBothDucksGround());
			    
		regDriveDuckEndButton.whenPressed(new RegDriveDuckEnd());
		regDriveAttackEndButton.whenPressed(new RegDriveAttackEnd());
		sineDriveDuckEndButton.whenPressed(new SineDriveDuckEnd());
		sineDriveAttackEndButton.whenPressed(new SineDriveAttackEnd());
		
		driveShiftButton.whenPressed(new DriveMode());
		climbShiftButton.whenPressed(new ClimbMode());
		climbingButton.whenPressed(new ClimbMode());

		gyroDriveStraightButton.whileHeld(new DriveToGyroHeading());
		turn180Button.whileHeld(new DriveToGyroHeading());
		turn360Button.whileHeld(new DriveToGyroHeading());
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

	public boolean getManualBrakeOnButton() {
		return manualBrakeOnButton.get();
	}
	
	public boolean getManualBrakeOffButton() {
		return manualBrakeOffButton.get();
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
    
    public boolean getTurn360Button() {
    	return turn360Button.get();
    }
}
