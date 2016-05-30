import com.alibaba.fastjson.{JSONArray, JSONObject}
import org.apache.spark.rdd.RDD
import org.scalatest.FlatSpec
import org.sjq.fields._
import org.sjq.rdd.RDDLike._

import scala.language.postfixOps

/**
 * @author hai
 */
object RDDLikeTest extends FlatSpec {

  val sc = Common.sc

  val data = List(
    """ { "keyA": 1, "keyB": "stringA", "keyC": true, "keyD": 20000, "keyE": 10.10101,
      |"keyF": {"m1": 1, "m2": 2, "m3": 3, "m4": true, "m5":{"mm1":1}}, "keyG": ["a",1,2,"b"] } """.stripMargin,
    """ { "keyA": 2, "keyB": "stringB", "keyC": false, "keyD": 40000, "keyE": 101.010101,
      |"keyF": {"m1": 11, "m2": 12, "m3": 13, "m4":false, "m5":{"mm1":2}}, "keyG": ["c",3,4,"d"] } """.stripMargin
  )


  val rddJson = sc.parallelize(data).parseJson
  "An RDDLike" can "map an RDD[String] within json into an RDD[JSONObject]" in {
    assert(rddJson.isInstanceOf[RDD[JSONObject]])
    assert(rddJson.filter(_.isInstanceOf[JSONObject]).count() == 2)
  }

  it can "map an RDD[JSONObject] into an RDD[Fields] which Fields is the fields that users has chosen" in {
    val rddFields = rddJson.fields(
      IntField("keyA"), StringField("keyB"), BooleanField("keyC"), LongField("keyD"), DoubleField("keyE"), MapField[String, Any]("keyF"))
    assert(rddFields.map(_(0).asInstanceOf[Int]).sum() == 3)
    assert(rddFields.map(_(1).asInstanceOf[String]).sortBy(s=>s).collect().foldLeft("")((a,b)=>a+b) == "stringAstringB")
    assert(rddFields.map(_(2).asInstanceOf[Boolean]).collect()(0) && !rddFields.map(_(2).asInstanceOf[Boolean]).collect()(1))
    assert(rddFields.map(_(3).asInstanceOf[Long]).fold(0L)((a,b)=>a+b) == (20000L+40000L))
    assert(rddFields.map(_(4).asInstanceOf[Double]).fold(0.0)((a,b)=>a+b) == (10.10101+101.010101))
    assert(rddFields.map(_(5).asInstanceOf[Map[String,Any]].keys.toList.sorted.fold("")((a,b)=> a + b)).distinct().first() == "m1m2m3m4m5" )
  }

  it can "support ArrayType in JSON" in {

    assert(sc.parallelize(List(data.head)).parseJson.fields(ListField[Any]("keyG")).first().head.asInstanceOf[List[Any]].mkString == List("a", 1, 2, "b").mkString)
    assert(sc.parallelize(List(data(1))).parseJson.fields(ListField[Any]("keyG")).first().head.asInstanceOf[List[Any]].mkString == List("c", 3, 4, "d").mkString)
  }

  it can "get one field only" in {
    assert(rddJson.field[Int](IntField("keyA")).sum() == 3)
    assert(rddJson.field[String](StringField("keyB")).sortBy(m=>m).collect().mkString == "stringAstringB")
    assert(sc.parallelize(List(data.head)).parseJson.field[Boolean](BooleanField("keyC")).first())
    assert(!sc.parallelize(List(data(1))).parseJson.field[Boolean](BooleanField("keyC")).first())
    assert(rddJson.field[Int](IntField("keyD")).sum() == 60000)
    assert(rddJson.field[Double](DoubleField("keyE")).sum() == (10.10101+101.010101))
    assert(sc.parallelize(List(data.head)).parseJson.field[Map[String, Int]](MapField("keyF")).first().get("m1") == Some(1))
    assert(sc.parallelize(List(data(1))).parseJson.field[Map[String, Int]](MapField("keyF")).first().get("m2") == Some(12))
    assert(sc.parallelize(List(data.head)).parseJson.field[List[Any]](ListField("keyG")).first().mkString == "a12b")
    assert(sc.parallelize(List(data(1))).parseJson.field[List[Any]](ListField("keyG")).first().mkString == "c34d")
  }

