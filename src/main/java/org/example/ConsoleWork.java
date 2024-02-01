package org.example;

import java.sql.*;
import java.util.*;

public class ConsoleWork{
    private Scanner scan = new Scanner(System.in);
    private List<CategoryItem> categories = new ArrayList<>();
    private String userName;
    private String password;
    private String host;
    private String port;
    private String database;
    private String strConn;
    public ConsoleWork(){
        this.userName="root";
        this.password="";
        this.host = "localhost";
        this.port = "3306";
        this.database = "javaDB";
        this.strConn ="jdbc:mariadb://"+host+":"+port+"/"+database;
    }

    public void Start(){
        boolean stop = false;
        while(!stop){
            System.out.println("[ 1 ] - Показати всі категорії \n" +
                    "[ 2 ] - Додати категорію \n" +
                    "[ 3 ] - Редагувати категорію \n" +
                    "[ 4 ] - Видалити категорію \n" +
                    "[ 0 ] - Вийти");
            String input;
            switch (Integer.parseInt(scan.nextLine())){
                case 1:
                    categories=showCategories(this.strConn,this.userName,this.password);
                    System.out.println("******************************************************************* \n");
                    for(var item : categories){
                        System.out.println("# "+item.getId()+" | "+item.getName()+" | "+item.getDescription());
                    }
                    do{
                        input = scan.nextLine();
                    }while (input.length()==0);
                    System.out.println("******************************************************************* \n");
                    break;
                case 2:
                    insertCategory(this.strConn,this.userName,this.password);
                    break;
                case 3:
                    editCategory(this.strConn,this.userName,this.password);
                    break;
                case 4:
                    deleteCategory(this.strConn,this.userName,this.password);
                    break;
                case 0:
                    stop=true;
                    break;
                    default:

                        break;
            }
        }
        System.out.println("Бувайте");
    }
    private static List<CategoryItem> showCategories(String strConn, String userName, String password){
        try(Connection conn = DriverManager.getConnection(strConn,userName,password)){ //відбувається конект до БЗ

            Statement statement = conn.createStatement();// створюється запит

            String selectQuery = "SELECT * FROM categories";// вводимо команду

            PreparedStatement preparedStatement = conn.prepareStatement(selectQuery);//створюємо об'єкт запиту


            ResultSet resultSet = preparedStatement.executeQuery();// зберігаємо зміну

            List<CategoryItem> list = new ArrayList<>();

            while (resultSet.next()) {
                CategoryItem category = new CategoryItem();
                // Retrieve data from the current row
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                category.setDescription(resultSet.getString("description"));
                list.add(category);
            }

            resultSet.close();//закривається потік
            preparedStatement.close();//закривається потік
            return list;
        }
        catch(Exception ex) {
            System.out.println("Помилка читання списку даних: "+ex.getMessage());
            return null;
        }
    }
    private static void insertCategory(String strConn, String userName,String password){
        try(Connection conn = DriverManager.getConnection(strConn,userName,password)){
            Scanner input = new Scanner(System.in);
            CategoryCreate newCategory = new CategoryCreate();
            System.out.println("Вкажіть назву категорії");
            newCategory.setName(input.nextLine());
            System.out.println("Вкажіть опис категорії");
            newCategory.setDescription(input.nextLine());

            Statement statement = conn.createStatement();
            String insertQuery = "INSERT INTO categories (name,description) VALUES (?,?)";

            PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);


            preparedStatement.setString(1, newCategory.getName());
            preparedStatement.setString(2, newCategory.getDescription());

            int rowsAffected = preparedStatement.executeUpdate();

            preparedStatement.close();
            System.out.println("Rows affected: " + rowsAffected);
            System.out.println("Category inserted successfully.");
        }
        catch(Exception ex) {
            System.out.println("Помилка створення категорії: "+ex.getMessage());
        }
    }
    private static void editCategory(String strConn, String userName,String password){
        try(Connection conn = DriverManager.getConnection(strConn,userName,password)){
            Scanner input = new Scanner(System.in);
            System.out.println("Вкажіть id категорії, яку хочете змінити");
            int id = Integer.parseInt(input.nextLine());
            CategoryCreate editCategory = new CategoryCreate();
            System.out.println("Вкажіть нову назву категорії");
            editCategory.setName(input.nextLine());
            System.out.println("Вкажіть новий опис категорії");
            editCategory.setDescription(input.nextLine());

            Statement statement = conn.createStatement();
            String editQuery = "UPDATE categories SET name=?, description=? WHERE id=?";

            PreparedStatement preparedStatement = conn.prepareStatement(editQuery);


            preparedStatement.setString(1, editCategory.getName());
            preparedStatement.setString(2, editCategory.getDescription());
            preparedStatement.setInt(3, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();

            System.out.println("Category edited successfully.");
        }
        catch(Exception ex) {
            System.out.println("Помилка оновлення категорії: "+ex.getMessage());
        }
    }
    private static void deleteCategory(String strConn, String userName,String password){
        try(Connection conn = DriverManager.getConnection(strConn,userName,password)){
            Scanner input = new Scanner(System.in);
            System.out.println("Вкажіть id категорії, яку хочете видалити");
            int id = Integer.parseInt(input.nextLine());
            Statement statement = conn.createStatement();
            String deleteQuery = "DELETE FROM categories WHERE id=?";

            PreparedStatement preparedStatement = conn.prepareStatement(deleteQuery);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();

            System.out.println("Category deleted successfully.");
        }
        catch(Exception ex) {
            System.out.println("Помилка видалення категорії: "+ex.getMessage());
        }
    }

}