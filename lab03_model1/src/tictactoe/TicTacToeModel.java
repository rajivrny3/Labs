package tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Creates a model of a tictactoe board.
 */
public class TicTacToeModel implements TicTacToe {
  private Player[][] board;
  private Player turnStatus;
  private boolean gameEnd;
  
  /**
   * Creates an instance of a TicTacToe Board with all squares set to null.
   * turn status is set to player X for first turn.
   * gameStatus is set to False because the game is not over.
   */
  public TicTacToeModel() {
    board = new Player[3][3];
    turnStatus = Player.X;
    gameEnd = false;
  }
  
  /**
   * Checks if the spot entered by a player is an invalid position. Helper for move method.
   */
  private boolean invalidPosition(int r, int c) {
    if (r < 0 || c < 0 || r > 2 || c > 2) {
      return true;
    } else {
      return false;
    }
  }
  
  /**
   *   Checks if the spot entered by a player is in an already 
   *   filled position. Helper for move method.
   */
  private boolean spotFilled(int r, int c) {
    if (board[r][c] != null) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void move(int r, int c) {
    if (isGameOver()) {
      throw new IllegalStateException("A new turn cannot be taken as the game is already over.");
    }
    
    if (invalidPosition(r, c)) {
      throw new IllegalArgumentException("The position entered is invalid. "
        + "Please try again within the bounds of the board.");
    }
    
    if (spotFilled(r, c)) {
      throw new IllegalArgumentException("The position entered is already filled.");
    }
    board[r][c] = turnStatus;
    
    if (getWinner() != null || fullBoard()) {
      gameEnd = true;
    }

    if (turnStatus == Player.X) {
      turnStatus = Player.O;
    } else {
      turnStatus = Player.X;
    }
  }

  @Override
  public Player getTurn() {
    return turnStatus;
  }
  
  /*
   * Helper method to determine if board is full and ends game if it returns true.
   */
  private boolean fullBoard() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        if (board[i][j] == null) {
          return false;
        }
      }
    }
    return true;
  }
  
  /*
   * Helper method to check for winners in any of the rows of the board.
   */
  private Player rowsEqual() {
    for (int r = 0; r < board.length; r++) {
      if (board[r][0] != null && board[r][0] == board [r][1] && board[r][1] == board[r][2]) {
        return board[r][0];
      }
    }
    return null;
  }
  
  /**
   * Helper method to check for winners in any of the columns of the board.
   */
  private Player columnsEqual() {
    for (int c = 0; c < board.length; c++) {
      if (board[0][c] != null && board[0][c] == board [1][c] && board[1][c] == board[2][c]) {
        return board[0][c];
      }
    }
    return null;
  }
  
  /**
   * Helper method to check for winners in any of the diagonals of the board.
   */
  private Player diagonalsEqual() {
    if (board[0][0] != null && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
      return board[0][0];
    }
    if (board[0][2] != null && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
      return board[2][0];
    } 
    return null;
  }
  


  @Override
  public boolean isGameOver() {
    return gameEnd;
  }

  @Override
  public Player getWinner() {
    Player winner = rowsEqual();
    if (winner != null) {
      return winner;
    }
    winner  = columnsEqual();
    if (winner != null) {
      return winner;
    }
    winner = diagonalsEqual();
    return winner;
  }
  
  /**
   * Creates a copy of the current board state to return to the player with getBoard().
   * This is so that the original board cannot be directly accessed and manipulated without
   * the use of the methods defined in this class.
   */
  private Player[][] copyBoard() {
    Player[][] copiedBoard = new Player[3][3];
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        copiedBoard[i][j] = board[i][j];
      }
    }
    return copiedBoard;
  }

  @Override
  public Player[][] getBoard() {
    Player[][] currentBoard = copyBoard();
    return currentBoard;
  }

  @Override
  public Player getMarkAt(int r, int c) {
    if (invalidPosition(r, c)) {
      throw new IllegalArgumentException("Invalid location. This index is not within the board.");
    }
    return board[r][c];
  }

  @Override
  public String toString() {
    // Using Java stream API to save code:
    return Arrays.stream(getBoard()).map(
      row -> " " + Arrays.stream(row).map(
        p -> p == null ? " " : p.toString()).collect(Collectors.joining(" | ")))
         .collect(Collectors.joining("\n-----------\n"));
    // This is the equivalent code as above, but using iteration, and still using the helpful
    // built-in String.join method.
    // List<String> rows = new ArrayList<>();
    // for(Player[] row : getBoard()) {
    //   List<String> rowStrings = new ArrayList<>();
    //   for(Player p : row) {
    //     if(p == null) {
    //       rowStrings.add(" ");
    //     } else {
    //       rowStrings.add(p.toString());
    //     }
    //   }
    //   rows.add(" " + String.join(" | ", rowStrings));
    // }
    // return String.join("\n-----------\n", rows);
  }
}



