package Test;

import org.junit.Before;
import org.junit.Test;
import io.realm.Realm;
import models.Question;


public class testQuestion extends junit.framework.TestCase {

    Question q;

    @Before
    public void setUp() {
        this.q = new Question();
    }


    //TEST DE QUESTION

    @Test
    public void emailTest() {
        assertEquals(false, this.q.setMail("holagmail.com"));
        assertEquals("holagmail.com", this.q.getMail());
        assertEquals(false, this.q.setMail("hola@gmailcom"));
        assertEquals("hola@gmailcom", this.q.getMail());
        assertEquals(true, this.q.setMail("ejemplo@gmail.com"));
        assertEquals("ejemplo@gmail.com", this.q.getMail());
        assertEquals(true, this.q.setMail("hola@hotmail.es"));
        assertEquals("hola@hotmail.es", this.q.getMail());


    }

    @Test
    public void colourTest() {
        assertEquals(false, this.q.setColour(""));
        assertEquals("", this.q.getMail());
        assertEquals(true, this.q.setColour("Amarillo"));
        assertEquals("Amarillo", this.q.getMail());
        assertEquals(true, this.q.setColour("Verde"));
        assertEquals("Verde", this.q.getMail());
        assertEquals(true, this.q.setColour("Rojo"));
        assertEquals("Rojo", this.q.getMail());
    }

    @Test
    public void nameTest() {
        assertEquals(false, this.q.setName(""));
        assertEquals("", this.q.getMail());
        assertEquals(true, this.q.setName("Carlos"));
        assertEquals("Carlos", this.q.getMail());
        assertEquals(true, this.q.setName("Alba"));
        assertEquals("Alba", this.q.getMail());
        assertEquals(true, this.q.setName("Mia Khalifa"));
        assertEquals("Mia Khalifa", this.q.getMail());
    }

    @Test
    public void tittleTest() {
        assertEquals(false, this.q.setTittle(""));
        assertEquals("", this.q.getMail());
        assertEquals(true, this.q.setTittle("Rio Duero"));
        assertEquals("Rio Duero", this.q.getMail());
        assertEquals(true, this.q.setTittle("Duda vino de Montilla"));
        assertEquals("Duda vino de Montilla", this.q.getMail());
        assertEquals(true, this.q.setTittle("Java"));
        assertEquals("Java", this.q.getMail());
    }

    @Test
    public void questionTest() {
        assertEquals(false, this.q.setQuestion(""));
        assertEquals("", this.q.getMail());
        assertEquals(true, this.q.setQuestion("¿Dónde viven las carpas?"));
        assertEquals("¿Dónde viven las carpas?", this.q.getMail());
        assertEquals(true, this.q.setQuestion("¿Cuántos años tiene el Papa Francisco XIV"));
        assertEquals("¿Cuántos años tiene el Papa Francisco XIV", this.q.getMail());
        assertEquals(true, this.q.setQuestion("¿Qué hay detrás de la muerte?"));
        assertEquals("¿Qué hay detrás de la muerte?", this.q.getMail());
    }

    @Test
    public void dateTest() {
        assertEquals(false, this.q.setDate(""));
        assertEquals("", this.q.getMail());
        assertEquals(false, this.q.setDate("1900/03/11"));
        assertEquals("1900/03/11", this.q.getMail());
        assertEquals(false, this.q.setDate("03/1900/11"));
        assertEquals("03/1900/11", this.q.getMail());
        assertEquals(false, this.q.setDate("03/22/1900"));
        assertEquals("03/22/1900", this.q.getMail());
        assertEquals(true, this.q.setDate("03/11/2000"));
        assertEquals("03/11/2000", this.q.getMail());
    }


}
