package lexer;
import java.util.*;

public class CompoundFactor implements Factor{
    
    private final DisjunctiveExpression leftExpression;
    private final DisjunctiveExpression rightExpression;
    
    private CompoundFactor(DisjunctiveExpression leftExpression, DisjunctiveExpression rightExpression){
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }
    
    @Override
    public String toString(){
        return "(" + leftExpression.toString() + " and " +  rightExpression.toString() + ")";
    }
    
    public ConjunctiveRepresentation conjunctiveRepresentation(){
        String string = "(" + leftExpression.conjunctiveRepresentation() + " or " + rightExpression.conjunctiveRepresentation() + ")";
        return new ConjunctiveRepresentation(string, true);
    }
    
    public static class Builder{
        public static final CompoundFactor build(LocationalToken token, DisjunctiveLexer lexer) throws ParserException{
            ParserException.verify(Token.Type.OPEN, token);
            Optional<LocationalToken> leftStart = lexer.nextValid();
            ParserException.verify(leftStart);
            DisjunctiveExpression leftExpression = DisjunctiveExpression.Builder.build(leftStart.get(), lexer);
            ParserException.verify(Token.Type.AND, lexer.nextValid().get());
            Optional<LocationalToken> rightStart = lexer.nextValid();
            ParserException.verify(rightStart);
            DisjunctiveExpression rightExpression = DisjunctiveExpression.Builder.build(rightStart.get(), lexer);
            ParserException.verify(Token.Type.CLOSE, lexer.nextValid().get());
            return new CompoundFactor(leftExpression, rightExpression);
            
        }
    }
}