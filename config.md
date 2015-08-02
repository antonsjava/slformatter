
# Configuration

Basically the formatter is configured by single pattern string. It 
define whole formating for formatter. 

Pattern string can define some placeholder, which will be replaced by 
defined value. Text which is not recognized as placeholder is simple 
added to output as is. 

## Placeholders

A placeholder define some text, which should be added to message. (
like date, time, ...)

A placeholder xxx is in pattern string in following form
 - '${xxx}' - will be replaced as is
 - '${xxx:num1}' - in this form there is defined maximum length
 - '${xxx:num1:num2}' - in this form there is efined maximum length 
                        and minimum length
 - '${xxx:num1:num2:param}' - in this form there is max. min length 
                       defined and also an parameter for placeholder

 If maximum length is specified and placeholder value is longer than 
 defined maximum, the value is cut. If maximum is positive the value 
 is cut from right if maximum is negative the value is cut from left./

 If minimum length is specified and placeholder value is shorter than 
 defined minimum, smacess will be added to value. If minimum is positive 
 spaces are added to right if minimum is negative spaces are added to left.

 Parameter value is used only by some placeholders (see concrete 
 placeholders). Normally is ignored.

## Placeholder list

 - 'date' - current date in form 'yyyy.MM.dd'
 - 'time' - current time in form 'HH:mm:ss.SSS'
 - 'ftime' - current time in form specified in parameter
 - 'name' - name of the logger
 - 'sname' - simple name of the logger all words (separated by dot) except
             last one are shorten to first letter only.  
 - 'level' - name of the level, where log record is logged
 - 'thread' - name of the current thread
 - 'message' - logged message

following placeholder are usefull only for debug purposes

 - 'class' - name of the class where log record was created (do not use 
             them - slow)
 - 'sclass' - simple name of the class where log record was created (do 
              not use them - slow)
 - 'method' - method name where log record was created (do not use 
              them - slow)

## Standard configuration

How to configure Java logging API is well documented. There is only one 
specific propery 'sk.antons.util.logging.SimpleLineFormatter.pattern' where 
format pattern string is specified.

## API configuration

Pattern string can be specified directly in constructor or via property setter.
It is also possible to configure logging using helper class SLConf. 
```
 - SLConf.reset() resets whole configuration
 - SLConf.rootLogger() - provides root logger for configuration
 - SLConf.logger(loggername) - provides specified logger for configuration
 - loggerSetup.info() , ,warn() fine(), ... sets level for logger
 - loggerSetup.console() , define console handler for logger
 - loggerSetup.file() , define file handler for logger
 - HandlerSetup.handler() , close handler configuration - mandatory
 - HandlerSetup.filterXXX() , sets XXX level for handler filtering
 - HandlerSetup.filterXXX() , sets XXX level for handler filtering
 - HandlerSetup.pattern() , starts pattern definition for formatter
 - HandlerFormatSetup.patternEnd() , stops pattern definition - mandatory
 - HandlerFormatSetup.text(), date(), ... define patter parts

```


