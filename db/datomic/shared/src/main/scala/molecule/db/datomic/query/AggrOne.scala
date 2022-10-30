package molecule.db.datomic.query

import java.lang.{Boolean => jBoolean, Double => jDouble, Float => jFloat, Integer => jInteger, Long => jLong}
import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
import java.net.URI
import java.util.{Date, UUID}
import clojure.lang.PersistentArrayMap
import molecule.boilerplate.ast.MoleculeModel._
import scala.reflect.ClassTag;


trait AggrOne[Tpl] { self: Sort[Tpl] with Base[Tpl] =>

  final private lazy val fromBigInt = (v: Any) => v.asInstanceOf[BigInt].bigInteger.asInstanceOf[Any]
  final private lazy val fromBigDec = (v: Any) => v.asInstanceOf[BigDecimal].bigDecimal.asInstanceOf[Any]
  final private lazy val fromChar   = (v: Any) => v.asInstanceOf[Char].toString.asInstanceOf[Any]
  final private lazy val fromByte   = (v: Any) => v.asInstanceOf[Byte].toInt.asInstanceOf[Any]
  final private lazy val fromShort  = (v: Any) => v.asInstanceOf[Short].toInt.asInstanceOf[Any]

  // Datomic Java to Scala type converters
  final private lazy val toBigInt = (v: AnyRef) => BigInt(v.asInstanceOf[jBigInt]).asInstanceOf[AnyRef]
  final private lazy val toBigDec = (v: AnyRef) => BigDecimal(v.asInstanceOf[jBigDecimal]).asInstanceOf[AnyRef]
  final private lazy val toChar   = (v: AnyRef) => v.asInstanceOf[String].charAt(0).asInstanceOf[AnyRef]
  final private lazy val toByte   = (v: AnyRef) => v.asInstanceOf[Integer].toByte.asInstanceOf[AnyRef]
  final private lazy val toShort  = (v: AnyRef) => v.asInstanceOf[Integer].toShort.asInstanceOf[AnyRef]

  final private lazy val dString : String => String     = (v: String) => "\"" + escStr(v) + "\""
  final private lazy val dInt    : Int => String        = (v: Int) => v.toString
  final private lazy val dLong   : Long => String       = (v: Long) => v.toString
  final private lazy val dFloat  : Float => String      = (v: Float) => "(float " + v.toString + ")"
  final private lazy val dDouble : Double => String     = (v: Double) => v.toString
  final private lazy val dBoolean: Boolean => String    = (v: Boolean) => v.toString
  final private lazy val dBigInt : BigInt => String     = (v: BigInt) => v.toString + "N"
  final private lazy val dBigDec : BigDecimal => String = (v: BigDecimal) => v.toString + "M"
  final private lazy val dDate   : Date => String       = (v: Date) => "#inst \"" + date2datomicStr2(v) + "\""
  final private lazy val dUUID   : UUID => String       = (v: UUID) => "#uuid \"" + v.toString + "\""
  final private lazy val dURI    : URI => String        = (v: URI) => v.toString
  final private lazy val dByte   : Byte => String       = (v: Byte) => v.toString
  final private lazy val dShort  : Short => String      = (v: Short) => v.toString
  final private lazy val dChar   : Char => String       = (v: Char) => "\"" + v.toString + "\""


