package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.Model;

import static java.lang.Math.random;

public class ControllerImpl implements ClassicMvcController {

  private final Model model;

  public ControllerImpl(Model model) {
    this.model = model;
  }

  @Override
  public void clickNextPuzzle() {
    model.resetPuzzle();
    if (model.getActivePuzzleIndex() == model.getPuzzleLibrarySize() - 1) {
      model.setActivePuzzleIndex(0);
    } else {
      model.setActivePuzzleIndex(model.getActivePuzzleIndex() + 1);
    }
  }

  @Override
  public void clickPrevPuzzle() {
    model.resetPuzzle();
    if (model.getActivePuzzleIndex() == 0) {
      model.setActivePuzzleIndex(model.getPuzzleLibrarySize() - 1);
    } else {
      model.setActivePuzzleIndex(model.getActivePuzzleIndex() - 1);
    }
  }

  @Override
  public void clickRandPuzzle() {
    model.resetPuzzle();
    int randInt = (int) Math.floor(random() * model.getPuzzleLibrarySize());
    while (randInt == model.getActivePuzzleIndex()) {
      randInt = (int) Math.floor(random() * model.getPuzzleLibrarySize());
    }
    model.setActivePuzzleIndex(randInt);
  }

  @Override
  public void clickResetPuzzle() {
    model.resetPuzzle();
  }

  @Override
  public void clickCell(int r, int c) {
    if (model.isLamp(r, c)) {
      model.removeLamp(r, c);
    } else {
      model.addLamp(r, c);
    }
  }
}
