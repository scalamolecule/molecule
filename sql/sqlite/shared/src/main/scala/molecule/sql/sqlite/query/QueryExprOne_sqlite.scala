package molecule.sql.sqlite.query

import molecule.base.error.ModelError
import molecule.sql.core.query.{QueryExprOne, SqlQueryBase}
import scala.reflect.ClassTag

trait QueryExprOne_sqlite extends QueryExprOne with LambdasOne_sqlite { self: SqlQueryBase =>


  override protected def matches(col: String, regex: String): Unit = {
    if (regex.nonEmpty)
      where += ((col, s"REGEXP '$regex'"))
  }

  override protected def aggr[T: ClassTag](
    ns: String,
    attr: String,
    col: String,
    fn: String,
    optN: Option[Int],
    res: ResOne[T]
  ): Unit = {
    checkAggrOne()
    lazy val n = optN.getOrElse(0)
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
        select += s"json_group_array(DISTINCT $col)"
        groupByCols -= col
        aggregate = true
        replaceCast((row: RS, paramIndex: Int) =>
          res.json2array(row.getString(paramIndex)).toSet
        )

      case "min" =>
        select += s"MIN($col)"
        groupByCols -= col
        aggregate = true

      case "mins" =>
        select2 = select2 + (select.length -> minMaxSelect(ns, attr, n, "ASC"))
        select += "<replace>" // add to maintain correct indexing
        groupByCols -= col
        aggregate = true
        replaceCast((row: RS, paramIndex: Int) =>
          res.json2array(row.getString(paramIndex)).toSet
        )

      case "max" =>
        select += s"MAX($col)"
        groupByCols -= col
        aggregate = true

      case "maxs" =>
        select2 = select2 + (select.length -> minMaxSelect(ns, attr, n, "DESC"))
        select += "<replace>" // add to maintain correct indexing
        groupByCols -= col
        aggregate = true
        replaceCast((row: RS, paramIndex: Int) =>
          res.json2array(row.getString(paramIndex)).toSet
        )

      case "sample" =>
        select2 = select2 + (select.length -> sampleSelect(ns, attr))
        select += "<replace>" // add to maintain correct indexing

      case "samples" =>
        select2 = select2 + (select.length -> samplesSelect(ns, attr, n))
        select += "<replace>" // add to maintain correct indexing
        groupByCols -= col
        aggregate = true
        replaceCast((row: RS, paramIndex: Int) =>
          res.json2array(row.getString(paramIndex)).toSet
        )

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
        selectWithOrder(col, "SUM", "")

      case "median" =>
        //        select += s"AVG(_$col)"
        //        groupByCols -= col
        //        aggregate = true
        // works but incurs a lot of extra complexity when combined with other attributes
        //        val subSelect =
        //          s"""(
        //             |    SELECT $col
        //             |    FROM Ns
        //             |    ORDER BY $col
        //             |    LIMIT 2 - (SELECT COUNT(*) FROM Ns) % 2
        //             |    OFFSET (SELECT (COUNT(*) - 1) / 2 FROM Ns)
        //             |  )""".stripMargin
        //        joins += (("INNER JOIN", subSelect, "_Ns", "", ""))

        // There should be a solution using percent_rank ...
        //        selectWithOrder(col, "percent_rank", "0.5) WITHIN GROUP (ORDER BY ")
        //        select += s"percentile_cont(0.5) within group (order by salary) over (partition by a.company_name) as median"
        if (orderBy.nonEmpty && orderBy.last._3 == col) {
          throw ModelError("Sorting by median not implemented for this database.")
        }
        // Falling back on calculating the median for each returned json array of values
        select += s"json_group_array($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(
          (row: RS, paramIndex: Int) =>
            getMedian(jsonArray2doubles(row.getString(paramIndex)))
        )

      case "avg" =>
        groupByCols -= col
        aggregate = true
        selectWithOrder(col, "AVG", "")

