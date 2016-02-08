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
public class manualLifter extends Command {
	
	private double rate, raw, distance, operatorY;
	private boolean brake, prevBrake;
	public static double lifterOutputValue;
	private static final String path = "/logs/Lifter Brake.txt";
	private static final Timer timer = new Timer();

    public manualLifter() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.lifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	Robot.lifter.disable();
    	Robot.lifter.setVoltageRamp();
    	Robot.lifter.setDistance();
    	Robot.lifter.getPIDController().reset();
    	//Robot.lift.resetEncoder();    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	operatorY = -Robot.oi.getOperatorY();
    	if (operatorY < 0){
    		operatorY = - (operatorY*operatorY);
    	}
    	else{
    		operatorY = (operatorY*operatorY);
    	}
    	lifterOutputValue = operatorY;
    	brake = Robot.oi.getOperatorBrake();
    	
    	//SmartDashboard.putBoolean("Tote Stopper", Robot.lift.getTote());
    	//SmartDashboard.putBoolean("Lift Stopper", Robot.lift.getLift());
    	
    	Robot.lifter.setLiftMotor(operatorY);
    	 
    	SmartDashboard.putNumber("Operator YValue", operatorY);
    	
    	if(brake && (brake!=prevBrake)){
    		try {
				writeFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		Robot.lifter.switchBrake();
    	}
    	prevBrake = brake;
    	/*
    	if(Robot.lifter.getCurrent()>35){
    		Robot.lifter.setBrakeOn();
    	}*/
    	
    	raw = Robot.lifter.getRaw();
    	rate = Robot.lifter.getRate();
    	distance = Robot.lifter.getPosition();
    	
    	//SmartDashboard.putNumber("Raw", raw);
    	//SmartDashboard.putNumber("Rate", rate);
    	SmartDashboard.putNumber("Lift Position", distance);    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	timer.stop();
    	timer.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	timer.stop();
    	timer.reset();
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
    	outputFile.write(timer.get()+"\tmanual");
    	outputFile.newLine();
    	outputFile.close();
    }
}
