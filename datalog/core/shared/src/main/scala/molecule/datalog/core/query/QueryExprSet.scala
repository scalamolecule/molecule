package molecule.datalog.core.query

import molecule.boilerplate.ast.DataModel._
import molecule.core.query.QueryExpr

trait QueryExprSet[Tpl] extends QueryExpr { self: Model2DatomicQuery[Tpl] with LambdasSet =>

  override protected def queryRefAttrSetMan(a: AttrSetMan): Unit = queryAttrSetMan(a)
  override protected def queryRefAttrSetTac(a: AttrSetTac): Unit = queryAttrSetTac(a)
  override protected def queryRefAttrSetOpt(a: AttrSetOpt): Unit = queryAttrSetOpt(a)


  override protected def queryAttrSetMan(attr: AttrSetMan): Unit = {
    attrIndex += 1
    val (e, a) = (es.last, s":${attr.ent}/${attr.attr}")
    attr match {
      case at: AttrSetManID             => setMan(attr, e, a, at.vs, resSetId)
      case at: AttrSetManString         => setMan(attr, e, a, at.vs, resSetString)
      case at: AttrSetManInt            => setMan(attr, e, a, at.vs, resSetInt)
      case at: AttrSetManLong           => setMan(attr, e, a, at.vs, resSetLong)
      case at: AttrSetManFloat          => setMan(attr, e, a, at.vs, resSetFloat)
      case at: AttrSetManDouble         => setMan(attr, e, a, at.vs, resSetDouble)
      case at: AttrSetManBoolean        => setMan(attr, e, a, at.vs, resSetBoolean)
      case at: AttrSetManBigInt         => setMan(attr, e, a, at.vs, resSetBigInt)
      case at: AttrSetManBigDecimal     => setMan(attr, e, a, at.vs, resSetBigDecimal)
      case at: AttrSetManDate           => setMan(attr, e, a, at.vs, resSetDate)
      case at: AttrSetManDuration       => setMan(attr, e, a, at.vs, resSetDuration)
      case at: AttrSetManInstant        => setMan(attr, e, a, at.vs, resSetInstant)
      case at: AttrSetManLocalDate      => setMan(attr, e, a, at.vs, resSetLocalDate)
      case at: AttrSetManLocalTime      => setMan(attr, e, a, at.vs, resSetLocalTime)
      case at: AttrSetManLocalDateTime  => setMan(attr, e, a, at.vs, resSetLocalDateTime)
      case at: AttrSetManOffsetTime     => setMan(attr, e, a, at.vs, resSetOffsetTime)
      case at: AttrSetManOffsetDateTime => setMan(attr, e, a, at.vs, resSetOffsetDateTime)
      case at: AttrSetManZonedDateTime  => setMan(attr, e, a, at.vs, resSetZonedDateTime)
      case at: AttrSetManUUID           => setMan(attr, e, a, at.vs, resSetUUID)
      case at: AttrSetManURI            => setMan(attr, e, a, at.vs, resSetURI)
      case at: AttrSetManByte           => setMan(attr, e, a, at.vs, resSetByte)
      case at: AttrSetManShort          => setMan(attr, e, a, at.vs, resSetShort)
      case at: AttrSetManChar           => setMan(attr, e, a, at.vs, resSetChar)
    }
  }

  override protected def queryAttrSetTac(attr: AttrSetTac): Unit = {
    val (e, a) = (es.last, s":${attr.ent}/${attr.attr}")
    attr match {
      case at: AttrSetTacID             => setTac(attr, e, a, at.vs, resSetId)
      case at: AttrSetTacString         => setTac(attr, e, a, at.vs, resSetString)
      case at: AttrSetTacInt            => setTac(attr, e, a, at.vs, resSetInt)
      case at: AttrSetTacLong           => setTac(attr, e, a, at.vs, resSetLong)
      case at: AttrSetTacFloat          => setTac(attr, e, a, at.vs, resSetFloat)
      case at: AttrSetTacDouble         => setTac(attr, e, a, at.vs, resSetDouble)
      case at: AttrSetTacBoolean        => setTac(attr, e, a, at.vs, resSetBoolean)
      case at: AttrSetTacBigInt         => setTac(attr, e, a, at.vs, resSetBigInt)
      case at: AttrSetTacBigDecimal     => setTac(attr, e, a, at.vs, resSetBigDecimal)
      case at: AttrSetTacDate           => setTac(attr, e, a, at.vs, resSetDate)
      case at: AttrSetTacDuration       => setTac(attr, e, a, at.vs, resSetDuration)
      case at: AttrSetTacInstant        => setTac(attr, e, a, at.vs, resSetInstant)
      case at: AttrSetTacLocalDate      => setTac(attr, e, a, at.vs, resSetLocalDate)
      case at: AttrSetTacLocalTime      => setTac(attr, e, a, at.vs, resSetLocalTime)
      case at: AttrSetTacLocalDateTime  => setTac(attr, e, a, at.vs, resSetLocalDateTime)
      case at: AttrSetTacOffsetTime     => setTac(attr, e, a, at.vs, resSetOffsetTime)
      case at: AttrSetTacOffsetDateTime => setTac(attr, e, a, at.vs, resSetOffsetDateTime)
      case at: AttrSetTacZonedDateTime  => setTac(attr, e, a, at.vs, resSetZonedDateTime)
      case at: AttrSetTacUUID           => setTac(attr, e, a, at.vs, resSetUUID)
      case at: AttrSetTacURI            => setTac(attr, e, a, at.vs, resSetURI)
      case at: AttrSetTacByte           => setTac(attr, e, a, at.vs, resSetByte)
      case at: AttrSetTacShort          => setTac(attr, e, a, at.vs, resSetShort)
      case at: AttrSetTacChar           => setTac(attr, e, a, at.vs, resSetChar)
    }
  }

