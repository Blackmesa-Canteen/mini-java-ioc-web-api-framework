package io.swen90007sm2.framework.db;

import io.swen90007sm2.framework.core.config.ConfigFileManager;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A helper for database connection with JDBC
 *
 * @author xiaotian
 */
public class DbConnectionHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbConnectionHelper.class);

    private static final ThreadLocal<Connection> DB_CONNECTION_HOLDER;


    private static final BasicDataSource DATA_SOURCE;

    static {
        DB_CONNECTION_HOLDER = new ThreadLocal<>();

        DATA_SOURCE = new BasicDataSource();
        DATA_SOURCE.setDriverClassName(ConfigFileManager.getJdbcDriver());
        DATA_SOURCE.setUrl(ConfigFileManager.getJdbcUrl());
        DATA_SOURCE.setUsername(ConfigFileManager.getJdbcUsername());
        DATA_SOURCE.setPassword(ConfigFileManager.getJdbcPassword());
    }

    public static Connection getDbConnection() {
        Connection res = DB_CONNECTION_HOLDER.get();
        if (res == null) {
            try {
                res = DATA_SOURCE.getConnection();
            } catch (SQLException e) {
                LOGGER.error("db connection exception: ", e);
                throw new RuntimeException(e);
            } finally {
                DB_CONNECTION_HOLDER.set(res);
            }
        }
        return res;
    }

    public static void closeDbConnection(Connection conn, Statement st, ResultSet rs) {
        try {
            if(rs != null){
                rs.close();
            }
            if(st != null){
                st.close();
            }
            if(conn != null){
                conn.close();
            }
            DB_CONNECTION_HOLDER.set(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
