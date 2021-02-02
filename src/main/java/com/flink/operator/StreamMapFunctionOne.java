/*
 @author Ejaskhan
*/
package com.flink.operator;
import org.apache.flink.api.common.functions.MapFunction;

public class StreamMapFunctionOne implements MapFunction<String,String> {
    @Override
    public String map(String str) {
        return str+" K8S Transformed " ;
    }
}
