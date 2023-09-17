package molecule.datalog.core.query

import java.util.{Set => jSet}
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import scala.reflect.ClassTag

trait ResolveExprSet[Tpl] { self: Model2DatomicQuery[Tpl] with LambdasSet =>

  protected def resolveAttrSetMan(es: List[Var], attr: AttrSetMan): List[Var] = {
    aritiesAttr()
    attrIndex += 1
    val (e, a) = (es.last, s":${attr.ns}/${attr.attr}")
    attr match {
      case at: AttrSetManString     => man(attr, e, a, at.vs, resSetString)
      case at: AttrSetManInt        => man(attr, e, a, at.vs, resSetInt)
      case at: AttrSetManLong       => man(attr, e, a, at.vs, resSetLong)
      case at: AttrSetManFloat      => man(attr, e, a, at.vs, resSetFloat)
      case at: AttrSetManDouble     => man(attr, e, a, at.vs, resSetDouble)
      case at: AttrSetManBoolean    => man(attr, e, a, at.vs, resSetBoolean)
      case at: AttrSetManBigInt     => man(attr, e, a, at.vs, resSetBigInt)
      case at: AttrSetManBigDecimal => man(attr, e, a, at.vs, resSetBigDecimal)
      case at: AttrSetManDate       => man(attr, e, a, at.vs, resSetDate)
      case at: AttrSetManUUID       => man(attr, e, a, at.vs, resSetUUID)
      case at: AttrSetManURI        => man(attr, e, a, at.vs, resSetURI)
      case at: AttrSetManByte       => man(attr, e, a, at.vs, resSetByte)
      case at: AttrSetManShort      => man(attr, e, a, at.vs, resSetShort)
      case at: AttrSetManChar       => man(attr, e, a, at.vs, resSetChar)
    }
    es
  }

  protected def resolveAttrSetTac(es: List[Var], attr: AttrSetTac): List[Var] = {
    val (e, a) = (es.last, s":${attr.ns}/${attr.attr}")
    attr match {
      case at: AttrSetTacString     => tac(attr, e, a, at.vs, resSetString)
      case at: AttrSetTacInt        => tac(attr, e, a, at.vs, resSetInt)
      case at: AttrSetTacLong       => tac(attr, e, a, at.vs, resSetLong)
      case at: AttrSetTacFloat      => tac(attr, e, a, at.vs, resSetFloat)
      case at: AttrSetTacDouble     => tac(attr, e, a, at.vs, resSetDouble)
      case at: AttrSetTacBoolean    => tac(attr, e, a, at.vs, resSetBoolean)
      case at: AttrSetTacBigInt     => tac(attr, e, a, at.vs, resSetBigInt)
      case at: AttrSetTacBigDecimal => tac(attr, e, a, at.vs, resSetBigDecimal)
      case at: AttrSetTacDate       => tac(attr, e, a, at.vs, resSetDate)
      case at: AttrSetTacUUID       => tac(attr, e, a, at.vs, resSetUUID)
      case at: AttrSetTacURI        => tac(attr, e, a, at.vs, resSetURI)
      case at: AttrSetTacByte       => tac(attr, e, a, at.vs, resSetByte)
      case at: AttrSetTacShort      => tac(attr, e, a, at.vs, resSetShort)
      case at: AttrSetTacChar       => tac(attr, e, a, at.vs, resSetChar)
    }
    es
  }

