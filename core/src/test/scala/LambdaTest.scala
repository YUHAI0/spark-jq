import org.scalatest.FlatSpec

import org.sjq.lambda.Lambda._

/**
 * @author hai
 */
class LambdaTest extends FlatSpec {

  val sc = Common.sc
  val rddInt = sc.parallelize(List("k" -> 1, "k" -> 2, "K" -> 10, "K" -> 11))
  val rddDouble = sc.parallelize(List("k" -> 1.0, "k" -> 2.1, "K" -> 10.1, "K" -> 11.2))
  val rddAny = sc.parallelize(List("k" -> 1, "k" -> 2.1, "K" -> 10, "K" -> 11.2))
  val rddTuple = sc.parallelize[(String,(Any, Any))](List("k" -> (1,2), "k" -> (2.1, 2), "K" -> (10, 1), "K" -> (11.2, 3)))

  "An Add Func" can "add two numeric element" in {
    assert(rddInt.reduceByKey(addInt).map(_._2).sum() == 24)
    assert(rddDouble.reduceByKey(addDouble).map(_._2).sum() == (1.0 + 2.1 + 10.1 + 11.2))
    assert(rddAny.reduceByKey(addDouble).map(_._2.doubleValue()).sum() == BigDecimal.valueOf(List[Any](3.1 + 21.2).map(_.doubleValue()).sum).doubleValue())
    assert(rddTuple.reduceByKey(addTuple2).map(_._2._2.doubleValue()).sum() == (2 + 2 + 1 + 3))
  }

}
