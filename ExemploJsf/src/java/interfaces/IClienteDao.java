/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

import java.util.ArrayList;
import to.ClienteTo;

/**
 *
 * @author junera
 */
public interface IClienteDao {
    public String incluir(ClienteTo cliente) throws Exception;
    public String alterar (ClienteTo cliente) throws Exception;
    public void excluirID (long cliente) throws Exception;
    public ClienteTo consultarID(long cliente) throws Exception;
    public ArrayList <ClienteTo> consultarTodos() throws Exception;
}
