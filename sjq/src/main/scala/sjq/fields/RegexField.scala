package sjq.fields

import java.util.regex.Pattern

/**
 * @author hai
 */
case class RegexField(override val name: String, regex: String, group: Int = 0) extends BaseField[String](name, Types.RegexType) {

  override def thisField: RegexField = RegexField(name.split("\\.").last, regex, group)

  def regex(input: String): String = {
    val pattern = Pattern.compile(regex)
    val matcher = pattern.matcher(input)
    if (matcher.find())
      matcher.group(group)
    else
      new Object().toString
  }
}
