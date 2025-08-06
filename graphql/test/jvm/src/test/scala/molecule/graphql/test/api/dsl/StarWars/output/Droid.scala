///*
//* AUTO-GENERATED Molecule DSL boilerplate code for entity `Droid`
//*
//* To change:
//* 1. Edit domain structure in molecule.graphql.client.dataModel.dsl.Starwars
//* 2. `sbt compile -Dmolecule=true`
//*/
//package molecule.graphql.test.api.dsl.StarWars.output
//
//import molecule.core.dataModel.{CardOne, CardSet}
//import molecule.core.dataModel as _dm
//import molecule.core.dataModel.*
//
//
//trait Droid_base {
//  protected lazy val id_man             : AttrOneManID     = AttrOneManID    ("Droid", "id"             , coord = List(1, 4   ))
//  protected lazy val name_man           : AttrOneManString = AttrOneManString("Droid", "name"           , coord = List(1, 5   ))
//  protected lazy val friends_man        : AttrSetManID     = AttrSetManID    ("Droid", "friends"        , coord = List(1, 6, 0), ref = Some("Character"))
//  protected lazy val appearsIn_man      : AttrSetManString = AttrSetManString("Droid", "appearsIn"      , coord = List(1, 7   ), validator = Some(validation_appearsIn))
//  protected lazy val primaryFunction_man: AttrOneManString = AttrOneManString("Droid", "primaryFunction", coord = List(1, 8   ))
//
//  protected lazy val name_opt           : AttrOneOptString = AttrOneOptString("Droid", "name"           , coord = List(1, 5   ))
//  protected lazy val friends_opt        : AttrSetOptID     = AttrSetOptID    ("Droid", "friends"        , coord = List(1, 6, 0), ref = Some("Character"))
//  protected lazy val appearsIn_opt      : AttrSetOptString = AttrSetOptString("Droid", "appearsIn"      , coord = List(1, 7   ), validator = Some(validation_appearsIn))
//  protected lazy val primaryFunction_opt: AttrOneOptString = AttrOneOptString("Droid", "primaryFunction", coord = List(1, 8   ))
//
//  private lazy val validation_appearsIn = new ValidateString {
//    override def validate(v: String): Seq[String] = {
//      val ok: String => Boolean = v => Seq("NEWHOPE", "EMPIRE", "JEDI").contains(v)
//      if (ok(v)) Nil else Seq(s"""Value `$v` is not one of the allowed values in Seq("NEWHOPE", "EMPIRE", "JEDI")""")
//    }
//  }
//}
//
////object Droid extends Droid_0[Nothing](DataModel(Nil, firstEntityIndex = 1)) with OptEntityOp_0[Droid_1_refs] with OptEntity_0[Droid_1_refs] {
//////  final def apply(id : String, ids: String*) = new Droid_0[String](DataModel(List(AttrOneTacString("Droid", "id", Eq, id +: ids, coord = List(1, 4))), firstEntityIndex = 1))
//////  final def apply(ids: Iterable[String])   = new Droid_0[String](DataModel(List(AttrOneTacString("Droid", "id", Eq, ids.toSeq, coord = List(1, 4))), firstEntityIndex = 1))
////
//////  override protected def _optEntity[OptEntityTpl](attrs: List[Attr]): Droid_1_refs[Option[OptEntityTpl], Any] =
//////    new Droid_1_refs[Option[OptEntityTpl], Any](DataModel(List(ast.OptEntity(attrs))))
////}
//
//
////class Droid_0[t](dataModel: DataModel) extends Droid_0_refs[t](dataModel) with Droid_base with ModelOps_0[t, Droid_0, Droid_1] {
//class Droid_0[t](dataModel: DataModel) extends Droid_0_refs[t](dataModel) with Droid_base {
//  lazy val id                = new Droid_1[String             , String](dataModel.add(id_man             )) with CardOne
//  lazy val name              = new Droid_1[String             , String](dataModel.add(name_man           )) with CardOne
//  lazy val friends           = new Droid_1[Set[Long]          , Long  ](dataModel.add(friends_man        )) with CardSet
//  lazy val appearsIn         = new Droid_1[Set[String]        , String](dataModel.add(appearsIn_man      )) with CardSet
//  lazy val primaryFunction   = new Droid_1[String             , String](dataModel.add(primaryFunction_man)) with CardOne
//  lazy val name_?            = new Droid_1[Option[String]     , String](dataModel.add(name_opt           )) with CardOne
//  lazy val friends_?         = new Droid_1[Option[Set[Long]]  , Long  ](dataModel.add(friends_opt        )) with CardSet
//  lazy val appearsIn_?       = new Droid_1[Option[Set[String]], String](dataModel.add(appearsIn_opt      )) with CardSet
//  lazy val primaryFunction_? = new Droid_1[Option[String]     , String](dataModel.add(primaryFunction_opt)) with CardOne
//}
//
////class Droid_0_refs[t](dataModel: DataModel) extends Molecule_00 {
//class Droid_0_refs[t](dataModel: DataModel) {
////  trait NestedInit extends NestedOp_0 with Nested_0 { self: Molecule =>
////    override protected def _nestedMan[NestedTpl](nestedDataModel: DataModel): NestedInit_00[NestedTpl] =
////      new NestedInit_00(DataModel(
////        self.dataModel.elements.init :+ Nested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
////        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes,
////        binds = self.dataModel.binds + nestedDataModel.binds
////      ))
////
////    override protected def _nestedOpt[NestedTpl](nestedDataModel: DataModel): NestedInit_00[NestedTpl] =
////      new NestedInit_00(DataModel(
////        self.dataModel.elements.init :+ OptNested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
////        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes,
////        binds = self.dataModel.binds + nestedDataModel.binds
////      ))
////  }
//
//  object Friends extends Character_0[t](dataModel.add(_dm.Ref("Droid", "friends", "Character", CardSet, false, List(1, 6, 0)))) //with NestedInit
//}
//
//
////class Droid_1[A, t](dataModel: DataModel) extends Droid_1_refs[A, t](dataModel) with Droid_base with ModelOps_1[A, t, Droid_1, Droid_2] {
//class Droid_1[A, t](dataModel: DataModel) extends Droid_1_refs[A, t](dataModel) with Droid_base {
//  lazy val id                = new Droid_2[A, Long               , Long  ](dataModel.add(id_man             )) with CardOne
//  lazy val name              = new Droid_2[A, String             , String](dataModel.add(name_man           )) with CardOne
//  lazy val friends           = new Droid_2[A, Set[Long]          , Long  ](dataModel.add(friends_man        )) with CardSet
//  lazy val appearsIn         = new Droid_2[A, Set[String]        , String](dataModel.add(appearsIn_man      )) with CardSet
//  lazy val primaryFunction   = new Droid_2[A, String             , String](dataModel.add(primaryFunction_man)) with CardOne
//  lazy val name_?            = new Droid_2[A, Option[String]     , String](dataModel.add(name_opt           )) with CardOne
//  lazy val friends_?         = new Droid_2[A, Option[Set[Long]]  , Long  ](dataModel.add(friends_opt        )) with CardSet
//  lazy val appearsIn_?       = new Droid_2[A, Option[Set[String]], String](dataModel.add(appearsIn_opt      )) with CardSet
//  lazy val primaryFunction_? = new Droid_2[A, Option[String]     , String](dataModel.add(primaryFunction_opt)) with CardOne
//}
//
////class Droid_1_refs[A, t](dataModel: DataModel) extends Molecule_01[A] {
//class Droid_1_refs[A, t](dataModel: DataModel) {
////  trait NestedInit extends NestedOp_1[A] with Nested_1[A] { self: Molecule =>
////    override protected def _nestedMan[NestedTpl](nestedDataModel: DataModel): NestedInit_01[A, NestedTpl] =
////      new NestedInit_01(DataModel(
////        self.dataModel.elements.init :+ Nested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
////        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes,
////        binds = self.dataModel.binds + nestedDataModel.binds
////      ))
////
////    override protected def _nestedOpt[NestedTpl](nestedDataModel: DataModel): NestedInit_01[A, NestedTpl] =
////      new NestedInit_01(DataModel(
////        self.dataModel.elements.init :+ OptNested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
////        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes,
////        binds = self.dataModel.binds + nestedDataModel.binds
////      ))
////  }
//
//  object Friends extends Character_1[A, t](dataModel.add(_dm.Ref("Droid", "friends", "Character", CardSet, false, List(1, 6, 0))))// with NestedInit
//}
//
//
////class Droid_2[A, B, t](dataModel: DataModel) extends Droid_2_refs[A, B, t](dataModel) with Droid_base with ModelOps_2[A, B, t, Droid_2, Droid_3] {
//class Droid_2[A, B, t](dataModel: DataModel) extends Droid_2_refs[A, B, t](dataModel) with Droid_base {
//  lazy val id                = new Droid_3[A, B, Long               , Long  ](dataModel.add(id_man             )) with CardOne
//  lazy val name              = new Droid_3[A, B, String             , String](dataModel.add(name_man           )) with CardOne
//  lazy val friends           = new Droid_3[A, B, Set[Long]          , Long  ](dataModel.add(friends_man        )) with CardSet
//  lazy val appearsIn         = new Droid_3[A, B, Set[String]        , String](dataModel.add(appearsIn_man      )) with CardSet
//  lazy val primaryFunction   = new Droid_3[A, B, String             , String](dataModel.add(primaryFunction_man)) with CardOne
//  lazy val name_?            = new Droid_3[A, B, Option[String]     , String](dataModel.add(name_opt           )) with CardOne
//  lazy val friends_?         = new Droid_3[A, B, Option[Set[Long]]  , Long  ](dataModel.add(friends_opt        )) with CardSet
//  lazy val appearsIn_?       = new Droid_3[A, B, Option[Set[String]], String](dataModel.add(appearsIn_opt      )) with CardSet
//  lazy val primaryFunction_? = new Droid_3[A, B, Option[String]     , String](dataModel.add(primaryFunction_opt)) with CardOne
//}
//
////class Droid_2_refs[A, B, t](dataModel: DataModel) extends Molecule_02[A, B] {
//class Droid_2_refs[A, B, t](dataModel: DataModel) {
////  trait NestedInit extends NestedOp_2[A, B] with Nested_2[A, B] { self: Molecule =>
////    override protected def _nestedMan[NestedTpl](nestedDataModel: DataModel): NestedInit_02[A, B, NestedTpl] =
////      new NestedInit_02(DataModel(
////        self.dataModel.elements.init :+ Nested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
////        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes,
////        binds = self.dataModel.binds + nestedDataModel.binds
////      ))
////
////    override protected def _nestedOpt[NestedTpl](nestedDataModel: DataModel): NestedInit_02[A, B, NestedTpl] =
////      new NestedInit_02(DataModel(
////        self.dataModel.elements.init :+ OptNested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
////        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes,
////        binds = self.dataModel.binds + nestedDataModel.binds
////      ))
////  }
//
//  object Friends extends Character_2[A, B, t](dataModel.add(_dm.Ref("Droid", "friends", "Character", CardSet, false, List(1, 6, 0)))) //with NestedInit
//}
//
//
//class Droid_3[A, B, C, t](dataModel: DataModel)