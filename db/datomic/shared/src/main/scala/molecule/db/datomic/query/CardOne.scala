package molecule.db.datomic.query

import java.lang.{Boolean => jBoolean, Double => jDouble, Integer => jInteger, Long => jLong}
import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
import java.net.URI
import java.util.{Date, UUID}
import clojure.lang.PersistentArrayMap
import molecule.boilerplate.ast.MoleculeModel._
import scala.reflect.ClassTag;


trait CardOne[Tpl] { self: Sort[Tpl] with Base[Tpl] =>

  // Datomic Java to Scala type converters
  final private lazy val tInt    = (v: AnyRef) => v.asInstanceOf[Integer].toInt.asInstanceOf[AnyRef]
  final private lazy val tBigInt = (v: AnyRef) => BigInt(v.asInstanceOf[jBigInt]).asInstanceOf[AnyRef]
  final private lazy val tBigDec = (v: AnyRef) => BigDecimal(v.asInstanceOf[jBigDecimal]).asInstanceOf[AnyRef]
  final private lazy val tChar   = (v: AnyRef) => v.asInstanceOf[String].charAt(0).asInstanceOf[AnyRef]
  final private lazy val tByte   = (v: AnyRef) => v.asInstanceOf[Integer].toByte.asInstanceOf[AnyRef]
  final private lazy val tShort  = (v: AnyRef) => v.asInstanceOf[Integer].toShort.asInstanceOf[AnyRef]

  final private lazy val rString    : String => String     = (v: String) => "\"" + escStr(v) + "\""
  final private lazy val rInt       : Int => String        = (v: Int) => v.toString
  final private lazy val rLong      : Long => String       = (v: Long) => v.toString
  final private lazy val rFloat     : Float => String      = (v: Float) => v.toString
  final private lazy val rDouble    : Double => String     = (v: Double) => v.toString
  final private lazy val rBoolean   : Boolean => String    = (v: Boolean) => v.toString
  final private lazy val rBigInt    : BigInt => String     = (v: BigInt) => v.toString + "N"
  final private lazy val rBigDecimal: BigDecimal => String = (v: BigDecimal) => v.toString + "M"
  final private lazy val rDate      : Date => String       = (v: Date) => "#inst \"" + date2datomicStr2(v) + "\""
  final private lazy val rUUID      : UUID => String       = (v: UUID) => "\"" + v.toString + "\""
  final private lazy val rURI       : URI => String        = (v: URI) => v.toString
  final private lazy val rByte      : Byte => String       = (v: Byte) => v.toString
  final private lazy val rShort     : Short => String      = (v: Short) => v.toString
  final private lazy val rChar      : Char => String       = (v: Char) => v.toString


  protected def resolveAtomOneMan(es: List[Var], atom: AtomOneMan): List[Var] = {
    attrIndex += 1
    val (e, a) = (es.last, s":${atom.ns}/${atom.attr}")
    atom match {
      case at: AtomOneManString     => atomMan(e, a, at.op, at.vs, "String", rString, identity, sortString(at))
      case at: AtomOneManInt        => atomMan(e, a, at.op, at.vs, "Int", rInt, tInt, sortInt(at))
      case at: AtomOneManLong       => atomMan(e, a, at.op, at.vs, "Long", rLong, identity, sortLong(at))
      case at: AtomOneManFloat      => atomMan(e, a, at.op, at.vs, "Float", rFloat, identity, sortFloat(at))
      case at: AtomOneManDouble     => atomMan(e, a, at.op, at.vs, "Double", rDouble, identity, sortDouble(at))
      case at: AtomOneManBoolean    => atomMan(e, a, at.op, at.vs, "Boolean", rBoolean, identity, sortBoolean(at))
      case at: AtomOneManBigInt     => atomMan(e, a, at.op, at.vs, "BigInt", rBigInt, tBigInt, sortBigInt(at))
      case at: AtomOneManBigDecimal => atomMan(e, a, at.op, at.vs, "BigDecimal", rBigDecimal, tBigDec, sortBigDecimal(at))
      case at: AtomOneManDate       => atomMan(e, a, at.op, at.vs, "Date", rDate, identity, sortDate(at))
      case at: AtomOneManUUID       => atomMan(e, a, at.op, at.vs, "UUID", rUUID, identity, sortUUID(at))
      case at: AtomOneManURI        => atomMan(e, a, at.op, at.vs, "URI", rURI, identity, sortURI(at))
      case at: AtomOneManByte       => atomMan(e, a, at.op, at.vs, "Byte", rByte, tByte, sortByte(at))
      case at: AtomOneManShort      => atomMan(e, a, at.op, at.vs, "Short", rShort, tShort, sortShort(at))
      case at: AtomOneManChar       => atomMan(e, a, at.op, at.vs, "Char", rChar, tChar, sortChar(at))
    }
    es
  }

