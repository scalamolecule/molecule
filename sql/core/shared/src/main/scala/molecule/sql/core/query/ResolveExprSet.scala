package molecule.sql.core.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.util.AggrUtils
import scala.reflect.ClassTag

trait ResolveExprSet[Tpl] extends AggrUtils { self: Model2SqlQuery[Tpl] with LambdasSet =>

  protected def resolveAttrSetMan(attr: AttrSetMan): Unit = {
    aritiesAttr()
    attr match {
      case at: AttrSetManString     => man(attr, "String", at.vs, resSetString)
      case at: AttrSetManInt        => man(attr, "Int", at.vs, resSetInt)
      case at: AttrSetManLong       => man(attr, "Long", at.vs, resSetLong)
      case at: AttrSetManFloat      => man(attr, "Float", at.vs, resSetFloat)
      case at: AttrSetManDouble     => man(attr, "Double", at.vs, resSetDouble)
      case at: AttrSetManBoolean    => man(attr, "Boolean", at.vs, resSetBoolean)
      case at: AttrSetManBigInt     => man(attr, "BigInt", at.vs, resSetBigInt)
      case at: AttrSetManBigDecimal => man(attr, "BigDecimal", at.vs, resSetBigDecimal)
      case at: AttrSetManDate       => man(attr, "Date", at.vs, resSetDate)
      case at: AttrSetManUUID       => man(attr, "UUID", at.vs, resSetUUID)
      case at: AttrSetManURI        => man(attr, "URI", at.vs, resSetURI)
      case at: AttrSetManByte       => man(attr, "Byte", at.vs, resSetByte)
      case at: AttrSetManShort      => man(attr, "Short", at.vs, resSetShort)
      case at: AttrSetManChar       => man(attr, "Char", at.vs, resSetChar)
    }
  }

  protected def resolveAttrSetTac(attr: AttrSetTac): Unit = {
    attr match {
      case at: AttrSetTacString     => tac(attr, "String",  at.vs, resSetString)
      case at: AttrSetTacInt        => tac(attr, "Int",  at.vs, resSetInt)
      case at: AttrSetTacLong       => tac(attr, "Long",  at.vs, resSetLong)
      case at: AttrSetTacFloat      => tac(attr, "Float",  at.vs, resSetFloat)
      case at: AttrSetTacDouble     => tac(attr, "Double",  at.vs, resSetDouble)
      case at: AttrSetTacBoolean    => tac(attr, "Boolean",  at.vs, resSetBoolean)
      case at: AttrSetTacBigInt     => tac(attr, "BigInt",  at.vs, resSetBigInt)
      case at: AttrSetTacBigDecimal => tac(attr, "BigDecimal",  at.vs, resSetBigDecimal)
      case at: AttrSetTacDate       => tac(attr, "Date",  at.vs, resSetDate)
      case at: AttrSetTacUUID       => tac(attr, "UUID",  at.vs, resSetUUID)
      case at: AttrSetTacURI        => tac(attr, "URI",  at.vs, resSetURI)
      case at: AttrSetTacByte       => tac(attr, "Byte",  at.vs, resSetByte)
      case at: AttrSetTacShort      => tac(attr, "Short",  at.vs, resSetShort)
      case at: AttrSetTacChar       => tac(attr, "Char",  at.vs, resSetChar)
    }
  }

  protected def resolveAttrSetOpt(attr: AttrSetOpt): Unit = {
    aritiesAttr()
    hasOptAttr = true // to avoid redundant None's
    attr match {
      case at: AttrSetOptString     => opt(at, at.vs, resOptSetString)
      case at: AttrSetOptInt        => opt(at, at.vs, resOptSetInt)
      case at: AttrSetOptLong       => opt(at, at.vs, resOptSetLong)
      case at: AttrSetOptFloat      => opt(at, at.vs, resOptSetFloat)
      case at: AttrSetOptDouble     => opt(at, at.vs, resOptSetDouble)
      case at: AttrSetOptBoolean    => opt(at, at.vs, resOptSetBoolean)
      case at: AttrSetOptBigInt     => opt(at, at.vs, resOptSetBigInt)
      case at: AttrSetOptBigDecimal => opt(at, at.vs, resOptSetBigDecimal)
      case at: AttrSetOptDate       => opt(at, at.vs, resOptSetDate)
      case at: AttrSetOptUUID       => opt(at, at.vs, resOptSetUUID)
      case at: AttrSetOptURI        => opt(at, at.vs, resOptSetURI)
      case at: AttrSetOptByte       => opt(at, at.vs, resOptSetByte)
      case at: AttrSetOptShort      => opt(at, at.vs, resOptSetShort)
      case at: AttrSetOptChar       => opt(at, at.vs, resOptSetChar)
    }
  }


