package sjq.fields

/**
 * @author hai
 */
abstract class BaseField[+T](val name: String, val valueType: Types.Type) extends Serializable {

  def hasParent = name.split("\\.").size > 1

  def parentField: ObjectField = {
    if (hasParent)
      ObjectField(name.substring(0, name.lastIndexOf(".")))
    else
      throw new RuntimeException("Field has no parent object")
  }

  def thisField: BaseField[T]

}
