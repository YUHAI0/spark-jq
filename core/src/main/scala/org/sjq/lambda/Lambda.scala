package org.sjq.lambda


/**
 * @author hai
 */

class SObject extends Object with Serializable with ((Any, Any) => Double) {

  implicit def AnyToNumber(value: Any): Number = {
    value match {
      case i: Int => i
      case i: Double => i
      case _ => 0
    }
  }

  override def apply(v1: Any, v2: Any): Double = v1.doubleValue() + v2.doubleValue()
}

object Lambda extends Serializable {

  implicit def AnyToNumber(value: Any): Number = {
    value match {
      case i: Int => i
      case i: Double => i
      case _ => 0
    }
  }

  implicit def AnyTupleToNumber(value: (Any, Any)): (Any, Any) = {
    value
  }

  def addInt(implicit ev1: Any => Number) = (a: Any, b: Any) => a.intValue() + b.intValue()

  def addDouble(implicit ev1: Any => Number): SObject = new SObject()

  def addTuple2(implicit ev1: Any => Number): (((Any, Any), (Any, Any)) => (Any, Any)) = (a: (Any, Any), b: (Any, Any)) => (a._1.doubleValue() + b._1.doubleValue(), a._2.doubleValue()+ b._2.doubleValue())

}
