package com.assignment.friendmanagement.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Kiran on 26/6/18.
 */
public class EmailUtilsTest {

    private EmailUtils emailUtils = new EmailUtils();

    @Test
    public void texWithMultipleEmails() {
        String text = "boleh di kirim ke email saya ekoprasetyo.crb@outlook.com tks...\n" +
                "boleh minta kirim ke db.maulana@gmail.com. \n" +
                "dee.wien@yahoo.com. b@b b@b.c b.@b a..@a .\n" +
                "deninainggolan@yahoo.co.id Senior Quantity Surveyor\n" +
                "Fajar.rohita@hotmail.com, terimakasih bu Cindy Hartanto\n" +
                "firmansyah1404@gmail.com saya mau dong bu cindy\n" +
                "fransiscajw@gmail.com \n" +
                "Hi Cindy ...pls share the Salary guide to donny_tri_wardono@yahoo.co.id thank a fa@ac.co ";
        List<String> strings = emailUtils.retrieveEmailIdsFromText(text);
        Assert.assertEquals(strings.size(), 10);
    }

    @Test
    public void textWithNoEmails() {
        String text = "random test @ I don't have email";
        List<String> strings = emailUtils.retrieveEmailIdsFromText(text);
        Assert.assertEquals(strings.size(), 0);

    }

    @Test
    public void textInvalidEmails() {
        String text = "random test x...@ I am invalid email";
        List<String> strings = emailUtils.retrieveEmailIdsFromText(text);
        Assert.assertEquals(strings.size(), 0);

    }

}