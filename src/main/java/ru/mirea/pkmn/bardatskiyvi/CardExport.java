package ru.mirea.pkmn.bardatskiyvi;

import ru.mirea.pkmn.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * The CardExport class provides functionalities for serializing Card objects to byte streams.
 * It extends the AbstractFileAction to make use of the standardized file path for resources.
 */
public class CardExport extends AbstractFileAction {

    /**
     * Serializes a Card object and writes it to a specified file.
     *
     * @param file the file to which the serialized Card object will be written
     * @param card the Card object to be serialized and written to the file
     */
    private static void serializeAndWrite(File file, Card card) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);

            objectOutputStream.writeObject(card);
            objectOutputStream.close();
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Serializes a Card object to a byte stream and writes it to a file.
     * The file is named based on the Card's name and saved in the resources directory.
     *
     * @param card the Card object to be serialized and written to a file
     */
    public static void serializeToBytes(Card card) {
        File output = new File(PATH_TO_RESOURCES + card.getName() + ".crd");

        serializeAndWrite(output, card);
    }

    /**
     * Serializes a Card object to a byte stream and writes it to a specified file.
     *
     * @param card the Card object to be serialized and written to a file
     * @param outputFilename the name of the file (without extension) to which the serialized Card object will be written
     */
    public static void serializeToBytes(Card card, String outputFilename) {
        File output = new File(PATH_TO_RESOURCES + outputFilename + ".crd");

        serializeAndWrite(output, card);
    }
}
