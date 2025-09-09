package com.ip.arshelle.app;

import com.ip.arshelle.ui.DialogBox;
import com.ip.arshelle.ui.Ui;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.InputStream;

public class Main extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image userImage;
    private Image botImage;

    private EchoSession session;

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox(8);
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        sendButton.setOnAction(e -> handleUserInput());
        userInput.setOnAction(e -> handleUserInput());

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scrollPane.setFitToWidth(true);
        scrollPane.setPrefSize(400, 520);
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setLeftAnchor(scrollPane, 1.0);
        AnchorPane.setRightAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(scrollPane, 50.0);

        userInput.setPromptText("Type a command…");
        userInput.setPrefWidth(310.0);
        AnchorPane.setBottomAnchor(userInput, 10.0);
        AnchorPane.setLeftAnchor(userInput, 10.0);

        sendButton.setPrefWidth(70.0);
        AnchorPane.setBottomAnchor(sendButton, 10.0);
        AnchorPane.setRightAnchor(sendButton, 10.0);

        scene = new Scene(mainLayout, 400, 570);
        stage.setTitle("SonOfAnton");
        stage.setScene(scene);
        stage.show();

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        userImage = loadImageSafe("/images/user.png");
        botImage  = loadImageSafe("/images/bot.png");

        Ui guiUi = new Ui((String line) -> {
            if (line == null || line.isEmpty()) return;

            var botBubble = DialogBox.getDukeDialog(line, botImage);

            Platform.runLater(() ->
                    dialogContainer.getChildren().add(botBubble)
            );
        });

        session = new EchoSession(guiUi);

        String logo = "_    _   _ _______  ____  _   _ \n"
                + "   / \\  | \\ | |__   __|/ __ \\| \\ | |\n"
                + "  / _ \\ |  \\| |  | |  | |  | |  \\| |\n"
                + " / ___ \\| |\\  |  | |  | |__| | |\\  |\n"
                + "/_/   \\_\\_| \\_|  |_|   \\____/|_| \\_|\n";
        guiUi.showWelcome(logo);
    }

    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (input.isEmpty()) return;

        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));

        boolean cont = session.handleCommand(input);

        userInput.clear();
        if (!cont) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
        }
    }

    private Image loadImageSafe(String path) {
        try (InputStream is = getClass().getResourceAsStream(path)) {
            return (is == null) ? null : new Image(is);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
