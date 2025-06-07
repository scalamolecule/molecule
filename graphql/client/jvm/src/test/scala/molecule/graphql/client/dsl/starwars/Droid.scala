/*
* AUTO-GENERATED Molecule DSL boilerplate code for entity `Droid`
*
* To change:
* 1. Edit domain structure in molecule.graphql.client.dataModel.dsl.Starwars
* 2. `sbt compile -Dmolecule=true`
*/
package molecule.graphql.client.dsl.starwars

import java.time._
import molecule.db.base.ast._
import molecule.db.core.api.Keywords._
import molecule.db.core.api._
import molecule.db.core.api.expression._
import molecule.db.core.ast
import molecule.db.core.ast._


trait Droid_base {
  protected lazy val id_man             : AttrOneManID     = AttrOneManID    ("Droid", "id"             , coord = List(1, 4   ))
  protected lazy val name_man           : AttrOneManString = AttrOneManString("Droid", "name"           , coord = List(1, 5   ))
  protected lazy val friends_man        : AttrSetManID     = AttrSetManID    ("Droid", "friends"        , coord = List(1, 6, 0), ref = Some("Character"))
  protected lazy val appearsIn_man      : AttrSetManString = AttrSetManString("Droid", "appearsIn"      , coord = List(1, 7   ), validator = Some(validation_appearsIn))
  protected lazy val primaryFunction_man: AttrOneManString = AttrOneManString("Droid", "primaryFunction", coord = List(1, 8   ))

  protected lazy val name_opt           : AttrOneOptString = AttrOneOptString("Droid", "name"           , coord = List(1, 5   ))
  protected lazy val friends_opt        : AttrSetOptID     = AttrSetOptID    ("Droid", "friends"        , coord = List(1, 6, 0), ref = Some("Character"))
  protected lazy val appearsIn_opt      : AttrSetOptString = AttrSetOptString("Droid", "appearsIn"      , coord = List(1, 7   ), validator = Some(validation_appearsIn))
  protected lazy val primaryFunction_opt: AttrOneOptString = AttrOneOptString("Droid", "primaryFunction", coord = List(1, 8   ))

  protected lazy val id_tac             : AttrOneTacID     = AttrOneTacID    ("Droid", "id"             , coord = List(1, 4   ))
  protected lazy val name_tac           : AttrOneTacString = AttrOneTacString("Droid", "name"           , coord = List(1, 5   ))
  protected lazy val friends_tac        : AttrSetTacID     = AttrSetTacID    ("Droid", "friends"        , coord = List(1, 6, 0), ref = Some("Character"))
  protected lazy val appearsIn_tac      : AttrSetTacString = AttrSetTacString("Droid", "appearsIn"      , coord = List(1, 7   ), validator = Some(validation_appearsIn))
  protected lazy val primaryFunction_tac: AttrOneTacString = AttrOneTacString("Droid", "primaryFunction", coord = List(1, 8   ))

  private lazy val validation_appearsIn = new ValidateString {
    override def validate(v: String): Seq[String] = {
      val ok: String => Boolean = v => Seq("NEWHOPE", "EMPIRE", "JEDI").contains(v)
      if (ok(v)) Nil else Seq(s"""Value `$v` is not one of the allowed values in Seq("NEWHOPE", "EMPIRE", "JEDI")""")
    }
  }
}

object Droid extends Droid_0[Nothing](DataModel(Nil, firstEntityIndex = 1)) with OptEntityOp_0[Droid_1_refs] with OptEntity_0[Droid_1_refs] {
  final def apply(id : String, ids: String*) = new Droid_0[String](DataModel(List(AttrOneTacString("Droid", "id", Eq, id +: ids, coord = List(1, 4))), firstEntityIndex = 1))
  final def apply(ids: Iterable[String])   = new Droid_0[String](DataModel(List(AttrOneTacString("Droid", "id", Eq, ids.toSeq, coord = List(1, 4))), firstEntityIndex = 1))

  override protected def _optEntity[OptEntityTpl](attrs: List[Attr]): Droid_1_refs[Option[OptEntityTpl], Any] =
    new Droid_1_refs[Option[OptEntityTpl], Any](DataModel(List(ast.OptEntity(attrs))))
}


class Droid_0[t](override val dataModel: DataModel) extends Droid_0_refs[t](dataModel) with Droid_base with ModelOps_0[t, Droid_0, Droid_1] {
  lazy val id                = new Droid_1[Long               , Long  ](dataModel.add(id_man             )) with ExprOneMan_1         [Long               , Long  , Droid_1, Droid_2] with CardOne
  lazy val name              = new Droid_1[String             , String](dataModel.add(name_man           )) with ExprOneMan_1_String  [String             , String, Droid_1, Droid_2] with CardOne
  lazy val friends           = new Droid_1[Set[Long]          , Long  ](dataModel.add(friends_man        )) with ExprSetMan_1         [Set[Long]          , Long  , Droid_1, Droid_2] with CardSet
  lazy val appearsIn         = new Droid_1[Set[String]        , String](dataModel.add(appearsIn_man      )) with ExprSetMan_1         [Set[String]        , String, Droid_1, Droid_2] with CardSet
  lazy val primaryFunction   = new Droid_1[String             , String](dataModel.add(primaryFunction_man)) with ExprOneMan_1_String  [String             , String, Droid_1, Droid_2] with CardOne

  lazy val name_?            = new Droid_1[Option[String]     , String](dataModel.add(name_opt           )) with ExprOneOpt_1         [Option[String]     , String, Droid_1, Droid_2] with CardOne
  lazy val friends_?         = new Droid_1[Option[Set[Long]]  , Long  ](dataModel.add(friends_opt        )) with ExprSetOpt_1         [Option[Set[Long]]  , Long  , Droid_1, Droid_2] with CardSet
  lazy val appearsIn_?       = new Droid_1[Option[Set[String]], String](dataModel.add(appearsIn_opt      )) with ExprSetOpt_1         [Option[Set[String]], String, Droid_1, Droid_2] with CardSet
  lazy val primaryFunction_? = new Droid_1[Option[String]     , String](dataModel.add(primaryFunction_opt)) with ExprOneOpt_1         [Option[String]     , String, Droid_1, Droid_2] with CardOne

