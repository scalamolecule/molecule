package molecule.db.datomic.query

import molecule.boilerplate.api.Keywords._
import molecule.boilerplate.ast.MoleculeModel._
import scala.reflect.ClassTag

trait ResolveExprSet[Tpl] { self: Base[Tpl] =>

  import LambdasSet._

  protected def resolveAttrSetMan(es: List[Var], attr: AttrSetMan): List[Var] = {
    aritiesAttr()
    attrIndex += 1
    val (e, a) = (es.last, s":${attr.ns}/${attr.attr}")
    attr match {
      case at: AttrSetManString     => man(e, a, at.op, at.vs, resSetString)
      case at: AttrSetManInt        => man(e, a, at.op, at.vs, resSetInt)
      case at: AttrSetManLong       => man(e, a, at.op, at.vs, resSetLong)
      case at: AttrSetManFloat      => man(e, a, at.op, at.vs, resSetFloat)
      case at: AttrSetManDouble     => man(e, a, at.op, at.vs, resSetDouble)
      case at: AttrSetManBoolean    => man(e, a, at.op, at.vs, resSetBoolean)
      case at: AttrSetManBigInt     => man(e, a, at.op, at.vs, resSetBigInt)
      case at: AttrSetManBigDecimal => man(e, a, at.op, at.vs, resSetBigDecimal)
      case at: AttrSetManDate       => man(e, a, at.op, at.vs, resSetDate)
      case at: AttrSetManUUID       => man(e, a, at.op, at.vs, resSetUUID)
      case at: AttrSetManURI        => man(e, a, at.op, at.vs, resSetURI)
      case at: AttrSetManByte       => man(e, a, at.op, at.vs, resSetByte)
      case at: AttrSetManShort      => man(e, a, at.op, at.vs, resSetShort)
      case at: AttrSetManChar       => man(e, a, at.op, at.vs, resSetChar)
    }
    es
  }

  protected def resolveAttrSetTac(es: List[Var], attr: AttrSetTac): List[Var] = {
    val (e, a) = (es.last, s":${attr.ns}/${attr.attr}")
    attr match {
      case at: AttrSetTacString     => tac(e, a, at.op, at.vs, resSetString)
      case at: AttrSetTacInt        => tac(e, a, at.op, at.vs, resSetInt)
      case at: AttrSetTacLong       => tac(e, a, at.op, at.vs, resSetLong)
      case at: AttrSetTacFloat      => tac(e, a, at.op, at.vs, resSetFloat)
      case at: AttrSetTacDouble     => tac(e, a, at.op, at.vs, resSetDouble)
      case at: AttrSetTacBoolean    => tac(e, a, at.op, at.vs, resSetBoolean)
      case at: AttrSetTacBigInt     => tac(e, a, at.op, at.vs, resSetBigInt)
      case at: AttrSetTacBigDecimal => tac(e, a, at.op, at.vs, resSetBigDecimal)
      case at: AttrSetTacDate       => tac(e, a, at.op, at.vs, resSetDate)
      case at: AttrSetTacUUID       => tac(e, a, at.op, at.vs, resSetUUID)
      case at: AttrSetTacURI        => tac(e, a, at.op, at.vs, resSetURI)
      case at: AttrSetTacByte       => tac(e, a, at.op, at.vs, resSetByte)
      case at: AttrSetTacShort      => tac(e, a, at.op, at.vs, resSetShort)
      case at: AttrSetTacChar       => tac(e, a, at.op, at.vs, resSetChar)
    }
    es
  }

