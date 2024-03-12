package molecule.datalog.core.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import scala.reflect.ClassTag
import java.util.{Date, UUID, Iterator => jIterator, List => jList, Map => jMap, Set => jSet}
import molecule.core.util.JavaConversions

trait ResolveExprSeq[Tpl] extends JavaConversions { self: Model2DatomicQuery[Tpl] with LambdasArr =>

  protected def resolveAttrSeqMan(es: List[Var], attr: AttrSeqMan): List[Var] = {
    aritiesAttr()
    attrIndex += 1
    val e = es.last
    attr match {
      case att: AttrSeqManID             => man(attr, e, att.vs, resArrId)
      case att: AttrSeqManString         => man(attr, e, att.vs, resArrString)
      case att: AttrSeqManInt            => man(attr, e, att.vs, resArrInt)
      case att: AttrSeqManLong           => man(attr, e, att.vs, resArrLong)
      case att: AttrSeqManFloat          => man(attr, e, att.vs, resArrFloat)
      case att: AttrSeqManDouble         => man(attr, e, att.vs, resArrDouble)
      case att: AttrSeqManBoolean        => man(attr, e, att.vs, resArrBoolean)
      case att: AttrSeqManBigInt         => man(attr, e, att.vs, resArrBigInt)
      case att: AttrSeqManBigDecimal     => man(attr, e, att.vs, resArrBigDecimal)
      case att: AttrSeqManDate           => man(attr, e, att.vs, resArrDate)
      case att: AttrSeqManDuration       => man(attr, e, att.vs, resArrDuration)
      case att: AttrSeqManInstant        => man(attr, e, att.vs, resArrInstant)
      case att: AttrSeqManLocalDate      => man(attr, e, att.vs, resArrLocalDate)
      case att: AttrSeqManLocalTime      => man(attr, e, att.vs, resArrLocalTime)
      case att: AttrSeqManLocalDateTime  => man(attr, e, att.vs, resArrLocalDateTime)
      case att: AttrSeqManOffsetTime     => man(attr, e, att.vs, resArrOffsetTime)
      case att: AttrSeqManOffsetDateTime => man(attr, e, att.vs, resArrOffsetDateTime)
      case att: AttrSeqManZonedDateTime  => man(attr, e, att.vs, resArrZonedDateTime)
      case att: AttrSeqManUUID           => man(attr, e, att.vs, resArrUUID)
      case att: AttrSeqManURI            => man(attr, e, att.vs, resArrURI)
      case att: AttrSeqManByte           => manByteArray(attr, e) // Byte Array only semantics
      case att: AttrSeqManShort          => man(attr, e, att.vs, resArrShort)
      case att: AttrSeqManChar           => man(attr, e, att.vs, resArrChar)
    }
    es
  }

  protected def resolveAttrSeqTac(es: List[Var], attr: AttrSeqTac): List[Var] = {
    val e = es.last
    attr match {
      case att: AttrSeqTacID             => tac(attr, e, att.vs, resArrId)
      case att: AttrSeqTacString         => tac(attr, e, att.vs, resArrString)
      case att: AttrSeqTacInt            => tac(attr, e, att.vs, resArrInt)
      case att: AttrSeqTacLong           => tac(attr, e, att.vs, resArrLong)
      case att: AttrSeqTacFloat          => tac(attr, e, att.vs, resArrFloat)
      case att: AttrSeqTacDouble         => tac(attr, e, att.vs, resArrDouble)
      case att: AttrSeqTacBoolean        => tac(attr, e, att.vs, resArrBoolean)
      case att: AttrSeqTacBigInt         => tac(attr, e, att.vs, resArrBigInt)
      case att: AttrSeqTacBigDecimal     => tac(attr, e, att.vs, resArrBigDecimal)
      case att: AttrSeqTacDate           => tac(attr, e, att.vs, resArrDate)
      case att: AttrSeqTacDuration       => tac(attr, e, att.vs, resArrDuration)
      case att: AttrSeqTacInstant        => tac(attr, e, att.vs, resArrInstant)
      case att: AttrSeqTacLocalDate      => tac(attr, e, att.vs, resArrLocalDate)
      case att: AttrSeqTacLocalTime      => tac(attr, e, att.vs, resArrLocalTime)
      case att: AttrSeqTacLocalDateTime  => tac(attr, e, att.vs, resArrLocalDateTime)
      case att: AttrSeqTacOffsetTime     => tac(attr, e, att.vs, resArrOffsetTime)
      case att: AttrSeqTacOffsetDateTime => tac(attr, e, att.vs, resArrOffsetDateTime)
      case att: AttrSeqTacZonedDateTime  => tac(attr, e, att.vs, resArrZonedDateTime)
      case att: AttrSeqTacUUID           => tac(attr, e, att.vs, resArrUUID)
      case att: AttrSeqTacURI            => tac(attr, e, att.vs, resArrURI)
      case att: AttrSeqTacByte           => tacByteArray(attr, e) // Byte Array only semantics
      case att: AttrSeqTacShort          => tac(attr, e, att.vs, resArrShort)
      case att: AttrSeqTacChar           => tac(attr, e, att.vs, resArrChar)
    }
    es
  }

