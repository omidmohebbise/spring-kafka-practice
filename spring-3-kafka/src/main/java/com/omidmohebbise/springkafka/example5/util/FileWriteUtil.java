package com.omidmohebbise.springkafka.example5.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriteUtil {
    public static void writeString(String fileName, String content){
        File file = new File("./" + fileName);
        file.getParentFile().mkdirs();
        try (FileWriter writer = new FileWriter(file, true)) { // Open in append mode
            if (file.exists()) {
                writer.write(System.lineSeparator()); // Add an empty line
                writer.write(System.lineSeparator()); // Add an empty line
            }
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
