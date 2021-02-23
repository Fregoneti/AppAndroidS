package presenters;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.example.demo.R;

import java.util.ArrayList;

import interfaces.IFormulario;
import models.Question;
import models.QuestionModel;
import views.App;

public class FormularioPresenter implements IFormulario.Presenter {


    String TAG = "Foro/ListPresenter";
    private IFormulario.View view;
    Question question;

    public FormularioPresenter(IFormulario.View view) {
        this.view = view;
    }


    @Override
    public ArrayList getSpinner() {
        return QuestionModel.getSpinner();
    }

    @Override
    public void onDeleteQuestion(String id) {
        view.deleteQuestion(id);
    }

    public void goBack() {
        view.onGoBack();
    }

    @Override
    public String getError(String error_code) {
        String error_msg = "";
        switch (error_code) {
            case "Name":
                error_msg = App.getContext().getResources().getString(R.string.name_error);
                break;
            case "Date":
                error_msg = App.getContext().getResources().getString(R.string.date_error);
                break;
            case "Mail":
                error_msg = App.getContext().getResources().getString(R.string.mail_error);
                break;
            case "Tittle":
                error_msg = App.getContext().getResources().getString(R.string.tittle_error);
                break;
            case "Colour":
                error_msg = App.getContext().getResources().getString(R.string.colour_error);
                break;
            case "Question":
                error_msg = App.getContext().getResources().getString(R.string.question_error);
                break;
            default:
                error_msg = "Error no se sabe donde";

        }
        return error_msg;
    }

    @Override
    public void takePicture() {
        view.takePicture();
    }



    @Override
    public void selectPicture() {
        view.selectPicture();
    }

    @Override
    public void onResetElements() {
        Log.d(TAG, "Resetting elements: ");
        view.resetElements();
    }

    @Override
    public void cleanImage() {
        view.resetPicture();
    }

    @Override
    public void onClickImage() {
        int WriteExternalStoragePermission = ContextCompat.checkSelfPermission(App.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        Log.d(TAG, "clickImage" + WriteExternalStoragePermission);
        if (WriteExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                view.showPermission(0);

            } else {
                view.showPermission(1);
            }
        } else {

            view.selectPicture();
        }
    }

    @Override
    public void onClickSave(Question question) {

        Log.d(TAG, "Save Button Clicked");

        if (QuestionModel.isQuestion(question.getId()) == true) {
            System.out.println(question.getId());
            QuestionModel.updateQuestion(question);
        } else {
            QuestionModel.insertQuestion(question);
        }

    }

    @Override
    public void onshowFormuAlert(int n) {
        Log.d(TAG, "Show alert form");
        view.showFormuAlert(n);
    }

    @Override
    public void onAddParameters(String id) {
        Log.d(TAG, "Refilling parameters");


        view.refillParameters(QuestionModel.searchQuestionById(id));


    }

    @Override
    public void onStartMainActivity() {
        view.startMainActivity();
    }

    @Override
    public void onAddSpinner() {
        view.AddSpinner();
    }

    @Override
    public Question onSearchQuestionById(String id) {
        return QuestionModel.searchQuestionById(id);

    }


}
