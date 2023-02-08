import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.*;
import java.util.stream.*;
import oop.ex5.field.*;
import oop.ex5.Runner.*;

class Main5 {
  public static void main(String[] args) {
    int argc = args.length;
    // 以下のコードは自由に変更してください
    try{
      //input
      int i;
      for (i = 0; i < argc; i++){
        File inputFile = new File(args[i]);

        if (!inputFile.exists()) {
          System.out.println("file " + args[i] + " doesn't exist");
        }

        FileReader fr = new FileReader(inputFile);
        BufferedReader br = new BufferedReader(fr);

        int lnum; //laneNumber
        int gnum; //goal
        int snum; //stamina

        String data;
        data = br.readLine();

        List<String> initValues = Arrays.asList(data.split(" "));

        lnum = Integer.parseInt(initValues.get(0));
        gnum = Integer.parseInt(initValues.get(1));
        snum = Integer.parseInt(initValues.get(2));

        data = br.readLine();
        List<String> RunnerTypes = Arrays.asList(data.split(" "));

        String[] arrayRunnerTypes = new String[RunnerTypes.size()];
        RunnerTypes.toArray(arrayRunnerTypes);
        int[] runnerTypes = Stream.of(arrayRunnerTypes).mapToInt(Integer::parseInt).toArray();

        //output

        File outputFile = new File(args[i]+".log");

        FileWriter fw = new FileWriter(outputFile);
        if(!outputFile.canWrite()){
          outputFile.setWritable(true);
        }

        field race = new field(runnerTypes, snum, gnum, lnum);

        while(!race.reached()){
          fw.write("State " + race.getStatus() + "\n");
          fw.write(race.getRunnerStatus());
          race.step();
          fw.write("Command " + race.getStatus() + "\n");
          fw.write(race.getCommands());
        }

        fw.write("State " + race.getStatus() + "\n");
        fw.write(race.getRunnerStatus());

        /*
        fw.write("\n\nwinner(s):");

        for(int j = 0; j < lnum; j++){
          if(race.getWinners()[j]) {
            fw.write(" " + j);
          }
        }

        */


        fw.close();
      }

    } catch (IOException e){
      System.out.println(e);
    }
  }
}
