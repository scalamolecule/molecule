package molecule.db.datomic.query

import java.lang.{Boolean => jBoolean, Double => jDouble, Float => jFloat, Integer => jInteger, Long => jLong}
import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
import java.net.URI
import java.util.{Date, UUID, List => jList, Map => jMap, Set => jSet}
import molecule.boilerplate.api.Keywords._
import molecule.boilerplate.ast.MoleculeModel._
import scala.reflect.ClassTag

trait ExprOne[Tpl] { self: Sort[Tpl] with Base[Tpl] =>

  private lazy val fromBigInt = (v: Any) => v.asInstanceOf[BigInt].bigInteger.asInstanceOf[Any]
  private lazy val fromBigDec = (v: Any) => v.asInstanceOf[BigDecimal].bigDecimal.asInstanceOf[Any]
  private lazy val fromChar   = (v: Any) => v.asInstanceOf[Char].toString.asInstanceOf[Any]
  private lazy val fromByte   = (v: Any) => v.asInstanceOf[Byte].toInt.asInstanceOf[Any]
  private lazy val fromShort  = (v: Any) => v.asInstanceOf[Short].toInt.asInstanceOf[Any]

  // Datomic Java to Scala type converters
  private lazy val toBigInt = (v: AnyRef) => v match {
    case v: jBigInt => BigInt(v).asInstanceOf[AnyRef]
    // todo: can we avoid dependency on clojure here?
    case v: clojure.lang.BigInt => BigInt(v.toBigInteger).asInstanceOf[AnyRef]
    //
    //      case v: Number => BigInt(v.longValue()).asInstanceOf[AnyRef]
    //    BigInt(v.asInstanceOf[jBigInt]).asInstanceOf[AnyRef]
  }
  private lazy val toBigDec = (v: AnyRef) => BigDecimal(v.asInstanceOf[jBigDecimal]).asInstanceOf[AnyRef]
  private lazy val toChar   = (v: AnyRef) => v.asInstanceOf[String].charAt(0).asInstanceOf[AnyRef]
  private lazy val toByte   = (v: AnyRef) => v match {
    case v: Integer => v.toByte.asInstanceOf[AnyRef]
    case v: jLong   => v.toByte.asInstanceOf[AnyRef]
  }
  private lazy val toShort  = (v: AnyRef) => v match {
    case v: Integer => v.toShort.asInstanceOf[AnyRef]
    case v: jLong   => v.toShort.asInstanceOf[AnyRef]
  }

  private lazy val dString : String => String     = (v: String) => "\"" + escStr(v) + "\""
  private lazy val dInt    : Int => String        = (v: Int) => v.toString
  private lazy val dLong   : Long => String       = (v: Long) => v.toString
  private lazy val dFloat  : Float => String      = (v: Float) => "(float " + v.toString + ")"
  private lazy val dDouble : Double => String     = (v: Double) => v.toString
  private lazy val dBoolean: Boolean => String    = (v: Boolean) => v.toString
  private lazy val dBigInt : BigInt => String     = (v: BigInt) => v.toString + "N"
  private lazy val dBigDec : BigDecimal => String = (v: BigDecimal) => v.toString + "M"
  private lazy val dDate   : Date => String       = (v: Date) => "#inst \"" + date2datomicStr2(v) + "\""
  private lazy val dUUID   : UUID => String       = (v: UUID) => "#uuid \"" + v.toString + "\""
  private lazy val dURI    : URI => String        = (v: URI) => v.toString
  private lazy val dByte   : Byte => String       = (v: Byte) => v.toString
  private lazy val dShort  : Short => String      = (v: Short) => v.toString
  private lazy val dChar   : Char => String       = (v: Char) => "\"" + v.toString + "\""

