package org.sjq.rdd

import com.alibaba.fastjson.{JSON, JSONObject}
import org.apache.spark.rdd.RDD
import org.sjq.fields.{Types, BaseField}

/**
 * @author hai
 */
trait RddWrapper {

  implicit class JQImp(val rdd: RDD[String]) {

    def jq(regex: String): RDD[String] = {
      rdd
    }

  }

  implicit class JsonToObject(val rdd: RDD[String]) {

    def parseJson: RDD[JSONObject] = {
      rdd.flatMap(line => {
        try {
          Some(JSON.parseObject(line))
        } catch {
          case e: Throwable =>
            None
        }
      })
    }

  }

  implicit class JsonObjectToFields(val rdd: RDD[JSONObject]) {

    def fields(fs: BaseField*): RDD[List[Any]] = {
      rdd.flatMap(obj => {
        try {
          val value = fs.map({ f =>
            f.valueType match {
              case Types.IntType => obj.getIntValue(f.name)
              case Types.LongType => obj.getLongValue(f.name)
              case Types.DoubleType => obj.getDouble(f.name)
              case Types.StringType => obj.getString(f.name)
              case Types.BooleanType => obj.getBoolean(f.name)
              case Types.MapType => Map(
                obj.getJSONObject(f.name).entrySet().toArray[java.util.Map.Entry[String,Any]](Array())
                  .map(m=> m.getKey -> m.getValue):_*)
              case _ => obj.get(f.name)
            }
          }).toList
          Some(value)
        }
        catch {
          case e: Throwable =>
            e.printStackTrace()
            None
        }
      })
    }
  }
}
