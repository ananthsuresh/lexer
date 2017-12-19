package lexer;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.*;
public class ConjunctiveTester{
    
    DisjunctiveLexer lexer;
    DisjunctiveExpression expression;
    
    @Test
    public void simpleTest() throws ParserException{
        lexer = new DisjunctiveLexer("(a and b)");
        expression = DisjunctiveExpression.Builder.build(lexer.nextValid().get(), lexer);
        assertEquals("(not a or not b)", expression.conjunctiveRepresentation());
    }
    
    @Test
    public void compoundTest() throws ParserException{
        lexer = new DisjunctiveLexer("((a and b) and c)");
        expression = DisjunctiveExpression.Builder.build(lexer.nextValid().get(), lexer);
        assertEquals("((not a or not b) or not c)", expression.conjunctiveRepresentation());
    }
      
    @Test
    public void notTest() throws ParserException{
        lexer = new DisjunctiveLexer("((not a and b) and not c)");
        expression = DisjunctiveExpression.Builder.build(lexer.nextValid().get(), lexer);
        assertEquals("((a or not b) or c)", expression.conjunctiveRepresentation());
        lexer = new DisjunctiveLexer("(not(not a and b) and not c)");
        expression = DisjunctiveExpression.Builder.build(lexer.nextValid().get(), lexer);
        assertEquals("(not (a or not b) or c)", expression.conjunctiveRepresentation());
    }
    
    
    
}