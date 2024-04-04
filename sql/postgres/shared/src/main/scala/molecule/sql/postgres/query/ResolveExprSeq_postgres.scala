package molecule.sql.postgres.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.sql.core.query.{ResolveExprSeq, SqlQueryBase}
import scala.collection.mutable.ListBuffer
import scala.reflect.ClassTag
import scala.util.Random


trait ResolveExprSeq_postgres
  extends ResolveExprSeq
    with LambdasSeq_postgres { self: SqlQueryBase =>


  //  override protected def setMan[T](
  //    attr: Attr, tpe: String, args: Seq[Set[T]], res: ResSet[T]
  //  ): Unit = {
  //    val col = getCol(attr: Attr)
  //    select += col
  //    groupByCols += col // if we later need to group by non-aggregated columns
  //
  //    if (isNestedOpt) {
  //      addCast(res.sql2setOrNull)
  //    } else {
  //      addCast(res.sql2set)
  //      notNull += col
  //    }
  //
  //    attr.filterAttr.fold {
  //      val pathAttr = path :+ attr.cleanAttr
  //      if (filterAttrVars.contains(pathAttr) && attr.op != V) {
  //        // Runtime check needed since we can't type infer it
  //        noCardManyFilterAttrExpr(attr)
  //      }
  //      setExpr(col, attr.op, args, res, true)
  //    } {
  //      case (dir, filterPath, filterAttr) => filterAttr match {
  //        case filterAttr: AttrOne => setExpr2(col, attr.op, filterAttr.name, true, tpe, res, true)
  //        case filterAttr          => setExpr2(col, attr.op, filterAttr.name, false, tpe, res, true)
  //      }
  //    }
  //  }
  //
  //
  //  // attr ----------------------------------------------------------------------
  //
  //  override protected def setAttr[T](
  //    col: String, res: ResSet[T], mandatory: Boolean
  //  ): Unit = {
  //    coalesce(col, res, if (mandatory) "man" else "tac")
  //    having += "COUNT(*) > 0"
  //  }
  //
  //  override protected def setOptAttr[T](col: String, res: ResSet[T]): Unit = {
  //    coalesce(col, res, "opt")
  //  }
  //
  //
  //  // eq ------------------------------------------------------------------------
  //
  //  override protected def setEqual[T](col: String, sets: Seq[Set[T]], res: ResSet[T]): Unit = {
  //    val setsNonEmpty = sets.filterNot(_.isEmpty)
  //    setsNonEmpty.length match {
  //      case 0 => where += (("FALSE", ""))
  //      case 1 => where += (("", matchArray((res.set2sqlArray(setsNonEmpty.head), setsNonEmpty.head.size), col)))
  //      case _ => where += (("", matchArrays(setsNonEmpty, col, res.set2sqlArray)))
  //    }
  //  }
  //
  //  override protected def setOptEqual[T](
  //    col: String, optSets: Option[Seq[Set[T]]], res: ResSet[T]
  //  ): Unit = {
  //    optSets.fold[Unit] {
  //      where += ((col, s"IS NULL"))
  //    } { sets =>
  //      setEqual(col, sets, res)
  //      coalesce(col, res, "opt")
  //    }
  //  }
  //
  //
  //  // neq -----------------------------------------------------------------------
  //
  //  override protected def setNeq[T](
  //    col: String, sets: Seq[Set[T]], res: ResSet[T], mandatory: Boolean
  //  ): Unit = {
  //    coalesce(col, res, if (mandatory) "man" else "tac")
  //    val setsNonEmpty = sets.filterNot(_.isEmpty)
  //    setsNonEmpty.length match {
  //      case 0 => notNull += col
  //      case 1 => where += (("", "NOT " +
  //        matchArray((res.set2sqlArray(setsNonEmpty.head), setsNonEmpty.head.size), col)))
  //      case _ => where += (("", "NOT " +
  //        matchArrays(setsNonEmpty, col, res.set2sqlArray)))
  //    }
  //  }
  //
  //
  //  override protected def setOptNeq[T](
  //    col: String, optSets: Option[Seq[Set[T]]], res: ResSet[T]
  //  ): Unit = {
  //    optSets match {
  //      case None | Some(Nil) =>
  //        setOptAttr(col, res)
  //      case Some(sets) =>
  //        setNeq(col, sets, res, true)
  //        replaceCast(res.array2optSet)
  //    }
  //    // Only asserted values
  //    notNull += col
  //  }
  //
  //
  //  // has -----------------------------------------------------------------------
  //
  //  override protected def has[T](
  //    col: String, sets: Seq[Set[T]], res: ResSet[T], one2sql: T => String, mandatory: Boolean
  //  ): Unit = {
  //    def contains(v: T): String = s"${one2sql(v)} = ANY($col)"
  //    def containsSet(set: Set[T]): String = set.map(contains).mkString(" AND ")
  //    coalesce(col, res, if (mandatory) "man" else "tac")
  //    sets.length match {
  //      case 0 =>
  //        where += (("FALSE", ""))
  //      case 1 =>
  //        val set = sets.head
  //        set.size match {
  //          case 0 => where += (("FALSE", ""))
  //          case 1 => where += (("", contains(set.head)))
  //          case _ => where += (("", containsSet(set)))
  //        }
  //
  //      case _ =>
  //        val expr = sets
  //          .filterNot(_.isEmpty)
  //          .map(containsSet).mkString("(\n    ", " OR\n    ", "\n  )")
  //        where += (("", expr))
  //    }
  //
  //  }
  //
  //  override protected def optHas[T](
  //    col: String,
  //    optSets: Option[Seq[Set[T]]],
  //    res: ResSet[T],
  //    one2sql: T => String,
  //  ): Unit = {
  //    optSets.fold[Unit] {
  //      where += ((col, s"IS NULL"))
  //    } { sets =>
  //      val setsWithValues = sets.filterNot(_.isEmpty)
  //      if (setsWithValues.nonEmpty) {
  //        has(col, sets, res, one2sql, true)
  //        replaceCast(res.array2optSet)
  //      } else {
  //        where += (("FALSE", ""))
  //      }
  //    }
  //  }
  //
  //
  //  // hasNo ---------------------------------------------------------------------
  //
  //  override protected def hasNo[T](
  //    col: String, sets: Seq[Set[T]], res: ResSet[T], one2sql: T => String, mandatory: Boolean
  //  ): Unit = {
  //    def notContains(v: T): String = s"${one2sql(v)} != ALL($col)"
  //    def notContainsSet(set: Set[T]): String = set.map(notContains).mkString("(", " OR ", ")")
  //    coalesce(col, res, if (mandatory) "man" else "tac")
  //    sets.length match {
  //      case 0 => ()
  //      case 1 =>
  //        val set = sets.head
  //        set.size match {
  //          case 0 => ()
  //          case 1 => where += (("", notContains(set.head)))
  //          case _ => where += (("", notContainsSet(set)))
  //        }
  //      case _ =>
  //        val expr = sets
  //          .filterNot(_.isEmpty)
  //          .map(notContainsSet)
  //          .mkString("(\n    ", " AND\n    ", "\n  )")
  //        where += (("", expr))
  //    }
  //  }
  //
  //  override protected def optHasNo[T](
  //    col: String,
  //    optSets: Option[Seq[Set[T]]],
  //    res: ResSet[T],
  //    one2sql: T => String
  //  ): Unit = {
  //    optSets.fold[Unit] {
  //      setOptAttr(col, res)
  //    } { sets =>
  //      hasNo(col, sets, res, one2sql, true)
  //      coalesce(col, res, "opt")
  //      replaceCast(res.array2optSet)
  //
  //    }
  //    // Only asserted values
  //    notNull += col
  //  }
  //
  //
  //  // aggregation ---------------------------------------------------------------
  //
  //  override protected def setAggr[T](
  //    col: String, fn: String, optN: Option[Int], res: ResSet[T]
  //  ): Unit = {
  //    checkAggrSet()
  //    select -= col
  //    lazy val colAlias           = col.replace('.', '_')
  //    lazy val n                  = optN.getOrElse(0)
  //    lazy val invisibleSeparator = 29.toChar
  //
  //    fn match {
  //      case "distinct" =>
  //        noBooleanSetAggr(res)
  //        select += s"ARRAY_AGG(DISTINCT ARRAY_TO_STRING($col, CHR(29)))"
  //        groupByCols -= col
  //        aggregate = true
  //        replaceCast(
  //          (row: RS, paramIndex: Int) => {
  //            row.getArray(paramIndex).getArray.asInstanceOf[Array[String]]
  //              .map(jsonArray =>
  //                jsonArray.split(invisibleSeparator).toSet // separate values in each array/set
  //                  .map(res.json2tpe) // cast each value
  //              ).toSet
  //          }
  //        )
  //
  //      case "min" =>
  //        noBooleanSetAggr(res)
  //        select += s"MIN($col)"
  //        groupByCols -= col
  //        aggregate = true
  //        replaceCast(res.array2setFirst)
  //
  //      case "mins" =>
  //        noBooleanSetAggr(res)
  //        select += s"ARRAY_AGG($colAlias)"
  //        tempTables += s"UNNEST($col) AS $colAlias"
  //        groupByCols -= col
  //        aggregate = true
  //        replaceCast(res.sqlArray2minN(n))
  //
  //      case "max" =>
  //        noBooleanSetAggr(res)
  //        select += s"MAX($col)"
  //        groupByCols -= col
  //        aggregate = true
  //        replaceCast(res.array2setLast)
  //
  //      case "maxs" =>
  //        noBooleanSetAggr(res)
  //        select += s"ARRAY_AGG($colAlias)"
  //        tempTables += s"UNNEST($col) AS $colAlias"
  //        groupByCols -= col
  //        aggregate = true
  //        replaceCast(res.sqlArray2maxN(n))
  //
  //      case "sample" =>
  //        noBooleanSetAggr(res)
  //        distinct = false
  //        select += col
  //        orderBy += ((level, -1, "RANDOM()", ""))
  //        hardLimit = 1
  //        replaceCast(res.sql2set)
  //
  //      case "samples" =>
  //        noBooleanSetAggr(res)
  //        select += s"ARRAY_AGG(DISTINCT ARRAY_TO_STRING($col, CHR(29)))"
  //        groupByCols -= col
  //        aggregate = true
  //        replaceCast(
  //          (row: RS, paramIndex: Int) => {
  //            val sets = row.getArray(paramIndex).getArray.asInstanceOf[Array[String]]
  //              .flatMap(jsonArray =>
  //                jsonArray.split(invisibleSeparator).toSet // separate values in each array/set
  //                  .map(res.json2tpe) // cast each value
  //              ).toSet
  //            Random.shuffle(sets).take(n)
  //          }
  //        )
  //
  //      case "count" =>
  //        noBooleanSetCounts(n)
  //        // Count of all (non-unique) values
  //        select += s"ARRAY_AGG($colAlias)"
  //        tempTables += s"UNNEST($col) AS $colAlias"
  //        groupByCols -= col
  //        aggregate = true
  //        replaceCast(
  //          (row: RS, paramIndex: Int) => {
  //            val resultSet = row.getArray(paramIndex).getResultSet
  //            var count     = 0
  //            while (resultSet.next()) {
  //              count += 1
  //            }
  //            count
  //          }
  //        )
  //
  //      case "countDistinct" =>
  //        noBooleanSetCounts(n)
  //        select += s"ARRAY_AGG(DISTINCT $colAlias)"
  //        tempTables += s"UNNEST($col) AS $colAlias"
  //        groupByCols -= col
  //        aggregate = true
  //        replaceCast(
  //          (row: RS, paramIndex: Int) => {
  //            val resultSet = row.getArray(paramIndex).getResultSet
  //            var count     = 0
  //            while (resultSet.next()) {
  //              count += 1
  //            }
  //            count
  //          }
  //        )
  //
  //      case "sum" =>
  //        select += s"ARRAY_AGG($colAlias)"
  //        tempTables += s"UNNEST($col) AS $colAlias"
  //        groupByCols -= col
  //        aggregate = true
  //        replaceCast((row: RS, paramIndex: Int) => {
  //          val json = row.getString(paramIndex)
  //          if (row.wasNull()) {
  //            Set.empty[T]
  //          } else {
  //            Set(
  //              res.stringArray2sum(
  //                json.substring(1, json.length - 1).split("\\]?, ?\\[?")
  //              )
  //            )
  //          }
  //        })
  //
  //      case "median" =>
  //        select += s"ARRAY_AGG($colAlias)"
  //        tempTables += s"UNNEST($col) AS $colAlias"
  //        groupByCols -= col
  //        aggregate = true
  //        replaceCast((row: RS, paramIndex: Int) =>
  //          getMedian(res.json2array(row.getString(paramIndex))
  //            .map(_.toString.toDouble).toList)
  //        )
  //
  //      case "avg" =>
  //        select += s"ARRAY_AGG($colAlias)"
  //        tempTables += s"UNNEST($col) AS $colAlias"
  //        groupByCols -= col
  //        aggregate = true
  //        replaceCast(
  //          (row: RS, paramIndex: Int) => {
  //            val resultSet = row.getArray(paramIndex).getResultSet
  //            val list      = ListBuffer.empty[Double]
  //            while (resultSet.next()) {
  //              list ++= res.json2array(row.getString(paramIndex)).map(_.toString.toDouble).toList
  //            }
  //            list.sum / list.size
  //          }
  //        )
  //
  //      case "variance" =>
  //        select += s"ARRAY_AGG($colAlias)"
  //        tempTables += s"UNNEST($col) AS $colAlias"
  //        groupByCols -= col
  //        aggregate = true
  //        replaceCast(
  //          (row: RS, paramIndex: Int) => {
  //            val resultSet = row.getArray(paramIndex).getResultSet
  //            val list      = ListBuffer.empty[Double]
  //            while (resultSet.next()) {
  //              list ++= res.json2array(row.getString(paramIndex)).map(_.toString.toDouble).toList
  //            }
  //            varianceOf(list.toList: _*)
  //          }
  //        )
  //
  //      case "stddev" =>
  //        select += s"ARRAY_AGG($colAlias)"
  //        tempTables += s"UNNEST($col) AS $colAlias"
  //        groupByCols -= col
  //        aggregate = true
  //        replaceCast(
  //          (row: RS, paramIndex: Int) => {
  //            val resultSet = row.getArray(paramIndex).getResultSet
  //            val list      = ListBuffer.empty[Double]
  //            while (resultSet.next()) {
  //              list ++= res.json2array(row.getString(paramIndex)).map(_.toString.toDouble).toList
  //            }
  //            stdDevOf(list.toList: _*)
  //          }
  //        )
  //
  //      case other => unexpectedKw(other)
  //    }
  //  }
  //
  //
  //  // filter attribute ----------------------------------------------------------
  //
  //  override protected def setEqual2[T](
  //    col: String, filterAttr: String, res: ResSet[T], mandatory: Boolean
  //  ): Unit = {
  //    if (mandatory) {
  //      val colAlias = col.replace(".", "_")
  //      select -= col
  //      groupByCols -= col
  //      select += s"ARRAY_AGG($colAlias)"
  //      tempTables += s"UNNEST($col) AS $colAlias"
  //      having += "COUNT(*) > 0"
  //      aggregate = true
  //    }
  //    where += ((col, "= " + filterAttr))
  //  }
  //
  //  override protected def has2[T](
  //    col: String, filterAttr: String, cardOne: Boolean, tpe: String,
  //    res: ResSet[T], mandatory: Boolean
  //  ): Unit = {
  //    if (cardOne) {
  //      where += (("", s"$col @> ARRAY(SELECT $filterAttr)"))
  //    } else {
  //      if (mandatory) {
  //        val colAlias = col.replace(".", "_")
  //        select -= col
  //        select += s"ARRAY_AGG($colAlias)"
  //        tempTables += s"UNNEST($col) AS $colAlias"
  //        groupByCols -= col
  //        having += "COUNT(*) > 0"
  //        aggregate = true
  //        replaceCast(res.array2set)
  //      }
  //      where += (("", s"$col @> $filterAttr"))
  //    }
  //  }
  //
  //  override protected def hasNo2[T](
  //    col: String, filterAttr: String, cardOne: Boolean, tpe: String,
  //    res: ResSet[T], mandatory: Boolean
  //  ): Unit = {
  //    if (cardOne) {
  //      if (mandatory) {
  //        val colAlias = col.replace(".", "_")
  //        select -= col
  //        select += s"ARRAY_AGG($colAlias)"
  //        tempTables += s"UNNEST($col) AS $colAlias"
  //        groupByCols -= col
  //        having += "COUNT(*) > 0"
  //        aggregate = true
  //        replaceCast(res.array2set)
  //      }
  //      where += (("", s"ARRAY(SELECT UNNEST($col) INTERSECT SELECT $filterAttr) = '{}'"))
  //    } else {
  //      where += (("", s"ARRAY(SELECT UNNEST($col) INTERSECT SELECT UNNEST($filterAttr)) = '{}'"))
  //    }
  //  }
  //
  //  // helpers -------------------------------------------------------------------
  //
  //  private def coalesce[T](col: String, res: ResSet[T], mode: String) = {
  //    val colAlias = col.replace(".", "_")
  //    select -= col
  //    groupByCols -= col
  //    mode match {
  //      case "man" =>
  //        select += s"ARRAY_AGG(DISTINCT $colAlias)"
  //        val tpe = res.tpeDb
  //        tempTables += s"UNNEST(CASE WHEN $col IS NULL THEN array[null]::$tpe[] ELSE $col END) AS $colAlias"
  //        replaceCast(res.array2set)
  //
  //      case "opt" =>
  //        select += s"COALESCE(ARRAY_AGG($col) FILTER (WHERE $col <> '{}'), '{}')"
  //        replaceCast(res.nestedArray2optCoalescedSet)
  //
  //      case "tac" =>
  //        where += (("", s"$col <> '{}'"))
  //    }
  //    aggregate = true
  //  }
  //
  //  private def matchArray(sqlArray: (String, Int), col: String): String = {
  //    s"(${sqlArray._1} <@ $col AND CARDINALITY($col) = ${sqlArray._2})"
  //  }
  //
  //  private def matchArrays[T](sets: Seq[Set[T]], col: String, set2sqlArray: Set[T] => String): String = {
  //    sets.map(set =>
  //      matchArray((set2sqlArray(set), set.size), col)
  //    ).mkString("(\n    ", " OR\n    ", "\n  )")
  //  }
}