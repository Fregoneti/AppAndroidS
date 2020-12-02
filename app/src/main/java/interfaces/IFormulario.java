package interfaces;

public interface IFormulario {

    public interface View {


        void startAboutActivity();
    }

    public interface Presenter {


        void onClickAboutButton();

        void onGetError();


    }
}
