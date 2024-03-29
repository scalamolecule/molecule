package molecule.sql.mysql.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.sql.core.query.{ResolveExprSet, SqlQueryBase}
import scala.reflect.ClassTag


trait ResolveExprSet_mysql
  extends ResolveExprSet
    with LambdasSet_mysql { self: SqlQueryBase =>


  override protected def setMan[T: ClassTag](
    attr: Attr, tpe: String, args: Seq[Set[T]], res: ResSet[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    groupByCols += col // if we later need to group by non-aggregated columns
    if (!isNestedOpt) {
      notNull += col
    }
    addCast((row: RS, paramIndex: Int) =>
      res.json2array(row.getString(paramIndex)).toSet
    )

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

  override protected def setExpr[T: ClassTag](
    attr: Attr, col: String, op: Op, sets: Seq[Set[T]], res: ResSet[T], mandatory: Boolean
  ): Unit = {
    op match {
      case V       => setAttr(col, res, mandatory)
      case Eq      => noCollectionMatching(attr)
      case Has     => has(col, sets, res, res.one2sql, mandatory)
      case HasNo   => hasNo(col, sets, res, res.one2sql, mandatory)
      case NoValue => if (mandatory) noApplyNothing(attr) else setNoValue(col)
      case other   => unexpectedOp(other)
    }
  }

  override protected def setOpt[T: ClassTag](
    attr: Attr,
    optSets: Option[Seq[Set[T]]],
    resOpt: ResSetOpt[T],
    res: ResSet[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    groupByCols += col // if we later need to group by non-aggregated columns
    addCast((row: RS, paramIndex: Int) => row.getString(paramIndex) match {
      case null | "[]" => Option.empty[Set[T]]
      case json        => Some(res.json2array(json).toSet)
    })
    attr.op match {
      case V     => setOptAttr(col, res)
      case Eq    => noCollectionMatching(attr)
      case Has   => optHas(col, optSets, res, resOpt.one2json)
      case HasNo => optHasNo(col, optSets, res, resOpt.one2json)
      case other => unexpectedOp(other)
    }
  }


  // attr ----------------------------------------------------------------------

  override protected def setAttr[T: ClassTag](
    col: String, res: ResSet[T], mandatory: Boolean
  ): Unit = {
    if (mandatory) {
      selectWithOrder(col, res.tpeDb, "JSON_ARRAYAGG")
      select -= col
      groupByCols -= col
      having += "COUNT(*) > 0"
      aggregate = true
      replaceCast((row: RS, paramIndex: Int) =>
        res.json2array(row.getString(paramIndex)).toSet
      )
    }
  }

  override protected def setOptAttr[T](col: String, res: ResSet[T]): Unit = {
    selectWithOrder(col, res.tpeDb, "JSON_ARRAYAGG", optional = true)
    select -= col
    groupByCols -= col
    aggregate = true
    replaceCast((row: RS, paramIndex: Int) =>
      res.json2optArray(row.getString(paramIndex)).map(_.toSet)
    )
  }


  // has -----------------------------------------------------------------------

  override protected def has[T: ClassTag](
    col: String, sets: Seq[Set[T]], res: ResSet[T], one2json: T => String, mandatory: Boolean
  ): Unit = {
    def containsSet(set: Set[T]): String = {
      val jsonValues = set.map(one2json).mkString(", ")
      s"JSON_CONTAINS($col, JSON_ARRAY($jsonValues))"
    }
    if (mandatory) {
      selectWithOrder(col, res.tpeDb, "JSON_ARRAYAGG", optional = true)
      select -= col
      groupByCols -= col
      aggregate = true
    }
    sets.length match {
      case 0 => where += (("FALSE", ""))
      case 1 =>
        val set = sets.head
        set.size match {
          case 0 => where += (("FALSE", ""))
          case 1 => where += (("", s"JSON_CONTAINS($col, JSON_ARRAY(${one2json(set.head)}))"))
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
      has(col, sets, res, one2sql, true)
      replaceCast((row: RS, paramIndex: Int) =>
        res.json2optArray(row.getString(paramIndex)).map(_.toSet)
      )
    }
  }


  // hasNo ---------------------------------------------------------------------

  override protected def hasNo[T](
    col: String, sets: Seq[Set[T]], res: ResSet[T], one2json: T => String, mandatory: Boolean
  ): Unit = {
    def notContains(v: T): String = s"NOT JSON_CONTAINS($col, JSON_ARRAY(${one2json(v)}))"
    def notContainsSet(set: Set[T]): String = {
      val jsonValues = set.map(one2json).mkString(", ")
      s"NOT JSON_CONTAINS($col, JSON_ARRAY($jsonValues))"
    }
    if (mandatory) {
      selectWithOrder(col, res.tpeDb, "JSON_ARRAYAGG", optional = true)
      select -= col
      groupByCols -= col
      aggregate = true
    }
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
      replaceCast((row: RS, paramIndex: Int) =>
        res.json2optArray(row.getString(paramIndex)).map(_.toSet)
      )
    }
    // Only asserted values
    notNull += col
  }


  // Filter attribute filters --------------------------------------------------

  override protected def has2[T](
    col: String, filterAttr: String, filterCardOne: Boolean, tpe: String,
    res: ResSet[T], mandatory: Boolean
  ): Unit = {
    if (filterCardOne) {
      where += (("", s"JSON_CONTAINS($col, JSON_ARRAY($filterAttr))"))
    } else {
      if (mandatory) {
        val i = getIndex
        select -= col
        select += s"JSON_ARRAYAGG(t_$i.vs)"
        having += "COUNT(*) > 0"
        aggregate = true
        groupByCols -= col
        val tpeDb = res.tpeDb
        tempTables += s"JSON_TABLE($col, '$$[*]' COLUMNS (vs $tpeDb PATH '$$')) t_$i"
      }
      where += (("", s"JSON_CONTAINS($col, $filterAttr)"))
    }
  }

  override protected def hasNo2[T](
    col: String, filterAttr: String, filterCardOne: Boolean, tpe: String,
    res: ResSet[T], mandatory: Boolean
  ): Unit = {
    if (filterCardOne) {
      if (mandatory) {
        val i = getIndex
        select -= col
        select += s"JSON_ARRAYAGG(t_$i.vs)"
        having += "COUNT(*) > 0"
        aggregate = true
        groupByCols -= col
        val tpeDb = res.tpeDb
        tempTables += s"JSON_TABLE($col, '$$[*]' COLUMNS (vs $tpeDb PATH '$$')) t_$i"
      }
      where += (("", s"NOT JSON_CONTAINS($col, JSON_ARRAY($filterAttr))"))
    } else {
      where += (("", s"NOT JSON_OVERLAPS($col, $filterAttr)"))
    }
  }


  // helpers -------------------------------------------------------------------

  private def selectWithOrder(
    col: String,
    tpeDb: String,
    fn: String,
    distinct: String = "",
    optional: Boolean = false
  ): Unit = {
    val i  = getIndex
    val vs = s"t_$i.vs"
    if (orderBy.nonEmpty && orderBy.last._3 == col) {
      // order by aggregate alias instead
      val alias = col.replace('.', '_') + "_" + fn.toLowerCase
      select += s"$fn($distinct$vs) $alias"
      val (level, _, _, dir) = orderBy.last
      orderBy.remove(orderBy.size - 1)
      orderBy += ((level, 1, alias, dir))
    } else {
      select += s"$fn($distinct$vs)"
    }
    if (optional) {
      joins += ((
        "LEFT OUTER JOIN",
        s"JSON_TABLE($col, '$$[*]' COLUMNS (vs $tpeDb PATH '$$')) t_$i",
        "",
        "true",
        ""
      ))

    } else {
      tempTables +=
        s"""JSON_TABLE(
           |    IF($col IS NULL, '[null]', $col),
           |    '$$[*]' COLUMNS (vs $tpeDb PATH '$$')
           |  ) t_$i""".stripMargin
    }
  }
}