package presenters;

import interfaces.IFormulario;
import interfaces.IHelp;

public class HelpPresenter implements IHelp.Presenter {

    String TAG = "Foro/HelpPresenter";
    private IHelp.View view;
    @Override
    public void onErrorConnection() {
        view.errorConnection();

    }
}