  // Single sample value extracted from clojure LazySeq
  private lazy val seq2String     = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[AnyRef]
  private lazy val seq2Int        = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[AnyRef]
  private lazy val seq2Long       = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[AnyRef]
  private lazy val seq2Float      = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[AnyRef]
  private lazy val seq2Double     = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[AnyRef]
  private lazy val seq2Boolean    = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[AnyRef]
  private lazy val seq2BigInt     = (v: AnyRef) => BigInt(v.asInstanceOf[jList[_]].get(0).asInstanceOf[jBigInt]).asInstanceOf[AnyRef]
  private lazy val seq2BigDecimal = (v: AnyRef) => BigDecimal(v.asInstanceOf[jList[_]].get(0).asInstanceOf[jBigDecimal]).asInstanceOf[AnyRef]
  private lazy val seq2Date       = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[AnyRef]
  private lazy val seq2UUID       = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[AnyRef]
  private lazy val seq2URI        = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[AnyRef]
  private lazy val seq2Byte       = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[Integer].toByte.asInstanceOf[AnyRef]
  private lazy val seq2Short      = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[Integer].toShort.asInstanceOf[AnyRef]
  private lazy val seq2Char       = (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[String].charAt(0).asInstanceOf[AnyRef]

  private lazy val set2listString     = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val set2listInt        = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val set2listLong       = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val set2listFloat      = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val set2listDouble     = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val set2listBoolean    = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val set2listBigInt     = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toList.map(v => BigInt(v.toString)).asInstanceOf[AnyRef]
  private lazy val set2listBigDecimal = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toList.map(v => BigDecimal(v.toString)).asInstanceOf[AnyRef]
  private lazy val set2listDate       = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val set2listUUID       = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val set2listURI        = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val set2listByte       = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toList.map(_.asInstanceOf[Integer].toByte).asInstanceOf[AnyRef]
  private lazy val set2listShort      = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toList.map(_.asInstanceOf[Integer].toShort).asInstanceOf[AnyRef]
  private lazy val set2listChar       = (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toList.map(_.asInstanceOf[String].charAt(0)).asInstanceOf[AnyRef]

  private lazy val vector2listString     = (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val vector2listInt        = (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val vector2listLong       = (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val vector2listFloat      = (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val vector2listDouble     = (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val vector2listBoolean    = (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val vector2listBigInt     = (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toList.map(v => BigInt(v.toString)).asInstanceOf[AnyRef]
  private lazy val vector2listBigDecimal = (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toList.map(v => BigDecimal(v.toString)).asInstanceOf[AnyRef]
  private lazy val vector2listDate       = (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val vector2listUUID       = (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val vector2listURI        = (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toList.asInstanceOf[AnyRef]
  private lazy val vector2listByte       = (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toList.map(_.asInstanceOf[Integer].toByte).asInstanceOf[AnyRef]
  private lazy val vector2listShort      = (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toList.map(_.asInstanceOf[Integer].toShort).asInstanceOf[AnyRef]
  private lazy val vector2listChar       = (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toList.map(_.asInstanceOf[String].charAt(0)).asInstanceOf[AnyRef]


  case class Res[T](
    tpe: String,
    toDatalog: T => String,
    fromScala: Any => Any,
    toScala: AnyRef => AnyRef,
    seq2t: AnyRef => AnyRef,
    set2list: AnyRef => AnyRef,
    vector2list: AnyRef => AnyRef,
  )

  private lazy val resString     = Res("String", dString, identity, identity, seq2String, set2listString, vector2listString)
  private lazy val resInt        = Res("Int", dInt, identity, identity, seq2Int, set2listInt, vector2listInt)
  private lazy val resLong       = Res("Long", dLong, identity, identity, seq2Long, set2listLong, vector2listLong)
  private lazy val resFloat      = Res("Float", dFloat, identity, identity, seq2Float, set2listFloat, vector2listFloat)
  private lazy val resDouble     = Res("Double", dDouble, identity, identity, seq2Double, set2listDouble, vector2listDouble)
  private lazy val resBoolean    = Res("Boolean", dBoolean, identity, identity, seq2Boolean, set2listBoolean, vector2listBoolean)
  private lazy val resBigInt     = Res("BigInt", dBigInt, fromBigInt, toBigInt, seq2BigInt, set2listBigInt, vector2listBigInt)
  private lazy val resBigDecimal = Res("BigDecimal", dBigDec, fromBigDec, toBigDec, seq2BigDecimal, set2listBigDecimal, vector2listBigDecimal)
  private lazy val resDate       = Res("Date", dDate, identity, identity, seq2Date, set2listDate, vector2listDate)
  private lazy val resUUID       = Res("UUID", dUUID, identity, identity, seq2UUID, set2listUUID, vector2listUUID)
  private lazy val resURI        = Res("URI", dURI, identity, identity, seq2URI, set2listURI, vector2listURI)
  private lazy val resByte       = Res("Byte", dByte, fromByte, toByte, seq2Byte, set2listByte, vector2listByte)
  private lazy val resShort      = Res("Short", dShort, fromShort, toShort, seq2Short, set2listShort, vector2listShort)
  private lazy val resChar       = Res("Char", dChar, fromChar, toChar, seq2Char, set2listChar, vector2listChar)


  protected def resolveAtomOneMan(es: List[Var], atom: AtomOneMan): List[Var] = {
    attrIndex += 1
    val (e, a) = (es.last, s":${atom.ns}/${atom.attr}")
    atom match {
      case at: AtomOneManString     => atomMan(e, a, at.op, at.vs, resString, sortString(at, attrIndex))
      case at: AtomOneManInt        => atomMan(e, a, at.op, at.vs, resInt, sortInt(at, attrIndex))
      case at: AtomOneManLong       => atomMan(e, a, at.op, at.vs, resLong, sortLong(at, attrIndex))
      case at: AtomOneManFloat      => atomMan(e, a, at.op, at.vs, resFloat, sortFloat(at, attrIndex))
      case at: AtomOneManDouble     => atomMan(e, a, at.op, at.vs, resDouble, sortDouble(at, attrIndex))
      case at: AtomOneManBoolean    => atomMan(e, a, at.op, at.vs, resBoolean, sortBoolean(at, attrIndex))
      case at: AtomOneManBigInt     => atomMan(e, a, at.op, at.vs, resBigInt, sortBigInt(at, attrIndex))
      case at: AtomOneManBigDecimal => atomMan(e, a, at.op, at.vs, resBigDecimal, sortBigDecimal(at, attrIndex))
      case at: AtomOneManDate       => atomMan(e, a, at.op, at.vs, resDate, sortDate(at, attrIndex))
      case at: AtomOneManUUID       => atomMan(e, a, at.op, at.vs, resUUID, sortUUID(at, attrIndex))
      case at: AtomOneManURI        => atomMan(e, a, at.op, at.vs, resURI, sortURI(at, attrIndex))
      case at: AtomOneManByte       => atomMan(e, a, at.op, at.vs, resByte, sortByte(at, attrIndex))
      case at: AtomOneManShort      => atomMan(e, a, at.op, at.vs, resShort, sortShort(at, attrIndex))
      case at: AtomOneManChar       => atomMan(e, a, at.op, at.vs, resChar, sortChar(at, attrIndex))
    }
    es
  }

  protected def atomMan[T: ClassTag](
    e: Var,
    a: Attr,
    op: Op,
    args: Seq[T],
    res: Res[T],
    sorter: Option[(Int, (Row, Row) => Int)]
  ): Unit = {
    val v = vv
    find += v
    castScala += res.toScala
    sorter.foreach(sorts += _)
    expr(e, a, v, op, args, res)
  }


  protected def resolveAtomOneTac(es: List[Var], atom: AtomOneTac): List[Var] = {
    val (e, a) = (es.last, s":${atom.ns}/${atom.attr}")
    atom match {
      case at: AtomOneTacString     => atomTac(e, a, at.op, at.vs, resString)
      case at: AtomOneTacInt        => atomTac(e, a, at.op, at.vs, resInt)
      case at: AtomOneTacLong       => atomTac(e, a, at.op, at.vs, resLong)
      case at: AtomOneTacFloat      => atomTac(e, a, at.op, at.vs, resFloat)
      case at: AtomOneTacDouble     => atomTac(e, a, at.op, at.vs, resDouble)
      case at: AtomOneTacBoolean    => atomTac(e, a, at.op, at.vs, resBoolean)
      case at: AtomOneTacBigInt     => atomTac(e, a, at.op, at.vs, resBigInt)
      case at: AtomOneTacBigDecimal => atomTac(e, a, at.op, at.vs, resBigDecimal)
      case at: AtomOneTacDate       => atomTac(e, a, at.op, at.vs, resDate)
      case at: AtomOneTacUUID       => atomTac(e, a, at.op, at.vs, resUUID)
      case at: AtomOneTacURI        => atomTac(e, a, at.op, at.vs, resURI)
      case at: AtomOneTacByte       => atomTac(e, a, at.op, at.vs, resByte)
      case at: AtomOneTacShort      => atomTac(e, a, at.op, at.vs, resShort)
      case at: AtomOneTacChar       => atomTac(e, a, at.op, at.vs, resChar)
    }
    es
  }

  private def atomTac[T: ClassTag](
    e: Var,
    a: Attr,
    op: Op,
    args: Seq[T],
    res: Res[T],
  ): Unit = {
    val v = vv
    expr(e, a, v, op, args, res)
  }

  private def expr[T: ClassTag](
    e: Var,
    a: Attr,
    v: Var,
    op: Op,
    args: Seq[T],
    res: Res[T],
  ): Unit = {
    op match {
      case V         => attr(e, a, v)
      case Eq        => equal(e, a, v, args, res.fromScala)
      case Neq       => neq(e, a, v, args, res.tpe, res.toDatalog)
      case Lt        => compare(e, a, v, args.head, "<", res.fromScala)
      case Gt        => compare(e, a, v, args.head, ">", res.fromScala)
      case Le        => compare(e, a, v, args.head, "<=", res.fromScala)
      case Ge        => compare(e, a, v, args.head, ">=", res.fromScala)
      case NoValue   => noValue(e, a, v)
      case Fn(kw, _) => aggr(e, a, v, kw, res)
      case other     => unexpected(other)
    }
  }


  private lazy val toInt = (v: AnyRef) => v.asInstanceOf[Integer].toInt.asInstanceOf[AnyRef]

  private def aggr[T](e: Var, a: Attr, v: Var, fn: Kw, res: Res[T]): Unit = {
    // Replace find/casting with aggregate function/cast
    find -= v
    fn match {
      case _: distinct =>
        find += s"(distinct $v)"
        castScala -= res.toScala
        castScala += res.set2list

      case mins(n) =>
        find += s"(min $n $v)"
        castScala -= res.toScala
        castScala += res.vector2list

      case _: min =>
        find += s"(min $v)"

      case maxs(n) =>
        find += s"(max $n $v)"
        castScala -= res.toScala
        castScala += res.vector2list

      case _: max =>
        find += s"(max $v)"

      case rands(n) =>
        find += s"(rand $n $v)"
        castScala -= res.toScala
        castScala += res.vector2list

      case _: rand =>
        find += s"(rand $v)"

      case samples(n) =>
        find += s"(sample $n $v)"
        castScala -= res.toScala
        castScala += res.vector2list

      case _: sample =>
        // Have to add "1" for some reason
        find += s"(sample 1 $v)"
        castScala -= res.toScala
        castScala += res.seq2t

      case _: count =>
        find += s"(count $v)"
        widh += e
        castScala -= res.toScala
        castScala += toInt

      case _: countDistinct =>
        find += s"(count-distinct $v)"
        widh += e
        castScala -= res.toScala
        castScala += toInt

      case _: sum =>
        find += s"(sum $v)"

      case _: median =>
        find += s"(median $v)"

      case _: avg =>
        find += s"(avg $v)"

      case _: variance =>
        find += s"(variance $v)"

      case _: stddev =>
        find += s"(stddev $v)"

    }
    where += s"[$e $a $v$tx]" -> wClause
  }

  private def attr(e: Var, a: Attr, v: Var): Unit = {
    where += s"[$e $a $v$tx]" -> wClause
  }

  private def equal[T: ClassTag](e: Var, a: Attr, v: Var, args: Seq[T], fromScala: Any => Any): Unit = {
    in += s"[$v ...]"
    where += s"[$e $a $v$tx]" -> wClause
    inputs += args.map(fromScala).toArray
  }

  private def neq[T](e: Var, a: Attr, v: Var, args: Seq[T], tpe: String, toDatalog: T => String): Unit = {
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

  private def compare[T](e: Var, a: Attr, v: Var, arg: T, op: String, fromScala: Any => Any): Unit = {
    val v1 = v + 1
    in += v1
    where += s"[$e $a $v$tx]" -> wClause
    where += s"[($op $v $v1)]" -> wNeqOne
    inputs += fromScala(arg).asInstanceOf[AnyRef]
  }

  private def noValue(e: Var, a: Attr, v: String): Unit = {
    where += s"(not [$e $a])" -> wNeqOne
  }


  private lazy val toOptString = (v: AnyRef) => (v match {
    case null      => Option.empty[String]
    case v: String => Some(v)
    case v         => Some(v.asInstanceOf[jMap[_, _]].values.iterator.next.asInstanceOf[String])
  }).asInstanceOf[AnyRef]

  private lazy val toOptInt = (v: AnyRef) => (v match {
    case null        => Option.empty[Int]
    case v: jInteger => Some(v.toInt)
    case v           => Some(v.asInstanceOf[jMap[_, _]].values.iterator.next.asInstanceOf[Integer].toInt)
  }).asInstanceOf[AnyRef]

  private lazy val toOptLong = (v: AnyRef) => (v match {
    case null     => Option.empty[Long]
    case v: jLong => Some(v)
    case v        => Some(v.asInstanceOf[jMap[_, _]].values.iterator.next.asInstanceOf[Long])
  }).asInstanceOf[AnyRef]

  private lazy val toOptFloat = (v: AnyRef) => (v match {
    case null      => Option.empty[Float]
    case v: jFloat => Some(v.toFloat)
    case v         => Some(v.asInstanceOf[jMap[_, _]].values.iterator.next.asInstanceOf[Float])
  }).asInstanceOf[AnyRef]

  private lazy val toOptDouble = (v: AnyRef) => (v match {
    case null       => Option.empty[Double]
    case v: jDouble => Some(v)
    case v          => Some(v.asInstanceOf[jMap[_, _]].values.iterator.next.asInstanceOf[Double])
  }).asInstanceOf[AnyRef]

  private lazy val toOptBoolean = (v: AnyRef) => (v match {
    case null        => Option.empty[Boolean]
    case v: jBoolean => Some(v)
    case v           => Some(v.asInstanceOf[jMap[_, _]].values.iterator.next.asInstanceOf[Boolean])
  }).asInstanceOf[AnyRef]

  private lazy val toOptBigInt = (v: AnyRef) => (v match {
    case null       => Option.empty[BigInt]
    case v: jBigInt => Some(BigInt(v))
    case v          => Some(
      BigInt(v.asInstanceOf[jMap[_, _]].values.iterator.next.asInstanceOf[jBigInt])
    )
  }).asInstanceOf[AnyRef]

  private lazy val toOptBigDecimal = (v: AnyRef) => (v match {
    case null           => Option.empty[BigDecimal]
    case v: jBigDecimal => Some(BigDecimal(v))
    case v              => Some(
      BigDecimal(v.asInstanceOf[jMap[_, _]].values.iterator.next.asInstanceOf[jBigDecimal])
    )
  }).asInstanceOf[AnyRef]

  private lazy val toOptDate = (v: AnyRef) => (v match {
    case null    => Option.empty[Date]
    case v: Date => Some(v)
    case v       => Some(v.asInstanceOf[jMap[_, _]].values.iterator.next.asInstanceOf[Date])
  }).asInstanceOf[AnyRef]

  private lazy val toOptUUID = (v: AnyRef) => (v match {
    case null    => Option.empty[UUID]
    case v: UUID => Some(v)
    case v       => Some(v.asInstanceOf[jMap[_, _]].values.iterator.next.asInstanceOf[UUID])
  }).asInstanceOf[AnyRef]

  private lazy val toOptURI = (v: AnyRef) => (v match {
    case null   => Option.empty[URI]
    case v: URI => Some(v)
    case v      => Some(v.asInstanceOf[jMap[_, _]].values.iterator.next.asInstanceOf[URI])
  }).asInstanceOf[AnyRef]

  private lazy val toOptByte = (v: AnyRef) => (v match {
    case null        => Option.empty[Byte]
    case v: jInteger => Some(v.toByte)
    case v           => Some(v.asInstanceOf[jMap[_, _]].values.iterator.next.asInstanceOf[Integer].toByte)
  }).asInstanceOf[AnyRef]

  private lazy val toOptShort = (v: AnyRef) => (v match {
    case null        => Option.empty[Short]
    case v: jInteger => Some(v.toShort)
    case v           => Some(v.asInstanceOf[jMap[_, _]].values.iterator.next.asInstanceOf[Integer].toShort)
  }).asInstanceOf[AnyRef]

  private lazy val toOptChar = (v: AnyRef) => (v match {
    case null      => Option.empty[Char]
    case v: String => Some(v.head)
    case v         => Some(v.asInstanceOf[jMap[_, _]].values.iterator.next.asInstanceOf[String].charAt(0))
  }).asInstanceOf[AnyRef]


  case class OptRes[T](
    tpe: String,
    toDatalog: T => String,
    fromScala: Any => Any,
    toScala: AnyRef => AnyRef,
  )

  private lazy val optString     = OptRes("String", dString, identity, toOptString)
  private lazy val optInt        = OptRes("Int", dInt, identity, toOptInt)
  private lazy val optLong       = OptRes("Long", dLong, identity, toOptLong)
  private lazy val optFloat      = OptRes("Float", dFloat, identity, toOptFloat)
  private lazy val optDouble     = OptRes("Double", dDouble, identity, toOptDouble)
  private lazy val optBoolean    = OptRes("Boolean", dBoolean, identity, toOptBoolean)
  private lazy val optBigInt     = OptRes("BigInt", dBigInt, fromBigInt, toOptBigInt)
  private lazy val optBigDecimal = OptRes("BigDecimal", dBigDec, fromBigDec, toOptBigDecimal)
  private lazy val optDate       = OptRes("Date", dDate, identity, toOptDate)
  private lazy val optUUID       = OptRes("UUID", dUUID, identity, toOptUUID)
  private lazy val optURI        = OptRes("URI", dURI, identity, toOptURI)
  private lazy val optByte       = OptRes("Byte", dByte, fromByte, toOptByte)
  private lazy val optShort      = OptRes("Short", dShort, fromShort, toOptShort)
  private lazy val optChar       = OptRes("Char", dChar, fromChar, toOptChar)

  protected def resolveAtomOneOpt(es: List[Var], atom: AtomOneOpt): List[Var] = {
    attrIndex += 1
    val (e, a) = (es.last, s":${atom.ns}/${atom.attr}")
    atom match {
      case at: AtomOneOptString     => atomOpt(e, a, at.op, at.vs, optString, sortString(at, attrIndex))
      case at: AtomOneOptInt        => atomOpt(e, a, at.op, at.vs, optInt, sortInt(at, attrIndex))
      case at: AtomOneOptLong       => atomOpt(e, a, at.op, at.vs, optLong, sortLong(at, attrIndex))
      case at: AtomOneOptFloat      => atomOpt(e, a, at.op, at.vs, optFloat, sortFloat(at, attrIndex))
      case at: AtomOneOptDouble     => atomOpt(e, a, at.op, at.vs, optDouble, sortDouble(at, attrIndex))
      case at: AtomOneOptBoolean    => atomOpt(e, a, at.op, at.vs, optBoolean, sortBoolean(at, attrIndex))
      case at: AtomOneOptBigInt     => atomOpt(e, a, at.op, at.vs, optBigInt, sortBigInt(at, attrIndex))
      case at: AtomOneOptBigDecimal => atomOpt(e, a, at.op, at.vs, optBigDecimal, sortBigDecimal(at, attrIndex))
      case at: AtomOneOptDate       => atomOpt(e, a, at.op, at.vs, optDate, sortDate(at, attrIndex))
      case at: AtomOneOptUUID       => atomOpt(e, a, at.op, at.vs, optUUID, sortUUID(at, attrIndex))
      case at: AtomOneOptURI        => atomOpt(e, a, at.op, at.vs, optURI, sortURI(at, attrIndex))
      case at: AtomOneOptByte       => atomOpt(e, a, at.op, at.vs, optByte, sortByte(at, attrIndex))
      case at: AtomOneOptShort      => atomOpt(e, a, at.op, at.vs, optShort, sortShort(at, attrIndex))
      case at: AtomOneOptChar       => atomOpt(e, a, at.op, at.vs, optChar, sortChar(at, attrIndex))
    }
    es
  }

  private def atomOpt[T: ClassTag](
    e: Var,
    a: Attr,
    op: Op,
    optArgs: Option[Seq[T]],
    res: OptRes[T],
    sorter: Option[(Int, (Row, Row) => Int)]
  ): Unit = {
    val v = vv
    castScala += res.toScala
    sorter.foreach(sorts += _)
    op match {
      case V     => optV(e, a, v)
      case Eq    => optEq(e, a, v, optArgs, res.fromScala)
      case Neq   => optNeq(e, a, v, optArgs, res.tpe, res.toDatalog)
      case Lt    => optCompare(e, a, v, optArgs, "<", res.fromScala)
      case Gt    => optCompare(e, a, v, optArgs, ">", res.fromScala)
      case Le    => optCompare(e, a, v, optArgs, "<=", res.fromScala)
      case Ge    => optCompare(e, a, v, optArgs, ">=", res.fromScala)
      case other => unexpected(other)
    }
  }

  private def optV(e: Var, a: Attr, v: Var) = {
    find += s"(pull $e-$v [[$a :limit nil]]) "
    where += s"[(identity $e) $e-$v]" -> wGround
  }

  private def optEq[T: ClassTag](e: Var, a: Attr, v: Var, optArgs: Option[Seq[T]], fromScala: Any => Any): Unit = {
    if (optArgs.isDefined) {
      find += v
      in += s"[$v ...]"
      where += s"[$e $a $v$tx]" -> wClause
      inputs += optArgs.get.map(fromScala).toArray
    } else {
      // None
      find += s"(pull $e-$v [[$a :limit nil]])"
      where += s"(not [$e $a])" -> wNeqOne
      where += s"[(identity $e) $e-$v]" -> wGround
    }
  }


  private def optNeq[T](e: Var, a: Attr, v: Var, optArgs: Option[Seq[T]], tpe: String, toDatalog: T => String): Unit = {
    find += v
    where += s"[$e $a $v$tx]" -> wClause
    if (optArgs.isDefined && optArgs.get.nonEmpty) {
      if (tpe == "URI") {
        optArgs.get.zipWithIndex.foreach { case (arg, i) =>
          where += s"""[(ground (new java.net.URI "$arg")) $v$i]""" -> wNeqOne
          where += s"[(!= $v $v$i)]" -> wNeqOne
        }
      } else {
        optArgs.get.foreach { arg =>
          where += s"[(!= $v ${toDatalog(arg)})]" -> wNeqOne
        }
      }
    }
  }

  private def optCompare[T](e: Var, a: Attr, v: Var, optArgs: Option[Seq[T]], op: String, fromScala: Any => Any): Unit = {
    if (optArgs.isDefined && optArgs.get.nonEmpty) {
      find += v
      val v1 = v + 1
      in += v1
      where += s"[$e $a $v$tx]" -> wClause
      where += s"[($op $v $v1)]" -> wNeqOne
      inputs += fromScala(optArgs.get.head).asInstanceOf[AnyRef]
    } else {
      // None - return null (clojure nil)
      find += s"$v-nil"
      where += s"[(ground nil) $v-nil]" -> wGround
    }
  }
}