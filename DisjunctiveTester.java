package lexer;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.*;
public class DisjunctiveTester{
    /*
    DisjunctiveLexer lexer;
    DisjunctiveExpression expression;
    
    @Test
    public void simpleTest() throws ParserException{
        lexer = new DisjunctiveLexer("a");
        expression = DisjunctiveExpression.Builder.build(lexer.nextValid().get(), lexer);
        assertEquals("true, a", expression.toString());  
    }
    
    @Test
    public void intermediateTest() throws ParserException{
        lexer = new DisjunctiveLexer("(a and b)");
        expression = DisjunctiveExpression.Builder.build(lexer.nextValid().get(), lexer);
        assertEquals("leftExpression:(true, a), rightExpression:(true, b)", expression.toString());  
    }
    
    @Test
    public void complexTest() throws ParserException{
        lexer = new DisjunctiveLexer("((a and not b) and not c)");
        expression = DisjunctiveExpression.Builder.build(lexer.nextValid().get(), lexer);
        assertEquals("leftExpression:(leftExpression:(true, a), rightExpression:(false, b)), rightExpression:(false, c)", expression.toString());  
    }
 
    @Test(expected = ParserException.class)
    public void invalidTest() throws ParserException{
        lexer = new DisjunctiveLexer("((a and not 123) and not c)");
        expression = DisjunctiveExpression.Builder.build(lexer.nextValid().get(), lexer); 
    }
    
    @Test
    public void negateTest() throws ParserException{
        lexer = new DisjunctiveLexer("a");
        expression = DisjunctiveExpression.Builder.build(lexer.nextValid().get(), lexer); 
        DisjunctiveExpression negated = expression.negate();
        assertEquals("false, a", negated.toString());
    }
    */

}