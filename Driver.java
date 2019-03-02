import java.util.*;
import java.io.*;

public class Driver{
  public static void main(String[] args){
    try{
      Maze maze1 = new Maze("data1.dat");
      System.out.println(maze1);
      maze1.setAnimate(false);
      System.out.println(maze1.solve());
      System.out.println(maze1);

      Maze maze2 = new Maze("data2.dat");
      System.out.println(maze2);
      maze1.setAnimate(false);
      System.out.println(maze2.solve());
      System.out.println(maze2);

      Maze maze3 = new Maze("data3.dat");
      System.out.println(maze3);
      maze1.setAnimate(false);
      System.out.println(maze3.solve());
      System.out.println(maze3);

      Maze maze4 = new Maze("Maze1.txt");
      System.out.println(maze4);
      maze1.setAnimate(false);
      System.out.println(maze4.solve());
      System.out.println(maze4);
    }catch(FileNotFoundException e){
      System.out.println("File not found");
    }
  }
}
