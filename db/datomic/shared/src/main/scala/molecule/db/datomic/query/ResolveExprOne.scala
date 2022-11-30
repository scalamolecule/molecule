package molecule.db.datomic.query

import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.api.Keywords._
import molecule.boilerplate.ast.MoleculeModel._
import scala.reflect.ClassTag

trait ResolveExprOne[Tpl] { self: SortOne_[Tpl] with SortOneOpt_[Tpl] with Base[Tpl] =>

  import LambdasOne._

  protected def resolveAttrOneMan(es: List[Var], attr: AttrOneMan): List[Var] = {
    attrIndex += 1
    val (e, a) = (es.last, s":${attr.ns}/${attr.attr}")
    attr match {
      case at: AttrOneManString     =>
        man(e, a, at.op, at.vs, resString, sortOneString(at, attrIndex))
      case at: AttrOneManInt        => man(e, a, at.op, at.vs, resInt, sortOneInt(at, attrIndex))
      case at: AttrOneManLong       => maL(e, a, at.op, at.vs, resLong, sortOneLong(at, attrIndex))
      case at: AttrOneManFloat      => man(e, a, at.op, at.vs, resFloat, sortOneFloat(at, attrIndex))
      case at: AttrOneManDouble     => man(e, a, at.op, at.vs, resDouble, sortOneDouble(at, attrIndex))
      case at: AttrOneManBoolean    => man(e, a, at.op, at.vs, resBoolean, sortOneBoolean(at, attrIndex))
      case at: AttrOneManBigInt     => man(e, a, at.op, at.vs, resBigInt, sortOneBigInt(at, attrIndex))
      case at: AttrOneManBigDecimal => man(e, a, at.op, at.vs, resBigDecimal, sortOneBigDecimal(at, attrIndex))
      case at: AttrOneManDate       => man(e, a, at.op, at.vs, resDate, sortOneDate(at, attrIndex))
      case at: AttrOneManUUID       => man(e, a, at.op, at.vs, resUUID, sortOneUUID(at, attrIndex))
      case at: AttrOneManURI        => man(e, a, at.op, at.vs, resURI, sortOneURI(at, attrIndex))
      case at: AttrOneManByte       => man(e, a, at.op, at.vs, resByte, sortOneByte(at, attrIndex))
      case at: AttrOneManShort      => man(e, a, at.op, at.vs, resShort, sortOneShort(at, attrIndex))
      case at: AttrOneManChar       => man(e, a, at.op, at.vs, resChar, sortOneChar(at, attrIndex))
    }
    es
  }

  protected def resolveAttrOneTac(es: List[Var], attr: AttrOneTac): List[Var] = {
    val (e, a) = (es.last, s":${attr.ns}/${attr.attr}")
    if (isNestedOpt)
      throw MoleculeException("Tacit attributes not allowed in optional nested queries. Found: " + a)
    attr match {
      case at: AttrOneTacString     => tac(e, a, at.op, at.vs, resString)
      case at: AttrOneTacInt        => tac(e, a, at.op, at.vs, resInt)
      case at: AttrOneTacLong       => tac(e, a, at.op, at.vs, resLong)
      case at: AttrOneTacFloat      => tac(e, a, at.op, at.vs, resFloat)
      case at: AttrOneTacDouble     => tac(e, a, at.op, at.vs, resDouble)
      case at: AttrOneTacBoolean    => tac(e, a, at.op, at.vs, resBoolean)
      case at: AttrOneTacBigInt     => tac(e, a, at.op, at.vs, resBigInt)
      case at: AttrOneTacBigDecimal => tac(e, a, at.op, at.vs, resBigDecimal)
      case at: AttrOneTacDate       => tac(e, a, at.op, at.vs, resDate)
      case at: AttrOneTacUUID       => tac(e, a, at.op, at.vs, resUUID)
      case at: AttrOneTacURI        => tac(e, a, at.op, at.vs, resURI)
      case at: AttrOneTacByte       => tac(e, a, at.op, at.vs, resByte)
      case at: AttrOneTacShort      => tac(e, a, at.op, at.vs, resShort)
      case at: AttrOneTacChar       => tac(e, a, at.op, at.vs, resChar)
    }
    es
  }

