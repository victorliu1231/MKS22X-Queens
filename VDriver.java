public class VDriver{
  public static void main(String[]args){
    QueenBoard q1 = new QueenBoard(4);
    /*
    QueenBoard q2 = new QueenBoard(0);
    System.out.println(q2.solve());
    System.out.println(q2);
    System.out.println(q2.countSolutions());
    System.out.println(q2);
    */
    /*
    q1.addQueen(1,0);
    System.out.println(q1.toStringUndercover());
    System.out.println();
    q1.addQueen(3,1);
    System.out.println(q1.toStringUndercover());
    System.out.println();
    q1.addQueen(0,2);
    System.out.println(q1.toStringUndercover());
    System.out.println();
    q1.addQueen(2,3);
    System.out.println(q1.toStringUndercover());
    System.out.println();
    q1.removeQueen(2,3);
    System.out.println(q1.toStringUndercover());
    System.out.println();
    q1.removeQueen(0,2);
    System.out.println(q1.toStringUndercover());
    System.out.println();
    q1.removeQueen(3,1);
    System.out.println(q1.toStringUndercover());
    System.out.println();
    q1.removeQueen(1,0);
    System.out.println(q1.toStringUndercover());
    System.out.println();
    */

    System.out.println("Should be true for the 4x4 board: "); System.out.println(q1.solve()); System.out.println(q1);
    q1 = new QueenBoard(4);
    System.out.println("Should be 2 solutions for the 4x4 board: "); System.out.println(q1.countSolutions());
  }
  //i have tested zero sized boards... now to test countSolutions w/ non zero values still in it and other sized boards
}
