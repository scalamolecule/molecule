package molecule.datalog.core.query

import java.lang.{Boolean => jBoolean, Double => jDouble, Long => jLong}
import java.math.{BigDecimal => jBigDecimal}
import java.util.{Map => jMap, Set => jSet}
import molecule.boilerplate.ast.Model._
import molecule.core.util.AggrUtils


trait SortOneSpecial[Tpl]
  extends SortOne_[Tpl]
    with ResolveBase with AggrUtils { self: DatomicModel2Query[Tpl] =>

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

  protected def intSorter(at: AttrOneManInt, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    at.op match {
      case Fn(kw, _) => kw match {
        case "sum"    => sortOneLong(at, attrIndex)
        case "median" => sortOneLongString(at, attrIndex)
        case _        => sortOneInt(at, attrIndex)
      }
      case _         => sortOneInt(at, attrIndex)
    }
  }

  protected def floatSorter(at: AttrOneManFloat, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    at.op match {
      case Fn(kw, _) => kw match {
        case "sum"    => sortOneDouble(at, attrIndex)
        case "median" => sortOneDoubleString(at, attrIndex)
        case _        => sortOneFloat(at, attrIndex)
      }
      case _         => sortOneFloat(at, attrIndex)
    }
  }

  protected def bigIntSorter(at: AttrOneManBigInt, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    at.op match {
      case Fn(kw, _) => kw match {
        case "sum"    => sortOneBigDecimalString(at, attrIndex)
        case "median" => sortOneBigDecimalString(at, attrIndex)
        case _        => sortOneBigInt(at, attrIndex)
      }
      case _         => sortOneBigInt(at, attrIndex)
    }
  }

  protected def byteSorter(at: AttrOneManByte, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    at.op match {
      case Fn(kw, _) => kw match {
        case "sum"    => sortOneLong(at, attrIndex)
        case "median" => sortOneLongString(at, attrIndex)
        case _        => sortOneByte(at, attrIndex)
      }
      case _         => sortOneByte(at, attrIndex)
    }
  }

  protected def shortSorter(at: AttrOneManShort, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    at.op match {
      case Fn(kw, _) => kw match {
        case "sum"    => sortOneLong(at, attrIndex)
        case "median" => sortOneLongString(at, attrIndex)
        case _        => sortOneShort(at, attrIndex)
      }
      case _         => sortOneShort(at, attrIndex)
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

  protected def sortOneDoublePossiblyMedianSet(
    attr: Attr,
    attrIndex: Int
  ): Option[(Int, Int => (Row, Row) => Int)] = {
    def median(rawSet: AnyRef): Double = {
      getMedian(rawSet.asInstanceOf[jSet[_]].toArray.map(_.toString.toDouble).toSet)
    }
    if (attr.op == Fn("median", None) && attr.sort.nonEmpty) {
      attr.sort.map { sort =>
        (
          sort.last.toString.toInt,
          sort.head match {
            case 'a' => (nestedIdsCount: Int) =>
              val i = nestedIdsCount + attrIndex
              (a: Row, b: Row) => median(a.get(i)).compareTo(median(b.get(i)))

            case 'd' => (nestedIdsCount: Int) =>
              val i = nestedIdsCount + attrIndex
              (a: Row, b: Row) => median(b.get(i)).compareTo(median(a.get(i)))
          }
        )
      }
    } else {
      // normal sorting
      sortOneDouble(attr, attrIndex)
    }
  }
}