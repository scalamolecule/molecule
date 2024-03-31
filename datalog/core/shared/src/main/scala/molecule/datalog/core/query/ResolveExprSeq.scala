package molecule.datalog.core.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.util.JavaConversions
import scala.reflect.ClassTag

trait ResolveExprSeq[Tpl] extends JavaConversions { self: Model2DatomicQuery[Tpl] with LambdasSeq =>

  protected def resolveAttrSeqMan(es: List[Var], attr: AttrSeqMan): List[Var] = {
    aritiesAttr()
    attrIndex += 1
    val e = es.last
    attr match {
      case at: AttrSeqManID             => man(attr, e, at.vs, resSeqId)
      case at: AttrSeqManString         => man(attr, e, at.vs, resSeqString)
      case at: AttrSeqManInt            => man(attr, e, at.vs, resSeqInt)
      case at: AttrSeqManLong           => man(attr, e, at.vs, resSeqLong)
      case at: AttrSeqManFloat          => man(attr, e, at.vs, resSeqFloat)
      case at: AttrSeqManDouble         => man(attr, e, at.vs, resSeqDouble)
      case at: AttrSeqManBoolean        => man(attr, e, at.vs, resSeqBoolean)
      case at: AttrSeqManBigInt         => man(attr, e, at.vs, resSeqBigInt)
      case at: AttrSeqManBigDecimal     => man(attr, e, at.vs, resSeqBigDecimal)
      case at: AttrSeqManDate           => man(attr, e, at.vs, resSeqDate)
      case at: AttrSeqManDuration       => man(attr, e, at.vs, resSeqDuration)
      case at: AttrSeqManInstant        => man(attr, e, at.vs, resSeqInstant)
      case at: AttrSeqManLocalDate      => man(attr, e, at.vs, resSeqLocalDate)
      case at: AttrSeqManLocalTime      => man(attr, e, at.vs, resSeqLocalTime)
      case at: AttrSeqManLocalDateTime  => man(attr, e, at.vs, resSeqLocalDateTime)
      case at: AttrSeqManOffsetTime     => man(attr, e, at.vs, resSeqOffsetTime)
      case at: AttrSeqManOffsetDateTime => man(attr, e, at.vs, resSeqOffsetDateTime)
      case at: AttrSeqManZonedDateTime  => man(attr, e, at.vs, resSeqZonedDateTime)
      case at: AttrSeqManUUID           => man(attr, e, at.vs, resSeqUUID)
      case at: AttrSeqManURI            => man(attr, e, at.vs, resSeqURI)
      case at: AttrSeqManByte           => manByteArray(attr, e, at.vs) // Byte Array only semantics
      case at: AttrSeqManShort          => man(attr, e, at.vs, resSeqShort)
      case at: AttrSeqManChar           => man(attr, e, at.vs, resSeqChar)
    }
    es
  }

