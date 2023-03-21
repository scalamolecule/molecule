package molecule.base.util.exceptions

//class MoleculeError(message: String, cause: Throwable = null)
//  extends RuntimeException(message, cause)
sealed trait MoleculeError extends Throwable with Product with Serializable {

  def msg: String

  override def getMessage: String = msg
}

case class ValidationErrors(
  errors: Map[String, Seq[String]],
  message: Option[String] = None
) extends MoleculeError {

  def msg = errors.mkString(
    "Validation errors:\n  ",
    "\n  ------------\n  ",
    ""
  )
}

case class ExecutionError(
  message: String,
  cause: Throwable = null
) extends MoleculeError {

  override def msg: String = toString

  override def toString: String = {
    val stackTrace = if (cause == null) "" else cause.getStackTrace.toList.mkString("\n", "\n", "")
    message + stackTrace
  }
}