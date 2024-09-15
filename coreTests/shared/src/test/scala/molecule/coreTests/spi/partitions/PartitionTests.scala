package molecule.coreTests.spi.partitions

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.dsl.Partitions._
import molecule.coreTests.setup.CoreTestSuite
import utest._


trait PartitionTests extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  lazy val tests = Tests {

    "Nested 2 levels" - partition { implicit conn =>
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


    "Back only" - partition { implicit conn =>
      for {
        _ <- lit_Book.title("A good book").cat("good").Author.name("Marc").save.transact
        _ <- lit_Book.title.Author.name._lit_Book.cat
          .query.get.map(_ ==> List(("A good book", "Marc", "good")))
      } yield ()
    }


    "Adjacent" - partition { implicit conn =>
      for {
        _ <- lit_Book.title.Author.name._lit_Book.Reviewers.name
          .insert("book", "John", "Marc").transact

        _ <- lit_Book.title.Author.name._lit_Book.Reviewers.name
          .query.get.map(_ ==> List(("book", "John", "Marc")))

        _ <- lit_Book.title.Author.name._lit_Book.Reviewers.*(gen_Person.name)
          .query.get.map(_ ==> List(("book", "John", List("Marc"))))
      } yield ()
    }


    "Nested" - partition { implicit conn =>
      for {
        _ <- lit_Book.title.Author.name._lit_Book.Reviewers.*(gen_Person.name)
          .insert("book", "John", List("Marc")).transact

        _ <- lit_Book.title.Author.name._lit_Book.Reviewers.name
          .query.get.map(_ ==> List(("book", "John", "Marc")))

        _ <- lit_Book.title.Author.name._lit_Book.Reviewers.*(gen_Person.name)
          .query.get.map(_ ==> List(("book", "John", List("Marc"))))
      } yield ()
    }


    "Nested + adjacent" - partition { implicit conn =>
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


    "Nested + nested" - partition { implicit conn =>
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
}