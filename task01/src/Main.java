import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args) throws IOException{
        List<String> alpha = new ArrayList<>();
        Mastermind mastermind = new Mastermind();
        File file = new File(args[0]);
        Reader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();
        String input;
        while((input = br.readLine())!= null){
            sb.append(input);
        }
        for(String s : sb.toString().split(",")){
            alpha.add(s);
        }
        mastermind.start(alpha);    
    }
}