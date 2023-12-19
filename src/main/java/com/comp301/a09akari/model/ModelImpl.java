package com.comp301.a09akari.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {

  private final PuzzleLibrary puzzleLibrary;
  private int activePuzzle;
  private final List<Point> lamps;
  private final List<ModelObserver> observers;

  public ModelImpl(PuzzleLibrary library) {
    puzzleLibrary = library;
    activePuzzle = 0;
    lamps = new ArrayList<>();
    observers = new ArrayList<>();
  }

  @Override
  public void addLamp(int r, int c) {
    if (r >= puzzleLibrary.getPuzzle(activePuzzle).getHeight()
        || c >= puzzleLibrary.getPuzzle(activePuzzle).getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (puzzleLibrary.getPuzzle(activePuzzle).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    Point location = new Point(c, r);
    if (!lamps.contains(location)) {
      lamps.add(location);
    }
    notifyObservers();
  }

  @Override
  public void removeLamp(int r, int c) {
    if (r >= puzzleLibrary.getPuzzle(activePuzzle).getHeight()
        || c >= puzzleLibrary.getPuzzle(activePuzzle).getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (puzzleLibrary.getPuzzle(activePuzzle).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    Point location = new Point(c, r);
    lamps.remove(location);
    notifyObservers();
  }

  @Override
  public boolean isLit(int r, int c) {
    if (r >= puzzleLibrary.getPuzzle(activePuzzle).getHeight()
        || c >= puzzleLibrary.getPuzzle(activePuzzle).getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (puzzleLibrary.getPuzzle(activePuzzle).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }

    if (puzzleLibrary.getPuzzle(activePuzzle).getCellType(r, c) != CellType.CORRIDOR) {
      return false;
    }

    boolean isLit = false;
    Point currentLocation = new Point(c, r);
    if (lamps.contains(currentLocation)) {
      return true;
    }

    boolean barrierHit = false;

    while (!barrierHit) { // look left
      if (currentLocation.x - 1 >= 0) {
        currentLocation.move(currentLocation.x - 1, currentLocation.y);
        if (lamps.contains(currentLocation)) {
          return true;
        }
        CellType currentType =
            puzzleLibrary.getPuzzle(activePuzzle).getCellType(currentLocation.y, currentLocation.x);
        if (currentType == CellType.CLUE || currentType == CellType.WALL) {
          barrierHit = true;
        }
      } else {
        barrierHit = true;
      }
    }

    currentLocation.move(c, r);
    barrierHit = false;

    while (!barrierHit) { // look right
      if (currentLocation.x + 1 < puzzleLibrary.getPuzzle(activePuzzle).getWidth()) {
        currentLocation.move(currentLocation.x + 1, currentLocation.y);

        if (lamps.contains(currentLocation)) {
          return true;
        }

        CellType currentType =
            puzzleLibrary.getPuzzle(activePuzzle).getCellType(currentLocation.y, currentLocation.x);
        if (currentType == CellType.CLUE || currentType == CellType.WALL) {
          barrierHit = true;
        }
      } else {
        barrierHit = true;
      }
    }

    currentLocation.move(c, r);
    barrierHit = false;

    while (!barrierHit) { // look down
      if (currentLocation.y + 1
          < puzzleLibrary.getPuzzle(activePuzzle).getHeight()) { // box to left is not OB
        currentLocation.move(currentLocation.x, currentLocation.y + 1);

        if (lamps.contains(currentLocation)) {
          return true;
        }

        CellType currentType =
            puzzleLibrary.getPuzzle(activePuzzle).getCellType(currentLocation.y, currentLocation.x);
        if (currentType == CellType.CLUE || currentType == CellType.WALL) {
          barrierHit = true;
        }
      } else {
        barrierHit = true;
      }
    }

    currentLocation.move(c, r);
    barrierHit = false;

    while (!barrierHit) { // look up
      if (currentLocation.y - 1 >= 0) { // box to left is not OB
        currentLocation.move(currentLocation.x, currentLocation.y - 1);

        if (lamps.contains(currentLocation)) {
          return true;
        }

        CellType currentType =
            puzzleLibrary.getPuzzle(activePuzzle).getCellType(currentLocation.y, currentLocation.x);
        if (currentType == CellType.CLUE || currentType == CellType.WALL) {
          barrierHit = true;
        }
      } else {
        barrierHit = true;
      }
    }
    return isLit;
  }

  @Override
  public boolean isLamp(int r, int c) {
    if (r >= puzzleLibrary.getPuzzle(activePuzzle).getHeight()
        || c >= puzzleLibrary.getPuzzle(activePuzzle).getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (puzzleLibrary.getPuzzle(activePuzzle).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    return lamps.contains(new Point(c, r));
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    Puzzle currentPuzzle = getActivePuzzle();

    if (r >= puzzleLibrary.getPuzzle(activePuzzle).getHeight()
        || c >= puzzleLibrary.getPuzzle(activePuzzle).getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (!lamps.contains(new Point(c, r))) {
      throw new IllegalArgumentException();
    }

    boolean barrierHit = false;
    Point currentLocation = new Point(c, r);

    while (!barrierHit) { // look left
      if (currentLocation.x > 0) {
        currentLocation.move(currentLocation.x - 1, currentLocation.y);

        if (lamps.contains(currentLocation)) {
          return true;
        }

        CellType currentType = currentPuzzle.getCellType(currentLocation.y, currentLocation.x);
        if (currentType == CellType.CLUE || currentType == CellType.WALL) {
          barrierHit = true;
        }
      } else {
        barrierHit = true;
      }
    }
    barrierHit = false;
    currentLocation.move(c, r);

    while (!barrierHit) { // look right
      if (currentLocation.x + 1 < currentPuzzle.getWidth()) {
        currentLocation.move(currentLocation.x + 1, currentLocation.y);

        if (lamps.contains(currentLocation)) {
          return true;
        }

        CellType currentType = currentPuzzle.getCellType(currentLocation.y, currentLocation.x);
        if (currentType == CellType.CLUE || currentType == CellType.WALL) {
          barrierHit = true;
        }
      } else {
        barrierHit = true;
      }
    }

    barrierHit = false;
    currentLocation.move(c, r);

    while (!barrierHit) { // look up
      if (currentLocation.y > 0) {
        currentLocation.move(currentLocation.x, currentLocation.y - 1);

        if (lamps.contains(currentLocation)) {
          return true;
        }

        CellType currentType = currentPuzzle.getCellType(currentLocation.y, currentLocation.x);
        if (currentType == CellType.CLUE || currentType == CellType.WALL) {
          barrierHit = true;
        }
      } else {
        barrierHit = true;
      }
    }

    barrierHit = false;
    currentLocation.move(c, r);

    while (!barrierHit) { // look down
      if (currentLocation.y + 1 < currentPuzzle.getHeight()) {
        currentLocation.move(currentLocation.x, currentLocation.y + 1);

        if (lamps.contains(currentLocation)) {
          return true;
        }

        CellType currentType = currentPuzzle.getCellType(currentLocation.y, currentLocation.x);
        if (currentType == CellType.CLUE || currentType == CellType.WALL) {
          barrierHit = true;
        }
      } else {
        barrierHit = true;
      }
    }
    return false;
  }

  @Override
  public Puzzle getActivePuzzle() {
    return puzzleLibrary.getPuzzle(activePuzzle);
  }

  @Override
  public int getActivePuzzleIndex() {
    return activePuzzle;
  }

  @Override
  public void setActivePuzzleIndex(int index) {
    if (index < 0 || index >= getPuzzleLibrarySize()) {
      throw new IndexOutOfBoundsException();
    }
    lamps.clear();
    activePuzzle = index;
    notifyObservers();
  }

  @Override
  public int getPuzzleLibrarySize() {
    return puzzleLibrary.size();
  }

  @Override
  public void resetPuzzle() {
    lamps.clear();
    notifyObservers();
  }

  @Override
  public boolean isSolved() {
    Puzzle currentPuzzle = puzzleLibrary.getPuzzle(activePuzzle);
    for (Point point : this.lamps) {
      if (isLampIllegal((int) point.getY(), (int) point.getX())) {
        return false;
      }
    }

    for (int i = 0; i < currentPuzzle.getHeight(); i++) {
      for (int j = 0; j < currentPuzzle.getWidth(); j++) {
        if (currentPuzzle.getCellType(i, j) == CellType.CORRIDOR) {
          if (!isLit(i, j)) {
            return false;
          }
        } else if (currentPuzzle.getCellType(i, j) == CellType.CLUE) {
          if (!isClueSatisfied(i, j)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    Puzzle currentPuzzle = puzzleLibrary.getPuzzle(activePuzzle);
    if (r >= currentPuzzle.getHeight() || c >= currentPuzzle.getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (currentPuzzle.getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }
    int requiredLamps = currentPuzzle.getClue(r, c);
    int lampCount = 0;

    Point currentLocation = new Point(c, r);
    if (c > 0) { // look left
      currentLocation.move(currentLocation.x - 1, currentLocation.y);
      if (lamps.contains(currentLocation)) {
        lampCount += 1;
      }
    }

    currentLocation.move(c, r);

    if (c + 1 < currentPuzzle.getWidth()) { // look right
      currentLocation.move(currentLocation.x + 1, currentLocation.y);
      if (lamps.contains(currentLocation)) {
        lampCount += 1;
      }
    }

    currentLocation.move(c, r);

    if (r > 0) { // look up
      currentLocation.move(currentLocation.x, currentLocation.y - 1);
      if (lamps.contains(currentLocation)) {
        lampCount += 1;
      }
    }

    currentLocation.move(c, r);

    if (r + 1 < currentPuzzle.getHeight()) { // look down
      currentLocation.move(currentLocation.x, currentLocation.y + 1);
      if (lamps.contains(currentLocation)) {
        lampCount += 1;
      }
    }

    return (lampCount == requiredLamps);
  }

  @Override
  public void addObserver(ModelObserver observer) {
    if (observer == null) {
      throw new IllegalArgumentException();
    }
    observers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    if (observer == null) {
      throw new IllegalArgumentException();
    }
    observers.remove(observer);
  }

  public void notifyObservers() {
    for (ModelObserver observer : observers) {
      observer.update(this);
    }
  }
}
