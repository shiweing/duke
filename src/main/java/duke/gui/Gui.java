package duke.gui;

import duke.logic.Duke;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Gui extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Peasant.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    /**
     * Initialise GUI displayed.
     */
    @FXML
    public void initialize() {
        sendButton.setOnKeyReleased((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        greet();

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        Platform.runLater(() -> userInput.requestFocus());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Greeting message from system at start up.
     */
    public void greet() {
        String usage = "Usage:\n"
                + "list\n"
                + "todo [task description]\n"
                + "deadline [task description] --by [deadline]\n"
                + "event [event description] --at [event time]\n"
                + "find [keyword]\n"
                + "done [task no...]\n"
                + "delete [task no...]"
                + "update [task id] {--desc [task description]} {--by [deadline]} {--at [event time]}  ";

        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Hello! I'm duke.util.Duke! What can I do for you?\n" + usage,
                        dukeImage, "Duke")
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage, "Peasant"),
                DialogBox.getDukeDialog(response, dukeImage, "Duke")
        );
        userInput.clear();

        if (duke.isExit()) {
            Platform.exit();
        }
    }
}
