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
      case at: AttrSeqManID             => man(attr, e, at.vs, resSeqId, sortOneID(at, attrIndex))
      case at: AttrSeqManString         => man(attr, e, at.vs, resSeqString, sortOneString(at, attrIndex))
      case at: AttrSeqManInt            => man(attr, e, at.vs, resSeqInt, intSorter(at, attrIndex))
      case at: AttrSeqManLong           => man(attr, e, at.vs, resSeqLong, sortOneLong(at, attrIndex))
      case at: AttrSeqManFloat          => man(attr, e, at.vs, resSeqFloat, floatSorter(at, attrIndex))
      case at: AttrSeqManDouble         => man(attr, e, at.vs, resSeqDouble, sortOneDouble(at, attrIndex))
      case at: AttrSeqManBoolean        => man(attr, e, at.vs, resSeqBoolean, sortOneBoolean(at, attrIndex))
      case at: AttrSeqManBigInt         => man(attr, e, at.vs, resSeqBigInt, bigIntSorter(at, attrIndex))
      case at: AttrSeqManBigDecimal     => man(attr, e, at.vs, resSeqBigDecimal, sortOneBigDecimal(at, attrIndex))
      case at: AttrSeqManDate           => man(attr, e, at.vs, resSeqDate, sortOneDate(at, attrIndex))
      case at: AttrSeqManDuration       => man(attr, e, at.vs, resSeqDuration, sortOneDuration(at, attrIndex))
      case at: AttrSeqManInstant        => man(attr, e, at.vs, resSeqInstant, sortOneInstant(at, attrIndex))
      case at: AttrSeqManLocalDate      => man(attr, e, at.vs, resSeqLocalDate, sortOneLocalDate(at, attrIndex))
      case at: AttrSeqManLocalTime      => man(attr, e, at.vs, resSeqLocalTime, sortOneLocalTime(at, attrIndex))
      case at: AttrSeqManLocalDateTime  => man(attr, e, at.vs, resSeqLocalDateTime, sortOneLocalDateTime(at, attrIndex))
      case at: AttrSeqManOffsetTime     => man(attr, e, at.vs, resSeqOffsetTime, sortOneOffsetTime(at, attrIndex))
      case at: AttrSeqManOffsetDateTime => man(attr, e, at.vs, resSeqOffsetDateTime, sortOneOffsetDateTime(at, attrIndex))
      case at: AttrSeqManZonedDateTime  => man(attr, e, at.vs, resSeqZonedDateTime, sortOneZonedDateTime(at, attrIndex))
      case at: AttrSeqManUUID           => man(attr, e, at.vs, resSeqUUID, sortOneUUID(at, attrIndex))
      case at: AttrSeqManURI            => man(attr, e, at.vs, resSeqURI, sortOneURI(at, attrIndex))
      case at: AttrSeqManByte           => manByteArray(attr, e, at.vs) // Byte Array only semantics
      case at: AttrSeqManShort          => man(attr, e, at.vs, resSeqShort, shortSorter(at, attrIndex))
      case at: AttrSeqManChar           => man(attr, e, at.vs, resSeqChar, sortOneChar(at, attrIndex))
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
      case at: AttrSeqOptID             => opt(attr, e, at.op, at.vs, resOptSeqId, resSeqId, sortOneOptId(at, attrIndex), sortOneID(at, attrIndex))
      case at: AttrSeqOptString         => opt(attr, e, at.op, at.vs, resOptSeqString, resSeqString, sortOneOptString(at, attrIndex), sortOneString(at, attrIndex))
      case at: AttrSeqOptInt            => opt(attr, e, at.op, at.vs, resOptSeqInt, resSeqInt, sortOneOptInt(at, attrIndex), sortOneInt(at, attrIndex))
      case at: AttrSeqOptLong           => opt(attr, e, at.op, at.vs, resOptSeqLong, resSeqLong, sorterOneOptLong(at, attrIndex), sortOneLong(at, attrIndex))
      case at: AttrSeqOptFloat          => opt(attr, e, at.op, at.vs, resOptSeqFloat, resSeqFloat, sortOneOptFloat(at, attrIndex), sortOneFloat(at, attrIndex))
      case at: AttrSeqOptDouble         => opt(attr, e, at.op, at.vs, resOptSeqDouble, resSeqDouble, sortOneOptDouble(at, attrIndex), sortOneDouble(at, attrIndex))
      case at: AttrSeqOptBoolean        => opt(attr, e, at.op, at.vs, resOptSeqBoolean, resSeqBoolean, sortOneOptBoolean(at, attrIndex), sortOneBoolean(at, attrIndex))
      case at: AttrSeqOptBigInt         => opt(attr, e, at.op, at.vs, resOptSeqBigInt, resSeqBigInt, sortOneOptBigInt(at, attrIndex), sortOneBigInt(at, attrIndex))
      case at: AttrSeqOptBigDecimal     => opt(attr, e, at.op, at.vs, resOptSeqBigDecimal, resSeqBigDecimal, sortOneOptBigDecimal(at, attrIndex), sortOneBigDecimal(at, attrIndex))
      case at: AttrSeqOptDate           => opt(attr, e, at.op, at.vs, resOptSeqDate, resSeqDate, sortOneOptDate(at, attrIndex), sortOneDate(at, attrIndex))
      case at: AttrSeqOptDuration       => opt(attr, e, at.op, at.vs, resOptSeqDuration, resSeqDuration, sortOneOptDuration(at, attrIndex), sortOneDuration(at, attrIndex))
      case at: AttrSeqOptInstant        => opt(attr, e, at.op, at.vs, resOptSeqInstant, resSeqInstant, sortOneOptInstant(at, attrIndex), sortOneInstant(at, attrIndex))
      case at: AttrSeqOptLocalDate      => opt(attr, e, at.op, at.vs, resOptSeqLocalDate, resSeqLocalDate, sortOneOptLocalDate(at, attrIndex), sortOneLocalDate(at, attrIndex))
      case at: AttrSeqOptLocalTime      => opt(attr, e, at.op, at.vs, resOptSeqLocalTime, resSeqLocalTime, sortOneOptLocalTime(at, attrIndex), sortOneLocalTime(at, attrIndex))
      case at: AttrSeqOptLocalDateTime  => opt(attr, e, at.op, at.vs, resOptSeqLocalDateTime, resSeqLocalDateTime, sortOneOptLocalDateTime(at, attrIndex), sortOneLocalDateTime(at, attrIndex))
      case at: AttrSeqOptOffsetTime     => opt(attr, e, at.op, at.vs, resOptSeqOffsetTime, resSeqOffsetTime, sortOneOptOffsetTime(at, attrIndex), sortOneOffsetTime(at, attrIndex))
      case at: AttrSeqOptOffsetDateTime => opt(attr, e, at.op, at.vs, resOptSeqOffsetDateTime, resSeqOffsetDateTime, sortOneOptOffsetDateTime(at, attrIndex), sortOneOffsetDateTime(at, attrIndex))
      case at: AttrSeqOptZonedDateTime  => opt(attr, e, at.op, at.vs, resOptSeqZonedDateTime, resSeqZonedDateTime, sortOneOptZonedDateTime(at, attrIndex), sortOneZonedDateTime(at, attrIndex))
      case at: AttrSeqOptUUID           => opt(attr, e, at.op, at.vs, resOptSeqUUID, resSeqUUID, sortOneOptUUID(at, attrIndex), sortOneUUID(at, attrIndex))
      case at: AttrSeqOptURI            => opt(attr, e, at.op, at.vs, resOptSeqURI, resSeqURI, sortOneOptURI(at, attrIndex), sortOneURI(at, attrIndex))
      case at: AttrSeqOptByte           => optByteArray(attr, e, at.vs, resOptSeqByte) // Byte Array only semantics
      case at: AttrSeqOptShort          => opt(attr, e, at.op, at.vs, resOptSeqShort, resSeqShort, sortOneOptShort(at, attrIndex), sortOneShort(at, attrIndex))
      case at: AttrSeqOptChar           => opt(attr, e, at.op, at.vs, resOptSeqChar, resSeqChar, sortOneOptChar(at, attrIndex), sortOneChar(at, attrIndex))
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
    seqs: Seq[Seq[T]],
    resSeq: ResSeq[T],
    sortT: Option[(Int, Int => (Row, Row) => Int)]
  ): Unit = {
    val v = vv
    val a = nsAttr(attr)
    attr.filterAttr.fold {
      val pathAttr = varPath :+ attr.cleanAttr
      if (filterAttrVars.contains(pathAttr) && attr.op != V) {
        // Runtime check needed since we can't type infer it
        throw ModelError(s"Cardinality-set filter attributes not allowed to " +
          s"do additional filtering. Found:\n  " + attr)
      }
      expr(attr, e, v, attr.op, seqs, resSeq, sortT)
      filterAttrVars1 = filterAttrVars1 + (a -> (e, v))
      filterAttrVars2.get(a).foreach(_(e, v))
    } { case (dir, filterPath, filterAttr) =>
      expr2(attr, e, v, attr.op, s":${filterAttr.ns}/${filterAttr.attr}")
    }
    refConfirmed = true
  }

  private def tac[T: ClassTag](
    attr: Attr, e: Var,
    seqs: Seq[Seq[T]],
    resSeq: ResSeq[T],
  ): Unit = {
    val v = vv
    val a = nsAttr(attr)
    attr.filterAttr.fold {
      expr(attr, e, v, attr.op, seqs, resSeq, None)
      filterAttrVars1 = filterAttrVars1 + (a -> (e, v))
      filterAttrVars2.get(a).foreach(_(e, v))
    } { case (dir, filterPath, filterAttr) =>
      expr2(attr, e, v, attr.op, s":${filterAttr.ns}/${filterAttr.attr}")
    }
    refConfirmed = true
  }

  private def expr[T: ClassTag](
    attr: Attr, e: Var, v: Var, op: Op,
    seqs: Seq[Seq[T]],
    resSeq: ResSeq[T],
    sortT: Option[(Int, Int => (Row, Row) => Int)]
  ): Unit = {
    op match {
      case V         => attrib(attr, e, v, resSeq)
      case Eq        => equal(attr, e, v, seqs, resSeq)
      case Neq       => neq(attr, e, v, seqs, resSeq.s2j, resSeq)
      case Has       => has(attr, e, v, seqs, resSeq.tpe, resSeq.toDatalog, resSeq)
      case HasNo     => hasNo(attr, e, v, seqs, resSeq.tpe, resSeq.toDatalog, resSeq)
      case NoValue   => noValue(attr, e)
      case Fn(kw, n) =>
        if (isRefAttr)
          throw ModelError("Aggregating Sets of ref ids not supported.")
        else
          aggr(attr, e, v, kw, n, resSeq, sortT)
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
    optSeqs: Option[Seq[Seq[T]]],
    resSeqOpt: ResSeqOpt[T],
    resSeq: ResSeq[T],
    sortTOpt: Option[(Int, Int => (Row, Row) => Int)],
    sortT: Option[(Int, Int => (Row, Row) => Int)]
  ): Unit = {
    val v = vv
    op match {
      case V     => optAttr(attr, e, v, resSeqOpt)
      case Eq    => optEqual(attr, e, v, optSeqs, resSeqOpt, resSeq)
      case Neq   => optNeq(attr, e, v, optSeqs, resSeqOpt, resSeq)
      case Has   => optHas(attr, e, v, optSeqs, resSeqOpt.tpe, resSeqOpt, resSeqOpt.toDatalog, resSeq)
      case HasNo => optHasNo(attr, e, v, optSeqs, resSeqOpt.tpe, resSeqOpt.toDatalog, resSeq, resSeqOpt)
      case other => unexpectedOp(other)
    }
  }


  // attr ----------------------------------------------------------------------

  private def attrib[T](attr: Attr, e: Var, v: Var, resSeq: ResSeq[T]): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    find += v3
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
    addCast(resSeq.jList2list)
  }

  private def optAttr[T](
    attr: Attr, e: Var, v: Var, resSeqOpt: ResSeqOpt[T]
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

    addCast(resSeqOpt.j2sOptList)
  }


  // eq ------------------------------------------------------------------------

  private def equal[T](
    attr: Attr, e: Var, v: Var,
    seqs: Seq[Seq[T]],
    resSeq: ResSeq[T],
  ): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    find += v3
    args += seqs.map(seq => seq.toSeq.map(resSeq.s2j).asJava).asJava
    in += s"[$v4 ...]"
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
    where += s"[(= $v3 $v4)]" -> wClause
    addCast(resSeq.jList2list)
  }

  private def optEqual[T](
    attr: Attr, e: Var, v: Var,
    optSeqs: Option[Seq[Seq[T]]],
    resSeqOpt: ResSeqOpt[T],
    resSeq: ResSeq[T],
  ): Unit = {
    optSeqs.fold[Unit] {
      none(attr, e, v, resSeqOpt)
    } { seqs =>
      equal(attr, e, v, seqs, resSeq)
      replaceCast(resSeqOpt.j2sOptList)
    }
  }


  // neq -----------------------------------------------------------------------

  private def neq[T](
    attr: Attr, e: Var, v: Var,
    seqs: Seq[Seq[T]],
    s2j: Any => Any,
    resSeq: ResSeq[T],
  ): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    if (seqs.nonEmpty && seqs.flatten.nonEmpty) {
      val blacklist   = v + "-blacklist"
      val blacklisted = v + "-blacklisted"

      // In both pre- and main query
      where += s"[$e $a $v]" -> wClause

      // Pre-query
      preArgs += seqs.map(seq => seq.map(s2j).asJava).asJava
      preIn += s"[$v4 ...]"
      // Find blacklisted entities that match input Seqs
      preWhere +=
        s"""[(datomic.api/q
           |          "[:find (distinct $pair)
           |            :in $$ $e
           |            :where [$e $a $v]
           |                   [$v $ai $i_]
           |                   [$v $av $v_]
           |                   [(vector $i_ $v_) $pair]]" $$ $e) [[$v1]]]""".stripMargin -> wClause
      preWhere += s"[(sort-by first $v1) $v2]" -> wClause
      preWhere += s"[(map second $v2) $v3]" -> wClause
      preWhere += s"[(= $v3 $v4)]" -> wClause

      // Main query
      find += v3
      inPost += blacklist
      // filter out blacklisted entities
      wherePost += s"[(contains? $blacklist $firstId) $blacklisted]" -> wClause
      wherePost += s"[(not $blacklisted)]" -> wClause
      // now get whitelisted list
      wherePost +=
        s"""[(datomic.api/q
           |          "[:find (distinct $pair)
           |            :in $$ $e
           |            :where [$e $a $v]
           |                   [$v $ai $i_]
           |                   [$v $av $v_]
           |                   [(vector $i_ $v_) $pair]]" $$ $e) [[$v1]]]""".stripMargin -> wClause
      wherePost += s"[(sort-by first $v1) $v2]" -> wClause
      wherePost += s"[(map second $v2) $v3]" -> wClause
      addCast(resSeq.jList2list)
    } else {
      // Get all
      attrib(attr, e, v, resSeq)
    }
  }

  private def optNeq[T](
    attr: Attr, e: Var, v: Var,
    optSeqs: Option[Seq[Seq[T]]],
    resSeqOpt: ResSeqOpt[T],
    resSeq: ResSeq[T],
  ): Unit = {
    optSeqs match {
      case Some(seqs) if seqs.nonEmpty && seqs.flatten.nonEmpty =>
        neq(attr, e, v, seqs, resSeq.s2j, resSeq)
        replaceCast(resSeqOpt.j2sOptList)

      case _ =>
        // Ignore empty seqs
        optWithoutNone(attr, e, v, resSeqOpt)
    }
  }

  private def optWithoutNone[T](
    attr: Attr, e: Var, v: Var, resSeqOpt: ResSeqOpt[T]
  ): Unit = {
    // Ignore empty seqs
    where += s"[(nil? ${v}1) ${v}1-empty]" -> wClause
    where += s"[(not ${v}1-empty)]" -> wClause
    optAttr(attr, e, v, resSeqOpt) // Get all available
  }


  // has -----------------------------------------------------------------------

  private def has[T: ClassTag](
    attr: Attr, e: Var, v: Var,
    seqs: Seq[Seq[T]], tpe: String, toDatalog: T => String,
    resSeq: ResSeq[T],
  ): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    if (seqs.nonEmpty && seqs.flatten.nonEmpty) {
      find += v3
      args += seqs.flatten.map(resSeq.s2j).asJava
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
      find += v
      where += s"[(ground nil) $v]" -> wGround
    }
    addCast(resSeq.jList2list)
  }

  private def optHas[T: ClassTag](
    attr: Attr, e: Var, v: Var,
    optSeqs: Option[Seq[Seq[T]]],
    tpe: String,
    resSeqOpt: ResSeqOpt[T],
    toDatalog: T => String,
    resSeq: ResSeq[T],
  ): Unit = {
    optSeqs match {
      case Some(seqs) if seqs.nonEmpty && seqs.flatten.nonEmpty =>
        has(attr, e, v, seqs, resSeq.tpe, resSeq.toDatalog, resSeq)
        replaceCast(resSeqOpt.j2sOptList)

      case Some(emptySeqs) =>
        // Match nothing
        find += v
        where += s"[(ground nil) $v]" -> wGround
        addCast(resSeqOpt.j2sOptList)

      case _ =>
        // Match non-asserted Seqs
        none(attr, e, v, resSeqOpt)
    }
  }


  // hasNo ---------------------------------------------------------------------

  private def hasNo[T](
    attr: Attr, e: Var, v: Var,
    seqs: Seq[Seq[T]], tpe: String, toDatalog: T => String,
    resSeq: ResSeq[T],
  ): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    if (seqs.nonEmpty && seqs.flatten.nonEmpty) {
      find += v3
      args += seqs.flatten.map(resSeq.s2j).asJava
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
      attrib(attr, e, v, resSeq)
    }
    addCast(resSeq.jList2list)
  }

  private def optHasNo[T](
    attr: Attr, e: Var, v: Var,
    optSeqs: Option[Seq[Seq[T]]],
    tpe: String,
    toDatalog: T => String,
    resSeq: ResSeq[T],
    resSeqOpt: ResSeqOpt[T],
  ): Unit = {
    optSeqs match {
      case Some(seqs) if seqs.nonEmpty && seqs.flatten.nonEmpty =>
        hasNo(attr, e, v, optSeqs.get, tpe, toDatalog, resSeq)
        replaceCast(resSeqOpt.j2sOptList)

      case _ => optWithoutNone(attr, e, v, resSeqOpt)
    }
  }


  // no value -----------------------------------------------------------------

  private def noValue(attr: Attr, e: Var): Unit = {
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
    attr: Attr, e: Var, v: Var, fn: String, optN: Option[Int], resSeq: ResSeq[T],
    sortT: Option[(Int, Int => (Row, Row) => Int)]
  ): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    checkAggrSet()
    lazy val n = optN.getOrElse(0)
    fn match {
      case "count" =>
        noBooleanSetCounts(n)
        find += s"(count $v_)"
        widh += v
        where += s"[$e $a $v]" -> wClause
        where += s"[$v $av $v_]" -> wClause
        addSort(sortOneInt(attr, attrIndex))
        addCast(toInt)

      case "countDistinct" =>
        noBooleanSetCounts(n)
        find += s"(count-distinct $v_)"
        widh += v
        where += s"[$e $a $v]" -> wClause
        where += s"[$v $av $v_]" -> wClause
        addSort(sortOneInt(attr, attrIndex))
        addCast(toInt)

      case "sum" =>
        widh += v
        find += s"(sum $v_)"
        where += s"[$e $a $v]" -> wClause
        where += s"[$v $av $v_]" -> wClause
        addSort(sortT)
        addCast(resSeq.j2s)

      case "min" =>
        noBooleanSetAggr(resSeq)
        find += s"(min $v_)"
        where += s"[$e $a $v]" -> wClause
        where += s"[$v $av $v_]" -> wClause
        addSort(sortT)
        addCast(resSeq.j2s)

      case "max" =>
        noBooleanSetAggr(resSeq)
        find += s"(max $v_)"
        where += s"[$e $a $v]" -> wClause
        where += s"[$v $av $v_]" -> wClause
        addSort(sortT)
        addCast(resSeq.j2s)

      case "sample" =>
        noBooleanSetAggr(resSeq)
        find += s"(sample 1 $v_)"
        where += s"[$e $a $v]" -> wClause
        where += s"[$v $av $v_]" -> wClause
        addSort(sortSample(attr, attrIndex))
        addCast(resSeq.jSet2s)

      case "median" =>
        widh += e
        find += s"(median $v_)"
        where += s"[$e $a $v]" -> wClause
        where += s"[$v $av $v_]" -> wClause
        addSort(sortMedian(attr, attrIndex))
        addCast(any2double)

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
      //        addCast(medianConverter.asInstanceOf[AnyRef => AnyRef])

      case "avg" =>
        widh += v
        find += s"(avg $v_)"
        where += s"[$e $a $v]" -> wClause
        where += s"[$v $av $v_]" -> wClause
        addSort(sortOneDouble(attr, attrIndex))
        addCast(any2double)

      case "variance" =>
        widh += v
        find += s"(variance $v_)"
        where += s"[$e $a $v]" -> wClause
        where += s"[$v $av $v_]" -> wClause
        addSort(sortOneDouble(attr, attrIndex))
        addCast(any2double)

      case "stddev" =>
        widh += v
        find += s"(stddev $v_)"
        where += s"[$e $a $v]" -> wClause
        where += s"[$v $av $v_]" -> wClause
        addSort(sortOneDouble(attr, attrIndex))
        addCast(any2double)

      case "mins" =>
        noBooleanSetAggr(resSeq)
        find += s"(min $n $v_)"
        where += s"[$e $a $v]" -> wClause
        where += s"[$v $av $v_]" -> wClause
        addCast(resSeq.vector2set)

      case "maxs" =>
        noBooleanSetAggr(resSeq)
        find += s"(max $n $v_)"
        where += s"[$e $a $v]" -> wClause
        where += s"[$v $av $v_]" -> wClause
        addCast(resSeq.vector2set)

      case "samples" =>
        noBooleanSetAggr(resSeq)
        find += s"(sample $n $v_)"
        where += s"[$e $a $v]" -> wClause
        where += s"[$v $av $v_]" -> wClause
        addCast(resSeq.vector2set)

      case "distinct" =>
        noBooleanSetAggr(resSeq)
        find += s"(distinct $v3)"
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
        addCast(resSeq.jSetOfLists2s)

      case other => unexpectedKw(other)
    }
  }


  // Filter attribute filters --------------------------------------------------

  private def equal2(
    attr: Attr, e: Var, v: Var, filterAttr: String
  ): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
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
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
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
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
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
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
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


  // byte array ----------------------------------------------------------------

  private def manByteArray(attr: Attr, e: Var, vs: Seq[Array[Byte]]): Unit = {
    val v = vv
    val a = nsAttr(attr)
    find += v
    attr.filterAttr.fold {
      where += s"[$e $a $v]" -> wClause
      byteArrayOps(attr, e, v, vs)

    } { _ =>
      throw ModelError(s"Filter attributes not allowed with byte arrays.")
    }
    refConfirmed = true
    addCast(identity) // return Byte array as-is
  }

  private def tacByteArray(attr: Attr, e: Var, vs: Seq[Array[Byte]]): Unit = {
    val v = vv
    val a = nsAttr(attr)
    attr.filterAttr.fold {
      where += s"[$e $a $v]" -> wClause
      byteArrayOps(attr, e, v, vs)
    } { _ =>
      throw ModelError(s"Filter attributes not allowed with byte arrays.")
    }
    refConfirmed = true
  }


  private def optByteArray(
    attr: Attr, e: Var, optVs: Option[Seq[Array[Byte]]], resSeqOpt: ResSeqOpt[Byte]
  ): Unit = {
    val v = vv
    val a = nsAttr(attr)
    attr.op match {
      case V =>
        find += s"(pull $e-$v [[$a :limit nil]]) "
        where += s"[(identity $e) $e-$v]" -> wGround

      case Eq =>
        optVs match {
          case Some(arrays) if arrays.nonEmpty && arrays.flatten.nonEmpty =>
            args += arrays.head
            find += v
            in += s"$v-input"
            where += s"[$e $a $v]" -> wClause
            where += s"[(java.util.Arrays/equals ^bytes $v ^bytes $v-input)]" -> wClause

          case Some(emptyArrays) =>
            find += v
            where += s"[(ground nil) $v]" -> wGround

          case _ =>
            none(attr, e, v, resSeqOpt)
        }

      case Neq =>
        find += v
        where += s"[$e $a $v]" -> wClause
        optVs.collect {
          case arrays if arrays.nonEmpty && arrays.flatten.nonEmpty =>
            args += arrays.head
            in += s"$v-input"
            where += s"[(java.util.Arrays/equals ^bytes $v ^bytes $v-input) $v-equal]" -> wClause
            where += s"[(not $v-equal)]" -> wClause
        }

      case _ => throw ModelError(
        s"Byte arrays can only be retrieved as-is. Filters not allowed.")
    }
    addCast(resSeqOpt.j2sOptList) // delegates to specialized cast for byte arrays
  }


  private def byteArrayOps(attr: Attr, e: Var, v: Var, vs: Seq[Array[Byte]]) = {
    attr.op match {
      case V   => ()
      case Eq  =>
        if (vs.nonEmpty && vs.flatten.nonEmpty) {
          args += vs.head
          in += s"$v-input"
          where += s"[(java.util.Arrays/equals ^bytes $v ^bytes $v-input)]" -> wClause
        } else {
          // Get none
          where += s"[(ground nil) $v]" -> wGround
        }
      case Neq =>
        if (vs.nonEmpty && vs.flatten.nonEmpty) {
          args += vs.head
          in += s"$v-input"
          where += s"[(java.util.Arrays/equals ^bytes $v ^bytes $v-input) $v-equal]" -> wClause
          where += s"[(not $v-equal)]" -> wClause
        } else {
          // get all
        }

      case _ => throw ModelError(
        s"Byte arrays can only be retrieved as-is. Filters not allowed.")
    }
  }


  // helpers -------------------------------------------------------------------

  private def none[T](attr: Attr, e: Var, v: Var, resSeqOpt: ResSeqOpt[T]): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    find += s"(pull $e-$v [[$a :limit nil]])"
    where += s"[(identity $e) $e-$v]" -> wGround
    where += s"(not [$e $a])" -> wNeqOne
    addCast(resSeqOpt.j2sOptList)
  }

  private def noBooleanSetAggr[T](resSeq: ResSeq[T]): Unit = {
    if (resSeq.tpe == "Boolean")
      throw ModelError("Aggregate functions not implemented for Sets of boolean values.")
  }
  private def noBooleanSetCounts(n: Int): Unit = {
    if (n == -1)
      throw ModelError("Aggregate functions not implemented for Sets of boolean values.")
  }
}