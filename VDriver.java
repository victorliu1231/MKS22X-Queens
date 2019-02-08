public class VDriver{
  public static void main(String[]args){
    QueenBoard q1 = new QueenBoard(4);
    /*
    q1.addQueen(3,3);
    System.out.println(q1.toStringUndercover());
    System.out.println();
    q1.addQueen(2,1);
    System.out.println(q1.toStringUndercover());
    System.out.println();
    q1.removeQueen(3,3);
    System.out.println(q1.toStringUndercover());
    */
    System.out.println("Should be true for the 4x4 board: "); System.out.println(q1.solve());
  }
}
