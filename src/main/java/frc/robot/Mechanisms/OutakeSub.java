package frc.robot.Mechanisms;
 
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
 
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Mechanisms.MechanismsConstants.OutakeConstants;
import frc.robot.Mechanisms.MechanismsConstants.ShootContants;
 
public class OutakeSub extends SubsystemBase {
 
    private final TalonFX shooterLeft = new TalonFX(OutakeConstants.shooterLeftId);
    private final TalonFX shooterRight = new TalonFX(OutakeConstants.shooterRightId);
    private final SparkMax hood = new SparkMax(OutakeConstants.hoodId, MotorType.kBrushless);

    private final VelocityVoltage velocityRequest = new VelocityVoltage(0);
 
    private double targetRPS = 0;
    private double targetHood = 0;
 
    public OutakeSub() {
        TalonFXConfiguration shooterConfig = new TalonFXConfiguration();
        shooterConfig.Slot0.kP = OutakeConstants.shooterkP;
        shooterConfig.Slot0.kI = OutakeConstants.shooterkI;
        shooterConfig.Slot0.kD = OutakeConstants.shooterkD;
        shooterConfig.Slot0.kS = OutakeConstants.shooterkS;
        shooterConfig.Slot0.kG = OutakeConstants.shooterkG;
        shooterConfig.Slot0.kV = OutakeConstants.shooterkV;
        shooterConfig.Slot0.kA = OutakeConstants.shooterkA;
 
        shooterConfig.MotorOutput.Inverted = OutakeConstants.shooterLeftInversion;
        shooterLeft.getConfigurator().apply(shooterConfig);
 
        shooterConfig.MotorOutput.Inverted = OutakeConstants.shooterRightInversion;
        shooterRight.getConfigurator().apply(shooterConfig);
 
        SparkMaxConfig hoodConfig = new SparkMaxConfig();
        hoodConfig.closedLoop.pid(OutakeConstants.hoodkP, OutakeConstants.hoodkI, OutakeConstants.hoodkD);
        hoodConfig.inverted(OutakeConstants.hoodInverted);
        hoodConfig.idleMode(IdleMode.kBrake);

        hood.configure(hoodConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        hood.getEncoder().setPosition(0);
    }
 
    public void setSpeed(double rps) {
        targetRPS = rps;
        shooterLeft.setControl(velocityRequest.withVelocity(rps));
        shooterRight.setControl(velocityRequest.withVelocity(rps));
    }
 
    public void setHood(double position) {
        targetHood = MathUtil.clamp(position, OutakeConstants.hoodMinPosition, OutakeConstants.hoodMaxPosition);
        hood.getClosedLoopController().setSetpoint(targetHood, ControlType.kPosition);
    }
 
    public boolean isReady() {
        double leftError = Math.abs(shooterLeft.getVelocity().getValueAsDouble() - targetRPS);
        double rightError = Math.abs(shooterRight.getVelocity().getValueAsDouble() - targetRPS);
        double hoodError = Math.abs(hood.getEncoder().getPosition() - targetHood);
        return targetRPS > 0
                && leftError < ShootContants.shooterTolerance
                && rightError < ShootContants.shooterTolerance
                && hoodError < ShootContants.hoodTolerance;
    }
 
    public void stop() {
        targetRPS = 0;
        shooterLeft.stopMotor();
        shooterRight.stopMotor();
        setHood(ShootContants.hoodHome);
    }
}
 
