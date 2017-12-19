package lexer;

import java.util.regex.*;
import java.util.*;
public class Lexer{
    
    private static Pattern tokenPatterns;
    private final Matcher matcher; 
    private int complexity = 0;
    
    public Lexer(String input){
        loadTypes();
        matcher = tokenPatterns.matcher(input);
    }
    
    public int getComplexity(){
        return complexity;
    }
    
    //helper method that combines the regexes of all the token types into a massive regex with named capturing groups
    private static void loadTypes(){
        StringBuffer combinedRegex = new StringBuffer();
        for(Token.Type tokenType: Token.Type.values()){
            combinedRegex.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.getPattern()));
        }
        tokenPatterns = Pattern.compile(new String(combinedRegex.substring(1))); //removes | from beginning
    }
    
    public boolean hasNext(){
       boolean hasNext = matcher.find();
       //resets pointer to previous match if next match is found
       if(hasNext){
           matcher.region(matcher.start(), matcher.regionEnd());
       }
       return hasNext; 
    }
    
    public LocationalToken next() throws ParserException{
        if(hasNext()){
            matcher.find();
            for(Token.Type tokenType: Token.Type.values()){
                String match = matcher.group(tokenType.name());
                if(match != null){
                    complexity += (tokenType.isComplex()) ? 1 : 0;
                    return new LocationalToken(Token.of(tokenType, match), matcher.start());
                }
            }
            
        }
        throw new ParserException(ParserException.ErrorCode.TOKEN_EXPECTED);

    }
    
    public Optional<LocationalToken> nextValid(Set<Token.Type> validTypes, Set<Token.Type> invalidTypes) throws ParserException{
        while(hasNext()){
            LocationalToken token = next();
            if(validTypes.contains(token.getType())){
                return Optional.of(token);
            }
            if(invalidTypes.contains(token.getType())){
                throw new ParserException(token, ParserException.ErrorCode.INVALID_TOKEN);
            }
        }
        
        return Optional.empty();
        
    }
}