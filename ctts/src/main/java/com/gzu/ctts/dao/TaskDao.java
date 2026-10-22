package com.gzu.ctts.dao;

import com.gzu.ctts.entity.Task;
import com.gzu.ctts.exception.BusinessException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskDao {

    private static final String URL = "jdbc:mysql://localhost:3306/ctts?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC-8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    private static final String SQL = "SELECT id, project_id, title, description, deadline, total_score, status, create_time, update_time FROM task";

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static List<Task> list() {
        List<Task> tasks = new ArrayList<>();
        // 1. 加载驱动
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new BusinessException(500, "加载数据库驱动失败");
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        // 2. 获取数据库连接
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            // 3.创建 PreparedStatement
            preparedStatement = connection.prepareStatement(SQL);
            // 4.执行SQL查询
            resultSet = preparedStatement.executeQuery();
            // 5. 处理结果集
            while (resultSet.next()) {

                Task task = new Task();
                task.setId(resultSet.getLong("id"));
                task.setProjectId(resultSet.getLong("project_id"));
                task.setTitle(resultSet.getString("title"));
                task.setDescription(resultSet.getString("description"));
                task.setDeadline(resultSet.getObject("deadline", LocalDateTime.class));
                task.setTotalScore(resultSet.getInt("total_score"));
                task.setStatus(resultSet.getInt("status"));
                task.setCreateTime(resultSet.getObject("create_time", LocalDateTime.class));
                task.setUpdateTime(resultSet.getObject("update_time", LocalDateTime.class));
                tasks.add(task);
            }
            return tasks;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new BusinessException(500, "数据库操作失败");
            }
        }

        return tasks;
    }


    public static void main(String[] args) {
        Task task = new Task();
        task.setProjectId(1L);
        task.setTitle("任务1");
        task.setDescription("这是任务1");
        task.setDeadline(LocalDateTime.now().plusDays(7));
        task.setTotalScore(100);
        task.setStatus(3);
        task.setCreateTime(LocalDateTime.now());
        task.setUpdateTime(LocalDateTime.now());
        add(task);

    }

    public static void add(Task task) {

        String SQL = "INSERT INTO task(project_id, title, description, deadline, total_score, status, create_time, update_time) VALUES(?, ?, ?, ?, ?, ?, ? , ?)";

        // 1. 加载驱动
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new BusinessException(500, "加载数据库驱动失败");
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setLong(1, task.getProjectId());
            preparedStatement.setString(2, task.getTitle());
            preparedStatement.setString(3, task.getDescription());
            preparedStatement.setObject(4, task.getDeadline());
            preparedStatement.setInt(5, task.getTotalScore());
            preparedStatement.setInt(6, task.getStatus());
            preparedStatement.setObject(7, task.getCreateTime());
            preparedStatement.setObject(8, task.getUpdateTime());

            int i = preparedStatement.executeUpdate();

            if (i > 0) {
                connection.commit();

                System.out.println("添加成功");
            }


        } catch (SQLException e) {

            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                throw new BusinessException(500, "数据库操作失败");
            }
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new BusinessException(500, "数据库操作失败");
                }
            }

            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    throw new BusinessException(500, "数据库操作失败");
                }
            }
        }

    }

}

