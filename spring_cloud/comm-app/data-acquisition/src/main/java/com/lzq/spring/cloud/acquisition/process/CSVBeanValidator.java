package com.lzq.spring.cloud.acquisition.process;

import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.InitializingBean;

public class CSVBeanValidator<T> implements Validator<T>, InitializingBean {

    @Override
    public void validate(T t) throws ValidationException {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