  protected def resolveAttrSeqOpt(es: List[Var], attr: AttrSeqOpt): List[Var] = {
    aritiesAttr()
    attrIndex += 1
    hasOptAttr = true // to avoid redundant None's
    val e = es.last
    attr match {
      case att: AttrSeqOptID             => opt(attr, e, att.op, att.vs, resOptArrId, resArrId)
      case att: AttrSeqOptString         => opt(attr, e, att.op, att.vs, resOptArrString, resArrString)
      case att: AttrSeqOptInt            => opt(attr, e, att.op, att.vs, resOptArrInt, resArrInt)
      case att: AttrSeqOptLong           => opt(attr, e, att.op, att.vs, resOptArrLong, resArrLong)
      case att: AttrSeqOptFloat          => opt(attr, e, att.op, att.vs, resOptArrFloat, resArrFloat)
      case att: AttrSeqOptDouble         => opt(attr, e, att.op, att.vs, resOptArrDouble, resArrDouble)
      case att: AttrSeqOptBoolean        => opt(attr, e, att.op, att.vs, resOptArrBoolean, resArrBoolean)
      case att: AttrSeqOptBigInt         => opt(attr, e, att.op, att.vs, resOptArrBigInt, resArrBigInt)
      case att: AttrSeqOptBigDecimal     => opt(attr, e, att.op, att.vs, resOptArrBigDecimal, resArrBigDecimal)
      case att: AttrSeqOptDate           => opt(attr, e, att.op, att.vs, resOptArrDate, resArrDate)
      case att: AttrSeqOptDuration       => opt(attr, e, att.op, att.vs, resOptArrDuration, resArrDuration)
      case att: AttrSeqOptInstant        => opt(attr, e, att.op, att.vs, resOptArrInstant, resArrInstant)
      case att: AttrSeqOptLocalDate      => opt(attr, e, att.op, att.vs, resOptArrLocalDate, resArrLocalDate)
      case att: AttrSeqOptLocalTime      => opt(attr, e, att.op, att.vs, resOptArrLocalTime, resArrLocalTime)
      case att: AttrSeqOptLocalDateTime  => opt(attr, e, att.op, att.vs, resOptArrLocalDateTime, resArrLocalDateTime)
      case att: AttrSeqOptOffsetTime     => opt(attr, e, att.op, att.vs, resOptArrOffsetTime, resArrOffsetTime)
      case att: AttrSeqOptOffsetDateTime => opt(attr, e, att.op, att.vs, resOptArrOffsetDateTime, resArrOffsetDateTime)
      case att: AttrSeqOptZonedDateTime  => opt(attr, e, att.op, att.vs, resOptArrZonedDateTime, resArrZonedDateTime)
      case att: AttrSeqOptUUID           => opt(attr, e, att.op, att.vs, resOptArrUUID, resArrUUID)
      case att: AttrSeqOptURI            => opt(attr, e, att.op, att.vs, resOptArrURI, resArrURI)
      case att: AttrSeqOptByte           => optByteArray(attr, e, resOptArrByte) // Byte Array only semantics
      case att: AttrSeqOptShort          => opt(attr, e, att.op, att.vs, resOptArrShort, resArrShort)
      case att: AttrSeqOptChar           => opt(attr, e, att.op, att.vs, resOptArrChar, resArrChar)
    }
    es
  }

