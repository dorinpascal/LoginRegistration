package sample;



import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.File;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private ImageView myselfImageView;

    @FXML
    private Button closeButton;

    @FXML
    private Button registerButton;

    @FXML
    private Label registrationMessageLabel;

    @FXML
    private PasswordField setPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label confirmPasswordLabel;

    @FXML
    private TextField firstnameTextField;

    @FXML
    private TextField lastnameTextField;

    @FXML
    private TextField usernameTextField;


    public void initialize(URL url, ResourceBundle resourceBundle) {


        try {

            File myselfFile = new File("images/IMG_1551.JPG");
            Image myselfImage = new Image(myselfFile.toURI().toString());
            myselfImageView.setImage(myselfImage);


        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void closeOnAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();



    }

    public void registerButtonOnAction() {

        boolean userNameApprove=!usernameTextField.getText().isBlank() && !usernameTextField.getText().trim().isEmpty();
        boolean firstNameApprove=!firstnameTextField.getText().isBlank() && !firstnameTextField.getText().trim().isEmpty();
        boolean lastNameApprove=!lastnameTextField.getText().isBlank() && !lastnameTextField.getText().trim().isEmpty();
        boolean passwordApprove=!setPasswordField.getText().isBlank() && !setPasswordField.getText().trim().isEmpty();
        boolean confirmPasswordApprove=!confirmPasswordField.getText().isBlank() && !confirmPasswordField.getText().trim().isEmpty();
        Paint paint=new Color(0.8,0.2,0.1,0.6);


        if (userNameApprove && firstNameApprove && lastNameApprove && passwordApprove && confirmPasswordApprove) {

            if (setPasswordField.getText().equals(confirmPasswordField.getText())) {
                registerUser();
                confirmPasswordLabel.setText("Password match");
            } else {
                confirmPasswordLabel.setText("Passwords do not match");
            }

        }
        else {
            registrationMessageLabel.setTextFill(paint);
            registrationMessageLabel.setText("Registration unsuccessful");
        }
    }

    public void registerUser() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String firstname = firstnameTextField.getText();
        String lastname = lastnameTextField.getText();
        String username = usernameTextField.getText();
        String password = setPasswordField.getText();

        String insertFields = "INSERT INTO account.user_account (lastname, firstname, username, password) VALUES('";
        String insertValues = firstname+"','"+lastname+"','"+username+"','"+password+"')";
        String inserttoRegister = insertFields + insertValues;

        try
        {
            Statement statement=connectDB.createStatement();
            statement.executeUpdate(inserttoRegister);
            registrationMessageLabel.setText("User has been registered successfully!");

           /* try
            {
                Stage stage=(Stage) registerButton.getScene().getWindow();
                Thread.sleep(1000);
                stage.close();
            }
            catch (Exception e)
            {

            }*/




        }
            catch (Exception e)
            {
                e.printStackTrace();
                e.getCause();
            }
    }


}
