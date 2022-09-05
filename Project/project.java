//STEP 1. Import required packages

import java.sql.*;
import java.util.*;
import java.io.*;
import java.lang.*;

public class project {

//Set JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
//static final String DB_URL = "jdbc:mysql://localhost/companydb";
   static final String DB_URL = "jdbc:mysql://localhost/projectdb?useSSL=false";
//  Database credentials
   static final String USER = "root";// add your user 
   static final String PASS = "9943";// add password
   

   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;


// STEP 2. Connecting to the Database
   try{
      //STEP 2a: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      //STEP 2b: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      //STEP 2c: Execute a query
      //System.out.println("Creating statement...");
      stmt = conn.createStatement();

      Scanner scan = new Scanner(System.in);
      scan.useDelimiter("\n");
      String sql;
      ResultSet rs = null;
      boolean loop = true;

      //STEP 3: Query to database
      while(loop) {
         System.out.println("\nLogin as a - ");
         System.out.println("1. User");
         System.out.println("2. Admin");
         System.out.println("3. Author");
         System.out.println("4. Publisher");
         System.out.println("0. Exit");

         System.out.print("\nEnter your choice: ");
         int choice = scan.nextInt();

         if(choice == 0)
            loop = false;

         else if(choice == 1) {
            boolean loop_1 = true;
            while(loop_1) {
               System.out.println("\nUser menu :");
               System.out.println("1. List of all available books");
               System.out.println("2. List of available books of specific author");
               System.out.println("3. List of available books of specific publisher");
               System.out.println("4. Search for a specific book");
               System.out.println("0. Exit");

               System.out.print("\nEnter your choice: ");
               int choice_1 = scan.nextInt();

               if(choice_1 == 0)
                  loop_1 = false;

               else if(choice_1 == 1) {
                  sql = "select isbn, title, author_id, publisher_id, year from book";
                  rs = stmt.executeQuery(sql);
                  int i=0;
                  
                  while(rs.next()) {
                     if(i == 0){
                        System.out.println("+---------------+--------------------------------------------------+-----------+--------------+------+");
                        System.out.format("| %-13s | %-48s | %9s | %-12s | %-4s |\n", "Isbn", "Title", "Author id", "Publisher id", "Year");
                        System.out.println("+---------------+--------------------------------------------------+-----------+--------------+------+");
                     }
                     String isbn = rs.getString("isbn");
                     String title = rs.getString("title");
                     String author_id = rs.getString("author_id");
                     String publisher_id = rs.getString("publisher_id");
                     String year = rs.getString("year");
                     i++;

                     System.out.format("| %-13s | %-48s | %9s | %-12s | %-4s |\n", isbn, title, author_id, publisher_id, year);
                     System.out.println("+---------------+--------------------------------------------------+-----------+--------------+------+");
                  }
               }

               else if(choice_1 == 2) {
                  boolean loop_3 = true;
                  while(loop_3) {
                     System.out.println("\nList of available books of specific author:");
                     System.out.println("1. By using Author ID");
                     System.out.println("2. By using Author name");
                     System.out.println("0. Exit");

                     System.out.print("\nEnter your choice: ");
                     int choice_2 = scan.nextInt();

                     if(choice_2 == 0)
                        loop_3 = false;

                     else if(choice_2 == 1) {
                        System.out.print("Enter the Author ID: ");
                        int id = scan.nextInt();
                     
                        sql = "select isbn, title, author_id, publisher_id, year from book where author_id = "+ String.valueOf(id);
                        rs = stmt.executeQuery(sql);
                        int i=0;

                        while(rs.next()) {
                           if(i == 0) {
                              System.out.println("+---------------+--------------------------------------------------+-----------+--------------+------+");
                              System.out.format("| %-13s | %-48s | %9s | %-12s | %-4s |\n", "Isbn", "Title", "Author id", "Publisher id", "Year");
                              System.out.println("+---------------+--------------------------------------------------+-----------+--------------+------+");
                           }
                           String isbn = rs.getString("isbn");
                           String title = rs.getString("title");
                           String author_id = rs.getString("author_id");
                           String publisher_id = rs.getString("publisher_id");
                           String year = rs.getString("year"); i++;

                           System.out.format("| %-13s | %-48s | %9s | %-12s | %-4s |\n", isbn, title, author_id, publisher_id, year);
                           System.out.println("+---------------+--------------------------------------------------+-----------+--------------+------+");
                        }
                     }

                     else if(choice_2 == 2) {
                        System.out.print("Enter the Author name: ");
                        String author_name = scan.next(); 

                        sql = "select isbn, title, name, publisher_id, year from book b inner join author a on a.id = b.author_id where a.name = '"+ author_name + "'";
                        rs = stmt.executeQuery(sql);
                        int i=0;

                        while(rs.next()) {
                           if(i == 0) {
                              System.out.println("+---------------+--------------------------------------------------+--------------------+--------------+------+");
                              System.out.format("| %-13s | %-48s | %18s | %-12s | %-4s |\n", "Isbn", "Title", "Author name", "Publisher id", "Year");
                              System.out.println("+---------------+--------------------------------------------------+--------------------+--------------+------+");
                           }
                           String isbn = rs.getString("isbn");
                           String title = rs.getString("title");
                           String name = rs.getString("name");
                           String publisher_id = rs.getString("publisher_id");
                           String year = rs.getString("year"); i++;

                           System.out.format("| %-13s | %-48s | %18s | %-12s | %-4s |\n", isbn, title, name, publisher_id, year);
                           System.out.println("+---------------+--------------------------------------------------+--------------------+--------------+------+");
                        }
                     }
                  }
               }

               else if(choice_1 == 3) {
                  boolean loop_3 = true;
                  while(loop_3) {
                     System.out.println("\nList of available books of specific publisher:");
                     System.out.println("1. By using Publisher ID");
                     System.out.println("2. By using Publisher name");
                     System.out.println("0. Exit");

                     System.out.print("\nEnter your choice: ");
                     int choice_2 = scan.nextInt();

                     if(choice_2 == 0)
                        loop_3 = false;

                     else if(choice_2 == 1) {
                        System.out.print("Enter the Publisher ID: ");
                        int id = scan.nextInt();

                        sql = "select isbn, title, author_id, publisher_id, year from book where publisher_id = "+ String.valueOf(id);
                        rs = stmt.executeQuery(sql);
                        int i=0;

                        while(rs.next()) {
                           if(i == 0) {
                              System.out.println("+---------------+--------------------------------------------------+-----------+--------------+------+");
                              System.out.format("| %-13s | %-48s | %9s | %-12s | %-4s |\n", "Isbn", "Title", "Author id", "Publisher id", "Year");
                              System.out.println("+---------------+--------------------------------------------------+-----------+--------------+------+");
                           }
                           String isbn = rs.getString("isbn");
                           String title = rs.getString("title");
                           String author_id = rs.getString("author_id");
                           String publisher_id = rs.getString("publisher_id");
                           String year = rs.getString("year"); i++;
                           System.out.format("| %-13s | %-48s | %9s | %-12s | %-4s |\n", isbn, title, author_id, publisher_id, year);
                           System.out.println("+---------------+--------------------------------------------------+-----------+--------------+------+");
                        }
                     }

                     else if(choice_2 == 2) {
                        System.out.print("Enter the Publisher name: ");
                        String publisher_name = scan.next(); 

                        sql = "select isbn, title, author_id, name, year from book b inner join publisher p on p.id = b.publisher_id where p.name = '"+ publisher_name + "'";
                        rs = stmt.executeQuery(sql);
                        int i=0;

                        while(rs.next()) {
                           if(i == 0) {
                              System.out.println("+---------------+--------------------------------------------------+-----------+-----------------+------+");
                              System.out.format("| %-13s | %-48s | %9s | %-15s | %-4s |\n", "Isbn", "Title", "Author id", "Publisher name", "Year");
                              System.out.println("+---------------+--------------------------------------------------+-----------+-----------------+------+");
                           }
                           String isbn = rs.getString("isbn");
                           String title = rs.getString("title");
                           String author_id = rs.getString("author_id");
                           String name = rs.getString("name");
                           String year = rs.getString("year"); i++;

                           System.out.format("| %-13s | %-48s | %9s | %-15s | %-4s |\n", isbn, title, author_id, name, year);
                           System.out.println("+---------------+--------------------------------------------------+-----------+-----------------+------+");
                        }
                     }
                  }
               }

               else if (choice_1 == 4) {
                  boolean loop_3 = true;
                  while(loop_3) {
                     System.out.println("\nSearch for a specific book:");
                     System.out.println("1. By using Book ISBN number");
                     System.out.println("2. By using Book name");
                     System.out.println("0. Exit");

                     System.out.print("\nEnter your choice: ");
                     int choice_2 = scan.nextInt();

                     if(choice_2 == 0)
                        loop_3 = false;

                     if(choice_2 == 1) {
                        System.out.print("Enter the ISBN number: ");
                        long num = scan.nextLong();
                        sql = "select isbn, title, author_id, publisher_id, year from book where isbn = " + String.valueOf(num);
                        rs = stmt.executeQuery(sql);
                        int i=0;

                        while(rs.next()) {
                           if(i == 0) {
                              System.out.println("+---------------+--------------------------------------------------+-----------+--------------+------+");
                              System.out.format("| %-13s | %-48s | %9s | %-12s | %-4s |\n", "Isbn", "Title", "Author id", "Publisher id", "Year");
                              System.out.println("+---------------+--------------------------------------------------+-----------+--------------+------+");
                           }
                           String isbn = rs.getString("isbn");
                           String title = rs.getString("title");
                           String author_id = rs.getString("author_id");
                           String publisher_id = rs.getString("publisher_id");
                           String year = rs.getString("year"); i++;

                           System.out.format("| %-13s | %-48s | %9s | %-12s | %-4s |\n", isbn, title, author_id, publisher_id, year);
                           System.out.println("+---------------+--------------------------------------------------+-----------+--------------+------+");
                        }
                     }

                     else if(choice_2 == 2) {
                        System.out.print("Enter the Book name: ");
                        String book_name = scan.next();
                        sql = "select isbn, title, author_id, publisher_id, year from book where title = '"+ book_name + "'";
                        rs = stmt.executeQuery(sql);
                        int i=0;

                        while(rs.next()) {
                           if(i == 0) {
                              System.out.println("+---------------+--------------------------------------------------+-----------+--------------+------+");
                              System.out.format("| %-13s | %-48s | %9s | %-12s | %-4s |\n", "Isbn", "Title", "Author id", "Publisher id", "Year");
                              System.out.println("+---------------+--------------------------------------------------+-----------+--------------+------+");
                           }
                           String isbn = rs.getString("isbn");
                           String title = rs.getString("title");
                           String author_id = rs.getString("author_id");
                           String publisher_id = rs.getString("publisher_id");
                           String year = rs.getString("year"); i++;

                           System.out.format("| %-13s | %-48s | %9s | %-12s | %-4s |\n", isbn, title, author_id, publisher_id, year);
                           System.out.println("+---------------+--------------------------------------------------+-----------+--------------+------+");
                        }
                     }
                  }
               }
            }
         }

         else if(choice == 2) {
            System.out.println("\nAuthetication for admin");
            System.out.print("Enter the username: ");
            String username = scan.next();
            System.out.print("Enter the password: ");
            String password = scan.next();
            
            sql = "select id from admin where username = '" + username + "' and password = '" + password + "'";
            rs = stmt.executeQuery(sql);

            if(rs.next() == false)
               System.out.println("\nAuthentication failed");

            else {
               System.out.println("\nAuthentication successful");
               String admin_id = rs.getString("id");
               System.out.println("admin id: " + admin_id);
               boolean loop_1 = true;

               while(loop_1) {
                  System.out.println("\nAdmin menu:");
                  System.out.println("1. Add new Author record");
                  System.out.println("2. Add new Publisher record");
                  System.out.println("3. Delete Author record");
                  System.out.println("4. Delete Publisher record");
                  System.out.println("0. Exit");

                  System.out.print("\nEnter your choice: ");
                  int choice_1 = scan.nextInt();

                  if(choice_1 == 0)
                     loop_1 = false;

                  else if(choice_1 == 1) {
                     System.out.print("Enter the author's name: ");
                     String name = scan.next();
                     System.out.print("Enter the author's country: ");
                     String country = scan.next();
                     System.out.print("Enter the author's age: ");
                     int age = scan.nextInt();
                     System.out.print("Enter the author's email id: ");
                     String email = scan.next();
                     String str = "NULL";

                     if(!email.equals(str)) 
                        sql = "insert into author (name, country, age, email) values ('" + name + "', '" + country + "', '" + age + "', '" + email + "')";
                     else
                        sql = "insert into author (name, country, age) values ('" + name + "', '" + country + "', '" + age + "')";
                     int count = stmt.executeUpdate(sql);
                     if(count == 1) 
                        System.out.println("\nInsertion query is successful.");
                     else
                        System.out.println("\nError: Insertion failed");
                  }

                  else if(choice_1 == 2) {
                     System.out.print("Enter the publisher's name: ");
                     String name = scan.next();
                     System.out.print("Enter the publisher's country: ");
                     String country = scan.next();

                     sql = "insert into publisher (name, country) values ('" + name + "', '" + country + "')";
                     int count = stmt.executeUpdate(sql);
                     if(count == 1) 
                        System.out.println("\nInsertion query is successful.");
                     else
                        System.out.println("\nError: Insertion failed");
                  }

                  else if(choice_1 == 3) {
                     System.out.print("Enter the author's id: ");
                     int author_id = scan.nextInt();

                     sql = "delete from book where author_id = '" + author_id + "'";
                     int c1 = stmt.executeUpdate(sql);
                     if(c1 > 0) 
                        System.out.println("\nAll records are deleted from Book table related to this author");
                     else if(c1 == 0) 
                        System.out.println("\nNo records are present related to this author in Book table for deleting");

                     sql = "delete from author where id = '" + author_id + "'";
                     int c2 = stmt.executeUpdate(sql);
                     if(c2 > 0)
                        System.out.println("\nAll records are deleted from Author table related to this author");
                     else if(c2 == 0) 
                        System.out.println("\nNo records are present related to this author in Author table for deleting");
                  }

                  else if(choice_1 == 4) {
                     System.out.print("Enter the publisher's id: ");
                     int pub_id = scan.nextInt();

                     sql = "delete from book where publisher_id = '" + pub_id + "'";
                     int c1 = stmt.executeUpdate(sql);
                     
                     if(c1 > 0) 
                        System.out.println("\nAll records are deleted from Book table related to this publisher");
                     else if(c1 == 0) 
                        System.out.println("\nNo records are present related to this publisher in Book table for deleting");

                     sql = "delete from publisher where id = '" + pub_id + "'";
                     int c2 = stmt.executeUpdate(sql);
                     if(c2 > 0)
                        System.out.println("\nAll records are deleted from Publisher table related to this publisher");
                     else if(c2 == 0) 
                        System.out.println("\nNo records are present related to this publisher in Publisher table for deleting");
                  }
               }
            }
         }

         else if(choice == 3) {
            System.out.print("\nEnter the author's id for login into db: ");
            int id = scan.nextInt();
            sql = "select name from author where id = '" + id + "'";
            rs = stmt.executeQuery(sql);

            if(rs.next() == false)
               System.out.println("Author's info not found in the database");
            
            else {
               String name = rs.getString("name");
               System.out.println("Author id: " + id + ", name: " + name);
               boolean loop_1 = true;

               while(loop_1) {
                  System.out.println("\nAuthor menu: ");
                  System.out.println("1. View details of Author");
                  System.out.println("2. Update Author details");
                  System.out.println("0. Exit");

                  System.out.print("\nEnter your choice: ");
                  int choice_1 = scan.nextInt();

                  if(choice_1 == 0)
                     loop_1 = false;

                  else if(choice_1 == 1) {
                     sql = "select name, country, age, email from author where id = '" + id + "'";
                     rs = stmt.executeQuery(sql);

                     while(rs.next()) {
                        String author_name = rs.getString("name");
                        String country = rs.getString("country");
                        String age = rs.getString("age");
                        String email = rs.getString("email");

                        System.out.println("Author id: " + id + ", name: " + author_name + ", country: " + country + ", age: " + age + ", email: " + email);
                     }
                  }

                  else if(choice_1 == 2) {
                     boolean loop_3 = true;
                     while(loop_3) {
                        System.out.println("\nUpdate Author details: ");
                        System.out.println("1. Name");
                        System.out.println("2. Age");
                        System.out.println("3. Email");
                        System.out.println("0. Exit");

                        System.out.print("\nEnter your choice: ");
                        int choice_2 = scan.nextInt();

                        if(choice_2 == 0)
                           loop_3 = false;

                        else if(choice_2 == 1) {
                           System.out.print("Enter the new name for author to update: ");
                           String new_name = scan.next();

                           sql = "update author set name = '" + new_name + "' where id = '" + id + "'";
                           int c = stmt.executeUpdate(sql);
                           if(c == 1)
                              System.out.println("Author's name is updated");
                           else if(c == 0)
                              System.out.println("Error: Author's name is not updated");
                        }

                        else if(choice_2 == 2) {
                           System.out.print("Enter the new age number to update: ");
                           int age = scan.nextInt();

                           sql = "update author set age = '" + age + "' where id = '" + id + "'";
                           int c = stmt.executeUpdate(sql);
                           if(c == 1)
                              System.out.println("Author's age is updated");
                           else if(c == 0)
                              System.out.println("Error: Author's age is not updated");
                        }

                        else if(choice_2 == 3) {
                           System.out.print("Enter the new email for author to update: ");
                           String email = scan.next();

                           sql = "update author set email = '" + email + "' where id = '" + id + "'";
                           int c = stmt.executeUpdate(sql);
                           if(c == 1)
                              System.out.println("Author's email is updated");
                           else if(c == 0)
                              System.out.println("Error: Author's email is not updated");
                        }
                     }
                  }
               }
            }
         }

         else if(choice == 4) {
            System.out.print("\nEnter the publisher's id for login: ");
            int id = scan.nextInt();
            sql = "select name from publisher where id = '" + id + "'";
            rs = stmt.executeQuery(sql);

            if(rs.next() == false) 
               System.out.println("Publisher's info not found in the database");
            
            else {
               String name = rs.getString("name");
               System.out.println("Publisher id: " + id + ", name: " + name);
               boolean loop_1 = true;

               while(loop_1) {
                  System.out.println("\nPublisher menu:");
                  System.out.println("1. View the details of publisher");
                  System.out.println("2. Adding of new Book record");
                  System.out.println("3. Deletion of a Book record of this publisher");
                  System.out.println("4. Updating of publisher details");
                  System.out.println("0. Exit");

                  System.out.print("\nEnter your choice: ");
                  int choice_1 = scan.nextInt();

                  if(choice_1 == 0)
                     loop_1 = false;

                  else if(choice_1 == 1) {
                     sql = "select name, country from publisher where id = '" + id + "'";
                     rs = stmt.executeQuery(sql);

                     while(rs.next()) {
                        String publisher_name = rs.getString("name");
                        String country = rs.getString("country");

                        System.out.println("Publisher id: " + id + ", name: " + publisher_name + ", country: " + country);
                     }
                  }

                  else if(choice_1 == 2) {
                     System.out.print("Enter the book isbn number: ");
                     int isbn = scan.nextInt();
                     System.out.print("Enter the book title: ");
                     String title = scan.next();
                     System.out.print("Enter the author_id number: ");
                     int author_id = scan.nextInt();
                     System.out.print("Enter the book published year: ");
                     int year = scan.nextInt();

                     sql = "insert into book (isbn, title, author_id, publisher_id, year) values ('" + isbn + "', '" + title + "', '" + author_id + "', '" + id + "', '" + year + "')";
                     int count = stmt.executeUpdate(sql);
                     if(count == 1) 
                        System.out.println("\nInsertion query is successful.");
                     else
                        System.out.println("\nError: Insertion failed");
                  }

                  else if(choice_1 == 3) {
                     boolean loop_3 = true;
                     while(loop_3) {
                        System.out.println("\nDeletion of a Book record of this publisher:");
                        System.out.println("1. By using Book isbn number");
                        System.out.println("2. By using Book name");
                        System.out.println("0. Exit");

                        System.out.print("\nEnter your choice: ");
                        int choice_2 = scan.nextInt();

                        if(choice_2 == 0)
                           loop_3 = false;

                        else if(choice_2 == 1) {
                           System.out.print("Enter the Book isbn number: ");
                           int isbn_num = scan.nextInt();

                           sql = "delete from book where isbn = '" + isbn_num + "' and publisher_id = '" + id + "'";
                           int c = stmt.executeUpdate(sql);
                           if(c > 0)
                              System.out.println("\nBook record is deleted from Book table related to this isbn number");
                           else if(c == 0)
                              System.out.println("\nNo book record is present related to this isbn number in Book table for deleteing");
                        }

                        else if(choice_2 == 2) {
                           System.out.print("Enter the Book title: ");
                           String book_title = scan.next();

                           sql = "delete from book where title = '" + book_title + "' and publisher_id = '" + id + "'";
                           int c = stmt.executeUpdate(sql);
                           if(c > 0)
                              System.out.println("\nBook record is deleted from Book table related to this title");
                           else if(c == 0)
                              System.out.println("\nNo book record is present related to this title in Book table for deleteing");
                        }
                     }
                  }

                  else if(choice_1 == 4) {
                     System.out.print("Enter the new name for publisher to update: ");
                     String new_name = scan.next();

                     sql = "update publisher set name = '" + new_name + "' where id = '" + id + "'";
                     int c = stmt.executeUpdate(sql);
                     if(c == 1)
                        System.out.println("Publisher's name is updated");
                     else if(c == 0)
                        System.out.println("Error: Publisher's name is not updated");
                  }
               }
            }
         }
      }

//STEP 5: Clean-up environment
      rs.close();
      stmt.close();
      conn.close();
	}
   catch(SQLException se){    	//Handle errors for JDBC
   	se.printStackTrace();
   }
   catch(Exception e){       //Handle errors for Class.forName
      e.printStackTrace();
   }
   finally{				         //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }
      catch(SQLException se2){}
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }					//end finally try
   }					//end try
   System.out.println("End of Code");
}					//end main
}					//end class

//Note : By default autocommit is on. you can set to false using con.setAutoCommit(false)