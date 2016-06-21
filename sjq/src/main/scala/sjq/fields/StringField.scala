package sjq.fields

/**
 * @author hai
 */
case class StringField(override val name: String) extends BaseField[String](name, Types.StringType) {
  override def thisField: StringField = StringField(name.split("\\.").last)
}
