public class VDriver{
  public static void main(String[]args){
    QueenBoard q1 = new QueenBoard(4);
    q1.addQueen(0,0);
    System.out.println(q1.toStringUndercover());
  }
}
