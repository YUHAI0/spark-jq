package org.sjq.fields

/**
 * @author hai
 */
case class DoubleField(override val name: String) extends BaseField[Double](name, Types.DoubleType)
