// AUTO-GENERATED Molecule DSL boilerplate code for entity `Other`
//package molecule.db.compliance.domains.dsl.Types
package mydomain
package ops // to access enums and let them be public to the user

import molecule.base.metaModel.CardOne
import molecule.core.dataModel.*
import molecule.db.core.api.*
import molecule.db.core.api.expression.*
import scala.Tuple.:*

object Other extends ops.Other_0(DataModel(Nil, firstEntityIndex = 0)) {
  //  final def apply(id: Long, ids: Long*) = new ops.Other_0(
  //    DataModel(List(AttrOneTacID("Other", "id", _Eq, id +: ids, coord = List(2, 174))), firstEntityIndex = 2)
  //  )
  //  final def apply(ids: Iterable[Long]) = new ops.Other_0(
  //    _DataModel(List(_AttrOneTacID("Other", "id", _Eq, ids.toSeq, coord = List(2, 174))), firstEntityIndex = 2)
  //  )
}



// Attributes  ============================================================================================================

class Other_0(override val dataModel: DataModel) extends Other_attrs with Molecule_0 {
  lazy val id     = Other_ExprOneMan_1[Long](dataModel.add(id_man))
  lazy val i      = Other_ExprOneMan_1[Int](dataModel.add(i_man))
  lazy val iSeq   = Other_ExprSeqMan_1[Int](dataModel.add(i_man))
  lazy val iSet   = Other_ExprSetMan_1[Int](dataModel.add(i_man))
  lazy val iMap   = Other_ExprMapMan_1[Int](dataModel.add(i_man))
  lazy val i_?    = Other_ExprOneOpt_1[Int](dataModel.add(i_opt))
  lazy val iSeq_? = Other_ExprSeqOpt_1[Int](dataModel.add(i_opt))
  lazy val iSet_? = Other_ExprSetOpt_1[Int](dataModel.add(i_opt))
  lazy val iMap_? = Other_ExprMapOpt_1[Int](dataModel.add(i_opt))
  lazy val i_     = Other_ExprOneTac_0[Int](dataModel.add(i_tac))
  lazy val iSeq_  = Other_ExprSeqTac_0[Int](dataModel.add(i_tac))
  lazy val iSet_  = Other_ExprSetTac_0[Int](dataModel.add(i_tac))
  lazy val iMap_  = Other_ExprMapTac_0[Int](dataModel.add(i_tac))
}


class Other_1[T](override val dataModel: DataModel) extends Other_attrs with Molecule_1[T] {
  lazy val i      = Other_ExprOneMan_n[Int, (T, Int)](dataModel.add(i_man))
  lazy val iSeq   = Other_ExprSeqMan_n[Int, (T, Seq[Int])](dataModel.add(i_man))
  lazy val iSet   = Other_ExprSetMan_n[Int, (T, Set[Int])](dataModel.add(i_man))
  lazy val iMap   = Other_ExprMapMan_n[Int, (T, Map[String, Int])](dataModel.add(i_man))
  lazy val i_?    = Other_ExprOneOpt_n[Int, (T, Option[Int])](dataModel.add(i_opt))
  lazy val iSeq_? = Other_ExprSeqOpt_n[Int, (T, Option[Seq[Int]])](dataModel.add(i_opt))
  lazy val iSet_? = Other_ExprSetOpt_n[Int, (T, Option[Set[Int]])](dataModel.add(i_opt))
  lazy val iMap_? = Other_ExprMapOpt_n[Int, (T, Option[Map[String, Int]])](dataModel.add(i_opt))
  lazy val i_     = Other_ExprOneTac_1[Int](dataModel.add(i_tac))
  lazy val iSeq_  = Other_ExprSeqTac_1[Int](dataModel.add(i_tac))
  lazy val iSet_  = Other_ExprSetTac_1[Int](dataModel.add(i_tac))
  lazy val iMap_  = Other_ExprMapTac_1[Int](dataModel.add(i_tac))
}


