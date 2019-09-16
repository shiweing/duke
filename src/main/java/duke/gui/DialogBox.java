package duke.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Circle displayPicture;
    @FXML
    private Label imageLabel;

    /**
     * Constructor for DialogBox.
     * @param text dialog message.
     * @param img profile picture.
     * @param imgLabel identity name.
     */
    public DialogBox(String text, Image img, String imgLabel) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setFill(new ImagePattern(img));
        imageLabel.setText(imgLabel);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Returns a DialogBox for user.
     * @param text input from user.
     * @param img profile picture of user.
     * @param imgLabel name of user.
     * @return user DialogBox.
     */
    public static DialogBox getUserDialog(String text, Image img, String imgLabel) {
        return new DialogBox(text, img, imgLabel);
    }

    /**
     * Returns a system DialogBox.
     * @param text response from system.
     * @param img profile picture of system.
     * @param imgLabel name of system.
     * @return system DialogBox.
     */
    public static DialogBox getDukeDialog(String text, Image img, String imgLabel) {
        var db = new DialogBox(text, img, imgLabel);
        db.getStyleClass().add("duke-style");
        db.flip();
        return db;
    }
}