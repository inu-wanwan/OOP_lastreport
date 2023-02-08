package oop.ex5.Runner;

public class Runner8 extends Runner{
    private int moved;

    public Runner8(int goal, int laneNum, int lane, int stamina) {
        super(goal, laneNum, lane, stamina);
        this.moved = 0;
    }

    @Override
    protected int selectCommand(Runner[] runners){
        int lane = getLane();
        int dist = getDist();
        int stamina = getStamina();
        int tiredness = getTiredness();
        int lr    = tiredness / 50;
        int jump2 = tiredness / 100;
        int jump3 = tiredness / 20;
        int sameLaneDist = 1000;
        boolean sameLane = false;
        boolean leftLane = false;
        boolean enoughStamina45 = (stamina >= 1 + jump2 + lr);
        boolean enoughStamina12 = (stamina >= 1 + lr);

        moved += 1;
        // search same lane runners & next lane runners
        for(int i = 0; i < laneNum; i++) {
            if(runners[i] != null) {
                if(runners[i].getLane() == lane && runners[i].getDist() > dist) {
                    sameLane = true;
                    if(runners[i].getDist() - dist < sameLaneDist){
                        sameLaneDist = runners[i].getDist() - dist;
                    }
                }
                if(runners[i].getLane() == lane - 1 && runners[i].getDist() == dist) {
                    leftLane = true;
                }
            }
        }
        if(moved < 3) {
            if(stamina >= 4 + jump3) {
                return 6;
            } else if (stamina >= 1 + jump2) {
                return 3;
            } else {
                return 0;
            }
        } else {
            if(sameLane) {
                if(leftLane) {
                    // move right
                    // left || (left && right) = left
                    if(sameLaneDist >= 2 && enoughStamina45) {
                        return 5;
                    } else if (enoughStamina12) {
                        return 2;
                    }

                } else {
                    //move left
                    if(sameLaneDist >= 2 && enoughStamina45) {
                        return 4;
                    } else if (enoughStamina12) {
                        return 1;
                    }
                }
            }
        }

        if(stamina >= 1 + jump2) {
            return 3;
        } else {
            return 0;
        }
    }
}