class Other_n[Tpl <: Tuple](override val dataModel: DataModel) extends Other_attrs with Molecule_n[Tpl] {
  lazy val i      = Other_ExprOneMan_n[Int, Tpl :* Int](dataModel.add(i_man))
  lazy val iSeq   = Other_ExprSeqMan_n[Int, Tpl :* Seq[Int]](dataModel.add(i_man))
  lazy val iSet   = Other_ExprSetMan_n[Int, Tpl :* Set[Int]](dataModel.add(i_man))
  lazy val iMap   = Other_ExprMapMan_n[Int, Tpl :* Int](dataModel.add(i_man))
  lazy val i_?    = Other_ExprOneOpt_n[Int, Tpl :* Option[Int]](dataModel.add(i_opt))
  lazy val iSeq_? = Other_ExprSeqOpt_n[Int, Tpl :* Option[Seq[Int]]](dataModel.add(i_opt))
  lazy val iSet_? = Other_ExprSetOpt_n[Int, Tpl :* Option[Set[Int]]](dataModel.add(i_opt))
  lazy val iMap_? = Other_ExprMapOpt_n[Int, Tpl :* Option[Map[String, Int]]](dataModel.add(i_opt))
  lazy val i_     = Other_ExprOneTac_n[Int, Tpl](dataModel.add(i_tac))
  lazy val iSeq_  = Other_ExprSeqTac_n[Int, Tpl](dataModel.add(i_tac))
  lazy val iSet_  = Other_ExprSetTac_n[Int, Tpl](dataModel.add(i_tac))
  lazy val iMap_  = Other_ExprMapTac_n[Int, Tpl](dataModel.add(i_tac))
}


// One -------------------------------

class Other_ExprOneMan_1[T](override val dataModel: DataModel)
  extends Other_1[T](dataModel)
    with ExprOneMan_1[T, [t] =>> Other_1[t] & Sort_1[t] & CardOne]
    with Sort_1[T] {
  override val entity = [t] => (dm: DataModel) => new Other_1[t](dm) with Sort_1[t] with CardOne
}
class Other_ExprOneMan_1_String[T](override val dataModel: DataModel)
  extends Other_1[T](dataModel)
    with ExprOneMan_1_String[T, [t] =>> Other_1[t] & Sort_1[t] & CardOne]
    with Sort_1[T] {
  override val entity = [t] => (dm: DataModel) => new Other_1[t](dm) with Sort_1[t] with CardOne
}
class Other_ExprOneMan_1_Enum[T](override val dataModel: DataModel)
  extends Other_1[T](dataModel)
    with ExprOneMan_1_Enum[T, [t] =>> Other_1[t]]([t] => (dm: DataModel) => new Other_1[t](dm) with Sort_1[t] with CardOne)
    with Sort_1[T]

class Other_ExprOneMan_1_Integer[T](override val dataModel: DataModel)
  extends Other_1[T](dataModel)
    with ExprOneMan_1_Integer[T, [t] =>> Other_1[t] & Sort_1[t] & CardOne]
    with Sort_1[T] {
  override val entity = [t] => (dm: DataModel) => new Other_1[t](dm) with Sort_1[t] with CardOne
}
class Other_ExprOneMan_1_Decimal[T](override val dataModel: DataModel)
  extends Other_1[T](dataModel)
    with ExprOneMan_1_Decimal[T, [t] =>> Other_1[t] & Sort_1[t] & CardOne]
    with Sort_1[T] {
  override val entity = [t] => (dm: DataModel) => new Other_1[t](dm) with Sort_1[t] with CardOne
}
class Other_ExprOneMan_1_Boolean[T](override val dataModel: DataModel)
  extends Other_1[T](dataModel)
    with ExprOneMan_1_Boolean[T, [t] =>> Other_1[t] & Sort_1[t] & CardOne]
    with Sort_1[T] {
  override val entity = [t] => (dm: DataModel) => new Other_1[t](dm) with Sort_1[t] with CardOne
}


class Other_ExprOneMan_n[T, Tpl <: Tuple](override val dataModel: DataModel)
  extends Other_n[Tpl](dataModel)
    with ExprOneMan_n[T, Tpl, [tpl <: Tuple] =>> Other_n[tpl] & Sort_n[tpl] & CardOne]
    with Sort_n[Tpl] {
  override val entity = [tpl <: Tuple] => (dm: DataModel) => new Other_n[tpl](dm) with Sort_n[tpl] with CardOne
}
class Other_ExprOneMan_n_String[T, Tpl <: Tuple](override val dataModel: DataModel)
  extends Other_n[Tpl](dataModel)
    with ExprOneMan_n[T, Tpl, [tpl <: Tuple] =>> Other_n[tpl] & Sort_n[tpl] & CardOne]
    with Sort_n[Tpl] {
  override val entity = [tpl <: Tuple] => (dm: DataModel) => new Other_n[tpl](dm) with Sort_n[tpl] with CardOne
}
class Other_ExprOneMan_n_Enum[T, Tpl <: Tuple](override val dataModel: DataModel)
  extends Other_n[Tpl](dataModel)
    with ExprOneMan_n_Enum[T, Tpl, [tpl <: Tuple] =>> Other_n[tpl]]([tpl <: Tuple] => (dm: DataModel) => new Other_n[tpl](dm) with Sort_n[tpl] with CardOne)
    with Sort_n[Tpl]

