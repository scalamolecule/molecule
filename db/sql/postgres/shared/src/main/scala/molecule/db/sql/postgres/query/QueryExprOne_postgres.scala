package molecule.db.sql.postgres.query

import molecule.core.ast.*
import molecule.db.core.query.Model2Query
import molecule.db.sql.core.javaSql.PrepStmt
import molecule.db.sql.core.query.{QueryExprOne, SqlQueryBase}
import scala.reflect.ClassTag

trait QueryExprOne_postgres
  extends QueryExprOne
    with LambdasOne_postgres { self: Model2Query & SqlQueryBase =>

  override protected def equal[T](
    col: String,
    args: Seq[T],
    one2sql: T => String,
    binding: Boolean,
    bind: (PrepStmt, Int, Int, Any) => Unit,
    tpe: String
  ): Unit = {
    if binding then {
      tpe match {
        case "Float" =>
          // Hack to handle floating-point precision
          // Highly recommended to use Double instead
          addBinding(s"ROUND(Entity.float::NUMERIC, 7)", bind, "= ROUND(?::NUMERIC, 7)")
        case "UUID"  =>
          addBinding(col, bind, "= CAST(? AS UUID)")
        case _       =>
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
    binding: Boolean = false,
    bind: (PrepStmt, Int, Int, Any) => Unit = null,
    tpe: String = ""
  ): Unit = {
    if binding then {
      tpe match {
        case "Float" =>
          // Hack to handle floating-point precision
          // Highly recommended to use Double instead
          addBinding(s"ROUND(Entity.float::NUMERIC, 7)", bind, "<> ROUND(?::NUMERIC, 7)")
        case "UUID"  =>
          addBinding(col, bind, "<> CAST(? AS UUID)")
        case _       =>
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
    binding: Boolean = false,
    bind: (PrepStmt, Int, Int, Any) => Unit = null,
    tpe: String = ""
  ): Unit = {
    if binding then {
      tpe match {
        case "Float" =>
          // Hack to handle floating-point precision
          // Highly recommended to use Double instead
          addBinding(s"ROUND(Entity.float::NUMERIC, 7)", bind, s"$op ROUND(?::NUMERIC, 7)")
        case "UUID"  =>
          addBinding(col, bind, s"$op CAST(? AS UUID)")
        case _       =>
          addBinding(col, bind, s"$op ?")
      }
    } else {
      where += ((col, op + " " + one2sql(args.head)))
    }
  }

  override protected def addSort(attr: AttrOne, col: String): Unit = {
    attr.sort.foreach { sort =>
      val (dir, arity) = (sort.head, sort.substring(1, 2).toInt)
      dir match {
        case 'a' => orderBy += ((level, arity, col, " NULLS FIRST"))
        case 'd' => orderBy += ((level, arity, col, " DESC NULLS LAST"))
      }
    }
  }

  private def castText(tpe: String): String = tpe match {
    case "Boolean" | "UUID" => "::text"
    case _                  => ""
  }

  override protected def aggr[T: ClassTag](
    ent: String,
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
    fn match {
      case "distinct" =>
        select += s"ARRAY_AGG(DISTINCT $col)"
        groupByCols -= col
        aggregate = true
        castStrategy.replace(res.array2set)

      case "min" =>
        select += s"MIN($col${castText(res.tpe)})"
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
        castStrategy.replace(res.array2set)

      case "max" =>
        select += s"MAX($col${castText(res.tpe)})"
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
        castStrategy.replace(res.array2set)

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
        castStrategy.replace(res.array2set)

      case "count" =>
        distinct = false
        groupByCols -= col
        aggregate = true
        selectWithOrder(col, "COUNT", "")
        castStrategy.replace(toInt)

      case "countDistinct" =>
        distinct = false
        groupByCols -= col
        aggregate = true
        selectWithOrder(col, "COUNT")
        castStrategy.replace(toInt)

      case "sum" =>
        groupByCols -= col
        aggregate = true
        selectWithOrder(col, "SUM", "")

      case "median" =>
        groupByCols -= col
        aggregate = true
        selectWithOrder(col, "percentile_cont", "0.5) WITHIN GROUP (ORDER BY ")

      case "avg" =>
        groupByCols -= col
        aggregate = true
        selectWithOrder(col, "AVG", "")

      case "variance" =>
        groupByCols -= col
        aggregate = true
        selectWithOrder(col, "VAR_POP", "")

      case "stddev" =>
        groupByCols -= col
        aggregate = true
        selectWithOrder(col, "STDDEV_POP", "")

      case other => unexpectedKw(other)
    }
  }
}