package molecule.datalog.datomic.test.txMetaData

import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
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
        _ <- A.i.Tx(C.i_(7).s_("tx").ii_(Set(8, 9))).insert(1).transact
        _ <- A.i.Tx(C.i.s.ii).query.get.map(_ ==> List(
          (1, 7, "tx", Set(8, 9))
        ))
      } yield ()
    }

    "Tx ref" - refs { implicit conn =>
      for {
        _ <- A.i.Tx(C.i_(7).D.i_(8)).insert(1).transact
        _ <- A.i.Tx(C.i.D.i).query.get.map(_ ==> List(
          (1, 7, 8)
        ))
      } yield ()
    }

    "Tx backref" - refs { implicit conn =>
      for {
        _ <- A.i.Tx(C.i_(7).D.i_(8)._C.s_("tx")).insert(1).transact
        _ <- A.i.Tx(C.i.D.i._C.s).query.get.map(_ ==> List(
          (1, 7, 8, "tx")
        ))
      } yield ()
    }

    "Tx composite" - refs { implicit conn =>
      for {
        _ <- A.i.Tx(C.i_(7).s_("tx") + B.i_(8)).insert(1).transact
        _ <- A.i.Tx(C.i.s + B.i).query.get.map(_ ==> List(
          (1, (7, "tx"), 8)
        ))
      } yield ()
    }

    "Composite + tx" - refs { implicit conn =>
      for {
        _ <- (A.i + C.i.s).Tx(E.i_(7).s_("tx")).insert(
          (1, (2, "a"))
        ).transact

        _ <- (A.i + C.i.s).Tx(E.i.s).query.get.map(_ ==> List(
          (1, (2, "a"), 7, "tx")
        ))
      } yield ()
    }

    "Composite + tx composite" - refs { implicit conn =>
      for {
        _ <- (A.i + C.i.s).Tx(D.i_(7).s_("tx") + E.i_(8)).insert(
          (1, (2, "a"))
        ).transact

        _ <- (A.i + C.i.s).Tx(D.i.s + E.i).query.get.map(_ ==> List(
          (1, (2, "a"), (7, "tx"), 8)
        ))
      } yield ()
    }


    "Nested" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(B.i.s).Tx(D.s_("tx")).insert(
          (1, List((2, "a")))
        ).transact

        _ <- A.i.Bb.*(B.i.s).Tx(D.s).query.get.map(_ ==> List(
          (1, List((2, "a")), "tx")
        ))
        _ <- A.i.Bb.*?(B.i.s).Tx(D.s).query.get.map(_ ==> List(
          (1, List((2, "a")), "tx")
        ))
      } yield ()
    }


    "Nested ref + tx ref" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(B.i.C.s).Tx(D.s_("tx").E.i_(7)).insert(
          (1, List((2, "a")))
        ).transact

        _ <- A.i.Bb.*(B.i.C.s).Tx(D.s.E.i).query.get.map(_ ==> List(
          (1, List((2, "a")), "tx", 7)
        ))
        _ <- A.i.Bb.*?(B.i.C.s).Tx(D.s.E.i).query.get.map(_ ==> List(
          (1, List((2, "a")), "tx", 7)
        ))
      } yield ()
    }


    "Nested + tx composite" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(B.i.s).Tx(D.s_("tx").i_(7) + E.i_(8)).insert(
          (1, List((2, "a")))
        ).transact

        _ <- A.i.Bb.*(B.i.s).Tx(D.s.i + E.i).query.get.map(_ ==> List(
          (1, List((2, "a")), ("tx", 7), 8)
        ))
        _ <- A.i.Bb.*?(B.i.s).Tx(D.s.i + E.i).query.get.map(_ ==> List(
          (1, List((2, "a")), ("tx", 7), 8)
        ))
      } yield ()
    }


    "Nested ref + tx composite" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(B.i.C.s).Tx(D.s_("tx").i_(7) + E.i_(8)).insert(
          (1, List((2, "a")))
        ).transact

        _ <- A.i.Bb.*(B.i.C.s).Tx(D.s.i + E.i).query.get.map(_ ==> List(
          (1, List((2, "a")), ("tx", 7), 8)
        ))
        _ <- A.i.Bb.*?(B.i.C.s).Tx(D.s.i + E.i).query.get.map(_ ==> List(
          (1, List((2, "a")), ("tx", 7), 8)
        ))
      } yield ()
    }


    "Nested composite" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(B.i.s + C.i).Tx(D.s_("tx")).insert(
          (1, List(((2, "a"), 3)))
        ).transact

        _ <- A.i.Bb.*(B.i.s + C.i).Tx(D.s).query.get.map(_ ==> List(
          (1, List(((2, "a"), 3)), "tx")
        ))
        _ <- A.i.Bb.*?(B.i.s + C.i).Tx(D.s).query.get.map(_ ==> List(
          (1, List(((2, "a"), 3)), "tx")
        ))
      } yield ()
    }


    "Nested composite + tx ref" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(B.i.s + C.i).Tx(D.s_("tx").E.i_(7)).insert(
          (1, List(((2, "a"), 3)))
        ).transact

        _ <- A.i.Bb.*(B.i.s + C.i).Tx(D.s.E.i).query.get.map(_ ==> List(
          (1, List(((2, "a"), 3)), "tx", 7)
        ))
        _ <- A.i.Bb.*?(B.i.s + C.i).Tx(D.s.E.i).query.get.map(_ ==> List(
          (1, List(((2, "a"), 3)), "tx", 7)
        ))
      } yield ()
    }


    "Nested composite + tx composite" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(B.i.s + C.i).Tx(D.s_("tx").i_(7) + E.i_(8)).insert(
          (1, List(((2, "a"), 3)))
        ).transact

        _ <- A.i.Bb.*(B.i.s + C.i).Tx(D.s.i + E.i).query.get.map(_ ==> List(
          (1, List(((2, "a"), 3)), ("tx", 7), 8)
        ))
        _ <- A.i.Bb.*?(B.i.s + C.i).Tx(D.s.i + E.i).query.get.map(_ ==> List(
          (1, List(((2, "a"), 3)), ("tx", 7), 8)
        ))
      } yield ()
    }


    "Nested 2 levels composite + tx ref" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(B.i.Cc.*(C.i.s + E.i)).Tx(F.s_("tx").G.i_(7)).insert(
          (1, List((2, List(((3, "a"), 4)))))
        ).transact

        _ <- A.i.Bb.*(B.i.Cc.*(C.i.s + E.i)).Tx(F.s.G.i).query.get.map(_ ==> List(
          (1, List((2, List(((3, "a"), 4)))), "tx", 7)
        ))
        _ <- A.i.Bb.*?(B.i.Cc.*?(C.i.s + E.i)).Tx(F.s.G.i).query.get.map(_ ==> List(
          (1, List((2, List(((3, "a"), 4)))), "tx", 7)
        ))
      } yield ()
    }


    "Nested 2 levels composite + tx composite" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(B.i.Cc.*(C.i.s + E.i)).Tx(F.s_("tx").i_(7) + G.i_(8)).insert(
          (1, List((2, List(((3, "a"), 4)))))
        ).transact

        _ <- A.i.Bb.*(B.i.Cc.*(C.i.s + E.i)).Tx(F.s.i + G.i).query.get.map(_ ==> List(
          (1, List((2, List(((3, "a"), 4)))), ("tx", 7), 8)
        ))
        _ <- A.i.Bb.*?(B.i.Cc.*?(C.i.s + E.i)).Tx(F.s.i + G.i).query.get.map(_ ==> List(
          (1, List((2, List(((3, "a"), 4)))), ("tx", 7), 8)
        ))
      } yield ()
    }


    "Composites in branch: 1 + 1" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(B.i + C.i.Dd.*(D.i)).Tx(F.s_("tx")).insert(
          (1, List((2, (3, List(5)))))
        ).transact

        _ <- A.i.Bb.*(B.i + C.i.Dd.*(D.i)).Tx(F.s).query.get.map(_ ==> List(
          (1, List((2, (3, List(5)))), "tx")
        ))
        _ <- A.i.Bb.*?(B.i + C.i.Dd.*?(D.i)).Tx(F.s).query.get.map(_ ==> List(
          (1, List((2, (3, List(5)))), "tx")
        ))
      } yield ()
    }

    "Composites in branch: 2 + 1" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(B.i.s + C.i.Dd.*(D.i)).Tx(F.s_("tx")).insert(
          (1, List(((2, "a"), (3, List(5)))))
        ).transact

        _ <- A.i.Bb.*(B.i.s + C.i.Dd.*(D.i)).Tx(F.s).query.get.map(_ ==> List(
          (1, List(((2, "a"), (3, List(5)))), "tx")
        ))
        _ <- A.i.Bb.*?(B.i.s + C.i.Dd.*?(D.i)).Tx(F.s).query.get.map(_ ==> List(
          (1, List(((2, "a"), (3, List(5)))), "tx")
        ))
      } yield ()
    }

    "Composites in branch: 2 + 2" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(B.i.s + C.i.s.Dd.*(D.i)).Tx(F.s_("tx")).insert(
          (1, List(((2, "a"), (3, "b", List(5)))))
        ).transact

        _ <- A.i.Bb.*(B.i.s + C.i.s.Dd.*(D.i)).Tx(F.s).query.get.map(_ ==> List(
          (1, List(((2, "a"), (3, "b", List(5)))), "tx")
        ))
        _ <- A.i.Bb.*?(B.i.s + C.i.s.Dd.*?(D.i)).Tx(F.s).query.get.map(_ ==> List(
          (1, List(((2, "a"), (3, "b", List(5)))), "tx")
        ))
      } yield ()
    }

    "Composites in branch: 2 + 1 + 2" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(B.i.s + E.i + C.s.Dd.*(D.i)).Tx(F.s_("tx")).insert(
          (1, List(((2, "a"), 3, ("b", List(5)))))
        ).transact

        _ <- A.i.Bb.*(B.i.s + E.i + C.s.Dd.*(D.i)).Tx(F.s).query.get.map(_ ==> List(
          (1, List(((2, "a"), 3, ("b", List(5)))), "tx")
        ))
        _ <- A.i.Bb.*?(B.i.s + E.i + C.s.Dd.*?(D.i)).Tx(F.s).query.get.map(_ ==> List(
          (1, List(((2, "a"), 3, ("b", List(5)))), "tx")
        ))
      } yield ()
    }


    "Initial composite + composite in branch" - refs { implicit conn =>
      for {
        _ <- (A.i + B.i.Cc.*(C.i.s + D.i.Ee.*(E.i))).Tx(F.s_("tx")).insert(
          (1, (2, Seq(((3, "a"), (4, List(5))))))
        ).transact

        _ <- (A.i + B.i.Cc.*(C.i.s + D.i.Ee.*(E.i))).Tx(F.s).query.get.map(_ ==> List(
          (1, (2, Seq(((3, "a"), (4, List(5))))), "tx")
        ))
        _ <- (A.i + B.i.Cc.*?(C.i.s + D.i.Ee.*?(E.i))).Tx(F.s).query.get.map(_ ==> List(
          (1, (2, Seq(((3, "a"), (4, List(5))))), "tx")
        ))
      } yield ()
    }


    "Composites everywhere" - refs { implicit conn =>
      for {
        _ <- (B.i.s + A.i.s.Bb
          .*(B.i.s + C.i.Dd
            .*(D.i.s + E.i))).Tx(F.s_("tx").i_(7) + G.i_(8)).insert(
          ((1, "a"), (2, "b", List(((3, "c"), (4, List(((5, "d"), 6)))))))
        ).transact

        _ <- (B.i.s + A.i.s.Bb
          .*(B.i.s + C.i.Dd
            .*(D.i.s + E.i))).Tx(F.s.i + G.i).query.get.map(_ ==> List(
          ((1, "a"), (2, "b", List(((3, "c"), (4, List(((5, "d"), 6)))))), ("tx", 7), 8)
        ))

        _ <- (B.i.s + A.i.s.Bb
          .*?(B.i.s + C.i.Dd
            .*?(D.i.s + E.i))).Tx(F.s.i + G.i).query.get.map(_ ==> List(
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
