package org.usfirst.frc.team3502.robot.commands;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class manualIntake extends Command {
	private boolean crabButton, crab, crabPrev;
	private boolean in, out, left, right;
	private double outputLeft, outputRight, scaleOutput;
	private boolean exit = false;
	private int crabDirection;
	private static int intakeControl;

    public manualIntake() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	intakeControl = Robot.intakeControl;
    	crabButton = crabPrev = false;
    	crab = Robot.intake.openCrab;
    	Robot.intake.setCrabPiston(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch (intakeControl){
	    	case 1:
	    		scaleOutput = Robot.oi.getScaleCrabMotorsDriver();
	    		scaleOutput = 1 - ((scaleOutput + 1)/2);
	    		setCrabMotorsDriver();
	    		crabButton = Robot.oi.getCrabPistonButtonDriver();
	    		break;
	    	case 2:
	    		scaleOutput = Robot.oi.getScaleCrabMotorsOperator();
	    		scaleOutput = 1 - ((scaleOutput + 1)/2);
	    		setCrabMotorsOperator();
	    		crabButton = Robot.oi.getCrabPistonButtonOperator();
	    		break;
	    	default:
	    		scaleOutput = 1 - ((Robot.oi.getScaleCrabMotorsOperator() + 1)/2);
	    		if(scaleOutput==0){
	    			scaleOutput = 1 - ((Robot.oi.getScaleCrabMotorsDriver() + 1)/2);
	    		}
	    		setCrabMotorsDual();
	    		crabButton = Robot.oi.getCrabPistonButtonDriver() || Robot.oi.getCrabPistonButtonOperator();
	    		break;
    	}
    	SmartDashboard.putNumber("Scaled Output", scaleOutput);
    	setCrabPistons();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intake.setCrabPiston(true);
    }
    
    private void setCrabMotorsDriver(){
    	in = Robot.oi.getIntakeIn(); 
    	out = Robot.oi.getIntakeOut();
    	left = Robot.oi.getIntakeLeft();
    	right = Robot.oi.getIntakeRight();
    	if(in){
    		outputLeft = -scaleOutput;
    		outputRight = scaleOutput;
    	}
    	else if(out){
    		outputLeft = scaleOutput;
    		outputRight = -scaleOutput;
    	}
    	else if(left){
    		outputLeft = outputRight = -scaleOutput;
    	}
    	else if(right){
    		outputLeft = outputRight = scaleOutput;
    	}
    	else{
    		outputLeft = outputRight = 0;
    	}
    	
    	//SmartDashboard.putNumber("Left Crab Output", outputLeft);
    	//SmartDashboard.putNumber("Right Crab Output", outputRight);
    	Robot.intake.setCrabMotors(outputLeft, outputRight);
    }
    
    private void setCrabMotorsOperator(){
    	crabDirection = Robot.oi.getPOV();
    	SmartDashboard.putNumber("POV", crabDirection);
    	switch (crabDirection){
	    	case 180:
	    		outputLeft = -scaleOutput;
	    		outputRight = scaleOutput;
	    		break;
	    	case 0:
	    		outputLeft = scaleOutput;
	    		outputRight = -scaleOutput;
	    		break;
	    	case 270:
	    		outputLeft = outputRight = -scaleOutput;
	    		break;
	    	case 90:
	    		outputLeft = outputRight = scaleOutput;
	    		break;
	    	default:
	    		outputLeft = outputRight = 0;
	    		break;
    	}
    	
    	//SmartDashboard.putNumber("Left Crab Output", outputLeft);
    	//SmartDashboard.putNumber("Right Crab Output", outputRight);
    	Robot.intake.setCrabMotors(outputLeft, outputRight);
    }
    
    private void setCrabMotorsDual(){
    	in = Robot.oi.getIntakeIn(); 
    	out = Robot.oi.getIntakeOut();
    	left = Robot.oi.getIntakeLeft();
    	right = Robot.oi.getIntakeRight();
    	crabDirection = Robot.oi.getPOV();
    	
    	if(in||out||left||right){
    		setCrabMotorsDriver();
    	}
    	else{
    		setCrabMotorsOperator();
    	}/*
    	if(in||(crabDirection==180)){
    		outputLeft = -scaleOutput;
    		outputRight = scaleOutput;
    	}
    	else if(out||(crabDirection==0)){
    		outputLeft = scaleOutput;
    		outputRight = -scaleOutput;
    	}
    	else if(left||(crabDirection==270)){
    		outputLeft = outputRight = -scaleOutput;
    	}
    	else if(right||(crabDirection==90)){
    		outputLeft = outputRight = scaleOutput;
    	}
    	else{
    		outputLeft = outputRight = 0;
    	}*/
    	
    	//SmartDashboard.putNumber("Left Crab Output", outputLeft);
    	//SmartDashboard.putNumber("Right Crab Output", outputRight);
    	Robot.intake.setCrabMotors(outputLeft, outputRight);
    }
    
    private void setCrabPistons(){
    	if((Robot.lifter.getPosition()<=3)&&(Robot.lifter.getPosition()>.1)&&(manualLifter.lifterOutputValue<-.2)){
    		Robot.intake.setCrabPiston(true);
    		crab = true;
    	}
    	
    	if ((crabButton != crabPrev) && (crabButton)){
    		crab = !crab;
    		Robot.intake.setCrabPiston(crab);
    	}
    	
    	crabPrev = crabButton;
    }
}
