package frc.robot.Mechanisms;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Mechanisms.MechanismsConstants.IntakeConstants;
import frc.robot.Mechanisms.MechanismsConstants.IntakeboxConstants;

public class IntakeSub extends SubsystemBase {

    private final TalonFX intakeMotor = new TalonFX(IntakeConstants.intakeId);
    private final TalonFX intakeboxMotor = new TalonFX(IntakeboxConstants.intakeboxId);

    public IntakeSub() {
        TalonFXConfiguration intakeMotorConfig = new TalonFXConfiguration();
        intakeMotorConfig.MotorOutput.Inverted = IntakeConstants.intakeInversion;
        intakeMotorConfig.Slot0.kP = IntakeConstants.kP;
        intakeMotorConfig.Slot0.kI = IntakeConstants.kI;
        intakeMotorConfig.Slot0.kD = IntakeConstants.kD;   
        intakeMotorConfig.Slot0.kS = IntakeConstants.kS;
        intakeMotorConfig.Slot0.kG = IntakeConstants.kG;
        intakeMotorConfig.Slot0.kV = IntakeConstants.kV;
        intakeMotorConfig.Slot0.kA = IntakeConstants.kA;
        intakeMotor.getConfigurator().apply(intakeMotorConfig);
     

        TalonFXConfiguration intakeboxMotorConfig = new TalonFXConfiguration();
        intakeboxMotorConfig.MotorOutput.Inverted = IntakeboxConstants.intakeboxInversion;
        intakeboxMotorConfig.Slot0.kP = IntakeboxConstants.kP;
        intakeboxMotorConfig.Slot0.kI = IntakeboxConstants.kI;
        intakeboxMotorConfig.Slot0.kD = IntakeboxConstants.kD;   
        intakeboxMotorConfig.Slot0.kS = IntakeboxConstants.kS;
        intakeboxMotorConfig.Slot0.kG = IntakeboxConstants.kG;
        intakeboxMotorConfig.Slot0.kV = IntakeboxConstants.kV;
        intakeboxMotorConfig.Slot0.kA = IntakeboxConstants.kA;
        intakeboxMotor.getConfigurator().apply(intakeboxMotorConfig);
    }

    public void setIntakeSpeed(double speed) {
        intakeMotor.set(speed);
        intakeboxMotor.set(speed);
    }
    public void applyIntakeVoltage(double voltage) {
        intakeMotor.setVoltage(voltage);
        intakeboxMotor.setVoltage(voltage);
    }
    public void stopIntake() {
        intakeMotor.set(0);
        intakeboxMotor.set(0);
    }

    public Command runIntakeCommand(double speed) {
        return this.run(() -> setIntakeSpeed(speed))
                   .finallyDo(() -> stopIntake());
    }

    public Command stopIntakeCommand() {
        return this.runOnce(this::stopIntake);
    }

    @Override
    public void periodic() {
    }

}
