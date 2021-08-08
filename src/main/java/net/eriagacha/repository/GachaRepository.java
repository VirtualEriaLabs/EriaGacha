package net.eriagacha.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import lombok.extern.log4j.Log4j2;
import net.data.datasource.DataSource;
import net.eriagacha.models.GachaTelemetryModel;

@Log4j2
public class GachaRepository {




  public void insertGachaTelemetry(GachaTelemetryModel gtm) throws SQLException {
    String insertString = """
        INSERT INTO GACHA_TELEMETRY(reward,user,date) VALUES (?,?,?)
        """;

    Connection con = DataSource.getConnection();

    try (PreparedStatement test = con.prepareStatement(insertString)) {
      con.setAutoCommit(false);
      test.setString(1, gtm.getRewardObtained());
      test.setString(2, gtm.getPlayerName());
      test.setString(3, gtm.getDate());
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

  public String selectGachaTelemetry(String username)
      throws SQLException, ExecutionException, InterruptedException {

    Connection dbConnection = DataSource.getConnection();
    String plainQuery = "select * from GACHA_TELEMETRY where user = ? ";
    PreparedStatement dbQuery = dbConnection.prepareStatement(plainQuery);
    dbQuery.setString(1, username);
    ResultSet dbResponse = dbQuery.executeQuery();
    String dbTuples = null;

    /*
    Table employees = dbConnection.get("employee");
    // execute the query asynchronously, obtain a future
    CompletableFuture<RowResult> rowsFuture = employees.select("name", "age")
        .where("name like :name")
        .orderBy("name")
        .bind("name", "m%").executeAsync();

    // wait until it's ready
        RowResult rows = rowsFuture.get();
     */

    while(dbResponse.next())
    {
      log.info(" INFO " + dbResponse.getString(1) + dbResponse.getString(2) + dbResponse.getString(3) + dbResponse.getString(4));
      dbTuples = dbTuples
          + "\n ********** "
          + "\n Recompensa : "  + dbResponse.getString(2)
          + "\n Usuario : " + dbResponse.getString(3)
          + "\n Fecha : " + dbResponse.getString(4);
    }
    return dbTuples;
  }
}
