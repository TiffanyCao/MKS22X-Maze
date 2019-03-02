
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
      animate = false;
      File text = new File(filename);
      Scanner readFile = new Scanner(text);
      int numLines = 0;
      int numCols = 0;
      while(readFile.hasNextLine()){
        numLines++; //find the number of rows
        String line = readFile.nextLine();
        numCols = line.length(); //find the number of columns
      }
      int numE = 0;
      int numS = 0;
      Scanner read = new Scanner(text);
      maze = new char[numLines][numCols];
      for(int i = 0; i < numLines; i++){ //convert the text into a 2D char array
        String line = read.nextLine();
        for(int y = 0; y < numCols; y++){
          if(line.charAt(y) == 'E') numE++; //check number of E's
          if(line.charAt(y) == 'S') numS++; //check number of S's
          maze[i][y] = line.charAt(y);
        }
      }
      if(numE != 1 || numS != 1) throw new IllegalStateException();
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

    private boolean isFull(){
      for(int i = 0; i < maze.length; i++){
        for(int y = 0; y < maze[i].length; y++){
          if(maze[i][y] == '@' || maze[i][y] == ' ') return false;
        }
      } return true;
    }

    private int[][] move = {{0, -1},
                            {-1, 0},
                            {0, 1},
                            {1, 0}};

    /**A method that checks if a square of the maze is available or empty
    *The character '@' is placed on the square
    *@param int row
    *@param int col
    *@return boolean
    */
    private boolean makeMove(int row, int col){
      if(row >= 0 && row < maze.length && col >= 0 && col < maze[0].length){ //check if it's within bounds
           if(maze[row][col] == ' '){ //checks if it's empty
             maze[row][col] = '@'; //place '@'
             return true;
           }
         }
      return false;
    }

    /**A method that undos a move by placing the character '@' on the square
    *@param int row
    *@param int col
    *@return boolean
    */
    private boolean undoMove(int row, int col){
      if(maze[row][col] != '@') return false; //checks if the square was marked
      maze[row][col] = '.'; //remarks the square
      return true;
    }

    /**Wrapper Solve Function returns the helper function

      Note the helper function has the same name, but different parameters.
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.
    *@return int the number of moves
    */
    public int solve(){

            //find the location of the S.
      int rowS = 0;
      int colS = 0;
      for(int i = 0; i < maze.length; i++){
        for(int y = 0; y < maze[i].length; y++){
          if(maze[i][y] == 'S'){
            rowS = i;
            colS = y;
            maze[i][y] = ' '; //erase the S
          }
        }
      }
      return solve(rowS, colS, 0); //and start solving at the location of the s.
    }

    /**
      Recursive Solve function:

      A solved maze has a path marked with '@' from S to E.

      Returns the number of @ symbols from S to E when the maze is solved,
      Returns -1 when the maze has no solution.


      Postcondition:

        The S is replaced with '@' but the 'E' is not.

        All visited spots that were not part of the solution are changed to '.'

        All visited spots that are part of the solution are changed to '@'

    *@param int row
    *@param int col
    *@param int count keeps track of how many moves there have been
    *@return int count
    */
    private int solve(int row, int col, int count){ //you can add more parameters since this is private


        //automatic animation! You are welcome.
        if(animate){

            clearTerminal();
            System.out.println(this);

            wait(20);
        }

        //COMPLETE SOLVE
        if(maze[row][col] == 'E') return count; //check if we're on E and return the number of moves
        if(!makeMove(row, col)) return -1; //if the path cannot be found, the function stops
        int test;
        for(int i = 0; i < move.length; i++){ //loops through possible moves
          test = solve(row + move[i][0], col + move[i][1], count + 1); //try next move
          if(test != -1){
            return test; //return the number of moves (aka count) if the function doesn't result in -1
          }
        }
        undoMove(row, col); //undo the move if path fails
        return -1; //so it compiles
    }


    public static void main(String[] args){
      try{
        Maze maze1 = new Maze("data1.dat");
        System.out.println(maze1);
        maze1.setAnimate(true);
        System.out.println(maze1.solve());
        System.out.println(maze1);
      }catch(FileNotFoundException e){
        System.out.println("File not found");
      }
    }
}
