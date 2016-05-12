SARK-JQ
========
A jq like tools in spark


Supported RDD Format
====================

1. RDD[String] within json data type


Supported JSON field
====================

1. Number -> Scala Int, Long, Double
2. String -> Scala String
3. Object -> Scala Map
4. Array -> Scala List
5. Boolean -> Scala Boolean

Usage
=====

1. rdd.parseJson

    - parse json RDD into JSONObject RDD

2. rdd.fileds("field1", "filed2")

    - return an RDD\[List(*field1Type*, *field2Type*)\]
    
    
Features in future
==================

1. support regex field
2. support more format: csv, xml, ...
3. support other input data, sql, kafka, flume, ...
