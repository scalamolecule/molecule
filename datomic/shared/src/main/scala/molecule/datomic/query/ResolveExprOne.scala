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
      case at: AttrOneManString     => man(attr, e, a, at.vs, resString, sortOneString(at, attrIndex))
      case at: AttrOneManInt        => man(attr, e, a, at.vs, resInt, intSorter(at, attrIndex))
      case at: AttrOneManLong       => manLong(attr, e, a, at.vs, resLong, sortOneLong(at, attrIndex))
      case at: AttrOneManFloat      => man(attr, e, a, at.vs, resFloat, floatSorter(at, attrIndex))
      case at: AttrOneManDouble     => man(attr, e, a, at.vs, resDouble, sortOneDouble(at, attrIndex))
      case at: AttrOneManBoolean    => man(attr, e, a, at.vs, resBoolean, sortOneBoolean(at, attrIndex))
      case at: AttrOneManBigInt     => man(attr, e, a, at.vs, resBigInt, bigIntSorter(at, attrIndex))
      case at: AttrOneManBigDecimal => man(attr, e, a, at.vs, resBigDecimal, sortOneBigDecimal(at, attrIndex))
      case at: AttrOneManDate       => man(attr, e, a, at.vs, resDate, sortOneDate(at, attrIndex))
      case at: AttrOneManUUID       => man(attr, e, a, at.vs, resUUID, sortOneUUID(at, attrIndex))
      case at: AttrOneManURI        => man(attr, e, a, at.vs, resURI, sortOneURI(at, attrIndex))
      case at: AttrOneManByte       => man(attr, e, a, at.vs, resByte, byteSorter(at, attrIndex))
      case at: AttrOneManShort      => man(attr, e, a, at.vs, resShort, shortSorter(at, attrIndex))
      case at: AttrOneManChar       => man(attr, e, a, at.vs, resChar, sortOneChar(at, attrIndex))
    }
    es
  }

  protected def resolveAttrOneTac(es: List[Var], attr: AttrOneTac): List[Var] = {
    val (e, a) = (es.last, s":${attr.ns}/${attr.attr}")
    if (isNestedOpt && !isTxMetaData)
      throw ModelError("Tacit attributes not allowed in optional nested queries. Found: " + a)
    attr match {
      case at: AttrOneTacString     => tac(attr, e, a, at.vs, resString)
      case at: AttrOneTacInt        => tac(attr, e, a, at.vs, resInt)
      case at: AttrOneTacLong       => tac(attr, e, a, at.vs, resLong)
      case at: AttrOneTacFloat      => tac(attr, e, a, at.vs, resFloat)
      case at: AttrOneTacDouble     => tac(attr, e, a, at.vs, resDouble)
      case at: AttrOneTacBoolean    => tac(attr, e, a, at.vs, resBoolean)
      case at: AttrOneTacBigInt     => tac(attr, e, a, at.vs, resBigInt)
      case at: AttrOneTacBigDecimal => tac(attr, e, a, at.vs, resBigDecimal)
      case at: AttrOneTacDate       => tac(attr, e, a, at.vs, resDate)
      case at: AttrOneTacUUID       => tac(attr, e, a, at.vs, resUUID)
      case at: AttrOneTacURI        => tac(attr, e, a, at.vs, resURI)
      case at: AttrOneTacByte       => tac(attr, e, a, at.vs, resByte)
      case at: AttrOneTacShort      => tac(attr, e, a, at.vs, resShort)
      case at: AttrOneTacChar       => tac(attr, e, a, at.vs, resChar)
    }
    es
  }

  protected def resolveAttrOneOpt(es: List[Var], attr: AttrOneOpt): List[Var] = {
    aritiesAttr()
    attrIndex += 1
    hasOptAttr = true // to avoid redundant None's
    val (e, a) = (es.last, s":${attr.ns}/${attr.attr}")
    attr match {
      case at: AttrOneOptString     => opt(attr, e, a, at.vs, resOptString, sortOneOptString(at, attrIndex))
      case at: AttrOneOptInt        => opt(attr, e, a, at.vs, resOptInt, sortOneOptInt(at, attrIndex))
      case at: AttrOneOptLong       => opt(attr, e, a, at.vs, resOptLong, sorterOneOptLong(at, attrIndex))
      case at: AttrOneOptFloat      => opt(attr, e, a, at.vs, resOptFloat, sortOneOptFloat(at, attrIndex))
      case at: AttrOneOptDouble     => opt(attr, e, a, at.vs, resOptDouble, sortOneOptDouble(at, attrIndex))
      case at: AttrOneOptBoolean    => opt(attr, e, a, at.vs, resOptBoolean, sortOneOptBoolean(at, attrIndex))
      case at: AttrOneOptBigInt     => opt(attr, e, a, at.vs, resOptBigInt, sortOneOptBigInt(at, attrIndex))
      case at: AttrOneOptBigDecimal => opt(attr, e, a, at.vs, resOptBigDecimal, sortOneOptBigDecimal(at, attrIndex))
      case at: AttrOneOptDate       => opt(attr, e, a, at.vs, resOptDate, sortOneOptDate(at, attrIndex))
      case at: AttrOneOptUUID       => opt(attr, e, a, at.vs, resOptUUID, sortOneOptUUID(at, attrIndex))
      case at: AttrOneOptURI        => opt(attr, e, a, at.vs, resOptURI, sortOneOptURI(at, attrIndex))
      case at: AttrOneOptByte       => opt(attr, e, a, at.vs, resOptByte, sortOneOptByte(at, attrIndex))
      case at: AttrOneOptShort      => opt(attr, e, a, at.vs, resOptShort, sortOneOptShort(at, attrIndex))
      case at: AttrOneOptChar       => opt(attr, e, a, at.vs, resOptChar, sortOneOptChar(at, attrIndex))
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
    attr: Attr,
    e: Var,
    a: Att,
    args: Seq[T],
    res: ResOne[T],
    sorter: Option[(Int, Int => (Row, Row) => Int)]
  ): Unit = {
    val v = getVar(attr)
    find += v
    addCast(res.j2s)
    addSort(sorter)
    attr.exprAttr.fold {
      expr(e, a, v, attr.op, args, res)
    } { exprAttr =>
      expr2(e, a, v, getVar(exprAttr), attr.op)
    }
  }

  private def manLong(
    attr: Attr,
    e: Var,
    a: Att,
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
        man(attr, e, a, args, res, sorter)
    }
  }

  private def tac[T: ClassTag](
    attr: Attr,
    e: Var,
    a: Att,
    args: Seq[T],
    res: ResOne[T],
  ): Unit = {
    val v = getVar(attr)
    attr.exprAttr.fold {
      expr(e, a, v, attr.op, args, res)
    } { exprAttr =>
      expr2(e, a, v, getVar(exprAttr), attr.op)
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
  private def expr2(
    e: Var,
    a: Att,
    v: Var,
    w: Var,
    op: Op,
  ): Unit = {
    op match {
      case Eq    => equal2(e, a, w)
      case Neq   => neq2(e, a, v, w)
      case Lt    => compare2(e, a, v, w, "<")
      case Gt    => compare2(e, a, v, w, ">")
      case Le    => compare2(e, a, v, w, "<=")
      case Ge    => compare2(e, a, v, w, ">=")
      case other => unexpectedOp(other)
    }
  }

  private def opt[T: ClassTag](
    attr: Attr,
    e: Var,
    a: Att,
    optArgs: Option[Seq[T]],
    resOpt: ResOneOpt[T],
    sorter: Option[(Int, Int => (Row, Row) => Int)]
  ): Unit = {
    val v = getVar(attr)
    addCast(resOpt.j2s)
    addSort(sorter)

    attr.exprAttr.fold {
      attr.op match {
        case V     => optV(e, a, v)
        case Eq    => optEqual(e, a, v, optArgs, resOpt.s2j)
        case Neq   => optEq(e, a, v, optArgs, resOpt.tpe, resOpt.toDatalog)
        case Lt    => optCompare(e, a, v, optArgs, "<", resOpt.s2j)
        case Gt    => optCompare(e, a, v, optArgs, ">", resOpt.s2j)
        case Le    => optCompare(e, a, v, optArgs, "<=", resOpt.s2j)
        case Ge    => optCompare(e, a, v, optArgs, ">=", resOpt.s2j)
        case other => unexpectedOp(other)
      }
    } { exprAttr =>
      //      expr2(e, a, v, getVar(exprAttr), attr.op)
      val w = getVar(exprAttr)
      attr.op match {
        case Eq    => optEqual2(e, a, v, w)
        case Neq   => optEq2(e, a, v, w)
        case Lt    => optCompare2(e, a, v, w, "<")
        case Gt    => optCompare2(e, a, v, w, ">")
        case Le    => optCompare2(e, a, v, w, "<=")
        case Ge    => optCompare2(e, a, v, w, ">=")
        case other => unexpectedOp(other)
      }
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
  private def equal2[T: ClassTag](e: Var, a: Att, w: Var): Unit = {
    where += s"[$e $a $w$tx]" -> wClause
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
  private def neq2[T](e: Var, a: Att, v: Var, w: Var): Unit = {
    where += s"[$e $a $v$tx]" -> wClause
    where += s"[(!= $v $w)]" -> wNeqOne
  }

  private def compare[T](e: Var, a: Att, v: Var, arg: T, op: String, fromScala: Any => Any): Unit = {
    val v1 = v + 1
    in += v1
    where += s"[$e $a $v$tx]" -> wClause
    where += s"[($op $v $v1)]" -> wNeqOne
    args += fromScala(arg).asInstanceOf[AnyRef]
  }
  private def compare2(e: Var, a: Att, v: Var, w: Var, op: String): Unit = {
    where += s"[$e $a $v$tx]" -> wClause
    where += s"[($op $v $w)]" -> wNeqOne
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
  private def optEqual2(
    e: Var,
    a: Att,
    v: Var,
    w: Var
  ): Unit = {
    find += v
    equal2(e, a, w)
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
  private def optEq2(
    e: Var,
    a: Att,
    v: Var,
    w: Var
  ): Unit = {
    find += v
    where += s"[$e $a $w$tx]" -> wClause
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
  private def optCompare2(
    e: Var,
    a: Att,
    v: Var,
    w: Var,
    op: String,
  ): Unit = {
    find += v
    compare2(e, a, v, w, op)
  }
}