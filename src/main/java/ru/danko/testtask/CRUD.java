package ru.danko.testtask;

import java.sql.*;
import java.util.Scanner;

public class CRUD{

    static final String DB_URL = "jdbc:postgresql://localhost:5432/studentsdatabase";
    static final String USER = "postgres";
    static final String PASS = "root";

    public String newStudent(){
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Введите имя");
        String name = sc.next();
        System.out.println("Введите фамилию");
        String surname = sc.next();
        try (Connection connection = DriverManager
                .getConnection(DB_URL, USER, PASS)){
            String sql = "INSERT INTO studentsdatabase.student (name,surname) VALUES (?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,surname);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return "Проверьте введенные параметры.";
        }
        return "Новый студент был успешно добавлен!";
    }



    public void getStudentByNumber(int number){
        String sql = "SELECT studentsdatabase.student_group.number, " +
                "COUNT(studentsdatabase.record_book.group_id)  " +
                "FROM studentsdatabase.student_group, studentsdatabase.record_book " +
                "GROUP BY studentsdatabase.student_group.number " +
                "HAVING COUNT(studentsdatabase.record_book.group_id)>?";
        try(Connection connection = DriverManager.
                getConnection(DB_URL, USER, PASS)){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,number);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while(resultSet.next()){
                String num = resultSet.getString(1);
                String count = resultSet.getString(2);
                String output = "Group number - %s, count - %s";
                System.out.println(String.format(output, num,count));
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            System.out.println("Такого студента не существует или проверьте введнные параметры.");
        }
    }

    public void getStudentsBySameYear(String year){
        String sql = "SELECT * FROM studentsdatabase.student WHERE studentsdatabase.record_book.graduation_year=?";
        try(Connection connection = DriverManager.
                getConnection(DB_URL, USER, PASS)){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,year);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            studentOutPut(resultSet);
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            System.out.println("Таких студентов не существует или проверьте введнные параметры.");
        }
    }

    public void updateStudent(Long id){
        String sql = "UPDATE studentsdatabase.student SET name=?,surname=?  WHERE id=?";
        String sql2 = "UPDATE studentsdatabase.student_group SET number=? WHERE id=?";
        String sql3 = "UPDATE studentsdatabase.record_book SET graduation_year=?  WHERE id=?";
        try(Connection connection = DriverManager.
                getConnection(DB_URL, USER, PASS)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql2);
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql3);
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите новое имя: ");
            String name = sc.next();
            System.out.println("Введите новую фамилию: ");
            String surname = sc.next();
            System.out.println("Введите новый номер группы: ");
            String number = sc.next();
            System.out.println("Введите новый год выпуска: ");
            String graduationYear = sc.next();
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setLong(3, id);
            preparedStatement1.setString(1, number);
            preparedStatement1.setLong(2, id);
            preparedStatement2.setString(1, graduationYear);
            preparedStatement2.setLong(2, id);
            preparedStatement.executeUpdate();
            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();
            System.out.println("Информация успешно обновлена!");
            sc.close();
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            System.out.println("Такого студента не существует или проверьте введнные параметры.");
        }
    }

    public void deleteStudent(Long id){
        String sql = "DELETE FROM studentsdatabase.student WHERE id=?";
        try(Connection connection = DriverManager.getConnection(DB_URL,USER,PASS)){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            System.out.println("Студент был успешно удален.");
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            System.out.println("Такого студента не существует или проверьте введнные параметры.");
        }

    }

    public void getStudentAllOrByIdOrNameOrSurnameOrNumberOrGraduation(String variant,String value){
        String sql = sqlSelect(variant);
        try (Connection connection = DriverManager
                .getConnection(DB_URL, USER, PASS)){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            if(variant.equals("id")){
                preparedStatement.setLong(1,Long.parseLong(variant));
            }
            if(!variant.equals("all")&&!variant.equals("id")){
                preparedStatement.setString(1,value);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            studentOutPut(resultSet);
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            System.out.println("Такого студента не существует или проверьте введнные параметры.");
        }
    }
    public void getStudentAll(){
        String sql = "SELECT student.name,student.surname, studentsdatabase.student_group.number," +
                "studentsdatabase.record_book.graduation_year " +
                "FROM studentsdatabase.student,studentsdatabase.record_book,studentsdatabase.student_group " +
                "WHERE student.id=record_book.id AND number=record_book.group_id";
        try (Connection connection = DriverManager
                .getConnection(DB_URL, USER, PASS)){
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            studentOutPut(resultSet);
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            System.out.println("Такого студента не существует или проверьте введнные параметры.");
        }
    }

    public String sqlSelect(String variant){
        String preSql = "SELECT studentsdatabase.student.name, studentsdatabase.student.surname, " +
                "studentsdatabase.student_group.number, studentsdatabase.record_book.graduation_year " +
                "FROM studentsdatabase.student,studentsdatabase.record_book," +
                "studentsdatabase.student_group";
        String sql=null;
        switch (variant){
            case ("name"):
                sql = preSql + " WHERE studentsdatabase.student.name = ? ORDER BY studentsdatabase.student.surname DESC";
                break;
            case("surname"):
                sql = preSql + " WHERE studentsdatabase.student.surname = ? ORDER BY studentsdatabase.student.surname DESC";
                break;
            case("number"):
                sql = preSql + " WHERE studentsdatabase.student_group.number = ? ORDER BY studentsdatabase.student.surname DESC";
                break;
            case("graduation_year"):
                sql = preSql + " WHERE studentsdatabase.record_book.graduation_year = ? ORDER BY studentsdatabase.student.surname DESC";
                break;
            case("id"):
                sql = preSql + " WHERE studentsdatabase.student.id = ? ORDER BY studentsdatabase.student.surname DESC";
                break;
            case("all"):
                sql = preSql + " ORDER BY studentsdatabase.student.surname DESC";

        }
        return sql;
    }

    public void studentOutPut(ResultSet resultSet) throws SQLException{
        int count = 1;
        while(resultSet.next()){
            String name = resultSet.getString(1);
            String surname = resultSet.getString(2);
            String number = resultSet.getString(3);
            String graduationYear = resultSet.getString(4);
            /*String number = resultSet.getString(3);
            String graduationYear = resultSet.getString(4);
            String output = "Student #%d: %s %s, group number - %s, graduation year - %s";*/
            System.out.println("Student №"+count+": Name - "+name+", Surname - "+surname+", Group number - "+number+", Graduation year - "+graduationYear);
        }
    }


}