  lazy val id_               = new Droid_0[                     Long  ](dataModel.add(id_tac             )) with ExprOneTac_0         [                     Long  , Droid_0, Droid_1] with CardOne
  lazy val name_             = new Droid_0[                     String](dataModel.add(name_tac           )) with ExprOneTac_0_String  [                     String, Droid_0, Droid_1] with CardOne
  lazy val friends_          = new Droid_0[                     Long  ](dataModel.add(friends_tac        )) with ExprSetTac_0         [                     Long  , Droid_0, Droid_1] with CardSet
  lazy val appearsIn_        = new Droid_0[                     String](dataModel.add(appearsIn_tac      )) with ExprSetTac_0         [                     String, Droid_0, Droid_1] with CardSet
  lazy val primaryFunction_  = new Droid_0[                     String](dataModel.add(primaryFunction_tac)) with ExprOneTac_0_String  [                     String, Droid_0, Droid_1] with CardOne

  override protected def _exprOneTac(op: Op, vs: Seq[t], binding: Boolean) = new Droid_0[t](addOne  (dataModel, op, vs, binding)) with CardOne
  override protected def _exprSet   (op: Op, vs: Set[t]            ) = new Droid_0[t](addSet  (dataModel, op, vs         )) with CardSet

  override protected def _attrTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]) = new Droid_0[   t](filterAttr(dataModel, op, a))
  override protected def _attrMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]) = new Droid_1[X, t](filterAttr(dataModel, op, a))
}

class Droid_0_refs[t](override val dataModel: DataModel) extends Molecule_00 {
  trait NestedInit extends NestedOp_0 with Nested_0 { self: Molecule =>
    override protected def _nestedMan[NestedTpl](nestedDataModel: DataModel): NestedInit_00[NestedTpl] =
      new NestedInit_00(DataModel(
        self.dataModel.elements.init :+ Nested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes,
        binds = self.dataModel.binds + nestedDataModel.binds
      ))

    override protected def _nestedOpt[NestedTpl](nestedDataModel: DataModel): NestedInit_00[NestedTpl] =
      new NestedInit_00(DataModel(
        self.dataModel.elements.init :+ OptNested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes,
        binds = self.dataModel.binds + nestedDataModel.binds
      ))
  }

  object Friends extends Character_0[t](dataModel.add(ast.Ref("Droid", "friends", "Character", CardSet, false, List(1, 6, 0)))) with NestedInit
}


class Droid_1[A, t](override val dataModel: DataModel) extends Droid_1_refs[A, t](dataModel) with Droid_base with ModelOps_1[A, t, Droid_1, Droid_2] {
  lazy val id                = new Droid_2[A, Long               , Long  ](dataModel.add(id_man             )) with ExprOneMan_2         [A, Long               , Long  , Droid_2, Droid_3] with CardOne
  lazy val name              = new Droid_2[A, String             , String](dataModel.add(name_man           )) with ExprOneMan_2_String  [A, String             , String, Droid_2, Droid_3] with CardOne
  lazy val friends           = new Droid_2[A, Set[Long]          , Long  ](dataModel.add(friends_man        )) with ExprSetMan_2         [A, Set[Long]          , Long  , Droid_2, Droid_3] with CardSet
  lazy val appearsIn         = new Droid_2[A, Set[String]        , String](dataModel.add(appearsIn_man      )) with ExprSetMan_2         [A, Set[String]        , String, Droid_2, Droid_3] with CardSet
  lazy val primaryFunction   = new Droid_2[A, String             , String](dataModel.add(primaryFunction_man)) with ExprOneMan_2_String  [A, String             , String, Droid_2, Droid_3] with CardOne

  lazy val name_?            = new Droid_2[A, Option[String]     , String](dataModel.add(name_opt           )) with ExprOneOpt_2         [A, Option[String]     , String, Droid_2, Droid_3] with CardOne
  lazy val friends_?         = new Droid_2[A, Option[Set[Long]]  , Long  ](dataModel.add(friends_opt        )) with ExprSetOpt_2         [A, Option[Set[Long]]  , Long  , Droid_2, Droid_3] with CardSet
  lazy val appearsIn_?       = new Droid_2[A, Option[Set[String]], String](dataModel.add(appearsIn_opt      )) with ExprSetOpt_2         [A, Option[Set[String]], String, Droid_2, Droid_3] with CardSet
  lazy val primaryFunction_? = new Droid_2[A, Option[String]     , String](dataModel.add(primaryFunction_opt)) with ExprOneOpt_2         [A, Option[String]     , String, Droid_2, Droid_3] with CardOne

  lazy val id_               = new Droid_1[A                     , Long  ](dataModel.add(id_tac             )) with ExprOneTac_1         [A                     , Long  , Droid_1, Droid_2] with CardOne
  lazy val name_             = new Droid_1[A                     , String](dataModel.add(name_tac           )) with ExprOneTac_1_String  [A                     , String, Droid_1, Droid_2] with CardOne
  lazy val friends_          = new Droid_1[A                     , Long  ](dataModel.add(friends_tac        )) with ExprSetTac_1         [A                     , Long  , Droid_1, Droid_2] with CardSet
  lazy val appearsIn_        = new Droid_1[A                     , String](dataModel.add(appearsIn_tac      )) with ExprSetTac_1         [A                     , String, Droid_1, Droid_2] with CardSet
  lazy val primaryFunction_  = new Droid_1[A                     , String](dataModel.add(primaryFunction_tac)) with ExprOneTac_1_String  [A                     , String, Droid_1, Droid_2] with CardOne

