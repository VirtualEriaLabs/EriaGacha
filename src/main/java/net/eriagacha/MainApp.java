package net.eriagacha;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import lombok.extern.log4j.Log4j2;
import net.data.datasource.DataSource;

@Log4j2
public class MainApp {

  public static void main(String[] args) throws SQLException {

    insertSuccessfullTest();
    insertFailedTest();
    insertSuccessfullAndFailedTest();
    /*
    try {
      Connection conn = DataSource.getConnection();
      System.out.println(conn.getCatalog());
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    */
  }

  /**
   * A successfull insert in the database
   * @throws SQLException
   */
  public static void insertSuccessfullTest() throws SQLException {
    String insertString = """
        INSERT INTO Prueba
          VALUES (1, 'Prueba 1');
        """;
    Connection con = DataSource.getConnection();
    try (PreparedStatement test = con.prepareStatement(insertString)) {
      con.setAutoCommit(false);
      test.execute();
      con.commit();
    } catch (SQLException e) {
      log.error(e.getMessage());
      if (con != null) {
        try {
          log.error("Transaction is being rolled back");
          con.rollback();
        } catch (SQLException excep) {
          log.error(excep.getMessage());
        }
      }
    }
    finally {
      con.close();
    }
  }

  /**
   * A failed insert with rollback to the database
   * @throws SQLException
   */
  public static void insertFailedTest() throws SQLException {
    String insertString = """
        INSERT INTO Pruebaa
          VALUES (2, 'Prueba2');
        """;
    String insertString2 = """
        INSERT INTO Pruebaa
          VALUES (4, 'Hola');
        """;
    Connection con = DataSource.getConnection();
    try (PreparedStatement test = con.prepareStatement(insertString);
         PreparedStatement test2 = con.prepareStatement(insertString2)) {
      con.setAutoCommit(false);
      test.execute();
      con.commit();
      test2.execute();
      con.commit();
    } catch (SQLException e) {
      log.error(e.getMessage());
      if (con != null) {
        try {
          log.error("Transaction is being rolled back");
          con.rollback();
        } catch (SQLException excep) {
          log.error(excep.getMessage());
        }
      }
    }
    finally {
      con.close();
    }
  }

  /**
   * Try to insert various values in a wrong table causing a rollback, but inserting the first correct insert
   * @throws SQLException
   */
  public static void insertSuccessfullAndFailedTest() throws SQLException {
    String insertString = """
        INSERT INTO Prueba
          VALUES (3, 'Hola');
        """;
    String insertString2 = """
        INSERT INTO Pruebaa
          VALUES (4, 'Hola');
        """;
    Connection con = DataSource.getConnection();
    try (PreparedStatement test = con.prepareStatement(insertString);
         PreparedStatement test2 = con.prepareStatement(insertString2)) {
      con.setAutoCommit(false);
      test.execute();
      con.commit();
      test2.execute();
      con.commit();
    } catch (SQLException e) {
      log.error(e.getMessage());
      if (con != null) {
        try {
          log.error("Transaction is being rolled back");
          con.rollback();
        } catch (SQLException excep) {
          log.error(excep.getMessage());
        }
      }
    }
    finally {
      con.close();
    }
  }



}
