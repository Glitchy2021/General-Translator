package OutputMessageTypes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class OutputBinaryCodeMessage extends OutputMessage {
    private String output_buffer;
    private String Binary_Code[][]= new String [26][2];
    private String Binary_filename = "GeneralTranslator (partial)/Data/BinaryCode_Table.txt";
    

    public OutputBinaryCodeMessage(PrintWriter output) throws FileNotFoundException, IOException
    {
        super(output);
        populateBinaryCode(Binary_filename);
        clearBuffer();
        
    }


    public String getLetterWithOrdinal(int ordinal_value)
    {
        return Binary_Code[ordinal_value][1];
    }
    public void writeLetter(int ordinal_value)throws IOException
    {
        output_buffer = output_buffer + getLetterWithOrdinal(ordinal_value);
    }
    public void writeEndOfWord() throws IOException
    {
        final String blank_char = " ";
        output_buffer = output_buffer + blank_char;
        
    }

    public void writeEndOfSentence() throws IOException
    {
        writeLine(output_buffer);
        clearBuffer();

    }
private void populateBinaryCode(String file_name) throws  FileNotFoundException, IOException
    {
        String line;
        BufferedReader input_file = new BufferedReader(new FileReader(file_name));

        line = input_file.readLine();
        int i =0;
        while(line != null)
        {
            Binary_Code[i][0] = line.substring(0,1);
            Binary_Code[i][1] = line.substring(1,line.length());

            line = input_file.readLine();
            i = i+1;
        }

        input_file.close();
    }
   
    private void clearBuffer() {
        // -----------------------------------------------------------
        // Clears line buffer by setting to empty string.
        // -----------------------------------------------------------
          final String empty_string = "";
          output_buffer = empty_string;
        }

}
