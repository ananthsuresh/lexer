package lexer;
import java.util.*;


public final class DisjunctiveLexer{
    
    public static final HashSet<Token.Type> invalidTypes;
    public static final HashSet<Token.Type> validTypes;
    private Lexer lexer; 
    
    static{
        invalidTypes = new HashSet<Token.Type>();
        invalidTypes.add(Token.Type.OR);
        invalidTypes.add(Token.Type.NUMBER);
        invalidTypes.add(Token.Type.BINARYOP);
        validTypes = new HashSet<Token.Type>();
        validTypes.add(Token.Type.AND);
        validTypes.add(Token.Type.ID);
        validTypes.add(Token.Type.NOT);
        validTypes.add(Token.Type.OPEN);
        validTypes.add(Token.Type.CLOSE);
    }
    
    public DisjunctiveLexer(String input){
        lexer = new Lexer(input);
    }
    
    public Optional<LocationalToken> nextValid() throws ParserException{
        return lexer.nextValid(validTypes, invalidTypes);
    }
}