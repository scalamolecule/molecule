package molecule.datomic.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import scala.reflect.ClassTag

trait ResolveExprOne[Tpl]
  extends SortOneSpecial[Tpl]
    with SortOneOpt_[Tpl] { self: DatomicModel2Query[Tpl] with LambdasOne =>

  protected def resolveAttrOneMan(es: List[Var], attr: AttrOneMan): List[Var] = {
    aritiesAttr()
    attrIndex += 1
    val (e, a) = (es.last, s":${attr.ns}/${attr.attr}")
    attr match {
      case at: AttrOneManString     => man(e, a, at.op, at.vs, resString, sortOneString(at, attrIndex))
      case at: AttrOneManInt        => man(e, a, at.op, at.vs, resInt, intSorter(at, attrIndex))
      case at: AttrOneManLong       => manLong(e, a, at.op, at.vs, resLong, sortOneLong(at, attrIndex))
      case at: AttrOneManFloat      => man(e, a, at.op, at.vs, resFloat, floatSorter(at, attrIndex))
      case at: AttrOneManDouble     => man(e, a, at.op, at.vs, resDouble, sortOneDouble(at, attrIndex))
      case at: AttrOneManBoolean    => man(e, a, at.op, at.vs, resBoolean, sortOneBoolean(at, attrIndex))
      case at: AttrOneManBigInt     => man(e, a, at.op, at.vs, resBigInt, bigIntSorter(at, attrIndex))
      case at: AttrOneManBigDecimal => man(e, a, at.op, at.vs, resBigDecimal, sortOneBigDecimal(at, attrIndex))
      case at: AttrOneManDate       => man(e, a, at.op, at.vs, resDate, sortOneDate(at, attrIndex))
      case at: AttrOneManUUID       => man(e, a, at.op, at.vs, resUUID, sortOneUUID(at, attrIndex))
      case at: AttrOneManURI        => man(e, a, at.op, at.vs, resURI, sortOneURI(at, attrIndex))
      case at: AttrOneManByte       => man(e, a, at.op, at.vs, resByte, byteSorter(at, attrIndex))
      case at: AttrOneManShort      => man(e, a, at.op, at.vs, resShort, shortSorter(at, attrIndex))
      case at: AttrOneManChar       => man(e, a, at.op, at.vs, resChar, sortOneChar(at, attrIndex))
    }
    es
  }

  protected def resolveAttrOneTac(es: List[Var], attr: AttrOneTac): List[Var] = {
    val (e, a) = (es.last, s":${attr.ns}/${attr.attr}")
    if (isNestedOpt && !isTxMetaData)
      throw ModelError("Tacit attributes not allowed in optional nested queries. Found: " + a)
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
    aritiesAttr()
    attrIndex += 1
    hasOptAttr = true // to avoid redundant None's
    val (e, a) = (es.last, s":${attr.ns}/${attr.attr}")
    attr match {
      case at: AttrOneOptString     => opt(e, a, at.op, at.vs, resOptString, sortOneOptString(at, attrIndex))
      case at: AttrOneOptInt        => opt(e, a, at.op, at.vs, resOptInt, sortOneOptInt(at, attrIndex))
      case at: AttrOneOptLong       => opt(e, a, at.op, at.vs, resOptLong, sorterOneOptLong(at, attrIndex))
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

  private def sorterOneOptLong(
    at: AttrOneOptLong,
    attrIndex: Int
  ): Option[(Int, Int => (Row, Row) => Int)] = {
    if (at.status.contains("ref"))
      sortOneOptLongRef(at, attrIndex)
    else
      sortOneOptLong(at, attrIndex)
  }

  private def addSort(sorter: Option[(Int, Int => (Row, Row) => Int)]): Unit = {
    sorter.foreach {
      case s if isTxMetaData => sortss = (sortss.head :+ s) +: sortss.tail
      case s                 => sortss = sortss.init :+ (sortss.last :+ s)
    }
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
    addCast(res.j2s)
    addSort(sorter)
    expr(e, a, v, op, args, res)
  }

  private def manLong(
    e: Var,
    a: Att,
    op: Op,
    args: Seq[Long],
    res: ResOne[Long],
    sorter: Option[(Int, Int => (Row, Row) => Int)]
  ): Unit = {
    a match {
      case ":_Generic/e"  =>
        find += e
        addCast(res.j2s)
        addSort(sorter)
      case ":_Generic/tx" =>
        find += txVar
        addCast(res.j2s)
        addSort(sorter)
      case a              =>
        man(e, a, op, args, res, sorter)
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
      case Eq        => equal(e, a, v, args, res.s2j)
      case Neq       => neq(e, a, v, args, res.tpe, res.toDatalog)
      case Lt        => compare(e, a, v, args.head, "<", res.s2j)
      case Gt        => compare(e, a, v, args.head, ">", res.s2j)
      case Le        => compare(e, a, v, args.head, "<=", res.s2j)
      case Ge        => compare(e, a, v, args.head, ">=", res.s2j)
      case NoValue   => noValue(e, a)
      case Fn(kw, n) => aggr(e, a, v, kw, n, res)
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
    addCast(resOpt.j2s)
    addSort(sorter)
    op match {
      case V     => optV(e, a, v)
      case Eq    => optEqual(e, a, v, optArgs, resOpt.s2j)
      case Neq   => optEq(e, a, v, optArgs, resOpt.tpe, resOpt.toDatalog)
      case Lt    => optCompare(e, a, v, optArgs, "<", resOpt.s2j)
      case Gt    => optCompare(e, a, v, optArgs, ">", resOpt.s2j)
      case Le    => optCompare(e, a, v, optArgs, "<=", resOpt.s2j)
      case Ge    => optCompare(e, a, v, optArgs, ">=", resOpt.s2j)
      case other => unexpectedOp(other)
    }
  }

  private def aggr[T](e: Var, a: Att, v: Var, fn: String, optN: Option[Int], res: ResOne[T]): Unit = {
    lazy val n = optN.getOrElse(0)
    // Replace find/casting with aggregate function/cast
    find -= v
    fn match {
      case "distinct" =>
        find += s"(distinct $v)"
        replaceCast(res.set2set)

      case "mins" =>
        find += s"(min $n $v)"
        replaceCast(res.vector2set)

      case "min" =>
        find += s"(min $v)"

      case "maxs" =>
        find += s"(max $n $v)"
        replaceCast(res.vector2set)

      case "max" =>
        find += s"(max $v)"

      case "rands" =>
        find += s"(rand $n $v)"
        replaceCast(res.vector2set)

      case "rand" =>
        find += s"(rand $v)"

      case "samples" =>
        find += s"(sample $n $v)"
        replaceCast(res.vector2set)

      case "sample" =>
        find += s"(sample 1 $v)"
        replaceCast(res.seq2t)

      case "count" =>
        find += s"(count $v)"
        widh += e
        replaceCast(toInt)

      case "countDistinct" =>
        find += s"(count-distinct $v)"
        widh += e
        replaceCast(toInt)

      case "sum"      => find += s"(sum $v)"
      case "median"   => find += s"(median $v)"
      case "avg"      => find += s"(avg $v)"
      case "variance" => find += s"(variance $v)"
      case "stddev"   => find += s"(stddev $v)"

      case other => unexpectedKw(other)
    }
    where += s"[$e $a $v$tx]" -> wClause
  }

  private def attr(e: Var, a: Att, v: Var): Unit = {
    where += s"[$e $a $v$tx]" -> wClause
  }

  private def equal[T: ClassTag](e: Var, a: Att, v: Var, argValues: Seq[T], fromScala: Any => Any): Unit = {
    in += s"[$v ...]"
    where += s"[$e $a $v$tx]" -> wClause
    args += argValues.map(fromScala).toArray
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
    args += fromScala(arg).asInstanceOf[AnyRef]
  }

  private def noValue(e: Var, a: Att): Unit = {
    where += s"(not [$e $a])" -> wNeqOne
  }


  private def optV(e: Var, a: Att, v: Var): Unit = {
    find += s"(pull $e-$v [[$a :limit nil]]) "
    where += s"[(identity $e) $e-$v]" -> wGround
  }

  private def optEqual[T: ClassTag](
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
      equal(e, a, v, vs, fromScala)
    }
  }

  private def optEq[T](
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
      neq(e, a, v, optArgs.get, tpe, toDatalog)
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