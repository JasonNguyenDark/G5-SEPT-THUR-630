package com.example.devbranch2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@RestController
public class DemoController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/add")
    public String addCustomer(@RequestParam String first, @RequestParam String last) {
        Customer customer = new Customer();
        customer.setFirstName(first);
        customer.setLastName(last);
        customerRepository.save(customer);
        return "Added new customer to repo!";
    }

    @GetMapping("/list")
    public void getCustomers() {
        //ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://database-1.cavyvvu6hnkp.us-east-1.rds.amazonaws.com:3306/database-1?characterEncoding=utf8",
                    "admin_jason", "RMIT!1234");

            System.out.println("Database connected!");
            Statement stmt=con.createStatement();
            ResultSet rs = null;
            rs = stmt.executeQuery("SELECT * FROM patient");
            while(rs.next()) {
                System.out.println("Patient list:");
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  "
                        + rs.getString(3) + " " + rs.getString(4)+ " " + rs.getString(5));
            }

            con.close();
        }catch(Exception e){ System.out.println(e);}

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://database-1.cavyvvu6hnkp.us-east-1.rds.amazonaws.com:3306/database-1?characterEncoding=utf8",
                    "admin_jason", "RMIT!1234");

            System.out.println("Database connected!");
            Statement stmt=con.createStatement();
            ResultSet rs2 = null;
            rs2 = stmt.executeQuery("SELECT * FROM doctor");;
            while(rs2.next()) {

                System.out.println("Doctor list:");
                System.out.println(rs2.getInt(1)+"  "+rs2.getString(2)+"  "
                        +rs2.getString(3)+" "+rs2.getString(4)+" "+rs2.getString(5));
            }

            con.close();
        }catch(Exception e){ System.out.println(e);}
        //return rs;
    }
    //return customerRepository.findAll();

    @GetMapping("/find/{id}")
    public Customer findCustomerById(@PathVariable Integer id) {
        return customerRepository.findCustomerById(id);
    }
}