  protected def resolveAttrSetOpt(es: List[Var], attr: AttrSetOpt): List[Var] = {
    aritiesAttr()
    attrIndex += 1
    val (e, a) = (es.last, s":${attr.ns}/${attr.attr}")
    attr match {
      case at: AttrSetOptString     => opt(e, a, at.op, at.vs, resOptSetString)
      case at: AttrSetOptInt        => opt(e, a, at.op, at.vs, resOptSetInt)
      case at: AttrSetOptLong       => opt(e, a, at.op, at.vs, resOptSetLong)
      case at: AttrSetOptFloat      => opt(e, a, at.op, at.vs, resOptSetFloat)
      case at: AttrSetOptDouble     => opt(e, a, at.op, at.vs, resOptSetDouble)
      case at: AttrSetOptBoolean    =>
        if (isFree && isNested)
          datomicFreePullBooleanBug
        else
          opt(e, a, at.op, at.vs, resOptSetBoolean)
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
    e: Var,
    a: Att,
    op: Op,
    args: Seq[Set[T]],
    res: ResSet[T],
  ): Unit = {
    val v = vv
    find += s"(distinct $v)"
    addCast(res.j2s)
    expr(e, a, v, op, args, res)
  }

  private def tac[T: ClassTag](
    e: Var,
    a: Att,
    op: Op,
    args: Seq[Set[T]],
    res: ResSet[T],
  ): Unit = {
    val v = vv
    expr(e, a, v, op, args, res)
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
      case Appl      => appl(e, a, v, sets, res.tpe, res.toDatalog)
      case Not       => not(e, a, v, sets, res.tpe, res.toDatalog)
      case Eq        => equal(e, a, v, sets, res.s2j)
      case Neq       => neq(e, a, v, sets, res.s2j)
      case Lt        => compare(e, a, v, sets.head.head, "<", res.tpe, res.toDatalog)
      case Gt        => compare(e, a, v, sets.head.head, ">", res.tpe, res.toDatalog)
      case Le        => compare(e, a, v, sets.head.head, "<=", res.tpe, res.toDatalog)
      case Ge        => compare(e, a, v, sets.head.head, ">=", res.tpe, res.toDatalog)
      case NoValue   => noValue(e, a)
      case Fn(kw, _) => aggr(e, a, v, kw, res)
      case other     => unexpectedOp(other)
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
      case Appl  => optApply(e, a, v, optSets, resOpt.tpe, resOpt.toDatalog)
      case Not   => optNot(e, a, v, optSets, resOpt.tpe, resOpt.toDatalog)
      case Eq    => optEq(e, a, v, optSets, resOpt.s2j)
      case Neq   => optNeq(e, a, v, optSets, resOpt.s2j)
      case Lt    => optCompare(e, a, v, optSets, "<", resOpt.tpe, resOpt.toDatalog)
      case Gt    => optCompare(e, a, v, optSets, ">", resOpt.tpe, resOpt.toDatalog)
      case Le    => optCompare(e, a, v, optSets, "<=", resOpt.tpe, resOpt.toDatalog)
      case Ge    => optCompare(e, a, v, optSets, ">=", resOpt.tpe, resOpt.toDatalog)
      case other => unexpectedOp(other)
    }
  }

  private def aggr[T](e: Var, a: Att, v: Var, fn: Kw, res: ResSet[T]): Unit = {
    // Replace find/casting with aggregate function/cast
    find -= s"(distinct $v)"
    fn match {
      case _: distinct =>
        val (v1, v2, e1) = (v + 1, v + 2, e + 1)
        find += s"(distinct $v2)"
        where += s"[$e $a $v$tx]" -> wClause
        where +=
          s"""[(datomic.api/q
             |          "[:find (distinct $v1)
             |            :in $$ $e1
             |            :where [$e1 $a $v1]]" $$ $e) [[$v2]]]""".stripMargin -> wClause
        replaceCast(res.sets)

      case mins(n) =>
        find += s"(min $n $v)"
        replaceCast(res.vector2set)

      case _: min =>
        find += s"(min 1 $v)"
        replaceCast(res.vector2set)

      case maxs(n) =>
        find += s"(max $n $v)"
        replaceCast(res.vector2set)

      case _: max =>
        find += s"(max 1 $v)"
        replaceCast(res.vector2set)

      case rands(n) =>
        find += s"(rand $n $v)"
        replaceCast(res.vector2set)

      case _: rand =>
        find += s"(rand 1 $v)"
        replaceCast(res.vector2set)

      case samples(n) =>
        find += s"(sample $n $v)"
        replaceCast(res.vector2set)

      case _: sample =>
        find += s"(sample 1 $v)"
        replaceCast(res.vector2set)

      case _: count =>
        find += s"(count $v)"
        widh += e
        replaceCast(toInt)

      case _: countDistinct =>
        find += s"(count-distinct $v)"
        widh += e
        replaceCast(toInt)

      case _: sum =>
        find += s"(sum $v)"
        replaceCast(res.j2sSet)

      case _: median =>
        find += s"(median $v)"
        replaceCast(res.j2sSet)

      case _: avg =>
        find += s"(avg $v)"
        replaceCast(res.j2sSet)

      case _: variance =>
        find += s"(variance $v)"
        replaceCast(res.j2sSet)

      case _: stddev =>
        find += s"(stddev $v)"
        replaceCast(res.j2sSet)
    }
    where += s"[$e $a $v$tx]" -> wClause
  }

