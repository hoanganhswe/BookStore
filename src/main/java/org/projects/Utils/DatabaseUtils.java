package org.projects.Utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseUtils {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try (InputStream input = DatabaseUtils.class.getClassLoader().getResourceAsStream("config.properties")) {
                Properties prop = new Properties();
                prop.load(input);

                String url = prop.getProperty("db.url");
                String user = prop.getProperty("db.user");
                String password = prop.getProperty("db.password");

                connection = DriverManager.getConnection(url, user, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void testConnection() {
        try (Connection conn = getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("✅ Kết nối database thành công!");
            } else {
                System.out.println("❌ Kết nối database thất bại!");
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi kết nối: " + e.getMessage());
        }
    }

}
