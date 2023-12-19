package com.comp301.a09akari.view;

import com.comp301.a09akari.SamplePuzzles;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.ModelImpl;
import com.comp301.a09akari.model.PuzzleImpl;
import com.comp301.a09akari.model.PuzzleLibraryImpl;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {

    PuzzleLibraryImpl puzzleLibrary = new PuzzleLibraryImpl();
    PuzzleImpl puzzle01 = new PuzzleImpl(SamplePuzzles.PUZZLE_01);
    PuzzleImpl puzzle02 = new PuzzleImpl(SamplePuzzles.PUZZLE_02);
    PuzzleImpl puzzle03 = new PuzzleImpl(SamplePuzzles.PUZZLE_03);
    PuzzleImpl puzzle04 = new PuzzleImpl(SamplePuzzles.PUZZLE_04);
    PuzzleImpl puzzle05 = new PuzzleImpl(SamplePuzzles.PUZZLE_05);

    puzzleLibrary.addPuzzle(puzzle01);
    puzzleLibrary.addPuzzle(puzzle02);
    puzzleLibrary.addPuzzle(puzzle03);
    puzzleLibrary.addPuzzle(puzzle04);
    puzzleLibrary.addPuzzle(puzzle05);

    ModelImpl model = new ModelImpl(puzzleLibrary);
    model.setActivePuzzleIndex(0);
    ControllerImpl controller = new ControllerImpl(model);

    View view = new View(model, stage, controller);

    view.update(model);

    stage.show();
  }
}
