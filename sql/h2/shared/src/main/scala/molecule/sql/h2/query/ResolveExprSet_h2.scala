package molecule.sql.h2.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.util.AggrUtils
import molecule.sql.core.query.{ResolveExpr, SqlQueryBase}
import scala.reflect.ClassTag

trait ResolveExprSet_h2[Tpl] extends ResolveExpr with LambdasSet_h2 with AggrUtils { self: SqlQueryBase =>

  override protected def resolveAttrSetMan(attr: AttrSetMan): Unit = {
    aritiesAttr()
    attr match {
      case at: AttrSetManString     => setMan(attr, "String", at.vs, resSetString)
      case at: AttrSetManInt        => setMan(attr, "Int", at.vs, resSetInt)
      case at: AttrSetManLong       => setMan(attr, "Long", at.vs, resSetLong)
      case at: AttrSetManFloat      => setMan(attr, "Float", at.vs, resSetFloat)
      case at: AttrSetManDouble     => setMan(attr, "Double", at.vs, resSetDouble)
      case at: AttrSetManBoolean    => setMan(attr, "Boolean", at.vs, resSetBoolean)
      case at: AttrSetManBigInt     => setMan(attr, "BigInt", at.vs, resSetBigInt)
      case at: AttrSetManBigDecimal => setMan(attr, "BigDecimal", at.vs, resSetBigDecimal)
      case at: AttrSetManDate       => setMan(attr, "Date", at.vs, resSetDate)
      case at: AttrSetManUUID       => setMan(attr, "UUID", at.vs, resSetUUID)
      case at: AttrSetManURI        => setMan(attr, "URI", at.vs, resSetURI)
      case at: AttrSetManByte       => setMan(attr, "Byte", at.vs, resSetByte)
      case at: AttrSetManShort      => setMan(attr, "Short", at.vs, resSetShort)
      case at: AttrSetManChar       => setMan(attr, "Char", at.vs, resSetChar)
    }
  }

  override protected def resolveAttrSetTac(attr: AttrSetTac): Unit = {
    attr match {
      case at: AttrSetTacString     => setTac(attr, "String", at.vs, resSetString)
      case at: AttrSetTacInt        => setTac(attr, "Int", at.vs, resSetInt)
      case at: AttrSetTacLong       => setTac(attr, "Long", at.vs, resSetLong)
      case at: AttrSetTacFloat      => setTac(attr, "Float", at.vs, resSetFloat)
      case at: AttrSetTacDouble     => setTac(attr, "Double", at.vs, resSetDouble)
      case at: AttrSetTacBoolean    => setTac(attr, "Boolean", at.vs, resSetBoolean)
      case at: AttrSetTacBigInt     => setTac(attr, "BigInt", at.vs, resSetBigInt)
      case at: AttrSetTacBigDecimal => setTac(attr, "BigDecimal", at.vs, resSetBigDecimal)
      case at: AttrSetTacDate       => setTac(attr, "Date", at.vs, resSetDate)
      case at: AttrSetTacUUID       => setTac(attr, "UUID", at.vs, resSetUUID)
      case at: AttrSetTacURI        => setTac(attr, "URI", at.vs, resSetURI)
      case at: AttrSetTacByte       => setTac(attr, "Byte", at.vs, resSetByte)
      case at: AttrSetTacShort      => setTac(attr, "Short", at.vs, resSetShort)
      case at: AttrSetTacChar       => setTac(attr, "Char", at.vs, resSetChar)
    }
  }

  override protected def resolveAttrSetOpt(attr: AttrSetOpt): Unit = {
    aritiesAttr()
    hasOptAttr = true // to avoid redundant None's
    attr match {
      case at: AttrSetOptString     => setOpt(at, at.vs, resOptSetString, resSetString)
      case at: AttrSetOptInt        => setOpt(at, at.vs, resOptSetInt, resSetInt)
      case at: AttrSetOptLong       => setOpt(at, at.vs, resOptSetLong, resSetLong)
      case at: AttrSetOptFloat      => setOpt(at, at.vs, resOptSetFloat, resSetFloat)
      case at: AttrSetOptDouble     => setOpt(at, at.vs, resOptSetDouble, resSetDouble)
      case at: AttrSetOptBoolean    => setOpt(at, at.vs, resOptSetBoolean, resSetBoolean)
      case at: AttrSetOptBigInt     => setOpt(at, at.vs, resOptSetBigInt, resSetBigInt)
      case at: AttrSetOptBigDecimal => setOpt(at, at.vs, resOptSetBigDecimal, resSetBigDecimal)
      case at: AttrSetOptDate       => setOpt(at, at.vs, resOptSetDate, resSetDate)
      case at: AttrSetOptUUID       => setOpt(at, at.vs, resOptSetUUID, resSetUUID)
      case at: AttrSetOptURI        => setOpt(at, at.vs, resOptSetURI, resSetURI)
      case at: AttrSetOptByte       => setOpt(at, at.vs, resOptSetByte, resSetByte)
      case at: AttrSetOptShort      => setOpt(at, at.vs, resOptSetShort, resSetShort)
      case at: AttrSetOptChar       => setOpt(at, at.vs, resOptSetChar, resSetChar)
    }
  }


