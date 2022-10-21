package molecule.base.util.exceptions

case class MoleculeCompileException(message: String, cause: Throwable = null)
  extends RuntimeException(message, cause)
