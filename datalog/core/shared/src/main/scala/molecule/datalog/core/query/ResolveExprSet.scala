package molecule.datalog.core.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import scala.reflect.ClassTag

trait ResolveExprSet[Tpl] { self: Model2DatomicQuery[Tpl] with LambdasSet =>

  protected def resolveAttrSetMan(es: List[Var], attr: AttrSetMan): List[Var] = {
    aritiesAttr()
    attrIndex += 1
    val (e, a) = (es.last, s":${attr.ns}/${attr.attr}")
    attr match {
      case at: AttrSetManID             => man(attr, e, a, at.vs, resSetId)
      case at: AttrSetManString         => man(attr, e, a, at.vs, resSetString)
      case at: AttrSetManInt            => man(attr, e, a, at.vs, resSetInt)
      case at: AttrSetManLong           => man(attr, e, a, at.vs, resSetLong)
      case at: AttrSetManFloat          => man(attr, e, a, at.vs, resSetFloat)
      case at: AttrSetManDouble         => man(attr, e, a, at.vs, resSetDouble)
      case at: AttrSetManBoolean        => man(attr, e, a, at.vs, resSetBoolean)
      case at: AttrSetManBigInt         => man(attr, e, a, at.vs, resSetBigInt)
      case at: AttrSetManBigDecimal     => man(attr, e, a, at.vs, resSetBigDecimal)
      case at: AttrSetManDate           => man(attr, e, a, at.vs, resSetDate)
      case at: AttrSetManDuration       => man(attr, e, a, at.vs, resSetDuration)
      case at: AttrSetManInstant        => man(attr, e, a, at.vs, resSetInstant)
      case at: AttrSetManLocalDate      => man(attr, e, a, at.vs, resSetLocalDate)
      case at: AttrSetManLocalTime      => man(attr, e, a, at.vs, resSetLocalTime)
      case at: AttrSetManLocalDateTime  => man(attr, e, a, at.vs, resSetLocalDateTime)
      case at: AttrSetManOffsetTime     => man(attr, e, a, at.vs, resSetOffsetTime)
      case at: AttrSetManOffsetDateTime => man(attr, e, a, at.vs, resSetOffsetDateTime)
      case at: AttrSetManZonedDateTime  => man(attr, e, a, at.vs, resSetZonedDateTime)
      case at: AttrSetManUUID           => man(attr, e, a, at.vs, resSetUUID)
      case at: AttrSetManURI            => man(attr, e, a, at.vs, resSetURI)
      case at: AttrSetManByte           => man(attr, e, a, at.vs, resSetByte)
      case at: AttrSetManShort          => man(attr, e, a, at.vs, resSetShort)
      case at: AttrSetManChar           => man(attr, e, a, at.vs, resSetChar)
    }
    es
  }

  protected def resolveAttrSetTac(es: List[Var], attr: AttrSetTac): List[Var] = {
    val (e, a) = (es.last, s":${attr.ns}/${attr.attr}")
    attr match {
      case at: AttrSetTacID             => tac(attr, e, a, at.vs, resSetId)
      case at: AttrSetTacString         => tac(attr, e, a, at.vs, resSetString)
      case at: AttrSetTacInt            => tac(attr, e, a, at.vs, resSetInt)
      case at: AttrSetTacLong           => tac(attr, e, a, at.vs, resSetLong)
      case at: AttrSetTacFloat          => tac(attr, e, a, at.vs, resSetFloat)
      case at: AttrSetTacDouble         => tac(attr, e, a, at.vs, resSetDouble)
      case at: AttrSetTacBoolean        => tac(attr, e, a, at.vs, resSetBoolean)
      case at: AttrSetTacBigInt         => tac(attr, e, a, at.vs, resSetBigInt)
      case at: AttrSetTacBigDecimal     => tac(attr, e, a, at.vs, resSetBigDecimal)
      case at: AttrSetTacDate           => tac(attr, e, a, at.vs, resSetDate)
      case at: AttrSetTacDuration       => tac(attr, e, a, at.vs, resSetDuration)
      case at: AttrSetTacInstant        => tac(attr, e, a, at.vs, resSetInstant)
      case at: AttrSetTacLocalDate      => tac(attr, e, a, at.vs, resSetLocalDate)
      case at: AttrSetTacLocalTime      => tac(attr, e, a, at.vs, resSetLocalTime)
      case at: AttrSetTacLocalDateTime  => tac(attr, e, a, at.vs, resSetLocalDateTime)
      case at: AttrSetTacOffsetTime     => tac(attr, e, a, at.vs, resSetOffsetTime)
      case at: AttrSetTacOffsetDateTime => tac(attr, e, a, at.vs, resSetOffsetDateTime)
      case at: AttrSetTacZonedDateTime  => tac(attr, e, a, at.vs, resSetZonedDateTime)
      case at: AttrSetTacUUID           => tac(attr, e, a, at.vs, resSetUUID)
      case at: AttrSetTacURI            => tac(attr, e, a, at.vs, resSetURI)
      case at: AttrSetTacByte           => tac(attr, e, a, at.vs, resSetByte)
      case at: AttrSetTacShort          => tac(attr, e, a, at.vs, resSetShort)
      case at: AttrSetTacChar           => tac(attr, e, a, at.vs, resSetChar)
    }
    es
  }

