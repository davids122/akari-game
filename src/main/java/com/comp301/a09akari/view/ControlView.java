package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.Puzzle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class ControlView implements FXComponent {
  private final Model model;
  private final ControllerImpl controller;

  public ControlView(Model model, ControllerImpl controller) {
    this.model = model;
    this.controller = controller;
  }

  @Override
  public Parent render() {

    HBox hBox = new HBox(20);
    Puzzle puzzle = model.getActivePuzzle();

    hBox.setPadding(new Insets(25, 25, 25, 25));
    hBox.setStyle("-fx-background-color: #FFFFFF");

    hBox.setPrefWidth(500);
    hBox.setPrefHeight(150);

    Button nextPuzzleButton = new Button("Next Puzzle");
    Button prevPuzzleButton = new Button("Prev. Puzzle");
    Button randomPuzzleButton = new Button("Random Puzzle");
    Button resetPuzzleButton = new Button("Reset Puzzle");

    nextPuzzleButton.setWrapText(true);
    prevPuzzleButton.setWrapText(true);
    randomPuzzleButton.setWrapText(true);
    resetPuzzleButton.setWrapText(true);

    nextPuzzleButton.setFont(new Font("Arial", 15));
    prevPuzzleButton.setFont(new Font("Arial", 15));
    randomPuzzleButton.setFont(new Font("Arial", 15));
    resetPuzzleButton.setFont(new Font("Arial", 15));

    nextPuzzleButton.setPrefWidth(80);
    prevPuzzleButton.setPrefWidth(80);
    randomPuzzleButton.setPrefWidth(80);
    resetPuzzleButton.setPrefWidth(80);

    nextPuzzleButton.setPrefHeight(80);
    prevPuzzleButton.setPrefHeight(80);
    randomPuzzleButton.setPrefHeight(80);
    resetPuzzleButton.setPrefHeight(80);

    nextPuzzleButton.setStyle("-fx-background-color: #F8AE2E; ");
    prevPuzzleButton.setStyle("-fx-background-color: #F8AE2E; ");
    randomPuzzleButton.setStyle("-fx-background-color: #F8AE2E; ");
    resetPuzzleButton.setStyle("-fx-background-color: #F8AE2E; ");

    nextPuzzleButton.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent actionEvent) {
            controller.clickNextPuzzle();
          }
        });

    prevPuzzleButton.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent actionEvent) {
            controller.clickPrevPuzzle();
          }
        });

    randomPuzzleButton.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent actionEvent) {
            controller.clickRandPuzzle();
          }
        });

    resetPuzzleButton.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent actionEvent) {
            controller.clickResetPuzzle();
          }
        });

    hBox.getChildren()
        .addAll(prevPuzzleButton, nextPuzzleButton, resetPuzzleButton, randomPuzzleButton);

    return hBox;
  }
}
