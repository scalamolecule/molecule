///*
//* AUTO-GENERATED Molecule DSL boilerplate code for entity `Character`
//*
//* To change:
//* 1. Edit data model in molecule.graphql.client.dsl.dataModel.Starwars
//* 2. `sbt compile -Dmolecule=true`
//*/
//package molecule.graphql.client.dsl.Starwars
//
//import java.time.*
//import molecule.base.ast.*
//import molecule.core.api.Keywords.*
//import molecule.core.api.*
//import molecule.core.api.expression.*
//import molecule.core.ast.DataModel
//import molecule.core.ast.DataModel._
//import scala.reflect.ClassTag
//
//
//trait Character_base {
//  protected lazy val id_man       : AttrOneManID     = AttrOneManID    ("Character", "id"       , coord = Seq(0, 0   ))
//  protected lazy val name_man     : AttrOneManString = AttrOneManString("Character", "name"     , coord = Seq(0, 1   ))
//  protected lazy val friends_man  : AttrSetManID     = AttrSetManID    ("Character", "friends"  , coord = Seq(0, 2, 0), ref = Some("Character"))
//  protected lazy val appearsIn_man: AttrSetManString = AttrSetManString("Character", "appearsIn", coord = Seq(0, 3   ), validator = Some(validation_appearsIn))
//
//  protected lazy val name_opt     : AttrOneOptString = AttrOneOptString("Character", "name"     , coord = Seq(0, 1   ))
//  protected lazy val friends_opt  : AttrSetOptID     = AttrSetOptID    ("Character", "friends"  , coord = Seq(0, 2, 0), ref = Some("Character"))
//  protected lazy val appearsIn_opt: AttrSetOptString = AttrSetOptString("Character", "appearsIn", coord = Seq(0, 3   ), validator = Some(validation_appearsIn))
//
//  protected lazy val id_tac       : AttrOneTacID     = AttrOneTacID    ("Character", "id"       , coord = Seq(0, 0   ))
//  protected lazy val name_tac     : AttrOneTacString = AttrOneTacString("Character", "name"     , coord = Seq(0, 1   ))
//  protected lazy val friends_tac  : AttrSetTacID     = AttrSetTacID    ("Character", "friends"  , coord = Seq(0, 2, 0), ref = Some("Character"))
//  protected lazy val appearsIn_tac: AttrSetTacString = AttrSetTacString("Character", "appearsIn", coord = Seq(0, 3   ), validator = Some(validation_appearsIn))
//
//  private lazy val validation_appearsIn = new ValidateString {
//    override def validate(v: String): Seq[String] = {
//      val ok: String => Boolean = v => Seq("NEWHOPE", "EMPIRE", "JEDI").contains(v)
//      if (ok(v)) Nil else Seq(s"""Value `$v` is not one of the allowed values in Seq("NEWHOPE", "EMPIRE", "JEDI")""")
//    }
//  }
//}
//
////private[Starwars] object Character extends Character_0[Nothing](Nil) {
//private object Character extends Character_0[Nothing](Nil) {
////object Character extends Character_0[Nothing](Nil) {
//  final def apply(id: String, ids: String*)                       = new Character_0[String](List(AttrOneTacID("Character", "id", Eq, id +: ids                  , coord = Seq(0, 0))))
//  final def apply(ids: Iterable[String])                          = new Character_0[String](List(AttrOneTacID("Character", "id", Eq, ids.toSeq                  , coord = Seq(0, 0))))
//  final def apply(id: Long, ids: Long*)                           = new Character_0[String](List(AttrOneTacID("Character", "id", Eq, (id +: ids).map(_.toString), coord = Seq(0, 0))))
//  final def apply(ids: Iterable[Long])(implicit x: DummyImplicit) = new Character_0[String](List(AttrOneTacID("Character", "id", Eq, ids.toSeq.map(_.toString)  , coord = Seq(0, 0))))
//}
//
//
//class Character_0[t: ClassTag](override val elements: List[Element]) extends Character_base with ModelOps_0[t, Character_0, Character_1] {
//  lazy val id          = new Character_1[String             , String](elements :+ id_man       ) with ExprOneMan_1        [String             , String, Character_1, Character_2] with CardOne
//  lazy val name        = new Character_1[String             , String](elements :+ name_man     ) with ExprOneMan_1_String [String             , String, Character_1, Character_2] with CardOne
//  lazy val friends     = new Character_1[Set[String]        , String](elements :+ friends_man  ) with ExprSetMan_1        [Set[String]        , String, Character_1, Character_2] with CardSet
//  lazy val appearsIn   = new Character_1[Set[String]        , String](elements :+ appearsIn_man) with ExprSetMan_1        [Set[String]        , String, Character_1, Character_2] with CardSet
//
//  lazy val name_?      = new Character_1[Option[String]     , String](elements :+ name_opt     ) with ExprOneOpt_1        [Option[String]     , String, Character_1, Character_2] with CardOne
//  lazy val friends_?   = new Character_1[Option[Set[String]], String](elements :+ friends_opt  ) with ExprSetOpt_1        [Option[Set[String]], String, Character_1, Character_2] with CardSet
//  lazy val appearsIn_? = new Character_1[Option[Set[String]], String](elements :+ appearsIn_opt) with ExprSetOpt_1        [Option[Set[String]], String, Character_1, Character_2] with CardSet
//
//  lazy val id_         = new Character_0[                     String](elements :+ id_tac       ) with ExprOneTac_0        [                     String, Character_0, Character_1] with CardOne
//  lazy val name_       = new Character_0[                     String](elements :+ name_tac     ) with ExprOneTac_0_String [                     String, Character_0, Character_1] with CardOne
//  lazy val friends_    = new Character_0[                     String](elements :+ friends_tac  ) with ExprSetTac_0        [                     String, Character_0, Character_1] with CardSet
//  lazy val appearsIn_  = new Character_0[                     String](elements :+ appearsIn_tac) with ExprSetTac_0        [                     String, Character_0, Character_1] with CardSet
//
//  override protected def _exprOneTac(op: Op, vs: Seq[t]     ) = new Character_0[t](addOne(elements, op, vs)) with CardOne
//  override protected def _exprSetTac(op: Op, vs: Seq[Set[t]]) = new Character_0[t](addSet(elements, op, vs)) with CardSet
//
//  override protected def _attrTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]) = new Character_0[   t](filterAttr(elements, op, a))
//  override protected def _attrMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]) = new Character_1[X, t](filterAttr(elements, op, a))
//
//  trait NestedInit extends NestedOp_0 with Nested_0 { self: Molecule =>
//    override protected def _nestedMan[NestedTpl](nestedElements: List[Element]): NestedInit_00[NestedTpl] = new NestedInit_00(self.elements.init :+ Nested   (self.elements.last.asInstanceOf[Ref], nestedElements))
//    override protected def _nestedOpt[NestedTpl](nestedElements: List[Element]): NestedInit_00[NestedTpl] = new NestedInit_00(self.elements.init :+ NestedOpt(self.elements.last.asInstanceOf[Ref], nestedElements))
//  }
//
//  object Friends extends Character_0[t](elements :+ DataModel.Ref("Character", "friends", "Character", CardSet, false, Seq(0, 2, 0))) with NestedInit
//
//  object _Droid     extends Droid_0    [t](elements :+ DataModel.BackRef("Droid", "Character", Seq(1, 0)))
//  object _Human     extends Human_0    [t](elements :+ DataModel.BackRef("Human", "Character", Seq(2, 0)))
//}
//
//
//class Character_1[A, t](override val elements: List[Element]) extends Character_base with ModelOps_1[A, t, Character_1, Character_2] {
//
//
////  lazy val __typename        = new Character_2[A, String             , String](elements :+ name_man     ) with ExprOneMan_2_String [A, String             , String, Character_2, Character_3] with CardOne
//  lazy val __typename        = new Character_2[A, String             , String](elements :+ name_man     ) with CardOne
//
//
//  lazy val id          = new Character_2[A, String             , String](elements :+ id_man       ) with ExprOneMan_2        [A, String             , String, Character_2, Character_3] with CardOne
//  lazy val name        = new Character_2[A, String             , String](elements :+ name_man     ) with ExprOneMan_2_String [A, String             , String, Character_2, Character_3] with CardOne
//  lazy val friends     = new Character_2[A, Set[String]        , String](elements :+ friends_man  ) with ExprSetMan_2        [A, Set[String]        , String, Character_2, Character_3] with CardSet
//  lazy val appearsIn   = new Character_2[A, Set[String]        , String](elements :+ appearsIn_man) with ExprSetMan_2        [A, Set[String]        , String, Character_2, Character_3] with CardSet
//
//  lazy val name_?      = new Character_2[A, Option[String]     , String](elements :+ name_opt     ) with ExprOneOpt_2        [A, Option[String]     , String, Character_2, Character_3] with CardOne
//  lazy val friends_?   = new Character_2[A, Option[Set[String]], String](elements :+ friends_opt  ) with ExprSetOpt_2        [A, Option[Set[String]], String, Character_2, Character_3] with CardSet
//  lazy val appearsIn_? = new Character_2[A, Option[Set[String]], String](elements :+ appearsIn_opt) with ExprSetOpt_2        [A, Option[Set[String]], String, Character_2, Character_3] with CardSet
//
//  lazy val id_         = new Character_1[A                     , String](elements :+ id_tac       ) with ExprOneTac_1        [A                     , String, Character_1, Character_2] with CardOne
//  lazy val name_       = new Character_1[A                     , String](elements :+ name_tac     ) with ExprOneTac_1_String [A                     , String, Character_1, Character_2] with CardOne
//  lazy val friends_    = new Character_1[A                     , String](elements :+ friends_tac  ) with ExprSetTac_1        [A                     , String, Character_1, Character_2] with CardSet
//  lazy val appearsIn_  = new Character_1[A                     , String](elements :+ appearsIn_tac) with ExprSetTac_1        [A                     , String, Character_1, Character_2] with CardSet
//
//  override protected def _aggrInt   (kw: Kw                         ) = new Character_1[Int   , Int   ](toInt    (elements, kw    )) with SortAttrs_1[Int   , Int   , Character_1]
//  override protected def _aggrDouble(kw: Kw                         ) = new Character_1[Double, Double](toDouble (elements, kw    )) with SortAttrs_1[Double, Double, Character_1]
//  override protected def _aggrDist  (kw: Kw                         ) = new Character_1[Set[A], t     ](asIs     (elements, kw    ))
//  override protected def _aggrSet   (kw: Kw, n: Option[Int]         ) = new Character_1[Set[t], t     ](asIs     (elements, kw, n ))
//  override protected def _aggrTsort (kw: Kw                         ) = new Character_1[A     , t     ](asIs     (elements, kw    )) with SortAttrs_1[A     , t     , Character_1]
//  override protected def _aggrT     (kw: Kw                         ) = new Character_1[A     , t     ](asIs     (elements, kw    ))
//  override protected def _exprOneMan(op: Op, vs: Seq[t]             ) = new Character_1[A     , t     ](addOne   (elements, op, vs)) with SortAttrs_1[A     , t     , Character_1] with CardOne
//  override protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]     ) = new Character_1[A     , t     ](addOptOne(elements, op, vs)) with SortAttrs_1[A     , t     , Character_1]
//  override protected def _exprOneTac(op: Op, vs: Seq[t]             ) = new Character_1[A     , t     ](addOne   (elements, op, vs)) with CardOne
//  override protected def _exprSetMan(op: Op, vs: Seq[Set[t]]        ) = new Character_1[A     , t     ](addSet   (elements, op, vs)) with CardSet
//  override protected def _exprSetOpt(op: Op, vs: Option[Seq[Set[t]]]) = new Character_1[A     , t     ](addOptSet(elements, op, vs))
//  override protected def _exprSetTac(op: Op, vs: Seq[Set[t]]        ) = new Character_1[A     , t     ](addSet   (elements, op, vs)) with CardSet
//  override protected def _sort      (sort: String                   ) = new Character_1[A     , t     ](addSort  (elements, sort  ))
//
//  override protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne) = new Character_1[A,    t](filterAttr(elements, op, a)) with SortAttrs_1[A,    t, Character_1]
//  override protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[A, t, ns1, ns2]             ) = new Character_2[A, A, t](filterAttr(elements, op, a)) with SortAttrs_2[A, A, t, Character_2]
//  override protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ) = new Character_1[A,    t](filterAttr(elements, op, a))
//  override protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ) = new Character_2[A, X, t](filterAttr(elements, op, a))
//
//  trait NestedInit extends NestedOp_1[A] with Nested_1[A] { self: Molecule =>
//    override protected def _nestedMan[NestedTpl](nestedElements: List[Element]): NestedInit_01[A, NestedTpl] = new NestedInit_01(self.elements.init :+ Nested   (self.elements.last.asInstanceOf[Ref], nestedElements))
//    override protected def _nestedOpt[NestedTpl](nestedElements: List[Element]): NestedInit_01[A, NestedTpl] = new NestedInit_01(self.elements.init :+ NestedOpt(self.elements.last.asInstanceOf[Ref], nestedElements))
//  }
//
//  object Friends extends Character_1[A, t](elements :+ DataModel.Ref("Character", "friends", "Character", CardSet, false, Seq(0, 2, 0))) with NestedInit
//
//  object _Droid     extends Droid_1    [A, t](elements :+ DataModel.BackRef("Droid", "Character", Seq(1, 0)))
//  object _Human     extends Human_1    [A, t](elements :+ DataModel.BackRef("Human", "Character", Seq(2, 0)))
//}
//
//
//class Character_2[A, B, t](override val elements: List[Element]) extends Character_base with ModelOps_2[A, B, t, Character_2, Character_3] {
//  lazy val id          = new Character_3[A, B, String             , String](elements :+ id_man       ) with ExprOneMan_3        [A, B, String             , String, Character_3, Character_4] with CardOne
//  lazy val name        = new Character_3[A, B, String             , String](elements :+ name_man     ) with ExprOneMan_3_String [A, B, String             , String, Character_3, Character_4] with CardOne
//  lazy val friends     = new Character_3[A, B, Set[String]        , String](elements :+ friends_man  ) with ExprSetMan_3        [A, B, Set[String]        , String, Character_3, Character_4] with CardSet
//  lazy val appearsIn   = new Character_3[A, B, Set[String]        , String](elements :+ appearsIn_man) with ExprSetMan_3        [A, B, Set[String]        , String, Character_3, Character_4] with CardSet
//
//  lazy val name_?      = new Character_3[A, B, Option[String]     , String](elements :+ name_opt     ) with ExprOneOpt_3        [A, B, Option[String]     , String, Character_3, Character_4] with CardOne
//  lazy val friends_?   = new Character_3[A, B, Option[Set[String]], String](elements :+ friends_opt  ) with ExprSetOpt_3        [A, B, Option[Set[String]], String, Character_3, Character_4] with CardSet
//  lazy val appearsIn_? = new Character_3[A, B, Option[Set[String]], String](elements :+ appearsIn_opt) with ExprSetOpt_3        [A, B, Option[Set[String]], String, Character_3, Character_4] with CardSet
//
//  lazy val id_         = new Character_2[A, B                     , String](elements :+ id_tac       ) with ExprOneTac_2        [A, B                     , String, Character_2, Character_3] with CardOne
//  lazy val name_       = new Character_2[A, B                     , String](elements :+ name_tac     ) with ExprOneTac_2_String [A, B                     , String, Character_2, Character_3] with CardOne
//  lazy val friends_    = new Character_2[A, B                     , String](elements :+ friends_tac  ) with ExprSetTac_2        [A, B                     , String, Character_2, Character_3] with CardSet
//  lazy val appearsIn_  = new Character_2[A, B                     , String](elements :+ appearsIn_tac) with ExprSetTac_2        [A, B                     , String, Character_2, Character_3] with CardSet
//
//  override protected def _aggrInt   (kw: Kw                         ) = new Character_2[A, Int   , Int   ](toInt    (elements, kw    )) with SortAttrs_2[A, Int   , Int   , Character_2]
//  override protected def _aggrDouble(kw: Kw                         ) = new Character_2[A, Double, Double](toDouble (elements, kw    )) with SortAttrs_2[A, Double, Double, Character_2]
//  override protected def _aggrDist  (kw: Kw                         ) = new Character_2[A, Set[B], t     ](asIs     (elements, kw    ))
//  override protected def _aggrSet   (kw: Kw, n: Option[Int]         ) = new Character_2[A, Set[t], t     ](asIs     (elements, kw, n ))
//  override protected def _aggrTsort (kw: Kw                         ) = new Character_2[A, B     , t     ](asIs     (elements, kw    )) with SortAttrs_2[A, B     , t     , Character_2]
//  override protected def _aggrT     (kw: Kw                         ) = new Character_2[A, B     , t     ](asIs     (elements, kw    ))
//  override protected def _exprOneMan(op: Op, vs: Seq[t]             ) = new Character_2[A, B     , t     ](addOne   (elements, op, vs)) with SortAttrs_2[A, B     , t     , Character_2] with CardOne
//  override protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]     ) = new Character_2[A, B     , t     ](addOptOne(elements, op, vs)) with SortAttrs_2[A, B     , t     , Character_2]
//  override protected def _exprOneTac(op: Op, vs: Seq[t]             ) = new Character_2[A, B     , t     ](addOne   (elements, op, vs)) with CardOne
//  override protected def _exprSetMan(op: Op, vs: Seq[Set[t]]        ) = new Character_2[A, B     , t     ](addSet   (elements, op, vs)) with CardSet
//  override protected def _exprSetOpt(op: Op, vs: Option[Seq[Set[t]]]) = new Character_2[A, B     , t     ](addOptSet(elements, op, vs))
//  override protected def _exprSetTac(op: Op, vs: Seq[Set[t]]        ) = new Character_2[A, B     , t     ](addSet   (elements, op, vs)) with CardSet
//  override protected def _sort      (sort: String                   ) = new Character_2[A, B     , t     ](addSort  (elements, sort  ))
//
//  override protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne) = new Character_2[A, B,    t](filterAttr(elements, op, a)) with SortAttrs_2[A, B,    t, Character_2]
//  override protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[B, t, ns1, ns2]             ) = new Character_3[A, B, B, t](filterAttr(elements, op, a)) with SortAttrs_3[A, B, B, t, Character_3]
//  override protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ) = new Character_2[A, B,    t](filterAttr(elements, op, a))
//  override protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ) = new Character_3[A, B, X, t](filterAttr(elements, op, a))
//
//  trait NestedInit extends NestedOp_2[A, B] with Nested_2[A, B] { self: Molecule =>
//    override protected def _nestedMan[NestedTpl](nestedElements: List[Element]): NestedInit_02[A, B, NestedTpl] = new NestedInit_02(self.elements.init :+ Nested   (self.elements.last.asInstanceOf[Ref], nestedElements))
//    override protected def _nestedOpt[NestedTpl](nestedElements: List[Element]): NestedInit_02[A, B, NestedTpl] = new NestedInit_02(self.elements.init :+ NestedOpt(self.elements.last.asInstanceOf[Ref], nestedElements))
//  }
//
//  object Friends extends Character_2[A, B, t](elements :+ DataModel.Ref("Character", "friends", "Character", CardSet, false, Seq(0, 2, 0))) with NestedInit
//
//  object _Droid     extends Droid_2    [A, B, t](elements :+ DataModel.BackRef("Droid", "Character", Seq(1, 0)))
//  object _Human     extends Human_2    [A, B, t](elements :+ DataModel.BackRef("Human", "Character", Seq(2, 0)))
//}
//
//
//class Character_3[A, B, C, t](override val elements: List[Element]) extends Character_base with ModelOps_3[A, B, C, t, Character_3, Character_4] {
//  lazy val id          = new Character_4[A, B, C, String             , String](elements :+ id_man       ) with ExprOneMan_4        [A, B, C, String             , String, Character_4, Character_5] with CardOne
//  lazy val name        = new Character_4[A, B, C, String             , String](elements :+ name_man     ) with ExprOneMan_4_String [A, B, C, String             , String, Character_4, Character_5] with CardOne
//  lazy val friends     = new Character_4[A, B, C, Set[String]        , String](elements :+ friends_man  ) with ExprSetMan_4        [A, B, C, Set[String]        , String, Character_4, Character_5] with CardSet
//  lazy val appearsIn   = new Character_4[A, B, C, Set[String]        , String](elements :+ appearsIn_man) with ExprSetMan_4        [A, B, C, Set[String]        , String, Character_4, Character_5] with CardSet
//
//  lazy val name_?      = new Character_4[A, B, C, Option[String]     , String](elements :+ name_opt     ) with ExprOneOpt_4        [A, B, C, Option[String]     , String, Character_4, Character_5] with CardOne
//  lazy val friends_?   = new Character_4[A, B, C, Option[Set[String]], String](elements :+ friends_opt  ) with ExprSetOpt_4        [A, B, C, Option[Set[String]], String, Character_4, Character_5] with CardSet
//  lazy val appearsIn_? = new Character_4[A, B, C, Option[Set[String]], String](elements :+ appearsIn_opt) with ExprSetOpt_4        [A, B, C, Option[Set[String]], String, Character_4, Character_5] with CardSet
//
//  lazy val id_         = new Character_3[A, B, C                     , String](elements :+ id_tac       ) with ExprOneTac_3        [A, B, C                     , String, Character_3, Character_4] with CardOne
//  lazy val name_       = new Character_3[A, B, C                     , String](elements :+ name_tac     ) with ExprOneTac_3_String [A, B, C                     , String, Character_3, Character_4] with CardOne
//  lazy val friends_    = new Character_3[A, B, C                     , String](elements :+ friends_tac  ) with ExprSetTac_3        [A, B, C                     , String, Character_3, Character_4] with CardSet
//  lazy val appearsIn_  = new Character_3[A, B, C                     , String](elements :+ appearsIn_tac) with ExprSetTac_3        [A, B, C                     , String, Character_3, Character_4] with CardSet
//
//  override protected def _aggrInt   (kw: Kw                         ) = new Character_3[A, B, Int   , Int   ](toInt    (elements, kw    )) with SortAttrs_3[A, B, Int   , Int   , Character_3]
//  override protected def _aggrDouble(kw: Kw                         ) = new Character_3[A, B, Double, Double](toDouble (elements, kw    )) with SortAttrs_3[A, B, Double, Double, Character_3]
//  override protected def _aggrDist  (kw: Kw                         ) = new Character_3[A, B, Set[C], t     ](asIs     (elements, kw    ))
//  override protected def _aggrSet   (kw: Kw, n: Option[Int]         ) = new Character_3[A, B, Set[t], t     ](asIs     (elements, kw, n ))
//  override protected def _aggrTsort (kw: Kw                         ) = new Character_3[A, B, C     , t     ](asIs     (elements, kw    )) with SortAttrs_3[A, B, C     , t     , Character_3]
//  override protected def _aggrT     (kw: Kw                         ) = new Character_3[A, B, C     , t     ](asIs     (elements, kw    ))
//  override protected def _exprOneMan(op: Op, vs: Seq[t]             ) = new Character_3[A, B, C     , t     ](addOne   (elements, op, vs)) with SortAttrs_3[A, B, C     , t     , Character_3] with CardOne
//  override protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]     ) = new Character_3[A, B, C     , t     ](addOptOne(elements, op, vs)) with SortAttrs_3[A, B, C     , t     , Character_3]
//  override protected def _exprOneTac(op: Op, vs: Seq[t]             ) = new Character_3[A, B, C     , t     ](addOne   (elements, op, vs)) with CardOne
//  override protected def _exprSetMan(op: Op, vs: Seq[Set[t]]        ) = new Character_3[A, B, C     , t     ](addSet   (elements, op, vs)) with CardSet
//  override protected def _exprSetOpt(op: Op, vs: Option[Seq[Set[t]]]) = new Character_3[A, B, C     , t     ](addOptSet(elements, op, vs))
//  override protected def _exprSetTac(op: Op, vs: Seq[Set[t]]        ) = new Character_3[A, B, C     , t     ](addSet   (elements, op, vs)) with CardSet
//  override protected def _sort      (sort: String                   ) = new Character_3[A, B, C     , t     ](addSort  (elements, sort  ))
//
//  override protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne) = new Character_3[A, B, C,    t](filterAttr(elements, op, a)) with SortAttrs_3[A, B, C,    t, Character_3]
//  override protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[C, t, ns1, ns2]             ) = new Character_4[A, B, C, C, t](filterAttr(elements, op, a)) with SortAttrs_4[A, B, C, C, t, Character_4]
//  override protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ) = new Character_3[A, B, C,    t](filterAttr(elements, op, a))
//  override protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ) = new Character_4[A, B, C, X, t](filterAttr(elements, op, a))
//
//  trait NestedInit extends NestedOp_3[A, B, C] with Nested_3[A, B, C] { self: Molecule =>
//    override protected def _nestedMan[NestedTpl](nestedElements: List[Element]): NestedInit_03[A, B, C, NestedTpl] = new NestedInit_03(self.elements.init :+ Nested   (self.elements.last.asInstanceOf[Ref], nestedElements))
//    override protected def _nestedOpt[NestedTpl](nestedElements: List[Element]): NestedInit_03[A, B, C, NestedTpl] = new NestedInit_03(self.elements.init :+ NestedOpt(self.elements.last.asInstanceOf[Ref], nestedElements))
//  }
//
//  object Friends extends Character_3[A, B, C, t](elements :+ DataModel.Ref("Character", "friends", "Character", CardSet, false, Seq(0, 2, 0))) with NestedInit
//
//  object _Droid     extends Droid_3    [A, B, C, t](elements :+ DataModel.BackRef("Droid", "Character", Seq(1, 0)))
//  object _Human     extends Human_3    [A, B, C, t](elements :+ DataModel.BackRef("Human", "Character", Seq(2, 0)))
//}
//
//
//class Character_4[A, B, C, D, t](override val elements: List[Element]) extends Character_base with ModelOps_4[A, B, C, D, t, Character_4, Character_5] {
//  lazy val id          = new Character_5[A, B, C, D, String             , String](elements :+ id_man       ) with ExprOneMan_5        [A, B, C, D, String             , String, Character_5, Character_6] with CardOne
//  lazy val name        = new Character_5[A, B, C, D, String             , String](elements :+ name_man     ) with ExprOneMan_5_String [A, B, C, D, String             , String, Character_5, Character_6] with CardOne
//  lazy val friends     = new Character_5[A, B, C, D, Set[String]        , String](elements :+ friends_man  ) with ExprSetMan_5        [A, B, C, D, Set[String]        , String, Character_5, Character_6] with CardSet
//  lazy val appearsIn   = new Character_5[A, B, C, D, Set[String]        , String](elements :+ appearsIn_man) with ExprSetMan_5        [A, B, C, D, Set[String]        , String, Character_5, Character_6] with CardSet
//
//  lazy val name_?      = new Character_5[A, B, C, D, Option[String]     , String](elements :+ name_opt     ) with ExprOneOpt_5        [A, B, C, D, Option[String]     , String, Character_5, Character_6] with CardOne
//  lazy val friends_?   = new Character_5[A, B, C, D, Option[Set[String]], String](elements :+ friends_opt  ) with ExprSetOpt_5        [A, B, C, D, Option[Set[String]], String, Character_5, Character_6] with CardSet
//  lazy val appearsIn_? = new Character_5[A, B, C, D, Option[Set[String]], String](elements :+ appearsIn_opt) with ExprSetOpt_5        [A, B, C, D, Option[Set[String]], String, Character_5, Character_6] with CardSet
//
//  lazy val id_         = new Character_4[A, B, C, D                     , String](elements :+ id_tac       ) with ExprOneTac_4        [A, B, C, D                     , String, Character_4, Character_5] with CardOne
//  lazy val name_       = new Character_4[A, B, C, D                     , String](elements :+ name_tac     ) with ExprOneTac_4_String [A, B, C, D                     , String, Character_4, Character_5] with CardOne
//  lazy val friends_    = new Character_4[A, B, C, D                     , String](elements :+ friends_tac  ) with ExprSetTac_4        [A, B, C, D                     , String, Character_4, Character_5] with CardSet
//  lazy val appearsIn_  = new Character_4[A, B, C, D                     , String](elements :+ appearsIn_tac) with ExprSetTac_4        [A, B, C, D                     , String, Character_4, Character_5] with CardSet
//
//  override protected def _aggrInt   (kw: Kw                         ) = new Character_4[A, B, C, Int   , Int   ](toInt    (elements, kw    )) with SortAttrs_4[A, B, C, Int   , Int   , Character_4]
//  override protected def _aggrDouble(kw: Kw                         ) = new Character_4[A, B, C, Double, Double](toDouble (elements, kw    )) with SortAttrs_4[A, B, C, Double, Double, Character_4]
//  override protected def _aggrDist  (kw: Kw                         ) = new Character_4[A, B, C, Set[D], t     ](asIs     (elements, kw    ))
//  override protected def _aggrSet   (kw: Kw, n: Option[Int]         ) = new Character_4[A, B, C, Set[t], t     ](asIs     (elements, kw, n ))
//  override protected def _aggrTsort (kw: Kw                         ) = new Character_4[A, B, C, D     , t     ](asIs     (elements, kw    )) with SortAttrs_4[A, B, C, D     , t     , Character_4]
//  override protected def _aggrT     (kw: Kw                         ) = new Character_4[A, B, C, D     , t     ](asIs     (elements, kw    ))
//  override protected def _exprOneMan(op: Op, vs: Seq[t]             ) = new Character_4[A, B, C, D     , t     ](addOne   (elements, op, vs)) with SortAttrs_4[A, B, C, D     , t     , Character_4] with CardOne
//  override protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]     ) = new Character_4[A, B, C, D     , t     ](addOptOne(elements, op, vs)) with SortAttrs_4[A, B, C, D     , t     , Character_4]
//  override protected def _exprOneTac(op: Op, vs: Seq[t]             ) = new Character_4[A, B, C, D     , t     ](addOne   (elements, op, vs)) with CardOne
//  override protected def _exprSetMan(op: Op, vs: Seq[Set[t]]        ) = new Character_4[A, B, C, D     , t     ](addSet   (elements, op, vs)) with CardSet
//  override protected def _exprSetOpt(op: Op, vs: Option[Seq[Set[t]]]) = new Character_4[A, B, C, D     , t     ](addOptSet(elements, op, vs))
//  override protected def _exprSetTac(op: Op, vs: Seq[Set[t]]        ) = new Character_4[A, B, C, D     , t     ](addSet   (elements, op, vs)) with CardSet
//  override protected def _sort      (sort: String                   ) = new Character_4[A, B, C, D     , t     ](addSort  (elements, sort  ))
//
//  override protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne) = new Character_4[A, B, C, D,    t](filterAttr(elements, op, a)) with SortAttrs_4[A, B, C, D,    t, Character_4]
//  override protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[D, t, ns1, ns2]             ) = new Character_5[A, B, C, D, D, t](filterAttr(elements, op, a)) with SortAttrs_5[A, B, C, D, D, t, Character_5]
//  override protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ) = new Character_4[A, B, C, D,    t](filterAttr(elements, op, a))
//  override protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ) = new Character_5[A, B, C, D, X, t](filterAttr(elements, op, a))
//
//  trait NestedInit extends NestedOp_4[A, B, C, D] with Nested_4[A, B, C, D] { self: Molecule =>
//    override protected def _nestedMan[NestedTpl](nestedElements: List[Element]): NestedInit_04[A, B, C, D, NestedTpl] = new NestedInit_04(self.elements.init :+ Nested   (self.elements.last.asInstanceOf[Ref], nestedElements))
//    override protected def _nestedOpt[NestedTpl](nestedElements: List[Element]): NestedInit_04[A, B, C, D, NestedTpl] = new NestedInit_04(self.elements.init :+ NestedOpt(self.elements.last.asInstanceOf[Ref], nestedElements))
//  }
//
//  object Friends extends Character_4[A, B, C, D, t](elements :+ DataModel.Ref("Character", "friends", "Character", CardSet, false, Seq(0, 2, 0))) with NestedInit
//
//  object _Droid     extends Droid_4    [A, B, C, D, t](elements :+ DataModel.BackRef("Droid", "Character", Seq(1, 0)))
//  object _Human     extends Human_4    [A, B, C, D, t](elements :+ DataModel.BackRef("Human", "Character", Seq(2, 0)))
//}
//
//
//class Character_5[A, B, C, D, E, t](override val elements: List[Element]) extends Character_base with ModelOps_5[A, B, C, D, E, t, Character_5, Character_6] {
//  lazy val id          = new Character_6[A, B, C, D, E, String             , String](elements :+ id_man       ) with ExprOneMan_6        [A, B, C, D, E, String             , String, Character_6, X8] with CardOne
//  lazy val name        = new Character_6[A, B, C, D, E, String             , String](elements :+ name_man     ) with ExprOneMan_6_String [A, B, C, D, E, String             , String, Character_6, X8] with CardOne
//  lazy val friends     = new Character_6[A, B, C, D, E, Set[String]        , String](elements :+ friends_man  ) with ExprSetMan_6        [A, B, C, D, E, Set[String]        , String, Character_6, X8] with CardSet
//  lazy val appearsIn   = new Character_6[A, B, C, D, E, Set[String]        , String](elements :+ appearsIn_man) with ExprSetMan_6        [A, B, C, D, E, Set[String]        , String, Character_6, X8] with CardSet
//
//  lazy val name_?      = new Character_6[A, B, C, D, E, Option[String]     , String](elements :+ name_opt     ) with ExprOneOpt_6        [A, B, C, D, E, Option[String]     , String, Character_6, X8] with CardOne
//  lazy val friends_?   = new Character_6[A, B, C, D, E, Option[Set[String]], String](elements :+ friends_opt  ) with ExprSetOpt_6        [A, B, C, D, E, Option[Set[String]], String, Character_6, X8] with CardSet
//  lazy val appearsIn_? = new Character_6[A, B, C, D, E, Option[Set[String]], String](elements :+ appearsIn_opt) with ExprSetOpt_6        [A, B, C, D, E, Option[Set[String]], String, Character_6, X8] with CardSet
//
//  lazy val id_         = new Character_5[A, B, C, D, E                     , String](elements :+ id_tac       ) with ExprOneTac_5        [A, B, C, D, E                     , String, Character_5, Character_6] with CardOne
//  lazy val name_       = new Character_5[A, B, C, D, E                     , String](elements :+ name_tac     ) with ExprOneTac_5_String [A, B, C, D, E                     , String, Character_5, Character_6] with CardOne
//  lazy val friends_    = new Character_5[A, B, C, D, E                     , String](elements :+ friends_tac  ) with ExprSetTac_5        [A, B, C, D, E                     , String, Character_5, Character_6] with CardSet
//  lazy val appearsIn_  = new Character_5[A, B, C, D, E                     , String](elements :+ appearsIn_tac) with ExprSetTac_5        [A, B, C, D, E                     , String, Character_5, Character_6] with CardSet
//
//  override protected def _aggrInt   (kw: Kw                         ) = new Character_5[A, B, C, D, Int   , Int   ](toInt    (elements, kw    )) with SortAttrs_5[A, B, C, D, Int   , Int   , Character_5]
//  override protected def _aggrDouble(kw: Kw                         ) = new Character_5[A, B, C, D, Double, Double](toDouble (elements, kw    )) with SortAttrs_5[A, B, C, D, Double, Double, Character_5]
//  override protected def _aggrDist  (kw: Kw                         ) = new Character_5[A, B, C, D, Set[E], t     ](asIs     (elements, kw    ))
//  override protected def _aggrSet   (kw: Kw, n: Option[Int]         ) = new Character_5[A, B, C, D, Set[t], t     ](asIs     (elements, kw, n ))
//  override protected def _aggrTsort (kw: Kw                         ) = new Character_5[A, B, C, D, E     , t     ](asIs     (elements, kw    )) with SortAttrs_5[A, B, C, D, E     , t     , Character_5]
//  override protected def _aggrT     (kw: Kw                         ) = new Character_5[A, B, C, D, E     , t     ](asIs     (elements, kw    ))
//  override protected def _exprOneMan(op: Op, vs: Seq[t]             ) = new Character_5[A, B, C, D, E     , t     ](addOne   (elements, op, vs)) with SortAttrs_5[A, B, C, D, E     , t     , Character_5] with CardOne
//  override protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]     ) = new Character_5[A, B, C, D, E     , t     ](addOptOne(elements, op, vs)) with SortAttrs_5[A, B, C, D, E     , t     , Character_5]
//  override protected def _exprOneTac(op: Op, vs: Seq[t]             ) = new Character_5[A, B, C, D, E     , t     ](addOne   (elements, op, vs)) with CardOne
//  override protected def _exprSetMan(op: Op, vs: Seq[Set[t]]        ) = new Character_5[A, B, C, D, E     , t     ](addSet   (elements, op, vs)) with CardSet
//  override protected def _exprSetOpt(op: Op, vs: Option[Seq[Set[t]]]) = new Character_5[A, B, C, D, E     , t     ](addOptSet(elements, op, vs))
//  override protected def _exprSetTac(op: Op, vs: Seq[Set[t]]        ) = new Character_5[A, B, C, D, E     , t     ](addSet   (elements, op, vs)) with CardSet
//  override protected def _sort      (sort: String                   ) = new Character_5[A, B, C, D, E     , t     ](addSort  (elements, sort  ))
//
//  override protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne) = new Character_5[A, B, C, D, E,    t](filterAttr(elements, op, a)) with SortAttrs_5[A, B, C, D, E,    t, Character_5]
//  override protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[E, t, ns1, ns2]             ) = new Character_6[A, B, C, D, E, E, t](filterAttr(elements, op, a)) with SortAttrs_6[A, B, C, D, E, E, t, Character_6]
//  override protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ) = new Character_5[A, B, C, D, E,    t](filterAttr(elements, op, a))
//  override protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ) = new Character_6[A, B, C, D, E, X, t](filterAttr(elements, op, a))
//
//  trait NestedInit extends NestedOp_5[A, B, C, D, E] with Nested_5[A, B, C, D, E] { self: Molecule =>
//    override protected def _nestedMan[NestedTpl](nestedElements: List[Element]): NestedInit_05[A, B, C, D, E, NestedTpl] = new NestedInit_05(self.elements.init :+ Nested   (self.elements.last.asInstanceOf[Ref], nestedElements))
//    override protected def _nestedOpt[NestedTpl](nestedElements: List[Element]): NestedInit_05[A, B, C, D, E, NestedTpl] = new NestedInit_05(self.elements.init :+ NestedOpt(self.elements.last.asInstanceOf[Ref], nestedElements))
//  }
//
//  object Friends extends Character_5[A, B, C, D, E, t](elements :+ DataModel.Ref("Character", "friends", "Character", CardSet, false, Seq(0, 2, 0))) with NestedInit
//
//  object _Droid     extends Droid_5    [A, B, C, D, E, t](elements :+ DataModel.BackRef("Droid", "Character", Seq(1, 0)))
//  object _Human     extends Human_5    [A, B, C, D, E, t](elements :+ DataModel.BackRef("Human", "Character", Seq(2, 0)))
//}
//
//
//class Character_6[A, B, C, D, E, F, t](override val elements: List[Element]) extends Character_base with ModelOps_6[A, B, C, D, E, F, t, Character_6, X8] {
//  lazy val id_         = new Character_6[A, B, C, D, E, F, String](elements :+ id_tac       ) with ExprOneTac_6        [A, B, C, D, E, F, String, Character_6, X8] with CardOne
//  lazy val name_       = new Character_6[A, B, C, D, E, F, String](elements :+ name_tac     ) with ExprOneTac_6_String [A, B, C, D, E, F, String, Character_6, X8] with CardOne
//  lazy val friends_    = new Character_6[A, B, C, D, E, F, String](elements :+ friends_tac  ) with ExprSetTac_6        [A, B, C, D, E, F, String, Character_6, X8] with CardSet
//  lazy val appearsIn_  = new Character_6[A, B, C, D, E, F, String](elements :+ appearsIn_tac) with ExprSetTac_6        [A, B, C, D, E, F, String, Character_6, X8] with CardSet
//
//  override protected def _aggrInt   (kw: Kw                         ) = new Character_6[A, B, C, D, E, Int   , Int   ](toInt    (elements, kw    )) with SortAttrs_6[A, B, C, D, E, Int   , Int   , Character_6]
//  override protected def _aggrDouble(kw: Kw                         ) = new Character_6[A, B, C, D, E, Double, Double](toDouble (elements, kw    )) with SortAttrs_6[A, B, C, D, E, Double, Double, Character_6]
//  override protected def _aggrDist  (kw: Kw                         ) = new Character_6[A, B, C, D, E, Set[F], t     ](asIs     (elements, kw    ))
//  override protected def _aggrSet   (kw: Kw, n: Option[Int]         ) = new Character_6[A, B, C, D, E, Set[t], t     ](asIs     (elements, kw, n ))
//  override protected def _aggrTsort (kw: Kw                         ) = new Character_6[A, B, C, D, E, F     , t     ](asIs     (elements, kw    )) with SortAttrs_6[A, B, C, D, E, F     , t     , Character_6]
//  override protected def _aggrT     (kw: Kw                         ) = new Character_6[A, B, C, D, E, F     , t     ](asIs     (elements, kw    ))
//  override protected def _exprOneMan(op: Op, vs: Seq[t]             ) = new Character_6[A, B, C, D, E, F     , t     ](addOne   (elements, op, vs)) with SortAttrs_6[A, B, C, D, E, F     , t     , Character_6] with CardOne
//  override protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]     ) = new Character_6[A, B, C, D, E, F     , t     ](addOptOne(elements, op, vs)) with SortAttrs_6[A, B, C, D, E, F     , t     , Character_6]
//  override protected def _exprOneTac(op: Op, vs: Seq[t]             ) = new Character_6[A, B, C, D, E, F     , t     ](addOne   (elements, op, vs)) with CardOne
//  override protected def _exprSetMan(op: Op, vs: Seq[Set[t]]        ) = new Character_6[A, B, C, D, E, F     , t     ](addSet   (elements, op, vs)) with CardSet
//  override protected def _exprSetOpt(op: Op, vs: Option[Seq[Set[t]]]) = new Character_6[A, B, C, D, E, F     , t     ](addOptSet(elements, op, vs))
//  override protected def _exprSetTac(op: Op, vs: Seq[Set[t]]        ) = new Character_6[A, B, C, D, E, F     , t     ](addSet   (elements, op, vs)) with CardSet
//  override protected def _sort      (sort: String                   ) = new Character_6[A, B, C, D, E, F     , t     ](addSort  (elements, sort  ))
//
//  override protected def _attrSortTac[ns1[_], ns2[_, _]](op: Op, a: ModelOps_0[t, ns1, ns2] with CardOne) = new Character_6[A, B, C, D, E, F, t](filterAttr(elements, op, a)) with SortAttrs_6[A, B, C, D, E, F, t, Character_6]
//  override protected def _attrTac    [ns1[_], ns2[_, _]](op: Op, a: ModelOps_0[t, ns1, ns2]             ) = new Character_6[A, B, C, D, E, F, t](filterAttr(elements, op, a))
//
//  object Friends extends Character_6[A, B, C, D, E, F, t](elements :+ DataModel.Ref("Character", "friends", "Character", CardSet, false, Seq(0, 2, 0)))
//
//  object _Droid     extends Droid_6    [A, B, C, D, E, F, t](elements :+ DataModel.BackRef("Droid", "Character", Seq(1, 0)))
//  object _Human     extends Human_6    [A, B, C, D, E, F, t](elements :+ DataModel.BackRef("Human", "Character", Seq(2, 0)))
//}
//
