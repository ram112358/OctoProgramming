 package org.usfirst.frc.team3502.robot.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.usfirst.frc.team3502.robot.MotionProfile;
import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ReadFile extends Command {
	
	private static final String path = "/home/lvuser/ProfileTest.txt";
	
	private Scanner in;
	
	private ArrayList<Integer>
		Pos,
		Vel;
	
	private boolean done = false;
	
	private int n,x;
	
    public ReadFile() {
    	requires(Robot.drive);
    }
    
    protected void initialize() {
    	SmartDashboard.putString("setedup", "yo");
    	MotionProfile.readingFile = true;
        SmartDashboard.putBoolean("ReadingFile", MotionProfile.readingFile);
        x = 0;
        SmartDashboard.putNumber("x", x);
    	try {
			setupFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Pos = new ArrayList<Integer>();
        Vel = new ArrayList<Integer>();
    }
    
    protected void execute() {
    	if (in.hasNextLine()) {
    		Vel.add(in.nextInt());
    		Pos.add(in.nextInt());
    		in.nextLine();
    	}
    	else {
    		done = true;
        	MotionProfile.readingFile = false;
    	}
    	x++;
        SmartDashboard.putNumber("x", x);
        SmartDashboard.putBoolean("ReadingFile", MotionProfile.readingFile);
    }
    
    protected boolean isFinished() {
        return done;
    }
    
    protected void end() {
		MotionProfile.kNumPoints = Pos.size();
		setProfile();
    }
    
    protected void interrupted() {
		MotionProfile.kNumPoints = Pos.size();
		setProfile();
    }
    
    private void setupFile() throws IOException{
    	in = new Scanner(path);
    	in.nextLine();
    	x++;
        SmartDashboard.putNumber("x", x);
    }
    
    private void setProfile(){
    	MotionProfile.Points = new double[MotionProfile.kNumPoints][3];
    	for (n = 0; n < MotionProfile.kNumPoints; n++) {
    		MotionProfile.Points[n][0] = Pos.get(n);
    		MotionProfile.Points[n][1] = Vel.get(n);
    		MotionProfile.Points[n][2] = 20;
    	}
    	x++;
        SmartDashboard.putNumber("x", x);
    }
}