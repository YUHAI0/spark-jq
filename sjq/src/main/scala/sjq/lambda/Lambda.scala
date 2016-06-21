package sjq.lambda

import scala.language.{implicitConversions, postfixOps, reflectiveCalls}


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

  implicit def castInt(obj: Any): Object {def cast[T]: T} = new {

    def cast[T]: T= {
      obj.asInstanceOf[T]
    }
  }

  def addInt(implicit ev1: Any => Number) = (a: Any, b: Any) => a.intValue() + b.intValue()

  def addDouble(implicit ev1: Any => Number): SObject = new SObject()

  def addTuple2[T](implicit ev1: T => Number): (((T, T), (T, T)) => (T, T)) = (a: (T, T), b: (T, T)) => ((a._1.intValue() + b._1.intValue()).cast[T], (a._2.intValue() + b._2.intValue()).cast[T])

  def addTuple3[T](implicit ev1: T => Number): (((T, T, T), (T, T, T)) => (T, T, T)) = (a: (T, T, T), b: (T, T, T)) => ((a._1.intValue() + b._1.intValue()).cast[T], (a._2.intValue() + b._2.intValue()).cast[T], (a._3.intValue() + b._3.intValue()).cast[T])

  def addTuple4[T](implicit ev1: T => Number): (((T, T, T, T), (T, T, T, T)) => (T, T, T, T)) = (a: (T, T, T, T), b: (T, T, T, T)) => ((a._1.intValue() + b._1.intValue()).cast[T], (a._2.intValue() + b._2.intValue()).cast[T], (a._3.intValue() + b._3.intValue()).cast[T], (a._4.intValue() + b._4.intValue()).cast[T])

  def addTuple5[T](implicit ev1: T => Number): (((T, T, T, T, T), (T, T, T, T, T)) => (T, T, T, T, T)) = (a: (T, T, T, T, T), b: (T, T, T, T, T)) => ((a._1.intValue() + b._1.intValue()).cast[T], (a._2.intValue() + b._2.intValue()).cast[T], (a._3.intValue() + b._3.intValue()).cast[T], (a._4.intValue() + b._4.intValue()).cast[T], (a._5.intValue() + b._5.intValue()).cast[T])

  def addTuple6[T](implicit ev1: T => Number): (((T, T, T, T, T, T), (T, T, T, T, T, T)) => (T, T, T, T, T, T)) = (a: (T, T, T, T, T, T), b: (T, T, T, T, T, T)) => ((a._1.intValue() + b._1.intValue()).cast[T], (a._2.intValue() + b._2.intValue()).cast[T], (a._3.intValue() + b._3.intValue()).cast[T], (a._4.intValue() + b._4.intValue()).cast[T], (a._5.intValue() + b._5.intValue()).cast[T], (a._6.intValue() + b._6.intValue()).cast[T])

}
