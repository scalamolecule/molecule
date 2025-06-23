// AUTO-GENERATED Molecule DSL boilerplate code for entity `Person`
package molecule.db.sql.h2

import molecule.base.metaModel.*
import molecule.core.dataModel as _dm
import molecule.core.dataModel.Keywords.Kw as _kw
import molecule.core.dataModel.Op as _op
import molecule.db.core.api.*
import molecule.db.core.api.expression.*
import molecule.db.core.ops.ModelTransformations_
import molecule.db.sql.h2.setup.*
import scala.Tuple.{Append, Init}


trait Person_base {
  protected def id_man     = _dm.AttrOneManID     ("Person", "id"    , coord = List(0, 0))
  protected def name_man   = _dm.AttrOneManString ("Person", "name"  , coord = List(0, 1))
  protected def age_man    = _dm.AttrOneManInt    ("Person", "age"   , coord = List(0, 2))
  protected def member_man = _dm.AttrOneManBoolean("Person", "member", coord = List(0, 3))
  
  protected def name_opt   = _dm.AttrOneOptString ("Person", "name"  , coord = List(0, 1))
  protected def age_opt    = _dm.AttrOneOptInt    ("Person", "age"   , coord = List(0, 2))
  protected def member_opt = _dm.AttrOneOptBoolean("Person", "member", coord = List(0, 3))
  
  protected def id_tac     = _dm.AttrOneTacID     ("Person", "id"    , coord = List(0, 0))
  protected def name_tac   = _dm.AttrOneTacString ("Person", "name"  , coord = List(0, 1))
  protected def age_tac    = _dm.AttrOneTacInt    ("Person", "age"   , coord = List(0, 2))
  protected def member_tac = _dm.AttrOneTacBoolean("Person", "member", coord = List(0, 3))
}

object Person extends Person[EmptyTuple, Nothing](_dm.DataModel(Nil, firstEntityIndex = 0)) {
  final def apply(id : Long, ids: Long*) = new Person_0[EmptyTuple, Nothing](_dm.DataModel(List(_dm.AttrOneTacID("Person", "id", _dm.Eq, id +: ids, coord = List(0, 0))), firstEntityIndex = 0))
  final def apply(ids: Iterable[Long])   = new Person_0[EmptyTuple, Nothing](_dm.DataModel(List(_dm.AttrOneTacID("Person", "id", _dm.Eq, ids.toSeq, coord = List(0, 0))), firstEntityIndex = 0))
}


