package com.assignment.friendmanagement.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class EmailUtils {

    private Logger logger = LogManager.getLogger();

    public List<String> retrieveEmailIdsFromText(String text) {
        String regEx = "[a-zA-Z0-9-_.]+@[a-zA-Z0-9-_.]+[a-zA-Z]";
        Pattern pattern = Pattern.compile(regEx);
        List<String> emails = new ArrayList();
        Matcher m = pattern.matcher(text);
        while (m.find()) {
            emails.add(m.group());
        }
        logger.info("{} has emails: {}",text,emails);
        return emails;

    }

}
