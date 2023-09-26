package molecule.sql.mysql.query

import molecule.boilerplate.ast.Model._
import molecule.sql.core.query.{ResolveExprOne, SqlQueryBase}

trait ResolveExprOne_mysql extends ResolveExprOne with LambdasOne_mysql { self: SqlQueryBase =>


  override protected def addSort(attr: Attr, col: String): Unit = {
    attr.sort.foreach { sort =>
      val (dir, arity) = (sort.head, sort.substring(1, 2).toInt)
      dir match {
        case 'a' => orderBy += ((level, arity, col, ""))
        case 'd' => orderBy += ((level, arity, col, " DESC"))
      }
    }
  }

  override protected def matches[T](col: String, regex: T): Unit = where += ((col, s"REGEXP '$regex'"))

  override protected def aggr[T](col: String, fn: String, optN: Option[Int], res: ResOne[T]): Unit = {
    lazy val n = optN.getOrElse(0)
    // Replace find/casting with aggregate function/cast
    select -= col
    fn match {
      case "distinct" =>
        select += s"ARRAY_AGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(res.array2set)

      case "min" =>
        select += s"MIN($col${res.tpe})"
        if (col.endsWith(".id")) {
          groupByCols -= col
          aggregate = true
        }

      case "mins" =>
        select +=
          s"""TRIM_ARRAY(
             |    ARRAY_AGG(DISTINCT $col order by $col ASC),
             |    GREATEST(
             |      0,
             |      ARRAY_LENGTH(ARRAY_AGG(DISTINCT $col), 1) - $n
             |    )
             |  )""".stripMargin
        groupByCols -= col
        aggregate = true
        replaceCast(res.array2set)

      case "max" =>
        select += s"MAX($col${res.tpe})"
        if (col.endsWith(".id")) {
          groupByCols -= col
          aggregate = true
        }

      case "maxs" =>
        select +=
          s"""TRIM_ARRAY(
             |    ARRAY_AGG(DISTINCT $col order by $col DESC),
             |    GREATEST(
             |      0,
             |      ARRAY_LENGTH(ARRAY_AGG(DISTINCT $col), 1) - $n
             |    )
             |  )""".stripMargin
        groupByCols -= col
        aggregate = true
        replaceCast(res.array2set)

      case "rand" =>
        distinct = false
        select += col
        orderBy += ((level, -1, "RANDOM()", ""))
        hardLimit = 1

      case "rands" =>
        select +=
          s"""TRIM_ARRAY(
             |    ARRAY_AGG($col order by random()),
             |    GREATEST(
             |      0,
             |      ARRAY_LENGTH(ARRAY_AGG($col), 1) - $n
             |    )
             |  )""".stripMargin
        groupByCols -= col
        aggregate = true
        replaceCast(res.array2set)

      case "sample" =>
        distinct = false
        select += col
        orderBy += ((level, -1, "RANDOM()", ""))
        hardLimit = 1

      case "samples" =>
        select +=
          s"""TRIM_ARRAY(
             |    ARRAY_AGG($col order by random()),
             |    GREATEST(
             |      0,
             |      ARRAY_LENGTH(ARRAY_AGG(DISTINCT $col), 1) - $n
             |    )
             |  )""".stripMargin
        groupByCols -= col
        aggregate = true
        replaceCast(res.array2set)

      case "count" =>
        distinct = false
        groupByCols -= col
        aggregate = true
        selectWithOrder(col, "COUNT", "")
        replaceCast(toInt)

      case "countDistinct" =>
        distinct = false
        groupByCols -= col
        aggregate = true
        selectWithOrder(col, "COUNT")
        replaceCast(toInt)

      case "sum" =>
        groupByCols -= col
        aggregate = true
        selectWithOrder(col, "SUM")

      case "median" =>
        groupByCols -= col
        aggregate = true
        // OBS: requires custom median function to be saved with schema
        selectWithOrder(col, "MEDIAN", cast = "::numeric")

      case "avg" =>
        groupByCols -= col
        aggregate = true
        selectWithOrder(col, "AVG")

      case "variance" =>
        groupByCols -= col
        aggregate = true
        selectWithOrder(col, "VAR_POP")

      case "stddev" =>
        groupByCols -= col
        aggregate = true
        selectWithOrder(col, "STDDEV_POP")

      case other => unexpectedKw(other)
    }
  }
}