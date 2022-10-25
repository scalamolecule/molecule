package molecule.core.api

trait SaveOps {
  //  def prepare: String = ???
  def run(implicit conn: Connection): TxReport
}
