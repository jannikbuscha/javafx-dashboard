package com.jannikbuscha.dashboard;

import com.jannikbuscha.dashboard.user.LocalUserData;
import com.jannikbuscha.dashboard.util.FXUtil;
import com.jannikbuscha.dashboard.util.Theme;
import com.sun.javafx.util.Logging;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.*;
import sun.util.logging.PlatformLogger;

import java.io.IOException;

@Getter
public class Main extends Application {

    private final String name = "JavaFX Dashboard", version = "1.0";

    @Getter
    private static Main instance;

    private Stage stage;

    @Setter
    private Scene scene;

    public void start(Stage stage) throws Exception {
        instance = this;
        createScene(stage);
    }

    private void createScene(Stage stage) throws IOException {
        this.stage = stage;

        Parent root = FXMLLoader.load(getClass().getResource("fxml/dashboard.fxml"));

        scene = new Scene(root);

        loadTheme();
        scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());

        scene.setRoot(root);
        scene.setFill(Color.TRANSPARENT);

        stage.setTitle(name + " " + version);
        stage.setScene(scene);

        stage.initStyle(StageStyle.TRANSPARENT);
        FXUtil.resizable(stage);

        stage.show();
    }

    private void loadTheme() {
        // Ignore CSS warnings
        Logging.getCSSLogger().setLevel(PlatformLogger.Level.OFF);

        if (!LocalUserData.existsFolder()) {
            LocalUserData.setProperty("theme", Theme.STANDARD.ordinal() + "");
            LocalUserData.setProperty("dark_mode", String.valueOf(false));
        }

        LocalUserData.getProperty("theme").ifPresent(theme -> Theme.setCurrentTheme(Theme.values()[Integer.parseInt(theme)], LocalUserData.getProperty("dark_mode").map(Boolean::valueOf).orElse(false)));
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.exit(0);
    }

    public static void main(String args[]) {
        launch(args);
    }

}
