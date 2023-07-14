import java.io.File;
import java.io.FileNotFoundException;
import java.lang.IllegalStateException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadTextFile
{
   private static Scanner input;
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
         input = new Scanner( new File( "clients.txt" ) );
      }
      catch ( FileNotFoundException fileNotFoundException )
      {
         System.err.println( "Error opening file.Terminating" );
         System.exit( 1 );
      } 
   }

   public static void readRecords()
   {
      System.out.printf( "%-10s%-12s%-12s%10s\n", "Account",
         "First Name", "Last Name", "Balance" );
      try
      {
         while ( input.hasNext() )
         {
            System.out.printf("%-10d%-12s%-12s%10.2f%n", input.nextInt(), input.next(), input.next(), input.nextDouble()); 
         }
      }
      catch ( NoSuchElementException elementException )
      {
         System.err.println( "Error opening file.Terminating" );
      }
      catch ( IllegalStateException stateException )
      {
         System.err.println( "Error opening file.Terminating" );
      }
   }

   public static void closeFile()
   {
      if ( input != null )
         input.close();
   }
} 