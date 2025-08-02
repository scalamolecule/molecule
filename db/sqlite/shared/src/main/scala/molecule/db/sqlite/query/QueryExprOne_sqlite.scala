package molecule.db.sqlite.query

import molecule.base.error.ModelError
import molecule.core.dataModel.{Op, Value}
import molecule.db.common.javaSql.PrepStmt
import molecule.db.common.query.{Model2Query, QueryExprOne, SqlQueryBase}
import scala.reflect.ClassTag

trait QueryExprOne_sqlite
  extends QueryExprOne
    with LambdasOne_sqlite { self: Model2Query & SqlQueryBase =>


  override protected def equal[T](
    col: String,
    args: Seq[T],
    one2sql: T => String,
    binding: Boolean,
    bind: (PrepStmt, Int, Int, Value) => Unit,
    tpe: String
  ): Unit = {
    if binding then {
      if (tpe == "Float") {
        // Hack to handle floating-point precision
        // Highly recommended to use Double instead
        addBinding(s"ROUND($col, 7)", bind, "= ROUND(?, 7)")
      } else {
        addBinding(col, bind, "= ?")
      }
    } else {
      where += (args.length match {
        case 1 => (col, "= " + one2sql(args.head))
        case 0 => ("FALSE", "") // Empty Seq of args matches no values
        case _ => (col, args.map(one2sql).mkString("IN (", ", ", ")"))
      })
    }
  }


  override protected def neq[T](
    col: String,
    args: Seq[T],
    one2sql: T => String,
    binding: Boolean,
    bind: (PrepStmt, Int, Int, Value) => Unit,
    tpe: String
  ): Unit = {
    if binding then {
      if (tpe == "Float") {
        // Hack to handle floating-point precision
        // Highly recommended to use Double instead
        addBinding(s"ROUND($col, 7)", bind, "<> ROUND(?, 7)")
      } else {
        addBinding(col, bind, "<> ?")
      }
    } else {
      where += (args.length match {
        case 1 => (col, "<> " + one2sql(args.head))
        case 0 => (col, "IS NOT NULL ")
        case _ => (col, args.map(one2sql).mkString("NOT IN (", ", ", ")"))
      })
    }
  }

  override protected def compare[T](
    col: String,
    args: Seq[T],
    op: String,
    one2sql: T => String,
    binding: Boolean,
    bind: (PrepStmt, Int, Int, Value) => Unit,
    tpe: String
  ): Unit = {
    if binding then {
      if (tpe == "Float") {
        // Hack to handle floating-point precision
        // Highly recommended to use Double instead
        addBinding(s"ROUND($col, 7)", bind, s"$op ROUND(?, 7)")
      } else {
        addBinding(col, bind, s"$op ?")
      }
    } else {
      where += ((col, op + " " + one2sql(args.head)))
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
    res: ResOne[T]
  ): Unit = {
    checkAggrOne()
    lazy val n = optN.getOrElse(0)
    def havingOp(expr: String) = addHaving(baseType, fn, expr, aggrOp, aggrOpValue, res)

    // Replace find/casting with aggregate function/cast
    select -= col

    def jsonArray2doubles(json: String): Seq[Double] = {
      if (json.startsWith("[\""))
        json.substring(2, json.length - 2).split("\",\"").map(_.toDouble).toSeq
      else
        json.substring(1, json.length - 1).split(",").map(_.toDouble).toSeq
    }

    fn match {
      case "distinct" =>
        aggregate = true
        select += s"json_group_array(DISTINCT $col)"
        if (!select.contains(col)) groupByCols -= col
        castStrategy.replace((row: RS, paramIndex: Int) =>
          res.json2array(row.getString(paramIndex)).toSet
        )

      case "min" =>
        aggregate = true
        select += s"MIN($col)"
        if (!select.contains(col)) groupByCols -= col
        havingOp(s"MIN($col)")

      case "mins" =>
        aggregate = true
        // OBS: select2 has to set be before setting select += ...
        select2 = select2 + (select.length -> minMaxSelect(ent, attr, n, "ASC"))
        select += "<replace>" // add to maintain correct indexing
        if (!select.contains(col)) groupByCols -= col
        castStrategy.replace((row: RS, paramIndex: Int) =>
          res.json2array(row.getString(paramIndex)).toSet
        )

      case "max" =>
        aggregate = true
        select += s"MAX($col)"
        if (!select.contains(col)) groupByCols -= col
        havingOp(s"MAX($col)")

      case "maxs" =>
        aggregate = true
        // OBS: select2 has to set be before setting select += ...
        select2 = select2 + (select.length -> minMaxSelect(ent, attr, n, "DESC"))
        select += "<replace>" // add to maintain correct indexing
        if (!select.contains(col)) groupByCols -= col
        castStrategy.replace((row: RS, paramIndex: Int) =>
          res.json2array(row.getString(paramIndex)).toSet
        )

      case "sample" =>
        select2 = select2 + (select.length -> sampleSelect(ent, attr))
        select += "<replace>" // add to maintain correct indexing
        addWhere(col, aggrOp, aggrOpValue, res) // ?

      case "samples" =>
        aggregate = true
        // OBS: select2 has to set be before setting select += ...
        select2 = select2 + (select.length -> samplesSelect(ent, attr, n))
        select += "<replace>" // add to maintain correct indexing
        if (!select.contains(col)) groupByCols -= col
        castStrategy.replace((row: RS, paramIndex: Int) =>
          res.json2array(row.getString(paramIndex)).toSet
        )

      case "count" =>
        aggregate = true
        distinct = false
        selectWithOrder(col, "COUNT", hasSort, "")
        if (!select.contains(col)) groupByCols -= col
        havingOp(s"COUNT($col)")
        castStrategy.replace(toInt)

      case "countDistinct" =>
        aggregate = true
        distinct = false
        selectWithOrder(col, "COUNT", hasSort)
        if (!select.contains(col)) groupByCols -= col
        havingOp(s"COUNT(DISTINCT $col)")
        castStrategy.replace(toInt)

      case "sum" =>
        aggregate = true
        if (!select.contains(col)) groupByCols -= col
        res.tpe match {
          case "BigInt" =>
            selectWithOrder(col, "SUM", hasSort, "")
            addHaving(baseType, fn, s"SUM($col)", aggrOp, aggrOpValue, res, "CAST(", " AS BIGINT)")

          case "BigDecimal" | "Double" =>
            selectWithOrder(col, "SUM", hasSort, "", "", "ROUND(", ", 10)")
            addHaving(baseType, fn, s"ROUND(SUM($col), 10)", aggrOp, aggrOpValue, res, "CAST(", " AS REAL)")

          case _ =>
            selectWithOrder(col, "SUM", hasSort, "")
            havingOp(s"SUM($col)")
        }


      case "median" =>
        //        select += s"AVG(_$col)"
        //        if (!select.contains(col)) groupByCols -= col
        //        aggregate = true
        // works but incurs a lot of extra complexity when combined with other attributes
        //        val subSelect =
        //          s"""(
        //             |    SELECT $col
        //             |    FROM Entity
        //             |    ORDER BY $col
        //             |    LIMIT 2 - (SELECT COUNT(*) FROM Entity) % 2
        //             |    OFFSET (SELECT (COUNT(*) - 1) / 2 FROM Entity)
        //             |  )""".stripMargin
        //        joins += (("INNER JOIN", subSelect, "_Entity", "", ""))

        // There should be a solution using percent_rank ...
        //        selectWithOrder(col, "percent_rank", "0.5) WITHIN GROUP (ORDER BY ")
        //        select += s"percentile_cont(0.5) within group (order by salary) over (partition by a.company_name) as median"
        if (orderBy.nonEmpty && orderBy.last._3 == col) {
          throw ModelError("Sorting by median not implemented for this database.")
        }
        if (aggrOp.isDefined) {
          throw ModelError("Operations on median not implemented for this database.")
        }
        aggregate = true
        // Falling back on calculating the median for each returned json array of values
        select += s"json_group_array($col)"
        if (!select.contains(col)) groupByCols -= col
        castStrategy.replace(
          (row: RS, paramIndex: Int) =>
            getMedian(jsonArray2doubles(row.getString(paramIndex)))
        )

      case "avg" =>
        aggregate = true
        selectWithOrder(col, "AVG", hasSort, "", "", "ROUND(", ", 10)")
        if (!select.contains(col)) groupByCols -= col
        havingOp(s"ROUND(AVG($col), 10)")


      case "variance" =>
        if (orderBy.nonEmpty && orderBy.last._3 == col) {
          throw ModelError("Sorting by variance not implemented for this database.")
        }
        if (aggrOp.isDefined) {
          throw ModelError("Operations on variance not implemented for this database.")
        }
        aggregate = true
        // Falling back on calculating the median for each returned json array of values
        select += s"json_group_array($col)"
        if (!select.contains(col)) groupByCols -= col
        havingOp(s"VAR_POP($col)")
        castStrategy.replace(
          (row: RS, paramIndex: Int) =>
            varianceOf(jsonArray2doubles(row.getString(paramIndex)))
        )

      case "stddev" =>
        if (orderBy.nonEmpty && orderBy.last._3 == col) {
          throw ModelError("Sorting by standard deviation not implemented for this database.")
        }
        if (aggrOp.isDefined) {
          throw ModelError("Operations on stddev not implemented for this database.")
        }
        aggregate = true
        select += s"json_group_array($col)"
        if (!select.contains(col)) groupByCols -= col
        havingOp(s"STDDEV_POP($col)")
        castStrategy.replace(
          (row: RS, paramIndex: Int) =>
            stdDevOf(jsonArray2doubles(row.getString(paramIndex)))
        )

      case other => unexpectedKw(other)
    }
  }


  def sampleSelect(
    ent: String, attr: String
  ): (List[(String, String, String, List[String])], Set[String]) => String = {
    (joins: List[(String, String, String, List[String])], groupCols: Set[String]) => {
      s"""(
         |    SELECT $attr
         |    FROM (
         |      SELECT distinct _$ent.$attr
         |      ${mkFrom(joins, groupCols)}
         |      ORDER BY RANDOM()
         |      LIMIT 1
         |    )
         |  ) AS ${ent}_${attr}_samples""".stripMargin
    }
  }

  def samplesSelect(
    ent: String, attr: String, n: Int
  ): (List[(String, String, String, List[String])], Set[String]) => String = {
    (joins: List[(String, String, String, List[String])], groupCols: Set[String]) => {
      s"""(
         |    SELECT JSON_GROUP_ARRAY($attr)
         |    FROM (
         |      SELECT distinct _$ent.$attr
         |      ${mkFrom(joins, groupCols)}
         |      ORDER BY RANDOM()
         |      LIMIT $n
         |    )
         |  ) AS ${ent}_${attr}_samples""".stripMargin
    }
  }

  def minMaxSelect(
    ent: String, attr: String, n: Int, dir: String
  ): (List[(String, String, String, List[String])], Set[String]) => String = {
    val fn = if (dir == "ASC") "_min" else "_max"
    (joins: List[(String, String, String, List[String])], groupCols: Set[String]) => {
      s"""(
         |    SELECT JSON_GROUP_ARRAY($attr)
         |    FROM (
         |      SELECT distinct _$ent.$attr
         |      ${mkFrom(joins, groupCols)}
         |      ORDER BY _$ent.$attr $dir
         |      LIMIT $n
         |    )
         |  ) AS ${ent}_$attr$fn""".stripMargin
    }
  }

  def mkFrom(
    joins: List[(String, String, String, List[String])],
    groupCols: Set[String]
  ): String = {
    val where = if (groupCols.isEmpty) "" else
      groupCols
        .map(ent_attr => s"_$from.${ent_attr.split('.')(1)} = $ent_attr")
        .mkString("\n      WHERE ", " AND\n        ", "")

    if (joins.isEmpty) {
      s"FROM $from AS _$from $where"
    } else {
      val joinTables = joins.map {
        case (join, table, as, predicates) =>
          val table_     = table + " AS _" + table
          val as_        = if (as.isEmpty) "" else " " + as
          val predicate_ = "_" + predicates.head.replace(" = ", " = _")
          s"$join $table_$as_ ON $predicate_"
      }.mkString("\n      ")

      s"""FROM $from AS _$from
         |      $joinTables $where"""
    }
  }
}