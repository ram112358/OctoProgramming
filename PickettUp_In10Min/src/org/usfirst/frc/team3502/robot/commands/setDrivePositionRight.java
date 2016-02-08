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
public class setDrivePositionRight extends Command {
	private double setpoint, position, time, tolerance = Robot.driveRight.tolerance;
	private boolean killPID;
	private static final double
		kP = Robot.driveRight.getPIDController().getP(),
		kI = Robot.driveRight.getPIDController().getI(),
		kD = Robot.driveRight.getPIDController().getD();
	private static final String path = "/logs/RightDrive_"+kP+"_"+kI+"_"+kD+".txt";
	private static final Timer timer = new Timer();

    public setDrivePositionRight(double setpoint) {
    	this.setpoint = setpoint;
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveRight);
        requires(Robot.driveCenter);
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
    	Robot.driveRight.setSetpoint(setpoint);
    	SmartDashboard.putNumber("Right Setpoint", Robot.driveRight.getSetpoint());
    	Robot.driveRight.resetEncoder();
    	Robot.driveRight.enable();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	position = Robot.driveRight.getPosition();
    	killPID = Robot.oi.getKillPID();
    	SmartDashboard.putNumber("Current Right Drive Position (PID)", position);
    	SmartDashboard.putNumber("Current Right Drive Position Graph (PID)", position);
    	
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
    		Robot.driveRight.setRightMotors(0);
    		return true;
    	}
    	else{
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	timer.reset();
    	Robot.driveRight.setRightMotors(0);
    	Robot.driveRight.getPIDController().reset();
    	Robot.driveRight.setRightMotors(0);
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
