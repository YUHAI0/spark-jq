package org.sjq.fields

/**
 * @author hai
 */
case class MapField[T1,T2](override val name: String) extends BaseField[Map[T1,T2]](name, Types.MapType)
