package ca.ubc.ece.cpen221.mp3.cryptarithm;

import ca.ubc.ece.cpen221.mp3.cryptarithm.Cryptarithm;
import ca.ubc.ece.cpen221.mp3.cryptarithm.InvalidCryptarithmException;
import ca.ubc.ece.cpen221.mp3.cryptarithm.NoSolutionException;
import ca.ubc.ece.cpen221.mp3.cryptarithm.SolveCryptarithm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CryptarithmTest {

    /*
    The following lines of code (lines 28 - 40) were taken from:
    https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println?fbclid=IwAR1Gft69frSMJHSOTRtQrR8BLyUISo5SwlOqkkeJbM2IdqPuBA0_eVYa6BU
     */
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }




    /**
     * Solves cryptarhithm SEND+MORE=MONEY for 1 solution
     */
    @Test
    public void cryptBasicTest1() {
        String[] crypt= {"SEND","+","MORE","=","MONEY"};
        try {
            Cryptarithm basicTest1 = new Cryptarithm(crypt);
            Map<Character, Integer> pair= new HashMap<>();
            List<Map<Character, Integer>> expected = new ArrayList<>();
            pair.put('S', 9);
            pair.put('E', 5);
            pair.put('N', 6);
            pair.put('D', 7);
            pair.put('M', 1);
            pair.put('O', 0);
            pair.put('R', 8);
            pair.put('Y', 2);
            expected.add(pair);

            List<Map<Character, Integer>> actual = basicTest1.solve();
            assertTrue(actual.contains(pair));
            //assertEquals(expected, actual)

        }catch(NoSolutionException e){
            System.out.println(e.getMessage());
        }catch(InvalidCryptarithmException e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * Tests exception for two operands immediately next to each other
     * @throws InvalidCryptarithmException
     */
    @Test(expected = InvalidCryptarithmException.class)
    public void cryptInvalid1() throws InvalidCryptarithmException{
        String[] crypt= {"SUN", "FUN", "=", "SWIM"};
        Cryptarithm invalid1 = new Cryptarithm(crypt);
        fail();

    }

    /**
     * Tests exception for having a non alphabetic value as a variable name
     * @throws InvalidCryptarithmException
     */
    @Test
    public void cryptInvalid2() {
        String[] crypt= {"SUN", "+", "F9N", "=", "SWIM"};
        try {
            Cryptarithm invalid2 = new Cryptarithm(crypt);
        }catch(Exception e){
            assertEquals("Invalid symbol: 9", e.getMessage());
        }

    }

    /**
     * Checks exception for two equals sign
     * @throws InvalidCryptarithmException
     */
    @Test(expected = InvalidCryptarithmException.class)
    public void cryptInvalid3() throws InvalidCryptarithmException{
        String[] crypt= {"SUN", "+", "FUN", "=", "SWIM", "=", "MOON"};
        Cryptarithm invalid3 = new Cryptarithm(crypt);
        fail();
    }

    /**
     * checks exception for two operands immediately next to each other after equals sign
     * @throws InvalidCryptarithmException
     */
    @Test(expected = InvalidCryptarithmException.class)
    public void cryptInvalid4() throws InvalidCryptarithmException{
        String[] crypt= {"SUN", "+", "FUN", "=", "SWIM", "MOON"};
        Cryptarithm invalid4 = new Cryptarithm(crypt);
        fail();
    }

    /**
     * Checks exception for cryptarithm begining with an operator
     * @throws InvalidCryptarithmException
     */
    @Test(expected = InvalidCryptarithmException.class)
    public void cryptInvalid5() throws InvalidCryptarithmException {
        String[] crypt= {"-", "SUN", "+", "FUN", "=", "SWIM"};
        Cryptarithm invalid5 = new Cryptarithm(crypt);

        fail();
    }

    @Test(expected = InvalidCryptarithmException.class)
    public void cryptInvalid6() throws InvalidCryptarithmException{
        String[] crypt= {"SUN", "+", "FUN", "-", "SWIM"};
        Cryptarithm invalid6 = new Cryptarithm(crypt);

        fail();
    }

    /**
     * Checks exception for cryptarithm ending with operator
     * @throws InvalidCryptarithmException
     */
    @Test(expected = InvalidCryptarithmException.class)
    public void cryptInvalid7() throws InvalidCryptarithmException{
        String[] crypt= {"SUN", "+", "FUN", "-", "SWIM", "+"};
        Cryptarithm invalid7 = new Cryptarithm(crypt);
        fail();
    }

    /**
     * Checks exception for cryptarithm with more than 10 variables
     * @throws InvalidCryptarithmException
     */
    @Test(expected = InvalidCryptarithmException.class)
    public void cryptInvalid8() throws InvalidCryptarithmException{
        String[] crypt= {"SUN", "+", "FUN", "=", "SWIM", "-", "GERBALKID"};
        Cryptarithm invalid8 = new Cryptarithm(crypt);

        fail();
    }

    /**
     * Solves Cryptarithm WINTER+IS+WINDIER+SUMMER+IS=SUNNIER
     * for 1 solution
     */
    @Test
    public void cryptBasicTest2_1sol() {
        String[] crypt= {"WINTER", "+" ,"IS", "+", "WINDIER", "+", "SUMMER", "+", "IS", "=", "SUNNIER"};
        try {
            Cryptarithm basicTest2 = new Cryptarithm(crypt);
            Map<Character, Integer> pair= new HashMap<>();
            List<Map<Character, Integer>> expected = new ArrayList<>();
            pair.put('W', 7);
            pair.put('I', 6);
            pair.put('N', 0);
            pair.put('T', 2);
            pair.put('E', 8);
            pair.put('R', 1);
            pair.put('S', 9);
            pair.put('D', 4);
            pair.put('U', 3);
            pair.put('M', 5);
            expected.add(pair);


            List<Map<Character, Integer>> actual = basicTest2.solve();
            //assertTrue(actual.contains(pair));
            assertEquals(expected, actual);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * Solves Cryptarithm JEDER+LIEBT=BERLIN
     * for 2 solutions
     */
    @Test
    public void cryptBasicTest3_2sol() {
        String[] crypt= {"JEDER", "+" ,"LIEBT", "=", "BERLIN"};
        try {
            Cryptarithm basicTest3 = new Cryptarithm(crypt);
            Map<Character, Integer> pair= new HashMap<>();
            Map<Character, Integer> pair2= new HashMap<>();
            List<Map<Character, Integer>> expected = new ArrayList<>();
            pair.put('J', 6);
            pair.put('E', 3);
            pair.put('D', 4);
            pair.put('R', 8);
            pair.put('L', 7);
            pair.put('I', 5);
            pair.put('B', 1);
            pair.put('T', 2);
            pair.put('N', 0);
            expected.add(pair);

            pair2.put('J', 4);
            pair2.put('E', 3);
            pair2.put('D', 6);
            pair2.put('R', 8);
            pair2.put('L', 9);
            pair2.put('I', 5);
            pair2.put('B', 1);
            pair2.put('T', 2);
            pair2.put('N', 0);
            expected.add(pair2);

            List<Map<Character, Integer>> actual = basicTest3.solve();

            assertEquals(expected, actual);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * Solves cryptarithm NORTH/SOUTH=EAST/WEST
     * for 1 solution
     */
    @Test
    public void cryptBasicTest4_1sol() {
        String[] crypt= {"NORTH", "/", "SOUTH", "=", "EAST", "/", "WEST"};
        try {
            Cryptarithm basicTest4 = new Cryptarithm(crypt);
            Map<Character, Integer> pair= new HashMap<>();
            List<Map<Character, Integer>> expected = new ArrayList<>();
            pair.put('N', 5);
            pair.put('O', 1);
            pair.put('R', 3);
            pair.put('T', 0);
            pair.put('H', 4);
            pair.put('S', 6);
            pair.put('U', 9);
            pair.put('E', 7);
            pair.put('A', 2);
            pair.put('W', 8);
            expected.add(pair);

            List<Map<Character, Integer>> actual = basicTest4.solve();
            assertEquals(expected, actual);

        }catch(NoSolutionException e){
            System.out.println(e.getMessage());
        }catch(InvalidCryptarithmException e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * Tries to solve I+CANT+GET+NO=SATISFACTION
     * @throws NoSolutionException
     */
    @Test
    public void cryptBasicTest5_0sol(){
        String[] crypt= {"I", "+", "CANT", "+", "GET", "+", "NO", "=", "SATISFACTION"};
        try {
            Cryptarithm basicTest5 = new Cryptarithm(crypt);
            basicTest5.solve();

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tries to solve cryptarithm
     * @throws NoSolutionException
     */
    @Test
    public void cryptBasicTest6_0sol(){
        String[] crypt= {"I", "+", "W", "=", "SATISFACTION", "+", "K"};
        try {
            Cryptarithm basicTest6 = new Cryptarithm(crypt);
            basicTest6.solve();

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void cryptBasicTest7_0sol(){
        String[] crypt= {"I", "+", "W", "=", "SATISFACTION", "-", "K"};
        try {
            Cryptarithm basicTest7 = new Cryptarithm(crypt);
            basicTest7.solve();

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tries to solve cryptarithm
     * @throws NoSolutionException
     */
    @Test
    public void cryptBasicTest8_0sol(){
        String[] crypt= {"I", "+", "W", "=", "SATISFACTION", "*", "K"};
        try {
            Cryptarithm basicTest8 = new Cryptarithm(crypt);
            basicTest8.solve();

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tries to solve cryptarithm
     * @throws NoSolutionException
     */
    @Test
    public void cryptBasicTest9_0sol(){
        String[] crypt= {"I", "*", "W", "=", "SATISFACTION", "*", "K"};
        try {
            Cryptarithm basicTest9 = new Cryptarithm(crypt);
            basicTest9.solve();

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tries to solve cryptarithm
     * @throws NoSolutionException
     */
    @Test
    public void cryptBasicTest10_0sol(){
        String[] crypt= {"I", "-", "W", "=", "SATISFACTION", "*", "K"};
        try {
            Cryptarithm basicTest10 = new Cryptarithm(crypt);
            basicTest10.solve();

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void SolveTest(){
        String[] crypt = {"JEDER","+","LIEBT","=","BERLIN"};
        String expected = "2 solution(s): [{R=8, B=1, D=4, T=2, E=3, I=5, J=6, L=7, N=0}, {R=8, B=1, D=6, T=2, E=3, I=5, J=4, L=9, N=0}]";
        SolveCryptarithm.main(crypt);
        assertEquals(expected, outContent.toString());

    }

    @Test
    public void SolveTest2(){
        String[] crypt = {"SEND","+","MORE","=","MONEY"};

        String expected = "1 solution(s): [{R=8, S=9, D=7, E=5, Y=2, M=1, N=6, O=0}]";
        SolveCryptarithm.main(crypt);
        assertEquals(expected, outContent.toString());
    }

    @Test()
    public void SolveTest3() throws InvalidCryptarithmException{
        String[] crypt = {"SEND","+","MORE","=","=","MONEY"};
        SolveCryptarithm.main(crypt);
    }

}