  override protected def _aggrInt   (kw: Kw                    ) = new Droid_1[Int   , Int   ](toInt    (dataModel, kw             )) with SortAttrs_1[Int   , Int   , Droid_1]
  override protected def _aggrT     (kw: Kw                    ) = new Droid_1[t     , t     ](asIs     (dataModel, kw             )) with SortAttrs_1[t     , t     , Droid_1]
  override protected def _aggrDouble(kw: Kw                    ) = new Droid_1[Double, Double](toDouble (dataModel, kw             )) with SortAttrs_1[Double, Double, Droid_1]
  override protected def _aggrSet   (kw: Kw, n: Option[Int]    ) = new Droid_1[Set[t], t     ](asIs     (dataModel, kw, n          ))
  override protected def _aggrDist  (kw: Kw                    ) = new Droid_1[Set[A], t     ](asIs     (dataModel, kw             ))
  override protected def _exprOneMan(op: Op, vs: Seq[t], binding: Boolean) = new Droid_1[A     , t     ](addOne   (dataModel, op, vs, binding)) with SortAttrs_1[A     , t     , Droid_1] with CardOne
  override protected def _exprOneOpt(op: Op, v : Option[t]     ) = new Droid_1[A     , t     ](addOneOpt(dataModel, op, v          )) with SortAttrs_1[A     , t     , Droid_1]
  override protected def _exprOneTac(op: Op, vs: Seq[t], binding: Boolean) = new Droid_1[A     , t     ](addOne   (dataModel, op, vs, binding)) with CardOne
  override protected def _exprSet   (op: Op, vs: Set[t]        ) = new Droid_1[A     , t     ](addSet   (dataModel, op, vs         )) with CardSet
  override protected def _exprSetOpt(op: Op, vs: Option[Set[t]]) = new Droid_1[A     , t     ](addSetOpt(dataModel, op, vs         ))
  override protected def _sort      (sort: String              ) = new Droid_1[A     , t     ](addSort  (dataModel, sort           ))

  override protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] & CardOne) = new Droid_1[A,    t](filterAttr(dataModel, op, a)) with SortAttrs_1[A,    t, Droid_1]
  override protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[A, t, ns1, ns2]          ) = new Droid_2[A, A, t](filterAttr(dataModel, op, a)) with SortAttrs_2[A, A, t, Droid_2]
  override protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]          ) = new Droid_1[A,    t](filterAttr(dataModel, op, a))
  override protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]          ) = new Droid_2[A, X, t](filterAttr(dataModel, op, a))
}

class Droid_1_refs[A, t](override val dataModel: DataModel) extends Molecule_01[A] {
  trait NestedInit extends NestedOp_1[A] with Nested_1[A] { self: Molecule =>
    override protected def _nestedMan[NestedTpl](nestedDataModel: DataModel): NestedInit_01[A, NestedTpl] =
      new NestedInit_01(DataModel(
        self.dataModel.elements.init :+ Nested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes,
        binds = self.dataModel.binds + nestedDataModel.binds
      ))

    override protected def _nestedOpt[NestedTpl](nestedDataModel: DataModel): NestedInit_01[A, NestedTpl] =
      new NestedInit_01(DataModel(
        self.dataModel.elements.init :+ OptNested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes,
        binds = self.dataModel.binds + nestedDataModel.binds
      ))
  }

  object Friends extends Character_1[A, t](dataModel.add(ast.Ref("Droid", "friends", "Character", CardSet, false, List(1, 6, 0)))) with NestedInit
}


class Droid_2[A, B, t](override val dataModel: DataModel) extends Droid_2_refs[A, B, t](dataModel) with Droid_base with ModelOps_2[A, B, t, Droid_2, Droid_3] {
  lazy val id                = new Droid_3[A, B, Long               , Long  ](dataModel.add(id_man             )) with ExprOneMan_3         [A, B, Long               , Long  , Droid_3, Droid_4] with CardOne
  lazy val name              = new Droid_3[A, B, String             , String](dataModel.add(name_man           )) with ExprOneMan_3_String  [A, B, String             , String, Droid_3, Droid_4] with CardOne
  lazy val friends           = new Droid_3[A, B, Set[Long]          , Long  ](dataModel.add(friends_man        )) with ExprSetMan_3         [A, B, Set[Long]          , Long  , Droid_3, Droid_4] with CardSet
  lazy val appearsIn         = new Droid_3[A, B, Set[String]        , String](dataModel.add(appearsIn_man      )) with ExprSetMan_3         [A, B, Set[String]        , String, Droid_3, Droid_4] with CardSet
  lazy val primaryFunction   = new Droid_3[A, B, String             , String](dataModel.add(primaryFunction_man)) with ExprOneMan_3_String  [A, B, String             , String, Droid_3, Droid_4] with CardOne

  lazy val name_?            = new Droid_3[A, B, Option[String]     , String](dataModel.add(name_opt           )) with ExprOneOpt_3         [A, B, Option[String]     , String, Droid_3, Droid_4] with CardOne
  lazy val friends_?         = new Droid_3[A, B, Option[Set[Long]]  , Long  ](dataModel.add(friends_opt        )) with ExprSetOpt_3         [A, B, Option[Set[Long]]  , Long  , Droid_3, Droid_4] with CardSet
  lazy val appearsIn_?       = new Droid_3[A, B, Option[Set[String]], String](dataModel.add(appearsIn_opt      )) with ExprSetOpt_3         [A, B, Option[Set[String]], String, Droid_3, Droid_4] with CardSet
  lazy val primaryFunction_? = new Droid_3[A, B, Option[String]     , String](dataModel.add(primaryFunction_opt)) with ExprOneOpt_3         [A, B, Option[String]     , String, Droid_3, Droid_4] with CardOne

  lazy val id_               = new Droid_2[A, B                     , Long  ](dataModel.add(id_tac             )) with ExprOneTac_2         [A, B                     , Long  , Droid_2, Droid_3] with CardOne
  lazy val name_             = new Droid_2[A, B                     , String](dataModel.add(name_tac           )) with ExprOneTac_2_String  [A, B                     , String, Droid_2, Droid_3] with CardOne
  lazy val friends_          = new Droid_2[A, B                     , Long  ](dataModel.add(friends_tac        )) with ExprSetTac_2         [A, B                     , Long  , Droid_2, Droid_3] with CardSet
  lazy val appearsIn_        = new Droid_2[A, B                     , String](dataModel.add(appearsIn_tac      )) with ExprSetTac_2         [A, B                     , String, Droid_2, Droid_3] with CardSet
  lazy val primaryFunction_  = new Droid_2[A, B                     , String](dataModel.add(primaryFunction_tac)) with ExprOneTac_2_String  [A, B                     , String, Droid_2, Droid_3] with CardOne

