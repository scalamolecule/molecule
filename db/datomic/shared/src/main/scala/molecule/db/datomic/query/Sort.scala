// GENERATED CODE ********************************
package molecule.db.datomic.query

import java.lang.{Boolean => jBoolean, Double => jDouble, Integer => jInteger, Long => jLong}
import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
import java.net.URI
import java.util.{Date, UUID}
import molecule.boilerplate.ast.MoleculeModel._


//trait Sort[Tpl] extends Base[Tpl] {
trait Sort[Tpl] { self: Base[Tpl] =>
  protected def sortString(a: Atom): Option[(Int, (Row, Row) => Int)] = a.sort.map { sort =>
    (
      sort.last.toInt,
      sort.head match {
        case 'a' => (a: Row, b: Row) =>
          a.get(attrIndex).asInstanceOf[String].compareTo(b.get(attrIndex).asInstanceOf[String])
        case 'd' => (a: Row, b: Row) =>
          b.get(attrIndex).asInstanceOf[String].compareTo(a.get(attrIndex).asInstanceOf[String])
      }
    )
  }

  protected def sortInt(a: Atom): Option[(Int, (Row, Row) => Int)] = a.sort.map { sort =>
    (
      sort.last.toInt,
      sort.head match {
        case 'a' => (a: Row, b: Row) =>
          a.get(attrIndex).asInstanceOf[jInteger].compareTo(b.get(attrIndex).asInstanceOf[jInteger])
        case 'd' => (a: Row, b: Row) =>
          b.get(attrIndex).asInstanceOf[jInteger].compareTo(a.get(attrIndex).asInstanceOf[jInteger])
      }
    )
  }

  protected def sortLong(a: Atom): Option[(Int, (Row, Row) => Int)] = a.sort.map { sort =>
    (
      sort.last.toInt,
      sort.head match {
        case 'a' => (a: Row, b: Row) =>
          a.get(attrIndex).asInstanceOf[jLong].compareTo(b.get(attrIndex).asInstanceOf[jLong])
        case 'd' => (a: Row, b: Row) =>
          b.get(attrIndex).asInstanceOf[jLong].compareTo(a.get(attrIndex).asInstanceOf[jLong])
      }
    )
  }

  protected def sortFloat(a: Atom): Option[(Int, (Row, Row) => Int)] = a.sort.map { sort =>
    (
      sort.last.toInt,
      sort.head match {
        case 'a' => (a: Row, b: Row) =>
          a.get(attrIndex).asInstanceOf[jDouble].compareTo(b.get(attrIndex).asInstanceOf[jDouble])
        case 'd' => (a: Row, b: Row) =>
          b.get(attrIndex).asInstanceOf[jDouble].compareTo(a.get(attrIndex).asInstanceOf[jDouble])
      }
    )
  }

  protected def sortDouble(a: Atom): Option[(Int, (Row, Row) => Int)] = a.sort.map { sort =>
    (
      sort.last.toInt,
      sort.head match {
        case 'a' => (a: Row, b: Row) =>
          a.get(attrIndex).asInstanceOf[jDouble].compareTo(b.get(attrIndex).asInstanceOf[jDouble])
        case 'd' => (a: Row, b: Row) =>
          b.get(attrIndex).asInstanceOf[jDouble].compareTo(a.get(attrIndex).asInstanceOf[jDouble])
      }
    )
  }

  protected def sortBoolean(a: Atom): Option[(Int, (Row, Row) => Int)] = a.sort.map { sort =>
    (
      sort.last.toInt,
      sort.head match {
        case 'a' => (a: Row, b: Row) =>
          a.get(attrIndex).asInstanceOf[jBoolean].compareTo(b.get(attrIndex).asInstanceOf[jBoolean])
        case 'd' => (a: Row, b: Row) =>
          b.get(attrIndex).asInstanceOf[jBoolean].compareTo(a.get(attrIndex).asInstanceOf[jBoolean])
      }
    )
  }

  protected def sortBigInt(a: Atom): Option[(Int, (Row, Row) => Int)] = a.sort.map { sort =>
    (
      sort.last.toInt,
      sort.head match {
        case 'a' => (a: Row, b: Row) =>
          a.get(attrIndex).asInstanceOf[jBigInt].compareTo(b.get(attrIndex).asInstanceOf[jBigInt])
        case 'd' => (a: Row, b: Row) =>
          b.get(attrIndex).asInstanceOf[jBigInt].compareTo(a.get(attrIndex).asInstanceOf[jBigInt])
      }
    )
  }

