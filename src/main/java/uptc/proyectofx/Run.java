package uptc.proyectofx;

import javafx.application.Application;
import javafx.stage.Stage;
import uptc.proyectofx.controller.LoginController;
import uptc.proyectofx.view.LoginView;

public class Run extends Application {
    @Override
    public void start(Stage primaryStage) {
        LoginView loginView = new LoginView(primaryStage);
        new LoginController(loginView);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
