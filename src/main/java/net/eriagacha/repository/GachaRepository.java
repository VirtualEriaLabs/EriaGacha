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

  public String selectGachaTelemetry(String username) throws SQLException {

    Connection con = DataSource.getConnection();
    String consulta = "select * from GACHA_TELEMETRY where user = ? ";
    PreparedStatement ps = con.prepareStatement(consulta);
    ps.setString(1, username);
    ResultSet rs;
    rs = ps.executeQuery();
    String xD = null;
    while( rs.next())
    {
      log.info(" INFO " + rs.getString(1) + rs.getString(2) + rs.getString(3) + rs.getString(4));
      xD = xD
          + "\n ********** "
          + "\n Recompensa : "  + rs.getString(2)
          + "\n Usuario : " + rs.getString(3)
          + "\n Fecha :" + rs.getString(4);
    }
    return xD;
  }
}