  override protected def _aggrInt   (kw: Kw                    ) = new Droid_2[A, Int   , Int   ](toInt    (dataModel, kw             )) with SortAttrs_2[A, Int   , Int   , Droid_2]
  override protected def _aggrT     (kw: Kw                    ) = new Droid_2[A, t     , t     ](asIs     (dataModel, kw             )) with SortAttrs_2[A, t     , t     , Droid_2]
  override protected def _aggrDouble(kw: Kw                    ) = new Droid_2[A, Double, Double](toDouble (dataModel, kw             )) with SortAttrs_2[A, Double, Double, Droid_2]
  override protected def _aggrSet   (kw: Kw, n: Option[Int]    ) = new Droid_2[A, Set[t], t     ](asIs     (dataModel, kw, n          ))
  override protected def _aggrDist  (kw: Kw                    ) = new Droid_2[A, Set[B], t     ](asIs     (dataModel, kw             ))
  override protected def _exprOneMan(op: Op, vs: Seq[t], binding: Boolean) = new Droid_2[A, B     , t     ](addOne   (dataModel, op, vs, binding)) with SortAttrs_2[A, B     , t     , Droid_2] with CardOne
  override protected def _exprOneOpt(op: Op, v : Option[t]     ) = new Droid_2[A, B     , t     ](addOneOpt(dataModel, op, v          )) with SortAttrs_2[A, B     , t     , Droid_2]
  override protected def _exprOneTac(op: Op, vs: Seq[t], binding: Boolean) = new Droid_2[A, B     , t     ](addOne   (dataModel, op, vs, binding)) with CardOne
  override protected def _exprSet   (op: Op, vs: Set[t]        ) = new Droid_2[A, B     , t     ](addSet   (dataModel, op, vs         )) with CardSet
  override protected def _exprSetOpt(op: Op, vs: Option[Set[t]]) = new Droid_2[A, B     , t     ](addSetOpt(dataModel, op, vs         ))
  override protected def _sort      (sort: String              ) = new Droid_2[A, B     , t     ](addSort  (dataModel, sort           ))

  override protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] & CardOne) = new Droid_2[A, B,    t](filterAttr(dataModel, op, a)) with SortAttrs_2[A, B,    t, Droid_2]
  override protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[B, t, ns1, ns2]          ) = new Droid_3[A, B, B, t](filterAttr(dataModel, op, a)) with SortAttrs_3[A, B, B, t, Droid_3]
  override protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]          ) = new Droid_2[A, B,    t](filterAttr(dataModel, op, a))
  override protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]          ) = new Droid_3[A, B, X, t](filterAttr(dataModel, op, a))
}

class Droid_2_refs[A, B, t](override val dataModel: DataModel) extends Molecule_02[A, B] {
  trait NestedInit extends NestedOp_2[A, B] with Nested_2[A, B] { self: Molecule =>
    override protected def _nestedMan[NestedTpl](nestedDataModel: DataModel): NestedInit_02[A, B, NestedTpl] =
      new NestedInit_02(DataModel(
        self.dataModel.elements.init :+ Nested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes,
        binds = self.dataModel.binds + nestedDataModel.binds
      ))

    override protected def _nestedOpt[NestedTpl](nestedDataModel: DataModel): NestedInit_02[A, B, NestedTpl] =
      new NestedInit_02(DataModel(
        self.dataModel.elements.init :+ OptNested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes,
        binds = self.dataModel.binds + nestedDataModel.binds
      ))
  }

  object Friends extends Character_2[A, B, t](dataModel.add(ast.Ref("Droid", "friends", "Character", CardSet, false, List(1, 6, 0)))) with NestedInit
}


class Droid_3[A, B, C, t](override val dataModel: DataModel) extends Droid_3_refs[A, B, C, t](dataModel) with Droid_base with ModelOps_3[A, B, C, t, Droid_3, Droid_4] {
  lazy val id                = new Droid_4[A, B, C, Long               , Long  ](dataModel.add(id_man             )) with ExprOneMan_4         [A, B, C, Long               , Long  , Droid_4, Droid_5] with CardOne
  lazy val name              = new Droid_4[A, B, C, String             , String](dataModel.add(name_man           )) with ExprOneMan_4_String  [A, B, C, String             , String, Droid_4, Droid_5] with CardOne
  lazy val friends           = new Droid_4[A, B, C, Set[Long]          , Long  ](dataModel.add(friends_man        )) with ExprSetMan_4         [A, B, C, Set[Long]          , Long  , Droid_4, Droid_5] with CardSet
  lazy val appearsIn         = new Droid_4[A, B, C, Set[String]        , String](dataModel.add(appearsIn_man      )) with ExprSetMan_4         [A, B, C, Set[String]        , String, Droid_4, Droid_5] with CardSet
  lazy val primaryFunction   = new Droid_4[A, B, C, String             , String](dataModel.add(primaryFunction_man)) with ExprOneMan_4_String  [A, B, C, String             , String, Droid_4, Droid_5] with CardOne

  lazy val name_?            = new Droid_4[A, B, C, Option[String]     , String](dataModel.add(name_opt           )) with ExprOneOpt_4         [A, B, C, Option[String]     , String, Droid_4, Droid_5] with CardOne
  lazy val friends_?         = new Droid_4[A, B, C, Option[Set[Long]]  , Long  ](dataModel.add(friends_opt        )) with ExprSetOpt_4         [A, B, C, Option[Set[Long]]  , Long  , Droid_4, Droid_5] with CardSet
  lazy val appearsIn_?       = new Droid_4[A, B, C, Option[Set[String]], String](dataModel.add(appearsIn_opt      )) with ExprSetOpt_4         [A, B, C, Option[Set[String]], String, Droid_4, Droid_5] with CardSet
  lazy val primaryFunction_? = new Droid_4[A, B, C, Option[String]     , String](dataModel.add(primaryFunction_opt)) with ExprOneOpt_4         [A, B, C, Option[String]     , String, Droid_4, Droid_5] with CardOne

