package com.example.registrationform;
import com.example.registrationform.model.RegistrationFormModel;
import com.example.registrationform.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class RegistrationFormController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    RegistrationFormModel form = new RegistrationFormModel();

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        Window owner = submitButton.getScene().getWindow();

        String errorMessage = validateFormFields(form);
        if (errorMessage == null)
        {
            form.addUser(new User(nameField.getText(),emailField.getText(),passwordField.getText()));
            nameField.clear();
            emailField.clear();
            passwordField.clear();
            AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Registration Successful!",
                    "List of users:" + form.displayUsers());
        }
        else{
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",errorMessage);
        }

    }

    private String validateFormFields(RegistrationFormModel form) {
        String errorMessage;
        if((errorMessage = RegistrationFormModel.checkNameField(nameField.getText()))!= null)return errorMessage;
        if((errorMessage = RegistrationFormModel.checkEmailField(emailField.getText()))!= null)return errorMessage;
        if((errorMessage = RegistrationFormModel.checkPasswordlField(passwordField.getText()))!= null)return errorMessage;
        return null;
    }

}
