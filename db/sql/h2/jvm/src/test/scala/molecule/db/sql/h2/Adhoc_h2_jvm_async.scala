package molecule.db.sql.h2

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.Color.{Blue, Green, Red, White, Yellow}
import molecule.db.core.util.Executor.*
import molecule.db.sql.h2.async.*
import molecule.db.sql.h2.setup.DbProviders_h2
//import moleculeGen.molecule.db.compliance.domains.dsl.Types.Color


class Adhoc_h2_jvm_async extends MUnit with DbProviders_h2 with TestUtils {

  import molecule.db.compliance.domains.dsl.Types.*

  "types" - types { implicit conn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

    for {
      List(a, b) <- Entity.int.insert(1, 2).transact.map(_.ids)
      _ <- Entity.int(3).save.transact
      _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2, 3))
      _ <- Entity(a).int(10).update.transact
      _ <- Entity(b).delete.transact
      _ <- Entity.int.a1.query.get.map(_ ==> List(3, 10))


      //      _ <- Entity.string.apply("str").query.inspect
      //      _ <- Entity.string.apply(Color.BLUE).query.inspect
      //
      //      _ <- Entity.color.apply("str").query.inspect
      //      _ <- Entity.color.apply(X.Y).query.inspect
      //      _ <- Entity.color.apply(Color.BLUE).query.inspect
      //      _ <- Entity.color.query.get.map(_ ==> List("BLUE"))
      //      _ <- Entity.color.query.get.map(_.map(Color.valueOf) ==> List(Color.BLUE))
      //      _ <- Entity.color.query.get.map(_ ==> List(Color.BLUE))

      //      _ <- Entity.int(?).string.query.inspect

    } yield ()
  }

  val a = (1, Set(Red.toString, Green.toString))
  val b = (2, Set(Green.toString, Blue.toString, Yellow.toString))


  "Mandatory: has" - types { implicit conn =>
    for {
      _ <- Entity.colorSet(Set(Red, Blue)).save.transact
      _ <- Entity.colorSet.has(Red).query.get.map(_ ==> List(Set("Red", "Blue")))
      _ <- Entity.colorSet.has(Yellow).query.get.map(_ ==> List())


      _ <- Entity.i.colorSet.insert(a, b).transact

      // Sets with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.colorSet.has(White).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSet.has(Red).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.colorSet.has(Green).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.colorSet.has(Blue).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.colorSet.has(Seq(White)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSet.has(Seq(Red)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.colorSet.has(Seq(Green)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.colorSet.has(Seq(Blue)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.colorSet.has(Red, Green).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.colorSet.has(Red, Blue).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.colorSet.has(Green, Blue).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.colorSet.has(Red, Green, Blue).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.colorSet.has(Seq(Red, Green)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.colorSet.has(Seq(Red, Blue)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.colorSet.has(Seq(Green, Blue)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.colorSet.has(Seq(Red, Green, Blue)).query.get.map(_ ==> List(a, b))


      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.colorSet.has(Seq.empty[Color]).query.get.map(_ ==> List())
    } yield ()
  }


  //    "unique" - unique { implicit conn =>
  //      import molecule.db.compliance.domains.dsl.Uniques._
  //      val triples             = getTriples.map(t => (t._3, t._1, t._2))
  //      val List(a, b, c, d, e) = triples.sortBy(p => (p._2, p._3, p._1))
  //      val query               = (c: String, l: Int) => Uniques.int.a3.s.a1.i.a2.query.from(c).limit(l)
  //      for {
  //        _ <- Uniques.int.s.i.insert(triples).transact
  //
  //      } yield ()
  //    }
  //

  //  "validation" - validation { implicit conn =>
  //    import molecule.db.compliance.domains.dsl.Validation._
  //
  //    for {
  //
  //      //        _ <- Type.int(1).save.transact
  //      //          .recover {
  //      //            case ValidationErrors(errorMap) =>
  //      //              errorMap.head._2.head ==>
  //      //                s"""Type.int with value `1` doesn't satisfy validation:
  //      //                   |_ > 2
  //      //                   |""".stripMargin
  //      //          }
  //
  //      //        _ <- Type.int(3).save.transact
  //      //        _ <- transact(Type.int(3).save, Type.int(5).save)
  //      //                _ <- transact(Type.int(1).save, Type.int.insert(5, 6)).recover {
  //      _ <- transact(
  //        Type.int.insert(5, 6),
  //        Type.int(1).save
  //      ).recover {
  //        case ValidationErrors(errorMap) =>
  //          errorMap.head._2.head ==>
  //            s"""Type.int with value `1` doesn't satisfy validation:
  //               |_ > 2
  //               |""".stripMargin
  //      }
  //
  //      _ <- unitOfWork {
  //        for {
  //          last <- Type.int.insert(5, 6).transact
  //          last <- Type.int(1).save.transact
  //        } yield last
  //      }.recover {
  //        case ValidationErrors(errorMap) =>
  //          errorMap.head._2.head ==>
  //            s"""Type.int with value `1` doesn't satisfy validation:
  //               |_ > 2
  //               |""".stripMargin
  //      }
  //
  //      // No data has been transacted
  //      _ <- Type.int.query.get.map(_ ==> List())
  //      //        _ <- Type.int.query.get.map(_ ==> List(3, 5))
  //
  //    } yield ()
  //  }
  //
  //    "partitions" - partition { implicit conn =>
  //      import molecule.db.compliance.domains.dsl.Groups._
  //      for {
  //
  //        _ <- lit_Book.title.Reviewers.name.Professions.*(gen_Profession.name)
  //          .insert("book", "Jan", List("Musician")).transact
  //
  //      } yield ()
  //    }
}
