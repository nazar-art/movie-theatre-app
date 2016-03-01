package net.lelyak.edu.utils;

public final class SQLStatements {

    public static final String SELECT_FROM_TABLE = "SELECT * FROM %s";
    public static final String SELECT_FROM_TABLE_BY_ID = "SELECT * FROM %s WHERE id=:id";
    public static final String SELECT_FROM_TABLE_BY_NAME = "SELECT * FROM %s WHERE name=:name";

    public static final String DELETE_FROM_TABLE = "DELETE FROM %s WHERE id=:id";

    public static final String TOTAL_COUNT_FROM_TABLE = "SELECT COUNT(*) FROM %s";
    public static final String TOTAL_COUNT_BY_ID = "SELECT COUNT(*) FROM %s WHERE id=:id";
    public static final String TOTAL_COUNT_BY_NAME = "SELECT COUNT(*) FROM %s WHERE name=:name";
}
