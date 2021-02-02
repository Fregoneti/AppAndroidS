package interfaces;

import java.util.List;

import models.Question;

public interface IList {

    public interface View {
        void startFormularioActivity();

        void startFormularioActivity(String id);

        void startSearchActivity();

        void startAboutActivity();


    }

    public interface Presenter {
        void onClickFloatingButton();
        void onClickQuestion(String id);

        void onClickSearchButton();
        void SetItemsFirstTime();

        List<Question> getAllQuestions();

        void onClickAboutButton();
    }
}
