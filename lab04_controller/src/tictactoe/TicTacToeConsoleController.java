package tictactoe;

import java.io.IOException;
import java.util.Scanner;

/**
 * This starter files is for students to implement a console controller for the
 * TicTacToe MVC assignment.
 */
public class TicTacToeConsoleController implements TicTacToeController {

  private final Appendable out;
  private final Scanner scan;

  /**
   * Constructor for the controller.
   * 
   * @param in  the source to read from
   * @param out the target to print to
   */
  public TicTacToeConsoleController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    this.scan = new Scanner(in);
  }
  


  @Override
  public void playGame(TicTacToe m) {
    if (m == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }
    try {
      out.append(m.toString()).append("\n");
      boolean prompt =  true;
      while (!m.isGameOver()) {
        if (prompt) {
          out.append("Enter a move for ").append(m.getTurn().toString()).append(":\n");
        }
        if (!scan.hasNext()) {
          return;
        }
        String rowInput = scan.next();
        if ("q".equals(rowInput)) {
          out.append("Game quit! Ending game state:\n").append(m.toString()).append("\n");
          return;
        }
        
        int row;
        try {
          row = Integer.parseInt(rowInput) - 1;
        } catch (NumberFormatException e) {
          out.append("Not a valid number: ").append(rowInput).append("\n");
          prompt = false;
          continue;
        }
        if (row + 1 < 0) {
          continue;
        }
        String colInput = scan.next();
        if ("q".equals(colInput)) {
          out.append("Game quit! Ending game state:\n").append(m.toString()).append("\n");
          return;
        }
        
        int col; 
        try {
          col = Integer.parseInt(colInput) - 1;
          m.move(row, col);
        } catch (NumberFormatException e) {
          out.append("Not a valid number: ").append(colInput).append("\n");
          prompt = false;
          continue;
        } catch (IllegalArgumentException e) {
          out.append("Not a valid move: ").append(rowInput).append(", ")
          .append(colInput).append("\n");
          prompt = false;
          continue;
        }
        prompt = true;
        
        out.append(m.toString()).append("\n");
        
        if (m.getWinner() != null) {
          out.append("Game is over! ").append(m.getWinner().toString()).append(" wins.\n");
          scan.close();
          return;
        } else if (m.isGameOver()) {
          out.append("Game is over! Tie game.\n");
          scan.close();
          return;
        }
      }
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
  }

}
