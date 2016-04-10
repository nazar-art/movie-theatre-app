package net.lelyak.edu.utils;

public final class SQLStatements {

    public static final String SELECT_FROM_TABLE = "SELECT * FROM %s";
    public static final String SELECT_FROM_TABLE_BY_ID = "SELECT * FROM %s WHERE id=:id";
    public static final String SELECT_FROM_TABLE_BY_NAME = "SELECT * FROM %s WHERE name=:name";
    public static final String SELECT_FROM_TABLE_BY_EMAIL = "SELECT * FROM %s WHERE email=:email";
    public static final String SELECT_FROM_TABLE_BY_USER_ID = "SELECT * FROM %s WHERE user_id=:user_id";

    public static final String DELETE_FROM_TABLE = "DELETE FROM %s WHERE id=:id";

    public static final String TOTAL_COUNT_FROM_TABLE = "SELECT COUNT(*) FROM %s";
    public static final String TOTAL_COUNT_BY_ID = "SELECT COUNT(*) FROM %s WHERE id=:id";
    public static final String TOTAL_COUNT_BY_NAME = "SELECT COUNT(*) FROM %s WHERE name=:name";
    public static final String TOTAL_COUNT_BY_MAIL = "SELECT COUNT(*) FROM %s WHERE email=:email";
}