  protected def setMan[T: ClassTag](attr: Attr, tpe: String, args: Seq[Set[T]], res: ResSet[T]): Unit = {
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
      setExpr(col, attr.op, args, res, "man")
    } {
      case filterAttr: AttrOne => setExpr2(col, attr.op, filterAttr.name, true)
      case filterAttr          => setExpr2(col, attr.op, filterAttr.name, false, tpe)
    }
  }

  protected def setTac[T: ClassTag](attr: Attr, tpe: String, args: Seq[Set[T]], res: ResSet[T]): Unit = {
    val col = getCol(attr: Attr)
    attr.filterAttr.fold {
      setExpr(col, attr.op, args, res, "tac")
    } {
      case filterAttr: AttrOne => setExpr2(col, attr.op, filterAttr.name, true)
      case filterAttr          => setExpr2(col, attr.op, filterAttr.name, false, tpe)
    }
    notNull += col
  }


  protected def setOpt[T: ClassTag](
    attr: Attr,
    optSets: Option[Seq[Set[T]]],
    resOpt: ResSetOpt[T],
    res: ResSet[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    addCast(resOpt.sql2setOpt)
    attr.op match {
      case V     => ()
      case Eq    => setOptEqual(col, optSets, res)
      case Neq   => setOptNeq(col, optSets, res)
      case Has   => optHas(col, optSets, resOpt.one2sql)
      case HasNo => optHasNo(col, optSets, resOpt.one2sql)
      case other => unexpectedOp(other)
    }
  }

  protected def setExpr[T: ClassTag](col: String, op: Op, sets: Seq[Set[T]], res: ResSet[T], mode: String): Unit = {
    op match {
      case V         => if (mode == "man") setAttr(col, res) else ()
      case Eq        => setEqual(col, sets, res)
      case Neq       => setNeq(col, sets, res)
      case Has       => has(col, sets, res.one2sql)
      case HasNo     => hasNo(col, sets, res.one2sql)
      case NoValue   => setNoValue(col)
      case Fn(kw, n) => setAggr(col, kw, n, res)
      case other     => unexpectedOp(other)
    }
  }

  protected def setExpr2(col: String, op: Op, filterAttr: String, cardOne: Boolean, tpe: String = ""): Unit = {
    op match {
      case Eq    => setEqual2(col, filterAttr)
      case Neq   => setNeq2(col, filterAttr)
      case Has   => has2(col, filterAttr, cardOne, tpe)
      case HasNo => hasNo2(col, filterAttr, cardOne, tpe)
      case other => unexpectedOp(other)
    }
  }

  protected def setAttr[T](col: String, res: ResSet[T]): Unit = {
    select -= col
    select += s"ARRAY_AGG($col)"
    having += "COUNT(*) > 0"
    aggregate = true
    replaceCast(res.nestedArray2coalescedSet)
  }

  protected def noBooleanSetAggr[T](res: ResSet[T]): Unit = {
    if (res.tpe == "Boolean")
      throw ModelError("Aggregate functions not implemented for Sets of boolean values.")
  }
  protected def noBooleanSetCounts(n: Int): Unit = {
    if (n == -1)
      throw ModelError("Aggregate functions not implemented for Sets of boolean values.")
  }

  protected def setAggr[T](col: String, fn: String, optN: Option[Int], res: ResSet[T]): Unit = {
    select -= col
    lazy val n = optN.getOrElse(0)
    fn match {
      case "distinct" =>
        noBooleanSetAggr(res)
        select += s"ARRAY_AGG(DISTINCT $col)"
        groupByCols -= col
        aggregate = true
        replaceCast(res.nestedArray2nestedSet)

      case "min" =>
        noBooleanSetAggr(res)
        select += s"MIN($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(res.array2setFirst)

      case "mins" =>
        noBooleanSetAggr(res)
        select += s"ARRAY_AGG(DISTINCT $col)"
        groupByCols -= col
        aggregate = true
        replaceCast(res.nestedArray2setAsc(n))

      case "max" =>
        noBooleanSetAggr(res)
        select += s"MAX($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(res.array2setLast)

      case "maxs" =>
        noBooleanSetAggr(res)
        select += s"ARRAY_AGG(DISTINCT $col)"
        groupByCols -= col
        aggregate = true
        replaceCast(res.nestedArray2setDesc(n))

      case "rand" =>
        noBooleanSetAggr(res)
        distinct = false
        select += col
        orderBy += ((level, -1, "RAND()", ""))
        hardLimit = 1
        replaceCast(res.nestedArray2coalescedSet)

      case "rands" =>
        noBooleanSetAggr(res)
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

      case "sample" =>
        noBooleanSetAggr(res)
        distinct = false
        select += col
        orderBy += ((level, -1, "RAND()", ""))
        hardLimit = 1
        replaceCast(res.nestedArray2coalescedSet)

      case "samples" =>
        noBooleanSetAggr(res)
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

      case "count" =>
        noBooleanSetCounts(n)
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
        noBooleanSetCounts(n)
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

  protected def matchSet(set: Set[String], col: String): String = {
    set
      .map(v => s"ARRAY_CONTAINS($col, $v)")
      .mkString("(\n    ", " AND\n    ", s" AND\n    CARDINALITY($col) = ${set.size}\n  )")
  }

  protected def matchSets[T](sets: Seq[Set[T]], col: String, set2sqls: Set[T] => Set[String]): String = {
    sets.map { set =>
      set2sqls(set)
        .map(v => s"ARRAY_CONTAINS($col, $v)")
        .mkString("", " AND\n      ", s" AND\n      CARDINALITY($col) = " + set.size)
    }.mkString("(\n    (\n      ", "\n    ) OR (\n      ", "\n    )\n  )")
  }

  protected def setEqual[T](col: String, sets: Seq[Set[T]], res: ResSet[T]): Unit = {
    val setsNonEmpty = sets.filterNot(_.isEmpty)
    setsNonEmpty.length match {
      case 0 => where += (("FALSE", ""))
      case 1 => where += (("", matchSet(res.set2sqls(setsNonEmpty.head), col)))
      case _ => where += (("", matchSets(setsNonEmpty, col, res.set2sqls)))
    }
  }

  protected def setEqual2(col: String, filterAttr: String): Unit = {
    where += ((col, "= " + filterAttr))
  }

  protected def setOptEqual[T](col: String, optSets: Option[Seq[Set[T]]], res: ResSet[T]): Unit = {
    optSets.fold[Unit] {
      where += ((col, s"IS NULL"))
    } { sets =>
      setEqual(col, sets, res)
    }
  }

  protected def setNeq[T](col: String, sets: Seq[Set[T]], res: ResSet[T]): Unit = {
    val setsNonEmpty = sets.filterNot(_.isEmpty)
    setsNonEmpty.length match {
      case 0 => ()
      case 1 => where += (("", "NOT " + matchSet(res.set2sqls(setsNonEmpty.head), col)))
      case _ => where += (("", "NOT " + matchSets(setsNonEmpty, col, res.set2sqls)))
    }
  }

  protected def setNeq2(col: String, filterAttr: String): Unit = {
    where += ((col, "<> " + filterAttr))
  }

  protected def setOptNeq[T](col: String, optSets: Option[Seq[Set[T]]], res: ResSet[T]): Unit = {
    if (optSets.isDefined && optSets.get.nonEmpty) {
      setNeq(col, optSets.get, res)
    }
    notNull += col
  }

  protected def has[T: ClassTag](col: String, sets: Seq[Set[T]], one2sql: T => String): Unit = {
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

  protected def has2(col: String, filterAttr: String, cardOne: Boolean, tpe: String): Unit = {
    if (cardOne) {
      where += (("", s"ARRAY_CONTAINS($col, $filterAttr)"))
    } else {
      where += (("", s"has_$tpe($col, $filterAttr)"))
    }
  }

  protected def optHas[T: ClassTag](
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

  protected def hasNo[T](col: String, sets: Seq[Set[T]], one2sql: T => String): Unit = {
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

  protected def hasNo2(col: String, filterAttr: String, cardOne: Boolean, tpe: String): Unit = {
    if (cardOne) {
      where += (("", s"NOT ARRAY_CONTAINS($col, $filterAttr)"))
    } else {
      where += (("", s"hasNo_$tpe($col, $filterAttr)"))
    }
  }

  protected def optHasNo[T](
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

  protected def setNoValue(col: String): Unit = {
    notNull -= col
    where += ((col, s"IS NULL"))
  }
}