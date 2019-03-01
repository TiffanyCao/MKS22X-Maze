
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

    private boolean makeMove(int row, int col){
      //int newRow = row + move[moveNum][0];
      //int newCol = col + move[moveNum][1];
      if(row >= 0 && row < maze.length && col >= 0 && col < maze[0].length){
           if(maze[row][col] == ' '){
             maze[row][col] = '@';
             return true;
           }
         }
      return false;
    }

    private boolean undoMove(int row, int col){
      if(maze[row][col] != '@') return false;
      maze[row][col] = '.';
      return true;
    }

    /*Wrapper Solve Function returns the helper function

      Note the helper function has the same name, but different parameters.
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.

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
    private int solve(int row, int col, int count){ //you can add more parameters since this is private


        //automatic animation! You are welcome.
        if(animate){

            clearTerminal();
            System.out.println(this);

            wait(20);
        }

        //COMPLETE SOLVE
        boolean x = true;
        if(maze[row][col] == 'E'){
          System.out.println(count + "line 176");

          return count; //check if we're already on E
        }
        if(!makeMove(row, col)){
          System.out.println(count + " " + row + " " + col);
          return -1; //if the path cannot be found
        }
        if(maze[row][col] == 'E') {
          System.out.println(count + " " + row + " " + col);
          return count; //check after move if we're on E
        }
        for(int i = 0; i < move.length; i++){
          if(solve(row + move[i][0], col + move[i][1], count+1) != -1){
            System.out.println(count + " " + row + " " + col);
            return count; //return the number of moves
          }else{
            x = false;
          }
        }
        if(!x) undoMove(row, col); //undo the move if path fails
        System.out.println(count + " " + row + " " + col);
        return -1; //so it compiles

    }


    public static void main(String[] args){
      try{
        Maze maze1 = new Maze("data1.dat");
        System.out.println(maze1);
        maze1.setAnimate(false);
        System.out.println(maze1.solve());
        System.out.println(maze1);
      }catch(FileNotFoundException e){
        System.out.println("File not found");
      }
    }
}
