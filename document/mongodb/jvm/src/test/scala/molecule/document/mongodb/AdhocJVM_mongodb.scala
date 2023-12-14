package molecule.document.mongodb

import com.mongodb.client.model.Filters
import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.document.mongodb.AdhocJVM_mongodb.int2
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.TestSuite_mongodb
import utest._
import scala.collection.immutable.List
import scala.language.implicitConversions


object AdhocJVM_mongodb extends TestSuite_mongodb {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        //        _ <- Ns.i(1).save.transact
        //        _ <- Ns.i.query.get.map(_ ==> List(1))

        _ <- Ns.i.insert(1, 2).transact
        _ <- Ns.i_(1).delete.transact
        _ <- Ns.i.query.get.map(_ ==> List(2))

        //        _ <- rawTransact(
        //          """{
        //            |  "collection": "Ns",
        //            |  "data": [
        //            |    {
        //            |      "i": 1,
        //            |      "s": "a"
        //            |    },
        //            |    {
        //            |      "i": 2,
        //            |      "s": "b"
        //            |    },
        //            |  ]
        //            |}
        //            |""".stripMargin)
        //        _ <- rawQuery(
        //          """{
        //            |  "collection": "A",
        //            |  "pipeline": [
        //            |    {
        //            |      "$match": {
        //            |        "$and": [
        //            |          {
        //            |            "i": {
        //            |              "$ne": null
        //            |            }
        //            |          }
        //            |        ]
        //            |      }
        //            |    },
        //            |  ]
        //            |}
        //            |""".stripMargin, false)//.map(println)

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        //        _ <- A.i(0).s("a").B.i(1).s("b").Cc.i(22)
        //          ._B.C.i(2).s("c")
        //          ._B._A.Bb.i(11)
        //          .save.transact
        //
        //        _ <- A.i.B.i.query.get.map(_ ==> List((0, 1)))
        //        _ <- A.i.B.i._A.s.query.get.map(_ ==> List((0, 1, "a")))
        //        _ <- A.i.B.i._A.Bb.i.query.get.map(_ ==> List((0, 1, 11)))
        //        _ <- A.i.B.i.C.i._B.s.query.get.map(_ ==> List((0, 1, 2, "b")))
        //        _ <- A.i.B.C.i.query.get.map(_ ==> List((0, 2)))
        //        _ <- A.i.B.C.i._B.i.query.get.map(_ ==> List((0, 2, 1)))
        //        _ <- A.i.B.i.C.i._B.Cc.i.query.get.map(_ ==> List((0, 1, 2, 22)))
        //        _ <- A.i.B.C.i._B.Cc.i.query.get.map(_ ==> List((0, 2, 22)))
        //        _ <- A.i.B.i.C.i._B.s._A.s.query.get.map(_ ==> List((0, 1, 2, "b", "a")))
        //        _ <- A.i.B.i.C.i._B._A.s.query.get.map(_ ==> List((0, 1, 2, "a")))
        //        _ <- A.i.B.C.i._B._A.s.query.get.map(_ ==> List((0, 2, "a")))
        //        _ <- A.i.B.i.C.i._B.s._A.Bb.i.query.get.map(_ ==> List((0, 1, 2, "b", 11)))
        //        _ <- A.i.B.i.C.i._B._A.Bb.i.query.get.map(_ ==> List((0, 1, 2, 11)))
        //        _ <- A.i.B.C.i._B._A.Bb.i.query.get.map(_ ==> List((0, 2, 11)))
        //        _ <- A.B.C.s._B._A.Bb.i.query.get.map(_ ==> List(("c", 11)))

        //        _ <- B.i.C.i.s.insert((1, 2, "x")).transact
        //        _ <- B.i.C.i.s.query.get.map(_ ==> List((1, 2, "x")))

        //                _ <- A.i.B.i.s.Cc.*(C.s.i.D.i).insert(1, 2, "x", List(("y", 3, 4))).transact
        //                _ <- A.i.B.i.s.Cc.*(C.s.i.D.i).query.get.map(_ ==> List((1, 2, "x", List(("y", 3, 4)))))

