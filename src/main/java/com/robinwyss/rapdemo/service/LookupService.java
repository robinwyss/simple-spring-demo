package com.robinwyss.rapdemo.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@Service
public class LookupService {

    public String lookupDomainName(String name){
        String result = null;
        try (InputStream inputStream = Runtime.getRuntime().exec("nslookup "+ name).getInputStream()) {

            String response = new String(inputStream.readAllBytes());
            if(response.contains("** server can't find")){
                result = "Not found";
            } else {
                // split by empty line
                String[] splitResult = response.split(System.lineSeparator()+System.lineSeparator());
                if(splitResult.length == 2){
                    result = splitResult[1];
                } else {
                    result = response;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = "Resolution failed";
        }
        return result;
    }
}
