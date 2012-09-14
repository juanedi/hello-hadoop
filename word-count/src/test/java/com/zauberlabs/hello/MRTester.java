/*******************************************************************************
* File: MRTester.java
* Copyright (c) 2012 Snaptracs, Inc. All rights reserved.
* Snaptracs, Inc. Proprietary and Confidential.
*******************************************************************************/
package com.zauberlabs.hello;

import java.util.Arrays;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import ar.com.zaubersoftware.bigdata.hellohadoop.worcount.WordCountMapper;
import ar.com.zaubersoftware.bigdata.hellohadoop.worcount.WordCountReducer;

/**
 * TODO: Description of the class, Comments in english by default  
 * 
 * 
 * @author Gonto
 * @since Sep 14, 2012
 */
public class MRTester {
    
    MapDriver<Object, Text, Text, IntWritable> mapDriver;
    ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;
    MapReduceDriver<Object, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;
    
    @Before
    public void setUp() {
      WordCountMapper mapper = new WordCountMapper();
      WordCountReducer reduced = new WordCountReducer();
      mapDriver = new MapDriver(mapper);
      reduceDriver = new ReduceDriver(reduced);
      mapReduceDriver = new MapReduceDriver(mapper, reduced);
      
    }

    @Test
    public void testMapper() {
      mapDriver.withInput(new LongWritable(23), new Text("hola gonto gonto como"));
      mapDriver.withOutput(new Text("hola"), new IntWritable(1));
      mapDriver.withOutput(new Text("gonto"), new IntWritable(1));
      mapDriver.withOutput(new Text("gonto"), new IntWritable(1));
      mapDriver.withOutput(new Text("como"), new IntWritable(1));
      mapDriver.runTest();
    }
    
    @Test
    public void testReducer() {
      reduceDriver.withInput(new Text("gonto"), Arrays.asList(new IntWritable(1), new IntWritable(1)));
      reduceDriver.withOutput(new Text("gonto"), new IntWritable(2));
      reduceDriver.runTest();
    }
    
    @Test
    public void testAll() {
        mapReduceDriver.withInput(new LongWritable(23), new Text("hola gonto gonto como"));
        mapReduceDriver.withOutput(new Text("como"), new IntWritable(1));
        mapReduceDriver.withOutput(new Text("gonto"), new IntWritable(2));
        mapReduceDriver.withOutput(new Text("hola"), new IntWritable(1));
        mapReduceDriver.runTest();
    }

}
