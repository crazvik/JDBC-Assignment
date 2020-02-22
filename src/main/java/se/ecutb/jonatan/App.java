package se.ecutb.jonatan;

import se.ecutb.jonatan.data.CityDaoJDBC;
public class App 
{
    public static void main( String[] args ) {
        CityDaoJDBC daoJDBC = new CityDaoJDBC();
        //System.out.println(daoJDBC.findById(95).toString());
        //System.out.println(daoJDBC.findByCode("gbr"));
        //System.out.println(daoJDBC.findByName("Paris"));
        //System.out.println(daoJDBC.findAll().size());
        //System.out.println(daoJDBC.add(new City("test", "tst", "here", 20)));
        //System.out.println(daoJDBC.delete(daoJDBC.findById(4090)));
    }
}