  override protected def queryAttrSetOpt(attr: AttrSetOpt): Unit = {
    attrIndex += 1
    val (e, a) = (es.last, s":${attr.ent}/${attr.attr}")
    attr match {
      case _: AttrSetOptID             => setOpt(attr, e, a, resOptSetId)
      case _: AttrSetOptString         => setOpt(attr, e, a, resOptSetString)
      case _: AttrSetOptInt            => setOpt(attr, e, a, resOptSetInt)
      case _: AttrSetOptLong           => setOpt(attr, e, a, resOptSetLong)
      case _: AttrSetOptFloat          => setOpt(attr, e, a, resOptSetFloat)
      case _: AttrSetOptDouble         => setOpt(attr, e, a, resOptSetDouble)
      case _: AttrSetOptBoolean        => setOpt(attr, e, a, resOptSetBoolean)
      case _: AttrSetOptBigInt         => setOpt(attr, e, a, resOptSetBigInt)
      case _: AttrSetOptBigDecimal     => setOpt(attr, e, a, resOptSetBigDecimal)
      case _: AttrSetOptDate           => setOpt(attr, e, a, resOptSetDate)
      case _: AttrSetOptDuration       => setOpt(attr, e, a, resOptSetDuration)
      case _: AttrSetOptInstant        => setOpt(attr, e, a, resOptSetInstant)
      case _: AttrSetOptLocalDate      => setOpt(attr, e, a, resOptSetLocalDate)
      case _: AttrSetOptLocalTime      => setOpt(attr, e, a, resOptSetLocalTime)
      case _: AttrSetOptLocalDateTime  => setOpt(attr, e, a, resOptSetLocalDateTime)
      case _: AttrSetOptOffsetTime     => setOpt(attr, e, a, resOptSetOffsetTime)
      case _: AttrSetOptOffsetDateTime => setOpt(attr, e, a, resOptSetOffsetDateTime)
      case _: AttrSetOptZonedDateTime  => setOpt(attr, e, a, resOptSetZonedDateTime)
      case _: AttrSetOptUUID           => setOpt(attr, e, a, resOptSetUUID)
      case _: AttrSetOptURI            => setOpt(attr, e, a, resOptSetURI)
      case _: AttrSetOptByte           => setOpt(attr, e, a, resOptSetByte)
      case _: AttrSetOptShort          => setOpt(attr, e, a, resOptSetShort)
      case _: AttrSetOptChar           => setOpt(attr, e, a, resOptSetChar)
    }
  }

  private def setMan[T](
    attr: Attr, e: Var, a: Att, args: Set[T], resSet: ResSet[T],
  ): Unit = {
    val v = vv
    find += s"(distinct $v)"
    addCast(resSet.j2sSet)
    attr.filterAttr.fold {
      val pathAttr = varPath :+ attr.cleanAttr
      if (filterAttrVars.contains(pathAttr) && attr.op != V) {
        noCardManyFilterAttrExpr(attr)
      }
      setExpr(false, attr, e, a, v, attr.op, args, resSet)
      filterAttrVars1 = filterAttrVars1 + (a -> (e -> v))
      filterAttrVars2.get(a).foreach(_(e, v))
    } { case (dir, filterPath, filterAttr) =>
      setFilterExpr(e, a, v, attr.op, s":${filterAttr.ent}/${filterAttr.attr}")
    }
    refConfirmed = true
  }

