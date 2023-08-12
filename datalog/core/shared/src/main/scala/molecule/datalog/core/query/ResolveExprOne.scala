package molecule.datalog.core.query

import java.util.{Set => jSet}
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
      case at: AttrOneManLong       => man(attr, e, a, at.vs, resLong, sortOneLong(at, attrIndex))
      case at: AttrOneManFloat      => man(attr, e, a, at.vs, resFloat, floatSorter(at, attrIndex))
      case at: AttrOneManDouble     => man(attr, e, a, at.vs, resDouble, sortOneDoublePossiblyMedianSet(at, attrIndex))
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
      case at: AttrOneOptString     => opt(attr, e, a, at.vs, resOptString, sortOneOptString(at, attrIndex), sortOneString(at, attrIndex))
      case at: AttrOneOptInt        => opt(attr, e, a, at.vs, resOptInt, sortOneOptInt(at, attrIndex), sortOneInt(at, attrIndex))
      case at: AttrOneOptLong       => opt(attr, e, a, at.vs, resOptLong, sorterOneOptLong(at, attrIndex), sorterOneLong(at, attrIndex))
      case at: AttrOneOptFloat      => opt(attr, e, a, at.vs, resOptFloat, sortOneOptFloat(at, attrIndex), sortOneFloat(at, attrIndex))
      case at: AttrOneOptDouble     => opt(attr, e, a, at.vs, resOptDouble, sortOneOptDouble(at, attrIndex), sortOneDoublePossiblyMedianSet(at, attrIndex))
      case at: AttrOneOptBoolean    => opt(attr, e, a, at.vs, resOptBoolean, sortOneOptBoolean(at, attrIndex), sortOneBoolean(at, attrIndex))
      case at: AttrOneOptBigInt     => opt(attr, e, a, at.vs, resOptBigInt, sortOneOptBigInt(at, attrIndex), sortOneBigInt(at, attrIndex))
      case at: AttrOneOptBigDecimal => opt(attr, e, a, at.vs, resOptBigDecimal, sortOneOptBigDecimal(at, attrIndex), sortOneBigDecimal(at, attrIndex))
      case at: AttrOneOptDate       => opt(attr, e, a, at.vs, resOptDate, sortOneOptDate(at, attrIndex), sortOneDate(at, attrIndex))
      case at: AttrOneOptUUID       => opt(attr, e, a, at.vs, resOptUUID, sortOneOptUUID(at, attrIndex), sortOneUUID(at, attrIndex))
      case at: AttrOneOptURI        => opt(attr, e, a, at.vs, resOptURI, sortOneOptURI(at, attrIndex), sortOneURI(at, attrIndex))
      case at: AttrOneOptByte       => opt(attr, e, a, at.vs, resOptByte, sortOneOptByte(at, attrIndex), sortOneByte(at, attrIndex))
      case at: AttrOneOptShort      => opt(attr, e, a, at.vs, resOptShort, sortOneOptShort(at, attrIndex), sortOneShort(at, attrIndex))
      case at: AttrOneOptChar       => opt(attr, e, a, at.vs, resOptChar, sortOneOptChar(at, attrIndex), sortOneChar(at, attrIndex))
    }
    es
  }

  private def sorterOneOptLong(
    at: AttrOneOptLong,
    attrIndex: Int
  ): Option[(Int, Int => (Row, Row) => Int)] = {
    if (at.refNs.isDefined)
      sortOneOptLongRef(at, attrIndex)
    else
      sortOneOptLong(at, attrIndex)
  }
  private def sorterOneLong(
    at: AttrOneOptLong,
    attrIndex: Int
  ): Option[(Int, Int => (Row, Row) => Int)] = {
    sortOneLong(at, attrIndex)
  }

  private def dummySorter(attr: Attr): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        (nestedIdsCount: Int) => (a: Row, b: Row) => 0
      )
    }
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
    addCast(res.j2s)
    addSort(sorter)
    val v = getVar(attr)
    find += v
    attr.filterAttr.fold {
      expr(e, a, v, attr.op, args, res)
      filterAttrVars1 = filterAttrVars1 + (a -> (e, v))
      filterAttrVars2.get(a).foreach(_(e, v))
    } { filterAttr =>
      expr2(e, a, v, getVar(filterAttr), attr.op)
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
    sortOpt: Option[(Int, Int => (Row, Row) => Int)],
    sortMan: Option[(Int, Int => (Row, Row) => Int)]
  ): Unit = {
    val v = getVar(attr)
    addCast(resOpt.j2s)
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
    res: ResOne[T],
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
    where += s"[$e $a $v$tx]" -> wClause
    where += s"[(clojure.string/$predicate $v $v1)]" -> wNeqOne
    args += arg.asInstanceOf[AnyRef]
  }
  private def regex[T](e: Var, a: Att, v: Var, regex: T): Unit = {
    where += s"[$e $a $v$tx]" -> wClause
    where += s"""[(re-find (re-pattern "$regex") $v)]""" -> wNeqOne
  }

  private def remainder[T](e: Var, a: Att, v: Var, args: Seq[T]): Unit = {
    where += s"[$e $a $v$tx]" -> wClause
    where += s"""[(rem $v ${args.head}) $v-rem]""" -> wNeqOne
    where += s"""[(= $v-rem ${args(1)})]""" -> wNeqOne
  }
  private def even(e: Var, a: Att, v: Var): Unit = {
    where += s"[$e $a $v$tx]" -> wClause
    where += s"""[(even? $v)]""" -> wNeqOne
  }
  private def odd(e: Var, a: Att, v: Var): Unit = {
    where += s"[$e $a $v$tx]" -> wClause
    where += s"""[(odd? $v)]""" -> wNeqOne
  }


  private def string[T: ClassTag](e: Var, a: Att, v: Var, args: Seq[T], op: String): Unit = {
    find -= v
    where += s"[$e $a $v$tx]" -> wClause
    op match {
      case "take" =>
        find += s"$v-1"
        where += s"[(max 0 ${args.head}) $v-n]" -> wNeqOne
        where += s"[(count $v) $v-length]" -> wNeqOne
        where += s"[(> $v-n 0)]" -> wNeqOne
        where += s"[(min $v-n $v-length) $v-end]" -> wNeqOne
        where += s"[(subs $v 0 $v-end) $v-1]" -> wNeqOne

      case "takeRight" =>
        find += s"$v-1"
        where += s"[(max 0 ${args.head}) $v-n]" -> wNeqOne
        where += s"[(count $v) $v-length]" -> wNeqOne
        where += s"[(> $v-n 0)]" -> wNeqOne
        where += s"[(- $v-length $v-n) $v-back]" -> wNeqOne
        where += s"[(max 0 $v-back) $v-begin]" -> wNeqOne
        where += s"[(subs $v $v-begin $v-length) $v-1]" -> wNeqOne

      case "drop" =>
        find += s"$v-1"
        where += s"[(max 0 ${args.head}) $v-n]" -> wNeqOne
        where += s"[(count $v) $v-length]" -> wNeqOne
        where += s"[(< $v-n $v-length)]" -> wNeqOne
        where += s"[(min $v-n $v-length) $v-begin]" -> wNeqOne
        where += s"[(subs $v $v-begin $v-length) $v-1]" -> wNeqOne

      case "dropRight" =>
        find += s"$v-1"
        where += s"[(max 0 ${args.head}) $v-n]" -> wNeqOne
        where += s"[(count $v) $v-length]" -> wNeqOne
        where += s"[(< $v-n $v-length)]" -> wNeqOne
        where += s"[(- $v-length $v-n) $v-end]" -> wNeqOne
        where += s"[(subs $v 0 $v-end) $v-1]" -> wNeqOne

      case "slice" =>
        find += s"$v-1"
        where += s"[(count $v) $v-length]" -> wNeqOne
        where += s"[(max 0 ${args.head}) $v-from]" -> wNeqOne
        where += s"[(min $v-length (max 0 ${args(1)})) $v-until]" -> wNeqOne
        where += s"[(< $v-from $v-until)]" -> wNeqOne
        where += s"[(subs $v $v-from $v-until) $v-1]" -> wNeqOne
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

      case "sum" =>
        find += s"(sum $v)"

      case "median" =>
        // OBS! Datomic rounds down to nearest whole number
        // when calculating the median for multiple numbers instead of
        // following the semantic described on wikipedia:
        // https://en.wikipedia.org/wiki/Median
        // See also
        // https://forum.datomic.com/t/unexpected-median-rounding/517
        // So we calculate the correct median value manually instead:
        find += s"(distinct $v)"
        val medianConverter: AnyRef => Double = {
          (v: AnyRef) => getMedian(v.asInstanceOf[jSet[_]].toArray.map(_.toString.toDouble).toSet)
        }
        replaceCast(medianConverter.asInstanceOf[AnyRef => AnyRef])

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
  private def equal2(e: Var, a: Att, v: Var, w: Var): Unit = {
    where += s"[$e $a $w$tx]" -> wClause
    where += s"[(identity $w) $v]" -> wGround
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
  private def neq2(e: Var, a: Att, v: Var, w: Var): Unit = {
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
    attr: Attr,
    e: Var,
    a: Att,
    v: Var,
    optArgs: Option[Seq[T]],
    fromScala: Any => Any,
    sortMan: Option[(Int, Int => (Row, Row) => Int)]
  ): Unit = {
    optArgs.fold[Unit] {
      addSort(dummySorter(attr))
      find += s"(pull $e-$v [[$a :limit nil]])"
      where += s"(not [$e $a])" -> wNeqOne
      where += s"[(identity $e) $e-$v]" -> wGround
    } { vs =>
      addSort(sortMan)
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
    find += v
    where += s"[$e $a $v$tx]" -> wClause
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
    find += v
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