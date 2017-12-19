package lexer;
import java.util.*;
public final class LocationalToken{
    
    private final Token token;
    private final int location;
    
    public LocationalToken(Token token, int location){
        this.token = token;
        this.location = location;
    }
    public Token getToken(){
        return token;
    }
    
    public int getLocation(){
        return location;
    }
    
    public Token.Type getType(){
        return token.getType();
    }
    
    public Optional<String> getData(){
        return token.getData();
    }
    
    @Override
    public String toString(){
        String ancillaryData = (token.getData().isPresent()) ? "(" + token.getData().get() + ")" : "";
        return token.getType().name() + ancillaryData;
    }
    
}