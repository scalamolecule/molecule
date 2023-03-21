package molecule.base.codegen.render

import molecule.base.ast.SchemaAST.{MetaNs, MetaSchema}


case class Dsl_Validations(schema: MetaSchema, namespace: MetaNs)
  extends DslFormatting(schema, namespace) {

  def validationMethod(attr: String, tpe: String, validations: Seq[(String, String)]): String = {
    validations.size match {
      case 1 => singleValidation(attr, tpe, validations.head)
      case _ => multipleValidations(attr, tpe, validations)
    }
  }

  private def singleValidation(attr: String, tpe: String, validation: (String, String)): String = {
    val (test, error) = validation
    val testStr       = if (test.contains("\n")) {
      val lines = test.split('\n').toList
      (lines.head +: lines.tail.map("      " + _)).mkString("\n")
    } else {
      test
    }
    val errorStr      = {
      if (error.contains("\n")) {
        s"""Seq(
           |        ${renderError(error)}
           |      )""".stripMargin('#') // Allow pipe character inside multiline string
      } else if (error.isEmpty) {
        val indentedTest = test.split('\n').toList.mkString("|           |  ", "\n||           |  ", "")
        s"""Seq(
           |        s\"\"\"$ns.$attr with value `$$v` doesn't satisfy validation:
           |        $indentedTest
           |        |           |\"\"\".stripMargin
           |      )""".stripMargin('#')
      } else {
        s"Seq(${renderError(error)})"
      }
    }
    s"""private lazy val validation_$attr = new Validate$tpe {
       |    override def validate(v: $tpe): Seq[String] = {
       |      val ok: $tpe => Boolean = $testStr
       |      if (ok(v)) Nil else $errorStr
       |    }
       |  }""".stripMargin('#')
  }


  private def multipleValidations(attr: String, tpe: String, validations: Seq[(String, String)]): String = {
    val test2errorPairs = validations.map {
      case (test, error) =>
        val testStr = if (test.contains("\n")) {
          val lines = test.split('\n').toList
          (lines.head +: lines.tail.map("          " + _)).mkString("\n")
        } else {
          test
        }
        if (error.contains("\n")) {
          s"""($testStr, \n          ${renderError(error)})"""
        } else {
          s"""($testStr, "$error")"""
        }
    }.mkString(",\n        ")

    s"""private lazy val validation_$attr = new Validate$tpe {
       |    override def validate(v: $tpe): Seq[String] = {
       |      Seq[($tpe => Boolean, String)](
       |        $test2errorPairs
       |      ).flatMap {
       |        case (ok, error) => if (ok(v)) Nil else Seq(error)
       |      }
       |    }
       |  }""".stripMargin('#') // Allow pipe character inside multiline string
  }


  private def renderError(error: String): String = {
    if (error.contains('\n')) {
      val indented = error.split('\n').toList.mkString("\n||  ")
      s"""\"\"\"$indented\"\"\".stripMargin"""
    } else {
      s"""\"$error\""""
    }
  }
}
