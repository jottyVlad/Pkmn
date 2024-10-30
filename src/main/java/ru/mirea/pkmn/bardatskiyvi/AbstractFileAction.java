package ru.mirea.pkmn.bardatskiyvi;

/**
 * Abstract base class for file-related actions.
 * This class provides a common constant for the file path to resources that can be used
 * by subclasses for reading from or writing to files.
 * Subclasses should implement specific file handling logic relevant to their use cases.
 */
abstract public class AbstractFileAction {
    /**
     * The path to the resources' directory.
     * This constant is used to define the standard location for resource files
     * within the project structure.
     */
    protected static final String PATH_TO_RESOURCES = "src/main/resources/";
}
