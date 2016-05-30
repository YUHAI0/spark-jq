package org.sjq.fields

/**
 * @author hai
 */
object Types extends Enumeration {
  type Type = Value
  val IntType, LongType, StringType, DoubleType, BooleanType, MapType, ListType, ObjectType = Value
}