  protected def resolveAttrSetOpt(es: List[Var], attr: AttrSetOpt): List[Var] = {
    aritiesAttr()
    attrIndex += 1
    hasOptAttr = true // to avoid redundant None's
    val (e, a) = (es.last, s":${attr.ns}/${attr.attr}")
    attr match {
      case at: AttrSetOptString     => opt(e, a, at.op, at.vs, resOptSetString)
      case at: AttrSetOptInt        => opt(e, a, at.op, at.vs, resOptSetInt)
      case at: AttrSetOptLong       => opt(e, a, at.op, at.vs, resOptSetLong)
      case at: AttrSetOptFloat      => opt(e, a, at.op, at.vs, resOptSetFloat)
      case at: AttrSetOptDouble     => opt(e, a, at.op, at.vs, resOptSetDouble)
      case at: AttrSetOptBoolean    => opt(e, a, at.op, at.vs, resOptSetBoolean)
      case at: AttrSetOptBigInt     => opt(e, a, at.op, at.vs, resOptSetBigInt)
      case at: AttrSetOptBigDecimal => opt(e, a, at.op, at.vs, resOptSetBigDecimal)
      case at: AttrSetOptDate       => opt(e, a, at.op, at.vs, resOptSetDate)
      case at: AttrSetOptUUID       => opt(e, a, at.op, at.vs, resOptSetUUID)
      case at: AttrSetOptURI        => opt(e, a, at.op, at.vs, resOptSetURI)
      case at: AttrSetOptByte       => opt(e, a, at.op, at.vs, resOptSetByte)
      case at: AttrSetOptShort      => opt(e, a, at.op, at.vs, resOptSetShort)
      case at: AttrSetOptChar       => opt(e, a, at.op, at.vs, resOptSetChar)
    }
    es
  }


  private def man[T: ClassTag](
    attr: Attr,
    e: Var,
    a: Att,
    args: Seq[Set[T]],
    res: ResSet[T],
  ): Unit = {
    val v = vv
    find += s"(distinct $v)"
    addCast(res.j2s)
    attr.filterAttr.fold {
      if (filterAttrVars.contains(attr.name) && attr.op != V) {
        // Runtime check needed since we can't type infer it
        throw ModelError(s"Cardinality-set filter attributes not allowed to do additional filtering. Found:\n  " + attr)
      }
      expr(e, a, v, attr.op, args, res)
      filterAttrVars1 = filterAttrVars1 + (a -> (e, v))
      filterAttrVars2.get(a).foreach(_(e, v))
    } { filterAttr =>
      expr2(e, a, v, attr.op, s":${filterAttr.ns}/${filterAttr.attr}")
    }
  }

  private def tac[T: ClassTag](
    attr: Attr,
    e: Var,
    a: Att,
    args: Seq[Set[T]],
    res: ResSet[T],
  ): Unit = {
    val v = vv
    attr.filterAttr.fold {
      expr(e, a, v, attr.op, args, res)
      filterAttrVars1 = filterAttrVars1 + (a -> (e, v))
      filterAttrVars2.get(a).foreach(_(e, v))
    } { filterAttr =>
      expr2(e, a, v, attr.op, s":${filterAttr.ns}/${filterAttr.attr}")
    }
  }

  private def expr[T: ClassTag](
    e: Var,
    a: Att,
    v: Var,
    op: Op,
    sets: Seq[Set[T]],
    res: ResSet[T],
  ): Unit = {
    op match {
      case V         => attr(e, a, v)
      case Eq        => equal(e, a, v, sets, res.s2j)
      case Neq       => neq(e, a, v, sets, res.s2j)
      case Has       => has(e, a, v, sets, res.tpe, res.toDatalog)
      case HasNo     => hasNo(e, a, v, sets, res.tpe, res.toDatalog)
      case NoValue   => noValue(e, a)
      case Fn(kw, n) =>
        if (isRef)
          throw ModelError("Aggregating Sets of ref ids not supported.")
        else
          aggr(e, a, v, kw, n, res)
      case other     => unexpectedOp(other)
    }
  }

  private def expr2(
    e: Var,
    a: Att,
    v: Var,
    op: Op,
    filterAttr: String
  ): Unit = {
    op match {
      case Eq    => equal2(e, a, v, filterAttr)
      case Neq   => neq2(e, a, v, filterAttr)
      case Has   => has2(e, a, v, filterAttr)
      case HasNo => hasNo2(e, a, v, filterAttr)
      case other => unexpectedOp(other)
    }
  }

