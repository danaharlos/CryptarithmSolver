package ca.ubc.ece.cpen221.mp3.permutation;

import ca.ubc.ece.cpen221.mp3.parser.ExpressionMaker;
import ca.ubc.ece.cpen221.mp3.expression.Expression;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PermutationsTest {

    @Test
    public void permutation1Test() {

        List<Integer> ints = new ArrayList<>();
        Collections.addAll(ints, 1, 2, 3);
        String expected = "123 213 231 132 312 321 ";
        Permutation<Integer> perm = new Permutation<>(ints);
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (perm.hasNext()) {
            List<Integer> list = perm.next();

            for (Integer i : list) sb.append(i);

            sb.append(" ");
            count++;
        }

        assertEquals(expected,sb.toString());
        assertEquals(6,count);
    }

    @Test
    public void permutation2Test() {

        List<Character> permList = new ArrayList<>();
        Collections.addAll(permList, 'a', 'b');
        String expected = "ab ba ";
        Permutation<Character> perm = new Permutation<>(permList);
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (perm.hasNext()) {
            List<Character> list = perm.next();

            for (Character i : list) sb.append(i);

            sb.append(" ");
            count++;
        }
        assertEquals(expected,sb.toString());
        assertEquals(2,count);
    }

    @Test
    public void permutation3Test() {

        List<String> permList = new ArrayList<>();
        Collections.addAll(permList, "Bryson");
        String expected = "Bryson ";
        Permutation<String> perm = new Permutation<>(permList);
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (perm.hasNext()) {
            List<String> list = perm.next();

            for (String i : list) sb.append(i);

            sb.append(" ");
            count++;
        }
        assertEquals(expected,sb.toString());
        assertEquals(1,count);
    }

    @Test
    public void permutation4Test() {

        List<String> permList = new ArrayList<>();
        Collections.addAll(permList);
        String expected = " ";
        Permutation<String> perm = new Permutation<>(permList);
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (perm.hasNext()) {
            List<String> list = perm.next();

            for (String i : list) sb.append(i);

            sb.append(" ");
            count++;
        }
        assertEquals(expected,sb.toString());
        assertEquals(1,count);
    }




    @Test
    public void permutationExpTest(){
        ExpressionMaker maker = new ExpressionMaker();

        Expression numExp1 = maker.createNumberExpression(1.0);
        Expression numExp2 = maker.createNumberExpression(2.0);
        Expression numExp3 = maker.createNumberExpression(3.0);
        Expression numExp4 = maker.createNumberExpression(4.0);
        Expression numExp5 = maker.createNumberExpression(5.0);
        Expression numExp6 = maker.createNumberExpression(6.0);

        List<Expression> expList = new ArrayList<>();
        Collections.addAll(expList, numExp1,numExp2,numExp3,numExp4,numExp5,numExp6);

        Permutation<Expression> perm = new Permutation<>(expList);

        int count = 0;
        while(perm.hasNext()){
            //List<Expression> list = perm.next();
            //for(Expression exp : list) System.out.print(exp.toString() + " ");
            //System.out.println(); //Make a new line
            perm.next();
            count++;
        }

        assertEquals(720,count);
    }

    @Test
    public void permutationStringTest(){
        List<String> strings = new ArrayList<>();
        Collections.addAll(strings, "A","B","C","D","E");

        Permutation<String> perm = new Permutation<>(strings);

        int count = 0;
        while(perm.hasNext()){
            perm.next();
            count++;
        }
        assertEquals(120, count);
    }

    @Test
    public void permutationCharTest(){
        List<Character> chars = new ArrayList<>();
        Collections.addAll(chars, '0','1','2','3','4','5','6','7','8','9');

        Permutation<Character> perm = new Permutation<>(chars);

        int count = 0;
        while(perm.hasNext()){
            perm.next();
            count++;
        }
        assertEquals( 3628800,count);
    }

    @Test
    public void permutationIntTest(){
        List<Integer> ints = new ArrayList<>();
        Collections.addAll(ints, 111,0,0,0,0,0,0,0);

        Permutation<Integer> perm = new Permutation<>(ints);

        int count = 0;
        while(perm.hasNext()){
            perm.next();
            count++;
        }
        assertEquals(40320,count);
    }

}
