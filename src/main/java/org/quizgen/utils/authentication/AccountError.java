package org.quizgen.utils.authentication;

public enum AccountError {
    FIELD_IS_BLANK("All fields must be filled out!"),
    PASSWORD_NOT_MATCH("Passwords do not match!"),
    WHITESPACE_NOT_ALLOWED("No whitespace allowed!"),
    NO_ERROR(""),
    USERNAME_EXISTS("Username already exists!"),

    PW_MUST_BE_ALPHANUMERIC("Password must contain at least 1 letter or number"),
    PW_MUST_HAVE_SPECIAL_CHARACTER("Password must contain a special character"),
    PW_MUST_BE_UPPERLOWER_CASED("Password must contain upper and lowercase letters"),
    PW_MUST_BE_MIN_LENGTH("Password must be at least " + PasswordPolicy.MIN_LENGTH_PASSWORD + " characters"),

    INVALID_CREDENTIALS("Wrong username or password");


    private final String errorMessage;

    AccountError(String errorMessage){
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString(){
        return this.errorMessage;
    }
}
