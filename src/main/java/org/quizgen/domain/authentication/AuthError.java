package org.quizgen.domain.authentication;

public enum AuthError {
    FIELD_IS_BLANK("All fields must be filled out!"),
    PASSWORD_NOT_MATCH("Passwords do not match!"),
    WHITESPACE_NOT_ALLOWED("No whitespace allowed!"),
    NO_ERROR(""),
    USERNAME_EXISTS("Username already exists!"),

    PW_MUST_BE_ALPHANUMERIC("Password must contain at least 1 letter or number"),
    PW_MUST_HAVE_SPECIAL_CHARACTER("Password must contain a special character"),
    PW_MUST_BE_UPPERLOWER_CASED("Password must contain upper and lowercase letters"),
    PW_MUST_BE_MIN_LENGTH("Password must be at least " + PasswordAuth.MIN_LENGTH_PASSWORD + " characters"),

    INVALID_CREDENTIALS("Wrong username or password"),
    RESET_PW_ERROR("Username and password not found!"),
    PASSWORD_SHOULD_NOT_MATCH("New password should not match old password!");


    private final String errorMessage;

    AuthError(String errorMessage){
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString(){
        return this.errorMessage;
    }
}
