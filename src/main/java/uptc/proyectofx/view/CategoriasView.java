package uptc.proyectofx.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;

public class CategoriasView extends Application {
    private Stage stage;
    private GridPane rightPanel; // Declarar rightPanel como campo de instancia

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        initialize();
    }

    private void initialize() {
        // Verificar y agregar la hoja de estilos CSS
        URL cssUrl = getClass().getResource("/css/style.css");
        if (cssUrl == null) {
            System.out.println("Archivo CSS no encontrado");
        } else {
            System.out.println("Archivo CSS encontrado: " + cssUrl.toExternalForm());
        }

        // Panel izquierdo con botones
        VBox leftPanel = new VBox(20);
        leftPanel.getStyleClass().add("left-pane");
        leftPanel.setPadding(new Insets(20, 10, 20, 10));
        leftPanel.setAlignment(Pos.TOP_CENTER);

        // Crear botones del menú con íconos
        Button inicioButton = createButtonWithIcon("Inicio", "/images/Icons/home.png");
        Button productosButton = createButtonWithIcon("Productos", "/images/Icons/products.png");
        Button historialButton = createButtonWithIcon("Historial", "/images/Icons/invoices.png");
        Button contactoButton = createButtonWithIcon("Contacto", "/images/Icons/contact.png");
        Button logoutButton = createButtonWithIcon("Atras", "/images/Icons/logout.png");

        // Agregar funcionalidad a los botones
        inicioButton.setOnAction(event -> handleInicioButton());
        productosButton.setOnAction(event -> handleProductosButton());
        historialButton.setOnAction(event -> handleHistorialButton());
        contactoButton.setOnAction(event -> handleContactoButton());
        logoutButton.setOnAction(event -> handleAtrasButton());

        leftPanel.getChildren().addAll(
                createCenteredButton(inicioButton),
                createCenteredButton(productosButton),
                createCenteredButton(historialButton),
                createCenteredButton(contactoButton),
                createCenteredButton(logoutButton)
        );

        // Inicializar rightPanel para mostrar las categorías
        rightPanel = new GridPane();
        rightPanel.setPadding(new Insets(20));
        rightPanel.setHgap(20);
        rightPanel.setVgap(20);
        rightPanel.getStyleClass().add("right-pane");

        // Añadir categorías (nombres e imágenes)
        addCategoria("Aseo", "/images/aseo.png", 1, 0);
        addCategoria("Bebidas Azucaradas", "/images/bebidas_azucaradas.png", 1, 1);
        addCategoria("Bebidas Alcohólicas", "/images/bebidas_alcoholicas.png", 1, 2);
        addCategoria("Lácteos", "/images/lacteos.png", 2, 0);
        addCategoria("Snacks", "/images/snacks.png", 2, 1);
        addCategoria("Víveres", "/images/viveres.png", 2, 2);

        // Añadir título a la vista de categorías
        Label tituloLabel = new Label("Productos");
        tituloLabel.getStyleClass().add("title-label");
        tituloLabel.setAlignment(Pos.CENTER);

        VBox rightPanelContainer = new VBox(10);
        rightPanelContainer.setAlignment(Pos.TOP_CENTER);
        rightPanelContainer.getChildren().addAll(tituloLabel, rightPanel);

        // Layout principal
        HBox mainLayout = new HBox(10);
        mainLayout.getChildren().addAll(leftPanel, rightPanelContainer);

        Scene scene = new Scene(mainLayout, 900, 600);

        // Agregar la hoja de estilos al scene si fue encontrada
        if (cssUrl != null) {
            scene.getStylesheets().add(cssUrl.toExternalForm());
        }

        stage.setScene(scene);
        stage.setTitle("Vista de Categorías");
        stage.show();
    }

    private void addCategoria(String categoriaName, String imagePath, int row, int col) {
        Label label = new Label(categoriaName);
        label.getStyleClass().add("product-label");

        // Cargar la imagen de la categoría
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(imagePath)));
        imageView.getStyleClass().add("image-view");
        imageView.setFitWidth(180); // Ajustar el tamaño del recuadro
        imageView.setFitHeight(130);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        VBox categoriaBox = new VBox(5, imageView, label);
        categoriaBox.getStyleClass().add("product-box");
        categoriaBox.setAlignment(Pos.CENTER);
        categoriaBox.setPadding(new Insets(10));
        categoriaBox.setStyle("-fx-border-color: gray; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-cursor: hand;");

        // Estilos al pasar el ratón sobre la categoría
        categoriaBox.setOnMouseEntered(event -> {
            categoriaBox.setStyle("-fx-border-color: gray; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-cursor: hand; -fx-background-color: #e0e0e0;");
        });
        categoriaBox.setOnMouseExited(event -> {
            categoriaBox.setStyle("-fx-border-color: gray; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-cursor: hand;");
        });

        // Acción al hacer clic en la categoría
        categoriaBox.setOnMouseClicked(event -> {
            showCategoriaInfo(categoriaName);
        });

        rightPanel.add(categoriaBox, col, row);
    }

    private void showCategoriaInfo(String categoriaName) {
        switch (categoriaName) {
            case "Aseo":
                Stage aseoStage = new Stage();
                AseoAdminView aseoView = new AseoAdminView();
                aseoView.start(aseoStage);
                stage.close(); // Cerrar la ventana actual de categorías
                break;
            case "Bebidas Azucaradas":
                Stage bAzucaradas = new Stage();
                BebidasAzucaradasView bebidasAzucaradasView = new BebidasAzucaradasView();
                bebidasAzucaradasView.start(bAzucaradas);
                stage.close();
                break;
            case "Bebidas Alcohólicas":
                Stage bAlcoholicas = new Stage();
                BebidasAlcoholicasView bebidasAlcoholicasView = new BebidasAlcoholicasView();
                bebidasAlcoholicasView.start(bAlcoholicas);
                stage.close();
                break;
            case "Lácteos":
                Stage lacteos = new Stage();
                LacteosView lacteosView = new LacteosView();
                lacteosView.start(lacteos);
                stage.close();
                break;
            case "Snacks":
                Stage snacks = new Stage();
                SnacksView snacksView = new SnacksView();
                snacksView.start(snacks);
                stage.close();
                break;
            case "Víveres":
                Stage viveres = new Stage();
                ViveresView viveresView = new ViveresView();
                viveresView.start(viveres);
                stage.close();
                break;
            default:
                System.out.println("Categoría no reconocida.");
        }
    }

    private Button createButtonWithIcon(String text, String iconPath) {
        ImageView icon = new ImageView(new Image(getClass().getResourceAsStream(iconPath)));
        icon.setFitWidth(16);
        icon.setFitHeight(16);
        Button button = new Button(text, icon);
        button.getStyleClass().add("button");
        return button;
    }

    private HBox createCenteredButton(Button button) {
        HBox container = new HBox(button);
        container.setAlignment(Pos.CENTER);
        return container;
    }

    private void handleInicioButton() {
        // TODO: Implementar funcionalidad
        Stage productosStage = new Stage();
        ProductsViewAdmin productsViewAdmin = new ProductsViewAdmin();
        productsViewAdmin.start(productosStage);
        stage.close(); // Cerrar la ventana actual de historial
    }

    private void handleProductosButton() {
        // TODO: Implementar funcionalidad
        CategoriasView categoriasView = new CategoriasView();
        categoriasView.start(stage); // Aquí llamamos a 'show' en lugar de 'start'
    }

    private void handleHistorialButton() {
        Stage historialStage = new Stage();
        HistorialView historialView = new HistorialView();
        historialView.start(historialStage);
        stage.close(); // Cerrar la ventana actual de categorías
    }

    private void handleContactoButton() {
        ContactoView contactoView = new ContactoView();
        Stage contactoStage = new Stage();
        contactoView.start(contactoStage);

        // Cerrar la ventana actual de productos
        stage.close();
    }

    private void handleAtrasButton() {
        Stage productosStage = new Stage();
        ProductsViewAdmin productsViewAdmin = new ProductsViewAdmin();
        productsViewAdmin.start(productosStage);
        stage.close(); // Cerrar la ventana actual de categorías
    }

    public static void main(String[] args) {
        launch(args);
    }
}
