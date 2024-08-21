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
import java.util.ArrayList;
import java.util.List;

public class BebidasAzucaradasView extends Application {
    private Stage stage;
    private GridPane rightPanel;
    private List<Producto> productos;

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        this.productos = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        URL cssUrl = getClass().getResource("/css/style.css");
        if (cssUrl == null) {
            System.out.println("Archivo CSS no encontrado");
        } else {
            System.out.println("Archivo CSS encontrado: " + cssUrl.toExternalForm());
        }

        VBox leftPanel = new VBox(20);
        leftPanel.getStyleClass().add("left-pane");
        leftPanel.setPadding(new Insets(20, 20, 20, 20));
        leftPanel.setAlignment(Pos.TOP_CENTER);
        leftPanel.setMinWidth(200);

        // Crear botones del menú con íconos
        Button inicioButton = createButtonWithIcon("Inicio", "/images/Icons/home.png");
        Button productosButton = createButtonWithIcon("Productos", "/images/Icons/products.png");
        Button historialButton = createButtonWithIcon("Historial", "/images/Icons/invoices.png");
        Button contactoButton = createButtonWithIcon("Contacto", "/images/Icons/contact.png");
        Button logoutButton = createButtonWithIcon("Atras", "/images/Icons/logout.png");

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

        rightPanel = new GridPane();
        rightPanel.setPadding(new Insets(20));
        rightPanel.setHgap(20);
        rightPanel.setVgap(20);
        rightPanel.getStyleClass().add("right-pane");

        addProducto(new Producto("CocaCola", "/images/bebidas/cocacola.png", 10.0, 5), 1, 0);
        addProducto(new Producto("Manzana", "/images/bebidas/manzana.png", 20.0, 10), 1, 1);
        addProducto(new Producto("Quatro", "/images/bebidas/quatro.png", 15.0, 8), 1, 2);
        addProducto(new Producto("Naranja", "/images/bebidas/naranja.png", 25.0, 12), 2, 0);
        addProducto(new Producto("Pepsi", "/images/bebidas/pepsi.png", 5.0, 20), 2, 1);
        addProducto(new Producto("Sprite", "/images/bebidas/sprite.png", 12.0, 30), 2, 2);

        Label tituloLabel = new Label("Bebidas Azucaradas");
        tituloLabel.getStyleClass().add("title-label");
        tituloLabel.setAlignment(Pos.CENTER);

        VBox rightPanelContainer = new VBox(10);
        rightPanelContainer.setAlignment(Pos.TOP_CENTER);
        rightPanelContainer.getChildren().addAll(tituloLabel, rightPanel);

        HBox mainLayout = new HBox(10);
        mainLayout.getChildren().addAll(leftPanel, rightPanelContainer);

        Scene scene = new Scene(mainLayout, 900, 600);

        if (cssUrl != null) {
            scene.getStylesheets().add(cssUrl.toExternalForm());
        }

        stage.setScene(scene);
        stage.setTitle("Vista de Bebidas Azucaradas");
        stage.show();
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.getStyleClass().add("button");
        return button;
    }

    private Button createButtonWithIcon(String text, String iconPath) {
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(iconPath)));
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);
        Button button = new Button(text, imageView);
        button.getStyleClass().add("button");
        return button;
    }

    private HBox createCenteredButton(Button button) {
        HBox container = new HBox(button);
        container.setAlignment(Pos.CENTER);
        return container;
    }

    private void addProducto(Producto producto, int row, int col) {
        productos.add(producto);
        updateGridPane();
    }

    private void updateGridPane() {
        rightPanel.getChildren().clear();
        int row = 1;
        int col = 0;

        for (Producto producto : productos) {
            Label label = new Label(producto.getNombre());
            label.getStyleClass().add("product-label");

            ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(producto.getImagePath())));
            imageView.getStyleClass().add("image-view");
            imageView.setFitWidth(120);
            imageView.setFitHeight(100);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);

            Button editButton = new Button("Editar");
            editButton.setOnAction(event -> handleEditProducto(producto));

            Button deleteButton = new Button("Eliminar");
            deleteButton.setOnAction(event -> handleDeleteProducto(producto));

            editButton.setMinWidth(60);
            deleteButton.setMinWidth(60);

            HBox buttonBox = new HBox(5, editButton, deleteButton);
            buttonBox.setAlignment(Pos.CENTER);

            VBox productoBox = new VBox(5, imageView, label, buttonBox);
            productoBox.getStyleClass().add("product-box");
            productoBox.setAlignment(Pos.CENTER);
            productoBox.setPadding(new Insets(10));
            productoBox.setStyle("-fx-border-color: gray; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-cursor: hand;");

            productoBox.setOnMouseEntered(event -> {
                productoBox.setStyle("-fx-border-color: gray; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-cursor: hand; -fx-background-color: #e0e0e0;");
            });
            productoBox.setOnMouseExited(event -> {
                productoBox.setStyle("-fx-border-color: gray; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-cursor: hand;");
            });

            rightPanel.add(productoBox, col, row);

            col++;
            if (col > 2) {
                col = 0;
                row++;
            }
        }
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
        stage.close();
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
        stage.close();
    }

    private void handleEditProducto(Producto producto) {
        Stage editStage = new Stage();
        EditProductoView editProductoView = new EditProductoView(producto.getNombre(), producto.getImagePath(), producto.getPrecio(), producto.getCantidad());
        editProductoView.start(editStage);

        editStage.setOnHidden(event -> {
            int newCantidad = editProductoView.getCantidad();
            producto.setCantidad(newCantidad);
            System.out.println("Producto: " + producto.getNombre() + ", Cantidad añadida: " + newCantidad);
            updateGridPane();
        });
    }

    private void handleDeleteProducto(Producto producto) {
        productos.remove(producto);
        updateGridPane();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class Producto2 {
    private String nombre;
    private String imagePath;
    private double precio;
    private int cantidad;

    public Producto2(String nombre, String imagePath, double precio, int cantidad) {
        this.nombre = nombre;
        this.imagePath = imagePath;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getImagePath() {
        return imagePath;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