  private def man[T: ClassTag](attr: Attr, tpe: String, args: Seq[Set[T]], res: ResSet[T]): Unit = {
    val col = getCol(attr: Attr)
    select += col
    if (isNestedOpt) {
      addCast(res.sql2setOrNull)
    } else {
      addCast(res.sql2set)
      notNull += col
    }

    attr.filterAttr.fold {
      if (filterAttrVars.contains(attr.name) && attr.op != V) {
        // Runtime check needed since we can't type infer it
        throw ModelError(s"Cardinality-set filter attributes not allowed to do additional filtering. Found:\n  " + attr)
      }
      expr(col, attr.op, args, res, "man")
    } {
      case filterAttr: AttrOne => expr2(col, attr.op, filterAttr.name, true)
      case filterAttr          => expr2(col, attr.op, filterAttr.name, false, tpe)
    }
  }

  private def tac[T: ClassTag](attr: Attr, tpe: String, args: Seq[Set[T]], res: ResSet[T]): Unit = {
    val col = getCol(attr: Attr)
    attr.filterAttr.fold {
      expr(col, attr.op, args, res, "tac")
    } {
      case filterAttr: AttrOne => expr2(col, attr.op, filterAttr.name, true)
      case filterAttr          => expr2(col, attr.op, filterAttr.name, false, tpe)
    }
    notNull += col
  }


  private def opt[T: ClassTag](attr: Attr, optSets: Option[Seq[Set[T]]], resOpt: ResSetOpt[T]): Unit = {
    val col = getCol(attr: Attr)
    select += col
    addCast(resOpt.sql2setOpt)
    attr.op match {
      case V     => ()
      case Eq    => optEqual(col, optSets, resOpt.set2sqls)
      case Neq   => optNeq(col, optSets, resOpt.set2sqls)
      case Has   => optHas(col, optSets, resOpt.one2sql)
      case HasNo => optHasNo(col, optSets, resOpt.one2sql)
      case other => unexpectedOp(other)
    }
  }

  private def expr[T: ClassTag](col: String, op: Op, sets: Seq[Set[T]], res: ResSet[T], mode: String): Unit = {
    op match {
      case V         => if (mode == "man") attr(col, res, mode) else ()
      case Eq        => equal(col, sets, res.set2sqls)
      case Neq       => neq(col, sets, res.set2sqls)
      case Has       => has(col, sets, res.one2sql)
      case HasNo     => hasNo(col, sets, res.one2sql)
      case NoValue   => noValue(col)
      case Fn(kw, n) => aggr(col, kw, n, res)
      case other     => unexpectedOp(other)
    }
  }

  private def expr2(col: String, op: Op, filterAttr: String, cardOne: Boolean, tpe: String = ""): Unit = {
    op match {
      case Eq    => equal2(col, filterAttr)
      case Neq   => neq2(col, filterAttr)
      case Has   => has2(col, filterAttr, cardOne, tpe)
      case HasNo => hasNo2(col, filterAttr, cardOne, tpe)
      case other => unexpectedOp(other)
    }
  }


  private def attr[T](col: String, res: ResSet[T], mode: String): Unit = {
    select -= col
    select += s"ARRAY_AGG($col)"
    having += "COUNT(*) > 0"
    aggregate = true
    replaceCast(res.nestedArray2coalescedSet)
  }

