package org.sjq.fields

/**
 * @author hai
 */
case class MapField[T1,T2](override val name: String) extends BaseField[Map[T1,T2]](name, Types.MapType) {
  override def thisField: MapField[T1, T2] = MapField[T1, T2](name.split("\\.").last)
}
