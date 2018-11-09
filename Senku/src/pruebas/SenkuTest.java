package pruebas;
import aplicacion.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SenkuTest.
 *
 * @author  (Natalia Palacios)
 * @version (8/11/2018)
 */
public class SenkuTest
{
    
    /**
     * Default constructor for test class AutomataCelularTest
     */
    public SenkuTest()
    {
        
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }
    
    @Test
    public void deberiaCrearBienLaMatriz()
    { 
        Senku s = new Senku();
        s.iniciaMatriz();
        char[][] m = s.getMatriz();
        for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				if(i == 3 && j == 3) {
					assertEquals(m[i][j],'v');
				}else if((j >= 2 && j < 5) || (i >= 2 && i < 5)) {
					assertEquals(m[i][j],'b');
				}else {
					assertEquals(m[i][j],'p');
				}
			}
		}
    }
    
    @Test
    public void deberiaMoverALaDerecha() {
    	Senku s = new Senku();
        s.iniciaMatriz();
        char[][] m = s.getMatriz();
        s.derecha(3, 1);
        assertEquals(m[3][1],'v');
        assertEquals(m[3][2],'v');
        assertEquals(m[3][3],'b');
    }
    
    @Test
    public void noDeberiaMoverALaDerecha() {
    	Senku s = new Senku();
        s.iniciaMatriz();
        char[][] m = s.getMatriz();
        s.derecha(2, 1);
        assertEquals(m[2][1],'b');
        assertEquals(m[2][2],'b');
        assertEquals(m[2][3],'b');
    }
    
    @Test
    public void deberiaMoverALaIzquierda() {
    	Senku s = new Senku();
        s.iniciaMatriz();
        char[][] m = s.getMatriz();
        s.izquierda(3, 5);
        assertEquals(m[3][5],'v');
        assertEquals(m[3][4],'v');
        assertEquals(m[3][3],'b');
    }
    
    @Test
    public void noDeberiaMoverALaIzquierda() {
    	Senku s = new Senku();
        s.iniciaMatriz();
        char[][] m = s.getMatriz();
        s.izquierda(2, 5);
        assertEquals(m[2][5],'b');
        assertEquals(m[2][4],'b');
        assertEquals(m[2][3],'b');
    }
    
    @Test
    public void deberiaMoverHaciaArriba() {
    	Senku s = new Senku();
        s.iniciaMatriz();
        char[][] m = s.getMatriz();
        s.arriba(5, 3);
        assertEquals(m[5][3],'v');
        assertEquals(m[4][3],'v');
        assertEquals(m[3][3],'b');
    }
    
    @Test
    public void noDeberiaMoverHaciaArriba() {
    	Senku s = new Senku();
        s.iniciaMatriz();
        char[][] m = s.getMatriz();
        s.arriba(5, 2);
        assertEquals(m[5][2],'b');
        assertEquals(m[4][2],'b');
        assertEquals(m[3][2],'b');
    }
    
    @Test
    public void deberiaMoverHaciaAbajo() {
    	Senku s = new Senku();
        s.iniciaMatriz();
        char[][] m = s.getMatriz();
        s.abajo(1, 3);
        assertEquals(m[1][3],'v');
        assertEquals(m[2][3],'v');
        assertEquals(m[3][3],'b');
    }
    
    @Test
    public void noDeberiaMoverHaciaAbajo() {
    	Senku s = new Senku();
        s.iniciaMatriz();
        char[][] m = s.getMatriz();
        s.abajo(1, 2);
        assertEquals(m[1][2],'b');
        assertEquals(m[2][2],'b');
        assertEquals(m[3][2],'b');
    }
            
   
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
}
