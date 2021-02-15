package models;

import android.util.Log;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;


public class QuestionModel {

    public static ArrayList<Question> getAllQuestion() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Question> result = realm.where(Question.class).findAll();

        ArrayList<Question> questionList = new ArrayList<>();
        questionList.addAll(realm.copyFromRealm(result));
        realm.close();
        return questionList;
    }

    public static void insertQuestion(Question q) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(r -> {
            realm.copyToRealm(q);
        });
        realm.close();
    }


    public boolean insertQuestionB(Question q) {
        boolean valid = false;
        if (q != null && !isQuestion(q.getId())) {
            Realm realm = Realm.getDefaultInstance();
            try {
                realm.beginTransaction();
                realm.copyToRealm(q);
                realm.commitTransaction();
                realm.close();
                valid = true;
            } catch (Exception e) {
                e.printStackTrace();
                realm.close();
                valid = false;
            }
        }
        return valid;
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

    public static boolean isQuestion(String id) {
        boolean valid = false;
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            valid = realm.where(Question.class).equalTo("id", id).findAll().size() != 0;
            realm.commitTransaction();
            realm.close();
        } catch (Exception e) {
            e.printStackTrace();
            realm.close();
        }
        return valid;
    }

    public static boolean updateUser(Question question) {
        boolean valid = false;
        Realm realm = Realm.getDefaultInstance();
        if (question != null && isQuestion(question.getId())) {
            try {
                realm.beginTransaction();
                realm.insertOrUpdate(question);
                realm.commitTransaction();
                realm.close();
                valid = true;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("PEta");
                realm.close();
                valid = false;
            }
        }
        return valid;
    }

    public static ArrayList<String> getSpinner() {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Question> questions = new ArrayList<>();
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            RealmResults<Question> rrealm = realm.where(Question.class).distinct("mode").findAll();
            questions.addAll(realm.copyFromRealm(rrealm));
            realm.commitTransaction();
            realm.close();
        } catch (Exception e) {
            e.printStackTrace();
            realm.close();
        }
        for (Question q : questions) {
            if (q.getMode() != null) {
                result.add(q.getMode());
            }
        }
        return result;
    }


}
