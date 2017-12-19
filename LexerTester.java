package lexer;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.*;
public class LexerTester{
    
    HashSet<Token.Type> invalid = new HashSet<Token.Type>();
    HashSet<Token.Type> valid = new HashSet<Token.Type>();

    
    @Test
    public void complexityTest() throws ParserException{
        String test = "(a and b) or not c";
        Lexer lexer = new Lexer(test);
        while(lexer.hasNext()){
            lexer.next();
        }
        assertEquals(2, lexer.getComplexity());
    }
    
    @Test
    public void lexerTest() throws ParserException{
        String test = "(a COMMENT and \t b) or c";
        String result = "";
        Lexer lexer = new Lexer(test);
        while(lexer.hasNext()){
            result += lexer.next() + " ";
        }
        assertEquals("OPEN ID(a) WHITESPACE WHITESPACE AND WHITESPACE ID(b) CLOSE WHITESPACE OR WHITESPACE ID(c) ", result); 
    }
    
    @Test(expected = ParserException.class)
    public void invalidTest() throws ParserException{
        invalid.add(Token.Type.NOT);
        invalid.add(Token.Type.AND);
        valid.add(Token.Type.ID);
        String test = "not and";
        Lexer lexer = new Lexer(test);
        lexer.nextValid(valid, invalid);
    }
    
    @Test
    public void validTest() throws ParserException{
        invalid.add(Token.Type.NOT);
        invalid.add(Token.Type.AND);
        valid.add(Token.Type.ID);
        String test = "abc";
        Lexer lexer = new Lexer(test);
        assertEquals("ID(abc)", lexer.nextValid(valid, invalid).get().toString());
    }
    
    @Test
    public void neitherTest() throws ParserException{
        invalid.add(Token.Type.NOT);
        invalid.add(Token.Type.AND);
        valid.add(Token.Type.ID);
        String test = "123";
        Lexer lexer = new Lexer(test);
        assertEquals(Optional.empty(), lexer.nextValid(valid, invalid));
    }
}