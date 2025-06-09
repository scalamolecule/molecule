package molecule.graphql


package object client {
//  object async extends Api_async with SpiAsync_graphql
//  object sync extends MoleculeImplicits_ with ApiSync with SpiSync_graphql
//  object Zio extends MoleculeImplicits_ with ApiZio with SpiZio_graphql


  // With capital Z to avoid collision with zio namespace from ZIO
  //    object Zio extends Api_zio with Api_zio_transact with Spi_h2_zio
//  object Ziox extends Api_zio with Spi_zio {
//
//    override def query_get[Tpl](q: Query[Tpl]): ZIO[Conn, MoleculeError, List[Tpl]] = ???
//    override def query_inspect[Tpl](q: Query[Tpl]): ZIO[Conn, MoleculeError, String] = ???
//    override def queryOffset_get[Tpl](q: QueryOffset[Tpl]): ZIO[Conn, MoleculeError, (List[Tpl], Int, Boolean)] = ???
//    override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl]): ZIO[Conn, MoleculeError, String] = ???
//    override def queryCursor_get[Tpl](q: QueryCursor[Tpl]): ZIO[Conn, MoleculeError, (List[Tpl], String, Boolean)] = ???
//    override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl]): ZIO[Conn, MoleculeError, String] = ???
//    override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit): ZIO[Conn, MoleculeError, Unit] = ???
//    override def query_unsubscribe[Tpl](q: Query[Tpl]): ZIO[Conn, MoleculeError, Unit] = ???
//    override def save_transact(save: Save): ZIO[Conn, MoleculeError, TxReport] = ???
//    override def save_inspect(save: Save): ZIO[Conn, MoleculeError, String] = ???
//    override def save_validate(save: Save): ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = ???
//    override def insert_transact(insert: Insert): ZIO[Conn, MoleculeError, TxReport] = ???
//    override def insert_inspect(insert: Insert): ZIO[Conn, MoleculeError, String] = ???
//    override def insert_validate(insert: Insert): ZIO[Conn, MoleculeError, Seq[(Int, Seq[InsertError])]] = ???
//    override def update_transact(update: Update): ZIO[Conn, MoleculeError, TxReport] = ???
//    override def update_inspect(update: Update): ZIO[Conn, MoleculeError, String] = ???
//    override def update_validate(update: Update): ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = ???
//    override def delete_transact(delete: Delete): ZIO[Conn, MoleculeError, TxReport] = ???
//    override def delete_inspect(delete: Delete): ZIO[Conn, MoleculeError, String] = ???
//  }
}
