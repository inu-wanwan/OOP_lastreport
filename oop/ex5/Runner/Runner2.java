package oop.ex5.Runner;

public class Runner2 extends Runner{
    public Runner2(int goal, int laneNum, int lane, int stamina) {
        super(goal, laneNum, lane, stamina);
    }

    @Override
    protected int selectCommand(Runner[] runners){
        if(getStamina() >= 4 + getTiredness() / 20){
            return 6;
        }else if(getStamina() >= 1 + getTiredness() / 100) {
            return 3;
        }else{
            return 0;
        }
    }
}
