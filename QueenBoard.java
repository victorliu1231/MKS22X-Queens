public class QueenBoard{
  private int[][]board;
  private int numQueens;

  public QueenBoard(int size){
    board = new int[size][size];
    numQueens = 0;
  }

  //queen placement is represented by -1. Squares it stares down have +1 to them
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
    for (int r = 0; r < board.length; r++){
      for (int c = 0; c < board.length; c++){
        if (board[r][c] != 0){
          throw new IllegalStateException("Board is not resetted yet! (still has non-zero values in it)");
        }
      }
    }
    for (int n = 0; n < board.length; n++){
      if (solveHelp(n, 0, n, 0)){
        return true;
      }
      removeQueen(n,0); //to reset the board
    }
    return false;
  }

  private boolean solveHelp(int r, int c, int lastQueenR, int lastQueenC){ //lastQueenR and lastQueenC stores the memory of the last placed queen's position
    if (numQueens == board.length){
      return true;
    } else {
      if (board[r][c] == 0){ //only branches down the tree if it is possible to place a queen here
        addQueen(r,c);
        return (solveHelp(0,c+1,r,c) || solveHelp(1,c+1,r,c) ||
               solveHelp(2,c+1,r,c) || solveHelp(3,c+1,r,c)); //goes to next column
      } else {
        if (r == board.length - 1){ //if we have gone to the last row and there is no spot to place a queen on this column, remove last queen and backtrack
          removeQueen(lastQueenR, lastQueenC);
        }
        return false;
      }
    }
  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    for (int r = 0; r < board.length; r++){
      for (int c = 0; c < board.length; c++){
        if (board[r][c] != 0){
          throw new IllegalStateException("Board is not resetted yet! (still has non-zero values in it)");
        }
      }
    }
    int ans = 0;
    for (int n = 0; n < board.length; n++){
      if (solveHelp(n, 0, n, 0)){
        ans++;
        clear(); //bcuz clear only activates when there is a solution, which is relatively rare, the average case runtime isn't as big as it seems
      } else {
        removeQueen(n,0);
      }
    }
    return ans;
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
