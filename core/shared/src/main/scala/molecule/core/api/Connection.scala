package molecule.core.api

trait Connection {
  type Data
  def transact(data: Data): TxReport

  def db: Any = ???
}
