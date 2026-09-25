package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

public class IndexCommands extends Command {

    private final IndexSub indexSub;
    private final double speed;

  public IndexCommands(IndexSub indexSub, double speed){
    this.indexSub = indexSub;
    this.speed = speed;
    addRequirements(indexSub);

  }

  @Override
  public void eat() {

    indexSub.setRollerSpeed(3);

  }

  @Override
  public void desatasco(){
    indexSub.setRollerSpeed(-3);
  }


}

