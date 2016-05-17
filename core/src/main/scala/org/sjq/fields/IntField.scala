package org.sjq.fields

/**
 * @author hai
 */
case class IntField(override val name: String) extends BaseField[Int](name, Types.IntType)
