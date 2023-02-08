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
    final private int[] nextLaneDiff;


    public field(int[] runnerTypes, int stamina, int goal, int laneNum) {
        this.status = 0;
        this.stamina = stamina;
        this.goal = goal;
        this.laneNum = laneNum;
        this.runners = new Runner[laneNum];
        this.commands = new int[laneNum];
        this.nextDist = new int[laneNum];
        this.nextLaneDiff = new int[laneNum];

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
            } else if (runnerTypes[i] == 6){
                this.runners[i] = new Runner6(goal, laneNum, i, stamina);
            } else if (runnerTypes[i] == 7){
                this.runners[i] = new Runner7(goal, laneNum, i, stamina);
            } else if (runnerTypes[i] == 8){
                this.runners[i] = new Runner8(goal, laneNum, i, stamina);
            } else {
                this.runners[i] = null;
            }
        }
    }

    public void step() {
        int command;
        int[] moveUpNums = new int[laneNum];
        int[] moveLRNums = new int[laneNum];

        status++;

        for (int i = 0; i < laneNum; i++) {
            if (runners[i] != null) {
                command = runners[i].getCommand(runners);
                commands[i] = command;
                if(command < 3){
                    nextDist[i] = 1;
                } else if (command < 6) {
                    nextDist[i] = 2;
                } else if (command < 9) {
                    nextDist[i] = 3;
                }
                if (command == 1 || command == 4 || command == 7){
                    nextLaneDiff[i] = -1;
                } else if (command == 2 || command == 5 || command == 8) {
                    nextLaneDiff[i] = 1;
                } else {
                    nextLaneDiff[i] = 0;
                }
            }
        }

        // vertical movement
        for (int i = 0; i < laneNum; i++) {
            if(runners[i] != null){
                if (commands[i] == 0 || commands[i] == 1 || commands[i] == 2) {
                    moveUpNums[i] = 1;
                } else {
                    // No same lane runner
                    if (runners[i].sameLane(runners, i) == 0) {
                        if (commands[i] == 3 || commands[i] == 4 || commands[i] == 5) {
                            moveUpNums[i] = 2;
                        } else if (commands[i] == 6 || commands[i] == 7 || commands[i] == 8) {
                            moveUpNums[i] = 3;
                        }
                    } else {
                        //exists same lane runner
                        int moveUpNum = nextDist[i];
                        for (int j = 0; j < runners[i].sameLane(runners, i); j++) {
                            int sameLaneRunner = runners[i].sameLaneRunners(runners, i)[j];
                            // condition: back runner overtakes former runner
                            if (runners[i].getDist() < runners[sameLaneRunner].getDist() &&
                                    runners[i].getDist() + moveUpNum
                                            >= runners[sameLaneRunner].getDist() + nextDist[sameLaneRunner]) {
                                moveUpNum = runners[sameLaneRunner].getDist() + nextDist[sameLaneRunner] - runners[i].getDist() - 1;
                            }
                        }
                        moveUpNums[i] = moveUpNum;
                    }
                    
                }

            }
        }
        for(int i = 0; i < laneNum; i++){
            if(runners[i] != null) {
                runners[i].moveUp(moveUpNums[i]);
            }
        }

        // horizontal movement
        for(int i = 0; i < laneNum; i++){
            if(runners[i] != null){
                if (commands[i] == 1 || commands[i] == 4 || commands[i] == 7) {
                    // condition: crossing　or collide
                    if(runners[i].leftNextLane(runners) >= 0 && nextLaneDiff[runners[i].leftNextLane(runners)] == 0){
                        runners[runners[i].leftNextLane(runners)].setCollision();
                    } else if (runners[i].leftNextLane(runners) >= 0 && nextLaneDiff[runners[i].leftNextLane(runners)] == 1){
                        runners[runners[i].leftNextLane(runners)].setCollision();
                        runners[i].setCollision();
                    } else if (runners[i].leftSecondLane(runners) >= 0 &&
                            nextLaneDiff[runners[i].leftSecondLane(runners)] == 1 &&
                            (runners[i].leftNextLane(runners) == -1) || (runners[i].leftNextLane(runners) >= 0 && nextLaneDiff[runners[i].leftNextLane(runners)] != -1)) {
                        runners[runners[i].leftSecondLane(runners)].setCollision();
                        runners[i].setCollision();
                    } else if (runners[i].leftSecondLane(runners) >= 0 &&
                            nextLaneDiff[runners[i].leftSecondLane(runners)] == 1 &&
                            (runners[i].leftNextLane(runners) >= 0 && nextLaneDiff[runners[i].leftNextLane(runners)] == -1)) {
                        runners[runners[i].leftSecondLane(runners)].setCollision();
                    } else if (runners[i].getLane() > 0) {
                        moveLRNums[i] = -1;
                    }
                    
                }
                if (commands[i] == 2 || commands[i] == 5 || commands[i] == 8) {
                    // condition: crossing　or collide
                    if(runners[i].rightNextLane(runners) >= 0 && nextLaneDiff[runners[i].rightNextLane(runners)] == 0){
                        runners[runners[i].rightNextLane(runners)].setCollision();
                    } else if (runners[i].rightNextLane(runners) >= 0 && nextLaneDiff[runners[i].rightNextLane(runners)] == -1){
                        runners[runners[i].rightNextLane(runners)].setCollision();
                        runners[i].setCollision();
                    } else if (runners[i].rightSecondLane(runners) >= 0 &&
                            nextLaneDiff[runners[i].rightSecondLane(runners)] == -1 &&
                            (runners[i].rightNextLane(runners) == -1) || (runners[i].rightNextLane(runners) >= 0 && nextLaneDiff[runners[i].rightNextLane(runners)] != 1)) {
                        runners[runners[i].rightSecondLane(runners)].setCollision();
                        runners[i].setCollision();
                    }else if (runners[i].rightSecondLane(runners) >= 0 &&
                            nextLaneDiff[runners[i].rightSecondLane(runners)] == -1 &&
                            (runners[i].rightNextLane(runners) >= 0 && nextLaneDiff[runners[i].rightNextLane(runners)] == 1)) {
                        runners[runners[i].rightSecondLane(runners)].setCollision();
                    } else if (runners[i].getLane() < laneNum -1) {
                        moveLRNums[i] = 1;
                    }
                }
            }
        }
        for(int i = 0; i < laneNum; i++){
            if(moveLRNums[i] == -1){
                runners[i].moveLeft();
            } else if (moveLRNums[i] == 1) {
                runners[i].moveRight();
            }
        }
        
        //decrease stamina
        for(int i = 0; i < laneNum; i++){
            if(runners[i] != null) {
                int lr = 1 + runners[i].getTiredness() / 50;
                int jump2 = 1 + runners[i].getTiredness() / 100;
                int jump3 = 4 + runners[i].getTiredness() / 20;
                if (commands[i] == 1 || commands[i] == 2) {
                    runners[i].decreaseStamina(lr);
                } else if (commands[i] == 3) {
                    runners[i].decreaseStamina(jump2);
                } else if (commands[i] == 4 || commands[i] == 5) {
                    runners[i].decreaseStamina(lr + jump2);
                } else if (commands[i] == 6) {
                    runners[i].decreaseStamina(jump3);
                } else if (commands[i] == 7 || commands[i] == 8) {
                    runners[i].decreaseStamina(lr + jump3);
                }
            }
        }

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

    final public boolean[] getWinners() {
        boolean[] winners = new boolean[laneNum];

        for(int i = 0; i < laneNum; i++) {
            if(runners[i] != null) {
                if(runners[i].reachGoal()){
                    winners[i] = true;
                }else{
                    winners[i] = false;
                }
            }else{
                winners[i] = false;
            }
        }
        return winners;
    }


}
