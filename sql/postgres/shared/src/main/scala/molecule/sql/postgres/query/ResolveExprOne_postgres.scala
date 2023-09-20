package molecule.sql.postgres.query

import molecule.boilerplate.ast.Model._
import molecule.sql.core.query.{ResolveExprOne, SqlQueryBase}

trait ResolveExprOne_postgres extends ResolveExprOne with LambdasOne_postgres { self: SqlQueryBase =>


  override protected def addSort(attr: Attr, col: String): Unit = {
    attr.sort.foreach { sort =>
      val (dir, arity) = (sort.head, sort.substring(1, 2).toInt)
      dir match {
        case 'a' => orderBy += ((level, arity, col, " NULLS FIRST"))
        case 'd' => orderBy += ((level, arity, col, " DESC NULLS LAST"))
      }
    }
  }

  //  private def startsWith[T](col: String, arg: T): Unit = where += ((col, s"LIKE '$arg%'"))
  //  private def endsWith[T](col: String, arg: T): Unit = where += ((col, s"LIKE '%$arg'"))
  //  private def contains[T](col: String, arg: T): Unit = where += ((col, s"LIKE '%$arg%'"))
  //  private def matches[T](col: String, regex: T): Unit = where += ((col, s"~ '$regex'"))
  //
  //  private def take[T](col: String, length: T, fn: String): Unit = {
  //    if (length.toString.toInt > 0) {
  //      select -= col
  //      notNull -= col
  //      val alias = col.replace('.', '_')
  //      select += s"$fn($col, $length) AS $alias"
  //      orderBy = orderBy.map {
  //        case (level, arity, `col`, dir) => (level, arity, alias, dir)
  //        case other                      => other
  //      }
  //    } else {
  //      // Take nothing
  //      where += (("FALSE", ""))
  //    }
  //  }
  //
  //  private def drop[T](col: String, arg: T, left: Boolean): Unit = {
  //    val i = arg.toString.toInt
  //    if (i > 0) {
  //      select -= col
  //      notNull -= col
  //      val alias           = col.replace('.', '_')
  //      val len             = s"LENGTH($col)"
  //      val (start, length) = if (left) (i + 1, len) else ("1", s"$len - $i")
  //      select += s"SUBSTRING($col, $start, $length) AS $alias"
  //      orderBy = orderBy.map {
  //        case (level, arity, `col`, dir) => (level, arity, alias, dir)
  //        case other                      => other
  //      }
  //      val clause = if (left) s"$len > $i" else s"$len > $i"
  //      where += ((clause, ""))
  //    } else {
  //      // Drop nothing
  //    }
  //  }
  //
  //  private def slice[T](col: String, args: Seq[T]): Unit = subString(col, args)
  //  private def subString[T](col: String, args: Seq[T]): Unit = {
  //    // 1-based string position
  //    val from  = args.head.toString.toInt.max(0) + 1
  //    val until = args(1).toString.toInt + 1
  //    if (from >= until) {
  //      where += (("FALSE", ""))
  //
  //    } else {
  //      select -= col
  //      notNull -= col
  //      val alias  = col.replace('.', '_')
  //      val len    = s"LENGTH($col)"
  //      val length = until - from
  //      select += s"SUBSTRING($col, $from, $length) AS $alias"
  //      orderBy = orderBy.map {
  //        case (level, arity, `col`, dir) => (level, arity, alias, dir)
  //        case other                      => other
  //      }
  //      where += ((s"$len >= $from", ""))
  //    }
  //  }
  //
  //  private def remainder[T](col: String, args: Seq[T]): Unit = where += ((col, s"% ${args.head} = ${args(1)}"))
  //  private def even(col: String): Unit = where += ((col, s"% 2 = 0"))
  //  private def odd(col: String): Unit = where += ((col, s"% 2 = 1"))

  private def cast(tpe: String): String = tpe match {
    case "Boolean" | "UUID" => "::text"
    case _                  => ""
  }

