package uptc.proyectofx.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import uptc.proyectofx.Employed.ProductsViewEmployed;
import uptc.proyectofx.view.LoginView;
import uptc.proyectofx.view.ProductsViewAdmin;

public class LoginController {
    private final LoginView loginView;
    private int failedAttempts = 0;
    private boolean isBlocked = false;
    private static final int MAX_ATTEMPTS = 3;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        initialize();
    }

    private void initialize() {
        Button loginButton = loginView.getLoginButton();
        TextField emailField = loginView.getEmailField();
        TextField passwordField = loginView.getPasswordField();

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (isBlocked) {
                    showAlert("Bloqueado", "Intenta nuevamente en unos momentos.");
                    return;
                }

                String email = emailField.getText();
                String password = passwordField.getText();

                if (isValidCredentials(email, password)) {
                    showAlert("Bienvenido", "Bienvenido de nuevo " + (email.endsWith("@admin") ? "Admin" : "Empleado"));
                    resetFailedAttempts();
                    if (email.endsWith("@admin")) {
                        showProductsViewAdmin();
                    } else if (email.endsWith("@emp")) {
                        showProductsViewEmployed();
                    }
                } else {
                    failedAttempts++;
                    if (failedAttempts >= MAX_ATTEMPTS) {
                        blockAccess();
                    } else {
                        showAlert("Error", "Verifica tus credenciales y vuelve a intentarlo. Intento " + failedAttempts + "/" + MAX_ATTEMPTS);
                    }
                }
            }
        });
    }

    private boolean isValidCredentials(String email, String password) {
        return (email.endsWith("@admin") && password.equals("Admin")) ||
                (email.endsWith("@emp") && password.equals("Empleado"));
    }

    private void showAlert(String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.show();

            Timeline closeAlertTimeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> alert.close()));
            closeAlertTimeline.setCycleCount(1);
            closeAlertTimeline.play();
        });
    }

    private void blockAccess() {
        isBlocked = true;
        showBlockedMessage();
        disableTextFields();
    }

    private void showBlockedMessage() {
        final int[] secondsLeft = {30};
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            secondsLeft[0]--;
            if (secondsLeft[0] <= 0) {
                isBlocked = false;
                enableTextFields();
                showAlert("Desbloqueado", "Puedes intentar nuevamente.");
            } else if (secondsLeft[0] % 10 == 0) {
                showAutoClosingAlert("Bloqueado", "Vuelve a intentarlo en " + secondsLeft[0] + " segundos.");
            }
        }));
        timeline.setCycleCount(secondsLeft[0]);
        timeline.play();
    }

    private void showAutoClosingAlert(String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.show();

            Timeline closeAlertTimeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> alert.close()));
            closeAlertTimeline.setCycleCount(1);
            closeAlertTimeline.play();
        });
    }

    private void resetFailedAttempts() {
        failedAttempts = 0;
    }

    private void disableTextFields() {
        loginView.getEmailField().setDisable(true);
        loginView.getPasswordField().setDisable(true);
    }

    private void enableTextFields() {
        loginView.getEmailField().setDisable(false);
        loginView.getPasswordField().setDisable(false);
    }

    private void showProductsViewAdmin() {
        Stage productStage = new Stage();

        // Crear la vista de productos y configurarla en el Stage
        ProductsViewAdmin productsViewAdmin = new ProductsViewAdmin();
        productsViewAdmin.setStage(productStage);

        productStage.show();
        loginView.getStage().close();
    }

    private void showProductsViewEmployed() {
        Stage productStage = new Stage();

        ProductsViewEmployed productsViewEmployed = new ProductsViewEmployed();

            productsViewEmployed.start(productStage);

        productStage.show();
        loginView.getStage().close();
    }
}
