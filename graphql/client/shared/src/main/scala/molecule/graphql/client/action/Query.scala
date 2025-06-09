package molecule.graphql.client.action

//package molecule.graphql.client.action
//
//import molecule.db.base.error.ModelError
//import molecule.db.core.action.{Query, QueryBind}
//import molecule.db.core.ast.DataModel
//import molecule.db.core.marshalling.dbView.DbView
//
//case class Query[Tpl](
//  dataModel: DataModel,
//  private[molecule] val optLimit: Option[Int] = None,
//  private[molecule] val dbView: Option[DbView] = None,
//  private[molecule] val printInspect: Boolean = false,
//  private[molecule] val bindValues: List[Any] = Nil
//) extends QueryBind[Tpl, Query]{
//
//
//
//
//  protected override def bind(inputs: List[Any]): Query[Tpl] = {
//    val found    = inputs.length
//    val expected = dataModel.binds
//    if found != expected then
//      throw ModelError(s"Expected $expected bind parameters but got $found.")
//
//    copy(bindValues = inputs)
//  }
//}
