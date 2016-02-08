package org.usfirst.frc.team3502.robot;

import org.usfirst.frc.team3502.robot.commands.auton_BinAndTote;
import org.usfirst.frc.team3502.robot.commands.pickUpTop;
import org.usfirst.frc.team3502.robot.commands.pickUpTote;
import org.usfirst.frc.team3502.robot.commands.setLifterLower;
import org.usfirst.frc.team3502.robot.commands.setLifterPosition;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
     Joystick 
     	left = new Joystick(0), 
     	right = new Joystick(1), 
     	operator = new Joystick(3);
     Button
     	intakeIn = new JoystickButton(left, 2),
     	intakeOut = new JoystickButton(left, 3),
     	intakeLeft = new JoystickButton(left, 4),
     	intakeRight = new JoystickButton(left, 5);
     Button
     	defaultDrive = new JoystickButton(right, 2),
     	 /*leftDriveAutonF = new JoystickButton(right, 6),
     	 leftDriveAutonR = new JoystickButton(right, 7),
     	 rightDriveAutonF = new JoystickButton(right, 11),
     	 rightDriveAutonR = new JoystickButton(right, 10),*/
     	turn90Degrees = new JoystickButton(right, 10),
     	//turn180Degrees = new JoystickButton(right, 11),
     	resetEncoder = new JoystickButton(right, 9);
     Button 
     	lowerTote = new JoystickButton(operator, 12),
     	pickUpStack = new JoystickButton(operator, 2),
     	feedStack = new JoystickButton(operator, 4),
     	killPID = new JoystickButton(operator, 11),
     	brake = new JoystickButton(operator, 1),
     	overideManual = new JoystickButton(operator, 7),
     	crabPistons = new JoystickButton(operator, 3);
     
     
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    public OI(){
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    	/*rightDriveAutonF.whenPressed(new setDrivePositionRight(4));
    	rightDriveAutonR.whenPressed(new setDrivePositionRight(0));
    	leftDriveAutonF.whenPressed(new setDrivePositionLeft(4));
    	leftDriveAutonR.whenPressed(new setDrivePositionLeft(0));*/
    	turn90Degrees.whenPressed(new auton_BinAndTote());
    	//turn90Degrees.whenPressed(new setDrivePositionRight(1.675));
    	//turn90Degrees.whenPressed(new setDrivePositionLeft(1.675));
    	pickUpStack.whenPressed(new pickUpTote(Robot.lifter.BOTTOM, Robot.lifter.MIDTOTETOP));
    	feedStack.whenPressed(new pickUpTop(Robot.lifter.MIDTOTETOP));
    	lowerTote.whenPressed(new setLifterLower());
    	/*if(Robot.lift.getTote()){
    		new PickUpTote(); //NOT WORKING
    	}*/
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    }
    
    public double getLeftY(){
    	return left.getY();
    }
    
    public double getLeftX(){
    	return left.getX();
    }
    
    public boolean getCrabPistonButtonDriver(){
    	return left.getTrigger();
    }
    
    public boolean getCrabPistonButtonOperator(){
    	return crabPistons.get();
    }
    
    public boolean getIntakeIn(){
    	return intakeIn.get();
    }
    
    public boolean getIntakeOut(){
    	return intakeOut.get();
    }
    
    public boolean getIntakeLeft(){
    	return intakeLeft.get();
    }
    
    public boolean getIntakeRight(){
    	return intakeRight.get();
    }
    
    public int getPOV(){
    	return operator.getPOV();
    }
    
    public double getRightY(){
    	return right.getY();
    }
    
    public double getStraiff(){
    	return right.getX();
    }
    
    public boolean getRightTrigger(){
    	return right.getTrigger();
    }
    
    public boolean getDefaultDrive(){
    	return defaultDrive.get();
    }
    public double getOperatorY(){
    	return operator.getY();
    }
    
    public double getScaleCrabMotorsOperator(){
    	return operator.getThrottle();
    }
    
    public double getScaleCrabMotorsDriver(){
    	return left.getZ();
    }
    
    public double getOperatorX(){
    	return operator.getX();
    }
    
    public boolean getOperatorBrake(){
    	return brake.get();
    }
    
    public boolean getKillPID(){
    	return killPID.get();
    }
    
    public boolean getOverideManual(){
    	return overideManual.get();
    }
    
    public boolean getResetEncoder(){
    	return resetEncoder.get();
    }
    
    
}

