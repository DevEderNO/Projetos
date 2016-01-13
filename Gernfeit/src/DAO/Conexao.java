
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    
    public Connection getConexao(String caminho) throws Exception{
        Connection con;
        try{
            String driver = "com.hxtt.sql.dbf.DBFDriver";
            String url = "jdbc:DBF:/";
            Class.forName(driver);
            con = DriverManager.getConnection(url+caminho);
        }catch(Exception ex){
            throw ex;
        }
        return con;
    }
}