class Other_ExprOneMan_n_Integer[T, Tpl <: Tuple](override val dataModel: DataModel)
  extends Other_n[Tpl](dataModel)
    with ExprOneMan_n[T, Tpl, [tpl <: Tuple] =>> Other_n[tpl] & Sort_n[tpl] & CardOne]
    with Sort_n[Tpl] {
  override val entity = [tpl <: Tuple] => (dm: DataModel) => new Other_n[tpl](dm) with Sort_n[tpl] with CardOne
}
class Other_ExprOneMan_n_Decimal[T, Tpl <: Tuple](override val dataModel: DataModel)
  extends Other_n[Tpl](dataModel)
    with ExprOneMan_n[T, Tpl, [tpl <: Tuple] =>> Other_n[tpl] & Sort_n[tpl] & CardOne]
    with Sort_n[Tpl] {
  override val entity = [tpl <: Tuple] => (dm: DataModel) => new Other_n[tpl](dm) with Sort_n[tpl] with CardOne
}
class Other_ExprOneMan_n_Boolean[T, Tpl <: Tuple](override val dataModel: DataModel)
  extends Other_n[Tpl](dataModel)
    with ExprOneMan_n[T, Tpl, [tpl <: Tuple] =>> Other_n[tpl] & Sort_n[tpl] & CardOne]
    with Sort_n[Tpl] {
  override val entity = [tpl <: Tuple] => (dm: DataModel) => new Other_n[tpl](dm) with Sort_n[tpl] with CardOne
}


class Other_ExprOneOpt_1[T](override val dataModel: DataModel)
  extends Other_1[Option[T]](dataModel)
    with ExprOneOpt[T, Other_1[Option[T]]]((dm: DataModel) => new Other_1[Option[T]](dm) with Sort_1[Option[T]] with CardOne)
    with Sort_1[Option[T]]

class Other_ExprOneOpt_1_Enum[T](override val dataModel: DataModel)
  extends Other_1[Option[T]](dataModel)
    with ExprOneOpt_Enum[T, Other_1[Option[T]]]((dm: DataModel) => new Other_1[Option[T]](dm) with Sort_1[Option[T]] with CardOne)
    with Sort_1[Option[T]]

class Other_ExprOneOpt_n[T, Tpl <: Tuple](override val dataModel: DataModel)
  extends Other_n[Tpl](dataModel)
    with ExprOneOpt[T, Other_n[Tpl]]((dm: DataModel) => new Other_n[Tpl](dm) with Sort_n[Tpl] with CardOne)
    with Sort_n[Tpl]

class Other_ExprOneOpt_n_Enum[T, Tpl <: Tuple](override val dataModel: DataModel)
  extends Other_n[Tpl](dataModel)
    with ExprOneOpt_Enum[T, Other_n[Tpl]]((dm: DataModel) => new Other_n[Tpl](dm) with Sort_n[Tpl] with CardOne)
    with Sort_n[Tpl]


class Other_ExprOneTac_0[T](override val dataModel: DataModel)
  extends Other_0(dataModel)
    with ExprOneTac[T, Other_0] {
  override val entity = (dm: DataModel) => new Other_0(dm) with CardOne
}
class Other_ExprOneTac_0_String[T](override val dataModel: DataModel)
  extends Other_0(dataModel)
    with ExprOneTac_String[T, Other_0] {
  override val entity = (dm: DataModel) => new Other_0(dm) with CardOne
}
class Other_ExprOneTac_0_Enum[T](override val dataModel: DataModel)
  extends Other_0(dataModel)
    with ExprOneTac_Enum[T, Other_0]((dm: DataModel) => new Other_0(dm) with Sort_1[T] with CardOne)

class Other_ExprOneTac_0_Integer[T](override val dataModel: DataModel)
  extends Other_0(dataModel)
    with ExprOneTac_Integer[T, Other_0] {
  override val entity = (dm: DataModel) => new Other_0(dm) with CardOne
}

