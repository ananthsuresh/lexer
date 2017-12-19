package lexer;

public final class Identifier implements Factor{
    private final String id;
    
    private Identifier(String id){
        this.id = id;
    }
        
    @Override
    public String toString(){
        return id;
    }
    
    public static final class Builder{
        public static final Identifier build(LocationalToken token) throws ParserException{
            ParserException.verify(Token.Type.ID, token);
            assert token.getData().isPresent();
            return new Identifier(token.getData().get());   
        }
    }
    
    public ConjunctiveRepresentation conjunctiveRepresentation(){
        return new ConjunctiveRepresentation(id, false);
    }
    
}