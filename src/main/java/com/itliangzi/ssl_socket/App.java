package com.itliangzi.ssl_socket;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Stream.of(1,2,3,4,5).collect(Collectors.toList()).forEach(a->System.out.println(a));
    }
}
