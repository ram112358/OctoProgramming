package org.usfirst.frc.team3502.robot.commands;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class setDrivePositionLeft extends Command {
	private double setpoint, position, time, tolerance = Robot.driveLeft.tolerance;
	private boolean killPID;
	private static final double
		kP = Robot.driveLeft.getPIDController().getP(),
		kI = Robot.driveLeft.getPIDController().getI(),
		kD = Robot.driveLeft.getPIDController().getD();
	private static final String path = "/logs/LeftDrive_"+kP+"_"+kI+"_"+kD+".txt";
	private static final Timer timer = new Timer();

    public setDrivePositionLeft(double setpoint) {
    	this.setpoint = setpoint;
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveLeft);
        requires(Robot.dPneumatics);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	/*try {
			openFile();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	Robot.driveLeft.setSetpoint(setpoint);
    	SmartDashboard.putNumber("Left Setpoint", Robot.driveLeft.getSetpoint());
    	Robot.driveLeft.resetEncoder();
    	Robot.driveLeft.enable();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	position = Robot.driveLeft.getPosition();
    	killPID = Robot.oi.getKillPID();
    	SmartDashboard.putNumber("Current Left Drive Position (PID)", position);
    	SmartDashboard.putNumber("Current Left Drive Position Graph (PID)", position);
    	
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
    	if(killPID||(time>3)){
    		return true;
    	}
    	if((position < setpoint+tolerance) && (position > setpoint-tolerance)){
    		Robot.driveLeft.setLeftMotors(0);
    		return true;
    	}
    	else{
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	timer.reset();
    	Robot.driveLeft.setLeftMotors(0);
    	Robot.driveLeft.getPIDController().reset();
    	Robot.driveLeft.setLeftMotors(0);
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
    		outputFile.write("Time\tSetpoint\tPosition");
    		outputFile.newLine();
        	outputFile.close();
    	}
    }
    
    private void writeFile() throws IOException{
    	BufferedWriter outputFile = new BufferedWriter(new FileWriter(path, true));
    	outputFile.write(time+"\t"+setpoint+"\t"+position);
    	outputFile.newLine();
    	outputFile.close();
    }
}
