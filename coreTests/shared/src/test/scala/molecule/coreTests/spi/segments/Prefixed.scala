package molecule.coreTests.spi.segments

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Segments._
import molecule.coreTests.setup._


case class Prefixed(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "Nested 2 levels" - segments { implicit conn =>
    for {
      _ <- lit_Book.title.Reviewers.name.Professions.*(gen_Profession.name)
        .insert("book", "Jan", List("Musician")).transact

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
        .insert("book", "John", "Marc").transact

      _ <- lit_Book.title.Author.name._lit_Book.Reviewers.name
        .query.get.map(_ ==> List(("book", "John", "Marc")))

      _ <- lit_Book.title.Author.name._lit_Book.Reviewers.*(gen_Person.name)
        .query.get.map(_ ==> List(("book", "John", List("Marc"))))
    } yield ()
  }


  "Nested" - segments { implicit conn =>
    for {
      _ <- lit_Book.title.Author.name._lit_Book.Reviewers.*(gen_Person.name)
        .insert("book", "John", List("Marc")).transact

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
      ).insert("book", "John", List(("Marc", "Musician"))).transact

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
        .insert("book", "John", List(("Marc", List("Musician")))).transact

      _ <- lit_Book.title.Author.name._lit_Book.Reviewers.name.Professions.name
        .query.get.map(_ ==> List(("book", "John", "Marc", "Musician")))

      _ <- lit_Book.title.Author.name._lit_Book.Reviewers.*(
          gen_Person.name.Professions.*(
            gen_Profession.name))
        .query.get.map(_ ==> List(("book", "John", List(("Marc", List("Musician"))))))
    } yield ()
  }
}