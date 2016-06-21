import org.scalatest.FlatSpec
import sjq.fields.RegexField
import sjq.rdd.RDDLike._

/**
 * @author hai
 */
class RDDRegexFieldTest extends FlatSpec {

  val sc = Common.sc

  val data =
    """
      | [
      |   {
      |     "keyRegA": "innerKeyA: valueA-09; innerKeyB: valueB-10",
      |     "keyRegB": "<html><tag id='id1'>Tag-11</tag></html>"
      |   },
      |   {
      |     "keyRegA": "innerKeyA: valueA-09; innerKeyB: valueB-10",
      |     "keyRegB": "<xml><tag id='id2'>Tag-12</tag></html>"
      |   }
      | ]
      |
    """.stripMargin

  val parseRdd = sc.parallelize(List(data)).parseJsonMix

  assert(parseRdd.field(RegexField("keyRegA", "\\w+:\\s([\\w-\\d]+)", 1)).sortBy(by=>by).collect().mkString == "valueA-09valueA-09")
  assert(parseRdd.field(RegexField("keyRegB", ">([\\w-\\d]*?)</", 1)).sortBy(by=>by).collect().mkString == "Tag-11Tag-12")

}
