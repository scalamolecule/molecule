package molecule.core

import java.util
import molecule.boilerplate.api.Keywords._
import molecule.boilerplate.ast.MoleculeModel._
import zio.{Chunk, ZIO}
import scala.runtime.RichInt

object Adhoc extends App {


//  trait TxReport
//
//  trait DataSource {}
//
//  case class DatomicConnection() extends DataSource
//
//  //  implicit val conn = DatomicConnection()
//
//  trait MoleculeError
//  case class ValidationError(msg: String) extends MoleculeError


//  List(1).d


  //  val r1: Ns_2[String, Int] with Aggr_2[String, Int, Ns_2] =
  //    Ns.str.int
  //
  //  val r2: List[(String, Int)] =
  //    Ns.str.int.get
  //
  //  val result: Either[String, List[(String, Int)]] = query(Ns.str.int)

  //  Ns.str_?.int.query.run.map(_ == Chunk((Some("Bob"), 42)))

  //  val a1: EncodedRows = Ns.str_?.Ref1.int.save
  //
  //  val savePersons = Ns.str.int.insert
  //
  //  savePersons("Bob", 42)
  //  savePersons(("Bob", 42), ("Liz", 35))
  //  savePersons(List(("Bob", 42), ("Liz", 35)))
  //
  //  val persons = List(("Bob", 42), ("Liz", 35))
  //  savePersons(persons)



//  Ns.str.int.insert.apply(("Bob", 42), ("Liz", 35))
//  Ns.str.int.query.run.map(_ == Chunk(("Bob", 42), ("Liz", 35)))
//  Ns.str.int.query.drop(20).take(10).run.map(_ == Chunk(("Bob", 42), ("Liz", 35)))
//
//  val cursor = "Base64-encoded-string"
//  Ns.str.int.query.from(cursor).run.map(_ == Chunk(("Bob", 42), ("Liz", 35)))




//  Ns.str.Ref1.int.insert.apply(("Bob", 42), ("Liz", 35))
//
//  Ns.str.apply("Bob").int(42).save.run.map(_ == Chunk.apply(("Bob", 42)))
//
//  Ns.int.a1.str.apply(max).save.run.map(_ == Chunk.apply(("Bob", 42)))
//
//  Ns.int.apply(min).a1.str.apply(max).save.run.map(_ == Chunk.apply((42, "Bob")))
//  Ns.int.apply(min).str.apply("bob").save.run.map(_ == Chunk.apply((42, "Bob")))
//  Ns.int.apply(42).a1.str.apply("bob").save.run.map(_ == Chunk.apply((42, "Bob")))
//  Ns.int.apply(_ + 1).a1.str.apply(_ + "X").save.run.map(_ == Chunk.apply((43, "BobX")))
//
//  Ns.int_?.apply(Option.empty[Int]).a1.str.apply("bob").save.run.map(_ == Chunk.apply(("Bob", 42)))
//  Ns.int_?.apply(Some(42)).a1.str.apply("bob").save.run.map(_ == Chunk.apply(("Bob", 42)))
//  Ns.int_?.apply(Some(Seq(42))).a1.str.apply("bob").save.run.map(_ == Chunk.apply(("Bob", 42)))
//
//  // Can't aggregate tacit attribute
//  //  Ns.int_.apply(min).str.apply("bob").save.run.map(_ == Chunk.apply(("Bob", 42)))
//  // Can't sort tacit attribute
//  //  Ns.int_.apply(42).a1.str.apply("bob").save.run.map(_ == Chunk.apply(("Bob", 42)))
//
//  Ns.int_.apply(42).str.apply("bob").save.run.map(_ == Chunk.apply(("Bob", 42)))


  //  for {
  //    x <- Ns.str$.int.query.run
  ////    x <- query(Ns.str.int)
  ////    x <- Ns.str.int.query.asOf(date).hList.offset(40).limit(20)
  ////    x <- Ns.str.int.query.asOf(date).hList.paginate(20, 40)
  ////    x <- Ns.str.int.query.asOf(date).hList.cursor(last)
  ////    x <- Ns.str_.int.query.cc(Customer)
  ////    x <- Ns.str_?.int.query.cc(Customer)
  //    _ <- insert(Ns.str.int)(List(
  //      ("Ben", 42),
  //      ("Liz", 35),
  //    ))
  //    _ <- save(Ns.str.int)
  //  } yield x

  //
  //  val r31: List[(Int, String)] = Ns.str.apply(count).str.get
  //  val r32: List[(Int, String)] = Ns.int.apply(count).str.get
  //
  //
  //  val r2: List[(Int, String)] = Ns.int.str.get
  //  val r4: List[(Int, Int)]    = Ns.int.str.apply(count).get
  //
  //  val r50: List[(Int, Double)]    = Ns.int.str.apply(avg).get
  //  val r51: List[(String, Double)]   = Ns.str.int.apply(avg).get
  //  val r52: Ns_2[String, Double] = Ns.str.int.apply(avg)
  //  val r61: List[(Double, Int)]      = Ns.str.apply(avg).int.get
  //
  //  val r7: List[(Int, List[String])] = Ns.int.str.apply(min(2)).get

  //    case class Ns(name: String) {
  //      lazy val age = Ns(name, age)
  //    }
  //    case class Ns(name: String, age: Int)

  //  def m2qx(m: String, es: Seq[Element], q: Query): Query = {
  //    es match {
  //      case h :: tail => h match {
  //        case Molecule(ns, es) => m2q(ns, es, q)
  //        case a: AtomMandatory => a match {
  //          case AtomString(attr, _, _, _) => m2q(m, tail, q.copy(f = Find(q.f.outputs :+ Val("?a"))))
  //          case AtomInt(attr, _, _, _)    => m2q(m, tail, q.copy(f = Find(q.f.outputs :+ Val("?a"))))
  //        }
  //        case _ => q
  //      }
  //      case Nil    => q
  //    }
  //  }

  //  val q = molecule2query(m.m, m.elements, Query())

  //  println(AtomString("str"))
  //  println("--")
  //  println(m)
}
