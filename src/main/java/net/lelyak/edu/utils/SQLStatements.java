package net.lelyak.edu.utils;

public final class SQLStatements {
    /**
     * Users:
     */
    public static String SELECT_FROM_USERS = "SELECT * FROM Users";
    public static String INSERT_INTO_USERS = "INSERT INTO Users (user_id, user_name, user_birthday, user_email, user_role, user_tickets) " +
            "VALUES (:id,:name,:birthday,:email,:role,:tickets )";
    public static String UPDATE_USERS = "UPDATE Users SET user_id=:id, user_name=:name, user_birthday=:birthday, user_email=:email, user_role=:role, user_tickets=:tickets";
    public static String DELETE_FROM_USERS = "DELETE FROM Users";
    public static String TOTAL_COUNT_FROM_USERS = "SELECT COUNT(*) FROM Users";

    /**
     * Events
     */
}
