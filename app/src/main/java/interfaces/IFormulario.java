package interfaces;

import android.content.Intent;

import java.util.ArrayList;

import models.Question;

public interface IFormulario {

    public interface View {

        void onGoBack();

        void resetPicture();

        void takePicture();

        void selectPicture();

        void showPermission(int n);

        void showFormuAlert(int n);

        //void AddParameters(String id);

        void refillParameters(Question q);

        void startMainActivity();


        void resetElements();

        void AddSpinner();

        void deleteQuestion(String id);

        Question searchQuestionById(String id);
    }

    public interface Presenter {


       ArrayList getSpinner();

       void onDeleteQuestion(String id);

        void goBack();

        void cleanImage();

        String getError(String error);

        void takePicture();

        void selectPicture();

        void onResetElements();

        void onClickImage();

        void onClickSave(Question question);
        void onshowFormuAlert(int n);

        void onAddParameters(String id);

        void onStartMainActivity();

        void onAddSpinner();

        Question onSearchQuestionById(String id);


    }
}
