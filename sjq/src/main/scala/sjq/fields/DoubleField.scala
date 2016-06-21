package sjq.fields

/**
 * @author hai
 */
case class DoubleField(override val name: String) extends BaseField[Double](name, Types.DoubleType) {
  override def thisField: DoubleField = DoubleField(name.split("\\.").last)
}