  private def opt[T: ClassTag](
    e: Var,
    a: Att,
    op: Op,
    optSets: Option[Seq[Set[T]]],
    resOpt: ResSetOpt[T],
  ): Unit = {
    val v = vv
    addCast(resOpt.j2s)
    op match {
      case V     => optV(e, a, v)
      case Eq    => optEqual(e, a, v, optSets, resOpt.s2j)
      case Neq   => optNeq(e, a, v, optSets, resOpt.s2j)
      case Has   => optHas(e, a, v, optSets, resOpt.tpe, resOpt.toDatalog)
      case HasNo => optHasNo(e, a, v, optSets, resOpt.tpe, resOpt.toDatalog)
      case other => unexpectedOp(other)
    }
  }


  protected def noBooleanSetAggr[T](res: ResSet[T]): Unit = {
    if (res.tpe == "Boolean")
      throw ModelError("Aggregate functions not implemented for Sets of boolean values.")
  }
  protected def noBooleanSetCounts(n: Int): Unit = {
    if (n == -1)
      throw ModelError("Aggregate functions not implemented for Sets of boolean values.")
  }

  private def aggr[T](e: Var, a: Att, v: Var, fn: String, optN: Option[Int], res: ResSet[T]): Unit = {
    lazy val n = optN.getOrElse(0)
    // Replace find/casting with aggregate function/cast
    find -= s"(distinct $v)"
    fn match {
      case "distinct" =>
        noBooleanSetAggr(res)
        val (v1, v2, e1) = (v + 1, v + 2, e + 1)
        find += s"(distinct $v2)"
        where += s"[$e $a $v]" -> wClause
        where +=
          s"""[(datomic.api/q
             |          "[:find (distinct $v1)
             |            :in $$ $e1
             |            :where [$e1 $a $v1]]" $$ $e) [[$v2]]]""".stripMargin -> wClause
        replaceCast(res.set2sets)

      case "min" =>
        noBooleanSetAggr(res)
        find += s"(min 1 $v)"
        replaceCast(res.vector2set)

      case "mins" =>
        noBooleanSetAggr(res)
        find += s"(min $n $v)"
        replaceCast(res.vector2set)

      case "max" =>
        noBooleanSetAggr(res)
        find += s"(max 1 $v)"
        replaceCast(res.vector2set)

      case "maxs" =>
        noBooleanSetAggr(res)
        find += s"(max $n $v)"
        replaceCast(res.vector2set)

      case "rand" =>
        noBooleanSetAggr(res)
        find += s"(rand 1 $v)"
        replaceCast(res.vector2set)

      case "rands" =>
        noBooleanSetAggr(res)
        find += s"(rand $n $v)"
        replaceCast(res.vector2set)

      case "sample" =>
        noBooleanSetAggr(res)
        find += s"(sample 1 $v)"
        replaceCast(res.vector2set)

      case "samples" =>
        noBooleanSetAggr(res)
        find += s"(sample $n $v)"
        replaceCast(res.vector2set)

      case "count" =>
        noBooleanSetCounts(n)
        find += s"(count $v)"
        widh += e
        replaceCast(toInt)

      case "countDistinct" =>
        noBooleanSetCounts(n)
        find += s"(count-distinct $v)"
        widh += e
        replaceCast(toInt)

      case "sum" =>
        find += s"(sum $v)"
        replaceCast(res.j2sSet)

      case "median" =>
        // OBS! Datomic rounds down to nearest whole number
        // when calculating the median for multiple numbers instead of
        // following the semantic described on wikipedia:
        // https://en.wikipedia.org/wiki/Median
        // See also
        // https://forum.datomic.com/t/unexpected-median-rounding/517
        // So we calculate the correct median value manually instead:
        find += s"(distinct $v)"
        val medianConverter: AnyRef => Double = {
          (v: AnyRef) => getMedian(v.asInstanceOf[jSet[_]].toArray.map(_.toString.toDouble).toSet)
        }
        replaceCast(medianConverter.asInstanceOf[AnyRef => AnyRef])

      case "avg" =>
        find += s"(avg $v)"
        replaceCast(any2double)

      case "variance" =>
        find += s"(variance $v)"
        replaceCast(any2double)

      case "stddev" =>
        find += s"(stddev $v)"
        replaceCast(any2double)

      case other => unexpectedKw(other)
    }
    where += s"[$e $a $v]" -> wClause
  }

