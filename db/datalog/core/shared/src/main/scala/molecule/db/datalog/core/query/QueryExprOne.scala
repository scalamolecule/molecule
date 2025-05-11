package molecule.db.datalog.core.query

import molecule.db.base.error.ModelError
import molecule.db.core.query.QueryExpr
import scala.reflect.ClassTag
import molecule.db.core.ast._

trait QueryExprOne[Tpl]
  extends SortOneSpecial[Tpl]
    with SortOneOpt_[Tpl]
    with QueryExpr { self: Model2DatomicQuery[Tpl] & LambdasOne =>

  override protected def queryAttrOneMan(attr: AttrOneMan): Unit = {
    attrIndex += 1
    val (e, a) = (es.last, s":${attr.ent}/${attr.attr}")
    attr match {
      case at: AttrOneManID             => man(attr, e, a, at.vs, resId, sortOneID(at, attrIndex))
      case at: AttrOneManString         => man(attr, e, a, at.vs, resString, sortOneString(at, attrIndex))
      case at: AttrOneManInt            => man(attr, e, a, at.vs, resInt, intSorter(at, attrIndex))
      case at: AttrOneManLong           => man(attr, e, a, at.vs, resLong, sortOneLong(at, attrIndex))
      case at: AttrOneManFloat          => man(attr, e, a, at.vs, resFloat, floatSorter(at, attrIndex))
      case at: AttrOneManDouble         => man(attr, e, a, at.vs, resDouble, sortOneDouble(at, attrIndex))
      case at: AttrOneManBoolean        => man(attr, e, a, at.vs, resBoolean, sortOneBoolean(at, attrIndex))
      case at: AttrOneManBigInt         => man(attr, e, a, at.vs, resBigInt, bigIntSorter(at, attrIndex))
      case at: AttrOneManBigDecimal     => man(attr, e, a, at.vs, resBigDecimal, sortOneBigDecimal(at, attrIndex))
      case at: AttrOneManDate           => man(attr, e, a, at.vs, resDate, sortOneDate(at, attrIndex))
      case at: AttrOneManDuration       => man(attr, e, a, at.vs, resDuration, sortOneDuration(at, attrIndex))
      case at: AttrOneManInstant        => man(attr, e, a, at.vs, resInstant, sortOneInstant(at, attrIndex))
      case at: AttrOneManLocalDate      => man(attr, e, a, at.vs, resLocalDate, sortOneLocalDate(at, attrIndex))
      case at: AttrOneManLocalTime      => man(attr, e, a, at.vs, resLocalTime, sortOneLocalTime(at, attrIndex))
      case at: AttrOneManLocalDateTime  => man(attr, e, a, at.vs, resLocalDateTime, sortOneLocalDateTime(at, attrIndex))
      case at: AttrOneManOffsetTime     => man(attr, e, a, at.vs, resOffsetTime, sortOneOffsetTime(at, attrIndex))
      case at: AttrOneManOffsetDateTime => man(attr, e, a, at.vs, resOffsetDateTime, sortOneOffsetDateTime(at, attrIndex))
      case at: AttrOneManZonedDateTime  => man(attr, e, a, at.vs, resZonedDateTime, sortOneZonedDateTime(at, attrIndex))
      case at: AttrOneManUUID           => man(attr, e, a, at.vs, resUUID, sortOneUUID(at, attrIndex))
      case at: AttrOneManURI            => man(attr, e, a, at.vs, resURI, sortOneURI(at, attrIndex))
      case at: AttrOneManByte           => man(attr, e, a, at.vs, resByte, byteSorter(at, attrIndex))
      case at: AttrOneManShort          => man(attr, e, a, at.vs, resShort, shortSorter(at, attrIndex))
      case at: AttrOneManChar           => man(attr, e, a, at.vs, resChar, sortOneChar(at, attrIndex))
    }
  }

  override protected def queryAttrOneTac(attr: AttrOneTac): Unit = {
    val (e, a) = (es.last, s":${attr.ent}/${attr.attr}")
    if (isOptNested)
      throw ModelError(s"Tacit attributes not allowed in optional nested queries (${attr.name}_).")
    attr match {
      case at: AttrOneTacID             => tac(attr, e, a, at.vs, resId)
      case at: AttrOneTacString         => tac(attr, e, a, at.vs, resString)
      case at: AttrOneTacInt            => tac(attr, e, a, at.vs, resInt)
      case at: AttrOneTacLong           => tac(attr, e, a, at.vs, resLong)
      case at: AttrOneTacFloat          => tac(attr, e, a, at.vs, resFloat)
      case at: AttrOneTacDouble         => tac(attr, e, a, at.vs, resDouble)
      case at: AttrOneTacBoolean        => tac(attr, e, a, at.vs, resBoolean)
      case at: AttrOneTacBigInt         => tac(attr, e, a, at.vs, resBigInt)
      case at: AttrOneTacBigDecimal     => tac(attr, e, a, at.vs, resBigDecimal)
      case at: AttrOneTacDate           => tac(attr, e, a, at.vs, resDate)
      case at: AttrOneTacDuration       => tac(attr, e, a, at.vs, resDuration)
      case at: AttrOneTacInstant        => tac(attr, e, a, at.vs, resInstant)
      case at: AttrOneTacLocalDate      => tac(attr, e, a, at.vs, resLocalDate)
      case at: AttrOneTacLocalTime      => tac(attr, e, a, at.vs, resLocalTime)
      case at: AttrOneTacLocalDateTime  => tac(attr, e, a, at.vs, resLocalDateTime)
      case at: AttrOneTacOffsetTime     => tac(attr, e, a, at.vs, resOffsetTime)
      case at: AttrOneTacOffsetDateTime => tac(attr, e, a, at.vs, resOffsetDateTime)
      case at: AttrOneTacZonedDateTime  => tac(attr, e, a, at.vs, resZonedDateTime)
      case at: AttrOneTacUUID           => tac(attr, e, a, at.vs, resUUID)
      case at: AttrOneTacURI            => tac(attr, e, a, at.vs, resURI)
      case at: AttrOneTacByte           => tac(attr, e, a, at.vs, resByte)
      case at: AttrOneTacShort          => tac(attr, e, a, at.vs, resShort)
      case at: AttrOneTacChar           => tac(attr, e, a, at.vs, resChar)
    }
  }

  override protected def queryAttrOneOpt(attr: AttrOneOpt): Unit = {
    attrIndex += 1
    val (e, a) = (es.last, s":${attr.ent}/${attr.attr}")
    attr match {
      case at: AttrOneOptID             => opt(attr, e, a, at.vs, resOptId, sortOneOptID(at, attrIndex), sortOneID(at, attrIndex))
      case at: AttrOneOptString         => opt(attr, e, a, at.vs, resOptString, sortOneOptString(at, attrIndex), sortOneString(at, attrIndex))
      case at: AttrOneOptInt            => opt(attr, e, a, at.vs, resOptInt, sortOneOptInt(at, attrIndex), sortOneInt(at, attrIndex))
      case at: AttrOneOptLong           => opt(attr, e, a, at.vs, resOptLong, sorterOneOptLong(at, attrIndex), sortOneLong(at, attrIndex))
      case at: AttrOneOptFloat          => opt(attr, e, a, at.vs, resOptFloat, sortOneOptFloat(at, attrIndex), sortOneFloat(at, attrIndex))
      case at: AttrOneOptDouble         => opt(attr, e, a, at.vs, resOptDouble, sortOneOptDouble(at, attrIndex), sortOneDouble(at, attrIndex))
      case at: AttrOneOptBoolean        => opt(attr, e, a, at.vs, resOptBoolean, sortOneOptBoolean(at, attrIndex), sortOneBoolean(at, attrIndex))
      case at: AttrOneOptBigInt         => opt(attr, e, a, at.vs, resOptBigInt, sortOneOptBigInt(at, attrIndex), sortOneBigInt(at, attrIndex))
      case at: AttrOneOptBigDecimal     => opt(attr, e, a, at.vs, resOptBigDecimal, sortOneOptBigDecimal(at, attrIndex), sortOneBigDecimal(at, attrIndex))
      case at: AttrOneOptDate           => opt(attr, e, a, at.vs, resOptDate, sortOneOptDate(at, attrIndex), sortOneDate(at, attrIndex))
      case at: AttrOneOptDuration       => opt(attr, e, a, at.vs, resOptDuration, sortOneOptDuration(at, attrIndex), sortOneDuration(at, attrIndex))
      case at: AttrOneOptInstant        => opt(attr, e, a, at.vs, resOptInstant, sortOneOptInstant(at, attrIndex), sortOneInstant(at, attrIndex))
      case at: AttrOneOptLocalDate      => opt(attr, e, a, at.vs, resOptLocalDate, sortOneOptLocalDate(at, attrIndex), sortOneLocalDate(at, attrIndex))
      case at: AttrOneOptLocalTime      => opt(attr, e, a, at.vs, resOptLocalTime, sortOneOptLocalTime(at, attrIndex), sortOneLocalTime(at, attrIndex))
      case at: AttrOneOptLocalDateTime  => opt(attr, e, a, at.vs, resOptLocalDateTime, sortOneOptLocalDateTime(at, attrIndex), sortOneLocalDateTime(at, attrIndex))
      case at: AttrOneOptOffsetTime     => opt(attr, e, a, at.vs, resOptOffsetTime, sortOneOptOffsetTime(at, attrIndex), sortOneOffsetTime(at, attrIndex))
      case at: AttrOneOptOffsetDateTime => opt(attr, e, a, at.vs, resOptOffsetDateTime, sortOneOptOffsetDateTime(at, attrIndex), sortOneOffsetDateTime(at, attrIndex))
      case at: AttrOneOptZonedDateTime  => opt(attr, e, a, at.vs, resOptZonedDateTime, sortOneOptZonedDateTime(at, attrIndex), sortOneZonedDateTime(at, attrIndex))
      case at: AttrOneOptUUID           => opt(attr, e, a, at.vs, resOptUUID, sortOneOptUUID(at, attrIndex), sortOneUUID(at, attrIndex))
      case at: AttrOneOptURI            => opt(attr, e, a, at.vs, resOptURI, sortOneOptURI(at, attrIndex), sortOneURI(at, attrIndex))
      case at: AttrOneOptByte           => opt(attr, e, a, at.vs, resOptByte, sortOneOptByte(at, attrIndex), sortOneByte(at, attrIndex))
      case at: AttrOneOptShort          => opt(attr, e, a, at.vs, resOptShort, sortOneOptShort(at, attrIndex), sortOneShort(at, attrIndex))
      case at: AttrOneOptChar           => opt(attr, e, a, at.vs, resOptChar, sortOneOptChar(at, attrIndex), sortOneChar(at, attrIndex))
    }
  }

  private def dummySorter(attr: Attr): Option[(Int, Int => (Row, Row) => Int)] = {
    attr.sort.map { sort =>
      (
        sort.last.toString.toInt,
        (nestedIdsCount: Int) => (a: Row, b: Row) => 0
      )
    }
  }

  private def man[T: ClassTag](
    attr: Attr,
    e: Var,
    a: Att,
    args: Seq[T],
    resOne: ResOne[T],
    sortT: Option[(Int, Int => (Row, Row) => Int)]
  ): Unit = {
    addSort(sortT)
    addCast(resOne.j2s)
    val v = getVar(attr)
    find += v
    attr.filterAttr.fold {
      expr(attr, e, a, v, attr.op, args, resOne)
      filterAttrVars1 = filterAttrVars1 + (a -> (e -> v))
      filterAttrVars2.get(a).foreach(_(e, v))
    } { case (_, filterPath, filterAttr) =>
      expr2(e, a, v, getVar(filterAttr, filterPath), attr.op)
    }
    refConfirmed = true
  }

  private def tac[T: ClassTag](
    attr: Attr,
    e: Var,
    a: Att,
    args: Seq[T],
    resOne: ResOne[T],
  ): Unit = {
    val v = getVar(attr, cache = attr.op == V)
    attr.filterAttr.fold {
      expr(attr, e, a, v, attr.op, args, resOne)
      filterAttrVars1 = filterAttrVars1 + (a -> (e -> v))
      filterAttrVars2.get(a).foreach(_(e, v))
    } { case (_, filterPath, filterAttr) =>
      expr2(e, a, v, getVar(filterAttr, filterPath), attr.op)
    }
    refConfirmed = true
  }

  private def expr[T: ClassTag](
    attr: Attr,
    e: Var,
    a: Att,
    v: Var,
    op: Op,
    args: Seq[T],
    resOne: ResOne[T]
  ): Unit = {
    op match {
      case V            => attrV(e, a, v)
      case Eq           => equal(e, a, v, args, resOne.s2j)
      case Neq          => neq(e, a, v, args, resOne.tpe, resOne.toDatalog)
      case Lt           => compare(e, a, v, args.head, "<", resOne.s2j)
      case Gt           => compare(e, a, v, args.head, ">", resOne.s2j)
      case Le           => compare(e, a, v, args.head, "<=", resOne.s2j)
      case Ge           => compare(e, a, v, args.head, ">=", resOne.s2j)
      case NoValue      => noValue(e, a)
      case Fn(kw, n)    => aggr(attr, e, a, v, kw, n, resOne)
      case StartsWith   => stringOp(e, a, v, args.head, "starts-with?")
      case EndsWith     => stringOp(e, a, v, args.head, "ends-with?")
      case Contains     => stringOp(e, a, v, args.head, "includes?")
      case Matches      => regex(e, a, v, args.head)
      case Remainder    => remainder(e, a, v, args)
      case Even         => even(e, a, v)
      case Odd          => odd(e, a, v)
      case AttrOp.Ceil  => ceil(e, a, v)
      case AttrOp.Floor => floor(e, a, v)
      case other        => unexpectedOp(other)
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

  private def opt[T](
    attr: Attr,
    e: Var,
    a: Att,
    optArgs: Option[Seq[T]],
    resOneOpt: ResOneOpt[T],
    sortTOpt: Option[(Int, Int => (Row, Row) => Int)],
    sortT: Option[(Int, Int => (Row, Row) => Int)]
  ): Unit = {
    val v = getVar(attr)
    addCast(resOneOpt.j2s)
    attr.op match {
      case V     => addSort(sortTOpt); optAttr(e, a, v)
      case Eq    => optEqual(attr, e, a, v, optArgs, resOneOpt.s2j, sortT)
      case Neq   => addSort(sortT); optNeq(e, a, v, optArgs, resOneOpt.tpe, resOneOpt.toDatalog)
      case Lt    => addSort(sortT); optCompare(e, a, v, optArgs, "<", resOneOpt.s2j)
      case Gt    => addSort(sortT); optCompare(e, a, v, optArgs, ">", resOneOpt.s2j)
      case Le    => addSort(sortT); optCompare(e, a, v, optArgs, "<=", resOneOpt.s2j)
      case Ge    => addSort(sortT); optCompare(e, a, v, optArgs, ">=", resOneOpt.s2j)
      case other => unexpectedOp(other)
    }
  }


  // attr ----------------------------------------------------------------------

  private def attrV(e: Var, a: Att, v: Var): Unit = {
    where += s"[$e $a $v]" -> wClause
  }

  private def optAttr(e: Var, a: Att, v: Var): Unit = {
    find += s"(pull $e-$v [[$a :limit nil]]) "
    where += s"[(identity $e) $e-$v]" -> wGround
  }


  // eq ------------------------------------------------------------------------

  private def equal[T](e: Var, a: Att, v: Var, argValues: Seq[T], fromScala: Any => Any): Unit = {
    args += argValues.map(fromScala).toArray
    in += s"[$v ...]"
    where += s"[$e $a $v]" -> wClause
  }

  private def optEqual[T](
    attr: Attr,
    e: Var,
    a: Att,
    v: Var,
    optArgs: Option[Seq[T]],
    fromScala: Any => Any,
    sortMan: Option[(Int, Int => (Row, Row) => Int)],
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


  // neq -----------------------------------------------------------------------

  private def neq[T](
    e: Var, a: Att, v: Var, args: Seq[T], tpe: String, toDatalog: T => String
  ): Unit = {
    where += s"[$e $a $v]" -> wClause
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

  private def optNeq[T](
    e: Var, a: Att, v: Var, optArgs: Option[Seq[T]], tpe: String, toDatalog: T => String
  ): Unit = {
    find += v
    where += s"[$e $a $v]" -> wClause
    if (optArgs.isDefined && optArgs.get.nonEmpty) {
      neq(e, a, v, optArgs.get, tpe, toDatalog)
    }
  }


  // compare -------------------------------------------------------------------

  private def compare[T](
    e: Var, a: Att, v: Var, arg: T, op: String, fromScala: Any => Any
  ): Unit = {
    val v1 = v + 1
    in += v1
    where += s"[$e $a $v]" -> wClause
    where += s"[($op $v $v1)]" -> wNeqOne
    args += fromScala(arg).asInstanceOf[AnyRef]
  }

  private def optCompare[T](
    e: Var, a: Att, v: Var, optArgs: Option[Seq[T]], op: String, fromScala: Any => Any
  ): Unit = {
    optArgs.fold[Unit] {
      find += s"$v-nil"
      where += s"[(ground nil) $v-nil]" -> wGround
    } { vs =>
      find += v
      compare(e, a, v, vs.head, op, fromScala)
    }
  }


  // no value ------------------------------------------------------------------

  private def noValue(e: Var, a: Att): Unit = {
    where += s"(not [$e $a])" -> wNeqOne
  }


  // string filters ------------------------------------------------------------

  private def stringOp[T](e: Var, a: Att, v: Var, arg: T, predicate: String): Unit = {
    val v1 = v + 1
    in += v1
    where += s"[$e $a $v]" -> wClause
    where += s"[(clojure.string/$predicate $v $v1)]" -> wNeqOne
    args += arg.asInstanceOf[AnyRef]
  }

  private def regex[T](e: Var, a: Att, v: Var, regex: T): Unit = {
    where += s"[$e $a $v]" -> wClause
    where += s"""[(re-find (re-pattern "$regex") $v)]""" -> wNeqOne
  }


  // number filters ------------------------------------------------------------

  private def remainder[T](e: Var, a: Att, v: Var, args: Seq[T]): Unit = {
    where += s"[$e $a $v]" -> wClause
    where += s"""[(rem $v ${args.head}) $v-rem]""" -> wNeqOne
    where += s"""[(= $v-rem ${args(1)})]""" -> wNeqOne
  }
  private def even(e: Var, a: Att, v: Var): Unit = {
    where += s"[$e $a $v]" -> wClause
    where += s"""[(even? $v)]""" -> wNeqOne
  }
  private def odd(e: Var, a: Att, v: Var): Unit = {
    where += s"[$e $a $v]" -> wClause
    where += s"""[(odd? $v)]""" -> wNeqOne
  }

  protected def ceil(e: Var, a: Att, v: Var): Unit = {
    val v0 = v + "0"
    where += s"[$e $a $v0]" -> wClause
    where += s"""[(Math/ceil $v0) $v]""" -> wNeqOne
  }

  protected def floor(e: Var, a: Att, v: Var): Unit = {
    val v0 = v + "0"
    where += s"[$e $a $v0]" -> wClause
    where += s"""[(Math/floor $v0) $v]""" -> wNeqOne
  }


  // aggregation ---------------------------------------------------------------

  private def aggr[T: ClassTag](
    attr: Attr, e: Var, a: Att, v: Var, fn: String, optN: Option[Int], resOne: ResOne[T]
  ): Unit = {
    checkAggrOne()
    lazy val n = optN.getOrElse(0)
    // Replace find/casting with aggregate function/cast
    find -= v
    fn match {
      case "count" =>
        widh += e
        find += s"(count $v)"
        replaceCast(toInt)

      case "countDistinct" =>
        widh += e
        find += s"(count-distinct $v)"
        replaceCast(toInt)

      case "sum" =>
        widh += e
        find += s"(sum $v)"

      case "min" =>
        find += s"(min $v)"

      case "max" =>
        find += s"(max $v)"

      case "sample" =>
        find += s"(sample 1 $v)"
        replaceCast(resOne.seq2t)

      case "median" =>
        widh += e
        find += s"(median $v)"
        replaceSort(sortMedian(attr, attrIndex))
        replaceCast(any2double)

      // OBS! Datomic rounds down to nearest whole number
      // when calculating the median for multiple numbers instead of
      // following the semantic described on wikipedia:
      // https://en.wikipedia.org/wiki/Median
      // See also
      // https://forum.datomic.com/t/unexpected-median-rounding/517
      // If we wanted the rounded version we can do this instead if desired:
      //        widh += e
      //        find += s"(distinct $v)"
      //        val medianConverter: AnyRef => Double = {
      //          (v: AnyRef) => getMedian(v.asInstanceOf[jSet[_]].toArray.map(_.toString.toDouble).toSet)
      //        }
      //        replaceCast(medianConverter.asInstanceOf[AnyRef => AnyRef])

      case "avg" =>
        widh += e
        find += s"(avg $v)"

      case "variance" =>
        widh += e
        find += s"(variance $v)"

      case "stddev" =>
        widh += e
        find += s"(stddev $v)"

      case "mins" =>
        find += s"(min $n $v)"
        replaceCast(resOne.vector2set)

      case "maxs" =>
        find += s"(max $n $v)"
        replaceCast(resOne.vector2set)

      case "samples" =>
        find += s"(sample $n $v)"
        replaceCast(resOne.vector2set)

      case "distinct" =>
        find += s"(distinct $v)"
        replaceCast(resOne.set2set)

      case other => unexpectedKw(other)
    }
    where += s"[$e $a $v]" -> wClause
  }


  // filter attribute filters --------------------------------------------------

  private def equal2(e: Var, a: Att, v: Var, w: Var): Unit = {
    where += s"[$e $a $w]" -> wClause
    where += s"[(identity $w) $v]" -> wGround
  }

  private def neq2(e: Var, a: Att, v: Var, w: Var): Unit = {
    where += s"[$e $a $v]" -> wClause
    where += s"[(!= $v $w)]" -> wNeqOne
  }

  private def compare2(e: Var, a: Att, v: Var, w: Var, op: String): Unit = {
    where += s"[$e $a $v]" -> wClause
    where += s"[($op $v $w)]" -> wNeqOne
  }
}