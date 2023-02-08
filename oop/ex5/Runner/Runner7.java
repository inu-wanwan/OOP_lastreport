package oop.ex5.Runner;

public class Runner7 extends Runner{

    public Runner7(int goal, int laneNum, int lane, int stamina) {
        super(goal, laneNum, lane, stamina);
    }

    @Override
    protected int selectCommand(Runner[] runners) {
        int lane = getLane();
        int dist = getDist();
        int stamina = getStamina();
        int tiredness = getTiredness();
        int lr    = tiredness / 50;
        int jump2 = tiredness / 100;
        int jump3 = tiredness / 20;
        boolean leftLane[] = {false, false, false};
        boolean rightLane[] = {false, false, false};
        int searchLane;
        int searchDist;


        // search side lane
        for(int i = 0; i < laneNum; i++) {
            if(runners[i] != null) {
                searchLane = runners[i].getLane();
                searchDist = runners[i].getDist();

                if(searchLane == lane - 1){
                    if(searchDist == dist - 1){
                        leftLane[0] = true;
                    } else if (searchDist == dist) {
                        leftLane[1] = true;
                    } else if (searchDist == dist + 1) {
                        leftLane[2] = true;
                    }
                } else if (searchLane == lane + 1) {
                    if(searchDist == dist - 1){
                        rightLane[0] = true;
                    } else if (searchDist == dist) {
                        rightLane[1] = true;
                    } else if (searchDist == dist + 1) {
                        rightLane[2] = true;
                    }
                }
            }
        }
        // choose return command
        if(stamina >= 5 + lr + jump3) {
            for(int i = 0; i < 3; i++) {
                if(leftLane[i]) {
                    return 7;
                } else if (rightLane[i]) {
                    return 8;
                }
            }
        }

        if (stamina >= 1 + jump2) {
            return 3;
        } else {
            return 0;
        }

    }
}
