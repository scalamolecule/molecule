package molecule.db.datomic.query

import molecule.boilerplate.api.Keywords._
import molecule.boilerplate.ast.MoleculeModel._
import scala.reflect.ClassTag

trait ExprOne[Tpl] { self: Sort_[Tpl] with Base[Tpl] =>

  import ResolveOne._

  protected def resolveAttrOneMan(es: List[Var], atom: AttrOneMan): List[Var] = {
    attrIndex += 1
    val (e, a) = (es.last, s":${atom.ns}/${atom.attr}")
    atom match {
      case at: AttrOneManString     => man(e, a, at.op, at.vs, resString, sortString(at, attrIndex))
      case at: AttrOneManInt        => man(e, a, at.op, at.vs, resInt, sortInt(at, attrIndex))
      case at: AttrOneManLong       => man(e, a, at.op, at.vs, resLong, sortLong(at, attrIndex))
      case at: AttrOneManFloat      => man(e, a, at.op, at.vs, resFloat, sortFloat(at, attrIndex))
      case at: AttrOneManDouble     => man(e, a, at.op, at.vs, resDouble, sortDouble(at, attrIndex))
      case at: AttrOneManBoolean    => man(e, a, at.op, at.vs, resBoolean, sortBoolean(at, attrIndex))
      case at: AttrOneManBigInt     => man(e, a, at.op, at.vs, resBigInt, sortBigInt(at, attrIndex))
      case at: AttrOneManBigDecimal => man(e, a, at.op, at.vs, resBigDecimal, sortBigDecimal(at, attrIndex))
      case at: AttrOneManDate       => man(e, a, at.op, at.vs, resDate, sortDate(at, attrIndex))
      case at: AttrOneManUUID       => man(e, a, at.op, at.vs, resUUID, sortUUID(at, attrIndex))
      case at: AttrOneManURI        => man(e, a, at.op, at.vs, resURI, sortURI(at, attrIndex))
      case at: AttrOneManByte       => man(e, a, at.op, at.vs, resByte, sortByte(at, attrIndex))
      case at: AttrOneManShort      => man(e, a, at.op, at.vs, resShort, sortShort(at, attrIndex))
      case at: AttrOneManChar       => man(e, a, at.op, at.vs, resChar, sortChar(at, attrIndex))
    }
    es
  }

  protected def resolveAttrOneTac(es: List[Var], atom: AttrOneTac): List[Var] = {
    val (e, a) = (es.last, s":${atom.ns}/${atom.attr}")
    atom match {
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

  protected def resolveAttrOneOpt(es: List[Var], atom: AttrOneOpt): List[Var] = {
    attrIndex += 1
    val (e, a) = (es.last, s":${atom.ns}/${atom.attr}")
    atom match {
      case at: AttrOneOptString     => opt(e, a, at.op, at.vs, resOptString, sortString(at, attrIndex))
      case at: AttrOneOptInt        => opt(e, a, at.op, at.vs, resOptInt, sortInt(at, attrIndex))
      case at: AttrOneOptLong       => opt(e, a, at.op, at.vs, resOptLong, sortLong(at, attrIndex))
      case at: AttrOneOptFloat      => opt(e, a, at.op, at.vs, resOptFloat, sortFloat(at, attrIndex))
      case at: AttrOneOptDouble     => opt(e, a, at.op, at.vs, resOptDouble, sortDouble(at, attrIndex))
      case at: AttrOneOptBoolean    => opt(e, a, at.op, at.vs, resOptBoolean, sortBoolean(at, attrIndex))
      case at: AttrOneOptBigInt     => opt(e, a, at.op, at.vs, resOptBigInt, sortBigInt(at, attrIndex))
      case at: AttrOneOptBigDecimal => opt(e, a, at.op, at.vs, resOptBigDecimal, sortBigDecimal(at, attrIndex))
      case at: AttrOneOptDate       => opt(e, a, at.op, at.vs, resOptDate, sortDate(at, attrIndex))
      case at: AttrOneOptUUID       => opt(e, a, at.op, at.vs, resOptUUID, sortUUID(at, attrIndex))
      case at: AttrOneOptURI        => opt(e, a, at.op, at.vs, resOptURI, sortURI(at, attrIndex))
      case at: AttrOneOptByte       => opt(e, a, at.op, at.vs, resOptByte, sortByte(at, attrIndex))
      case at: AttrOneOptShort      => opt(e, a, at.op, at.vs, resOptShort, sortShort(at, attrIndex))
      case at: AttrOneOptChar       => opt(e, a, at.op, at.vs, resOptChar, sortChar(at, attrIndex))
    }
    es
  }


  private def man[T: ClassTag](
    e: Var,
    a: Att,
    op: Op,
    args: Seq[T],
    res: ResOne[T],
    sorter: Option[(Int, (Row, Row) => Int)]
  ): Unit = {
    val v = vv
    find += v
    castScala += res.toScala
    sorter.foreach(sorts += _)
    expr(e, a, v, op, args, res)
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

  private def opt[T: ClassTag](
    e: Var,
    a: Att,
    op: Op,
    optArgs: Option[Seq[T]],
    res: ResOneOpt[T],
    sorter: Option[(Int, (Row, Row) => Int)]
  ): Unit = {
    val v = vv
    castScala += res.toScala
    sorter.foreach(sorts += _)
    op match {
      case V     => optV(e, a, v)
      case Appl  => optApply(e, a, v, optArgs, res.fromScala)
      case Not   => optNot(e, a, v, optArgs, res.tpe, res.toDatalog)
      case Lt    => optCompare(e, a, v, optArgs, "<", res.fromScala)
      case Gt    => optCompare(e, a, v, optArgs, ">", res.fromScala)
      case Le    => optCompare(e, a, v, optArgs, "<=", res.fromScala)
      case Ge    => optCompare(e, a, v, optArgs, ">=", res.fromScala)
      case other => unexpected(other)
    }
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
      case Appl      => appl(e, a, v, args, res.fromScala)
      case Not       => not(e, a, v, args, res.tpe, res.toDatalog)
      case Lt        => compare(e, a, v, args.head, "<", res.fromScala)
      case Gt        => compare(e, a, v, args.head, ">", res.fromScala)
      case Le        => compare(e, a, v, args.head, "<=", res.fromScala)
      case Ge        => compare(e, a, v, args.head, ">=", res.fromScala)
      case NoValue   => noValue(e, a)
      case Fn(kw, _) => aggr(e, a, v, kw, res)
      case other     => unexpected(other)
    }
  }

  private def aggr[T](e: Var, a: Att, v: Var, fn: Kw, res: ResOne[T]): Unit = {
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