package org.usfirst.frc.team3502.robot.commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class RecordMoving extends Command {
	private double power;
	private String note;
	
	private double[] time;
	private int
		startPosition,
		n,
		counter;
	private int[]
		position,
		velocity;
	private static final Timer timer = new Timer();
	private static final String path = "/home/lvuser/ProfileTest.txt";

    public RecordMoving() {
    	requires(Robot.drive);
    }

    protected void initialize() {
    	startPosition = Robot.drive.getPosition();
    	power = NetworkTable.getTable("Preferences").getNumber("Power Level", 0);
    	note = NetworkTable.getTable("Preferences").getString("Special Note", "");
    	
    	position = new int[1000];
    	velocity = new int[1000];
    	time = new double[1000];
    	n = 0;
    	
    	try {
			openFile();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	timer.start();
    }

    protected void execute() {
    	Robot.drive.set(power);
    	time[n] = timer.get();
    	position[n] = startPosition - Robot.drive.getPosition();
    	velocity[n] = Robot.drive.getVelocity();
    	n++;
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.drive.set(0.0);
    	try {
    		writeFile();
    	}
    	catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

    protected void interrupted() {
    	Robot.drive.set(0.0);
    	try {
    		writeFile();
    	}
    	catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }
    
    private void openFile() throws IOException{
    	File file = new File(path);
    	if(!file.exists()) {
    		file.createNewFile();
    		BufferedWriter outputFile = new BufferedWriter(new FileWriter(path, true));
    		outputFile.write("Vel\tPos\tTime\tPower Level:" + power + "\t Special Note:" + note);
    		outputFile.newLine();
        	outputFile.close();
    	}
    }
    
    private void writeFile() throws IOException{
    	BufferedWriter outputFile = new BufferedWriter(new FileWriter(path, true));
    	for(counter = 0; counter < n; counter++){
    	outputFile.write(velocity[n] + "\t" + position[n] + "\t" + time[n]);
    	outputFile.newLine();
    	outputFile.close();
    	}
    }
}