  protected def resolveAttrSeqTac(es: List[Var], attr: AttrSeqTac): List[Var] = {
    val e = es.last
    attr match {
      case at: AttrSeqTacID             => tac(attr, e, at.vs, resSeqId)
      case at: AttrSeqTacString         => tac(attr, e, at.vs, resSeqString)
      case at: AttrSeqTacInt            => tac(attr, e, at.vs, resSeqInt)
      case at: AttrSeqTacLong           => tac(attr, e, at.vs, resSeqLong)
      case at: AttrSeqTacFloat          => tac(attr, e, at.vs, resSeqFloat)
      case at: AttrSeqTacDouble         => tac(attr, e, at.vs, resSeqDouble)
      case at: AttrSeqTacBoolean        => tac(attr, e, at.vs, resSeqBoolean)
      case at: AttrSeqTacBigInt         => tac(attr, e, at.vs, resSeqBigInt)
      case at: AttrSeqTacBigDecimal     => tac(attr, e, at.vs, resSeqBigDecimal)
      case at: AttrSeqTacDate           => tac(attr, e, at.vs, resSeqDate)
      case at: AttrSeqTacDuration       => tac(attr, e, at.vs, resSeqDuration)
      case at: AttrSeqTacInstant        => tac(attr, e, at.vs, resSeqInstant)
      case at: AttrSeqTacLocalDate      => tac(attr, e, at.vs, resSeqLocalDate)
      case at: AttrSeqTacLocalTime      => tac(attr, e, at.vs, resSeqLocalTime)
      case at: AttrSeqTacLocalDateTime  => tac(attr, e, at.vs, resSeqLocalDateTime)
      case at: AttrSeqTacOffsetTime     => tac(attr, e, at.vs, resSeqOffsetTime)
      case at: AttrSeqTacOffsetDateTime => tac(attr, e, at.vs, resSeqOffsetDateTime)
      case at: AttrSeqTacZonedDateTime  => tac(attr, e, at.vs, resSeqZonedDateTime)
      case at: AttrSeqTacUUID           => tac(attr, e, at.vs, resSeqUUID)
      case at: AttrSeqTacURI            => tac(attr, e, at.vs, resSeqURI)
      case at: AttrSeqTacByte           => tacByteArray(attr, e, at.vs) // Byte Array only semantics
      case at: AttrSeqTacShort          => tac(attr, e, at.vs, resSeqShort)
      case at: AttrSeqTacChar           => tac(attr, e, at.vs, resSeqChar)
    }
    es
  }

  protected def resolveAttrSeqOpt(es: List[Var], attr: AttrSeqOpt): List[Var] = {
    aritiesAttr()
    attrIndex += 1
    hasOptAttr = true // to avoid redundant None's
    val e = es.last
    attr match {
      case at: AttrSeqOptID             => opt(attr, e, at.vs, resOptSeqId, resSeqId)
      case at: AttrSeqOptString         => opt(attr, e, at.vs, resOptSeqString, resSeqString)
      case at: AttrSeqOptInt            => opt(attr, e, at.vs, resOptSeqInt, resSeqInt)
      case at: AttrSeqOptLong           => opt(attr, e, at.vs, resOptSeqLong, resSeqLong)
      case at: AttrSeqOptFloat          => opt(attr, e, at.vs, resOptSeqFloat, resSeqFloat)
      case at: AttrSeqOptDouble         => opt(attr, e, at.vs, resOptSeqDouble, resSeqDouble)
      case at: AttrSeqOptBoolean        => opt(attr, e, at.vs, resOptSeqBoolean, resSeqBoolean)
      case at: AttrSeqOptBigInt         => opt(attr, e, at.vs, resOptSeqBigInt, resSeqBigInt)
      case at: AttrSeqOptBigDecimal     => opt(attr, e, at.vs, resOptSeqBigDecimal, resSeqBigDecimal)
      case at: AttrSeqOptDate           => opt(attr, e, at.vs, resOptSeqDate, resSeqDate)
      case at: AttrSeqOptDuration       => opt(attr, e, at.vs, resOptSeqDuration, resSeqDuration)
      case at: AttrSeqOptInstant        => opt(attr, e, at.vs, resOptSeqInstant, resSeqInstant)
      case at: AttrSeqOptLocalDate      => opt(attr, e, at.vs, resOptSeqLocalDate, resSeqLocalDate)
      case at: AttrSeqOptLocalTime      => opt(attr, e, at.vs, resOptSeqLocalTime, resSeqLocalTime)
      case at: AttrSeqOptLocalDateTime  => opt(attr, e, at.vs, resOptSeqLocalDateTime, resSeqLocalDateTime)
      case at: AttrSeqOptOffsetTime     => opt(attr, e, at.vs, resOptSeqOffsetTime, resSeqOffsetTime)
      case at: AttrSeqOptOffsetDateTime => opt(attr, e, at.vs, resOptSeqOffsetDateTime, resSeqOffsetDateTime)
      case at: AttrSeqOptZonedDateTime  => opt(attr, e, at.vs, resOptSeqZonedDateTime, resSeqZonedDateTime)
      case at: AttrSeqOptUUID           => opt(attr, e, at.vs, resOptSeqUUID, resSeqUUID)
      case at: AttrSeqOptURI            => opt(attr, e, at.vs, resOptSeqURI, resSeqURI)
      case at: AttrSeqOptByte           => optByteArray(attr, e, at.vs, resOptSeqByte) // Byte Array only semantics
      case at: AttrSeqOptShort          => opt(attr, e, at.vs, resOptSeqShort, resSeqShort)
      case at: AttrSeqOptChar           => opt(attr, e, at.vs, resOptSeqChar, resSeqChar)
    }
    es
  }


