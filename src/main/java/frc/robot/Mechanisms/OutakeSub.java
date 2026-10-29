package frc.robot.Mechanisms;
 
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
 
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Mechanisms.MechanismsConstants.OutakeConstants;
import frc.robot.Mechanisms.MechanismsConstants.ShootContants;
 
public class OutakeSub extends SubsystemBase {
 
    private final TalonFX shooterLeft = new TalonFX(OutakeConstants.shooterLeftId);
    private final TalonFX shooterRight = new TalonFX(OutakeConstants.shooterRightId);
    private final TalonFX hood = new TalonFX(OutakeConstants.hoodId);
 
    private final VelocityVoltage velocityRequest = new VelocityVoltage(0);
    private final PositionVoltage hoodRequest = new PositionVoltage(0);
 
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
 
        TalonFXConfiguration hoodConfig = new TalonFXConfiguration();
        hoodConfig.Slot0.kP = OutakeConstants.hoodkP;
        hoodConfig.Slot0.kI = OutakeConstants.hoodkI;
        hoodConfig.Slot0.kD = OutakeConstants.hoodkD;
        hoodConfig.Slot0.kS = OutakeConstants.hoodkS;
        hoodConfig.Slot0.kG = OutakeConstants.hoodkG;
        hoodConfig.Slot0.kV = OutakeConstants.hoodkV;
        hoodConfig.Slot0.kA = OutakeConstants.hoodkA;
        hoodConfig.MotorOutput.Inverted = OutakeConstants.hoodInversion;
        hoodConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
 
        hood.getConfigurator().apply(hoodConfig);
        hood.setPosition(0);
    }
 
    public void setSpeed(double rps) {
        targetRPS = rps;
        shooterLeft.setControl(velocityRequest.withVelocity(rps));
        shooterRight.setControl(velocityRequest.withVelocity(rps));
    }
 
    public void setHood(double position) {
        targetHood = MathUtil.clamp(position, OutakeConstants.hoodMinPosition, OutakeConstants.hoodMaxPosition);
        hood.setControl(hoodRequest.withPosition(targetHood));
    }
 
    public boolean isReady() {
        double leftError = Math.abs(shooterLeft.getVelocity().getValueAsDouble() - targetRPS);
        double rightError = Math.abs(shooterRight.getVelocity().getValueAsDouble() - targetRPS);
        double hoodError = Math.abs(hood.getPosition().getValueAsDouble() - targetHood);
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
 