  override protected def aggr[T](col: String, fn: String, optN: Option[Int], res: ResOne[T]): Unit = {
    lazy val n = optN.getOrElse(0)
    // Replace find/casting with aggregate function/cast
    select -= col
    fn match {
      case "distinct" =>
        select += s"ARRAY_AGG(DISTINCT $col)"
        groupByCols -= col
        aggregate = true
        replaceCast(res.array2set)

      case "min" =>
        select += s"MIN($col${cast(res.tpe)})"
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
        select += s"MAX($col${cast(res.tpe)})"
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
        /*
        Using percentile_count or custom median function both calculate
        from non-distinct values (Array semantics instead of Set semantics).
        See https://wiki.postgresql.org/wiki/Aggregate_Median

        // Some attempts:

        _ <- rawQuery(
          """select
            |  (
            |     select
            |       percentile_cont(0.5) within group(order by x)
            |     from (
            |       select distinct Ns.bigDecimal as x
            |       from Ns
            |     ) t
            |  ) z
            |from Ns
            |group by z
            |""".stripMargin)

        _ <- rawQuery(
          """select
            |  Ns.i,
            |  (
            |     select
            |       percentile_cont(0.5) within group(order by x)
            |     from (
            |       select distinct _Ns.bigDecimal as x
            |       from Ns as _Ns
            |       where _Ns.i = Ns.i
            |     ) t
            |  )
            |from Ns
            |group by Ns.i
            |""".stripMargin)

        _ <- rawQuery(
          """select
            |  median(Ns.bigDecimal)
            |from Ns
            |""".stripMargin)

        _ <- rawQuery(
          """select
            |  Ns.i,
            |  median(Ns.bigDecimal)
            |from Ns
            |group by Ns.i
            |""".stripMargin)
        */
        // Using brute force instead (until someone has a better solution) and collecting
        // all distinct values to calculate the median value of unique values (Set semantics)
        select += s"ARRAY_AGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(
          (row: Row, n: Int) => {
            val resultSet = row.getArray(n).getResultSet
            var set       = Set.empty[Double]
            while (resultSet.next()) {
              set += resultSet.getDouble(2)
            }
            getMedian(set)
          }
        )

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

  //  private def selectWithOrder(col: String, fn: String, distinct: String = "DISTINCT "): Unit = {
  //    if (orderBy.nonEmpty && orderBy.last._3 == col) {
  //      // order by aggregate alias instead
  //      val alias = col.replace('.', '_') + "_" + fn.toLowerCase
  //      select += s"$fn($distinct$col) $alias"
  //      val (level, _, _, dir) = orderBy.last
  //      orderBy.remove(orderBy.size - 1)
  //      orderBy += ((level, 1, alias, dir))
  //    } else {
  //      select += s"$fn($distinct$col)"
  //    }
  //  }
  //
  //  private def equal[T: ClassTag](col: String, args: Seq[T], one2sql: T => String): Unit = {
  //    where += (args.length match {
  //      case 1 => (col, "= " + one2sql(args.head))
  //      case 0 => (col, "IS NULL ")
  //      case _ => (col, args.map(one2sql).mkString("IN (", ", ", ")"))
  //    })
  //  }
  //  private def equal2(col: String, filterAttr: String): Unit = {
  //    where += ((col, "= " + filterAttr))
  //  }
  //
  //  private def neq[T](col: String, args: Seq[T], one2sql: T => String): Unit = {
  //    where += (args.length match {
  //      case 1 => (col, "<> " + one2sql(args.head))
  //      case 0 => (col, "IS NOT NULL ")
  //      case _ => (col, args.map(one2sql).mkString("NOT IN (", ", ", ")"))
  //    })
  //  }
  //  private def neq2(col: String, filterAttr: String): Unit = {
  //    where += ((col, " != " + filterAttr))
  //  }
  //
  //  private def compare[T](col: String, arg: T, op: String, one2sql: T => String): Unit = {
  //    where += ((col, op + " " + one2sql(arg)))
  //  }
  //  private def compare2(col: String, op: String, filterAttr: String): Unit = {
  //    where += ((col, op + " " + filterAttr))
  //  }
  //
  //  private def noValue(col: String): Unit = {
  //    notNull -= col
  //    where += ((col, s"IS NULL"))
  //  }
  //
  //
  //  private def optEqual[T: ClassTag](
  //    col: String,
  //    optArgs: Option[Seq[T]],
  //    one2sql: T => String,
  //  ): Unit = {
  //    optArgs.fold[Unit] {
  //      where += ((col, s"IS NULL"))
  //    } {
  //      case Nil => where += (("FALSE", ""))
  //      case vs  => equal(col, vs, one2sql)
  //    }
  //  }
  //
  //  private def optNeq[T](
  //    col: String,
  //    optArgs: Option[Seq[T]],
  //    one2sql: T => String
  //  ): Unit = {
  //    if (optArgs.isDefined && optArgs.get.nonEmpty) {
  //      neq(col, optArgs.get, one2sql)
  //    } else {
  //      notNull += col
  //    }
  //  }
  //
  //  private def optCompare[T](
  //    col: String,
  //    optArgs: Option[Seq[T]],
  //    op: String,
  //    one2sql: T => String
  //  ): Unit = {
  //    optArgs.fold[Unit] {
  //      // Always return empty result when trying to compare None
  //      where += (("FALSE", ""))
  //    } { vs =>
  //      where += ((col, op + " " + one2sql(vs.head)))
  //    }
  //  }
}