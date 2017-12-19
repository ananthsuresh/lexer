package lexer;
import java.util.*;
public class CommandLine{
    public static void main(String[] args){
        while(true){
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter a Disjunctive Expression (or 'exit' to quit):");
            String inputExpression = sc.nextLine();
            if(inputExpression.equals("exit")){
                break;
            }
            DisjunctiveLexer lexer = new DisjunctiveLexer(inputExpression);
            try{
                DisjunctiveExpression expression =  DisjunctiveExpression.Builder.build(lexer.nextValid().get(), lexer);
                String conjunctiveExpression = expression.conjunctiveRepresentation();
                System.out.println("The conjunctive representation is: " + conjunctiveExpression + "\n");
            }
            catch(ParserException e){
                System.out.println(e.toString());
            }
        }
    }
}