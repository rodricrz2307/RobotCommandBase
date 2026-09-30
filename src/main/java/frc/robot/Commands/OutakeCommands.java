package frc.robot.Commands;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.NeutralOut;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Mechanisms.IntakeSub;
import frc.robot.Mechanisms.OutakeSub;
    
public class OutakeCommands extends Command {

    private final OutakeSub outakeSub;
    private final double speed;

   public OutakeCommands (OutakeSub outakeSub, double speed) {
    this.outakeSub = outakeSub;
    this.speed = speed;
    addRequirements(outakeSub);
}
    

@Override
public void execute() {
    outakeSub.setOutakeSpeed(speed);
}

@Override
public void end(boolean interrupted) {
    outakeSub.stopOutake();
}
}

   }
