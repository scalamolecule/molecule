package molecule.datalog.core.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.query.QueryExpr
import molecule.core.util.JavaConversions
import scala.reflect.ClassTag

trait QueryExprSeq[Tpl] extends QueryExpr { self: Model2DatomicQuery[Tpl] with LambdasSeq =>

  override protected def queryAttrSeqMan(attr: AttrSeqMan): Unit = {
    aritiesAttr()
    attrIndex += 1
    val e = es.last
    attr match {
      case at: AttrSeqManID             => seqMan(attr, e, at.vs, resSeqId)
      case at: AttrSeqManString         => seqMan(attr, e, at.vs, resSeqString)
      case at: AttrSeqManInt            => seqMan(attr, e, at.vs, resSeqInt)
      case at: AttrSeqManLong           => seqMan(attr, e, at.vs, resSeqLong)
      case at: AttrSeqManFloat          => seqMan(attr, e, at.vs, resSeqFloat)
      case at: AttrSeqManDouble         => seqMan(attr, e, at.vs, resSeqDouble)
      case at: AttrSeqManBoolean        => seqMan(attr, e, at.vs, resSeqBoolean)
      case at: AttrSeqManBigInt         => seqMan(attr, e, at.vs, resSeqBigInt)
      case at: AttrSeqManBigDecimal     => seqMan(attr, e, at.vs, resSeqBigDecimal)
      case at: AttrSeqManDate           => seqMan(attr, e, at.vs, resSeqDate)
      case at: AttrSeqManDuration       => seqMan(attr, e, at.vs, resSeqDuration)
      case at: AttrSeqManInstant        => seqMan(attr, e, at.vs, resSeqInstant)
      case at: AttrSeqManLocalDate      => seqMan(attr, e, at.vs, resSeqLocalDate)
      case at: AttrSeqManLocalTime      => seqMan(attr, e, at.vs, resSeqLocalTime)
      case at: AttrSeqManLocalDateTime  => seqMan(attr, e, at.vs, resSeqLocalDateTime)
      case at: AttrSeqManOffsetTime     => seqMan(attr, e, at.vs, resSeqOffsetTime)
      case at: AttrSeqManOffsetDateTime => seqMan(attr, e, at.vs, resSeqOffsetDateTime)
      case at: AttrSeqManZonedDateTime  => seqMan(attr, e, at.vs, resSeqZonedDateTime)
      case at: AttrSeqManUUID           => seqMan(attr, e, at.vs, resSeqUUID)
      case at: AttrSeqManURI            => seqMan(attr, e, at.vs, resSeqURI)
      case at: AttrSeqManByte           => manByteArray(attr, e, at.vs) // Byte Array only semantics
      case at: AttrSeqManShort          => seqMan(attr, e, at.vs, resSeqShort)
      case at: AttrSeqManChar           => seqMan(attr, e, at.vs, resSeqChar)
    }
  }

  override protected def queryAttrSeqTac(attr: AttrSeqTac): Unit = {
//    val e = es.last
    val e = es.last
    attr match {
      case at: AttrSeqTacID             => seqTac(attr, e, at.vs, resSeqId)
      case at: AttrSeqTacString         => seqTac(attr, e, at.vs, resSeqString)
      case at: AttrSeqTacInt            => seqTac(attr, e, at.vs, resSeqInt)
      case at: AttrSeqTacLong           => seqTac(attr, e, at.vs, resSeqLong)
      case at: AttrSeqTacFloat          => seqTac(attr, e, at.vs, resSeqFloat)
      case at: AttrSeqTacDouble         => seqTac(attr, e, at.vs, resSeqDouble)
      case at: AttrSeqTacBoolean        => seqTac(attr, e, at.vs, resSeqBoolean)
      case at: AttrSeqTacBigInt         => seqTac(attr, e, at.vs, resSeqBigInt)
      case at: AttrSeqTacBigDecimal     => seqTac(attr, e, at.vs, resSeqBigDecimal)
      case at: AttrSeqTacDate           => seqTac(attr, e, at.vs, resSeqDate)
      case at: AttrSeqTacDuration       => seqTac(attr, e, at.vs, resSeqDuration)
      case at: AttrSeqTacInstant        => seqTac(attr, e, at.vs, resSeqInstant)
      case at: AttrSeqTacLocalDate      => seqTac(attr, e, at.vs, resSeqLocalDate)
      case at: AttrSeqTacLocalTime      => seqTac(attr, e, at.vs, resSeqLocalTime)
      case at: AttrSeqTacLocalDateTime  => seqTac(attr, e, at.vs, resSeqLocalDateTime)
      case at: AttrSeqTacOffsetTime     => seqTac(attr, e, at.vs, resSeqOffsetTime)
      case at: AttrSeqTacOffsetDateTime => seqTac(attr, e, at.vs, resSeqOffsetDateTime)
      case at: AttrSeqTacZonedDateTime  => seqTac(attr, e, at.vs, resSeqZonedDateTime)
      case at: AttrSeqTacUUID           => seqTac(attr, e, at.vs, resSeqUUID)
      case at: AttrSeqTacURI            => seqTac(attr, e, at.vs, resSeqURI)
      case at: AttrSeqTacByte           => tacByteArray(attr, e, at.vs) // Byte Array only semantics
      case at: AttrSeqTacShort          => seqTac(attr, e, at.vs, resSeqShort)
      case at: AttrSeqTacChar           => seqTac(attr, e, at.vs, resSeqChar)
    }
  }

