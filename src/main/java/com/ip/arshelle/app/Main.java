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
    private static final double APP_W = 400.0;
    private static final double APP_H = 570.0;
    private static final double SCROLL_BOTTOM = 50.0;
    private static final double GAP = 10.0;
    private static final double INPUT_W = 310.0;
    private static final double SEND_W = 70.0;

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
        AnchorPane root = createRoot();
        configureScrollPane(root);
        configureInputRow(root);
        bindAutoScroll();

        loadImages();
        Ui guiUi = initUiAndSession();

        scene = new Scene(root, APP_W, APP_H);
        stage.setTitle("SonOfAnton");
        stage.setScene(scene);
        stage.show();

        showWelcome(guiUi);
    }

    private AnchorPane createRoot() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox(8);
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        userInput.setPromptText("Type a command…");
        sendButton = new Button("Send");

        sendButton.setOnAction(e -> handleUserInput());
        userInput.setOnAction(e -> handleUserInput());

        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(scrollPane, userInput, sendButton);
        return root;
    }

    private void configureScrollPane(AnchorPane root) {
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefSize(APP_W, APP_H - 50);
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setLeftAnchor(scrollPane, 1.0);
        AnchorPane.setRightAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(scrollPane, SCROLL_BOTTOM);
    }

    private void configureInputRow(AnchorPane root) {
        userInput.setPrefWidth(INPUT_W);
        AnchorPane.setBottomAnchor(userInput, GAP);
        AnchorPane.setLeftAnchor(userInput, GAP);

        sendButton.setPrefWidth(SEND_W);
        AnchorPane.setBottomAnchor(sendButton, GAP);
        AnchorPane.setRightAnchor(sendButton, GAP);
    }

    private void bindAutoScroll() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    private void loadImages() {
        userImage = loadImageSafe("/images/user.png");
        botImage  = loadImageSafe("/images/bot.png");
    }

    private Ui initUiAndSession() {
        Ui guiUi = new Ui((String line) -> {
            if (line == null || line.isEmpty()) return;
            var botBubble = DialogBox.getDukeDialog(line, botImage);
            Platform.runLater(() -> dialogContainer.getChildren().add(botBubble));
        });
        session = new EchoSession(guiUi);
        return guiUi;
    }

    private void showWelcome(Ui guiUi) {
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
