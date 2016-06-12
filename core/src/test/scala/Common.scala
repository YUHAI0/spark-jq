import org.apache.spark.{SparkContext, SparkConf}

/**
 * @author hai
 */
object Common {

  val conf  = new SparkConf().setAppName("JQTest").setMaster("local")
  val sc    = new SparkContext(conf)

}
