package com.ivan.springhotelsapp.service;

import com.ivan.springhotelsapp.entity.Visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

public abstract class ServiceHelper {
     protected BinaryOperator<List<Visitor>> listVisitorBiOperator = (x, y) ->{
            List<Visitor>  list = new ArrayList();
            list.addAll(x);
            list.addAll(y);
            return list;
        };
}