  private def vars(attr: Attr, v: String) = {
    val (ns, at) = (attr.ns, attr.attr)
    (
      s":$ns/$at", s":$ns.$at/i_", s":$ns.$at/v_",
      v + "-i", v + "-v",
      v + 1, v + 2, v + 3, v + 4, v + 5, v + 6,
      v + "-pair"
    )
  }
  private def nsAttr(attr: Attr): String = s":${attr.ns}/${attr.attr}"

  private def man[T: ClassTag](
    attr: Attr, e: Var,
    seq: Seq[T],
    resSeq: ResSeq[T],
  ): Unit = {
    val v = vv
    find += s"${v}3"
    addCast(resSeq.j2sList)
    val a = nsAttr(attr)
    attr.filterAttr.fold {
      val pathAttr = varPath :+ attr.cleanAttr
      if (filterAttrVars.contains(pathAttr) && attr.op != V) {
        // Runtime check needed since we can't type infer it
        throw ModelError(s"Cardinality-seq filter attributes not allowed to " +
          s"do additional filtering. Found:\n  " + attr)
      }
      expr(false, attr, e, v, attr.op, seq, resSeq)
      filterAttrVars1 = filterAttrVars1 + (a -> (e, v))
      filterAttrVars2.get(a).foreach(_(e, v))
    } { case (dir, filterPath, filterAttr) =>
      expr2(attr, e, v, attr.op, s":${filterAttr.ns}/${filterAttr.attr}", resSeq)
    }
  }

  private def tac[T: ClassTag](
    attr: Attr, e: Var,
    seq: Seq[T],
    resSeq: ResSeq[T],
  ): Unit = {
    val v = vv
    val a = nsAttr(attr)
    attr.filterAttr.fold {
      expr(true, attr, e, v, attr.op, seq, resSeq)
      filterAttrVars1 = filterAttrVars1 + (a -> (e, v))
      filterAttrVars2.get(a).foreach(_(e, v))
    } { case (dir, filterPath, filterAttr) =>
      expr2(attr, e, v, attr.op, s":${filterAttr.ns}/${filterAttr.attr}", resSeq)
    }
  }

  private def expr[T: ClassTag](
    tacit: Boolean,
    attr: Attr, e: Var, v: Var, op: Op,
    seq: Seq[T],
    resSeq: ResSeq[T],
  ): Unit = {
    op match {
      case V       => attrV(tacit, attr, e, v)
      case Eq      => noCollectionMatching(attr)
      case Has     => has(tacit, attr, e, v, seq, resSeq)
      case HasNo   => hasNo(attr, e, v, seq, resSeq)
      case NoValue => if (tacit) noValue(e, nsAttr(attr)) else noApplyNothing(attr)
      case other   => unexpectedOp(other)
    }
  }

  private def expr2[T](
    attr: Attr, e: Var, v: Var, op: Op, filterAttr: String, resSeq: ResSeq[T],
  ): Unit = {
    op match {
      case Has   => has2(attr, e, v, filterAttr, resSeq)
      case HasNo => hasNo2(attr, e, v, filterAttr, resSeq)
      case other => unexpectedOp(other)
    }
  }

