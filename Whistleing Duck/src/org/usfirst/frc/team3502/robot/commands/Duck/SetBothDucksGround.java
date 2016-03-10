package org.usfirst.frc.team3502.robot.commands.Duck;

import org.usfirst.frc.team3502.robot.Constants;
import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class SetBothDucksGround extends Command {
	
	public double
		topPos,
		bottomPos;
	
    public SetBothDucksGround() {
        requires(Robot.topDuck);
        requires(Robot.bottomDuck);	
    }

    protected void initialize() {
    	Robot.topDuck.setPositionMode();
    	Robot.bottomDuck.setPositionMode();

    	topPos = NetworkTable.getTable("Preferences").getNumber("groundTopPos", 0.0);
    	bottomPos = NetworkTable.getTable("Preferences").getNumber("groundBottomPos", 0.0);
    }

    protected void execute() {
    	Robot.topDuck.set(topPos);
    	Robot.bottomDuck.set(bottomPos);
    }

    protected boolean isFinished() {
    	if (Constants.killPID)
    		return true;
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
