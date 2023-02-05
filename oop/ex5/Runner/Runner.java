package oop.ex5.Runner;

public abstract class Runner {
  private int lane;
  private int dist;
  private int stamina;
  private int tiredness;
  private boolean collision;
  protected final int goal;
  protected final int laneNum;

  public Runner(int goal, int laneNum, int lane, int stamina) {
    this.goal = goal;
    this.laneNum = laneNum;
    this.lane = lane;
    this.stamina = stamina;
    this.dist = 0;
    this.tiredness = 0;
    this.collision = false;
  }

  final public int getLane() {
    return lane;
  }

  final public int getDist() {
    return dist;
  }

  final public int getStamina() {
    return stamina;
  }

  final public int getTiredness() {
    return tiredness;
  }

  final public String getStatus() {
    return lane + " " + dist + " " + stamina + " " + tiredness + " " + (collision ? 1 : 0);
  }

  final public void moveUp(int num) {
    dist += num;
  }

  final public void moveLeft() {
    lane--;
  }

  final public void moveRight() {
    lane++;
  }

  final public void decreaseStamina(int consumption) {
    stamina -= consumption;
    tiredness += consumption;
    if(stamina < 0) throw new RuntimeException("Overuse Stamina");
  }

  final public void setCollision() {
    collision = true;
  }

  final public boolean reachGoal() {
    if (dist >= goal) {
      return true;
    } else {
      return false;
    }
  }

  final public int sameLane(Runner[] runners, int runnerNum) {
    int sameLaneRunner = -1;

    for(int i = 0; i < laneNum; i++){
      if(i != runnerNum && runners[i] != null) {
        if(runners[runnerNum].getLane() == runners[i].getLane()) {
          sameLaneRunner = i;
        }
      }
    }
    return sameLaneRunner;
  }

  final public int getCommand(Runner[] runners) {
    if(collision) {
      collision = false;
      return 0;
    }
    return selectCommand(runners);
  }

  abstract protected int selectCommand(Runner[] runners);


}