      case "variance" =>
        if (orderBy.nonEmpty && orderBy.last._3 == col) {
          throw ModelError("Sorting by variance not implemented for this database.")
        }
        // Falling back on calculating the median for each returned json array of values
        select += s"json_group_array($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(
          (row: RS, paramIndex: Int) =>
            varianceOf(jsonArray2doubles(row.getString(paramIndex)))
        )

      case "stddev" =>
        if (orderBy.nonEmpty && orderBy.last._3 == col) {
          throw ModelError("Sorting by standard deviation not implemented for this database.")
        }
        groupByCols -= col
        aggregate = true
        select += s"json_group_array($col)"
        replaceCast(
          (row: RS, paramIndex: Int) =>
            stdDevOf(jsonArray2doubles(row.getString(paramIndex)))
        )

      case other => unexpectedKw(other)
    }
  }


  def sampleSelect(
    ns: String, attr: String
  ): (List[(String, String, String, String, String)], Set[String]) => String = {
    (joins: List[(String, String, String, String, String)], groupCols: Set[String]) => {
      s"""(
         |    SELECT $attr
         |    FROM (
         |      SELECT distinct _$ns.$attr
         |      ${mkFrom(joins, groupCols)}
         |      ORDER BY RANDOM()
         |      LIMIT 1
         |    )
         |  ) AS ${ns}_${attr}_samples""".stripMargin
    }
  }

  def samplesSelect(
    ns: String, attr: String, n: Int
  ): (List[(String, String, String, String, String)], Set[String]) => String = {
    (joins: List[(String, String, String, String, String)], groupCols: Set[String]) => {
      s"""(
         |    SELECT JSON_GROUP_ARRAY($attr)
         |    FROM (
         |      SELECT distinct _$ns.$attr
         |      ${mkFrom(joins, groupCols)}
         |      ORDER BY RANDOM()
         |      LIMIT $n
         |    )
         |  ) AS ${ns}_${attr}_samples""".stripMargin
    }
  }

  def minMaxSelect(
    ns: String, attr: String, n: Int, dir: String
  ): (List[(String, String, String, String, String)], Set[String]) => String = {
    val fn = if (dir == "ASC") "_min" else "_max"
    (joins: List[(String, String, String, String, String)], groupCols: Set[String]) => {
      s"""(
         |    SELECT JSON_GROUP_ARRAY($attr)
         |    FROM (
         |      SELECT distinct _$ns.$attr
         |      ${mkFrom(joins, groupCols)}
         |      ORDER BY _$ns.$attr $dir
         |      LIMIT $n
         |    )
         |  ) AS ${ns}_$attr$fn""".stripMargin
    }
  }

  def mkFrom(
    joins: List[(String, String, String, String, String)],
    groupCols: Set[String]
  ): String = {
    val where = if (groupCols.isEmpty) "" else
      groupCols
        .map(ns_attr => s"_$from.${ns_attr.split('.')(1)} = $ns_attr")
        .mkString("\n      WHERE ", " AND\n        ", "")

    if(joins.isEmpty) {
      s"FROM $from AS _$from $where"
    } else{
      val max1  = joins.map(_._1.length).max
      val max2  = joins.map(_._2.length).max
      val max3  = joins.map(_._3.length).max
      val max4  = joins.map(_._4.length).max + 1
      val hasAs = joins.exists(_._3.nonEmpty)
      val joinTables = joins.map {
        case (join, table, as, lft, rgt) =>
          val join_     = join + padS(max1, join)
          val table_    = table + padS(max2, table) + " AS _" + table
          val as_       = if (hasAs) {
            if (as.isEmpty) padS(max3 + 4, "") else " AS " + as + padS(max3, as)
          } else ""
          val predicate = "_" + lft + padS(max4, lft) + "= _" + rgt.drop(2) // really hackish
          s"$join_ $table_$as_ ON $predicate"
      }.mkString("\n      ")

      s"""FROM $from AS _$from
         |      $joinTables $where"""
    }
  }
}