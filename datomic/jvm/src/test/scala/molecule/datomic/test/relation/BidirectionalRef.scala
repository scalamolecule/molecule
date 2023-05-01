package molecule.datomic.test.relation

import datomic.Peer
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions


object BidirectionalRef extends DatomicTestSuite {

  override lazy val tests = Tests {

    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- Ns.i(1).Self.i(2).save.transact

        _ <- Ns.i.Self.i.query.inspect

        _ = {
          Peer.q(
            """[:find  ?a ?c ?b ?d
              | :where [?a :Ns/i ?b]
              |        [?a :Ns/self ?c]
              |        [?c :Ns/i ?d]
              |        ]""".stripMargin,
            conn.db
          ).forEach { r => println(r) }

          println("-------")
          Peer.q(
            """[:find  ?a ?c ?b ?d
              | :where [?a :Ns/i ?b]
              |        [?c :Ns/self ?a]
              |        [?c :Ns/i ?d]
              |        ]""".stripMargin,
            conn.db
          ).forEach { r => println(r) }

          println("-------")
          Peer.q(
            """[:find  ?b ?d
              | :in $ %
              | :where [?a :Ns/i ?b]
              |        (bi-ref-?b ?a ?c)
              |        [?c :Ns/i ?d]
              |        ]""".stripMargin,
            conn.db,
            """[
              |  [(bi-ref-?b ?a ?c) [?a :Ns/self ?c]]
              |  [(bi-ref-?b ?a ?c) [?c :Ns/self ?a]]
              |]
              |""".stripMargin
          ).forEach { r => println(r) }

        }
        _ <- Ns.i.Self.i.query.get.map(_ ==> List((1, 2)))


        _ <- Ns.i(1).Self.i.query.get.map(_ ==> List((1, 2)))

        // todo
        //        _ <- Ns.i(2).Self.i.query.get.map(_ ==> List((1, 2)))

      } yield ()

    }
  }
}
