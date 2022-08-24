package com.johnburitto.interurbantransit.model;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class FiltersManager
 * @version 1.0.0
 * @since 24.08.2022, 15:00
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FiltersManager {
    private static final String FILE_PATH = "src/main/resources/static/filters/";

    public static void parseAndSaveToFile(String json, String fileName) throws FileNotFoundException {
        PrintWriter myWriter = new PrintWriter(FILE_PATH + fileName);

        json = json.replace("\"", "");
        json = json.replace("{", "");
        json = json.replace("}", "");
        json = json.replace(":", " ");
        json = json.replace(",", " ");

        for (String element : json.split(" ")) {
            if (element.equals("true")) {
                myWriter.println("checked ");
            }
            if (element.equals("false")) {
                myWriter.println(" ");
            }
        }

        myWriter.close();
    }

    public static List<String> readFromFile(String fileName) throws IOException {
        return Arrays.asList(new String(Files.readAllBytes(Paths.get(FILE_PATH + fileName))).split("\n"));
    }
}
