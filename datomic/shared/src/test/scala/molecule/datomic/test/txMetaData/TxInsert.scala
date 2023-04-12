package molecule.datomic.test.txMetaData

import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._

object TxInsert extends DatomicTestSuite {


  override lazy val tests = Tests {

    "Basic" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        // Apply tx meta data to tacit tx attribute:
        _ <- Ns.int.Tx(Ns.string_("a")).insert(0).transact

        // Data without tx meta data
        _ <- Ns.int.insert(1).transact

        // All base data (without tx meta data)
        _ <- Ns.int.query.get.map(_ ==> List(1, 0))

        // Data with tx meta data
        _ <- Ns.int.Tx(Ns.string_).query.get.map(_ ==> List(0))

        // Data without tx meta data
        _ <- Ns.int.Tx(Ns.string_()).query.get.map(_ ==> List(1))

        // Initial namespace(s) don't need to have a ref to tx meta namespaces
        _ <- Ns.int.Tx(Ref.s_("b")).insert(2).transact
        _ <- Ns.int.Tx(Other.s_("c")).insert(3).transact

        _ <- Ns.int.Tx(Ref.s_).query.get.map(_ ==> List(2))
        _ <- Ns.int.Tx(Other.s_).query.get.map(_ ==> List(3))

        // Base data with tx meta data
        _ <- Ns.int.Tx(Ns.string).query.get.map(_ ==> List((0, "a")))
        _ <- Ns.int.Tx(Ref.s).query.get.map(_ ==> List((2, "b")))
        _ <- Ns.int.Tx(Other.s).query.get.map(_ ==> List((3, "c")))
      } yield ()
    }

    "Multiple attrs" - refs { implicit conn =>
      for {
        _ <- Ns.i.Tx(R2.i_(7).s_("tx").ii_(Set(8, 9))).insert(1).transact
        _ <- Ns.i.Tx(R2.i.s.ii).query.get.map(_ ==> List(
          (1, 7, "tx", Set(8, 9))
        ))
      } yield ()
    }

    "Tx ref" - refs { implicit conn =>
      for {
        _ <- Ns.i.Tx(R2.i_(7).R3.i_(8)).insert(1).transact
        _ <- Ns.i.Tx(R2.i.R3.i).query.get.map(_ ==> List(
          (1, 7, 8)
        ))
      } yield ()
    }

    "Tx backref" - refs { implicit conn =>
      for {
        _ <- Ns.i.Tx(R2.i_(7).R3.i_(8)._R2.s_("tx")).insert(1).transact
        _ <- Ns.i.Tx(R2.i.R3.i._R2.s).query.get.map(_ ==> List(
          (1, 7, 8, "tx")
        ))
      } yield ()
    }

    "Tx composite" - refs { implicit conn =>
      for {
        _ <- Ns.i.Tx(R2.i_(7).s_("tx") + R1.i_(8)).insert(1).transact
        _ <- Ns.i.Tx(R2.i.s + R1.i).query.get.map(_ ==> List(
          (1, (7, "tx"), 8)
        ))
      } yield ()
    }

    "Composite + tx" - refs { implicit conn =>
      for {
        _ <- (Ns.i + R2.i.s).Tx(R4.i_(7).s_("tx")).insert(
          (1, (2, "a"))
        ).transact

        _ <- (Ns.i + R2.i.s).Tx(R4.i.s).query.get.map(_ ==> List(
          (1, (2, "a"), 7, "tx")
        ))
      } yield ()
    }

    "Composite + tx composite" - refs { implicit conn =>
      for {
        _ <- (Ns.i + R2.i.s).Tx(R3.i_(7).s_("tx") + R4.i_(8)).insert(
          (1, (2, "a"))
        ).transact

        _ <- (Ns.i + R2.i.s).Tx(R3.i.s + R4.i).query.get.map(_ ==> List(
          (1, (2, "a"), (7, "tx"), 8)
        ))
      } yield ()
    }


    "Nested" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.s).Tx(R3.s_("tx")).insert(
          (1, List((2, "a")))
        ).transact

        _ <- Ns.i.Rs1.*(R1.i.s).Tx(R3.s).query.get.map(_ ==> List(
          (1, List((2, "a")), "tx")
        ))
        _ <- Ns.i.Rs1.*?(R1.i.s).Tx(R3.s).query.get.map(_ ==> List(
          (1, List((2, "a")), "tx")
        ))
      } yield ()
    }


    "Nested ref + tx ref" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.R2.s).Tx(R3.s_("tx").R4.i_(7)).insert(
          (1, List((2, "a")))
        ).transact

        _ <- Ns.i.Rs1.*(R1.i.R2.s).Tx(R3.s.R4.i).query.get.map(_ ==> List(
          (1, List((2, "a")), "tx", 7)
        ))
        _ <- Ns.i.Rs1.*?(R1.i.R2.s).Tx(R3.s.R4.i).query.get.map(_ ==> List(
          (1, List((2, "a")), "tx", 7)
        ))
      } yield ()
    }


    "Nested + tx composite" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.s).Tx(R3.s_("tx").i_(7) + R4.i_(8)).insert(
          (1, List((2, "a")))
        ).transact

        _ <- Ns.i.Rs1.*(R1.i.s).Tx(R3.s.i + R4.i).query.get.map(_ ==> List(
          (1, List((2, "a")), ("tx", 7), 8)
        ))
        _ <- Ns.i.Rs1.*?(R1.i.s).Tx(R3.s.i + R4.i).query.get.map(_ ==> List(
          (1, List((2, "a")), ("tx", 7), 8)
        ))
      } yield ()
    }


    "Nested ref + tx composite" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.R2.s).Tx(R3.s_("tx").i_(7) + R4.i_(8)).insert(
          (1, List((2, "a")))
        ).transact

        _ <- Ns.i.Rs1.*(R1.i.R2.s).Tx(R3.s.i + R4.i).query.get.map(_ ==> List(
          (1, List((2, "a")), ("tx", 7), 8)
        ))
        _ <- Ns.i.Rs1.*?(R1.i.R2.s).Tx(R3.s.i + R4.i).query.get.map(_ ==> List(
          (1, List((2, "a")), ("tx", 7), 8)
        ))
      } yield ()
    }


    "Nested composite" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.s + R2.i).Tx(R3.s_("tx")).insert(
          (1, List(((2, "a"), 3)))
        ).transact

        _ <- Ns.i.Rs1.*(R1.i.s + R2.i).Tx(R3.s).query.get.map(_ ==> List(
          (1, List(((2, "a"), 3)), "tx")
        ))
        _ <- Ns.i.Rs1.*?(R1.i.s + R2.i).Tx(R3.s).query.get.map(_ ==> List(
          (1, List(((2, "a"), 3)), "tx")
        ))
      } yield ()
    }


    "Nested composite + tx ref" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.s + R2.i).Tx(R3.s_("tx").R4.i_(7)).insert(
          (1, List(((2, "a"), 3)))
        ).transact

        _ <- Ns.i.Rs1.*(R1.i.s + R2.i).Tx(R3.s.R4.i).query.get.map(_ ==> List(
          (1, List(((2, "a"), 3)), "tx", 7)
        ))
        _ <- Ns.i.Rs1.*?(R1.i.s + R2.i).Tx(R3.s.R4.i).query.get.map(_ ==> List(
          (1, List(((2, "a"), 3)), "tx", 7)
        ))
      } yield ()
    }


    "Nested composite + tx composite" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.s + R2.i).Tx(R3.s_("tx").i_(7) + R4.i_(8)).insert(
          (1, List(((2, "a"), 3)))
        ).transact

        _ <- Ns.i.Rs1.*(R1.i.s + R2.i).Tx(R3.s.i + R4.i).query.get.map(_ ==> List(
          (1, List(((2, "a"), 3)), ("tx", 7), 8)
        ))
        _ <- Ns.i.Rs1.*?(R1.i.s + R2.i).Tx(R3.s.i + R4.i).query.get.map(_ ==> List(
          (1, List(((2, "a"), 3)), ("tx", 7), 8)
        ))
      } yield ()
    }


    "Nested 2 levels composite + tx ref" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.Rs2.*(R2.i.s + R4.i)).Tx(R5.s_("tx").R6.i_(7)).insert(
          (1, List((2, List(((3, "a"), 4)))))
        ).transact

        _ <- Ns.i.Rs1.*(R1.i.Rs2.*(R2.i.s + R4.i)).Tx(R5.s.R6.i).query.get.map(_ ==> List(
          (1, List((2, List(((3, "a"), 4)))), "tx", 7)
        ))
        _ <- Ns.i.Rs1.*?(R1.i.Rs2.*?(R2.i.s + R4.i)).Tx(R5.s.R6.i).query.get.map(_ ==> List(
          (1, List((2, List(((3, "a"), 4)))), "tx", 7)
        ))
      } yield ()
    }


    "Nested 2 levels composite + tx composite" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.Rs2.*(R2.i.s + R4.i)).Tx(R5.s_("tx").i_(7) + R6.i_(8)).insert(
          (1, List((2, List(((3, "a"), 4)))))
        ).transact

        _ <- Ns.i.Rs1.*(R1.i.Rs2.*(R2.i.s + R4.i)).Tx(R5.s.i + R6.i).query.get.map(_ ==> List(
          (1, List((2, List(((3, "a"), 4)))), ("tx", 7), 8)
        ))
        _ <- Ns.i.Rs1.*?(R1.i.Rs2.*?(R2.i.s + R4.i)).Tx(R5.s.i + R6.i).query.get.map(_ ==> List(
          (1, List((2, List(((3, "a"), 4)))), ("tx", 7), 8)
        ))
      } yield ()
    }


    "Composites in branch: 1 + 1" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i + R2.i.Rs3.*(R3.i)).Tx(R5.s_("tx")).insert(
          (1, List((2, (3, List(5)))))
        ).transact

        _ <- Ns.i.Rs1.*(R1.i + R2.i.Rs3.*(R3.i)).Tx(R5.s).query.get.map(_ ==> List(
          (1, List((2, (3, List(5)))), "tx")
        ))
        _ <- Ns.i.Rs1.*?(R1.i + R2.i.Rs3.*?(R3.i)).Tx(R5.s).query.get.map(_ ==> List(
          (1, List((2, (3, List(5)))), "tx")
        ))
      } yield ()
    }

    "Composites in branch: 2 + 1" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.s + R2.i.Rs3.*(R3.i)).Tx(R5.s_("tx")).insert(
          (1, List(((2, "a"), (3, List(5)))))
        ).transact

        _ <- Ns.i.Rs1.*(R1.i.s + R2.i.Rs3.*(R3.i)).Tx(R5.s).query.get.map(_ ==> List(
          (1, List(((2, "a"), (3, List(5)))), "tx")
        ))
        _ <- Ns.i.Rs1.*?(R1.i.s + R2.i.Rs3.*?(R3.i)).Tx(R5.s).query.get.map(_ ==> List(
          (1, List(((2, "a"), (3, List(5)))), "tx")
        ))
      } yield ()
    }

    "Composites in branch: 2 + 2" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.s + R2.i.s.Rs3.*(R3.i)).Tx(R5.s_("tx")).insert(
          (1, List(((2, "a"), (3, "b", List(5)))))
        ).transact

        _ <- Ns.i.Rs1.*(R1.i.s + R2.i.s.Rs3.*(R3.i)).Tx(R5.s).query.get.map(_ ==> List(
          (1, List(((2, "a"), (3, "b", List(5)))), "tx")
        ))
        _ <- Ns.i.Rs1.*?(R1.i.s + R2.i.s.Rs3.*?(R3.i)).Tx(R5.s).query.get.map(_ ==> List(
          (1, List(((2, "a"), (3, "b", List(5)))), "tx")
        ))
      } yield ()
    }

    "Composites in branch: 2 + 1 + 2" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.s + R4.i + R2.s.Rs3.*(R3.i)).Tx(R5.s_("tx")).insert(
          (1, List(((2, "a"), 3, ("b", List(5)))))
        ).transact

        _ <- Ns.i.Rs1.*(R1.i.s + R4.i + R2.s.Rs3.*(R3.i)).Tx(R5.s).query.get.map(_ ==> List(
          (1, List(((2, "a"), 3, ("b", List(5)))), "tx")
        ))
        _ <- Ns.i.Rs1.*?(R1.i.s + R4.i + R2.s.Rs3.*?(R3.i)).Tx(R5.s).query.get.map(_ ==> List(
          (1, List(((2, "a"), 3, ("b", List(5)))), "tx")
        ))
      } yield ()
    }


    "Initial composite + composite in branch" - refs { implicit conn =>
      for {
        _ <- (Ns.i + R1.i.Rs2.*(R2.i.s + R3.i.Rs4.*(R4.i))).Tx(R5.s_("tx")).insert(
          (1, (2, Seq(((3, "a"), (4, List(5))))))
        ).transact

        _ <- (Ns.i + R1.i.Rs2.*(R2.i.s + R3.i.Rs4.*(R4.i))).Tx(R5.s).query.get.map(_ ==> List(
          (1, (2, Seq(((3, "a"), (4, List(5))))), "tx")
        ))
        _ <- (Ns.i + R1.i.Rs2.*?(R2.i.s + R3.i.Rs4.*?(R4.i))).Tx(R5.s).query.get.map(_ ==> List(
          (1, (2, Seq(((3, "a"), (4, List(5))))), "tx")
        ))
      } yield ()
    }


    "Composites everywhere" - refs { implicit conn =>
      for {
        _ <- (R1.i.s + Ns.i.s.Rs1
          .*(R1.i.s + R2.i.Rs3
            .*(R3.i.s + R4.i))).Tx(R5.s_("tx").i_(7) + R6.i_(8)).insert(
          ((1, "a"), (2, "b", List(((3, "c"), (4, List(((5, "d"), 6)))))))
        ).transact

        _ <- (R1.i.s + Ns.i.s.Rs1
          .*(R1.i.s + R2.i.Rs3
            .*(R3.i.s + R4.i))).Tx(R5.s.i + R6.i).query.get.map(_ ==> List(
          ((1, "a"), (2, "b", List(((3, "c"), (4, List(((5, "d"), 6)))))), ("tx", 7), 8)
        ))

        _ <- (R1.i.s + Ns.i.s.Rs1
          .*?(R1.i.s + R2.i.Rs3
            .*?(R3.i.s + R4.i))).Tx(R5.s.i + R6.i).query.get.map(_ ==> List(
          ((1, "a"), (2, "b", List(((3, "c"), (4, List(((5, "d"), 6)))))), ("tx", 7), 8)
        ))
      } yield ()
    }


    "Large tx meta data" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        _ <- Ns.string.Tx(Ns
          .int_(int1)
          .long_(long1)
          .double_(double1)
          .boolean_(boolean1)
          .date_(date1)
          .uuid_(uuid1)
        ).insert("With tx meta data").transact

        // Add data without tx meta data
        _ <- Ns.string.insert("Without tx meta data").transact

        // Data with and without tx meta data created
        _ <- Ns.string.query.get.map(_ ==> List(
          "With tx meta data",
          "Without tx meta data"
        ))

        // Use transaction meta data to filter
        _ <- Ns.string.Tx(Ns.int_(int1)).query.get.map(_ ==> List("With tx meta data"))
        _ <- Ns.string.Tx(Ns.long_(long1)).query.get.map(_ ==> List("With tx meta data"))
        _ <- Ns.string.Tx(Ns.double_(double1)).query.get.map(_ ==> List("With tx meta data"))
        _ <- Ns.string.Tx(Ns.boolean_(boolean1)).query.get.map(_ ==> List("With tx meta data"))
        _ <- Ns.string.Tx(Ns.date_(date1)).query.get.map(_ ==> List("With tx meta data"))
        _ <- Ns.string.Tx(Ns.uuid_(uuid1)).query.get.map(_ ==> List("With tx meta data"))

        // All tx meta data present
        _ <- Ns.string.Tx(Ns
          .int_(int1)
          .long_(long1)
          .double_(double1)
          .boolean_(boolean1)
          .date_(date1)
          .uuid_(uuid1)
        ).query.get.map(_ ==> List("With tx meta data"))
      } yield ()
    }


    "Apply tx meta data to tacit attributes only" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        _ <- Ns.int.Tx(Ns.string).insert(0, "a").transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> s"For inserts, tx meta data must be applied to tacit attributes, like Ns.string_(<metadata>)"
        }

        _ <- Ns.int.Tx(Ns.string("a")).insert(List((0, "b"))).transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> s"For inserts, tx meta data must be applied to tacit attributes, like Ns.string_(<metadata>)"
        }

        _ <- Ns.int.Tx(Ns.string_?).insert(List((0, Some("b")))).transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> s"For inserts, tx meta data must be applied to tacit attributes, like Ns.string_(<metadata>)"
        }

        _ <- Ns.int.Tx(Ns.string_?(Some("a"))).insert(List((0, Some("b")))).transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> s"For inserts, tx meta data must be applied to tacit attributes, like Ns.string_(<metadata>)"
        }

        _ <- Ns.int.Tx(Ns.string_).insert(0).transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> s"Missing applied value for attribute Ns.string"
        }
      } yield ()
    }
  }
}
