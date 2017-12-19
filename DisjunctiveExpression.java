package lexer;

public final class DisjunctiveExpression{
    
    private final Factor factor;
    private final boolean positive;
    
    private DisjunctiveExpression(Factor factor, boolean positive){
        this.factor = factor;
        this.positive = positive;
    }
    
    public final boolean isPositive(){
        return positive;
    }
    
    public static String convertToString(boolean b){
        return (b ? "":"not ");
    }
    
    public final DisjunctiveExpression negate(){
        return new DisjunctiveExpression(this.factor, !this.positive);
    }
    
    @Override
    public String toString(){
        if(factor instanceof Identifier){
            return convertToString(positive) + factor;
        }
        else{
            return factor.toString();
        }

    }
    
    public final String conjunctiveRepresentation(){
        ConjunctiveRepresentation representation = factor.conjunctiveRepresentation();

        if(positive ^ representation.isNegated()){
            return "not " + representation.getRepresentation();
        }
        else{
            return representation.getRepresentation();
        }
    }
    public static class Builder{
        public static final DisjunctiveExpression build(LocationalToken token, DisjunctiveLexer lexer) throws ParserException{
            boolean positiveExpression = true;
            Factor factor;
            if(token.getType() == Token.Type.NOT){
                positiveExpression = false;
                token = lexer.nextValid().get();
            }
            if(token.getType() == Token.Type.ID){                          
                factor = Identifier.Builder.build(token);
            }
            else if(token.getType() == Token.Type.OPEN){
                factor= CompoundFactor.Builder.build(token, lexer);   
            }
            else{
                throw new ParserException(token, ParserException.ErrorCode.INVALID_TOKEN);
            }
            return new DisjunctiveExpression(factor, positiveExpression);
        }
    }
}