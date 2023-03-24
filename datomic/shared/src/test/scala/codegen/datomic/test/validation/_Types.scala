//package codegen.datomic.test.validation
//
//import java.nio.file.{Files, Paths}
//import codegen.DatomicTestGenBase
//import molecule.base.util.{BaseHelpers, CodeGenBase}
//
//object _Types extends DatomicTestGenBase("Types", "/test/validation") with BaseHelpers {
//
//  val content = {
//    s"""package molecule.datomic.test.validation
//       |
//       |import molecule.base.util.exceptions._
//       |import molecule.core.util.Executor._
//       |import molecule.coreTests.dataModels.core.dsl.Validation._
//       |import molecule.datomic.async._
//       |import molecule.datomic.setup.DatomicTestSuite
//       |import utest._
//       |import scala.language.implicitConversions
//       |
//       |object Types_ extends DatomicTestSuite {
//       |
//       |  lazy val tests = Tests {
//       |
//       |    $cardOne
//       |  }
//       |}
//       |""".stripMargin
//  }
//
//  private def cardOne: String = {
//    tpeVarImp.map { case (name, tpe, v, imp) =>
//      s""""Card one, $name" - validation { implicit conn =>
//         |      for {
//         |        _ <- Type.$v(${v}1).save.transact.expect {
//         |          case ValidationErrors(errors, _) =>
//         |            errors.head ==>
//         |              "Type.$v" -> Seq(
//         |                s\"\"\"Type.$v with value `$$${v}1` doesn't satisfy validation:
//         |                   |  _ > $$${v}1
//         |                   |\"\"\".stripMargin
//         |              )
//         |        }
//         |      } yield ()
//         |    }""".stripMargin('#')
//    }.mkString("\n\n    ")
//  }
//}
