package com.itpaths.rules.price.serice;

import com.itpaths.rules.price.dao.model.Calndr;
import com.itpaths.rules.price.dao.repository.InsertOp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

//@Service
public class InsertService {
    //@Autowired
    private InsertOp insertOp;
    public void insertCalndr(Calndr calndr) {
        insertOp.insertWithQuery(calndr);
        /*File file = new File("D:\\clients\\drools-belgium\\Tables_20\\calndr.txt");    //creates a new file instance
        FileReader fr = null;   //reads the file
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
        StringBuffer sb = new StringBuffer();    //constructs a string buffer with no characters
        String line = "";
        while (true) {
            try {
                System.out.println(line);
                Calndr calndr = new Calndr();
                String words[] = line.split(",");
                calndr.setCalndrDate((java.sql.Date) getDate(words[0]));
                calndr.setStsCd(words[1]);
                calndr.setCalndrDayInWk(Integer.parseInt(words[2]));
                calndr.setCalndrDayInYr(Integer.parseInt(words[3]));
                calndr.setCalndrHoliday(words[4]);
                calndr.setCalndrWkNo(Integer.parseInt(words[5]));
                calndr.setCalndrHighSeasn(words[6]);
                calndr.setOperBadgeId(words[7]);
                calndr.setOperId(words[8]);
                calndr.setTimeStamp((java.sql.Date) getDate(words[9]));
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            sb.append(line);      //appends line to string buffer
            sb.append("\n");     //line feed
        }
        try {
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public Date getDate(String date) throws Exception {
        Date date1 = null;
        if (!date.isEmpty())
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        return date1;
    }
}

