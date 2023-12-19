package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.ModelObserver;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View implements FXComponent, ModelObserver {

  private final Model model;
  private final ControllerImpl controller;
  private final Stage stage;

  public View(Model model, Stage stage, ControllerImpl controller) {
    this.model = model;
    this.stage = stage;
    this.controller = controller;
    model.addObserver(this);
  }

  @Override
  public Parent render() {
    VBox layout = new VBox();

    PuzzleView pv = new PuzzleView(model, controller);
    ControlView cv = new ControlView(model, controller);
    MessageView mv = new MessageView(model, controller);

    layout.getChildren().add(cv.render());
    layout.getChildren().add(pv.render());
    layout.getChildren().add(mv.render());

    return layout;
  }

  @Override
  public void update(Model model) {
    Scene scene = new Scene(render(), 800, 800);
    stage.setScene(scene);
  }
}
