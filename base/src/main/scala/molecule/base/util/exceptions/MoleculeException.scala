package molecule.base.util.exceptions

case class MoleculeException(message: String, cause: Throwable = null)
  extends RuntimeException(message, cause)