  final protected def atomMan[T: ClassTag](
    e: Var,
    a: Var,
    op: Op,
    args: Seq[T],
    tpe: String,
    v2datalog: T => String,
    typer: AnyRef => AnyRef,
    sorter: Option[(Int, (Row, Row) => Int)]
  ): Unit = {
    val v = vv
    find += v
    typers += typer
    sorter.foreach(sorts += _)
    expr(e, a, v, op, args, tpe, v2datalog)
  }


  protected def resolveAtomOneTac(es: List[Var], atom: AtomOneTac): List[Var] = {
    val (e, a) = (es.last, s":${atom.ns}/${atom.attr}")
    atom match {
      case at: AtomOneTacString     => atomTac(e, a, at.op, at.vs, "String", rString)
      case at: AtomOneTacInt        => atomTac(e, a, at.op, at.vs, "Int", rInt)
      case at: AtomOneTacLong       => atomTac(e, a, at.op, at.vs, "Long", rLong)
      case at: AtomOneTacFloat      => atomTac(e, a, at.op, at.vs, "Float", rFloat)
      case at: AtomOneTacDouble     => atomTac(e, a, at.op, at.vs, "Double", rDouble)
      case at: AtomOneTacBoolean    => atomTac(e, a, at.op, at.vs, "Boolean", rBoolean)
      case at: AtomOneTacBigInt     => atomTac(e, a, at.op, at.vs, "BigInt", rBigInt)
      case at: AtomOneTacBigDecimal => atomTac(e, a, at.op, at.vs, "BigDecimal", rBigDecimal)
      case at: AtomOneTacDate       => atomTac(e, a, at.op, at.vs, "Date", rDate)
      case at: AtomOneTacUUID       => atomTac(e, a, at.op, at.vs, "UUID", rUUID)
      case at: AtomOneTacURI        => atomTac(e, a, at.op, at.vs, "URI", rURI)
      case at: AtomOneTacByte       => atomTac(e, a, at.op, at.vs, "Byte", rByte)
      case at: AtomOneTacShort      => atomTac(e, a, at.op, at.vs, "Short", rShort)
      case at: AtomOneTacChar       => atomTac(e, a, at.op, at.vs, "Char", rChar)
    }
    es
  }

  private def atomTac[T: ClassTag](
    e: Var,
    a: Var,
    op: Op,
    args: Seq[T],
    tpe: String,
    v2datalog: T => String,
  ): Unit = {
    val v = vv
    expr(e, a, v, op, args, tpe, v2datalog)
  }

  private def expr[T: ClassTag](
    e: Var,
    a: Var,
    v: Var,
    op: Op,
    args: Seq[T],
    tpe: String,
    v2datalog: T => String,
  ): Unit = {
    op match {
      case V       => attr(e, a, v)
      case Eq      => equal(e, a, v, args)
      case Neq     => neq(e, a, v, args, tpe, v2datalog)
      case Lt      => compare(e, a, v, args.head, "<")
      case Gt      => compare(e, a, v, args.head, ">")
      case Le      => compare(e, a, v, args.head, "<=")
      case Ge      => compare(e, a, v, args.head, ">=")
      case NoValue => noValue(e, a, v)
      case other   => unexpected(other)
    }
  }


  private def attr(e: Var, a: Var, v: Var): Unit = {
    where += s"[$e $a $v$tx]" -> wClause
  }

  private def equal[T: ClassTag](e: Var, a: Var, v: Var, args: Seq[T]): Unit = {
    in += s"[$v ...]"
    where += s"[$e $a $v$tx]" -> wClause
    inputs += args.toArray
  }

  private def neq[T](e: Var, a: Var, v: Var, args: Seq[T], tpe: String, render: T => String): Unit = {
    where += s"[$e $a $v$tx]" -> wClause
    if (tpe == "URI") {
      args.foreach { arg =>
        where += s"[(ground (new java.net.URI ${render(arg)})) ${v}1]" -> wNeqOne
        where += s"[(!= $v ${v}1)]" -> wNeqOne
      }
    } else {
      args.foreach { arg =>
        where += s"[(!= $v ${render(arg)})]" -> wNeqOne
      }
    }
  }

