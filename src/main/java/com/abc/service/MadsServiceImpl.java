package com.abc.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;

@Service
public class MadsServiceImpl implements MadsService {
    @Override
    public void hello() {
        System.out.println("ahahahahahah");
    }

    @Override
    public void byby() {
        System.out.println("mamammaamamamamm");

//        String num = "2323";
//        StringUtils.isNumeric(num);
//
//        String[] array_string = new String[] { "a", "b", "c" };
//        List list_string = Arrays.asList(array_string);
//
////        list_string.toArray(new String[list_string.size()]);
//
//        Iterator iterator = list_string.iterator();
//        if(iterator.hasNext()) {
//            String s = (String)iterator.next();
//        }
    }

}
