package lexer;

import java.util.regex.*;
import java.util.*;
public final class Token{
    
    public enum Type{
        NOT("not", false, false, Optional.empty()),
        AND("and", false, true, Optional.of(ParserException.ErrorCode.AND_EXPECTED)),
        OR("or", false, true, Optional.empty()),
        OPEN("[(]", false, false, Optional.of(ParserException.ErrorCode.OPEN_EXPECTED)),
        CLOSE("[)]", false, false, Optional.of(ParserException.ErrorCode.CLOSE_EXPECTED)),
        ID("[a-z]+", true, false, Optional.of(ParserException.ErrorCode.ID_EXPECTED)),
        NUMBER("-?\\d+", true, false, Optional.empty()),
        BINARYOP("[-/+*]", true, false, Optional.empty()),
        WHITESPACE("\\s+", false, false, Optional.empty())  
        ;
        
        private final String pattern;
        private final boolean hasData;
        private final boolean isComplex;
        private Optional<ParserException.ErrorCode> errorCode;
        
        Type(String pattern, boolean hasData, boolean isComplex, Optional<ParserException.ErrorCode> errorCode){
            this.pattern = pattern;
            this.hasData = hasData;
            this.isComplex = isComplex;
            this.errorCode = errorCode;
        }
        
        public String getPattern(){
            return pattern;
        }
        
        public boolean hasData(){
            return hasData;
        } 
        
        public boolean isComplex(){
            return isComplex;
        }
        
        public Optional<ParserException.ErrorCode> getErrorCode(){
            return errorCode;
        }
    }
    
    private final Type type;
    private final Optional<String> data;
    private static Map<Builder, Token> tokens = new HashMap<Builder, Token>();
    
    private Token(Type type, Optional<String> data){
        this.type = type;
        this.data = data;
    }
    
    public Type getType(){
        return type;
    }
    
    public Optional<String> getData(){
        return data;
    }
    
    @Override
    public boolean equals(Object token){
        Type inputTokenType = ((Token)token).getType();
        Optional<String> inputTokenData = ((Token)token).getData();
        return (this.type.equals(inputTokenType) && this.data.equals(inputTokenData)); //checks if type and ancillary data are both equal
    }
    
    @Override 
    public int hashCode(){
        int result = 17;
        result = 31 * result + this.getData().hashCode();
        result = 31 * result + this.getType().getPattern().hashCode();
        result = 31 * result + ((this.getType().hasData()) ? 1:0); //hashCode of boolean from Effective Java section 9
        return result;
    }
    
    @Override
    public String toString(){
        return this.getType().getPattern();
    }
    
    
    public static Token of(Type type, String data) throws IllegalArgumentException{
        Optional<String> buildData = Optional.ofNullable(data);
        Type buildType = type;
        //tests if patterns match if Type is one that takes data
        if(buildType.hasData() && buildData.isPresent()){
            boolean patternMatch = Pattern.matches(buildType.getPattern(), buildData.get());
            if(!patternMatch){
                throw new IllegalArgumentException();
            }
        }
        //if type is not supposed to have ancillary data but does, replaces it with empty Optional
        if(!buildType.hasData() && buildData.isPresent()){
            buildType = type;
            buildData = Optional.empty();
        }
        
        if(buildType.hasData() && !buildData.isPresent()){
            throw new IllegalArgumentException();
        }
        Builder testBuilder = new Builder(buildType, buildData);
        Token testToken = testBuilder.build();
        Token mapToken = tokens.putIfAbsent(testBuilder, testToken);
        
        if(mapToken == null){
            return testToken;
        }
        else{
            return mapToken;
        }
    }
    
    private static class Builder{
        private final Type type;
        private final Optional<String> data;
        
        public Builder(Type type, Optional<String> data){
            this.type = type;
            this.data = data;
        }
        
        private Token build(){
            Token newToken = new Token(type, data);
            return newToken;
        }
        
        @Override 
        public boolean equals(Object builder){
            Type inputBuilderType = ((Builder)builder).type;
            Optional<String> inputBuilderData = ((Builder)builder).data;
            return (this.type.equals(inputBuilderType) && this.data.equals(inputBuilderData)); //checks if type and ancillary data are both equal
        }
        
        @Override
        public int hashCode(){
            int result = 23;
            result = 31 * result + this.data.hashCode();
            result = 31 * result + this.type.getPattern().hashCode();
            result = 31 * result + ((this.type.hasData()) ? 1:0); //hashCode of boolean from Effective Java section 9
            return result;
        }
    }
}