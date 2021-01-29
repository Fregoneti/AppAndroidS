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
    }
}
