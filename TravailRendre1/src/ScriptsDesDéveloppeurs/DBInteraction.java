
package ScriptsDesDéveloppeurs;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

public class DBInteraction {
    public static void Connection(){
        String user="root";
        String url="jdbc:mysql://localhost:3306/db";
        String password="1234";
        Connection con=null;
        Statement str=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(url, user, password);
            str = con.createStatement();
            String Table="CREATE TABLE DevData (Developpeurs VARCHAR (32),Jour CHAR(11),NbScripts INTEGER)";
            str.executeUpdate(Table);
            System.out.println("Table DevData créée avec succès.");
            String[] sql = {
                "INSERT INTO DevData VALUES ('ALAMI', 'Lundi', 1)",
                "INSERT INTO DevData VALUES ('WAFI', 'Lundi', 2)",
                "INSERT INTO DevData VALUES ('SALAMI', 'Mardi', 9)",
                "INSERT INTO DevData VALUES ('SAFI', 'Mardi', 2)",
                "INSERT INTO DevData VALUES ('ALAMI', 'Mardi', 2)",
                "INSERT INTO DevData VALUES ('SEBIHI', 'Mercredi', 2)",
                "INSERT INTO DevData VALUES ('WAFI', 'Jeudi', 3)",
                "INSERT INTO DevData VALUES ('ALAOUI', 'Vendredi', 9)",
                "INSERT INTO DevData VALUES ('WAFI', 'Vendredi', 3)",
                "INSERT INTO DevData VALUES ('SEBIHI', 'Vendredi', 4)"
            };
            for (String i : sql) {
                System.out.println("Exécution de : " + i); 
                str.addBatch(i);
            }

            str.executeBatch();
            System.out.println("Données insérées avec succès.");
            
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBInteraction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (str != null) str.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                System.out.println("Impossible de libérer les ressources");
            }
        }
    }
    public static void ExoJDBC(){
        Connection con=null;
        Statement str=null;
        ResultSet rs=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","1234");
            str=con.createStatement();
            String sql="SELECT jour, MAX(NbScripts) AS max_scripts FROM DevData GROUP BY jour" ;
            rs=str.executeQuery(sql);
            while(rs.next()){
                System.out.println("Jour: " + rs.getString(1)+", Max Scripts: " + rs.getInt(2));
            }
            rs.close();
            str.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void ExoJDBC2(){
        Connection con=null;
        Statement str=null;
        ResultSet rs=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","1234");
            str=con.createStatement();
            String sql="SELECT Developpeurs, SUM(NbScripts) AS sum_scripts FROM DevData GROUP BY Developpeurs ORDER BY sum_scripts DESC" ;
            rs=str.executeQuery(sql);
            while(rs.next()){
                System.out.println("Le Developpeur: " + rs.getString(1)+", Total scripts: " + rs.getInt(2));
            }
            rs.close();
            str.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
    public static void ExoJDBC3(){
         Connection con=null;
         Statement str=null;
         ResultSet rs=null;
         try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","1234");
            str=con.createStatement();
            String sql="Select SUM(NbScripts) as sum_scripts From DevData";
            rs=str.executeQuery(sql);
            if(rs.next()){
            System.out.println("Le total de scripts dans une semaine est :"+rs.getInt(1));
            }
            rs.close();
            str.close();
            con.close();
         }catch(Exception e){
             e.printStackTrace();
         }
    }
    public static void ExoJDBC4(){
        Connection con=null;
        Statement str=null;
        ResultSet rs=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","1234");
            str=con.createStatement();
            String sql="SELECT Developpeurs, SUM(NbScripts) AS sum_scripts FROM DevData Group by Developpeurs " ;
            rs=str.executeQuery(sql);
            while(rs.next()){
                System.out.println("Le Developpeur: " + rs.getString(1)+", Total scripts: " + rs.getInt(2));
            }
            rs.close();
            str.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
    public static void ExoJDBC5(){
        Connection con=null;
        Statement str=null;
        ResultSet rs=null;
        ResultSetMetaData rsmd=null;
        Scanner s= new Scanner(System.in);
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","1234");
            str=con.createStatement();
            System.out.println("Veuillez entrer une requete sql");
            String sql=s.nextLine();
            boolean hasResultSet = str.execute(sql);
            if(hasResultSet){
            rs=str.executeQuery(sql);
            rsmd=rs.getMetaData();
            int nbrcolonne=rsmd.getColumnCount();
            
            System.out.println("Le nombre de colonnes est : "+nbrcolonne);
            for(int i = 1; i <= nbrcolonne; i++){
               System.out.println("Le nom de colonne est : "+rsmd.getColumnName(i));
               System.out.println("Le type des données est : "+rsmd.getColumnTypeName(i));
            }
            System.out.println("\n Le contenu de la table est : ");
            while(rs.next()){
                for (int i = 1; i <= nbrcolonne; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println("\n");
            }
            }else{
                int rowCount = str.getUpdateCount();
                System.out.println("Nombre de lignes modifiées : " + rowCount);
            }
            rs.close();
            str.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
    
    public static void main(String[] args){
        //Connection();
        //ExoJDBC();
        //ExoJDBC2();
        //ExoJDBC3();
        //ExoJDBC4();
        ExoJDBC5();
    }   
}
