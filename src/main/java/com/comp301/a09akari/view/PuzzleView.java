package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.Puzzle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class PuzzleView implements FXComponent {

  private final ControllerImpl controller;
  private final Model model;

  public PuzzleView(Model model, ControllerImpl controller) {
    this.model = model;
    this.controller = controller;
  }

  @Override
  public Parent render() {

    GridPane grid = new GridPane();

    Puzzle puzzle = model.getActivePuzzle();

    grid.setPadding(new Insets(20, 0, 20, 50));

    for (int r = 0; r < puzzle.getHeight(); r++) {
      for (int c = 0; c < puzzle.getWidth(); c++) {
        if (puzzle.getCellType(r, c) == CellType.CORRIDOR) {
          StackPane stackPane = new StackPane();
          stackPane.setMaxWidth(40);
          stackPane.setMaxHeight(40);
          stackPane.setPrefWidth(40);
          stackPane.setPrefHeight(40);
          javafx.scene.control.Button button = new javafx.scene.control.Button();
          button.setPrefHeight(40);
          button.setPrefWidth(40);
          button.setMinHeight(30);
          button.setMinWidth(30);
          int finalR = r;
          int finalC = c;
          button.setOnAction(
              new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                  controller.clickCell(finalR, finalC);
                }
              });
          stackPane.getChildren().add(button);

          if (model.isLamp(r, c)) {
            if (model.isLampIllegal(r, c)) {
              javafx.scene.image.Image image = new Image("light-bulb2.png");
              ImageView imageView = new ImageView(image);
              imageView.setFitHeight(30);
              imageView.setFitWidth(30);
              imageView.setPreserveRatio(true);
              button.setGraphic(imageView);
            } else {
              javafx.scene.image.Image image = new Image("light-bulb.png");
              ImageView imageView = new ImageView(image);
              imageView.setFitHeight(30);
              imageView.setFitWidth(30);
              imageView.setPreserveRatio(true);
              button.setGraphic(imageView);
              stackPane.setStyle("-fx-background-color: #B8860B;");
            }
          } else if (model.isLit(r, c)) {
            button.setStyle("-fx-background-color: #B8860B;");
          }

          grid.add(stackPane, c, r);
        } else if (puzzle.getCellType(r, c) == CellType.CLUE) {
          StackPane stackPane = new StackPane();
          Color color = Color.BLACK;
          Label label = new Label(((Integer) puzzle.getClue(r, c)).toString());
          Color color2 = Color.WHITE;
          Color color3 = Color.LIGHTGREEN;
          if (model.isClueSatisfied(r, c)) {
            label.setTextFill(color3);
          } else {
            label.setTextFill(color2);
          }
          label.setFont(new Font("Arial", 20));
          stackPane.getChildren().addAll(new Rectangle(40, 40, color), label);
          grid.add(stackPane, c, r);
        } else {
          Color color = Color.BLACK;
          grid.add(new Rectangle(40, 40, color), c, r);
        }
      }
    }
    grid.setStyle("-fx-background-color: white; -fx-grid-lines-visible: true");

    return grid;
  }
}
