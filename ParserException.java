package lexer;
import java.util.*;
public final class ParserException extends Exception{
    
    private static final long serialVersionUID = 293L;
    
    public enum ErrorCode{
        TOKEN_EXPECTED,
        INVALID_TOKEN,
        TRAILING_INPUT,
        AND_EXPECTED,
        OR_EXPECTED,
        OPEN_EXPECTED,
        CLOSE_EXPECTED,
        ID_EXPECTED;
    }
    
    private final ErrorCode errorCode;
    
    private final int location;
    
    public ErrorCode getErrorCode(){
        return errorCode;
    }
    
    public int getLocation(){
        return location;
    }
    
    ParserException(LocationalToken token, ErrorCode errorCode){
        this.errorCode = errorCode;
        this.location = token.getLocation();
    }
    
    ParserException(ErrorCode errorCode){
        this.errorCode = errorCode;
        this.location = -1;
    }
    
    @Override
    public String toString(){
        String error = errorCode.name();
        String location = (getLocation() == -1) ? "" :  " at " + Integer.toString(getLocation());
        return errorCode + location;
    }
    
    public final static void verify(Token.Type expectedType, LocationalToken token) throws ParserException{
        if(!token.getType().equals(expectedType)){
            ErrorCode errorCode = expectedType.getErrorCode().isPresent() ? expectedType.getErrorCode().get() : ParserException.ErrorCode.INVALID_TOKEN;
            throw new ParserException(token, errorCode);
        }
    }
    public static void verify(Optional<LocationalToken> token) throws ParserException{
        if(!token.isPresent()){
            throw new ParserException(ErrorCode.TOKEN_EXPECTED);
        }
    }
    
    public static void verifyEnd(Optional<LocationalToken> token) throws ParserException{
        if(token.isPresent()){
            throw new ParserException(ErrorCode.TRAILING_INPUT);
        }
    }
    
    
}