  private def vars(attr: Attr, v: String) = {
    val (ns, at) = (attr.ns, attr.attr)
    (
      s":$ns/$at", s":$ns.$at/i_", s":$ns.$at/v_",
      v + "_i", v + "_v",
      v + 1, v + 2, v + 3, v + 4, v + 5, v + 6,
      v + "_pair", v + "_pair2",
      v + "_array",
    )
  }
  private def nsAttr(attr: Attr): String = s":${attr.ns}/${attr.attr}"

  private def man[T: ClassTag](
    attr: Attr, e: Var,
    arrays: Seq[Seq[T]],
    resArr: ResArr[T],
  ): Unit = {
    val v = vv
    val a = nsAttr(attr)
    //    find += s"(distinct ${v}_pair)" // position index -> value
    //    addCast(res.seq2array)
    attr.filterAttr.fold {
      val pathAttr = varPath :+ attr.cleanAttr
      if (filterAttrVars.contains(pathAttr) && attr.op != V) {
        // Runtime check needed since we can't type infer it
        throw ModelError(s"Cardinality-set filter attributes not allowed to " +
          s"do additional filtering. Found:\n  " + attr)
      }
      expr(attr, e, v, attr.op, arrays, resArr)
      filterAttrVars1 = filterAttrVars1 + (a -> (e, v))
      filterAttrVars2.get(a).foreach(_(e, v))
    } { case (dir, filterPath, filterAttr) =>
      expr2(attr, e, v, attr.op, s":${filterAttr.ns}/${filterAttr.attr}")
    }
    refConfirmed = true
  }

  private def tac[T: ClassTag](
    attr: Attr, e: Var,
    arrays: Seq[Seq[T]],
    resArr: ResArr[T],
  ): Unit = {
    val v = vv
    val a = nsAttr(attr)
    attr.filterAttr.fold {
      expr(attr, e, v, attr.op, arrays, resArr)
      filterAttrVars1 = filterAttrVars1 + (a -> (e, v))
      filterAttrVars2.get(a).foreach(_(e, v))
    } { case (dir, filterPath, filterAttr) =>
      expr2(attr, e, v, attr.op, s":${filterAttr.ns}/${filterAttr.attr}")
    }
    refConfirmed = true
  }

  private def expr[T: ClassTag](
    attr: Attr, e: Var, v: Var, op: Op,
    arrays: Seq[Seq[T]],
    resArr: ResArr[T],
  ): Unit = {
    op match {
      case V         => attrib(attr, e, v, resArr)
      case Eq        => equal(attr, e, v, arrays, resArr)
      case Neq       => neq(attr, e, v, arrays, resArr.s2j)
      case Has       => has(attr, e, v, arrays, resArr.tpe, resArr.toDatalog)
      case HasNo     => hasNo(attr, e, v, arrays, resArr.tpe, resArr.toDatalog)
      case NoValue   => noValue(attr, e)
      case Fn(kw, n) =>
        if (isRefAttr)
          throw ModelError("Aggregating Sets of ref ids not supported.")
        else
          aggr(attr, e, v, kw, n, resArr)
      case other     => unexpectedOp(other)
    }
  }

  private def expr2(
    attr: Attr, e: Var, v: Var, op: Op, filterAttr: String
  ): Unit = {
    op match {
      case Eq    => equal2(attr, e, v, filterAttr)
      case Neq   => neq2(attr, e, v, filterAttr)
      case Has   => has2(attr, e, v, filterAttr)
      case HasNo => hasNo2(attr, e, v, filterAttr)
      case other => unexpectedOp(other)
    }
  }

  private def opt[T: ClassTag](
    attr: Attr, e: Var, op: Op,
    optArrays: Option[Seq[Seq[T]]],
    resArrOpt: ResArrOpt[T],
    resArr: ResArr[T],
  ): Unit = {
    val v = vv
    addCast(resArrOpt.j2s)
    op match {
      case V     => optAttr(attr, e, v, resArrOpt)
      case Eq    => optEqual(attr, e, v, optArrays, resArrOpt, resArr)
      case Neq   => optNeq(attr, e, v, optArrays, resArrOpt.s2j)
      case Has   => optHas(attr, e, v, optArrays, resArrOpt.tpe, resArrOpt, resArrOpt.toDatalog)
      case HasNo => optHasNo(attr, e, v, optArrays, resArrOpt.tpe, resArrOpt.toDatalog)
      case other => unexpectedOp(other)
    }
  }


