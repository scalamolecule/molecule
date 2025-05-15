package molecule.db.sql.core.query

import molecule.db.base.error.ModelError
import molecule.db.core.ast.*
import molecule.db.core.query.{Model2Query, QueryExpr}

trait QueryExprSetRefAttr extends QueryExpr with LambdasSet { self: Model2Query & SqlQueryBase =>

  protected var joinTable: String = ""
  protected var entId    : String = ""
  protected var eid      : String = ""
  protected var rid      : String = ""
  protected var refIds   : String = ""

  protected def setCoords(attr: Attr): Unit = {
    val (ent, refAttr, ref) = (attr.ent, attr.attr, attr.ref.get)
    joinTable = ss(ent, refAttr, ref)
    entId = ent + ".id"
    eid = ss(ent, "id")
    rid = ss(ref, "id")
    refIds = s"${ent}_$refAttr"
  }

  override protected def queryRefAttrSetMan(attr: AttrSetMan): Unit = {
    setCoords(attr)
    attr match {
      case at: AttrSetManID     => setRefMan(attr, at.vs, resSetId)
      case at: AttrSetManInt    => setRefMan(attr, at.vs, resSetInt)
      case at: AttrSetManLong   => setRefMan(attr, at.vs, resSetLong)
      case at: AttrSetManDouble => setRefMan(attr, at.vs, resSetDouble)
      case _                    => ()
    }
  }

  override protected def queryRefAttrSetTac(attr: AttrSetTac): Unit = {
    setCoords(attr)
    attr match {
      case at: AttrSetTacID     => setRefTac(attr, at.vs, resSetId)
      case at: AttrSetTacInt    => setRefTac(attr, at.vs, resSetInt)
      case at: AttrSetTacLong   => setRefTac(attr, at.vs, resSetLong)
      case at: AttrSetTacDouble => setRefTac(attr, at.vs, resSetDouble)
      case _                    => ()
    }
  }

  override protected def queryRefAttrSetOpt(attr: AttrSetOpt): Unit = {
    setCoords(attr)
    attr match {
      case at: AttrSetOptID     => setRefOpt(at, at.vs, resOptSetId, resSetId)
      case at: AttrSetOptInt    => setRefOpt(at, at.vs, resOptSetInt, resSetInt)
      case at: AttrSetOptLong   => setRefOpt(at, at.vs, resOptSetLong, resSetLong)
      case at: AttrSetOptDouble => setRefOpt(at, at.vs, resOptSetDouble, resSetDouble)
      case other                => throw ModelError("Unexpected optional Set type: " + other)
    }
  }


  protected def setRefMan[T](attr: Attr, args: Set[T], res: ResSet[T]): Unit = {
    select += s"ARRAY_AGG($joinTable.$rid) $refIds"
    joins += (("INNER JOIN", joinTable, "", List(s"$entId = $joinTable.$eid")))
    groupBy += entId
    castStrategy.add(res.sql2set)

    attr.filterAttr.fold {
      val pathAttr = path :+ attr.cleanAttr
      if (filterAttrVars.contains(pathAttr) && attr.op != V) {
        noCardManyFilterAttrExpr(attr)
      }
      setRefExpr(attr, refIds, attr.op, args)
    } { case (dir, filterPath, filterAttr) =>
      setFilterRefExpr(refIds, attr.op, filterAttr.name)
    }
  }

  protected def setRefTac[T](attr: Attr, args: Set[T], res: ResSet[T]): Unit = {
    val col = getCol(attr: Attr)
    joins += (("INNER JOIN", joinTable, "", List(s"$entId = $joinTable.$eid")))
    groupBy += entId
    attr.filterAttr.fold {
      setRefExpr(attr, col, attr.op, args)
    } { case (dir, filterPath, filterAttr) =>
      setFilterRefExpr(col, attr.op, filterAttr.name)
    }
  }


  protected def setRefOpt[T](
    attr: Attr, optSet: Option[Set[T]], resOpt: ResSetOpt[T], res: ResSet[T]
  ): Unit = {
    select += s"ARRAY_AGG($joinTable.$rid) $refIds"
    joins += (("LEFT JOIN", joinTable, "", List(s"$entId = $joinTable.$eid")))
    groupBy += entId
    castStrategy.add(resOpt.sql2setOpt)
    attr.op match {
      case V     => ()
      case Eq    => noCollectionMatching(attr)
      case other => unexpectedOp(other)
    }
  }

  protected def setRefExpr[T](
    attr: Attr, col: String, op: Op, set: Set[T]
  ): Unit = {
    op match {
      case V       => ()
      case Eq      => noCollectionMatching(attr)
      case Has     => refHas(set)
      case HasNo   => refHasNo(set)
      case NoValue =>
        refNoValue(s"$joinTable.$eid")
      case other   => unexpectedOp(other)
    }
  }

