package molecule.coreTests.spi.relation.nested

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Refs._
import molecule.coreTests.setup._


case class NestedLevels(
  suite: MUnitSuite,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "All levels" - refs { implicit conn =>
    for {
      _ <- A.i.Bb.*(
        B.i.Cc.*(
          C.i.Dd.*(
            D.i.Ee.*(
              E.i.Ff.*(
                F.i.Gg.*(
                  G.i.Hh.*(
                    H.i
                  )
                )
              )
            )
          )
        )
      ).insert(List(
        (0, Nil),
        (1, List((10, Nil))),
        (2, List((10, List((20, Nil))))),
        (3, List((10, List((20, List((30, Nil))))))),
        (4, List((10, List((20, List((30, List((40, Nil))))))))),
        (5, List((10, List((20, List((30, List((40, List((50, Nil))))))))))),
        (6, List((10, List((20, List((30, List((40, List((50, List((60, Nil))))))))))))),
        (7, List((10, List((20, List((30, List((40, List((50, List((60, List(70)))))))))))))),
      )).transact


      // 0 levels
      _ <- A.i.a1.query.get.map(_ ==> List(0, 1, 2, 3, 4, 5, 6, 7))


      // 1 level, mandatory nested data
      _ <- A.i.a1.Bb.*(B.i).query.get.map(_ ==> List(
        // (0, Nil) not included
        (1, List(10)),
        (2, List(10)),
        (3, List(10)),
        (4, List(10)),
        (5, List(10)),
        (6, List(10)),
        (7, List(10))
      ))
      // 1 level, optional nested data
      _ <- A.i.a1.Bb.*?(B.i).query.get.map(_ ==> List(
        (0, Nil), // Empty nested data included
        (1, List(10)),
        (2, List(10)),
        (3, List(10)),
        (4, List(10)),
        (5, List(10)),
        (6, List(10)),
        (7, List(10))
      ))


      // 2 levels
      _ <- A.i.a1.Bb.*(B.i.Cc.*(C.i)).query.get.map(_ ==> List(
        (2, List((10, List(20)))),
        (3, List((10, List(20)))),
        (4, List((10, List(20)))),
        (5, List((10, List(20)))),
        (6, List((10, List(20)))),
        (7, List((10, List(20))))
      ))
      _ <- A.i.a1.Bb.*?(B.i.Cc.*?(C.i)).query.get.map(_ ==> List(
        (0, Nil),
        (1, List((10, Nil))),
        (2, List((10, List(20)))),
        (3, List((10, List(20)))),
        (4, List((10, List(20)))),
        (5, List((10, List(20)))),
        (6, List((10, List(20)))),
        (7, List((10, List(20))))
      ))


      // 3 levels
      _ <- A.i.a1.Bb.*(B.i.Cc.*(C.i.Dd.*(D.i))).query.get.map(_ ==> List(
        (3, List((10, List((20, List(30)))))),
        (4, List((10, List((20, List(30)))))),
        (5, List((10, List((20, List(30)))))),
        (6, List((10, List((20, List(30)))))),
        (7, List((10, List((20, List(30))))))
      ))
      _ <- A.i.a1.Bb.*?(B.i.Cc.*?(C.i.Dd.*?(D.i))).query.get.map(_ ==> List(
        (0, Nil),
        (1, List((10, Nil))),
        (2, List((10, List((20, Nil))))),
        (3, List((10, List((20, List(30)))))),
        (4, List((10, List((20, List(30)))))),
        (5, List((10, List((20, List(30)))))),
        (6, List((10, List((20, List(30)))))),
        (7, List((10, List((20, List(30))))))
      ))


      // 4 levels
      _ <- A.i.a1.Bb.*(B.i.Cc.*(C.i.Dd.*(D.i.Ee.*(E.i)))).query.get.map(_ ==> List(
        (4, List((10, List((20, List((30, List(40)))))))),
        (5, List((10, List((20, List((30, List(40)))))))),
        (6, List((10, List((20, List((30, List(40)))))))),
        (7, List((10, List((20, List((30, List(40))))))))
      ))
      _ <- A.i.a1.Bb.*?(B.i.Cc.*?(C.i.Dd.*?(D.i.Ee.*?(E.i)))).query.get.map(_ ==> List(
        (0, Nil),
        (1, List((10, Nil))),
        (2, List((10, List((20, Nil))))),
        (3, List((10, List((20, List((30, Nil))))))),
        (4, List((10, List((20, List((30, List(40)))))))),
        (5, List((10, List((20, List((30, List(40)))))))),
        (6, List((10, List((20, List((30, List(40)))))))),
        (7, List((10, List((20, List((30, List(40))))))))
      ))


      // 5 levels
      _ <- A.i.a1.Bb.*(B.i.Cc.*(C.i.Dd.*(D.i.Ee.*(E.i.Ff.*(F.i))))).query.get.map(_ ==> List(
        (5, List((10, List((20, List((30, List((40, List(50)))))))))),
        (6, List((10, List((20, List((30, List((40, List(50)))))))))),
        (7, List((10, List((20, List((30, List((40, List(50))))))))))
      ))
      _ <- A.i.a1.Bb.*?(B.i.Cc.*?(C.i.Dd.*?(D.i.Ee.*?(E.i.Ff.*?(F.i))))).query.get.map(_ ==> List(
        (0, Nil),
        (1, List((10, Nil))),
        (2, List((10, List((20, Nil))))),
        (3, List((10, List((20, List((30, Nil))))))),
        (4, List((10, List((20, List((30, List((40, Nil))))))))),
        (5, List((10, List((20, List((30, List((40, List(50)))))))))),
        (6, List((10, List((20, List((30, List((40, List(50)))))))))),
        (7, List((10, List((20, List((30, List((40, List(50))))))))))
      ))


      // 6 levels
      _ <- A.i.a1.Bb.*(B.i.Cc.*(C.i.Dd.*(D.i.Ee.*(E.i.Ff.*(F.i.Gg.*(G.i)))))).query.get.map(_ ==> List(
        (6, List((10, List((20, List((30, List((40, List((50, List(60)))))))))))),
        (7, List((10, List((20, List((30, List((40, List((50, List(60))))))))))))
      ))
      _ <- A.i.a1.Bb.*?(B.i.Cc.*?(C.i.Dd.*?(D.i.Ee.*?(E.i.Ff.*?(F.i.Gg.*?(G.i)))))).query.get.map(_ ==> List(
        (0, Nil),
        (1, List((10, Nil))),
        (2, List((10, List((20, Nil))))),
        (3, List((10, List((20, List((30, Nil))))))),
        (4, List((10, List((20, List((30, List((40, Nil))))))))),
        (5, List((10, List((20, List((30, List((40, List((50, Nil))))))))))),
        (6, List((10, List((20, List((30, List((40, List((50, List(60)))))))))))),
        (7, List((10, List((20, List((30, List((40, List((50, List(60))))))))))))
      ))


      // 7 levels
      _ <- A.i.a1.Bb.*(B.i.Cc.*(C.i.Dd.*(D.i.Ee.*(E.i.Ff.*(F.i.Gg.*(G.i.Hh.*(H.i))))))).query.get.map(_ ==> List(
        (7, List((10, List((20, List((30, List((40, List((50, List((60, List(70))))))))))))))
      ))
      _ <- A.i.a1.Bb.*?(B.i.Cc.*?(C.i.Dd.*?(D.i.Ee.*?(E.i.Ff.*?(F.i.Gg.*?(G.i.Hh.*?(H.i))))))).query.get.map(_ ==> List(
        (0, Nil),
        (1, List((10, Nil))),
        (2, List((10, List((20, Nil))))),
        (3, List((10, List((20, List((30, Nil))))))),
        (4, List((10, List((20, List((30, List((40, Nil))))))))),
        (5, List((10, List((20, List((30, List((40, List((50, Nil))))))))))),
        (6, List((10, List((20, List((30, List((40, List((50, List((60, Nil))))))))))))),
        (7, List((10, List((20, List((30, List((40, List((50, List((60, List(70))))))))))))))
      ))
    } yield ()
  }
}
