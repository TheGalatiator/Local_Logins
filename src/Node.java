public class Node
{
    private Node next;
    private String title, username, password;

    public Node ( String title, String username, String password )
    {
        this.title = title;
        this.username = username;
        this.password = password;
        next = null;
    }

    public String getTitle() { return title; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Node getNext()  { return next; }

    public void setTitle(String title ) { this.title = title; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setNext( Node nxt ) { next = nxt; }

    public String toString() { return "Title: " + title + "\nUsername: " + username + "\nPassword: " + password; }

}