  private def attr(e: Var, a: Att, v: Var): Unit = {
    where += s"[$e $a $v]" -> wClause
  }


  private def equal[T](e: Var, a: Att, v: Var, sets: Seq[Set[T]], fromScala: Any => Any): Unit = {
    val (set, v1, v2, e1) = (v + "-set", v + 1, v + 2, e + 1)
    val aa                = a.split("/").last
    aa match {
      case "id" =>
        in += s"[$e ...]"
      case _    =>
        in += s"[$set ...]"
        where += s"[$e $a $v]" -> wClause
    }
    where +=
      s"""[(datomic.api/q
         |          "[:find (distinct $v1)
         |            :in $$ $e1
         |            :where [$e1 $a $v1]]" $$ $e) [[$v2]]]""".stripMargin -> wClause
    where += s"[(= $v2 $set)]" -> wClause
    args += sets.map(set => set.map(fromScala).asJava).asJava
  }

  private def optEqual[T](
    e: Var,
    a: Att,
    v: Var,
    optSets: Option[Seq[Set[T]]],
    fromScala: Any => Any
  ): Unit = {
    optSets.fold[Unit] {
      none(e, a, v)
    } { sets =>
      find += s"(distinct $v)"
      equal(e, a, v, sets, fromScala)
    }
  }

