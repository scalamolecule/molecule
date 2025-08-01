package molecule.db.postgresql.query

import molecule.core.dataModel.*
import molecule.db.common.javaSql.PrepStmt
import molecule.db.common.query.{Model2Query, QueryExprOne, SqlQueryBase}
import scala.reflect.ClassTag

trait QueryExprOne_postgresql
  extends QueryExprOne
    with LambdasOne_postgresql { self: Model2Query & SqlQueryBase =>

  override protected def equal[T](
    col: String,
    args: Seq[T],
    one2sql: T => String,
    binding: Boolean,
    bind: (PrepStmt, Int, Int, Value) => Unit,
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
    bind: (PrepStmt, Int, Int, Value) => Unit = null,
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
    bind: (PrepStmt, Int, Int, Value) => Unit = null,
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
    lazy val n        = optN.getOrElse(0)
    lazy val castText = res.tpe match {
      case "Boolean" | "UUID" => "::text"
      case _                  => ""
    }
    def havingOp(expr: String) = addHaving(baseType, fn, expr, aggrOp, aggrOpValue, res)

    lazy val castAggrOpV = getCast(aggrOpValue, res)

    // Replace find/casting with aggregate function/cast
    select -= col
    fn match {
      case "distinct" =>
        aggregate = true
        select += s"ARRAY_AGG(DISTINCT $col)"
        groupByCols -= col
        castStrategy.replace(res.array2set)

      case "min" =>
        aggregate = true
        select += s"MIN($col$castText)"
        groupByCols -= col
        havingOp(s"MIN($col$castAggrOpV)")

      case "mins" =>
        aggregate = true
        select +=
          s"""TRIM_ARRAY(
             |    ARRAY_AGG(DISTINCT $col order by $col ASC),
             |    GREATEST(
             |      0,
             |      ARRAY_LENGTH(ARRAY_AGG(DISTINCT $col), 1) - $n
             |    )
             |  )""".stripMargin
        groupByCols -= col
        castStrategy.replace(res.array2set)

      case "max" =>
        aggregate = true
        select += s"MAX($col$castText)"
        groupByCols -= col
        havingOp(s"MAX($col$castAggrOpV)")

      case "maxs" =>
        aggregate = true
        select +=
          s"""TRIM_ARRAY(
             |    ARRAY_AGG(DISTINCT $col order by $col DESC),
             |    GREATEST(
             |      0,
             |      ARRAY_LENGTH(ARRAY_AGG(DISTINCT $col), 1) - $n
             |    )
             |  )""".stripMargin
        groupByCols -= col
        castStrategy.replace(res.array2set)

      case "sample" =>
        distinct = false
        select += col
        addWhere(col, aggrOp, aggrOpValue, res)
        orderBy += ((level, -1, "RANDOM()", ""))
        hardLimit = 1

      case "samples" =>
        aggregate = true
        select +=
          s"""TRIM_ARRAY(
             |    ARRAY_AGG($col order by random()),
             |    GREATEST(
             |      0,
             |      ARRAY_LENGTH(ARRAY_AGG(DISTINCT $col), 1) - $n
             |    )
             |  )""".stripMargin
        groupByCols -= col
        castStrategy.replace(res.array2set)

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
        groupByCols -= col
        selectWithOrder(col, "SUM", "", "", "ROUND(", s"$castAggrOpV, 10)")
        addHaving(baseType, fn, s"ROUND(SUM($col)$castAggrOpV, 10)", aggrOp, aggrOpValue, res, "ROUND(", s"$castAggrOpV, 10)")

      case "median" =>
        aggregate = true
        groupByCols -= col
        aggrOp.fold {
          selectWithOrder(col, "percentile_cont", "0.5) WITHIN GROUP (ORDER BY ")
        } { op =>
          selectWithOrder(col, "percentile_cont(0.5) WITHIN GROUP (ORDER BY ", "", "", "ROUND(", s")$castAggrOpV, 10)")
          addHaving(baseType, fn, s"ROUND(percentile_cont(0.5) WITHIN GROUP (ORDER BY $col)$castAggrOpV, 10)",
            aggrOp, aggrOpValue, res, "ROUND(", s"$castAggrOpV, 10)")
        }

      case "avg" =>
        aggregate = true
        selectWithOrder(col, "AVG", "", "", "ROUND(", s"$castAggrOpV, 10)")
        groupByCols -= col
        addHaving(baseType, fn, s"ROUND(AVG($col)$castAggrOpV, 10)", aggrOp, aggrOpValue, res, "ROUND(", s"$castAggrOpV, 10)")

      case "variance" =>
        aggregate = true
        selectWithOrder(col, "VAR_POP", "", "", "ROUND(", s"$castAggrOpV, 10)")
        groupByCols -= col
        addHaving(baseType, fn, s"ROUND(VAR_POP($col)$castAggrOpV, 10)", aggrOp, aggrOpValue, res, "ROUND(", s"$castAggrOpV, 10)")

      case "stddev" =>
        aggregate = true
        selectWithOrder(col, "STDDEV_POP", "")
        groupByCols -= col
        addHaving(baseType, fn, s"ROUND(STDDEV_POP($col)$castAggrOpV, 10)", aggrOp, aggrOpValue, res, "ROUND(", s"$castAggrOpV, 10)")

      case other => unexpectedKw(other)
    }
  }

  private def getCast[T: ClassTag](aggrOpValue: Option[Value], res: ResOne[T]): String = {
    aggrOpValue.fold(
      res.tpe match {
        case "Double"     => "::NUMERIC"
        case "BigDecimal" => "::DECIMAL"
        case _            => ""
      }
    ) {
      case OneDouble(_)     => "::DECIMAL"
      case OneBigDecimal(_) => "::DECIMAL"
      case OneBoolean(_)    => "::text"
      case OneUUID(_)       => "::text"
      case _                => ""
    }
  }
}