        //                _ <- B.i.C.i.s._B.C1.s.D.i.insert((1, 2, "x", "a", 3)).transact
        //                _ <- B.i.C.i.s._B.C1.s.D.i.query.get.map(_ ==> List((1, 2, "x", "a", 3)))
        //
        //                _ <- A.i.Bb.*(B.i.C.i.s._B.C1.s.D.i).insert(0, List((1, 2, "x", "a", 3))).transact
        //                _ <- A.i.Bb.*(B.i.C.i.s._B.C1.s.D.i).query.get.map(_ ==> List((0, List((1, 2, "x", "a", 3)))))
        //                _ <- A.i_.Bb.*(B.i.C.i._B.C1.s.D.i).query.get.map(_ ==> List(List((1, 2, "a", 3))))
        //                _ <- A.i_.Bb.*?(B.i.C.i._B.C1.s.D.i).query.get.map(_ ==> List(List((1, 2, "a", 3))))
        //
        //        _ <- A.i_.Bb.*(B.i.C.i._B.C1.D.i).query.get.map(_ ==> List(List((1, 2, 3))))
        //        _ <- A.i_.Bb.*?(B.i.C.i._B.C1.D.i).query.get.map(_ ==> List(List((1, 2, 3))))
        //
        //                _ <- A.i.Bb.*(B.i.C.i._B.C1.s.D.i.Ee.*(E.i.F.i)).insert(1, List((1, 2, "a", 3, List((4, 5))))).transact
        //                _ <- A.i_(1).Bb.*(B.i.C.i._B.C1.s.D.i.Ee.*(E.i.F.i)).query.get.map(_ ==> List(List((1, 2, "a", 3, List((4, 5))))))
        //                _ <- A.i_(1).Bb.*?(B.i.C.i._B.C1.s.D.i.Ee.*?(E.i.F.i)).query.get.map(_ ==> List(List((1, 2, "a", 3, List((4, 5))))))
        //
        //        _ <- A.i.Bb.*(B.i.C.i.Dd.*(D.i.E.i._D.E1.s.F.i)).insert(2, List((1, 2, List((3, 4, "a", 5))))).transact
        //        _ <- A.i_(2).Bb.*(B.i.C.i.Dd.*(D.i.E.i._D.E1.s.F.i)).query.get.map(_ ==> List(List((1, 2, List((3, 4, "a", 5))))))
        //        _ <- A.i_(2).Bb.*?(B.i.C.i.Dd.*?(D.i.E.i._D.E1.s.F.i)).query.get.map(_ ==> List(List((1, 2, List((3, 4, "a", 5))))))


        //        _ <- A.i.Bb.*(B.i).insert(List(
        ////          (0, Nil),
        //          (1, List(10, 11)),
        //        )).transact
        //
        ////        _ <- A.i.a1.query.get.map(_ ==> List(0, 1))
        //        _ <- A.i.Bb.*(B.i).query.get.map(_ ==> List(
        //          (1, List(10, 11))
        //        ))


        //        _ <- A.bool.OwnBb.*(B.s.OwnCc.*(C.i)).insert(List(
        //          (true, List(("a", List(1)))),
        //          (false, List(
        //            ("b", List(2, 3)),
        //            ("c", List(4, 5)),
        //          )),
        //        )).transact
        //
        ////        _ <- A.bool.OwnBb.s.OwnCc.i.query.i.get.map(_ ==> List(
        ////          (true, "a", 1),
        ////          (false, "b", 2),
        ////          (false, "b", 3),
        ////          (false, "c", 4),
        ////          (false, "c", 5),
        ////        ))
        //
        //        _ <- A.bool.OwnBb.*(B.s.OwnCc.*(C.i)).query.get.map(_ ==> List(
        //          (true, List(("a", List(1)))),
        //          (false, List(
        //            ("b", List(2, 3)),
        //            ("c", List(4, 5)),
        //          )),
        //        ))


        //                _ <- A.i.OwnBb.*(B.i.OwnCc.*(C.i)).insert(List(
        //        //          (0, Nil),
        //        //          (1, List((10, Nil))),
        //                  (1, List(
        //                    (10, List(100, 101)),
        //        //            (12, List(121, 122)),
        //                  )),
        //                  (2, List((20, List(200)))),
        //                )).transact
        //
        //                _ <- A.i.a1.OwnBb.*(B.i.OwnCc.*(C.i)).query.get.map(_ ==> List(
        //                  //          (0, Nil),
        //                  //          (1, List((10, Nil))),
        //                  (1, List(
        //                    (10, List(100, 101)),
        //                    //            (12, List(121, 122)),
        //                  )),
        //                  (2, List((20, List(200)))),
        //                ))
        //
        //
        //        _ <- A.i.Bb.*(B.i.Cc.*(C.i)).insert(List(
        //          (0, Nil),
        //          (1, List((10, Nil))),
        //          //          (1, List(
        //          //            (10, List(100, 101)),
        //          //            //            (12, List(121, 122)),
        //          //          )),
        //          (2, List((20, List(200)))),
        //        )).i.transact
        //
        //        _ <- A.i.a1.Bb.*(B.i.Cc.*(C.i)).query.get.map(_ ==> List(
        //          (0, Nil),
        //          (1, List((10, Nil))),
        //          //          (1, List(
        //          //            (10, List(100, 101)),
        //          //            //            (12, List(121, 122)),
        //          //          )),
        //          (2, List((20, List(200)))),
        //        ))
        //
        //
        ////        _ <- A.i.a1.query.get.map(_ ==> List(0, 1, 2))
        //        _ <- A.i.a1.Bb.*(B.i).query.i.get.map(_ ==> List(
        //          // (0, Nil) not included
        //          (1, List(10)),
        //          (2, List(10)),
        ////          (3, List(10)),
        ////          (4, List(10)),
        ////          (5, List(10)),
        ////          (6, List(10)),
        ////          (7, List(10))
        //        ))


