// GENERATED CODE ********************************
package molecule.db.datomic.query

import java.lang.{Boolean => jBoolean, Double => jDouble, Integer => jInteger, Long => jLong}
import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
import java.net.URI
import java.util.{Date, UUID}
import molecule.boilerplate.ast.MoleculeModel._


trait Sort_[Tpl] { self: Base[Tpl] =>

  protected def sortString(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            //println(s"$nestedIdsCount SORT INDEX asc  (${attr.attr}): " + i)
            (a: Row, b: Row) =>
              a.get(i).asInstanceOf[String].compareTo(b.get(i).asInstanceOf[String])
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            //println(s"$nestedIdsCount SORT INDEX desc (${attr.attr}): " + i)
            (a: Row, b: Row) =>
              b.get(i).asInstanceOf[String].compareTo(a.get(i).asInstanceOf[String])
        }
      )
    }
  }

  protected def sortInt(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            //println(s"$nestedIdsCount SORT INDEX asc  (${attr.attr}): " + i)
            (a: Row, b: Row) =>
              a.get(i).asInstanceOf[jInteger].compareTo(b.get(i).asInstanceOf[jInteger])
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            //println(s"$nestedIdsCount SORT INDEX desc (${attr.attr}): " + i)
            (a: Row, b: Row) =>
              b.get(i).asInstanceOf[jInteger].compareTo(a.get(i).asInstanceOf[jInteger])
        }
      )
    }
  }

  protected def sortLong(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            //println(s"$nestedIdsCount SORT INDEX asc  (${attr.attr}): " + i)
            (a: Row, b: Row) =>
              a.get(i).asInstanceOf[jLong].compareTo(b.get(i).asInstanceOf[jLong])
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            //println(s"$nestedIdsCount SORT INDEX desc (${attr.attr}): " + i)
            (a: Row, b: Row) =>
              b.get(i).asInstanceOf[jLong].compareTo(a.get(i).asInstanceOf[jLong])
        }
      )
    }
  }

  protected def sortFloat(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            //println(s"$nestedIdsCount SORT INDEX asc  (${attr.attr}): " + i)
            (a: Row, b: Row) =>
              a.get(i).asInstanceOf[jDouble].compareTo(b.get(i).asInstanceOf[jDouble])
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            //println(s"$nestedIdsCount SORT INDEX desc (${attr.attr}): " + i)
            (a: Row, b: Row) =>
              b.get(i).asInstanceOf[jDouble].compareTo(a.get(i).asInstanceOf[jDouble])
        }
      )
    }
  }

  protected def sortDouble(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            //println(s"$nestedIdsCount SORT INDEX asc  (${attr.attr}): " + i)
            (a: Row, b: Row) =>
              a.get(i).asInstanceOf[jDouble].compareTo(b.get(i).asInstanceOf[jDouble])
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            //println(s"$nestedIdsCount SORT INDEX desc (${attr.attr}): " + i)
            (a: Row, b: Row) =>
              b.get(i).asInstanceOf[jDouble].compareTo(a.get(i).asInstanceOf[jDouble])
        }
      )
    }
  }

  protected def sortBoolean(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            //println(s"$nestedIdsCount SORT INDEX asc  (${attr.attr}): " + i)
            (a: Row, b: Row) =>
              a.get(i).asInstanceOf[jBoolean].compareTo(b.get(i).asInstanceOf[jBoolean])
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            //println(s"$nestedIdsCount SORT INDEX desc (${attr.attr}): " + i)
            (a: Row, b: Row) =>
              b.get(i).asInstanceOf[jBoolean].compareTo(a.get(i).asInstanceOf[jBoolean])
        }
      )
    }
  }

  protected def sortBigInt(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            //println(s"$nestedIdsCount SORT INDEX asc  (${attr.attr}): " + i)
            (a: Row, b: Row) =>
              a.get(i).asInstanceOf[jBigInt].compareTo(b.get(i).asInstanceOf[jBigInt])
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            //println(s"$nestedIdsCount SORT INDEX desc (${attr.attr}): " + i)
            (a: Row, b: Row) =>
              b.get(i).asInstanceOf[jBigInt].compareTo(a.get(i).asInstanceOf[jBigInt])
        }
      )
    }
  }

  protected def sortBigDecimal(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            //println(s"$nestedIdsCount SORT INDEX asc  (${attr.attr}): " + i)
            (a: Row, b: Row) =>
              a.get(i).asInstanceOf[jBigDecimal].compareTo(b.get(i).asInstanceOf[jBigDecimal])
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            //println(s"$nestedIdsCount SORT INDEX desc (${attr.attr}): " + i)
            (a: Row, b: Row) =>
              b.get(i).asInstanceOf[jBigDecimal].compareTo(a.get(i).asInstanceOf[jBigDecimal])
        }
      )
    }
  }

  protected def sortDate(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            //println(s"$nestedIdsCount SORT INDEX asc  (${attr.attr}): " + i)
            (a: Row, b: Row) =>
              a.get(i).asInstanceOf[Date].compareTo(b.get(i).asInstanceOf[Date])
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            //println(s"$nestedIdsCount SORT INDEX desc (${attr.attr}): " + i)
            (a: Row, b: Row) =>
              b.get(i).asInstanceOf[Date].compareTo(a.get(i).asInstanceOf[Date])
        }
      )
    }
  }

  protected def sortUUID(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            //println(s"$nestedIdsCount SORT INDEX asc  (${attr.attr}): " + i)
            (a: Row, b: Row) =>
              a.get(i).asInstanceOf[UUID].compareTo(b.get(i).asInstanceOf[UUID])
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            //println(s"$nestedIdsCount SORT INDEX desc (${attr.attr}): " + i)
            (a: Row, b: Row) =>
              b.get(i).asInstanceOf[UUID].compareTo(a.get(i).asInstanceOf[UUID])
        }
      )
    }
  }

  protected def sortURI(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            //println(s"$nestedIdsCount SORT INDEX asc  (${attr.attr}): " + i)
            (a: Row, b: Row) =>
              a.get(i).asInstanceOf[URI].compareTo(b.get(i).asInstanceOf[URI])
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            //println(s"$nestedIdsCount SORT INDEX desc (${attr.attr}): " + i)
            (a: Row, b: Row) =>
              b.get(i).asInstanceOf[URI].compareTo(a.get(i).asInstanceOf[URI])
        }
      )
    }
  }

  protected def sortByte(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            //println(s"$nestedIdsCount SORT INDEX asc  (${attr.attr}): " + i)
            (a: Row, b: Row) =>
              a.get(i).asInstanceOf[jInteger].compareTo(b.get(i).asInstanceOf[jInteger])
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            //println(s"$nestedIdsCount SORT INDEX desc (${attr.attr}): " + i)
            (a: Row, b: Row) =>
              b.get(i).asInstanceOf[jInteger].compareTo(a.get(i).asInstanceOf[jInteger])
        }
      )
    }
  }

  protected def sortShort(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            //println(s"$nestedIdsCount SORT INDEX asc  (${attr.attr}): " + i)
            (a: Row, b: Row) =>
              a.get(i).asInstanceOf[jInteger].compareTo(b.get(i).asInstanceOf[jInteger])
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            //println(s"$nestedIdsCount SORT INDEX desc (${attr.attr}): " + i)
            (a: Row, b: Row) =>
              b.get(i).asInstanceOf[jInteger].compareTo(a.get(i).asInstanceOf[jInteger])
        }
      )
    }
  }

  protected def sortChar(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            //println(s"$nestedIdsCount SORT INDEX asc  (${attr.attr}): " + i)
            (a: Row, b: Row) =>
              a.get(i).asInstanceOf[String].compareTo(b.get(i).asInstanceOf[String])
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            //println(s"$nestedIdsCount SORT INDEX desc (${attr.attr}): " + i)
            (a: Row, b: Row) =>
              b.get(i).asInstanceOf[String].compareTo(a.get(i).asInstanceOf[String])
        }
      )
    }
  }
}