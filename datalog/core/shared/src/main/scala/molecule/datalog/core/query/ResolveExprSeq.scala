package molecule.datalog.core.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import scala.reflect.ClassTag
import java.util.{Date, UUID, Iterator => jIterator, List => jList, Map => jMap, Set => jSet}
import molecule.core.util.JavaConversions

trait ResolveExprSeq[Tpl] extends JavaConversions { self: Model2DatomicQuery[Tpl] with LambdasSeq =>

  protected def resolveAttrSeqMan(es: List[Var], attr: AttrSeqMan): List[Var] = {
    aritiesAttr()
    attrIndex += 1
    val e = es.last
    attr match {
      case att: AttrSeqManID             => man(attr, e, att.vs, resSeqId)
      case att: AttrSeqManString         => man(attr, e, att.vs, resSeqString)
      case att: AttrSeqManInt            => man(attr, e, att.vs, resSeqInt)
      case att: AttrSeqManLong           => man(attr, e, att.vs, resSeqLong)
      case att: AttrSeqManFloat          => man(attr, e, att.vs, resSeqFloat)
      case att: AttrSeqManDouble         => man(attr, e, att.vs, resSeqDouble)
      case att: AttrSeqManBoolean        => man(attr, e, att.vs, resSeqBoolean)
      case att: AttrSeqManBigInt         => man(attr, e, att.vs, resSeqBigInt)
      case att: AttrSeqManBigDecimal     => man(attr, e, att.vs, resSeqBigDecimal)
      case att: AttrSeqManDate           => man(attr, e, att.vs, resSeqDate)
      case att: AttrSeqManDuration       => man(attr, e, att.vs, resSeqDuration)
      case att: AttrSeqManInstant        => man(attr, e, att.vs, resSeqInstant)
      case att: AttrSeqManLocalDate      => man(attr, e, att.vs, resSeqLocalDate)
      case att: AttrSeqManLocalTime      => man(attr, e, att.vs, resSeqLocalTime)
      case att: AttrSeqManLocalDateTime  => man(attr, e, att.vs, resSeqLocalDateTime)
      case att: AttrSeqManOffsetTime     => man(attr, e, att.vs, resSeqOffsetTime)
      case att: AttrSeqManOffsetDateTime => man(attr, e, att.vs, resSeqOffsetDateTime)
      case att: AttrSeqManZonedDateTime  => man(attr, e, att.vs, resSeqZonedDateTime)
      case att: AttrSeqManUUID           => man(attr, e, att.vs, resSeqUUID)
      case att: AttrSeqManURI            => man(attr, e, att.vs, resSeqURI)
      case att: AttrSeqManByte           => manByteArray(attr, e) // Byte Array only semantics
      case att: AttrSeqManShort          => man(attr, e, att.vs, resSeqShort)
      case att: AttrSeqManChar           => man(attr, e, att.vs, resSeqChar)
    }
    es
  }

