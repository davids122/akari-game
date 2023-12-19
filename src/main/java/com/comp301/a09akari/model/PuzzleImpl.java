package com.comp301.a09akari.model;

public class PuzzleImpl implements Puzzle {

  private final int[][] board;

  public PuzzleImpl(int[][] board) {

    int[][] newBoard = new int[board.length][board[0].length];
    for (int i = 0; i < board.length; i++)
      System.arraycopy(board[i], 0, newBoard[i], 0, board[0].length);
    this.board = newBoard;
  }

  @Override
  public int getWidth() {
    return board[0].length;
  }

  @Override
  public int getHeight() {
    return board.length;
  }

  @Override
  public CellType getCellType(int r, int c) {
    if (r >= getHeight() || c >= getWidth()) {
      throw new IndexOutOfBoundsException();
    }

    CellType cellType = null;
    switch (board[r][c]) {
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
        cellType = CellType.CLUE;
        break;
      case 5:
        cellType = CellType.WALL;
        break;
      case 6:
        cellType = CellType.CORRIDOR;
        break;
    }
    return cellType;
  }

  @Override
  public int getClue(int r, int c) {
    if (r >= getHeight() || c >= getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }
    return board[r][c];
  }
}
