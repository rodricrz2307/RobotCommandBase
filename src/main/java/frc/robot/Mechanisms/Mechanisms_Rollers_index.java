package frc.robot.Mechanisms;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.NeutralOut;
import com.ctre.phoenix6.controls.StaticBrake;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
//krakenx60
public class Rollers extends SubsystemBase {
    public final TalonFX rollers = new TalonFX(MechanismsConstants.IndexConstants.roller1Id);
    public final TalonFX rollers2 = new TalonFX(MechanismsConstants.IndexConstants.roller2Id);
    public Rollers(){
        TalonFXConfiguration roller1Configuration = new TalonFXConfiguration();
        roller1Configuration.MotorOutput.Inverted = MechanismsConstants.IndexConstants.rollersInversion;
        roller1Configuration.Slot0.kP = MechanismsConstants.IndexConstants.kP;
        roller1Configuration.Slot0.kI = MechanismsConstants.IndexConstants.kI;
        roller1Configuration.Slot0.kD = MechanismsConstants.IndexConstants.kD;
        rollers.getConfigurator().apply(roller1Configuration);

        TalonFXConfiguration roller2Configuration = new TalonFXConfiguration();
        roller2Configuration.MotorOutput.Inverted = MechanismsConstants.IndexConstants.rollersInversion;
        roller2Configuration.Slot0.kP = MechanismsConstants.IndexConstants.kP;
        roller2Configuration.Slot0.kI = MechanismsConstants.IndexConstants.kI;
        roller2Configuration.Slot0.kD = MechanismsConstants.IndexConstants.kD;
        rollers2.getConfigurator().apply(roller2Configuration);
    }
        public void setSpeed(double speed) {
        rollers.setControl(new DutyCycleOut(speed));
        rollers2.setControl(new DutyCycleOut(speed));   
    }

    public void applyVoltage(double voltage) {
        rollers.setControl(new DutyCycleOut(voltage));
        rollers2.setControl(new DutyCycleOut(voltage));
    }
    public void stopRollers() {
        rollers.setControl(new NeutralOut());
        rollers2.setControl(new NeutralOut());
    }
    public void breakRollers() {
        rollers.setControl(new StaticBrake());
        rollers2.setControl(new StaticBrake());
    }
    public Object setrollersPositionVoltage(double d) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setrollersPositionVoltage'");
    }
}
