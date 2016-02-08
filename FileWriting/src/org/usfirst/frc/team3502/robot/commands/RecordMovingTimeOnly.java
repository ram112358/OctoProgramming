package org.usfirst.frc.team3502.robot.commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class RecordMovingTimeOnly extends Command {
	private double[]
		time;
	private int
		n,
		counter;
	private static final Timer timer = new Timer();
	private static final String path = "/home/lvuser/ProfileTest.txt";

    public RecordMovingTimeOnly() {
    	requires(Robot.drive);
    }

    protected void initialize() {
    	time = new double[1000];
    	n = 0;
    	timer.start();
    }

    protected void execute() {
    	time[n] = timer.get();
    	n = n + 1;

    	if (Robot.oi.getWriteFileButton())
    		Robot.drive.set(.5);
    	else
    		Robot.drive.set(0.0);
    }

    protected boolean isFinished() {
    	return false;
    }

    protected void end() {
    	Robot.drive.set(0.0);
    	try {
			openFile();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			openFile();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
    		outputFile.write("THIS IS ONLY A TEST OF THE TIME");
    		outputFile.newLine();
        	outputFile.close();
    	}
    }
    
    private void writeFile() throws IOException{
    	BufferedWriter outputFile = new BufferedWriter(new FileWriter(path, true));
    	for(counter = 0; counter < n; counter++){
    		outputFile.write("" + time[counter]);
    		outputFile.newLine();
    	}
    	outputFile.close();
    }
}