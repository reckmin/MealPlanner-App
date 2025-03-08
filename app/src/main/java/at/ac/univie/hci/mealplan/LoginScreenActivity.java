package at.ac.univie.hci.mealplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoginScreenActivity extends AppCompatActivity {

    private String emailInput;
    private String passwordInput;

    //useremail, userpassword
    List<Map.Entry<String, String>> usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.emailInput = "";
        this.passwordInput = "";
        this.usersList = new ArrayList<>();
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
    }

    public void signUp(View v){
        EditText emailInputText = findViewById(R.id.userNameEditText);
        EditText passwordInputText = findViewById(R.id.passwordEditText);
        emailInput = emailInputText.getText().toString();
        passwordInput = passwordInputText.getText().toString();

        //check if input ok
        try {
            checkIfEmpty();
        } catch (Exception e) {
            return;
        }

        //check if user already registered
        if( checkIsUserPresent(emailInput,passwordInput)){
            setErrorMessageText("user is already registered");
            setErrorMessageVisible();
        }
        else{
            //store user credentials
            usersList.add(new AbstractMap.SimpleEntry<>(emailInput, passwordInput));
            setErrorMessageText("Sign up successful");
            setErrorMessageVisible();
        }
    }

    private void setErrorMessageVisible(){
        TextView errorMessage = findViewById(R.id.loginInputEmptyTextView);
        errorMessage.setVisibility(View.VISIBLE);
    }

    private void setErrorMessageText(String text){
        TextView errorMessage = findViewById(R.id.loginInputEmptyTextView);
        errorMessage.setText(text);
    }

    public void launchCalendar(View v) {
        EditText emailInputText = findViewById(R.id.userNameEditText);
        EditText passwordInputText = findViewById(R.id.passwordEditText);
        emailInput = emailInputText.getText().toString();
        passwordInput = passwordInputText.getText().toString();

        //check if input is correct, otherwise dont launch calendar
        try {
            checkInput();
        } catch (Exception e) {
            return;
        }

        Intent intentLaunchMusicData = new Intent(LoginScreenActivity.this, calendarActivity.class);
        startActivity(intentLaunchMusicData);
    }

    private void applyWrongInputConsequences(String messageText){
        //show error message
        setErrorMessageText(messageText);
        setErrorMessageVisible();

        //hide keyboard
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View focus = getCurrentFocus();
        if (focus != null) {
            inputMethodManager.hideSoftInputFromWindow(focus.getWindowToken(), 0);
        }
    }

    private void checkInput() throws Exception {
        checkIfEmpty();
        checkIsUserRegistered(emailInput, passwordInput);
        checkEmailPasswordMatch(emailInput,passwordInput);
    }

    private void checkIfEmpty() throws Exception {
        if(emailInput.isEmpty()){
            applyWrongInputConsequences("please provide user information");
            throw new Exception("emailInput is empty");
        }
        if(passwordInput.isEmpty()){
            applyWrongInputConsequences("please provide user information");
            throw new Exception("passwordInput is empty");
        }
    }

    private void checkIsUserRegistered(String emailInputCheck, String passwordInputCheck) throws Exception {
        boolean isRegistered = checkIsUserPresent(emailInputCheck,passwordInputCheck);

        if(!isRegistered){
            applyWrongInputConsequences("email not found, please sign up");
            throw new Exception("User is not registered");
        }
    }
    private boolean checkIsUserPresent(String emailInputCheck, String passwordInputCheck){
        boolean isRegistered = false;
        for (Map.Entry<String, String> user : usersList) {
            if(emailInputCheck.equals(user.getKey())){
                isRegistered = true;
            }
        }
        return isRegistered;
    }
    private void checkEmailPasswordMatch(String emailInputCheck, String passwordInputCheck) throws Exception {
        boolean match = false;
        for (Map.Entry<String, String> user : usersList) {
            if(emailInputCheck.equals(user.getKey())){
                if(passwordInputCheck.equals(user.getValue())){
                    match = true;
                }
                break;
            }
        }
        if(!match){
            applyWrongInputConsequences("email and password do not match");
            throw new Exception("wrong password");
        }
    }



}