  lazy val id_               = new Droid_3[A, B, C                     , Long  ](dataModel.add(id_tac             )) with ExprOneTac_3         [A, B, C                     , Long  , Droid_3, Droid_4] with CardOne
  lazy val name_             = new Droid_3[A, B, C                     , String](dataModel.add(name_tac           )) with ExprOneTac_3_String  [A, B, C                     , String, Droid_3, Droid_4] with CardOne
  lazy val friends_          = new Droid_3[A, B, C                     , Long  ](dataModel.add(friends_tac        )) with ExprSetTac_3         [A, B, C                     , Long  , Droid_3, Droid_4] with CardSet
  lazy val appearsIn_        = new Droid_3[A, B, C                     , String](dataModel.add(appearsIn_tac      )) with ExprSetTac_3         [A, B, C                     , String, Droid_3, Droid_4] with CardSet
  lazy val primaryFunction_  = new Droid_3[A, B, C                     , String](dataModel.add(primaryFunction_tac)) with ExprOneTac_3_String  [A, B, C                     , String, Droid_3, Droid_4] with CardOne

  override protected def _aggrInt   (kw: Kw                    ) = new Droid_3[A, B, Int   , Int   ](toInt    (dataModel, kw             )) with SortAttrs_3[A, B, Int   , Int   , Droid_3]
  override protected def _aggrT     (kw: Kw                    ) = new Droid_3[A, B, t     , t     ](asIs     (dataModel, kw             )) with SortAttrs_3[A, B, t     , t     , Droid_3]
  override protected def _aggrDouble(kw: Kw                    ) = new Droid_3[A, B, Double, Double](toDouble (dataModel, kw             )) with SortAttrs_3[A, B, Double, Double, Droid_3]
  override protected def _aggrSet   (kw: Kw, n: Option[Int]    ) = new Droid_3[A, B, Set[t], t     ](asIs     (dataModel, kw, n          ))
  override protected def _aggrDist  (kw: Kw                    ) = new Droid_3[A, B, Set[C], t     ](asIs     (dataModel, kw             ))
  override protected def _exprOneMan(op: Op, vs: Seq[t], binding: Boolean) = new Droid_3[A, B, C     , t     ](addOne   (dataModel, op, vs, binding)) with SortAttrs_3[A, B, C     , t     , Droid_3] with CardOne
  override protected def _exprOneOpt(op: Op, v : Option[t]     ) = new Droid_3[A, B, C     , t     ](addOneOpt(dataModel, op, v          )) with SortAttrs_3[A, B, C     , t     , Droid_3]
  override protected def _exprOneTac(op: Op, vs: Seq[t], binding: Boolean) = new Droid_3[A, B, C     , t     ](addOne   (dataModel, op, vs, binding)) with CardOne
  override protected def _exprSet   (op: Op, vs: Set[t]        ) = new Droid_3[A, B, C     , t     ](addSet   (dataModel, op, vs         )) with CardSet
  override protected def _exprSetOpt(op: Op, vs: Option[Set[t]]) = new Droid_3[A, B, C     , t     ](addSetOpt(dataModel, op, vs         ))
  override protected def _sort      (sort: String              ) = new Droid_3[A, B, C     , t     ](addSort  (dataModel, sort           ))

  override protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] & CardOne) = new Droid_3[A, B, C,    t](filterAttr(dataModel, op, a)) with SortAttrs_3[A, B, C,    t, Droid_3]
  override protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[C, t, ns1, ns2]          ) = new Droid_4[A, B, C, C, t](filterAttr(dataModel, op, a)) with SortAttrs_4[A, B, C, C, t, Droid_4]
  override protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]          ) = new Droid_3[A, B, C,    t](filterAttr(dataModel, op, a))
  override protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]          ) = new Droid_4[A, B, C, X, t](filterAttr(dataModel, op, a))
}

class Droid_3_refs[A, B, C, t](override val dataModel: DataModel) extends Molecule_03[A, B, C] {
  trait NestedInit extends NestedOp_3[A, B, C] with Nested_3[A, B, C] { self: Molecule =>
    override protected def _nestedMan[NestedTpl](nestedDataModel: DataModel): NestedInit_03[A, B, C, NestedTpl] =
      new NestedInit_03(DataModel(
        self.dataModel.elements.init :+ Nested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes,
        binds = self.dataModel.binds + nestedDataModel.binds
      ))

    override protected def _nestedOpt[NestedTpl](nestedDataModel: DataModel): NestedInit_03[A, B, C, NestedTpl] =
      new NestedInit_03(DataModel(
        self.dataModel.elements.init :+ OptNested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes,
        binds = self.dataModel.binds + nestedDataModel.binds
      ))
  }

  object Friends extends Character_3[A, B, C, t](dataModel.add(ast.Ref("Droid", "friends", "Character", CardSet, false, List(1, 6, 0)))) with NestedInit
}


class Droid_4[A, B, C, D, t](override val dataModel: DataModel) extends Droid_4_refs[A, B, C, D, t](dataModel) with Droid_base with ModelOps_4[A, B, C, D, t, Droid_4, Droid_5] {
  lazy val id                = new Droid_5[A, B, C, D, Long               , Long  ](dataModel.add(id_man             )) with ExprOneMan_5         [A, B, C, D, Long               , Long  , Droid_5, Droid_6] with CardOne
  lazy val name              = new Droid_5[A, B, C, D, String             , String](dataModel.add(name_man           )) with ExprOneMan_5_String  [A, B, C, D, String             , String, Droid_5, Droid_6] with CardOne
  lazy val friends           = new Droid_5[A, B, C, D, Set[Long]          , Long  ](dataModel.add(friends_man        )) with ExprSetMan_5         [A, B, C, D, Set[Long]          , Long  , Droid_5, Droid_6] with CardSet
  lazy val appearsIn         = new Droid_5[A, B, C, D, Set[String]        , String](dataModel.add(appearsIn_man      )) with ExprSetMan_5         [A, B, C, D, Set[String]        , String, Droid_5, Droid_6] with CardSet
  lazy val primaryFunction   = new Droid_5[A, B, C, D, String             , String](dataModel.add(primaryFunction_man)) with ExprOneMan_5_String  [A, B, C, D, String             , String, Droid_5, Droid_6] with CardOne

