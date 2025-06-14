package boilerplate.graphql.client.api

import boilerplate.graphql.GraphqlBase

object _OptRef extends GraphqlBase("OptRef", "/api") {
  val content = {
    val traits = (0 to 21).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.graphql.client.api
       |
       |import molecule.core.dataModel.*
       |
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |trait ${fileName}Op${a0}[${`A..V, `}Entity[${`_, _, _`}]] {
         |  protected def _optRef[RefTpl](optRefDataModel: DataModel): Entity[${`A..V, `}Option[RefTpl], Any] = ???
         |}
         |trait $fileName_$arity[${`A..V, `}Entity[${`_, _, _`}]] { self: ${fileName}Op${a0}[${`A..V, `}Entity] =>
         |  final def ?[a                                                               ] (optRef: Molecule_01[a                                                               ]): Entity[${`A..V, `}Option[a                                                                 ], Any] = _optRef[a                                                                 ](optRef.dataModel)
         |  final def ?[a, b                                                            ] (optRef: Molecule_02[a, b                                                            ]): Entity[${`A..V, `}Option[(a, b                                                            )], Any] = _optRef[(a, b                                                            )](optRef.dataModel)
         |  final def ?[a, b, c                                                         ] (optRef: Molecule_03[a, b, c                                                         ]): Entity[${`A..V, `}Option[(a, b, c                                                         )], Any] = _optRef[(a, b, c                                                         )](optRef.dataModel)
         |  final def ?[a, b, c, d                                                      ] (optRef: Molecule_04[a, b, c, d                                                      ]): Entity[${`A..V, `}Option[(a, b, c, d                                                      )], Any] = _optRef[(a, b, c, d                                                      )](optRef.dataModel)
         |  final def ?[a, b, c, d, e                                                   ] (optRef: Molecule_05[a, b, c, d, e                                                   ]): Entity[${`A..V, `}Option[(a, b, c, d, e                                                   )], Any] = _optRef[(a, b, c, d, e                                                   )](optRef.dataModel)
         |  final def ?[a, b, c, d, e, f                                                ] (optRef: Molecule_06[a, b, c, d, e, f                                                ]): Entity[${`A..V, `}Option[(a, b, c, d, e, f                                                )], Any] = _optRef[(a, b, c, d, e, f                                                )](optRef.dataModel)
         |  final def ?[a, b, c, d, e, f, g                                             ] (optRef: Molecule_07[a, b, c, d, e, f, g                                             ]): Entity[${`A..V, `}Option[(a, b, c, d, e, f, g                                             )], Any] = _optRef[(a, b, c, d, e, f, g                                             )](optRef.dataModel)
         |  final def ?[a, b, c, d, e, f, g, h                                          ] (optRef: Molecule_08[a, b, c, d, e, f, g, h                                          ]): Entity[${`A..V, `}Option[(a, b, c, d, e, f, g, h                                          )], Any] = _optRef[(a, b, c, d, e, f, g, h                                          )](optRef.dataModel)
         |  final def ?[a, b, c, d, e, f, g, h, i                                       ] (optRef: Molecule_09[a, b, c, d, e, f, g, h, i                                       ]): Entity[${`A..V, `}Option[(a, b, c, d, e, f, g, h, i                                       )], Any] = _optRef[(a, b, c, d, e, f, g, h, i                                       )](optRef.dataModel)
         |  final def ?[a, b, c, d, e, f, g, h, i, j                                    ] (optRef: Molecule_10[a, b, c, d, e, f, g, h, i, j                                    ]): Entity[${`A..V, `}Option[(a, b, c, d, e, f, g, h, i, j                                    )], Any] = _optRef[(a, b, c, d, e, f, g, h, i, j                                    )](optRef.dataModel)
         |  final def ?[a, b, c, d, e, f, g, h, i, j, k                                 ] (optRef: Molecule_11[a, b, c, d, e, f, g, h, i, j, k                                 ]): Entity[${`A..V, `}Option[(a, b, c, d, e, f, g, h, i, j, k                                 )], Any] = _optRef[(a, b, c, d, e, f, g, h, i, j, k                                 )](optRef.dataModel)
         |  final def ?[a, b, c, d, e, f, g, h, i, j, k, l                              ] (optRef: Molecule_12[a, b, c, d, e, f, g, h, i, j, k, l                              ]): Entity[${`A..V, `}Option[(a, b, c, d, e, f, g, h, i, j, k, l                              )], Any] = _optRef[(a, b, c, d, e, f, g, h, i, j, k, l                              )](optRef.dataModel)
         |  final def ?[a, b, c, d, e, f, g, h, i, j, k, l, m                           ] (optRef: Molecule_13[a, b, c, d, e, f, g, h, i, j, k, l, m                           ]): Entity[${`A..V, `}Option[(a, b, c, d, e, f, g, h, i, j, k, l, m                           )], Any] = _optRef[(a, b, c, d, e, f, g, h, i, j, k, l, m                           )](optRef.dataModel)
         |  final def ?[a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ] (optRef: Molecule_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ]): Entity[${`A..V, `}Option[(a, b, c, d, e, f, g, h, i, j, k, l, m, n                        )], Any] = _optRef[(a, b, c, d, e, f, g, h, i, j, k, l, m, n                        )](optRef.dataModel)
         |  final def ?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ] (optRef: Molecule_15[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ]): Entity[${`A..V, `}Option[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     )], Any] = _optRef[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     )](optRef.dataModel)
         |  final def ?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ] (optRef: Molecule_16[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ]): Entity[${`A..V, `}Option[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  )], Any] = _optRef[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  )](optRef.dataModel)
         |  final def ?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ] (optRef: Molecule_17[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ]): Entity[${`A..V, `}Option[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               )], Any] = _optRef[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               )](optRef.dataModel)
         |  final def ?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ] (optRef: Molecule_18[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ]): Entity[${`A..V, `}Option[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            )], Any] = _optRef[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            )](optRef.dataModel)
         |  final def ?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ] (optRef: Molecule_19[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ]): Entity[${`A..V, `}Option[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         )], Any] = _optRef[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         )](optRef.dataModel)
         |  final def ?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ] (optRef: Molecule_20[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ]): Entity[${`A..V, `}Option[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      )], Any] = _optRef[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      )](optRef.dataModel)
         |  final def ?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ] (optRef: Molecule_21[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ]): Entity[${`A..V, `}Option[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   )], Any] = _optRef[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   )](optRef.dataModel)
         |  final def ?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v] (optRef: Molecule_22[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v]): Entity[${`A..V, `}Option[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)], Any] = _optRef[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)](optRef.dataModel)
         |}
         |""".stripMargin
  }
}
