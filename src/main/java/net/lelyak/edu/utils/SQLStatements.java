package net.lelyak.edu.utils;

public final class SQLStatements {
    /**
     * Users:
     */
    public static String SELECT_FROM_USERS = "SELECT * FROM Users";
    public static String INSERT_INTO_USERS = "INSERT INTO Users (user_id, user_name, user_birthday, user_email, user_role ) " + "VALUES (:id,:name,:birthday,:email,:role )";
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

    /**
     * Auditoriums:
     */
    public static String SELECT_FROM_AUDITORIUMS = "SELECT * FROM Auditoriums ";
    public static String INSERT_INTO_AUDITORIUMS = "INSERT INTO Auditoriums " +
            "(aud_id, aud_name, aud_seats, aud_vip) VALUES (:id, :name, :seats, :vip)";
    public static String UPDATE_AUDITORIUMS = "UPDATE Auditoriums SET aud_id=:id, aud_name=:name, aud_seats=:seats, aud_vip=:vip";
    public static String DELETE_FROM_AUDITORIUMS = "DELETE FROM Auditoriums ";
    public static String TOTAL_COUNT_FROM_AUDITORIUMS = "SELECT COUNT(*) FROM Auditoriums";

    /**
     * AspectCounter:
     */
    public static String SELECT_FROM_ASPECTCOUNTER = "SELECT * FROM AspectCounter ";
    public static String INSERT_INTO_ASPECTCOUNTER = "INSERT INTO AspectCounter (aspect_id=:id, aspect_name=:name, aspect_source=:source, aspect_counter=:counter) " +
            "VALUES (:id, :name, :source, :counter)";
    public static String UPDATE_ASPECTCOUNTER = "UPDATE AspectCounter SET aspect_id=:id, aspect_name=:name, aspect_source=:source, aspect_counter=:counter";
    public static String DELETE_FROM_ASPECTCOUNTER = "DELETE FROM AspectCounter ";
    public static String TOTAL_COUNT_FROM_ASPECTCOUNTER = "SELECT COUNT(*) FROM AspectCounter";



}
