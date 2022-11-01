package molecule.db.datomic.query

import molecule.boilerplate.api.Keywords._
import molecule.boilerplate.ast.MoleculeModel._
import scala.reflect.ClassTag

trait ExprOne[Tpl] { self: TypeResolvers with Sort_[Tpl] with Base[Tpl] =>

  protected def resolveAttrOneMan(es: List[Var], atom: AttrOneMan): List[Var] = {
    attrIndex += 1
    val (e, a) = (es.last, s":${atom.ns}/${atom.attr}")
    atom match {
      case at: AttrOneManString     => atomMan(e, a, at.op, at.vs, resString, sortString(at, attrIndex))
      case at: AttrOneManInt        => atomMan(e, a, at.op, at.vs, resInt, sortInt(at, attrIndex))
      case at: AttrOneManLong       => atomMan(e, a, at.op, at.vs, resLong, sortLong(at, attrIndex))
      case at: AttrOneManFloat      => atomMan(e, a, at.op, at.vs, resFloat, sortFloat(at, attrIndex))
      case at: AttrOneManDouble     => atomMan(e, a, at.op, at.vs, resDouble, sortDouble(at, attrIndex))
      case at: AttrOneManBoolean    => atomMan(e, a, at.op, at.vs, resBoolean, sortBoolean(at, attrIndex))
      case at: AttrOneManBigInt     => atomMan(e, a, at.op, at.vs, resBigInt, sortBigInt(at, attrIndex))
      case at: AttrOneManBigDecimal => atomMan(e, a, at.op, at.vs, resBigDecimal, sortBigDecimal(at, attrIndex))
      case at: AttrOneManDate       => atomMan(e, a, at.op, at.vs, resDate, sortDate(at, attrIndex))
      case at: AttrOneManUUID       => atomMan(e, a, at.op, at.vs, resUUID, sortUUID(at, attrIndex))
      case at: AttrOneManURI        => atomMan(e, a, at.op, at.vs, resURI, sortURI(at, attrIndex))
      case at: AttrOneManByte       => atomMan(e, a, at.op, at.vs, resByte, sortByte(at, attrIndex))
      case at: AttrOneManShort      => atomMan(e, a, at.op, at.vs, resShort, sortShort(at, attrIndex))
      case at: AttrOneManChar       => atomMan(e, a, at.op, at.vs, resChar, sortChar(at, attrIndex))
    }
    es
  }

  protected def resolveAttrOneTac(es: List[Var], atom: AttrOneTac): List[Var] = {
    val (e, a) = (es.last, s":${atom.ns}/${atom.attr}")
    atom match {
      case at: AttrOneTacString     => atomTac(e, a, at.op, at.vs, resString)
      case at: AttrOneTacInt        => atomTac(e, a, at.op, at.vs, resInt)
      case at: AttrOneTacLong       => atomTac(e, a, at.op, at.vs, resLong)
      case at: AttrOneTacFloat      => atomTac(e, a, at.op, at.vs, resFloat)
      case at: AttrOneTacDouble     => atomTac(e, a, at.op, at.vs, resDouble)
      case at: AttrOneTacBoolean    => atomTac(e, a, at.op, at.vs, resBoolean)
      case at: AttrOneTacBigInt     => atomTac(e, a, at.op, at.vs, resBigInt)
      case at: AttrOneTacBigDecimal => atomTac(e, a, at.op, at.vs, resBigDecimal)
      case at: AttrOneTacDate       => atomTac(e, a, at.op, at.vs, resDate)
      case at: AttrOneTacUUID       => atomTac(e, a, at.op, at.vs, resUUID)
      case at: AttrOneTacURI        => atomTac(e, a, at.op, at.vs, resURI)
      case at: AttrOneTacByte       => atomTac(e, a, at.op, at.vs, resByte)
      case at: AttrOneTacShort      => atomTac(e, a, at.op, at.vs, resShort)
      case at: AttrOneTacChar       => atomTac(e, a, at.op, at.vs, resChar)
    }
    es
  }

  protected def resolveAttrOneOpt(es: List[Var], atom: AttrOneOpt): List[Var] = {
    attrIndex += 1
    val (e, a) = (es.last, s":${atom.ns}/${atom.attr}")
    atom match {
      case at: AttrOneOptString     => atomOpt(e, a, at.op, at.vs, optString, sortString(at, attrIndex))
      case at: AttrOneOptInt        => atomOpt(e, a, at.op, at.vs, optInt, sortInt(at, attrIndex))
      case at: AttrOneOptLong       => atomOpt(e, a, at.op, at.vs, optLong, sortLong(at, attrIndex))
      case at: AttrOneOptFloat      => atomOpt(e, a, at.op, at.vs, optFloat, sortFloat(at, attrIndex))
      case at: AttrOneOptDouble     => atomOpt(e, a, at.op, at.vs, optDouble, sortDouble(at, attrIndex))
      case at: AttrOneOptBoolean    => atomOpt(e, a, at.op, at.vs, optBoolean, sortBoolean(at, attrIndex))
      case at: AttrOneOptBigInt     => atomOpt(e, a, at.op, at.vs, optBigInt, sortBigInt(at, attrIndex))
      case at: AttrOneOptBigDecimal => atomOpt(e, a, at.op, at.vs, optBigDecimal, sortBigDecimal(at, attrIndex))
      case at: AttrOneOptDate       => atomOpt(e, a, at.op, at.vs, optDate, sortDate(at, attrIndex))
      case at: AttrOneOptUUID       => atomOpt(e, a, at.op, at.vs, optUUID, sortUUID(at, attrIndex))
      case at: AttrOneOptURI        => atomOpt(e, a, at.op, at.vs, optURI, sortURI(at, attrIndex))
      case at: AttrOneOptByte       => atomOpt(e, a, at.op, at.vs, optByte, sortByte(at, attrIndex))
      case at: AttrOneOptShort      => atomOpt(e, a, at.op, at.vs, optShort, sortShort(at, attrIndex))
      case at: AttrOneOptChar       => atomOpt(e, a, at.op, at.vs, optChar, sortChar(at, attrIndex))
    }
    es
  }


