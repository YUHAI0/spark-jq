package org.sjq.fields

/**
 * @author hai
 */
case class BooleanField(override val name: String) extends BaseField[Boolean](name, Types.BooleanType) {
  override def thisField: BooleanField = BooleanField(name.split("\\.").last)
}
