import java.util.Scanner;
public class TicTacToePvP{
  public static int endGame(char[] places,char playerMove, char otherMove){
    if((places[6] == playerMove && places[7] == playerMove && places[8] == playerMove) ||
       (places[3] == playerMove && places[4] == playerMove && places[5] == playerMove) ||
       (places[0] == playerMove && places[1] == playerMove && places[2] == playerMove) ||
       (places[6] == playerMove && places[3] == playerMove && places[0] == playerMove) ||
       (places[7] == playerMove && places[4] == playerMove && places[1] == playerMove) ||
       (places[8] == playerMove && places[5] == playerMove && places[2] == playerMove) ||
       (places[0] == playerMove && places[4] == playerMove && places[8] == playerMove) ||
       (places[2] == playerMove && places[4] == playerMove && places[6] == playerMove)){
      return 10;
    }
    if((places[6] == otherMove && places[7] == otherMove && places[8] == otherMove) ||
       (places[3] == otherMove && places[4] == otherMove && places[5] == otherMove) ||
       (places[0] == otherMove && places[1] == otherMove && places[2] == otherMove) ||
       (places[6] == otherMove && places[3] == otherMove && places[0] == otherMove) ||
       (places[7] == otherMove && places[4] == otherMove && places[1] == otherMove) ||
       (places[8] == otherMove && places[5] == otherMove && places[2] == otherMove) ||
       (places[0] == otherMove && places[4] == otherMove && places[8] == otherMove) ||
       (places[2] == otherMove && places[4] == otherMove && places[6] == otherMove)){
      return -10;
    }
    return 0;
  }
  public static void drawGame(char[] places){
    
    System.out.println("Gameboard\t    NUMPAD Controls\n" +
                       places[6] + " | " +places[7] + " | " + places[8] + "\t\t7 | 8 | 9\n--+---+--\t\t--+---+--\n" +
                       places[3] + " | " +places[4] + " | " + places[5] + "\t\t4 | 5 | 6\n--+---+--\t\t--+---+--\n" + 
                       places[0] + " | " +places[1] + " | " + places[2] + "\t\t1 | 2 | 3");
  }
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    System.out.println("TicTacToe");
    char[] places = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
    System.out.println("Controls: The numbers on your numpad will control your moves as seen below");
    drawGame(places);
    boolean isPlayerTurn;
    System.out.println("Do you (Player1) want to go first? (y/n): ");
    String uiTurn = s.nextLine();
    char playerMove;
    char otherMove;
    if(uiTurn.equals("y")){
      isPlayerTurn = true;
      playerMove = 'X';
      otherMove = 'O';
    }else{
      isPlayerTurn = false;
      playerMove = 'O';
      otherMove = 'X';
    }
    if(!(uiTurn.equals("n") || uiTurn.equals("y"))){
      System.out.println("Error: Unexpected input -> Exiting program");
      return;
    }
    for(int count = 1; count > 0; count++){
      System.out.println("____Turn " + count + "____");
      if((!(new String(places).contains(" ")))){
          System.out.println("Tie Game! Nobody wins...");
          drawGame(places);
          return;
        } else if(endGame(places,playerMove,otherMove) == 10){
          System.out.println("Player 1 Wins!");
          drawGame(places);
          return;
        } else if(endGame(places,playerMove,otherMove) == -10){
          System.out.println("Player 2 Wins!");
          drawGame(places);
          return;
        }
      if(isPlayerTurn){
        System.out.println("Player 1 turn:\nWhere do you want to place your marker (Use Numpad Controls): ");
        drawGame(places);
        int ui = s.nextInt();
        if(places[ui-1] == ' ')
           places[ui-1] = playerMove;
        else continue;
        isPlayerTurn = false;
        continue;
      }
      if(!isPlayerTurn){
        System.out.println("Player 2 turn:\nWhere do you want to place your marker (Use Numpad Controls): ");
        drawGame(places);
        int ui = s.nextInt();
        if(places[ui-1] == ' ')
          places[ui-1] = otherMove;
        else continue;
        isPlayerTurn = true;
        continue;
      }  
    }
    s.close();
  }
}