  protected def resolveAttrSetOpt(es: List[Var], attr: AttrSetOpt): List[Var] = {
    aritiesAttr()
    attrIndex += 1
    hasOptAttr = true
    val (e, a) = (es.last, s":${attr.ns}/${attr.attr}")
    attr match {
      case _: AttrSetOptID             => opt(attr, e, a, resOptSetId)
      case _: AttrSetOptString         => opt(attr, e, a, resOptSetString)
      case _: AttrSetOptInt            => opt(attr, e, a, resOptSetInt)
      case _: AttrSetOptLong           => opt(attr, e, a, resOptSetLong)
      case _: AttrSetOptFloat          => opt(attr, e, a, resOptSetFloat)
      case _: AttrSetOptDouble         => opt(attr, e, a, resOptSetDouble)
      case _: AttrSetOptBoolean        => opt(attr, e, a, resOptSetBoolean)
      case _: AttrSetOptBigInt         => opt(attr, e, a, resOptSetBigInt)
      case _: AttrSetOptBigDecimal     => opt(attr, e, a, resOptSetBigDecimal)
      case _: AttrSetOptDate           => opt(attr, e, a, resOptSetDate)
      case _: AttrSetOptDuration       => opt(attr, e, a, resOptSetDuration)
      case _: AttrSetOptInstant        => opt(attr, e, a, resOptSetInstant)
      case _: AttrSetOptLocalDate      => opt(attr, e, a, resOptSetLocalDate)
      case _: AttrSetOptLocalTime      => opt(attr, e, a, resOptSetLocalTime)
      case _: AttrSetOptLocalDateTime  => opt(attr, e, a, resOptSetLocalDateTime)
      case _: AttrSetOptOffsetTime     => opt(attr, e, a, resOptSetOffsetTime)
      case _: AttrSetOptOffsetDateTime => opt(attr, e, a, resOptSetOffsetDateTime)
      case _: AttrSetOptZonedDateTime  => opt(attr, e, a, resOptSetZonedDateTime)
      case _: AttrSetOptUUID           => opt(attr, e, a, resOptSetUUID)
      case _: AttrSetOptURI            => opt(attr, e, a, resOptSetURI)
      case _: AttrSetOptByte           => opt(attr, e, a, resOptSetByte)
      case _: AttrSetOptShort          => opt(attr, e, a, resOptSetShort)
      case _: AttrSetOptChar           => opt(attr, e, a, resOptSetChar)
    }
    es
  }

  private def man[T: ClassTag](
    attr: Attr, e: Var, a: Att, args: Set[T], resSet: ResSet[T],
  ): Unit = {
    val v = vv
    find += s"(distinct $v)"
    addCast(resSet.j2sSet)
    attr.filterAttr.fold {
      val pathAttr = varPath :+ attr.cleanAttr
      if (filterAttrVars.contains(pathAttr) && attr.op != V) {
        // Runtime check needed since we can't type infer it
        throw ModelError(s"Cardinality-set filter attributes not allowed to " +
          s"do additional filtering. Found:\n  " + attr)
      }
      expr(false, attr, e, a, v, attr.op, args, resSet)
      filterAttrVars1 = filterAttrVars1 + (a -> (e, v))
      filterAttrVars2.get(a).foreach(_(e, v))
    } { case (dir, filterPath, filterAttr) =>
      expr2(e, a, v, attr.op, s":${filterAttr.ns}/${filterAttr.attr}")
    }
    refConfirmed = true
  }

  private def tac[T: ClassTag](
    attr: Attr, e: Var, a: Att, args: Set[T], resSet: ResSet[T],
  ): Unit = {
    val v = vv
    attr.filterAttr.fold {
      expr(true, attr, e, a, v, attr.op, args, resSet)
      filterAttrVars1 = filterAttrVars1 + (a -> (e, v))
      filterAttrVars2.get(a).foreach(_(e, v))
    } { case (dir, filterPath, filterAttr) =>
      expr2(e, a, v, attr.op, s":${filterAttr.ns}/${filterAttr.attr}")
    }
    refConfirmed = true
  }

  private def expr[T: ClassTag](
    tacit: Boolean,
    attr: Attr, e: Var, a: Att, v: Var, op: Op,
    sets: Set[T],
    resSet: ResSet[T],
  ): Unit = {
    op match {
      case V       => attrV(e, a, v)
      case Eq      => noCollectionMatching(attr)
      case Has     => has(e, a, v, sets, resSet.tpe, resSet.toDatalog)
      case HasNo   => hasNo(e, a, v, sets, resSet.tpe, resSet.toDatalog)
      case NoValue => if (tacit) noValue(e, a) else noApplyNothing(attr)
      case other   => unexpectedOp(other)
    }
  }

  private def expr2(
    e: Var, a: Att, v: Var, op: Op, filterAttr: String
  ): Unit = {
    op match {
      case Has   => has2(e, a, v, filterAttr)
      case HasNo => hasNo2(e, a, v, filterAttr)
      case other => unexpectedOp(other)
    }
  }

  private def opt[T: ClassTag](
    attr: Attr, e: Var, a: Att, resSetOpt: ResSetOpt[T],
  ): Unit = {
    val v = vv
    addCast(resSetOpt.j2sOptSet)
    attr.op match {
      case V     => optAttr(e, a, v, resSetOpt)
      case Eq    => noCollectionMatching(attr)
      case other => unexpectedOp(other)
    }
  }


  // attr ----------------------------------------------------------------------

  private def attrV(e: Var, a: Att, v: Var): Unit = {
    where += s"[$e $a $v]" -> wClause
  }

  private def optAttr[T](e: Var, a: Att, v: Var, resOpt: ResSetOpt[T]): Unit = {
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

  private def has[T: ClassTag](
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

  private def hasNo[T](
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

  private def noValue(e: Var, a: Att): Unit = {
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

  private def has2(e: Var, a: Att, v: Var, filterAttr: String): Unit = {
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


  private def hasNo2(e: Var, a: Att, v: Var, filterAttr: String): Unit = {
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