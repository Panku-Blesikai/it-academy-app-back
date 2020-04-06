package it.academy.app.shared;

public final class Constants {

    private Constants() {

    }

    public static final String DB_URI = System.getenv("MONGODB_URI");
    public static final String DB_NAME =  System.getenv("MONGODB_NAME");
    public static final String COLLECTION_ADMIN = System.getenv("COLLECTION_ADMIN");
    public static final String COLLECTION_APPLICATIONFORM = System.getenv("COLLECTION_APPLICATIONFORM");
    public static final String EMAIL = "pankublesikai@gmail.com";
    public static final String LT_ZONE = "Europe/Vilnius";
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

}
