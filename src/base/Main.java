package base;

import java.sql.*;

public class Main
{
    static final String place = "jdbc:mysql://";
    static final String host = "localhost";
    static final String port = "3306";
    static final String dataBaseName = "tienda";
    static final String user = "root";
    static final String password = "";
    static final String urlParameters = "?serverTimezone=UTC";
    
    static final String url = String.format("%s%s:%s/%s%s", 
        place, host, port, dataBaseName, urlParameters);
    
    public static void main(String[] args)
    {
        Main app = new Main();
        app.run();
    }
    
    public void run()
    {
        try
        {
            //Cargar Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver cargado correctamente.");

            //Conexion
            Connection conexion = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado a: " + url);
            System.out.println("");
            
            //Sentencia
            Statement sentencia = conexion.createStatement();
            
            //Ejecutar y almacenar resultado de la consulta empleados
            ResultSet resulEmpleados = sentencia.executeQuery("select * from empleados");
            
            //Recorrer los resultados
            while (resulEmpleados.next())
            {
                int codigo = resulEmpleados.getInt("e_codigo");
                String nombre = resulEmpleados.getString("e_nombre");
                String apellidos = resulEmpleados.getString("e_apellidos");
                String contrasena = resulEmpleados.getString("e_contrasena");
                System.out.println(String.format("%d %s %s : %s", 
                    codigo, nombre, apellidos, contrasena));
                
            }
            
            System.out.print("\n");
            
            //Ejecutar y almacenar resultado de la consulta productos
            ResultSet resulProductos = sentencia.executeQuery("select * from productos");
            
            //Recorrer los resultados
            while (resulProductos.next())
            {
                int codigo = resulProductos.getInt("p_codigo");
                String nombre = resulProductos.getString("p_nombre");
                String descripcion = resulProductos.getString("p_descripcion");
                double precio = resulProductos.getDouble("p_precio");
                System.out.println(String.format("%d %s %s : %s", 
                    codigo, nombre, descripcion, precio));
                
            }
            
            //cerrar conexion total
            sentencia.close();
            resulEmpleados.close();
            resulProductos.close();
            conexion.close();
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println("Erro al cargar el driver.");
        }
        catch (SQLException ex)
        {
            System.out.println("Error al conectarse a: " + url + "\nPorque: " + ex);
        }
    }
    
}
