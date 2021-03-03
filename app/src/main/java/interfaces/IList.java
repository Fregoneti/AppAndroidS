package interfaces;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import models.Question;

public interface IList {

    interface View {
        void startFormularioActivity();

        void startFormularioActivity(String id);

        void startSearchActivity();

        void startAboutActivity();

        void onActivityResult(int requestCode, int resultCode, Intent data);

        void clickHelpButton();

    }

    interface Presenter {
        void onClickFloatingButton();

        void onClickQuestion(String id);

        ArrayList<Question> getQuestionByQuery(String campo, String value);

        void onClickSearchButton();

        void SetItemsFirstTime();

        List<Question> getAllQuestions();

        void onClickAboutButton();

        void onClickHelpButton();
    }
}
