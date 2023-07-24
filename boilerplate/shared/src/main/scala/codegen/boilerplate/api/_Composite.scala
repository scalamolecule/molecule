package codegen.boilerplate.api

import codegen.BoilerplateGenBase

object _Composite extends BoilerplateGenBase("Composite", "/api") {
  val traits  = (0 to 21).map(arity => Trait(arity).body).mkString("\n")
  val content = {

    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api
       |
       |import molecule.boilerplate.ast.Model._
       |
       |/** Add sub-molecule to composite molecule.
       |  * <br><br>
       |  * Composite molecules model entities with attributes from different namespaces that are
       |  * not necessarily related. Each group of attributes is modelled by a molecule and these
       |  * "sub-molecules" are tied together with `+` methods to form a composite molecule.
       |  * <br><br>
       |  * `+` methods of this interface adds a new sub-molecule to the composite.
       |  * {{{
       |  * //                                          | add sub-molecule
       |  * m(Article.name.author + Tag.category.weight + Publisher.name).get.map(_ ==> List(
       |  *   (("Battle of Waterloo", "Ben Bridge"), ("History", 5), "Bestseller Publications Inc.")
       |  * ))
       |  * }}}
       |  */
       |trait CompositeBase
       |$traits
       |
       |case class Composite_22[$tns, $txs_](
       |  override val elements: List[Element],
       |  txs: CompositeTx_[$txs]
       |) extends CompositeBase with Elements_22[$tns] {
       |  def Tx: Tx22[$tns, Nothing] with TxMetaData_22[$tns] = txs._compositeTx_22(elements)
       |}
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |case class $fileName_$n0[${`Tn, `}$txs_](
         |  override val elements: List[Element],
         |  txs: CompositeTx_[$txs]
         |) extends CompositeBase with Elements_$n0${`[Tn]`} {
         |  def Tx: Tx$arity[${`Tn, `}Nothing] with TxMetaData_$arity${`[Tn]`} = txs._compositeTx_$n0(elements)
         |
         |  final def +                                                                  (nextMolecule: Molecule_00                                                                  ): Composite_$n0[${`Tn, `}                                                                    $txs] = Composite_$n0(elements :+ Composite(nextMolecule.elements), txs)
         |  final def +[a                                                               ](nextMolecule: Molecule_01[a                                                               ]): Composite_$n1[${`Tn, `}a                                                                 , $txs] = Composite_$n1(elements :+ Composite(nextMolecule.elements), txs)
         |  final def +[a, b                                                            ](nextMolecule: Molecule_02[a, b                                                            ]): Composite_$n1[${`Tn, `}(a, b                                                            ), $txs] = Composite_$n1(elements :+ Composite(nextMolecule.elements), txs)
         |  final def +[a, b, c                                                         ](nextMolecule: Molecule_03[a, b, c                                                         ]): Composite_$n1[${`Tn, `}(a, b, c                                                         ), $txs] = Composite_$n1(elements :+ Composite(nextMolecule.elements), txs)
         |  final def +[a, b, c, d                                                      ](nextMolecule: Molecule_04[a, b, c, d                                                      ]): Composite_$n1[${`Tn, `}(a, b, c, d                                                      ), $txs] = Composite_$n1(elements :+ Composite(nextMolecule.elements), txs)
         |  final def +[a, b, c, d, e                                                   ](nextMolecule: Molecule_05[a, b, c, d, e                                                   ]): Composite_$n1[${`Tn, `}(a, b, c, d, e                                                   ), $txs] = Composite_$n1(elements :+ Composite(nextMolecule.elements), txs)
         |  final def +[a, b, c, d, e, f                                                ](nextMolecule: Molecule_06[a, b, c, d, e, f                                                ]): Composite_$n1[${`Tn, `}(a, b, c, d, e, f                                                ), $txs] = Composite_$n1(elements :+ Composite(nextMolecule.elements), txs)
         |  final def +[a, b, c, d, e, f, g                                             ](nextMolecule: Molecule_07[a, b, c, d, e, f, g                                             ]): Composite_$n1[${`Tn, `}(a, b, c, d, e, f, g                                             ), $txs] = Composite_$n1(elements :+ Composite(nextMolecule.elements), txs)
         |  final def +[a, b, c, d, e, f, g, h                                          ](nextMolecule: Molecule_08[a, b, c, d, e, f, g, h                                          ]): Composite_$n1[${`Tn, `}(a, b, c, d, e, f, g, h                                          ), $txs] = Composite_$n1(elements :+ Composite(nextMolecule.elements), txs)
         |  final def +[a, b, c, d, e, f, g, h, i                                       ](nextMolecule: Molecule_09[a, b, c, d, e, f, g, h, i                                       ]): Composite_$n1[${`Tn, `}(a, b, c, d, e, f, g, h, i                                       ), $txs] = Composite_$n1(elements :+ Composite(nextMolecule.elements), txs)
         |  final def +[a, b, c, d, e, f, g, h, i, j                                    ](nextMolecule: Molecule_10[a, b, c, d, e, f, g, h, i, j                                    ]): Composite_$n1[${`Tn, `}(a, b, c, d, e, f, g, h, i, j                                    ), $txs] = Composite_$n1(elements :+ Composite(nextMolecule.elements), txs)
         |  final def +[a, b, c, d, e, f, g, h, i, j, k                                 ](nextMolecule: Molecule_11[a, b, c, d, e, f, g, h, i, j, k                                 ]): Composite_$n1[${`Tn, `}(a, b, c, d, e, f, g, h, i, j, k                                 ), $txs] = Composite_$n1(elements :+ Composite(nextMolecule.elements), txs)
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l                              ](nextMolecule: Molecule_12[a, b, c, d, e, f, g, h, i, j, k, l                              ]): Composite_$n1[${`Tn, `}(a, b, c, d, e, f, g, h, i, j, k, l                              ), $txs] = Composite_$n1(elements :+ Composite(nextMolecule.elements), txs)
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m                           ](nextMolecule: Molecule_13[a, b, c, d, e, f, g, h, i, j, k, l, m                           ]): Composite_$n1[${`Tn, `}(a, b, c, d, e, f, g, h, i, j, k, l, m                           ), $txs] = Composite_$n1(elements :+ Composite(nextMolecule.elements), txs)
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ](nextMolecule: Molecule_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ]): Composite_$n1[${`Tn, `}(a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ), $txs] = Composite_$n1(elements :+ Composite(nextMolecule.elements), txs)
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ](nextMolecule: Molecule_15[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ]): Composite_$n1[${`Tn, `}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ), $txs] = Composite_$n1(elements :+ Composite(nextMolecule.elements), txs)
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ](nextMolecule: Molecule_16[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ]): Composite_$n1[${`Tn, `}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ), $txs] = Composite_$n1(elements :+ Composite(nextMolecule.elements), txs)
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ](nextMolecule: Molecule_17[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ]): Composite_$n1[${`Tn, `}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ), $txs] = Composite_$n1(elements :+ Composite(nextMolecule.elements), txs)
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ](nextMolecule: Molecule_18[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ]): Composite_$n1[${`Tn, `}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ), $txs] = Composite_$n1(elements :+ Composite(nextMolecule.elements), txs)
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ](nextMolecule: Molecule_19[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ]): Composite_$n1[${`Tn, `}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ), $txs] = Composite_$n1(elements :+ Composite(nextMolecule.elements), txs)
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ](nextMolecule: Molecule_20[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ]): Composite_$n1[${`Tn, `}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ), $txs] = Composite_$n1(elements :+ Composite(nextMolecule.elements), txs)
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ](nextMolecule: Molecule_21[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ]): Composite_$n1[${`Tn, `}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ), $txs] = Composite_$n1(elements :+ Composite(nextMolecule.elements), txs)
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v](nextMolecule: Molecule_22[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v]): Composite_$n1[${`Tn, `}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v), $txs] = Composite_$n1(elements :+ Composite(nextMolecule.elements), txs)
         |}""".stripMargin
  }
}
