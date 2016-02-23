
# slformatter

The slformatter is Simple Line Formatter for Java logging API. It is 
java library with class extending **java.util.logging.Formatter**.
The formatter outputs log records in one simple line and allows you to 
format the resulting line.

## Motivation

I don't like that two line output of standard java formatter. I like to 
have some time and logger name information together with message output. 
But I want to have it in one line with fixed length meta info and then 
mesage.

It is also useful to configure logging easily in code directly so you 
can use logging for small standalone applications or just ad hoc 'testing'
main methods. 

## Configuration

All details of configuration is described [here](./config.md). 
The formatter is configured by pattern string where you can specify how 
output looks like.

Following pattern
```
${date} ${time} ${level}: ${message}
```
produces output like
```
2015.07.31 22:53:56.023 FINER: file size 12342
2015.07.31 22:53:56.023 INFO: start copy of war file
2015.07.31 22:53:56.023 INFO: copy MzvZuZobrPortlety.war 540.0 kB/s 75%  
2015.07.31 22:53:57.078 INFO: copy MzvZuZobrPortlety.war 377.3 kB/s 79% 
2015.07.31 22:53:58.026 INFO: copy MzvZuZobrPortlety.war 297.0 kB/s 83% 
```

It is also possible to format length of individual parts of the pattern. 
Maximum and minimum length can be specified. 

Following pattern
```
${date} ${time} ${level:3:-3}: ${message}
```
produces output like 
```
2015.07.31 22:53:56.023 FIN: file size 12342
2015.07.31 22:53:56.023 INF: start copy of war file
2015.07.31 22:53:56.023 INF: copy MzvZuZobrPortlety.war 540.0 kB/s 75%  
2015.07.31 22:53:57.078 INF: copy MzvZuZobrPortlety.war 377.3 kB/s 79% 
2015.07.31 22:53:58.026 INF: copy MzvZuZobrPortlety.war 297.0 kB/s 83% 
```
Level is cut to maximum and minimum 3 chars so all messages starts at 
same position.

## Usage

Some times it is useful to configure the logging output from code itself. 
I personally like to start small peace of code and setup all log levels 
for my loggers (which are named using class names) and info level for 
all other loggers.

Following code define such configuration
```
 SLConf.reset(); // reset previous conf
        
 SLConf.rootLogger() // for root logger
       .console() // create console handler for System.out
       .filterWarn() // with filter for warn level
       .pattern() // start pattern
       .time()
       .text(" ").level(3, -3)
       .text(" ").simpleName(-20, -20)
       .text(" ").message()
       .patternEnd() // end pattern
       .handler(); // apply handler to logger
        
 SLConf.rootLogger() // for root logger
       .file("target/test.log") // create file handler
       .filterAll() // with all level filter
       .pattern() // start pattern
       .date()
       .text(" ").time()
       .text(" ").level(3, -3)
       .text(" ").simpleName(-20, -20)
       .text(" ").message()
       .patternEnd() // end pattern
       .handler(); // apply handler to logger
        
 SLConf.rootLogger().info(); // set all loggers to info level
 SLConf.logger("sk.antons").all(); // set my logers to all levels
```

## Usage shortcuts

As I usually needs such logging configuration I created two shortcuts 
```
 SLConf.simpleConsole("sk.antons"); // set all loggers to info level 
                                    // and sk.antons loggers to all levels
                                    // only console output

 SLConf.simpleFile("sk.antons", "target/test.log"); 
                                    // set all loggers to info level 
                                    // and sk.antons loggers to all levels
                                    // info to console and all other to file.

```

## Maven usage

```
   <dependency>
      <groupId>com.github.antonsjava</groupId>
      <artifactId>slformatter</artifactId>
      <version>1.0</version>
   </dependency>
```

