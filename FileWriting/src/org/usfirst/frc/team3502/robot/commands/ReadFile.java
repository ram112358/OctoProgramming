package org.usfirst.frc.team3502.robot.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.usfirst.frc.team3502.robot.MotionProfile;
import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ReadFile extends Command {
	
	private static final String path = "/home/lvuser/ProfileTest.txt";
	
	private Scanner
		counter,
		in;
	
	private int trajectoryLength;
	
	private double[][] trajectory;
	
    public ReadFile() {
    	requires(Robot.drive);
    }
    
    protected void initialize() {
    	System.out.println(0);
    	initTrajectory();
    	fillTrajectory();
    	System.out.println();
    	setTrajectory();
    	System.out.println();
    	for (int x = 0; x < trajectoryLength; x++) {
    		System.out.println(trajectory[x][0]);
    		System.out.println(trajectory[x][1]);
    	}
    	for (int x = 0; x < MotionProfile.kNumPoints; x++) {
    		System.out.println(MotionProfile.Points[x][0]);
    		System.out.println(MotionProfile.Points[x][1]);
    	}
    }
    
    protected void execute() {
    	
    }
    
    protected boolean isFinished() {
        return false;
    }
    
    protected void end() {
    }
    
    protected void interrupted() {
    }
    
    private void initTrajectory() {
		trajectoryLength = 0;
    	
    	try {
			counter = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		counter.nextLine();
    	while (counter.hasNextLine()){
    		trajectoryLength++;
    		counter.nextLine();
    	}
    	counter.close();
    	trajectory = new double[trajectoryLength][2];
    }
    
    private void fillTrajectory() {
    	
    	try {
			in = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	for (int y = 0; y < trajectoryLength ; y++){
    		in.nextLine();
    		trajectory[y][0] = in.nextInt();
    		trajectory[y][1] = in.nextInt();
    	}
    	in.close();
    }
    
    private void setTrajectory(){
    	MotionProfile.kNumPoints = trajectoryLength;
    	MotionProfile.Points = new double[MotionProfile.kNumPoints][3];
    	for (int n = 0; n < trajectoryLength; n++){
    		// MotionProfile.Points[n][0] = ((double)trajectory[n][0] / ((double)Robot.drive.encCPR * 4.0));
    		// MotionProfile.Points[n][1] = ((double)(trajectory[n][1] / ((double)Robot.drive.encCPR * 4.0)) * 600.0);
    		MotionProfile.Points[n][0] = trajectory[n][0];
    		MotionProfile.Points[n][1] = trajectory[n][1];
    		MotionProfile.Points[n][2] = 20.0;
    	}
    }
}