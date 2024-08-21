    package uptc.proyectofx.view;

    import javafx.application.Application;
    import javafx.geometry.Insets;
    import javafx.geometry.Pos;
    import javafx.scene.Scene;
    import javafx.scene.control.Button;
    import javafx.scene.control.Label;
    import javafx.scene.control.TextField;
    import javafx.scene.image.Image;
    import javafx.scene.image.ImageView;
    import javafx.scene.layout.GridPane;
    import javafx.scene.layout.HBox;
    import javafx.scene.layout.VBox;
    import javafx.stage.FileChooser;
    import javafx.stage.Stage;

    import java.io.File;

    public class EditProductoView extends Application {
        private String productoName;
        private String imagePath;
        private double precio;
        private int cantidad;

        private TextField cantidadField;
        private ImageView imageView;
        private String selectedImagePath;

        public EditProductoView(String productoName, String imagePath, double precio, int cantidad) {
            this.productoName = productoName;
            this.imagePath = imagePath;
            this.precio = precio;
            this.cantidad = cantidad;
            this.selectedImagePath = imagePath;
        }

        @Override
        public void start(Stage stage) {
            VBox root = new VBox(10);
            root.setPadding(new Insets(10));
            root.setAlignment(Pos.CENTER);

            cantidadField = new TextField(String.valueOf(cantidad));

            imageView = new ImageView(new Image(getClass().getResourceAsStream(imagePath)));
            imageView.setFitWidth(120);
            imageView.setFitHeight(100);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);

            Button selectImageButton = new Button("Seleccionar Imagen");
            selectImageButton.setOnAction(event -> handleSelectImage(stage));

            Button saveButton = new Button("Guardar");
            saveButton.setOnAction(event -> stage.close());

            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            gridPane.setPadding(new Insets(10));
            gridPane.add(new Label("Cantidad:"), 0, 0);
            gridPane.add(cantidadField, 1, 0);
            gridPane.add(new Label("Imagen:"), 0, 1);
            gridPane.add(imageView, 1, 1);
            gridPane.add(selectImageButton, 1, 2);

            HBox buttonBox = new HBox(10, saveButton);
            buttonBox.setAlignment(Pos.CENTER);

            root.getChildren().addAll(gridPane, buttonBox);

            Scene scene = new Scene(root, 400, 400);
            stage.setScene(scene);
            stage.setTitle("Editar Producto");
            stage.show();
        }

        private void handleSelectImage(Stage stage) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagenes", "*.png", "*.jpg", "*.jpeg"));
            File selectedFile = fileChooser.showOpenDialog(stage);

            if (selectedFile != null) {
                selectedImagePath = selectedFile.toURI().toString();
                imageView.setImage(new Image(selectedImagePath));
            }
        }

        public int getCantidad() {
            return Integer.parseInt(cantidadField.getText());
        }

        public static void main(String[] args) {
            launch(args);
        }
    }