  lazy val name_?            = new Droid_5[A, B, C, D, Option[String]     , String](dataModel.add(name_opt           )) with ExprOneOpt_5         [A, B, C, D, Option[String]     , String, Droid_5, Droid_6] with CardOne
  lazy val friends_?         = new Droid_5[A, B, C, D, Option[Set[Long]]  , Long  ](dataModel.add(friends_opt        )) with ExprSetOpt_5         [A, B, C, D, Option[Set[Long]]  , Long  , Droid_5, Droid_6] with CardSet
  lazy val appearsIn_?       = new Droid_5[A, B, C, D, Option[Set[String]], String](dataModel.add(appearsIn_opt      )) with ExprSetOpt_5         [A, B, C, D, Option[Set[String]], String, Droid_5, Droid_6] with CardSet
  lazy val primaryFunction_? = new Droid_5[A, B, C, D, Option[String]     , String](dataModel.add(primaryFunction_opt)) with ExprOneOpt_5         [A, B, C, D, Option[String]     , String, Droid_5, Droid_6] with CardOne

  lazy val id_               = new Droid_4[A, B, C, D                     , Long  ](dataModel.add(id_tac             )) with ExprOneTac_4         [A, B, C, D                     , Long  , Droid_4, Droid_5] with CardOne
  lazy val name_             = new Droid_4[A, B, C, D                     , String](dataModel.add(name_tac           )) with ExprOneTac_4_String  [A, B, C, D                     , String, Droid_4, Droid_5] with CardOne
  lazy val friends_          = new Droid_4[A, B, C, D                     , Long  ](dataModel.add(friends_tac        )) with ExprSetTac_4         [A, B, C, D                     , Long  , Droid_4, Droid_5] with CardSet
  lazy val appearsIn_        = new Droid_4[A, B, C, D                     , String](dataModel.add(appearsIn_tac      )) with ExprSetTac_4         [A, B, C, D                     , String, Droid_4, Droid_5] with CardSet
  lazy val primaryFunction_  = new Droid_4[A, B, C, D                     , String](dataModel.add(primaryFunction_tac)) with ExprOneTac_4_String  [A, B, C, D                     , String, Droid_4, Droid_5] with CardOne

  override protected def _aggrInt   (kw: Kw                    ) = new Droid_4[A, B, C, Int   , Int   ](toInt    (dataModel, kw             )) with SortAttrs_4[A, B, C, Int   , Int   , Droid_4]
  override protected def _aggrT     (kw: Kw                    ) = new Droid_4[A, B, C, t     , t     ](asIs     (dataModel, kw             )) with SortAttrs_4[A, B, C, t     , t     , Droid_4]
  override protected def _aggrDouble(kw: Kw                    ) = new Droid_4[A, B, C, Double, Double](toDouble (dataModel, kw             )) with SortAttrs_4[A, B, C, Double, Double, Droid_4]
  override protected def _aggrSet   (kw: Kw, n: Option[Int]    ) = new Droid_4[A, B, C, Set[t], t     ](asIs     (dataModel, kw, n          ))
  override protected def _aggrDist  (kw: Kw                    ) = new Droid_4[A, B, C, Set[D], t     ](asIs     (dataModel, kw             ))
  override protected def _exprOneMan(op: Op, vs: Seq[t], binding: Boolean) = new Droid_4[A, B, C, D     , t     ](addOne   (dataModel, op, vs, binding)) with SortAttrs_4[A, B, C, D     , t     , Droid_4] with CardOne
  override protected def _exprOneOpt(op: Op, v : Option[t]     ) = new Droid_4[A, B, C, D     , t     ](addOneOpt(dataModel, op, v          )) with SortAttrs_4[A, B, C, D     , t     , Droid_4]
  override protected def _exprOneTac(op: Op, vs: Seq[t], binding: Boolean) = new Droid_4[A, B, C, D     , t     ](addOne   (dataModel, op, vs, binding)) with CardOne
  override protected def _exprSet   (op: Op, vs: Set[t]        ) = new Droid_4[A, B, C, D     , t     ](addSet   (dataModel, op, vs         )) with CardSet
  override protected def _exprSetOpt(op: Op, vs: Option[Set[t]]) = new Droid_4[A, B, C, D     , t     ](addSetOpt(dataModel, op, vs         ))
  override protected def _sort      (sort: String              ) = new Droid_4[A, B, C, D     , t     ](addSort  (dataModel, sort           ))

  override protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] & CardOne) = new Droid_4[A, B, C, D,    t](filterAttr(dataModel, op, a)) with SortAttrs_4[A, B, C, D,    t, Droid_4]
  override protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[D, t, ns1, ns2]          ) = new Droid_5[A, B, C, D, D, t](filterAttr(dataModel, op, a)) with SortAttrs_5[A, B, C, D, D, t, Droid_5]
  override protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]          ) = new Droid_4[A, B, C, D,    t](filterAttr(dataModel, op, a))
  override protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]          ) = new Droid_5[A, B, C, D, X, t](filterAttr(dataModel, op, a))
}

class Droid_4_refs[A, B, C, D, t](override val dataModel: DataModel) extends Molecule_04[A, B, C, D] {
  trait NestedInit extends NestedOp_4[A, B, C, D] with Nested_4[A, B, C, D] { self: Molecule =>
    override protected def _nestedMan[NestedTpl](nestedDataModel: DataModel): NestedInit_04[A, B, C, D, NestedTpl] =
      new NestedInit_04(DataModel(
        self.dataModel.elements.init :+ Nested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes,
        binds = self.dataModel.binds + nestedDataModel.binds
      ))

    override protected def _nestedOpt[NestedTpl](nestedDataModel: DataModel): NestedInit_04[A, B, C, D, NestedTpl] =
      new NestedInit_04(DataModel(
        self.dataModel.elements.init :+ OptNested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes,
        binds = self.dataModel.binds + nestedDataModel.binds
      ))
  }

  object Friends extends Character_4[A, B, C, D, t](dataModel.add(ast.Ref("Droid", "friends", "Character", CardSet, false, List(1, 6, 0)))) with NestedInit
}


