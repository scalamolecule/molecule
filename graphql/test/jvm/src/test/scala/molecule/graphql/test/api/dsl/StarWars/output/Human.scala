/*
* AUTO-GENERATED Molecule DSL boilerplate code for entity `Human`
*
* To change:
* 1. Edit domain structure in molecule.graphql.client.dataModel.dsl.Starwars
* 2. `sbt compile -Dmolecule=true`
*/
package molecule.graphql.test.api.dsl.StarWars.output

import molecule.base.metaModel.{CardOne, CardSet}
import molecule.core.dataModel as _dm
import molecule.core.dataModel.*


trait Human_base {
  protected lazy val id_man        : AttrOneManID     = AttrOneManID    ("Human", "id"        , coord = List(2, 9    ))
  protected lazy val name_man      : AttrOneManString = AttrOneManString("Human", "name"      , coord = List(2, 10   ))
  protected lazy val friends_man   : AttrSetManID     = AttrSetManID    ("Human", "friends"   , coord = List(2, 11, 0), ref = Some("Character"))
  protected lazy val appearsIn_man : AttrSetManString = AttrSetManString("Human", "appearsIn" , coord = List(2, 12   ), validator = Some(validation_appearsIn))
  protected lazy val homePlanet_man: AttrOneManString = AttrOneManString("Human", "homePlanet", coord = List(2, 13   ))

  protected lazy val name_opt      : AttrOneOptString = AttrOneOptString("Human", "name"      , coord = List(2, 10   ))
  protected lazy val friends_opt   : AttrSetOptID     = AttrSetOptID    ("Human", "friends"   , coord = List(2, 11, 0), ref = Some("Character"))
  protected lazy val appearsIn_opt : AttrSetOptString = AttrSetOptString("Human", "appearsIn" , coord = List(2, 12   ), validator = Some(validation_appearsIn))
  protected lazy val homePlanet_opt: AttrOneOptString = AttrOneOptString("Human", "homePlanet", coord = List(2, 13   ))

  private lazy val validation_appearsIn = new ValidateString {
    override def validate(v: String): Seq[String] = {
      val ok: String => Boolean = v => Seq("NEWHOPE", "EMPIRE", "JEDI").contains(v)
      if (ok(v)) Nil else Seq(s"""Value `$v` is not one of the allowed values in Seq("NEWHOPE", "EMPIRE", "JEDI")""")
    }
  }
}

//object Human extends Human_0[Nothing](DataModel(Nil, firstEntityIndex = 2)) with OptEntityOp_0[Human_1_refs] with OptEntity_0[Human_1_refs] {
//object Human extends Human_0[Nothing](DataModel(Nil, firstEntityIndex = 2)) {
////  final def apply(id : String, ids: String*) = new Human_0[String](DataModel(List(AttrOneTacString("Human", "id", Eq, id +: ids, coord = List(2, 9))), firstEntityIndex = 2))
////  final def apply(ids: Iterable[String])   = new Human_0[String](DataModel(List(AttrOneTacString("Human", "id", Eq, ids.toSeq, coord = List(2, 9))), firstEntityIndex = 2))
//
////  override protected def _optEntity[OptEntityTpl](attrs: List[Attr]): Human_1_refs[Option[OptEntityTpl], Any] =
////    new Human_1_refs[Option[OptEntityTpl], Any](DataModel(List(ast.OptEntity(attrs))))
//}


//class Human_0[t](override val dataModel: DataModel) extends Human_0_refs[t](dataModel) with Human_base with ModelOps_0[t, Human_0, Human_1] {
class Human_0[t](dataModel: DataModel) extends Human_0_refs[t](dataModel) with Human_base {
  lazy val id           = new Human_1[String             , String](dataModel.add(id_man        )) with CardOne
  lazy val name         = new Human_1[String             , String](dataModel.add(name_man      )) with CardOne
  lazy val friends      = new Human_1[Set[Long]          , Long  ](dataModel.add(friends_man   )) with CardSet
  lazy val appearsIn    = new Human_1[Set[String]        , String](dataModel.add(appearsIn_man )) with CardSet
  lazy val homePlanet   = new Human_1[String             , String](dataModel.add(homePlanet_man)) with CardOne
  lazy val name_?       = new Human_1[Option[String]     , String](dataModel.add(name_opt      )) with CardOne
  lazy val friends_?    = new Human_1[Option[Set[Long]]  , Long  ](dataModel.add(friends_opt   )) with CardSet
  lazy val appearsIn_?  = new Human_1[Option[Set[String]], String](dataModel.add(appearsIn_opt )) with CardSet
  lazy val homePlanet_? = new Human_1[Option[String]     , String](dataModel.add(homePlanet_opt)) with CardOne
}

