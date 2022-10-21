package molecule.core.api

trait InsertOps {
  //  def prepare: String = ???
  def run(implicit conn: Connection): TxReport
}
