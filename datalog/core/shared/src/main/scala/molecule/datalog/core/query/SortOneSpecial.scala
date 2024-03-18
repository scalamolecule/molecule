package molecule.datalog.core.query

import java.lang.{Boolean => jBoolean, Double => jDouble, Float => jFloat, Integer => jInteger, Long => jLong}
import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
import java.net.URI
import java.util.{Date, UUID, List => jList, Map => jMap}
import molecule.boilerplate.ast.Model._
import molecule.core.util.AggrUtils

trait SortOneSpecial[Tpl]
  extends SortOne_[Tpl]
    with ResolveBase with AggrUtils { self: Model2DatomicQuery[Tpl] =>

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

  protected def intSorter(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.op match {
      case Fn(kw, _) => kw match {
        case "sum"    => sortOneLong(attr, attrIndex)
        case "median" => sortOneLongString(attr, attrIndex)
        case _        => sortOneInt(attr, attrIndex)
      }
      case _         => sortOneInt(attr, attrIndex)
    }
  }

  protected def floatSorter(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.op match {
      case Fn(kw, _) => kw match {
        case "sum"    => sortOneDouble(attr, attrIndex)
        case "median" => sortOneDoubleString(attr, attrIndex)
        case _        => sortOneFloat(attr, attrIndex)
      }
      case _         => sortOneFloat(attr, attrIndex)
    }
  }

  protected def bigIntSorter(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.op match {
      case Fn(kw, _) => kw match {
        case "sum"    => sortOneBigDecimalString(attr, attrIndex)
        case "median" => sortOneBigDecimalString(attr, attrIndex)
        case _        => sortOneBigInt(attr, attrIndex)
      }
      case _         => sortOneBigInt(attr, attrIndex)
    }
  }

  protected def byteSorter(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.op match {
      case Fn(kw, _) => kw match {
        case "sum"    => sortOneLong(attr, attrIndex)
        case "median" => sortOneLongString(attr, attrIndex)
        case _        => sortOneByte(attr, attrIndex)
      }
      case _         => sortOneByte(attr, attrIndex)
    }
  }

  protected def shortSorter(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.op match {
      case Fn(kw, _) => kw match {
        case "sum"    => sortOneLong(attr, attrIndex)
        case "median" => sortOneLongString(attr, attrIndex)
        case _        => sortOneShort(attr, attrIndex)
      }
      case _         => sortOneShort(attr, attrIndex)
    }
  }


  private def sortOneBigDecimalString(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              new jBigDecimal(a.get(i).toString).compareTo(new jBigDecimal(b.get(i).toString))
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              new jBigDecimal(b.get(i).toString).compareTo(new jBigDecimal(a.get(i).toString))
        }
      )
    }
  }

  private def sortOneLongString(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              jLong.valueOf(a.get(i).toString).compareTo(jLong.valueOf(b.get(i).toString))
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              jLong.valueOf(b.get(i).toString).compareTo(jLong.valueOf(a.get(i).toString))
        }
      )
    }
  }

  private def sortOneDoubleString(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              jDouble.parseDouble(a.get(i).toString).compareTo(jDouble.parseDouble(b.get(i).toString))
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              jDouble.parseDouble(b.get(i).toString).compareTo(jDouble.parseDouble(a.get(i).toString))
        }
      )
    }
  }

  protected def sortOneOptLongRef(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(
                a, b, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
                  m1.values.iterator
                    .next.asInstanceOf[jMap[_, _]].values.iterator
                    .next.asInstanceOf[jLong].compareTo(
                      m2.values.iterator
                        .next.asInstanceOf[jMap[_, _]].values.iterator
                        .next.asInstanceOf[jLong])
              )
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(
                b, a, i, (m1: jMap[_, _], m2: jMap[_, _]) =>
                  m1.values.iterator
                    .next.asInstanceOf[jMap[_, _]].values.iterator
                    .next.asInstanceOf[jLong].compareTo(
                      m2.values.iterator
                        .next.asInstanceOf[jMap[_, _]].values.iterator
                        .next.asInstanceOf[jLong])
              )
        }
      )
    }
  }

  protected def sortOneBooleanOptNested(
    attr: Attr,
    attrIndex: Int
  ): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) => {
              val x: jBoolean = a.get(i) match {
                case bool: jBoolean => bool
                case `none`         => false
              }
              val y: jBoolean = b.get(i) match {
                case bool: jBoolean => bool
                case `none`         => false
              }
              x.compareTo(y)
            }
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              val x: jBoolean = a.get(i) match {
                case bool: jBoolean => bool
                case `none`         => false
              }
              val y: jBoolean = b.get(i) match {
                case bool: jBoolean => bool
                case `none`         => false
              }
              y.compareTo(x)
        }
      )
    }
  }

  protected def sortSample(
    attr: Attr,
    attrIndex: Int
  ): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              a.get(i).asInstanceOf[jList[_]].iterator().next() match {
                case v: String      => v.compareTo(b.get(i).asInstanceOf[jList[String]].iterator.next())
                case v: jInteger    => v.compareTo(b.get(i).asInstanceOf[jList[jInteger]].iterator.next())
                case v: jLong       => v.compareTo(b.get(i).asInstanceOf[jList[jLong]].iterator.next())
                case v: jFloat      => v.compareTo(b.get(i).asInstanceOf[jList[jFloat]].iterator.next())
                case v: jDouble     => v.compareTo(b.get(i).asInstanceOf[jList[jDouble]].iterator.next())
                case v: jBoolean    => v.compareTo(b.get(i).asInstanceOf[jList[jBoolean]].iterator.next())
                case v: jBigInt     => v.compareTo(b.get(i).asInstanceOf[jList[jBigInt]].iterator.next())
                case v: jBigDecimal => v.compareTo(b.get(i).asInstanceOf[jList[jBigDecimal]].iterator.next())
                case v: Date        => v.compareTo(b.get(i).asInstanceOf[jList[Date]].iterator.next())
                case v: UUID        => v.compareTo(b.get(i).asInstanceOf[jList[UUID]].iterator.next())
                case v: URI         => v.compareTo(b.get(i).asInstanceOf[jList[URI]].iterator.next())
              }
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              b.get(i).asInstanceOf[jList[_]].iterator().next() match {
                case v: String      => v.compareTo(a.get(i).asInstanceOf[jList[String]].iterator.next())
                case v: jInteger    => v.compareTo(a.get(i).asInstanceOf[jList[jInteger]].iterator.next())
                case v: jLong       => v.compareTo(a.get(i).asInstanceOf[jList[jLong]].iterator.next())
                case v: jFloat      => v.compareTo(a.get(i).asInstanceOf[jList[jFloat]].iterator.next())
                case v: jDouble     => v.compareTo(a.get(i).asInstanceOf[jList[jDouble]].iterator.next())
                case v: jBoolean    => v.compareTo(a.get(i).asInstanceOf[jList[jBoolean]].iterator.next())
                case v: jBigInt     => v.compareTo(a.get(i).asInstanceOf[jList[jBigInt]].iterator.next())
                case v: jBigDecimal => v.compareTo(a.get(i).asInstanceOf[jList[jBigDecimal]].iterator.next())
                case v: Date        => v.compareTo(a.get(i).asInstanceOf[jList[Date]].iterator.next())
                case v: UUID        => v.compareTo(a.get(i).asInstanceOf[jList[UUID]].iterator.next())
                case v: URI         => v.compareTo(a.get(i).asInstanceOf[jList[URI]].iterator.next())
              }
        }
      )
    }
  }

  protected def sortMedian(
    attr: Attr,
    attrIndex: Int
  ): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              a.get(i).toString.toDouble.compareTo(b.get(i).toString.toDouble)
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              b.get(i).toString.toDouble.compareTo(a.get(i).toString.toDouble)
        }
      )
    }
  }
}