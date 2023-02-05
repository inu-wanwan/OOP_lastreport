package oop.ex5.field;

import oop.ex5.Runner.*;

public class field {
    private int status;
    private int stamina;
    private int goal;
    private int laneNum;
    private Runner[] runners;
    private int[] commands;


    public field(int[] runnerTypes, int stamina, int goal, int laneNum) {
        this.status = 0;
        this.stamina = stamina;
        this.goal = goal;
        this.laneNum = laneNum;
        this.runners = new Runner[laneNum];
        this.commands = new int[laneNum];
        for(int i = 0; i < laneNum; i++){
            if (runnerTypes[i] == 0){
                this.runners[i] = new Runner0(goal, laneNum, i, stamina);
            } else if (runnerTypes[i] == 1){
                this.runners[i] = new Runner1(goal, laneNum, i, stamina);
            } else if (runnerTypes[i] == 2){
                this.runners[i] = new Runner2(goal, laneNum, i, stamina);
            } else if (runnerTypes[i] == 3){
                this.runners[i] = new Runner3(goal, laneNum, i, stamina);
            } else if (runnerTypes[i] == 4){
                this.runners[i] = new Runner4(goal, laneNum, i, stamina);
            } else if (runnerTypes[i] == 5){
                this.runners[i] = new Runner5(goal, laneNum, i, stamina);
            } else {
                this.runners[i] = null;
            }
        }
    }

    public void step(){
        int command = -1;
        for(int i = 0; i < laneNum; i++){
            if (runners[i] != null) {
                command = runners[i].getCommand(runners);
                if (command == 0) {
                    runners[i].moveUp(1);
                } else if (command == 3) {
                    runners[i].moveUp(2);
                    runners[i].decreaseStamina(1 + runners[i].getTiredness() / 100);
                } else if (command == 6) {
                    runners[i].moveUp(3);
                    runners[i].decreaseStamina(4 + runners[i].getTiredness() / 20);
                } else if (command == 1)
                commands[i] = command;
            }
        }
        status++;
    }

    public int getStatus() {return status;}

    public boolean reached() {
        boolean ans = false;
        for(int i = 0; i < laneNum; i++){
            if(runners[i] != null){
                if(runners[i].reachGoal()){
                    ans = true;
                }
            }
        }

        return ans;
    }

    public String getRunnerStatus() {
        String status = "";
        for(int i = 0; i < laneNum; i++) {
            if(runners[i] != null) {
                status += runners[i].getStatus() + "\n";
            }
        }

        return status;
    }

    public String getCommands() {
        String strCommands = "";
        for(int i = 0; i < laneNum; i++) {
            if(runners[i] != null) {
                strCommands += commands[i] + "\n";
            }
        }
        return strCommands;
    }


}
