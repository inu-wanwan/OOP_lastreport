package oop.ex5.field;

import oop.ex5.Runner.*;

public class field {
    private int status;
    final private int stamina;
    final private int goal;
    final private int laneNum;
    final private Runner[] runners;
    final private int[] commands;
    final private int[] nextDist;


    public field(int[] runnerTypes, int stamina, int goal, int laneNum) {
        this.status = 0;
        this.stamina = stamina;
        this.goal = goal;
        this.laneNum = laneNum;
        this.runners = new Runner[laneNum];
        this.commands = new int[laneNum];
        this.nextDist = new int[laneNum];

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

    public void step() {
        int command;
        for (int i = 0; i < laneNum; i++) {
            if (runners[i] != null) {
                command = runners[i].getCommand(runners);
                commands[i] = command;
                if(command < 3){
                    nextDist[i] = 1;
                } else if (3 <= command && command < 6) {
                    nextDist[i] = 2;
                } else if (6 <= command && command < 9) {
                    nextDist[i] = 3;
                }
            }
        }
        for (int i = 0; i < laneNum; i++) {
            if(runners[i] != null){
                if (commands[i] == 0 || commands[i] == 1 || commands[i] == 2) {
                    runners[i].moveUp(1);
                }
                // No same lane runner
                if (runners[i].sameLane(runners, i) == 0) {
                    if (commands[i] == 3 || commands[i] == 4 || commands[i] == 5) {
                        runners[i].moveUp(2);
                    } else if (commands[i] == 6 || commands[i] == 7 || commands[i] == 8) {
                        runners[i].moveUp(3);
                    }
                } else {
                    //exists same lane runner
                    int moveUpNum = nextDist[i];
                    for(int j = 0; j < runners[i].sameLane(runners, i); j++) {
                        int sameLaneRunner = runners[i].sameLaneRunners(runners, i)[j];
                        // condition: back runner overtakes former runner
                        if(runners[i].getDist() < runners[sameLaneRunner].getDist() &&
                                runners[i].getDist() + moveUpNum
                                >= runners[sameLaneRunner].getDist() + nextDist[sameLaneRunner]){
                            moveUpNum = runners[sameLaneRunner].getDist() + nextDist[sameLaneRunner] - runners[i].getDist() - 1;
                        }
                    }
                    runners[i].moveUp(moveUpNum);
                }

                if (commands[i] == 3 || commands[i] == 4 || commands[i] == 5) {
                    runners[i].decreaseStamina(1 + runners[i].getTiredness() / 100);
                } else if (commands[i] == 6 || commands[i] == 7 || commands[i] == 8) {
                    runners[i].decreaseStamina(4 + runners[i].getTiredness() / 20);
                }

                if (commands[i] == 1 || commands[i] == 4 || commands[i] == 7) {
                    if(runners[i].getLane() > 0) {
                        runners[i].moveLeft();
                    }
                    runners[i].decreaseStamina(1 + runners[i].getTiredness() / 50);
                }
                if (commands[i] == 2 || commands[i] == 5 || commands[i] == 8) {
                    if(runners[i].getLane() < laneNum-1) {
                        runners[i].moveRight();
                    }
                    runners[i].decreaseStamina(1 + runners[i].getTiredness() / 50);
                }
            }
        }
        status++;
    }

    public int getStatus() { return status; }

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
