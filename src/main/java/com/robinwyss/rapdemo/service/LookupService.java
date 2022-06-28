package com.robinwyss.rapdemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@Service
public class LookupService {

    private static final Logger logger = LoggerFactory.getLogger(LookupService.class);

    public String lookupDomainName(String name) {
        String result = null;
        try {
            logger.info("looking up {}", name);
            Process p = Runtime.getRuntime().exec(new String[] {
                    "/bin/sh",
                    "-c",
                    "nslookup " + name
            });
            try (InputStream inputStream = p.getInputStream()) {
                String response = new String(inputStream.readAllBytes());
                logger.debug("response {}", response);
                if (response.contains("** server can't find")) {
                    result = "Not found";
                } else {
                    // split by empty line
                    String[] splitResult = response.split(System.lineSeparator() + System.lineSeparator());
                    if (splitResult.length == 2) {
                        result = splitResult[1];
                    } else {
                        result = response;
                    }
                }
            }

            try (InputStream inputStream = p.getErrorStream()) {
                String error = new String(inputStream.readAllBytes());
                logger.warn("error {}", error);
            }

        } catch (IOException e) {
            e.printStackTrace();
            logger.warn("could not execute command", e);
            result = "Resolution failed";
        }
        return result;
    }
}
