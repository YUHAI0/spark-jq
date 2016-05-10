SARK-JQ
========
A jq-like lib in spark


Supported RDD Format
====================

1. RDD[String] within json data type

Usage
=====

1. rdd.parseJson

    - parse json RDD into JSONObject RDD

2. rdd.fileds("field1", "filed2")

    - return an List RDD\[List(*field1Type*, *field2Type*)\]
