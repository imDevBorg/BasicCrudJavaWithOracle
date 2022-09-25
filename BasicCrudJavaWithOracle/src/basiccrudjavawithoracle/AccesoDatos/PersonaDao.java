
package basiccrudjavawithoracle.AccesoDatos;

import basiccrudjavawithoracle.modelo.Persona;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import oracle.jdbc.OracleTypes;


public class PersonaDao extends Conexion implements DataBaseMethodsI{
    
    private static final String FINDALL_PERSONA="{?=call findAllPersona()}";
    private static final String CREATE_PERSONA="{call insertPersona(?,?,?)}";
    private static final String DELETE_PERSONA="{call deletePersona(?)}";
    private static final String FINDONE_PERSONA="{?=call findOnePersona(?)}";
    private static final String UPDATE_PERSONA="{call updatePersona(?,?,?,?)}";

    @Override
    public Collection findAll() throws GlobalException, NoDataExeption {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("No se ha localizado el Driver");
        } catch (SQLException e) {
            throw new NoDataExeption("La base de datos no se encuentra disponible");
        }
        
        ResultSet rs= null;
        ArrayList collection = new ArrayList();
        Persona personaObj= null;
        CallableStatement pstmt= null;
        
        try{
            pstmt= conexion.prepareCall(FINDALL_PERSONA);
            pstmt.registerOutParameter(1,OracleTypes.CURSOR);
            pstmt.execute();
            rs= (ResultSet) pstmt.getObject(1);
            while(rs.next()){
                personaObj=new Persona(
                        rs.getInt("idPersona"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email")
                
                );
                collection.add(personaObj);
            }
            
        }catch(SQLException e){
            e.printStackTrace();
            throw new GlobalException("Setencia no valida");
        } finally{
            try{
                if(rs!= null){
                    rs.close();
                }
                
                if(pstmt!=null){
                    pstmt.close();
                }
                
                desconectar();
            }catch(SQLException e){
                throw new GlobalException("Estatutos no validos");
            }
        }
        
        if(collection == null || collection.size()==0){
            throw new NoDataExeption("No hay datos ");
        }
        return collection;
    }

    @Override
    public void create(Object obj) throws GlobalException, NoDataExeption {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("No se ha localizado el Driver");
        } catch (SQLException e) {
            throw new NoDataExeption("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt=null;
        Persona persona=(Persona)obj;
        try {
            pstmt= conexion.prepareCall(CREATE_PERSONA);
            pstmt.setString(1,persona.getNombre());
            pstmt.setString(2,persona.getApellido());
            pstmt.setString(3,persona.getEmail());
            boolean resultado=pstmt.execute();
            if (resultado== true) {
                throw new NoDataExeption("No se realizó la inserción");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Llave duplicada");
        } finally{
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }

    @Override
    public void delete(Object obj) throws GlobalException, NoDataExeption {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("No se ha localizado el Driver");
        } catch (SQLException e) {
            throw new NoDataExeption("La base de datos no se encuentra disponible");
        }
        PreparedStatement pstmt= null;
        Persona personaObj= (Persona)obj;
        try {
            pstmt= conexion.prepareCall(DELETE_PERSONA);
            pstmt.setInt(1, personaObj.getIdPersona());
            int resultado= pstmt.executeUpdate();
            if (resultado==0) {
                throw new NoDataExeption("No se realizo la eliminacion");
            }
        } catch (SQLException e) {
            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (pstmt !=null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw  new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }

    @Override
    public Object findOne(Object obj) throws GlobalException, NoDataExeption {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("No se ha localizado el Driver");
        } catch (SQLException e) {
            throw new NoDataExeption("La base de datos no se encuentra disponible");
        }
        ResultSet rs=null;
        Persona personaObj= (Persona)obj;
        CallableStatement pstmt= null;
        
        try{
            pstmt= conexion.prepareCall(FINDONE_PERSONA);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setInt(2, personaObj.getIdPersona());
            pstmt.execute();
            rs=(ResultSet) pstmt.getObject(1);
            while (rs.next()) {                
                personaObj.setIdPersona(rs.getInt("idPersona"));
                personaObj.setNombre(rs.getString("nombre"));
                personaObj.setApellido(rs.getString("apellido"));
                personaObj.setEmail(rs.getString("email"));
            }

        }catch(SQLException e){
            e.printStackTrace();
            throw new GlobalException("Setencia no valida");
        } finally{
            try{
                if(rs!= null){
                    rs.close();
                }
                
                if(pstmt!=null){
                    pstmt.close();
                }
                
                desconectar();
            }catch(SQLException e){
                throw new GlobalException("Estatutos no validos");
            }
        }
        if (personaObj==null) {
            throw new NoDataExeption("No hay Datos");
        }
        return personaObj;

    }

    @Override
    public void update(Object obj) throws GlobalException, NoDataExeption {
        try{
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("No se ha localizado el Driver");
        } catch (SQLException e) {
            throw new NoDataExeption("La base de datos no se encuentra disponible");
        }
        PreparedStatement pstmt = null;
        Persona personaObj=(Persona)obj;
        try {
            pstmt= conexion.prepareCall(UPDATE_PERSONA);
            pstmt.setInt(1, personaObj.getIdPersona());
            pstmt.setString(2, personaObj.getNombre());
            pstmt.setString(3, personaObj.getApellido());
            pstmt.setString(4, personaObj.getEmail());
            int resultado= pstmt.executeUpdate();
            if (resultado== 0) {
                throw new NoDataExeption("No se realizo la actualizacion");
            }else{
                System.out.println("Modificacion satisfactoria");
            }
        } catch (SQLException e) {
            throw  new GlobalException("Sentetncia no valida");
        }finally{
            try {
                if (pstmt!=null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw  new GlobalException("Estatus invalidos o nulos");
            }
        }

              
    }
    


}

