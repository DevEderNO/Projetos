package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
 
    public Connection getConexao() throws Exception {
        Connection conn;
        try {

            String driver = "org.postgresql.Driver";
            String servidor = "localhost:5432";
            String conexao = "jdbc:postgresql";
            String bdNome = "ling6";
            String usuario = "ling6";
            String senha = "ling6";
            Class.forName(driver);
            conn = DriverManager.getConnection(conexao + "://" + servidor + "/" + bdNome, usuario, senha);

        } catch (Exception ex) {
            throw ex;
        }
        return conn;
    }
}
