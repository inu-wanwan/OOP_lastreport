package oop.ex5.Runner;

public class Runner6 extends Runner{
    public Runner6(int goal, int laneNum, int lane, int stamina) {
        super(goal, laneNum, lane, stamina);
    }

    @Override
    protected int selectCommand(Runner[] runners){
        return 0;
    }
}
