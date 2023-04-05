package molecule.datomic.test

import java.util.Date
import molecule.base.ast.SchemaAST._
import molecule.base.error._
import molecule.core.api.TxReport
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.test.validation.ValidateVariables.validation
import utest._
import scala.concurrent.Future
import scala.language.implicitConversions
import molecule.boilerplate.ast.Model._

object Adhoc extends DatomicTestSuite {

  private lazy val validation_noErrorMsg        = new ValidateInt {
    override def validateWith(valueAttrs: Seq[Attr]): ValidateInt = new ValidateInt(valueAttrs) {
      val int = valueAttrs(0).asInstanceOf[AttrOneManInt].vs.head
      override def validate(v: Int): Seq[String] = {
        val ok: Int => Boolean = _ > int
        if (ok(v)) Nil else Seq(
          s"""Format2.noErrorMsg with value `$v` doesn't satisfy validation:
             |  _ > int
             |""".stripMargin
        )
      }
    }
  }
  private lazy val validation_errorMsg          = new ValidateInt {
    override def validateWith(valueAttrs: Seq[Attr]): ValidateInt = new ValidateInt(valueAttrs) {
      val int1 = valueAttrs(0).asInstanceOf[AttrOneManInt].vs.head
      override def validate(v: Int): Seq[String] = {
        val ok: Int => Boolean = _ > int1
        if (ok(v)) Nil else Seq(s"""One-line error msg""")
      }
    }
  }
  private lazy val validation_errorMsgWithValue = new ValidateInt {
    override def validateWith(valueAttrs: Seq[Attr]): ValidateInt = new ValidateInt(valueAttrs) {
      val int2 = valueAttrs(0).asInstanceOf[AttrOneManInt].vs.head
      override def validate(v: Int): Seq[String] = {
        val ok: Int => Boolean = _ > int2
        if (ok(v)) Nil else Seq(s"""One-line error msg. Found $v""")
      }
    }
  }
  private lazy val validation_multilineMsg      = new ValidateInt {
    override def validateWith(valueAttrs: Seq[Attr]): ValidateInt = new ValidateInt(valueAttrs) {
      val int3 = valueAttrs(0).asInstanceOf[AttrOneManInt].vs.head
      override def validate(v: Int): Seq[String] = {
        val ok: Int => Boolean = (v: Int) => v.>(int3)
        if (ok(v)) Nil else Seq(
          s"""Validation failed:
             |Input value `$v` is not bigger than `int3` value `$int3`.""".stripMargin
        )
      }
    }
  }
  private lazy val validation_multiLine         = new ValidateInt {
    override def validateWith(valueAttrs: Seq[Attr]): ValidateInt = new ValidateInt(valueAttrs) {
      val int4 = valueAttrs(0).asInstanceOf[AttrOneManInt].vs.head
      override def validate(v: Int): Seq[String] = {
        val ok: Int => Boolean = { v =>
          val data   = 22
          val result = data % int4
          v > result
        }
        if (ok(v)) Nil else Seq(
          s"""Format2.multiLine with value `$v` doesn't satisfy validation:
             |  { v =>
             |    val data   = 22
             |    val result = data % int4
             |    v > result
             |  }
             |""".stripMargin
        )
      }
    }
  }
  private lazy val validation_multiLine2        = new ValidateInt {
    override def validateWith(valueAttrs: Seq[Attr]): ValidateInt = new ValidateInt(valueAttrs) {
      val int5 = valueAttrs(0).asInstanceOf[AttrOneManInt].vs.head
      override def validate(v: Int): Seq[String] = {
        val ok: Int => Boolean = { v =>
          val data   = 22
          val result = {
            data % int5
          }
          v > result
        }
        if (ok(v)) Nil else Seq(s"""One-line error msg""")
      }
    }
  }
  private lazy val validation_multiLine3        = new ValidateInt {
    override def validateWith(valueAttrs: Seq[Attr]): ValidateInt = new ValidateInt(valueAttrs) {
      val int6 = valueAttrs(0).asInstanceOf[AttrOneManInt].vs.head
      override def validate(v: Int): Seq[String] = {
        val ok: Int => Boolean = { v =>
          val data   = 22
          val result = data % int6
          v > result
        }
        if (ok(v)) Nil else Seq(
          s"""Long error explanation
             |with multiple lines""".stripMargin
        )
      }
    }
  }
  private lazy val validation_logic             = new ValidateInt {
    override def validateWith(valueAttrs: Seq[Attr]): ValidateInt = new ValidateInt(valueAttrs) {
      val int7 = valueAttrs(0).asInstanceOf[AttrOneManInt].vs.head
      override def validate(v: Int): Seq[String] = {
        val ok: Int => Boolean = v => v >= 3 && v <= 9 && v != int7 && v % 2 == 1
        if (ok(v)) Nil else Seq(s"""Value must be an odd number between 3 and 9 but not `int7` value `$int7`""")
      }
    }
  }
  private lazy val validation_multipleErrors    = new ValidateInt {
    override def validateWith(valueAttrs: Seq[Attr]): ValidateInt = new ValidateInt(valueAttrs) {
      val int8 = valueAttrs(0).asInstanceOf[AttrOneManInt].vs.head
      val str  = valueAttrs(1).asInstanceOf[AttrOneManString].vs.head
      val ints = valueAttrs(2).asInstanceOf[AttrSetManInt].vs.head
      val strs = valueAttrs(3).asInstanceOf[AttrSetManString].vs.head
      override def validate(v: Int): Seq[String] = {
        Seq[(Int => Boolean, String)](
          (v => v > int8, s"""Number must be bigger than `int8` value `$int8`. Found: $v"""),
          (v => v < str.length, s"""Number must be smaller than `str` value `$str` length `${str.length}`. Found: $v"""),
          (v => {
            val count = (3 to ints.head).length
            v != count
          }, s"""Number must not be count of 3 to `ints` head value `${ints.head}`. Found: $v"""),
          (v => {
            val divider = strs.size
            v % divider == 1
          },
            s"""Number must
               |be odd. Found: $v""".stripMargin)
        ).flatMap {
          case (ok, error) => if (ok(v)) Nil else Seq(error)
        }
      }
    }
  }


  override lazy val tests = Tests {

    //    "types" - types { implicit conn =>
    //      for {
    //        _ <- Ns.int.apply(3).save.transact
    //        _ <- Ns.int.query.get.map(_ ==> List(3))
    //
    //      } yield ()
    //    }



//    "Compare with other attr value" - validation { implicit conn =>
//      for {
//        _ <- AttrValue.low(5).high(5).save.transact
//          .map(_ ==> "Unexpected success").recover {
//          case ValidationErrors(errorMap, _) =>
//            errorMap ==>
//              Map(
//                "AttrValue.low" -> Seq(
//                  s"""AttrValue.low with value `5` doesn't satisfy validation:
//                     |  _ < high.value
//                     |""".stripMargin
//                )
//              )
//        }
//      } yield ()
//    }


    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Refs._
    //      for {
    //        _ <- Ns.i.Tx(R2.i).insert(1, 2).transact
    //          .map(_ ==> "Unexpected success").recover { case ExecutionError(err, _) =>
    //          err ==>
    //            """Missing applied value for attribute:
    //              |AttrOneManInt("R2", "i", V, Seq(), None, Nil, Nil, None, None)""".stripMargin
    //        }
    //      } yield ()
    //    }
  }

}
