package org.usfirst.frc.team3502.robot.commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class setLifterPosition extends Command {
	
	private double setpoint, position, time, tolerance = Robot.lifter.TOLERANCE;
	private boolean killPID, prevBottomState;
	private static final double
		kP = Robot.lifter.getPIDController().getP(),
		kI = Robot.lifter.getPIDController().getI(),
		kD = Robot.lifter.getPIDController().getD();
	private static final String path = "/logs/Lifter_"+kP+"_"+kI+"_"+kD+".txt";
	private static final Timer timer = new Timer();

    public setLifterPosition(double distance) {
        // Use requires() here to declare subsystem dependencies
    	this.setpoint = distance;
        requires(Robot.lifter);
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {/*
    	try {
			writeFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	prevBottomState = Robot.lifter.getLiftBottomStopper();
    	Robot.intake.setCrabPiston(true);
    	//SmartDashboard.putNumber("Previous Lift Setpoint", Robot.lift.getSetpoint());
    	Robot.lifter.setSetpoint(setpoint);
    	if(Robot.lifter.getSetpoint()==0){
    		tolerance = 0.1;
    	}
    	SmartDashboard.putNumber("Lift Tolerance 2", tolerance);
    	SmartDashboard.putNumber("Lift Setpoint", Robot.lifter.getSetpoint());
    	Robot.lifter.setBrakeOff();
    	// Robot.lift.setVoltageRamp();
    	Robot.lifter.enable();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {    	
    	position = Robot.lifter.getPosition();
    	killPID = Robot.oi.getKillPID();
    	SmartDashboard.putNumber("Lift Position (PID)", position);
    	SmartDashboard.putNumber("Current Lift Position Graph (PID)", position);
    	//Kirby (>'.')>
    	time = timer.get();
    	
    	/*try {
			writeFile();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	//TODO PID position/command to lift BIN with back lifter.
    	//TODO AUTONOMOUS
    	//TODO Automatically lift when red button is pressed?  Try it out.
    	//TODO SmartDashboard large display says lifter height  relative to important things:  Bottom, Tote Height, Step Height, Bin lift height, bin on tote height
    	//TODO Option to reverse controls so that the rear of the robot is driven as the front
    	
    	if(killPID){
    		return true;
    	}
    	if((position > 8)||((Robot.lifter.getLiftBottomStopper())&&(Robot.lifter.getLiftBottomStopper()!=prevBottomState))){
    		return true;
    	}
    	
    	if(((position < setpoint+tolerance) && (position > setpoint-tolerance)) || (time>1.75)){
    		return true;
    	}
    	else{
    		return false;
    	}/*
    	if(Robot.lifter.onTarget()||(time>2)){
    		return true;
    	}
    	else{
    		return false;
    	}*/
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.lifter.setBrakeOn();
    	timer.reset();
    	Robot.lifter.getPIDController().reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    private void openFile() throws IOException{
    	File file = new File(path);
    	if(!file.exists()) {
    		file.createNewFile();
    		BufferedWriter outputFile = new BufferedWriter(new FileWriter(path, true));
        	outputFile.close();
    	}
    }
    
    private void writeFile() throws IOException{
		openFile();
    	BufferedWriter outputFile = new BufferedWriter(new FileWriter(path, true));
    	outputFile.write(timer.get()+"\tposition");
    	outputFile.newLine();
    	outputFile.close();
    }
}
