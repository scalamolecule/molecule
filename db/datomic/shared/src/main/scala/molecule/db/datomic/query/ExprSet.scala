package molecule.db.datomic.query

import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.api.Keywords._
import molecule.boilerplate.ast.MoleculeModel._
import scala.reflect.ClassTag
import java.util.{Collections, List => jList, Set => jSet}

trait ExprSet[Tpl] { self: Sort_[Tpl] with Base[Tpl] =>

  import ResolveSet._

  protected def resolveAttrSetMan(es: List[Var], atom: AttrSetMan): List[Var] = {
    attrIndex += 1
    val (e, a) = (es.last, s":${atom.ns}/${atom.attr}")
    atom match {
      case at: AttrSetManString     => man(e, a, at.op, at.vs, resString)
      case at: AttrSetManInt        => man(e, a, at.op, at.vs, resInt)
      case at: AttrSetManLong       => man(e, a, at.op, at.vs, resLong)
      case at: AttrSetManFloat      => man(e, a, at.op, at.vs, resFloat)
      case at: AttrSetManDouble     => man(e, a, at.op, at.vs, resDouble)
      case at: AttrSetManBoolean    => man(e, a, at.op, at.vs, resBoolean)
      case at: AttrSetManBigInt     => man(e, a, at.op, at.vs, resBigInt)
      case at: AttrSetManBigDecimal => man(e, a, at.op, at.vs, resBigDecimal)
      case at: AttrSetManDate       => man(e, a, at.op, at.vs, resDate)
      case at: AttrSetManUUID       => man(e, a, at.op, at.vs, resUUID)
      case at: AttrSetManURI        => man(e, a, at.op, at.vs, resURI)
      case at: AttrSetManByte       => man(e, a, at.op, at.vs, resByte)
      case at: AttrSetManShort      => man(e, a, at.op, at.vs, resShort)
      case at: AttrSetManChar       => man(e, a, at.op, at.vs, resChar)
    }
    es
  }

  protected def resolveAttrSetTac(es: List[Var], atom: AttrSetTac): List[Var] = {
    val (e, a) = (es.last, s":${atom.ns}/${atom.attr}")
    atom match {
      case at: AttrSetTacString     => tac(e, a, at.op, at.vs, resString)
      case at: AttrSetTacInt        => tac(e, a, at.op, at.vs, resInt)
      case at: AttrSetTacLong       => tac(e, a, at.op, at.vs, resLong)
      case at: AttrSetTacFloat      => tac(e, a, at.op, at.vs, resFloat)
      case at: AttrSetTacDouble     => tac(e, a, at.op, at.vs, resDouble)
      case at: AttrSetTacBoolean    => tac(e, a, at.op, at.vs, resBoolean)
      case at: AttrSetTacBigInt     => tac(e, a, at.op, at.vs, resBigInt)
      case at: AttrSetTacBigDecimal => tac(e, a, at.op, at.vs, resBigDecimal)
      case at: AttrSetTacDate       => tac(e, a, at.op, at.vs, resDate)
      case at: AttrSetTacUUID       => tac(e, a, at.op, at.vs, resUUID)
      case at: AttrSetTacURI        => tac(e, a, at.op, at.vs, resURI)
      case at: AttrSetTacByte       => tac(e, a, at.op, at.vs, resByte)
      case at: AttrSetTacShort      => tac(e, a, at.op, at.vs, resShort)
      case at: AttrSetTacChar       => tac(e, a, at.op, at.vs, resChar)
    }
    es
  }

