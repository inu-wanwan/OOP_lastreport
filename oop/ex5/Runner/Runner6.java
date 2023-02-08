package oop.ex5.Runner;

public class Runner6 extends Runner{

    public Runner6(int goal, int laneNum, int lane, int stamina) {
        super(goal, laneNum, lane, stamina);
    }

    @Override
    protected int selectCommand(Runner[] runners){
        int lane = getLane();
        int stamina = getStamina();
        int tiredness = getTiredness();
        int lr    = tiredness / 50;
        int jump2 = tiredness / 100;
        int jump3 = tiredness / 20;

        if (leftNextLane(runners) >= 0) {
            if (stamina >= 2 + lr + jump2) {
                return 4;
            } else if (stamina >= 1 + lr) {
                return 1;
            }
        } else if (rightNextLane(runners) >= 0) {
            if (stamina >= 2 + lr + jump2) {
                return 5;
            } else if (stamina >= 1 + lr) {
                return 2;
            }
        }
        // no side lane runner
         if (stamina >= 1 + jump2) {
            return 3;
        } else {
            return 0;
        }
    }
}