  protected def atomMan[T: ClassTag](
    e: Var,
    a: Att,
    op: Op,
    args: Seq[T],
    res: Res[T],
    sorter: Option[(Int, (Row, Row) => Int)]
  ): Unit = {
    val v = vv
    find += v
    castScala += res.toScala
    sorter.foreach(sorts += _)
    expr(e, a, v, op, args, res)
  }

  private def atomTac[T: ClassTag](
    e: Var,
    a: Att,
    op: Op,
    args: Seq[T],
    res: Res[T],
  ): Unit = {
    val v = vv
    expr(e, a, v, op, args, res)
  }

  private def atomOpt[T: ClassTag](
    e: Var,
    a: Att,
    op: Op,
    optArgs: Option[Seq[T]],
    res: OptRes[T],
    sorter: Option[(Int, (Row, Row) => Int)]
  ): Unit = {
    val v = vv
    castScala += res.toScala
    sorter.foreach(sorts += _)
    op match {
      case V    => optV(e, a, v)
      case Appl => optEq(e, a, v, optArgs, res.fromScala)
      case Not  => optNeq(e, a, v, optArgs, res.tpe, res.toDatalog)
      case Lt   => optCompare(e, a, v, optArgs, "<", res.fromScala)
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
    res: Res[T],
  ): Unit = {
    op match {
      case V    => attr(e, a, v)
      case Appl => equal(e, a, v, args, res.fromScala)
      case Not  => neq(e, a, v, args, res.tpe, res.toDatalog)
      case Lt   => compare(e, a, v, args.head, "<", res.fromScala)
      case Gt        => compare(e, a, v, args.head, ">", res.fromScala)
      case Le        => compare(e, a, v, args.head, "<=", res.fromScala)
      case Ge        => compare(e, a, v, args.head, ">=", res.fromScala)
      case NoValue   => noValue(e, a, v)
      case Fn(kw, _) => aggr(e, a, v, kw, res)
      case other     => unexpected(other)
    }
  }


  private def aggr[T](e: Var, a: Att, v: Var, fn: Kw, res: Res[T]): Unit = {
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

  private def equal[T: ClassTag](e: Var, a: Att, v: Var, args: Seq[T], fromScala: Any => Any): Unit = {
    in += s"[$v ...]"
    where += s"[$e $a $v$tx]" -> wClause
    inputs += args.map(fromScala).toArray
  }

  private def neq[T](e: Var, a: Att, v: Var, args: Seq[T], tpe: String, toDatalog: T => String): Unit = {
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
    inputs += fromScala(arg).asInstanceOf[AnyRef]
  }

  private def noValue(e: Var, a: Att, v: String): Unit = {
    where += s"(not [$e $a])" -> wNeqOne
  }


  private def optV(e: Var, a: Att, v: Var) = {
    find += s"(pull $e-$v [[$a :limit nil]]) "
    where += s"[(identity $e) $e-$v]" -> wGround
  }

  private def optEq[T: ClassTag](
    e: Var,
    a: Att,
    v: Var,
    optArgs: Option[Seq[T]],
    fromScala: Any => Any
  ): Unit = {
    if (optArgs.isDefined) {
      find += v
      in += s"[$v ...]"
      where += s"[$e $a $v$tx]" -> wClause
      inputs += optArgs.get.map(fromScala).toArray
    } else {
      // None
      find += s"(pull $e-$v [[$a :limit nil]])"
      where += s"(not [$e $a])" -> wNeqOne
      where += s"[(identity $e) $e-$v]" -> wGround
    }
  }


  private def optNeq[T](
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
      if (tpe == "URI") {
        optArgs.get.zipWithIndex.foreach { case (arg, i) =>
          where += s"""[(ground (new java.net.URI "$arg")) $v$i]""" -> wNeqOne
          where += s"[(!= $v $v$i)]" -> wNeqOne
        }
      } else {
        optArgs.get.foreach { arg =>
          where += s"[(!= $v ${toDatalog(arg)})]" -> wNeqOne
        }
      }
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
    if (optArgs.isDefined && optArgs.get.nonEmpty) {
      find += v
      val v1 = v + 1
      in += v1
      where += s"[$e $a $v$tx]" -> wClause
      where += s"[($op $v $v1)]" -> wNeqOne
      inputs += fromScala(optArgs.get.head).asInstanceOf[AnyRef]
    } else {
      // None - return null (clojure nil)
      find += s"$v-nil"
      where += s"[(ground nil) $v-nil]" -> wGround
    }
  }
}