  protected def resolveAtomOneMan(es: List[Var], atom: AtomOneMan): List[Var] = {
    attrIndex += 1
    val (e, a) = (es.last, s":${atom.ns}/${atom.attr}")
    atom match {
      case at: AtomOneManString     => atomMan(e, a, at.op, at.vs, "String", dString, identity, identity, sortString(at, attrIndex))
      case at: AtomOneManInt        => atomMan(e, a, at.op, at.vs, "Int", dInt, identity, identity, sortInt(at, attrIndex))
      case at: AtomOneManLong       => atomMan(e, a, at.op, at.vs, "Long", dLong, identity, identity, sortLong(at, attrIndex))
      case at: AtomOneManFloat      => atomMan(e, a, at.op, at.vs, "Float", dFloat, identity, identity, sortFloat(at, attrIndex))
      case at: AtomOneManDouble     => atomMan(e, a, at.op, at.vs, "Double", dDouble, identity, identity, sortDouble(at, attrIndex))
      case at: AtomOneManBoolean    => atomMan(e, a, at.op, at.vs, "Boolean", dBoolean, identity, identity, sortBoolean(at, attrIndex))
      case at: AtomOneManBigInt     => atomMan(e, a, at.op, at.vs, "BigInt", dBigInt, fromBigInt, toBigInt, sortBigInt(at, attrIndex))
      case at: AtomOneManBigDecimal => atomMan(e, a, at.op, at.vs, "BigDecimal", dBigDec, fromBigDec, toBigDec, sortBigDecimal(at, attrIndex))
      case at: AtomOneManDate       => atomMan(e, a, at.op, at.vs, "Date", dDate, identity, identity, sortDate(at, attrIndex))
      case at: AtomOneManUUID       => atomMan(e, a, at.op, at.vs, "UUID", dUUID, identity, identity, sortUUID(at, attrIndex))
      case at: AtomOneManURI        => atomMan(e, a, at.op, at.vs, "URI", dURI, identity, identity, sortURI(at, attrIndex))
      case at: AtomOneManByte       => atomMan(e, a, at.op, at.vs, "Byte", dByte, fromByte, toByte, sortByte(at, attrIndex))
      case at: AtomOneManShort      => atomMan(e, a, at.op, at.vs, "Short", dShort, fromShort, toShort, sortShort(at, attrIndex))
      case at: AtomOneManChar       => atomMan(e, a, at.op, at.vs, "Char", dChar, fromChar, toChar, sortChar(at, attrIndex))
    }
    es
  }

  final protected def atomMan[T: ClassTag](
    e: Var,
    a: Attr,
    op: Op,
    args: Seq[T],
    tpe: String,
    toDatalog: T => String,
    fromScala: Any => Any,
    toScala: AnyRef => AnyRef,
    sorter: Option[(Int, (Row, Row) => Int)]
  ): Unit = {
    val v = vv
    find += v
    castScala += toScala
    sorter.foreach(sorts += _)
    expr(e, a, v, op, args, tpe, toDatalog, fromScala)
  }


  protected def resolveAtomOneTac(es: List[Var], atom: AtomOneTac): List[Var] = {
    val (e, a) = (es.last, s":${atom.ns}/${atom.attr}")
    atom match {
      case at: AtomOneTacString     => atomTac(e, a, at.op, at.vs, "String", dString, identity)
      case at: AtomOneTacInt        => atomTac(e, a, at.op, at.vs, "Int", dInt, identity)
      case at: AtomOneTacLong       => atomTac(e, a, at.op, at.vs, "Long", dLong, identity)
      case at: AtomOneTacFloat      => atomTac(e, a, at.op, at.vs, "Float", dFloat, identity)
      case at: AtomOneTacDouble     => atomTac(e, a, at.op, at.vs, "Double", dDouble, identity)
      case at: AtomOneTacBoolean    => atomTac(e, a, at.op, at.vs, "Boolean", dBoolean, identity)
      case at: AtomOneTacBigInt     => atomTac(e, a, at.op, at.vs, "BigInt", dBigInt, fromBigInt)
      case at: AtomOneTacBigDecimal => atomTac(e, a, at.op, at.vs, "BigDecimal", dBigDec, fromBigDec)
      case at: AtomOneTacDate       => atomTac(e, a, at.op, at.vs, "Date", dDate, identity)
      case at: AtomOneTacUUID       => atomTac(e, a, at.op, at.vs, "UUID", dUUID, identity)
      case at: AtomOneTacURI        => atomTac(e, a, at.op, at.vs, "URI", dURI, identity)
      case at: AtomOneTacByte       => atomTac(e, a, at.op, at.vs, "Byte", dByte, fromByte)
      case at: AtomOneTacShort      => atomTac(e, a, at.op, at.vs, "Short", dShort, fromShort)
      case at: AtomOneTacChar       => atomTac(e, a, at.op, at.vs, "Char", dChar, fromChar)
    }
    es
  }

