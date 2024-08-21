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

import java.util.Objects;

public class ProductsVendEmployed extends Application {

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
        VBox rightPanel = createRightPanel(primaryStage);
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

        HBox userBox = new HBox(10, userImageView, createLabel("Users\nexample@demo.com"));
        userBox.setAlignment(Pos.CENTER_LEFT);

        // Imagen de inicio
        Image inicioImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("home.png")));
        ImageView inicioImageView = new ImageView(inicioImage);
        inicioImageView.setFitHeight(30);
        inicioImageView.setFitWidth(30);

        Label inicioLabel = createLabel("Inicio");
        HBox inicioBox = new HBox(10, inicioImageView, inicioLabel);
        inicioBox.setAlignment(Pos.CENTER_LEFT);

        inicioLabel.setOnMouseEntered(event -> inicioLabel.setTextFill(Color.YELLOW));
        inicioLabel.setOnMouseExited(event -> inicioLabel.setTextFill(Color.BLACK));
        inicioLabel.setOnMouseClicked(event -> {
            new ProductsViewEmployed().start(primaryStage);
        });

        // Imagen de registrar venta
        Image saleImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("invoices.png")));
        ImageView saleImageView = new ImageView(saleImage);
        saleImageView.setFitHeight(30);
        saleImageView.setFitWidth(30);

        Label registerSaleLabel = createLabel("Registrar Venta");
        HBox saleBox = new HBox(10, saleImageView, registerSaleLabel);
        saleBox.setAlignment(Pos.CENTER_LEFT);

        registerSaleLabel.setOnMouseEntered(event -> registerSaleLabel.setTextFill(Color.YELLOW));
        registerSaleLabel.setOnMouseExited(event -> registerSaleLabel.setTextFill(Color.BLACK));

        Label vacio = createLabel("");
        saleBox.setAlignment(Pos.CENTER_LEFT);
        Label vacio2 = createLabel("");
        saleBox.setAlignment(Pos.CENTER_LEFT);
        Label vacio3 = createLabel("");
        saleBox.setAlignment(Pos.CENTER_LEFT);
        Label vacio4 = createLabel("");
        saleBox.setAlignment(Pos.CENTER_LEFT);
        Label vacio5 = createLabel("");
        saleBox.setAlignment(Pos.CENTER_LEFT);
        Label vacio6 = createLabel("");
        saleBox.setAlignment(Pos.CENTER_LEFT);
        Label vacio7 = createLabel("");
        saleBox.setAlignment(Pos.CENTER_LEFT);
        Label vacio8 = createLabel("");
        saleBox.setAlignment(Pos.CENTER_LEFT);
        // Imagen de volver atrás
        Image backImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("logout.png")));
        ImageView backImageView = new ImageView(backImage);
        backImageView.setFitHeight(30);
        backImageView.setFitWidth(30);

        Label backLabel = createLabel("Atrás");
        HBox backBox = new HBox(10, backImageView, backLabel);
        backBox.setAlignment(Pos.CENTER_LEFT);

        backLabel.setOnMouseEntered(event -> backLabel.setTextFill(Color.YELLOW));
        backLabel.setOnMouseExited(event -> backLabel.setTextFill(Color.BLACK));
        backLabel.setOnMouseClicked(event -> {
            new ProductsViewEmployed().start(primaryStage);
        });

        leftPanel.getChildren().addAll(userBox, inicioBox, saleBox, vacio, vacio2, vacio3, vacio4, vacio5, vacio6, vacio7, vacio8, backBox);
        return leftPanel;
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setTextFill(Color.BLACK);
        label.setFont(Font.font("Segoe UI", 16));
        label.setWrapText(true);
        return label;
    }

    private VBox createRightPanel(Stage primaryStage) {
        VBox rightPanel = new VBox();
        rightPanel.setPadding(new Insets(20));
        rightPanel.setSpacing(20);
        rightPanel.setStyle("-fx-background-color: #FFB24B;");

        Label titleLabel = new Label("Registrar Ventas");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.BLACK);

        HBox topPanel = new HBox(titleLabel);
        topPanel.setAlignment(Pos.CENTER);
        topPanel.setPadding(new Insets(10));

        GridPane productGrid = new GridPane();
        productGrid.setPadding(new Insets(10));
        productGrid.setHgap(40);
        productGrid.setVgap(40);
        productGrid.setAlignment(Pos.CENTER);

        addProductLabel(productGrid, "Aseo", "aseo.png", 0, 0, primaryStage);
        addProductLabel(productGrid, "Bebidas Azucaradas", "bebidas_azucaradas.png", 0, 1, primaryStage);
        addProductLabel(productGrid, "Bebidas Alcoholicas", "bebidas_alcoholicas.png", 0, 2, primaryStage);
        addProductLabel(productGrid, "Lacteos", "lacteos.png", 1, 0, primaryStage);
        addProductLabel(productGrid, "Snacks", "snacks.png", 1, 1, primaryStage);
        addProductLabel(productGrid, "Viveres", "viveres.png", 1, 2, primaryStage);

        rightPanel.getChildren().addAll(topPanel, productGrid);
        return rightPanel;
    }

    private void addProductLabel(GridPane productGrid, String text, String imagePath, int row, int col, Stage primaryStage) {
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

            // Añadir eventos para cambiar de ventana al hacer clic en las categorías
            if ("Aseo".equals(text)) {
                productBox.setOnMouseClicked(event -> {
                    new AseoView().start(primaryStage);
                });
            } else if ("Bebidas Azucaradas".equals(text)) {
                productBox.setOnMouseClicked(event -> {
                    new BebidasAzucaradasView().start(primaryStage);
                });
            } else if ("Bebidas Alcoholicas".equals(text)) {
                productBox.setOnMouseClicked(event -> {
                    new BebidasAlcoholicasView().start(primaryStage);
                });
            }else if ("Lacteos".equals(text)) {
                productBox.setOnMouseClicked(event -> {
                    new LacteosView().start(primaryStage);
                });
            }
            else if ("Snacks".equals(text)) {
                productBox.setOnMouseClicked(event -> {
                    new SnacksView().start(primaryStage);
                });
            }else if ("Viveres".equals(text)) {
                productBox.setOnMouseClicked(event -> {
                    new ViveresView().start(primaryStage);
                });
            }

        } catch (Exception e) {
            System.err.println("Error loading image: " + imagePath);
            e.printStackTrace();
        }
    }
}