//class Human_0_refs[t](override val dataModel: DataModel) extends Molecule_00 {
class Human_0_refs[t](dataModel: DataModel) {
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

  object Friends extends Character_0[t](dataModel.add(_dm.Ref("Human", "friends", "Character", CardSet, false, List(2, 11, 0)))) //with NestedInit
}


//class Human_1[A, t](dataModel: DataModel) extends Human_1_refs[A, t](dataModel) with Human_base with ModelOps_1[A, t, Human_1, Human_2] {
class Human_1[A, t](dataModel: DataModel) extends Human_1_refs[A, t](dataModel) with Human_base {
  lazy val id           = new Human_2[A, Long               , Long  ](dataModel.add(id_man        )) with CardOne
  lazy val name         = new Human_2[A, String             , String](dataModel.add(name_man      )) with CardOne
  lazy val friends      = new Human_2[A, Set[Long]          , Long  ](dataModel.add(friends_man   )) with CardSet
  lazy val appearsIn    = new Human_2[A, Set[String]        , String](dataModel.add(appearsIn_man )) with CardSet
  lazy val homePlanet   = new Human_2[A, String             , String](dataModel.add(homePlanet_man)) with CardOne
  lazy val name_?       = new Human_2[A, Option[String]     , String](dataModel.add(name_opt      )) with CardOne
  lazy val friends_?    = new Human_2[A, Option[Set[Long]]  , Long  ](dataModel.add(friends_opt   )) with CardSet
  lazy val appearsIn_?  = new Human_2[A, Option[Set[String]], String](dataModel.add(appearsIn_opt )) with CardSet
  lazy val homePlanet_? = new Human_2[A, Option[String]     , String](dataModel.add(homePlanet_opt)) with CardOne
}

//class Human_1_refs[A, t](dataModel: DataModel) extends Molecule_01[A] {
class Human_1_refs[A, t](dataModel: DataModel) {
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

  object Friends extends Character_1[A, t](dataModel.add(_dm.Ref("Human", "friends", "Character", CardSet, false, List(2, 11, 0)))) //with NestedInit
}


//class Human_2[A, B, t](dataModel: DataModel) extends Human_2_refs[A, B, t](dataModel) with Human_base with ModelOps_2[A, B, t, Human_2, Human_3] {
class Human_2[A, B, t](dataModel: DataModel) extends Human_2_refs[A, B, t](dataModel) with Human_base {
  lazy val id           = new Human_3[A, B, Long               , Long  ](dataModel.add(id_man        )) with CardOne
  lazy val name         = new Human_3[A, B, String             , String](dataModel.add(name_man      )) with CardOne
  lazy val friends      = new Human_3[A, B, Set[Long]          , Long  ](dataModel.add(friends_man   )) with CardSet
  lazy val appearsIn    = new Human_3[A, B, Set[String]        , String](dataModel.add(appearsIn_man )) with CardSet
  lazy val homePlanet   = new Human_3[A, B, String             , String](dataModel.add(homePlanet_man)) with CardOne
  lazy val name_?       = new Human_3[A, B, Option[String]     , String](dataModel.add(name_opt      )) with CardOne
  lazy val friends_?    = new Human_3[A, B, Option[Set[Long]]  , Long  ](dataModel.add(friends_opt   )) with CardSet
  lazy val appearsIn_?  = new Human_3[A, B, Option[Set[String]], String](dataModel.add(appearsIn_opt )) with CardSet
  lazy val homePlanet_? = new Human_3[A, B, Option[String]     , String](dataModel.add(homePlanet_opt)) with CardOne
}

//class Human_2_refs[A, B, t](dataModel: DataModel) extends Molecule_02[A, B] {
class Human_2_refs[A, B, t](dataModel: DataModel) {
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

  object Friends extends Character_2[A, B, t](dataModel.add(_dm.Ref("Human", "friends", "Character", CardSet, false, List(2, 11, 0)))) //with NestedInit
}


class Human_3[A, B, C, t](dataModel: DataModel)
