package presenters;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.example.demo.R;

import interfaces.IFormulario;
import views.App;

public class FormularioPresenter implements IFormulario.Presenter {


    String TAG = "Foro/ListPresenter";
    private IFormulario.View view;

    public FormularioPresenter (IFormulario.View view) {
        this.view = view;
    }




    public void goBack(){
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
            default:error_msg="";

        }
        return error_msg;
    }

    @Override
    public void takePicture() {
        view.takePicture();
    }

    @Override
    public void onClickAddSpinnerOption() {
        view.addSpinnerOption();
    }

    @Override
    public void selectPicture(){
        view.selectPicture();
    }

    @Override
    public void cleanImage(){view.resetPicture();};

    @Override
    public void onClickImage() {
        int WriteExternalStoragePermission = ContextCompat.checkSelfPermission(App.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        Log.d(TAG, "clickImage" + WriteExternalStoragePermission);
        if (WriteExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
            // Permiso denegado
            // A partir de Marshmallow (6.0) se pide aceptar o rechazar el permiso en tiempo de ejecución
            // En las versiones anteriores no es posible hacerlo
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                view.showPermission(0);
                // Una vez que se pide aceptar o rechazar el permiso se ejecuta el método "onRequestPermissionsResult" para manejar la respuesta
                // Si el usuario marca "No preguntar más" no se volverá a mostrar este diálogo
            } else {
                view.showPermission(1);
            }
        } else {
            // Permiso aceptado
            view.selectPicture();
        }
    }






}