  private def attr(e: Var, a: Att, v: Var): Unit = {
    where += s"[$e $a $v$tx]" -> wClause
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

  private def appl[T: ClassTag](
    e: Var, a: Att, v: Var, sets: Seq[Set[T]], tpe: String, toDatalog: T => String
  ): Unit = {
    where += s"[$e $a $v$tx]" -> wClause
    if (sets.nonEmpty && sets.flatten.nonEmpty) {
      where += s"(rule$v $e)" -> wClause
      rules ++= mkRules(e, a, v, sets, tpe, toDatalog)
    } else {
      where += s"[(ground nil) $v]" -> wGround
    }

  }

  private def not[T](e: Var, a: Att, v: Var, sets: Seq[Set[T]], tpe: String, toDatalog: T => String): Unit = {
    // Common for pre-query and main query
    where += s"[$e $a $v$tx]" -> wClause

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

  private def equal[T](e: Var, a: Att, v: Var, sets: Seq[Set[T]], fromScala: Any => Any): Unit = {
    val (set, v1, v2, e1) = (v + "-set", v + 1, v + 2, e + 1)
    in += s"[$set ...]"
    where += s"[$e $a $v$tx]" -> wClause
    where +=
      s"""[(datomic.api/q
         |          "[:find (distinct $v1)
         |            :in $$ $e1
         |            :where [$e1 $a $v1]]" $$ $e) [[$v2]]]""".stripMargin -> wClause
    where += s"[(= $v2 $set)]" -> wClause
    args += sets.map(set => set.map(fromScala).asJava).asJava
  }

  private def neq[T](e: Var, a: Att, v: Var, sets: Seq[Set[T]], fromScala: Any => Any): Unit = {
    // Common for pre-query and main query
    where += s"[$e $a $v$tx]" -> wClause

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

  private def compare[T](e: Var, a: Att, v: Var, arg: T, op: String, tpe: String, toDatalog: T => String): Unit = {
    where += s"[$e $a $v$tx]" -> wClause
    where += s"(rule$v $e)" -> wClause
    rules += s"[(rule$v $e) [$e $a $v] " + (tpe match {
      case "Float" => s"""[(float $v) $v-float] [($op $v-float (float $arg))]]""" // compare coerced floats
      case "URI"   => s"""[($op $v (new java.net.URI "$arg"))]]"""
      case _       => s"""[($op $v ${toDatalog(arg)})]]"""
    })
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

  private def optApply[T: ClassTag](
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
      appl(e, a, v, sets, tpe, toDatalog)
    }
  }

  private def optNot[T](
    e: Var,
    a: Att,
    v: Var,
    optSets: Option[Seq[Set[T]]],
    tpe: String,
    toDatalog: T => String
  ): Unit = {
    find += s"(distinct $v)"
    if (optSets.isDefined) {
      not(e, a, v, optSets.get, tpe, toDatalog)
    } else {
      where += s"[$e $a $v$tx]" -> wClause
    }
  }

  private def optEq[T](
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

  private def optCompare[T](
    e: Var,
    a: Att,
    v: Var,
    optSets: Option[Seq[Set[T]]],
    op: String,
    tpe: String,
    toDatalog: T => String
  ): Unit = {
    find += s"(distinct $v)"
    optSets.fold[Unit] {
      where += s"[$e $a $v$tx]" -> wClause
    } { sets =>
      compare(e, a, v, sets.head.head, op, tpe, toDatalog)
    }
  }
}