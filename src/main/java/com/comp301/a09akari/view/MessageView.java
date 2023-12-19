package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.Model;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MessageView implements FXComponent {

  private final ControllerImpl controller;
  private final Model model;

  public MessageView(Model model, ControllerImpl controller) {
    this.controller = controller;
    this.model = model;
  }

  @Override
  public Parent render() {

    HBox hbox = new HBox(20);

    hbox.setPrefWidth(500);
    hbox.setPrefHeight(150);

    Label successLabel = new Label("Success!");

    successLabel.setPrefHeight(100);
    successLabel.setPrefWidth(180);
    successLabel.setTextFill(Color.GREEN);
    successLabel.setCenterShape(true);
    Font font = new Font("Arial", 20);
    successLabel.setFont(font);

    successLabel.setPadding(new Insets(20, 20, 20, 20));
    successLabel.setWrapText(true);

    Label puzzleIndex =
        new Label(
            "Puzzle "
                + (model.getActivePuzzleIndex() + 1)
                + " out of "
                + model.getPuzzleLibrarySize());

    puzzleIndex.setPrefWidth(150);
    puzzleIndex.setPrefHeight(100);

    puzzleIndex.setFont(font);

    puzzleIndex.setPadding(new Insets(20, 20, 20, 20));

    puzzleIndex.setWrapText(true);

    hbox.getChildren().add(puzzleIndex);

    if (model.isSolved()) {
      hbox.getChildren().add(successLabel);
    }

    return hbox;
  }
}