  protected def setFilterRefExpr(col: String, op: Op, filterAttr: String): Unit = {
    op match {
      case Has   => refHas2(col, filterAttr)
      case HasNo => refHasNo2(col, filterAttr)
      case other => unexpectedOp(other)
    }
  }

  protected def contains(v: String): String = {
    s"(SELECT ARRAY_CONTAINS(ARRAY_AGG($joinTable.$rid), $v) FROM $joinTable WHERE $joinTable.$eid = $entId)"
  }

  protected def sizeCheck(size: Int): String = {
    s"(SELECT CARDINALITY(ARRAY_AGG($joinTable.$rid)) = $size FROM $joinTable WHERE $joinTable.$eid = $entId)"
  }

  protected def setRefMatchSet(set: Set[String]): String = {
    set
      .map(contains)
      .mkString("(\n    ", " AND\n    ", s" AND\n    ${sizeCheck(set.size)}\n  )")
  }

  protected def setRefEqual[T](set: Set[T], res: ResSet[T]): Unit = {
    where += (("", setRefMatchSet(res.set2sqls(set))))
  }

  protected def setRefOptEqual[T](optSets: Option[Set[T]], res: ResSet[T]): Unit = {
    optSets.fold[Unit] {
      where += (("",
        s"""(
           |    SELECT count($joinTable.$rid) = 0
           |    FROM $joinTable
           |    WHERE $joinTable.$eid = $entId
           |  )""".stripMargin
      ))
    } { sets =>
      setRefEqual(sets, res)
    }
  }

  protected def setRefNeq[T](set: Set[T], res: ResSet[T]): Unit = {
    where += (("", "NOT " + setRefMatchSet(res.set2sqls(set))))
  }

  protected def setRefOptNeq[T](optSet: Option[Set[T]], res: ResSet[T]): Unit = {
    optSet.foreach(set => setRefNeq(set, res))
    setNotNull(s"$joinTable.$eid")
  }


  protected def arrayMatches(arrayContains: String): String = {
    s"""(
       |    SELECT
       |      $arrayContains
       |    FROM $joinTable
       |    WHERE $joinTable.$eid = $entId
       |  )""".stripMargin
  }

  protected def refHas[T](set: Set[T]): Unit = {
    set.size match {
      case 0 => where += (("FALSE", ""))
      case 1 =>
        val arrayContains = arrayMatches(
          s"  ARRAY_CONTAINS(ARRAY_AGG($joinTable.$rid), ${set.head})"
        )
        where += (("", arrayContains))
      case _ =>
        val arrayContains = set
          .map(v => s"ARRAY_CONTAINS(ARRAY_AGG($joinTable.$rid), $v)")
          .mkString(" OR\n      ")
        where += (("", arrayMatches(arrayContains)))
    }
  }

  protected def refHas2(col: String, filterAttr: String): Unit = {
    where += (("", s"ARRAY_CONTAINS($col, $filterAttr)"))
  }

  protected def refOptHas[T](col: String, optSets: Option[Set[T]]): Unit = {
    optSets.fold[Unit] {
      where += ((s"$joinTable.$rid", s"IS NULL"))
    } { sets =>
      refHas(sets)
    }
  }

  protected def refHasNo[T](set: Set[T]): Unit = {
    set.size match {
      case 0 => ()
      case 1 => where += (("",
        arrayMatches(
          s"  " + s"NOT ARRAY_CONTAINS(ARRAY_AGG($joinTable.$rid), ${set.head})"
        )
      ))
      case _ =>
        val arrayContains = set
          .map(v => s"NOT ARRAY_CONTAINS(ARRAY_AGG($joinTable.$rid), $v)")
          .mkString(" AND\n        ")
        where += (("", arrayMatches(arrayContains)))
    }
  }

  protected def refHasNo2(col: String, filterAttr: String): Unit = {
    where += (("", s"NOT ARRAY_CONTAINS($col, $filterAttr)"))
  }

  protected def refOptHasNo[T](optSet: Option[Set[T]]): Unit = {
    optSet.fold[Unit] {
      where += ((s"$joinTable.$rid", s"IS NOT NULL"))
    } { set =>
      refHasNo(set)
    }
  }

  protected def refNoValue(col: String): Unit = {
    unsetNotNull(col)
    // Make join optional
    joins.remove(joins.length - 1)
    joins += (("LEFT JOIN", joinTable, "", List(s"$entId = $col")))
    setNull(col)
  }
}