  private def manByteArray(attr: Attr, e: Var): Unit = {
    val v = vv
    val a = nsAttr(attr)
    find += v
    //    addCast(identity)
    addCast { (array: AnyRef) =>
      println(array)
      println(array.asInstanceOf[Array[_]].toList)



      array.asInstanceOf[Array[Byte]].toList
    }

    attr.filterAttr.fold {
      attr.op match {
        case V => where += s"[$e $a $v]" -> wClause
        case _ => throw ModelError(
          s"Byte arrays can only be retrieved as-is. Filters not allowed.")
      }
    } { _ =>
      throw ModelError(s"Filter attributes not allowed with byte arrays.")
    }
    refConfirmed = true
  }

  private def tacByteArray(attr: Attr, e: Var): Unit = {
    val v = vv
    val a = nsAttr(attr)
    attr.filterAttr.fold {
      attr.op match {
        case V => where += s"[$e $a $v]" -> wClause
        case _ => throw ModelError(
          s"Byte arrays can only be retrieved as-is. Filters not allowed.")
      }
    } { _ =>
      throw ModelError(s"Filter attributes not allowed with byte arrays.")
    }
    refConfirmed = true
  }

  private def optByteArray(attr: Attr, e: Var, resArrOpt: ResArrOpt[Byte]): Unit = {
    val v = vv
    val a = nsAttr(attr)
    find += s"(pull $e-$v [[$a :limit nil]]) "
    where += s"[(identity $e) $e-$v]" -> wGround
    addCast(resArrOpt.optAttr2s)
  }

  // attr ----------------------------------------------------------------------

