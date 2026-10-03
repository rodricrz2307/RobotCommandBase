package frc.robot.Commands;
 
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Mechanisms.OutakeSub;
 
public class OutakeCommands {
 
    public static Command spinUp(OutakeSub outake, double rps, double hoodPosition) {
        return Commands.startEnd(
                () -> {
                    outake.setHood(hoodPosition);
                    outake.setSpeed(rps);
                },
                outake::stop,
                outake);
    }
 
    public static Command shoot(OutakeSub outake, Command feed, double rps, double hoodPosition) {
        return spinUp(outake, rps, hoodPosition)
                .alongWith(Commands.waitUntil(outake::isReady).andThen(feed));
    }
}
 
