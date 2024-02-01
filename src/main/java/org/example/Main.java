package org.example;

import java.sql.*;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //inputData();
        //simpleArray();
        //sortUser();

        ConsoleWork cw = new ConsoleWork();
        cw.Start();
        /*createCategory(strConn,userName,password);

        insertCategory(strConn,userName,password);
        var list = showCategories(strConn,userName,password);
        for(var c : list){
            System.out.println(c);
        }*/


    }

    /**/
    /*private static void createCategory(String strConn, String userName,String password){
        try(Connection conn = DriverManager.getConnection(strConn,userName,password)){
            System.out.println("Connection completed");
            Statement statement = conn.createStatement();
            String createTableSQL = "CREATE TABLE IF NOT EXISTS categories ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "name VARCHAR(255) NOT NULL,"
                    + "description TEXT"
                    + ")";


            statement.execute(createTableSQL);

            statement.close();

            System.out.println("В БД створено таблицю categories");

        }catch(Exception ex){
            System.out.println("Error connection : "+ex.getMessage());
        }
    }*/
    private static void sortUsers(){
        User user = new User();
        user.setFirstName("Назарій");
        user.setLastName("Зубар");
        user.setAge(17);

        System.out.println(user);
        User[]users = new User[]{
                new User("Максим","Зубар",12),
                user
        };
        for(var item : users){
            System.out.println(item);
        }
        Arrays.sort(users);
        for(var item : users){
            System.out.println(item);
        }

    }

    private static void ShowArr(int[]arr ){
        for (var item : arr){
            System.out.printf("%d\t",item);
        }
        System.out.println();
    }
    private static void simpleArray(){
        Scanner scanner = new Scanner(System.in);
        int n = 10;
        int[]arr=new int[n];

        System.out.println("-----Звичайний масив-----");
        ShowArr(arr);
        System.out.println("-----Відсортований масив-----");
        Arrays.sort(arr);
        ShowArr(arr);
        int count = 0;
        for(var item : arr){
            if(item>=0)
                count++;
        }
        System.out.println("\nКількість додатних чисел : "+count);
    }

    private static int getRandom(int min,int max){
        Random random = new Random();
        return random.nextInt(max-min)+min;
    }
    public static void inputData(){
        Scanner input = new Scanner(System.in);
        System.out.println("Як вас звати?");
        String name = input.nextLine();
        System.out.println("Привіт "+name+" !");
    }

}
