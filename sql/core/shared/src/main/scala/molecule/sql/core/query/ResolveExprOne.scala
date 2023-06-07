package molecule.sql.core.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import scala.reflect.ClassTag

trait ResolveExprOne[Tpl] { self: SqlModel2Query[Tpl] with LambdasOne =>

  protected def resolveAttrOneMan(attr: AttrOneMan): Unit = {
    aritiesAttr()
    sortAttrIndex += 1
    val (e, a) = ("x", s":${attr.ns}/${attr.attr}")
    attr match {
      case at: AttrOneManLong       => manLong(attr, at.vs, resLong1)
      case at: AttrOneManString     => man(attr, at.vs, resString1)
      case at: AttrOneManInt        => man(attr, at.vs, resInt1)
      case at: AttrOneManFloat      => man(attr, at.vs, resFloat1)
      case at: AttrOneManDouble     => man(attr, at.vs, resDouble1)
      case at: AttrOneManBoolean    => man(attr, at.vs, resBoolean1)
      case at: AttrOneManBigInt     => man(attr, at.vs, resBigInt1)
      case at: AttrOneManBigDecimal => man(attr, at.vs, resBigDecimal1)
      case at: AttrOneManDate       => man(attr, at.vs, resDate1)
      case at: AttrOneManUUID       => man(attr, at.vs, resUUID1)
      case at: AttrOneManURI        => man(attr, at.vs, resURI1)
      case at: AttrOneManByte       => man(attr, at.vs, resByte1)
      case at: AttrOneManShort      => man(attr, at.vs, resShort1)
      case at: AttrOneManChar       => man(attr, at.vs, resChar1)
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
      case at: AttrOneOptString     => opt(attr, e, a, at.vs, resOptString1)
      case at: AttrOneOptInt        => opt(attr, e, a, at.vs, resOptInt1)
      case at: AttrOneOptLong       => opt(attr, e, a, at.vs, resOptLong1)
      case at: AttrOneOptFloat      => opt(attr, e, a, at.vs, resOptFloat1)
      case at: AttrOneOptDouble     => opt(attr, e, a, at.vs, resOptDouble1)
      case at: AttrOneOptBoolean    => opt(attr, e, a, at.vs, resOptBoolean1)
      case at: AttrOneOptBigInt     => opt(attr, e, a, at.vs, resOptBigInt1)
      case at: AttrOneOptBigDecimal => opt(attr, e, a, at.vs, resOptBigDecimal1)
      case at: AttrOneOptDate       => opt(attr, e, a, at.vs, resOptDate1)
      case at: AttrOneOptUUID       => opt(attr, e, a, at.vs, resOptUUID1)
      case at: AttrOneOptURI        => opt(attr, e, a, at.vs, resOptURI1)
      case at: AttrOneOptByte       => opt(attr, e, a, at.vs, resOptByte1)
      case at: AttrOneOptShort      => opt(attr, e, a, at.vs, resOptShort1)
      case at: AttrOneOptChar       => opt(attr, e, a, at.vs, resOptChar1)
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
    args: Seq[T],
    res: ResOne[T],
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    if (isNestedOpt) {
      addCast(res.j2sOrNull)
    } else {
      addCast(res.j2s)
      notNull += ((col, s"IS NOT NULL"))
    }
    addSort(attr, col)
    attr.filterAttr.fold {
      expr(col, attr.op, args, res)
      //      filterAttrVars1 = filterAttrVars1 + (a -> (e, v))
      //      filterAttrVars2.get(a).foreach(_(e, v))
    } { filterAttr =>
      expr2(col, attr.op)
    }
  }

  private def manLong(
    attr: Attr,
    args: Seq[Long],
    res: ResOne[Long],
  ): Unit = {
    attr.name match {
      case "_Generic.eid"  =>
        select += "id"
        addCast(res.j2s)
        addSort(attr, "id")
      case "_Generic.txId" =>
        throw ModelError("txId not implemented yet for jdbc")
      case a               =>
        man(attr, args, res)
    }
  }

  private def tac[T: ClassTag](
    attr: Attr,
    e: Var,
    a: Att,
    args: Seq[T],
    res: ResOne[T],
  ): Unit = {
    val col = getCol(attr: Attr)
    notNull += ((col, s"IS NOT NULL"))
    val v = getVar(attr)
    attr.filterAttr.fold {
      expr(col, attr.op, args, res)
      filterAttrVars1 = filterAttrVars1 + (a -> (e, v))
      filterAttrVars2.get(a).foreach(_(e, v))
    } { filterAttr =>
      expr2(col, attr.op)
    }
  }


  private def opt[T: ClassTag](
    attr: Attr,
    e: Var,
    a: Att,
    optArgs: Option[Seq[T]],
    resOpt: ResOneOpt[T],
  ): Unit = {
    val v   = getVar(attr)
    val col = getCol(attr: Attr)
    addCast(resOpt.j2s)
    addSort(attr, col)
    select += col
    attr.filterAttr.fold {
      attr.op match {
        case V     => () // selected col can already be a value or null
        case Eq    => optEqual(col, optArgs, resOpt.s2sql)
        case Neq   => optNeq(col, optArgs, resOpt.tpe, resOpt.s2sql)
        case Lt    => optCompare(col, optArgs, "<", resOpt.s2sql)
        case Gt    => optCompare(col, optArgs, ">", resOpt.s2sql)
        case Le    => optCompare(col, optArgs, "<=", resOpt.s2sql)
        case Ge    => optCompare(col, optArgs, ">=", resOpt.s2sql)
        case other => unexpectedOp(other)
      }
    } { filterAttr =>
      addSort(attr, col)
      val w = getVar(filterAttr)
      attr.op match {
        case Eq    => optEqual2(e, a, v, w)
        case Neq   => optNeq2(e, a, v, w)
        case Lt    => optCompare2(e, a, v, w, "<")
        case Gt    => optCompare2(e, a, v, w, ">")
        case Le    => optCompare2(e, a, v, w, "<=")
        case Ge    => optCompare2(e, a, v, w, ">=")
        case other => unexpectedOp(other)
      }
    }
  }

  private def expr[T: ClassTag](
    col: String,
    op: Op,
    args: Seq[T],
    res: ResOne[T],
  ): Unit = {
    op match {
      case V       => () //attr(e, a, v)
      case Eq      => equal(col, args, res.s2sql)
      case Neq     => neq(col, args, res.tpe, res.s2sql)
      case Lt      => compare(col, args.head, "<", res.s2sql)
      case Gt      => compare(col, args.head, ">", res.s2sql)
      case Le      => compare(col, args.head, "<=", res.s2sql)
      case Ge      => compare(col, args.head, ">=", res.s2sql)
      case NoValue => noValue(col)
      //      case Fn(kw, n)  => aggr(e, a, v, kw, n, resOLD)
      //      case StartsWith => stringOp(e, a, v, args.head, "starts-with?")
      //      case EndsWith   => stringOp(e, a, v, args.head, "ends-with?")
      //      case Contains   => stringOp(e, a, v, args.head, "includes?")
      //      case Matches    => regex(e, a, v, args.head)
      //      case Take       => string(e, a, v, args, "take")
      //      case TakeRight  => string(e, a, v, args, "takeRight")
      //      case Drop       => string(e, a, v, args, "drop")
      //      case DropRight  => string(e, a, v, args, "dropRight")
      //      case SubString  => string(e, a, v, args, "slice")
      //      case Slice      => string(e, a, v, args, "slice")
      case Remainder => remainder(col, args)
      case Even      => even(col)
      case Odd       => odd(col)
      case other     => unexpectedOp(other)
    }
  }
  private def expr2(
    col: String,
    op: Op,
  ): Unit = op match {
    //    case Eq    => equal2(e, a, v, w)
    //    case Neq   => neq2(e, a, v, w)
    //    case Lt    => compare2(e, a, v, w, "<")
    //    case Gt    => compare2(e, a, v, w, ">")
    //    case Le    => compare2(e, a, v, w, "<=")
    //    case Ge    => compare2(e, a, v, w, ">=")
    case other => unexpectedOp(other)
  }


  private def stringOp[T](col: String, arg: T, predicate: String): Unit = {
    //    val v1 = v + 1
    //    in += v1
    //    whereOLD += s"[$e $a $v$tx]" -> wClause
    //    whereOLD += s"[(clojure.string/$predicate $v $v1)]" -> wNeqOne
    //    args += arg.asInstanceOf[AnyRef]
  }
  private def regex[T](col: String, regex: T): Unit = {
    //    whereOLD += s"[$e $a $v$tx]" -> wClause
    //    whereOLD += s"""[(re-find (re-pattern "$regex") $v)]""" -> wNeqOne
  }

  private def remainder[T](col: String, args: Seq[T]): Unit = {
    where += ((col, s"% ${args.head} = ${args(1)}"))
  }
  private def even(col: String): Unit = {
    //    whereOLD += s"[$e $a $v$tx]" -> wClause
    //    whereOLD += s"""[(even? $v)]""" -> wNeqOne
    where += ((col, s"% 2 = 0"))
  }
  private def odd(col: String): Unit = {
    //    whereOLD += s"[$e $a $v$tx]" -> wClause
    //    whereOLD += s"""[(odd? $v)]""" -> wNeqOne
    where += ((col, s"% 2 = 1"))
  }


  private def string[T: ClassTag](col: String, args: Seq[T], op: String): Unit = {
    //    select -= v
    //    whereOLD += s"[$e $a $v$tx]" -> wClause
    op match {
      case "take" =>
      //        select += s"$v-1"
      //        whereOLD += s"[(max 0 ${args.head}) $v-n]" -> wNeqOne
      //        whereOLD += s"[(count $v) $v-length]" -> wNeqOne
      //        whereOLD += s"[(> $v-n 0)]" -> wNeqOne
      //        whereOLD += s"[(min $v-n $v-length) $v-end]" -> wNeqOne
      //        whereOLD += s"[(subs $v 0 $v-end) $v-1]" -> wNeqOne

      case "takeRight" =>
      //        select += s"$v-1"
      //        whereOLD += s"[(max 0 ${args.head}) $v-n]" -> wNeqOne
      //        whereOLD += s"[(count $v) $v-length]" -> wNeqOne
      //        whereOLD += s"[(> $v-n 0)]" -> wNeqOne
      //        whereOLD += s"[(- $v-length $v-n) $v-back]" -> wNeqOne
      //        whereOLD += s"[(max 0 $v-back) $v-begin]" -> wNeqOne
      //        whereOLD += s"[(subs $v $v-begin $v-length) $v-1]" -> wNeqOne

      case "drop" =>
      //        select += s"$v-1"
      //        whereOLD += s"[(max 0 ${args.head}) $v-n]" -> wNeqOne
      //        whereOLD += s"[(count $v) $v-length]" -> wNeqOne
      //        whereOLD += s"[(< $v-n $v-length)]" -> wNeqOne
      //        whereOLD += s"[(min $v-n $v-length) $v-begin]" -> wNeqOne
      //        whereOLD += s"[(subs $v $v-begin $v-length) $v-1]" -> wNeqOne

      case "dropRight" =>
      //        select += s"$v-1"
      //        whereOLD += s"[(max 0 ${args.head}) $v-n]" -> wNeqOne
      //        whereOLD += s"[(count $v) $v-length]" -> wNeqOne
      //        whereOLD += s"[(< $v-n $v-length)]" -> wNeqOne
      //        whereOLD += s"[(- $v-length $v-n) $v-end]" -> wNeqOne
      //        whereOLD += s"[(subs $v 0 $v-end) $v-1]" -> wNeqOne

      case "slice" =>
      //        select += s"$v-1"
      //        whereOLD += s"[(count $v) $v-length]" -> wNeqOne
      //        whereOLD += s"[(max 0 ${args.head}) $v-from]" -> wNeqOne
      //        whereOLD += s"[(min $v-length (max 0 ${args(1)})) $v-until]" -> wNeqOne
      //        whereOLD += s"[(< $v-from $v-until)]" -> wNeqOne
      //        whereOLD += s"[(subs $v $v-from $v-until) $v-1]" -> wNeqOne
    }
  }


  private def aggr[T](col: String, fn: String, optN: Option[Int], res: ResOneOLD[T]): Unit = {
    lazy val n = optN.getOrElse(0)
    // Replace find/casting with aggregate function/cast
    //    select -= v
    fn match {
      case "distinct" =>
        //        select += s"(distinct $v)"
        replaceCast(res.set2set)

      case "mins" =>
        //        select += s"(min $n $v)"
        replaceCast(res.vector2set)

      case "min" =>
      //        select += s"(min $v)"

      case "maxs" =>
        //        select += s"(max $n $v)"
        replaceCast(res.vector2set)

      case "max" =>
      //        select += s"(max $v)"

      case "rands" =>
        //        select += s"(rand $n $v)"
        replaceCast(res.vector2set)

      case "rand" =>
      //        select += s"(rand $v)"

      case "samples" =>
        //        select += s"(sample $n $v)"
        replaceCast(res.vector2set)

      case "sample" =>
        //        select += s"(sample 1 $v)"
        replaceCast(res.seq2t)

      case "count" =>
        //        select += s"(count $v)"
        //        widh += e
        replaceCast(toInt)

      case "countDistinct" =>
        //        select += s"(count-distinct $v)"
        //        widh += e
        replaceCast(toInt)

      //      case "sum"      => select += s"(sum $v)"
      //      case "median"   => select += s"(median $v)"
      //      case "avg"      => select += s"(avg $v)"
      //      case "variance" => select += s"(variance $v)"
      //      case "stddev"   => select += s"(stddev $v)"

      case other => unexpectedKw(other)
    }
    //    whereOLD += s"[$e $a $v$tx]" -> wClause
  }

  private def attr(col: String): Unit = {
    //    whereOLD += s"[$e $a $v$tx]" -> wClause


  }

  private def equal[T: ClassTag](col: String, argValues: Seq[T], s2sql: T => String): Unit = {
    if (argValues.length == 1)
      where += ((col, "= " + s2sql(argValues.head)))
    else
      where += ((col, argValues.map(s2sql).mkString("IN (", ", ", ")")))
  }
  private def equal2(col: String, w: Var): Unit = {
    //    whereOLD += s"[$e $a $w$tx]" -> wClause
    //    whereOLD += s"[(identity $w) $v]" -> wGround
  }

  private def neq[T](col: String, args: Seq[T], tpe: String, s2sql: T => String): Unit = {
    //    whereOLD += s"[$e $a $v$tx]" -> wClause
    //    if (tpe == "URI") {
    //      args.zipWithIndex.foreach { case (arg, i) =>
    //        whereOLD += s"""[(ground (new java.net.URI "$arg")) $v$i]""" -> wNeqOne
    //        whereOLD += s"[(!= $v $v$i)]" -> wNeqOne
    //      }
    //    } else {
    //      args.foreach { arg =>
    //        whereOLD += s"[(!= $v ${toDatalog(arg)})]" -> wNeqOne
    //      }
    //    }

    if (args.length == 1)
      where += ((col, "<> " + s2sql(args.head)))
    else
      where += ((col, args.map(s2sql).mkString("NOT IN (", ", ", ")")))
  }
  private def neq2(col: String, w: Var): Unit = {
    //    whereOLD += s"[$e $a $v$tx]" -> wClause
    //    whereOLD += s"[(!= $v $w)]" -> wNeqOne
  }

  private def compare[T](col: String, arg: T, op: String, s2sql: T => String): Unit = {
    where += ((col, op + " " + s2sql(arg)))
  }
  private def compare2(col: String, w: Var, op: String): Unit = {
    //    whereOLD += s"[$e $a $v$tx]" -> wClause
    //    whereOLD += s"[($op $v $w)]" -> wNeqOne
  }

  private def noValue(col: String): Unit = {
    // Skip tacit not null clause in this special case
    notNull -= ((col, s"IS NOT NULL"))
    where += ((col, s"IS NULL"))
  }


  private def optEqual[T: ClassTag](
    col: String,
    optArgs: Option[Seq[T]],
    s2sql: T => String,
  ): Unit = {
    optArgs.fold[Unit] {
      where += ((col, s"IS NULL"))
    } { vs =>
      equal(col, vs, s2sql)
    }
  }
  private def optEqual2(
    e: Var,
    a: Att,
    v: Var,
    w: Var
  ): Unit = {
    //    select += v
    //    equal2(e, a, v, w)
  }

  private def optNeq[T](
    col: String,
    optArgs: Option[Seq[T]],
    tpe: String,
    s2sql: T => String
  ): Unit = {
    if (optArgs.isDefined && optArgs.get.nonEmpty) {
      neq(col, optArgs.get, tpe, s2sql)
    } else {
      where += ((col, "IS NOT NULL"))
    }
  }
  private def optNeq2(
    e: Var,
    a: Att,
    v: Var,
    w: Var
  ): Unit = {
    //    select += v
    //    neq2(e, a, v, w)
  }

  private def optCompare[T](
    col: String,
    optArgs: Option[Seq[T]],
    op: String,
    s2sql: T => String
  ): Unit = {
    optArgs.fold[Unit] {
      // Always return empty result when trying to compare None
      where += (("FALSE", ""))
    } { vs =>
      where += ((col, op + " " + s2sql(vs.head)))
    }


  }
  private def optCompare2(
    e: Var,
    a: Att,
    v: Var,
    w: Var,
    op: String,
  ): Unit = {
    //    select += v
    //    compare2(e, a, v, w, op)
  }
}