  private def compare[T](e: Var, a: Var, v: Var, arg: T, op: String): Unit = {
    val v1 = v + 1
    in += v1
    where += s"[$e $a $v$tx]" -> wClause
    where += s"[($op $v $v1)]" -> wNeqOne
    inputs += arg.asInstanceOf[AnyRef]
  }

  private def noValue(e: Var, a: Var, v: String): Unit = {
    where += s"(not [$e $a])" -> wNeqOne
  }


  final private lazy val optString = (v: AnyRef) => (v match {
    case null      => Option.empty[String]
    case v: String => Some(v)
    case v         => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[String])
  }).asInstanceOf[AnyRef]

  final private lazy val optInt = (v: AnyRef) => (v match {
    case null        => Option.empty[Int]
    case v: jInteger => Some(v.toInt)
    case v           => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[Integer].toInt)
  }).asInstanceOf[AnyRef]

  final private lazy val optLong = (v: AnyRef) => (v match {
    case null     => Option.empty[Long]
    case v: jLong => Some(v)
    case v        => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[Long])
  }).asInstanceOf[AnyRef]

  final private lazy val optFloat = (v: AnyRef) => (v match {
    case null       => Option.empty[Float]
    case v: jDouble => Some(v.toFloat)
    case v          => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[Float])
  }).asInstanceOf[AnyRef]

  final private lazy val optDouble = (v: AnyRef) => (v match {
    case null       => Option.empty[Double]
    case v: jDouble => Some(v)
    case v          => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[Double])
  }).asInstanceOf[AnyRef]

  final private lazy val optBoolean = (v: AnyRef) => (v match {
    case null        => Option.empty[Boolean]
    case v: jBoolean => Some(v)
    case v           => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[Boolean])
  }).asInstanceOf[AnyRef]

  final private lazy val optBigInt = (v: AnyRef) => (v match {
    case null       => Option.empty[BigInt]
    case v: jBigInt => Some(v)
    case v          => Some(
      BigInt(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[jBigInt])
    )
  }).asInstanceOf[AnyRef]

  final private lazy val optBigDecimal = (v: AnyRef) => (v match {
    case null           => Option.empty[BigDecimal]
    case v: jBigDecimal => Some(v)
    case v              => Some(
      BigDecimal(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[jBigDecimal])
    )
  }).asInstanceOf[AnyRef]

  final private lazy val optDate = (v: AnyRef) => (v match {
    case null    => Option.empty[Date]
    case v: Date => Some(v)
    case v       => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[Date])
  }).asInstanceOf[AnyRef]

  final private lazy val optUUID = (v: AnyRef) => (v match {
    case null    => Option.empty[UUID]
    case v: UUID => Some(v)
    case v       => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[UUID])
  }).asInstanceOf[AnyRef]

  final private lazy val optURI = (v: AnyRef) => (v match {
    case null   => Option.empty[URI]
    case v: URI => Some(v)
    case v      => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[URI])
  }).asInstanceOf[AnyRef]

  final private lazy val optByte = (v: AnyRef) => (v match {
    case null     => Option.empty[Byte]
    case v: jLong => Some(v.toByte)
    case v        => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[Integer].toByte)
  }).asInstanceOf[AnyRef]

  final private lazy val optShort = (v: AnyRef) => (v match {
    case null     => Option.empty[Short]
    case v: jLong => Some(v.toShort)
    case v        => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[Integer].toShort)
  }).asInstanceOf[AnyRef]

  final private lazy val optChar = (v: AnyRef) => (v match {
    case null      => Option.empty[Char]
    case v: String => Some(v.head)
    case v         => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[String].charAt(0))
  }).asInstanceOf[AnyRef]


  protected def resolveAtomOneOpt(es: List[Var], atom: AtomOneOpt): List[Var] = {
    attrIndex += 1
    val (e, a) = (es.last, s":${atom.ns}/${atom.attr}")
    atom match {
      case at: AtomOneOptString     => atomOpt(e, a, at.op, at.vs, "String", rString, optString, sortString(at))
      case at: AtomOneOptInt        => atomOpt(e, a, at.op, at.vs, "Int", rInt, optInt, sortInt(at))
      case at: AtomOneOptLong       => atomOpt(e, a, at.op, at.vs, "Long", rLong, optLong, sortLong(at))
      case at: AtomOneOptFloat      => atomOpt(e, a, at.op, at.vs, "Float", rFloat, optFloat, sortFloat(at))
      case at: AtomOneOptDouble     => atomOpt(e, a, at.op, at.vs, "Double", rDouble, optDouble, sortDouble(at))
      case at: AtomOneOptBoolean    => atomOpt(e, a, at.op, at.vs, "Boolean", rBoolean, optBoolean, sortBoolean(at))
      case at: AtomOneOptBigInt     => atomOpt(e, a, at.op, at.vs, "BigInt", rBigInt, optBigInt, sortBigInt(at))
      case at: AtomOneOptBigDecimal => atomOpt(e, a, at.op, at.vs, "BigDecimal", rBigDecimal, optBigDecimal, sortBigDecimal(at))
      case at: AtomOneOptDate       => atomOpt(e, a, at.op, at.vs, "Date", rDate, optDate, sortDate(at))
      case at: AtomOneOptUUID       => atomOpt(e, a, at.op, at.vs, "UUID", rUUID, optUUID, sortUUID(at))
      case at: AtomOneOptURI        => atomOpt(e, a, at.op, at.vs, "URI", rURI, optURI, sortURI(at))
      case at: AtomOneOptByte       => atomOpt(e, a, at.op, at.vs, "Byte", rByte, optByte, sortByte(at))
      case at: AtomOneOptShort      => atomOpt(e, a, at.op, at.vs, "Short", rShort, optShort, sortShort(at))
      case at: AtomOneOptChar       => atomOpt(e, a, at.op, at.vs, "Char", rChar, optChar, sortChar(at))
    }
    es
  }

  private def atomOpt[T: ClassTag](
    e: Var,
    a: Var,
    op: Op,
    optArgs: Option[Seq[T]],
    tpe: String,
    v2datalog: T => String,
    typer: AnyRef => AnyRef,
    sorter: Option[(Int, (Row, Row) => Int)]
  ): Unit = {
    val v = vv
    typers += typer
    sorter.foreach(sorts += _)
    op match {
      case V     => optV(e, a, v)
      case Eq    => optEq(e, a, v, optArgs)
      case Neq   => optNeq(e, a, v, optArgs, tpe, v2datalog)
      case Lt    => optCompare(e, a, v, optArgs, "<")
      case Gt    => optCompare(e, a, v, optArgs, ">")
      case Le    => optCompare(e, a, v, optArgs, "<=")
      case Ge    => optCompare(e, a, v, optArgs, ">=")
      case other => unexpected(other)
    }
  }

  private def optV(e: Var, a: Var, v: Var) = {
    find += s"(pull $e-$v [[$a :limit nil]]) "
    where += s"[(identity $e) $e-$v]" -> wGround
  }

  private def optEq[T: ClassTag](e: Var, a: Var, v: Var, optArgs: Option[Seq[T]]): Unit = {
    if (optArgs.isDefined) {
      find += v
      in += s"[$v ...]"
      where += s"[$e $a $v$tx]" -> wClause
      inputs += optArgs.get.toArray
    } else {
      // None
      find += s"(pull $e-$v [[$a :limit nil]])"
      where += s"(not [$e $a])" -> wNeqOne
      where += s"[(identity $e) $e-$v]" -> wGround
    }
  }


  private def optNeq[T](e: Var, a: Var, v: Var, optArgs: Option[Seq[T]], tpe: String, render: T => String): Unit = {
    find += v
    where += s"[$e $a $v$tx]" -> wClause
    if (optArgs.isDefined && optArgs.get.nonEmpty) {
      if (tpe == "URI") {
        optArgs.get.foreach { arg =>
          where += s"[(ground (new java.net.URI ${render(arg)})) ${v}1]" -> wNeqOne
          where += s"[(!= $v ${v}1)]" -> wNeqOne
        }
      } else {
        optArgs.get.foreach { arg =>
          where += s"[(!= $v ${render(arg)})]" -> wNeqOne
        }
      }
    }
  }

  private def optCompare[T](e: Var, a: Var, v: Var, optArgs: Option[Seq[T]], op: String): Unit = {
    if (optArgs.isDefined && optArgs.get.nonEmpty) {
      find += v
      val v1 = v + 1
      in += v1
      where += s"[$e $a $v$tx]" -> wClause
      where += s"[($op $v $v1)]" -> wNeqOne
      inputs += optArgs.get.head.asInstanceOf[AnyRef]
    } else {
      // None - return null (clojure nil)
      find += s"$v-nil"
      where += s"[(ground nil) $v-nil]" -> wGround
    }
  }
}