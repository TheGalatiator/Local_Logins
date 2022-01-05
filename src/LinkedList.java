import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class LinkedList {
    private Node headPtr = null;
    File file = new File("logins.txt");

    /**
     * The constructor creates an empty list
     */
    public LinkedList()
    {
        headPtr = null;
    }

    /**
     * Determines if the list is empty
     */
    public boolean isEmpty()
    {
        return headPtr == null;
    }

    /**
     * Inserting a populated node at the front of the list
     */
    public void addLogin(String title, String username, String password)
    {
        Node newFirst = new Node( title, username, password );
        newFirst.setNext( headPtr );
        headPtr = newFirst;
    }

    /**
     * Prints the nodes of a linked list
     */
    public void printLogins()
    {
        Node p = headPtr;
        while ( p != null )
        {
            System.out.println( "Title: " + p.getTitle() );
            System.out.println( "Username: " + p.getUsername() );
            System.out.println( "Password: " + p.getPassword() );
            System.out.println( " " );
            p = p.getNext();
        }
    }

    /**
     * Print info of single login
     */
    public void printLogin( String title )
    {
        Node p = headPtr;
        while ( p != null )
        {
            if ( p.getTitle().equals(title) )
            {
                System.out.println("Title: " + p.getTitle());
                System.out.println("Username: " + p.getUsername());
                System.out.println("Password: " + p.getPassword());
                System.out.println(" ");
                break;
            }
            p = p.getNext();
        }
    }

    /**
     * Checks if a specified object is already contained in the linked list
     */
    public boolean contains(String title)
    {
        String node_title;
        Node p = headPtr;
        while ( p != null )
        {
            node_title = p.getTitle();
            if (node_title.equals(title)){
                return true;
            }
            else {
                p = p.getNext();
            }
        }
        return false;
    }

    /**
     * Edit the password value of a specified node
     */
    public void editPassword( String title, String new_password )
    {
        Node p = headPtr;
        while ( p != null )
        {
            if ( p.getTitle().equals(title) )
            {
                p.setPassword(new_password);
            }
            p = p.getNext();
        }
    }

    /**
     * Edit the username value of a specified node
     */
    public void editUsername( String title, String new_username )
    {
        Node p = headPtr;
        while ( p != null )
        {
            if ( p.getTitle().equals(title) )
            {
                p.setUsername(new_username);
            }
            p = p.getNext();
        }
    }

    /**
     * Edit the title value of a specified node
     */
    public void editTitle( String title, String new_title )
    {
        Node p = headPtr;
        while ( p != null )
        {
            if ( p.getTitle().equals(title) )
            {
                p.setTitle(new_title);
            }
            p = p.getNext();
        }
    }

    /**
     * Randomly generates a password with size
     * indicated by the user
     */
    public String generatePassword( int size )
    {
        Random random = new Random();
        String characters = "abcdefghijklmnopqrstuvwxyz1234567890-_.";
        StringBuilder password = new StringBuilder();
        char character;

        for (int i = 0; i < size; i++){
            int random_number = random.nextInt(38);
            password.append(characters.charAt(random_number));
        }

        return password.toString();
    }

    /**
     * Deleting a specified node
     */
    public void delete( String title )
    {

        if (title.equals(headPtr.getTitle())){
            headPtr = headPtr.getNext();
            return;
        }

        Node p = headPtr;
        while (p != null){
            if (title.equals(p.getNext().getTitle())){
                p.setNext(p.getNext().getNext());
                break;
            }
            else if (p.getNext().getNext() == null && title.equals(p.getNext().getTitle())){
                p.setNext(null);
            }
            else{
                p = p.getNext();
            }
        }
    }

    /**
     * Populating a linked list with previous login sessions
     */
    public void loadFile(){
        int counter = 0;
        String title = null, username = null, password = null;
        Scanner sc2 = null;
        try {
            sc2 = new Scanner(new File("logins.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (true){
            assert sc2 != null;
            if (!sc2.hasNextLine()) break;
            counter++;
            String line = sc2.nextLine();
            int pos = line.indexOf(" ");
            String word = line.substring(pos + 1);

            if (counter == 1){
                title = word;
            }
            else if (counter == 2){
                username = word;
            }
            else if (counter == 3){
                password = word;
                addLogin(title, username, password);
            }
            else if (counter == 4){
                counter = 0;
            }
        }
    }

    /**
     * Clears all text in a text file
     * @throws IOException
     */
    public void clearText() throws IOException {
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);
        pw.print("");
    }

    /**
     * Writes all login info to a text file
     * @throws IOException
     */
    void loginsToFile() throws IOException {
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);

        for(Node temp = headPtr; temp!=null; temp = temp.getNext())
        {
            pw.println("Title: " + temp.getTitle());
            pw.println("Username: " + temp.getUsername());
            pw.println("Password: " + temp.getPassword());
            pw.println(" ");
        }
        pw.close();
    }
}