  protected def resolveAttrOneOpt(es: List[Var], attr: AttrOneOpt): List[Var] = {
    attrIndex += 1
    val (e, a) = (es.last, s":${attr.ns}/${attr.attr}")
    attr match {
      case at: AttrOneOptString     => opt(e, a, at.op, at.vs, resOptString, sortOneOptString(at, attrIndex))
      case at: AttrOneOptInt        => opt(e, a, at.op, at.vs, resOptInt, sortOneOptInt(at, attrIndex))
      case at: AttrOneOptLong       => opt(e, a, at.op, at.vs, resOptLong, sortOneOptLong(at, attrIndex))
      case at: AttrOneOptFloat      => opt(e, a, at.op, at.vs, resOptFloat, sortOneOptFloat(at, attrIndex))
      case at: AttrOneOptDouble     => opt(e, a, at.op, at.vs, resOptDouble, sortOneOptDouble(at, attrIndex))
      case at: AttrOneOptBoolean    => opt(e, a, at.op, at.vs, resOptBoolean, sortOneOptBoolean(at, attrIndex))
      case at: AttrOneOptBigInt     => opt(e, a, at.op, at.vs, resOptBigInt, sortOneOptBigInt(at, attrIndex))
      case at: AttrOneOptBigDecimal => opt(e, a, at.op, at.vs, resOptBigDecimal, sortOneOptBigDecimal(at, attrIndex))
      case at: AttrOneOptDate       => opt(e, a, at.op, at.vs, resOptDate, sortOneOptDate(at, attrIndex))
      case at: AttrOneOptUUID       => opt(e, a, at.op, at.vs, resOptUUID, sortOneOptUUID(at, attrIndex))
      case at: AttrOneOptURI        => opt(e, a, at.op, at.vs, resOptURI, sortOneOptURI(at, attrIndex))
      case at: AttrOneOptByte       => opt(e, a, at.op, at.vs, resOptByte, sortOneOptByte(at, attrIndex))
      case at: AttrOneOptShort      => opt(e, a, at.op, at.vs, resOptShort, sortOneOptShort(at, attrIndex))
      case at: AttrOneOptChar       => opt(e, a, at.op, at.vs, resOptChar, sortOneOptChar(at, attrIndex))
    }
    es
  }


  private def man[T: ClassTag](
    e: Var,
    a: Att,
    op: Op,
    args: Seq[T],
    res: ResOne[T],
    sorter: Option[(Int, Int => (Row, Row) => Int)]
  ): Unit = {
    val v = vv
    find += v
    casts += res.j2s
    sorter.foreach(sorts += _)
    expr(e, a, v, op, args, res)
  }

  private def maL(
    e: Var,
    a: Att,
    op: Op,
    args: Seq[Long],
    res: ResOne[Long],
    sorter: Option[(Int, Int => (Row, Row) => Int)]
  ): Unit = {
    a match {
      case ":Generic/e"  =>
        find += e
        casts += res.j2s
        sorter.foreach(sorts += _)
      case ":Generic/tx" =>
      case a             => man(e, a, op, args, res, sorter)
    }
  }

  private def tac[T: ClassTag](
    e: Var,
    a: Att,
    op: Op,
    args: Seq[T],
    res: ResOne[T],
  ): Unit = {
    val v = vv
    expr(e, a, v, op, args, res)
  }

  private def expr[T: ClassTag](
    e: Var,
    a: Att,
    v: Var,
    op: Op,
    args: Seq[T],
    res: ResOne[T],
  ): Unit = {
    op match {
      case V         => attr(e, a, v)
      case Appl      => appl(e, a, v, args, res.s2j)
      case Not       => not(e, a, v, args, res.tpe, res.toDatalog)
      case Lt        => compare(e, a, v, args.head, "<", res.s2j)
      case Gt        => compare(e, a, v, args.head, ">", res.s2j)
      case Le        => compare(e, a, v, args.head, "<=", res.s2j)
      case Ge        => compare(e, a, v, args.head, ">=", res.s2j)
      case NoValue   => noValue(e, a)
      case Fn(kw, _) => aggr(e, a, v, kw, res)
      case other     => unexpectedOp(other)
    }
  }

  private def opt[T: ClassTag](
    e: Var,
    a: Att,
    op: Op,
    optArgs: Option[Seq[T]],
    resOpt: ResOneOpt[T],
    sorter: Option[(Int, Int => (Row, Row) => Int)]
  ): Unit = {
    val v = vv
    casts += resOpt.j2s
    sorter.foreach(sorts += _)
    op match {
      case V     => optV(e, a, v)
      case Appl  => optApply(e, a, v, optArgs, resOpt.s2j)
      case Not   => optNot(e, a, v, optArgs, resOpt.tpe, resOpt.toDatalog)
      case Lt    => optCompare(e, a, v, optArgs, "<", resOpt.s2j)
      case Gt    => optCompare(e, a, v, optArgs, ">", resOpt.s2j)
      case Le    => optCompare(e, a, v, optArgs, "<=", resOpt.s2j)
      case Ge    => optCompare(e, a, v, optArgs, ">=", resOpt.s2j)
      case other => unexpectedOp(other)
    }
  }

