package oop.ex5.Runner;

public class Runner1 extends Runner{
    public Runner1(int goal, int laneNum, int lane, int stamina) {
        super(goal, laneNum, lane, stamina);
    }

    @Override
    protected int selectCommand(Runner[] runners){
        if(getStamina() >= 1 + getTiredness() / 100){
            return 3;
        }else{
            return 0;
        }
    }
}