  private def aggr[T](col: String, fn: String, optN: Option[Int], res: ResSet[T]): Unit = {
    select -= col
    lazy val n = optN.getOrElse(0)
    fn match {
      case "distinct" =>
        select += s"ARRAY_AGG(DISTINCT $col)"
        groupByCols -= col
        aggregate = true
        replaceCast(res.nestedArray2nestedSet)

      case "mins" =>
        select += s"ARRAY_AGG(DISTINCT $col)"
        groupByCols -= col
        aggregate = true
        replaceCast(res.nestedArray2setAsc(n))

      case "min" =>
        select += s"MIN($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(res.array2setFirst)

      case "maxs" =>
        select += s"ARRAY_AGG(DISTINCT $col)"
        groupByCols -= col
        aggregate = true
        replaceCast(res.nestedArray2setDesc(n))

      case "max" =>
        select += s"MAX($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(res.array2setLast)

      case "rands" =>
        select +=
          s"""ARRAY_SLICE(
             |    ARRAY_AGG($col order by RAND()),
             |    1,
             |    LEAST(
             |      $n,
             |      ARRAY_LENGTH(ARRAY_AGG($col))
             |    )
             |  )""".stripMargin
        groupByCols -= col
        aggregate = true
        replaceCast(res.nestedArray2coalescedSet)

      case "rand" =>
        distinct = false
        select += col
        orderBy += ((level, -1, "RAND()", ""))
        hardLimit = 1
        replaceCast(res.nestedArray2coalescedSet)


      case "samples" =>
        select +=
          s"""ARRAY_SLICE(
             |    ARRAY_AGG(DISTINCT $col order by RAND()),
             |    1,
             |    LEAST(
             |      $n,
             |      ARRAY_LENGTH(ARRAY_AGG(DISTINCT $col))
             |    )
             |  )""".stripMargin
        groupByCols -= col
        aggregate = true
        replaceCast(res.nestedArray2coalescedSet)

      case "sample" =>
        distinct = false
        select += col
        orderBy += ((level, -1, "RAND()", ""))
        hardLimit = 1
        replaceCast(res.nestedArray2coalescedSet)

      case "count" =>
        // Count of all (non-unique) values
        select += s"ARRAY_AGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(
          (row: Row, n: Int) => {
            val outerArrayResultSet = row.getArray(n).getResultSet
            var count               = 0
            while (outerArrayResultSet.next()) {
              count += outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]].length
            }
            count
          }
        )

      case "countDistinct" =>
        // Count of unique values (Set semantics)
        select += s"ARRAY_AGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(
          (row: Row, n: Int) => {
            val outerArrayResultSet = row.getArray(n).getResultSet
            var set                 = Set.empty[Any]
            while (outerArrayResultSet.next()) {
              outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]].foreach { value =>
                set += value
              }
            }
            set.size
          }
        )

      case "sum" =>
        // Sum of unique values (Set semantics)
        select += s"ARRAY_AGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(res.array2setSum)

      case "median" =>
        // Using brute force and collecting all unique values to calculate the median value
        // Median of unique values (Set semantics)
        select += s"ARRAY_AGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(
          (row: Row, n: Int) => {
            val outerArrayResultSet = row.getArray(n).getResultSet
            var set                 = Set.empty[Double]
            while (outerArrayResultSet.next()) {
              val array = outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]]
              array.foreach(v => set += v.toString.toDouble) // not the most efficient...
            }
            getMedian(set)
          }
        )
      // select += s"MEDIAN(ALL $col)" // other semantics

      case "avg" =>
        // Average of unique values (Set semantics)
        select += s"ARRAY_AGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(
          (row: Row, n: Int) => {
            val outerArrayResultSet = row.getArray(n).getResultSet
            var set                 = Set.empty[Double]
            while (outerArrayResultSet.next()) {
              val array = outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]]
              array.foreach(v => set += v.toString.toDouble) // not the most efficient...
            }
            set.sum / set.size
          }
        )
      // select += s"AVG(DISTINCT $col)" // other semantics

      case "variance" =>
        // Variance of unique values (Set semantics)
        select += s"ARRAY_AGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(
          (row: Row, n: Int) => {
            val outerArrayResultSet = row.getArray(n).getResultSet
            var set                 = Set.empty[Double]
            while (outerArrayResultSet.next()) {
              val array = outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]]
              array.foreach(v => set += v.toString.toDouble) // not the most efficient...
            }
            varianceOf(set.toList: _*)
          }
        )
      // select += s"VAR_POP($col)" // other semantics
      // select += s"VAR_SAMP($col)" // other semantics

      case "stddev" =>
        // Standard deviation of unique values (Set semantics)
        select += s"ARRAY_AGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(
          (row: Row, n: Int) => {
            val outerArrayResultSet = row.getArray(n).getResultSet
            var set                 = Set.empty[Double]
            while (outerArrayResultSet.next()) {
              val array = outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]]
              array.foreach(v => set += v.toString.toDouble) // not the most efficient...
            }
            stdDevOf(set.toList: _*)
          }
        )
      // select += s"STDDEV($col)" // other semantics

      case other => unexpectedKw(other)
    }
  }

  private def matchSet(set: Set[String], col: String): String = {
    set
      .map(v => s"ARRAY_CONTAINS($col, $v)")
      .mkString("(\n    ", " AND\n    ", s" AND\n    CARDINALITY($col) = ${set.size}\n  )")
  }

  private def matchSets[T](sets: Seq[Set[T]], col: String, set2sqls: Set[T] => Set[String]): String = {
    sets.map { set =>
      set2sqls(set)
        .map(v => s"ARRAY_CONTAINS($col, $v)")
        .mkString("", " AND\n      ", s" AND\n      CARDINALITY($col) = " + set.size)
    }.mkString("(\n    (\n      ", "\n    ) OR (\n      ", "\n    )\n  )")
  }

  private def equal[T](col: String, sets: Seq[Set[T]], set2sqls: Set[T] => Set[String]): Unit = {
    val setsNonEmpty = sets.filterNot(_.isEmpty)
    setsNonEmpty.length match {
      case 0 => where += (("FALSE", ""))
      case 1 => where += (("", matchSet(set2sqls(setsNonEmpty.head), col)))
      case _ => where += (("", matchSets(setsNonEmpty, col, set2sqls)))
    }
  }

  private def equal2(col: String, filterAttr: String): Unit = {
    where += ((col, "= " + filterAttr))
  }

  private def optEqual[T](col: String, optSets: Option[Seq[Set[T]]], set2sqls: Set[T] => Set[String]): Unit = {
    optSets.fold[Unit] {
      where += ((col, s"IS NULL"))
    } { sets =>
      equal(col, sets, set2sqls)
    }
  }

  private def neq[T](col: String, sets: Seq[Set[T]], set2sqls: Set[T] => Set[String]): Unit = {
    val setsNonEmpty = sets.filterNot(_.isEmpty)
    setsNonEmpty.length match {
      case 0 => ()
      case 1 => where += (("", "NOT " + matchSet(set2sqls(setsNonEmpty.head), col)))
      case _ => where += (("", "NOT " + matchSets(setsNonEmpty, col, set2sqls)))
    }
  }

  private def neq2(col: String, filterAttr: String): Unit = {
    where += ((col, "<> " + filterAttr))
  }

  private def optNeq[T](col: String, optSets: Option[Seq[Set[T]]], set2sqls: Set[T] => Set[String]): Unit = {
    if (optSets.isDefined && optSets.get.nonEmpty) {
      neq(col, optSets.get, set2sqls)
    }
    notNull += col
  }

  private def has[T: ClassTag](col: String, sets: Seq[Set[T]], one2sql: T => String): Unit = {
    def contains(v: T): String = s"ARRAY_CONTAINS($col, ${one2sql(v)})"
    def containsSet(set: Set[T]): String = set.map(contains).mkString("(", " AND\n   ", ")")
    sets.length match {
      case 0 => where += (("FALSE", ""))
      case 1 =>
        val set = sets.head
        set.size match {
          case 0 => where += (("FALSE", ""))
          case 1 => where += (("", contains(set.head)))
          case _ => where += (("", containsSet(set)))
        }
      case _ =>
        val expr = sets
          .filterNot(_.isEmpty)
          .map(containsSet).mkString("(", " OR\n   ", ")")
        where += (("", expr))
    }
  }

  private def has2(col: String, filterAttr: String, cardOne: Boolean, tpe: String): Unit = {
    if (cardOne) {
      where += (("", s"ARRAY_CONTAINS($col, $filterAttr)"))
    } else {
      where += (("", s"has_$tpe($col, $filterAttr)"))
    }
  }

  private def optHas[T: ClassTag](
    col: String,
    optSets: Option[Seq[Set[T]]],
    one2sql: T => String
  ): Unit = {
    optSets.fold[Unit] {
      where += ((col, s"IS NULL"))
    } { sets =>
      has(col, sets, one2sql)
    }
  }

  private def hasNo[T](col: String, sets: Seq[Set[T]], one2sql: T => String): Unit = {
    def notContains(v: T): String = s"NOT ARRAY_CONTAINS($col, ${one2sql(v)})"
    def notContainsSet(set: Set[T]): String = set.map(notContains).mkString("(", " OR\n   ", ")")
    sets.length match {
      case 0 => ()
      case 1 =>
        val set = sets.head
        set.size match {
          case 0 => ()
          case 1 => where += (("", notContains(set.head)))
          case _ => where += (("", notContainsSet(set)))
        }
      case _ =>
        val expr = sets
          .filterNot(_.isEmpty)
          .map(notContainsSet).mkString("(", " AND\n   ", ")")
        where += (("", expr))
    }
  }

  private def hasNo2(col: String, filterAttr: String, cardOne: Boolean, tpe: String): Unit = {
    if (cardOne) {
      where += (("", s"NOT ARRAY_CONTAINS($col, $filterAttr)"))
    } else {
      where += (("", s"hasNo_$tpe($col, $filterAttr)"))
    }
  }

  private def optHasNo[T](
    col: String,
    optSets: Option[Seq[Set[T]]],
    one2sql: T => String
  ): Unit = {
    optSets.fold[Unit] {
      where += ((col, s"IS NOT NULL"))
    } { sets =>
      val setsWithValues = sets.filterNot(_.isEmpty)
      if (setsWithValues.nonEmpty) {
        hasNo(col, sets.filterNot(_.isEmpty), one2sql)
      } else {
        where += ((col, s"IS NOT NULL"))
      }
    }
  }

  private def noValue(col: String): Unit = {
    notNull -= col
    where += ((col, s"IS NULL"))
  }
}