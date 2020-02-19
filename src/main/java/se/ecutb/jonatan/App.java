package se.ecutb.jonatan;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        CityDaoJDBC daoJDBC = new CityDaoJDBC();
        //System.out.println(daoJDBC.findById(95).toString());
        //System.out.println(daoJDBC.findByCode("gbr"));
        //System.out.println(daoJDBC.findByName("Paris"));
        //System.out.println(daoJDBC.findAll().size());
        System.out.println(daoJDBC.add(new City("here", "tst", "there", 2000)));
        //System.out.println(daoJDBC.delete(daoJDBC.findById(4081)));
    }
}
