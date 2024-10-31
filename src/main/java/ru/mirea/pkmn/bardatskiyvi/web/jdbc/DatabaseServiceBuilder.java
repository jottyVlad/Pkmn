package ru.mirea.pkmn.bardatskiyvi.web.jdbc;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Builder class for creating DatabaseServiceImpl instances.
 * Allows for configuring the properties file used for database connection.
 */
public class DatabaseServiceBuilder {
    private String _propertiesFile = "database.properties";

    /**
     * Constructs a DatabaseServiceBuilder with the default properties file ("database.properties").
     */
    public DatabaseServiceBuilder() {}


    /**
     * Builds a DatabaseServiceImpl instance using the configured properties file.
     *
     * @return A new DatabaseServiceImpl instance.
     * @throws SQLException If a database access error occurs.
     * @throws IOException  If an I/O error occurs while loading the properties file.
     */
    public DatabaseServiceImpl buildDatabaseService() throws SQLException, IOException {
        return new DatabaseServiceImpl(_propertiesFile);
    }


    /**
     * Sets the properties file to be used for database connection.
     *
     * @param propertiesFile The path to the properties file.
     * @return The DatabaseServiceBuilder instance for method chaining.
     */
    public DatabaseServiceBuilder setPropertiesFile(String propertiesFile) {
        this._propertiesFile = propertiesFile;
        return this;
    }
}
