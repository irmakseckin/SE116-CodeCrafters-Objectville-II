import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[]args){

        System.out.println("Starting ObjectVille... ");

        try(BufferedReader br = new BufferedReader(new FileReader("map00.txt"))){

            String line;

            while((line = br.readLine()) != null){

                System.out.println(line);
            }



        }catch(IOException e){
            System.out.println("Cannot read the file.");
            System.out.println(e.getMessage());
        }
    }
}
