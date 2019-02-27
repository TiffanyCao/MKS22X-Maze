
import java.util.*;
import java.io.*;
public class Maze{


    private char[][]maze;
    private boolean animate;//false by default

    /*Constructor loads a maze text file, and sets animate to false by default.

      1. The file contains a rectangular ascii maze, made with the following 4 characters:
      '#' - Walls - locations that cannot be moved onto
      ' ' - Empty Space - locations that can be moved onto
      'E' - the location of the goal (exactly 1 per file)

      'S' - the location of the start(exactly 1 per file)

      2. The maze has a border of '#' around the edges. So you don't have to check for out of bounds!


      3. When the file is not found OR the file is invalid (not exactly 1 E and 1 S) then:

         throw a FileNotFoundException or IllegalStateException

    */

    public Maze(String filename) throws FileNotFoundException{
        //COMPLETE CONSTRUCTOR
      Scanner text = new Scanner(filename);
      int numLines = 0;
      int numCols = 0;
      while(text.hasNextLine()){
        numLines++; //find the row
        String line = text.nextLine();
        numCols = line.length(); //find the column
      }
      Scanner read = new Scanner(filename);
      maze = new char[numLines][numCols];
      for(int i = 0; i < numLines; i++){ //convert the text into a 2D char array
        String line = read.nextLine();
        for(int y = 0; y < numCols; y++){
          maze[i][y] = line.charAt(y);
        }
      }
    }

    public String toString(){
      String result = "";
      for(int i = 0; i < maze.length; i++){
        for(int y = 0; y < maze[i].length; y++){
          result += maze[i][y] + "";
          if(y == maze[i].length - 1) result += "\n";
        }
      }
      return result;
    }

    private void wait(int millis){
         try {
             Thread.sleep(millis);
         }
         catch (InterruptedException e) {
         }
     }


    public void setAnimate(boolean b){

        animate = b;

    }


    public void clearTerminal(){

        //erase terminal, go to top left of screen.

        System.out.println("\033[2J\033[1;1H");

    }



    /*Wrapper Solve Function returns the helper function

      Note the helper function has the same name, but different parameters.
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.

    */
    public int solve(){

            //find the location of the S.


            //erase the S


            //and start solving at the location of the s.

            //return solve(???,???);
            return 0;

    }

    /*
      Recursive Solve function:

      A solved maze has a path marked with '@' from S to E.

      Returns the number of @ symbols from S to E when the maze is solved,
      Returns -1 when the maze has no solution.


      Postcondition:

        The S is replaced with '@' but the 'E' is not.

        All visited spots that were not part of the solution are changed to '.'

        All visited spots that are part of the solution are changed to '@'
    */
    private int solve(int row, int col){ //you can add more parameters since this is private


        //automatic animation! You are welcome.
        if(animate){

            clearTerminal();
            System.out.println(this);

            wait(20);
        }

        //COMPLETE SOLVE

        return -1; //so it compiles
    }


    public static void main(String[] args){
      try{
        Maze data1 = new Maze("data1.dat");
        System.out.println(data1);
      }catch(FileNotFoundException e){
        System.out.println("File not found");
      }
    }
}
