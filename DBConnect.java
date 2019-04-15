import java.sql.*;

import javax.swing.JOptionPane;

public class DBConnect {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public DBConnect() throws Exception{
		
			Class.forName("com.mysql.jdbc.Driver");
                        String conUrl="jdbc:mysql://localhost:3306/jiss";
                        String user="root";
                        String pass="";
			con = DriverManager.getConnection(conUrl,user,pass);
			st = con.createStatement();
		
	}
	
	
	public boolean checkDB(String dbname){ //checks whether a given database exists or needs to be created
		try{
			String query = "show databases";
			rs = st.executeQuery(query);
			
			while(rs.next()){
				String name = rs.getString(dbname);
				if(name.equals("JISS")){
					return true;
				}
			}
			
			
		}catch(Exception ex){
			System.out.println("Error1 : "+ ex);
			
		}
		
		return false;
	}
	
	public void query(String query){ //executes a query
		try{
			rs = st.executeQuery(query);
			//System.out.println(query+ ": success");
		}catch(Exception ex){
			System.out.println("Error2 : "+ ex);
			
		}
	}
	
	public void update(String query){ //executes a data manipulation statement
		try{
			st.executeUpdate(query);
		//	System.out.println(query+ ": success");
		}catch(DataTruncation ex){
			JOptionPane.showMessageDialog(JISS.frame, "No changes made because some data field was too long.");
		}
		catch(Exception ex){
			System.out.println("Error : "+ ex);
			
		}
	}
	
	public ResultSet getrs(String query){ //gets result set of a particular query
		try{
			rs = st.executeQuery(query);
			//System.out.println(query+ ": success");
			return rs;
			
		}catch(Exception ex){
			System.out.println("Error : "+ ex);
			
		}
		
		return null;
	}
	
	public int queryCount(String query){ //executes a count query and returns the result
		try{
			rs = st.executeQuery(query);
			//System.out.println(query+ ": success");
			rs.next();
			return rs.getInt(1);
			
		}catch(Exception ex){
			System.out.println("Error : "+ ex);
			
		}
		return -1;
	}
}
