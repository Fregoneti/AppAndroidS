package Test;

import org.junit.Before;
import org.junit.Test;

import io.realm.Realm;
import models.Question;
import models.QuestionModel;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class testQuestionModel {

    Question q1;
    Question q2;
    Question q3;
    QuestionModel qm;


    @Before
    public void setUp() {
        Realm realm = Realm.getDefaultInstance();
        this.qm = new QuestionModel();
        this.q1 = new Question();
        this.q2 = new Question();
        this.q3 = new Question();


        q1.setTittle("Diferencia predicado y sujeto");
        q1.setColour("Amarillo");
        q1.setMail("prueba1@prueba.es");
        q1.setDate("03/11/2000");
        q1.setName("Carlos");
        q1.setQuestion("¿Cuáles serian las diferencias entres esos dos terminos?");
        q1.setId("621SJDHS");


        q2.setTittle("IntelliJ vs NetBeans");
        q2.setColour("Rojo");
        q2.setMail("prueba2@prueba.es");
        q2.setDate("02/10/2020");
        //q2.setName("Pepe");
        q2.setQuestion("¿Cuál IDE es mejor, Netbeans o IntelliJ?");
        q2.setId("12312SDAS");

        //ESTE TIENE QUE PEtAR
        q3.setTittle("No se que poner");
        q3.setColour("Verde");
        q3.setMail("prueba3@prueba.es");
        q3.setDate("01/09/1900");
        //q3.setName("Coco");
        q3.setQuestion("¿Enserio no se que poner?");
        q3.setId("SDAS213S");




    }

    @Test
    public void QuestionModel_isQuestionTest(){
        assertTrue(qm.isQuestion(q1.getId()));
        assertTrue(qm.isQuestion(q2.getId()));
        assertTrue(qm.isQuestion(q3.getId()));

        assertFalse(qm.isQuestion("12D3123123"));
        assertFalse(qm.isQuestion("22232S22"));
        assertFalse(qm.isQuestion("81F723822"));

    }

    @Test
    public void QuestionModel_InsertTest(){
        assertTrue(qm.insertQuestionB(q1));
        assertTrue(qm.insertQuestionB(q2));

        //FALSE; NO PODEMOS VOLVER A METER EL MISMO
        assertFalse(qm.insertQuestionB(q1));
        assertFalse(qm.insertQuestionB(q2));

        //FALTA DEL NOMBRE
        assertFalse(qm.insertQuestionB(q3));

    }

    @Test
    public void QuestionModel_UpdateTest(){

    }

    @Test
    public void removeQuestionTest() {
        assertTrue(QuestionModel.removeQuestion(q1.getId()));
        assertTrue(QuestionModel.removeQuestion(q2.getId()));
        assertTrue(QuestionModel.removeQuestion(q3.getId()));

        //YA NO HAY ESTA PREGUNTA. BORRADA ANTES
        assertFalse(QuestionModel.removeQuestion(q1.getId()));
        assertFalse(QuestionModel.removeQuestion(q2.getId()));
        assertFalse(QuestionModel.removeQuestion(q3.getId()));

        //NO EXISTE Q CON ESA ID
        assertFalse(QuestionModel.removeQuestion("SADSA3312AS"));
    }

    @Test
    public void QuestionModel_SearchQuestionByIdTest(){
        assertEquals(q1,qm.searchQuestionById(q1.getId()));
        assertEquals(q2,qm.searchQuestionById(q2.getId()));
        assertEquals(q3,qm.searchQuestionById(q3.getId()));

        assertEquals(q1,qm.searchQuestionById("621SJDHS"));
        assertEquals(q1,qm.searchQuestionById("12312SDAS"));
        assertEquals(q1,qm.searchQuestionById("SDAS213S"));

        assertEquals(null,qm.searchQuestionById("999999A"));

    }
}