  override protected def queryAttrSeqOpt(attr: AttrSeqOpt): Unit = {
    aritiesAttr()
    attrIndex += 1
    val e = es.last
    attr match {
      case _: AttrSeqOptID             => seqOpt(attr, e, resOptSeqId)
      case _: AttrSeqOptString         => seqOpt(attr, e, resOptSeqString)
      case _: AttrSeqOptInt            => seqOpt(attr, e, resOptSeqInt)
      case _: AttrSeqOptLong           => seqOpt(attr, e, resOptSeqLong)
      case _: AttrSeqOptFloat          => seqOpt(attr, e, resOptSeqFloat)
      case _: AttrSeqOptDouble         => seqOpt(attr, e, resOptSeqDouble)
      case _: AttrSeqOptBoolean        => seqOpt(attr, e, resOptSeqBoolean)
      case _: AttrSeqOptBigInt         => seqOpt(attr, e, resOptSeqBigInt)
      case _: AttrSeqOptBigDecimal     => seqOpt(attr, e, resOptSeqBigDecimal)
      case _: AttrSeqOptDate           => seqOpt(attr, e, resOptSeqDate)
      case _: AttrSeqOptDuration       => seqOpt(attr, e, resOptSeqDuration)
      case _: AttrSeqOptInstant        => seqOpt(attr, e, resOptSeqInstant)
      case _: AttrSeqOptLocalDate      => seqOpt(attr, e, resOptSeqLocalDate)
      case _: AttrSeqOptLocalTime      => seqOpt(attr, e, resOptSeqLocalTime)
      case _: AttrSeqOptLocalDateTime  => seqOpt(attr, e, resOptSeqLocalDateTime)
      case _: AttrSeqOptOffsetTime     => seqOpt(attr, e, resOptSeqOffsetTime)
      case _: AttrSeqOptOffsetDateTime => seqOpt(attr, e, resOptSeqOffsetDateTime)
      case _: AttrSeqOptZonedDateTime  => seqOpt(attr, e, resOptSeqZonedDateTime)
      case _: AttrSeqOptUUID           => seqOpt(attr, e, resOptSeqUUID)
      case _: AttrSeqOptURI            => seqOpt(attr, e, resOptSeqURI)
      case _: AttrSeqOptByte          => optByteArray(attr, e, resOptSeqByte) // Byte Array only semantics
      case _: AttrSeqOptShort          => seqOpt(attr, e, resOptSeqShort)
      case _: AttrSeqOptChar           => seqOpt(attr, e, resOptSeqChar)
    }
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

  private def seqMan[T](
    attr: Attr, e: Var, seq: Seq[T], resSeq: ResSeq[T]
  ): Unit = {
    val v = vv
    find += s"${v}3"
    addCast(resSeq.j2sList)
    val a = nsAttr(attr)
    attr.filterAttr.fold {
      val pathAttr = varPath :+ attr.cleanAttr
      if (filterAttrVars.contains(pathAttr) && attr.op != V) {
        noCardManyFilterAttrExpr(attr)
      }
      seqExpr(false, attr, e, v, attr.op, seq, resSeq)
      filterAttrVars1 = filterAttrVars1 + (a -> (e -> v))
      filterAttrVars2.get(a).foreach(_(e, v))
    } { case (dir, filterPath, filterAttr) =>
      seqFilterExpr(attr, e, v, attr.op, s":${filterAttr.ns}/${filterAttr.attr}", resSeq)
    }
  }

  private def seqTac[T](
    attr: Attr, e: Var, seq: Seq[T], resSeq: ResSeq[T]
  ): Unit = {
    val v = vv
    val a = nsAttr(attr)
    attr.filterAttr.fold {
      seqExpr(true, attr, e, v, attr.op, seq, resSeq)
      filterAttrVars1 = filterAttrVars1 + (a -> (e -> v))
      filterAttrVars2.get(a).foreach(_(e, v))
    } { case (dir, filterPath, filterAttr) =>
      seqFilterExpr(attr, e, v, attr.op, s":${filterAttr.ns}/${filterAttr.attr}", resSeq)
    }
  }

  private def seqExpr[T](
    tacit: Boolean, attr: Attr, e: Var, v: Var, op: Op, seq: Seq[T], resSeq: ResSeq[T]
  ): Unit = {
    op match {
      case V       => seqAttr(tacit, attr, e, v)
      case Eq      => noCollectionMatching(attr)
      case Has     => seqHas(tacit, attr, e, v, seq, resSeq)
      case HasNo   => seqHasNo(attr, e, v, seq, resSeq)
      case NoValue => if (tacit) seqNoValue(e, nsAttr(attr)) else noApplyNothing(attr)
      case other   => unexpectedOp(other)
    }
  }

  private def seqFilterExpr[T](
    attr: Attr, e: Var, v: Var, op: Op, filterAttr: String, resSeq: ResSeq[T],
  ): Unit = {
    op match {
      case Has   => seqFilterHas(attr, e, v, filterAttr, resSeq)
      case HasNo => seqFilterHasNo(attr, e, v, filterAttr, resSeq)
      case other => unexpectedOp(other)
    }
  }

  private def seqOpt[T](
    attr: Attr, e: Var,
    resSeqOpt: ResSeqOpt[T],
  ): Unit = {
    val v = vv
    addCast(resSeqOpt.j2sOptList)
    attr.op match {
      case V     => seqOptAttr(attr, e, v)
      case Eq    => noCollectionMatching(attr)
      case other => unexpectedOp(other)
    }
  }


  // attr ----------------------------------------------------------------------

  private def seqAttr(
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

  private def seqOptAttr(
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

  private def seqHas[T](
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

  private def seqHasNo[T](
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
          where += s"[(not-any? $v3-set $v5-set)]" -> wClause

        case _ =>
          where += s"[(set $v3) $v4]" -> wClause
          where += s"[(not-any? $v4 $v5)]" -> wClause
      }

    } else {
      // Get all
      seqAttr(false, attr, e, v)
    }
  }

  private def seqNoValue(e: Var, a: Var): Unit = {
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


  // filter attribute ----------------------------------------------------------

  private def seqFilterHas[T](
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


  private def seqFilterHasNo[T](
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

      case NoValue =>
        // Get none
        where += s"[(ground nil) $v]" -> wGround

      case _ => throw ModelError(
        s"Byte arrays (${attr.cleanName}) can only be retrieved as-is. Filters not allowed."
      )
    }
  }

  private def optByteArray(
    attr: Attr, e: Var, resSeqOpt: ResSeqOpt[Byte]
  ): Unit = {

    attr.op match {
      case V =>
        val v = vv
        val a = nsAttr(attr)
        find += s"(pull $e-$v [[$a :limit nil]]) "
        where += s"[(identity $e) $e-$v]" -> wGround
        addCast(resSeqOpt.j2sOptList) // delegates to specialized cast for byte arrays

      case _ => throw ModelError(
        s"Byte arrays (${attr.cleanName}) can only be retrieved as-is. Filters not allowed."
      )
    }
  }
}