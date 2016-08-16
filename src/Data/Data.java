package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Data{
	Connection con;
	public 	Data(){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//String url = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=src/´Ê¿â/´Ê¿â.mdb";
			con = DriverManager.getConnection("jdbc:odbc:´Ê¿â");
			//con = DriverManager.getConnection(url,"","");
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace(System.out);
		}
	}
	public ResultSet getData(){
		Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "SELECT * FROM ´ÊÓïÐÅÏ¢";
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
		
	}
	public void addData(String word,String character){
		try{
		PreparedStatement pstm = con.prepareStatement("INSERT INTO ´ÊÓïÐÅÏ¢(´ÊÓï,ÌáÊ¾) VALUES(?,?)");
		pstm.setString(1,word);
		pstm.setString(2,character);
		pstm.executeUpdate();
	    System.out.println("Ìí¼Ó³É¹¦");
	    }
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deletData(String str){
		try{
			System.out.println(str);
		PreparedStatement pstm = con.prepareStatement("DELETE FROM ´ÊÓïÐÅÏ¢ WHERE ´ÊÓï='"+str+"'");
	    pstm.executeUpdate();
	    System.out.println("É¾³ý"+str+"Íê±Ï");
		pstm.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void DataClose(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}





