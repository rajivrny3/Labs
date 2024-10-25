package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import tictactoe.Player;
import tictactoe.TicTacToe;
import tictactoe.TicTacToeModel;

/**
 * A class used to test the TicTacToeModel class.
 */
public class TicTacToeTester {
  private TicTacToe model;

  @Before
    public void setUp() {
    model = new TicTacToeModel();
  }

  @Test
    public void testBlankBoard() {
    assertEquals(Player.X, model.getTurn());
    assertEquals(null, model.getMarkAt(0, 0));

    Player[][] board = model.getBoard();

    assertEquals(null, board[2][2]);
    assertEquals(null, board[1][1]);
  }
  
  @Test
  public void testMoving() {
    model.move(0, 0);
    model.move(1, 1);
    model.move(2, 2);
    
    Player [][] board = model.getBoard();
    assertEquals(Player.X, board[0][0]);
    assertEquals(Player.O, board[1][1]);
    assertEquals(Player.X, board[2][2]);
  }
  
  @Test
  public void testWinner() {
    model.move(0, 0);
    model.move(1, 0);
    model.move(0, 1);
    model.move(1, 1);
    model.move(0, 2);
    
    
    assertEquals(Player.X, model.getWinner());
  }
  
  @Test
  public void testWinnerC() {
    model.move(0, 0);
    model.move(0, 1);
    model.move(1, 0);
    model.move(0, 2);
    model.move(2, 0);
  }
  
  @Test
  public void testWinnderD() {
    model.move(0, 0);
    model.move(0, 1);
    model.move(1, 1);
    model.move(0, 2);
    model.move(2, 2);
  }
  
  @Test
  public void testWinnderD2() {
    model.move(0, 2);
    model.move(0, 1);
    model.move(1, 1);
    model.move(1, 0);
    model.move(2, 0);
  }
  
  @Test
  public void testFullBoard() {
    model.move(1, 1);
    model.move(0, 0);
    model.move(0, 1);
    model.move(2, 1);
    model.move(1, 2);
    model.move(1, 0);
    model.move(2, 0);
    model.move(0, 2);
    model.move(2, 2);
  }
  
  @Test(expected = IllegalStateException.class)
  public void testMoveAfterWinner() {
    model.move(0, 0);
    model.move(1, 0);
    model.move(0, 1);
    model.move(1, 1);
    model.move(0, 2);
    model.move(1, 2);
  }
  
  @Test(expected = IllegalArgumentException.class) 
  public void testGetMarkOutside() {
    model.getMarkAt(3, 0);
  }
  
  
  @Test(expected = IllegalArgumentException.class) 
  public void testInvalidMove() {
    model.move(0, 0);
    model.move(0, 0);
  }
  
  @Test(expected = IllegalArgumentException.class) 
  public void testInvalidPosition1() {
    model.move(3, 2);
  }
    
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPosition2() {
    model.move(-1, 2);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPosition3() {
    model.move(1, 4);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPosition4() {
    model.move(0, -1);
  }
  

}
