package uptc.proyectofx.controller;

import javafx.stage.Stage;
import uptc.proyectofx.view.ProductsViewAdmin;

public class ProductsControllerAdmin {
    private final ProductsViewAdmin productsViewAdmin;

    public ProductsControllerAdmin(Stage stage) {
        // Crear una instancia de ProductsViewAdmin sin argumentos
        this.productsViewAdmin = new ProductsViewAdmin();

        // Configurar el Stage usando el método setStage
        this.productsViewAdmin.setStage(stage);

        // Aquí podrías agregar más lógica para el controlador, si es necesario
    }
}
