
package basiccrudjavawithoracle;

import basiccrudjavawithoracle.AccesoDatos.GlobalException;
import basiccrudjavawithoracle.AccesoDatos.NoDataExeption;
import java.util.logging.Level;
import java.util.logging.Logger;
import basiccrudjavawithoracle.AccesoDatos.PersonaDao;
import basiccrudjavawithoracle.modelo.Persona;
import java.sql.SQLException;

public class BasicCrudJavaWithOracle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws SQLException {
        try {
            
            PersonaDao personaDao= new PersonaDao();
            
            Persona objPersona=new Persona(21,"CarlosUpdt","XingUpdt","a@a.com");
            
            personaDao.create(objPersona);
            /*
            for(Object object : personaDao.findAll()){
                Persona personaObj = (Persona) object;
                System.out.println(personaObj.toString());
            }*/
            
            
        } catch (GlobalException ex) {
            Logger.getLogger(BasicCrudJavaWithOracle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataExeption ex) {
            Logger.getLogger(BasicCrudJavaWithOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
