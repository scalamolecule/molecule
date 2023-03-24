package molecule.datomic.test

import java.util.Date
import molecule.base.ast.SchemaAST.{CardOne, MetaAttr}
import molecule.base.util.exceptions._
import molecule.core.api.TxReport
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._
import scala.concurrent.Future
import scala.language.implicitConversions

object Adhoc extends DatomicTestSuite {


  lazy val tests = Tests {

    //    "types" - types { implicit conn =>
    //      for {
    //        _ <- Ns.int.apply(3).save.transact
    //        _ <- Ns.int.query.get.map(_ ==> List(3))
    //
    //      } yield ()
    //    }


    "validation" - validation { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Validation._
      for {
        _ <- Format.errorMsg.apply(1).save.transact.expect {
          case ValidationErrors(errors, _) =>
            errors.head ==>
              "Format.errorMsg" -> Seq("One-line error msg")
        }

        // 1 has correctly not been saved
        _ <- Format.errorMsg.query.get.map(_ ==> Nil)

        _ = {
          println(str2date("2001-07-01 00:00:00").getTime)
        }

      } yield ()
    }

    /*

    val validation_int = new ValidateInt {
      def validate(v: Int): Seq[String] = {
        val ok = {
          v > 2
        }
        if (ok) Nil else List(s"Ns.int with value `$v` doesn't satisfy validation: _ > 2")
      }
    }
    val validation_int2 = new ValidateInt {
      def validate(v: Int): Seq[String] = {
        val checks: Seq[(Int => Boolean, String)] = Seq(
          (v => v <= 2, "Number must be bigger than 2"),
          (v => v >= 10, "Number must be smaller than 10"),
          (v => v % 2 == 0, "Number must be odd")
        )
        checks.flatMap {
          case (test, error) => if (test(v)) Nil else Seq(error)
        }
      }
    }

    protected lazy val i_man          : AttrOneManInt        = AttrOneManInt       ("Ns", "i"          )
    protected lazy val s_man          : AttrOneManString     = AttrOneManString    ("Ns", "s"          )
    protected lazy val u_man          : AttrOneManInt        = AttrOneManInt       ("Ns", "u"          )
    protected lazy val string_man     : AttrOneManString     = AttrOneManString    ("Ns", "string"     )
    protected lazy val int_man        : AttrOneManInt        = AttrOneManInt       ("Ns", "int"        , validation = Some(validation_int))
     */

    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Refs._
    //      for {
    //        _ <- Ns.i.Tx(R2.i).insert(1, 2).transact
    //          .map(_ ==> "Unexpected success").recover { case ExecutionError(err, _) =>
    //          err ==>
    //            """Missing applied value for attribute:
    //              |AttrOneManInt("R2", "i", V, Seq(), None, Nil, None, None)""".stripMargin
    //        }
    //      } yield ()
    //    }
  }

}
