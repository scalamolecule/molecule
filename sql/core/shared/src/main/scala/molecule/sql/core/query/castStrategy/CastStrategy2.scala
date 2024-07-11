//package molecule.sql.core.query.castStrategy
//
//import molecule.sql.core.query.SqlQueryBase
//
//sealed trait CastStrategy extends SqlQueryBase  {
////  protected var strategies = List.empty[CastStrategy[Cast]]
////  private var casts = List.empty[Cast]
//
//  def add(cast: Cast): Unit
//
//  def nest: CastNested[Cast]
//  //= new CastNested[Cast](this)
////  def nestOptional: CastNested[Cast]
////  def nestOptRef: CastNested[Cast]
//}
//
//case class CastTuple(casts: List[Cast]) extends CastStrategy {
//
//  override def add(cast: Cast): Unit = {
//    casts = casts :+ cast
//  }
//  override def nest: CastNested[Cast] = {
//    val newTuple = CastTuple[Cast]()
//    CastNested()
//  }
//}
//
//case class CastNested[Cast](branches: List[CastStrategy[Cast]])
//  extends CastStrategy[Cast] {
////  protected var strategies = List.empty[CastStrategy[Cast]]
//
////  strategies = previous match {
////    case s: CastTuple[Cast] =>
////  }
//
////  private var casts = List.empty[Cast]
//
//  override def add(cast: Cast): Unit = {
////    casts = casts :+ cast
//    branches.last.add(cast)
//  }
//  override def nest: CastNested[Cast] = ???
//}
//
//
////case class CastTpl[Cast](resolved: List[CastStrategy[Cast]])
////extends CastStrategy[Cast] {
////  override def add(cast: Cast): Unit = {
////    casts = casts :+ cast
////  }
////
////  override def nest: CastNested[Cast] = {
////    new CastNested(this)
////  }
////}