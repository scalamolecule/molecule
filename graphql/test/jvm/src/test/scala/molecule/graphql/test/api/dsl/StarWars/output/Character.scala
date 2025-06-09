/*
* AUTO-GENERATED Molecule DSL boilerplate code for entity `Character`
*
* To change:
* 1. Edit domain structure in molecule.graphql.client.dataModel.dsl.Starwars
* 2. `sbt compile -Dmolecule=true`
*/
package molecule.graphql.test.api.dsl.StarWars.output

import molecule.base.ast.{CardOne, CardSet}
import molecule.core.ast
import molecule.core.ast.*


private[StarWars] trait Character_base {
  protected lazy val id_man       : AttrOneManID     = AttrOneManID    ("Character", "id"       )
  protected lazy val name_man     : AttrOneManString = AttrOneManString("Character", "name"     )
  protected lazy val friends_man  : AttrSetManID     = AttrSetManID    ("Character", "friends"  , ref = Some("Character"))
  protected lazy val appearsIn_man: AttrSetManString = AttrSetManString("Character", "appearsIn", validator = Some(validation_appearsIn))

  protected lazy val name_opt     : AttrOneOptString = AttrOneOptString("Character", "name"     )
  protected lazy val friends_opt  : AttrSetOptID     = AttrSetOptID    ("Character", "friends"  , ref = Some("Character"))
  protected lazy val appearsIn_opt: AttrSetOptString = AttrSetOptString("Character", "appearsIn", validator = Some(validation_appearsIn))

  protected lazy val id_tac       : AttrOneTacID     = AttrOneTacID    ("Character", "id"       )
  protected lazy val name_tac     : AttrOneTacString = AttrOneTacString("Character", "name"     )
  protected lazy val friends_tac  : AttrSetTacID     = AttrSetTacID    ("Character", "friends"  , ref = Some("Character"))
  protected lazy val appearsIn_tac: AttrSetTacString = AttrSetTacString("Character", "appearsIn", validator = Some(validation_appearsIn))

  private lazy val validation_appearsIn = new ValidateString {
    override def validate(v: String): Seq[String] = {
      val ok: String => Boolean = v => Seq("NEWHOPE", "EMPIRE", "JEDI").contains(v)
      if (ok(v)) Nil else Seq(s"""Value `$v` is not one of the allowed values in Seq("NEWHOPE", "EMPIRE", "JEDI")""")
    }
  }
}

//private[dsl] object Character extends Character_0[Nothing](DataModel(Nil, firstEntityIndex = 0)) with OptEntityOp_0[Character_1_refs] with OptEntity_0[Character_1_refs] {
//private[dsl] object Character extends Character_0[Nothing](DataModel(Nil, firstEntityIndex = 0)) {
  //  final def apply(id : Long, ids: Long*) = new Character_0[String](DataModel(List(AttrOneTacID("Character", "id", Eq, id +: ids, coord = List(0, 0))), firstEntityIndex = 0))
  //  final def apply(ids: Iterable[Long])   = new Character_0[String](DataModel(List(AttrOneTacID("Character", "id", Eq, ids.toSeq, coord = List(0, 0))), firstEntityIndex = 0))

  //  final def apply(id: String, ids: String*) = new Character_0[String](DataModel(List(AttrOneTacString("Character", "id", Eq, id +: ids, coord = List(0, 0))), firstEntityIndex = 0))
  //  final def apply(ids: Iterable[String]) = new Character_0[String](DataModel(List(AttrOneTacString("Character", "id", Eq, ids.toSeq, coord = List(0, 0))), firstEntityIndex = 0))

  //  override protected def _optEntity[OptEntityTpl](attrs: List[Attr]): Character_1_refs[Option[OptEntityTpl], Any] =
  //    new Character_1_refs[Option[OptEntityTpl], Any](DataModel(List(ast.OptEntity(attrs))))
//}


//private[dsl] class Character_0[t](override val dataModel: DataModel) extends Character_0_refs[t](dataModel) with Character_base with ModelOps_0[t, Character_0, Character_1] {
private[dsl] class Character_0[t](dataModel: DataModel) extends Character_0_refs[t](dataModel) with Character_base {
  lazy val id          = new Character_1[String             , String](dataModel.add(id_man       )) with CardOne
  lazy val name        = new Character_1[String             , String](dataModel.add(name_man     )) with CardOne
  lazy val friends     = new Character_1[Set[Long]          , Long  ](dataModel.add(friends_man  )) with CardSet
  lazy val appearsIn   = new Character_1[Set[String]        , String](dataModel.add(appearsIn_man)) with CardSet

  lazy val name_?      = new Character_1[Option[String]     , String](dataModel.add(name_opt     )) with CardOne
  lazy val friends_?   = new Character_1[Option[Set[Long]]  , Long  ](dataModel.add(friends_opt  )) with CardSet
  lazy val appearsIn_? = new Character_1[Option[Set[String]], String](dataModel.add(appearsIn_opt)) with CardSet
}

