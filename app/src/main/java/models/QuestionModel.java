package models;

import java.util.ArrayList;
import io.realm.Realm;
import io.realm.RealmResults;


public class QuestionModel {

    public ArrayList<Question> getAllQuestion(){
        Realm realm=Realm.getDefaultInstance();
        RealmResults<Question> result=realm.where(Question.class).findAll();

        ArrayList<Question> questionList=new ArrayList<>();
        questionList.addAll(realm.copyFromRealm(result));
        realm.close();
        return questionList;
    }

    public static void insertQuestion(Question q){
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(r -> {
            realm.copyToRealm(q);
        });
        realm.close();
    }



}
