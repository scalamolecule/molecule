// GENERATED CODE ********************************
package molecule.db.datalog.core.query

import java.lang.{Boolean as jBoolean, Double as jDouble, Float as jFloat, Integer as jInteger, Long as jLong}
import java.math.{BigDecimal as jBigDecimal, BigInteger as jBigInt}
import java.net.URI
import java.time.*
import java.util.{Date, UUID}
import molecule.core.ast.DataModel.*


trait SortOne_[Tpl] { self: Model2DatomicQuery[Tpl] =>

  protected def sortOneID(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
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

  protected def sortOneDuration(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              Duration.parse(a.get(i).toString).compareTo(Duration.parse(b.get(i).toString))
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              Duration.parse(b.get(i).toString).compareTo(Duration.parse(a.get(i).toString))
        }
      )
    }
  }

  protected def sortOneInstant(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              Instant.parse(a.get(i).toString).compareTo(Instant.parse(b.get(i).toString))
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              Instant.parse(b.get(i).toString).compareTo(Instant.parse(a.get(i).toString))
        }
      )
    }
  }

  protected def sortOneLocalDate(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              LocalDate.parse(a.get(i).toString).compareTo(LocalDate.parse(b.get(i).toString))
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              LocalDate.parse(b.get(i).toString).compareTo(LocalDate.parse(a.get(i).toString))
        }
      )
    }
  }

  protected def sortOneLocalTime(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              LocalTime.parse(a.get(i).toString).compareTo(LocalTime.parse(b.get(i).toString))
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              LocalTime.parse(b.get(i).toString).compareTo(LocalTime.parse(a.get(i).toString))
        }
      )
    }
  }

  protected def sortOneLocalDateTime(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              LocalDateTime.parse(a.get(i).toString).compareTo(LocalDateTime.parse(b.get(i).toString))
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              LocalDateTime.parse(b.get(i).toString).compareTo(LocalDateTime.parse(a.get(i).toString))
        }
      )
    }
  }

  protected def sortOneOffsetTime(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              OffsetTime.parse(a.get(i).toString).compareTo(OffsetTime.parse(b.get(i).toString))
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              OffsetTime.parse(b.get(i).toString).compareTo(OffsetTime.parse(a.get(i).toString))
        }
      )
    }
  }

  protected def sortOneOffsetDateTime(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              OffsetDateTime.parse(a.get(i).toString).compareTo(OffsetDateTime.parse(b.get(i).toString))
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              OffsetDateTime.parse(b.get(i).toString).compareTo(OffsetDateTime.parse(a.get(i).toString))
        }
      )
    }
  }

  protected def sortOneZonedDateTime(attr: Attr, attrIndex: Int): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        sort.head match {
          case 'a' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              ZonedDateTime.parse(a.get(i).toString).compareTo(ZonedDateTime.parse(b.get(i).toString))
          case 'd' => (nestedIdsCount: Int) =>
            val i = nestedIdsCount + attrIndex
            (a: Row, b: Row) =>
              ZonedDateTime.parse(b.get(i).toString).compareTo(ZonedDateTime.parse(a.get(i).toString))
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