  protected def resolveAttrSeqTac(es: List[Var], attr: AttrSeqTac): List[Var] = {
    val e = es.last
    attr match {
      case att: AttrSeqTacID             => tac(attr, e, att.vs, resSeqId)
      case att: AttrSeqTacString         => tac(attr, e, att.vs, resSeqString)
      case att: AttrSeqTacInt            => tac(attr, e, att.vs, resSeqInt)
      case att: AttrSeqTacLong           => tac(attr, e, att.vs, resSeqLong)
      case att: AttrSeqTacFloat          => tac(attr, e, att.vs, resSeqFloat)
      case att: AttrSeqTacDouble         => tac(attr, e, att.vs, resSeqDouble)
      case att: AttrSeqTacBoolean        => tac(attr, e, att.vs, resSeqBoolean)
      case att: AttrSeqTacBigInt         => tac(attr, e, att.vs, resSeqBigInt)
      case att: AttrSeqTacBigDecimal     => tac(attr, e, att.vs, resSeqBigDecimal)
      case att: AttrSeqTacDate           => tac(attr, e, att.vs, resSeqDate)
      case att: AttrSeqTacDuration       => tac(attr, e, att.vs, resSeqDuration)
      case att: AttrSeqTacInstant        => tac(attr, e, att.vs, resSeqInstant)
      case att: AttrSeqTacLocalDate      => tac(attr, e, att.vs, resSeqLocalDate)
      case att: AttrSeqTacLocalTime      => tac(attr, e, att.vs, resSeqLocalTime)
      case att: AttrSeqTacLocalDateTime  => tac(attr, e, att.vs, resSeqLocalDateTime)
      case att: AttrSeqTacOffsetTime     => tac(attr, e, att.vs, resSeqOffsetTime)
      case att: AttrSeqTacOffsetDateTime => tac(attr, e, att.vs, resSeqOffsetDateTime)
      case att: AttrSeqTacZonedDateTime  => tac(attr, e, att.vs, resSeqZonedDateTime)
      case att: AttrSeqTacUUID           => tac(attr, e, att.vs, resSeqUUID)
      case att: AttrSeqTacURI            => tac(attr, e, att.vs, resSeqURI)
      case att: AttrSeqTacByte           => tacByteArray(attr, e) // Byte Array only semantics
      case att: AttrSeqTacShort          => tac(attr, e, att.vs, resSeqShort)
      case att: AttrSeqTacChar           => tac(attr, e, att.vs, resSeqChar)
    }
    es
  }

