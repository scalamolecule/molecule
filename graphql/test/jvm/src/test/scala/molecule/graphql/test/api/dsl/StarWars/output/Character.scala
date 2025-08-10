//// AUTO-GENERATED Molecule DSL boilerplate code for entity `Character`
//package molecule.graphql.test.api.dsl.StarWars.output
//
//import molecule.core.dataModel.*
//import molecule.core.dataModel as _dm
//import molecule.core.dataModel.*
//import molecule.graphql.client.api.*
//
//
//private[StarWars] trait Character_base {
//  protected lazy val id_man       : AttrOneManID     = AttrOneManID    ("Character", "id"       )
//  protected lazy val name_man     : AttrOneManString = AttrOneManString("Character", "name"     )
//  protected lazy val friends_man  : AttrSetManID     = AttrSetManID    ("Character", "friends"  , ref = Some("Character"))
//  protected lazy val appearsIn_man: AttrSetManString = AttrSetManString("Character", "appearsIn", validator = Some(validation_appearsIn))
//
//  protected lazy val name_opt     : AttrOneOptString = AttrOneOptString("Character", "name"     )
//  protected lazy val friends_opt  : AttrSetOptID     = AttrSetOptID    ("Character", "friends"  , ref = Some("Character"))
//  protected lazy val appearsIn_opt: AttrSetOptString = AttrSetOptString("Character", "appearsIn", validator = Some(validation_appearsIn))
//
//  private lazy val validation_appearsIn = new ValidateString {
//    override def validate(v: String): Seq[String] = {
//      val ok: String => Boolean = v => Seq("NEWHOPE", "EMPIRE", "JEDI").contains(v)
//      if (ok(v)) Nil else Seq(s"""Value `$v` is not one of the allowed values in Seq("NEWHOPE", "EMPIRE", "JEDI")""")
//    }
//  }
//}
//
//
//private[dsl] class Character_0[t](override val dataModel: DataModel) extends Character_0_refs[t](dataModel) with Character_base {
//  lazy val id          = new Character_1[String             , String](dataModel.add(id_man       )) with OneValue
//  lazy val name        = new Character_1[String             , String](dataModel.add(name_man     )) with OneValue
//  lazy val friends     = new Character_1[Set[Long]          , Long  ](dataModel.add(friends_man  )) with SetValue
//  lazy val appearsIn   = new Character_1[Set[String]        , String](dataModel.add(appearsIn_man)) with SetValue
//
//  lazy val name_?      = new Character_1[Option[String]     , String](dataModel.add(name_opt     )) with OneValue
//  lazy val friends_?   = new Character_1[Option[Set[Long]]  , Long  ](dataModel.add(friends_opt  )) with SetValue
//  lazy val appearsIn_? = new Character_1[Option[Set[String]], String](dataModel.add(appearsIn_opt)) with SetValue
//}
//
//private[dsl] class Character_0_refs[t](override val dataModel: DataModel) extends Molecule_00 {
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
//
//  object Friends extends Character_0[t](dataModel.add(_dm.Ref("Character", "friends", "Character", SetValue, false, List(0, 2, 0)))) with NestedInit
//
//  object _Droid     extends Droid_0    [t](dataModel.add(_dm.BackRef("Droid", "Character", List(1, 0))))
//  object _Human     extends Human_0    [t](dataModel.add(_dm.BackRef("Human", "Character", List(2, 0))))
//}
//
//
//private[dsl] class Character_1[A, t](override val dataModel: DataModel) extends Character_1_refs[A, t](dataModel) with Character_base {
//  lazy val id          = new Character_2[A, Long               , Long  ](dataModel.add(id_man       )) with OneValue
//  lazy val name        = new Character_2[A, String             , String](dataModel.add(name_man     )) with OneValue
//  lazy val friends     = new Character_2[A, Set[Long]          , Long  ](dataModel.add(friends_man  )) with SetValue
//  lazy val appearsIn   = new Character_2[A, Set[String]        , String](dataModel.add(appearsIn_man)) with SetValue
//  lazy val name_?      = new Character_2[A, Option[String]     , String](dataModel.add(name_opt     )) with OneValue
//  lazy val friends_?   = new Character_2[A, Option[Set[Long]]  , Long  ](dataModel.add(friends_opt  )) with SetValue
//  lazy val appearsIn_? = new Character_2[A, Option[Set[String]], String](dataModel.add(appearsIn_opt)) with SetValue
//}
//
//private[dsl] class Character_1_refs[A, t](override val dataModel: DataModel) extends Molecule_01[A] {
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
//
//  object Friends extends Character_1[A, t](dataModel.add(_dm.Ref("Character", "friends", "Character", SetValue, false, List(0, 2, 0)))) with NestedInit
//
//  object _Droid     extends Droid_1    [A, t](dataModel.add(_dm.BackRef("Droid", "Character", List(1, 0))))
//  object _Human     extends Human_1    [A, t](dataModel.add(_dm.BackRef("Human", "Character", List(2, 0))))
//}
//
//
//private[dsl] class Character_2[A, B, t](override val dataModel: DataModel) extends Character_2_refs[A, B, t](dataModel) with Character_base {
//  lazy val id          = new Character_3[A, B, Long               , Long  ](dataModel.add(id_man       )) with OneValue
//  lazy val name        = new Character_3[A, B, String             , String](dataModel.add(name_man     )) with OneValue
//  lazy val friends     = new Character_3[A, B, Set[Long]          , Long  ](dataModel.add(friends_man  )) with SetValue
//  lazy val appearsIn   = new Character_3[A, B, Set[String]        , String](dataModel.add(appearsIn_man)) with SetValue
//  lazy val name_?      = new Character_3[A, B, Option[String]     , String](dataModel.add(name_opt     )) with OneValue
//  lazy val friends_?   = new Character_3[A, B, Option[Set[Long]]  , Long  ](dataModel.add(friends_opt  )) with SetValue
//  lazy val appearsIn_? = new Character_3[A, B, Option[Set[String]], String](dataModel.add(appearsIn_opt)) with SetValue
//}
//
//private[dsl] class Character_2_refs[A, B, t](override val dataModel: DataModel) extends Molecule_02[A, B] {
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
//
//  object Friends extends Character_2[A, B, t](dataModel.add(_dm.Ref("Character", "friends", "Character", SetValue, false, List(0, 2, 0)))) with NestedInit
//
//  object _Droid     extends Droid_2    [A, B, t](dataModel.add(_dm.BackRef("Droid", "Character", List(1, 0))))
//  object _Human     extends Human_2    [A, B, t](dataModel.add(_dm.BackRef("Human", "Character", List(2, 0))))
//}
//
//
//private[dsl] class Character_3[A, B, C, t](override val dataModel: DataModel) extends Molecule_03[A, B, C]
//
