package interfaces;

public interface ISearch {

    public interface View {

        void startDatePicker();

        void goBack();

        void search();

        void clickHelpButton();
    }

    public interface Presenter {

        public void onClickDatePicker();

        public void onClickAboutButton();

        void onBack();

        void onSearch();

        void onClickHelpButton();
    }
}
