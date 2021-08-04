package net.eriagacha.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.extern.log4j.Log4j2;
import net.data.datasource.DataSource;
import net.eriagacha.models.GachaTelemetryModel;

@Log4j2
public class GachaRepository {


  public void insertGachaTelemetry(GachaTelemetryModel gtm) throws SQLException {
    String insertString = """
        INSERT INTO GACHA_TELEMETRY(Reward,User,Date) VALUES (?,?,?)
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
}
