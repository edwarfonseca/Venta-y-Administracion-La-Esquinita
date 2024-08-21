package uptc.proyectofx.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.net.URL;

public class ContactoView extends Application {
    private Stage stage;
    private MediaPlayer mediaPlayer;

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        initialize();
    }

    private void initialize() {
        // Reproducir música de fondo
        String audioPath = getClass().getResource("/sounds/background.mp3").toExternalForm();
        Media media = new Media(audioPath);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Repetir indefinidamente
        mediaPlayer.play();

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

        // Panel derecho con información de contacto
        VBox rightPanel = new VBox(40);
        rightPanel.setPadding(new Insets(40, 20, 40, 20));
        rightPanel.setAlignment(Pos.TOP_CENTER);

        Label tituloLabel = new Label("Tienda la Esquinita");
        tituloLabel.getStyleClass().add("title-label");
        tituloLabel.setAlignment(Pos.CENTER);

        Label descripcionLabel = new Label("Bienvenido a la Tienda la Esquinita, donde ofrecemos una gran variedad de productos de calidad, desde lácteos frescos hasta los mejores snacks y víveres esenciales. Estamos comprometidos con ofrecer productos frescos y de alta calidad a precios accesibles. Nuestra tienda es tu lugar de confianza para todas tus compras diarias.");
        descripcionLabel.setWrapText(true);
        descripcionLabel.getStyleClass().add("description-label");
        descripcionLabel.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-padding: 20; -fx-border-radius: 10; -fx-background-radius: 10;");
        descripcionLabel.setMaxWidth(600);

        Button buscarGpsButton = new Button("Buscar en GPS (Maps)");
        buscarGpsButton.setOnAction(event -> handleBuscarGps());

        VBox.setMargin(buscarGpsButton, new Insets(20, 0, 60, 0));

        Label footerLabel = new Label("Nombre del dueño: Juan Pérez");
        footerLabel.getStyleClass().add("footer-label");
        footerLabel.setStyle("-fx-text-fill: gray;");
        VBox.setVgrow(footerLabel, Priority.ALWAYS);

        rightPanel.getChildren().addAll(tituloLabel, descripcionLabel, buscarGpsButton, footerLabel);

        HBox mainLayout = new HBox(10);
        mainLayout.getChildren().addAll(leftPanel, rightPanel);

        Scene scene = new Scene(mainLayout, 900, 600);

        if (cssUrl != null) {
            scene.getStylesheets().add(cssUrl.toExternalForm());
        }

        stage.setScene(scene);
        stage.setTitle("Contacto");
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

    private void handleHistorialButton() {
        Stage historialStage = new Stage();
        HistorialView historialView = new HistorialView();
        historialView.start(historialStage);
        stage.close();
    }

    private void handleContactoButton() {
        // Esta es la vista actual
    }

    private void handleAtrasButton() {
        // Detener la música de fondo
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        Stage productosStage = new Stage();
        ProductsViewAdmin productsViewAdmin = new ProductsViewAdmin();
        productsViewAdmin.start(productosStage);
        stage.close();
    }

    private void handleBuscarGps() {
        // Simular abrir ubicación en Google Maps
        try {
            java.awt.Desktop.getDesktop().browse(new java.net.URI("https://www.google.com/maps"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
