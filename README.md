SARK-JQ
========
A Spark toolkit, not exactly like jq but inspired by it 

[![Build Status](https://travis-ci.org/yuhai1023/spark-jq.svg?branch=master)](https://travis-ci.org/yuhai1023/spark-jq)


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
6. Compose Field, such as "map1.map2.intField" -> Type above

Usage
=====

RDDLike
-------

0. first of all

    import org.sjq.rdd.RDDLike._

1. rdd.parseJson

    - parse json RDD into JSONObject RDD

2. rddJson.fields("field1", "filed2")

    - return an RDD\[List(*field1Type*, *field2Type*)\]
    
3. rddFields(n)

    - return RDD\[element n in list\]

4. rddJson.key\[T\]("field1") or rdd.field(*fieldFoo*)

    - return RDD\[T\]

5. rddJson.jsonObject("objKey")

    - return an JSONObject RDD
    
6. rddField.\[Int|Long|Double|Boolean|List\[T\]|Map\[T1,T2\]|JSONObject\]
    
    - map RDD\[Any\] into RDD\[T with specified type\]
    
Lambda
------

0. first of all
    
    import org.sjq.lambda.Lambda._
    
1. addInt | addDouble

    - an RDD\[(Key, (Int, Int))\] use with reduceByKey(addInt)

2. addTuple2

    - an RDD\[(Key, ((AnyNumber, AnyNumber), (AnyNumber, AnyNumber))\] use with reduceByKey(addTuple2)

    
Features in future
==================

1. support regex field
2. support more format: csv, xml, ...
3. support other input data, sql, kafka, flume, ...
4. support other RDD reduce function utils

LICENSE
=======

MIT
