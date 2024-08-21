package uptc.proyectofx.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;

public class HistorialView extends Application {
    private Stage stage;

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

        Button inicioButton = createButtonWithIcon("Inicio", "/images/Icons/home.png");
        Button productosButton = createButtonWithIcon("Productos", "/images/Icons/products.png");
        Button facturasButton = createButtonWithIcon("Historial", "/images/Icons/invoices.png");
        Button contactoButton = createButtonWithIcon("Contacto", "/images/Icons/contact.png");
        Button logoutButton = createButtonWithIcon("Atras", "/images/Icons/logout.png");

        // Agregar funcionalidad a los botones
        inicioButton.setOnAction(event -> handleInicioButton());
        productosButton.setOnAction(event -> handleProductosButton());
        facturasButton.setOnAction(event -> handleFacturasButton());
        contactoButton.setOnAction(event -> handleContactoButton());
        logoutButton.setOnAction(event -> handleAtrasButton());

        leftPanel.getChildren().addAll(
                createCenteredButton(inicioButton),
                createCenteredButton(productosButton),
                createCenteredButton(facturasButton),
                createCenteredButton(contactoButton),
                createCenteredButton(logoutButton)
        );

        // Panel derecho con tabla de historial
        VBox rightPanel = new VBox(10);
        rightPanel.setAlignment(Pos.TOP_CENTER);
        rightPanel.setPadding(new Insets(10));
        rightPanel.getStyleClass().add("right-pane");

        Label titleLabel = new Label("Historial De Ventas");
        titleLabel.getStyleClass().add("title-label");

        TableView<HistorialData> tableView = new TableView<>();
        tableView.setPrefWidth(600); // Ancho preferido de 600
        tableView.setMinWidth(500); // Ancho mínimo de 500
        tableView.setMaxWidth(800); // Ancho máximo de 800

        TableColumn<HistorialData, String> mesCol = new TableColumn<>("Mes");
        mesCol.setCellValueFactory(new PropertyValueFactory<>("mes"));
        mesCol.setPrefWidth(200); // Establece un ancho preferido de 200

        TableColumn<HistorialData, String> empleadoCol = new TableColumn<>("Empleado");
        empleadoCol.setCellValueFactory(new PropertyValueFactory<>("empleado"));
        empleadoCol.setPrefWidth(200); // Establece un ancho preferido de 200

        TableColumn<HistorialData, Button> contabilidadCol = new TableColumn<>("Contabilidad");
        contabilidadCol.setCellValueFactory(new PropertyValueFactory<>("contabilidadButton"));
        contabilidadCol.setPrefWidth(200); // Establece un ancho preferido de 200

        tableView.getColumns().addAll(mesCol, empleadoCol, contabilidadCol);

        // Agregar datos a la tabla
        tableView.getItems().addAll(
                new HistorialData("Marzo", "Julian"),
                new HistorialData("Abril", "Edwar"),
                new HistorialData("Mayo", "Edwar"),
                new HistorialData("Junio", "Julian"),
                new HistorialData("Julio", "Edwar"),
                new HistorialData("Agosto", "Julian")
        );

        rightPanel.getChildren().addAll(titleLabel, tableView);

        // Layout principal
        HBox mainLayout = new HBox(10);
        mainLayout.getChildren().addAll(leftPanel, rightPanel);

        Scene scene = new Scene(mainLayout, 900, 600);

        // Agregar la hoja de estilos al scene si fue encontrada
        if (cssUrl != null) {
            scene.getStylesheets().add(cssUrl.toExternalForm());
        }

        stage.setScene(scene);
        stage.setTitle("Historial View");
        stage.show();
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

    private void handleFacturasButton() {
        // Ya estamos en la vista de Historial
    }

    private void handleContactoButton() {
        // TODO: Implementar funcionalidad
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
        stage.close(); // Cerrar la ventana actual de historial
    }

    // Clase interna para representar los datos de cada fila
    public class HistorialData {
        private final String mes;
        private final String empleado;
        private final Button contabilidadButton;

        public HistorialData(String mes, String empleado) {
            this.mes = mes;
            this.empleado = empleado;
            this.contabilidadButton = new Button("ver");
            this.contabilidadButton.setOnAction(event -> showContabilidadDetails());
        }

        public String getMes() {
            return mes;
        }

        public String getEmpleado() {
            return empleado;
        }

        public Button getContabilidadButton() {
            return contabilidadButton;
        }

        private void showContabilidadDetails() {
            Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(stage);

            VBox dialogVBox = new VBox(20);
            dialogVBox.setPadding(new Insets(20));
            dialogVBox.setAlignment(Pos.CENTER);

            TableView<DetalleContabilidad> detailsTable = new TableView<>();
            TableColumn<DetalleContabilidad, String> inversionCol = new TableColumn<>("Inversión");
            TableColumn<DetalleContabilidad, String> ventasCol = new TableColumn<>("Ventas");
            TableColumn<DetalleContabilidad, String> gananciaCol = new TableColumn<>("Ganancia");

            inversionCol.setCellValueFactory(new PropertyValueFactory<>("inversion"));
            ventasCol.setCellValueFactory(new PropertyValueFactory<>("ventas"));
            gananciaCol.setCellValueFactory(new PropertyValueFactory<>("ganancia"));

            detailsTable.getColumns().addAll(inversionCol, ventasCol, gananciaCol);

            detailsTable.getItems().add(new DetalleContabilidad("$600.000", "$1.300.000", "$700.000"));

            dialogVBox.getChildren().addAll(new Label("Detalles de Contabilidad"), detailsTable);

            Scene dialogScene = new Scene(dialogVBox, 400, 300);
            dialog.setScene(dialogScene);
            dialog.setTitle("Detalles de Contabilidad");
            dialog.showAndWait();
        }
    }

    // Clase interna para representar los detalles de contabilidad
    public class DetalleContabilidad {
        private final String inversion;
        private final String ventas;
        private final String ganancia;

        public DetalleContabilidad(String inversion, String ventas, String ganancia) {
            this.inversion = inversion;
            this.ventas = ventas;
            this.ganancia = ganancia;
        }

        public String getInversion() {
            return inversion;
        }

        public String getVentas() {
            return ventas;
        }

        public String getGanancia() {
            return ganancia;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