class Person_0[Tpl <: EmptyTuple, T](override val dataModel: _dm.DataModel)
  extends Person_base
    with Molecule_0
    with Molecule_01[Tpl]
    with ModelTransformations_
    with FilterAttr[Tpl, T, Person]
    with SortAttrsOps[Tpl, T, Person]
    with AggregatesOps[Tpl, T, Person]
    with ExprOneManOps[Tpl, T, Person]
    with ExprOneOptOps[Tpl, T, Person]
    with ExprOneTacOps[Tpl, T, Person] {

  def id       = new Person_1[Tuple1[Long   ]        , Long   ](dataModel.add(id_man    )) with ExprOneMan         [Tuple1[Long           ], Long   , Person_1] with CardOne
  def name     = new Person_1[Tuple1[String ]        , String ](dataModel.add(name_man  )) with ExprOneMan_String  [Tuple1[String         ], String , Person_1] with CardOne
  def age      = new Person_1[Tuple1[Int    ]        , Int    ](dataModel.add(age_man   )) with ExprOneMan_Integer [Tuple1[Int            ], Int    , Person_1] with CardOne
  def member   = new Person_1[Tuple1[Boolean]        , Boolean](dataModel.add(member_man)) with ExprOneMan_Boolean [Tuple1[Boolean        ], Boolean, Person_1] with CardOne

  def name_?   = new Person_1[Tuple1[Option[String] ], String ](dataModel.add(name_opt  )) with ExprOneOpt         [Tuple1[Option[String] ], String , Person_1] with CardOne
  def age_?    = new Person_1[Tuple1[Option[Int]    ], Int    ](dataModel.add(age_opt   )) with ExprOneOpt         [Tuple1[Option[Int]    ], Int    , Person_1] with CardOne
  def member_? = new Person_1[Tuple1[Option[Boolean]], Boolean](dataModel.add(member_opt)) with ExprOneOpt         [Tuple1[Option[Boolean]], Boolean, Person_1] with CardOne

  def id_      = new Person_0[Tpl                    , Long   ](dataModel.add(id_tac    )) with ExprOneTac         [Tpl                    , Long   , Person_0] with CardOne
  def name_    = new Person_0[Tpl                    , String ](dataModel.add(name_tac  )) with ExprOneTac_String  [Tpl                    , String , Person_0] with CardOne
  def age_     = new Person_0[Tpl                    , Int    ](dataModel.add(age_tac   )) with ExprOneTac_Integer [Tpl                    , Int    , Person_0] with CardOne
  def member_  = new Person_0[Tpl                    , Boolean](dataModel.add(member_tac)) with ExprOneTac         [Tpl                    , Boolean, Person_0] with CardOne

  override protected def _sort      (sort: String                         ) = new Person[Tpl                      , T     ](addSort  (dataModel, sort           ))
  override protected def _aggrInt   (kw: _kw                              ) = new Person[Append[Init[Tpl], Int   ], Int   ](toInt    (dataModel, kw             )) with SortAttrs[Append[Init[Tpl], Int   ], Int   , Person]
  override protected def _aggrT     (kw: _kw                              ) = new Person[Tpl                      , T     ](asIs     (dataModel, kw             )) with SortAttrs[Tpl                      , T     , Person]
  override protected def _aggrDouble(kw: _kw                              ) = new Person[Append[Init[Tpl], Double], Double](toDouble (dataModel, kw             )) with SortAttrs[Append[Init[Tpl], Double], Double, Person]
  override protected def _aggrSet   (kw: _kw, n: Option[Int]              ) = new Person[Append[Init[Tpl], Set[T]], T     ](asIs     (dataModel, kw, n          ))
  override protected def _aggrDist  (kw: _kw                              ) = new Person[Append[Init[Tpl], Set[T]], T     ](asIs     (dataModel, kw             ))
  override protected def _exprOneMan(op: _op, vs: Seq[T], binding: Boolean) = new Person[Tpl                      , T     ](addOne   (dataModel, op, vs, binding)) with SortAttrs[Tpl, T, Person] with CardOne
  override protected def _exprOneOpt(op: _op, v : Option[T]               ) = new Person[Tpl                      , T     ](addOneOpt(dataModel, op, v          )) with SortAttrs[Tpl, T, Person]
  override protected def _exprOneTac(op: _op, vs: Seq[T], binding: Boolean) = new Person[Tpl                      , T     ](addOne   (dataModel, op, vs, binding)) with CardOne

  override protected def _filterAttrTacTac                        (op: _op, a: Molecule_01[EmptyTuple]                  ) = new Person[Tpl           , T](filterAttr(dataModel, op, a))
  override protected def _filterAttrTacMan[ns[_ <: Tuple     , _]](op: _op, a: Molecule & SortAttrsOps[Tuple1[T], T, ns]) = new Person[Append[Tpl, T], T](filterAttr(dataModel, op, a))
  override protected def _filterAttrManTac                        (op: _op, a: Molecule_01[EmptyTuple] & CardOne        ) = new Person[Tpl           , T](filterAttr(dataModel, op, a)) with SortAttrs[Tpl           , T, Person]
  override protected def _filterAttrManMan[ns[_ <: Tuple     , _]](op: _op, a: Molecule & SortAttrsOps[Tuple1[T], T, ns]) = new Person[Append[Tpl, T], T](filterAttr(dataModel, op, a)) with SortAttrs[Append[Tpl, T], T, Person]
}