  private def equal2(e: Var, a: Att, v: Var, filterAttr: String): Unit = {
    preFind = e

    where += s"[$e $a $v]" -> wClause
    where +=
      s"""[(datomic.api/q
         |          "[:find (distinct ${v}1)
         |            :in $$ ${e}1
         |            :where [${e}1 $a ${v}1]]" $$ $e) [[${v}2]]]""".stripMargin -> wClause
    val link: (Var, Var) => Unit = (e1: Var, v1: Var) => {
      where +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${v1}1)
           |            :in $$ ${e1}1
           |            :where [${e1}1 $filterAttr ${v1}1]]" $$ $e1) [[${v1}2]]]""".stripMargin -> wClause
      where += s"[(= ${v}2 ${v1}2)]" -> wClause
    }
    filterAttrVars1.get(filterAttr).fold {
      filterAttrVars2 = filterAttrVars2 + (filterAttr -> link)
    } { case (e, a) => link(e, a) }
  }

  private def neq[T](e: Var, a: Att, v: Var, sets: Seq[Set[T]], fromScala: Any => Any): Unit = {
    where += s"[$e $a $v]" -> wClause
    if (sets.nonEmpty && sets.flatten.nonEmpty) {
      val blacklist               = v + "-blacklist"
      val blacklisted             = v + "-blacklisted"
      val (set, set1, v1, v2, e1) = (v + "-set", v + "-set1", v + 1, v + 2, e + 1)

      // Pre-query
      preIn += s"[$set ...]"
      preWhere +=
        s"""[(datomic.api/q
           |          "[:find (distinct $v1)
           |            :in $$ $e1
           |            :where [$e1 $a $v1]]" $$ $e) [[$v2]]]""".stripMargin -> wClause
      preWhere += s"[(into #{} $set) $set1]" -> wClause
      preWhere += s"[(= $v2 $set1)]" -> wClause
      preArgs += sets.map(set => set.map(fromScala).asJava).asJava

      // Main query
      inPost += blacklist
      wherePost += s"[(contains? $blacklist $e) $blacklisted]" -> wClause
      wherePost += s"[(not $blacklisted)]" -> wClause
    }
  }

  private def optNeq[T](
    e: Var,
    a: Att,
    v: Var,
    optSets: Option[Seq[Set[T]]],
    fromScala: Any => Any
  ): Unit = {
    optSets.fold[Unit] {
      none(e, a, v)
    } { sets =>
      find += s"(distinct $v)"
      neq(e, a, v, sets, fromScala)
    }
  }

  private def neq2(e: Var, a: Att, v: Var, filterAttr: String): Unit = {
    where += s"[$e $a $v]" -> wClause
    val process: (Var, Var) => Unit = (e1: Var, v1: Var) => {
      val blacklist   = v1 + "-blacklist"
      val blacklisted = v1 + "-blacklisted"

      // Pre-query
      preFind = e1
      preWhere +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${v}1)
           |            :in $$ ${e}1
           |            :where [${e}1 $a ${v}1]]" $$ $e) [[${v}2]]]""".stripMargin -> wClause
      preWhere +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${v1}1)
           |            :in $$ ${e1}1
           |            :where [${e1}1 $filterAttr ${v1}1]]" $$ $e1) [[${v1}2]]]""".stripMargin -> wClause
      preWhere += s"[(= ${v}2 ${v1}2)]" -> wClause

      // Main query
      inPost += blacklist
      wherePost += s"[(contains? $blacklist $e1) $blacklisted]" -> wClause
      wherePost += s"[(not $blacklisted)]" -> wClause
    }
    filterAttrVars1.get(filterAttr).fold {
      filterAttrVars2 = filterAttrVars2 + (filterAttr -> process)
    } { case (e, a) =>
      process(e, a)
    }
  }

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
    e: Var,
    a: Att,
    v: Var,
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

  private def has2(e: Var, a: Att, v: Var, filterAttr: String): Unit = {
    where += s"[$e $a $v]" -> wClause
    val process: (Var, Var) => Unit = (e1: Var, v1: Var) => {
      where +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${v}1)
           |            :in $$ ${e}1
           |            :where [${e}1 $a ${v}1]]" $$ $e) [[${v}2]]]""".stripMargin -> wClause
      where +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${v1}1)
           |            :in $$ ${e1}1
           |            :where [${e1}1 $filterAttr ${v1}1]]" $$ $e1) [[${v1}2]]]""".stripMargin -> wClause
      where += s"[(clojure.set/intersection ${v}2 ${v1}2) $v1-intersection]" -> wClause
      where += s"[(= ${v1}2 $v1-intersection)]" -> wClause
    }
    filterAttrVars1.get(filterAttr).fold {
      filterAttrVars2 = filterAttrVars2 + (filterAttr -> process)
    } { case (e, a) =>
      process(e, a)
    }
  }

  private def hasNo[T](e: Var, a: Att, v: Var, sets: Seq[Set[T]], tpe: String, toDatalog: T => String): Unit = {
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
      wherePost += s"[(contains? $blacklist $e) $blacklisted]" -> wClause
      wherePost += s"[(not $blacklisted)]" -> wClause
    }
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

  private def optHasNo[T](
    e: Var,
    a: Att,
    v: Var,
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

  private def hasNo2(e: Var, a: Att, v: Var, filterAttr: String): Unit = {
    // Common for pre-query and main query
    where += s"[$e $a $v]" -> wClause
    val process: (Var, Var) => Unit = (e1: Var, v1: Var) => {
      where +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${v}1)
           |            :in $$ ${e}1
           |            :where [${e}1 $a ${v}1]]" $$ $e) [[${v}2]]]""".stripMargin -> wClause
      where +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${v1}1)
           |            :in $$ ${e1}1
           |            :where [${e1}1 $filterAttr ${v1}1]]" $$ $e1) [[${v1}2]]]""".stripMargin -> wClause
      where += s"[(clojure.set/intersection ${v}2 ${v1}2) $v1-intersection]" -> wClause
      where += s"[(empty? $v1-intersection)]" -> wClause
    }
    filterAttrVars1.get(filterAttr).fold {
      filterAttrVars2 = filterAttrVars2 + (filterAttr -> process)
    } { case (e, a) =>
      process(e, a)
    }
  }


  private def noValue(e: Var, a: Att): Unit = {
    where += s"(not [$e $a])" -> wNeqOne
  }

  private def optV(e: Var, a: Att, v: Var): Unit = {
    find += s"(pull $e-$v [[$a :limit nil]]) "
    where += s"[(identity $e) $e-$v]" -> wGround
  }

  private def none(e: Var, a: Att, v: Var): Unit = {
    find += s"(pull $e-$v [[$a :limit nil]])"
    where += s"[(identity $e) $e-$v]" -> wGround
    where += s"(not [$e $a])" -> wNeqOne
  }
}