  protected def resolveAttrSeqOpt(es: List[Var], attr: AttrSeqOpt): List[Var] = {
    aritiesAttr()
    attrIndex += 1
    hasOptAttr = true // to avoid redundant None's
    val e = es.last
    attr match {
      case att: AttrSeqOptID             => opt(attr, e, att.op, att.vs, resOptSeqId, resSeqId)
      case att: AttrSeqOptString         => opt(attr, e, att.op, att.vs, resOptSeqString, resSeqString)
      case att: AttrSeqOptInt            => opt(attr, e, att.op, att.vs, resOptSeqInt, resSeqInt)
      case att: AttrSeqOptLong           => opt(attr, e, att.op, att.vs, resOptSeqLong, resSeqLong)
      case att: AttrSeqOptFloat          => opt(attr, e, att.op, att.vs, resOptSeqFloat, resSeqFloat)
      case att: AttrSeqOptDouble         => opt(attr, e, att.op, att.vs, resOptSeqDouble, resSeqDouble)
      case att: AttrSeqOptBoolean        => opt(attr, e, att.op, att.vs, resOptSeqBoolean, resSeqBoolean)
      case att: AttrSeqOptBigInt         => opt(attr, e, att.op, att.vs, resOptSeqBigInt, resSeqBigInt)
      case att: AttrSeqOptBigDecimal     => opt(attr, e, att.op, att.vs, resOptSeqBigDecimal, resSeqBigDecimal)
      case att: AttrSeqOptDate           => opt(attr, e, att.op, att.vs, resOptSeqDate, resSeqDate)
      case att: AttrSeqOptDuration       => opt(attr, e, att.op, att.vs, resOptSeqDuration, resSeqDuration)
      case att: AttrSeqOptInstant        => opt(attr, e, att.op, att.vs, resOptSeqInstant, resSeqInstant)
      case att: AttrSeqOptLocalDate      => opt(attr, e, att.op, att.vs, resOptSeqLocalDate, resSeqLocalDate)
      case att: AttrSeqOptLocalTime      => opt(attr, e, att.op, att.vs, resOptSeqLocalTime, resSeqLocalTime)
      case att: AttrSeqOptLocalDateTime  => opt(attr, e, att.op, att.vs, resOptSeqLocalDateTime, resSeqLocalDateTime)
      case att: AttrSeqOptOffsetTime     => opt(attr, e, att.op, att.vs, resOptSeqOffsetTime, resSeqOffsetTime)
      case att: AttrSeqOptOffsetDateTime => opt(attr, e, att.op, att.vs, resOptSeqOffsetDateTime, resSeqOffsetDateTime)
      case att: AttrSeqOptZonedDateTime  => opt(attr, e, att.op, att.vs, resOptSeqZonedDateTime, resSeqZonedDateTime)
      case att: AttrSeqOptUUID           => opt(attr, e, att.op, att.vs, resOptSeqUUID, resSeqUUID)
      case att: AttrSeqOptURI            => opt(attr, e, att.op, att.vs, resOptSeqURI, resSeqURI)
      case att: AttrSeqOptByte           => optByteArray(attr, e, resOptSeqByte) // Byte Array only semantics
      case att: AttrSeqOptShort          => opt(attr, e, att.op, att.vs, resOptSeqShort, resSeqShort)
      case att: AttrSeqOptChar           => opt(attr, e, att.op, att.vs, resOptSeqChar, resSeqChar)
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
  ): Unit = {
    val v = vv
    val a = nsAttr(attr)
    //    find += s"(distinct ${v}-pair)" // position index -> value
    //    addCast(res.seq2array)
    attr.filterAttr.fold {
      val pathAttr = varPath :+ attr.cleanAttr
      if (filterAttrVars.contains(pathAttr) && attr.op != V) {
        // Runtime check needed since we can't type infer it
        throw ModelError(s"Cardinality-set filter attributes not allowed to " +
          s"do additional filtering. Found:\n  " + attr)
      }
      expr(attr, e, v, attr.op, seqs, resSeq)
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
      expr(attr, e, v, attr.op, seqs, resSeq)
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
  ): Unit = {
    op match {
      case V         => attrib(attr, e, v, resSeq)
      case Eq        => equal(attr, e, v, seqs, resSeq)
      case Neq       => neq(attr, e, v, seqs, resSeq.s2j, resSeq)
      case Has       => has(attr, e, v, seqs, resSeq.tpe, resSeq.toDatalog)
      case HasNo     => hasNo(attr, e, v, seqs, resSeq.tpe, resSeq.toDatalog)
      case NoValue   => noValue(attr, e)
      case Fn(kw, n) =>
        if (isRefAttr)
          throw ModelError("Aggregating Sets of ref ids not supported.")
        else
          aggr(attr, e, v, kw, n, resSeq)
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
  ): Unit = {
    val v = vv
    addCast(resSeqOpt.j2s)
    op match {
      case V     => optAttr(attr, e, v, resSeqOpt)
      case Eq    => optEqual(attr, e, v, optSeqs, resSeqOpt, resSeq)
      case Neq   => optNeq(attr, e, v, optSeqs, resSeqOpt.s2j, resSeq)
      case Has   => optHas(attr, e, v, optSeqs, resSeqOpt.tpe, resSeqOpt, resSeqOpt.toDatalog)
      case HasNo => optHasNo(attr, e, v, optSeqs, resSeqOpt.tpe, resSeqOpt.toDatalog)
      case other => unexpectedOp(other)
    }
  }


  private def manByteArray(attr: Attr, e: Var): Unit = {
    val v = vv
    val a = nsAttr(attr)
    find += v
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
    addCast(identity) // return array as-is
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

  private def optByteArray(attr: Attr, e: Var, resSeqOpt: ResSeqOpt[Byte]): Unit = {
    val v = vv
    val a = nsAttr(attr)
    find += s"(pull $e-$v [[$a :limit nil]]) "
    where += s"[(identity $e) $e-$v]" -> wGround
    addCast(resSeqOpt.optAttr2s)
  }

  // attr ----------------------------------------------------------------------

  private def attrib[T](attr: Attr, e: Var, v: Var, resSeq: ResSeq[T]): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    find += s"(distinct $pair)" // position index -> value
    where += s"[$e $a $v]" -> wClause
    where += s"[$v $ai $i_]" -> wClause
    where += s"[$v $av $v_]" -> wClause
    where += s"[(vector $i_ $v_) $pair]" -> wClause
    addCast(resSeq.pairs2list)
  }

  private def optAttr[T](
    attr: Attr, e: Var, v: Var, resSeqOpt: ResSeqOpt[T]
  ): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
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
    replaceCast(resSeqOpt.optAttr2s)
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
    }
  }


  // neq -----------------------------------------------------------------------

  private def neq[T](
    attr: Attr, e: Var, v: Var,
    seqs: Seq[Seq[T]],
    fromScala: Any => Any,
    resSeq: ResSeq[T],
  ): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    if (seqs.nonEmpty && seqs.flatten.nonEmpty) {
      val blacklist   = v + "-blacklist"
      val blacklisted = v + "-blacklisted"

      // In both pre- and main query
      where += s"[$e $a $v]" -> wClause

      // Pre-query
      preArgs += seqs.map(seq => seq.map(fromScala).asJava).asJava
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
      attrib(attr,e,v,resSeq)
    }
  }

