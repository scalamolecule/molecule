//package molecule.graphql.client.spi
//
//import boopickle.Default.*
//import molecule.core.dataModel.*
//import molecule.graphql.client
//import scala.concurrent.{Future, ExecutionContext as EC}
//
//
//object SpiAsync_graphql extends SpiAsync_graphql
//
//trait SpiAsync_graphql extends SpiAsyncBase {
//
//  override protected def renderInspectQuery(label: String, dataModel: DataModel)
//                                           (implicit ec: EC): Future[String] = Future {
////    val query = new Model2SqlQuery_postgres(elements).getSqlQuery(Nil, None, None, None)
////    printRaw(label, Nil, query)
//    ???
//  }
//}
