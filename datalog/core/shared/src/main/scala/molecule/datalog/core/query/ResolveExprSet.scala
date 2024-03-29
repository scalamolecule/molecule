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
    hasOptAttr = true // to avoid redundant None's
    val (e, a) = (es.last, s":${attr.ns}/${attr.attr}")
    attr match {
      case at: AttrSetOptID             => opt(attr, e, a, at.vs, resOptSetId)
      case at: AttrSetOptString         => opt(attr, e, a, at.vs, resOptSetString)
      case at: AttrSetOptInt            => opt(attr, e, a, at.vs, resOptSetInt)
      case at: AttrSetOptLong           => opt(attr, e, a, at.vs, resOptSetLong)
      case at: AttrSetOptFloat          => opt(attr, e, a, at.vs, resOptSetFloat)
      case at: AttrSetOptDouble         => opt(attr, e, a, at.vs, resOptSetDouble)
      case at: AttrSetOptBoolean        => opt(attr, e, a, at.vs, resOptSetBoolean)
      case at: AttrSetOptBigInt         => opt(attr, e, a, at.vs, resOptSetBigInt)
      case at: AttrSetOptBigDecimal     => opt(attr, e, a, at.vs, resOptSetBigDecimal)
      case at: AttrSetOptDate           => opt(attr, e, a, at.vs, resOptSetDate)
      case at: AttrSetOptDuration       => opt(attr, e, a, at.vs, resOptSetDuration)
      case at: AttrSetOptInstant        => opt(attr, e, a, at.vs, resOptSetInstant)
      case at: AttrSetOptLocalDate      => opt(attr, e, a, at.vs, resOptSetLocalDate)
      case at: AttrSetOptLocalTime      => opt(attr, e, a, at.vs, resOptSetLocalTime)
      case at: AttrSetOptLocalDateTime  => opt(attr, e, a, at.vs, resOptSetLocalDateTime)
      case at: AttrSetOptOffsetTime     => opt(attr, e, a, at.vs, resOptSetOffsetTime)
      case at: AttrSetOptOffsetDateTime => opt(attr, e, a, at.vs, resOptSetOffsetDateTime)
      case at: AttrSetOptZonedDateTime  => opt(attr, e, a, at.vs, resOptSetZonedDateTime)
      case at: AttrSetOptUUID           => opt(attr, e, a, at.vs, resOptSetUUID)
      case at: AttrSetOptURI            => opt(attr, e, a, at.vs, resOptSetURI)
      case at: AttrSetOptByte           => opt(attr, e, a, at.vs, resOptSetByte)
      case at: AttrSetOptShort          => opt(attr, e, a, at.vs, resOptSetShort)
      case at: AttrSetOptChar           => opt(attr, e, a, at.vs, resOptSetChar)
    }
    es
  }

  private def man[T: ClassTag](
    attr: Attr, e: Var, a: Att, args: Seq[Set[T]], resSet: ResSet[T],
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
  }

  private def tac[T: ClassTag](
    attr: Attr, e: Var, a: Att, args: Seq[Set[T]], resSet: ResSet[T],
  ): Unit = {
    val v = vv
    attr.filterAttr.fold {
      expr(true, attr, e, a, v, attr.op, args, resSet)
      filterAttrVars1 = filterAttrVars1 + (a -> (e, v))
      filterAttrVars2.get(a).foreach(_(e, v))
    } { case (dir, filterPath, filterAttr) =>
      expr2(e, a, v, attr.op, s":${filterAttr.ns}/${filterAttr.attr}")
    }
  }

  private def expr[T: ClassTag](
    tacit: Boolean,
    attr: Attr, e: Var, a: Att, v: Var, op: Op,
    sets: Seq[Set[T]],
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
    attr: Attr,
    e: Var,
    a: Att,
    optSets: Option[Seq[Set[T]]],
    resSetOpt: ResSetOpt[T],
  ): Unit = {
    val v = vv
    addCast(resSetOpt.j2sOptSet)
    attr.op match {
      case V     => optAttr(e, a, v, resSetOpt)
      case Eq    => noCollectionMatching(attr)
      case Has   => optHas(e, a, v, optSets, resSetOpt.tpe, resSetOpt.toDatalog)
      case HasNo => optHasNo(e, a, v, optSets, resSetOpt.tpe, resSetOpt.toDatalog)
      case other => unexpectedOp(other)
    }
  }


  // attr ----------------------------------------------------------------------

  private def attrV(e: Var, a: Att, v: Var): Unit = {
    where += s"[$e $a $v]" -> wClause
  }

  private def optAttr[T](e: Var, a: Att, v: Var, resSetOpt: ResSetOpt[T]): Unit = {
    val (e1, v1, v2, v3) = (e + 1, v + 1, v + 2, v + 3)
    find += s"(distinct $v3)"
    where +=
      s"""[(datomic.api/q
         |          "[:find (pull $e1 [[$a :limit nil]])
         |            :in $$ $e1]" $$ $e) [[$v1]]]""".stripMargin -> wClause
    where += s"[(if (nil? $v1) {$a []} $v1) $v2]" -> wClause
    where += s"[($a $v2) $v3]" -> wClause
    replaceCast(resSetOpt.optAttr2s)
  }


  // has -----------------------------------------------------------------------

  private def has[T: ClassTag](
    e: Var, a: Att, v: Var, sets: Seq[Set[T]], tpe: String, toDatalog: T => String
  ): Unit = {
    where += s"[$e $a $v]" -> wClause
    if (sets.nonEmpty && sets.flatten.nonEmpty) {
      where += s"(rule$v $e)" -> wClause
      rules ++= mkRules(e, a, v, sets, tpe, toDatalog)
    } else {
      where += s"[(ground nil) $v]" -> wGround
    }
  }

  private def optHas[T: ClassTag](
    e: Var, a: Att, v: Var,
    optSets: Option[Seq[Set[T]]],
    tpe: String,
    toDatalog: T => String
  ): Unit = {
    optSets.fold[Unit] {
      none(e, a, v)
    } { sets =>
      find += s"(distinct $v)"
      has(e, a, v, sets, tpe, toDatalog)
    }
  }

  // hasNo ---------------------------------------------------------------------

  private def hasNo[T](
    e: Var, a: Att, v: Var, sets: Seq[Set[T]], tpe: String, toDatalog: T => String
  ): Unit = {
    // Common for pre-query and main query
    where += s"[$e $a $v]" -> wClause

    if (sets.nonEmpty && sets.flatten.nonEmpty) {
      // Pre-query
      preWhere += s"(rule$v $e)" -> wClause
      preRules ++= mkRules(e, a, v, sets, tpe, toDatalog)

      // Main query
      val blacklist   = v + "-blacklist"
      val blacklisted = v + "-blacklisted"
      inPost += blacklist
      wherePost += s"[(contains? $blacklist $firstId) $blacklisted]" -> wClause
      wherePost += s"[(not $blacklisted)]" -> wClause
    }
  }

  private def optHasNo[T](
    e: Var, a: Att, v: Var,
    optSets: Option[Seq[Set[T]]],
    tpe: String,
    toDatalog: T => String
  ): Unit = {
    find += s"(distinct $v)"
    if (optSets.isDefined) {
      hasNo(e, a, v, optSets.get, tpe, toDatalog)
    } else {
      where += s"[$e $a $v]" -> wClause
    }
  }


  // no value -----------------------------------------------------------------

  private def noValue(e: Var, a: Att): Unit = {
    where += s"(not [$e $a])" -> wNeqOne
  }


  // Filter attribute filters --------------------------------------------------

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

  private def none[T](e: Var, a: Att, v: Var): Unit = {
    find += s"(pull $e-$v [[$a :limit nil]])"
    where += s"(not [$e $a])" -> wNeqOne
    where += s"[(identity $e) $e-$v]" -> wGround
  }

  private def mkRules[T](
    e: Var, a: Att, v: Var, sets: Seq[Set[T]], tpe: String, toDatalog: T => String
  ): Seq[String] = {
    tpe match {
      case "Float" =>
        sets.flatMap {
          case set if set.isEmpty => None
          case set                => Some(
            set.zipWithIndex.map { case (arg, i) =>
              // Coerce Datomic float values for correct comparison (don't know why this is necessary)
              // See example: https://clojurians-log.clojureverse.org/datomic/2019-10-29
              s"""[$e $a $v$i] [(float $v$i) $v$i-float] [(= $v$i-float (float $arg))]"""
            }.mkString(s"[(rule$v $e)\n    ", "\n    ", "]")
          )
        }
      case "URI"   =>
        sets.flatMap {
          case set if set.isEmpty => None
          case set                => Some(
            set.zipWithIndex.map { case (arg, i) =>
              s"""[(ground (new java.net.URI "$arg")) $v$i-uri] [$e $a $v$i-uri]"""
            }.mkString(s"[(rule$v $e)\n    ", "\n    ", "]")
          )
        }
      case _       =>
        sets.flatMap {
          case set if set.isEmpty => None
          case set                => Some(
            set.map(arg => s"[$e $a ${toDatalog(arg)}]")
              .mkString(s"[(rule$v $e)\n    ", "\n    ", "]")
          )
        }
    }
  }
}