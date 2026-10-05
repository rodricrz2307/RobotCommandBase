package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Mechanisms.Mechanisms_Rollers_Index;

public class IndexCommands {

    public static Command startRollers(Mechanisms_Rollers_Index rollers){
        return Commands.runOnce(() -> rollers.setrollersPositionVoltage(0) ,rollers);
    }

    public static Command stopRollers(Mechanisms_Rollers_Index rollers){
        return Commands.runOnce(() -> rollers.setrollersPositionVoltage(-0.25),rollers);
    }
    
public static Command feed(Rollers rollers) {
        return Commands.startEnd(
                () -> rollers.setSpeed(IndexConstants.feedSpeed),
                rollers::stopRollers,
                rollers);
    }
}
