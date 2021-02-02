package interfaces;

import android.content.Intent;

import models.Question;

public interface IFormulario {

    public interface View {

        void onGoBack();

        void resetPicture();

        void addSpinnerOption();

        void takePicture();

        void selectPicture();

        void showPermission(int n);

        void showFormuAlert(int n);

        //void AddParameters(String id);

        void refillParameters(Question q);

        void startMainActivity();
    }

    public interface Presenter {


        void goBack();

        void cleanImage();

        String getError(String error);

        void takePicture();

        void selectPicture();

        void onClickAddSpinnerOption();

        void onClickImage();

        void onClickSave(Question question);
        void onshowFormuAlert(int n);

        void onAddParameters(String id);

        void onStartMainActivity();


    }
}
