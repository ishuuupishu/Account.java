import java.io.Serializable;

public class Account implements Serializable
{
    private int account;
    private String firstName;
    private String lastName;
    private double balance;
    
    public Account() 
    {
        this( 0, "", "", 0.0 );
    }
    
    public Account(
        int acct, String first, String last, double bal )
    {
        setAccount( acct );
        setFirstName( first );
        setLastName( last );
        setBalance( bal );
    } 

    public void setAccount( int acct )
    {
        account = acct;
    }

    public int getAccount() 
    { 
        return account; 
    }

    public void setFirstName( String first )
    {
        firstName = first;
    }

    public String getFirstName() 
    { 
        return firstName; 
    }

    public void setLastName( String last )
    {
        lastName = last;
    }

    public String getLastName() 
    {
        return lastName; 
    }

    public void setBalance( double bal )
    {
        balance = bal;
    }

    public double getBalance() 
    { 
        return balance; 
    }
}