class Droid_5[A, B, C, D, E, t](override val dataModel: DataModel) extends Droid_5_refs[A, B, C, D, E, t](dataModel) with Droid_base with ModelOps_5[A, B, C, D, E, t, Droid_5, Droid_6] {
  lazy val id                = new Droid_6[A, B, C, D, E, Long               , Long  ](dataModel.add(id_man             )) with ExprOneMan_6         [A, B, C, D, E, Long               , Long  , Droid_6, X8] with CardOne
  lazy val name              = new Droid_6[A, B, C, D, E, String             , String](dataModel.add(name_man           )) with ExprOneMan_6_String  [A, B, C, D, E, String             , String, Droid_6, X8] with CardOne
  lazy val friends           = new Droid_6[A, B, C, D, E, Set[Long]          , Long  ](dataModel.add(friends_man        )) with ExprSetMan_6         [A, B, C, D, E, Set[Long]          , Long  , Droid_6, X8] with CardSet
  lazy val appearsIn         = new Droid_6[A, B, C, D, E, Set[String]        , String](dataModel.add(appearsIn_man      )) with ExprSetMan_6         [A, B, C, D, E, Set[String]        , String, Droid_6, X8] with CardSet
  lazy val primaryFunction   = new Droid_6[A, B, C, D, E, String             , String](dataModel.add(primaryFunction_man)) with ExprOneMan_6_String  [A, B, C, D, E, String             , String, Droid_6, X8] with CardOne

  lazy val name_?            = new Droid_6[A, B, C, D, E, Option[String]     , String](dataModel.add(name_opt           )) with ExprOneOpt_6         [A, B, C, D, E, Option[String]     , String, Droid_6, X8] with CardOne
  lazy val friends_?         = new Droid_6[A, B, C, D, E, Option[Set[Long]]  , Long  ](dataModel.add(friends_opt        )) with ExprSetOpt_6         [A, B, C, D, E, Option[Set[Long]]  , Long  , Droid_6, X8] with CardSet
  lazy val appearsIn_?       = new Droid_6[A, B, C, D, E, Option[Set[String]], String](dataModel.add(appearsIn_opt      )) with ExprSetOpt_6         [A, B, C, D, E, Option[Set[String]], String, Droid_6, X8] with CardSet
  lazy val primaryFunction_? = new Droid_6[A, B, C, D, E, Option[String]     , String](dataModel.add(primaryFunction_opt)) with ExprOneOpt_6         [A, B, C, D, E, Option[String]     , String, Droid_6, X8] with CardOne

  lazy val id_               = new Droid_5[A, B, C, D, E                     , Long  ](dataModel.add(id_tac             )) with ExprOneTac_5         [A, B, C, D, E                     , Long  , Droid_5, Droid_6] with CardOne
  lazy val name_             = new Droid_5[A, B, C, D, E                     , String](dataModel.add(name_tac           )) with ExprOneTac_5_String  [A, B, C, D, E                     , String, Droid_5, Droid_6] with CardOne
  lazy val friends_          = new Droid_5[A, B, C, D, E                     , Long  ](dataModel.add(friends_tac        )) with ExprSetTac_5         [A, B, C, D, E                     , Long  , Droid_5, Droid_6] with CardSet
  lazy val appearsIn_        = new Droid_5[A, B, C, D, E                     , String](dataModel.add(appearsIn_tac      )) with ExprSetTac_5         [A, B, C, D, E                     , String, Droid_5, Droid_6] with CardSet
  lazy val primaryFunction_  = new Droid_5[A, B, C, D, E                     , String](dataModel.add(primaryFunction_tac)) with ExprOneTac_5_String  [A, B, C, D, E                     , String, Droid_5, Droid_6] with CardOne

  override protected def _aggrInt   (kw: Kw                    ) = new Droid_5[A, B, C, D, Int   , Int   ](toInt    (dataModel, kw             )) with SortAttrs_5[A, B, C, D, Int   , Int   , Droid_5]
  override protected def _aggrT     (kw: Kw                    ) = new Droid_5[A, B, C, D, t     , t     ](asIs     (dataModel, kw             )) with SortAttrs_5[A, B, C, D, t     , t     , Droid_5]
  override protected def _aggrDouble(kw: Kw                    ) = new Droid_5[A, B, C, D, Double, Double](toDouble (dataModel, kw             )) with SortAttrs_5[A, B, C, D, Double, Double, Droid_5]
  override protected def _aggrSet   (kw: Kw, n: Option[Int]    ) = new Droid_5[A, B, C, D, Set[t], t     ](asIs     (dataModel, kw, n          ))
  override protected def _aggrDist  (kw: Kw                    ) = new Droid_5[A, B, C, D, Set[E], t     ](asIs     (dataModel, kw             ))
  override protected def _exprOneMan(op: Op, vs: Seq[t], binding: Boolean) = new Droid_5[A, B, C, D, E     , t     ](addOne   (dataModel, op, vs, binding)) with SortAttrs_5[A, B, C, D, E     , t     , Droid_5] with CardOne
  override protected def _exprOneOpt(op: Op, v : Option[t]     ) = new Droid_5[A, B, C, D, E     , t     ](addOneOpt(dataModel, op, v          )) with SortAttrs_5[A, B, C, D, E     , t     , Droid_5]
  override protected def _exprOneTac(op: Op, vs: Seq[t], binding: Boolean) = new Droid_5[A, B, C, D, E     , t     ](addOne   (dataModel, op, vs, binding)) with CardOne
  override protected def _exprSet   (op: Op, vs: Set[t]        ) = new Droid_5[A, B, C, D, E     , t     ](addSet   (dataModel, op, vs         )) with CardSet
  override protected def _exprSetOpt(op: Op, vs: Option[Set[t]]) = new Droid_5[A, B, C, D, E     , t     ](addSetOpt(dataModel, op, vs         ))
  override protected def _sort      (sort: String              ) = new Droid_5[A, B, C, D, E     , t     ](addSort  (dataModel, sort           ))

