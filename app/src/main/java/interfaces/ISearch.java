package interfaces;

public interface ISearch {

    public interface View {

        void startDatePicker();

        void goBack();

        void search();
    }

    public interface Presenter {

        public void onClickDatePicker();

        public void onClickAboutButton();

        void onBack();

        void onSearch();
    }
}
