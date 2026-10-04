package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Mechanisms.Rollers;

public class IndexCommands {

    public static Command startRollers(Rollers rollers){
        return Commands.runOnce(() -> rollers.setrollersPositionVoltage(0) ,rollers);
    }

    public static Command stopRollers(Rollers rollers){
        return Commands.runOnce(() -> rollers.setrollersPositionVoltage(-0.25),rollers);
    }

}
