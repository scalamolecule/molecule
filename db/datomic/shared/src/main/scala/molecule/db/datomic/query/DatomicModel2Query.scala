package molecule.db.datomic.query

import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
import java.net.URI
import java.util.{Date, UUID, List => jList}
import clojure.lang.PersistentArrayMap
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.MoleculeModel._
import molecule.core.query.Model2Query
import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer
import java.lang.{Boolean => jBoolean, Double => jDouble, Integer => jInteger, Long => jLong}
import molecule.base.util.BaseHelpers
import scala.reflect.ClassTag;


class DatomicModel2Query[Tpl](elements: Seq[Element]) extends Sort[Tpl] with BaseHelpers {

  final lazy protected val query   : String      = renderQuery(true)
  final lazy protected val queryRaw: String      = renderQuery(false)
  final lazy protected val inputs  : Seq[AnyRef] = renderRules ++ ins

  final protected def renderQuery(optimized: Boolean): String = {
    // Recursively resolve molecule
    resolve(List(vv), elements)

    // Build Datomic query string
    val find1       = (sortIds ++ find).map(v => if (v.startsWith("?")) v else s"\n        $v").mkString(" ").trim
    val widh1       = if (widh.isEmpty) "" else widh.distinct.mkString("\n :with  ", " ", "")
    val hasRules    = rules.nonEmpty
    val r           = if (hasRules) "% " else ""
    val in1         = if (!hasRules && in.isEmpty) "" else in.mkString("\n :in    $ " + r, " ", "")
    val where1      = where.distinct
    val clausePairs = if (optimized) where1.sortBy(_._2) else where1
    val where2      = clausePairs.map(_._1).mkString("\n        ")
    val q           =
      s"""[:find  $find1 $widh1 $in1
         | :where $where2]""".stripMargin


    println("\n--- QUERY ---------------------------------------------------------")
    elements.foreach(println)
    println("---")
    println(q)
    if (inputs.nonEmpty) {
      println("---")
      inputs.foreach {
        case a: Array[_] => println(a.toList)
        case other       => println(other)
      }
    }
    q
  }

  final protected def renderRules: Seq[String] = {
    if (rules.isEmpty) Nil else {
      val res = Seq(rules.mkString("[\n  ", "\n  ", "\n]"))
      //      println("Rules:\n" + res.head)
      res
    }
  }

  private def unexpected(element: Element) = throw MoleculeException("Unexpected element: " + element)
  private def unexpected(op: Op) = throw MoleculeException("Unexpected operation: " + op)


  @tailrec
  final protected def resolve(es: List[Var], elements: Seq[Element]): List[Var] = elements match {
    case element :: tail => element match {
      case a: Atom =>
        a match {
          case a: AtomOneMan => attrIndex += 1; resolve(resolveAtomOneMan(es, a), tail)
          case a: AtomOneOpt => attrIndex += 1; resolve(resolveAtomOneOpt(es, a), tail)
          case a: AtomOneTac => resolve(resolveAtomOneTac(es, a), tail)
          case other         => unexpected(other)
        }
      case b: Bond => es
      case other   => unexpected(other)
    }
    case Nil             => es
  }


  // Datomic Java to Scala typers
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
  final private lazy val rChar      : Char => String       = (v: Char) => v.toString
  final private lazy val rByte      : Byte => String       = (v: Byte) => v.toString
  final private lazy val rShort     : Short => String      = (v: Short) => v.toString