class Other_ExprOneTac_1[T](override val dataModel: DataModel)
  extends Other_1[T](dataModel)
    with ExprOneTac[T, Other_1[?]] {
  override val entity = (dm: DataModel) => new Other_1[T](dm) with CardOne
}
class Other_ExprOneTac_1_String[T](override val dataModel: DataModel)
  extends Other_1[T](dataModel)
    with ExprOneTac_String[T, Other_1[?]] {
  override val entity = (dm: DataModel) => new Other_1[T](dm) with CardOne
}
class Other_ExprOneTac_1_Enum[T](override val dataModel: DataModel)
  extends Other_1[T](dataModel)
    with ExprOneTac_Enum[T, Other_1[?]]((dm: DataModel) => new Other_1[T](dm) with  CardOne)

class Other_ExprOneTac_1_Integer[T](override val dataModel: DataModel)
  extends Other_1[T](dataModel)
    with ExprOneTac_Integer[T, Other_1[?]] {
  override val entity = (dm: DataModel) => new Other_1[T](dm) with CardOne
}


class Other_ExprOneTac_n[T, Tpl <: Tuple](override val dataModel: DataModel)
  extends Other_n[Tpl](dataModel)
    with ExprOneTac[T, Other_n[?]] {
  override val entity = (dm: DataModel) => new Other_n[Tpl](dm) with CardOne
}

class Other_ExprOneTac_n_String[T, Tpl <: Tuple](override val dataModel: DataModel)
  extends Other_n[Tpl](dataModel)
    with ExprOneTac_String[T, Other_n[?]] {
  override val entity = (dm: DataModel) => new Other_n[Tpl](dm) with CardOne
}

class Other_ExprOneTac_n_Enum[T, Tpl <: Tuple](override val dataModel: DataModel)
  extends Other_n[Tpl](dataModel)
    with ExprOneTac_Enum[T, Other_n[?]]((dm: DataModel) => new Other_n[Tpl](dm) with  CardOne)

class Other_ExprOneTac_n_Integer[T, Tpl <: Tuple](override val dataModel: DataModel)
  extends Other_n[Tpl](dataModel)
    with ExprOneTac_Integer[T, Other_n[?]] {
  override val entity = (dm: DataModel) => new Other_n[Tpl](dm) with CardOne
}


// Set -------------------------------

class Other_ExprSetMan_1[T](override val dataModel: DataModel)
  extends Other_1[Set[T]](dataModel)
    with ExprSetMan[T, Other_1[Set[T]]]((dm: DataModel) => new Other_1[Set[T]](dm))

class Other_ExprSetMan_n[T, Tpl <: Tuple](override val dataModel: DataModel)
  extends Other_n[Tpl](dataModel)
    with ExprSetMan[T, Other_n[Tpl]]((dm: DataModel) => new Other_n[Tpl](dm))


class Other_ExprSetOpt_1[T](override val dataModel: DataModel)
  extends Other_1[Option[Set[T]]](dataModel)
    with ExprSetOpt[T, Other_1[Option[Set[T]]]]((dm: DataModel) => new Other_1[Option[Set[T]]](dm))

class Other_ExprSetOpt_n[T, Tpl <: Tuple](override val dataModel: DataModel)
  extends Other_n[Tpl](dataModel)
    with ExprSetOpt[T, Other_n[Tpl]]((dm: DataModel) => new Other_n[Tpl](dm))


class Other_ExprSetTac_0[T](override val dataModel: DataModel)
  extends Other_0(dataModel)
    with ExprOneTac[T, Other_0] {
  override val entity = (dm: DataModel) => new Other_0(dm)
}

class Other_ExprSetTac_1[T](override val dataModel: DataModel)
  extends Other_1[Set[T]](dataModel)
    with ExprSetTac[T, Other_1[Set[T]]]((dm: DataModel) => new Other_1[Set[T]](dm))

class Other_ExprSetTac_n[T, Tpl <: Tuple](override val dataModel: DataModel)
  extends Other_n[Tpl](dataModel)
    with ExprSetTac[T, Other_n[Tpl]]((dm: DataModel) => new Other_n[Tpl](dm))


// Seq -------------------------------

class Other_ExprSeqMan_1[T](override val dataModel: DataModel)
  extends Other_1[Seq[T]](dataModel)
    with ExprSeqMan[T, Other_1[Seq[T]]]((dm: DataModel) => new Other_1[Seq[T]](dm))

class Other_ExprSeqMan_n[T, Tpl <: Tuple](override val dataModel: DataModel)
  extends Other_n[Tpl](dataModel)
    with ExprSeqMan[T, Other_n[Tpl]]((dm: DataModel) => new Other_n[Tpl](dm))


