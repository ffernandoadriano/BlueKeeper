package lnandobr.bluekeeper.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Classe de Application do JavaFX
 */
public class Reference extends Application {

    /**
     * Método main(): onde tudo começa
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @see javafx.application.Application#start(Stage)
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        // Carrega o arquivo de layout
        Pane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Layout.fxml")));

        // Cria a cena
        Scene scene = new Scene(root, 850, 400);

        // Define largura e altura mínima para a janela
        primaryStage.setMinWidth(850);
        primaryStage.setMinHeight(400);

        // Define o título da janela
        primaryStage.setTitle("BlueKeeper");

        // Associa a cena à janela
        primaryStage.setScene(scene);

        // Exibe a janela
        primaryStage.show();
    }
}
