package org.quizgen.utils.login;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SignUpValidationFX {

    private TextField username;
    private TextField password;
    private TextField rePassword;
    private UsernameValidationFX usernameValidator;
    private PasswordValidationFX passwordValidator;
    private String usernameError;
    private String passwordError;
    private String fieldsBlankError;
    private String passwordMatchesError;


    public SignUpValidationFX(TextField username, TextField password, TextField rePassword){
        this.username = username;
        this.password = password;
        this.rePassword = rePassword;
        this.usernameValidator = new UsernameValidationFX(username);
        this.passwordValidator = new PasswordValidationFX(password);
        this.usernameError = usernameValidator.usernameIsTakenError();
        this.passwordError = passwordValidator.passwordRegisterError();
        this.fieldsBlankError = "All fields must be filled out!";
        this.passwordMatchesError = "Passwords do not match!";
    }

    public boolean signUpSuccessful(){
        return setErrorMessage().equals("");
    }

    public String setErrorMessage(){
        if(fieldIsBlank(username) || fieldIsBlank(password) || fieldIsBlank(rePassword)){
            return fieldsBlankError;
        }
        else if(!passwordsMatches(password, rePassword)){
            return passwordMatchesError;
        }
        else if(!usernameValidator.usernameRegisterSuccessful()){
            return usernameError;
        }
        else if(!passwordValidator.passwordRegisterSuccessful()){
            return passwordError;
        }
        else {
            return "";
        }
    }

    private boolean fieldIsBlank(TextField field){
        return field.getText().isBlank();
    }

    private boolean passwordsMatches(TextField password, TextField reenterPassword){
        return password.getText().equals(reenterPassword.getText());
    }

}
