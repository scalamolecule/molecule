package molecule.datalog.datomic.test

import java.util.Date
import datomic.Peer
import molecule.base.ast.SchemaAST.pad
import molecule.datalog.datomic.sync._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions


object AdhocDatomicJVMsync extends DatomicTestSuite {


  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._


      Ns.s.double.insert(
        ("a", 1.5),
        ("a", 2.0),
        ("b", 7.5),
      ).transact
      Ns.s.double(sum).query.get.foreach(println)

    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      A.s.Bb.*(B.i.a1).query.inspect
    }


  }
}
