//package molecule.core
//
//import molecule.base.ast.SchemaAST._
//import molecule.boilerplate.api._
//import molecule.boilerplate.api.expression._
//import molecule.boilerplate.api.Keywords._
//import molecule.boilerplate.ast.MoleculeModel._
//import molecule.boilerplate.markers.NamespaceMarkers._
//
//
//trait Ns {
//  protected lazy val str_man: AtomOneManString = AtomOneManString("Ns", "str")
//  protected lazy val int_man: AtomOneManInt    = AtomOneManInt   ("Ns", "int")
//  protected lazy val ref_man: AtomOneManLong   = AtomOneManLong  ("Ns", "ref")
//
//  protected lazy val str_opt: AtomOneOptString = AtomOneOptString("Ns", "str")
//  protected lazy val int_opt: AtomOneOptInt    = AtomOneOptInt   ("Ns", "int")
//  protected lazy val ref_opt: AtomOneOptLong   = AtomOneOptLong  ("Ns", "ref")
//
//  protected lazy val str_tac: AtomOneTacString = AtomOneTacString("Ns", "str")
//  protected lazy val int_tac: AtomOneTacInt    = AtomOneTacInt   ("Ns", "int")
//  protected lazy val ref_tac: AtomOneTacLong   = AtomOneTacLong  ("Ns", "ref")
//}
//
//object Ns extends Ns_0[Nothing](Nil)
//
//
//class Ns_0[t](override val elements: Seq[Element]) extends Ns with ModelOps_0[t, Ns_0] with Molecule_00 {
//  lazy val str   = new Ns_1[String        , String](elements :+ str_man) with ExprOneMan_1[String        , String, Ns_1]
//  lazy val int   = new Ns_1[Int           , Int   ](elements :+ int_man) with ExprOneMan_1[Int           , Int   , Ns_1]
//  lazy val ref   = new Ns_1[Int           , Int   ](elements :+ int_man) with ExprOneMan_1[Int           , Int   , Ns_1]
//
//  lazy val str_? = new Ns_1[Option[String], String](elements :+ str_opt) with ExprOneOpt_1[Option[String], String, Ns_1]
//  lazy val int_? = new Ns_1[Option[Int]   , Int   ](elements :+ int_opt) with ExprOneOpt_1[Option[Int]   , Int   , Ns_1]
//
//  lazy val str_  = new Ns_0[String                ](elements :+ str_tac) with ExprOneTac_0[String                , Ns_0]
//  lazy val int_  = new Ns_0[Int                   ](elements :+ int_tac) with ExprOneTac_0[Int                   , Ns_0]
//
//  override protected def _exprTac(op: Op, vs: Seq[t]) = new Ns_0[t](addVs(elements, op, vs))
//}
//
//
//class Ns_1[A, t](override val elements: Seq[Element]) extends Ns with ModelOps_1[A, t, Ns_1] with Molecule_01[A] {
//  lazy val str   = new Ns_2[A, String, String        ](elements :+ str_man) with ExprOneMan_2[A, String        , String, Ns_2]
//  lazy val int   = new Ns_2[A, Int   , Int           ](elements :+ int_man) with ExprOneMan_2[A, Int           , Int   , Ns_2]
//
//  lazy val str_? = new Ns_2[A, Option[String], String](elements :+ str_opt) with ExprOneOpt_2[A, Option[String], String, Ns_2]
//  lazy val int_? = new Ns_2[A, Option[Int]   , Int   ](elements :+ int_opt) with ExprOneOpt_2[A, Option[Int]   , Int   , Ns_2]
//
//  lazy val str_  = new Ns_1[A, String                ](elements :+ str_tac) with ExprOneTac_1[A, String                , Ns_1]
//  lazy val int_  = new Ns_1[A, Int                   ](elements :+ int_tac) with ExprOneTac_1[A, Int                   , Ns_1]
//
//  override protected def _aggrInt   (kw: Kw                    ) = new Ns_1[Int    , Int   ](toInt   (elements, kw    )) with SortAttrs_1[Int    , Int   , Ns_1]
//  override protected def _aggrDouble(kw: Kw                    ) = new Ns_1[Double , Double](toDouble(elements, kw    )) with SortAttrs_1[Double , Double, Ns_1]
//  override protected def _aggrList  (kw: Kw, n: Option[Int]    ) = new Ns_1[List[t], t     ](toList  (elements, kw, n )) with SortAttrs_1[List[t], t     , Ns_1]
//  override protected def _aggrT     (kw: Kw                    ) = new Ns_1[t      , t     ](asIs    (elements, kw    )) with SortAttrs_1[t      , t     , Ns_1]
//  override protected def _exprMan   (op: Op, vs: Seq[t]        ) = new Ns_1[A      , t     ](addVs   (elements, op, vs)) with SortAttrs_1[A      , t     , Ns_1]
//  override protected def _exprOpt   (op: Op, vs: Option[Seq[t]]) = new Ns_1[A      , t     ](addOptVs(elements, op, vs)) with SortAttrs_1[A      , t     , Ns_1]
//  override protected def _exprTac   (op: Op, vs: Seq[t]        ) = new Ns_1[A      , t     ](addVs   (elements, op, vs))
//  override protected def _addSort   (sort: String              ) = new Ns_1[A      , t     ](addSort (elements, sort  ))
//
//  object Ref1 extends Ref1_1[A, t](elements :+ Bond("Ns", "ref1", "Ref1", One))
//}
//
//
//class Ns_2[A, B, t](override val elements: Seq[Element]) extends Ns with ModelOps_2[A, B, t, Ns_2] with Molecule_02[A, B] {
//  lazy val str_ = new Ns_2[A, B, String](elements :+ str_tac) with ExprOneTac_2[A, B, String, Ns_2]
//  lazy val int_ = new Ns_2[A, B, Int   ](elements :+ int_tac) with ExprOneTac_2[A, B, Int   , Ns_2]
//
//  override protected def _aggrInt   (kw: Kw                    ) = new Ns_2[A, Int    , Int   ](toInt   (elements, kw    )) with SortAttrs_2[A, Int    , Int   , Ns_2]
//  override protected def _aggrDouble(kw: Kw                    ) = new Ns_2[A, Double , Double](toDouble(elements, kw    )) with SortAttrs_2[A, Double , Double, Ns_2]
//  override protected def _aggrList  (kw: Kw, n: Option[Int]    ) = new Ns_2[A, List[t], t     ](toList  (elements, kw, n )) with SortAttrs_2[A, List[t], t     , Ns_2]
//  override protected def _aggrT     (kw: Kw                    ) = new Ns_2[A, t      , t     ](asIs    (elements, kw    )) with SortAttrs_2[A, t      , t     , Ns_2]
//  override protected def _exprMan   (op: Op, vs: Seq[t]        ) = new Ns_2[A, B      , t     ](addVs   (elements, op, vs)) with SortAttrs_2[A, B      , t     , Ns_2]
//  override protected def _exprOpt   (op: Op, vs: Option[Seq[t]]) = new Ns_2[A, B      , t     ](addOptVs(elements, op, vs)) with SortAttrs_2[A, B      , t     , Ns_2]
//  override protected def _exprTac   (op: Op, vs: Seq[t]        ) = new Ns_2[A, B      , t     ](addVs   (elements, op, vs))
//  override protected def _addSort   (sort: String              ) = new Ns_2[A, B      , t     ](addSort (elements, sort  ))
//
//  object Ref1 extends Ref1_2[A, B, t](elements :+ Bond("Ns", "ref1", "Ref1", One))
//}
//
//
//
//
//
//class Ref1_0[t](override val elements: Seq[Element]) extends Ns with ModelOps_0[t, Ref1_0] with Molecule_00 {
//  lazy val str   = new Ref1_1[String, String](elements :+ str_man) with ExprOneMan_1[String, String, Ref1_1]
//  lazy val int   = new Ref1_1[Int   , Int   ](elements :+ int_man) with ExprOneMan_1[Int   , Int   , Ref1_1]
//
//  lazy val str_? = new Ref1_1[Option[String], String](elements :+ str_opt) with ExprOneOpt_1[Option[String], String, Ref1_1]
//  lazy val int_? = new Ref1_1[Option[Int]   , Int   ](elements :+ int_opt) with ExprOneOpt_1[Option[Int]   , Int   , Ref1_1]
//
//  lazy val str_  = new Ref1_0[String](elements :+ str_tac) with ExprOneTac_0[String, Ref1_0]
//  lazy val int_  = new Ref1_0[Int   ](elements :+ int_tac) with ExprOneTac_0[Int   , Ref1_0]
//
//  override protected def _exprTac(op: Op, vs: Seq[t]) = new Ref1_0[t](addVs(elements, op, vs))
//}
//
//
//class Ref1_1[A, t](override val elements: Seq[Element]) extends Ns with ModelOps_1[A, t, Ref1_1] with Molecule_01[A] {
//  lazy val str   = new Ref1_2[A, String, String](elements :+ str_man) with ExprOneMan_2[A, String, String, Ref1_2]
//  lazy val int   = new Ref1_2[A, Int   , Int   ](elements :+ int_man) with ExprOneMan_2[A, Int   , Int   , Ref1_2]
//
//  lazy val str_? = new Ref1_2[A, Option[String], String](elements :+ str_opt) with ExprOneOpt_2[A, Option[String], String, Ref1_2]
//  lazy val int_? = new Ref1_2[A, Option[Int]   , Int   ](elements :+ int_opt) with ExprOneOpt_2[A, Option[Int]   , Int   , Ref1_2]
//
//  lazy val str_  = new Ref1_1[A, String](elements :+ str_tac) with ExprOneTac_1[A, String, Ref1_1]
//  lazy val int_  = new Ref1_1[A, Int   ](elements :+ int_tac) with ExprOneTac_1[A, Int   , Ref1_1]
//
//  override protected def _aggrInt   (kw: Kw                    ) = new Ref1_1[Int    , Int   ](toInt   (elements, kw    )) with SortAttrs_1[Int    , Int   , Ref1_1]
//  override protected def _aggrDouble(kw: Kw                    ) = new Ref1_1[Double , Double](toDouble(elements, kw    )) with SortAttrs_1[Double , Double, Ref1_1]
//  override protected def _aggrList  (kw: Kw, n: Option[Int]    ) = new Ref1_1[List[t], t     ](toList  (elements, kw, n )) with SortAttrs_1[List[t], t     , Ref1_1]
//  override protected def _aggrT     (kw: Kw                    ) = new Ref1_1[t      , t     ](asIs    (elements, kw    )) with SortAttrs_1[t      , t     , Ref1_1]
//  override protected def _exprMan   (op: Op, vs: Seq[t]        ) = new Ref1_1[A      , t     ](addVs   (elements, op, vs)) with SortAttrs_1[A      , t     , Ref1_1]
//  override protected def _exprOpt   (op: Op, vs: Option[Seq[t]]) = new Ref1_1[A      , t     ](addOptVs(elements, op, vs)) with SortAttrs_1[A      , t     , Ref1_1]
//  override protected def _exprTac   (op: Op, vs: Seq[t]        ) = new Ref1_1[A      , t     ](addVs   (elements, op, vs))
//  override protected def _addSort   (sort: String              ) = new Ref1_1[A      , t     ](addSort (elements, sort  ))
//}
//
//
//class Ref1_2[A, B, t](override val elements: Seq[Element]) extends Ns with ModelOps_2[A, B, t, Ref1_2] with Molecule_02[A, B] {
//  lazy val str_ = new Ref1_2[A, B, String](elements :+ str_tac) with ExprOneTac_2[A, B, String, Ref1_2]
//  lazy val int_ = new Ref1_2[A, B, Int   ](elements :+ int_tac) with ExprOneTac_2[A, B, Int   , Ref1_2]
//
//  override protected def _aggrInt   (kw: Kw                    ) = new Ref1_2[A, Int    , Int   ](toInt   (elements, kw    )) with SortAttrs_2[A, Int    , Int   , Ref1_2]
//  override protected def _aggrDouble(kw: Kw                    ) = new Ref1_2[A, Double , Double](toDouble(elements, kw    )) with SortAttrs_2[A, Double , Double, Ref1_2]
//  override protected def _aggrList  (kw: Kw, n: Option[Int]    ) = new Ref1_2[A, List[t], t     ](toList  (elements, kw, n )) with SortAttrs_2[A, List[t], t     , Ref1_2]
//  override protected def _aggrT     (kw: Kw                    ) = new Ref1_2[A, t      , t     ](asIs    (elements, kw    )) with SortAttrs_2[A, t      , t     , Ref1_2]
//  override protected def _exprMan   (op: Op, vs: Seq[t]        ) = new Ref1_2[A, B      , t     ](addVs   (elements, op, vs)) with SortAttrs_2[A, B      , t     , Ref1_2]
//  override protected def _exprOpt   (op: Op, vs: Option[Seq[t]]) = new Ref1_2[A, B      , t     ](addOptVs(elements, op, vs)) with SortAttrs_2[A, B      , t     , Ref1_2]
//  override protected def _exprTac   (op: Op, vs: Seq[t]        ) = new Ref1_2[A, B      , t     ](addVs   (elements, op, vs))
//  override protected def _addSort   (sort: String              ) = new Ref1_2[A, B      , t     ](addSort (elements, sort  ))
//}
