/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication12;
import java.util.Scanner;
import java.sql.*;
/**
 *
 * @author rohit
 */
public class JavaApplication12 {


    /**
     * @param args the command line arguments
     */
    private static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        
        System.out.println("Enter user name");
        String Username=sc.nextLine();
        System.out.println("Enter passowrd");
        String  password=sc.nextLine();
        
       
       RPS m=new RPS();
       m.setVisible(true);
       
        // TODO code application logic here
    }
    
}
