package se.ecutb.jonatan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDaoJDBC implements CityDao {
    private City city;
    private List<City> cities = new ArrayList<>();

    private static final String INSERT =
            "INSERT INTO city(name,countryCode,district,population) VALUES (?,?,?,?)";

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "password");
        return connection;
    }

    @Override
    public City findById(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM city WHERE id=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                city = new City(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    @Override
    public List<City> findByCode(String code) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM city WHERE countrycode LIKE ?");
            statement.setString(1, "%" + code.toUpperCase() + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                city =  new City(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5));
                cities.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    @Override
    public List<City> findByName(String name) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM city WHERE name LIKE ?");
            statement.setString(1, "%" + name.toUpperCase() + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                city =  new City(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5));
                cities.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    @Override
    public List<City> findAll() {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM city");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                city =  new City(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5));
                cities.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    @Override
    public City add(City city) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("ALTER TABLE city AUTO_INCREMENT = 4079");
            statement.execute();
            statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, city.getCityName());
            statement.setString(2, city.getCountryCode());
            statement.setString(3,city.getCityDistrict());
            statement.setObject(4, city.getCityPopulation());
            statement.execute();
            resultSet = statement.getGeneratedKeys();
            while(resultSet.next()) {
                city = new City(
                        resultSet.getInt(1),
                        city.getCityName(),
                        city.getCountryCode(),
                        city.getCityDistrict(),
                        city.getCityPopulation()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    @Override
    public City update(City city) {
        return null;
    }

    @Override
    public int delete(City city) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM city WHERE ID=?");
            statement.setInt(1, city.getCityId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city.getCityId();
    }
}