//private[dsl] class Character_0_refs[t](override val dataModel: DataModel) {
private[dsl] class Character_0_refs[t](dataModel: DataModel) {
//  trait NestedInit extends NestedOp_0 with Nested_0 { self: Molecule =>
//    override protected def _nestedMan[NestedTpl](nestedDataModel: DataModel): NestedInit_00[NestedTpl] =
//      new NestedInit_00(DataModel(
//        self.dataModel.elements.init :+ Nested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
//        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes,
//        binds = self.dataModel.binds + nestedDataModel.binds
//      ))
//
//    override protected def _nestedOpt[NestedTpl](nestedDataModel: DataModel): NestedInit_00[NestedTpl] =
//      new NestedInit_00(DataModel(
//        self.dataModel.elements.init :+ OptNested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
//        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes,
//        binds = self.dataModel.binds + nestedDataModel.binds
//      ))
//  }

  object Friends extends Character_0[t](dataModel.add(ast.Ref("Character", "friends", "Character", CardSet, false, List(0, 2, 0)))) //with NestedInit

  object _Droid     extends Droid_0    [t](dataModel.add(ast.BackRef("Droid", "Character", List(1, 0))))
  object _Human     extends Human_0    [t](dataModel.add(ast.BackRef("Human", "Character", List(2, 0))))
}


//private[dsl] class Character_1[A, t](override val dataModel: DataModel) extends Character_1_refs[A, t](dataModel) with Character_base with ModelOps_1[A, t, Character_1, Character_2] {
private[dsl] class Character_1[A, t](dataModel: DataModel) extends Character_1_refs[A, t](dataModel) with Character_base {
  lazy val id          = new Character_2[A, Long               , Long  ](dataModel.add(id_man       )) with CardOne
  lazy val name        = new Character_2[A, String             , String](dataModel.add(name_man     )) with CardOne
  lazy val friends     = new Character_2[A, Set[Long]          , Long  ](dataModel.add(friends_man  )) with CardSet
  lazy val appearsIn   = new Character_2[A, Set[String]        , String](dataModel.add(appearsIn_man)) with CardSet
  lazy val name_?      = new Character_2[A, Option[String]     , String](dataModel.add(name_opt     )) with CardOne
  lazy val friends_?   = new Character_2[A, Option[Set[Long]]  , Long  ](dataModel.add(friends_opt  )) with CardSet
  lazy val appearsIn_? = new Character_2[A, Option[Set[String]], String](dataModel.add(appearsIn_opt)) with CardSet

//  override protected def _aggrInt   (kw: Kw                    ) = new Character_1[Int   , Int   ](toInt    (dataModel, kw             )) with SortAttrs_1[Int   , Int   , Character_1]
//  override protected def _aggrT     (kw: Kw                    ) = new Character_1[t     , t     ](asIs     (dataModel, kw             )) with SortAttrs_1[t     , t     , Character_1]
//  override protected def _aggrDouble(kw: Kw                    ) = new Character_1[Double, Double](toDouble (dataModel, kw             )) with SortAttrs_1[Double, Double, Character_1]
//  override protected def _aggrSet   (kw: Kw, n: Option[Int]    ) = new Character_1[Set[t], t     ](asIs     (dataModel, kw, n          ))
//  override protected def _aggrDist  (kw: Kw                    ) = new Character_1[Set[A], t     ](asIs     (dataModel, kw             ))
//  override protected def _exprOneMan(op: Op, vs: Seq[t], binding: Boolean) = new Character_1[A     , t     ](addOne   (dataModel, op, vs, binding)) with SortAttrs_1[A     , t     , Character_1] with CardOne
//  override protected def _exprOneOpt(op: Op, v : Option[t]     ) = new Character_1[A     , t     ](addOneOpt(dataModel, op, v          )) with SortAttrs_1[A     , t     , Character_1]
//  override protected def _exprOneTac(op: Op, vs: Seq[t], binding: Boolean) = new Character_1[A     , t     ](addOne   (dataModel, op, vs, binding)) with CardOne
//  override protected def _exprSet   (op: Op, vs: Set[t]        ) = new Character_1[A     , t     ](addSet   (dataModel, op, vs         )) with CardSet
//  override protected def _exprSetOpt(op: Op, vs: Option[Set[t]]) = new Character_1[A     , t     ](addSetOpt(dataModel, op, vs         ))
//  override protected def _sort      (sort: String              ) = new Character_1[A     , t     ](addSort  (dataModel, sort           ))
//
//  override protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] & CardOne) = new Character_1[A,    t](filterAttr(dataModel, op, a)) with SortAttrs_1[A,    t, Character_1]
//  override protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[A, t, ns1, ns2]          ) = new Character_2[A, A, t](filterAttr(dataModel, op, a)) with SortAttrs_2[A, A, t, Character_2]
//  override protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]          ) = new Character_1[A,    t](filterAttr(dataModel, op, a))
//  override protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]          ) = new Character_2[A, X, t](filterAttr(dataModel, op, a))
}

