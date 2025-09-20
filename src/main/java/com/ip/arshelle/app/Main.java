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

/**
 * JavaFX entry point for the SonOfAnton chatbot application.
 * <p>
 * Responsible for building the GUI scene graph (message area, input
 * controls), wiring user actions to the underlying session, and
 * showing the initial screen. All behavior is UI-related; business
 * logic is delegated to {@link EchoSession}.
 * <p>
 * AI-assisted documentation note: JavaDoc text initially generated
 * with the help of ChatGPT and then reviewed/edited by the author.
 */
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

    /**
     * Configures and shows the primary stage, including the scrollable dialog
     * area, the input row, image resources, and the backing session. Finally,
     * displays the welcome banner.
     *
     * @param stage the primary JavaFX stage provided by the runtime
     */
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

    /**
     * Creates the root layout and initializes UI controls:
     * the scroll pane, message container, input field, and send button.
     *
     * @return the configured {@link AnchorPane} used as the root node
     */
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

    /**
     * Sizes and anchors the scroll pane within the root layout.
     *
     * @param root the root layout to which the scroll pane is anchored
     */
    private void configureScrollPane(AnchorPane root) {
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefSize(APP_W, APP_H - 50);
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setLeftAnchor(scrollPane, 1.0);
        AnchorPane.setRightAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(scrollPane, SCROLL_BOTTOM);
    }

    /**
     * Sizes and anchors the input field and send button within the root layout.
     *
     * @param root the root layout used for anchoring controls
     */
    private void configureInputRow(AnchorPane root) {
        userInput.setPrefWidth(INPUT_W);
        AnchorPane.setBottomAnchor(userInput, GAP);
        AnchorPane.setLeftAnchor(userInput, GAP);

        sendButton.setPrefWidth(SEND_W);
        AnchorPane.setBottomAnchor(sendButton, GAP);
        AnchorPane.setRightAnchor(sendButton, GAP);
    }

    /**
     * Keeps the scroll pane pinned to the bottom as new messages are added.
     */
    private void bindAutoScroll() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Loads avatar images used by the user and the bot. If a resource is
     * missing, the field is left as {@code null}.
     */
    private void loadImages() {
        userImage = loadImageSafe("/images/user.png");
        botImage  = loadImageSafe("/images/bot.png");
    }

    /**
     * Creates a {@link Ui} that renders bot messages into the dialog container,
     * initializes the {@link EchoSession}, and returns the UI instance so the
     * caller can show the welcome banner.
     *
     * @return a UI instance that forwards lines to the GUI
     */
    private Ui initUiAndSession() {
        Ui guiUi = new Ui((String line) -> {
            if (line == null || line.isEmpty()) return;
            var botBubble = DialogBox.getDukeDialog(line, botImage);
            Platform.runLater(() -> dialogContainer.getChildren().add(botBubble));
        });
        session = new EchoSession(guiUi);
        return guiUi;
    }

    /**
     * Renders the welcome banner and logo to the UI.
     *
     * @param guiUi the UI used to display the banner
     */
    private void showWelcome(Ui guiUi) {
        String logo = "_    _   _ _______  ____  _   _ \n"
                + "   / \\  | \\ | |__   __|/ __ \\| \\ | |\n"
                + "  / _ \\ |  \\| |  | |  | |  | |  \\| |\n"
                + " / ___ \\| |\\  |  | |  | |__| | |\\  |\n"
                + "/_/   \\_\\_| \\_|  |_|   \\____/|_| \\_|\n";
        guiUi.showWelcome(logo);
    }

    /**
     * Handles a single user input: reads the text field, shows the user's
     * bubble, delegates to the session, and disables input if the session
     * requests exit.
     */
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

    /**
     * Loads an image resource from the classpath.
     *
     * @param path classpath to the image (e.g., {@code /images/user.png})
     * @return an {@link Image} if found; {@code null} if missing or on error
     */
    private Image loadImageSafe(String path) {
        try (InputStream is = getClass().getResourceAsStream(path)) {
            return (is == null) ? null : new Image(is);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Launches the JavaFX application.
     *
     * @param args JVM arguments passed to JavaFX launcher
     */
    public static void main(String[] args) {
        launch(args);
    }
}
