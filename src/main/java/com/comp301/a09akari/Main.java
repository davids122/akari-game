package com.comp301.a09akari;

import com.comp301.a09akari.view.AppLauncher;
import javafx.application.Application;

public class Main {
  public static void main(String[] args) {

    /*


    int[][] board = new int[1][1];
    board[0][0] = 6;

    PuzzleImpl puzzle0 = new PuzzleImpl(board);

    PuzzleLibraryImpl puzzleLibrary = new PuzzleLibraryImpl();
    puzzleLibrary.addPuzzle(puzzle0);

    System.out.println("Puzzle 1 tests: ");
    ModelImpl model = new ModelImpl(puzzleLibrary);
    System.out.println("Expected output: False");
    System.out.println("Actual: " + model.isSolved());

    model.addLamp(0, 0);

    System.out.println("Expected output: True");
    System.out.println("Actual: " + model.isSolved());


    //PUZZLE 1

    int[][] board2 = new int[3][3];
    board2[0][0] = 6;
    board2[0][1] = 6;
    board2[0][2] = 5;
    board2[1][0] = 5;
    board2[1][1] = 3;
    board2[1][2] = 6;
    board2[2][0] = 6;
    board2[2][1] = 6;
    board2[2][2] = 6;

    PuzzleImpl puzzle1 = new PuzzleImpl(board2);

    puzzleLibrary.addPuzzle(puzzle1);
    model.setActivePuzzleIndex(1);
    model.resetPuzzle();

    System.out.println();
    System.out.println("Model 2 Tests:");
    System.out.println("Expected: false");
    System.out.println("Actual: " + model.isSolved());

    model.addLamp(0, 1);

    System.out.println("Expected: false");
    System.out.println("Actual: " + model.isSolved());

    model.addLamp(1, 2);

    System.out.println("Expected: false");
    System.out.println("Actual: " + model.isSolved());

    model.addLamp(2, 1);

    System.out.println("Expected: true");
    System.out.println("Actual: " + model.isSolved());

    model.addLamp(0, 0);

    System.out.println("Expected: false");
    System.out.println("Actual: " + model.isSolved());


    //PUZZLE 2 (5X5)

    int[][] board3 = new int[5][5];
    for(int i = 0; i < 5; i++){
      for(int j = 0; j < 5; j++){
        board3[i][j] = 6;
      }
    }
    board3[1][1] = 5;
    board3[1][3] = 0;
    board3[3][1] = 3;
    board3[3][3] = 3;

    PuzzleImpl puzzle2 = new PuzzleImpl(board3);
    puzzleLibrary.addPuzzle(puzzle2);
    model.setActivePuzzleIndex(2);
    model.resetPuzzle();

    System.out.println();
    System.out.println("Puzzle2 TESTS: ");

    model.addLamp(0, 1);

    System.out.println("Expected: false");
    System.out.println("Actual: " + model.isSolved());

    model.addLamp(3, 0);
    model.addLamp(2, 1);

    System.out.println("Expected: false");
    System.out.println("Actual: " + model.isSolved());
    System.out.println(model.getActivePuzzle().getCellType(3, 2));

    model.addLamp(3, 2);
    model.addLamp(3, 4);

    System.out.println("Expected: false");
    System.out.println("Actual: " + model.isSolved());

    model.addLamp(4, 4);

    System.out.println("Expected: false");
    System.out.println("Actual: " + model.isSolved());

    model.addLamp(4, 3);

    System.out.println("Expected: false");
    System.out.println("Actual: " + model.isSolved());

    model.removeLamp(4, 4);

    System.out.println("Expected: true");
    System.out.println("Actual: " + model.isSolved());

    */

    Application.launch(AppLauncher.class);
  }
}
