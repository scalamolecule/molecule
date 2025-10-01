package molecule.db.compliance.test.segments

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Segments.*
import molecule.db.compliance.setup.DbProviders


case class Prefixed(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Nested 2 levels" - segments {
    for {
      _ <- lit_Book.title.gen_Reviewers.name.gen_Professions.*(gen_Profession.name)
        .insert(("book", "Jan", List("Musician"))).transact

      _ <- lit_Book.title.gen_Reviewers.name.gen_Professions.*(gen_Profession.name)
        .query.get.map(_ ==> List(("book", "Jan", List("Musician"))))

      // Same as
      _ <- lit_Book.title.gen_Reviewers.gen_Professions.*(gen_Profession.name)
        .query.get.map(_ ==> List(("book", List("Musician"))))
    } yield ()
  }


  "Back only" - segments {
    for {
      _ <- lit_Book.title("A good book").cat("good").Author.name("Marc").save.transact
      _ <- lit_Book.title.Author.name._lit_Book.cat
        .query.get.map(_ ==> List(("A good book", "Marc", "good")))
    } yield ()
  }


  "Adjacent" - segments {
    for {
      _ <- lit_Book.title.Author.name._lit_Book.gen_Reviewers.name
        .insert(("book", "John", "Marc")).transact

      _ <- lit_Book.title.Author.name._lit_Book.gen_Reviewers.name
        .query.get.map(_ ==> List(("book", "John", "Marc")))

      _ <- lit_Book.title.Author.name._lit_Book.gen_Reviewers.*(gen_Person.name)
        .query.get.map(_ ==> List(("book", "John", List("Marc"))))
    } yield ()
  }


  "Nested" - segments {
    for {
      _ <- lit_Book.title.Author.name._lit_Book.gen_Reviewers.*(gen_Person.name)
        .insert(("book", "John", List("Marc"))).transact

      _ <- lit_Book.title.Author.name._lit_Book.gen_Reviewers.name
        .query.get.map(_ ==> List(("book", "John", "Marc")))

      _ <- lit_Book.title.Author.name._lit_Book.gen_Reviewers.*(gen_Person.name)
        .query.get.map(_ ==> List(("book", "John", List("Marc"))))
    } yield ()
  }


  "Nested + adjacent" - segments {
    for {
      _ <- lit_Book.title.Author.name._lit_Book.gen_Reviewers.*(
        gen_Person.name.gen_Professions.name
      ).insert(("book", "John", List(("Marc", "Musician")))).transact

      _ <- lit_Book.title.Author.name._lit_Book.gen_Reviewers.name.gen_Professions.name
        .query.get.map(_ ==> List(("book", "John", "Marc", "Musician")))

      _ <- lit_Book.title.Author.name._lit_Book.gen_Reviewers.*(gen_Person.name.gen_Professions.name)
        .query.get.map(_ ==> List(("book", "John", List(("Marc", "Musician")))))
    } yield ()
  }


  "Nested + nested" - segments {
    for {
      _ <- lit_Book.title.Author.name._lit_Book.gen_Reviewers.*(
          gen_Person.name.gen_Professions.*(
            gen_Profession.name))
        .insert(("book", "John", List(("Marc", List("Musician"))))).transact

      _ <- lit_Book.title.Author.name._lit_Book.gen_Reviewers.name.gen_Professions.name
        .query.get.map(_ ==> List(("book", "John", "Marc", "Musician")))

      _ <- lit_Book.title.Author.name._lit_Book.gen_Reviewers.*(
          gen_Person.name.gen_Professions.*(
            gen_Profession.name))
        .query.get.map(_ ==> List(("book", "John", List(("Marc", List("Musician"))))))
    } yield ()
  }
}