package org.sjq.rdd

import com.alibaba.fastjson.JSONObject
import org.apache.spark.rdd.RDD
import org.sjq.fields.{RegexField, BaseField, Types}

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

  implicit class AnyFieldToSpecified(val rdd: RDD[Any]) {

    def Int: RDD[Int] = {
      rdd.flatMap(element => {
        try {
          Some(element.asInstanceOf[Int])
        } catch {
          case e: Throwable =>
            None
        }
      })
    }

    def Long: RDD[Long] = {
      rdd.flatMap(element => {
        try {
          Some(element.asInstanceOf[Long])
        } catch {
          case e: Throwable =>
            None
        }
      })
    }

    def Double: RDD[Double] = {
      rdd.flatMap(element => {
        try {
          Some(element.asInstanceOf[Double])
        } catch {
          case e: Throwable =>
            None
        }
      })
    }

    def Boolean: RDD[Boolean] = {
      rdd.flatMap(element => {
        try {
          Some(element.asInstanceOf[Boolean])
        } catch {
          case e: Throwable =>
            None
        }
      })
    }

    def List[T]: RDD[List[T]] = {
      rdd.flatMap(element => {
        try {
          Some(element.asInstanceOf[List[T]])
        } catch {
          case e: Throwable =>
            None
        }
      })
    }


    def Map[T1, T2]: RDD[Map[T1, T2]] = {
      rdd.flatMap(element => {
        try {
          Some(element.asInstanceOf[Map[T1, T2]])
        } catch {
          case e: Throwable =>
            None
        }
      })
    }

    def JSONObject: RDD[JSONObject] = {
      rdd.flatMap(element => {
        try {
          Some(element.asInstanceOf[JSONObject])
        } catch {
          case e: Throwable =>
            None
        }
      })
    }

    def String: RDD[String] = {
      rdd.flatMap(element => {
        try {
          Some(element.toString)
        } catch {
          case e: Throwable =>
            None
        }
      })
    }
  }

  implicit class ListToField(val rdd: RDD[List[Any]]) {

    def apply(n: Int): RDD[Any] = {
      rdd.flatMap(list => {
        try {
          Some(list(n))
        } catch {
          case e: Throwable =>
            None
        }
      })
    }

    def apply[T1: ClassTag, T2: ClassTag](n1: Int, n2: Int): RDD[(T1, T2)] = {
      rdd.flatMap(list => {
        try {
          Some((list(n1).asInstanceOf[T1], list(n2).asInstanceOf[T2]))
        } catch {
          case e: Throwable =>
            None
        }
      })
    }

    def apply[T1: ClassTag, T2: ClassTag, T3: ClassTag](n1: Int, n2: Int, n3: Int): RDD[(T1, T2, T3)] = {
      rdd.flatMap(list => {
        try {
          Some((list(n1).asInstanceOf[T1], list(n2).asInstanceOf[T2], list(n3).asInstanceOf[T3]))
        } catch {
          case e: Throwable =>
            None
        }
      })
    }

    def apply[T1: ClassTag, T2: ClassTag, T3: ClassTag, T4: ClassTag](n1: Int, n2: Int, n3: Int, n4: Int): RDD[(T1, T2, T3, T4)] = {
      rdd.flatMap(list => {
        try {
          Some((list(n1).asInstanceOf[T1], list(n2).asInstanceOf[T2], list(n3).asInstanceOf[T3], list(n4).asInstanceOf[T4]))
        } catch {
          case e: Throwable =>
            None
        }
      })
    }

    def apply[T1: ClassTag, T2: ClassTag, T3: ClassTag, T4: ClassTag, T5: ClassTag](n1: Int, n2: Int, n3: Int, n4: Int, n5: Int): RDD[(T1, T2, T3, T4, T5)] = {
      rdd.flatMap(list => {
        try {
          Some((list(n1).asInstanceOf[T1], list(n2).asInstanceOf[T2], list(n3).asInstanceOf[T3], list(n4).asInstanceOf[T4], list(n5).asInstanceOf[T5]))
        } catch {
          case e: Throwable =>
            None
        }
      })
    }

    def apply[T1: ClassTag, T2: ClassTag, T3: ClassTag, T4: ClassTag, T5: ClassTag, T6: ClassTag](n1: Int, n2: Int, n3: Int, n4: Int, n5: Int, n6: Int): RDD[(T1, T2, T3, T4, T5, T6)] = {
      rdd.flatMap(list => {
        try {
          Some((list(n1).asInstanceOf[T1], list(n2).asInstanceOf[T2], list(n3).asInstanceOf[T3], list(n4).asInstanceOf[T4], list(n5).asInstanceOf[T5], list(n6).asInstanceOf[T6]))
        } catch {
          case e: Throwable =>
            None
        }
      })
    }

    def apply[T1: ClassTag, T2: ClassTag, T3: ClassTag, T4: ClassTag, T5: ClassTag, T6: ClassTag, T7: ClassTag](n1: Int, n2: Int, n3: Int, n4: Int, n5: Int, n6: Int, n7: Int): RDD[(T1, T2, T3, T4, T5, T6, T7)] = {
      rdd.flatMap(list => {
        try {
          Some((list(n1).asInstanceOf[T1], list(n2).asInstanceOf[T2], list(n3).asInstanceOf[T3], list(n4).asInstanceOf[T4], list(n5).asInstanceOf[T5], list(n6).asInstanceOf[T6], list(n7).asInstanceOf[T7]))
        } catch {
          case e: Throwable =>
            None
        }
      })
    }

    def apply[T1: ClassTag, T2: ClassTag, T3: ClassTag, T4: ClassTag, T5: ClassTag, T6: ClassTag, T7: ClassTag, T8: ClassTag](n1: Int, n2: Int, n3: Int, n4: Int, n5: Int, n6: Int, n7: Int, n8: Int): RDD[(T1, T2, T3, T4, T5, T6, T7, T8)] = {
      rdd.flatMap(list => {
        try {
          Some((list(n1).asInstanceOf[T1], list(n2).asInstanceOf[T2], list(n3).asInstanceOf[T3], list(n4).asInstanceOf[T4], list(n5).asInstanceOf[T5], list(n6).asInstanceOf[T6], list(n7).asInstanceOf[T7], list(n8).asInstanceOf[T8]))
        } catch {
          case e: Throwable =>
            None
        }
      })
    }

    def apply[
    T1: ClassTag, T2: ClassTag, T3: ClassTag, T4: ClassTag, T5: ClassTag, T6: ClassTag, T7: ClassTag, T8: ClassTag,
    T9: ClassTag]
    (n1: Int, n2: Int, n3: Int, n4: Int, n5: Int, n6: Int, n7: Int, n8: Int, n9: Int):
    RDD[(T1, T2, T3, T4, T5, T6, T7, T8, T9)] = {
      rdd.flatMap(list => {
        try {
          Some((
            list(n1).asInstanceOf[T1], list(n2).asInstanceOf[T2], list(n3).asInstanceOf[T3], list(n4).asInstanceOf[T4], list(n5).asInstanceOf[T5], list(n6).asInstanceOf[T6], list(n7).asInstanceOf[T7], list(n8).asInstanceOf[T8],
            list(n9).asInstanceOf[T9]
            ))
        } catch {
          case e: Throwable =>
            None
        }
      })
    }

    def apply[
    T1: ClassTag, T2: ClassTag, T3: ClassTag, T4: ClassTag, T5: ClassTag, T6: ClassTag, T7: ClassTag, T8: ClassTag,
    T9: ClassTag, T10: ClassTag]
    (n1: Int, n2: Int, n3: Int, n4: Int, n5: Int, n6: Int, n7: Int, n8: Int, n9: Int, n10: Int):
    RDD[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10)] = {
      rdd.flatMap(list => {
        try {
          Some((
            list(n1).asInstanceOf[T1], list(n2).asInstanceOf[T2], list(n3).asInstanceOf[T3], list(n4).asInstanceOf[T4], list(n5).asInstanceOf[T5], list(n6).asInstanceOf[T6], list(n7).asInstanceOf[T7], list(n8).asInstanceOf[T8],
            list(n9).asInstanceOf[T9], list(n10).asInstanceOf[T10]
            ))
        } catch {
          case e: Throwable =>
            None
        }
      })
    }

    def apply[
    T1: ClassTag, T2: ClassTag, T3: ClassTag, T4: ClassTag, T5: ClassTag, T6: ClassTag, T7: ClassTag, T8: ClassTag,
    T9: ClassTag, T10: ClassTag, T11: ClassTag]
    (n1: Int, n2: Int, n3: Int, n4: Int, n5: Int, n6: Int, n7: Int, n8: Int, n9: Int, n10: Int, n11: Int):
    RDD[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11)] = {
      rdd.flatMap(list => {
        try {
          Some((
            list(n1).asInstanceOf[T1], list(n2).asInstanceOf[T2], list(n3).asInstanceOf[T3], list(n4).asInstanceOf[T4], list(n5).asInstanceOf[T5], list(n6).asInstanceOf[T6], list(n7).asInstanceOf[T7], list(n8).asInstanceOf[T8],
            list(n9).asInstanceOf[T9], list(n10).asInstanceOf[T10], list(n11).asInstanceOf[T11]
            ))
        } catch {
          case e: Throwable =>
            None
        }
      })
    }

    def apply[
    T1: ClassTag, T2: ClassTag, T3: ClassTag, T4: ClassTag, T5: ClassTag, T6: ClassTag, T7: ClassTag, T8: ClassTag,
    T9: ClassTag, T10: ClassTag, T11: ClassTag, T12: ClassTag]
    (n1: Int, n2: Int, n3: Int, n4: Int, n5: Int, n6: Int, n7: Int, n8: Int, n9: Int, n10: Int, n11: Int, n12: Int):
    RDD[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12)] = {
      rdd.flatMap(list => {
        try {
          Some((
            list(n1).asInstanceOf[T1], list(n2).asInstanceOf[T2], list(n3).asInstanceOf[T3], list(n4).asInstanceOf[T4], list(n5).asInstanceOf[T5], list(n6).asInstanceOf[T6], list(n7).asInstanceOf[T7], list(n8).asInstanceOf[T8],
            list(n9).asInstanceOf[T9], list(n10).asInstanceOf[T10], list(n11).asInstanceOf[T11], list(n12).asInstanceOf[T12]
            ))
        } catch {
          case e: Throwable =>
            None
        }
      })
    }

    def apply[
    T1: ClassTag, T2: ClassTag, T3: ClassTag, T4: ClassTag, T5: ClassTag, T6: ClassTag, T7: ClassTag, T8: ClassTag,
    T9: ClassTag, T10: ClassTag, T11: ClassTag, T12: ClassTag, T13: ClassTag]
    (n1: Int, n2: Int, n3: Int, n4: Int, n5: Int, n6: Int, n7: Int, n8: Int, n9: Int, n10: Int, n11: Int, n12: Int, n13: Int):
    RDD[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13)] = {
      rdd.flatMap(list => {
        try {
          Some((
            list(n1).asInstanceOf[T1], list(n2).asInstanceOf[T2], list(n3).asInstanceOf[T3], list(n4).asInstanceOf[T4], list(n5).asInstanceOf[T5], list(n6).asInstanceOf[T6], list(n7).asInstanceOf[T7], list(n8).asInstanceOf[T8],
            list(n9).asInstanceOf[T9], list(n10).asInstanceOf[T10], list(n11).asInstanceOf[T11], list(n12).asInstanceOf[T12], list(n13).asInstanceOf[T13]
            ))
        } catch {
          case e: Throwable =>
            None
        }
      })
    }

    def apply[
    T1: ClassTag, T2: ClassTag, T3: ClassTag, T4: ClassTag, T5: ClassTag, T6: ClassTag, T7: ClassTag, T8: ClassTag,
    T9: ClassTag, T10: ClassTag, T11: ClassTag, T12: ClassTag, T13: ClassTag, T14: ClassTag]
    (n1: Int, n2: Int, n3: Int, n4: Int, n5: Int, n6: Int, n7: Int, n8: Int, n9: Int, n10: Int, n11: Int, n12: Int, n13: Int, n14: Int):
    RDD[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14)] = {
      rdd.flatMap(list => {
        try {
          Some((
            list(n1).asInstanceOf[T1], list(n2).asInstanceOf[T2], list(n3).asInstanceOf[T3], list(n4).asInstanceOf[T4], list(n5).asInstanceOf[T5], list(n6).asInstanceOf[T6], list(n7).asInstanceOf[T7], list(n8).asInstanceOf[T8],
            list(n9).asInstanceOf[T9], list(n10).asInstanceOf[T10], list(n11).asInstanceOf[T11], list(n12).asInstanceOf[T12], list(n13).asInstanceOf[T13], list(n14).asInstanceOf[T14]
            ))
        } catch {
          case e: Throwable =>
            None
        }
      })
    }

    def apply[
    T1: ClassTag, T2: ClassTag, T3: ClassTag, T4: ClassTag, T5: ClassTag, T6: ClassTag, T7: ClassTag, T8: ClassTag,
    T9: ClassTag, T10: ClassTag, T11: ClassTag, T12: ClassTag, T13: ClassTag, T14: ClassTag, T15: ClassTag]
    (n1: Int, n2: Int, n3: Int, n4: Int, n5: Int, n6: Int, n7: Int, n8: Int, n9: Int, n10: Int, n11: Int, n12: Int, n13: Int, n14: Int, n15: Int):
    RDD[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15)] = {
      rdd.flatMap(list => {
        try {
          Some((
            list(n1).asInstanceOf[T1], list(n2).asInstanceOf[T2], list(n3).asInstanceOf[T3], list(n4).asInstanceOf[T4], list(n5).asInstanceOf[T5], list(n6).asInstanceOf[T6], list(n7).asInstanceOf[T7], list(n8).asInstanceOf[T8],
            list(n9).asInstanceOf[T9], list(n10).asInstanceOf[T10], list(n11).asInstanceOf[T11], list(n12).asInstanceOf[T12], list(n13).asInstanceOf[T13], list(n14).asInstanceOf[T14], list(n15).asInstanceOf[T15]
            ))
        } catch {
          case e: Throwable =>
            None
        }
      })
    }

    def apply[
    T1: ClassTag, T2: ClassTag, T3: ClassTag, T4: ClassTag, T5: ClassTag, T6: ClassTag, T7: ClassTag, T8: ClassTag,
    T9: ClassTag, T10: ClassTag, T11: ClassTag, T12: ClassTag, T13: ClassTag, T14: ClassTag, T15: ClassTag, T16: ClassTag]
    (n1: Int, n2: Int, n3: Int, n4: Int, n5: Int, n6: Int, n7: Int, n8: Int, n9: Int, n10: Int, n11: Int, n12: Int, n13: Int, n14: Int, n15: Int, n16: Int):
    RDD[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16)] = {
      rdd.flatMap(list => {
        try {
          Some((
            list(n1).asInstanceOf[T1], list(n2).asInstanceOf[T2], list(n3).asInstanceOf[T3], list(n4).asInstanceOf[T4], list(n5).asInstanceOf[T5], list(n6).asInstanceOf[T6], list(n7).asInstanceOf[T7], list(n8).asInstanceOf[T8],
            list(n9).asInstanceOf[T9], list(n10).asInstanceOf[T10], list(n11).asInstanceOf[T11], list(n12).asInstanceOf[T12], list(n13).asInstanceOf[T13], list(n14).asInstanceOf[T14], list(n15).asInstanceOf[T15], list(n16).asInstanceOf[T16]
            ))
        } catch {
          case e: Throwable =>
            None
        }
      })
    }

    // More apply ... or cut this feature
  }


  implicit class JsonObjectToFields(val rdd: RDD[JSONObject]) extends Serializable {

    private def flatField(obj: JSONObject, field: BaseField[Any]): Any = {
      if (field.hasParent) {
        val parent = field.parentField
        val me = field.thisField
        flatField(flatField(obj, parent).asInstanceOf[JSONObject], me)
      } else field.valueType match {
        case Types.IntType => obj.getIntValue(field.name)
        case Types.LongType => obj.getLongValue(field.name)
        case Types.DoubleType => obj.getDouble(field.name)
        case Types.StringType => obj.getString(field.name)
        case Types.BooleanType => obj.getBoolean(field.name)
        case Types.MapType => Map(
          obj.getJSONObject(field.name).entrySet().toArray[java.util.Map.Entry[String,Any]](Array())
            .map(m => m.getKey -> m.getValue): _*)
        case Types.ListType => obj.getJSONArray(field.name).toArray.toList
        case Types.ObjectType => obj.getJSONObject(field.name)
        case Types.RegexType => field.asInstanceOf[RegexField].regex(obj.getString(field.name))
        case _ => obj.get(field.name)
      }
    }

    def fields(fields: BaseField[Any]*): RDD[List[Any]] = {
      rdd.flatMap(obj => {
        try {
          val value = fields.map(flatField(obj, _)).toList
          Some(value)
        }
        catch {
          case e: Throwable =>
            e.printStackTrace()
            None
        }
      })
    }

    def field[T: ClassTag](field: BaseField[T]): RDD[T] = {
      rdd.flatMap(obj => {
        try {
          val value = flatField(obj, field)
          Some(value.asInstanceOf[T])
        }
        catch {
          case e: Throwable =>
            e.printStackTrace()
            None
        }
      })
    }

    def key[T: ClassTag](key: String): RDD[T] = {
      rdd.flatMap(obj => {
        try {
          val value = obj.get(key)
          val cast = value match {
            //            case _:Integer => obj.getIntValue(key)
            //            case _:java.lang.Long => obj.getLong(key)
            //            case _:java.lang.Double => obj.getDouble(key)
            case _: java.math.BigDecimal => obj.getBigDecimal(key).doubleValue()
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
