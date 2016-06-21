package sjq.fields

/**
 * @author hai
 */
case class IntField(override val name: String) extends BaseField[Int](name, Types.IntType) {
  override def thisField: IntField = IntField(name.split("\\.").last)
}
