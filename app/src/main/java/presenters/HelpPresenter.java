package presenters;

import interfaces.IHelp;

public class HelpPresenter implements IHelp.Presenter {

    String TAG = "Foro/HelpPresenter";
    private IHelp.View view;

    public HelpPresenter(IHelp.View view) {
        this.view = view;
    }

    @Override
    public void onErrorConnection() {
        view.errorConnection();

    }
}
