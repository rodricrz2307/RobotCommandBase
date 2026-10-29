package frc.robot.Mechanisms;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Rollers extends SubsystemBase {
    public final SparkMax rollers = new SparkMax(MechanismsConstants.IndexConstants.roller1Id, MotorType.kBrushless);
    public final SparkMax rollers2 = new SparkMax(MechanismsConstants.IndexConstants.roller2Id, MotorType.kBrushless);
    public Rollers(){
        SparkMaxConfig roller1Configuration = new SparkMaxConfig();
        roller1Configuration.inverted(MechanismsConstants.IndexConstants.rollersInverted);
        roller1Configuration.idleMode(IdleMode.kCoast);
        roller1Configuration.closedLoop.pid(
                MechanismsConstants.IndexConstants.kP,
                MechanismsConstants.IndexConstants.kI,
                MechanismsConstants.IndexConstants.kD);
        rollers.configure(roller1Configuration, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        SparkMaxConfig roller2Configuration = new SparkMaxConfig();
        roller2Configuration.inverted(MechanismsConstants.IndexConstants.rollersInverted);
        roller2Configuration.idleMode(IdleMode.kCoast);
        roller2Configuration.closedLoop.pid(
                MechanismsConstants.IndexConstants.kP,
                MechanismsConstants.IndexConstants.kI,
                MechanismsConstants.IndexConstants.kD);
        rollers2.configure(roller2Configuration, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }
        public void setSpeed(double speed) {
        rollers.set(speed);
        rollers2.set(speed);
    }

    public void applyVoltage(double voltage) {
        rollers.setVoltage(voltage);
        rollers2.setVoltage(voltage);
    }
    public void stopRollers() {
        rollers.stopMotor();
        rollers2.stopMotor();
    }
    public void breakRollers() {
        rollers.stopMotor();
        rollers2.stopMotor();
    }
    public Object setrollersPositionVoltage(double d) {

        throw new UnsupportedOperationException("Unimplemented method 'setrollersPositionVoltage'");
    }
}