class Other_ExprSeqOpt_1[T](override val dataModel: DataModel)
  extends Other_1[Option[Seq[T]]](dataModel)
    with ExprSeqOpt[T, Other_1[Option[Seq[T]]]]((dm: DataModel) => new Other_1[Option[Seq[T]]](dm))

class Other_ExprSeqOpt_n[T, Tpl <: Tuple](override val dataModel: DataModel)
  extends Other_n[Tpl](dataModel)
    with ExprSeqOpt[T, Other_n[Tpl]]((dm: DataModel) => new Other_n[Tpl](dm))


class Other_ExprSeqTac_0[T](override val dataModel: DataModel)
  extends Other_0(dataModel)
    with ExprOneTac[T, Other_0] {
  override val entity = (dm: DataModel) => new Other_0(dm)
}
class Other_ExprSeqTac_1[T](override val dataModel: DataModel)
  extends Other_1[Seq[T]](dataModel)
    with ExprSeqTac[T, Other_1[Seq[T]]]((dm: DataModel) => new Other_1[Seq[T]](dm))

class Other_ExprSeqTac_n[T, Tpl <: Tuple](override val dataModel: DataModel)
  extends Other_n[Tpl](dataModel)
    with ExprSeqTac[T, Other_n[Tpl]]((dm: DataModel) => new Other_n[Tpl](dm))


// Map -------------------------------

class Other_ExprMapMan_1[T](override val dataModel: DataModel)
  extends Other_1[Map[String, T]](dataModel)
    with ExprMapMan_1[T, [t] =>> Other_1[t]]([t] => (dm: DataModel) => new Other_1[t](dm))

class Other_ExprMapMan_n[T, Tpl <: Tuple](override val dataModel: DataModel)
  extends Other_n[Tpl](dataModel)
    with ExprMapMan_n[T, Tpl, [tpl <: Tuple] =>> Other_n[tpl]]([tpl <: Tuple] => (dm: DataModel) => new Other_n[tpl](dm))


class Other_ExprMapOpt_1[T](override val dataModel: DataModel)
  extends Other_1[Option[Map[String, T]]](dataModel)
    with ExprMapOpt_1[T, [t] =>> Other_1[t]]([t] => (dm: DataModel) => new Other_1[t](dm))

class Other_ExprMapOpt_n[T, Tpl <: Tuple](override val dataModel: DataModel)
  extends Other_n[Tpl](dataModel)
    with ExprMapOpt_n[T, Tpl, [t <: Tuple] =>> Other_n[t]]([t <: Tuple] => (dm: DataModel) => new Other_n[t](dm))


class Other_ExprMapTac_0[T](override val dataModel: DataModel)
  extends Other_0(dataModel)
    with ExprMapTac[T, Other_0]((dm: DataModel) => new Other_0(dm))

class Other_ExprMapTac_1[T](override val dataModel: DataModel)
  extends Other_1[Map[String, T]](dataModel)
    with ExprMapTac[T, Other_1[Map[String, T]]]((dm: DataModel) => new Other_1[Map[String, T]](dm))

class Other_ExprMapTac_n[T, Tpl <: Tuple](override val dataModel: DataModel)
  extends Other_n[Tpl](dataModel)
    with ExprMapTac[T, Other_n[Tpl]]((dm: DataModel) => new Other_n[Tpl](dm))




// Byte Array -------------------------------

class Other_ExprBArMan_1[T](override val dataModel: DataModel)
  extends Other_1[Array[T]](dataModel)
    with ExprBArMan[T, Other_1[Array[T]]]((dm: DataModel) => new Other_1[Array[T]](dm))

class Other_ExprBArMan_n[T, Tpl <: Tuple](override val dataModel: DataModel)
  extends Other_n[Tpl](dataModel)
    with ExprBArMan[T, Other_n[Tpl]]((dm: DataModel) => new Other_n[Tpl](dm))


class Other_ExprBArOpt_1[T](override val dataModel: DataModel)
  extends Other_1[Option[Array[T]]](dataModel)
    with ExprBArOpt[T, Other_1[Option[Array[T]]]]((dm: DataModel) => new Other_1[Option[Array[T]]](dm))

class Other_ExprBArOpt_n[T, Tpl <: Tuple](override val dataModel: DataModel)
  extends Other_n[Tpl](dataModel)
    with ExprBArOpt[T, Other_n[Tpl]]((dm: DataModel) => new Other_n[Tpl](dm))


