import com.alibaba.fastjson.JSONObject
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkContext, SparkConf}
import org.scalatest.FlatSpec
import org.sjq.fields._
import org.sjq.rdd.RDDLike._

import scala.language.postfixOps

/**
 * @author hai
 */
class RDDLikeTest extends FlatSpec {

  val conf = new SparkConf().setAppName("RDDLikeTest").setMaster("local[2]")
  val sc = new SparkContext(conf)
  val data = List(
    """ { "keyA": 1, "keyB": "stringA", "keyC": true, "keyD": 20000, "keyE": 10.10101, "keyF": {"m1": 1, "m2": 2, "m3": 3}, "keyG": ["a",1,2,"b"] } """,
    """ { "keyA": 2, "keyB": "stringB", "keyC": false, "keyD": 40000, "keyE": 101.010101, "keyF": {"m1": 11, "m2": 12, "m3": 13}, "keyG": ["c",3,4,"d"] } """
  )


  val rddJson = sc.parallelize(data).parseJson
  "An RDDLike" can "map an RDD[String] within json into an RDD[JSONObject]" in {
    assert(rddJson.isInstanceOf[RDD[JSONObject]])
    assert(rddJson.filter(_.isInstanceOf[JSONObject]).count() == 2)
  }

  it can "map an RDD[JSONObject] into an RDD[Fields] which Fields is the fields that users has chosen" in {
    val rddFields = rddJson.fields(IntField("keyA"), StringField("keyB"), BooleanField("keyC"), LongField("keyD"), DoubleField("keyE"), MapField("keyF"))
    assert(rddFields.map(_(0).asInstanceOf[Int]).sum() == 3)
    assert(rddFields.map(_(1).asInstanceOf[String]).sortBy(s=>s).collect().foldLeft("")((a,b)=>a+b) == "stringAstringB")
    assert(rddFields.map(_(2).asInstanceOf[Boolean]).collect()(0) && !rddFields.map(_(2).asInstanceOf[Boolean]).collect()(1))
    assert(rddFields.map(_(3).asInstanceOf[Long]).fold(0L)((a,b)=>a+b) == (20000L+40000L))
    assert(rddFields.map(_(4).asInstanceOf[Double]).fold(0.0)((a,b)=>a+b) == (10.10101+101.010101))
    assert(rddFields.map(_(5).asInstanceOf[Map[String,Int]].keys.toList.sorted.fold("")((a,b)=> a + b)).distinct().first() == "m1m2m3" )
    assert(rddFields.map(_(5).asInstanceOf[Map[String,Int]].values.sum).sum() == 42)
  }

  it can "support ArrayType in JSON" in {

    assert(sc.parallelize(List(data.head)).parseJson.fields(ListField("keyG")).first().head.asInstanceOf[List[Any]].mkString == List("a", 1, 2, "b").mkString)
    assert(sc.parallelize(List(data(1))).parseJson.fields(ListField("keyG")).first().head.asInstanceOf[List[Any]].mkString == List("c", 3, 4, "d").mkString)


  }
}
