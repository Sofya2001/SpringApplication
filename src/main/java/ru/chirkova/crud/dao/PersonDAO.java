package ru.chirkova.crud.dao;

import org.springframework.stereotype.Component;
import ru.chirkova.crud.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private static final String URL="jdbc:postgresql://localhost:5432/person_db";
    private static final String USERNAME="postgres";
    private static final String PASSWORD="Sofya2001!";
    private static Connection connection;

    static{
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection= DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> index() throws SQLException {
        List<Person> people=new ArrayList<>();
        Statement statement=connection.createStatement();
        String SQL="Select * from person";
        ResultSet resultSet= statement.executeQuery(SQL);

        while(resultSet.next()){
            Person person=new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));
            people.add(person);
        }
        return people;
    }
    public Person show(int id) throws SQLException {
        Person person=null;
     PreparedStatement preparedStatement=
             connection.prepareStatement("SELECT * from person where id=?");
     preparedStatement.setInt(1,id);
     ResultSet resultSet=preparedStatement.executeQuery();
     resultSet.next();
     person=new Person();
     person.setName(resultSet.getString("name"));
     person.setAge(resultSet.getInt("age"));
     person.setId(resultSet.getInt("id"));
     person.setEmail(resultSet.getString("email"));

     return person;
    }


    public void save(Person person) throws SQLException {
        PreparedStatement preparedStatement=
                connection.prepareStatement("INSERT INTO person values (1,?,?,?)");
     preparedStatement.setString(1, person.getName());
     preparedStatement.setInt(2,person.getAge());
     preparedStatement.setString(3, person.getEmail());

     preparedStatement.executeUpdate();
    }
    public void updatePerson(int id,Person person) throws SQLException {

        PreparedStatement preparedStatement=
                connection.prepareStatement("UPDATE person set name=?,age=?,email=? where id=?");
        preparedStatement.setString(1, person.getName());
        preparedStatement.setInt(2, person.getAge());
        preparedStatement.setString(3, person.getEmail());
        preparedStatement.setInt(4, id);

        preparedStatement.executeUpdate();
    }
    public void deletePerson(int id) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("DELETE from person where id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }
    }