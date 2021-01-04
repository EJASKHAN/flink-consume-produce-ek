package com.mbrdi.operator;

import org.apache.flink.api.common.functions.MapFunction;

public class StreamMapFunctionOne implements MapFunction<String,String> {
    @Override
    public String map(String str) throws Exception {
        return str+" Transformed " ;
    }
}