  protected def resolveAttrSetOpt(es: List[Var], atom: AttrSetOpt): List[Var] = {
    attrIndex += 1
    val (e, a) = (es.last, s":${atom.ns}/${atom.attr}")
    atom match {
      case at: AttrSetOptString     => opt(e, a, at.op, at.vs, optString)
      case at: AttrSetOptInt        => opt(e, a, at.op, at.vs, optInt)
      case at: AttrSetOptLong       => opt(e, a, at.op, at.vs, optLong)
      case at: AttrSetOptFloat      => opt(e, a, at.op, at.vs, optFloat)
      case at: AttrSetOptDouble     => opt(e, a, at.op, at.vs, optDouble)
      case at: AttrSetOptBoolean    => opt(e, a, at.op, at.vs, optBoolean)
      case at: AttrSetOptBigInt     => opt(e, a, at.op, at.vs, optBigInt)
      case at: AttrSetOptBigDecimal => opt(e, a, at.op, at.vs, optBigDecimal)
      case at: AttrSetOptDate       => opt(e, a, at.op, at.vs, optDate)
      case at: AttrSetOptUUID       => opt(e, a, at.op, at.vs, optUUID)
      case at: AttrSetOptURI        => opt(e, a, at.op, at.vs, optURI)
      case at: AttrSetOptByte       => opt(e, a, at.op, at.vs, optByte)
      case at: AttrSetOptShort      => opt(e, a, at.op, at.vs, optShort)
      case at: AttrSetOptChar       => opt(e, a, at.op, at.vs, optChar)
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
    castScala += res.toScala
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

  private def opt[T: ClassTag](
    e: Var,
    a: Att,
    op: Op,
    optSets: Option[Seq[Set[T]]],
    res: ResSetOpt[T],
  ): Unit = {
    val v = vv
    castScala += res.toScala
    op match {
      case V     => optV(e, a, v)
      case Appl  => optApply(e, a, v, optSets, res.fromScala)
      case Not   => optNot(e, a, v, optSets, res.tpe, res.toDatalog)
      case Lt    => optCompare(e, a, v, optSets, "<", res.fromScala)
      case Gt    => optCompare(e, a, v, optSets, ">", res.fromScala)
      case Le    => optCompare(e, a, v, optSets, "<=", res.fromScala)
      case Ge    => optCompare(e, a, v, optSets, ">=", res.fromScala)
      case other => unexpected(other)
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
      case Appl      => appl(e, a, v, sets, res.tpe, res.toDatalog)
      case Not       => not(e, a, v, sets, res.tpe, res.toDatalog)
      case Eq        => equal(e, a, v, sets, res.fromScala)
      case Neq       => neq(e, a, v, sets, res.fromScala)
      case Lt        => compare(e, a, v, sets.head.head, "<", res.tpe, res.toDatalog)
      case Gt        => compare(e, a, v, sets.head.head, ">", res.tpe, res.toDatalog)
      case Le        => compare(e, a, v, sets.head.head, "<=", res.tpe, res.toDatalog)
      case Ge        => compare(e, a, v, sets.head.head, ">=", res.tpe, res.toDatalog)
      case NoValue   => noValue(e, a, v)
      case Fn(kw, _) => aggr(e, a, v, kw, res)
      case other     => unexpected(other)
    }
  }

  private def aggr[T](e: Var, a: Att, v: Var, fn: Kw, res: ResSet[T]): Unit = {
    // Replace find/casting with aggregate function/cast
    find -= v
    fn match {
      case _: distinct =>
        find += s"(distinct $v)"
        castScala -= res.toScala
        castScala += res.set2list

      case mins(n) =>
        find += s"(min $n $v)"
        castScala -= res.toScala
        castScala += res.vector2list

      case _: min =>
        find += s"(min $v)"

      case maxs(n) =>
        find += s"(max $n $v)"
        castScala -= res.toScala
        castScala += res.vector2list

      case _: max =>
        find += s"(max $v)"

      case rands(n) =>
        find += s"(rand $n $v)"
        castScala -= res.toScala
        castScala += res.vector2list

      case _: rand =>
        find += s"(rand $v)"

      case samples(n) =>
        find += s"(sample $n $v)"
        castScala -= res.toScala
        castScala += res.vector2list

      case _: sample =>
        // Have to add "1" for some reason
        find += s"(sample 1 $v)"
        castScala -= res.toScala
        castScala += res.seq2t

      case _: count =>
        find += s"(count $v)"
        widh += e
        castScala -= res.toScala
        castScala += toInt

      case _: countDistinct =>
        find += s"(count-distinct $v)"
        widh += e
        castScala -= res.toScala
        castScala += toInt

      case _: sum      => find += s"(sum $v)"
      case _: median   => find += s"(median $v)"
      case _: avg      => find += s"(avg $v)"
      case _: variance => find += s"(variance $v)"
      case _: stddev   => find += s"(stddev $v)"
    }
    where += s"[$e $a $v$tx]" -> wClause
  }

  private def attr(e: Var, a: Att, v: Var): Unit = {
    where += s"[$e $a $v$tx]" -> wClause
  }

  private def mkRules[T](e: Var, a: Att, v: Var, sets: Seq[Set[T]], tpe: String, toDatalog: T => String): Seq[String] = {
    if (tpe == "URI") {
      sets.flatMap {
        case set if set.isEmpty => None
        case set                => Some(
          set.zipWithIndex.map { case (arg, i) =>
            s"""[(ground (new java.net.URI "$arg")) $v$i] [$e $a $v$i]"""
          }.mkString(s"[(rule$v $e)\n    ", "\n    ", "]")
        )
      }
    } else {
      sets.flatMap {
        case set if set.isEmpty => None
        case set                => Some(
          set.map(arg => s"[$e $a ${toDatalog(arg)}]")
            .mkString(s"[(rule$v $e)\n    ", "\n    ", "]")
        )
      }
    }
  }

  private def appl[T: ClassTag](e: Var, a: Att, v: Var, sets: Seq[Set[T]], tpe: String, toDatalog: T => String): Unit = {
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
    if (tpe == "URI") {
      rules += s"""[(rule$v $e) [(ground (new java.net.URI "$arg")) ${v}1] [$e $a ${v}1]]"""
    } else {
      rules += s"[(rule$v $e) [$e $a $v][($op $v ${toDatalog(arg)})]]"
    }
  }

  private def noValue(e: Var, a: Att, v: String): Unit = {
    where += s"(not [$e $a])" -> wNeqOne
  }


  private def optV(e: Var, a: Att, v: Var) = {
    find += s"(pull $e-$v [[$a :limit nil]]) "
    where += s"[(identity $e) $e-$v]" -> wGround
  }

  private def optApply[T: ClassTag](
    e: Var,
    a: Att,
    v: Var,
    optArgs: Option[Seq[Set[T]]],
    fromScala: Any => Any
  ): Unit = {
    if (optArgs.isDefined) {
      find += v
      in += s"[$v ...]"
      where += s"[$e $a $v$tx]" -> wClause
      args += optArgs.get.map(fromScala).toArray
    } else {
      // None
      find += s"(pull $e-$v [[$a :limit nil]])"
      where += s"(not [$e $a])" -> wNeqOne
      where += s"[(identity $e) $e-$v]" -> wGround
    }
  }


  private def optNot[T](
    e: Var,
    a: Att,
    v: Var,
    optArgs: Option[Seq[Set[T]]],
    tpe: String,
    toDatalog: T => String
  ): Unit = {
    find += v
    where += s"[$e $a $v$tx]" -> wClause
    if (optArgs.isDefined && optArgs.get.nonEmpty) {
      if (tpe == "URI") {
        optArgs.get.zipWithIndex.foreach { case (arg, i) =>
          where += s"""[(ground (new java.net.URI "$arg")) $v$i]""" -> wNeqOne
          where += s"[(!= $v $v$i)]" -> wNeqOne
        }
      } else {
        optArgs.get.foreach { arg =>
          //          where += s"[(!= $v ${toDatalog(arg)})]" -> wNeqOne
        }
      }
    }
  }

  private def optCompare[T](
    e: Var,
    a: Att,
    v: Var,
    optArgs: Option[Seq[Set[T]]],
    op: String,
    fromScala: Any => Any
  ): Unit = {
    if (optArgs.isDefined && optArgs.get.nonEmpty) {
      find += v
      val v1 = v + 1
      in += v1
      where += s"[$e $a $v$tx]" -> wClause
      where += s"[($op $v $v1)]" -> wNeqOne
      args += fromScala(optArgs.get.head).asInstanceOf[AnyRef]
    } else {
      // None - return null (clojure nil)
      find += s"$v-nil"
      where += s"[(ground nil) $v-nil]" -> wGround
    }
  }
}