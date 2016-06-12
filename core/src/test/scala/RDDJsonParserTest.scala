import com.alibaba.fastjson.JSONObject
import org.apache.spark.rdd.RDD
import org.scalatest.FlatSpec
import org.sjq.fields.StringField
import org.sjq.rdd.RDDLike._

/**
 * @author hai
 */
class RDDJsonParserTest extends FlatSpec {

  val sc = Common.sc

  val data = sc.parallelize(List(
    """
      | [{"keyA": 1, "keyB": "s", "keyC": true}, {"keyA": 3, "keyB": "r", "keyC": true}]
    """.stripMargin,
    """
      | [ {"keyA": 2, "keyB": "t", "keyC": false}, {"keyA": 4, "keyB": "i", "keyC": false}]
    """.stripMargin,
    """
      | {"keyA": 5, "keyB": "n", "keyC": true}
    """.stripMargin
  ))


  "an RDDLike" can "parse json array data input" in {
    val parseRdd = data.parseJsonMix
    assert(parseRdd.isInstanceOf[RDD[JSONObject]])
    assert(parseRdd.count() == 5)
    val listRdd = parseRdd.fields(StringField("keyA"), StringField("keyB"), StringField("keyC"))
    assert(listRdd(0).map(_.toString.toInt).sum() == 15)
    assert(listRdd(1).map(_.toString).sortBy(by=>by).collect().mkString.equals("strin".toList.sortBy(by=>by).mkString("")))
  }

}