  it can "also get one field by key name & type" in {
    assert(rddJson.key[Int]("keyA").sum() == 3)
    assert(rddJson.key[String]("keyB").sortBy(m=>m).collect().mkString == "stringAstringB")
    assert(sc.parallelize(List(data.head)).parseJson.key[Boolean]("keyC").first())
    assert(!sc.parallelize(List(data(1))).parseJson.key[Boolean]("keyC").first())
    assert(rddJson.key[Int]("keyD").sum() == 60000)
    assert(rddJson.key[Double]("keyE").sum() == (10.10101+101.010101))
    assert(sc.parallelize(List(data.head)).parseJson.key[JSONObject]("keyF").first().getIntValue("m1") == 1)
    assert(sc.parallelize(List(data(1))).parseJson.key[JSONObject]("keyF").first().getIntValue("m2") == 12)
    assert(sc.parallelize(List(data.head)).parseJson.key[JSONArray]("keyG").first().toArray.mkString == "a12b")
    assert(sc.parallelize(List(data(1))).parseJson.key[JSONArray]("keyG").first().toArray.mkString == "c34d")
  }

  it can "get a JSONObject and use next field method" in {
    val rdd = rddJson.jsonObject("keyF")
    assert(rdd.key[Int]("m1").sum() == 12)
    assert(rdd.key[Boolean]("m4").filter(_ == true).count() == 1)
    assert(rdd.jsonObject("m5").key[Int]("mm1").sum() == 3)
  }

  it can "map RDD[Any] into RDD[T] and get element of RDD[List[Any]] with apply(n: Int) method" in {
    val rddFields = rddJson.fields(
      IntField("keyA"), StringField("keyB"), BooleanField("keyC"), LongField("keyD"), DoubleField("keyE"), MapField[String, Any]("keyF"))

    assert(rddFields(0).Int.sum() == 3)
    assert(rddFields(1).String.sortBy(s=>s).collect().foldLeft("")((a,b)=>a+b) == "stringAstringB")
    assert(rddFields(2).Boolean.collect()(0) && !rddFields(2).Boolean.collect()(1))
    assert(rddFields(3).Long.fold(0L)((a,b)=>a+b) == (20000L+40000L))
    assert(rddFields(4).Double.fold(0.0)((a,b)=>a+b) == (10.10101+101.010101))
    assert(rddFields(5).Map[String,Any].map(_.keys.toList.sorted.fold("")((a,b)=> a + b)).distinct().first() == "m1m2m3m4m5" )
  }

  it can "support get JSONObject field by ObjectField" in {
    val rdd = rddJson.field[JSONObject](ObjectField("keyF"))
    assert(rdd.key[Int]("m1").sum() == 12)
    assert(rdd.key[Boolean]("m4").filter(_ == true).count() == 1)
    assert(rdd.jsonObject("m5").key[Int]("mm1").sum() == 3)
  }

  it can "get an compose field wrapper by object, ex. 'a.b.c.d' "  in {
    assert(rddJson.field(IntField("keyF.m1")).sum() == 12)
    assert(rddJson.field(BooleanField("keyF.m4")).filter(_ == true).count() == 1)
    assert(rddJson.field(IntField("keyF.m5.mm1")).sum() == 3)

    val rdd = rddJson.fields(IntField("keyF.m1"), BooleanField("keyF.m4"), IntField("keyF.m5.mm1"))
    assert(rdd(0).Int.sum() == 12)
    assert(rdd(1).Boolean.filter(_ == true).count() == 1)
    assert(rdd(2).Int.sum() == 3)
  }

}
