import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadSequentialFile
{
    private static ObjectInputStream input;

    public static void main(String[] args) 
    {
        openFile();
        readRecords();
        closeFile();
    }
    public static void openFile()
    {
        try
        {
            input = new ObjectInputStream(
                new FileInputStream( "clients.ser" ) );
        }
        catch ( IOException ioException )
        {
            System.err.println( "Error opening file." );
            System.exit(1);
        }
    }

    public static void readRecords()
    {
        System.out.printf( "%-10s%-12s%-12s%10s\n", "Account",
            "First Name", "Last Name", "Balance" );

        try
        { 
            while ( true )
            {
                Account record = ( Account ) input.readObject();

                System.out.printf( "%-10d%-12s%-12s%10.2f\n",
                record.getAccount(), record.getFirstName(),
                record.getLastName(), record.getBalance() );
            }
        }
        catch ( EOFException endOfFileException )
        {
            System.out.printf("%No more records%n");
        }
        catch ( ClassNotFoundException classNotFoundException )
        {
            System.err.println( "Invalid object type. Terminating." );
        }
        catch ( IOException ioException )
        {
            System.err.println( "Error reading from file. Terminating." );
        }
    }

    public static void closeFile()
    {
        try
        {
            if ( input != null )
                input.close();
            System.exit( 0 );
        }
        catch ( IOException ioException )
        {
            System.err.println( "Error closing file.Terminating." );
            System.exit( 1 );
        }
    }
}