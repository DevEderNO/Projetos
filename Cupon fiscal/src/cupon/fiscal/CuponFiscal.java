/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cupon.fiscal;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author suporte06
 */
public class CuponFiscal {

    public static void main(String[] args) throws FileNotFoundException, IOException, StringIndexOutOfBoundsException {
        // Variavel responsavel por receber o arquivo .txt
        InputStream is = new FileInputStream("c:\\tokiko.txt");
        // Variavel que recebe a leitura do arquivo .txt
        InputStreamReader irs = new InputStreamReader(is);
        // Variavel que recebe o buffer da leitura do arquivo
        BufferedReader br = new BufferedReader(irs);
        int tamanho = 0, cont = 0;

        while (br != null) {
            // Variavel que recebe cada linha do arquivo .txt
            String r = br.readLine();
            cont = 0;
            try {
                tamanho = (int) r.length();
            } catch (Exception e) {
                if (tamanho > 0) {
                    return;
                }
            }

            if (r.toString().indexOf("CCF:") > 0) {
                System.out.println(r);
            }
            
            if (r.toString().indexOf("consumidor:") > 0) {
                if (r.toString().indexOf(".") > 0) {
                    System.out.println(r.substring(0, r.toString().indexOf("."))
                            + (r.substring(r.toString().indexOf(".") + 1, r.toString().indexOf(".") + 4))
                            + (r.substring(r.toString().indexOf(".") + 5, r.toString().indexOf(".") + 8))
                            + (r.substring(r.toString().indexOf(".") + 9, tamanho)));
                }
            }
            if (tamanho > 7) {
                if (r.substring(0, 8).toString().equals("TOTAL  R")) {
                    System.out.println(r);
                    cont++;
                }
            }
            if (cont > 0) {
                System.out.println("-------------------------------------------------------------------------------------");
            }

        }
    }

}
/*
if (r.toString().indexOf("CCF:") > 0) {
                System.out.println(r);
            }

            if (r.toString().indexOf("consumidor:") > 0) {
                System.out.println(r);
            }
            if (tamanho > 7) {
                if (r.substring(0, 8).toString().equals("TOTAL  R")) {
                    System.out.println(r);
                    cont++;
                }
            }
            if (cont > 0) {
                System.out.println("-----------------------------------------------------");
            }



if (r.toString().indexOf("CCF:") > 0) {
                System.out.println(r);

            }
            if (r.toString().indexOf("consumidor:") > 0) {
                System.out.println(r.substring(0,r.toString().indexOf("."))+
                        (r.substring(r.toString().indexOf(".")+1,r.toString().indexOf(".")+4))+
                        (r.substring(r.toString().indexOf(".")+5,r.toString().indexOf(".")+8))+
                        (r.substring(r.toString().indexOf(".")+9,tamanho)));
            }
            if (r.toString().indexOf("consumidor:") > 0) {
                System.out.println(r);
            }
            if (tamanho > 7) {
                if (r.substring(0,8).toString().equals("TOTAL  R")) {
                    System.out.println(r);
                }
            }
            if (r.toString().indexOf("consumidor:") > 0) {
                
                    System.out.println(r);
               
            }
 */
