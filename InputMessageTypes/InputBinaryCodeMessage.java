package InputMessageTypes;

import Exceptions.InvalidBinaryCodeFoundException;
import Exceptions.InvalidCharFoundException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InputBinaryCodeMessage extends InputMessage{
    
private String line_read= null;
private String line_buffer;
private int current_char_index;
private String Last_Binary_read;
private String[][] Binary_code = new String[26][2];
private String Binary_code_filename = "GeneralTranslator (partial)\\Data\\BinaryCode_Table.txt";


    public InputBinaryCodeMessage(BufferedReader input)throws FileNotFoundException, IOException
    
    {
        super(input);
        populateBinaryCode(Binary_code_filename);
    }

    
    public int getOrdinal(String chr) {
        
        int i=0;
        int ordinal_value=0;
        boolean found = false;

        while(i <Binary_code.length && !found)
        {
            if(Binary_code[i][1].equals(chr))
            {
                ordinal_value =i;
                found = true;
            }
            else
            {
                i =i+1;
            }
        }
        return ordinal_value;
    }

    
    public String readLetter() throws IOException,InvalidCharFoundException {
        

        if(endOfLine()){
            line_read = getNextLine();
            line_buffer = line_read + "*";
            current_char_index =0;
        }

        if(line_read==null)
        {
            return null;
        }
       String Binary_read = line_buffer.substring(current_char_index,current_char_index+1);

       Last_Binary_read = Binary_read;

       if(invalidBinaryCode(Binary_read))
       {
        throw new InvalidBinaryCodeFoundException();
       }

       current_char_index= current_char_index+1;
        return Binary_read;
        
    }

    
    public boolean endOfWord() {
        
        final char blank_char = ' ';
    return Last_Binary_read.charAt(0) == blank_char;
    }

    
    public boolean endOfLine() throws IOException {
        
        
        return current_char_index >= line_buffer.length();
    }
    private void populateBinaryCode(String file_name) throws  FileNotFoundException, IOException
    {
        String line;
        BufferedReader input_file = new BufferedReader(new FileReader(file_name));

        line = input_file.readLine();
        int i =0;
        while(line != null)
        {
            Binary_code[i][0] = line.substring(0,1);
            Binary_code[i][1] = line.substring(1,line.length());

            line = input_file.readLine();
            i = i+1;
        }

        input_file.close();
    }

    private boolean invalidBinaryCode(String chr_str)
    {
        boolean found = false;
        int i = 0;
    
        while(i < Binary_code.length && !found)
          if(chr_str.equals(Binary_code[i][1]))
            found = true;
          else
            i = i + 1;
    
        
        return !found;
    }
}
