package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Mechanisms.IntakeSub;


public class IntakeCommands extends Command {

    private final IntakeSub intakeSub;
    private final double speed;

    public IntakeCommands(IntakeSub intakeSub, double speed) {
        this.intakeSub = intakeSub;
        this.speed = speed;
        addRequirements(intakeSub);
    }

    @Override
    public void execute() {
        intakeSub.setIntakeSpeed(speed);
    }

    @Override
    public void end(boolean interrupted) {
        intakeSub.stopIntake();
    }
    
}
