package net.lelyak.edu.utils;

public final class SQLStatements {
    /**
     * Users:
     */
    public static String SELECT_FROM_USERS = "SELECT * FROM Users ";
    public static String INSERT_INTO_USERS = "INSERT INTO Users (user_id, user_name, user_birthday, user_email, user_role, user_tickets) " + "VALUES (:id,:name,:birthday,:email,:role,:tickets )";
    public static String UPDATE_USERS = "UPDATE Users SET user_id=:id, user_name=:name, user_birthday=:birthday, user_email=:email, user_role=:role, user_tickets=:tickets";
    public static String DELETE_FROM_USERS = "DELETE FROM Users ";
    public static String TOTAL_COUNT_FROM_USERS = "SELECT COUNT(*) FROM Users";

    /**
     * Events:
     */
    public static String SELECT_FROM_EVENTS = "SELECT * FROM Events";
    public static String INSERT_INTO_EVENTS = "INSERT INTO Events (event_id, event_name, event_price, event_rating, event_date) " +
            "VALUES (:id, :name, :price, :rating, :date)";
    public static String UPDATE_EVENTS = "UPDATE Events SET event_id=:id, event_name=:name, event_price=:price, event_rating=:rating, event_date=:date";
    public static String DELETE_FROM_EVENTS = "DELETE FROM Events ";
    public static String TOTAL_COUNT_FROM_EVENTS = "SELECT COUNT(*) FROM Events";


    /**
     * Tickets:
     */
    public static String SELECT_FROM_TICKETS = "SELECT * FROM Tickets ";
    public static String INSERT_INTO_TICKETS = "INSERT INTO Tickets (tick_id, tick_price, event_id, user_id) " +
            "VALUES (:id, :price, :event, :user)";
    public static String UPDATE_TICKETS = "UPDATE Tickets SET tick_id=:id, tick_price=:price, event_id=:event, user_id=:user";
    public static String DELETE_FROM_TICKETS = "DELETE FROM Tickets ";
    public static String TOTAL_COUNT_FROM_TICKETS = "SELECT COUNT(*) FROM Tickets";

}