class Person_1[Tpl <: Tuple1, T](override val dataModel: _dm.DataModel)
  extends Person_base
    with Molecule_1[T]
    with Molecule_01[Tpl]
    with ModelTransformations_
    with FilterAttr[Tpl, T, Person]
    with SortAttrsOps[Tpl, T, Person]
    with AggregatesOps[Tpl, T, Person]
    with ExprOneManOps[Tpl, T, Person]
    with ExprOneOptOps[Tpl, T, Person]
    with ExprOneTacOps[Tpl, T, Person] {

  def id       = new Person_n[Append[Tpl, Long           ], Long   ](dataModel.add(id_man    )) with ExprOneMan         [Append[Tpl, Long           ], Long   , Person_n] with CardOne
  def name     = new Person_n[Append[Tpl, String         ], String ](dataModel.add(name_man  )) with ExprOneMan_String  [Append[Tpl, String         ], String , Person_n] with CardOne
  def age      = new Person_n[Append[Tpl, Int            ], Int    ](dataModel.add(age_man   )) with ExprOneMan_Integer [Append[Tpl, Int            ], Int    , Person_n] with CardOne
  def member   = new Person_n[Append[Tpl, Boolean        ], Boolean](dataModel.add(member_man)) with ExprOneMan_Boolean [Append[Tpl, Boolean        ], Boolean, Person_n] with CardOne

  def name_?   = new Person_n[Append[Tpl, Option[String] ], String ](dataModel.add(name_opt  )) with ExprOneOpt         [Append[Tpl, Option[String] ], String , Person_n] with CardOne
  def age_?    = new Person_n[Append[Tpl, Option[Int]    ], Int    ](dataModel.add(age_opt   )) with ExprOneOpt         [Append[Tpl, Option[Int]    ], Int    , Person_n] with CardOne
  def member_? = new Person_n[Append[Tpl, Option[Boolean]], Boolean](dataModel.add(member_opt)) with ExprOneOpt         [Append[Tpl, Option[Boolean]], Boolean, Person_n] with CardOne

  def id_      = new Person_1[Tpl                         , Long   ](dataModel.add(id_tac    )) with ExprOneTac         [Tpl                         , Long   , Person_1] with CardOne
  def name_    = new Person_1[Tpl                         , String ](dataModel.add(name_tac  )) with ExprOneTac_String  [Tpl                         , String , Person_1] with CardOne
  def age_     = new Person_1[Tpl                         , Int    ](dataModel.add(age_tac   )) with ExprOneTac_Integer [Tpl                         , Int    , Person_1] with CardOne
  def member_  = new Person_1[Tpl                         , Boolean](dataModel.add(member_tac)) with ExprOneTac         [Tpl                         , Boolean, Person_1] with CardOne


  override protected def _sort      (sort: String                         ) = new Person[Tpl                      , T     ](addSort  (dataModel, sort           ))
  override protected def _aggrInt   (kw: _kw                              ) = new Person[Append[Init[Tpl], Int   ], Int   ](toInt    (dataModel, kw             )) with SortAttrs[Append[Init[Tpl], Int   ], Int   , Person]
  override protected def _aggrT     (kw: _kw                              ) = new Person[Tpl                      , T     ](asIs     (dataModel, kw             )) with SortAttrs[Tpl                      , T     , Person]
  override protected def _aggrDouble(kw: _kw                              ) = new Person[Append[Init[Tpl], Double], Double](toDouble (dataModel, kw             )) with SortAttrs[Append[Init[Tpl], Double], Double, Person]
  override protected def _aggrSet   (kw: _kw, n: Option[Int]              ) = new Person[Append[Init[Tpl], Set[T]], T     ](asIs     (dataModel, kw, n          ))
  override protected def _aggrDist  (kw: _kw                              ) = new Person[Append[Init[Tpl], Set[T]], T     ](asIs     (dataModel, kw             ))
  override protected def _exprOneMan(op: _op, vs: Seq[T], binding: Boolean) = new Person[Tpl                      , T     ](addOne   (dataModel, op, vs, binding)) with SortAttrs[Tpl, T, Person] with CardOne
  override protected def _exprOneOpt(op: _op, v : Option[T]               ) = new Person[Tpl                      , T     ](addOneOpt(dataModel, op, v          )) with SortAttrs[Tpl, T, Person]
  override protected def _exprOneTac(op: _op, vs: Seq[T], binding: Boolean) = new Person[Tpl                      , T     ](addOne   (dataModel, op, vs, binding)) with CardOne

  override protected def _filterAttrTacTac                        (op: _op, a: Molecule_01[EmptyTuple]                  ) = new Person[Tpl           , T](filterAttr(dataModel, op, a))
  override protected def _filterAttrTacMan[ns[_ <: Tuple     , _]](op: _op, a: Molecule & SortAttrsOps[Tuple1[T], T, ns]) = new Person[Append[Tpl, T], T](filterAttr(dataModel, op, a))
  override protected def _filterAttrManTac                        (op: _op, a: Molecule_01[EmptyTuple] & CardOne        ) = new Person[Tpl           , T](filterAttr(dataModel, op, a)) with SortAttrs[Tpl           , T, Person]
  override protected def _filterAttrManMan[ns[_ <: Tuple     , _]](op: _op, a: Molecule & SortAttrsOps[Tuple1[T], T, ns]) = new Person[Append[Tpl, T], T](filterAttr(dataModel, op, a)) with SortAttrs[Append[Tpl, T], T, Person]
}

