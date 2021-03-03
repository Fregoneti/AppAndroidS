package interfaces;

import java.util.ArrayList;

import models.Question;

public interface IHelp {

    public interface View {
        void errorConnection();

    }

    public interface Presenter {


        void onErrorConnection();
    }
}
