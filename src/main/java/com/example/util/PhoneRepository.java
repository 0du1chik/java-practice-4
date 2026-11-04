package com.example.util;

import com.example.model.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Клас для збереження об'єктів ієрархії Phone у базі даних через JDBC.
 */
public class PhoneRepository {
    private String url;
    private String user;
    private String password;

    public PhoneRepository(String configFilePath) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(configFilePath)) {
            props.load(fis);
            this.url = props.getProperty("db.url");
            this.user = props.getProperty("db.user");
            this.password = props.getProperty("db.password");
            System.out.println("Конфігурацію БД успішно завантажено з: " + configFilePath);
        } catch (IOException e) {
            System.out.println("Помилка читання конфігураційного файлу БД: " + e.getMessage());
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void save(Phone phone) {
        if (url == null) {
            System.out.println("Збереження в БД скасовано: неналаштовано підключення.");
            return;
        }

        String sql = "INSERT INTO phones (type, brand, model, price, ram_gb, operating_system, has_torch, is_dual_screen, protection_standard) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String type = phone.getClass().getSimpleName();
            pstmt.setString(1, type);
            pstmt.setString(2, phone.getBrand());
            pstmt.setString(3, phone.getModel());
            pstmt.setDouble(4, phone.getPrice());
            pstmt.setInt(5, phone.getRamGb());

            // Заповнення полів залежно від типу об'єкта
            if (phone instanceof SmartPhone) {
                SmartPhone sp = (SmartPhone) phone;
                pstmt.setString(6, sp.getOperatingSystem());
            } else {
                pstmt.setNull(6, java.sql.Types.VARCHAR);
            }

            if (phone instanceof KeypadPhone) {
                KeypadPhone kp = (KeypadPhone) phone;
                pstmt.setBoolean(7, kp.isHasTorch());
            } else {
                pstmt.setNull(7, java.sql.Types.BOOLEAN);
            }

            if (phone instanceof FoldableSmartPhone) {
                FoldableSmartPhone fsp = (FoldableSmartPhone) phone;
                pstmt.setBoolean(8, fsp.isDualScreen());
            } else {
                pstmt.setNull(8, java.sql.Types.BOOLEAN);
            }

            if (phone instanceof SecureKeypadPhone) {
                SecureKeypadPhone skp = (SecureKeypadPhone) phone;
                pstmt.setString(9, skp.getProtectionStandard());
            } else {
                pstmt.setNull(9, java.sql.Types.VARCHAR);
            }

            pstmt.executeUpdate();
            System.out.println("Об'єкт успішно збережено в БД (Таблиця phones, type=" + type + ")!");

        } catch (SQLException e) {
            System.out.println("Помилка виконання SQL-запиту: " + e.getMessage());
        }
    }
}