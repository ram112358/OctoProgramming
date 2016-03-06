package org.usfirst.frc.team3502.robot.commands.Duck;

import org.usfirst.frc.team3502.robot.Constants;
import org.usfirst.frc.team3502.robot.Robot;
import org.usfirst.frc.team3502.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Squeeze extends Command {

    public Squeeze() {
    	requires(Robot.topDuck);
    	requires(Robot.bottomDuck);
    }

    protected void initialize() {
    	Robot.topDuck.setPositionMode();
    	Robot.bottomDuck.setPositionMode();
    }

    protected void execute() {
    	Robot.topDuck.set((double)Robot.bottomDuck.getEncPosition() / 4096 - Constants.kSqueezeFactor);
    	Robot.bottomDuck.set((double)Robot.bottomDuck.getEncPosition() / 4096);
    }

    protected boolean isFinished() {
    	return Robot.topDuck.getClosedLoopError() <= Constants.kTopIzone ||
    			Robot.bottomDuck.getClosedLoopError() <= Constants.kTopIzone;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}