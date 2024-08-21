package uptc.proyectofx.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {
    private final Stage stage;
    private Button loginButton;
    private TextField emailField;
    private PasswordField passwordField;

    public LoginView(Stage stage) {
        this.stage = stage;
        initialize();
    }

    private void initialize() {
        VBox leftBox = new VBox();
        leftBox.getStyleClass().add("left-pane");
        leftBox.setMinWidth(400);

        String logoPath = "/images/logo.png";
        ImageView logoView = new ImageView(new Image(getClass().getResourceAsStream(logoPath)));
        logoView.setFitWidth(200);
        logoView.setPreserveRatio(true);
        logoView.getStyleClass().add("logo");

        Label tiendaLabel = new Label("La Esquinita");
        tiendaLabel.setId("store-title");

        leftBox.getChildren().addAll(logoView, tiendaLabel);

        VBox rightBox = new VBox();
        rightBox.getStyleClass().add("right-pane");
        rightBox.setMinWidth(400);

        Label loginTitle = new Label("Iniciar Sesión");
        loginTitle.setId("login-title");
        rightBox.getChildren().add(loginTitle);

        emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.getStyleClass().add("text-field");
        emailField.setMaxWidth(300);
        rightBox.getChildren().add(emailField);

        passwordField = new PasswordField();
        passwordField.setPromptText("Contraseña");
        passwordField.getStyleClass().add("text-field");
        passwordField.setMaxWidth(300);
        rightBox.getChildren().add(passwordField);

        loginButton = new Button("Iniciar sesión");
        loginButton.getStyleClass().add("button");
        rightBox.getChildren().add(loginButton);

        HBox mainBox = new HBox(leftBox, rightBox);

        Scene scene = new Scene(mainBox, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Login - Tienda La Esquinita");
        stage.show();
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public TextField getEmailField() {
        return emailField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public Stage getStage() {
        return stage;
    }
}
