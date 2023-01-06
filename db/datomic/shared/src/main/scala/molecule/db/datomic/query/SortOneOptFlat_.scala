// GENERATED CODE ********************************
package molecule.db.datomic.query

import java.lang.{Boolean => jBoolean, Double => jDouble, Float => jFloat, Integer => jInteger, Long => jLong}
import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
import java.net.URI
import java.util.{Date, UUID}
import molecule.boilerplate.ast.Model._


trait SortOneOptFlat_[Tpl] extends ResolveBase { self: Base[Tpl] =>

  private def compare(
    a: Row,
    b: Row,
    i: Int,
    compareMapValues: (Any, Any) => Int
  ): Int = {
    (a.get(i), b.get(i)) match {
      case (`none`, `none`)   => 0
      case (`none`, _)        => -1
      case (_, `none`)        => 1
      case (v1: Any, v2: Any) => compareMapValues(v1, v2)
    }
  }

  protected def sortOneOptFlatString(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(a, b, i, (v1, v2) =>
                v1.asInstanceOf[String].compareTo(v2.asInstanceOf[String]))
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(b, a, i, (v1, v2) =>
                v1.asInstanceOf[String].compareTo(v2.asInstanceOf[String]))
        }
      )
    }
  }

  protected def sortOneOptFlatInt(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              // Datomic can return both Integer or Long
              compare(a, b, i, (v1, v2) => v1.toString.toInt.compareTo(v2.toString.toInt))
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(b, a, i, (v1, v2) => v1.toString.toInt.compareTo(v2.toString.toInt))
        }
      )
    }
  }

  protected def sortOneOptFlatLong(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(a, b, i, (v1, v2) => v1.asInstanceOf[jLong].compareTo(v2.asInstanceOf[jLong]))
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(b, a, i, (v1, v2) => v1.asInstanceOf[jLong].compareTo(v2.asInstanceOf[jLong]))
        }
      )
    }
  }

  protected def sortOneOptFlatFloat(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(a, b, i, (v1, v2) => v1.asInstanceOf[jFloat].compareTo(v2.asInstanceOf[jFloat]))
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(b, a, i, (v1, v2) => v1.asInstanceOf[jFloat].compareTo(v2.asInstanceOf[jFloat]))
        }
      )
    }
  }

  protected def sortOneOptFlatDouble(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(a, b, i, (v1, v2) => v1.asInstanceOf[jDouble].compareTo(v2.asInstanceOf[jDouble]))
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(b, a, i, (v1, v2) => v1.asInstanceOf[jDouble].compareTo(v2.asInstanceOf[jDouble]))
        }
      )
    }
  }

  protected def sortOneOptFlatBoolean(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(a, b, i, (v1, v2) => v1.asInstanceOf[jBoolean].compareTo(v2.asInstanceOf[jBoolean]))
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(b, a, i, (v1, v2) => v1.asInstanceOf[jBoolean].compareTo(v2.asInstanceOf[jBoolean]))
        }
      )
    }
  }

  protected def sortOneOptFlatBigInt(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(a, b, i, (v1, v2) => v1.asInstanceOf[jBigInt].compareTo(v2.asInstanceOf[jBigInt]))
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(b, a, i, (v1, v2) => v1.asInstanceOf[jBigInt].compareTo(v2.asInstanceOf[jBigInt]))
        }
      )
    }
  }

  protected def sortOneOptFlatBigDecimal(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(a, b, i, (v1, v2) => v1.asInstanceOf[jBigDecimal].compareTo(v2.asInstanceOf[jBigDecimal]))
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(b, a, i, (v1, v2) => v1.asInstanceOf[jBigDecimal].compareTo(v2.asInstanceOf[jBigDecimal]))
        }
      )
    }
  }

  protected def sortOneOptFlatDate(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(a, b, i, (v1, v2) => v1.asInstanceOf[Date].compareTo(v2.asInstanceOf[Date]))
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(b, a, i, (v1, v2) => v1.asInstanceOf[Date].compareTo(v2.asInstanceOf[Date]))
        }
      )
    }
  }

  protected def sortOneOptFlatUUID(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(a, b, i, (v1, v2) => v1.asInstanceOf[UUID].compareTo(v2.asInstanceOf[UUID]))
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(b, a, i, (v1, v2) => v1.asInstanceOf[UUID].compareTo(v2.asInstanceOf[UUID]))
        }
      )
    }
  }

  protected def sortOneOptFlatURI(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(a, b, i, (v1, v2) => v1.asInstanceOf[URI].compareTo(v2.asInstanceOf[URI]))
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(b, a, i, (v1, v2) => v1.asInstanceOf[URI].compareTo(v2.asInstanceOf[URI]))
        }
      )
    }
  }

  protected def sortOneOptFlatByte(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(a, b, i, (v1, v2) => v1.asInstanceOf[jInteger].compareTo(v2.asInstanceOf[jInteger]))
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(b, a, i, (v1, v2) => v1.asInstanceOf[jInteger].compareTo(v2.asInstanceOf[jInteger]))
        }
      )
    }
  }

  protected def sortOneOptFlatShort(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(a, b, i, (v1, v2) => v1.asInstanceOf[jInteger].compareTo(v2.asInstanceOf[jInteger]))
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(b, a, i, (v1, v2) => v1.asInstanceOf[jInteger].compareTo(v2.asInstanceOf[jInteger]))
        }
      )
    }
  }

  protected def sortOneOptFlatChar(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(a, b, i, (v1, v2) => v1.asInstanceOf[String].compareTo(v2.asInstanceOf[String]))
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              compare(b, a, i, (v1, v2) => v1.asInstanceOf[String].compareTo(v2.asInstanceOf[String]))
        }
      )
    }
  }
}