        _ <- A.i.OwnBb.*(
          B.i.Cc.*(
            C.i.OwnDd.*(
              D.i
              //                .Ee.*(
              //                  E.i.Ff.*(
              //                    F.i.Gg.*(
              //                      G.i.Hh.*(
              //                        H.i
              //                      )
              //                    )
              //                  )
              //                )

            )
          )
        ).insert(List(
          //          (0, Nil),
          //          (1, List((10, Nil))),
          //          (2, List((10, List((20, Nil))))),
          //          (3, List((10, List((20, List((30, Nil))))))),

          (2, List((10, List((20, Nil))))),
          (3, List((10, List((20, List(30)))))),

          //                            (4, List((10, List((20, List((30, List((40, Nil))))))))),
          //                            (5, List((10, List((20, List((30, List((40, List((50, Nil))))))))))),
          //                            (6, List((10, List((20, List((30, List((40, List((50, List((60, Nil))))))))))))),
          //                            (7, List((10, List((20, List((30, List((40, List((50, List((60, List(70)))))))))))))),
        )).transact
        //
        //
        //        // 0 levels
        //        //        _ <- A.i.a1.query.get.map(_ ==> List(0, 1, 2, 3, 4, 5, 6, 7))
        //        //        // 1 level, mandatory nested data
        //        //        _ <- A.i.a1.Bb.*(B.i).query.i.get.map(_ ==> List(
        //        //          // (0, Nil) not included
        //        //          (1, List(10)),
        //        //          (2, List(10)),
        //        //          (3, List(10)),
        //        //          (4, List(10)),
        //        //          (5, List(10)),
        //        //          (6, List(10)),
        //        //          (7, List(10))
        //        //        ))
        //        //        // 1 level, optional nested data
        //        //        _ <- A.i.a1.Bb.*?(B.i).query.get.map(_ ==> List(
        //        //          (0, Nil), // Empty nested data included
        //        //          (1, List(10)),
        //        //          (2, List(10)),
        //        //          (3, List(10)),
        //        //          (4, List(10)),
        //        //          (5, List(10)),
        //        //          (6, List(10)),
        //        //          (7, List(10))
        //        //        ))
        //        //        // 2 levels
        //        _ <- A.i.a1.Bb.*(B.i.Cc.*(C.i)).query.get.map(_ ==> List(
        //          //          (2, List((10, List(20)))),
        //          (3, List((10, List(20)))),
        //          //          (4, List((10, List(20)))),
        //          //          (5, List((10, List(20)))),
        //          //          (6, List((10, List(20)))),
        //          //          (7, List((10, List(20))))
        //        ))
        //        //        _ <- A.i.a1.Bb.*?(B.i.Cc.*?(C.i)).query.i.get.map(_ ==> List(
        //        //          (0, Nil),
        //        //          (1, List((10, Nil))),
        //        //          (2, List((10, List(20)))),
        //        //          (3, List((10, List(20)))),
        //        //          (4, List((10, List(20)))),
        //        //          (5, List((10, List(20)))),
        //        //          (6, List((10, List(20)))),
        //        //          (7, List((10, List(20))))
        //        //        ))

        // 3 levels
        _ <- A.i.a1.OwnBb.*(B.i.Cc.*(C.i.OwnDd.*(D.i))).query.i.get.map(_ ==> List(
        (3, List((10, List((20, List(30)))))),
        //          (4, List((10, List((20, List(30)))))),
        //          (5, List((10, List((20, List(30)))))),
        //          (6, List((10, List((20, List(30)))))),
        //          (7, List((10, List((20, List(30))))))
        ))
        //        _ <- A.i.a1.Bb.*?(B.i.Cc.*?(C.i.Dd.*?(D.i))).query.get.map(_ ==> List(
        //          (0, Nil),
        //          (1, List((10, Nil))),
        //          (2, List((10, List((20, Nil))))),
        //          (3, List((10, List((20, List(30)))))),
        //          (4, List((10, List((20, List(30)))))),
        //          (5, List((10, List((20, List(30)))))),
        //          (6, List((10, List((20, List(30)))))),
        //          (7, List((10, List((20, List(30))))))
        //        ))

      } yield ()
    }



    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Uniques._
    //      for {
    //        _ <- Uniques.i(1).save.transact
    //
    //      } yield ()
    //    }
    //
    //
    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //
    //      for {
    //        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
    //
    //      } yield ()
    //    }
  }
}
