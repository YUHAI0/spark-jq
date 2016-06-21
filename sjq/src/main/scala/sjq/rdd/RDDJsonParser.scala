package sjq.rdd

import com.alibaba.fastjson.{JSON, JSONObject}
import org.apache.spark.rdd.RDD


/**
 * @author hai
 */
trait RDDJsonParser {

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

    def parseJsonMix: RDD[JSONObject] = {
      rdd.flatMap(line => {
        try {
          if (line.trim.startsWith("[")) {
            JSON.parseArray(line).toArray.map(_.asInstanceOf[JSONObject])
          } else {
            List(JSON.parseObject(line))
          }
        } catch {
          case e: Throwable =>
            Nil
        }
      })
    }

  }

}
