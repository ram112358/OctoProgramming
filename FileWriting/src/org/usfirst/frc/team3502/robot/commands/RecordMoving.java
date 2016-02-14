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
	private String note;
	private double
		power,
		startTime,
		endTime;
	private double[]
		time;
	private int
		startPosition,
		n,
		counter;
	private int[]
		position,
		velocity;
	private boolean[]
		beingPowered;
	//private static final Timer timer = new Timer();
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
    	beingPowered = new boolean[1000];
    	n = 0;
    	
    	//timer.start();
    	startTime = Timer.getFPGATimestamp();
    }

    protected void execute() {
    	time[n] = Timer.getFPGATimestamp() - startTime;
    	position[n] = startPosition - Robot.drive.getPosition();
    	velocity[n] = Robot.drive.getVelocity();
    	n = n + 1;

    	if (Robot.oi.getWriteFileButton()){
    		Robot.drive.set(power);
    		beingPowered[n] = true;
    		endTime = time[n];
    	}
    	else{
    		Robot.drive.set(0.0);
    		beingPowered[n] = true;
    	}
    }

    protected boolean isFinished() {
    	if (time[n] - endTime >= 1.5)
    		return true;
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
    		outputFile.write("Vel\tPos\tTime\tBeingPowered\tPower Level: " + power + "\tSpecial Note: " + note);
    		outputFile.newLine();
        	outputFile.close();
    	}
    }
    
    private void writeFile() throws IOException{
    	BufferedWriter outputFile = new BufferedWriter(new FileWriter(path, true));
    	for(counter = 0; counter < n; counter++){
    		outputFile.write(velocity[counter] + "\t" + position[counter] + "\t" + time[counter] + "\t" + beingPowered[counter]);
    		outputFile.newLine();
    	}
    	outputFile.close();
    }
}