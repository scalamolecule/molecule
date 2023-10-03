package molecule.sql.postgres.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.sql.core.query.{ResolveExprSet, SqlQueryBase}
import scala.reflect.ClassTag


trait ResolveExprSet_postgres
  extends ResolveExprSet
    with LambdasSet_postgres { self: SqlQueryBase =>


  override protected def setMan[T: ClassTag](attr: Attr, tpe: String, args: Seq[Set[T]], res: ResSet[T]): Unit = {
    val col = getCol(attr: Attr)
    select += col
    groupByCols += col // if we later need to group by non-aggregated columns

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


  override protected def setAttr[T](col: String, res: ResSet[T]): Unit = {
    select -= col
    groupByCols -= col
    if (res.tpe == "Boolean" && !expectedFilterAttrs.contains(col) && !isNested && !isNestedOpt) {
      // If we don't apply this hack, Boolean sets throw
      // ERROR: cannot accumulate arrays of different dimensionality
      // https://stackoverflow.com/questions/46849237/postgresql-array-agginteger/46849678#46849678
      val colAlias = col.replace(".", "_")
      select += s"ARRAY_AGG(DISTINCT $colAlias)"
      tempTables = tempTables :+ s"UNNEST($col) AS $colAlias"
    } else {
      select += s"ARRAY_AGG($col)"
      where += (("", s"$col <> '{}'"))
      replaceCast(res.nestedArray2coalescedSet)
    }
    having += "COUNT(*) > 0"
    aggregate = true
  }

  override protected def setAggr[T: ClassTag](col: String, fn: String, optN: Option[Int], res: ResSet[T]): Unit = {
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
            val resultSet = row.getArray(paramIndex).getResultSet
            var count     = 0
            while (resultSet.next()) {
              count += resultSet.getArray(2).getArray.asInstanceOf[Array[_]].length
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
            val resultSet = row.getArray(paramIndex).getResultSet
            var set       = Set.empty[Any]
            while (resultSet.next()) {
              resultSet.getArray(2).getArray.asInstanceOf[Array[_]].foreach { value =>
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
            val resultSet = row.getArray(paramIndex).getResultSet
            var set       = Set.empty[Double]
            while (resultSet.next()) {
              val array = resultSet.getArray(2).getArray.asInstanceOf[Array[_]]
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
            val resultSet = row.getArray(paramIndex).getResultSet
            var set       = Set.empty[Double]
            while (resultSet.next()) {
              val array = resultSet.getArray(2).getArray.asInstanceOf[Array[_]]
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
            val resultSet = row.getArray(paramIndex).getResultSet
            var set       = Set.empty[Double]
            while (resultSet.next()) {
              val array = resultSet.getArray(2).getArray.asInstanceOf[Array[_]]
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
            val resultSet = row.getArray(paramIndex).getResultSet
            var set       = Set.empty[Double]
            while (resultSet.next()) {
              val array = resultSet.getArray(2).getArray.asInstanceOf[Array[_]]
              array.foreach(v => set += v.toString.toDouble) // not the most efficient...
            }
            stdDevOf(set.toList: _*)
          }
        )

      case other => unexpectedKw(other)
    }
  }

  private def matchArray(sqlArray: (String, Int), col: String): String = {
    s"(${sqlArray._1} <@ $col AND CARDINALITY($col) = ${sqlArray._2})"
  }

  private def matchArrays[T](sets: Seq[Set[T]], col: String, set2sqlArray: Set[T] => String): String = {
    sets.map(set =>
      matchArray((set2sqlArray(set), set.size), col)
    ).mkString("(\n    ", " OR\n    ", "\n  )")
  }

  override protected def setEqual[T](col: String, sets: Seq[Set[T]], res: ResSet[T]): Unit = {
    val setsNonEmpty = sets.filterNot(_.isEmpty)
    setsNonEmpty.length match {
      case 0 => where += (("FALSE", ""))
      case 1 => where += (("", matchArray((res.set2sqlArray(setsNonEmpty.head), setsNonEmpty.head.size), col)))
      case _ => where += (("", matchArrays(setsNonEmpty, col, res.set2sqlArray)))
    }
  }

  override protected def setNeq[T](col: String, sets: Seq[Set[T]], res: ResSet[T]): Unit = {
    val setsNonEmpty = sets.filterNot(_.isEmpty)
    setsNonEmpty.length match {
      case 0 => ()
      case 1 => where += (("", "NOT " +
        matchArray((res.set2sqlArray(setsNonEmpty.head), setsNonEmpty.head.size), col)))
      case _ => where += (("", "NOT " +
        matchArrays(setsNonEmpty, col, res.set2sqlArray)))
    }
  }

  override protected def has[T: ClassTag](col: String, sets: Seq[Set[T]], one2sql: T => String): Unit = {
    def contains(v: T): String = s"${one2sql(v)} = ANY($col)"
    def containsSet(set: Set[T]): String = set.map(contains).mkString(" AND ")
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

  override protected def hasNo[T](col: String, sets: Seq[Set[T]], one2sql: T => String): Unit = {
    def notContains(v: T): String = s"${one2sql(v)} != ALL($col)"
    def notContainsSet(set: Set[T]): String = set.map(notContains).mkString("(", " OR ", ")")
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