  private def attrib[T](attr: Attr, e: Var, v: Var, resArr: ResArr[T]): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair, pair2, array) = vars(attr, v)
    find += s"(distinct $pair)" // position index -> value
    where += s"[$e $a $v]" -> wClause
    where += s"[$v $ai $i_]" -> wClause
    where += s"[$v $av $v_]" -> wClause
    where += s"[(vector $i_ $v_) $pair]" -> wClause
    addCast(resArr.pairs2array)

  }

  private def optAttr[T](
    attr: Attr, e: Var, v: Var, resArrOpt: ResArrOpt[T]
  ): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair, pair2, array) = vars(attr, v)
    if (refConfirmed) {
      find += v6
      where +=
        s"""[(datomic.api/q
           |          "[:find (pull $e [{($a :limit nil) [$ai $av]}])
           |            :in $$ $e]" $$ $e) [[$v1]]]""".stripMargin -> wClause
      where += s"[(if (nil? $v1) {$a []} $v1) $v2]" -> wClause
      where += s"[($a $v2) $v3]" -> wClause
      where += s"[(map vals $v3) $v4]" -> wClause
      where += s"[(sort-by first $v4) $v5]" -> wClause
      where += s"[(map second $v5) $v6]" -> wClause

    } else {
      val List(e0, card, refAttr, refId) = varPath.takeRight(4)
      val refDatom                       = s"[$e0 $refAttr $refId]"
      if (where.last == refDatom -> wClause) {
        // cancel previous ref Datom since we will pull it instead
        where.remove(where.size - 1)
        varPath = varPath.dropRight(3)
      }
      val e = varPath.last
      if (card == "one") {
        find += s"(distinct $v4)"
        where +=
          s"""[(datomic.api/q
             |          "[:find (pull $e [{$refAttr [$a]}] :limit nil)
             |            :in $$ $e]" $$ $e) [[$v1]]]""".stripMargin -> wClause
        where += s"[(if (nil? $v1) {$refAttr {$a []}} $v1) $v2]" -> wClause
        where += s"[($refAttr $v2) $v3]" -> wClause
        where += s"[($a $v3) $v4]" -> wClause
        where += s"[(map vals $v4) $v5]" -> wClause

      } else {
        find += s"(distinct $v6)"
        where +=
          s"""[(datomic.api/q
             |          "[:find (pull $e [{($a :limit nil) [$ai $av]}])
             |            :in $$ $e]" $$ $e) [[$v1]]]""".stripMargin -> wClause
        where += s"[(if (nil? $v1) {$a [{$a []}]} $v1) $v2]" -> wClause
        where += s"[($a $v2) $v3]" -> wClause
        where += s"[(first $v3) $v4]" -> wClause
        where += s"[($a $v4) $v5]" -> wClause
        where += s"[(map vals $v4) $v5]" -> wClause
      }
    }
    replaceCast(resArrOpt.optAttr2s)
  }


  // eq ------------------------------------------------------------------------

  private def equal[T](
    attr: Attr, e: Var, v: Var,
    arrays: Seq[Seq[T]],
    resArr: ResArr[T],
  ): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair, pair2, array) = vars(attr, v)
    find += v3
    in += s"[$array ...]"
    where +=
      s"""[(datomic.api/q
         |          "[:find (distinct $pair)
         |            :in $$ $e
         |            :where [$e $a $v]
         |                   [$v $ai $i_]
         |                   [$v $av $v_]
         |                   [(vector $i_ $v_) $pair]]" $$ $e) [[$v1]]]""".stripMargin -> wClause
    where += s"[(sort-by first $v1) $v2]" -> wClause
    where += s"[(map second $v2) $v3]" -> wClause
    where += s"[(= $array $v3)]" -> wClause
    args += arrays.map(array => array.toSeq.map(resArr.s2j).asJava).asJava
    addCast(resArr.seq2array)
  }

  private def optEqual[T](
    attr: Attr, e: Var, v: Var,
    optArrays: Option[Seq[Seq[T]]],
    resArrOpt: ResArrOpt[T],
    resArr: ResArr[T],
  ): Unit = {
    optArrays.fold[Unit] {
      none(attr, e, v, resArrOpt)
    } { arrays =>
      //      find += s"(distinct $v)"
      equal(attr, e, v, arrays, resArr)
    }
  }


  // neq -----------------------------------------------------------------------

  private def neq[T](
    attr: Attr, e: Var, v: Var, arrays: Seq[Seq[T]], fromScala: Any => Any
  ): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair, pair2, array) = vars(attr, v)

    find += v3
    where += s"[$e $a $v]" -> wClause
    if (arrays.nonEmpty && arrays.flatten.nonEmpty) {
      val blacklist               = v + "-blacklist"
      val blacklisted             = v + "-blacklisted"
      val (set, set1, v1, v2, e1) = (v + "-set", v + "-set1", v + 1, v + 2, e + 1)

      // Pre-query
      preIn += s"[$set ...]"
      preWhere +=
        s"""[(datomic.api/q
           |          "[:find (distinct $v1)
           |            :in $$ $e1
           |            :where [$e1 $a $v1]]" $$ $e) [[$v2]]]""".stripMargin -> wClause
      preWhere += s"[(into #{} $set) $set1]" -> wClause
      preWhere += s"[(= $v2 $set1)]" -> wClause
      preArgs += arrays.map(set => set.map(fromScala)).asJava

      // Main query
      inPost += blacklist
      wherePost += s"[(contains? $blacklist $firstId) $blacklisted]" -> wClause
      wherePost += s"[(not $blacklisted)]" -> wClause
    }
  }

  private def optNeq[T](
    attr: Attr, e: Var, v: Var,
    optArrays: Option[Seq[Seq[T]]],
    fromScala: Any => Any
  ): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair, pair2, array) = vars(attr, v)
    find += s"(distinct $v)"
    optArrays.fold[Unit] {
      where += s"[$e $a $v]" -> wClause
    } { sets =>
      neq(attr, e, v, sets, fromScala)
    }
  }


  // has -----------------------------------------------------------------------

  private def has[T: ClassTag](
    attr: Attr, e: Var, v: Var,
    arrays: Seq[Seq[T]], tpe: String, toDatalog: T => String
  ): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair, pair2, array) = vars(attr, v)
    where += s"[$e $a $v]" -> wClause
    if (arrays.nonEmpty && arrays.flatten.nonEmpty) {
      where += s"(rule$v $e)" -> wClause
      rules ++= mkRules(attr, e, v, arrays, tpe, toDatalog)
    } else {
      where += s"[(ground nil) $v]" -> wGround
    }
  }

  private def optHas[T: ClassTag](
    attr: Attr, e: Var, v: Var,
    optArrays: Option[Seq[Seq[T]]],
    tpe: String,
    resArrOpt: ResArrOpt[T],
    toDatalog: T => String
  ): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair, pair2, array) = vars(attr, v)
    optArrays.fold[Unit] {
      none(attr, e, v, resArrOpt)
    } { sets =>
      find += s"(distinct $v)"
      has(attr, e, v, sets, tpe, toDatalog)
    }
  }


  // hasNo ---------------------------------------------------------------------

  private def hasNo[T](
    attr: Attr, e: Var, v: Var,
    arrays: Seq[Seq[T]], tpe: String, toDatalog: T => String
  ): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair, pair2, array) = vars(attr, v)
    // Common for pre-query and main query
    where += s"[$e $a $v]" -> wClause

    if (arrays.nonEmpty && arrays.flatten.nonEmpty) {
      // Pre-query
      preWhere += s"(rule$v $e)" -> wClause
      preRules ++= mkRules(attr, e, v, arrays, tpe, toDatalog)

      // Main query
      val blacklist   = v + "-blacklist"
      val blacklisted = v + "-blacklisted"
      inPost += blacklist
      wherePost += s"[(contains? $blacklist $firstId) $blacklisted]" -> wClause
      wherePost += s"[(not $blacklisted)]" -> wClause
    }
  }

  private def optHasNo[T](
    attr: Attr, e: Var, v: Var,
    optArrays: Option[Seq[Seq[T]]],
    tpe: String,
    toDatalog: T => String
  ): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair, pair2, array) = vars(attr, v)
    find += s"(distinct $v)"
    if (optArrays.isDefined) {
      hasNo(attr, e, v, optArrays.get, tpe, toDatalog)
    } else {
      where += s"[$e $a $v]" -> wClause
    }
  }


  // no value -----------------------------------------------------------------

  private def noValue(attr: Attr, e: Var): Unit = {
    //    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair, pair2, array) = vars(attr, v)

    val a = nsAttr(attr)
    if (refConfirmed) {
      where += s"(not [$e $a])" -> wNeqOne
    } else {
      val List(e0, _, refAttr, refId) = varPath.takeRight(4)
      val refDatom                    = s"[$e0 $refAttr $refId]"
      if (where.last == refDatom -> wClause) {
        // cancel previous ref Datom since we will pull it instead
        where.remove(where.size - 1)
        varPath = varPath.dropRight(3)
      }
      where += s"(not [$e0 $refAttr])" -> wNeqOne
    }
  }


  // aggregation ---------------------------------------------------------------

  private def aggr[T](
    attr: Attr, e: Var, v: Var, fn: String, optN: Option[Int], resArr: ResArr[T]
  ): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair, pair2, array) = vars(attr, v)
    checkAggrSet()
    lazy val n = optN.getOrElse(0)
    // Replace find/casting with aggregate function/cast
    find -= s"(distinct $v)"
    fn match {
      case "distinct" =>
        noBooleanSetAggr(resArr)
        val (v1, v2, e1) = (v + 1, v + 2, e + 1)
        find += s"(distinct $v2)"
        where += s"[$e $a $v]" -> wClause
        where +=
          s"""[(datomic.api/q
             |          "[:find (distinct $v1)
             |            :in $$ $e1
             |            :where [$e1 $a $v1]]" $$ $e) [[$v2]]]""".stripMargin -> wClause
        replaceCast(resArr.set2sets)

      case "min" =>
        noBooleanSetAggr(resArr)
        find += s"(min 1 $v)"
        replaceCast(resArr.vector2set)

      case "mins" =>
        noBooleanSetAggr(resArr)
        find += s"(min $n $v)"
        replaceCast(resArr.vector2set)

      case "max" =>
        noBooleanSetAggr(resArr)
        find += s"(max 1 $v)"
        replaceCast(resArr.vector2set)

      case "maxs" =>
        noBooleanSetAggr(resArr)
        find += s"(max $n $v)"
        replaceCast(resArr.vector2set)

      case "sample" =>
        noBooleanSetAggr(resArr)
        find += s"(sample 1 $v)"
        replaceCast(resArr.vector2set)

      case "samples" =>
        noBooleanSetAggr(resArr)
        find += s"(sample $n $v)"
        replaceCast(resArr.vector2set)

      case "count" =>
        noBooleanSetCounts(n)
        find += s"(count $v)"
        widh += e
        replaceCast(toInt)

      case "countDistinct" =>
        noBooleanSetCounts(n)
        find += s"(count-distinct $v)"
        widh += e
        replaceCast(toInt)

      case "sum" =>
        widh += e
        find += s"(sum $v)"
        //        replaceCast(res.j2sSet)
        ???

      case "median" =>
        widh += e
        find += s"(median $v)"
        // Force whole number to cast as double according to aggregate type for median/avg/variance/stddev)
        replaceCast((v: AnyRef) => v.toString.toDouble.asInstanceOf[AnyRef])

      // OBS! Datomic rounds down to nearest whole number
      // when calculating the median for multiple numbers instead of
      // following the semantic described on wikipedia:
      // https://en.wikipedia.org/wiki/Median
      // See also
      // https://forum.datomic.com/t/unexpected-median-rounding/517
      // So we calculate the correct median value manually instead:
      //        widh += e
      //        find += s"(distinct $v)"
      //        val medianConverter: AnyRef => Double = {
      //          (v: AnyRef) =>
      //            getMedian(v.asInstanceOf[jArray[_]].toArray
      //              .map(_.toString.toDouble).toSet)
      //        }
      //        replaceCast(medianConverter.asInstanceOf[AnyRef => AnyRef])

      case "avg" =>
        widh += e
        find += s"(avg $v)"
        replaceCast(any2double)

      case "variance" =>
        widh += e
        find += s"(variance $v)"
        replaceCast(any2double)

      case "stddev" =>
        widh += e
        find += s"(stddev $v)"
        replaceCast(any2double)

      case other => unexpectedKw(other)
    }

    where += s"[$e $a $v]" -> wClause
  }


  // Filter attribute filters --------------------------------------------------

  private def equal2(
    attr: Attr, e: Var, v: Var, filterAttr: String
  ): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair, pair2, array) = vars(attr, v)
    preFind = e
    where += s"[$e $a $v]" -> wClause
    where +=
      s"""[(datomic.api/q
         |          "[:find (distinct ${v}1)
         |            :in $$ ${e}1
         |            :where [${e}1 $a ${v}1]]" $$ $e) [[${v}2]]]""".stripMargin -> wClause
    val link: (Var, Var) => Unit = (e1: Var, v1: Var) => {
      where +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${v1}1)
           |            :in $$ ${e1}1
           |            :where [${e1}1 $filterAttr ${v1}1]]" $$ $e1) [[${v1}2]]]""".stripMargin -> wClause
      where += s"[(= ${v}2 ${v1}2)]" -> wClause
    }
    filterAttrVars1.get(filterAttr).fold {
      filterAttrVars2 = filterAttrVars2 + (filterAttr -> link)
    } { case (e, a) => link(e, a) }
  }


  private def neq2(
    attr: Attr, e: Var, v: Var, filterAttr: String
  ): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair, pair2, array) = vars(attr, v)
    where += s"[$e $a $v]" -> wClause
    val process: (Var, Var) => Unit = (e1: Var, v1: Var) => {
      val blacklist   = v1 + "-blacklist"
      val blacklisted = v1 + "-blacklisted"

      // Pre-query
      preFind = e1
      preWhere +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${v}1)
           |            :in $$ ${e}1
           |            :where [${e}1 $a ${v}1]]" $$ $e) [[${v}2]]]""".stripMargin -> wClause
      preWhere +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${v1}1)
           |            :in $$ ${e1}1
           |            :where [${e1}1 $filterAttr ${v1}1]]" $$ $e1) [[${v1}2]]]""".stripMargin -> wClause
      preWhere += s"[(= ${v}2 ${v1}2)]" -> wClause

      // Main query
      inPost += blacklist
      wherePost += s"[(contains? $blacklist $e1) $blacklisted]" -> wClause
      wherePost += s"[(not $blacklisted)]" -> wClause
    }
    filterAttrVars1.get(filterAttr).fold {
      filterAttrVars2 = filterAttrVars2 + (filterAttr -> process)
    } { case (e, a) =>
      process(e, a)
    }
  }


  private def has2(attr: Attr, e: Var, v: Var, filterAttr: String): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair, pair2, array) = vars(attr, v)
    where += s"[$e $a $v]" -> wClause
    val process: (Var, Var) => Unit = (e1: Var, v1: Var) => {
      where +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${v}1)
           |            :in $$ ${e}1
           |            :where [${e}1 $a ${v}1]]" $$ $e) [[${v}2]]]""".stripMargin -> wClause
      where +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${v1}1)
           |            :in $$ ${e1}1
           |            :where [${e1}1 $filterAttr ${v1}1]]" $$ $e1) [[${v1}2]]]""".stripMargin -> wClause
      where += s"[(clojure.set/intersection ${v}2 ${v1}2) $v1-intersection]" -> wClause
      where += s"[(= ${v1}2 $v1-intersection)]" -> wClause
    }
    filterAttrVars1.get(filterAttr).fold {
      filterAttrVars2 = filterAttrVars2 + (filterAttr -> process)
    } { case (e, a) =>
      process(e, a)
    }
  }


  private def hasNo2(attr: Attr, e: Var, v: Var, filterAttr: String): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair, pair2, array) = vars(attr, v)
    // Common for pre-query and main query
    where += s"[$e $a $v]" -> wClause
    val process: (Var, Var) => Unit = (e1: Var, v1: Var) => {
      where +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${v}1)
           |            :in $$ ${e}1
           |            :where [${e}1 $a ${v}1]]" $$ $e) [[${v}2]]]""".stripMargin -> wClause
      where +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${v1}1)
           |            :in $$ ${e1}1
           |            :where [${e1}1 $filterAttr ${v1}1]]" $$ $e1) [[${v1}2]]]""".stripMargin -> wClause
      where += s"[(clojure.set/intersection ${v}2 ${v1}2) $v1-intersection]" -> wClause
      where += s"[(empty? $v1-intersection)]" -> wClause
    }
    filterAttrVars1.get(filterAttr).fold {
      filterAttrVars2 = filterAttrVars2 + (filterAttr -> process)
    } { case (e, a) =>
      process(e, a)
    }
  }


  // helpers -------------------------------------------------------------------

  private def none[T](attr: Attr, e: Var, v: Var, resArrOpt: ResArrOpt[T]): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair, pair2, array) = vars(attr, v)
    if (refConfirmed) {
      find += s"(pull $e-$v [[$a :limit nil]])"
      where += s"[(identity $e) $e-$v]" -> wGround
      where += s"(not [$e $a])" -> wNeqOne

    } else {
      val List(e0, _, refAttr, refId) = varPath.takeRight(4)
      val refDatom                    = s"[$e0 $refAttr $refId]"
      if (where.last == refDatom -> wClause) {
        // cancel previous ref Datom since we will pull it instead
        where.remove(where.size - 1)
        varPath = varPath.dropRight(3)
      }
      find += s"$v"
      where += s"(not [$e0 $refAttr])" -> wNeqOne
      where += s"[(ground #{[]}) $v]" -> wNeqOne
      replaceCast(resArrOpt.optAttr2s)
    }
  }

  private def mkRules[T](
    attr: Attr, e: Var, v: Var,
    arrays: Seq[Seq[T]], tpe: String, toDatalog: T => String
  ): Seq[String] = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair, pair2, array) = vars(attr, v)
    tpe match {
      case "Float" =>
        arrays.flatMap {
          case set if set.isEmpty => None
          case set                => Some(
            set.zipWithIndex.map { case (arg, i) =>
              // Coerce Datomic float values for correct comparison (don't know why this is necessary)
              // See example: https://clojurians-log.clojureverse.org/datomic/2019-10-29
              s"""[$e $a $v$i] [(float $v$i) $v$i-float] [(= $v$i-float (float $arg))]"""
            }.mkString(s"[(rule$v $e)\n    ", "\n    ", "]")
          )
        }
      case "URI"   =>
        arrays.flatMap {
          case set if set.isEmpty => None
          case set                => Some(
            set.zipWithIndex.map { case (arg, i) =>
              s"""[(ground (new java.net.URI "$arg")) $v$i-uri] [$e $a $v$i-uri]"""
            }.mkString(s"[(rule$v $e)\n    ", "\n    ", "]")
          )
        }
      case _       =>
        arrays.flatMap {
          case set if set.isEmpty => None
          case set                => Some(
            set.map(arg => s"[$e $a ${toDatalog(arg)}]")
              .mkString(s"[(rule$v $e)\n    ", "\n    ", "]")
          )
        }
    }
  }

  private def noBooleanSetAggr[T](resArr: ResArr[T]): Unit = {
    if (resArr.tpe == "Boolean")
      throw ModelError("Aggregate functions not implemented for Sets of boolean values.")
  }
  private def noBooleanSetCounts(n: Int): Unit = {
    if (n == -1)
      throw ModelError("Aggregate functions not implemented for Sets of boolean values.")
  }
}