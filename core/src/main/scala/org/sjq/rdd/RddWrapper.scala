package org.sjq.rdd

import com.alibaba.fastjson.{JSON, JSONObject}
import org.apache.spark.rdd.RDD
import org.sjq.fields.{BaseField, Types}

import scala.reflect.ClassTag

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

    def fields(fields: BaseField[Any]*): RDD[List[Any]] = {
      rdd.flatMap(obj => {
        try {
          val value = fields.map({ f =>
            f.valueType match {
              case Types.IntType => obj.getIntValue(f.name)
              case Types.LongType => obj.getLongValue(f.name)
              case Types.DoubleType => obj.getDouble(f.name)
              case Types.StringType => obj.getString(f.name)
              case Types.BooleanType => obj.getBoolean(f.name)
              case Types.MapType => Map(
                obj.getJSONObject(f.name).entrySet().toArray[java.util.Map.Entry[String,Any]](Array())
                  .map(m=> m.getKey -> m.getValue):_*)
              case Types.ListType => obj.getJSONArray(f.name).toArray.toList
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

    def field[T : ClassTag](field: BaseField[T]): RDD[T] = {
      rdd.flatMap(obj => {
        try {
          val value = field.valueType match {
            case Types.IntType => obj.getIntValue(field.name)
            case Types.LongType => obj.getLongValue(field.name)
            case Types.DoubleType => obj.getDouble(field.name)
            case Types.StringType => obj.getString(field.name)
            case Types.BooleanType => obj.getBoolean(field.name)
            case Types.MapType => Map(
              obj.getJSONObject(field.name).entrySet().toArray[java.util.Map.Entry[String,Any]](Array())
                .map(m=> m.getKey -> m.getValue):_*)
            case Types.ListType => obj.getJSONArray(field.name).toArray.toList
            case _ => obj.get(field.name)
          }
          Some(value.asInstanceOf[T])
        }
        catch {
          case e: Throwable =>
            e.printStackTrace()
            None
        }
      })
    }

    def key[T : ClassTag](key: String): RDD[T] = {
      rdd.flatMap(obj => {
        try {
          val value = obj.get(key)
          val cast = value match {
//            case _:Integer => obj.getIntValue(key)
//            case _:java.lang.Long => obj.getLong(key)
//            case _:java.lang.Double => obj.getDouble(key)
            case _:java.math.BigDecimal => obj.getBigDecimal(key).doubleValue()
//            case _:java.lang.String => obj.getString(key)
//            case _:java.lang.Boolean => obj.getBoolean(key)
//            case _:JSONObject => obj.getJSONObject(key)
//            case _:JSONArray => obj.getJSONArray(key)
            case _ => value
          }
          Some(cast.asInstanceOf[T])
        }
        catch {
          case e: Throwable =>
            e.printStackTrace()
            None
        }
      })
    }

    def jsonObject(keyName: String): RDD[JSONObject] = {
      key[JSONObject](keyName)
    }
  }
}
