// GENERATED CODE ********************************
package molecule.datalog.core.query

import java.lang.{Boolean => jBoolean, Double => jDouble, Float => jFloat, Integer => jInteger, Long => jLong}
import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
import java.net.URI
import java.util.{Date, UUID, Map => jMap}
import molecule.boilerplate.ast.Model._


trait SortOneOpt_[Tpl] { self: Model2DatomicQuery[Tpl] =>

  private def compare(
    a: Row,
    b: Row,
    i: Int,
    compareMapValues: (jMap[_, _], jMap[_, _]) => Int
  ): Int = {
    (a.get(i), b.get(i)) match {
      case (null, null)                     => 0
      case (null, _)                        => -1
      case (_, null)                        => 1
      case (m1: jMap[_, _], m2: jMap[_, _]) => compareMapValues(m1, m2)
    }
  }

  protected def sortOneOptString(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(a, b, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
                m1.values.iterator.next.asInstanceOf[String].compareTo(
                  m2.values.iterator.next.asInstanceOf[String])
              )
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(b, a, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
                m1.values.iterator.next.asInstanceOf[String].compareTo(
                  m2.values.iterator.next.asInstanceOf[String])
              )
        }
      )
    }
  }

  protected def sortOneOptInt(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(a, b, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
                m1.values.iterator.next.toString.toInt.compareTo(
                  m2.values.iterator.next.toString.toInt)
              )
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(b, a, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
                m1.values.iterator.next.toString.toInt.compareTo(
                  m2.values.iterator.next.toString.toInt)
              )
        }
      )
    }
  }

  protected def sortOneOptLong(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(a, b, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
                m1.values.iterator.next.asInstanceOf[jLong].compareTo(
                  m2.values.iterator.next.asInstanceOf[jLong])
              )
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(b, a, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
                m1.values.iterator.next.asInstanceOf[jLong].compareTo(
                  m2.values.iterator.next.asInstanceOf[jLong])
              )
        }
      )
    }
  }

  protected def sortOneOptFloat(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(a, b, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
                m1.values.iterator.next.asInstanceOf[jFloat].compareTo(
                  m2.values.iterator.next.asInstanceOf[jFloat])
              )
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(b, a, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
                m1.values.iterator.next.asInstanceOf[jFloat].compareTo(
                  m2.values.iterator.next.asInstanceOf[jFloat])
              )
        }
      )
    }
  }

  protected def sortOneOptDouble(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(a, b, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
                m1.values.iterator.next.asInstanceOf[jDouble].compareTo(
                  m2.values.iterator.next.asInstanceOf[jDouble])
              )
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(b, a, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
                m1.values.iterator.next.asInstanceOf[jDouble].compareTo(
                  m2.values.iterator.next.asInstanceOf[jDouble])
              )
        }
      )
    }
  }

  protected def sortOneOptBoolean(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(a, b, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
                m1.values.iterator.next.asInstanceOf[jBoolean].compareTo(
                  m2.values.iterator.next.asInstanceOf[jBoolean])
              )
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(b, a, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
                m1.values.iterator.next.asInstanceOf[jBoolean].compareTo(
                  m2.values.iterator.next.asInstanceOf[jBoolean])
              )
        }
      )
    }
  }

  protected def sortOneOptBigInt(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(a, b, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
                m1.values.iterator.next.asInstanceOf[jBigInt].compareTo(
                  m2.values.iterator.next.asInstanceOf[jBigInt])
              )
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(b, a, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
                m1.values.iterator.next.asInstanceOf[jBigInt].compareTo(
                  m2.values.iterator.next.asInstanceOf[jBigInt])
              )
        }
      )
    }
  }

  protected def sortOneOptBigDecimal(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(a, b, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
                m1.values.iterator.next.asInstanceOf[jBigDecimal].compareTo(
                  m2.values.iterator.next.asInstanceOf[jBigDecimal])
              )
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(b, a, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
                m1.values.iterator.next.asInstanceOf[jBigDecimal].compareTo(
                  m2.values.iterator.next.asInstanceOf[jBigDecimal])
              )
        }
      )
    }
  }

  protected def sortOneOptDate(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(a, b, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
                m1.values.iterator.next.asInstanceOf[Date].compareTo(
                  m2.values.iterator.next.asInstanceOf[Date])
              )
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(b, a, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
                m1.values.iterator.next.asInstanceOf[Date].compareTo(
                  m2.values.iterator.next.asInstanceOf[Date])
              )
        }
      )
    }
  }

  protected def sortOneOptUUID(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(a, b, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
                m1.values.iterator.next.asInstanceOf[UUID].compareTo(
                  m2.values.iterator.next.asInstanceOf[UUID])
              )
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(b, a, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
                m1.values.iterator.next.asInstanceOf[UUID].compareTo(
                  m2.values.iterator.next.asInstanceOf[UUID])
              )
        }
      )
    }
  }

  protected def sortOneOptURI(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(a, b, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
                m1.values.iterator.next.asInstanceOf[URI].compareTo(
                  m2.values.iterator.next.asInstanceOf[URI])
              )
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(b, a, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
                m1.values.iterator.next.asInstanceOf[URI].compareTo(
                  m2.values.iterator.next.asInstanceOf[URI])
              )
        }
      )
    }
  }

  protected def sortOneOptByte(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(a, b, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
                m1.values.iterator.next.asInstanceOf[jInteger].compareTo(
                  m2.values.iterator.next.asInstanceOf[jInteger])
              )
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(b, a, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
                m1.values.iterator.next.asInstanceOf[jInteger].compareTo(
                  m2.values.iterator.next.asInstanceOf[jInteger])
              )
        }
      )
    }
  }

  protected def sortOneOptShort(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(a, b, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
                m1.values.iterator.next.asInstanceOf[jInteger].compareTo(
                  m2.values.iterator.next.asInstanceOf[jInteger])
              )
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(b, a, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
                m1.values.iterator.next.asInstanceOf[jInteger].compareTo(
                  m2.values.iterator.next.asInstanceOf[jInteger])
              )
        }
      )
    }
  }

  protected def sortOneOptChar(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(a, b, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
                m1.values.iterator.next.asInstanceOf[String].compareTo(
                  m2.values.iterator.next.asInstanceOf[String])
              )
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(b, a, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
                m1.values.iterator.next.asInstanceOf[String].compareTo(
                  m2.values.iterator.next.asInstanceOf[String])
              )
        }
      )
    }
  }
}