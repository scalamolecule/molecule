package molecule.sql.sqlite.query

import molecule.base.error.ModelError
import molecule.sql.core.query.{ResolveExprOne, SqlQueryBase}
import scala.reflect.ClassTag

trait ResolveExprOne_sqlite extends ResolveExprOne with LambdasOne_sqlite { self: SqlQueryBase =>

  override protected def aggr[T: ClassTag](
    col: String, fn: String, optN: Option[Int], res: ResOne[T]
  ): Unit = {
    checkAggrOne()
    lazy val n = optN.getOrElse(0)
    // Replace find/casting with aggregate function/cast
    select -= col
    //    lazy val colAlias = col.replace('.', '_')

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
        replaceCast((row: RS, paramIndex: Int) =>
          res.json2array(row.getString(paramIndex)).toSet
        )

      case "max" =>
        select += s"MAX($col)"
        groupByCols -= col
        aggregate = true

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
        replaceCast((row: RS, paramIndex: Int) =>
          res.json2array(row.getString(paramIndex)).toSet
        )

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
}