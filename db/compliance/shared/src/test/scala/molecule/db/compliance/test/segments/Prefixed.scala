package molecule.db.compliance.test.segments

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Segments.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*


case class Prefixed(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Nested 2 levels" - segments { implicit conn =>
    for {
      _ <- lit_Book.title.Reviewers.name.Professions.*(gen_Profession.name)
        .insert(("book", "Jan", List("Musician"))).transact

      _ <- lit_Book.title.Reviewers.name.Professions.*(gen_Profession.name)
        .query.get.map(_ ==> List(("book", "Jan", List("Musician"))))

      // Same as
      _ <- lit_Book.title.Reviewers.Professions.*(gen_Profession.name)
        .query.get.map(_ ==> List(("book", List("Musician"))))
    } yield ()
  }


  "Back only" - segments { implicit conn =>
    for {
      _ <- lit_Book.title("A good book").cat("good").Author.name("Marc").save.transact
      _ <- lit_Book.title.Author.name._lit_Book.cat
        .query.get.map(_ ==> List(("A good book", "Marc", "good")))
    } yield ()
  }


  "Adjacent" - segments { implicit conn =>
    for {
      _ <- lit_Book.title.Author.name._lit_Book.Reviewers.name
        .insert(("book", "John", "Marc")).transact

      _ <- lit_Book.title.Author.name._lit_Book.Reviewers.name
        .query.get.map(_ ==> List(("book", "John", "Marc")))

      _ <- lit_Book.title.Author.name._lit_Book.Reviewers.*(gen_Person.name)
        .query.get.map(_ ==> List(("book", "John", List("Marc"))))
    } yield ()
  }


  "Nested" - segments { implicit conn =>
    for {
      _ <- lit_Book.title.Author.name._lit_Book.Reviewers.*(gen_Person.name)
        .insert(("book", "John", List("Marc"))).transact

      _ <- lit_Book.title.Author.name._lit_Book.Reviewers.name
        .query.get.map(_ ==> List(("book", "John", "Marc")))

      _ <- lit_Book.title.Author.name._lit_Book.Reviewers.*(gen_Person.name)
        .query.get.map(_ ==> List(("book", "John", List("Marc"))))
    } yield ()
  }


  "Nested + adjacent" - segments { implicit conn =>
    for {
      _ <- lit_Book.title.Author.name._lit_Book.Reviewers.*(
        gen_Person.name.Professions.name
      ).insert(("book", "John", List(("Marc", "Musician")))).transact

      _ <- lit_Book.title.Author.name._lit_Book.Reviewers.name.Professions.name
        .query.get.map(_ ==> List(("book", "John", "Marc", "Musician")))

      _ <- lit_Book.title.Author.name._lit_Book.Reviewers.*(gen_Person.name.Professions.name)
        .query.get.map(_ ==> List(("book", "John", List(("Marc", "Musician")))))
    } yield ()
  }


  "Nested + nested" - segments { implicit conn =>
    for {
      _ <- lit_Book.title.Author.name._lit_Book.Reviewers.*(
          gen_Person.name.Professions.*(
            gen_Profession.name))
        .insert(("book", "John", List(("Marc", List("Musician"))))).transact

      _ <- lit_Book.title.Author.name._lit_Book.Reviewers.name.Professions.name
        .query.get.map(_ ==> List(("book", "John", "Marc", "Musician")))

      _ <- lit_Book.title.Author.name._lit_Book.Reviewers.*(
          gen_Person.name.Professions.*(
            gen_Profession.name))
        .query.get.map(_ ==> List(("book", "John", List(("Marc", List("Musician"))))))
    } yield ()
  }
}