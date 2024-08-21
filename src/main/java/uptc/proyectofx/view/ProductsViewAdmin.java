package uptc.proyectofx.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import uptc.proyectofx.controller.LoginController;

import java.net.URL;

public class ProductsViewAdmin extends Application {
    private Stage stage;
    private GridPane rightPanel;
    private boolean initialized = false;

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        initialize();
    }

    private void initialize() {
        if (initialized) return; // Evitar múltiples inicializaciones
        initialized = true;

        // Panel izquierdo con botones e iconos
        VBox leftPanel = new VBox(20);
        leftPanel.getStyleClass().add("left-pane");
        leftPanel.setPadding(new Insets(20, 10, 20, 10));
        leftPanel.setAlignment(Pos.TOP_CENTER);

        Button inicioButton = createButtonWithIcon("Inicio", "/images/Icons/home.png");
        Button productosButton = createButtonWithIcon("Productos", "/images/Icons/products.png");
        Button facturasButton = createButtonWithIcon("Historial", "/images/Icons/invoices.png");
        Button contactoButton = createButtonWithIcon("Contacto", "/images/Icons/contact.png");
        Button logoutButton = createButtonWithIcon("Logout", "/images/Icons/logout.png");

        // Agregar funcionalidad a los botones
        inicioButton.setOnAction(event -> handleInicioButton());
        productosButton.setOnAction(event -> handleProductosButton());
        facturasButton.setOnAction(event -> handleFacturasButton());
        contactoButton.setOnAction(event -> handleContactoButton());
        logoutButton.setOnAction(event -> handleLogoutButton());

        leftPanel.getChildren().addAll(
                createCenteredButton(inicioButton),
                createCenteredButton(productosButton),
                createCenteredButton(facturasButton),
                createCenteredButton(contactoButton),
                createCenteredButton(logoutButton)
        );

        // Panel derecho con título e imágenes de productos
        rightPanel = new GridPane();
        rightPanel.getStyleClass().add("right-pane");
        rightPanel.setAlignment(Pos.CENTER);
        rightPanel.setPadding(new Insets(10));
        rightPanel.setHgap(50);
        rightPanel.setVgap(50);

        Label titleLabel = new Label("Productos Top");
        titleLabel.getStyleClass().add("title-label");

        VBox rightPanelContainer = new VBox(10, titleLabel, rightPanel);
        rightPanelContainer.setAlignment(Pos.TOP_CENTER);

        HBox rightPanelWrapper = new HBox(rightPanelContainer);
        rightPanelWrapper.setAlignment(Pos.CENTER);

        // Simulación de productos más vendidos
        addProduct("Poker", "/images/Bebidas_Al/poker.png", 0, 0);
        addProduct("Aguila", "/images/Bebidas_Al/aguila.png", 0, 1);
        addProduct("Onix", "/images/Bebidas_Al/onix.png", 1, 0);
        addProduct("Lider", "/images/Bebidas_Al/lider.png", 1, 1);

        // Layout principal
        HBox mainLayout = new HBox(10);
        mainLayout.getChildren().addAll(leftPanel, rightPanelWrapper);

        Scene scene = new Scene(mainLayout, 800, 600);

        // Verificar y agregar la hoja de estilos CSS
        URL cssUrl = getClass().getResource("/css/style.css");
        if (cssUrl == null) {
            System.out.println("Archivo CSS no encontrado");
        } else {
            System.out.println("Archivo CSS encontrado: " + cssUrl.toExternalForm());
            scene.getStylesheets().add(cssUrl.toExternalForm());
        }

        stage.setScene(scene);
        stage.setTitle("Products View Admin");
        stage.show();
    }

    private Button createButtonWithIcon(String text, String iconPath) {
        Button button = new Button(text);
        button.getStyleClass().add("button");
        button.setPrefWidth(200);
        button.setPrefHeight(40);

        ImageView icon = new ImageView(new Image(getClass().getResourceAsStream(iconPath)));
        icon.setFitWidth(20);
        icon.setFitHeight(20);
        button.setGraphic(icon);
        button.setContentDisplay(ContentDisplay.LEFT);

        return button;
    }

    private HBox createCenteredButton(Button button) {
        HBox container = new HBox(button);
        container.setAlignment(Pos.CENTER);
        return container;
    }

    private void addProduct(String productName, String imagePath, int row, int col) {
        Label label = new Label(productName);
        label.getStyleClass().add("product-label");

        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(imagePath)));
        imageView.getStyleClass().add("image-view");
        imageView.setFitWidth(220);
        imageView.setFitHeight(175);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        VBox productBox = new VBox(5, imageView, label);
        productBox.getStyleClass().add("product-box");
        productBox.setAlignment(Pos.CENTER);

        productBox.setOnMouseClicked(event -> showProductInfo(productName));

        rightPanel.add(productBox, col, row);
    }

    private void showProductInfo(String productName) {
        Stage dialog = new Stage();
        dialog.initOwner(stage);

        VBox dialogVBox = new VBox(20);
        dialogVBox.setPadding(new Insets(20));
        dialogVBox.setAlignment(Pos.CENTER);

        Label productLabel = new Label("Producto: " + productName);
        productLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label distributorLabel = new Label("Distribuidor: Bavaria");
        distributorLabel.setStyle("-fx-font-size: 14px;");

        Label contactLabel = new Label("Contacto: +57 123456789");
        contactLabel.setStyle("-fx-font-size: 14px;");

        dialogVBox.getChildren().addAll(productLabel, distributorLabel, contactLabel);

        Scene dialogScene = new Scene(dialogVBox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.setTitle("Información del Producto");
        dialog.showAndWait();
    }

    private void handleInicioButton() {
        // TODO: Agregar funcionalidad para el botón Inicio
        Stage productosStage = new Stage();
        ProductsViewAdmin productsViewAdmin = new ProductsViewAdmin();
        productsViewAdmin.start(productosStage);
        stage.close(); // Cerrar la ventana actual de historial
    }

    private void handleProductosButton() {
        // Cargar la nueva vista CategoriasView
        CategoriasView categoriasView = new CategoriasView();
        categoriasView.start(stage); // Aquí llamamos a 'show' en lugar de 'start'
    }

    private void handleFacturasButton() {
        // Crear la vista del historial y configurarla en el Stage
        HistorialView historialView = new HistorialView();
        Stage historialStage = new Stage();
        historialView.start(historialStage);

        // Cerrar la ventana actual de productos
        stage.close();
    }

    private void handleContactoButton() {
        // TODO: Agregar funcionalidad para el botón Contacto
        ContactoView contactoView = new ContactoView();
        Stage contactoStage = new Stage();
        contactoView.start(contactoStage);

        // Cerrar la ventana actual de productos
        stage.close();
    }

    private void handleLogoutButton() {
        Stage loginStage = new Stage();

        // Crear la vista de login y configurarla en el Stage
        LoginView loginView = new LoginView(loginStage);
        new LoginController(loginView);

        // Mostrar la ventana de login
        loginStage.show();

        // Cerrar la ventana actual de productos
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        initialize();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
