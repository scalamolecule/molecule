package codegen.boilerplate.api

import codegen.BoilerplateGenBase

object _CompositeInit extends BoilerplateGenBase("CompositeInit", "/api") {
  val content = {
    val traits = (0 to 22).map(arity => Trait(arity).body).mkString("\n")
    //       |import scala.language.higherKinds
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api
       |
       |import molecule.boilerplate.ast.Model._
       |
       |/** Build composite molecule.
       | * <br><br>
       | * Composite molecules model entities with attributes from different namespaces that are
       | * not necessarily related. Each group of attributes is modelled by a molecule and the
       | * "sub-molecules" are tied together with `+` methods to form a composite molecule.
       | * <br><br>
       | * The attributes of the first sub-molecule are tied
       | * together in a tuple of its own before being merged with the tuple of attribute values
       | * of the second sub-molecule. If any of the sub-molecules are of arity-1, then no tuple is created:
       | * {{{
       | * for {
       | *   // Arity 1 + 1
       | *   _ <- m(Article.name + Tag.category).get.map(_ ==> List(
       | *     ("Battle of Waterloo", "History")
       | *   ))
       | *
       | *   // Arity 1 + 2
       | *   _ <- m(Article.name + Tag.category.weight).get.map(_ ==> List(
       | *     ("Battle of Waterloo", ("History", 5))
       | *   ))
       | *
       | *   // Arity 2 + 1
       | *   _ <- m(Article.name.author + Tag.category).get.map(_ ==> List(
       | *     (("Battle of Waterloo", "Ben Bridge"), "History")
       | *   ))
       | *
       | *   // Arity 2 + 2
       | *   _ <- m(Article.name.author + Tag.category.weight).get.map(_ ==> List(
       | *     (("Battle of Waterloo", "Ben Bridge"), ("History", 5))
       | *   ))
       | *
       | *   // Arity 3 + 2 etc...
       | *   _ <- m(Article.name.author.editor + Tag.category.weight).get.map(_ ==> List(
       | *     (("Battle of Waterloo", "Ben Bridge", "Joe Moe"), ("History", 5))
       | *   ))
       | * } yield ()
       | * }}}
       | */
       |trait CompositeInitBase
       |
       |trait CompositeInitOp_0[Tpl] extends CompositeInitBase { self: Molecule[Tpl] =>
       |  protected def _composite00    (compositeElements: Seq[Element]): Composite_00     = Composite_00(Seq(Composite(elements), Composite(compositeElements)))
       |  protected def _composite01[T1](compositeElements: Seq[Element]): Composite_01[T1] = Composite_01(Seq(Composite(elements), Composite(compositeElements)))
       |}
       |
       |trait CompositeInitOp_n[Tpl] extends CompositeInitBase { self: Molecule[Tpl] =>
       |  protected def _composite01[T1    ](compositeElements: Seq[Element]): Composite_01[T1    ] = Composite_01(Seq(Composite(elements), Composite(compositeElements)))
       |  protected def _composite02[T1, T2](compositeElements: Seq[Element]): Composite_02[T1, T2] = Composite_02(Seq(Composite(elements), Composite(compositeElements)))
       |}
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    override val n0 = if (arity == 0) "00" else "01"
    override val n1 = if (arity == 0) "01" else "02"
    val opN  = if (arity == 0) "0" else "n"
    val body =
      s"""
         |trait ${fileName}_$arity${`[A..V]`} extends CompositeInitOp_$opN[$tpl] { self: Molecule[$tpl] =>
         |  final def +                                                                  (nextMolecule: Molecule_00                                                                  ): Composite_$n0${`[A0]`} = _composite$n0${`[A0]`}(nextMolecule.elements)
         |  final def +[a                                                               ](nextMolecule: Molecule_01[a                                                               ]): Composite_$n1[${`A..V,`}a                                                                 ] = _composite$n1[${`A..V,`}a                                                                 ](nextMolecule.elements)
         |  final def +[a, b                                                            ](nextMolecule: Molecule_02[a, b                                                            ]): Composite_$n1[${`A..V,`}(a, b                                                            )] = _composite$n1[${`A..V,`}(a, b                                                            )](nextMolecule.elements)
         |  final def +[a, b, c                                                         ](nextMolecule: Molecule_03[a, b, c                                                         ]): Composite_$n1[${`A..V,`}(a, b, c                                                         )] = _composite$n1[${`A..V,`}(a, b, c                                                         )](nextMolecule.elements)
         |  final def +[a, b, c, d                                                      ](nextMolecule: Molecule_04[a, b, c, d                                                      ]): Composite_$n1[${`A..V,`}(a, b, c, d                                                      )] = _composite$n1[${`A..V,`}(a, b, c, d                                                      )](nextMolecule.elements)
         |  final def +[a, b, c, d, e                                                   ](nextMolecule: Molecule_05[a, b, c, d, e                                                   ]): Composite_$n1[${`A..V,`}(a, b, c, d, e                                                   )] = _composite$n1[${`A..V,`}(a, b, c, d, e                                                   )](nextMolecule.elements)
         |  final def +[a, b, c, d, e, f                                                ](nextMolecule: Molecule_06[a, b, c, d, e, f                                                ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f                                                )] = _composite$n1[${`A..V,`}(a, b, c, d, e, f                                                )](nextMolecule.elements)
         |  final def +[a, b, c, d, e, f, g                                             ](nextMolecule: Molecule_07[a, b, c, d, e, f, g                                             ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g                                             )] = _composite$n1[${`A..V,`}(a, b, c, d, e, f, g                                             )](nextMolecule.elements)
         |  final def +[a, b, c, d, e, f, g, h                                          ](nextMolecule: Molecule_08[a, b, c, d, e, f, g, h                                          ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g, h                                          )] = _composite$n1[${`A..V,`}(a, b, c, d, e, f, g, h                                          )](nextMolecule.elements)
         |  final def +[a, b, c, d, e, f, g, h, i                                       ](nextMolecule: Molecule_09[a, b, c, d, e, f, g, h, i                                       ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i                                       )] = _composite$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i                                       )](nextMolecule.elements)
         |  final def +[a, b, c, d, e, f, g, h, i, j                                    ](nextMolecule: Molecule_10[a, b, c, d, e, f, g, h, i, j                                    ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j                                    )] = _composite$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j                                    )](nextMolecule.elements)
         |  final def +[a, b, c, d, e, f, g, h, i, j, k                                 ](nextMolecule: Molecule_11[a, b, c, d, e, f, g, h, i, j, k                                 ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k                                 )] = _composite$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k                                 )](nextMolecule.elements)
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l                              ](nextMolecule: Molecule_12[a, b, c, d, e, f, g, h, i, j, k, l                              ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l                              )] = _composite$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l                              )](nextMolecule.elements)
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m                           ](nextMolecule: Molecule_13[a, b, c, d, e, f, g, h, i, j, k, l, m                           ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l, m                           )] = _composite$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l, m                           )](nextMolecule.elements)
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ](nextMolecule: Molecule_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l, m, n                        )] = _composite$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l, m, n                        )](nextMolecule.elements)
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ](nextMolecule: Molecule_15[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     )] = _composite$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     )](nextMolecule.elements)
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ](nextMolecule: Molecule_16[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  )] = _composite$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  )](nextMolecule.elements)
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ](nextMolecule: Molecule_17[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               )] = _composite$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               )](nextMolecule.elements)
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ](nextMolecule: Molecule_18[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            )] = _composite$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            )](nextMolecule.elements)
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ](nextMolecule: Molecule_19[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         )] = _composite$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         )](nextMolecule.elements)
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ](nextMolecule: Molecule_20[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      )] = _composite$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      )](nextMolecule.elements)
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ](nextMolecule: Molecule_21[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   )] = _composite$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   )](nextMolecule.elements)
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v](nextMolecule: Molecule_22[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)] = _composite$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)](nextMolecule.elements)
         |}""".stripMargin
  }
}
