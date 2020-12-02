package presenters;

import interfaces.IList;

public class ListPresenter implements IList.Presenter {


    private IList.View view;

    public ListPresenter(IList.View view) {
        this.view = view;
    }

    public void onClickFloatingButton() {
        view.startFormularioActivity();
    }

    @Override
    public void onClickSearchButton() {
        view.startSearchActivity();
    }

    @Override
    public void onClickAboutButton() {
        view.startAboutActivity();

    }
}
