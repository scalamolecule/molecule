package molecule.sql.core.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import scala.math.{max, min}
import scala.reflect.ClassTag

trait ResolveExprOne[Tpl]
  extends SortOneSpecial[Tpl]
    with SortOneOpt_[Tpl] { self: SqlModel2Query[Tpl] with LambdasOne =>

  protected def resolveAttrOneMan(attr: AttrOneMan, curTable: String): Unit = {
    aritiesAttr()
    sortAttrIndex += 1
    val (e, a) = ("x", s":${attr.ns}/${attr.attr}")
    attr match {
      case at: AttrOneManString     => man(attr, e, a, at.vs, resString1, resString, sortOneString(at, sortAttrIndex), curTable)
      case at: AttrOneManInt        => man(attr, e, a, at.vs, resInt1, resInt, intSorter(at, sortAttrIndex), curTable)
      case at: AttrOneManLong       => manLong(attr, e, a, at.vs, resLong1, resLong, sortOneLong(at, sortAttrIndex))
      case at: AttrOneManFloat      => man(attr, e, a, at.vs, resFloat1, resFloat, floatSorter(at, sortAttrIndex))
      case at: AttrOneManDouble     => man(attr, e, a, at.vs, resDouble1, resDouble, sortOneDouble(at, sortAttrIndex))
      case at: AttrOneManBoolean    => man(attr, e, a, at.vs, resBoolean1, resBoolean, sortOneBoolean(at, sortAttrIndex))
      case at: AttrOneManBigInt     => man(attr, e, a, at.vs, resBigInt1, resBigInt, bigIntSorter(at, sortAttrIndex))
      case at: AttrOneManBigDecimal => man(attr, e, a, at.vs, resBigDecimal1, resBigDecimal, sortOneBigDecimal(at, sortAttrIndex))
      case at: AttrOneManDate       => man(attr, e, a, at.vs, resDate1, resDate, sortOneDate(at, sortAttrIndex))
      case at: AttrOneManUUID       => man(attr, e, a, at.vs, resUUID1, resUUID, sortOneUUID(at, sortAttrIndex))
      case at: AttrOneManURI        => man(attr, e, a, at.vs, resURI1, resURI, sortOneURI(at, sortAttrIndex))
      case at: AttrOneManByte       => man(attr, e, a, at.vs, resByte1, resByte, byteSorter(at, sortAttrIndex))
      case at: AttrOneManShort      => man(attr, e, a, at.vs, resShort1, resShort, shortSorter(at, sortAttrIndex))
      case at: AttrOneManChar       => man(attr, e, a, at.vs, resChar1, resChar, sortOneChar(at, sortAttrIndex))
    }
  }

  protected def resolveAttrOneTac(attr: AttrOneTac): Unit = {
    val (e, a) = ("x", s":${attr.ns}/${attr.attr}")
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
  }

  protected def resolveAttrOneOpt(attr: AttrOneOpt): Unit = {
    aritiesAttr()
    sortAttrIndex += 1
    hasOptAttr = true // to avoid redundant None's
    val (e, a) = ("x", s":${attr.ns}/${attr.attr}")
    attr match {
      case at: AttrOneOptString     => opt(attr, e, a, at.vs, resOptString, sortOneOptString(at, sortAttrIndex), sortOneString(at, sortAttrIndex))
      case at: AttrOneOptInt        => opt(attr, e, a, at.vs, resOptInt, sortOneOptInt(at, sortAttrIndex), sortOneInt(at, sortAttrIndex))
      case at: AttrOneOptLong       => opt(attr, e, a, at.vs, resOptLong, sorterOneOptLong(at, sortAttrIndex), sorterOneLong(at, sortAttrIndex))
      case at: AttrOneOptFloat      => opt(attr, e, a, at.vs, resOptFloat, sortOneOptFloat(at, sortAttrIndex), sortOneFloat(at, sortAttrIndex))
      case at: AttrOneOptDouble     => opt(attr, e, a, at.vs, resOptDouble, sortOneOptDouble(at, sortAttrIndex), sortOneDouble(at, sortAttrIndex))
      case at: AttrOneOptBoolean    => opt(attr, e, a, at.vs, resOptBoolean, sortOneOptBoolean(at, sortAttrIndex), sortOneBoolean(at, sortAttrIndex))
      case at: AttrOneOptBigInt     => opt(attr, e, a, at.vs, resOptBigInt, sortOneOptBigInt(at, sortAttrIndex), sortOneBigInt(at, sortAttrIndex))
      case at: AttrOneOptBigDecimal => opt(attr, e, a, at.vs, resOptBigDecimal, sortOneOptBigDecimal(at, sortAttrIndex), sortOneBigDecimal(at, sortAttrIndex))
      case at: AttrOneOptDate       => opt(attr, e, a, at.vs, resOptDate, sortOneOptDate(at, sortAttrIndex), sortOneDate(at, sortAttrIndex))
      case at: AttrOneOptUUID       => opt(attr, e, a, at.vs, resOptUUID, sortOneOptUUID(at, sortAttrIndex), sortOneUUID(at, sortAttrIndex))
      case at: AttrOneOptURI        => opt(attr, e, a, at.vs, resOptURI, sortOneOptURI(at, sortAttrIndex), sortOneURI(at, sortAttrIndex))
      case at: AttrOneOptByte       => opt(attr, e, a, at.vs, resOptByte, sortOneOptByte(at, sortAttrIndex), sortOneByte(at, sortAttrIndex))
      case at: AttrOneOptShort      => opt(attr, e, a, at.vs, resOptShort, sortOneOptShort(at, sortAttrIndex), sortOneShort(at, sortAttrIndex))
      case at: AttrOneOptChar       => opt(attr, e, a, at.vs, resOptChar, sortOneOptChar(at, sortAttrIndex), sortOneChar(at, sortAttrIndex))
    }
  }

  private def sorterOneOptLong(
    at: AttrOneOptLong,
    attrIndex: Int
  ): Option[(Int, Int => (RowOLD, RowOLD) => Int)] = {
    if (at.status.contains("ref"))
      sortOneOptLongRef(at, attrIndex)
    else
      sortOneOptLong(at, attrIndex)
  }
  private def sorterOneLong(
    at: AttrOneOptLong,
    attrIndex: Int
  ): Option[(Int, Int => (RowOLD, RowOLD) => Int)] = {
    if (at.status.contains("ref"))
      sortOneLong(at, attrIndex)
    else
      sortOneLong(at, attrIndex)
  }

  protected def dummySorter(attr: Attr): Option[(Int, Int => (RowOLD, RowOLD) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        (nestedIdsCount: Int) => (a: RowOLD, b: RowOLD) => 0
      )
    }
  }
  private def addSort(sorter: Option[(Int, Int => (RowOLD, RowOLD) => Int)]): Unit = {
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
    resOLD: ResOneOLD[T],
    sorter: Option[(Int, Int => (RowOLD, RowOLD) => Int)],
    curTable: String = ""
  ): Unit = {
    addCast(res.j2s)
    addCastOLD(resOLD.j2s)
    addSort(sorter)
    val v = getVar(attr)

    val col = if (curTable.isBlank) attr.name else curTable + "." + attr.attr
//    val col = attr.ns + "." + attr.attr
    select += col
    where += ((col, s"IS NOT NULL"))

    attr.filterAttr.fold {
      expr(e, a, v, attr.op, args, resOLD)
      filterAttrVars1 = filterAttrVars1 + (a -> (e, v))
      filterAttrVars2.get(a).foreach(_(e, v))
    } { filterAttr =>
      expr2(e, a, v, getVar(filterAttr), attr.op)
    }
  }

  private def manLong(
    attr: Attr,
    e: Var,
    a: Att,
    args: Seq[Long],
    res: ResOne[Long],
    resOLD: ResOneOLD[Long],
    sorter: Option[(Int, Int => (RowOLD, RowOLD) => Int)]
  ): Unit = {
    a match {
      case ":_Generic/e"  =>
        select += e
        addCastOLD(resOLD.j2s)
        addSort(sorter)
      case ":_Generic/tx" =>
        select += txVar
        addCastOLD(resOLD.j2s)
        addSort(sorter)
      case a              =>
        man(attr, e, a, args, res, resOLD, sorter)
    }
  }

  private def tac[T: ClassTag](
    attr: Attr,
    e: Var,
    a: Att,
    args: Seq[T],
    res: ResOneOLD[T],
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
    sortOpt: Option[(Int, Int => (RowOLD, RowOLD) => Int)],
    sortMan: Option[(Int, Int => (RowOLD, RowOLD) => Int)]
  ): Unit = {
    val v = getVar(attr)
    addCastOLD(resOpt.j2s)
    attr.filterAttr.fold {
      attr.op match {
        case V     => addSort(sortOpt); optV(e, a, v)
        case Eq    => optEqual(attr, e, a, v, optArgs, resOpt.s2j, sortMan)
        case Neq   => addSort(sortMan); optNeq(e, a, v, optArgs, resOpt.tpe, resOpt.toDatalog)
        case Lt    => addSort(sortMan); optCompare(e, a, v, optArgs, "<", resOpt.s2j)
        case Gt    => addSort(sortMan); optCompare(e, a, v, optArgs, ">", resOpt.s2j)
        case Le    => addSort(sortMan); optCompare(e, a, v, optArgs, "<=", resOpt.s2j)
        case Ge    => addSort(sortMan); optCompare(e, a, v, optArgs, ">=", resOpt.s2j)
        case other => unexpectedOp(other)
      }
    } { filterAttr =>
      addSort(sortMan)
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
    e: Var,
    a: Att,
    v: Var,
    op: Op,
    args: Seq[T],
    res: ResOneOLD[T],
  ): Unit = {
    op match {
      case V          => attr(e, a, v)
      case Eq         => equal(e, a, v, args, res.s2j)
      case Neq        => neq(e, a, v, args, res.tpe, res.toDatalog)
      case Lt         => compare(e, a, v, args.head, "<", res.s2j)
      case Gt         => compare(e, a, v, args.head, ">", res.s2j)
      case Le         => compare(e, a, v, args.head, "<=", res.s2j)
      case Ge         => compare(e, a, v, args.head, ">=", res.s2j)
      case NoValue    => noValue(e, a)
      case Fn(kw, n)  => aggr(e, a, v, kw, n, res)
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
    sortMan: Option[(Int, Int => (RowOLD, RowOLD) => Int)]
  ): Unit = {
    optArgs.fold[Unit] {
      addSort(dummySorter(attr))
      select += s"(pull $e-$v [[$a :limit nil]])"
      whereOLD += s"(not [$e $a])" -> wNeqOne
      whereOLD += s"[(identity $e) $e-$v]" -> wGround
    } { vs =>
      addSort(sortMan)
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