class Person_n[Tpl <: Tuple, T](override val dataModel: _dm.DataModel)
  extends Person_base
    with Molecule_n[Tpl]
    with ModelTransformations_
    with FilterAttr[Tpl, T, Person]
    with SortAttrsOps[Tpl, T, Person]
    with AggregatesOps[Tpl, T, Person]
    with ExprOneManOps[Tpl, T, Person]
    with ExprOneOptOps[Tpl, T, Person]
    with ExprOneTacOps[Tpl, T, Person] {

  def id       = new Person_n[Append[Tpl, Long           ], Long   ](dataModel.add(id_man    )) with ExprOneMan         [Append[Tpl, Long           ], Long   , Person] with CardOne
  def name     = new Person_n[Append[Tpl, String         ], String ](dataModel.add(name_man  )) with ExprOneMan_String  [Append[Tpl, String         ], String , Person] with CardOne
  def age      = new Person_n[Append[Tpl, Int            ], Int    ](dataModel.add(age_man   )) with ExprOneMan_Integer [Append[Tpl, Int            ], Int    , Person] with CardOne
  def member   = new Person_n[Append[Tpl, Boolean        ], Boolean](dataModel.add(member_man)) with ExprOneMan_Boolean [Append[Tpl, Boolean        ], Boolean, Person] with CardOne

  def name_?   = new Person_n[Append[Tpl, Option[String] ], String ](dataModel.add(name_opt  )) with ExprOneOpt         [Append[Tpl, Option[String] ], String , Person] with CardOne
  def age_?    = new Person_n[Append[Tpl, Option[Int]    ], Int    ](dataModel.add(age_opt   )) with ExprOneOpt         [Append[Tpl, Option[Int]    ], Int    , Person] with CardOne
  def member_? = new Person_n[Append[Tpl, Option[Boolean]], Boolean](dataModel.add(member_opt)) with ExprOneOpt         [Append[Tpl, Option[Boolean]], Boolean, Person] with CardOne

  def id_      = new Person_n[Tpl                         , Long   ](dataModel.add(id_tac    )) with ExprOneTac         [Tpl                         , Long   , Person] with CardOne
  def name_    = new Person_n[Tpl                         , String ](dataModel.add(name_tac  )) with ExprOneTac_String  [Tpl                         , String , Person] with CardOne
  def age_     = new Person_n[Tpl                         , Int    ](dataModel.add(age_tac   )) with ExprOneTac_Integer [Tpl                         , Int    , Person] with CardOne
  def member_  = new Person_n[Tpl                         , Boolean](dataModel.add(member_tac)) with ExprOneTac         [Tpl                         , Boolean, Person] with CardOne


  override protected def _sort      (sort: String                         ) = new Person[Tpl                      , T     ](addSort  (dataModel, sort           ))
  override protected def _aggrInt   (kw: _kw                              ) = new Person[Append[Init[Tpl], Int   ], Int   ](toInt    (dataModel, kw             )) with SortAttrs[Append[Init[Tpl], Int   ], Int   , Person]
  override protected def _aggrT     (kw: _kw                              ) = new Person[Tpl                      , T     ](asIs     (dataModel, kw             )) with SortAttrs[Tpl                      , T     , Person]
  override protected def _aggrDouble(kw: _kw                              ) = new Person[Append[Init[Tpl], Double], Double](toDouble (dataModel, kw             )) with SortAttrs[Append[Init[Tpl], Double], Double, Person]
  override protected def _aggrSet   (kw: _kw, n: Option[Int]              ) = new Person[Append[Init[Tpl], Set[T]], T     ](asIs     (dataModel, kw, n          ))
  override protected def _aggrDist  (kw: _kw                              ) = new Person[Append[Init[Tpl], Set[T]], T     ](asIs     (dataModel, kw             ))
  override protected def _exprOneMan(op: _op, vs: Seq[T], binding: Boolean) = new Person[Tpl                      , T     ](addOne   (dataModel, op, vs, binding)) with SortAttrs[Tpl, T, Person] with CardOne
  override protected def _exprOneOpt(op: _op, v : Option[T]               ) = new Person[Tpl                      , T     ](addOneOpt(dataModel, op, v          )) with SortAttrs[Tpl, T, Person]
  override protected def _exprOneTac(op: _op, vs: Seq[T], binding: Boolean) = new Person[Tpl                      , T     ](addOne   (dataModel, op, vs, binding)) with CardOne

  override protected def _filterAttrTacTac                        (op: _op, a: Molecule_01[EmptyTuple]                  ) = new Person[Tpl           , T](filterAttr(dataModel, op, a))
  override protected def _filterAttrTacMan[ns[_ <: Tuple     , _]](op: _op, a: Molecule & SortAttrsOps[Tuple1[T], T, ns]) = new Person[Append[Tpl, T], T](filterAttr(dataModel, op, a))
  override protected def _filterAttrManTac                        (op: _op, a: Molecule_01[EmptyTuple] & CardOne        ) = new Person[Tpl           , T](filterAttr(dataModel, op, a)) with SortAttrs[Tpl           , T, Person]
  override protected def _filterAttrManMan[ns[_ <: Tuple     , _]](op: _op, a: Molecule & SortAttrsOps[Tuple1[T], T, ns]) = new Person[Append[Tpl, T], T](filterAttr(dataModel, op, a)) with SortAttrs[Append[Tpl, T], T, Person]
}

