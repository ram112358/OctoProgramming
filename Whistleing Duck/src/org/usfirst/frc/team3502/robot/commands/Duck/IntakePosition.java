package org.usfirst.frc.team3502.robot.commands.Duck;

import org.usfirst.frc.team3502.robot.Constants;
import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class IntakePosition extends Command {

    public IntakePosition() {
    	requires(Robot.topDuck);
    	requires(Robot.bottomDuck);
    }

    protected void initialize() {
    	Robot.bottomDuck.setPositionPIDValues();
    	
    	Robot.topDuck.setPositionMode();
    	Robot.bottomDuck.setPositionMode();
    	
    	
    }

    protected void execute() {
    	// Robot.topDuck.set(Constants.kTopIntakePos);
    	// Robot.bottomDuck.set(Constants.kBottomDownSoftLimit);
    	
    	Robot.topDuck.set(NetworkTable.getTable("Preferences").getNumber("topIntakePos", 0));
    	Robot.bottomDuck.set(NetworkTable.getTable("Preferences").getNumber("bottomDownSoftPos", 0));
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