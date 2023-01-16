package molecule.base.util.exceptions

case class MoleculeError(message: String, cause: Throwable = null)
  extends RuntimeException(message, cause) {

  override def toString: String = {
    val stackTrace = if(cause == null) "" else cause.getStackTrace.toList.mkString("\n", "\n", "")
    message + stackTrace
  }
}