class Person[Tpl <: Tuple, T](override val dataModel: _dm.DataModel)
  extends Person_base
    with Molecule_01[Tpl]
    with ModelTransformations_
    with FilterAttr[Tpl, T, Person]
    with SortAttrsOps[Tpl, T, Person]
    with AggregatesOps[Tpl, T, Person]
    with ExprOneManOps[Tpl, T, Person]
    with ExprOneOptOps[Tpl, T, Person]
    with ExprOneTacOps[Tpl, T, Person] {

  def id       = new Person[Append[Tpl, Long           ], Long   ](dataModel.add(id_man    )) with ExprOneMan         [Append[Tpl, Long           ], Long   , Person] with CardOne
  def name     = new Person[Append[Tpl, String         ], String ](dataModel.add(name_man  )) with ExprOneMan_String  [Append[Tpl, String         ], String , Person] with CardOne
  def age      = new Person[Append[Tpl, Int            ], Int    ](dataModel.add(age_man   )) with ExprOneMan_Integer [Append[Tpl, Int            ], Int    , Person] with CardOne
  def member   = new Person[Append[Tpl, Boolean        ], Boolean](dataModel.add(member_man)) with ExprOneMan_Boolean [Append[Tpl, Boolean        ], Boolean, Person] with CardOne

  def name_?   = new Person[Append[Tpl, Option[String] ], String ](dataModel.add(name_opt  )) with ExprOneOpt         [Append[Tpl, Option[String] ], String , Person] with CardOne
  def age_?    = new Person[Append[Tpl, Option[Int]    ], Int    ](dataModel.add(age_opt   )) with ExprOneOpt         [Append[Tpl, Option[Int]    ], Int    , Person] with CardOne
  def member_? = new Person[Append[Tpl, Option[Boolean]], Boolean](dataModel.add(member_opt)) with ExprOneOpt         [Append[Tpl, Option[Boolean]], Boolean, Person] with CardOne

  def id_      = new Person[Tpl                         , Long   ](dataModel.add(id_tac    )) with ExprOneTac         [Tpl                         , Long   , Person] with CardOne
  def name_    = new Person[Tpl                         , String ](dataModel.add(name_tac  )) with ExprOneTac_String  [Tpl                         , String , Person] with CardOne
  def age_     = new Person[Tpl                         , Int    ](dataModel.add(age_tac   )) with ExprOneTac_Integer [Tpl                         , Int    , Person] with CardOne
  def member_  = new Person[Tpl                         , Boolean](dataModel.add(member_tac)) with ExprOneTac         [Tpl                         , Boolean, Person] with CardOne


  override protected def _sort      (sort: String                         ) = new Person[Tpl                      , T     ](addSort  (dataModel, sort           ))
  override protected def _aggrInt   (kw: _kw                              ) = new Person[Append[Init[Tpl], Int   ], Int   ](toInt    (dataModel, kw             )) with SortAttrs[Append[Init[Tpl], Int   ], Int   , Person]
  override protected def _aggrT     (kw: _kw                              ) = new Person[Tpl                      , T     ](asIs     (dataModel, kw             )) with SortAttrs[Tpl                      , T     , Person]
  override protected def _aggrDouble(kw: _kw                              ) = new Person[Append[Init[Tpl], Double], Double](toDouble (dataModel, kw             )) with SortAttrs[Append[Init[Tpl], Double], Double, Person]
  override protected def _aggrSet   (kw: _kw, n: Option[Int]              ) = new Person[Append[Init[Tpl], Set[T]], T     ](asIs     (dataModel, kw, n          ))
  override protected def _aggrDist  (kw: _kw                              ) = new Person[Append[Init[Tpl], Set[T]], T     ](asIs     (dataModel, kw             ))
  override protected def _exprOneMan(op: _op, vs: Seq[T], binding: Boolean) = new Person[Tpl                      , T     ](addOne   (dataModel, op, vs, binding)) with SortAttrs[Tpl, T, Person] with CardOne
  override protected def _exprOneOpt(op: _op, v : Option[T]               ) = new Person[Tpl                      , T     ](addOneOpt(dataModel, op, v          )) with SortAttrs[Tpl, T, Person]
  override protected def _exprOneTac(op: _op, vs: Seq[T], binding: Boolean) = new Person[Tpl                      , T     ](addOne   (dataModel, op, vs, binding)) with CardOne

  override protected def _filterAttrTacTac                        (op: _op, a: Molecule_01[EmptyTuple]                  ) = new Person[Tpl           , T](filterAttr(dataModel, op, a))
  override protected def _filterAttrTacMan[ns[_ <: Tuple     , _]](op: _op, a: Molecule & SortAttrsOps[Tuple1[T], T, ns]) = new Person[Append[Tpl, T], T](filterAttr(dataModel, op, a))
  override protected def _filterAttrManTac                        (op: _op, a: Molecule_01[EmptyTuple] & CardOne        ) = new Person[Tpl           , T](filterAttr(dataModel, op, a)) with SortAttrs[Tpl           , T, Person]
  override protected def _filterAttrManMan[ns[_ <: Tuple     , _]](op: _op, a: Molecule & SortAttrsOps[Tuple1[T], T, ns]) = new Person[Append[Tpl, T], T](filterAttr(dataModel, op, a)) with SortAttrs[Append[Tpl, T], T, Person]
}