  private def optNeq[T](
    attr: Attr, e: Var, v: Var,
    optSeqs: Option[Seq[Seq[T]]],
    fromScala: Any => Any,
    resSeq: ResSeq[T],
  ): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    find += s"(distinct $v)"
    optSeqs.fold[Unit] {
      where += s"[$e $a $v]" -> wClause
    } { sets =>
      neq(attr, e, v, sets, fromScala, resSeq)
    }
  }


  // has -----------------------------------------------------------------------

  private def has[T: ClassTag](
    attr: Attr, e: Var, v: Var,
    seqs: Seq[Seq[T]], tpe: String, toDatalog: T => String
  ): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    where += s"[$e $a $v]" -> wClause
    if (seqs.nonEmpty && seqs.flatten.nonEmpty) {
      where += s"(rule$v $e)" -> wClause
      rules ++= mkRules(attr, e, v, seqs, tpe, toDatalog)
    } else {
      where += s"[(ground nil) $v]" -> wGround
    }
  }

  private def mkRules[T](
    attr: Attr, e: Var, v: Var,
    seqs: Seq[Seq[T]], tpe: String, toDatalog: T => String
  ): Seq[String] = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    tpe match {
      case "Float" =>
        seqs.flatMap {
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
        seqs.flatMap {
          case set if set.isEmpty => None
          case set                => Some(
            set.zipWithIndex.map { case (arg, i) =>
              s"""[(ground (new java.net.URI "$arg")) $v$i-uri] [$e $a $v$i-uri]"""
            }.mkString(s"[(rule$v $e)\n    ", "\n    ", "]")
          )
        }
      case _       =>
        seqs.flatMap {
          case set if set.isEmpty => None
          case set                => Some(
            set.map(arg => s"[$e $a ${toDatalog(arg)}]")
              .mkString(s"[(rule$v $e)\n    ", "\n    ", "]")
          )
        }
    }
  }

  private def optHas[T: ClassTag](
    attr: Attr, e: Var, v: Var,
    optSeqs: Option[Seq[Seq[T]]],
    tpe: String,
    resSeqOpt: ResSeqOpt[T],
    toDatalog: T => String
  ): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    optSeqs.fold[Unit] {
      none(attr, e, v, resSeqOpt)
    } { sets =>
      find += s"(distinct $v)"
      has(attr, e, v, sets, tpe, toDatalog)
    }
  }


  // hasNo ---------------------------------------------------------------------

  private def hasNo[T](
    attr: Attr, e: Var, v: Var,
    seqs: Seq[Seq[T]], tpe: String, toDatalog: T => String
  ): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    // Common for pre-query and main query
    where += s"[$e $a $v]" -> wClause

    if (seqs.nonEmpty && seqs.flatten.nonEmpty) {
      // Pre-query
      preWhere += s"(rule$v $e)" -> wClause
      preRules ++= mkRules(attr, e, v, seqs, tpe, toDatalog)

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
    optSeqs: Option[Seq[Seq[T]]],
    tpe: String,
    toDatalog: T => String
  ): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    find += s"(distinct $v)"
    if (optSeqs.isDefined) {
      hasNo(attr, e, v, optSeqs.get, tpe, toDatalog)
    } else {
      where += s"[$e $a $v]" -> wClause
    }
  }


  // no value -----------------------------------------------------------------

  private def noValue(attr: Attr, e: Var): Unit = {
    //    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)

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
    attr: Attr, e: Var, v: Var, fn: String, optN: Option[Int], resSeq: ResSeq[T]
  ): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    checkAggrSet()
    lazy val n = optN.getOrElse(0)
    // Replace find/casting with aggregate function/cast
    find -= s"(distinct $v)"
    fn match {
      case "distinct" =>
        noBooleanSetAggr(resSeq)
        val (v1, v2, e1) = (v + 1, v + 2, e + 1)
        find += s"(distinct $v2)"
        where += s"[$e $a $v]" -> wClause
        where +=
          s"""[(datomic.api/q
             |          "[:find (distinct $v1)
             |            :in $$ $e1
             |            :where [$e1 $a $v1]]" $$ $e) [[$v2]]]""".stripMargin -> wClause
        replaceCast(resSeq.set2sets)

      case "min" =>
        noBooleanSetAggr(resSeq)
        find += s"(min 1 $v)"
        replaceCast(resSeq.vector2set)

      case "mins" =>
        noBooleanSetAggr(resSeq)
        find += s"(min $n $v)"
        replaceCast(resSeq.vector2set)

      case "max" =>
        noBooleanSetAggr(resSeq)
        find += s"(max 1 $v)"
        replaceCast(resSeq.vector2set)

      case "maxs" =>
        noBooleanSetAggr(resSeq)
        find += s"(max $n $v)"
        replaceCast(resSeq.vector2set)

      case "sample" =>
        noBooleanSetAggr(resSeq)
        find += s"(sample 1 $v)"
        replaceCast(resSeq.vector2set)

      case "samples" =>
        noBooleanSetAggr(resSeq)
        find += s"(sample $n $v)"
        replaceCast(resSeq.vector2set)

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


  // helpers -------------------------------------------------------------------

  private def none[T](attr: Attr, e: Var, v: Var, resSeqOpt: ResSeqOpt[T]): Unit = {
    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
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
      replaceCast(resSeqOpt.optAttr2s)
    }
  }

