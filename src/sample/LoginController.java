package sample;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button cancelButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private ImageView brandingImageView;

    @FXML
    private ImageView lockImageView;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField enterPasswordField;

    @FXML
    private Button registrationButton;

    @Override
    public void initialize(URL urk, ResourceBundle resourceBundle) {
        try {

            File brandingFile = new File("images/IMG_1551.JPG");
            Image brandingImage = new Image(brandingFile.toURI().toString());
            brandingImageView.setImage(brandingImage);

            File lockFile = new File("images/IMG_0776.JPG");
            Image lockImage = new Image(lockFile.toURI().toString());
            lockImageView.setImage(lockImage);
        } catch (NullPointerException e) {
            e.printStackTrace();

        }

    }


    public void cancelButtonOnAction() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void loginButtonOnAction() {
        if (((!usernameTextField.getText().isBlank()) && (!enterPasswordField.getText().isBlank())) && !usernameTextField.getText().trim().isEmpty()) {


            validateLogin();
            usernameTextField.setText("");
            enterPasswordField.setText("");
        } else loginMessageLabel.setText("Please enter a valid username and password");


    }


    public void validateLogin() {

        DatabaseConnection connection = new DatabaseConnection();
        Connection connectionDB = connection.getConnection();

        String verifyLogin = "SELECT count(1) FROM account.user_account WHERE username='" + usernameTextField.getText() + "' AND password='" + enterPasswordField.getText() + "'";


        try {


            Statement statement = connectionDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            Paint paint = new Color(1, 0.7, 0.1, 0.9);

            while (queryResult.next()) {

                if (queryResult.getInt(1) == 1) {
                    System.out.println("safaga");

                    loginMessageLabel.setTextFill(paint);
                    loginMessageLabel.setText("Login successful");


                } else {

                    System.out.println("sgag");
                    loginMessageLabel.setText("Invalid login.Please try again");

                }


            }

        } catch (NullPointerException e) {
            e.getCause();
            System.out.println("null");
        } catch (SQLException e) {
            e.getCause();
            System.out.println("sql");
        }

    }

    public void createAccountForm() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 600, 511));
            registerStage.show();


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void registrationLabelActionHandler() {
        try {
            createAccountForm();
        }
        catch (Exception e) {
            e.getCause();
        }
    }

}
