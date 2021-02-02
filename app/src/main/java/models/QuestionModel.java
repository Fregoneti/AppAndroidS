package models;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;


public class QuestionModel {

    public static ArrayList<Question> getAllQuestion(){
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

    public static boolean removeQuestion(String id) {
        boolean valid = false;
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
           RealmResults<Question> q = realm.where(Question.class).equalTo("id", id).findAll();

            q.deleteAllFromRealm();
            realm.commitTransaction();
            realm.close();
            valid = true;
        } catch (Exception e) {
            e.printStackTrace();
            realm.close();
            valid = false;
        }
        return valid;
    }


    public static Question searchQuestionById(String id) {
        Question question = null;
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            question = realm.copyFromRealm(realm.where(Question.class).equalTo("id", id).findFirst());
            realm.commitTransaction();
            realm.close();
        } catch (Exception e) {
            e.printStackTrace();
            realm.close();
        }
        return question;
    }



}