  private def aggr[T](e: Var, a: Att, v: Var, fn: Kw, res: ResOne[T]): Unit = {
    // Replace find/casting with aggregate function/cast
    find -= v
    fn match {
      case _: distinct =>
        find += s"(distinct $v)"
        casts -= res.j2s
        casts += res.set2set

      case mins(n) =>
        find += s"(min $n $v)"
        casts -= res.j2s
        casts += res.vector2set

      case _: min =>
        find += s"(min $v)"

      case maxs(n) =>
        find += s"(max $n $v)"
        casts -= res.j2s
        casts += res.vector2set

      case _: max =>
        find += s"(max $v)"

      case rands(n) =>
        find += s"(rand $n $v)"
        casts -= res.j2s
        casts += res.vector2set

      case _: rand =>
        find += s"(rand $v)"

      case samples(n) =>
        find += s"(sample $n $v)"
        casts -= res.j2s
        casts += res.vector2set

      case _: sample =>
        find += s"(sample 1 $v)"
        casts -= res.j2s
        casts += res.seq2t

      case _: count =>
        find += s"(count $v)"
        widh += e
        casts -= res.j2s
        casts += toInt

      case _: countDistinct =>
        find += s"(count-distinct $v)"
        widh += e
        casts -= res.j2s
        casts += toInt

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

  private def appl[T: ClassTag](e: Var, a: Att, v: Var, argValues: Seq[T], fromScala: Any => Any): Unit = {
    in += s"[$v ...]"
    where += s"[$e $a $v$tx]" -> wClause
    args += argValues.map(fromScala).toArray
  }

  private def not[T](e: Var, a: Att, v: Var, args: Seq[T], tpe: String, toDatalog: T => String): Unit = {
    where += s"[$e $a $v$tx]" -> wClause
    if (tpe == "URI") {
      args.zipWithIndex.foreach { case (arg, i) =>
        where += s"""[(ground (new java.net.URI "$arg")) $v$i]""" -> wNeqOne
        where += s"[(!= $v $v$i)]" -> wNeqOne
      }
    } else {
      args.foreach { arg =>
        where += s"[(!= $v ${toDatalog(arg)})]" -> wNeqOne
      }
    }
  }

  private def compare[T](e: Var, a: Att, v: Var, arg: T, op: String, fromScala: Any => Any): Unit = {
    val v1 = v + 1
    in += v1
    where += s"[$e $a $v$tx]" -> wClause
    where += s"[($op $v $v1)]" -> wNeqOne
    args += fromScala(arg).asInstanceOf[AnyRef]
  }

  private def noValue(e: Var, a: Att): Unit = {
    where += s"(not [$e $a])" -> wNeqOne
  }


  private def optV(e: Var, a: Att, v: Var): Unit = {
    find += s"(pull $e-$v [[$a :limit nil]]) "
    where += s"[(identity $e) $e-$v]" -> wGround
  }

  private def optApply[T: ClassTag](
    e: Var,
    a: Att,
    v: Var,
    optArgs: Option[Seq[T]],
    fromScala: Any => Any
  ): Unit = {
    optArgs.fold[Unit] {
      find += s"(pull $e-$v [[$a :limit nil]])"
      where += s"(not [$e $a])" -> wNeqOne
      where += s"[(identity $e) $e-$v]" -> wGround
    } { vs =>
      find += v
      appl(e, a, v, vs, fromScala)
    }
  }

  private def optNot[T](
    e: Var,
    a: Att,
    v: Var,
    optArgs: Option[Seq[T]],
    tpe: String,
    toDatalog: T => String
  ): Unit = {
    find += v
    where += s"[$e $a $v$tx]" -> wClause
    if (optArgs.isDefined && optArgs.get.nonEmpty) {
      not(e, a, v, optArgs.get, tpe, toDatalog)
    }
  }

  private def optCompare[T](
    e: Var,
    a: Att,
    v: Var,
    optArgs: Option[Seq[T]],
    op: String,
    fromScala: Any => Any
  ): Unit = {
    optArgs.fold[Unit] {
      find += s"$v-nil"
      where += s"[(ground nil) $v-nil]" -> wGround
    } { vs =>
      find += v
      compare(e, a, v, vs.head, op, fromScala)
    }
  }
}