  private def atomTac[T: ClassTag](
    e: Var,
    a: Attr,
    op: Op,
    args: Seq[T],
    tpe: String,
    toDatalog: T => String,
    fromScala: Any => Any,
  ): Unit = {
    val v = vv
    expr(e, a, v, op, args, tpe, toDatalog, fromScala)
  }

  private def expr[T: ClassTag](
    e: Var,
    a: Attr,
    v: Var,
    op: Op,
    args: Seq[T],
    tpe: String,
    toDatalog: T => String,
    fromScala: Any => Any,
  ): Unit = {
    op match {
      case V       => attr(e, a, v)
      case Eq      => equal(e, a, v, args, fromScala)
      case Neq     => neq(e, a, v, args, tpe, toDatalog)
      case Lt      => compare(e, a, v, args.head, "<", fromScala)
      case Gt      => compare(e, a, v, args.head, ">", fromScala)
      case Le      => compare(e, a, v, args.head, "<=", fromScala)
      case Ge      => compare(e, a, v, args.head, ">=", fromScala)
      case NoValue => noValue(e, a, v)
      case other   => unexpected(other)
    }
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


  final private lazy val toOptString = (v: AnyRef) => (v match {
    case null      => Option.empty[String]
    case v: String => Some(v)
    case v         => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[String])
  }).asInstanceOf[AnyRef]

