package com.lzq.spring.cloud.acquisition.process;

import com.lzq.spring.cloud.acquisition.entity.Person;
import org.springframework.batch.item.file.LineMapper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CSVLineMapper implements LineMapper<Person> {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    public Person mapLine(String s, int i) throws Exception {
        String[] lines = s.split(",");
        Date date = simpleDateFormat.parse(lines[4]);
        return new Person(lines[0],Integer.valueOf(lines[1]),lines[2],lines[3],date);
    }
}