  private def setTac[T](
    attr: Attr, e: Var, a: Att, args: Set[T], resSet: ResSet[T],
  ): Unit = {
    val v = vv
    attr.filterAttr.fold {
      setExpr(true, attr, e, a, v, attr.op, args, resSet)
      filterAttrVars1 = filterAttrVars1 + (a -> (e -> v))
      filterAttrVars2.get(a).foreach(_(e, v))
    } { case (dir, filterPath, filterAttr) =>
      setFilterExpr(e, a, v, attr.op, s":${filterAttr.ent}/${filterAttr.attr}")
    }
    refConfirmed = true
  }

  private def setExpr[T](
    tacit: Boolean,
    attr: Attr, e: Var, a: Att, v: Var, op: Op,
    sets: Set[T],
    resSet: ResSet[T],
  ): Unit = {
    op match {
      case V       => setAttr(e, a, v)
      case Eq      => noCollectionMatching(attr)
      case Has     => setHas(e, a, v, sets, resSet.tpe, resSet.toDatalog)
      case HasNo   => setHasNo(e, a, v, sets, resSet.tpe, resSet.toDatalog)
      case NoValue => if (tacit) setNoValue(e, a) else noApplyNothing(attr)
      case other   => unexpectedOp(other)
    }
  }

  private def setFilterExpr(
    e: Var, a: Att, v: Var, op: Op, filterAttr: String
  ): Unit = {
    op match {
      case Has   => setFilterHas(e, a, v, filterAttr)
      case HasNo => setFilterHasNo(e, a, v, filterAttr)
      case other => unexpectedOp(other)
    }
  }

  private def setOpt[T](
    attr: Attr, e: Var, a: Att, resSetOpt: ResSetOpt[T],
  ): Unit = {
    val v = vv
    addCast(resSetOpt.j2sOptSet)
    attr.op match {
      case V     => setOptAttr(e, a, v, resSetOpt)
      case Eq    => noCollectionMatching(attr)
      case other => unexpectedOp(other)
    }
  }


  // attr ----------------------------------------------------------------------

  private def setAttr(e: Var, a: Att, v: Var): Unit = {
    where += s"[$e $a $v]" -> wClause
  }

  private def setOptAttr[T](e: Var, a: Att, v: Var, resOpt: ResSetOpt[T]): Unit = {
    if (refConfirmed) {
      val (e1, v1, v2, v3) = (e + 1, v + 1, v + 2, v + 3)
      find += s"(distinct $v3)"
      where +=
        s"""[(datomic.api/q
           |          "[:find (pull $e1 [[$a :limit nil]])
           |            :in $$ $e1]" $$ $e) [[$v1]]]""".stripMargin -> wClause
      where += s"[(if (nil? $v1) {$a []} $v1) $v2]" -> wClause
      where += s"[($a $v2) $v3]" -> wClause

    } else {
      val List(e0, card, refAttr, refId) = varPath.takeRight(4)
      val refDatom                       = s"[$e0 $refAttr $refId]"
      if (where.last == refDatom -> wClause) {
        // cancel previous ref Datom since we will pull it instead
        where.remove(where.size - 1)
        varPath = varPath.dropRight(3)
      }
      val e                        = varPath.last
      val (e1, v1, v2, v3, v4, v5) = (e0 + 1, v + 1, v + 2, v + 3, v + 4, v + 5)

      if (card == "one") {
        find += s"(distinct $v4)"
        where +=
          s"""[(datomic.api/q
             |          "[:find (pull $e1 [{$refAttr [$a]}] :limit nil)
             |            :in $$ $e1]" $$ $e) [[$v1]]]""".stripMargin -> wClause
        where += s"[(if (nil? $v1) {$refAttr {$a []}} $v1) $v2]" -> wClause
        where += s"[($refAttr $v2) $v3]" -> wClause
        where += s"[($a $v3) $v4]" -> wClause

      } else {
        find += s"(distinct $v5)"
        where +=
          s"""[(datomic.api/q
             |          "[:find (pull $e1 [{$refAttr [$a]}] :limit nil)
             |            :in $$ $e1]" $$ $e) [[$v1]]]""".stripMargin -> wClause
        where += s"[(if (nil? $v1) {$refAttr [{$a []}]} $v1) $v2]" -> wClause
        where += s"[($refAttr $v2) $v3]" -> wClause
        where += s"[(first $v3) $v4]" -> wClause
        where += s"[($a $v4) $v5]" -> wClause
      }
    }
    replaceCast(resOpt.optAttr2s)
  }