  private def resolveAtomOneMan(es: List[Var], atom: AtomOneMan): List[Var] = {
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
      case at: AtomOneManChar       => atomMan(e, a, at.op, at.vs, "Char", rChar, tChar, sortChar(at))
      case at: AtomOneManByte       => atomMan(e, a, at.op, at.vs, "Byte", rByte, tByte, sortByte(at))
      case at: AtomOneManShort      => atomMan(e, a, at.op, at.vs, "Short", rShort, tShort, sortShort(at))
      case other                    => unexpected(other)
    }
    es
  }

  final protected def atomMan[T: ClassTag](
    e: Var,
    a: String,
    op: Op,
    args: Seq[T],
    tpe: String,
    render: T => String,
    typer: AnyRef => AnyRef,
    sorter: Option[(Int, (Row, Row) => Int)]
  ): Unit = {
    val v = vv
    find += v
    where += s"[$e $a $v$tx]" -> wClause
    typers += typer
    sorter.foreach(sorts += _)
    op match {
      case V     => // attribute value as is
      case Eq    => equals(v, args)
      case Neq   => neq(tpe, v, args, render)
      case Lt    => compare(e, a, v, args.head, "<")
      case Gt    => compare(e, a, v, args.head, ">")
      case Le    => compare(e, a, v, args.head, "<=")
      case Ge    => compare(e, a, v, args.head, ">=")
      case other => unexpected(other)
    }
  }

  private def equals[T: ClassTag](v: String, args: Seq[T]): Unit = {
    in += s"[$v ...]"
    ins += args.toArray
  }

  private def neq[T](tpe: String, v: String, args: Seq[T], render: T => String): Unit = {
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

  private def compare[T](e: String, a: String, v: String, arg: T, op: String): Unit = {
    val v1 = v + 1
    in += v1
    ins += arg.asInstanceOf[AnyRef]
    where += s"[$e $a $v$tx]" -> wClause
    where += s"[($op $v $v1)]" -> wNeqOne
  }


  private def resolveAtomOneTac(es: List[Var], atom: AtomOneTac): List[Var] = {
    atomTac(es.last, s":${atom.ns}/${atom.attr}", atom.op)
    es
  }

  private def atomTac(e: Var, a: String, op: Op): Unit = {
    op match {
      case V     => where += s"[$e $a _$tx]" -> wClause
      case Eq    =>
        val v = vv
        in += s"[$v ...]"
        where += s"[$e $a $v$tx]" -> wClause
      case other => unexpected(other)
    }
  }

  private def resolveAtomOneOpt(es: List[Var], atom: AtomOneOpt): List[Var] = {
    val (e, a) = (es.last, s":${atom.ns}/${atom.attr}")
    atom match {
      case _: AtomOneOptString => atomOpt(e, a, atom.op, (v: AnyRef) => (v match {
        case null => Option.empty[String]
        case v    => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[String])
      }).asInstanceOf[AnyRef])

      case _: AtomOneOptInt => atomOpt(e, a, atom.op, (v: AnyRef) => (v match {
        case null => Option.empty[Int]
        case v    => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[Integer].toInt)
      }).asInstanceOf[AnyRef])

      case _: AtomOneOptLong => atomOpt(e, a, atom.op, (v: AnyRef) => (v match {
        case null => Option.empty[Long]
        case v    => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[Long])
      }).asInstanceOf[AnyRef])

      case _: AtomOneOptFloat => atomOpt(e, a, atom.op, (v: AnyRef) => (v match {
        case null => Option.empty[Float]
        case v    => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[Float])
      }).asInstanceOf[AnyRef])

      case _: AtomOneOptDouble => atomOpt(e, a, atom.op, (v: AnyRef) => (v match {
        case null => Option.empty[Double]
        case v    => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[Double])
      }).asInstanceOf[AnyRef])

      case _: AtomOneOptBoolean => atomOpt(e, a, atom.op, (v: AnyRef) => (v match {
        case null => Option.empty[Boolean]
        case v    => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[Boolean])
      }).asInstanceOf[AnyRef])

      case _: AtomOneOptBigInt => atomOpt(e, a, atom.op, (v: AnyRef) => (v match {
        case null => Option.empty[BigInt]
        case v    => Some(BigInt(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[jBigInt]))
      }).asInstanceOf[AnyRef])

      case _: AtomOneOptBigDecimal => atomOpt(e, a, atom.op, (v: AnyRef) => (v match {
        case null => Option.empty[BigDecimal]
        case v    => Some(BigDecimal(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[jBigDecimal]))
      }).asInstanceOf[AnyRef])

      case _: AtomOneOptDate => atomOpt(e, a, atom.op, (v: AnyRef) => (v match {
        case null => Option.empty[Date]
        case v    => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[Date])
      }).asInstanceOf[AnyRef])

      case _: AtomOneOptUUID => atomOpt(e, a, atom.op, (v: AnyRef) => (v match {
        case null => Option.empty[UUID]
        case v    => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[UUID])
      }).asInstanceOf[AnyRef])

      case _: AtomOneOptURI => atomOpt(e, a, atom.op, (v: AnyRef) => (v match {
        case null => Option.empty[URI]
        case v    => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[URI])
      }).asInstanceOf[AnyRef])

      case _: AtomOneOptChar => atomOpt(e, a, atom.op, (v: AnyRef) => (v match {
        case null => Option.empty[Char]
        case v    => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[String].charAt(0))
      }).asInstanceOf[AnyRef])

      case _: AtomOneOptByte => atomOpt(e, a, atom.op, (v: AnyRef) => (v match {
        case null => Option.empty[Byte]
        case v    => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[Integer].toByte)
      }).asInstanceOf[AnyRef])

      case _: AtomOneOptShort => atomOpt(e, a, atom.op, (v: AnyRef) => (v match {
        case null => Option.empty[Short]
        case v    => Some(v.asInstanceOf[PersistentArrayMap].values.iterator.next.asInstanceOf[Integer].toShort)
      }).asInstanceOf[AnyRef])

      case other => unexpected(other)
    }
    es
  }

  private def atomOpt(e: Var, a: String, op: Op, typer: AnyRef => AnyRef): Unit = {
    op match {
      case V =>
        val v = vv
        find += s"(pull $e-$v [[$a :limit nil]]) "
        where += s"[(identity $e) $e-$v]" -> wGround
        typers += typer

      case other => unexpected(other)
    }
  }

}