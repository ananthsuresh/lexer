package lexer;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
public class TokenTester{
    
    @Test
    public void equalTokenTest(){
        //Same Type and Data
        Token t1 = Token.of(Token.Type.NOT, "");
        Token t2 = Token.of(Token.Type.NOT, "");
        assertEquals(t1,t2);
        assertEquals(t1.hashCode(), t2.hashCode()); //Testing hashcodes are the same for equal objects
        //Same Type different Data for Type which is not supposed to have ancillary data
        t1 = Token.of(Token.Type.NOT, "abc");
        t2 = Token.of(Token.Type.NOT, "dfg");
        assertEquals(t1,t2);
        assertEquals(t1.hashCode(), t2.hashCode()); //Testing hashcodes are the same for equal objects
    }
    
    @Test
    public void unequalTokenTest(){
        //Different Type, same Data
        Token t1 = Token.of(Token.Type.OR, "");
        Token t2 = Token.of(Token.Type.AND, "");
        assertThat(t1, is(not(t2)));
        assertThat(t1.hashCode(), is(not(t2.hashCode())));
        //Same Type, different Data
        t1 = Token.of(Token.Type.ID, "ab");
        t2 = Token.of(Token.Type.ID, "cd");
        assertThat(t1, is(not(t2)));
        assertThat(t1.hashCode(), is(not(t2.hashCode())));
        //Different Type, different Data
        t1 = Token.of(Token.Type.AND, "");
        t2 = Token.of(Token.Type.OR, "gg");
        assertThat(t1, is(not(t2)));
        assertThat(t1.hashCode(), is(not(t2.hashCode()))); 
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void illegalData(){
        Token.of(Token.Type.ID, "33");
    }
    
    @Test
    public void testLocational(){
        Token t1 = Token.of(Token.Type.OR, "");
        LocationalToken lt1 = new LocationalToken(t1, 12);
        assertEquals(lt1.getToken(), t1);
        assertEquals(lt1.getLocation(), 12);
        assertEquals(lt1.getType(), t1.getType());
        assertEquals(lt1.getData(), t1.getData());
    }

}