package molecule.base.error

/** Insert error - validation errors for an insert row of data
 *
 * @param tplIndex     Index of attribute value in data tuple
 * @param fullAttr     Full attribute name (Entity.attr)
 * @param errors       Error messages
 * @param nestedErrors Nested errors
 */
case class InsertError(
  tplIndex: Int,
  fullAttr: String,
  errors: Seq[String] = Nil,
  nestedErrors: Seq[(Int, Seq[InsertError])] = Nil
) {
  override def toString: String = render(0)

  def render(indent: Int, extraPipe: Boolean = false): String = {
    val s         = "  " * indent
    val errorsStr = if (errors.isEmpty) "" else {
      errors.map { error =>
        if (error.contains('\n')) {
          val list     = error.split('\n').toList
          val indented = if (extraPipe) {
            list.mkString("", s"\n||$s       |", "")
          } else {
            list.mkString("", s"\n|$s       |", "")
          }
          s"""s\"\"\"$indented\"\"\".stripMargin"""
        } else {
          s"""\"\"\"$error\"\"\""""
        }
      }.mkString(
        s"\n$s    ",
        s",\n$s    ",
        s"\n$s  "
      )
    }

    val nestedErrorsStr = if (nestedErrors.isEmpty) "" else {
      nestedErrors.map {
        case (rowIndex, errors) =>
          val nestedErrors = errors
            .map(_.render(indent + 4))
            .mkString(s",\n$s        ")
          s"""$s    (
             |$s      $rowIndex, // nested row index
             |$s      Seq(
             |$s        $nestedErrors
             |$s      )
             |$s    )"""
      }.mkString(
        s"\n",
        s",\n",
        s"\n$s  "
      )
    }

    s"""InsertError(
       |$s  $tplIndex, // tuple index
       |$s  "$fullAttr",
       |$s  Seq($errorsStr),
       |$s  Seq($nestedErrorsStr)
       |$s)""".stripMargin
  }
}
