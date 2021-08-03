package net.data.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataSource {

  public static HikariConfig config = new HikariConfig();
  public static HikariDataSource ds;

  static {
    config.setJdbcUrl("jdbc:mysql://virtual-eria.com:3306/eriacraft?characterEncoding=utf8");
    config.setUsername("eriacraft");
    config.setPassword("RCc<Vy1pc?M=&<&y^");
    config.addDataSourceProperty("cachePrepStmts", "true");
    config.addDataSourceProperty("prepStmtCacheSize", "250");
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
    ds = new HikariDataSource(config);
  }

  public static Connection getConnection() throws SQLException {
    return ds.getConnection();
  }
}
