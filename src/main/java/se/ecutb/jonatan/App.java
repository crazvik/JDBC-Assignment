package se.ecutb.jonatan;

import se.ecutb.jonatan.data.CityDaoJDBC;
import se.ecutb.jonatan.entity.City;

public class App
{
    public static void main( String[] args ) {
        CityDaoJDBC daoJDBC = new CityDaoJDBC();
        System.out.println(daoJDBC.findById(95).toString());
        System.out.println(daoJDBC.findByCode("gbr"));
        System.out.println(daoJDBC.findByName("Paris"));
        System.out.println(daoJDBC.findAll().size() + " rows");
        System.out.println(daoJDBC.add(new City("test", "JPN", "here", 20)));
        System.out.println(daoJDBC.delete(new City("test", "JPN", "here", 20)) + " row(s) removed");
        //System.out.println(daoJDBC.update(new City(4079,"thiscity", "", "", 0)));
    }
}