//private[dsl] class Character_1_refs[A, t](override val dataModel: DataModel) extends Molecule_01[A] {
private[dsl] class Character_1_refs[A, t](dataModel: DataModel) {
//  trait NestedInit extends NestedOp_1[A] with Nested_1[A] { self: Molecule =>
//    override protected def _nestedMan[NestedTpl](nestedDataModel: DataModel): NestedInit_01[A, NestedTpl] =
//      new NestedInit_01(DataModel(
//        self.dataModel.elements.init :+ Nested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
//        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes,
//        binds = self.dataModel.binds + nestedDataModel.binds
//      ))
//
//    override protected def _nestedOpt[NestedTpl](nestedDataModel: DataModel): NestedInit_01[A, NestedTpl] =
//      new NestedInit_01(DataModel(
//        self.dataModel.elements.init :+ OptNested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
//        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes,
//        binds = self.dataModel.binds + nestedDataModel.binds
//      ))
//  }

  object Friends extends Character_1[A, t](dataModel.add(ast.Ref("Character", "friends", "Character", CardSet, false, List(0, 2, 0)))) //with NestedInit

  object _Droid     extends Droid_1    [A, t](dataModel.add(ast.BackRef("Droid", "Character", List(1, 0))))
  object _Human     extends Human_1    [A, t](dataModel.add(ast.BackRef("Human", "Character", List(2, 0))))
}


//private[dsl] class Character_2[A, B, t](override val dataModel: DataModel) extends Character_2_refs[A, B, t](dataModel) with Character_base with ModelOps_2[A, B, t, Character_2, Character_3] {
private[dsl] class Character_2[A, B, t](dataModel: DataModel) extends Character_2_refs[A, B, t](dataModel) with Character_base {
  lazy val id          = new Character_3[A, B, Long               , Long  ](dataModel.add(id_man       )) with CardOne
  lazy val name        = new Character_3[A, B, String             , String](dataModel.add(name_man     )) with CardOne
  lazy val friends     = new Character_3[A, B, Set[Long]          , Long  ](dataModel.add(friends_man  )) with CardSet
  lazy val appearsIn   = new Character_3[A, B, Set[String]        , String](dataModel.add(appearsIn_man)) with CardSet
  lazy val name_?      = new Character_3[A, B, Option[String]     , String](dataModel.add(name_opt     )) with CardOne
  lazy val friends_?   = new Character_3[A, B, Option[Set[Long]]  , Long  ](dataModel.add(friends_opt  )) with CardSet
  lazy val appearsIn_? = new Character_3[A, B, Option[Set[String]], String](dataModel.add(appearsIn_opt)) with CardSet
}

//private[dsl] class Character_2_refs[A, B, t](override val dataModel: DataModel) extends Molecule_02[A, B] {
private[dsl] class Character_2_refs[A, B, t](dataModel: DataModel) {
//  trait NestedInit extends NestedOp_2[A, B] with Nested_2[A, B] { self: Molecule =>
//    override protected def _nestedMan[NestedTpl](nestedDataModel: DataModel): NestedInit_02[A, B, NestedTpl] =
//      new NestedInit_02(DataModel(
//        self.dataModel.elements.init :+ Nested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
//        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes,
//        binds = self.dataModel.binds + nestedDataModel.binds
//      ))
//
//    override protected def _nestedOpt[NestedTpl](nestedDataModel: DataModel): NestedInit_02[A, B, NestedTpl] =
//      new NestedInit_02(DataModel(
//        self.dataModel.elements.init :+ OptNested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
//        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes,
//        binds = self.dataModel.binds + nestedDataModel.binds
//      ))
//  }

  object Friends extends Character_2[A, B, t](dataModel.add(ast.Ref("Character", "friends", "Character", CardSet, false, List(0, 2, 0)))) //with NestedInit

  object _Droid     extends Droid_2    [A, B, t](dataModel.add(ast.BackRef("Droid", "Character", List(1, 0))))
  object _Human     extends Human_2    [A, B, t](dataModel.add(ast.BackRef("Human", "Character", List(2, 0))))
}


//private[dsl] class Character_3[A, B, C, t](override val dataModel: DataModel) extends Character_3_refs[A, B, C, t](dataModel) with Character_base with ModelOps_3[A, B, C, t, Character_3, Character_4] {
//private[dsl] class Character_3[A, B, C, t](override val dataModel: DataModel) extends Character_3_refs[A, B, C, t](dataModel) with Character_base {
private[dsl] class Character_3[A, B, C, t](dataModel: DataModel)

