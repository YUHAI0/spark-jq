package org.sjq.fields

/**
 * @author hai
 */
case class BooleanField(override val name: String) extends BaseField[Boolean](name, Types.BooleanType)