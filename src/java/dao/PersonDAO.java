package dao;

import model.Person;
import utils.DatabaseUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

    private final DatabaseUtils databaseUtils;

    public PersonDAO() {
        databaseUtils = new DatabaseUtils();
    }

    public List<Person> listAllPersons() throws SQLException {
        System.out.println("dao.PersonDAO.listAllPersons()");
        List<Person> listPerson = new ArrayList<>();
        String sql = "SELECT * FROM person";
        try (Connection connection = databaseUtils.conectar();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("age");
                String rol = resultSet.getString("rol");
                listPerson.add(new Person(id, name, email, age, rol));
            }
        }
        return listPerson;
    }

    public List<String> getAllRols() throws SQLException {
        System.out.println("dao.PersonDAO.getAllRols()");
        List<String> cargos = new ArrayList<>();
        String sql = "SELECT DISTINCT rol FROM person";
        try (Connection connection = databaseUtils.conectar();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                cargos.add(resultSet.getString("rol"));
            }
        }
        return cargos;
    }

    public List<Person> getClientByRol(String rol) throws SQLException {
        System.out.println("dao.PersonDAO.getClientByRol()");
        List<Person> clientes = new ArrayList<>();
        String sql = "SELECT * FROM person WHERE rol = ?";
        try (Connection connection = databaseUtils.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, rol);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("age");
                clientes.add(new Person(id, name, email, age, rol));
            }
        }
        return clientes;
    }

    public void insertPerson(Person person) throws SQLException {
        System.out.println("dao.PersonDAO.insertPerson()");
        String sql = "INSERT INTO person (name, email, age, rol) VALUES (?, ?, ?, ?)";
        try (Connection connection = databaseUtils.conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getEmail());
            preparedStatement.setInt(3, person.getAge());
            preparedStatement.setString(4, person.getRol());
            preparedStatement.executeUpdate();
        }
    }

    public Person getPersonById(int id) throws SQLException {
        System.out.println("dao.PersonDAO.getPersonById()");
        Person person = null;
        String sql = "SELECT * FROM person WHERE id = ?";
        try (Connection connection = databaseUtils.conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("age");
                String rol = resultSet.getString("rol");
                person = new Person(id, name, email, age, rol);
            }
        }
        return person;
    }

    public void updatePerson(Person person) throws SQLException {
        System.out.println("dao.PersonDAO.updatePerson()");
        String sql = "UPDATE person SET name = ?, email = ?, age = ?, rol = ? WHERE id = ?";
        try (Connection connection = databaseUtils.conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getEmail());
            preparedStatement.setInt(3, person.getAge());
            preparedStatement.setString(4, person.getRol());
            preparedStatement.setInt(5, person.getId());
            preparedStatement.executeUpdate();
        }
    }

    public void deletePerson(int id) throws SQLException {
        System.out.println("dao.PersonDAO.deletePerson()");
        String sql = "DELETE FROM person WHERE id = ?";
        try (Connection connection = databaseUtils.conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
