package org.sjq.fields

/**
 * @author hai
 */
case class LongField(override val name: String) extends BaseField[Long](name, Types.LongType) {
  override def thisField: LongField = LongField(name.split("\\.").last)
}
