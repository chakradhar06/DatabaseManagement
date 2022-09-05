//STEP 1. Import required packages

import java.sql.*;


public class JdbcDemo {

//Set JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
//static final String DB_URL = "jdbc:mysql://localhost/companydb";
   static final String DB_URL = "jdbc:mysql://localhost/companydb?useSSL=false";
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
      conn = DriverManager.getConnection(DB_URL,USER,PASS);
      //STEP 2c: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();

//STEP 3: Query to database
      String sql;
      sql = "SELECT min(salary), max(salary), sum(salary), avg(salary) from employee where dno = 5";
      ResultSet rs = stmt.executeQuery(sql);
      // stmt.executeupdate(...) - for insert, update, delete 

//STEP 4: Extract data from result set
      while(rs.next()){
         //Retrieve by column name
         String min  = rs.getString("min(salary)");
         String max  = rs.getString("max(salary)");
         String sum  = rs.getString("sum(salary)");
         String avg  = rs.getString("avg(salary)");

         //Display values
         System.out.print("min: " + min);
         System.out.print(", max: " + max);
         System.out.print(", sum: " + sum);
      	System.out.println(", avg: " + avg);
      			}

//STEP 5: Clean-up environment
      rs.close();
      stmt.close();
      conn.close();
	}catch(SQLException se){    	 //Handle errors for JDBC
      	se.printStackTrace();
   	}catch(Exception e){        	//Handle errors for Class.forName
      e.printStackTrace();
   }finally{				//finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }
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
