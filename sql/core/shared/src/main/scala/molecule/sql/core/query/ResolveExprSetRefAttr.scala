package molecule.sql.core.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.query.ResolveExpr
import scala.reflect.ClassTag

trait ResolveExprSetRefAttr extends ResolveExpr with LambdasSet { self: SqlQueryBase =>

  protected var joinTable: String = ""
  protected var nsId     : String = ""
  protected var ns_id    : String = ""
  protected var ref_id   : String = ""
  protected var refIds   : String = ""

  protected def setCoords(attr: Attr): Unit = {
    val (ns, refAttr, refNs) = (attr.ns, attr.attr, attr.refNs.get)
    joinTable = ss(ns, refAttr, refNs)
    nsId = ns + ".id"
    ns_id = ss(ns, "id")
    ref_id = ss(refNs, "id")
    refIds = s"${ns}_$refAttr"
  }

  override protected def resolveRefAttrSetMan(attr: AttrSetMan): Unit = {
    setCoords(attr)
    aritiesAttr()
    attr match {
      case at: AttrSetManID     => refMan(attr, at.vs, resSetId)
      case at: AttrSetManInt    => refMan(attr, at.vs, resSetInt)
      case at: AttrSetManLong   => refMan(attr, at.vs, resSetLong)
      case at: AttrSetManDouble => refMan(attr, at.vs, resSetDouble)
      case _                    => ()
    }
  }

  override protected def resolveRefAttrSetTac(attr: AttrSetTac): Unit = {
    setCoords(attr)
    attr match {
      case at: AttrSetTacID     => refTac(attr, at.vs, resSetId)
      case at: AttrSetTacInt    => refTac(attr, at.vs, resSetInt)
      case at: AttrSetTacLong   => refTac(attr, at.vs, resSetLong)
      case at: AttrSetTacDouble => refTac(attr, at.vs, resSetDouble)
      case _                    => ()
    }
  }

  override protected def resolveRefAttrSetOpt(attr: AttrSetOpt): Unit = {
    setCoords(attr)
    aritiesAttr()
    hasOptAttr = true // to avoid redundant None's
    attr match {
      case at: AttrSetOptID     => refOpt(at, at.vs, resOptSetId, resSetId)
      case at: AttrSetOptInt    => refOpt(at, at.vs, resOptSetInt, resSetInt)
      case at: AttrSetOptLong   => refOpt(at, at.vs, resOptSetLong, resSetLong)
      case at: AttrSetOptDouble => refOpt(at, at.vs, resOptSetDouble, resSetDouble)
      case other                => throw ModelError("Unexpected optional Set type: " + other)
    }
  }


  protected def refMan[T: ClassTag](attr: Attr, args: Set[T], res: ResSet[T]): Unit = {
    select += s"ARRAY_AGG($joinTable.$ref_id) $refIds"
    joins += (("INNER JOIN", joinTable, "", s"$nsId", s"= $joinTable.$ns_id"))
    groupBy += nsId
    addCast(res.sql2set)

    attr.filterAttr.fold {
      val pathAttr = path :+ attr.cleanAttr
      if (filterAttrVars.contains(pathAttr) && attr.op != V) {
        // Runtime check needed since we can't type infer it
        throw ModelError(s"Cardinality-set filter attributes not allowed to do additional filtering. Found:\n  " + attr)
      }
      refExpr(refIds, attr.op, args, res)
    } { case (dir, filterPath, filterAttr) =>
      refExpr2(refIds, attr.op, filterAttr.name)
    }
  }

  protected def refTac[T: ClassTag](attr: Attr, args: Set[T], res: ResSet[T]): Unit = {
    val col = getCol(attr: Attr)
    joins += (("INNER JOIN", joinTable, "", s"$nsId", s"= $joinTable.$ns_id"))
    groupBy += nsId
    attr.filterAttr.fold {
      refExpr(col, attr.op, args, res)
    } { case (dir, filterPath, filterAttr) =>
      refExpr2(col, attr.op, filterAttr.name)
    }
  }


