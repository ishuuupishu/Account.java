import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.IllegalStateException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CreditInquiry
{
    private static final boolean DEBIT_BALANCE = false;
    private static final boolean CREDIT_BALANCE = false;
    private static final boolean ZERO_BALANCE = false;
    private static MenuOption choices[] =  MenuOption.values();

    public static void main(String[] args) {

        MenuOption accountType = getRequest();
        while (accountType != MenuOption.END)
        {
            switch(accountType != MenuOption.END)
            {
                case ZERO_BALANCE:
                    System.out.printf("%nAccounts with zero balances:%n");
                break;
                case CREDIT_BALANCE:
                    System.out.printf("%nAccounts with credit balances:%n");
                break;
                case DEBIT_BALANCE:
                    System.out.printf("%nAccounts with debit balances:%n");
                break;
            }
            readRecords(accountType);
            accountType = getRequest(); // get user's request
        }
    }

    private static MenuOption getRequest()
    {
        int request = 4;

        System.out.printf( "\nEnter Request: %s\n%s\n%s\n%s\n%s\n",
            "Enter request", " 1 - List accounts with zero balances",
            " 2 - List accounts with credit balances",
            " 3 - List accounts with debit balances", " 4 - End of run" );

        try
        {
            try (Scanner input = new Scanner(System.in)) {
                do
                {
                    System.out.print( "\n? " );
                    request = input.nextInt();
                } while ( ( request < 1 ) || ( request > 4 ) );
            }
        }
        catch ( NoSuchElementException noSuchElementException )
        {
            System.err.println( "Invalid input. Terminating" );
        }

        return choices[ request - 1 ];
    }


    private static void readRecords(MenuOption accountType)
    {
        try (Scanner input = new Scanner(Paths.get("clients.txt")))
        {     
            while ( input.hasNext() )
            {
                int accountNumber = input.nextInt();
                String firstName = input.next();
                String lastName = input.next();
                double balance = input.nextDouble();

                if ( shouldDisplay( accountType,balance ) )
                System.out.printf( "%-10d%-12s%-12s%10.2f\n",accountNumber,firstName,lastName,balance );
            }
        }
        catch ( NoSuchElementException |IllegalStateException | IOException e )
        {
            System.err.println( "File improperly formed. Terminating" );
            System.exit( 1 );
        }
    }

    private static boolean shouldDisplay( MenuOption accountType, double balance )
    {
        if ( ( accountType == MenuOption.CREDIT_BALANCE ) && ( balance < 0 ) )
            return true;

        else if ( ( accountType == MenuOption.DEBIT_BALANCE ) && ( balance > 0 ) )
            return true;

        else if ( ( accountType == MenuOption.ZERO_BALANCE ) && ( balance == 0 ) )
            return true;

        return false;
    }

}