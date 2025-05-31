///*
//* AUTO-GENERATED Molecule DSL boilerplate code for entity `Character`
//*
//* To change:
//* 1. Edit domain structure in molecule.graphql.client.dataModel.dsl.Starwars
//* 2. `sbt compile -Dmolecule=true`
//*/
//package molecule.graphql.client.dsl.Starwars
//
//import molecule.db.base.ast.*
//import molecule.db.core.api.*
//import molecule.db.core.api.Keywords.*
//import molecule.db.core.api.expression.*
//import molecule.db.core.ast
//import molecule.db.core.ast.*
//
//
//trait Character_base {
//  protected lazy val id_man       : AttrOneManID     = AttrOneManID    ("Character", "id"       , coord = List(0, 0   ))
//  protected lazy val name_man     : AttrOneManString = AttrOneManString("Character", "name"     , coord = List(0, 1   ))
//  protected lazy val friends_man  : AttrSetManID     = AttrSetManID    ("Character", "friends"  , coord = List(0, 2, 0), ref = Some("Character"))
//  protected lazy val appearsIn_man: AttrSetManString = AttrSetManString("Character", "appearsIn", coord = List(0, 3   ), validator = Some(validation_appearsIn))
//
//  protected lazy val name_opt     : AttrOneOptString = AttrOneOptString("Character", "name"     , coord = List(0, 1   ))
//  protected lazy val friends_opt  : AttrSetOptID     = AttrSetOptID    ("Character", "friends"  , coord = List(0, 2, 0), ref = Some("Character"))
//  protected lazy val appearsIn_opt: AttrSetOptString = AttrSetOptString("Character", "appearsIn", coord = List(0, 3   ), validator = Some(validation_appearsIn))
//
//  protected lazy val id_tac       : AttrOneTacID     = AttrOneTacID    ("Character", "id"       , coord = List(0, 0   ))
//  protected lazy val name_tac     : AttrOneTacString = AttrOneTacString("Character", "name"     , coord = List(0, 1   ))
//  protected lazy val friends_tac  : AttrSetTacID     = AttrSetTacID    ("Character", "friends"  , coord = List(0, 2, 0), ref = Some("Character"))
//  protected lazy val appearsIn_tac: AttrSetTacString = AttrSetTacString("Character", "appearsIn", coord = List(0, 3   ), validator = Some(validation_appearsIn))
//
//  private lazy val validation_appearsIn = new ValidateString {
//    override def validate(v: String): Seq[String] = {
//      val ok: String => Boolean = v => Seq("NEWHOPE", "EMPIRE", "JEDI").contains(v)
//        if (ok(v)) Nil else Seq(s"""Value `$v` is not one of the allowed values in Seq("NEWHOPE", "EMPIRE", "JEDI")""")
//    }
//  }
//}
//
//object Character extends Character_0[Nothing](DataModel(Nil, firstEntityIndex = 0)) with OptEntityOp_0[Character_1_refs] with OptEntity_0[Character_1_refs] {
//  final def apply(id : String, ids: String*) = new Character_0[String](DataModel(List(AttrOneTacString("Character", "id", Eq, id +: ids, coord = List(0, 0))), firstEntityIndex = 0))
//  final def apply(ids: Iterable[String])   = new Character_0[String](DataModel(List(AttrOneTacString("Character", "id", Eq, ids.toSeq, coord = List(0, 0))), firstEntityIndex = 0))
////
////  override protected def _optEntity[OptEntityTpl](attrs: List[Attr]): Character_1_refs[Option[OptEntityTpl], Any] =
////    new Character_1_refs[Option[OptEntityTpl], Any](DataModel(List(ast.OptEntity(attrs))))
//}
//
//
//class Character_0[t](override val dataModel: DataModel) extends Character_0_refs[t](dataModel) with Character_base with ModelOps_0[t, Character_0, Character_1] {
//  lazy val id          = new Character_1[String               , String  ](dataModel.add(id_man       )) with ExprOneMan_1         [String               , String  , Character_1, Character_2] with CardOne
//  lazy val name        = new Character_1[String             , String](dataModel.add(name_man     )) with ExprOneMan_1_String  [String             , String, Character_1, Character_2] with CardOne
//  lazy val friends     = new Character_1[Set[Long]          , Long  ](dataModel.add(friends_man  )) with ExprSetMan_1         [Set[Long]          , Long  , Character_1, Character_2] with CardSet
//  lazy val appearsIn   = new Character_1[Set[String]        , String](dataModel.add(appearsIn_man)) with ExprSetMan_1         [Set[String]        , String, Character_1, Character_2] with CardSet
//
//  lazy val name_?      = new Character_1[Option[String]     , String](dataModel.add(name_opt     )) with ExprOneOpt_1         [Option[String]     , String, Character_1, Character_2] with CardOne
//  lazy val friends_?   = new Character_1[Option[Set[Long]]  , Long  ](dataModel.add(friends_opt  )) with ExprSetOpt_1         [Option[Set[Long]]  , Long  , Character_1, Character_2] with CardSet
//  lazy val appearsIn_? = new Character_1[Option[Set[String]], String](dataModel.add(appearsIn_opt)) with ExprSetOpt_1         [Option[Set[String]], String, Character_1, Character_2] with CardSet
//
//  lazy val id_         = new Character_0[                     Long  ](dataModel.add(id_tac       )) with ExprOneTac_0         [                     Long  , Character_0, Character_1] with CardOne
//  lazy val name_       = new Character_0[                     String](dataModel.add(name_tac     )) with ExprOneTac_0_String  [                     String, Character_0, Character_1] with CardOne
//  lazy val friends_    = new Character_0[                     Long  ](dataModel.add(friends_tac  )) with ExprSetTac_0         [                     Long  , Character_0, Character_1] with CardSet
//  lazy val appearsIn_  = new Character_0[                     String](dataModel.add(appearsIn_tac)) with ExprSetTac_0         [                     String, Character_0, Character_1] with CardSet
//
//  override protected def _exprOneTac(op: Op, vs: Seq[t]) = new Character_0[t](addOne(dataModel, op, vs)) with CardOne
//  override protected def _exprSet   (op: Op, vs: Set[t]) = new Character_0[t](addSet(dataModel, op, vs)) with CardSet
//
//  override protected def _attrTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]) = new Character_0[   t](filterAttr(dataModel, op, a))
//  override protected def _attrMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]) = new Character_1[X, t](filterAttr(dataModel, op, a))
//}
//
//class Character_0_refs[t](override val dataModel: DataModel) extends Molecule_00 {
//  trait NestedInit extends NestedOp_0 with Nested_0 { self: Molecule =>
//    override protected def _nestedMan[NestedTpl](nestedDataModel: DataModel): NestedInit_00[NestedTpl] =
//      new NestedInit_00(DataModel(
//        self.dataModel.elements.init :+ Nested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
//        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes
//      ))
//
//    override protected def _nestedOpt[NestedTpl](nestedDataModel: DataModel): NestedInit_00[NestedTpl] =
//      new NestedInit_00(DataModel(
//        self.dataModel.elements.init :+ OptNested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
//        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes
//      ))
//  }
//
//  object Friends extends Character_0[t](dataModel.add(ast.Ref("Character", "friends", "Character", CardSet, false, List(0, 2, 0)))) with NestedInit
//
//  object _Droid     extends Droid_0    [t](dataModel.add(ast.BackRef("Droid", "Character", List(1, 0))))
//  object _Human     extends Human_0    [t](dataModel.add(ast.BackRef("Human", "Character", List(2, 0))))
//}
//
//
//class Character_1[A, t](override val dataModel: DataModel) extends Character_1_refs[A, t](dataModel) with Character_base with ModelOps_1[A, t, Character_1, Character_2] {
//  lazy val id          = new Character_2[A, Long               , Long  ](dataModel.add(id_man       )) with ExprOneMan_2         [A, Long               , Long  , Character_2, Character_3] with CardOne
//  lazy val name        = new Character_2[A, String             , String](dataModel.add(name_man     )) with ExprOneMan_2_String  [A, String             , String, Character_2, Character_3] with CardOne
//  lazy val friends     = new Character_2[A, Set[Long]          , Long  ](dataModel.add(friends_man  )) with ExprSetMan_2         [A, Set[Long]          , Long  , Character_2, Character_3] with CardSet
//  lazy val appearsIn   = new Character_2[A, Set[String]        , String](dataModel.add(appearsIn_man)) with ExprSetMan_2         [A, Set[String]        , String, Character_2, Character_3] with CardSet
//
//  lazy val name_?      = new Character_2[A, Option[String]     , String](dataModel.add(name_opt     )) with ExprOneOpt_2         [A, Option[String]     , String, Character_2, Character_3] with CardOne
//  lazy val friends_?   = new Character_2[A, Option[Set[Long]]  , Long  ](dataModel.add(friends_opt  )) with ExprSetOpt_2         [A, Option[Set[Long]]  , Long  , Character_2, Character_3] with CardSet
//  lazy val appearsIn_? = new Character_2[A, Option[Set[String]], String](dataModel.add(appearsIn_opt)) with ExprSetOpt_2         [A, Option[Set[String]], String, Character_2, Character_3] with CardSet
//
//  lazy val id_         = new Character_1[A                     , Long  ](dataModel.add(id_tac       )) with ExprOneTac_1         [A                     , Long  , Character_1, Character_2] with CardOne
//  lazy val name_       = new Character_1[A                     , String](dataModel.add(name_tac     )) with ExprOneTac_1_String  [A                     , String, Character_1, Character_2] with CardOne
//  lazy val friends_    = new Character_1[A                     , Long  ](dataModel.add(friends_tac  )) with ExprSetTac_1         [A                     , Long  , Character_1, Character_2] with CardSet
//  lazy val appearsIn_  = new Character_1[A                     , String](dataModel.add(appearsIn_tac)) with ExprSetTac_1         [A                     , String, Character_1, Character_2] with CardSet
//
//  override protected def _aggrInt   (kw: Kw                    ) = new Character_1[Int   , Int   ](toInt    (dataModel, kw    )) with SortAttrs_1[Int   , Int   , Character_1]
//  override protected def _aggrT     (kw: Kw                    ) = new Character_1[t     , t     ](asIs     (dataModel, kw    )) with SortAttrs_1[t     , t     , Character_1]
//  override protected def _aggrDouble(kw: Kw                    ) = new Character_1[Double, Double](toDouble (dataModel, kw    )) with SortAttrs_1[Double, Double, Character_1]
//  override protected def _aggrSet   (kw: Kw, n: Option[Int]    ) = new Character_1[Set[t], t     ](asIs     (dataModel, kw, n ))
//  override protected def _aggrDist  (kw: Kw                    ) = new Character_1[Set[A], t     ](asIs     (dataModel, kw    ))
//  override protected def _exprOneMan(op: Op, vs: Seq[t] , binding: Boolean       ) = new Character_1[A     , t     ](addOne   (dataModel, op, vs)) with SortAttrs_1[A     , t     , Character_1] with CardOne
//  override protected def _exprOneOpt(op: Op, v : Option[t]     ) = new Character_1[A     , t     ](addOneOpt(dataModel, op, v )) with SortAttrs_1[A     , t     , Character_1]
//  override protected def _exprOneTac(op: Op, vs: Seq[t]        ) = new Character_1[A     , t     ](addOne   (dataModel, op, vs)) with CardOne
//  override protected def _exprSet   (op: Op, vs: Set[t]        ) = new Character_1[A     , t     ](addSet   (dataModel, op, vs)) with CardSet
//  override protected def _exprSetOpt(op: Op, vs: Option[Set[t]]) = new Character_1[A     , t     ](addSetOpt(dataModel, op, vs))
//  override protected def _sort      (sort: String              ) = new Character_1[A     , t     ](addSort  (dataModel, sort))
//
//  override protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] & CardOne) = new Character_1[A,    t](filterAttr(dataModel, op, a)) with SortAttrs_1[A,    t, Character_1]
//  override protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[A, t, ns1, ns2]          ) = new Character_2[A, A, t](filterAttr(dataModel, op, a)) with SortAttrs_2[A, A, t, Character_2]
//  override protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]          ) = new Character_1[A,    t](filterAttr(dataModel, op, a))
//  override protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]          ) = new Character_2[A, X, t](filterAttr(dataModel, op, a))
//}
//
//class Character_1_refs[A, t](override val dataModel: DataModel) extends Molecule_01[A] {
//  trait NestedInit extends NestedOp_1[A] with Nested_1[A] { self: Molecule =>
//    override protected def _nestedMan[NestedTpl](nestedDataModel: DataModel): NestedInit_01[A, NestedTpl] =
//      new NestedInit_01(DataModel(
//        self.dataModel.elements.init :+ Nested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
//        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes
//      ))
//
//    override protected def _nestedOpt[NestedTpl](nestedDataModel: DataModel): NestedInit_01[A, NestedTpl] =
//      new NestedInit_01(DataModel(
//        self.dataModel.elements.init :+ OptNested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
//        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes
//      ))
//  }
//
//  object Friends extends Character_1[A, t](dataModel.add(ast.Ref("Character", "friends", "Character", CardSet, false, List(0, 2, 0)))) with NestedInit
//
//  object _Droid     extends Droid_1    [A, t](dataModel.add(ast.BackRef("Droid", "Character", List(1, 0))))
//  object _Human     extends Human_1    [A, t](dataModel.add(ast.BackRef("Human", "Character", List(2, 0))))
//}
//
//
//class Character_2[A, B, t](override val dataModel: DataModel) extends Character_2_refs[A, B, t](dataModel) with Character_base with ModelOps_2[A, B, t, Character_2, Character_3] {
//  lazy val id          = new Character_3[A, B, Long               , Long  ](dataModel.add(id_man       )) with ExprOneMan_3         [A, B, Long               , Long  , Character_3, Character_4] with CardOne
//  lazy val name        = new Character_3[A, B, String             , String](dataModel.add(name_man     )) with ExprOneMan_3_String  [A, B, String             , String, Character_3, Character_4] with CardOne
//  lazy val friends     = new Character_3[A, B, Set[Long]          , Long  ](dataModel.add(friends_man  )) with ExprSetMan_3         [A, B, Set[Long]          , Long  , Character_3, Character_4] with CardSet
//  lazy val appearsIn   = new Character_3[A, B, Set[String]        , String](dataModel.add(appearsIn_man)) with ExprSetMan_3         [A, B, Set[String]        , String, Character_3, Character_4] with CardSet
//
//  lazy val name_?      = new Character_3[A, B, Option[String]     , String](dataModel.add(name_opt     )) with ExprOneOpt_3         [A, B, Option[String]     , String, Character_3, Character_4] with CardOne
//  lazy val friends_?   = new Character_3[A, B, Option[Set[Long]]  , Long  ](dataModel.add(friends_opt  )) with ExprSetOpt_3         [A, B, Option[Set[Long]]  , Long  , Character_3, Character_4] with CardSet
//  lazy val appearsIn_? = new Character_3[A, B, Option[Set[String]], String](dataModel.add(appearsIn_opt)) with ExprSetOpt_3         [A, B, Option[Set[String]], String, Character_3, Character_4] with CardSet
//
//  lazy val id_         = new Character_2[A, B                     , Long  ](dataModel.add(id_tac       )) with ExprOneTac_2         [A, B                     , Long  , Character_2, Character_3] with CardOne
//  lazy val name_       = new Character_2[A, B                     , String](dataModel.add(name_tac     )) with ExprOneTac_2_String  [A, B                     , String, Character_2, Character_3] with CardOne
//  lazy val friends_    = new Character_2[A, B                     , Long  ](dataModel.add(friends_tac  )) with ExprSetTac_2         [A, B                     , Long  , Character_2, Character_3] with CardSet
//  lazy val appearsIn_  = new Character_2[A, B                     , String](dataModel.add(appearsIn_tac)) with ExprSetTac_2         [A, B                     , String, Character_2, Character_3] with CardSet
//
//  override protected def _aggrInt   (kw: Kw                    ) = new Character_2[A, Int   , Int   ](toInt    (dataModel, kw    )) with SortAttrs_2[A, Int   , Int   , Character_2]
//  override protected def _aggrT     (kw: Kw                    ) = new Character_2[A, t     , t     ](asIs     (dataModel, kw    )) with SortAttrs_2[A, t     , t     , Character_2]
//  override protected def _aggrDouble(kw: Kw                    ) = new Character_2[A, Double, Double](toDouble (dataModel, kw    )) with SortAttrs_2[A, Double, Double, Character_2]
//  override protected def _aggrSet   (kw: Kw, n: Option[Int]    ) = new Character_2[A, Set[t], t     ](asIs     (dataModel, kw, n ))
//  override protected def _aggrDist  (kw: Kw                    ) = new Character_2[A, Set[B], t     ](asIs     (dataModel, kw    ))
//  override protected def _exprOneMan(op: Op, vs: Seq[t] , binding: Boolean       ) = new Character_2[A, B     , t     ](addOne   (dataModel, op, vs)) with SortAttrs_2[A, B     , t     , Character_2] with CardOne
//  override protected def _exprOneOpt(op: Op, v : Option[t]     ) = new Character_2[A, B     , t     ](addOneOpt(dataModel, op, v )) with SortAttrs_2[A, B     , t     , Character_2]
//  override protected def _exprOneTac(op: Op, vs: Seq[t]        ) = new Character_2[A, B     , t     ](addOne   (dataModel, op, vs)) with CardOne
//  override protected def _exprSet   (op: Op, vs: Set[t]        ) = new Character_2[A, B     , t     ](addSet   (dataModel, op, vs)) with CardSet
//  override protected def _exprSetOpt(op: Op, vs: Option[Set[t]]) = new Character_2[A, B     , t     ](addSetOpt(dataModel, op, vs))
//  override protected def _sort      (sort: String              ) = new Character_2[A, B     , t     ](addSort  (dataModel, sort))
//
//  override protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] & CardOne) = new Character_2[A, B,    t](filterAttr(dataModel, op, a)) with SortAttrs_2[A, B,    t, Character_2]
//  override protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[B, t, ns1, ns2]          ) = new Character_3[A, B, B, t](filterAttr(dataModel, op, a)) with SortAttrs_3[A, B, B, t, Character_3]
//  override protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]          ) = new Character_2[A, B,    t](filterAttr(dataModel, op, a))
//  override protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]          ) = new Character_3[A, B, X, t](filterAttr(dataModel, op, a))
//}
//
//class Character_2_refs[A, B, t](override val dataModel: DataModel) extends Molecule_02[A, B] {
//  trait NestedInit extends NestedOp_2[A, B] with Nested_2[A, B] { self: Molecule =>
//    override protected def _nestedMan[NestedTpl](nestedDataModel: DataModel): NestedInit_02[A, B, NestedTpl] =
//      new NestedInit_02(DataModel(
//        self.dataModel.elements.init :+ Nested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
//        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes
//      ))
//
//    override protected def _nestedOpt[NestedTpl](nestedDataModel: DataModel): NestedInit_02[A, B, NestedTpl] =
//      new NestedInit_02(DataModel(
//        self.dataModel.elements.init :+ OptNested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
//        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes
//      ))
//  }
//
//  object Friends extends Character_2[A, B, t](dataModel.add(ast.Ref("Character", "friends", "Character", CardSet, false, List(0, 2, 0)))) with NestedInit
//
//  object _Droid     extends Droid_2    [A, B, t](dataModel.add(ast.BackRef("Droid", "Character", List(1, 0))))
//  object _Human     extends Human_2    [A, B, t](dataModel.add(ast.BackRef("Human", "Character", List(2, 0))))
//}
//
//
//class Character_3[A, B, C, t](override val dataModel: DataModel) extends Character_3_refs[A, B, C, t](dataModel) with Character_base with ModelOps_3[A, B, C, t, Character_3, Character_4] {
//  lazy val id          = new Character_4[A, B, C, Long               , Long  ](dataModel.add(id_man       )) with ExprOneMan_4         [A, B, C, Long               , Long  , Character_4, Character_5] with CardOne
//  lazy val name        = new Character_4[A, B, C, String             , String](dataModel.add(name_man     )) with ExprOneMan_4_String  [A, B, C, String             , String, Character_4, Character_5] with CardOne
//  lazy val friends     = new Character_4[A, B, C, Set[Long]          , Long  ](dataModel.add(friends_man  )) with ExprSetMan_4         [A, B, C, Set[Long]          , Long  , Character_4, Character_5] with CardSet
//  lazy val appearsIn   = new Character_4[A, B, C, Set[String]        , String](dataModel.add(appearsIn_man)) with ExprSetMan_4         [A, B, C, Set[String]        , String, Character_4, Character_5] with CardSet
//
//  lazy val name_?      = new Character_4[A, B, C, Option[String]     , String](dataModel.add(name_opt     )) with ExprOneOpt_4         [A, B, C, Option[String]     , String, Character_4, Character_5] with CardOne
//  lazy val friends_?   = new Character_4[A, B, C, Option[Set[Long]]  , Long  ](dataModel.add(friends_opt  )) with ExprSetOpt_4         [A, B, C, Option[Set[Long]]  , Long  , Character_4, Character_5] with CardSet
//  lazy val appearsIn_? = new Character_4[A, B, C, Option[Set[String]], String](dataModel.add(appearsIn_opt)) with ExprSetOpt_4         [A, B, C, Option[Set[String]], String, Character_4, Character_5] with CardSet
//
//  lazy val id_         = new Character_3[A, B, C                     , Long  ](dataModel.add(id_tac       )) with ExprOneTac_3         [A, B, C                     , Long  , Character_3, Character_4] with CardOne
//  lazy val name_       = new Character_3[A, B, C                     , String](dataModel.add(name_tac     )) with ExprOneTac_3_String  [A, B, C                     , String, Character_3, Character_4] with CardOne
//  lazy val friends_    = new Character_3[A, B, C                     , Long  ](dataModel.add(friends_tac  )) with ExprSetTac_3         [A, B, C                     , Long  , Character_3, Character_4] with CardSet
//  lazy val appearsIn_  = new Character_3[A, B, C                     , String](dataModel.add(appearsIn_tac)) with ExprSetTac_3         [A, B, C                     , String, Character_3, Character_4] with CardSet
//
//  override protected def _aggrInt   (kw: Kw                    ) = new Character_3[A, B, Int   , Int   ](toInt    (dataModel, kw    )) with SortAttrs_3[A, B, Int   , Int   , Character_3]
//  override protected def _aggrT     (kw: Kw                    ) = new Character_3[A, B, t     , t     ](asIs     (dataModel, kw    )) with SortAttrs_3[A, B, t     , t     , Character_3]
//  override protected def _aggrDouble(kw: Kw                    ) = new Character_3[A, B, Double, Double](toDouble (dataModel, kw    )) with SortAttrs_3[A, B, Double, Double, Character_3]
//  override protected def _aggrSet   (kw: Kw, n: Option[Int]    ) = new Character_3[A, B, Set[t], t     ](asIs     (dataModel, kw, n ))
//  override protected def _aggrDist  (kw: Kw                    ) = new Character_3[A, B, Set[C], t     ](asIs     (dataModel, kw    ))
//  override protected def _exprOneMan(op: Op, vs: Seq[t] , binding: Boolean       ) = new Character_3[A, B, C     , t     ](addOne   (dataModel, op, vs)) with SortAttrs_3[A, B, C     , t     , Character_3] with CardOne
//  override protected def _exprOneOpt(op: Op, v : Option[t]     ) = new Character_3[A, B, C     , t     ](addOneOpt(dataModel, op, v )) with SortAttrs_3[A, B, C     , t     , Character_3]
//  override protected def _exprOneTac(op: Op, vs: Seq[t]        ) = new Character_3[A, B, C     , t     ](addOne   (dataModel, op, vs)) with CardOne
//  override protected def _exprSet   (op: Op, vs: Set[t]        ) = new Character_3[A, B, C     , t     ](addSet   (dataModel, op, vs)) with CardSet
//  override protected def _exprSetOpt(op: Op, vs: Option[Set[t]]) = new Character_3[A, B, C     , t     ](addSetOpt(dataModel, op, vs))
//  override protected def _sort      (sort: String              ) = new Character_3[A, B, C     , t     ](addSort  (dataModel, sort))
//
//  override protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] & CardOne) = new Character_3[A, B, C,    t](filterAttr(dataModel, op, a)) with SortAttrs_3[A, B, C,    t, Character_3]
//  override protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[C, t, ns1, ns2]          ) = new Character_4[A, B, C, C, t](filterAttr(dataModel, op, a)) with SortAttrs_4[A, B, C, C, t, Character_4]
//  override protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]          ) = new Character_3[A, B, C,    t](filterAttr(dataModel, op, a))
//  override protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]          ) = new Character_4[A, B, C, X, t](filterAttr(dataModel, op, a))
//}
//
//class Character_3_refs[A, B, C, t](override val dataModel: DataModel) extends Molecule_03[A, B, C] {
//  trait NestedInit extends NestedOp_3[A, B, C] with Nested_3[A, B, C] { self: Molecule =>
//    override protected def _nestedMan[NestedTpl](nestedDataModel: DataModel): NestedInit_03[A, B, C, NestedTpl] =
//      new NestedInit_03(DataModel(
//        self.dataModel.elements.init :+ Nested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
//        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes
//      ))
//
//    override protected def _nestedOpt[NestedTpl](nestedDataModel: DataModel): NestedInit_03[A, B, C, NestedTpl] =
//      new NestedInit_03(DataModel(
//        self.dataModel.elements.init :+ OptNested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
//        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes
//      ))
//  }
//
//  object Friends extends Character_3[A, B, C, t](dataModel.add(ast.Ref("Character", "friends", "Character", CardSet, false, List(0, 2, 0)))) with NestedInit
//
//  object _Droid     extends Droid_3    [A, B, C, t](dataModel.add(ast.BackRef("Droid", "Character", List(1, 0))))
//  object _Human     extends Human_3    [A, B, C, t](dataModel.add(ast.BackRef("Human", "Character", List(2, 0))))
//}
//
//
//class Character_4[A, B, C, D, t](override val dataModel: DataModel) extends Character_4_refs[A, B, C, D, t](dataModel) with Character_base with ModelOps_4[A, B, C, D, t, Character_4, Character_5] {
//  lazy val id          = new Character_5[A, B, C, D, Long               , Long  ](dataModel.add(id_man       )) with ExprOneMan_5         [A, B, C, D, Long               , Long  , Character_5, Character_6] with CardOne
//  lazy val name        = new Character_5[A, B, C, D, String             , String](dataModel.add(name_man     )) with ExprOneMan_5_String  [A, B, C, D, String             , String, Character_5, Character_6] with CardOne
//  lazy val friends     = new Character_5[A, B, C, D, Set[Long]          , Long  ](dataModel.add(friends_man  )) with ExprSetMan_5         [A, B, C, D, Set[Long]          , Long  , Character_5, Character_6] with CardSet
//  lazy val appearsIn   = new Character_5[A, B, C, D, Set[String]        , String](dataModel.add(appearsIn_man)) with ExprSetMan_5         [A, B, C, D, Set[String]        , String, Character_5, Character_6] with CardSet
//
//  lazy val name_?      = new Character_5[A, B, C, D, Option[String]     , String](dataModel.add(name_opt     )) with ExprOneOpt_5         [A, B, C, D, Option[String]     , String, Character_5, Character_6] with CardOne
//  lazy val friends_?   = new Character_5[A, B, C, D, Option[Set[Long]]  , Long  ](dataModel.add(friends_opt  )) with ExprSetOpt_5         [A, B, C, D, Option[Set[Long]]  , Long  , Character_5, Character_6] with CardSet
//  lazy val appearsIn_? = new Character_5[A, B, C, D, Option[Set[String]], String](dataModel.add(appearsIn_opt)) with ExprSetOpt_5         [A, B, C, D, Option[Set[String]], String, Character_5, Character_6] with CardSet
//
//  lazy val id_         = new Character_4[A, B, C, D                     , Long  ](dataModel.add(id_tac       )) with ExprOneTac_4         [A, B, C, D                     , Long  , Character_4, Character_5] with CardOne
//  lazy val name_       = new Character_4[A, B, C, D                     , String](dataModel.add(name_tac     )) with ExprOneTac_4_String  [A, B, C, D                     , String, Character_4, Character_5] with CardOne
//  lazy val friends_    = new Character_4[A, B, C, D                     , Long  ](dataModel.add(friends_tac  )) with ExprSetTac_4         [A, B, C, D                     , Long  , Character_4, Character_5] with CardSet
//  lazy val appearsIn_  = new Character_4[A, B, C, D                     , String](dataModel.add(appearsIn_tac)) with ExprSetTac_4         [A, B, C, D                     , String, Character_4, Character_5] with CardSet
//
//  override protected def _aggrInt   (kw: Kw                    ) = new Character_4[A, B, C, Int   , Int   ](toInt    (dataModel, kw    )) with SortAttrs_4[A, B, C, Int   , Int   , Character_4]
//  override protected def _aggrT     (kw: Kw                    ) = new Character_4[A, B, C, t     , t     ](asIs     (dataModel, kw    )) with SortAttrs_4[A, B, C, t     , t     , Character_4]
//  override protected def _aggrDouble(kw: Kw                    ) = new Character_4[A, B, C, Double, Double](toDouble (dataModel, kw    )) with SortAttrs_4[A, B, C, Double, Double, Character_4]
//  override protected def _aggrSet   (kw: Kw, n: Option[Int]    ) = new Character_4[A, B, C, Set[t], t     ](asIs     (dataModel, kw, n ))
//  override protected def _aggrDist  (kw: Kw                    ) = new Character_4[A, B, C, Set[D], t     ](asIs     (dataModel, kw    ))
//  override protected def _exprOneMan(op: Op, vs: Seq[t] , binding: Boolean       ) = new Character_4[A, B, C, D     , t     ](addOne   (dataModel, op, vs)) with SortAttrs_4[A, B, C, D     , t     , Character_4] with CardOne
//  override protected def _exprOneOpt(op: Op, v : Option[t]     ) = new Character_4[A, B, C, D     , t     ](addOneOpt(dataModel, op, v )) with SortAttrs_4[A, B, C, D     , t     , Character_4]
//  override protected def _exprOneTac(op: Op, vs: Seq[t]        ) = new Character_4[A, B, C, D     , t     ](addOne   (dataModel, op, vs)) with CardOne
//  override protected def _exprSet   (op: Op, vs: Set[t]        ) = new Character_4[A, B, C, D     , t     ](addSet   (dataModel, op, vs)) with CardSet
//  override protected def _exprSetOpt(op: Op, vs: Option[Set[t]]) = new Character_4[A, B, C, D     , t     ](addSetOpt(dataModel, op, vs))
//  override protected def _sort      (sort: String              ) = new Character_4[A, B, C, D     , t     ](addSort  (dataModel, sort))
//
//  override protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] & CardOne) = new Character_4[A, B, C, D,    t](filterAttr(dataModel, op, a)) with SortAttrs_4[A, B, C, D,    t, Character_4]
//  override protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[D, t, ns1, ns2]          ) = new Character_5[A, B, C, D, D, t](filterAttr(dataModel, op, a)) with SortAttrs_5[A, B, C, D, D, t, Character_5]
//  override protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]          ) = new Character_4[A, B, C, D,    t](filterAttr(dataModel, op, a))
//  override protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]          ) = new Character_5[A, B, C, D, X, t](filterAttr(dataModel, op, a))
//}
//
//class Character_4_refs[A, B, C, D, t](override val dataModel: DataModel) extends Molecule_04[A, B, C, D] {
//  trait NestedInit extends NestedOp_4[A, B, C, D] with Nested_4[A, B, C, D] { self: Molecule =>
//    override protected def _nestedMan[NestedTpl](nestedDataModel: DataModel): NestedInit_04[A, B, C, D, NestedTpl] =
//      new NestedInit_04(DataModel(
//        self.dataModel.elements.init :+ Nested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
//        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes
//      ))
//
//    override protected def _nestedOpt[NestedTpl](nestedDataModel: DataModel): NestedInit_04[A, B, C, D, NestedTpl] =
//      new NestedInit_04(DataModel(
//        self.dataModel.elements.init :+ OptNested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
//        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes
//      ))
//  }
//
//  object Friends extends Character_4[A, B, C, D, t](dataModel.add(ast.Ref("Character", "friends", "Character", CardSet, false, List(0, 2, 0)))) with NestedInit
//
//  object _Droid     extends Droid_4    [A, B, C, D, t](dataModel.add(ast.BackRef("Droid", "Character", List(1, 0))))
//  object _Human     extends Human_4    [A, B, C, D, t](dataModel.add(ast.BackRef("Human", "Character", List(2, 0))))
//}
//
//
//class Character_5[A, B, C, D, E, t](override val dataModel: DataModel) extends Character_5_refs[A, B, C, D, E, t](dataModel) with Character_base with ModelOps_5[A, B, C, D, E, t, Character_5, Character_6] {
//  lazy val id          = new Character_6[A, B, C, D, E, Long               , Long  ](dataModel.add(id_man       )) with ExprOneMan_6         [A, B, C, D, E, Long               , Long  , Character_6, X8] with CardOne
//  lazy val name        = new Character_6[A, B, C, D, E, String             , String](dataModel.add(name_man     )) with ExprOneMan_6_String  [A, B, C, D, E, String             , String, Character_6, X8] with CardOne
//  lazy val friends     = new Character_6[A, B, C, D, E, Set[Long]          , Long  ](dataModel.add(friends_man  )) with ExprSetMan_6         [A, B, C, D, E, Set[Long]          , Long  , Character_6, X8] with CardSet
//  lazy val appearsIn   = new Character_6[A, B, C, D, E, Set[String]        , String](dataModel.add(appearsIn_man)) with ExprSetMan_6         [A, B, C, D, E, Set[String]        , String, Character_6, X8] with CardSet
//
//  lazy val name_?      = new Character_6[A, B, C, D, E, Option[String]     , String](dataModel.add(name_opt     )) with ExprOneOpt_6         [A, B, C, D, E, Option[String]     , String, Character_6, X8] with CardOne
//  lazy val friends_?   = new Character_6[A, B, C, D, E, Option[Set[Long]]  , Long  ](dataModel.add(friends_opt  )) with ExprSetOpt_6         [A, B, C, D, E, Option[Set[Long]]  , Long  , Character_6, X8] with CardSet
//  lazy val appearsIn_? = new Character_6[A, B, C, D, E, Option[Set[String]], String](dataModel.add(appearsIn_opt)) with ExprSetOpt_6         [A, B, C, D, E, Option[Set[String]], String, Character_6, X8] with CardSet
//
//  lazy val id_         = new Character_5[A, B, C, D, E                     , Long  ](dataModel.add(id_tac       )) with ExprOneTac_5         [A, B, C, D, E                     , Long  , Character_5, Character_6] with CardOne
//  lazy val name_       = new Character_5[A, B, C, D, E                     , String](dataModel.add(name_tac     )) with ExprOneTac_5_String  [A, B, C, D, E                     , String, Character_5, Character_6] with CardOne
//  lazy val friends_    = new Character_5[A, B, C, D, E                     , Long  ](dataModel.add(friends_tac  )) with ExprSetTac_5         [A, B, C, D, E                     , Long  , Character_5, Character_6] with CardSet
//  lazy val appearsIn_  = new Character_5[A, B, C, D, E                     , String](dataModel.add(appearsIn_tac)) with ExprSetTac_5         [A, B, C, D, E                     , String, Character_5, Character_6] with CardSet
//
//  override protected def _aggrInt   (kw: Kw                    ) = new Character_5[A, B, C, D, Int   , Int   ](toInt    (dataModel, kw    )) with SortAttrs_5[A, B, C, D, Int   , Int   , Character_5]
//  override protected def _aggrT     (kw: Kw                    ) = new Character_5[A, B, C, D, t     , t     ](asIs     (dataModel, kw    )) with SortAttrs_5[A, B, C, D, t     , t     , Character_5]
//  override protected def _aggrDouble(kw: Kw                    ) = new Character_5[A, B, C, D, Double, Double](toDouble (dataModel, kw    )) with SortAttrs_5[A, B, C, D, Double, Double, Character_5]
//  override protected def _aggrSet   (kw: Kw, n: Option[Int]    ) = new Character_5[A, B, C, D, Set[t], t     ](asIs     (dataModel, kw, n ))
//  override protected def _aggrDist  (kw: Kw                    ) = new Character_5[A, B, C, D, Set[E], t     ](asIs     (dataModel, kw    ))
//  override protected def _exprOneMan(op: Op, vs: Seq[t] , binding: Boolean       ) = new Character_5[A, B, C, D, E     , t     ](addOne   (dataModel, op, vs)) with SortAttrs_5[A, B, C, D, E     , t     , Character_5] with CardOne
//  override protected def _exprOneOpt(op: Op, v : Option[t]     ) = new Character_5[A, B, C, D, E     , t     ](addOneOpt(dataModel, op, v )) with SortAttrs_5[A, B, C, D, E     , t     , Character_5]
//  override protected def _exprOneTac(op: Op, vs: Seq[t]        ) = new Character_5[A, B, C, D, E     , t     ](addOne   (dataModel, op, vs)) with CardOne
//  override protected def _exprSet   (op: Op, vs: Set[t]        ) = new Character_5[A, B, C, D, E     , t     ](addSet   (dataModel, op, vs)) with CardSet
//  override protected def _exprSetOpt(op: Op, vs: Option[Set[t]]) = new Character_5[A, B, C, D, E     , t     ](addSetOpt(dataModel, op, vs))
//  override protected def _sort      (sort: String              ) = new Character_5[A, B, C, D, E     , t     ](addSort  (dataModel, sort))
//
//  override protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] & CardOne) = new Character_5[A, B, C, D, E,    t](filterAttr(dataModel, op, a)) with SortAttrs_5[A, B, C, D, E,    t, Character_5]
//  override protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[E, t, ns1, ns2]          ) = new Character_6[A, B, C, D, E, E, t](filterAttr(dataModel, op, a)) with SortAttrs_6[A, B, C, D, E, E, t, Character_6]
//  override protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]          ) = new Character_5[A, B, C, D, E,    t](filterAttr(dataModel, op, a))
//  override protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]          ) = new Character_6[A, B, C, D, E, X, t](filterAttr(dataModel, op, a))
//}
//
//class Character_5_refs[A, B, C, D, E, t](override val dataModel: DataModel) extends Molecule_05[A, B, C, D, E] {
//  trait NestedInit extends NestedOp_5[A, B, C, D, E] with Nested_5[A, B, C, D, E] { self: Molecule =>
//    override protected def _nestedMan[NestedTpl](nestedDataModel: DataModel): NestedInit_05[A, B, C, D, E, NestedTpl] =
//      new NestedInit_05(DataModel(
//        self.dataModel.elements.init :+ Nested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
//        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes
//      ))
//
//    override protected def _nestedOpt[NestedTpl](nestedDataModel: DataModel): NestedInit_05[A, B, C, D, E, NestedTpl] =
//      new NestedInit_05(DataModel(
//        self.dataModel.elements.init :+ OptNested(self.dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
//        self.dataModel.attrIndexes ++ nestedDataModel.attrIndexes
//      ))
//  }
//
//  object Friends extends Character_5[A, B, C, D, E, t](dataModel.add(ast.Ref("Character", "friends", "Character", CardSet, false, List(0, 2, 0)))) with NestedInit
//
//  object _Droid     extends Droid_5    [A, B, C, D, E, t](dataModel.add(ast.BackRef("Droid", "Character", List(1, 0))))
//  object _Human     extends Human_5    [A, B, C, D, E, t](dataModel.add(ast.BackRef("Human", "Character", List(2, 0))))
//}
//
//
//class Character_6[A, B, C, D, E, F, t](override val dataModel: DataModel) extends Character_6_refs[A, B, C, D, E, F, t](dataModel) with Character_base with ModelOps_6[A, B, C, D, E, F, t, Character_6, X8] {
//  lazy val id_         = new Character_6[A, B, C, D, E, F, Long  ](dataModel.add(id_tac       )) with ExprOneTac_6         [A, B, C, D, E, F, Long  , Character_6, X8] with CardOne
//  lazy val name_       = new Character_6[A, B, C, D, E, F, String](dataModel.add(name_tac     )) with ExprOneTac_6_String  [A, B, C, D, E, F, String, Character_6, X8] with CardOne
//  lazy val friends_    = new Character_6[A, B, C, D, E, F, Long  ](dataModel.add(friends_tac  )) with ExprSetTac_6         [A, B, C, D, E, F, Long  , Character_6, X8] with CardSet
//  lazy val appearsIn_  = new Character_6[A, B, C, D, E, F, String](dataModel.add(appearsIn_tac)) with ExprSetTac_6         [A, B, C, D, E, F, String, Character_6, X8] with CardSet
//
//  override protected def _aggrInt   (kw: Kw                    ) = new Character_6[A, B, C, D, E, Int   , Int   ](toInt    (dataModel, kw    )) with SortAttrs_6[A, B, C, D, E, Int   , Int   , Character_6]
//  override protected def _aggrT     (kw: Kw                    ) = new Character_6[A, B, C, D, E, t     , t     ](asIs     (dataModel, kw    )) with SortAttrs_6[A, B, C, D, E, t     , t     , Character_6]
//  override protected def _aggrDouble(kw: Kw                    ) = new Character_6[A, B, C, D, E, Double, Double](toDouble (dataModel, kw    )) with SortAttrs_6[A, B, C, D, E, Double, Double, Character_6]
//  override protected def _aggrSet   (kw: Kw, n: Option[Int]    ) = new Character_6[A, B, C, D, E, Set[t], t     ](asIs     (dataModel, kw, n ))
//  override protected def _aggrDist  (kw: Kw                    ) = new Character_6[A, B, C, D, E, Set[F], t     ](asIs     (dataModel, kw    ))
//  override protected def _exprOneMan(op: Op, vs: Seq[t] , binding: Boolean       ) = new Character_6[A, B, C, D, E, F     , t     ](addOne   (dataModel, op, vs)) with SortAttrs_6[A, B, C, D, E, F     , t     , Character_6] with CardOne
//  override protected def _exprOneOpt(op: Op, v : Option[t]     ) = new Character_6[A, B, C, D, E, F     , t     ](addOneOpt(dataModel, op, v )) with SortAttrs_6[A, B, C, D, E, F     , t     , Character_6]
//  override protected def _exprOneTac(op: Op, vs: Seq[t]        ) = new Character_6[A, B, C, D, E, F     , t     ](addOne   (dataModel, op, vs)) with CardOne
//  override protected def _exprSet   (op: Op, vs: Set[t]        ) = new Character_6[A, B, C, D, E, F     , t     ](addSet   (dataModel, op, vs)) with CardSet
//  override protected def _exprSetOpt(op: Op, vs: Option[Set[t]]) = new Character_6[A, B, C, D, E, F     , t     ](addSetOpt(dataModel, op, vs))
//  override protected def _sort      (sort: String              ) = new Character_6[A, B, C, D, E, F     , t     ](addSort  (dataModel, sort))
//
//  override protected def _attrSortTac[ns1[_], ns2[_, _]](op: Op, a: ModelOps_0[t, ns1, ns2] & CardOne) = new Character_6[A, B, C, D, E, F, t](filterAttr(dataModel, op, a)) with SortAttrs_6[A, B, C, D, E, F, t, Character_6]
//  override protected def _attrTac    [ns1[_], ns2[_, _]](op: Op, a: ModelOps_0[t, ns1, ns2]          ) = new Character_6[A, B, C, D, E, F, t](filterAttr(dataModel, op, a))
//}
//
//class Character_6_refs[A, B, C, D, E, F, t](override val dataModel: DataModel) extends Molecule_06[A, B, C, D, E, F] {
//  object Friends extends Character_6[A, B, C, D, E, F, t](dataModel.add(ast.Ref("Character", "friends", "Character", CardSet, false, List(0, 2, 0))))
//
//  object _Droid     extends Droid_6    [A, B, C, D, E, F, t](dataModel.add(ast.BackRef("Droid", "Character", List(1, 0))))
//  object _Human     extends Human_6    [A, B, C, D, E, F, t](dataModel.add(ast.BackRef("Human", "Character", List(2, 0))))
//}
//
