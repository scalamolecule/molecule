package molecule.base.codegen.render

import molecule.base.ast.SchemaAST._


case class Dsl_Validations(schema: MetaSchema, namespace: MetaNs)
  extends DslFormatting(schema, namespace) {

  def validationMethod(
    attr: String,
    baseTpe: String,
    validations: Seq[(String, String)],
    valueAttrs: Seq[(String, String, String, String)]
  ): String = {
    val static = valueAttrs.isEmpty
    val single = validations.length == 1
    val pad    = if (static) "" else "  "
    val body   = if (single) {
      getBodySingle(pad, attr, baseTpe, validations.head)
    } else {
      getBodyMulti(pad, baseTpe, validations)
    }
    if (static) {
      getStatic(pad, body, attr, baseTpe)
    } else {
      getDynamic(body, attr, baseTpe, valueAttrs)
    }
  }


  private def getStatic(
    pad: String,
    body: String,
    attr: String,
    baseTpe: String,
  ): String = {
    s"""private lazy val validation_$attr = new Validate$baseTpe {
       |$pad    override def validate(v: $baseTpe): Seq[String] = {
       |$pad      $body
       |$pad    }
       |$pad  }"""
  }


  private def getDynamic(
    body: String,
    attr: String,
    baseTpe: String,
    valueAttrs: Seq[(String, String, String, String)]
  ): String = {
    val validator      = "Validate" + baseTpe
    val maxAttr        = valueAttrs.map(_._1.length).max.max(9) // align with _validate too
    val maxMetaAttr    = valueAttrs.map(_._3.length).max
    val maxValue       = valueAttrs.map(_._4.length).max
    val variableParams = valueAttrs.map { case (attr, tpe, _, _) => s"$attr: $tpe" }.mkString(", ")
    val variables      = valueAttrs.map(_._1).mkString(", ")

    val (variablesMeta, variablesValue) = valueAttrs
      .zipWithIndex
      .map { case ((attr, _, metaAttr, value), i) =>
        val pad1 = padS(maxAttr, attr)
        val pad2 = padS(maxMetaAttr, metaAttr)
        val pad3 = padS(maxValue, value)
        (
          s"val $attr$pad1 = _attrs($i).asInstanceOf[$metaAttr$pad2].vs.head",
          s"val $attr$pad1 = _values($i).asInstanceOf[$value$pad3].v"
        )
      }.unzip

    val variablesFromMetaAttr = variablesMeta.mkString("\n      ")
    val variablesFromValue    = variablesValue.mkString("\n      ")

    s"""
       |  private lazy val validation_$attr = new $validator {
       |    val _withVariables = {
       |      ($variableParams) =>
       |        (v: $baseTpe) => {
       |          $body
       |        }
       |    }
       |    override def withAttrs(attrs: Seq[Attr]): $validator = new $validator(attrs) {
       |      $variablesFromMetaAttr
       |      val _validate = _withVariables($variables)
       |      override def validate(v: $baseTpe): Seq[String] = _validate(v)
       |    }
       |    override def withValues(values: Seq[Value]): $validator = new $validator(_values = values) {
       |      $variablesFromValue
       |      val _validate = _withVariables($variables)
       |      override def validate(v: $baseTpe): Seq[String] = _validate(v)
       |    }
       |  }"""
  }


  private def getBodySingle(
    pad: String,
    attr: String,
    baseTpe: String,
    validation: (String, String),
  ): String = {
    val (test, error) = validation
    val testStr       = if (test.contains("\n")) {
      val lines = test.split('\n').toList
      (lines.head +: lines.tail.map(s"$pad        " + _)).mkString("\n")
    } else {
      test
    }
    val error1        = renderError(error, pad)
    val errorStr      = {
      if (error.contains("\n")) {
        s"""Seq(
           |$pad          $error1
           |$pad        )"""
      } else if (error.isEmpty) {
        val indentedTest = test.split('\n').toList.mkString(s"|$pad             |  ", s"\n||$pad             |  ", "")
        s"""Seq(
           |$pad          s\"\"\"$ns.$attr with value `$$v` doesn't satisfy validation:
           |$pad           $indentedTest
           |$pad|$pad             |\"\"\".stripMargin
           |$pad        )""".stripMargin('#')
      } else {
        s"Seq($error1)"
      }
    }

    s"""val ok: $baseTpe => Boolean = $testStr
       |$pad        if (ok(v)) Nil else $errorStr"""
  }


  private def getBodyMulti(
    pad: String,
    baseTpe: String,
    validations: Seq[(String, String)],
  ): String = {
    val test2errorPairs = validations.map {
      case (test, error) =>
        val testStr = if (test.contains("\n")) {
          val lines = test.split('\n').toList
          (lines.head +: lines.tail.map(s"$pad            " + _)).mkString("\n")
        } else {
          test
        }
        val error1  = renderError(error, pad)
        if (error.contains("\n")) {
          s"""($testStr, \n$pad            $error1)"""
        } else {
          s"""($testStr, $error1)"""
        }
    }.mkString(s",\n$pad          ")

    s"""Seq[($baseTpe => Boolean, String)](
       |$pad          $test2errorPairs
       |$pad        ).flatMap {
       |$pad          case (ok, error) => if (ok(v)) Nil else Seq(error)
       |$pad        }"""
  }

  private def renderError(error: String, pad: String): String = {
    if (error.contains('\n')) {
      val indented = error.split('\n').toList.mkString(s"\n||$pad     ")
      s"""s\"\"\"$indented\"\"\".stripMargin"""
    } else {
      s"""s\"\"\"$error\"\"\""""
    }
  }
}
