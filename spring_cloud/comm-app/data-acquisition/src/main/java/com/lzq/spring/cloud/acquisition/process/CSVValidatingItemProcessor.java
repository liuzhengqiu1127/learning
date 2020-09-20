package com.lzq.spring.cloud.acquisition.process;

import com.lzq.spring.cloud.acquisition.entity.Person;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

public class CSVValidatingItemProcessor extends ValidatingItemProcessor<Person> {

    @Override
    public Person process(Person item) throws ValidationException {
        super.process(item);
        if (item.getSex().equals("1")) item.setSex("男");
        else item.setSex("女");
        return item;
    }
}
