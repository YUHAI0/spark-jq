package sjq.fields

import com.alibaba.fastjson.JSONObject

/**
 * @author hai
 */
case class ObjectField(override val name: String) extends BaseField[JSONObject](name, Types.ObjectType) {
  override def thisField: ObjectField = ObjectField(name.split("\\.").last)
}
