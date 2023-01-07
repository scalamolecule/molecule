package molecule.db.datomic.test


import molecule.core.transaction.Save
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import scribe.output.LogOutput
import scribe.output.format.OutputFormat
import scribe.writer.Writer
import scribe.{LogRecord, Logger, Logging}
import utest._
import scala.collection.mutable.ListBuffer


object Adhoc extends DatomicTestSuite with Logging {


  lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        _ <- Ns.int.insert(1, 2, 3).transact
        _ <- Ns.int(1, 2).query.get.map(_ ==> List(1, 2))
        _ <- Ns.int.query.get.map(_ ==> List(1, 2, 3))
      } yield ()
    }


    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Refs._
    //
    //
    //      //      Ns.i.v.Self.i(v).s
    //
    //      //      Ns.i.>(1).as(v1).R1.i.<(v1)
    //
    //    }
  }

}
