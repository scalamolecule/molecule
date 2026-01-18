package molecule.db.mysql.query

import scala.reflect.ClassTag
import scala.util.Random
import molecule.core.dataModel.*
import molecule.core.error.ModelError
import molecule.db.common.javaSql.PrepStmt
import molecule.db.common.query.{Model2Query, QueryExprOne, QueryExprRef, SqlQueryBase}

trait QueryExprOne_mysql
  extends QueryExprOne
    with LambdasOne_mysql { self: Model2Query & QueryExprRef & SqlQueryBase =>

  // MySQL doesn't support NULLS FIRST/LAST syntax, so we simulate it
  override protected def addSort(attr: AttrOne, col: String): Unit = {
    attr.sort.foreach { sort =>
      val (dir, arity) = (sort.head, sort.substring(1, 2).toInt)

      // Check if this is an aggregate function
      val isAggregate = attr.op.isInstanceOf[AggrFn]

      // In join subqueries with aggregates and GROUP BY, or when sorting aggregate functions,
      // we can't use IS NULL checks in ORDER BY due to sql_mode=only_full_group_by
      if ((insideJoinSubQuery && aggregate) || isAggregate) {
        // Just sort by the column directly (will be replaced with alias by selectWithOrder)
        val sortDir = if (dir == 'a') "" else " DESC"
        orderBy += ((level, arity, col, sortDir))
      } else {
        dir match {
          case 'a' =>
            // ASC NULLS FIRST: sort NULLs first, then values ascending
            orderBy += ((level, arity, s"($col IS NOT NULL)", ""))
            orderBy += ((level, arity, col, ""))
          case 'd' =>
            // DESC NULLS LAST: sort values descending, then NULLs last
            orderBy += ((level, arity, s"($col IS NULL)", ""))
            orderBy += ((level, arity, col, " DESC"))
        }
      }
    }
  }

  override protected def matches[T](
    col: String,
    args: Seq[T],
    binding: Boolean,
    bind: (PrepStmt, Int, Int, Value) => Unit
  ): Unit = {
    if binding then {
      addBinding(col, bind, "REGEXP ?")
    } else {
      val regex = args.head.toString
      if (regex.nonEmpty)
        where += ((col, s"REGEXP '$regex'"))
    }
  }

  override protected def aggr[T: ClassTag](
    baseType: String,
    ent: String,
    attr: String,
    col: String,
    fn: String,
    optN: Option[Int],
    aggrOp: Option[Op],
    aggrOpValue: Option[Value],
    hasSort: Boolean,
    res: ResOne[T],
    mandatory: Boolean = true
  ): Unit = {
    checkAggrOne()
    lazy val sep     = "0x1D" // Use invisible ascii Group Selector to separate concatenated values
    lazy val sepChar = 29.toChar
    lazy val n       = optN.getOrElse(0)
    def havingOp(expr: String) = addHaving(baseType, fn, expr, aggrOp, aggrOpValue, res)

    // If large number of mins/maxs/samples is needed, group_concat max size can be raised from default 1024 char
    // https://dev.mysql.com/doc/refman/8.0/en/server-system-variables.html#sysvar_group_concat_max_len

    select -= col
    fn match {
      case "distinct" =>
        aggregate = true
        select += s"JSON_ARRAYAGG($col)"
        if (!select.contains(col)) groupByCols -= col
        if (mandatory)
          castStrategy.replace((row: RS, paramIndex: Int) =>
            res.json2array(row.getString(paramIndex)).toSet
          )

      case "min" =>
        aggregate = true
        if (hasSort || insideJoinSubQuery) {
          // Use COALESCE to treat NULL appropriately for proper sorting and JOIN subqueries
          val defaultValue = res.tpe match {
            case "String" => "''"
            case _        => "0"
          }
          val alias        = col.replace('.', '_') + "_min"
          select += s"COALESCE(MIN($col), $defaultValue) AS $alias"
          if (hasSort) {
            val (level, _, _, dir) = orderBy.last
            orderBy.remove(orderBy.size - 1)
            orderBy += ((level, 1, alias, dir))
          }
        } else {
          select += s"MIN($col)"
        }
        if (!select.contains(col)) groupByCols -= col
        havingOp(s"MIN($col)")

      case "mins" =>
        aggregate = true
        select += s"GROUP_CONCAT(DISTINCT $col SEPARATOR $sep)"
        if (!select.contains(col)) groupByCols -= col
        if (mandatory)
          castStrategy.replace((row: RS, paramIndex: Int) =>
            row.getString(paramIndex).split(sepChar).map(res.json2tpe).take(n).toSet
          )

      case "max" =>
        aggregate = true
        if (hasSort || insideJoinSubQuery) {
          // Use COALESCE to treat NULL appropriately for proper sorting and JOIN subqueries
          val defaultValue = res.tpe match {
            case "String" => "''"
            case _        => "0"
          }
          val alias        = col.replace('.', '_') + "_max"
          select += s"COALESCE(MAX($col), $defaultValue) AS $alias"
          if (hasSort) {
            val (level, _, _, dir) = orderBy.last
            orderBy.remove(orderBy.size - 1)
            orderBy += ((level, 1, alias, dir))
          }
        } else {
          select += s"MAX($col)"
        }
        if (!select.contains(col)) groupByCols -= col
        havingOp(s"MAX($col)")

      case "maxs" =>
        aggregate = true
        select += s"GROUP_CONCAT(DISTINCT $col ORDER BY $col DESC SEPARATOR $sep)"
        if (!select.contains(col)) groupByCols -= col
        if (mandatory)
          castStrategy.replace((row: RS, paramIndex: Int) =>
            row.getString(paramIndex).split(sepChar).map(res.json2tpe).take(n).toSet
          )

      case "sample" =>
        if (aggrOp.isDefined) {
          throw ModelError("Operations on sample not implemented for this database.")
        }
        aggregate = true
        select += s"JSON_ARRAYAGG($col)"
        if (!select.contains(col)) groupByCols -= col
        havingOp("RAND()")
        if (mandatory)
          castStrategy.replace((row: RS, paramIndex: Int) => {
            val array = res.json2array(row.getString(paramIndex))
            val rnd   = new Random().nextInt(array.length)
            array(rnd)
          })

      case "samples" =>
        aggregate = true
        select += s"JSON_ARRAYAGG($col)"
        if (!select.contains(col)) groupByCols -= col
        if (mandatory)
          castStrategy.replace((row: RS, paramIndex: Int) => {
            val array = res.json2array(row.getString(paramIndex))
            Random.shuffle(array.toSet).take(n)
          })

      case "count" =>
        aggregate = true
        distinct = false
        selectWithOrder(col, "COUNT", hasSort, "")
        if (!select.contains(col)) groupByCols -= col
        havingOp(s"COUNT($col)")
        if (mandatory)
          castStrategy.replace(toInt)

      case "countDistinct" =>
        aggregate = true
        distinct = false
        selectWithOrder(col, "COUNT", hasSort, aliasSuffix = Some("countDistinct"))
        if (!select.contains(col)) groupByCols -= col
        havingOp(s"COUNT(DISTINCT $col)")
        if (mandatory)
          castStrategy.replace(toInt)

      case "sum" =>
        aggregate = true
        selectWithOrder(col, "SUM", hasSort, "", "", "ROUND(", ", 6)")
        if (!select.contains(col)) groupByCols -= col
        havingOp(s"ROUND(SUM($col), 6)")

      case "median" =>
        if (orderBy.nonEmpty && orderBy.last._3 == col) {
          throw ModelError("Sorting by median not implemented for this database.")
        }
        if (aggrOp.isDefined) {
          throw ModelError("Operations on median not implemented for this database.")
        }
        if (insideSubQuery && !insideJoinSubQuery) {
          throw ModelError(noStatisticalFunctionsInSubquery)
        }
        aggregate = true
        if (insideJoinSubQuery) {
          // Add alias for JOIN subqueries so outer query can reference it
          val alias = col.replace('.', '_') + "_median"
          select += s"JSON_ARRAYAGG($col) AS $alias"
        } else {
          select += s"JSON_ARRAYAGG($col)"
        }
        if (!select.contains(col)) groupByCols -= col
        if (mandatory)
          castStrategy.replace(
            (row: RS, paramIndex: Int) => {
              val json = row.getString(paramIndex)
              if (json == null) {
                0.0
              } else {
                getMedian(json.substring(1, json.length - 1).split(", ").map(_.toDouble).toList)
              }
            }
          )

      case "avg" =>
        aggregate = true
        selectWithOrder(col, "AVG", hasSort, "", "", "ROUND(", ", 6)")
        if (!select.contains(col)) groupByCols -= col
        havingOp(s"ROUND(AVG($col), 6)")

      case "variance" =>
        if (orderBy.nonEmpty && orderBy.last._3 == col) {
          throw ModelError("Sorting by variance not implemented for this database.")
        }
        if (aggrOp.isDefined) {
          throw ModelError("Operations on variance not implemented for this database.")
        }
        if (insideSubQuery && !insideJoinSubQuery) {
          throw ModelError(noStatisticalFunctionsInSubquery)
        }
        aggregate = true
        if (insideJoinSubQuery) {
          // Add alias for JOIN subqueries so outer query can reference it
          val alias = col.replace('.', '_') + "_variance"
          select += s"JSON_ARRAYAGG($col) AS $alias"
        } else {
          select += s"JSON_ARRAYAGG($col)"
        }
        if (!select.contains(col)) groupByCols -= col
        havingOp(s"VAR_POP($col)")
        if (mandatory)
          castStrategy.replace(
            (row: RS, paramIndex: Int) => {
              val json = row.getString(paramIndex)
              if (json == null) {
                0.0
              } else {
                varianceOf(json.substring(1, json.length - 1).split(", ").map(_.toDouble).toSeq)
              }
            }
          )

      case "stddev" =>
        if (orderBy.nonEmpty && orderBy.last._3 == col) {
          throw ModelError("Sorting by standard deviation not implemented for this database.")
        }
        if (aggrOp.isDefined) {
          throw ModelError("Operations on stddev not implemented for this database.")
        }
        if (insideSubQuery && !insideJoinSubQuery) {
          throw ModelError(noStatisticalFunctionsInSubquery)
        }
        aggregate = true
        if (insideJoinSubQuery) {
          // Add alias for JOIN subqueries so outer query can reference it
          val alias = col.replace('.', '_') + "_stddev"
          select += s"JSON_ARRAYAGG($col) AS $alias"
        } else {
          select += s"JSON_ARRAYAGG($col)"
        }
        if (!select.contains(col)) groupByCols -= col
        havingOp(s"STDDEV_POP($col)")
        if (mandatory)
          castStrategy.replace(
            (row: RS, paramIndex: Int) => {
              val json = row.getString(paramIndex)
              if (json == null) {
                0.0
              } else {
                stdDevOf(json.substring(1, json.length - 1).split(", ").map(_.toDouble).toSeq)
              }
            }
          )

      case other => unexpectedKw(other)
    }
  }
}