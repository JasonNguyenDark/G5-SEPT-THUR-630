package com.example.devbranch2;

import com.mysql.jdbc.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;

@RestController
public class DemoController {

    @Autowired
    private CustomerRepository customerRepository;
    private Customer c = new Customer();

    // Adding new user account to database
    @PostMapping("/signup")
    public String addCustomer(@RequestParam String ID, @RequestParam String first, @RequestParam String last) {
        Customer customer = new Customer();
        // Adding here
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con= DriverManager.getConnection(
                    "jdbc:mysql://database-1.cavyvvu6hnkp.us-east-1.rds.amazonaws.com:3306/database-1?characterEncoding=utf8",
                    "admin_jason", "RMIT!1234");
            System.out.println("Database connected!");
            System.out.println("Adding new account..");
            Statement stmt=con.createStatement();

            String email = first + last + "@mail.com";
            int id = Integer.parseInt(ID);
            String sql = "INSERT INTO patient (ID, firstname, surname, email, password)"
                    + " VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = con.prepareStatement(sql);

            preparedStmt.setInt (1, id);
            preparedStmt.setString (2, first);
            preparedStmt.setString   (3, last);
            preparedStmt.setString(4, email);
            preparedStmt.setString    (5, "1");

            preparedStmt.execute();

            System.out.println("Adding successfully!");
            con.close();
        }catch(Exception e){ System.out.println(e);}
        // Finish adding
        customer.setFirstName(first);
        customer.setLastName(last);
        System.out.println(first + " " + last);
        customerRepository.save(customer);
        return "<head>\n" +
                "    <meta http-equiv=\"refresh\" content=\"0.1; URL=login.html\" />\n" +
                "</head>" +
                "<p>Go to Log in page..</p>";
    }


    // https://www.codejava.net/coding/how-to-code-login-and-logout-with-java-servlet-jsp-and-mysql
    // Checking the sign in info of user
    @PostMapping("/signin")
    public String logIn(@RequestParam String email, @RequestParam String password) {

        // Adding here
        try {
            // create connection with database
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con= DriverManager.getConnection(
                    "jdbc:mysql://database-1.cavyvvu6hnkp.us-east-1.rds.amazonaws.com:3306/database-1?characterEncoding=utf8",
                    "admin_jason", "RMIT!1234");
            System.out.println("Database connected!");
            System.out.println("Checking account info..");

            // create statement
            Statement stmt=con.createStatement();
            // create sql statement
            String sql = "SELECT * FROM patient WHERE email = '" + email + "'";
            System.out.println("SQL prepared");

            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("SQL executed successfully!");

            String pass = null;
            int id = -1;
            String fullName = null;
            while(rs.next()) {
//                System.out.println(rs.getInt(1));
//                System.out.println(rs.getString(2));
//                System.out.println(rs.getString(3));
//                System.out.println(rs.getString(4));
//                System.out.println(rs.getString(5));
                pass = rs.getString(5);
                id = rs.getInt(1);
                fullName = rs.getString(2) + rs.getString(3);
            }
            System.out.println("Password = " + pass);

            c.setId(id);
            c.setFullName(fullName);

            con.close();
            if (password.contains(pass) == true) {
                System.out.println("Log in successfully!");
                return "<head>\n" +
                        "    <meta http-equiv=\"refresh\" content=\"0.1; URL=home.html\" />\n" +
                        "</head>" +
                        "<p>Log in successfully!</p>";
            } else {
                System.out.println("Log in unsuccessfully!!!");
                return "<head>\n" +
                        "    <meta http-equiv=\"refresh\" content=\"1; URL=login.html\" />\n" +
                        "</head>" +
                        "<p>Log in unsuccessfully!!!</p>";
            }

        }catch(Exception e){ System.out.println(e);}
        // Finish adding

        //customerRepository.save(customer);
        return "nah";
    }

    // Database func
    @GetMapping("/list")
    public void getCustomers() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con= DriverManager.getConnection(
                    "jdbc:mysql://database-1.cavyvvu6hnkp.us-east-1.rds.amazonaws.com:3306/database-1?characterEncoding=utf8",
                    "admin_jason", "RMIT!1234");

            System.out.println("Database connected!");
            Statement stmt=con.createStatement();
            ResultSet rs = null;
            rs = stmt.executeQuery("SELECT * FROM patient");
            System.out.println("Patient list:");
            while(rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  "
                        + rs.getString(3) + " " + rs.getString(4)+ " " + rs.getString(5));
            }

            con.close();
        }catch(Exception e){ System.out.println(e);}

        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con= DriverManager.getConnection(
                    "jdbc:mysql://database-1.cavyvvu6hnkp.us-east-1.rds.amazonaws.com:3306/database-1?characterEncoding=utf8",
                    "admin_jason", "RMIT!1234");

            System.out.println("Database connected!");
            Statement stmt=con.createStatement();
            ResultSet rs2 = null;
            rs2 = stmt.executeQuery("SELECT * FROM doctor");
            System.out.println("Doctor list:");
            while(rs2.next()) {
                System.out.println(rs2.getInt(1)+"  "+rs2.getString(2)+"  "
                        +rs2.getString(3)+" "+rs2.getString(4)+" "+rs2.getString(5));
            }

            con.close();
        }catch(Exception e){ System.out.println(e);}
    }

    @GetMapping("/find/{id}")
    public Customer findCustomerById(@PathVariable Integer id) {
        return customerRepository.findCustomerById(id);
    }
}