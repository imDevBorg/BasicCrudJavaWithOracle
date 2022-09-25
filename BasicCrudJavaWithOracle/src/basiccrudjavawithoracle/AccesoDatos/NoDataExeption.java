
package basiccrudjavawithoracle.AccesoDatos;

public class NoDataExeption extends java.lang.Exception{
    public NoDataExeption (){}
    
   public NoDataExeption (String msg){
       super(msg);
   }
    
}
