package uptc.proyectofx.Employed;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BebidasAzucaradasView extends Application {

    private List<Product> productList = new ArrayList<>(); // List to store selected products
    private VBox rightPanel; // Reference to the right panel to update it when changing categories
    private ComboBox<String> categoriesComboBox; // Category selector

    // Lists of products for different categories
    // Lists of products for different categories
    private List<Product> BEbidasAzucaradas = List.of(
            new Product("Coca-Cola", 2800, 1,15,  "cocacola.png"),
            new Product("Manzana", 1700, 1,15, "manzana.png"),
            new Product("Naranja", 2300, 1,15, "naranja.png"),
            new Product("Pepsi", 2000, 1, 15,"pepsi.png"),
            new Product("Quatro", 1800, 1,15, "quatro.png"),
            new Product("Sprite", 2200, 1, 15,"sprite.png")
    );

    private List<Product> jugos = List.of(
            new Product("Hit Botella", 2000, 1, 15,"botella.png"),
            new Product("Hit caja", 2900, 1, 15,"caja.png"),
            new Product("Del Valle", 2800, 1, 15,"delvalle.png"),
            new Product("Hit lulo", 2700, 1,15,"lulo.png"),
            new Product("Tampico", 1200, 1, 15,"tampico.png"),
            new Product("Tea", 2500, 1, 15,"tea.png")
    );

    private Alert persistentInvoiceAlert; // Persistent Alert for the invoice

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
        rightPanel = createRightPanel();
        root.setCenter(rightPanel);

        Scene scene = new Scene(root, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Save products to file on startup
        saveProductsWithQuantityToFile();
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

        // Imagen de volver atrás
        Image backImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("logout.png")));
        ImageView backImageView = new ImageView(backImage);
        backImageView.setFitHeight(30);
        backImageView.setFitWidth(30);

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

        Label backLabel = createLabel("Atrás");
        HBox backBox = new HBox(10, backImageView, backLabel);
        backBox.setAlignment(Pos.CENTER_LEFT);

        backLabel.setOnMouseEntered(event -> backLabel.setTextFill(Color.YELLOW));
        backLabel.setOnMouseExited(event -> backLabel.setTextFill(Color.BLACK));
        backLabel.setOnMouseClicked(event -> {
            ProductsVendEmployed productsVendEmployed = new ProductsVendEmployed();
            try {
                productsVendEmployed.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        VBox.setMargin(userBox, new Insets(10, 0, 10, 0));
        VBox.setMargin(saleBox, new Insets(10, 0, 10, 0));
        VBox.setMargin(backBox, new Insets(10, 0, 10, 0));
        leftPanel.getChildren().addAll(userBox, saleBox, vacio, vacio2, vacio3, vacio4, vacio5, vacio6, vacio7, vacio8, backBox);
        return leftPanel;
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setTextFill(Color.BLACK);
        label.setFont(Font.font("Segoe UI", 16));
        label.setWrapText(true);
        return label;
    }

    private VBox createRightPanel() {
        VBox rightPanel = new VBox();
        rightPanel.setPadding(new Insets(20));
        rightPanel.setSpacing(20);
        rightPanel.setStyle("-fx-background-color: #FFB24B;");

        Label titleLabel = new Label("Bebidas Azucaradas");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.BLACK);

        HBox topPanel = new HBox(titleLabel);
        topPanel.setAlignment(Pos.CENTER);
        topPanel.setPadding(new Insets(10));

        // Create ComboBox for categories
        categoriesComboBox = new ComboBox<>();
        categoriesComboBox.getItems().addAll(
                "Bebidas Azucaradas",
                "Jugos"
        );
        categoriesComboBox.setPromptText("Categorías");
        categoriesComboBox.setValue("Bebidas Azucaradas"); // Set default category
        categoriesComboBox.setOnAction(event -> updateProductGrid());

        HBox categoryBox = new HBox(categoriesComboBox);
        categoryBox.setAlignment(Pos.CENTER_RIGHT);
        categoryBox.setPadding(new Insets(10));

        HBox topRightPanel = new HBox(20, topPanel, categoryBox);
        topRightPanel.setAlignment(Pos.CENTER);
        topRightPanel.setPadding(new Insets(10));

        rightPanel.getChildren().addAll(topRightPanel, createProductGrid(BEbidasAzucaradas));
        return rightPanel;
    }

    private GridPane createProductGrid(List<Product> products) {
        GridPane productGrid = new GridPane();
        productGrid.setPadding(new Insets(10));
        productGrid.setHgap(40);
        productGrid.setVgap(40);
        productGrid.setAlignment(Pos.CENTER);

        int row = 0;
        int col = 0;
        for (Product product : products) {
            addProductLabel(productGrid, product, row, col);
            col++;
            if (col > 2) {
                col = 0;
                row++;
            }
        }
        return productGrid;
    }

    private void updateProductGrid() {
        rightPanel.getChildren().remove(1); // Remove the old product grid

        List<Product> selectedProducts = List.of();
        if (categoriesComboBox.getValue().equals("Bebidas Azucaradas")) {
            selectedProducts = BEbidasAzucaradas;
        } else if (categoriesComboBox.getValue().equals("Jugos")) {
            selectedProducts = jugos;
        }

        rightPanel.getChildren().add(createProductGrid(selectedProducts));
    }

    private void addProductLabel(GridPane grid, Product product, int row, int col) {
        VBox productBox = new VBox();
        productBox.setPadding(new Insets(10));
        productBox.setSpacing(10);
        productBox.setAlignment(Pos.CENTER);
        productBox.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #CCCCCC; -fx-border-width: 1px;");

        Image productImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(product.getImagePath())));
        ImageView productImageView = new ImageView(productImage);
        productImageView.setFitHeight(100);
        productImageView.setFitWidth(100);

        Label nameLabel = new Label(product.getName());
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        nameLabel.setAlignment(Pos.CENTER);

        Label priceLabel = new Label("$" + product.getPrice());
        priceLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        priceLabel.setAlignment(Pos.CENTER);

        productBox.getChildren().addAll(productImageView, nameLabel, priceLabel);

        productBox.setOnMouseClicked(event -> {
            addProductToInvoice(product);
        });

        productBox.setOnMouseEntered(event -> {
            productBox.setStyle("-fx-background-color: #FFD700; -fx-border-color: #CCCCCC; -fx-border-width: 1px;");
        });

        productBox.setOnMouseExited(event -> {
            productBox.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #CCCCCC; -fx-border-width: 1px;");
        });

        grid.add(productBox, col, row);
    }

    private void addProductToInvoice(Product product) {
        int availableQuantity = product.getAvailableQuantity();
        if (availableQuantity <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Producto Agotado");
            alert.setHeaderText(null);
            alert.setContentText("El producto " + product.getName() + " está agotado y no se puede vender más.");
            alert.showAndWait();
            return;
        }

        TextInputDialog dialog = new TextInputDialog("1");
        dialog.setTitle("Cantidad del Producto");
        dialog.setHeaderText("Ingrese la cantidad para " + product.getName());
        dialog.setContentText("Cantidad:");

        dialog.showAndWait().ifPresent(quantityStr -> {
            try {
                int quantity = Integer.parseInt(quantityStr);
                if (quantity > 15 || quantity > availableQuantity) {
                    throw new NumberFormatException("Cantidad excede el máximo permitido de 15 o la cantidad disponible.");
                }
                productList.add(new Product(product.getName(), product.getPrice(), quantity, product.getMaxQuantity(), product.getImagePath()));
                product.reduceAvailableQuantity(quantity); // Reduce the available quantity

                // Show persistent alert with invoice information
                if (persistentInvoiceAlert == null) {
                    persistentInvoiceAlert = new Alert(Alert.AlertType.INFORMATION);
                    persistentInvoiceAlert.setTitle("Factura");
                    persistentInvoiceAlert.setHeaderText("Productos seleccionados:");
                }

                StringBuilder content = new StringBuilder();
                double total = 0;
                for (Product p : productList) {
                    content.append(p.getName()).append(" - Cantidad: ").append(p.getQuantity()).append(" - $").append(p.getPrice() * p.getQuantity()).append("\n");
                    total += p.getPrice() * p.getQuantity();
                }
                content.append("Total: $").append(total);
                persistentInvoiceAlert.setContentText(content.toString());

                persistentInvoiceAlert.show();

                // Save the sale to productos.txt
                saveSaleToFile(product.getName(), quantity, total);

                // Update the quantities file
                saveProductsWithQuantityToFile();

            } catch (NumberFormatException e) {
                // Handle invalid number input
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Cantidad inválida");
                alert.setContentText("Por favor ingrese un número válido para la cantidad (máximo 15 o la cantidad disponible).");
                alert.showAndWait();
            }
        });
    }

    private void saveSaleToFile(String productName, int quantity, double total) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("productos.txt", true))) {
            writer.write(productName + " - Cantidad: " + quantity + " - Total: $" + total);

            writer.write("\nCosto Total: $"+ total);
            writer.write("\n-----------------------------------------------------\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveProductsWithQuantityToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("products_with_quantities.txt", true))) { // Append mode
            writer.write("\n-----------------------------------------------------\n"); // Separator line

            for (Product product : BEbidasAzucaradas) {
                writer.write(product.getName() + ", Cantidad: " + product.getAvailableQuantity());
                writer.newLine();
            }

            for (Product product : jugos) {
                writer.write(product.getName() + ", Cantidad: " + product.getAvailableQuantity());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Product class to store product information
    static class Product {
        private final String name;
        private final double price;
        private final int quantity;
        private final int maxQuantity;
        private final String imagePath;
        private int availableQuantity; // Available quantity for sale

        public Product(String name, double price, int quantity, int maxQuantity, String imagePath) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.maxQuantity = maxQuantity;
            this.imagePath = imagePath;
            this.availableQuantity = maxQuantity; // Initialize available quantity to maxQuantity
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }

        public int getMaxQuantity() {
            return maxQuantity;
        }

        public String getImagePath() {
            return imagePath;
        }

        public int getAvailableQuantity() {
            return availableQuantity;
        }

        public void reduceAvailableQuantity(int quantity) {
            this.availableQuantity -= quantity;
        }
    }
}
