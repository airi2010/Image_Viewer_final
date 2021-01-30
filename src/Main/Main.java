
package Main;

import apps.mock.Main_mock;
import apps.swing.Main_image;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        retry("");
    }
    public static void retry(String error){
        if(error.length()>0){
            System.out.println("");
            System.out.println("ERROR: "+error);
        }
        System.out.println("Escriba el número del método que quiere usar:");
        System.out.println("1º Image_Viewer por comando(Mock)");
        System.out.println("2º Image_Viewer gráfico(Swing)");
        System.out.println("3º Salir");
        Scanner scanner = new Scanner(System.in);
        int data=0;
        try{data=Integer.parseInt(scanner.next());}catch(Exception e){retry("Escriba un valor numérico");}
        
        if(data==1){
            new Main_mock().start(); 
        }else if(data==2){
            new Main_image();
        }else if(data==3){
            System.exit(0);
        }else{
            retry("Ese número no está en las opciones");
        }
    }
}
