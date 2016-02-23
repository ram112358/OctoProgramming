package org.usfirst.frc.team3502.robot.commands.Duck;

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
    	Robot.topDuck.set((double)Robot.bottomDuck.getEncPosition() / 4096 - RobotMap.squeezeFactor);
    	Robot.bottomDuck.set((double)Robot.bottomDuck.getEncPosition() / 4096);
    }

    protected boolean isFinished() {
    	return Robot.topDuck.getClosedLoopError() <= Robot.topDuck.izone ||
    			Robot.bottomDuck.getClosedLoopError() <= Robot.bottomDuck.izone;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}