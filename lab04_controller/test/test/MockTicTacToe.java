package test;

import tictactoe.Player;
import tictactoe.TicTacToe;

/**
 * Mock class for TicTacToe.
 */
public class MockTicTacToe implements TicTacToe {
  private StringBuilder log;
  private final int unique;
  
  public MockTicTacToe(StringBuilder log, int unique) {
    this.log = log;
    this.unique = unique;
  }

  @Override
  public void move(int r, int c) {
    log.append("Input: " + (r + 1)  + " " + (c + 1) + "\n");
  }

  @Override
  public Player getTurn() {
    return Player.X;
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public Player getWinner() {
    return null;
  }

  @Override
  public Player[][] getBoard() {
    return null;
  }

  @Override
  public Player getMarkAt(int r, int c) {
    log.append("Input: " + r + " " + c + "\n");
    return Player.X;
  }

}
