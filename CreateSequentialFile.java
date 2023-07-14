import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CreateSequentialFile
{
    private static ObjectOutputStream output;

    public static void main(String[] args) 
    {
        openFile();
        addRecords();
        closeFile();
    }

    public static void openFile()
    {
        try // open file
        {
            output = new ObjectOutputStream(
                new FileOutputStream( "clients.ser" ) );
        }
        catch ( IOException ioException )
        {
            System.err.println( "Error opening file. Terminating." );
            System.exit(1);
        }
    }

    public static void addRecords()
    {
        try (Scanner input = new Scanner( System.in )) {
            System.out.printf("%s%n%s%n? ", 
   "Enter account number, first name, last name and balance.",
                "Enter end-of-file indicator to end input.");

            while ( input.hasNext() )
            {
                try // output values to file
                {
                    Account record = new Account(input.nextInt(),
                        input.next(), input.next(), input.nextDouble());

                    output.writeObject(record);
                }
                catch ( NoSuchElementException elementException )
                {
                    System.err.println( "Invalid input. Please try again." );
                    input.nextLine();
                }
                catch ( IOException ioException )
                {
                    System.err.println( "Error writing to file." );
                    break;
                }
                System.out.printf( "? " );
            }
        }
    }

    public static void closeFile() 
    {
        try
        {
            if ( output != null )
                output.close();
        }
        catch ( IOException ioException )
        {
            System.err.println( "Error closing file." );
            System.exit( 1 );
        }
    }
}