  final private lazy val toOptInt = (v: AnyRef) => (v match {
    case null        => Option.empty[Int]
    case v: jInteger => Some(v.toInt)
    case v           => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[Integer].toInt)
  }).asInstanceOf[AnyRef]

  final private lazy val toOptLong = (v: AnyRef) => (v match {
    case null     => Option.empty[Long]
    case v: jLong => Some(v)
    case v        => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[Long])
  }).asInstanceOf[AnyRef]

  final private lazy val toOptFloat = (v: AnyRef) => (v match {
    case null      => Option.empty[Float]
    case v: jFloat => Some(v.toFloat)
    case v         => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[Float])
  }).asInstanceOf[AnyRef]

  final private lazy val toOptDouble = (v: AnyRef) => (v match {
    case null       => Option.empty[Double]
    case v: jDouble => Some(v)
    case v          => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[Double])
  }).asInstanceOf[AnyRef]

  final private lazy val toOptBoolean = (v: AnyRef) => (v match {
    case null        => Option.empty[Boolean]
    case v: jBoolean => Some(v)
    case v           => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[Boolean])
  }).asInstanceOf[AnyRef]

  final private lazy val toOptBigInt = (v: AnyRef) => (v match {
    case null       => Option.empty[BigInt]
    case v: jBigInt => Some(BigInt(v))
    case v          => Some(
      BigInt(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[jBigInt])
    )
  }).asInstanceOf[AnyRef]

  final private lazy val toOptBigDecimal = (v: AnyRef) => (v match {
    case null           => Option.empty[BigDecimal]
    case v: jBigDecimal => Some(BigDecimal(v))
    case v              => Some(
      BigDecimal(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[jBigDecimal])
    )
  }).asInstanceOf[AnyRef]

  final private lazy val toOptDate = (v: AnyRef) => (v match {
    case null    => Option.empty[Date]
    case v: Date => Some(v)
    case v       => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[Date])
  }).asInstanceOf[AnyRef]

  final private lazy val toOptUUID = (v: AnyRef) => (v match {
    case null    => Option.empty[UUID]
    case v: UUID => Some(v)
    case v       => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[UUID])
  }).asInstanceOf[AnyRef]

  final private lazy val toOptURI = (v: AnyRef) => (v match {
    case null   => Option.empty[URI]
    case v: URI => Some(v)
    case v      => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[URI])
  }).asInstanceOf[AnyRef]

  final private lazy val toOptByte = (v: AnyRef) => (v match {
    case null        => Option.empty[Byte]
    case v: jInteger => Some(v.toByte)
    case v           => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[Integer].toByte)
  }).asInstanceOf[AnyRef]

  final private lazy val toOptShort = (v: AnyRef) => (v match {
    case null        => Option.empty[Short]
    case v: jInteger => Some(v.toShort)
    case v           => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[Integer].toShort)
  }).asInstanceOf[AnyRef]

  final private lazy val toOptChar = (v: AnyRef) => (v match {
    case null      => Option.empty[Char]
    case v: String => Some(v.head)
    case v         => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[String].charAt(0))
  }).asInstanceOf[AnyRef]


  protected def resolveAtomOneOpt(es: List[Var], atom: AtomOneOpt): List[Var] = {
    attrIndex += 1
    val (e, a) = (es.last, s":${atom.ns}/${atom.attr}")
    atom match {
      case at: AtomOneOptString     => atomOpt(e, a, at.op, at.vs, "String", dString, identity, toOptString, sortString(at, attrIndex))
      case at: AtomOneOptInt        => atomOpt(e, a, at.op, at.vs, "Int", dInt, identity, toOptInt, sortInt(at, attrIndex))
      case at: AtomOneOptLong       => atomOpt(e, a, at.op, at.vs, "Long", dLong, identity, toOptLong, sortLong(at, attrIndex))
      case at: AtomOneOptFloat      => atomOpt(e, a, at.op, at.vs, "Float", dFloat, identity, toOptFloat, sortFloat(at, attrIndex))
      case at: AtomOneOptDouble     => atomOpt(e, a, at.op, at.vs, "Double", dDouble, identity, toOptDouble, sortDouble(at, attrIndex))
      case at: AtomOneOptBoolean    => atomOpt(e, a, at.op, at.vs, "Boolean", dBoolean, identity, toOptBoolean, sortBoolean(at, attrIndex))
      case at: AtomOneOptBigInt     => atomOpt(e, a, at.op, at.vs, "BigInt", dBigInt, fromBigInt, toOptBigInt, sortBigInt(at, attrIndex))
      case at: AtomOneOptBigDecimal => atomOpt(e, a, at.op, at.vs, "BigDecimal", dBigDec, fromBigDec, toOptBigDecimal, sortBigDecimal(at, attrIndex))
      case at: AtomOneOptDate       => atomOpt(e, a, at.op, at.vs, "Date", dDate, identity, toOptDate, sortDate(at, attrIndex))
      case at: AtomOneOptUUID       => atomOpt(e, a, at.op, at.vs, "UUID", dUUID, identity, toOptUUID, sortUUID(at, attrIndex))
      case at: AtomOneOptURI        => atomOpt(e, a, at.op, at.vs, "URI", dURI, identity, toOptURI, sortURI(at, attrIndex))
      case at: AtomOneOptByte       => atomOpt(e, a, at.op, at.vs, "Byte", dByte, fromByte, toOptByte, sortByte(at, attrIndex))
      case at: AtomOneOptShort      => atomOpt(e, a, at.op, at.vs, "Short", dShort, fromShort, toOptShort, sortShort(at, attrIndex))
      case at: AtomOneOptChar       => atomOpt(e, a, at.op, at.vs, "Char", dChar, fromChar, toOptChar, sortChar(at, attrIndex))
    }
    es
  }

  private def atomOpt[T: ClassTag](
    e: Var,
    a: Attr,
    op: Op,
    optArgs: Option[Seq[T]],
    tpe: String,
    toDatalog: T => String,
    fromScala: Any => Any,
    toScala: AnyRef => AnyRef,
    sorter: Option[(Int, (Row, Row) => Int)]
  ): Unit = {
    val v = vv
    castScala += toScala
    sorter.foreach(sorts += _)
    op match {
      case V     => optV(e, a, v)
      case Eq    => optEq(e, a, v, optArgs, fromScala)
      case Neq   => optNeq(e, a, v, optArgs, tpe, toDatalog)
      case Lt    => optCompare(e, a, v, optArgs, "<", fromScala)
      case Gt    => optCompare(e, a, v, optArgs, ">", fromScala)
      case Le    => optCompare(e, a, v, optArgs, "<=", fromScala)
      case Ge    => optCompare(e, a, v, optArgs, ">=", fromScala)
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