  protected def sortBigDecimal(a: Atom): Option[(Int, (Row, Row) => Int)] = a.sort.map { sort =>
    (
      sort.last.toInt,
      sort.head match {
        case 'a' => (a: Row, b: Row) =>
          a.get(attrIndex).asInstanceOf[jBigDecimal].compareTo(b.get(attrIndex).asInstanceOf[jBigDecimal])
        case 'd' => (a: Row, b: Row) =>
          b.get(attrIndex).asInstanceOf[jBigDecimal].compareTo(a.get(attrIndex).asInstanceOf[jBigDecimal])
      }
    )
  }

  protected def sortDate(a: Atom): Option[(Int, (Row, Row) => Int)] = a.sort.map { sort =>
    (
      sort.last.toInt,
      sort.head match {
        case 'a' => (a: Row, b: Row) =>
          a.get(attrIndex).asInstanceOf[Date].compareTo(b.get(attrIndex).asInstanceOf[Date])
        case 'd' => (a: Row, b: Row) =>
          b.get(attrIndex).asInstanceOf[Date].compareTo(a.get(attrIndex).asInstanceOf[Date])
      }
    )
  }

  protected def sortUUID(a: Atom): Option[(Int, (Row, Row) => Int)] = a.sort.map { sort =>
    (
      sort.last.toInt,
      sort.head match {
        case 'a' => (a: Row, b: Row) =>
          a.get(attrIndex).asInstanceOf[UUID].compareTo(b.get(attrIndex).asInstanceOf[UUID])
        case 'd' => (a: Row, b: Row) =>
          b.get(attrIndex).asInstanceOf[UUID].compareTo(a.get(attrIndex).asInstanceOf[UUID])
      }
    )
  }

  protected def sortURI(a: Atom): Option[(Int, (Row, Row) => Int)] = a.sort.map { sort =>
    (
      sort.last.toInt,
      sort.head match {
        case 'a' => (a: Row, b: Row) =>
          a.get(attrIndex).asInstanceOf[URI].compareTo(b.get(attrIndex).asInstanceOf[URI])
        case 'd' => (a: Row, b: Row) =>
          b.get(attrIndex).asInstanceOf[URI].compareTo(a.get(attrIndex).asInstanceOf[URI])
      }
    )
  }

  protected def sortByte(a: Atom): Option[(Int, (Row, Row) => Int)] = a.sort.map { sort =>
    (
      sort.last.toInt,
      sort.head match {
        case 'a' => (a: Row, b: Row) =>
          a.get(attrIndex).asInstanceOf[jInteger].compareTo(b.get(attrIndex).asInstanceOf[jInteger])
        case 'd' => (a: Row, b: Row) =>
          b.get(attrIndex).asInstanceOf[jInteger].compareTo(a.get(attrIndex).asInstanceOf[jInteger])
      }
    )
  }

  protected def sortShort(a: Atom): Option[(Int, (Row, Row) => Int)] = a.sort.map { sort =>
    (
      sort.last.toInt,
      sort.head match {
        case 'a' => (a: Row, b: Row) =>
          a.get(attrIndex).asInstanceOf[jInteger].compareTo(b.get(attrIndex).asInstanceOf[jInteger])
        case 'd' => (a: Row, b: Row) =>
          b.get(attrIndex).asInstanceOf[jInteger].compareTo(a.get(attrIndex).asInstanceOf[jInteger])
      }
    )
  }

  protected def sortChar(a: Atom): Option[(Int, (Row, Row) => Int)] = a.sort.map { sort =>
    (
      sort.last.toInt,
      sort.head match {
        case 'a' => (a: Row, b: Row) =>
          a.get(attrIndex).asInstanceOf[String].compareTo(b.get(attrIndex).asInstanceOf[String])
        case 'd' => (a: Row, b: Row) =>
          b.get(attrIndex).asInstanceOf[String].compareTo(a.get(attrIndex).asInstanceOf[String])
      }
    )
  }
}