  protected def refOpt[T: ClassTag](
    attr: Attr,
    optSet: Option[Set[T]],
    resOpt: ResSetOpt[T],
    res: ResSet[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += s"ARRAY_AGG($joinTable.$ref_id) $refIds"
    joins += (("LEFT JOIN", joinTable, "", s"$nsId", s"= $joinTable.$ns_id"))
    groupBy += nsId
    addCast(resOpt.sql2setOpt)
    attr.op match {
      case V     => ()
      case Eq    => refOptEqual(optSet, res)
      case Neq   => refOptNeq(optSet, res)
      case Has   => refOptHas(col, optSet)
      case HasNo => refOptHasNo(optSet)
      case other => unexpectedOp(other)
    }
  }

  protected def refExpr[T: ClassTag](col: String, op: Op, set: Set[T], res: ResSet[T]): Unit = {
    op match {
      case V        => ()
      case Eq       => refEqual(set, res)
      case Neq      => refNeq(set, res)
      case Has      => refHas(set)
      case HasNo    => refHasNo(set)
      case NoValue  => refNoValue(col)
      case Fn(_, _) => throw ModelError("Aggregating Sets of ref ids not supported.")
      case other    => unexpectedOp(other)
    }
  }

  protected def refExpr2(col: String, op: Op, filterAttr: String): Unit = {
    op match {
      case Eq    => refEqual2(col, filterAttr)
      case Neq   => refNeq2(col, filterAttr)
      case Has   => refHas2(col, filterAttr)
      case HasNo => refHasNo2(col, filterAttr)
      case other => unexpectedOp(other)
    }
  }

  protected def contains(v: String): String = {
    s"(SELECT ARRAY_CONTAINS(ARRAY_AGG($joinTable.$ref_id), $v) FROM $joinTable WHERE $joinTable.$ns_id = $nsId)"
  }

  protected def sizeCheck(size: Int): String = {
    s"(SELECT CARDINALITY(ARRAY_AGG($joinTable.$ref_id)) = $size FROM $joinTable WHERE $joinTable.$ns_id = $nsId)"
  }

  protected def refMatchSet(set: Set[String]): String = {
    set
      .map(contains)
      .mkString("(\n    ", " AND\n    ", s" AND\n    ${sizeCheck(set.size)}\n  )")
  }

  protected def refEqual[T](set: Set[T], res: ResSet[T]): Unit = {
    where += (("", refMatchSet(res.set2sqls(set))))
  }

  protected def refEqual2(col: String, filterAttr: String): Unit = {
    where += ((col, "= " + filterAttr))
  }

  protected def refOptEqual[T](optSets: Option[Set[T]], res: ResSet[T]): Unit = {
    optSets.fold[Unit] {
      where += (("",
        s"""(
           |    SELECT count($joinTable.$ref_id) = 0
           |    FROM $joinTable
           |    WHERE $joinTable.$ns_id = $nsId
           |  )""".stripMargin
      ))
    } { sets =>
      refEqual(sets, res)
    }
  }

  protected def refNeq[T](set: Set[T], res: ResSet[T]): Unit = {
    where += (("", "NOT " + refMatchSet(res.set2sqls(set))))
  }

  protected def refNeq2(col: String, filterAttr: String): Unit = {
    where += ((col, "<> " + filterAttr))
  }

  protected def refOptNeq[T](optSet: Option[Set[T]], res: ResSet[T]): Unit = {
    optSet.foreach(set => refNeq(set, res))
    notNull += s"$joinTable.$ns_id"
  }


  protected def arrayMatches(arrayContains: String): String = {
    s"""(
       |    SELECT
       |      $arrayContains
       |    FROM $joinTable
       |    WHERE $joinTable.$ns_id = $nsId
       |  )""".stripMargin
  }

  protected def refHas[T: ClassTag](set: Set[T]): Unit = {
    set.size match {
      case 0 => where += (("FALSE", ""))
      case 1 => where += (("", arrayMatches(s"  ARRAY_CONTAINS(ARRAY_AGG($joinTable.$ref_id), ${set.head})")))
      case _ =>
        val arrayContains = set
          .map(v => s"ARRAY_CONTAINS(ARRAY_AGG($joinTable.$ref_id), $v)")
          .mkString(" OR\n      ")
        where += (("", arrayMatches(arrayContains)))
    }
  }

  protected def refHas2(col: String, filterAttr: String): Unit = {
    where += (("", s"ARRAY_CONTAINS($col, $filterAttr)"))
  }

  protected def refOptHas[T: ClassTag](col: String, optSets: Option[Set[T]]): Unit = {
    optSets.fold[Unit] {
      where += ((s"$joinTable.$ref_id", s"IS NULL"))
    } { sets =>
      refHas(sets)
    }
  }

  protected def refHasNo[T](set: Set[T]): Unit = {
    set.size match {
      case 0 => ()
      case 1 => where += (("", arrayMatches(s"  NOT ARRAY_CONTAINS(ARRAY_AGG($joinTable.$ref_id), ${set.head})")))
      case _ =>
        val arrayContains = set
          .map(v => s"NOT ARRAY_CONTAINS(ARRAY_AGG($joinTable.$ref_id), $v)")
            .mkString(" AND\n        ")
        where += (("", arrayMatches(arrayContains)))
    }
  }

  protected def refHasNo2(col: String, filterAttr: String): Unit = {
    where += (("", s"NOT ARRAY_CONTAINS($col, $filterAttr)"))
  }

  protected def refOptHasNo[T](optSet: Option[Set[T]]): Unit = {
    optSet.fold[Unit] {
      where += ((s"$joinTable.$ref_id", s"IS NOT NULL"))
    } { set =>
      refHasNo(set)
    }
  }

  protected def refNoValue(col: String): Unit = {
    notNull -= col
    where += ((col, s"IS NULL"))
  }
}