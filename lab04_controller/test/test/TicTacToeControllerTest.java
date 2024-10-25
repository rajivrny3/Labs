package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import tictactoe.Player;
import tictactoe.TicTacToe;
import tictactoe.TicTacToeConsoleController;
import tictactoe.TicTacToeController;
import tictactoe.TicTacToeModel;


/**
 * Test cases for the tic tac toe controller, using mocks for readable and
 * appendable.
 */
public class TicTacToeControllerTest {

  private TicTacToe board;
  private TicTacToeController controller;
  
  @Before
  public void setup() {
    board = new TicTacToeModel();
  }
  
  
  @Test
  public void testGameMove() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("1 3 q");
    StringBuilder log = new StringBuilder();
    board = new MockTicTacToe(log, 123);
    controller = new TicTacToeConsoleController(in, out);
    controller.playGame(board);
    assertEquals("Input: 1 3\n", log.toString());
  }
  
  @Test
  public void testInvalidInputRow() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("four 1 1 1 q");
    StringBuilder log = new StringBuilder();
    board = new MockTicTacToe(log, 123);
    controller = new TicTacToeConsoleController(in, out);
    controller.playGame(board);
    assertEquals("Input: 1 1\n", log.toString());
  }
  
  @Test
  public void testInvalidInputCol() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("1 four 1 1 q");
    StringBuilder log = new StringBuilder();
    board = new MockTicTacToe(log, 123);
    controller = new TicTacToeConsoleController(in, out);
    controller.playGame(board);
    assertEquals("Input: 1 1\n", log.toString());
  }
  
  @Test
  public void testInvalidMove() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("1 4 1 3 q");
    StringBuilder log = new StringBuilder();
    board = new MockTicTacToe(log, 123);
    controller = new TicTacToeConsoleController(in, out);
    controller.playGame(board);
    assertEquals("Input: 1 4\nInput: 1 3\n", log.toString());
  }
  
  @Test
  public void testInvalidMoves() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("1 4 1 4 q");
    StringBuilder log = new StringBuilder();
    board = new MockTicTacToe(log, 123);
    controller = new TicTacToeConsoleController(in, out);
    controller.playGame(board);
    assertEquals("Input: 1 4\nInput: 1 4\n", log.toString());
  }
  
  @Test
  public void testInvalidInputContinue() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("-1 1 3 q");
    StringBuilder log = new StringBuilder();
    board = new MockTicTacToe(log, 123);
    controller = new TicTacToeConsoleController(in, out);
    controller.playGame(board);
    assertEquals("Input: 1 3\n", log.toString());
  }
  
  @Test
  public void testContinueAfterInvalid() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("4 4 1 1");
    StringBuilder log = new StringBuilder();
    board = new MockTicTacToe(log, 123);
    controller = new TicTacToeConsoleController(in, out);
    controller.playGame(board);
    assertEquals("Input: 4 4\nInput: 1 1\n", log.toString());
  }
  
  @Test
  public void testQuitRow() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("q");
    StringBuilder log = new StringBuilder();
    board = new MockTicTacToe(log, 123);
    controller = new TicTacToeConsoleController(in, out);
    controller.playGame(board);
    assertEquals("", log.toString());
  }
  
  @Test
  public void testQuitCol() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("1 q");
    StringBuilder log = new StringBuilder();
    board = new MockTicTacToe(log, 123);
    controller = new TicTacToeConsoleController(in, out);
    controller.playGame(board);
    assertEquals("", log.toString());
  }
  
  @Test
  public void testFullGameDraw() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("1 3 2 2 2 3 3 3 1 1 1 2 3 2 2 1 3 1");
    StringBuilder log = new StringBuilder();
    board = new MockTicTacToe(log, 123);
    controller = new TicTacToeConsoleController(in, out);
    controller.playGame(board);
    assertEquals("Input: 1 3\nInput: 2 2\nInput: 2 3\nInput: 3 3\nInput: 1 "
         + "1\nInput: 1 2\nInput: 3 2\nInput: 2 1\nInput: 3 1\n", log.toString());
  }
  
  @Test
  public void testFullGameWinX() {
    StringBuffer out = new  StringBuffer();
    Reader in = new StringReader("1 3 2 2 2 3 3 2 3 3");
    StringBuilder log =  new StringBuilder();
    board = new MockTicTacToe(log, 123);
    controller = new TicTacToeConsoleController(in, out);
    controller.playGame(board);
    assertEquals("Input: 1 3\nInput: 2 2\nInput: 2 3\nInput: 3 2\nInput: 3 3\n", log.toString());
  }
  
  @Test
  public void testFullGameWinO() {
    StringBuffer out = new  StringBuffer();
    Reader in = new StringReader("1 1 2 2 1 2 2 1 3 1 2 3");
    StringBuilder log =  new StringBuilder();
    board = new MockTicTacToe(log, 123);
    controller = new TicTacToeConsoleController(in, out);
    controller.playGame(board);
    assertEquals("Input: 1 1\nInput: 2 2\nInput: 1 2\nInput: 2 1"
         + "\nInput: 3 1\nInput: 2 3\n", log.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidModel() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("1 2 q");
    
    TicTacToeController controller = new TicTacToeConsoleController(in, out);
    controller.playGame(null);
  }

  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    // Testing when something goes wrong with the Appendable
    // Here we are passing it a mock of an Appendable that always fails
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    Appendable gameLog = new FailingAppendable();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
  }
}