  override protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] & CardOne) = new Droid_5[A, B, C, D, E,    t](filterAttr(dataModel, op, a)) with SortAttrs_5[A, B, C, D, E,    t, Droid_5]
  override protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[E, t, ns1, ns2]          ) = new Droid_6[A, B, C, D, E, E, t](filterAttr(dataModel, op, a)) with SortAttrs_6[A, B, C, D, E, E, t, Droid_6]
  override protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]          ) = new Droid_5[A, B, C, D, E,    t](filterAttr(dataModel, op, a))
  override protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]          ) = new Droid_6[A, B, C, D, E, X, t](filterAttr(dataModel, op, a))
}

class Droid_5_refs[A, B, C, D, E, t](override val dataModel: DataModel) extends Molecule_05[A, B, C, D, E] {
  trait NestedInit extends NestedOp_5[A, B, C, D, E] with Nested_5[A, B, C, D, E] { self: Molecule =>
    override protected def _nestedMan[NestedTpl](nestedDataModel: DataModel): NestedInit_05[A, B, C, D, E, NestedTpl] =
      new NestedInit_05(DataModel(
        self.dataModel.elements.init :+ Nested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes,
        binds = self.dataModel.binds + nestedDataModel.binds
      ))

    override protected def _nestedOpt[NestedTpl](nestedDataModel: DataModel): NestedInit_05[A, B, C, D, E, NestedTpl] =
      new NestedInit_05(DataModel(
        self.dataModel.elements.init :+ OptNested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes,
        binds = self.dataModel.binds + nestedDataModel.binds
      ))
  }

  object Friends extends Character_5[A, B, C, D, E, t](dataModel.add(ast.Ref("Droid", "friends", "Character", CardSet, false, List(1, 6, 0)))) with NestedInit
}


class Droid_6[A, B, C, D, E, F, t](override val dataModel: DataModel) extends Droid_6_refs[A, B, C, D, E, F, t](dataModel) with Droid_base with ModelOps_6[A, B, C, D, E, F, t, Droid_6, X8] {
  lazy val id_               = new Droid_6[A, B, C, D, E, F, Long  ](dataModel.add(id_tac             )) with ExprOneTac_6         [A, B, C, D, E, F, Long  , Droid_6, X8] with CardOne
  lazy val name_             = new Droid_6[A, B, C, D, E, F, String](dataModel.add(name_tac           )) with ExprOneTac_6_String  [A, B, C, D, E, F, String, Droid_6, X8] with CardOne
  lazy val friends_          = new Droid_6[A, B, C, D, E, F, Long  ](dataModel.add(friends_tac        )) with ExprSetTac_6         [A, B, C, D, E, F, Long  , Droid_6, X8] with CardSet
  lazy val appearsIn_        = new Droid_6[A, B, C, D, E, F, String](dataModel.add(appearsIn_tac      )) with ExprSetTac_6         [A, B, C, D, E, F, String, Droid_6, X8] with CardSet
  lazy val primaryFunction_  = new Droid_6[A, B, C, D, E, F, String](dataModel.add(primaryFunction_tac)) with ExprOneTac_6_String  [A, B, C, D, E, F, String, Droid_6, X8] with CardOne

  override protected def _aggrInt   (kw: Kw                    ) = new Droid_6[A, B, C, D, E, Int   , Int   ](toInt    (dataModel, kw             )) with SortAttrs_6[A, B, C, D, E, Int   , Int   , Droid_6]
  override protected def _aggrT     (kw: Kw                    ) = new Droid_6[A, B, C, D, E, t     , t     ](asIs     (dataModel, kw             )) with SortAttrs_6[A, B, C, D, E, t     , t     , Droid_6]
  override protected def _aggrDouble(kw: Kw                    ) = new Droid_6[A, B, C, D, E, Double, Double](toDouble (dataModel, kw             )) with SortAttrs_6[A, B, C, D, E, Double, Double, Droid_6]
  override protected def _aggrSet   (kw: Kw, n: Option[Int]    ) = new Droid_6[A, B, C, D, E, Set[t], t     ](asIs     (dataModel, kw, n          ))
  override protected def _aggrDist  (kw: Kw                    ) = new Droid_6[A, B, C, D, E, Set[F], t     ](asIs     (dataModel, kw             ))
  override protected def _exprOneMan(op: Op, vs: Seq[t], binding: Boolean) = new Droid_6[A, B, C, D, E, F     , t     ](addOne   (dataModel, op, vs, binding)) with SortAttrs_6[A, B, C, D, E, F     , t     , Droid_6] with CardOne
  override protected def _exprOneOpt(op: Op, v : Option[t]     ) = new Droid_6[A, B, C, D, E, F     , t     ](addOneOpt(dataModel, op, v          )) with SortAttrs_6[A, B, C, D, E, F     , t     , Droid_6]
  override protected def _exprOneTac(op: Op, vs: Seq[t], binding: Boolean) = new Droid_6[A, B, C, D, E, F     , t     ](addOne   (dataModel, op, vs, binding)) with CardOne
  override protected def _exprSet   (op: Op, vs: Set[t]        ) = new Droid_6[A, B, C, D, E, F     , t     ](addSet   (dataModel, op, vs         )) with CardSet
  override protected def _exprSetOpt(op: Op, vs: Option[Set[t]]) = new Droid_6[A, B, C, D, E, F     , t     ](addSetOpt(dataModel, op, vs         ))
  override protected def _sort      (sort: String              ) = new Droid_6[A, B, C, D, E, F     , t     ](addSort  (dataModel, sort           ))

  override protected def _attrSortTac[ns1[_], ns2[_, _]](op: Op, a: ModelOps_0[t, ns1, ns2] & CardOne) = new Droid_6[A, B, C, D, E, F, t](filterAttr(dataModel, op, a)) with SortAttrs_6[A, B, C, D, E, F, t, Droid_6]
  override protected def _attrTac    [ns1[_], ns2[_, _]](op: Op, a: ModelOps_0[t, ns1, ns2]          ) = new Droid_6[A, B, C, D, E, F, t](filterAttr(dataModel, op, a))
}

class Droid_6_refs[A, B, C, D, E, F, t](override val dataModel: DataModel) extends Molecule_06[A, B, C, D, E, F] {
  object Friends extends Character_6[A, B, C, D, E, F, t](dataModel.add(ast.Ref("Droid", "friends", "Character", CardSet, false, List(1, 6, 0))))
}

