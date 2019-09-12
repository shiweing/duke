package duke.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private Label imageLabel;

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
        displayPicture.setImage(img);
        imageLabel.setText(imgLabel);
        displayPicture.setClip(new Circle(30,30,30));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String text, Image img, String imgLabel) {
        return new DialogBox(text, img, imgLabel);
    }

    public static DialogBox getDukeDialog(String text, Image img, String imgLabel) {
        var db = new DialogBox(text, img, imgLabel);
        Node dukeDialog = db.getChildren().get(0);
        dukeDialog.setStyle(dukeDialog.getStyle() + "-fx-background-color: #93b5b3; -fx-text-fill:  #f2f6f5");
        db.flip();
        return db;
    }
}