import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CountNumbers {

    public static void main(String[] args) {
        countNumbers("number.txt");
    }

    public static void countNumbers(String filePath){
        try
        {
            File file=new File(filePath);
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            List<Float> numbers = new ArrayList<>();
            String line;
            float total = 0;
            while((line=br.readLine())!=null)
            {
                StringBuffer currentLineNumber = new StringBuffer();
                if(line.startsWith(" ") | line.startsWith("#")){
                    //check if line starts with space of #, and if yes, skiping the line
                    continue;
                }else {
                    char [] chars = line.toCharArray();
                    for (int i = 0; i < line.toCharArray().length; i++) {
                        if (Character.isDigit(chars[i]) | chars[i]== '-' | chars[i]=='.'){
                            currentLineNumber.append(chars[i]);
                        }
                    }
                }
                //check if number is valid and do not contains more than one dot.
                boolean isNumberValid = currentLineNumber.toString().split("\\.").length <= 2;
                if(isNumberValid & !currentLineNumber.isEmpty()) {
                    numbers.add(Float.parseFloat(currentLineNumber.toString()));
                }
            }
            fr.close();
            total = numbers.stream().reduce(Float::sum).get();
            System.out.println("Sum of all numbers in the file is = "+ total);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
