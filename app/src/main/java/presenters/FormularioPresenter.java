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
    public void onClickAddSpinnerOption() {
        view.addSpinnerOption();
    }


}
