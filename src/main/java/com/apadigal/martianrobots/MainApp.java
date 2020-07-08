/*
 * Swash Tech Ltd.
 *
 * MainApp.java
 *
 * © 2018 Swash Tech Ltd. All Rights Reserved
 */
// ---- Package ---------------------------------------------------------------
package com.apadigal.martianrobots;
// ---- Import Statements -----------------------------------------------------

import com.apadigal.martianrobots.exception.MartianRobotsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainApp {
    private static final Logger log = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) throws IOException {
        String startingCoordinates = null;
        List<List<String>> robotMoves;

        if(args.length>0){
            Path path = Paths.get(args[0]);
            try (BufferedReader in = Files.newBufferedReader(path)) {
                try {
                    List<String> list = in.lines()
                            .collect(Collectors.toList());
                    startingCoordinates = list.get(0);
                    list.remove(0);

                     robotMoves = IntStream.range(0, (list.size() + 1)/2)
                            .mapToObj(i -> list.subList(i * 2, Math.min(2 * (i + 1), list.size())))
                            .collect(Collectors.toList());
                    System.out.println(robotMoves);

                }catch(UncheckedIOException ex){
                    if(ex.getCause() instanceof MalformedInputException)
                        throw new MartianRobotsException("Invalid file format, only expected UTF-8 file");
                    else
                        throw new MartianRobotsException("Unexpected error :"+ ex.getMessage());
                }
            }catch (NoSuchFileException ex){
                throw new MartianRobotsException("File not found :"+ args[0]);
            }

        }else{
            log.error("Please supply file path as an argument");
        }
    }
}