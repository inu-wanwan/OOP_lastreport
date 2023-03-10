package oop.ex5.Runner;

public class Runner5 extends Runner{
    private  int moved = 0;
    private boolean moveRight;
    private boolean moveLeft;
    public Runner5(int goal, int laneNum, int lane, int stamina) {
        super(goal, laneNum, lane, stamina);
        this.moveRight = false;
        this.moveLeft = false;
    }


    @Override
    protected int selectCommand(Runner[] runners){
        int lane = getLane();
        int stamina = getStamina();
        int tiredness = getTiredness();
        int lr    = tiredness / 50;
        int jump2 = tiredness / 100;
        int jump3 = tiredness / 20;

        if ((lane >= laneNum / 2 && lane < laneNum  && moved == 0) || (moved == 1 && moveRight && lane > 0)) {
            moved += 1;
            moveRight = true;

            if(lane > 0) {
                if (stamina >= 5 + lr + jump3) {
                    return 7;
                } else if (stamina >= 2 + lr + jump2) {
                    return 4;
                } else if (stamina >= 1 + lr) {
                    return 1;
                }
            } else {
                return 0;
            }
        } else if ((lane < laneNum / 2 && lane >= 0 && moved == 0) || (moved == 1 && moveLeft && lane < laneNum - 1)) {
            moved += 1;
            moveLeft = true;

            if(lane < laneNum - 1) {
                if (stamina >= 5 + lr + jump3) {
                    return 8;
                } else if (stamina >= 2 + lr + jump2) {
                    return 5;
                } else if (stamina >= 1 + lr) {
                    return 2;
                }
            } else {
                return 0;
            }

        } else {
            if (goal - getDist() <= stamina) {
                return 3;
            } else {
                return 0;
            }
        }
        return 0;
    }

}