//  private def mkRules[T](
//    attr: Attr, e: Var, v: Var,
//    seqs: Seq[Seq[T]], tpe: String, toDatalog: T => String
//  ): Seq[String] = {
//    val (a, ai, av, i_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
//    tpe match {
//      case "Float" =>
//        seqs.flatMap {
//          case set if set.isEmpty => None
//          case set                => Some(
//            set.zipWithIndex.map { case (arg, i) =>
//              // Coerce Datomic float values for correct comparison (don't know why this is necessary)
//              // See example: https://clojurians-log.clojureverse.org/datomic/2019-10-29
//              s"""[$e $a $v$i] [(float $v$i) $v$i-float] [(= $v$i-float (float $arg))]"""
//            }.mkString(s"[(rule$v $e)\n    ", "\n    ", "]")
//          )
//        }
//      case "URI"   =>
//        seqs.flatMap {
//          case set if set.isEmpty => None
//          case set                => Some(
//            set.zipWithIndex.map { case (arg, i) =>
//              s"""[(ground (new java.net.URI "$arg")) $v$i-uri] [$e $a $v$i-uri]"""
//            }.mkString(s"[(rule$v $e)\n    ", "\n    ", "]")
//          )
//        }
//      case _       =>
//        seqs.flatMap {
//          case set if set.isEmpty => None
//          case set                => Some(
//            set.map(arg => s"[$e $a ${toDatalog(arg)}]")
//              .mkString(s"[(rule$v $e)\n    ", "\n    ", "]")
//          )
//        }
//    }
//  }

  private def noBooleanSetAggr[T](resSeq: ResSeq[T]): Unit = {
    if (resSeq.tpe == "Boolean")
      throw ModelError("Aggregate functions not implemented for Sets of boolean values.")
  }
  private def noBooleanSetCounts(n: Int): Unit = {
    if (n == -1)
      throw ModelError("Aggregate functions not implemented for Sets of boolean values.")
  }
}