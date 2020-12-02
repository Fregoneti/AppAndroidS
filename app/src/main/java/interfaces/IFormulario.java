package interfaces;

public interface IFormulario {

    public interface View {

        void onGoBack();

        void addSpinnerOption();
    }

    public interface Presenter {

        void goBack();

        String getError(String error);


        void onClickAddSpinnerOption();
    }
}
