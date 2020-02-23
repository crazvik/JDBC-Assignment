package se.ecutb.jonatan.data;

import org.springframework.stereotype.Repository;
import se.ecutb.jonatan.entity.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static se.ecutb.jonatan.data.Database.getConnection;

@Repository
public class CityDaoJDBC implements CityDao {
    private City city;
    private final List<City> cities = new ArrayList<>();

    private static final String INSERT_SQL = "INSERT INTO country(Name, Code, Region, Population) VALUES(?, ?, ?, ?) ";

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
        cities.clear();
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM city WHERE countryCode LIKE ?");
            statement.setString(1, "%" + code.toUpperCase() + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                city =  new City(resultSet.getString(2),
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
        cities.clear();
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM city WHERE name LIKE ?");
            statement.setString(1, "%" + name.toUpperCase() + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                city =  new City(resultSet.getString(2),
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
        cities.clear();
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM city");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                city =  new City(resultSet.getString(2),
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
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT exists(SELECT * FROM country WHERE Code LIKE ?)");
            statement.setString(1, city.getCountryCode());
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
            } else {
                statement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, city.getCityName());
                statement.setString(2, city.getCountryCode());
                statement.setString(3, city.getCityDistrict());
                statement.setInt(4, city.getCityPopulation());
                statement.executeUpdate();
            }
            statement = connection.prepareStatement("INSERT INTO city(Name, CountryCode, District, Population) VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, city.getCityName());
            statement.setString(2, city.getCountryCode());
            statement.setString(3, city.getCityDistrict());
            statement.setInt(4, city.getCityPopulation());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                city.setCityId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("City can't be null!");
        }
        return city;
    }

    @Override
    public City update(City city) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE city SET name=? WHERE ID=?", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, city.getCityName());
            statement.setInt(2, city.getCityId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("City doesn't exist!");
        }
        return city;
    }

    @Override
    public int delete(City city) {
        int rowsAffected = 0;
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM city WHERE ID=?", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, city.getCityId());
            rowsAffected = statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    city.setCityId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("City doesn't exist!");
        }
        return rowsAffected;
    }
}