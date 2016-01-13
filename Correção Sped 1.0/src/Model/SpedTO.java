/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;

/**
 *
 * @author suporte06
 */
public class SpedTO {
    private File arquivo;
    
    public SpedTO(){
        this.arquivo=null;
    }

    public File getArquivo() {
        return arquivo;
    }

    public void setArquivo(File arquivo) {
        this.arquivo = arquivo;
    }

}
