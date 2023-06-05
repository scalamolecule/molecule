package molecule.sql.core.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import scala.reflect.ClassTag

trait ResolveExprOne[Tpl]
  extends SortOneSpecial[Tpl]
    with SortOneOpt_[Tpl] { self: SqlModel2Query[Tpl] with LambdasOne =>

  protected def resolveAttrOneMan(attr: AttrOneMan): Unit = {
    aritiesAttr()
    sortAttrIndex += 1
    val (e, a) = ("x", s":${attr.ns}/${attr.attr}")
    attr match {
      case at: AttrOneManString     => man(attr, e, a, at.vs, resString1, resOptString1)
      case at: AttrOneManInt        => man(attr, e, a, at.vs, resInt1, resOptInt1)
      case at: AttrOneManLong       => manLong(attr, e, a, at.vs, resLong1, resOptLong1)
      case at: AttrOneManFloat      => man(attr, e, a, at.vs, resFloat1, resOptFloat1)
      case at: AttrOneManDouble     => man(attr, e, a, at.vs, resDouble1, resOptDouble1)
      case at: AttrOneManBoolean    => man(attr, e, a, at.vs, resBoolean1, resOptBoolean1)
      case at: AttrOneManBigInt     => man(attr, e, a, at.vs, resBigInt1, resOptBigInt1)
      case at: AttrOneManBigDecimal => man(attr, e, a, at.vs, resBigDecimal1, resOptBigDecimal1)
      case at: AttrOneManDate       => man(attr, e, a, at.vs, resDate1, resOptDate1)
      case at: AttrOneManUUID       => man(attr, e, a, at.vs, resUUID1, resOptUUID1)
      case at: AttrOneManURI        => man(attr, e, a, at.vs, resURI1, resOptURI1)
      case at: AttrOneManByte       => man(attr, e, a, at.vs, resByte1, resOptByte1)
      case at: AttrOneManShort      => man(attr, e, a, at.vs, resShort1, resOptShort1)
      case at: AttrOneManChar       => man(attr, e, a, at.vs, resChar1, resOptChar1)
    }
  }

  protected def resolveAttrOneTac(attr: AttrOneTac): Unit = {
    val (e, a) = ("x", s":${attr.ns}/${attr.attr}")
    if (isNestedOpt && !isTxMetaData)
      throw ModelError("Tacit attributes not allowed in optional nested queries. Found: " + a)
    attr match {
      case at: AttrOneTacString     => tac(attr, e, a, at.vs, resString1)
      case at: AttrOneTacInt        => tac(attr, e, a, at.vs, resInt1)
      case at: AttrOneTacLong       => tac(attr, e, a, at.vs, resLong1)
      case at: AttrOneTacFloat      => tac(attr, e, a, at.vs, resFloat1)
      case at: AttrOneTacDouble     => tac(attr, e, a, at.vs, resDouble1)
      case at: AttrOneTacBoolean    => tac(attr, e, a, at.vs, resBoolean1)
      case at: AttrOneTacBigInt     => tac(attr, e, a, at.vs, resBigInt1)
      case at: AttrOneTacBigDecimal => tac(attr, e, a, at.vs, resBigDecimal1)
      case at: AttrOneTacDate       => tac(attr, e, a, at.vs, resDate1)
      case at: AttrOneTacUUID       => tac(attr, e, a, at.vs, resUUID1)
      case at: AttrOneTacURI        => tac(attr, e, a, at.vs, resURI1)
      case at: AttrOneTacByte       => tac(attr, e, a, at.vs, resByte1)
      case at: AttrOneTacShort      => tac(attr, e, a, at.vs, resShort1)
      case at: AttrOneTacChar       => tac(attr, e, a, at.vs, resChar1)
    }
  }

  protected def resolveAttrOneOpt(attr: AttrOneOpt): Unit = {
    aritiesAttr()
    sortAttrIndex += 1
    hasOptAttr = true // to avoid redundant None's
    val (e, a) = ("x", s":${attr.ns}/${attr.attr}")
    attr match {
      case at: AttrOneOptString     => opt(attr, e, a, at.vs, resOptString1, resOptString)
      case at: AttrOneOptInt        => opt(attr, e, a, at.vs, resOptInt1, resOptInt)
      case at: AttrOneOptLong       => opt(attr, e, a, at.vs, resOptLong1, resOptLong)
      case at: AttrOneOptFloat      => opt(attr, e, a, at.vs, resOptFloat1, resOptFloat)
      case at: AttrOneOptDouble     => opt(attr, e, a, at.vs, resOptDouble1, resOptDouble)
      case at: AttrOneOptBoolean    => opt(attr, e, a, at.vs, resOptBoolean1, resOptBoolean)
      case at: AttrOneOptBigInt     => opt(attr, e, a, at.vs, resOptBigInt1, resOptBigInt)
      case at: AttrOneOptBigDecimal => opt(attr, e, a, at.vs, resOptBigDecimal1, resOptBigDecimal)
      case at: AttrOneOptDate       => opt(attr, e, a, at.vs, resOptDate1, resOptDate)
      case at: AttrOneOptUUID       => opt(attr, e, a, at.vs, resOptUUID1, resOptUUID)
      case at: AttrOneOptURI        => opt(attr, e, a, at.vs, resOptURI1, resOptURI)
      case at: AttrOneOptByte       => opt(attr, e, a, at.vs, resOptByte1, resOptByte)
      case at: AttrOneOptShort      => opt(attr, e, a, at.vs, resOptShort1, resOptShort)
      case at: AttrOneOptChar       => opt(attr, e, a, at.vs, resOptChar1, resOptChar)
    }
  }

  private def addSort(attr: Attr, col: String): Unit = {
    attr.sort.foreach { sort =>
      val (dir, arity) = (sort.head, sort.last)
      if (orderBy.exists(ob => ob._1 == level && ob._2 == arity)) {
        throw ModelError(s"Sort arity $arity is already used on this level. " +
          s"Please use distinct continuous sort arities beginning from 1.")
      }
      dir match {
        case 'a' => orderBy += ((level, arity, col, ""))
        case 'd' => orderBy += ((level, arity, col, " DESC"))
      }
    }
  }

  private def man[T: ClassTag](
    attr: Attr,
    e: Var,
    a: Att,
    args: Seq[T],
    res: ResOne[T],
    resOpt: ResOneOpt[T],
  ): Unit = {
//    val v   = getVar(attr)
    val col = getCol(attr: Attr)
    select += col
    //    println(s"########################### $attr  $isNestedOpt")
    if (isNestedOpt) {
      addCast(res.j2sOrNull.asInstanceOf[(Row, AttrIndex) => AnyRef])
//      addCast(resOpt.j2sOrNull)
    } else {
      addCast(res.j2s.asInstanceOf[(Row, AttrIndex) => AnyRef])
      where += ((col, s"IS NOT NULL"))
    }
    addSort(attr, col)
    //    attr.filterAttr.fold {
    //      expr(e, a, v, attr.op, args, res)
    //      filterAttrVars1 = filterAttrVars1 + (a -> (e, v))
    //      filterAttrVars2.get(a).foreach(_(e, v))
    //    } { filterAttr =>
    //      expr2(e, a, v, getVar(filterAttr), attr.op)
    //    }
  }

  private def manLong(
    attr: Attr,
    e: Var,
    a: Att,
    args: Seq[Long],
    res: ResOne[Long],
    resOpt: ResOneOpt[Long],
  ): Unit = {
    a match {
      case ":_Generic/eid"  =>
        select += "id"
        addCast(res.j2s)
        addSort(attr, "id")
      case ":_Generic/txId" =>
        //        select += txVar
        //        addSort(attr "??")
        throw ModelError("txId not implemented yet for jdbc")
      case a                =>
        man(attr, e, a, args, res, resOpt)
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
    attr.filterAttr.fold {
      expr(e, a, v, attr.op, args, res)
      filterAttrVars1 = filterAttrVars1 + (a -> (e, v))
      filterAttrVars2.get(a).foreach(_(e, v))
    } { filterAttr =>
      expr2(e, a, v, getVar(filterAttr), attr.op)
    }
  }


  private def opt[T: ClassTag](
    attr: Attr,
    e: Var,
    a: Att,
    optArgs: Option[Seq[T]],
    resOpt: ResOneOpt[T],
    resOptOLD: ResOneOptOLD[T],
  ): Unit = {
    addCast(resOpt.j2s)
    val v   = getVar(attr)
    val col = getCol(attr: Attr)
    select += col
    addSort(attr, col)
    

    //    addCastOLD(resOptOLD.j2s)
    //    attr.filterAttr.fold {
    //      attr.op match {
    //        case V     => addSort(attr, col); optV(e, a, v)
    //        case Eq    => optEqual(attr, e, a, v, optArgs, resOptOLD.s2j)
    //        case Neq   => addSort(attr, col); optNeq(e, a, v, optArgs, resOptOLD.tpe, resOptOLD.toDatalog)
    //        case Lt    => addSort(attr, col); optCompare(e, a, v, optArgs, "<", resOptOLD.s2j)
    //        case Gt    => addSort(attr, col); optCompare(e, a, v, optArgs, ">", resOptOLD.s2j)
    //        case Le    => addSort(attr, col); optCompare(e, a, v, optArgs, "<=", resOptOLD.s2j)
    //        case Ge    => addSort(attr, col); optCompare(e, a, v, optArgs, ">=", resOptOLD.s2j)
    //        case other => unexpectedOp(other)
    //      }
    //    } { filterAttr =>
    //      addSort(attr, col)
    //      val w = getVar(filterAttr)
    //      attr.op match {
    //        case Eq    => optEqual2(e, a, v, w)
    //        case Neq   => optNeq2(e, a, v, w)
    //        case Lt    => optCompare2(e, a, v, w, "<")
    //        case Gt    => optCompare2(e, a, v, w, ">")
    //        case Le    => optCompare2(e, a, v, w, "<=")
    //        case Ge    => optCompare2(e, a, v, w, ">=")
    //        case other => unexpectedOp(other)
    //      }
    //    }
  }

  private def expr[T: ClassTag](
    e: Var,
    a: Att,
    v: Var,
    op: Op,
    args: Seq[T],
    res: ResOne[T],
    resOLD: ResOneOLD[T] = null,
  ): Unit = {
    op match {
      case V          => attr(e, a, v)
      case Eq         => equal(e, a, v, args, resOLD.s2j)
      case Neq        => neq(e, a, v, args, resOLD.tpe, resOLD.toDatalog)
      case Lt         => compare(e, a, v, args.head, "<", resOLD.s2j)
      case Gt         => compare(e, a, v, args.head, ">", resOLD.s2j)
      case Le         => compare(e, a, v, args.head, "<=", resOLD.s2j)
      case Ge         => compare(e, a, v, args.head, ">=", resOLD.s2j)
      case NoValue    => noValue(e, a)
      case Fn(kw, n)  => aggr(e, a, v, kw, n, resOLD)
      case StartsWith => stringOp(e, a, v, args.head, "starts-with?")
      case EndsWith   => stringOp(e, a, v, args.head, "ends-with?")
      case Contains   => stringOp(e, a, v, args.head, "includes?")
      case Matches    => regex(e, a, v, args.head)
      case Take       => string(e, a, v, args, "take")
      case TakeRight  => string(e, a, v, args, "takeRight")
      case Drop       => string(e, a, v, args, "drop")
      case DropRight  => string(e, a, v, args, "dropRight")
      case SubString  => string(e, a, v, args, "slice")
      case Slice      => string(e, a, v, args, "slice")
      case Remainder  => remainder(e, a, v, args)
      case Even       => even(e, a, v)
      case Odd        => odd(e, a, v)
      case other      => unexpectedOp(other)
    }
  }
  private def expr2(
    e: Var,
    a: Att,
    v: Var,
    w: Var,
    op: Op,
  ): Unit = op match {
    case Eq    => equal2(e, a, v, w)
    case Neq   => neq2(e, a, v, w)
    case Lt    => compare2(e, a, v, w, "<")
    case Gt    => compare2(e, a, v, w, ">")
    case Le    => compare2(e, a, v, w, "<=")
    case Ge    => compare2(e, a, v, w, ">=")
    case other => unexpectedOp(other)
  }


  private def stringOp[T](e: Var, a: Att, v: Var, arg: T, predicate: String): Unit = {
    val v1 = v + 1
    in += v1
    whereOLD += s"[$e $a $v$tx]" -> wClause
    whereOLD += s"[(clojure.string/$predicate $v $v1)]" -> wNeqOne
    args += arg.asInstanceOf[AnyRef]
  }
  private def regex[T](e: Var, a: Att, v: Var, regex: T): Unit = {
    whereOLD += s"[$e $a $v$tx]" -> wClause
    whereOLD += s"""[(re-find (re-pattern "$regex") $v)]""" -> wNeqOne
  }

  private def remainder[T](e: Var, a: Att, v: Var, args: Seq[T]): Unit = {
    whereOLD += s"[$e $a $v$tx]" -> wClause
    whereOLD += s"""[(rem $v ${args.head}) $v-rem]""" -> wNeqOne
    whereOLD += s"""[(= $v-rem ${args(1)})]""" -> wNeqOne
  }
  private def even(e: Var, a: Att, v: Var): Unit = {
    whereOLD += s"[$e $a $v$tx]" -> wClause
    whereOLD += s"""[(even? $v)]""" -> wNeqOne
  }
  private def odd(e: Var, a: Att, v: Var): Unit = {
    whereOLD += s"[$e $a $v$tx]" -> wClause
    whereOLD += s"""[(odd? $v)]""" -> wNeqOne
  }


  private def string[T: ClassTag](e: Var, a: Att, v: Var, args: Seq[T], op: String): Unit = {
    select -= v
    whereOLD += s"[$e $a $v$tx]" -> wClause
    op match {
      case "take" =>
        select += s"$v-1"
        whereOLD += s"[(max 0 ${args.head}) $v-n]" -> wNeqOne
        whereOLD += s"[(count $v) $v-length]" -> wNeqOne
        whereOLD += s"[(> $v-n 0)]" -> wNeqOne
        whereOLD += s"[(min $v-n $v-length) $v-end]" -> wNeqOne
        whereOLD += s"[(subs $v 0 $v-end) $v-1]" -> wNeqOne

      case "takeRight" =>
        select += s"$v-1"
        whereOLD += s"[(max 0 ${args.head}) $v-n]" -> wNeqOne
        whereOLD += s"[(count $v) $v-length]" -> wNeqOne
        whereOLD += s"[(> $v-n 0)]" -> wNeqOne
        whereOLD += s"[(- $v-length $v-n) $v-back]" -> wNeqOne
        whereOLD += s"[(max 0 $v-back) $v-begin]" -> wNeqOne
        whereOLD += s"[(subs $v $v-begin $v-length) $v-1]" -> wNeqOne

      case "drop" =>
        select += s"$v-1"
        whereOLD += s"[(max 0 ${args.head}) $v-n]" -> wNeqOne
        whereOLD += s"[(count $v) $v-length]" -> wNeqOne
        whereOLD += s"[(< $v-n $v-length)]" -> wNeqOne
        whereOLD += s"[(min $v-n $v-length) $v-begin]" -> wNeqOne
        whereOLD += s"[(subs $v $v-begin $v-length) $v-1]" -> wNeqOne

      case "dropRight" =>
        select += s"$v-1"
        whereOLD += s"[(max 0 ${args.head}) $v-n]" -> wNeqOne
        whereOLD += s"[(count $v) $v-length]" -> wNeqOne
        whereOLD += s"[(< $v-n $v-length)]" -> wNeqOne
        whereOLD += s"[(- $v-length $v-n) $v-end]" -> wNeqOne
        whereOLD += s"[(subs $v 0 $v-end) $v-1]" -> wNeqOne

      case "slice" =>
        select += s"$v-1"
        whereOLD += s"[(count $v) $v-length]" -> wNeqOne
        whereOLD += s"[(max 0 ${args.head}) $v-from]" -> wNeqOne
        whereOLD += s"[(min $v-length (max 0 ${args(1)})) $v-until]" -> wNeqOne
        whereOLD += s"[(< $v-from $v-until)]" -> wNeqOne
        whereOLD += s"[(subs $v $v-from $v-until) $v-1]" -> wNeqOne
    }
  }


  private def aggr[T](e: Var, a: Att, v: Var, fn: String, optN: Option[Int], res: ResOneOLD[T]): Unit = {
    lazy val n = optN.getOrElse(0)
    // Replace find/casting with aggregate function/cast
    select -= v
    fn match {
      case "distinct" =>
        select += s"(distinct $v)"
        replaceCast(res.set2set)

      case "mins" =>
        select += s"(min $n $v)"
        replaceCast(res.vector2set)

      case "min" =>
        select += s"(min $v)"

      case "maxs" =>
        select += s"(max $n $v)"
        replaceCast(res.vector2set)

      case "max" =>
        select += s"(max $v)"

      case "rands" =>
        select += s"(rand $n $v)"
        replaceCast(res.vector2set)

      case "rand" =>
        select += s"(rand $v)"

      case "samples" =>
        select += s"(sample $n $v)"
        replaceCast(res.vector2set)

      case "sample" =>
        select += s"(sample 1 $v)"
        replaceCast(res.seq2t)

      case "count" =>
        select += s"(count $v)"
        widh += e
        replaceCast(toInt)

      case "countDistinct" =>
        select += s"(count-distinct $v)"
        widh += e
        replaceCast(toInt)

      case "sum"      => select += s"(sum $v)"
      case "median"   => select += s"(median $v)"
      case "avg"      => select += s"(avg $v)"
      case "variance" => select += s"(variance $v)"
      case "stddev"   => select += s"(stddev $v)"

      case other => unexpectedKw(other)
    }
    whereOLD += s"[$e $a $v$tx]" -> wClause
  }

  private def attr(e: Var, a: Att, v: Var): Unit = {
    whereOLD += s"[$e $a $v$tx]" -> wClause


  }

  private def equal[T: ClassTag](e: Var, a: Att, v: Var, argValues: Seq[T], fromScala: Any => Any): Unit = {
    in += s"[$v ...]"
    whereOLD += s"[$e $a $v$tx]" -> wClause
    args += argValues.map(fromScala).toArray
  }
  private def equal2(e: Var, a: Att, v: Var, w: Var): Unit = {
    whereOLD += s"[$e $a $w$tx]" -> wClause
    whereOLD += s"[(identity $w) $v]" -> wGround
  }

  private def neq[T](e: Var, a: Att, v: Var, args: Seq[T], tpe: String, toDatalog: T => String): Unit = {
    whereOLD += s"[$e $a $v$tx]" -> wClause
    if (tpe == "URI") {
      args.zipWithIndex.foreach { case (arg, i) =>
        whereOLD += s"""[(ground (new java.net.URI "$arg")) $v$i]""" -> wNeqOne
        whereOLD += s"[(!= $v $v$i)]" -> wNeqOne
      }
    } else {
      args.foreach { arg =>
        whereOLD += s"[(!= $v ${toDatalog(arg)})]" -> wNeqOne
      }
    }
  }
  private def neq2(e: Var, a: Att, v: Var, w: Var): Unit = {
    whereOLD += s"[$e $a $v$tx]" -> wClause
    whereOLD += s"[(!= $v $w)]" -> wNeqOne
  }

  private def compare[T](e: Var, a: Att, v: Var, arg: T, op: String, fromScala: Any => Any): Unit = {
    val v1 = v + 1
    in += v1
    whereOLD += s"[$e $a $v$tx]" -> wClause
    whereOLD += s"[($op $v $v1)]" -> wNeqOne
    args += fromScala(arg).asInstanceOf[AnyRef]
  }
  private def compare2(e: Var, a: Att, v: Var, w: Var, op: String): Unit = {
    whereOLD += s"[$e $a $v$tx]" -> wClause
    whereOLD += s"[($op $v $w)]" -> wNeqOne
  }

  private def noValue(e: Var, a: Att): Unit = {
    whereOLD += s"(not [$e $a])" -> wNeqOne
  }


  private def optV(e: Var, a: Att, v: Var): Unit = {
    select += s"(pull $e-$v [[$a :limit nil]]) "
    whereOLD += s"[(identity $e) $e-$v]" -> wGround
  }

  private def optEqual[T: ClassTag](
    attr: Attr,
    e: Var,
    a: Att,
    v: Var,
    optArgs: Option[Seq[T]],
    fromScala: Any => Any,
    //    sortMan: Option[(Int, Int => (RowOLD, RowOLD) => Int)]
  ): Unit = {
    val col = getCol(attr: Attr)
    optArgs.fold[Unit] {
      addSort(attr, col)
      select += s"(pull $e-$v [[$a :limit nil]])"
      whereOLD += s"(not [$e $a])" -> wNeqOne
      whereOLD += s"[(identity $e) $e-$v]" -> wGround
    } { vs =>
      addSort(attr, col)
      select += v
      equal(e, a, v, vs, fromScala)
    }
  }
  private def optEqual2(
    e: Var,
    a: Att,
    v: Var,
    w: Var
  ): Unit = {
    select += v
    equal2(e, a, v, w)
  }

  private def optNeq[T](
    e: Var,
    a: Att,
    v: Var,
    optArgs: Option[Seq[T]],
    tpe: String,
    toDatalog: T => String
  ): Unit = {
    select += v
    whereOLD += s"[$e $a $v$tx]" -> wClause
    if (optArgs.isDefined && optArgs.get.nonEmpty) {
      neq(e, a, v, optArgs.get, tpe, toDatalog)
    }
  }
  private def optNeq2(
    e: Var,
    a: Att,
    v: Var,
    w: Var
  ): Unit = {
    select += v
    neq2(e, a, v, w)
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
      select += s"$v-nil"
      whereOLD += s"[(ground nil) $v-nil]" -> wGround
    } { vs =>
      select += v
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
    select += v
    compare2(e, a, v, w, op)
  }
}