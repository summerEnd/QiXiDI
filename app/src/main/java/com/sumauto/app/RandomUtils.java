package com.sumauto.app;

import java.util.Random;

/**
 * Created by Lincoln on 2016/3/14.
 */
public class RandomUtils {
    public static String randomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        char ch;
        for (int i = 0; i < length; i++) {
            boolean lowerCase = random.nextBoolean();
            if (lowerCase) {
                ch = (char) ('a' + random.nextInt(26));
            }
            else {
                ch = (char) ('A' + random.nextInt(26));
            }
            sb.append(ch);
        }
        return sb.toString();
    }

    public static int randomInt(int start,int end){
        if (start>end){
            end=start;
        }

        Random random = new Random();
        return random.nextInt(end-start)+start;
    }

    public static String randomArticle(int maxSectionNum,int sectionMaxLength){
        int section=randomInt(1,maxSectionNum);
        StringBuilder article=new StringBuilder();
        for (int i = 0; i < section; i++) {
            int length=randomInt(10,sectionMaxLength);
            String sectionContent=randomString(length);
            article.append(sectionContent);
            article.append("\n");
        }
        return article.toString();
    }
}