  private def opt[T: ClassTag](
    attr: Attr, e: Var,
    optSeq: Option[Seq[T]],
    resSeqOpt: ResSeqOpt[T],
    resSeq: ResSeq[T],
  ): Unit = {
    val v = vv
    addCast(resSeqOpt.j2sOptList)
    attr.op match {
      case V     => optAttr(attr, e, v)
      case Eq    => noCollectionMatching(attr)
      case Has   => optHas(attr, e, v, optSeq, resSeq)
      case HasNo => optHasNo(attr, e, v, optSeq, resSeq, resSeqOpt)
      case other => unexpectedOp(other)
    }
  }


  // attr ----------------------------------------------------------------------

  private def attrV(
    tacit: Boolean, attr: Attr, e: Var, v: Var
  ): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    where += s"[$e $a _]" -> wClause
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
    if (tacit) {
      widh += v3 // Don't coalesce List to Set
    }
  }

  private def optAttr(
    attr: Attr, e: Var, v: Var
  ): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
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
  }


  // has -----------------------------------------------------------------------

  private def has[T: ClassTag](
    tacit: Boolean,
    attr: Attr, e: Var, v: Var,
    seq: Seq[T],
    resSeq: ResSeq[T],
  ): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    if (seq.nonEmpty) {
      args += seq.map(resSeq.s2j).asJava
      in += v5
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
      resSeq.tpe match {
        case "Boolean" =>
          // Need to convert to sets of Strings for `some` to work on boolean false (maybe a bug?)
          where += s"[(map str $v3) $v3-list]" -> wClause
          where += s"[(set $v3-list) $v3-set]" -> wClause
          where += s"[(map str $v5) $v5-list]" -> wClause
          where += s"[(set $v5-list) $v5-set]" -> wClause
          where += s"[(some $v3-set $v5-set)]" -> wClause

        case _ =>
          where += s"[(set $v3) $v4]" -> wClause
          where += s"[(some $v4 $v5)]" -> wClause
      }

    } else {
      // Match nothing
      where += s"[$e $a $v3]" -> wClause
      where += s"[(ground nil) $v3]" -> wGround
    }
    if (tacit) {
      widh += v3 // Don't coalesce List to Set
    }
  }

  private def optHas[T: ClassTag](
    attr: Attr, e: Var, v: Var,
    optSeq: Option[Seq[T]],
    resSeq: ResSeq[T],
  ): Unit = {
    optSeq.fold[Unit] {
      none(attr, e, v)
    } { seq =>
      find += v + 3
      has(false, attr, e, v, seq, resSeq)
    }
  }


  // hasNo ---------------------------------------------------------------------

  private def hasNo[T](
    attr: Attr, e: Var, v: Var,
    seq: Seq[T],
    resSeq: ResSeq[T],
  ): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    if (seq.nonEmpty) {
      find += v3
      args += seq.map(resSeq.s2j).asJava
      in += v5
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
      resSeq.tpe match {
        case "Boolean" =>
          // Need to convert to sets of Strings for `some` to work on boolean false (maybe a bug?)
          where += s"[(map str $v3) $v3-list]" -> wClause
          where += s"[(set $v3-list) $v3-set]" -> wClause
          where += s"[(map str $v5) $v5-list]" -> wClause
          where += s"[(set $v5-list) $v5-set]" -> wClause
          where += s"[(not-any? $v3-set $v5-set)]" -> wClause

        case _ =>
          where += s"[(set $v3) $v4]" -> wClause
          where += s"[(not-any? $v4 $v5)]" -> wClause
      }

    } else {
      // Get all
      attrV(false, attr, e, v)
    }
  }

  private def optHasNo[T](
    attr: Attr, e: Var, v: Var,
    optSeq: Option[Seq[T]],
    resSeq: ResSeq[T],
    resSeqOpt: ResSeqOpt[T],
  ): Unit = {
    optSeq match {
      case Some(seq) if seq.nonEmpty =>
        //        hasNo(attr, e, v, optSeq.get, resSeq)
        hasNo(attr, e, v, seq, resSeq)
        replaceCast(resSeqOpt.j2sOptList)

      case _ => optWithoutNone(attr, e, v)
    }
  }


  // no value -----------------------------------------------------------------

  private def noValue(e: Var, a: Var): Unit = {
    where += s"(not [$e $a])" -> wNeqOne
  }


  // Filter attribute filters --------------------------------------------------

  private def has2[T](
    attr: Attr, e: Var, v: Var, filterAttr: String, resSeq: ResSeq[T]
  ): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    where += s"[$e $a _]" -> wClause
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

    val process: (Var, Var) => Unit = if (attr.filterAttr.get._3.isInstanceOf[AttrOne]) {
      (_: Var, w: Var) =>
        where += s"[(set $v3) $v4]" -> wClause
        where += s"[(contains? $v4 $w)]" -> wClause

    } else if (resSeq.tpe == "Boolean") {
      (_: Var, w: Var) =>
        // Need to convert to sets of Strings for `some` to work on boolean false (maybe a bug?)
        where += s"[(map str $v3) $v3-list]" -> wClause
        where += s"[(set $v3-list) $v3-set]" -> wClause
        where += s"[(map str ${w}3) ${w}3-list]" -> wClause
        where += s"[(set ${w}3-list) ${w}3-set]" -> wClause
        where += s"[(clojure.set/intersection $v3-set ${w}3-set) $w-intersection]" -> wClause
        where += s"[(= ${w}3-set $w-intersection)]" -> wClause

    } else {
      (_: Var, w: Var) =>
        where += s"[(set $v3) $v4]" -> wClause
        where += s"[(set ${w}3) ${w}4]" -> wClause
        where += s"[(clojure.set/intersection $v4 ${w}4) $w-intersection]" -> wClause
        where += s"[(= ${w}4 $w-intersection)]" -> wClause
    }
    filterAttrVars1.get(filterAttr).fold {
      filterAttrVars2 = filterAttrVars2 + (filterAttr -> process)
    } { case (e, a) =>
      process(e, a)
    }
  }


  private def hasNo2[T](
    attr: Attr, e: Var, v: Var, filterAttr: String, resSeq: ResSeq[T]
  ): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    where += s"[$e $a _]" -> wClause
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

    val process: (Var, Var) => Unit = if (attr.filterAttr.get._3.isInstanceOf[AttrOne]) {
      (_: Var, w: Var) => {
        where += s"[(set $v3) $v4]" -> wClause
        where += s"[(contains? $v4 $w) $v5]" -> wClause
        where += s"[(not $v5)]" -> wClause
      }

    } else if (resSeq.tpe == "Boolean") {
      (_: Var, w: Var) =>
        // Need to convert to sets of Strings for `some` to work on boolean false (maybe a bug?)
        where += s"[(map str $v3) $v3-list]" -> wClause
        where += s"[(set $v3-list) $v3-set]" -> wClause
        where += s"[(map str ${w}3) ${w}3-list]" -> wClause
        where += s"[(set ${w}3-list) ${w}3-set]" -> wClause
        where += s"[(clojure.set/intersection $v3-set ${w}3-set) $w-intersection]" -> wClause
        where += s"[(empty? $w-intersection)]" -> wClause

    } else {
      (_: Var, w: Var) =>
        where += s"[(set $v3) $v4]" -> wClause
        where += s"[(set ${w}3) ${w}4]" -> wClause
        where += s"[(clojure.set/intersection $v4 ${w}4) $w-intersection]" -> wClause
        where += s"[(empty? $w-intersection)]" -> wClause
    }

    filterAttrVars1.get(filterAttr).fold {
      filterAttrVars2 = filterAttrVars2 + (filterAttr -> process)
    } { case (e, a) =>
      process(e, a)
    }
  }


  // byte array ----------------------------------------------------------------

  private def manByteArray(attr: Attr, e: Var, byteArray: Array[Byte]): Unit = {
    val v = vv
    val a = nsAttr(attr)
    find += v
    attr.filterAttr.fold {
      where += s"[$e $a $v]" -> wClause
      byteArrayOps(attr, v, byteArray)

    } { _ =>
      throw ModelError(s"Filter attributes not allowed with byte arrays.")
    }
    addCast(identity) // return Byte array as-is
  }

  private def tacByteArray(attr: Attr, e: Var, byteArray: Array[Byte]): Unit = {
    val v = vv
    val a = nsAttr(attr)
    attr.filterAttr.fold {
      where += s"[$e $a $v]" -> wClause
      byteArrayOps(attr, v, byteArray)
    } { _ =>
      throw ModelError(s"Filter attributes not allowed with byte arrays.")
    }
  }

  private def byteArrayOps(attr: Attr, v: Var, byteArray: Array[Byte]): Unit = {
    attr.op match {
      case V   => ()
      case Eq  =>
        if (byteArray.length != 0) {
          args += byteArray
          in += s"$v-input"
          where += s"[(java.util.Arrays/equals ^bytes $v ^bytes $v-input)]" -> wClause
        } else {
          // Get none
          where += s"[(ground nil) $v]" -> wGround
        }
      case Neq =>
        if (byteArray.length != 0) {
          args += byteArray
          in += s"$v-input"
          where += s"[(java.util.Arrays/equals ^bytes $v ^bytes $v-input) $v-equal]" -> wClause
          where += s"[(not $v-equal)]" -> wClause
        } else {
          // get all
        }

      case NoValue  =>
        // Get none
        where += s"[(ground nil) $v]" -> wGround

      case _ => throw ModelError(
        s"Byte arrays can only be retrieved as-is. Filters not allowed.")
    }
  }

  private def optByteArray(
    attr: Attr, e: Var, optByteArray: Option[Array[Byte]], resSeqOpt: ResSeqOpt[Byte]
  ): Unit = {
    val v = vv
    val a = nsAttr(attr)
    attr.op match {
      case V =>
        find += s"(pull $e-$v [[$a :limit nil]]) "
        where += s"[(identity $e) $e-$v]" -> wGround

      case Eq =>
        optByteArray match {
          case Some(byteArray) if byteArray.length != 0 =>
            args += byteArray
            find += v
            in += s"$v-input"
            where += s"[$e $a $v]" -> wClause
            where += s"[(java.util.Arrays/equals ^bytes $v ^bytes $v-input)]" -> wClause

          case Some(emptyByteArray) =>
            find += v
            where += s"[(ground nil) $v]" -> wGround

          case _ =>
            none(attr, e, v)
        }

      case Neq =>
        find += v
        where += s"[$e $a $v]" -> wClause
        optByteArray.map { byteArray =>
          args += byteArray
          in += s"$v-input"
          where += s"[(java.util.Arrays/equals ^bytes $v ^bytes $v-input) $v-equal]" -> wClause
          where += s"[(not $v-equal)]" -> wClause
        }


      case _ => throw ModelError(
        s"Byte arrays can only be retrieved as-is. Filters not allowed.")
    }
    addCast(resSeqOpt.j2sOptList) // delegates to specialized cast for byte arrays
  }


  // helpers -------------------------------------------------------------------

  private def none(attr: Attr, e: Var, v: Var): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    find += s"(pull $e-$v [[$a :limit nil]])"
    where += s"[(identity $e) $e-$v]" -> wGround
    where += s"(not [$e $a])" -> wNeqOne
  }

  private def optWithoutNone(attr: Attr, e: Var, v: Var): Unit = {
    // Ignore empty seqs
    where += s"[(nil? ${v}1) ${v}1-empty]" -> wClause
    where += s"[(not ${v}1-empty)]" -> wClause
    optAttr(attr, e, v) // Get all available
  }
}