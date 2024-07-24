package com.yob.bot.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileReader {
    public static List<String> readLines(String path)  {
        File file = new File(path);

        try {
            Scanner scanner = new Scanner(file);
            List<String> list = new ArrayList<>();

            while (scanner.hasNext()) {
                list.add(scanner.nextLine());
            }
            return list;
        } catch (FileNotFoundException e) {
            return Collections.emptyList();
        }
    }
}
