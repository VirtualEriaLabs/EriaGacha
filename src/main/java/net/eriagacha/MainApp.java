package net.eriagacha;

import java.sql.Connection;
import java.sql.SQLException;
import net.data.datasource.DataSource;

public class MainApp {
  public static void main(String[] args) {

    try {
      Connection conn = DataSource.getConnection();
      System.out.println(conn.getCatalog());
    }
    catch (SQLException e){
      e.printStackTrace();
    }
  }
}
