package molecule.sql.postgres.query

import molecule.sql.core.query.SqlQueryBase
import molecule.sql.h2.query.ResolveExprSetRefAttr_h2

trait ResolveExprSetRefAttr_postgres[Tpl]
  extends ResolveExprSetRefAttr_h2[Tpl]
    with LambdasSet_postgres { self: SqlQueryBase =>

  //  private var joinTable: String = ""
  //  private var nsId     : String = ""
  //  private var ns_id    : String = ""
  //  private var ref_id   : String = ""
  //  private var refIds   : String = ""
  //
  //  private def setCoords(attr: Attr): Unit = {
  //    val (ns, refAttr, refNs) = (attr.ns, attr.attr, attr.refNs.get)
  //    joinTable = ns + "_" + refAttr + "_" + refNs
  //    nsId = ns + ".id"
  //    ns_id = ns + "_id"
  //    ref_id = refNs + "_id"
  //    refIds = s"${ns}_$refAttr"
  //  }
  //
  //  protected def resolveRefAttrSetMan(attr: AttrSetMan): Unit = {
  //    setCoords(attr)
  //    aritiesAttr()
  //    attr match {
  //      case at: AttrSetManInt    => man(attr, at.vs, resSetInt)
  //      case at: AttrSetManLong   => man(attr, at.vs, resSetLong)
  //      case at: AttrSetManDouble => man(attr, at.vs, resSetDouble)
  //      case _                    => ()
  //    }
  //  }
  //
  //  protected def resolveRefAttrSetTac(attr: AttrSetTac): Unit = {
  //    setCoords(attr)
  //    attr match {
  //      case at: AttrSetTacInt    => tac(attr, at.vs, resSetInt)
  //      case at: AttrSetTacLong   => tac(attr, at.vs, resSetLong)
  //      case at: AttrSetTacDouble => tac(attr, at.vs, resSetDouble)
  //      case _                    => ()
  //    }
  //  }
  //
  //  protected def resolveRefAttrSetOpt(attr: AttrSetOpt): Unit = {
  //    setCoords(attr)
  //    aritiesAttr()
  //    hasOptAttr = true // to avoid redundant None's
  //    attr match {
  //      case at: AttrSetOptInt    => opt(at, at.vs, resOptSetInt)
  //      case at: AttrSetOptLong   => opt(at, at.vs, resOptSetLong)
  //      case at: AttrSetOptDouble => opt(at, at.vs, resOptSetDouble)
  //      case other                => throw ModelError("Unexpected optional Set type: " + other)
  //    }
  //  }
  //
  //
  //  private def man[T: ClassTag](attr: Attr, args: Seq[Set[T]], res: ResSet[T]): Unit = {
  //    select += s"ARRAY_AGG($joinTable.$ref_id) $refIds"
  //    joins += (("INNER JOIN", joinTable, "", s"$nsId = $joinTable.$ns_id"))
  //    groupBy += nsId
  //    if (isNestedOpt) {
  //      addCast(res.sql2setOrNull)
  //    } else {
  //      addCast(res.sql2set)
  //    }
  //    attr.filterAttr.fold {
  //      if (filterAttrVars.contains(attr.name) && attr.op != V) {
  //        // Runtime check needed since we can't type infer it
  //        throw ModelError(s"Cardinality-set filter attributes not allowed to do additional filtering. Found:\n  " + attr)
  //      }
  //      expr(refIds, attr.op, args, res)
  //    } { filterAttr =>
  //      expr2(refIds, attr.op, filterAttr.name)
  //    }
  //  }
  //
  //  private def tac[T: ClassTag](attr: Attr, args: Seq[Set[T]], res: ResSet[T]): Unit = {
  //    val col = getCol(attr: Attr)
  //    joins += (("INNER JOIN", joinTable, "", s"$nsId = $joinTable.$ns_id"))
  //    groupBy += nsId
  //    attr.filterAttr.fold {
  //      expr(col, attr.op, args, res)
  //    } { filterAttr =>
  //      expr2(col, attr.op, filterAttr.name)
  //    }
  //  }
  //
  //
  //  private def opt[T: ClassTag](attr: Attr, optSets: Option[Seq[Set[T]]], resOpt: ResSetOpt[T]): Unit = {
  //    val col = getCol(attr: Attr)
  //    select += s"ARRAY_AGG($joinTable.$ref_id) $refIds"
  //    joins += (("LEFT JOIN", joinTable, "", s"$nsId = $joinTable.$ns_id"))
  //    groupBy += nsId
  //
  //    addCast(resOpt.sql2setOpt)
  //    attr.op match {
  //      case V     => ()
  //      case Eq    => optEqual(optSets, resOpt.set2sqls)
  //      case Neq   => optNeq(optSets, resOpt.set2sqls)
  //      case Has   => optHas(col, optSets)
  //      case HasNo => optHasNo(optSets)
  //      case other => unexpectedOp(other)
  //    }
  //  }
  //
  //  private def expr[T: ClassTag](col: String, op: Op, sets: Seq[Set[T]], res: ResSet[T]): Unit = {
  //    op match {
  //      case V        => ()
  //      case Eq       => equal(sets, res.set2sqls)
  //      case Neq      => neq(sets, res.set2sqls)
  //      case Has      => has(sets)
  //      case HasNo    => hasNo(sets)
  //      case NoValue  => noValue(col)
  //      case Fn(_, _) => throw ModelError("Aggregating Sets of ref ids not supported.")
  //      case other    => unexpectedOp(other)
  //    }
  //  }
  //
  //  private def expr2(col: String, op: Op, filterAttr: String): Unit = {
  //    op match {
  //      case Eq    => equal2(col, filterAttr)
  //      case Neq   => neq2(col, filterAttr)
  //      case Has   => has2(col, filterAttr)
  //      case HasNo => hasNo2(col, filterAttr)
  //      case other => unexpectedOp(other)
  //    }
  //  }
  //
  //  private lazy val refIdArray =
  //    s"""(SELECT
  //       |    ARRAY_AGG(
  //       |      $joinTable.$ref_id
  //       |      ORDER BY $joinTable.$ref_id ASC
  //       |    )
  //       |    FROM $joinTable
  //       |    WHERE $joinTable.$ns_id = $nsId
  //       |  )""".stripMargin
  //
  //  private def contains(v: String): String = {
  //    s"(SELECT ARRAY_CONTAINS(ARRAY_AGG($joinTable.$ref_id), $v) FROM $joinTable WHERE $joinTable.$ns_id = $nsId)"
  //  }
  //
  //  private def sizeCheck(size: Int): String = {
  //    s"(SELECT CARDINALITY(ARRAY_AGG($joinTable.$ref_id)) = $size FROM $joinTable WHERE $joinTable.$ns_id = $nsId)"
  //  }
  //
  //  private def matchSet(set: Set[String]): String = {
  //    set
  //      .map(contains)
  //      .mkString("(\n    ", " AND\n    ", s" AND\n    ${sizeCheck(set.size)}\n  )")
  //  }
  //
  //  private def matchSets[T](sets: Seq[Set[T]], set2sqls: Set[T] => Set[String]): String = {
  //    sets
  //      .map { set =>
  //        val size = set.size
  //        set2sqls(set)
  //          .map(contains)
  //          .mkString("", " AND\n      ", s" AND\n      ${sizeCheck(size)}")
  //      }.mkString("(\n    (\n      ", "\n    ) OR (\n      ", "\n    )\n  )")
  //  }
  //
  //
  //  private def equal[T](sets: Seq[Set[T]], set2sqls: Set[T] => Set[String]): Unit = {
  //    val setsNonEmpty = sets.filterNot(_.isEmpty)
  //    setsNonEmpty.length match {
  //      case 0 => where += (("FALSE", ""))
  //      case 1 => where += (("", matchSet(set2sqls(setsNonEmpty.head))))
  //      case _ => where += (("", matchSets(setsNonEmpty, set2sqls)))
  //    }
  //  }
  //
  //  private def equal2(col: String, filterAttr: String): Unit = {
  //    where += ((col, "= " + filterAttr))
  //  }
  //
  //  private def optEqual[T](optSets: Option[Seq[Set[T]]], set2sqls: Set[T] => Set[String]): Unit = {
  //    optSets.fold[Unit] {
  //      where += (("", s"$refIdArray IS NULL"))
  //    } { sets =>
  //      equal(sets, set2sqls)
  //    }
  //  }
  //
  //  private def neq[T](sets: Seq[Set[T]], set2sqls: Set[T] => Set[String]): Unit = {
  //    val setsNonEmpty = sets.filterNot(_.isEmpty)
  //    setsNonEmpty.length match {
  //      case 0 => ()
  //      case 1 => where += (("", "NOT " + matchSet(set2sqls(setsNonEmpty.head))))
  //      case _ => where += (("", "NOT " + matchSets(setsNonEmpty, set2sqls)))
  //    }
  //  }
  //
  //  private def neq2(col: String, filterAttr: String): Unit = {
  //    where += ((col, "<> " + filterAttr))
  //  }
  //
  //  private def optNeq[T](optSets: Option[Seq[Set[T]]], set2sqls: Set[T] => Set[String]): Unit = {
  //    if (optSets.isDefined && optSets.get.nonEmpty) {
  //      neq(optSets.get, set2sqls)
  //    }
  //    notNull += s"$joinTable.$ns_id"
  //  }
  //
  //
  //  private def arrayMatches(arrayContains: String): String = {
  //    s"""(SELECT
  //       |    $arrayContains
  //       |    FROM $joinTable
  //       |    WHERE $joinTable.$ns_id = $nsId
  //       |  )""".stripMargin
  //  }
  //
  //  private def has[T: ClassTag](sets: Seq[Set[T]]): Unit = {
  //    sets.length match {
  //      case 0 => where += (("FALSE", ""))
  //      case 1 =>
  //        val set = sets.head
  //        set.size match {
  //          case 0 => where += (("FALSE", ""))
  //          case 1 => where += (("", arrayMatches(s"  ARRAY_CONTAINS(ARRAY_AGG($joinTable.$ref_id), ${set.head})")))
  //          case _ =>
  //            val arrayContains = set
  //              .map(v => s"ARRAY_CONTAINS(ARRAY_AGG($joinTable.$ref_id), $v)")
  //              .mkString(" AND\n      ")
  //            where += (("", arrayMatches(arrayContains)))
  //        }
  //      case _ =>
  //        val arrayContains = sets.filterNot(_.isEmpty).map(set =>
  //          set
  //            .map(v => s"ARRAY_CONTAINS(ARRAY_AGG($joinTable.$ref_id), $v)")
  //            .mkString(" AND\n        ")
  //        ).mkString("(\n        ", "\n      ) OR\n      (\n        ", "\n      )\n")
  //        where += (("", arrayMatches(arrayContains)))
  //    }
  //  }
  //
  //  private def has2(col: String, filterAttr: String): Unit = {
  //    where += (("", s"ARRAY_CONTAINS($col, $filterAttr)"))
  //  }
  //
  //  private def optHas[T: ClassTag](col: String, optSets: Option[Seq[Set[T]]]): Unit = {
  //    optSets.fold[Unit] {
  //      where += ((s"$joinTable.$ref_id", s"IS NULL"))
  //    } { sets =>
  //      has(sets)
  //    }
  //  }
  //
  //  private def hasNo[T](sets: Seq[Set[T]]): Unit = {
  //    sets.length match {
  //      case 0 => ()
  //      case 1 =>
  //        val set = sets.head
  //        set.size match {
  //          case 0 => ()
  //          case 1 => where += (("", arrayMatches(s"  NOT ARRAY_CONTAINS(ARRAY_AGG($joinTable.$ref_id), ${set.head})")))
  //          case _ =>
  //            val arrayContains = set
  //              .map(v => s"ARRAY_CONTAINS(ARRAY_AGG($joinTable.$ref_id), $v)")
  //              .mkString("  NOT (", " AND\n      ", ")")
  //            where += (("", arrayMatches(arrayContains)))
  //        }
  //      case _ =>
  //        val arrayContains = sets.filterNot(_.isEmpty).map(set =>
  //          set
  //            .map(v => s"NOT ARRAY_CONTAINS(ARRAY_AGG($joinTable.$ref_id), $v)")
  //            .mkString(" OR\n        ")
  //        ).mkString("(\n        ", "\n      ) AND\n      (\n        ", "\n      )\n")
  //        where += (("", arrayMatches(arrayContains)))
  //    }
  //  }
  //
  //  private def hasNo2(col: String, filterAttr: String): Unit = {
  //    where += (("", s"NOT ARRAY_CONTAINS($col, $filterAttr)"))
  //  }
  //
  //  private def optHasNo[T](optSets: Option[Seq[Set[T]]]): Unit = {
  //    optSets.fold[Unit] {
  //      where += ((s"$joinTable.$ref_id", s"IS NOT NULL"))
  //    } { sets =>
  //      val setsWithValues = sets.filterNot(_.isEmpty)
  //      //      if (setsWithValues.nonEmpty && setsWithValues.head.nonEmpty) {
  //      if (setsWithValues.nonEmpty) {
  //        hasNo(sets.filterNot(_.isEmpty))
  //      } else {
  //        where += ((s"$joinTable.$ref_id", s"IS NOT NULL"))
  //      }
  //    }
  //  }
  //
  //  private def noValue(col: String): Unit = {
  //    notNull -= col
  //    where += ((col, s"IS NULL"))
  //  }
}