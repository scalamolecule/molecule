package molecule.sql.mariadb.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.sql.core.query.{ResolveExprOne, SqlQueryBase}
import scala.reflect.ClassTag
import scala.util.Random

trait ResolveExprOne_mariadb extends ResolveExprOne with LambdasOne_mariadb { self: SqlQueryBase =>


  override protected def addSort(attr: Attr, col: String): Unit = {
    attr.sort.foreach { sort =>
      val (dir, arity) = (sort.head, sort.substring(1, 2).toInt)
      dir match {
        case 'a' => orderBy += ((level, arity, col, ""))
        case 'd' => orderBy += ((level, arity, col, " DESC"))
      }
    }
  }

  override protected def matches(col: String, regex: String): Unit = {
    if (regex.nonEmpty)
      where += ((col, s"REGEXP '$regex'"))
  }


  override protected def aggr[T: ClassTag](
    col: String, fn: String, optN: Option[Int], res: ResOne[T]
  ): Unit = {
    checkAggrOne()
    lazy val sep     = "0x1D" // Use invisible ascii Group Selector to separate concatenated values
    lazy val sepChar = 29.toChar
    lazy val n       = optN.getOrElse(0)
    select -= col
    fn match {
      case "distinct" =>
        select += s"JSON_ARRAYAGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast((row: RS, paramIndex: Int) => res.json2array(row.getString(paramIndex)).toSet)

      case "min" =>
        select += s"MIN($col)"
        groupByCols -= col
        aggregate = true

      case "mins" =>
        select += s"GROUP_CONCAT(DISTINCT $col SEPARATOR $sep)"
        groupByCols -= col
        aggregate = true
        replaceCast((row: RS, paramIndex: Int) =>
          row.getString(paramIndex).split(sepChar).map(res.json2tpe).take(n).toSet
        )

      case "max" =>
        select += s"MAX($col)"
        groupByCols -= col
        aggregate = true

      case "maxs" =>
        select += s"GROUP_CONCAT(DISTINCT $col ORDER BY $col DESC SEPARATOR $sep)"
        groupByCols -= col
        aggregate = true
        replaceCast((row: RS, paramIndex: Int) =>
          row.getString(paramIndex).split(sepChar).map(res.json2tpe).take(n).toSet
        )

      case "sample" =>
        select += s"JSON_ARRAYAGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast((row: RS, paramIndex: Int) => {
          val array = res.json2array(row.getString(paramIndex))
          val rnd   = new Random().nextInt(array.length)
          array(rnd)
        })

      case "samples" =>
        select += s"JSON_ARRAYAGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast((row: RS, paramIndex: Int) => {
          val array = res.json2array(row.getString(paramIndex))
          Random.shuffle(array.toSet).take(n)
        })

      case "count" =>
        selectWithOrder(col, "COUNT", "")
        distinct = false
        groupByCols -= col
        aggregate = true
        replaceCast(toInt)

      case "countDistinct" =>
        selectWithOrder(col, "COUNT")
        distinct = false
        groupByCols -= col
        aggregate = true
        replaceCast(toInt)

      case "sum" =>
        selectWithOrder(col, "SUM", "")
        groupByCols -= col
        aggregate = true

      case "median" =>
        if (orderBy.nonEmpty && orderBy.last._3 == col) {
          throw ModelError("Sorting by median not implemented for this database.")
        }
        select += s"JSON_ARRAYAGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(
          (row: RS, paramIndex: Int) => {
            val json = row.getString(paramIndex)
            getMedian(json.substring(1, json.length - 1).split(",").map(_.toDouble).toList)
          }
        )

      // Implement native MariaDB median/variance/stddev methods

      //        selectWithOrder(col, "MEDIAN", "")
      //        groupByCols -= col
      //        aggregate = true

      case "avg" =>
        selectWithOrder(col, "AVG", "")
        groupByCols -= col
        aggregate = true

      case "variance" =>
        if (orderBy.nonEmpty && orderBy.last._3 == col) {
          throw ModelError("Sorting by variance not implemented for this database.")
        }
        groupByCols -= col
        aggregate = true
        select += s"JSON_ARRAYAGG($col)"
        replaceCast(
          (row: RS, paramIndex: Int) => {
            val json    = row.getString(paramIndex)
            val doubles = json.substring(1, json.length - 1).split(",").map(_.toDouble).toSeq
            varianceOf(doubles: _*)
          }
        )

      case "stddev" =>
        if (orderBy.nonEmpty && orderBy.last._3 == col) {
          throw ModelError("Sorting by standard deviation not implemented for this database.")
        }
        groupByCols -= col
        aggregate = true
        select += s"JSON_ARRAYAGG($col)"
        replaceCast(
          (row: RS, paramIndex: Int) => {
            val json    = row.getString(paramIndex)
            val doubles = json.substring(1, json.length - 1).split(",").map(_.toDouble).toSeq
            stdDevOf(doubles: _*)
          }
        )

      case other => unexpectedKw(other)
    }
  }
}