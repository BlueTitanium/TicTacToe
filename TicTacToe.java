import java.util.Scanner;
public class TicTacToe{
  //maxmin functions
  public static int max(int a, int b){
    if(a > b)return a;
    return b;
  }
  public static int min(int a, int b){
    if(a<b)return a;
    return b;
  }
  //if this looks bad, it is
  //I probably should have used a matrix and that could've made things wayyyy easier
  public static int endGame(char[] places,char playerMove, char cpuMove){
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
    if((places[6] == cpuMove && places[7] == cpuMove && places[8] == cpuMove) ||
       (places[3] == cpuMove && places[4] == cpuMove && places[5] == cpuMove) ||
       (places[0] == cpuMove && places[1] == cpuMove && places[2] == cpuMove) ||
       (places[6] == cpuMove && places[3] == cpuMove && places[0] == cpuMove) ||
       (places[7] == cpuMove && places[4] == cpuMove && places[1] == cpuMove) ||
       (places[8] == cpuMove && places[5] == cpuMove && places[2] == cpuMove) ||
       (places[0] == cpuMove && places[4] == cpuMove && places[8] == cpuMove) ||
       (places[2] == cpuMove && places[4] == cpuMove && places[6] == cpuMove)){
      return -10;
    }
    return 0;
  }
  //depth is the variable about how many turns it will take the AI to win
  //obviously you want the smallest depth
  public static int minMax(char[] places, char playerMove, char cpuMove, int depth, boolean isMax){
    //Funny story: I was struggling so long trying to find out why my AI didn't work well
    //It turns out that I was using the opposite values for my final score, and thus had to multiply it by -1
    int finalScore = -1*endGame(places,playerMove,cpuMove);
    if(finalScore == -10) return finalScore;
    if(finalScore == 10) return finalScore;
    if(!(new String(places).contains(" ")))return 0;
    if(isMax){
      //this interprets the computer's possible move choices
      int best = -100000;
      for(int i = 0; i < 9; i++){
        if(places[i] == ' '){
          places[i] = cpuMove;
          best = max(best,minMax(places,playerMove,cpuMove,(depth+1),!isMax));
          places[i] = ' ';
        }
      }
      return best - depth;
    }else{
      //this interprets the player's possible move choices
      int best = 100000;
      for(int i = 0; i < 9; i++){
        if(places[i] == ' '){
          places[i] = playerMove;
          best = min(best,minMax(places,playerMove,cpuMove,(depth+1),!isMax));
          places[i] = ' ';
        }
      }
      return best + depth;
    }
  }
  //this will give the array value for the next move that the cpu will make
  public static int nextCPUMove(char[] places, char playerMove, char cpuMove){
    int best = -1000;
    int bestMove = -1;
    for(int i = 0; i < 9; i++){
      if(places[i] == ' '){
        places[i] = cpuMove;
        int move = minMax(places, playerMove,cpuMove, 1, false);
        places[i] = ' ';
        if(move>best){
          bestMove = i;
          best = move;
        }
      }
    }
    return bestMove;
  }
  public static void drawGame(char[] places){
    
    System.out.println("Gameboard\t    NUMPAD Controls\n" +
                       places[6] + " | " +places[7] + " | " + places[8] + "\t\t7 | 8 | 9\n--+---+--\t\t--+---+--\n" +
                       places[3] + " | " +places[4] + " | " + places[5] + "\t\t4 | 5 | 6\n--+---+--\t\t--+---+--\n" + 
                       places[0] + " | " +places[1] + " | " + places[2] + "\t\t1 | 2 | 3");
  }
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    System.out.println("Welcome to the super duper ultra hard Tic-Tac-Toe! You probably can't beat the AI! Good Luck!");
    char[] places = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
    System.out.println("Controls: The numbers on your numpad will control your moves as seen below");
    drawGame(places);
    boolean isPlayerTurn;
    System.out.println("Do you want to go first? (y/n): ");
    String uiTurn = s.nextLine();
    char playerMove;
    char cpuMove;
    if(uiTurn.equals("y")){
      isPlayerTurn = true;
      playerMove = 'X';
      cpuMove = 'O';
    }else{
      isPlayerTurn = false;
      playerMove = 'O';
      cpuMove = 'X';
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
        } else if(endGame(places,playerMove,cpuMove) == 10){
          System.out.println("You Won! Congratulations!");
          drawGame(places);
          return;
        } else if(endGame(places,playerMove,cpuMove) == -10){
          System.out.println("You lose... The CPU now has been evaluated to be better than humanity, marking the end of the human race and the rise of the AI");
          drawGame(places);
          return;
        }
      if(isPlayerTurn){
        System.out.println("Player turn:\nWhere do you want to place your marker (Use Numpad Controls): ");
        drawGame(places);
        int ui = s.nextInt();
        places[ui-1] = playerMove;
        isPlayerTurn = false;
        continue;
      }
      if(!isPlayerTurn){
        System.out.println("Computer turn: ");
        drawGame(places);
        places[nextCPUMove(places,playerMove,cpuMove)] = cpuMove;
        isPlayerTurn = true;
        continue;
      }  
    }
    s.close();
  }
}