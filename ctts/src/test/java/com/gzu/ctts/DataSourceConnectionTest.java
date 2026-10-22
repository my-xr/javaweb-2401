package com.gzu.ctts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

@SpringBootTest
public class DataSourceConnectionTest {

    @Autowired
    private DataSource dataSource;  // Spring Boot 自动注入 HikariDataSource

    @Test
    public void testConnection() throws SQLException {
        System.out.println("========== 数据源类型 ==========");
        System.out.println("DataSource = " + dataSource.getClass().getName());

        try (Connection conn = dataSource.getConnection()) {
            System.out.println("========== 连接成功 ==========");
            System.out.println("Connection = " + conn);
            System.out.println("是否已关闭 = " + conn.isClosed());
            System.out.println("是否有效 = " + conn.isValid(3));

            DatabaseMetaData metaData = conn.getMetaData();
            System.out.println("========== 数据库元数据 ==========");
            System.out.println("数据库产品名 = " + metaData.getDatabaseProductName());
            System.out.println("数据库版本 = " + metaData.getDatabaseProductVersion());
            System.out.println("驱动名 = " + metaData.getDriverName());
            System.out.println("驱动版本 = " + metaData.getDriverVersion());
            System.out.println("URL = " + metaData.getURL());
            System.out.println("用户名 = " + metaData.getUserName());

        }

        System.out.println("========== 测试通过：数据库连接正常 ==========");
    }
}