  private def setHas[T](
    e: Var, a: Att, v: Var, set: Set[T], tpe: String, toDatalog: T => String
  ): Unit = {
    where += s"[$e $a $v]" -> wClause
    if (set.nonEmpty) {
      where += s"(rule$v $e)" -> wClause
      rules ++= mkRules(e, a, v, set, tpe, toDatalog)
    } else {
      where += s"[(ground nil) $v]" -> wGround
    }
  }

  private def setHasNo[T](
    e: Var, a: Att, v: Var, set: Set[T], tpe: String, toDatalog: T => String
  ): Unit = {
    // Common for pre-query and main query
    where += s"[$e $a $v]" -> wClause
    if (set.nonEmpty) {
      // Pre-query
      preWhere += s"(rule$v $e)" -> wClause
      preRules ++= mkRules(e, a, v, set, tpe, toDatalog)

      // Main query
      val blacklist   = v + "-blacklist"
      val blacklisted = v + "-blacklisted"
      inPost += blacklist
      wherePost += s"[(contains? $blacklist $firstId) $blacklisted]" -> wClause
      wherePost += s"[(not $blacklisted)]" -> wClause
    }
  }

  private def setNoValue(e: Var, a: Att): Unit = {
    if (refConfirmed) {
      where += s"(not [$e $a])" -> wNeqOne
    } else {
      val List(e0, _, refAttr, refId) = varPath.takeRight(4)
      val refDatom                    = s"[$e0 $refAttr $refId]"
      if (where.last == refDatom -> wClause) {
        // cancel previous ref Datom since we will pull it instead
        where.remove(where.size - 1)
        varPath = varPath.dropRight(3)
      }
      where += s"(not [$e0 $refAttr])" -> wNeqOne
    }
  }


  // filter attribute  ---------------------------------------------------------

  private def setFilterHas(e: Var, a: Att, v: Var, filterAttr: String): Unit = {
    where += s"[$e $a $v]" -> wClause
    val process: (Var, Var) => Unit = (f: Var, w: Var) => {
      where +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${v}1)
           |            :in $$ ${e}1
           |            :where [${e}1 $a ${v}1]]" $$ $e) [[${v}2]]]""".stripMargin -> wClause
      where +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${w}1)
           |            :in $$ ${f}1
           |            :where [${f}1 $filterAttr ${w}1]]" $$ $f) [[${w}2]]]""".stripMargin -> wClause
      where += s"[(clojure.set/intersection ${v}2 ${w}2) $w-intersection]" -> wClause
      where += s"[(= ${w}2 $w-intersection)]" -> wClause
    }
    filterAttrVars1.get(filterAttr).fold {
      filterAttrVars2 = filterAttrVars2 + (filterAttr -> process)
    } { case (e, a) =>
      process(e, a)
    }
  }


  private def setFilterHasNo(e: Var, a: Att, v: Var, filterAttr: String): Unit = {
    where += s"[$e $a $v]" -> wClause
    val process: (Var, Var) => Unit = (f: Var, w: Var) => {
      where +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${v}1)
           |            :in $$ ${e}1
           |            :where [${e}1 $a ${v}1]]" $$ $e) [[${v}2]]]""".stripMargin -> wClause
      where +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${w}1)
           |            :in $$ ${f}1
           |            :where [${f}1 $filterAttr ${w}1]]" $$ $f) [[${w}2]]]""".stripMargin -> wClause
      where += s"[(clojure.set/intersection ${v}2 ${w}2) $w-intersection]" -> wClause
      where += s"[(empty? $w-intersection)]" -> wClause
    }
    filterAttrVars1.get(filterAttr).fold {
      filterAttrVars2 = filterAttrVars2 + (filterAttr -> process)
    } { case (e, a) =>
      process(e, a)
    }
  }


  // helpers -------------------------------------------------------------------

  private def mkRules[T](
    e: Var, a: Att, v: Var, set: Set[T], tpe: String, toDatalog: T => String
  ): Seq[String] = {
    tpe match {
      case "Float" => set.toSeq.zipWithIndex.map { case (arg, i) =>
        // Coerce Datomic float values for correct comparison (don't know why this is necessary)
        // See example: https://clojurians-log.clojureverse.org/datomic/2019-10-29
        s"""[(rule$v $e)
           |    [$e $a $v$i] [(float $v$i) $v$i-float] [(= $v$i-float (float $arg))]]""".stripMargin
      }

      case "URI" => set.toSeq.zipWithIndex.map { case (arg, i) =>
        s"""[(rule$v $e)
           |    [(ground (new java.net.URI "$arg")) $v$i-uri] [$e $a $v$i-uri]]""".stripMargin
      }

      case _ => set.toSeq.map { arg =>
        s"""[(rule$v $e)
           |    [$e $a ${toDatalog(arg)}]]""".stripMargin
      }
    }
  }
}