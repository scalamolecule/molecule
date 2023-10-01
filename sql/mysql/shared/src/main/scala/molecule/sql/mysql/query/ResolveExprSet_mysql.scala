package molecule.sql.mysql.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.sql.core.query.{ResolveExprSet, SqlQueryBase}
import scala.reflect.ClassTag


trait ResolveExprSet_mysql
  extends ResolveExprSet
    with LambdasSet_mysql { self: SqlQueryBase =>


  override protected def setMan[T: ClassTag](attr: Attr, tpe: String, args: Seq[Set[T]], res: ResSet[T]): Unit = {
    val col = getCol(attr: Attr)
    select += col
    groupByCols += col // if we later need to group by non-aggregated columns
    if (!isNestedOpt) {
      notNull += col
    }
    addCast((row: Row, paramIndex: Int) =>
      res.json2array(row.getString(paramIndex)).toSet
    )

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

  override protected def setOpt[T: ClassTag](
    attr: Attr,
    optSets: Option[Seq[Set[T]]],
    resOpt: ResSetOpt[T],
    res: ResSet[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    groupByCols += col // if we later need to group by non-aggregated columns
    addCast((row: Row, paramIndex: Int) => row.getString(paramIndex) match {
      case null | "[]" => Option.empty[Set[T]]
      case json        => Some(res.json2array(json).toSet)
    })
    attr.op match {
      case V     => ()
      case Eq    => setOptEqual(col, optSets, res)
      case Neq   => setOptNeq(col, optSets, res)
      case Has   => optHas(col, optSets, resOpt.one2json)
      case HasNo => optHasNo(col, optSets, resOpt.one2json)
      case other => unexpectedOp(other)
    }
  }

  override protected def setExpr[T: ClassTag](col: String, op: Op, sets: Seq[Set[T]], res: ResSet[T], mode: String): Unit = {
    op match {
      case V         => if (mode == "man") setAttr(col, res) else ()
      case Eq        => setEqual(col, sets, res)
      case Neq       => setNeq(col, sets, res)
      case Has       => has(col, sets, res.one2json)
      case HasNo     => hasNo(col, sets, res.one2json)
      case NoValue   => setNoValue(col)
      case Fn(kw, n) => setAggr(col, kw, n, res)
      case other     => unexpectedOp(other)
    }
  }

  override protected def setAttr[T](col: String, res: ResSet[T]): Unit = {
    replaceCast((row: Row, paramIndex: Int) => {
      res.json2array(row.getString(paramIndex)).toSet
    })
  }

  override protected def setAggr[T](col: String, fn: String, optN: Option[Int], res: ResSet[T]): Unit = {
    select -= col
    lazy val n = optN.getOrElse(0)
    fn match {
      case "distinct" =>
        noBooleanSetAggr(res)
        groupByCols -= col
        replaceCast((row: Row, paramIndex: Int) =>
          res.json2array(row.getString(paramIndex)).toSet
        )

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
        orderBy += ((level, -1, "RANDOM()", ""))
        hardLimit = 1
        replaceCast(res.sql2set)

      case "rands" =>
        noBooleanSetAggr(res)
        select +=
          s"""TRIM_ARRAY(
             |    ARRAY_AGG($col order by RANDOM()),
             |    GREATEST(
             |      0,
             |      ARRAY_LENGTH(ARRAY_AGG($col), 1) - $n
             |    )
             |  )""".stripMargin
        groupByCols -= col
        aggregate = true
        replaceCast(res.nestedArray2coalescedSet)

      case "sample" =>
        noBooleanSetAggr(res)
        distinct = false
        select += col
        orderBy += ((level, -1, "RANDOM()", ""))
        hardLimit = 1
        replaceCast(res.sql2set)

      case "samples" =>
        noBooleanSetAggr(res)
        select +=
          s"""TRIM_ARRAY(
             |    ARRAY_AGG($col order by RANDOM()),
             |    GREATEST(
             |      0,
             |      ARRAY_LENGTH(ARRAY_AGG(DISTINCT $col), 1) - $n
             |    )
             |  )""".stripMargin
        groupByCols -= col
        aggregate = true
        replaceCast(res.nestedArray2coalescedSet)


      // Using brute force in the following aggregate functions to be able to
      // aggregate _unique_ values (Set semantics instead of Array semantics)

      case "count" =>
        noBooleanSetCounts(n)
        // Count of all (non-unique) values
        select += s"ARRAY_AGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(
          (row: Row, paramIndex: Int) => {
            val outerArrayResultSet = row.getArray(paramIndex).getResultSet
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
          (row: Row, paramIndex: Int) => {
            val outerArrayResultSet = row.getArray(paramIndex).getResultSet
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
        select += s"ARRAY_AGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(
          (row: Row, paramIndex: Int) => {
            val outerArrayResultSet = row.getArray(paramIndex).getResultSet
            var set                 = Set.empty[Double]
            while (outerArrayResultSet.next()) {
              val array = outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]]
              array.foreach(v => set += v.toString.toDouble) // not the most efficient...
            }
            getMedian(set)
          }
        )

      case "avg" =>
        // Average of unique values (Set semantics)
        select += s"ARRAY_AGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(
          (row: Row, paramIndex: Int) => {
            val outerArrayResultSet = row.getArray(paramIndex).getResultSet
            var set                 = Set.empty[Double]
            while (outerArrayResultSet.next()) {
              val array = outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]]
              array.foreach(v => set += v.toString.toDouble) // not the most efficient...
            }
            set.sum / set.size
          }
        )

      case "variance" =>
        // Variance of unique values (Set semantics)
        select += s"ARRAY_AGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(
          (row: Row, paramIndex: Int) => {
            val outerArrayResultSet = row.getArray(paramIndex).getResultSet
            var set                 = Set.empty[Double]
            while (outerArrayResultSet.next()) {
              val array = outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]]
              array.foreach(v => set += v.toString.toDouble) // not the most efficient...
            }
            varianceOf(set.toList: _*)
          }
        )

      case "stddev" =>
        // Standard deviation of unique values (Set semantics)
        select += s"ARRAY_AGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(
          (row: Row, paramIndex: Int) => {
            val outerArrayResultSet = row.getArray(paramIndex).getResultSet
            var set                 = Set.empty[Double]
            while (outerArrayResultSet.next()) {
              val array = outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]]
              array.foreach(v => set += v.toString.toDouble) // not the most efficient...
            }
            stdDevOf(set.toList: _*)
          }
        )

      case other => unexpectedKw(other)
    }
  }

  private def matchArray[T](col: String, set: Set[T], one2json: T => String): String = {
    val size       = set.size
    val jsonValues = set.map(one2json).mkString(", ")
    s"JSON_LENGTH($col) = $size AND JSON_CONTAINS($col, JSON_ARRAY($jsonValues))"
  }

  private def matchArrays[T](col: String, sets: Seq[Set[T]], one2json: T => String): String = {
    sets.map(set =>
      matchArray(col, set, one2json)
    ).mkString("(\n    ", " OR\n    ", "\n  )")
  }

  override protected def setEqual[T](col: String, sets: Seq[Set[T]], res: ResSet[T]): Unit = {
    val setsNonEmpty = sets.filterNot(_.isEmpty)
    setsNonEmpty.length match {
      case 0 => where += (("FALSE", ""))
      case 1 => where += (("", matchArray(col, setsNonEmpty.head, res.one2json)))
      case _ => where += (("", matchArrays(col, setsNonEmpty, res.one2json)))
    }
  }

  override protected def setNeq[T](col: String, sets: Seq[Set[T]], res: ResSet[T]): Unit = {
    val setsNonEmpty = sets.filterNot(_.isEmpty)
    setsNonEmpty.length match {
      case 0 => ()
      case 1 => where += (("", "NOT (" + matchArray(col, setsNonEmpty.head, res.one2json) + ")"))
      case _ => where += (("", "NOT (" + matchArrays(col, setsNonEmpty, res.one2json) + ")"))
    }
  }

  override protected def has[T: ClassTag](col: String, sets: Seq[Set[T]], one2json: T => String): Unit = {
    def containsSet(set: Set[T]): String = {
      val jsonValues = set.map(one2json).mkString(", ")
      s"JSON_CONTAINS($col, JSON_ARRAY($jsonValues))"
    }
    sets.length match {
      case 0 => where += (("FALSE", ""))
      case 1 =>
        val set = sets.head
        set.size match {
          case 0 => where += (("FALSE", ""))
          case 1 => where += (("", s"JSON_CONTAINS($col, JSON_ARRAY(${one2json(set.head)}))"))
          case _ => where += (("", containsSet(set)))
        }
      case _ =>
        val expr = sets
          .filterNot(_.isEmpty)
          .map(containsSet).mkString("(\n    ", " OR\n    ", "\n  )")
        where += (("", expr))
    }
  }

  override protected def has2(col: String, filterAttr: String, cardOne: Boolean, tpe: String): Unit = {
    if (cardOne) {
      where += (("", s"$col @> ARRAY(SELECT $filterAttr)"))
    } else {
      where += (("", s"$col @> $filterAttr"))
    }
  }

  override protected def hasNo[T](col: String, sets: Seq[Set[T]], one2json: T => String): Unit = {
    def notContains(v: T): String = s"NOT JSON_CONTAINS($col, JSON_ARRAY(${one2json(v)}))"
    def notContainsSet(set: Set[T]): String = {
      val jsonValues = set.map(one2json).mkString(", ")
      s"NOT JSON_CONTAINS($col, JSON_ARRAY($jsonValues))"
    }
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
          .map(notContainsSet)
          .mkString("(\n    ", " AND\n    ", "\n  )")
        where += (("", expr))
    }
  }

  override protected def hasNo2(col: String, filterAttr: String, cardOne: Boolean, tpe: String): Unit = {
    if (cardOne) {
      where += (("", s"ARRAY(SELECT UNNEST($col) INTERSECT SELECT $filterAttr) = '{}'"))
    } else {
      where += (("", s"ARRAY(SELECT UNNEST($col) INTERSECT SELECT UNNEST($filterAttr)) = '{}'"))
    }
  }
}