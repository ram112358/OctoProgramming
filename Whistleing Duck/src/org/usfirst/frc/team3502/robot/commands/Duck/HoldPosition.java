package org.usfirst.frc.team3502.robot.commands.Duck;

import org.usfirst.frc.team3502.robot.Constants;
import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class HoldPosition extends Command {

    public HoldPosition() {
    	requires(Robot.topDuck);
    	requires(Robot.bottomDuck);
    }

    protected void initialize() {
    	Robot.bottomDuck.setPositionPIDValues();
    	
    	Robot.topDuck.setPositionMode();
    	Robot.bottomDuck.setPositionMode();
    }

    protected void execute() {
    	// Robot.topDuck.set(Constants.kTopUpSoftLimit);
    	// Robot.bottomDuck.set(Constants.kBottomDownSoftLimit);
    	
    	Robot.topDuck.set(NetworkTable.getTable("Preferences").getNumber("topHoldPos", 0));
    	Robot.bottomDuck.set(NetworkTable.getTable("Preferences").getNumber("bottomHoldPos", 0));
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.bottomDuck.setJoystickPIDValues();
    }

    protected void interrupted() {
    	Robot.bottomDuck.setJoystickPIDValues();
    }
}