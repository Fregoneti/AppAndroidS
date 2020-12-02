package interfaces;

public interface IList {

    public interface View {
        void startFormularioActivity();

        void startSearchActivity();

        void startAboutActivity();
    }

    public interface Presenter {
        void onClickFloatingButton();

        void onClickSearchButton();

        void onClickAboutButton();
    }
}
