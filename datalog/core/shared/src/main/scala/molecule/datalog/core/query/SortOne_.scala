// GENERATED CODE ********************************
package molecule.datalog.core.query

import java.lang.{Boolean => jBoolean, Double => jDouble, Float => jFloat, Integer => jInteger, Long => jLong}
import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
import java.net.URI
import java.util.{Date, UUID}
import molecule.boilerplate.ast.Model._


trait SortOne_[Tpl] { self: Model2DatomicQuery[Tpl] =>

  protected def sortOneString(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              a.get(i).asInstanceOf[String].compareTo(b.get(i).asInstanceOf[String])
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              b.get(i).asInstanceOf[String].compareTo(a.get(i).asInstanceOf[String])
        }
      )
    }
  }

  protected def sortOneInt(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              a.get(i).toString.toInt.compareTo(b.get(i).toString.toInt)
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              b.get(i).toString.toInt.compareTo(a.get(i).toString.toInt)
        }
      )
    }
  }

  protected def sortOneLong(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              a.get(i).asInstanceOf[jLong].compareTo(b.get(i).asInstanceOf[jLong])
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              b.get(i).asInstanceOf[jLong].compareTo(a.get(i).asInstanceOf[jLong])
        }
      )
    }
  }

  protected def sortOneFloat(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              a.get(i).asInstanceOf[jFloat].compareTo(b.get(i).asInstanceOf[jFloat])
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              b.get(i).asInstanceOf[jFloat].compareTo(a.get(i).asInstanceOf[jFloat])
        }
      )
    }
  }

  protected def sortOneDouble(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              a.get(i).asInstanceOf[jDouble].compareTo(b.get(i).asInstanceOf[jDouble])
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              b.get(i).asInstanceOf[jDouble].compareTo(a.get(i).asInstanceOf[jDouble])
        }
      )
    }
  }

  protected def sortOneBoolean(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              a.get(i).asInstanceOf[jBoolean].compareTo(b.get(i).asInstanceOf[jBoolean])
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              b.get(i).asInstanceOf[jBoolean].compareTo(a.get(i).asInstanceOf[jBoolean])
        }
      )
    }
  }

  protected def sortOneBigInt(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              a.get(i).asInstanceOf[jBigInt].compareTo(b.get(i).asInstanceOf[jBigInt])
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              b.get(i).asInstanceOf[jBigInt].compareTo(a.get(i).asInstanceOf[jBigInt])
        }
      )
    }
  }

  protected def sortOneBigDecimal(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              a.get(i).asInstanceOf[jBigDecimal].compareTo(b.get(i).asInstanceOf[jBigDecimal])
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              b.get(i).asInstanceOf[jBigDecimal].compareTo(a.get(i).asInstanceOf[jBigDecimal])
        }
      )
    }
  }

  protected def sortOneDate(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              a.get(i).asInstanceOf[Date].compareTo(b.get(i).asInstanceOf[Date])
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              b.get(i).asInstanceOf[Date].compareTo(a.get(i).asInstanceOf[Date])
        }
      )
    }
  }

  protected def sortOneUUID(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              a.get(i).asInstanceOf[UUID].compareTo(b.get(i).asInstanceOf[UUID])
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              b.get(i).asInstanceOf[UUID].compareTo(a.get(i).asInstanceOf[UUID])
        }
      )
    }
  }

  protected def sortOneURI(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              a.get(i).asInstanceOf[URI].compareTo(b.get(i).asInstanceOf[URI])
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              b.get(i).asInstanceOf[URI].compareTo(a.get(i).asInstanceOf[URI])
        }
      )
    }
  }

  protected def sortOneByte(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              a.get(i).asInstanceOf[jInteger].compareTo(b.get(i).asInstanceOf[jInteger])
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              b.get(i).asInstanceOf[jInteger].compareTo(a.get(i).asInstanceOf[jInteger])
        }
      )
    }
  }

  protected def sortOneShort(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              a.get(i).asInstanceOf[jInteger].compareTo(b.get(i).asInstanceOf[jInteger])
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              b.get(i).asInstanceOf[jInteger].compareTo(a.get(i).asInstanceOf[jInteger])
        }
      )
    }
  }

  protected def sortOneChar(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              a.get(i).asInstanceOf[String].compareTo(b.get(i).asInstanceOf[String])
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              b.get(i).asInstanceOf[String].compareTo(a.get(i).asInstanceOf[String])
        }
      )
    }
  }
}