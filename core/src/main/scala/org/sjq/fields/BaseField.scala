package org.sjq.fields

/**
 * @author hai
 */
class BaseField[+T](val name: String, val valueType: Types.Type) extends Serializable