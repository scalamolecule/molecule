package molecule.sql.postgres.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.sql.core.query.{ResolveExprSet, SqlQueryBase}
import scala.reflect.ClassTag


trait ResolveExprSet_postgres
  extends ResolveExprSet
    with LambdasSet_postgres { self: SqlQueryBase =>


  override protected def setMan[T: ClassTag](
    attr: Attr, tpe: String, args: Seq[Set[T]], res: ResSet[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    groupByCols += col // if we later need to group by non-aggregated columns

    if (isNestedOpt) {
      addCast(res.sql2setOrNull)
    } else {
      addCast(res.sql2set)
      notNull += col
    }

    attr.filterAttr.fold {
      val pathAttr = path :+ attr.cleanAttr
      if (filterAttrVars.contains(pathAttr) && attr.op != V) {
        // Runtime check needed since we can't type infer it
        throw ModelError(s"Cardinality-set filter attributes not allowed to " +
          s"do additional filtering. Found:\n  " + attr)
      }
      setExpr(attr, col, attr.op, args, res, true)
    } {
      case (dir, filterPath, filterAttr) => filterAttr match {
        case filterAttr: AttrOne => setExpr2(col, attr.op, filterAttr.name, true, tpe, res, true)
        case filterAttr          => setExpr2(col, attr.op, filterAttr.name, false, tpe, res, true)
      }
    }
  }


  // attr ----------------------------------------------------------------------

  override protected def setAttr[T: ClassTag](
    col: String, res: ResSet[T], mandatory: Boolean
  ): Unit = {
    coalesce(col, res, if (mandatory) "man" else "tac")
    having += "COUNT(*) > 0"
  }

  override protected def setOptAttr[T](col: String, res: ResSet[T]): Unit = {
    coalesce(col, res, "opt")
  }


  // has -----------------------------------------------------------------------

  override protected def has[T: ClassTag](
    col: String, sets: Seq[Set[T]], res: ResSet[T], one2sql: T => String, mandatory: Boolean
  ): Unit = {
    def contains(v: T): String = s"${one2sql(v)} = ANY($col)"
    def containsSet(set: Set[T]): String = set.map(contains).mkString(" AND ")
    coalesce(col, res, if (mandatory) "man" else "tac")
    sets.length match {
      case 0 =>
        where += (("FALSE", ""))
      case 1 =>
        val set = sets.head
        set.size match {
          case 0 => where += (("FALSE", ""))
          case 1 => where += (("", contains(set.head)))
          case _ => where += (("", containsSet(set)))
        }

      case _ =>
        val expr = sets
          .filterNot(_.isEmpty)
          .map(containsSet).mkString("(\n    ", " OR\n    ", "\n  )")
        where += (("", expr))
    }

  }

  override protected def optHas[T: ClassTag](
    col: String,
    optSets: Option[Seq[Set[T]]],
    res: ResSet[T],
    one2sql: T => String,
  ): Unit = {
    optSets.fold[Unit] {
      where += ((col, s"IS NULL"))
    } { sets =>
      val setsWithValues = sets.filterNot(_.isEmpty)
      if (setsWithValues.nonEmpty) {
        has(col, sets, res, one2sql, true)
        replaceCast(res.array2optSet)
      } else {
        where += (("FALSE", ""))
      }
    }
  }


  // hasNo ---------------------------------------------------------------------

  override protected def hasNo[T](
    col: String, sets: Seq[Set[T]], res: ResSet[T], one2sql: T => String, mandatory: Boolean
  ): Unit = {
    def notContains(v: T): String = s"${one2sql(v)} != ALL($col)"
    def notContainsSet(set: Set[T]): String = set.map(notContains).mkString("(", " OR ", ")")
    coalesce(col, res, if (mandatory) "man" else "tac")
    sets.length match {
      case 0 => ()
      case 1 =>
        val set = sets.head
        set.size match {
          case 0 => ()
          case 1 => where += (("", notContains(set.head)))
          case _ => where += (("", notContainsSet(set)))
        }
      case _ =>
        val expr = sets
          .filterNot(_.isEmpty)
          .map(notContainsSet)
          .mkString("(\n    ", " AND\n    ", "\n  )")
        where += (("", expr))
    }
  }

  override protected def optHasNo[T: ClassTag](
    col: String,
    optSets: Option[Seq[Set[T]]],
    res: ResSet[T],
    one2sql: T => String
  ): Unit = {
    optSets.fold[Unit] {
      setOptAttr(col, res)
    } { sets =>
      hasNo(col, sets, res, one2sql, true)
      coalesce(col, res, "opt")
      replaceCast(res.array2optSet)

    }
    // Only asserted values
    notNull += col
  }


  // Filter attribute filters --------------------------------------------------

  override protected def has2[T](
    col: String, filterAttr: String, cardOne: Boolean, tpe: String,
    res: ResSet[T], mandatory: Boolean
  ): Unit = {
    if (cardOne) {
      where += (("", s"$col @> ARRAY(SELECT $filterAttr)"))
    } else {
      if (mandatory) {
        val colAlias = col.replace(".", "_")
        select -= col
        select += s"ARRAY_AGG($colAlias)"
        tempTables += s"UNNEST($col) AS $colAlias"
        groupByCols -= col
        having += "COUNT(*) > 0"
        aggregate = true
        replaceCast(res.array2set)
      }
      where += (("", s"$col @> $filterAttr"))
    }
  }

  override protected def hasNo2[T](
    col: String, filterAttr: String, cardOne: Boolean, tpe: String,
    res: ResSet[T], mandatory: Boolean
  ): Unit = {
    if (cardOne) {
      if (mandatory) {
        val colAlias = col.replace(".", "_")
        select -= col
        select += s"ARRAY_AGG($colAlias)"
        tempTables += s"UNNEST($col) AS $colAlias"
        groupByCols -= col
        having += "COUNT(*) > 0"
        aggregate = true
        replaceCast(res.array2set)
      }
      where += (("", s"ARRAY(SELECT UNNEST($col) INTERSECT SELECT $filterAttr) = '{}'"))
    } else {
      where += (("", s"ARRAY(SELECT UNNEST($col) INTERSECT SELECT UNNEST($filterAttr)) = '{}'"))
    }
  }

  // helpers -------------------------------------------------------------------

  private def coalesce[T](col: String, res: ResSet[T], mode: String) = {
    val colAlias = col.replace(".", "_")
    select -= col
    groupByCols -= col
    mode match {
      case "man" =>
        select += s"ARRAY_AGG(DISTINCT $colAlias)"
        val tpe = res.tpeDb
        tempTables += s"UNNEST(CASE WHEN $col IS NULL THEN array[null]::$tpe[] ELSE $col END) AS $colAlias"
        replaceCast(res.array2set)

      case "opt" =>
        select += s"COALESCE(ARRAY_AGG($col) FILTER (WHERE $col <> '{}'), '{}')"
        replaceCast(res.nestedArray2optCoalescedSet)

      case "tac" =>
        where += (("", s"$col <> '{}'"))
    }
    aggregate = true
  }
}