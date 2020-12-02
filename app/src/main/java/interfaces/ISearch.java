package interfaces;

public interface ISearch {

    public interface View {

        void startDatePicker();
        void startAboutActivity();
        void goBack();
    }

    public interface Presenter {
        void onClickDatePicker();
        void onClickAboutButton();
        void onBack();
    }
}
