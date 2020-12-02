package presenters;

import com.example.demo.R;

import interfaces.IFormulario;
import views.App;

public class FormularioPresenter implements IFormulario.Presenter {


    String TAG = "Foro/ListPresenter";
    private IFormulario.View view;

    public FormularioPresenter (IFormulario.View view) {
        this.view = view;
    }


    @Override
    public void onClickSearchButton() {

    }

    @Override
    public void onClickAboutButton() {

    }

    @Override
    public String onGetError(String error_code) {
        String error_msg = "";
        switch (error_code) {
            case "QuestionName":
                error_msg = App.getContext().getResources().getString(R.string.name_error);
                break;
        }
        return error_msg;
    }
}
