package sjq.fields

/**
 * @author hai
 */
case class ListField[T](override val name: String) extends BaseField[List[T]](name, Types.ListType) {
  override def thisField: ListField[T] = ListField[T](name.split("\\.").last)
}
