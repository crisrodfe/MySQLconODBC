/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqlconodbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author CrisRodFe
 */
public class PuenteODBCAccess {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException 
    {
//      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");    
//      Connection c = DriverManager.getConnection("jdbc:odbc:localhost","root","root");
//      Statement sentencia = c.createStatement();      
//      ResultSet r;
//      
      int opcion; 
      Scanner sc = new Scanner(System.in);
      
      
        Connection conn=DriverManager.getConnection("jdbc:ucanaccess://pru_odbc.mdb");
        Statement s = conn.createStatement();
        ResultSet r;       
      do{         
        System.out.print("\n¿Qué consulta quiere realizar?\n"+
                           "1.Apellido de todos los empleados del departamento de VENTAS.\n"+
                           "2.Apellido de todos los empleados contratados entre 1980 y 1990.\n"+
                           "3.Nombre de departamento y la suma de sus salarios.\n"+
                           "4.Salir.\n"+
                           "Opción elegida: ");
        opcion = sc.nextInt();

        switch(opcion)
        {
            case 1:
                r = s.executeQuery("Select apellido from empleados,departamentos where departamentos.dnombre='ventas'");
                
                System.out.println("\n");
                while(r.next())
                {                  
                    System.out.println(r.getString(1));
                };
                break;
            case 2:
                r = s.executeQuery("Select apellido from empleados where fecha_alta > #02/02/1980# and fecha_alta < #01/02/1990#;");
                System.out.println("\n");
                while(r.next())
                {
                    System.out.println(r.getString(1));
                };
                break;
            case 3:
                r = s.executeQuery("Select dnombre,sum(salario) from empleados,departamentos group by departamentos.dnombre");
                System.out.println("\n");
                while(r.next())
                {
                    System.out.println(r.getString(1)+"  "+r.getFloat(2));
                };           
                break;
            case 4:
                System.exit(0);
                break;
            default : 
                System.out.println("La opción elegida no es correcta.");    
                break;
        }       
      }while(opcion != 4);  
    }    
}
