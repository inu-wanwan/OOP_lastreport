package oop.ex5.Runner;

public class Runner3 extends Runner{
    private int preCommand = 9;
    public Runner3(int goal, int laneNum, int lane, int stamina) {
        super(goal, laneNum, lane, stamina);
    }

    @Override
    protected int selectCommand(Runner[] runners){
        int stamina = getStamina();
        int tiredness = getTiredness();
        int lr    = tiredness / 50;
        int jump2 = tiredness / 100;
        int jump3 = tiredness / 20;

        while(true) {
            preCommand = (preCommand - 1) % 9;
            if(preCommand < 0){
                preCommand = 9 + preCommand;
            }
            if (preCommand == 0) {
                return 0;
            } else if (preCommand == 1 || preCommand == 2) {
                if(stamina >= 1 + lr){
                    return preCommand;
                }
            } else if (preCommand == 3) {
                if(stamina >= 1 + jump2){
                    return preCommand;
                }
            } else if (preCommand == 4 || preCommand == 5) {
                if(stamina >= 2 + lr + jump2){
                    return preCommand;
                }
            } else if (preCommand == 6) {
                if(stamina >= 4 + jump3){
                    return preCommand;
                }
            } else if (preCommand == 7 || preCommand == 8) {
                if(stamina >= 5 + lr + jump3){
                    return preCommand;
                }
            }
        }
    }

}
