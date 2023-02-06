package oop.ex5.Runner;

public class Runner4 extends Runner{
    public Runner4(int goal, int laneNum, int lane, int stamina) {
        super(goal, laneNum, lane, stamina);
    }

    @Override
    protected int selectCommand(Runner[] runners){
        int stamina = getStamina();
        int tiredness = getTiredness();
        int lr    = tiredness / 50;
        int jump2 = tiredness / 100;
        int jump3 = tiredness / 20;

        if(getLane() != 0){
            if (stamina >= 5 + lr + jump3) {
                return 7;
            } else if (stamina >= 2 + lr + jump2) {
                return 4;
            } else if (stamina >= 1 + lr) {
                return 1;
            } else {
                return 0;
            }
        } else {
            if (stamina >= 1 + jump2) {
                return 3;
            } else {
                return 0;
            }
        }
    }
}
