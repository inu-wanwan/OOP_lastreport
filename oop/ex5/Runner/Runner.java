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
    int sameLaneRunner = 0;

    for(int i = 0; i < laneNum; i++){
      if(i != runnerNum && runners[i] != null) {
        if(runners[runnerNum].getLane() == runners[i].getLane()) {
          sameLaneRunner += 1;
        }
      }
    }
    return sameLaneRunner;
  }

  final public int[] sameLaneRunners(Runner[] runners, int runnerNum) {
    int[] sameLaneRunners = null;
    int sameLaneRunnersNum = runners[runnerNum].sameLane(runners, runnerNum);

    if(sameLaneRunnersNum != 0) {
      sameLaneRunners = new int[sameLaneRunnersNum];
      int j = 0;
      for(int i = 0; i < laneNum; i++) {
        if(i != runnerNum && runners[i] != null) {
          if(runners[runnerNum].getLane() == runners[i].getLane()) {
            sameLaneRunners[j] = i;
            j++;
          }
        }
      }
    }

    return sameLaneRunners;
   }


  final public int leftNextLane(Runner[] runners, int runnerNum) {

    for(int i = 0; i < laneNum; i++) {
      if(runners[i] != null) {
        if(runners[i].getDist() == runners[runnerNum].getDist() &&
                runners[i].getLane() == runners[runnerNum].getLane() - 1) {
          return i;
        }
      }
    }
    return -1;
  }

  final public int rightNextLane(Runner[] runners, int runnerNum) {

    for(int i = 0; i < laneNum; i++) {
      if(runners[i] != null) {
        if(runners[i].getDist() == runners[runnerNum].getDist() &&
                runners[i].getLane() == runners[runnerNum].getLane() + 1) {
          return i;
        }
      }
    }
    return -1;
  }

  final public int leftSecondLane(Runner[] runners, int runnerNum) {

    for(int i = 0; i < laneNum; i++) {
      if(runners[i] != null) {
        if(runners[i].getDist() == runners[runnerNum].getDist() &&
                runners[i].getLane() == runners[runnerNum].getLane() - 2) {
          return i;
        }
      }
    }
    return -1;
  }

  final public int rightSecondLane(Runner[] runners, int runnerNum) {

    for(int i = 0; i < laneNum; i++) {
      if(runners[i] != null) {
        if(runners[i].getDist() == runners[runnerNum].getDist() &&
                runners[i].getLane() == runners[runnerNum].getLane() + 2) {
          return i;
        }
      }
    }
    return -1;
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
