package uptc.proyectofx.Employed;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import uptc.proyectofx.controller.LoginController;
import uptc.proyectofx.view.LoginView;

import java.util.Objects;

public class ProductsViewEmployed extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tienda La Esquinita - Productos");

        BorderPane root = new BorderPane();

        // Crear y añadir el panel izquierdo
        VBox leftPanel = createLeftPanel(primaryStage);
        root.setLeft(leftPanel);

        // Crear y añadir el panel derecho
        VBox rightPanel = createRightPanel();
        root.setCenter(rightPanel);

        Scene scene = new Scene(root, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createLeftPanel(Stage primaryStage) {
        VBox leftPanel = new VBox();
        leftPanel.setPadding(new Insets(20));
        leftPanel.setSpacing(20);
        leftPanel.setStyle("-fx-background-color: #F0F4F8;");
        leftPanel.setPrefWidth(200);

        // Imagen de usuario
        Image userImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imgInc.png")));
        ImageView userImageView = new ImageView(userImage);
        userImageView.setFitHeight(30);
        userImageView.setFitWidth(30);

        HBox userBox = new HBox(10, userImageView, createLabel("Users\nexample@demo.com", 16));
        userBox.setAlignment(Pos.CENTER_LEFT);

        // Imagen de inicio
        Image inicioImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("home.png")));
        ImageView inicioImageView = new ImageView(inicioImage);
        inicioImageView.setFitHeight(30);
        inicioImageView.setFitWidth(30);

        Label inicioLabel = createLabel("Inicio", 14);
        HBox inicioBox = new HBox(10, inicioImageView, inicioLabel);
        inicioBox.setAlignment(Pos.CENTER_LEFT);

        inicioLabel.setOnMouseEntered(event -> inicioLabel.setTextFill(Color.YELLOW));
        inicioLabel.setOnMouseExited(event -> inicioLabel.setTextFill(Color.BLACK));

        // Imagen de registrar venta
        Image saleImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("invoices.png")));
        ImageView saleImageView = new ImageView(saleImage);
        saleImageView.setFitHeight(30);
        saleImageView.setFitWidth(30);

        //Imagen de Contacto
        Image contactImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("contact.png")));
        ImageView contactImageView = new ImageView(contactImage);
        contactImageView.setFitHeight(30);
        contactImageView.setFitWidth(30);

        Label Contacto = createLabel("Contacto", 14);

        HBox conBox = new HBox(10, contactImageView,Contacto);
        conBox.setAlignment(Pos.CENTER_LEFT);
        Contacto.setOnMouseEntered(event -> Contacto.setTextFill(Color.YELLOW));
        Contacto.setOnMouseExited(event -> Contacto.setTextFill(Color.BLACK));

        Label vacio1 = createLabel("", 14);
        Label vacio2 = createLabel("", 14);
        Label vacio3 = createLabel("", 14);
        Label vacio4 = createLabel("", 14);
        Label vacio5 = createLabel("", 14);
        Label vacio6 = createLabel("", 14);
        Label vacio7 = createLabel("", 14);

        Label registerSaleLabel = createLabel("Registrar Venta", 14);
        HBox saleBox = new HBox(10, saleImageView, registerSaleLabel);
        saleBox.setAlignment(Pos.CENTER_LEFT);

        registerSaleLabel.setOnMouseEntered(event -> registerSaleLabel.setTextFill(Color.YELLOW));
        registerSaleLabel.setOnMouseExited(event -> registerSaleLabel.setTextFill(Color.BLACK));

        registerSaleLabel.setOnMouseClicked(event -> {
            // Cambiar a la ventana ProductsVendEmployed
            ProductsVendEmployed productsVendEmployed = new ProductsVendEmployed();
            try {
                productsVendEmployed.start(new Stage());
                primaryStage.close(); // Cerrar la ventana actual
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        // Imagen de logout
        Image logoutImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("logout.png")));
        ImageView logoutImageView = new ImageView(logoutImage);
        logoutImageView.setFitHeight(30);
        logoutImageView.setFitWidth(30);

        Label logoutLabel = createLabel("Logout", 14);
        HBox logoutBox = new HBox(10, logoutImageView, logoutLabel);
        logoutBox.setAlignment(Pos.CENTER_LEFT);

        logoutLabel.setOnMouseEntered(event -> logoutLabel.setTextFill(Color.YELLOW));
        logoutLabel.setOnMouseExited(event -> logoutLabel.setTextFill(Color.BLACK));
        logoutLabel.setOnMouseClicked(event -> {

            logoutBox.setOnMouseClicked(event1 -> handleLogoutButton(primaryStage));

        });

        leftPanel.getChildren().addAll(userBox, inicioBox, saleBox,conBox,vacio1,vacio2,vacio3,vacio4,vacio5,vacio6,vacio7, logoutBox);
        return leftPanel;

    }
    private void handleLogoutButton(Stage primaryStage) {
        // Cerrar la ventana actual
        primaryStage.close();

        // Mostrar la ventana de inicio de sesión
        Stage loginStage = new Stage();
        LoginView loginView = new LoginView(loginStage);
        new LoginController(loginView);
        loginStage.show();
    }



    private Label createLabel(String text, int fontSize) {
        Label label = new Label(text);
        label.setTextFill(Color.BLACK);
        label.setFont(Font.font("Segoe UI", fontSize));
        label.setWrapText(true);
        return label;
    }

    private VBox createRightPanel() {
        VBox rightPanel = new VBox();
        rightPanel.setPadding(new Insets(20));
        rightPanel.setSpacing(20);
        rightPanel.setStyle("-fx-background-color: #FFB24B;");

        Label titleLabel = new Label("PRODUCTOS MAS VENDIDOS");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.BLACK);

        HBox topPanel = new HBox(titleLabel);
        topPanel.setAlignment(Pos.CENTER);
        topPanel.setPadding(new Insets(10));

        GridPane productGrid = new GridPane();
        productGrid.setPadding(new Insets(10));
        productGrid.setHgap(20);
        productGrid.setVgap(20);
        productGrid.setAlignment(Pos.CENTER);

        addProductLabel(productGrid, "Poker", "poker.png", 0, 0);
        addProductLabel(productGrid, "Aguila", "aguila.png", 0, 1);
        addProductLabel(productGrid, "Onix", "onix.png", 1, 0);
        addProductLabel(productGrid, "Lider", "lider.png", 1, 1);

        rightPanel.getChildren().addAll(topPanel, productGrid);
        return rightPanel;
    }

    private void addProductLabel(GridPane productGrid, String text, String imagePath, int row, int col) {
        try {
            Image image = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(imagePath)));
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(150);
            imageView.setFitWidth(150);

            Label label = new Label(text, imageView);
            label.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            label.setContentDisplay(javafx.scene.control.ContentDisplay.TOP);
            label.setAlignment(Pos.CENTER);

            VBox productBox = new VBox();
            productBox.setAlignment(Pos.CENTER);
            productBox.getChildren().add(label);

            productGrid.add(productBox, col, row);
        } catch (Exception e) {
            System.err.println("Error loading image: " + imagePath);
            e.printStackTrace();
        }
    }
}
