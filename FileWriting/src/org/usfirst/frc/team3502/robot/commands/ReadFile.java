 package org.usfirst.frc.team3502.robot.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.usfirst.frc.team3502.robot.MotionProfile;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ReadFile extends Command {
	
	private static final String path = "/home/lvuser/ProfileTest.txt";
	
	private Scanner in;
	
	private ArrayList<Integer>
		Pos,
		Vel;
	
	private boolean done = false;
	
	private int n;
	
    public ReadFile() {
    }
    
    protected void initialize() {
    	try {
			setupFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	MotionProfile.notProcessing = false;
        SmartDashboard.putBoolean("Processing Trajectory", MotionProfile.notProcessing);
    }
    
    protected void execute() {
    	if (in.hasNextLine()) {
    		Vel.add(in.nextInt());
    		Pos.add(in.nextInt());
    		in.nextLine();
    	}
    	else {
    		MotionProfile.kNumPoints = Pos.size();
    		setProfile();
    		done = true;
        	MotionProfile.notProcessing = true;
    	}
        SmartDashboard.putBoolean("Processing Trajectory", MotionProfile.notProcessing);
    }
    
    protected boolean isFinished() {
        return done;
    }
    
    protected void end() {
    }
    
    protected void interrupted() {
    }
    
    private void setupFile() throws IOException{
    	in = new Scanner(path);
    	//in.nextLine();
    	SmartDashboard.putString("setedup", "setedup");
    }
    
    private void setProfile(){
    	MotionProfile.Points = new double[MotionProfile.kNumPoints][3];
    	for (n = 0; n < MotionProfile.kNumPoints; n++) {
    		MotionProfile.Points[n][0] = Pos.get(n);
    		MotionProfile.Points[n][1] = Vel.get(n);
    		MotionProfile.Points[n][2] = 20;
    	}
    }
}