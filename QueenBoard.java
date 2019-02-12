public class QueenBoard{
  private int[][]board;
  private int numQueens;
  private int numSolutions;

  public QueenBoard(int size){
    board = new int[size][size];
    numQueens = 0;
    numSolutions = 0;
  }

  //Queen placement is represented by -1. Squares it stares down have +1 to them.
  //Don't need to add +1 to squares left of queen placement since implementation of code will never call addQueen in situations
  //where you need to test if a queen can be added left of another queen
  private boolean addQueen(int r, int c){
    int i = 0; //incrementor for the moving up/down part of diagonals
    for (int colIndex = c; colIndex < board.length; colIndex++){
      board[r][colIndex] += 1;
      if (r+i < board.length){ //makes sure the array doesn't go out of bounds
        board[r+i][colIndex] += 1;
      }
      if (r-i >= 0){ //makes sure the array doesn't go out of bounds
        board[r-i][colIndex] += 1;
      }
      i++;
    }
    for (int rowIndex = 0; rowIndex < board.length; rowIndex++){
      board[rowIndex][c] += 1;
    }
    board[r][c] = -1;
    numQueens++;
    return true;
  }

  //returns true when queen is successfully removed, false when there is no queen at the specified square
  private boolean removeQueen(int r, int c){
    if (board[r][c] != -1){
      return false;
    }
    int i = 0; //incrementor for the moving up/down part of diagonals
    for (int colIndex = c; colIndex < board.length; colIndex++){
      board[r][colIndex] -= 1;
      if (r+i < board.length){ //makes sure the array doesn't go out of bounds
        board[r+i][colIndex] -= 1;
      }
      if (r-i >= 0){ //makes sure the array doesn't go out of bounds
        board[r-i][colIndex] -= 1;
      }
      i++;
    }
    for (int rowIndex = 0; rowIndex < board.length; rowIndex++){
      board[rowIndex][c] -= 1;
    }
    numQueens--;
    board[r][c] = 0; //since queens will never be placed in positions that are >= 1, it is fine to set (r,c) to be 0
    return true;
  }


  /**
  *@return The output string formatted as follows:
  *All numbers that represent queens are replaced with 'Q'
  *all others are displayed as underscores '_'
  *There are spaces between each symbol:
  *"""_ _ Q _
  *   Q _ _ _
  *   _ _ _ Q
  *   _ Q _ _"""
  *(pythonic string notation for clarity,
  *excludes the character up to the *)
  */
  public String toString(){
    String ans = "";
    for (int r = 0; r < board.length; r++){
      for (int c = 0; c < board.length; c++){
        if (board[r][c] == -1){
          ans+= "Q";
        } else {
          ans+= "_";
        }
        //this if case makes sure there is no whitespace at the end of rows
        if (c != board.length - 1){
          ans+= " ";
        }
      }
      //this if case makes sure there is no new row at the end of parsing
      if (r != board.length - 1){
        ans+= "\n";
      }
    }
    return ans;
  }

  //prints out the array but with the numbers underneath that make up the data structure of the board
  public String toStringUndercover(){
    String ans = "";
    for (int r = 0; r < board.length; r++){
      for (int c = 0; c < board.length; c++){
        if (board[r][c] == -1){
          ans+= "Q";
        } else {
          ans+= board[r][c];
        }
        //this if case makes sure there is no whitespace at the end of rows
        if (c != board.length - 1){
          ans+= " ";
        }
      }
      //this if case makes sure there is no new row at the end of parsing
      if (r != board.length - 1){
        ans+= "\n";
      }
    }
    return ans;
  }



  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;
  *        true when the board is solveable, and leaves the board in a solved state
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public boolean solve(){
    if (board.length == 0){ //0x0 board has 1 way to place 0 queens
      return true;
    }
    for (int r = 0; r < board.length; r++){
      for (int c = 0; c < board.length; c++){
        if (board[r][c] != 0){
          throw new IllegalStateException("Board is not resetted yet! (still has non-zero values in it)");
        }
      }
    }
    for (int n = 0; n < board.length; n++){
      if (solveHelp(n, 0, false)){
        return true;
      }
      removeQueen(n,0); //to reset the board if solveHelp is false
    }
    return false;
  }

  //for the solve() method, return type is boolean
  public boolean solveHelp(int r, int c, boolean isSolved){
    if (numQueens == board.length){
      return true;
    } else {
      if (board[r][c] == 0){ //only branches down the tree if it is possible to place a queen here
        addQueen(r,c);
        for (int n = 0; n < board.length; n++){
          isSolved = isSolved || solveHelp(n, c+1, isSolved); //goes to next column
        }
        if (!isSolved){
          removeQueen(r,c);
        }
        return isSolved;
      } 
      return false;
    }
  }



  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    if (board.length == 0){
      return 1; //0x0 board has 1 way to place 0 queens
    }
    numSolutions = 0;
    for (int r = 0; r < board.length; r++){
      for (int c = 0; c < board.length; c++){
        if (board[r][c] != 0){
          throw new IllegalStateException("Board is not resetted yet! (still has non-zero values in it)");
        }
      }
    }
    for (int n = 0; n < board.length; n++){
      solveHelp(n, 0, n, 0);
      clear(); //bcuz clear only activates when there is a solution, which is relatively rare, the average case runtime isn't as big as it seems
    }
    return numSolutions;
  }

  //for the countSolutions method, modifies numSolutions variable
  public void solveHelp(int r, int c, int lastQueenR, int lastQueenC){ //lastQueenR and lastQueenC stores the memory of the last placed queen's position
    if (numQueens == board.length && r == board.length - 1){
      removeQueen(lastQueenR, lastQueenC); //backtracks and adds a solution when you find a working configuration
      numSolutions++;
      return;
    } else {
      if (c < board.length && board[r][c] == 0){ //utilizes short circuiting; only branches down the tree if it is possible to place a queen here
        addQueen(r,c);
        int memory = numSolutions;
        for (int n = 0; n < board.length; n++){
          solveHelp(n, c+1, r, c); //goes to next column
        }
        if (numSolutions == memory){ //if the new number of solutions is same as the old # before going to the new column, that means the current column produced no new solutions, so backtrack
          removeQueen(r,c);
        }
      }

      if (r == board.length - 1){ //if you are finished parsing through the column, then backtrack to explore any new solutions
        removeQueen(lastQueenR, lastQueenC);
      }
      return;
    }
  }

  private void clear(){
    for (int r = 0; r < board.length; r++){
      for (int c = 0; c < board.length; c++){
        board[r][c] = 0;
      }
    }
    numQueens = 0;
  }

}
