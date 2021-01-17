package com.lzq.study.geektime;

import com.lzq.study.geektime.algorithms.famous.Pattern;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PatternTest {

    @Test
    public void matchTest()
    {
        String expression = "ab*c?dff";
        Pattern pattern = new Pattern(expression.toCharArray(),expression.length());
        String compareStr = "abiiddffddc9dff";
        Assert.assertTrue(pattern.match(compareStr.toCharArray(),compareStr.length()));
    }
}
