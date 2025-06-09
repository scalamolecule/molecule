package molecule.base.error

sealed trait MoleculeError extends Throwable with Product with Serializable {
  def msg: String
  override def getMessage: String = msg
}


case class ModelError(message: String) extends MoleculeError {
  override def msg: String = message
  override def toString: String = message
}


case class ValidationErrors(errorMap: Map[String, Seq[String]]) extends MoleculeError {
  override def msg: String = toString

  override def toString: String = {
    val errorList = errorMap.map { case (attr, errs) =>
      val errs1   = errs.map(error =>
        if (error.contains('\n')) {
          val list     = error.split('\n').toList
          val indented = list.mkString("", "\n|        |", "")
          s"""\"\"\"$indented\"\"\".stripMargin"""
        } else if (error.contains('"')) {
          s"""\"\"\"$error\"\"\""""
        } else {
          s"""\"$error\""""
        }
      )
      val errsStr = errs1.mkString(",\n      ")
      s""""$attr" -> Seq(
         |      $errsStr
         |    )"""
    }.mkString(",\n    ")
    s"""ValidationErrors(
       |  Map(
       |    $errorList
       |  )
       |)""".stripMargin
  }
}


case class InsertErrors(
  errors: Seq[(Int, Seq[InsertError])],
  message: Option[String] = None
) extends MoleculeError {
  override def msg: String = "InsertErrors with optional message: " + message

  // Recurse potentially nested insert errors
  private def errorsStr(indent: Int = 0, extraPipe: Boolean = false) = {
    val s   = "  " * indent
    val str = if (errors.isEmpty) "" else {
      val lastBreak = if (extraPipe) "\n  " else "\n"
      errors.map {
        case (rowIndex, errors) =>
          val errorsStr       = errors.map { error =>
            error.render(indent + 3, true)
          }.mkString(s",\n$s      ")
          val rowIndexComment = if (rowIndex == -1)
            "Appended tx meta data errors row "
          else
            "Top-level row index"
          val str             =
            s"""$s  (
               |$s    $rowIndex, // $rowIndexComment
               |$s    Seq(
               |$s      $errorsStr
               |$s    )
               |$s  )"""
          if (extraPipe) str else str.stripMargin
      }.mkString("\n", ",\n", lastBreak)
    }
    s"""Seq($str)"""
  }

  override def toString: String = {
    s"""InsertErrors(
       |  ${errorsStr(1, true)},
       |  $message
       |)""".stripMargin
  }
}


case class ExecutionError(message: String) extends MoleculeError {
  override def msg: String = message
//  override def toString: String = message
}