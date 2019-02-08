public class QueenBoard{
  private int[][]board;

  public QueenBoard(int size){
    board = new int[size][size];
  }

  private boolean addQueen(int r, int c){
    //X's out the rows that the queen looks across
    for (int x = 0; x < board.length; x++){
      board[r][x] += 1;
    }
    for (int y = 0; y < board.length; y++){
      board[y][c] += 1;
    }
    int n = 0; //to help increment with diagonals
    //x < board.length is horizontal restriction, n < board.length is vertical restriction
    for (int x = c-r; x < board.length && n < board.length; x++){ //x = c-r calculates which column to start the diagonal
      if (x >= 0){ //doesn't put the increase if the start point is off the board
        board[n][x] += 1;
      }
      n++;
    }
    n = 0;
    //x >= 0 is horizontal restriction, n < board.length is vertical restriction
    for (int x = c+r; x >= 0 && n < board.length; x--){ //x = c+r calculates which column to start the diagonal
      if (x < board.length){ //doesn't put the increase if the start point is off the board
        board[n][x] += 1;
      }
      n++;
    }
    board[r][c] = -1; //sets position (r,c) to be -1 to indicate queen spot
    return true;
  }

  private boolean removeQueen(int r, int c){
    //X's out the rows that the queen looks across
    for (int x = 0; x < board.length; x++){
      board[r][x] -= 1;
    }
    for (int y = 0; y < board.length; y++){
      board[y][c] -= 1;
    }
    int n = 0; //to help increment with diagonals
    //x < board.length is horizontal restriction, n < board.length is vertical restriction
    for (int x = c-r; x < board.length && n < board.length; x++){ //x = c-r calculates which column to start the diagonal
      if (x >= 0){ //doesn't put the increase if the start point is off the board
        board[n][x] -= 1;
      }
      n++;
    }
    n = 0;
    //x >= 0 is horizontal restriction, n < board.length is vertical restriction
    for (int x = c+r; x >= 0 && n < board.length; x--){ //x = c+r calculates which column to start the diagonal
      if (x < board.length){ //doesn't put the increase if the start point is off the board
        board[n][x] -= 1;
      }
      n++;
    }
    board[r][c] = 0; //since queens will never be placed in positions that are >= 1, it is fine to set (r,c) to be 0
    return true;
  }


  /**
  *@return The output string formatted as follows:
  *All numbers that represent queens are replaced with 'Q'
  *all others are displayed as underscores '_'
  *There are spaces between each symbol:
  *"""_ _ Q _
  *Q _ _ _

  *_ _ _ Q

  *_ Q _ _"""
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
    return true;
  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    return -1;
  }


}
