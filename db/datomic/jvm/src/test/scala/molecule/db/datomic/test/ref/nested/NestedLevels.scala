package molecule.db.datomic.test.ref.nested

import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object NestedLevels extends DatomicTestSuite {


  lazy val tests = Tests {

    "All levels" - refs { implicit conn =>
      Ns.i.Rs1.*(
        R1.i.Rs2.*(
          R2.i.Rs3.*(
            R3.i.Rs4.*(
              R4.i.Rs5.*(
                R5.i.Rs6.*(
                  R6.i.Rs7.*(
                    R7.i
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
      Ns.i.a1.query.get ==> List(0, 1, 2, 3, 4, 5, 6, 7)


      // 1 level, mandatory nested data
      Ns.i.a1.Rs1.*(R1.i).query.get ==> List(
        // (0, Nil) not included
        (1, List(10)),
        (2, List(10)),
        (3, List(10)),
        (4, List(10)),
        (5, List(10)),
        (6, List(10)),
        (7, List(10))
      )
      // 1 level, optional nested data
      Ns.i.a1.Rs1.*?(R1.i).query.get ==> List(
        (0, Nil), // Empty nested data included
        (1, List(10)),
        (2, List(10)),
        (3, List(10)),
        (4, List(10)),
        (5, List(10)),
        (6, List(10)),
        (7, List(10))
      )


      // 2 levels
      Ns.i.a1.Rs1.*(R1.i.Rs2.*(R2.i)).query.get ==> List(
        (2, List((10, List(20)))),
        (3, List((10, List(20)))),
        (4, List((10, List(20)))),
        (5, List((10, List(20)))),
        (6, List((10, List(20)))),
        (7, List((10, List(20))))
      )
      Ns.i.a1.Rs1.*?(R1.i.Rs2.*?(R2.i)).query.get ==> List(
        (0, Nil),
        (1, List((10, Nil))),
        (2, List((10, List(20)))),
        (3, List((10, List(20)))),
        (4, List((10, List(20)))),
        (5, List((10, List(20)))),
        (6, List((10, List(20)))),
        (7, List((10, List(20))))
      )


      // 3 levels
      Ns.i.a1.Rs1.*(R1.i.Rs2.*(R2.i.Rs3.*(R3.i))).query.get ==> List(
        (3, List((10, List((20, List(30)))))),
        (4, List((10, List((20, List(30)))))),
        (5, List((10, List((20, List(30)))))),
        (6, List((10, List((20, List(30)))))),
        (7, List((10, List((20, List(30))))))
      )
      Ns.i.a1.Rs1.*?(R1.i.Rs2.*?(R2.i.Rs3.*?(R3.i))).query.get ==> List(
        (0, Nil),
        (1, List((10, Nil))),
        (2, List((10, List((20, Nil))))),
        (3, List((10, List((20, List(30)))))),
        (4, List((10, List((20, List(30)))))),
        (5, List((10, List((20, List(30)))))),
        (6, List((10, List((20, List(30)))))),
        (7, List((10, List((20, List(30))))))
      )


      // 4 levels
      Ns.i.a1.Rs1.*(R1.i.Rs2.*(R2.i.Rs3.*(R3.i.Rs4.*(R4.i)))).query.get ==> List(
        (4, List((10, List((20, List((30, List(40)))))))),
        (5, List((10, List((20, List((30, List(40)))))))),
        (6, List((10, List((20, List((30, List(40)))))))),
        (7, List((10, List((20, List((30, List(40))))))))
      )
      Ns.i.a1.Rs1.*?(R1.i.Rs2.*?(R2.i.Rs3.*?(R3.i.Rs4.*?(R4.i)))).query.get ==> List(
        (0, Nil),
        (1, List((10, Nil))),
        (2, List((10, List((20, Nil))))),
        (3, List((10, List((20, List((30, Nil))))))),
        (4, List((10, List((20, List((30, List(40)))))))),
        (5, List((10, List((20, List((30, List(40)))))))),
        (6, List((10, List((20, List((30, List(40)))))))),
        (7, List((10, List((20, List((30, List(40))))))))
      )


      // 5 levels
      Ns.i.a1.Rs1.*(R1.i.Rs2.*(R2.i.Rs3.*(R3.i.Rs4.*(R4.i.Rs5.*(R5.i))))).query.get ==> List(
        (5, List((10, List((20, List((30, List((40, List(50)))))))))),
        (6, List((10, List((20, List((30, List((40, List(50)))))))))),
        (7, List((10, List((20, List((30, List((40, List(50))))))))))
      )
      Ns.i.a1.Rs1.*?(R1.i.Rs2.*?(R2.i.Rs3.*?(R3.i.Rs4.*?(R4.i.Rs5.*?(R5.i))))).query.get ==> List(
        (0, Nil),
        (1, List((10, Nil))),
        (2, List((10, List((20, Nil))))),
        (3, List((10, List((20, List((30, Nil))))))),
        (4, List((10, List((20, List((30, List((40, Nil))))))))),
        (5, List((10, List((20, List((30, List((40, List(50)))))))))),
        (6, List((10, List((20, List((30, List((40, List(50)))))))))),
        (7, List((10, List((20, List((30, List((40, List(50))))))))))
      )


      // 6 levels
      Ns.i.a1.Rs1.*(R1.i.Rs2.*(R2.i.Rs3.*(R3.i.Rs4.*(R4.i.Rs5.*(R5.i.Rs6.*(R6.i)))))).query.get ==> List(
        (6, List((10, List((20, List((30, List((40, List((50, List(60)))))))))))),
        (7, List((10, List((20, List((30, List((40, List((50, List(60))))))))))))
      )
      Ns.i.a1.Rs1.*?(R1.i.Rs2.*?(R2.i.Rs3.*?(R3.i.Rs4.*?(R4.i.Rs5.*?(R5.i.Rs6.*?(R6.i)))))).query.get ==> List(
        (0, Nil),
        (1, List((10, Nil))),
        (2, List((10, List((20, Nil))))),
        (3, List((10, List((20, List((30, Nil))))))),
        (4, List((10, List((20, List((30, List((40, Nil))))))))),
        (5, List((10, List((20, List((30, List((40, List((50, Nil))))))))))),
        (6, List((10, List((20, List((30, List((40, List((50, List(60)))))))))))),
        (7, List((10, List((20, List((30, List((40, List((50, List(60))))))))))))
      )


      // 7 levels
      Ns.i.a1.Rs1.*(R1.i.Rs2.*(R2.i.Rs3.*(R3.i.Rs4.*(R4.i.Rs5.*(R5.i.Rs6.*(R6.i.Rs7.*(R7.i))))))).query.get ==> List(
        (7, List((10, List((20, List((30, List((40, List((50, List((60, List(70))))))))))))))
      )
      Ns.i.a1.Rs1.*?(R1.i.Rs2.*?(R2.i.Rs3.*?(R3.i.Rs4.*?(R4.i.Rs5.*?(R5.i.Rs6.*?(R6.i.Rs7.*?(R7.i))))))).query.get ==> List(
        (0, Nil),
        (1, List((10, Nil))),
        (2, List((10, List((20, Nil))))),
        (3, List((10, List((20, List((30, Nil))))))),
        (4, List((10, List((20, List((30, List((40, Nil))))))))),
        (5, List((10, List((20, List((30, List((40, List((50, Nil))))))))))),
        (6, List((10, List((20, List((30, List((40, List((50, List((60, Nil))))))))))))),
        (7, List((10, List((20, List((30, List((40, List((50, List((60, List(70))))))))))))))
      )
    }
  }
}
