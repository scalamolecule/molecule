package molecule.base.codegen.render

import molecule.base.ast.SchemaAST.{MetaNs, MetaSchema}


case class Dsl_Validations(schema: MetaSchema, namespace: MetaNs)
  extends DslFormatting(schema, namespace) {

  def validationMethod(
    attr: String,
    baseTpe: String,
    validations: Seq[(String, String)],
    valueAttrMetas: Seq[(String, String)]
  ): String = {
    val validator         = "Validate" + baseTpe
    val (pad, start, end) = if (valueAttrMetas.isEmpty) {
      ("", "", "")
    } else {
      val maxValueAttr    = valueAttrMetas.map(_._1.length).max
      val maxMetaAttr     = valueAttrMetas.map(_._2.length).max
      val values          = valueAttrMetas
        .zipWithIndex
        .map { case ((attr, metaAttr), i) =>
          val pad1 = padS(maxValueAttr, attr)
          val pad2 = padS(maxMetaAttr, metaAttr)
          s"\n      val $attr$pad1 = _attrs($i).asInstanceOf[$metaAttr$pad2].vs.head"
        }
        .mkString(
          // prefixing with underscore to avoid name clash with possible custom attribute name `attrs`
          s"\n    override def validateWith(_attrs: Seq[Attr]): $validator = new $validator(_attrs) {",
          "",
          ""
        )
      ("  ", values, "\n    }")
    }
    validations.size match {
      case 1 => singleValidation(attr, baseTpe, validations.head, pad, start, end)
      case _ => multipleValidations(attr, baseTpe, validations, pad, start, end)
    }
  }

  private def singleValidation(
    attr: String,
    baseTpe: String,
    validation: (String, String),
    pad: String,
    start: String,
    end: String
  ): String = {
    val (test, error) = validation
    val testStr       = if (test.contains("\n")) {
      val lines = test.split('\n').toList
      (lines.head +: lines.tail.map(s"$pad      " + _)).mkString("\n")
    } else {
      test
    }
    val error1        = renderError(error, pad)
    val errorStr      = {
      if (error.contains("\n")) {
        s"""Seq(
           |$pad        $error1
           |$pad      )""".stripMargin('#') // Allow pipe character inside multiline string
      } else if (error.isEmpty) {
        val indentedTest = test.split('\n').toList.mkString(s"|$pad           |  ", s"\n||$pad           |  ", "")
        s"""Seq(
           |$pad        s\"\"\"$ns.$attr with value `$$v` doesn't satisfy validation:
           |$pad         $indentedTest
           |$pad         |$pad           |\"\"\".stripMargin
           |$pad      )""".stripMargin('#')
      } else {
        s"Seq($error1)"
      }
    }
    s"""private lazy val validation_$attr = new Validate$baseTpe {$start
       |$pad    override def validate(v: $baseTpe): Seq[String] = {
       |$pad      val ok: $baseTpe => Boolean = $testStr
       |$pad      if (ok(v)) Nil else $errorStr
       |$pad    }$end
       |  }""".stripMargin('#')
  }


  private def multipleValidations(
    attr: String,
    tpe: String,
    validations: Seq[(String, String)],
    pad: String,
    start: String,
    end: String
  ): String = {
    val test2errorPairs = validations.map {
      case (test, error) =>
        val testStr = if (test.contains("\n")) {
          val lines = test.split('\n').toList
          (lines.head +: lines.tail.map(s"$pad          " + _)).mkString("\n")
        } else {
          test
        }
        val error1  = renderError(error, pad)
        if (error.contains("\n")) {
          s"""($testStr, \n$pad          $error1)"""
        } else {
          s"""($testStr, $error1)"""
        }
    }.mkString(s",\n$pad        ")

    s"""private lazy val validation_$attr = new Validate$tpe {$start
       |$pad    override def validate(v: $tpe): Seq[String] = {
       |$pad      Seq[($tpe => Boolean, String)](
       |$pad        $test2errorPairs
       |$pad      ).flatMap {
       |$pad        case (ok, error) => if (ok(v)) Nil else Seq(error)
       |$pad      }
       |$pad    }$end
       |  }""".stripMargin('#') // Allow pipe character inside multiline string
  }


  private def renderError(error: String, pad: String): String = {
    if (error.contains('\n')) {
      val indented = error.split('\n').toList.mkString(s"\n||$pad   ")
      s"""s\"\"\"$indented\"\"\".stripMargin"""
    } else {
      s"""s\"\"\"$error\"\"\""""
    }
  }
}
