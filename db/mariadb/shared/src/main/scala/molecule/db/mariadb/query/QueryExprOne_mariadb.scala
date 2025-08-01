package molecule.db.mariadb.query

import molecule.base.error.ModelError
import molecule.core.dataModel.*
import molecule.db.common.javaSql.PrepStmt
import molecule.db.common.query.{LambdasOne, Model2Query, QueryExprOne, SqlQueryBase}
import scala.reflect.ClassTag
import scala.util.Random

trait QueryExprOne_mariadb
  extends QueryExprOne
    with LambdasOne { self: Model2Query & SqlQueryBase =>

  override protected def matches[T](
    col: String,
    args: Seq[T],
    binding: Boolean,
    bind: (PrepStmt, Int, Int, Value) => Unit
  ): Unit = {
    if binding then {
      addBinding(col, bind, "REGEXP BINARY ?")
    } else {
      val regex = args.head.toString
      if (regex.nonEmpty)
        where += ((col, s"REGEXP BINARY '$regex'")) // "BINARY" makes it case-sensitive
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
    res: ResOne[T]
  ): Unit = {
    checkAggrOne()
    lazy val sep     = "0x1D" // Use invisible ascii Group Selector to separate concatenated values
    lazy val sepChar = 29.toChar
    lazy val n       = optN.getOrElse(0)
    def havingOp(expr: String) = addHaving(baseType, fn, expr, aggrOp, aggrOpValue, res)
    select -= col
    fn match {
      case "distinct" =>
        aggregate = true
        select += s"JSON_ARRAYAGG($col)"
        groupByCols -= col
        castStrategy.replace((row: RS, paramIndex: Int) =>
          res.json2array(row.getString(paramIndex)).toSet
        )

      case "min" =>
        aggregate = true
        select += s"MIN($col)"
        groupByCols -= col
        havingOp(s"MIN($col)")

      case "mins" =>
        aggregate = true
        select += s"GROUP_CONCAT(DISTINCT $col SEPARATOR $sep)"
        groupByCols -= col
        castStrategy.replace((row: RS, paramIndex: Int) =>
          row.getString(paramIndex).split(sepChar).map(res.json2tpe).take(n).toSet
        )

      case "max" =>
        aggregate = true
        select += s"MAX($col)"
        groupByCols -= col
        havingOp(s"MAX($col)")

      case "maxs" =>
        aggregate = true
        select += s"GROUP_CONCAT(DISTINCT $col ORDER BY $col DESC SEPARATOR $sep)"
        groupByCols -= col
        castStrategy.replace((row: RS, paramIndex: Int) =>
          row.getString(paramIndex).split(sepChar).map(res.json2tpe).take(n).toSet
        )

      case "sample" =>
        if (aggrOp.isDefined) {
          throw ModelError("Operations on sample not implemented for this database.")
        }
        aggregate = true
        select += s"JSON_ARRAYAGG($col)"
        groupByCols -= col
        havingOp("RAND()")
        castStrategy.replace((row: RS, paramIndex: Int) => {
          val array = res.json2array(row.getString(paramIndex))
          val rnd   = new Random().nextInt(array.length)
          array(rnd)
        })

      case "samples" =>
        aggregate = true
        select += s"JSON_ARRAYAGG($col)"
        groupByCols -= col
        castStrategy.replace((row: RS, paramIndex: Int) => {
          val array = res.json2array(row.getString(paramIndex))
          Random.shuffle(array.toSet).take(n)
        })

      case "count" =>
        aggregate = true
        distinct = false
        selectWithOrder(col, "COUNT", "")
        groupByCols -= col
        havingOp(s"COUNT($col)")
        castStrategy.replace(toInt)

      case "countDistinct" =>
        aggregate = true
        distinct = false
        selectWithOrder(col, "COUNT")
        groupByCols -= col
        havingOp(s"COUNT(DISTINCT $col)")
        castStrategy.replace(toInt)

      case "sum" =>
        aggregate = true
        selectWithOrder(col, "SUM", "", "", "ROUND(", ", 6)")
        groupByCols -= col
        havingOp(s"ROUND(SUM($col), 6)")

      case "median" =>
        if (orderBy.nonEmpty && orderBy.last._3 == col) {
          throw ModelError("Sorting by median not implemented for this database.")
        }
        if (aggrOp.isDefined) {
          throw ModelError("Operations on median not implemented for this database.")
        }
        aggregate = true
        select += s"JSON_ARRAYAGG($col)"
        groupByCols -= col
        castStrategy.replace(
          (row: RS, paramIndex: Int) => {
            val json = row.getString(paramIndex)
            getMedian(json.substring(1, json.length - 1).split(",").map(_.toDouble).toList)
          }
        )

      case "avg" =>
        aggregate = true
        selectWithOrder(col, "AVG", "", "", "ROUND(", ", 6)")
        groupByCols -= col
        havingOp(s"ROUND(AVG($col), 6)")

      case "variance" =>
        if (orderBy.nonEmpty && orderBy.last._3 == col) {
          throw ModelError("Sorting by variance not implemented for this database.")
        }
        if (aggrOp.isDefined) {
          throw ModelError("Operations on variance not implemented for this database.")
        }
        aggregate = true
        select += s"JSON_ARRAYAGG($col)"
        groupByCols -= col
        havingOp(s"VAR_POP($col)")
        castStrategy.replace(
          (row: RS, paramIndex: Int) => {
            val json = row.getString(paramIndex)
            varianceOf(json.substring(1, json.length - 1).split(",").map(_.toDouble).toSeq)
          }
        )

      case "stddev" =>
        if (orderBy.nonEmpty && orderBy.last._3 == col) {
          throw ModelError("Sorting by standard deviation not implemented for this database.")
        }
        if (aggrOp.isDefined) {
          throw ModelError("Operations on stddev not implemented for this database.")
        }
        aggregate = true
        select += s"JSON_ARRAYAGG($col)"
        groupByCols -= col
        havingOp(s"STDDEV_POP($col)")
        castStrategy.replace(
          (row: RS, paramIndex: Int) => {
            val json = row.getString(paramIndex)
            stdDevOf(json.substring(1, json.length - 1).split(",").map(_.toDouble).toSeq)
          }
        )

      case other => unexpectedKw(other)
    }
  }
}