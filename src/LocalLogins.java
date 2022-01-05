import java.io.*;

public class LocalLogins {
    static void menu()
    {
        System.out.println("1 - Add Login");
        System.out.println("2 - Delete Login");
        System.out.println("3 - Edit Login");
        System.out.println("4 - Print Logins");
        System.out.println("5 - Find Login");
        System.out.println("6 - Exit\n");
        System.out.print("Select: ");
    }

    static String getSelection() throws IOException
    {
        BufferedReader menuReader = new BufferedReader(new InputStreamReader(System.in));
        return menuReader.readLine();
    }

    public static void main(String[] args) throws Exception {
        LinkedList logins = new LinkedList();
        String menu_select, title, new_title, new_username, new_password, password_type, edit_select;

        System.out.println("  _      ____   _____          _         _      ____   _____ _____ _   _  _____ ");
        System.out.println(" | |    / __ \\ / ____|   /\\   | |       | |    / __ \\ / ____|_   _| \\ | |/ ____|");
        System.out.println(" | |   | |  | | |       /  \\  | |       | |   | |  | | |  __  | | |  \\| | (___  ");
        System.out.println(" | |   | |  | | |      / /\\ \\ | |       | |   | |  | | | |_ | | | | . ` |\\___ \\ ");
        System.out.println(" | |___| |__| | |____ / ____ \\| |____   | |___| |__| | |__| |_| |_| |\\  |____) |");
        System.out.println(" |______\\____/ \\_____/_/    \\_\\______|  |______\\____/ \\_____|_____|_| \\_|_____/ \n\n");

        logins.loadFile();

        while (true){
            menu();
            menu_select = getSelection();

            switch (menu_select) {
                case "1":
                    System.out.print("Entry Title: ");
                    new_title = getSelection();

                    if (logins.contains(new_title)) {
                        System.out.println("LOGIN ALREADY EXISTS: PROCESS TERMINATED\n");
                        continue;
                    }

                    System.out.print("Entry Username: ");
                    new_username = getSelection();

                    System.out.println("Generate Password? [y/n]");
                    password_type = getSelection();

                    if (password_type.equals("y")) {
                        new_password = logins.generatePassword(12);

                        logins.addLogin(new_title, new_username, new_password);
                        System.out.println("LOGIN CREATION SUCCESSFUL\n");
                    } else if (password_type.equals("n")) {
                        System.out.print("Entry Password: ");
                        new_password = getSelection();

                        logins.addLogin(new_title, new_username, new_password);
                        System.out.println("LOGIN CREATION SUCCESSFUL\n");
                    }
                    else {
                        System.out.println("INVALID OPERATION: PROCESS TERMINATED\n");
                    }
                    break;
                case "2":
                    System.out.print("Entry of Title to Delete: ");
                    title = getSelection();

                    if (logins.isEmpty()){
                        System.out.println("NO SAVED LOGINS: PROCESS TERMINATED\n");
                        continue;
                    }
                    else if (!logins.contains(title)){
                        System.out.println("LOGIN DOES NOT EXIST: PROCESS TERMINATED\n");
                        continue;
                    }

                    logins.delete(title);
                    System.out.println("LOGIN DELETION SUCCESSFUL\n");
                    break;
                case "3":
                    System.out.print("Title of Entry to Edit: ");
                    title = getSelection();

                    if (logins.isEmpty()){
                        System.out.println("NO SAVED LOGINS: PROCESS TERMINATED\n");
                        continue;
                    }
                    else if (!logins.contains(title)){
                        System.out.println("LOGIN DOES NOT EXIST: PROCESS TERMINATED\n");
                        continue;
                    }

                    if (logins.contains(title)) {
                        System.out.println("What would you like to edit? [t/u/p]");
                        edit_select = getSelection();

                        if (edit_select.equals("t")) {
                            System.out.print("New Title: ");
                            new_title = getSelection();
                            System.out.println(" ");
                            logins.editTitle(title, new_title);
                            System.out.println("SUCCESSFULLY EDITED\n");
                        } else if (edit_select.equals("u")) {
                            System.out.print("New Username: ");
                            new_username = getSelection();
                            System.out.println(" ");
                            logins.editUsername(title, new_username);
                            System.out.println("SUCCESSFULLY EDITED\n");
                        } else if (edit_select.equals("p")) {
                            System.out.print("New Password: ");
                            new_password = getSelection();
                            System.out.println(" ");
                            logins.editPassword(title, new_password);
                            System.out.println("SUCCESSFULLY EDITED\n");
                        } else {
                            System.out.println("INVALID OPERATION: PROCESS TERMINATED\n");
                        }
                    }
                    break;
                case "4":
                    logins.printLogins();
                    break;
                case "5":
                    System.out.print("Entry of Title to Find: ");
                    title = getSelection();

                    if (logins.isEmpty()){
                        System.out.println("NO SAVED LOGINS: PROCESS TERMINATED\n");
                        continue;
                    }
                    else if (!logins.contains(title)){
                        System.out.println("LOGIN DOES NOT EXIST: PROCESS TERMINATED\n");
                        continue;
                    }

                    logins.printLogin(title);
                    break;
                case "6":
                    logins.clearText();
                    logins.loginsToFile();
                    System.out.println("LOGIN INFO SAVED");
                    System.exit(0);
            }
        }
    }
}