class Other_ExprBArTac_0[T](override val dataModel: DataModel)
  extends Other_0(dataModel)
    with ExprOneTac[T, Other_0] {
  override val entity = (dm: DataModel) => new Other_0(dm)
}
class Other_ExprBArTac_1[T](override val dataModel: DataModel)
  extends Other_1[Array[T]](dataModel)
    with ExprBArTac[T, Other_1[Array[T]]]((dm: DataModel) => new Other_1[Array[T]](dm))

class Other_ExprBArTac_n[T, Tpl <: Tuple](override val dataModel: DataModel)
  extends Other_n[Tpl](dataModel)
    with ExprBArTac[T, Other_n[Tpl]]((dm: DataModel) => new Other_n[Tpl](dm))




// Expressions =========================================================================================================

//type Test1 = Long *: Tuple1[Int]
//type Test2 = Tuple1[Int] :* Long
//type Test3 = Init[Tuple1[Int]] :* Long

//trait Test {
//  val a1   : Other_ExprOneMan_1[Long]          = Other.id
//  val a2   : Query[Long]                       = Other.id.query
//  val a3   : Query[Long]                       = Other.id.a1.query
//  val a4   : Any                               = Other.id.apply(1).a1.query
//  val a5   : Query[(Long, Int)]                = Other.id.i.query
//  val a6   : Query[Tpl]                        = Other.id.i.>(4).query
//  val x6   : Query[(Long, Int, Int)]           = Other.id.i.>(4).i.query
//  val a7   : Query[Int]                        = Other.id.i_.query
//  val a8   : Query[Option[Int]]                = Other.i_?.query
//  val a9   : Query[(Long, Option[Int])]        = Other.id.i_?.query
//  val a0   : Query[T]                          = Other.i.apply(Other.i_).query
//  val b4   : Query[Int]                        = Other.i.apply(Other.i_).i_.query
//  val b1   : Query[T]                          = Other.i.apply(count).query
//  val b2   : Query[T]                          = Other.i.apply(min).query
//  val b3   : Query[(Int, Int)]                 = Other.i.apply(Other.i_).i.query
//  val b5   : Query[(Long, Int, Int)]           = Other.id.i.apply(count).i.query
//  val b5_37: Query[(Long, Int, Int)]           = Other.id.i.i.query
//  //  val b6_33: Query[(Long, Int) :* Int]         = Other.id.i.i.query
//  val b7   : Query[(Int, Int)]                 = Other.i.apply(min).i.query
//}


// Meta attributes =====================================================================================================

trait Other_attrs {
  protected def id_man = AttrOneManID("Other", "id", coord = List(2, 34))
  protected def i_man = AttrOneManInt("Other", "i", coord = List(2, 35))
  protected def s_man = AttrOneManString("Other", "s", coord = List(2, 36))
  protected def int_man = AttrOneManInt("Other", "int", coord = List(2, 37))

  protected def i_opt = AttrOneOptInt("Other", "i", coord = List(2, 35))
  protected def s_opt = AttrOneOptString("Other", "s", coord = List(2, 36))
  protected def int_opt = AttrOneOptInt("Other", "int", coord = List(2, 37))

  protected def id_tac = AttrOneTacID("Other", "id", coord = List(2, 34))
  protected def i_tac = AttrOneTacInt("Other", "i", coord = List(2, 35))
  protected def s_tac = AttrOneTacString("Other", "s", coord = List(2, 36))
  protected def int_tac = AttrOneTacInt("Other", "int", coord = List(2, 37))
}


// Sort ================================================================================================================

trait Sort_1[T] extends Sort[Other_1[T]] { self: Molecule =>
  override def sortEntity: DataModel => Other_1[T] = (dm: DataModel) => Other_1[T](dm)
}
trait Sort_n[Tpl <: Tuple] extends Sort[Other_n[Tpl]] { self: Molecule =>
  override def sortEntity: DataModel => Other_n[Tpl] = (dm: DataModel) => Other_n[Tpl](dm)
}

//trait Sort_1[T] extends Sorting_1[Other_1, T] { self: Molecule =>
//  override def sortEntity: DataModel => Other_1[T] = (dm: DataModel) => new Other_1[T](dm)
//}
//
//trait Sort_n[Tpl <: Tuple] extends Sorting_n[Other_n, Tpl] { self: Molecule =>
//  override def sortEntity: DataModel => Other_n[Tpl] = (dm: DataModel) => new Other_n[Tpl](dm)
//}