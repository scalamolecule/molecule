package molecule.boilerplate.api

import molecule.boilerplate.api._
import scala.language.higherKinds


/** Transaction meta data on molecule.
 * <br><br>
 * `Tx` takes a transaction meta data molecule with attributes having the transaction id as their entity id.
 * {{{
 * for {
 *   // Save molecule with transaction data
 *   _ <- Person.name("Ben").Tx(MyMetaData.action("add member")).save
 *
 *   // Query for data with transaction meta data - "which persons became members"
 *   _ <- Person.name.Tx(MyMetaData.action_("add member")).get.map(_ ==> List("Ben"))
 * } yield ()
 * }}}
 */
trait TxMetaDataBase

trait Tx_0 extends TxMetaDataBase {
  object Tx {
    final def apply                                                                   (txMetaData: Molecule_00                                                                  ): Molecule_00                                                                   = ???
    final def apply[a                                                               ] (txMetaData: Molecule_01[a                                                               ]): Molecule_01[a                                                               ] = ???
    final def apply[a, b                                                            ] (txMetaData: Molecule_02[a, b                                                            ]): Molecule_02[a, b                                                            ] = ???
    final def apply[a, b, c                                                         ] (txMetaData: Molecule_03[a, b, c                                                         ]): Molecule_03[a, b, c                                                         ] = ???
    final def apply[a, b, c, d                                                      ] (txMetaData: Molecule_04[a, b, c, d                                                      ]): Molecule_04[a, b, c, d                                                      ] = ???
    final def apply[a, b, c, d, e                                                   ] (txMetaData: Molecule_05[a, b, c, d, e                                                   ]): Molecule_05[a, b, c, d, e                                                   ] = ???
    final def apply[a, b, c, d, e, f                                                ] (txMetaData: Molecule_06[a, b, c, d, e, f                                                ]): Molecule_06[a, b, c, d, e, f                                                ] = ???
    final def apply[a, b, c, d, e, f, g                                             ] (txMetaData: Molecule_07[a, b, c, d, e, f, g                                             ]): Molecule_07[a, b, c, d, e, f, g                                             ] = ???
    final def apply[a, b, c, d, e, f, g, h                                          ] (txMetaData: Molecule_08[a, b, c, d, e, f, g, h                                          ]): Molecule_08[a, b, c, d, e, f, g, h                                          ] = ???
    final def apply[a, b, c, d, e, f, g, h, i                                       ] (txMetaData: Molecule_09[a, b, c, d, e, f, g, h, i                                       ]): Molecule_09[a, b, c, d, e, f, g, h, i                                       ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j                                    ] (txMetaData: Molecule_10[a, b, c, d, e, f, g, h, i, j                                    ]): Molecule_10[a, b, c, d, e, f, g, h, i, j                                    ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k                                 ] (txMetaData: Molecule_11[a, b, c, d, e, f, g, h, i, j, k                                 ]): Molecule_11[a, b, c, d, e, f, g, h, i, j, k                                 ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l                              ] (txMetaData: Molecule_12[a, b, c, d, e, f, g, h, i, j, k, l                              ]): Molecule_12[a, b, c, d, e, f, g, h, i, j, k, l                              ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m                           ] (txMetaData: Molecule_13[a, b, c, d, e, f, g, h, i, j, k, l, m                           ]): Molecule_13[a, b, c, d, e, f, g, h, i, j, k, l, m                           ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ] (txMetaData: Molecule_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ]): Molecule_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ] (txMetaData: Molecule_15[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ]): Molecule_15[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ] (txMetaData: Molecule_16[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ]): Molecule_16[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ] (txMetaData: Molecule_17[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ]): Molecule_17[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ] (txMetaData: Molecule_18[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ]): Molecule_18[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ] (txMetaData: Molecule_19[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ]): Molecule_19[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ] (txMetaData: Molecule_20[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ]): Molecule_20[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ] (txMetaData: Molecule_21[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ]): Molecule_21[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v] (txMetaData: Molecule_22[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v]): Molecule_22[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v] = ???

    final def apply                                                                   (txMetaData: Composite_00                                                                  ): Molecule_00                                                                   = ???
    final def apply[a                                                               ] (txMetaData: Composite_01[a                                                               ]): Molecule_01[a                                                               ] = ???
    final def apply[a, b                                                            ] (txMetaData: Composite_02[a, b                                                            ]): Molecule_02[a, b                                                            ] = ???
    final def apply[a, b, c                                                         ] (txMetaData: Composite_03[a, b, c                                                         ]): Molecule_03[a, b, c                                                         ] = ???
    final def apply[a, b, c, d                                                      ] (txMetaData: Composite_04[a, b, c, d                                                      ]): Molecule_04[a, b, c, d                                                      ] = ???
    final def apply[a, b, c, d, e                                                   ] (txMetaData: Composite_05[a, b, c, d, e                                                   ]): Molecule_05[a, b, c, d, e                                                   ] = ???
    final def apply[a, b, c, d, e, f                                                ] (txMetaData: Composite_06[a, b, c, d, e, f                                                ]): Molecule_06[a, b, c, d, e, f                                                ] = ???
    final def apply[a, b, c, d, e, f, g                                             ] (txMetaData: Composite_07[a, b, c, d, e, f, g                                             ]): Molecule_07[a, b, c, d, e, f, g                                             ] = ???
    final def apply[a, b, c, d, e, f, g, h                                          ] (txMetaData: Composite_08[a, b, c, d, e, f, g, h                                          ]): Molecule_08[a, b, c, d, e, f, g, h                                          ] = ???
    final def apply[a, b, c, d, e, f, g, h, i                                       ] (txMetaData: Composite_09[a, b, c, d, e, f, g, h, i                                       ]): Molecule_09[a, b, c, d, e, f, g, h, i                                       ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j                                    ] (txMetaData: Composite_10[a, b, c, d, e, f, g, h, i, j                                    ]): Molecule_10[a, b, c, d, e, f, g, h, i, j                                    ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k                                 ] (txMetaData: Composite_11[a, b, c, d, e, f, g, h, i, j, k                                 ]): Molecule_11[a, b, c, d, e, f, g, h, i, j, k                                 ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l                              ] (txMetaData: Composite_12[a, b, c, d, e, f, g, h, i, j, k, l                              ]): Molecule_12[a, b, c, d, e, f, g, h, i, j, k, l                              ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m                           ] (txMetaData: Composite_13[a, b, c, d, e, f, g, h, i, j, k, l, m                           ]): Molecule_13[a, b, c, d, e, f, g, h, i, j, k, l, m                           ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ] (txMetaData: Composite_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ]): Molecule_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ] (txMetaData: Composite_15[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ]): Molecule_15[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ] (txMetaData: Composite_16[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ]): Molecule_16[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ] (txMetaData: Composite_17[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ]): Molecule_17[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ] (txMetaData: Composite_18[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ]): Molecule_18[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ] (txMetaData: Composite_19[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ]): Molecule_19[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ] (txMetaData: Composite_20[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ]): Molecule_20[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ] (txMetaData: Composite_21[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ]): Molecule_21[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v] (txMetaData: Composite_22[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v]): Molecule_22[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v] = ???
  }
}

trait Tx_1[A] extends TxMetaDataBase {
  object Tx {
    final def apply                                                                   (txMetaData: Molecule_00                                                               ): Molecule_01[A                                                               ] = ???
    final def apply[a                                                               ] (txMetaData: Molecule_01[a                                                            ]): Molecule_02[A, a                                                            ] = ???
    final def apply[a, b                                                            ] (txMetaData: Molecule_02[a, b                                                         ]): Molecule_03[A, a, b                                                         ] = ???
    final def apply[a, b, c                                                         ] (txMetaData: Molecule_03[a, b, c                                                      ]): Molecule_04[A, a, b, c                                                      ] = ???
    final def apply[a, b, c, d                                                      ] (txMetaData: Molecule_04[a, b, c, d                                                   ]): Molecule_05[A, a, b, c, d                                                   ] = ???
    final def apply[a, b, c, d, e                                                   ] (txMetaData: Molecule_05[a, b, c, d, e                                                ]): Molecule_06[A, a, b, c, d, e                                                ] = ???
    final def apply[a, b, c, d, e, f                                                ] (txMetaData: Molecule_06[a, b, c, d, e, f                                             ]): Molecule_07[A, a, b, c, d, e, f                                             ] = ???
    final def apply[a, b, c, d, e, f, g                                             ] (txMetaData: Molecule_07[a, b, c, d, e, f, g                                          ]): Molecule_08[A, a, b, c, d, e, f, g                                          ] = ???
    final def apply[a, b, c, d, e, f, g, h                                          ] (txMetaData: Molecule_08[a, b, c, d, e, f, g, h                                       ]): Molecule_09[A, a, b, c, d, e, f, g, h                                       ] = ???
    final def apply[a, b, c, d, e, f, g, h, i                                       ] (txMetaData: Molecule_09[a, b, c, d, e, f, g, h, i                                    ]): Molecule_10[A, a, b, c, d, e, f, g, h, i                                    ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j                                    ] (txMetaData: Molecule_10[a, b, c, d, e, f, g, h, i, j                                 ]): Molecule_11[A, a, b, c, d, e, f, g, h, i, j                                 ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k                                 ] (txMetaData: Molecule_11[a, b, c, d, e, f, g, h, i, j, k                              ]): Molecule_12[A, a, b, c, d, e, f, g, h, i, j, k                              ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l                              ] (txMetaData: Molecule_12[a, b, c, d, e, f, g, h, i, j, k, l                           ]): Molecule_13[A, a, b, c, d, e, f, g, h, i, j, k, l                           ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m                           ] (txMetaData: Molecule_13[a, b, c, d, e, f, g, h, i, j, k, l, m                        ]): Molecule_14[A, a, b, c, d, e, f, g, h, i, j, k, l, m                        ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ] (txMetaData: Molecule_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n                     ]): Molecule_15[A, a, b, c, d, e, f, g, h, i, j, k, l, m, n                     ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ] (txMetaData: Molecule_15[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                  ]): Molecule_16[A, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                  ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ] (txMetaData: Molecule_16[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p               ]): Molecule_17[A, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p               ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ] (txMetaData: Molecule_17[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q            ]): Molecule_18[A, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q            ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ] (txMetaData: Molecule_18[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r         ]): Molecule_19[A, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r         ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ] (txMetaData: Molecule_19[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s      ]): Molecule_20[A, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s      ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ] (txMetaData: Molecule_20[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t   ]): Molecule_21[A, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t   ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ] (txMetaData: Molecule_21[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u]): Molecule_22[A, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u] = ???

    final def apply                                                                   (txMetaData: Composite_00                                                                  ): Molecule_01[A                                                               ] = ???
    final def apply[a                                                               ] (txMetaData: Composite_01[a                                                               ]): Molecule_02[A, a                                                            ] = ???
    final def apply[a, b                                                            ] (txMetaData: Composite_02[a, b                                                            ]): Molecule_03[A, a, b                                                         ] = ???
    final def apply[a, b, c                                                         ] (txMetaData: Composite_03[a, b, c                                                         ]): Molecule_04[A, a, b, c                                                      ] = ???
    final def apply[a, b, c, d                                                      ] (txMetaData: Composite_04[a, b, c, d                                                      ]): Molecule_05[A, a, b, c, d                                                   ] = ???
    final def apply[a, b, c, d, e                                                   ] (txMetaData: Composite_05[a, b, c, d, e                                                   ]): Molecule_06[A, a, b, c, d, e                                                ] = ???
    final def apply[a, b, c, d, e, f                                                ] (txMetaData: Composite_06[a, b, c, d, e, f                                                ]): Molecule_07[A, a, b, c, d, e, f                                             ] = ???
    final def apply[a, b, c, d, e, f, g                                             ] (txMetaData: Composite_07[a, b, c, d, e, f, g                                             ]): Molecule_08[A, a, b, c, d, e, f, g                                          ] = ???
    final def apply[a, b, c, d, e, f, g, h                                          ] (txMetaData: Composite_08[a, b, c, d, e, f, g, h                                          ]): Molecule_09[A, a, b, c, d, e, f, g, h                                       ] = ???
    final def apply[a, b, c, d, e, f, g, h, i                                       ] (txMetaData: Composite_09[a, b, c, d, e, f, g, h, i                                       ]): Molecule_10[A, a, b, c, d, e, f, g, h, i                                    ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j                                    ] (txMetaData: Composite_10[a, b, c, d, e, f, g, h, i, j                                    ]): Molecule_11[A, a, b, c, d, e, f, g, h, i, j                                 ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k                                 ] (txMetaData: Composite_11[a, b, c, d, e, f, g, h, i, j, k                                 ]): Molecule_12[A, a, b, c, d, e, f, g, h, i, j, k                              ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l                              ] (txMetaData: Composite_12[a, b, c, d, e, f, g, h, i, j, k, l                              ]): Molecule_13[A, a, b, c, d, e, f, g, h, i, j, k, l                           ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m                           ] (txMetaData: Composite_13[a, b, c, d, e, f, g, h, i, j, k, l, m                           ]): Molecule_14[A, a, b, c, d, e, f, g, h, i, j, k, l, m                        ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ] (txMetaData: Composite_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ]): Molecule_15[A, a, b, c, d, e, f, g, h, i, j, k, l, m, n                     ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ] (txMetaData: Composite_15[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ]): Molecule_16[A, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                  ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ] (txMetaData: Composite_16[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ]): Molecule_17[A, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p               ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ] (txMetaData: Composite_17[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ]): Molecule_18[A, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q            ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ] (txMetaData: Composite_18[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ]): Molecule_19[A, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r         ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ] (txMetaData: Composite_19[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ]): Molecule_20[A, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s      ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ] (txMetaData: Composite_20[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ]): Molecule_21[A, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t   ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ] (txMetaData: Composite_21[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ]): Molecule_22[A, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u] = ???
  }
}

trait Tx_2[A, B] extends TxMetaDataBase {
  object Tx {
    final def apply                                                             (txMetaData: Molecule_00                                                            ): Molecule_02[A, B                                                            ] = ???
    final def apply[a                                                         ] (txMetaData: Molecule_01[a                                                         ]): Molecule_03[A, B, a                                                         ] = ???
    final def apply[a, b                                                      ] (txMetaData: Molecule_02[a, b                                                      ]): Molecule_04[A, B, a, b                                                      ] = ???
    final def apply[a, b, c                                                   ] (txMetaData: Molecule_03[a, b, c                                                   ]): Molecule_05[A, B, a, b, c                                                   ] = ???
    final def apply[a, b, c, d                                                ] (txMetaData: Molecule_04[a, b, c, d                                                ]): Molecule_06[A, B, a, b, c, d                                                ] = ???
    final def apply[a, b, c, d, e                                             ] (txMetaData: Molecule_05[a, b, c, d, e                                             ]): Molecule_07[A, B, a, b, c, d, e                                             ] = ???
    final def apply[a, b, c, d, e, f                                          ] (txMetaData: Molecule_06[a, b, c, d, e, f                                          ]): Molecule_08[A, B, a, b, c, d, e, f                                          ] = ???
    final def apply[a, b, c, d, e, f, g                                       ] (txMetaData: Molecule_07[a, b, c, d, e, f, g                                       ]): Molecule_09[A, B, a, b, c, d, e, f, g                                       ] = ???
    final def apply[a, b, c, d, e, f, g, h                                    ] (txMetaData: Molecule_08[a, b, c, d, e, f, g, h                                    ]): Molecule_10[A, B, a, b, c, d, e, f, g, h                                    ] = ???
    final def apply[a, b, c, d, e, f, g, h, i                                 ] (txMetaData: Molecule_09[a, b, c, d, e, f, g, h, i                                 ]): Molecule_11[A, B, a, b, c, d, e, f, g, h, i                                 ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j                              ] (txMetaData: Molecule_10[a, b, c, d, e, f, g, h, i, j                              ]): Molecule_12[A, B, a, b, c, d, e, f, g, h, i, j                              ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k                           ] (txMetaData: Molecule_11[a, b, c, d, e, f, g, h, i, j, k                           ]): Molecule_13[A, B, a, b, c, d, e, f, g, h, i, j, k                           ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l                        ] (txMetaData: Molecule_12[a, b, c, d, e, f, g, h, i, j, k, l                        ]): Molecule_14[A, B, a, b, c, d, e, f, g, h, i, j, k, l                        ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m                     ] (txMetaData: Molecule_13[a, b, c, d, e, f, g, h, i, j, k, l, m                     ]): Molecule_15[A, B, a, b, c, d, e, f, g, h, i, j, k, l, m                     ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n                  ] (txMetaData: Molecule_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n                  ]): Molecule_16[A, B, a, b, c, d, e, f, g, h, i, j, k, l, m, n                  ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o               ] (txMetaData: Molecule_15[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o               ]): Molecule_17[A, B, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o               ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p            ] (txMetaData: Molecule_16[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p            ]): Molecule_18[A, B, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p            ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q         ] (txMetaData: Molecule_17[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q         ]): Molecule_19[A, B, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q         ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r      ] (txMetaData: Molecule_18[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r      ]): Molecule_20[A, B, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r      ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s   ] (txMetaData: Molecule_19[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s   ]): Molecule_21[A, B, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s   ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t] (txMetaData: Molecule_20[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t]): Molecule_22[A, B, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t] = ???

    final def apply                                                             (txMetaData: Composite_00                                                            ): Molecule_02[A, B                                                            ] = ???
    final def apply[a                                                         ] (txMetaData: Composite_01[a                                                         ]): Molecule_03[A, B, a                                                         ] = ???
    final def apply[a, b                                                      ] (txMetaData: Composite_02[a, b                                                      ]): Molecule_04[A, B, a, b                                                      ] = ???
    final def apply[a, b, c                                                   ] (txMetaData: Composite_03[a, b, c                                                   ]): Molecule_05[A, B, a, b, c                                                   ] = ???
    final def apply[a, b, c, d                                                ] (txMetaData: Composite_04[a, b, c, d                                                ]): Molecule_06[A, B, a, b, c, d                                                ] = ???
    final def apply[a, b, c, d, e                                             ] (txMetaData: Composite_05[a, b, c, d, e                                             ]): Molecule_07[A, B, a, b, c, d, e                                             ] = ???
    final def apply[a, b, c, d, e, f                                          ] (txMetaData: Composite_06[a, b, c, d, e, f                                          ]): Molecule_08[A, B, a, b, c, d, e, f                                          ] = ???
    final def apply[a, b, c, d, e, f, g                                       ] (txMetaData: Composite_07[a, b, c, d, e, f, g                                       ]): Molecule_09[A, B, a, b, c, d, e, f, g                                       ] = ???
    final def apply[a, b, c, d, e, f, g, h                                    ] (txMetaData: Composite_08[a, b, c, d, e, f, g, h                                    ]): Molecule_10[A, B, a, b, c, d, e, f, g, h                                    ] = ???
    final def apply[a, b, c, d, e, f, g, h, i                                 ] (txMetaData: Composite_09[a, b, c, d, e, f, g, h, i                                 ]): Molecule_11[A, B, a, b, c, d, e, f, g, h, i                                 ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j                              ] (txMetaData: Composite_10[a, b, c, d, e, f, g, h, i, j                              ]): Molecule_12[A, B, a, b, c, d, e, f, g, h, i, j                              ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k                           ] (txMetaData: Composite_11[a, b, c, d, e, f, g, h, i, j, k                           ]): Molecule_13[A, B, a, b, c, d, e, f, g, h, i, j, k                           ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l                        ] (txMetaData: Composite_12[a, b, c, d, e, f, g, h, i, j, k, l                        ]): Molecule_14[A, B, a, b, c, d, e, f, g, h, i, j, k, l                        ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m                     ] (txMetaData: Composite_13[a, b, c, d, e, f, g, h, i, j, k, l, m                     ]): Molecule_15[A, B, a, b, c, d, e, f, g, h, i, j, k, l, m                     ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n                  ] (txMetaData: Composite_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n                  ]): Molecule_16[A, B, a, b, c, d, e, f, g, h, i, j, k, l, m, n                  ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o               ] (txMetaData: Composite_15[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o               ]): Molecule_17[A, B, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o               ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p            ] (txMetaData: Composite_16[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p            ]): Molecule_18[A, B, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p            ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q         ] (txMetaData: Composite_17[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q         ]): Molecule_19[A, B, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q         ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r      ] (txMetaData: Composite_18[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r      ]): Molecule_20[A, B, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r      ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s   ] (txMetaData: Composite_19[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s   ]): Molecule_21[A, B, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s   ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t] (txMetaData: Composite_20[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t]): Molecule_22[A, B, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t] = ???
  }
}

trait Tx_3[A, B, C] extends TxMetaDataBase {
  object Tx {
    final def apply                                                          (txMetaData: Molecule_00                                                         ): Molecule_03[A, B, C                                                         ] = ???
    final def apply[a                                                      ] (txMetaData: Molecule_01[a                                                      ]): Molecule_04[A, B, C, a                                                      ] = ???
    final def apply[a, b                                                   ] (txMetaData: Molecule_02[a, b                                                   ]): Molecule_05[A, B, C, a, b                                                   ] = ???
    final def apply[a, b, c                                                ] (txMetaData: Molecule_03[a, b, c                                                ]): Molecule_06[A, B, C, a, b, c                                                ] = ???
    final def apply[a, b, c, d                                             ] (txMetaData: Molecule_04[a, b, c, d                                             ]): Molecule_07[A, B, C, a, b, c, d                                             ] = ???
    final def apply[a, b, c, d, e                                          ] (txMetaData: Molecule_05[a, b, c, d, e                                          ]): Molecule_08[A, B, C, a, b, c, d, e                                          ] = ???
    final def apply[a, b, c, d, e, f                                       ] (txMetaData: Molecule_06[a, b, c, d, e, f                                       ]): Molecule_09[A, B, C, a, b, c, d, e, f                                       ] = ???
    final def apply[a, b, c, d, e, f, g                                    ] (txMetaData: Molecule_07[a, b, c, d, e, f, g                                    ]): Molecule_10[A, B, C, a, b, c, d, e, f, g                                    ] = ???
    final def apply[a, b, c, d, e, f, g, h                                 ] (txMetaData: Molecule_08[a, b, c, d, e, f, g, h                                 ]): Molecule_11[A, B, C, a, b, c, d, e, f, g, h                                 ] = ???
    final def apply[a, b, c, d, e, f, g, h, i                              ] (txMetaData: Molecule_09[a, b, c, d, e, f, g, h, i                              ]): Molecule_12[A, B, C, a, b, c, d, e, f, g, h, i                              ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j                           ] (txMetaData: Molecule_10[a, b, c, d, e, f, g, h, i, j                           ]): Molecule_13[A, B, C, a, b, c, d, e, f, g, h, i, j                           ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k                        ] (txMetaData: Molecule_11[a, b, c, d, e, f, g, h, i, j, k                        ]): Molecule_14[A, B, C, a, b, c, d, e, f, g, h, i, j, k                        ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l                     ] (txMetaData: Molecule_12[a, b, c, d, e, f, g, h, i, j, k, l                     ]): Molecule_15[A, B, C, a, b, c, d, e, f, g, h, i, j, k, l                     ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m                  ] (txMetaData: Molecule_13[a, b, c, d, e, f, g, h, i, j, k, l, m                  ]): Molecule_16[A, B, C, a, b, c, d, e, f, g, h, i, j, k, l, m                  ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n               ] (txMetaData: Molecule_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n               ]): Molecule_17[A, B, C, a, b, c, d, e, f, g, h, i, j, k, l, m, n               ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o            ] (txMetaData: Molecule_15[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o            ]): Molecule_18[A, B, C, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o            ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p         ] (txMetaData: Molecule_16[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p         ]): Molecule_19[A, B, C, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p         ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q      ] (txMetaData: Molecule_17[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q      ]): Molecule_20[A, B, C, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q      ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r   ] (txMetaData: Molecule_18[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r   ]): Molecule_21[A, B, C, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r   ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s] (txMetaData: Molecule_19[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s]): Molecule_22[A, B, C, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s] = ???

    final def apply                                                          (txMetaData: Composite_00                                                         ): Molecule_03[A, B, C                                                         ] = ???
    final def apply[a                                                      ] (txMetaData: Composite_01[a                                                      ]): Molecule_04[A, B, C, a                                                      ] = ???
    final def apply[a, b                                                   ] (txMetaData: Composite_02[a, b                                                   ]): Molecule_05[A, B, C, a, b                                                   ] = ???
    final def apply[a, b, c                                                ] (txMetaData: Composite_03[a, b, c                                                ]): Molecule_06[A, B, C, a, b, c                                                ] = ???
    final def apply[a, b, c, d                                             ] (txMetaData: Composite_04[a, b, c, d                                             ]): Molecule_07[A, B, C, a, b, c, d                                             ] = ???
    final def apply[a, b, c, d, e                                          ] (txMetaData: Composite_05[a, b, c, d, e                                          ]): Molecule_08[A, B, C, a, b, c, d, e                                          ] = ???
    final def apply[a, b, c, d, e, f                                       ] (txMetaData: Composite_06[a, b, c, d, e, f                                       ]): Molecule_09[A, B, C, a, b, c, d, e, f                                       ] = ???
    final def apply[a, b, c, d, e, f, g                                    ] (txMetaData: Composite_07[a, b, c, d, e, f, g                                    ]): Molecule_10[A, B, C, a, b, c, d, e, f, g                                    ] = ???
    final def apply[a, b, c, d, e, f, g, h                                 ] (txMetaData: Composite_08[a, b, c, d, e, f, g, h                                 ]): Molecule_11[A, B, C, a, b, c, d, e, f, g, h                                 ] = ???
    final def apply[a, b, c, d, e, f, g, h, i                              ] (txMetaData: Composite_09[a, b, c, d, e, f, g, h, i                              ]): Molecule_12[A, B, C, a, b, c, d, e, f, g, h, i                              ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j                           ] (txMetaData: Composite_10[a, b, c, d, e, f, g, h, i, j                           ]): Molecule_13[A, B, C, a, b, c, d, e, f, g, h, i, j                           ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k                        ] (txMetaData: Composite_11[a, b, c, d, e, f, g, h, i, j, k                        ]): Molecule_14[A, B, C, a, b, c, d, e, f, g, h, i, j, k                        ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l                     ] (txMetaData: Composite_12[a, b, c, d, e, f, g, h, i, j, k, l                     ]): Molecule_15[A, B, C, a, b, c, d, e, f, g, h, i, j, k, l                     ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m                  ] (txMetaData: Composite_13[a, b, c, d, e, f, g, h, i, j, k, l, m                  ]): Molecule_16[A, B, C, a, b, c, d, e, f, g, h, i, j, k, l, m                  ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n               ] (txMetaData: Composite_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n               ]): Molecule_17[A, B, C, a, b, c, d, e, f, g, h, i, j, k, l, m, n               ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o            ] (txMetaData: Composite_15[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o            ]): Molecule_18[A, B, C, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o            ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p         ] (txMetaData: Composite_16[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p         ]): Molecule_19[A, B, C, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p         ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q      ] (txMetaData: Composite_17[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q      ]): Molecule_20[A, B, C, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q      ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r   ] (txMetaData: Composite_18[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r   ]): Molecule_21[A, B, C, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r   ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s] (txMetaData: Composite_19[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s]): Molecule_22[A, B, C, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s] = ???
  }
}

trait Tx_4[A, B, C, D] extends TxMetaDataBase {
  object Tx {
    final def apply                                                       (txMetaData: Molecule_00                                                      ): Molecule_04[A, B, C, D                                                      ] = ???
    final def apply[a                                                   ] (txMetaData: Molecule_01[a                                                   ]): Molecule_05[A, B, C, D, a                                                   ] = ???
    final def apply[a, b                                                ] (txMetaData: Molecule_02[a, b                                                ]): Molecule_06[A, B, C, D, a, b                                                ] = ???
    final def apply[a, b, c                                             ] (txMetaData: Molecule_03[a, b, c                                             ]): Molecule_07[A, B, C, D, a, b, c                                             ] = ???
    final def apply[a, b, c, d                                          ] (txMetaData: Molecule_04[a, b, c, d                                          ]): Molecule_08[A, B, C, D, a, b, c, d                                          ] = ???
    final def apply[a, b, c, d, e                                       ] (txMetaData: Molecule_05[a, b, c, d, e                                       ]): Molecule_09[A, B, C, D, a, b, c, d, e                                       ] = ???
    final def apply[a, b, c, d, e, f                                    ] (txMetaData: Molecule_06[a, b, c, d, e, f                                    ]): Molecule_10[A, B, C, D, a, b, c, d, e, f                                    ] = ???
    final def apply[a, b, c, d, e, f, g                                 ] (txMetaData: Molecule_07[a, b, c, d, e, f, g                                 ]): Molecule_11[A, B, C, D, a, b, c, d, e, f, g                                 ] = ???
    final def apply[a, b, c, d, e, f, g, h                              ] (txMetaData: Molecule_08[a, b, c, d, e, f, g, h                              ]): Molecule_12[A, B, C, D, a, b, c, d, e, f, g, h                              ] = ???
    final def apply[a, b, c, d, e, f, g, h, i                           ] (txMetaData: Molecule_09[a, b, c, d, e, f, g, h, i                           ]): Molecule_13[A, B, C, D, a, b, c, d, e, f, g, h, i                           ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j                        ] (txMetaData: Molecule_10[a, b, c, d, e, f, g, h, i, j                        ]): Molecule_14[A, B, C, D, a, b, c, d, e, f, g, h, i, j                        ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k                     ] (txMetaData: Molecule_11[a, b, c, d, e, f, g, h, i, j, k                     ]): Molecule_15[A, B, C, D, a, b, c, d, e, f, g, h, i, j, k                     ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l                  ] (txMetaData: Molecule_12[a, b, c, d, e, f, g, h, i, j, k, l                  ]): Molecule_16[A, B, C, D, a, b, c, d, e, f, g, h, i, j, k, l                  ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m               ] (txMetaData: Molecule_13[a, b, c, d, e, f, g, h, i, j, k, l, m               ]): Molecule_17[A, B, C, D, a, b, c, d, e, f, g, h, i, j, k, l, m               ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n            ] (txMetaData: Molecule_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n            ]): Molecule_18[A, B, C, D, a, b, c, d, e, f, g, h, i, j, k, l, m, n            ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o         ] (txMetaData: Molecule_15[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o         ]): Molecule_19[A, B, C, D, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o         ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p      ] (txMetaData: Molecule_16[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p      ]): Molecule_20[A, B, C, D, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p      ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q   ] (txMetaData: Molecule_17[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q   ]): Molecule_21[A, B, C, D, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q   ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r] (txMetaData: Molecule_18[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r]): Molecule_22[A, B, C, D, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r] = ???

    final def apply                                                       (txMetaData: Composite_00                                                      ): Molecule_04[A, B, C, D                                                      ] = ???
    final def apply[a                                                   ] (txMetaData: Composite_01[a                                                   ]): Molecule_05[A, B, C, D, a                                                   ] = ???
    final def apply[a, b                                                ] (txMetaData: Composite_02[a, b                                                ]): Molecule_06[A, B, C, D, a, b                                                ] = ???
    final def apply[a, b, c                                             ] (txMetaData: Composite_03[a, b, c                                             ]): Molecule_07[A, B, C, D, a, b, c                                             ] = ???
    final def apply[a, b, c, d                                          ] (txMetaData: Composite_04[a, b, c, d                                          ]): Molecule_08[A, B, C, D, a, b, c, d                                          ] = ???
    final def apply[a, b, c, d, e                                       ] (txMetaData: Composite_05[a, b, c, d, e                                       ]): Molecule_09[A, B, C, D, a, b, c, d, e                                       ] = ???
    final def apply[a, b, c, d, e, f                                    ] (txMetaData: Composite_06[a, b, c, d, e, f                                    ]): Molecule_10[A, B, C, D, a, b, c, d, e, f                                    ] = ???
    final def apply[a, b, c, d, e, f, g                                 ] (txMetaData: Composite_07[a, b, c, d, e, f, g                                 ]): Molecule_11[A, B, C, D, a, b, c, d, e, f, g                                 ] = ???
    final def apply[a, b, c, d, e, f, g, h                              ] (txMetaData: Composite_08[a, b, c, d, e, f, g, h                              ]): Molecule_12[A, B, C, D, a, b, c, d, e, f, g, h                              ] = ???
    final def apply[a, b, c, d, e, f, g, h, i                           ] (txMetaData: Composite_09[a, b, c, d, e, f, g, h, i                           ]): Molecule_13[A, B, C, D, a, b, c, d, e, f, g, h, i                           ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j                        ] (txMetaData: Composite_10[a, b, c, d, e, f, g, h, i, j                        ]): Molecule_14[A, B, C, D, a, b, c, d, e, f, g, h, i, j                        ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k                     ] (txMetaData: Composite_11[a, b, c, d, e, f, g, h, i, j, k                     ]): Molecule_15[A, B, C, D, a, b, c, d, e, f, g, h, i, j, k                     ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l                  ] (txMetaData: Composite_12[a, b, c, d, e, f, g, h, i, j, k, l                  ]): Molecule_16[A, B, C, D, a, b, c, d, e, f, g, h, i, j, k, l                  ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m               ] (txMetaData: Composite_13[a, b, c, d, e, f, g, h, i, j, k, l, m               ]): Molecule_17[A, B, C, D, a, b, c, d, e, f, g, h, i, j, k, l, m               ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n            ] (txMetaData: Composite_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n            ]): Molecule_18[A, B, C, D, a, b, c, d, e, f, g, h, i, j, k, l, m, n            ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o         ] (txMetaData: Composite_15[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o         ]): Molecule_19[A, B, C, D, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o         ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p      ] (txMetaData: Composite_16[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p      ]): Molecule_20[A, B, C, D, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p      ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q   ] (txMetaData: Composite_17[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q   ]): Molecule_21[A, B, C, D, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q   ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r] (txMetaData: Composite_18[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r]): Molecule_22[A, B, C, D, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r] = ???
  }
}

trait Tx_5[A, B, C, D, E] extends TxMetaDataBase {
  object Tx {
    final def apply                                                    (txMetaData: Molecule_00                                                   ): Molecule_05[A, B, C, D, E                                                   ] = ???
    final def apply[a                                                ] (txMetaData: Molecule_01[a                                                ]): Molecule_06[A, B, C, D, E, a                                                ] = ???
    final def apply[a, b                                             ] (txMetaData: Molecule_02[a, b                                             ]): Molecule_07[A, B, C, D, E, a, b                                             ] = ???
    final def apply[a, b, c                                          ] (txMetaData: Molecule_03[a, b, c                                          ]): Molecule_08[A, B, C, D, E, a, b, c                                          ] = ???
    final def apply[a, b, c, d                                       ] (txMetaData: Molecule_04[a, b, c, d                                       ]): Molecule_09[A, B, C, D, E, a, b, c, d                                       ] = ???
    final def apply[a, b, c, d, e                                    ] (txMetaData: Molecule_05[a, b, c, d, e                                    ]): Molecule_10[A, B, C, D, E, a, b, c, d, e                                    ] = ???
    final def apply[a, b, c, d, e, f                                 ] (txMetaData: Molecule_06[a, b, c, d, e, f                                 ]): Molecule_11[A, B, C, D, E, a, b, c, d, e, f                                 ] = ???
    final def apply[a, b, c, d, e, f, g                              ] (txMetaData: Molecule_07[a, b, c, d, e, f, g                              ]): Molecule_12[A, B, C, D, E, a, b, c, d, e, f, g                              ] = ???
    final def apply[a, b, c, d, e, f, g, h                           ] (txMetaData: Molecule_08[a, b, c, d, e, f, g, h                           ]): Molecule_13[A, B, C, D, E, a, b, c, d, e, f, g, h                           ] = ???
    final def apply[a, b, c, d, e, f, g, h, i                        ] (txMetaData: Molecule_09[a, b, c, d, e, f, g, h, i                        ]): Molecule_14[A, B, C, D, E, a, b, c, d, e, f, g, h, i                        ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j                     ] (txMetaData: Molecule_10[a, b, c, d, e, f, g, h, i, j                     ]): Molecule_15[A, B, C, D, E, a, b, c, d, e, f, g, h, i, j                     ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k                  ] (txMetaData: Molecule_11[a, b, c, d, e, f, g, h, i, j, k                  ]): Molecule_16[A, B, C, D, E, a, b, c, d, e, f, g, h, i, j, k                  ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l               ] (txMetaData: Molecule_12[a, b, c, d, e, f, g, h, i, j, k, l               ]): Molecule_17[A, B, C, D, E, a, b, c, d, e, f, g, h, i, j, k, l               ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m            ] (txMetaData: Molecule_13[a, b, c, d, e, f, g, h, i, j, k, l, m            ]): Molecule_18[A, B, C, D, E, a, b, c, d, e, f, g, h, i, j, k, l, m            ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n         ] (txMetaData: Molecule_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n         ]): Molecule_19[A, B, C, D, E, a, b, c, d, e, f, g, h, i, j, k, l, m, n         ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o      ] (txMetaData: Molecule_15[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o      ]): Molecule_20[A, B, C, D, E, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o      ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p   ] (txMetaData: Molecule_16[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p   ]): Molecule_21[A, B, C, D, E, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p   ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q] (txMetaData: Molecule_17[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q]): Molecule_22[A, B, C, D, E, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q] = ???

    final def apply                                                    (txMetaData: Composite_00                                                   ): Molecule_05[A, B, C, D, E                                                   ] = ???
    final def apply[a                                                ] (txMetaData: Composite_01[a                                                ]): Molecule_06[A, B, C, D, E, a                                                ] = ???
    final def apply[a, b                                             ] (txMetaData: Composite_02[a, b                                             ]): Molecule_07[A, B, C, D, E, a, b                                             ] = ???
    final def apply[a, b, c                                          ] (txMetaData: Composite_03[a, b, c                                          ]): Molecule_08[A, B, C, D, E, a, b, c                                          ] = ???
    final def apply[a, b, c, d                                       ] (txMetaData: Composite_04[a, b, c, d                                       ]): Molecule_09[A, B, C, D, E, a, b, c, d                                       ] = ???
    final def apply[a, b, c, d, e                                    ] (txMetaData: Composite_05[a, b, c, d, e                                    ]): Molecule_10[A, B, C, D, E, a, b, c, d, e                                    ] = ???
    final def apply[a, b, c, d, e, f                                 ] (txMetaData: Composite_06[a, b, c, d, e, f                                 ]): Molecule_11[A, B, C, D, E, a, b, c, d, e, f                                 ] = ???
    final def apply[a, b, c, d, e, f, g                              ] (txMetaData: Composite_07[a, b, c, d, e, f, g                              ]): Molecule_12[A, B, C, D, E, a, b, c, d, e, f, g                              ] = ???
    final def apply[a, b, c, d, e, f, g, h                           ] (txMetaData: Composite_08[a, b, c, d, e, f, g, h                           ]): Molecule_13[A, B, C, D, E, a, b, c, d, e, f, g, h                           ] = ???
    final def apply[a, b, c, d, e, f, g, h, i                        ] (txMetaData: Composite_09[a, b, c, d, e, f, g, h, i                        ]): Molecule_14[A, B, C, D, E, a, b, c, d, e, f, g, h, i                        ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j                     ] (txMetaData: Composite_10[a, b, c, d, e, f, g, h, i, j                     ]): Molecule_15[A, B, C, D, E, a, b, c, d, e, f, g, h, i, j                     ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k                  ] (txMetaData: Composite_11[a, b, c, d, e, f, g, h, i, j, k                  ]): Molecule_16[A, B, C, D, E, a, b, c, d, e, f, g, h, i, j, k                  ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l               ] (txMetaData: Composite_12[a, b, c, d, e, f, g, h, i, j, k, l               ]): Molecule_17[A, B, C, D, E, a, b, c, d, e, f, g, h, i, j, k, l               ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m            ] (txMetaData: Composite_13[a, b, c, d, e, f, g, h, i, j, k, l, m            ]): Molecule_18[A, B, C, D, E, a, b, c, d, e, f, g, h, i, j, k, l, m            ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n         ] (txMetaData: Composite_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n         ]): Molecule_19[A, B, C, D, E, a, b, c, d, e, f, g, h, i, j, k, l, m, n         ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o      ] (txMetaData: Composite_15[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o      ]): Molecule_20[A, B, C, D, E, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o      ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p   ] (txMetaData: Composite_16[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p   ]): Molecule_21[A, B, C, D, E, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p   ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q] (txMetaData: Composite_17[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q]): Molecule_22[A, B, C, D, E, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q] = ???
  }
}

trait Tx_6[A, B, C, D, E, F] extends TxMetaDataBase {
  object Tx {
    final def apply                                                 (txMetaData: Molecule_00                                                ): Molecule_06[A, B, C, D, E, F                                                ] = ???
    final def apply[a                                             ] (txMetaData: Molecule_01[a                                             ]): Molecule_07[A, B, C, D, E, F, a                                             ] = ???
    final def apply[a, b                                          ] (txMetaData: Molecule_02[a, b                                          ]): Molecule_08[A, B, C, D, E, F, a, b                                          ] = ???
    final def apply[a, b, c                                       ] (txMetaData: Molecule_03[a, b, c                                       ]): Molecule_09[A, B, C, D, E, F, a, b, c                                       ] = ???
    final def apply[a, b, c, d                                    ] (txMetaData: Molecule_04[a, b, c, d                                    ]): Molecule_10[A, B, C, D, E, F, a, b, c, d                                    ] = ???
    final def apply[a, b, c, d, e                                 ] (txMetaData: Molecule_05[a, b, c, d, e                                 ]): Molecule_11[A, B, C, D, E, F, a, b, c, d, e                                 ] = ???
    final def apply[a, b, c, d, e, f                              ] (txMetaData: Molecule_06[a, b, c, d, e, f                              ]): Molecule_12[A, B, C, D, E, F, a, b, c, d, e, f                              ] = ???
    final def apply[a, b, c, d, e, f, g                           ] (txMetaData: Molecule_07[a, b, c, d, e, f, g                           ]): Molecule_13[A, B, C, D, E, F, a, b, c, d, e, f, g                           ] = ???
    final def apply[a, b, c, d, e, f, g, h                        ] (txMetaData: Molecule_08[a, b, c, d, e, f, g, h                        ]): Molecule_14[A, B, C, D, E, F, a, b, c, d, e, f, g, h                        ] = ???
    final def apply[a, b, c, d, e, f, g, h, i                     ] (txMetaData: Molecule_09[a, b, c, d, e, f, g, h, i                     ]): Molecule_15[A, B, C, D, E, F, a, b, c, d, e, f, g, h, i                     ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j                  ] (txMetaData: Molecule_10[a, b, c, d, e, f, g, h, i, j                  ]): Molecule_16[A, B, C, D, E, F, a, b, c, d, e, f, g, h, i, j                  ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k               ] (txMetaData: Molecule_11[a, b, c, d, e, f, g, h, i, j, k               ]): Molecule_17[A, B, C, D, E, F, a, b, c, d, e, f, g, h, i, j, k               ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l            ] (txMetaData: Molecule_12[a, b, c, d, e, f, g, h, i, j, k, l            ]): Molecule_18[A, B, C, D, E, F, a, b, c, d, e, f, g, h, i, j, k, l            ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m         ] (txMetaData: Molecule_13[a, b, c, d, e, f, g, h, i, j, k, l, m         ]): Molecule_19[A, B, C, D, E, F, a, b, c, d, e, f, g, h, i, j, k, l, m         ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n      ] (txMetaData: Molecule_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n      ]): Molecule_20[A, B, C, D, E, F, a, b, c, d, e, f, g, h, i, j, k, l, m, n      ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o   ] (txMetaData: Molecule_15[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o   ]): Molecule_21[A, B, C, D, E, F, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o   ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p] (txMetaData: Molecule_16[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p]): Molecule_22[A, B, C, D, E, F, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p] = ???

    final def apply                                                 (txMetaData: Composite_00                                                ): Molecule_06[A, B, C, D, E, F                                                ] = ???
    final def apply[a                                             ] (txMetaData: Composite_01[a                                             ]): Molecule_07[A, B, C, D, E, F, a                                             ] = ???
    final def apply[a, b                                          ] (txMetaData: Composite_02[a, b                                          ]): Molecule_08[A, B, C, D, E, F, a, b                                          ] = ???
    final def apply[a, b, c                                       ] (txMetaData: Composite_03[a, b, c                                       ]): Molecule_09[A, B, C, D, E, F, a, b, c                                       ] = ???
    final def apply[a, b, c, d                                    ] (txMetaData: Composite_04[a, b, c, d                                    ]): Molecule_10[A, B, C, D, E, F, a, b, c, d                                    ] = ???
    final def apply[a, b, c, d, e                                 ] (txMetaData: Composite_05[a, b, c, d, e                                 ]): Molecule_11[A, B, C, D, E, F, a, b, c, d, e                                 ] = ???
    final def apply[a, b, c, d, e, f                              ] (txMetaData: Composite_06[a, b, c, d, e, f                              ]): Molecule_12[A, B, C, D, E, F, a, b, c, d, e, f                              ] = ???
    final def apply[a, b, c, d, e, f, g                           ] (txMetaData: Composite_07[a, b, c, d, e, f, g                           ]): Molecule_13[A, B, C, D, E, F, a, b, c, d, e, f, g                           ] = ???
    final def apply[a, b, c, d, e, f, g, h                        ] (txMetaData: Composite_08[a, b, c, d, e, f, g, h                        ]): Molecule_14[A, B, C, D, E, F, a, b, c, d, e, f, g, h                        ] = ???
    final def apply[a, b, c, d, e, f, g, h, i                     ] (txMetaData: Composite_09[a, b, c, d, e, f, g, h, i                     ]): Molecule_15[A, B, C, D, E, F, a, b, c, d, e, f, g, h, i                     ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j                  ] (txMetaData: Composite_10[a, b, c, d, e, f, g, h, i, j                  ]): Molecule_16[A, B, C, D, E, F, a, b, c, d, e, f, g, h, i, j                  ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k               ] (txMetaData: Composite_11[a, b, c, d, e, f, g, h, i, j, k               ]): Molecule_17[A, B, C, D, E, F, a, b, c, d, e, f, g, h, i, j, k               ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l            ] (txMetaData: Composite_12[a, b, c, d, e, f, g, h, i, j, k, l            ]): Molecule_18[A, B, C, D, E, F, a, b, c, d, e, f, g, h, i, j, k, l            ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m         ] (txMetaData: Composite_13[a, b, c, d, e, f, g, h, i, j, k, l, m         ]): Molecule_19[A, B, C, D, E, F, a, b, c, d, e, f, g, h, i, j, k, l, m         ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n      ] (txMetaData: Composite_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n      ]): Molecule_20[A, B, C, D, E, F, a, b, c, d, e, f, g, h, i, j, k, l, m, n      ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o   ] (txMetaData: Composite_15[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o   ]): Molecule_21[A, B, C, D, E, F, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o   ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p] (txMetaData: Composite_16[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p]): Molecule_22[A, B, C, D, E, F, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p] = ???

  }
}

trait Tx_7[A, B, C, D, E, F, G] extends TxMetaDataBase {
  object Tx {
    final def apply                                              (txMetaData: Molecule_00                                             ): Molecule_07[A, B, C, D, E, F, G                                             ] = ???
    final def apply[a                                          ] (txMetaData: Molecule_01[a                                          ]): Molecule_08[A, B, C, D, E, F, G, a                                          ] = ???
    final def apply[a, b                                       ] (txMetaData: Molecule_02[a, b                                       ]): Molecule_09[A, B, C, D, E, F, G, a, b                                       ] = ???
    final def apply[a, b, c                                    ] (txMetaData: Molecule_03[a, b, c                                    ]): Molecule_10[A, B, C, D, E, F, G, a, b, c                                    ] = ???
    final def apply[a, b, c, d                                 ] (txMetaData: Molecule_04[a, b, c, d                                 ]): Molecule_11[A, B, C, D, E, F, G, a, b, c, d                                 ] = ???
    final def apply[a, b, c, d, e                              ] (txMetaData: Molecule_05[a, b, c, d, e                              ]): Molecule_12[A, B, C, D, E, F, G, a, b, c, d, e                              ] = ???
    final def apply[a, b, c, d, e, f                           ] (txMetaData: Molecule_06[a, b, c, d, e, f                           ]): Molecule_13[A, B, C, D, E, F, G, a, b, c, d, e, f                           ] = ???
    final def apply[a, b, c, d, e, f, g                        ] (txMetaData: Molecule_07[a, b, c, d, e, f, g                        ]): Molecule_14[A, B, C, D, E, F, G, a, b, c, d, e, f, g                        ] = ???
    final def apply[a, b, c, d, e, f, g, h                     ] (txMetaData: Molecule_08[a, b, c, d, e, f, g, h                     ]): Molecule_15[A, B, C, D, E, F, G, a, b, c, d, e, f, g, h                     ] = ???
    final def apply[a, b, c, d, e, f, g, h, i                  ] (txMetaData: Molecule_09[a, b, c, d, e, f, g, h, i                  ]): Molecule_16[A, B, C, D, E, F, G, a, b, c, d, e, f, g, h, i                  ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j               ] (txMetaData: Molecule_10[a, b, c, d, e, f, g, h, i, j               ]): Molecule_17[A, B, C, D, E, F, G, a, b, c, d, e, f, g, h, i, j               ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k            ] (txMetaData: Molecule_11[a, b, c, d, e, f, g, h, i, j, k            ]): Molecule_18[A, B, C, D, E, F, G, a, b, c, d, e, f, g, h, i, j, k            ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l         ] (txMetaData: Molecule_12[a, b, c, d, e, f, g, h, i, j, k, l         ]): Molecule_19[A, B, C, D, E, F, G, a, b, c, d, e, f, g, h, i, j, k, l         ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m      ] (txMetaData: Molecule_13[a, b, c, d, e, f, g, h, i, j, k, l, m      ]): Molecule_20[A, B, C, D, E, F, G, a, b, c, d, e, f, g, h, i, j, k, l, m      ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n   ] (txMetaData: Molecule_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n   ]): Molecule_21[A, B, C, D, E, F, G, a, b, c, d, e, f, g, h, i, j, k, l, m, n   ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o] (txMetaData: Molecule_15[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o]): Molecule_22[A, B, C, D, E, F, G, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o] = ???

    final def apply                                              (txMetaData: Composite_00                                             ): Molecule_07[A, B, C, D, E, F, G                                             ] = ???
    final def apply[a                                          ] (txMetaData: Composite_01[a                                          ]): Molecule_08[A, B, C, D, E, F, G, a                                          ] = ???
    final def apply[a, b                                       ] (txMetaData: Composite_02[a, b                                       ]): Molecule_09[A, B, C, D, E, F, G, a, b                                       ] = ???
    final def apply[a, b, c                                    ] (txMetaData: Composite_03[a, b, c                                    ]): Molecule_10[A, B, C, D, E, F, G, a, b, c                                    ] = ???
    final def apply[a, b, c, d                                 ] (txMetaData: Composite_04[a, b, c, d                                 ]): Molecule_11[A, B, C, D, E, F, G, a, b, c, d                                 ] = ???
    final def apply[a, b, c, d, e                              ] (txMetaData: Composite_05[a, b, c, d, e                              ]): Molecule_12[A, B, C, D, E, F, G, a, b, c, d, e                              ] = ???
    final def apply[a, b, c, d, e, f                           ] (txMetaData: Composite_06[a, b, c, d, e, f                           ]): Molecule_13[A, B, C, D, E, F, G, a, b, c, d, e, f                           ] = ???
    final def apply[a, b, c, d, e, f, g                        ] (txMetaData: Composite_07[a, b, c, d, e, f, g                        ]): Molecule_14[A, B, C, D, E, F, G, a, b, c, d, e, f, g                        ] = ???
    final def apply[a, b, c, d, e, f, g, h                     ] (txMetaData: Composite_08[a, b, c, d, e, f, g, h                     ]): Molecule_15[A, B, C, D, E, F, G, a, b, c, d, e, f, g, h                     ] = ???
    final def apply[a, b, c, d, e, f, g, h, i                  ] (txMetaData: Composite_09[a, b, c, d, e, f, g, h, i                  ]): Molecule_16[A, B, C, D, E, F, G, a, b, c, d, e, f, g, h, i                  ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j               ] (txMetaData: Composite_10[a, b, c, d, e, f, g, h, i, j               ]): Molecule_17[A, B, C, D, E, F, G, a, b, c, d, e, f, g, h, i, j               ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k            ] (txMetaData: Composite_11[a, b, c, d, e, f, g, h, i, j, k            ]): Molecule_18[A, B, C, D, E, F, G, a, b, c, d, e, f, g, h, i, j, k            ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l         ] (txMetaData: Composite_12[a, b, c, d, e, f, g, h, i, j, k, l         ]): Molecule_19[A, B, C, D, E, F, G, a, b, c, d, e, f, g, h, i, j, k, l         ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m      ] (txMetaData: Composite_13[a, b, c, d, e, f, g, h, i, j, k, l, m      ]): Molecule_20[A, B, C, D, E, F, G, a, b, c, d, e, f, g, h, i, j, k, l, m      ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n   ] (txMetaData: Composite_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n   ]): Molecule_21[A, B, C, D, E, F, G, a, b, c, d, e, f, g, h, i, j, k, l, m, n   ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o] (txMetaData: Composite_15[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o]): Molecule_22[A, B, C, D, E, F, G, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o] = ???
  }
}

trait Tx_8[A, B, C, D, E, F, G, H] extends TxMetaDataBase {
  object Tx {
    final def apply                                           (txMetaData: Molecule_00                                          ): Molecule_08[A, B, C, D, E, F, G, H                                          ] = ???
    final def apply[a                                       ] (txMetaData: Molecule_01[a                                       ]): Molecule_09[A, B, C, D, E, F, G, H, a                                       ] = ???
    final def apply[a, b                                    ] (txMetaData: Molecule_02[a, b                                    ]): Molecule_10[A, B, C, D, E, F, G, H, a, b                                    ] = ???
    final def apply[a, b, c                                 ] (txMetaData: Molecule_03[a, b, c                                 ]): Molecule_11[A, B, C, D, E, F, G, H, a, b, c                                 ] = ???
    final def apply[a, b, c, d                              ] (txMetaData: Molecule_04[a, b, c, d                              ]): Molecule_12[A, B, C, D, E, F, G, H, a, b, c, d                              ] = ???
    final def apply[a, b, c, d, e                           ] (txMetaData: Molecule_05[a, b, c, d, e                           ]): Molecule_13[A, B, C, D, E, F, G, H, a, b, c, d, e                           ] = ???
    final def apply[a, b, c, d, e, f                        ] (txMetaData: Molecule_06[a, b, c, d, e, f                        ]): Molecule_14[A, B, C, D, E, F, G, H, a, b, c, d, e, f                        ] = ???
    final def apply[a, b, c, d, e, f, g                     ] (txMetaData: Molecule_07[a, b, c, d, e, f, g                     ]): Molecule_15[A, B, C, D, E, F, G, H, a, b, c, d, e, f, g                     ] = ???
    final def apply[a, b, c, d, e, f, g, h                  ] (txMetaData: Molecule_08[a, b, c, d, e, f, g, h                  ]): Molecule_16[A, B, C, D, E, F, G, H, a, b, c, d, e, f, g, h                  ] = ???
    final def apply[a, b, c, d, e, f, g, h, i               ] (txMetaData: Molecule_09[a, b, c, d, e, f, g, h, i               ]): Molecule_17[A, B, C, D, E, F, G, H, a, b, c, d, e, f, g, h, i               ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j            ] (txMetaData: Molecule_10[a, b, c, d, e, f, g, h, i, j            ]): Molecule_18[A, B, C, D, E, F, G, H, a, b, c, d, e, f, g, h, i, j            ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k         ] (txMetaData: Molecule_11[a, b, c, d, e, f, g, h, i, j, k         ]): Molecule_19[A, B, C, D, E, F, G, H, a, b, c, d, e, f, g, h, i, j, k         ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l      ] (txMetaData: Molecule_12[a, b, c, d, e, f, g, h, i, j, k, l      ]): Molecule_20[A, B, C, D, E, F, G, H, a, b, c, d, e, f, g, h, i, j, k, l      ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m   ] (txMetaData: Molecule_13[a, b, c, d, e, f, g, h, i, j, k, l, m   ]): Molecule_21[A, B, C, D, E, F, G, H, a, b, c, d, e, f, g, h, i, j, k, l, m   ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n] (txMetaData: Molecule_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n]): Molecule_22[A, B, C, D, E, F, G, H, a, b, c, d, e, f, g, h, i, j, k, l, m, n] = ???

    final def apply                                           (txMetaData: Composite_00                                          ): Molecule_08[A, B, C, D, E, F, G, H                                          ] = ???
    final def apply[a                                       ] (txMetaData: Composite_01[a                                       ]): Molecule_09[A, B, C, D, E, F, G, H, a                                       ] = ???
    final def apply[a, b                                    ] (txMetaData: Composite_02[a, b                                    ]): Molecule_10[A, B, C, D, E, F, G, H, a, b                                    ] = ???
    final def apply[a, b, c                                 ] (txMetaData: Composite_03[a, b, c                                 ]): Molecule_11[A, B, C, D, E, F, G, H, a, b, c                                 ] = ???
    final def apply[a, b, c, d                              ] (txMetaData: Composite_04[a, b, c, d                              ]): Molecule_12[A, B, C, D, E, F, G, H, a, b, c, d                              ] = ???
    final def apply[a, b, c, d, e                           ] (txMetaData: Composite_05[a, b, c, d, e                           ]): Molecule_13[A, B, C, D, E, F, G, H, a, b, c, d, e                           ] = ???
    final def apply[a, b, c, d, e, f                        ] (txMetaData: Composite_06[a, b, c, d, e, f                        ]): Molecule_14[A, B, C, D, E, F, G, H, a, b, c, d, e, f                        ] = ???
    final def apply[a, b, c, d, e, f, g                     ] (txMetaData: Composite_07[a, b, c, d, e, f, g                     ]): Molecule_15[A, B, C, D, E, F, G, H, a, b, c, d, e, f, g                     ] = ???
    final def apply[a, b, c, d, e, f, g, h                  ] (txMetaData: Composite_08[a, b, c, d, e, f, g, h                  ]): Molecule_16[A, B, C, D, E, F, G, H, a, b, c, d, e, f, g, h                  ] = ???
    final def apply[a, b, c, d, e, f, g, h, i               ] (txMetaData: Composite_09[a, b, c, d, e, f, g, h, i               ]): Molecule_17[A, B, C, D, E, F, G, H, a, b, c, d, e, f, g, h, i               ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j            ] (txMetaData: Composite_10[a, b, c, d, e, f, g, h, i, j            ]): Molecule_18[A, B, C, D, E, F, G, H, a, b, c, d, e, f, g, h, i, j            ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k         ] (txMetaData: Composite_11[a, b, c, d, e, f, g, h, i, j, k         ]): Molecule_19[A, B, C, D, E, F, G, H, a, b, c, d, e, f, g, h, i, j, k         ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l      ] (txMetaData: Composite_12[a, b, c, d, e, f, g, h, i, j, k, l      ]): Molecule_20[A, B, C, D, E, F, G, H, a, b, c, d, e, f, g, h, i, j, k, l      ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m   ] (txMetaData: Composite_13[a, b, c, d, e, f, g, h, i, j, k, l, m   ]): Molecule_21[A, B, C, D, E, F, G, H, a, b, c, d, e, f, g, h, i, j, k, l, m   ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m, n] (txMetaData: Composite_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n]): Molecule_22[A, B, C, D, E, F, G, H, a, b, c, d, e, f, g, h, i, j, k, l, m, n] = ???
  }
}

trait Tx_9[A, B, C, D, E, F, G, H, I] extends TxMetaDataBase {
  object Tx {
    final def apply                                        (txMetaData: Molecule_00                                       ): Molecule_09[A, B, C, D, E, F, G, H, I                                       ] = ???
    final def apply[a                                    ] (txMetaData: Molecule_01[a                                    ]): Molecule_10[A, B, C, D, E, F, G, H, I, a                                    ] = ???
    final def apply[a, b                                 ] (txMetaData: Molecule_02[a, b                                 ]): Molecule_11[A, B, C, D, E, F, G, H, I, a, b                                 ] = ???
    final def apply[a, b, c                              ] (txMetaData: Molecule_03[a, b, c                              ]): Molecule_12[A, B, C, D, E, F, G, H, I, a, b, c                              ] = ???
    final def apply[a, b, c, d                           ] (txMetaData: Molecule_04[a, b, c, d                           ]): Molecule_13[A, B, C, D, E, F, G, H, I, a, b, c, d                           ] = ???
    final def apply[a, b, c, d, e                        ] (txMetaData: Molecule_05[a, b, c, d, e                        ]): Molecule_14[A, B, C, D, E, F, G, H, I, a, b, c, d, e                        ] = ???
    final def apply[a, b, c, d, e, f                     ] (txMetaData: Molecule_06[a, b, c, d, e, f                     ]): Molecule_15[A, B, C, D, E, F, G, H, I, a, b, c, d, e, f                     ] = ???
    final def apply[a, b, c, d, e, f, g                  ] (txMetaData: Molecule_07[a, b, c, d, e, f, g                  ]): Molecule_16[A, B, C, D, E, F, G, H, I, a, b, c, d, e, f, g                  ] = ???
    final def apply[a, b, c, d, e, f, g, h               ] (txMetaData: Molecule_08[a, b, c, d, e, f, g, h               ]): Molecule_17[A, B, C, D, E, F, G, H, I, a, b, c, d, e, f, g, h               ] = ???
    final def apply[a, b, c, d, e, f, g, h, i            ] (txMetaData: Molecule_09[a, b, c, d, e, f, g, h, i            ]): Molecule_18[A, B, C, D, E, F, G, H, I, a, b, c, d, e, f, g, h, i            ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j         ] (txMetaData: Molecule_10[a, b, c, d, e, f, g, h, i, j         ]): Molecule_19[A, B, C, D, E, F, G, H, I, a, b, c, d, e, f, g, h, i, j         ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k      ] (txMetaData: Molecule_11[a, b, c, d, e, f, g, h, i, j, k      ]): Molecule_20[A, B, C, D, E, F, G, H, I, a, b, c, d, e, f, g, h, i, j, k      ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l   ] (txMetaData: Molecule_12[a, b, c, d, e, f, g, h, i, j, k, l   ]): Molecule_21[A, B, C, D, E, F, G, H, I, a, b, c, d, e, f, g, h, i, j, k, l   ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m] (txMetaData: Molecule_13[a, b, c, d, e, f, g, h, i, j, k, l, m]): Molecule_22[A, B, C, D, E, F, G, H, I, a, b, c, d, e, f, g, h, i, j, k, l, m] = ???

    final def apply                                        (txMetaData: Composite_00                                       ): Molecule_09[A, B, C, D, E, F, G, H, I                                       ] = ???
    final def apply[a                                    ] (txMetaData: Composite_01[a                                    ]): Molecule_10[A, B, C, D, E, F, G, H, I, a                                    ] = ???
    final def apply[a, b                                 ] (txMetaData: Composite_02[a, b                                 ]): Molecule_11[A, B, C, D, E, F, G, H, I, a, b                                 ] = ???
    final def apply[a, b, c                              ] (txMetaData: Composite_03[a, b, c                              ]): Molecule_12[A, B, C, D, E, F, G, H, I, a, b, c                              ] = ???
    final def apply[a, b, c, d                           ] (txMetaData: Composite_04[a, b, c, d                           ]): Molecule_13[A, B, C, D, E, F, G, H, I, a, b, c, d                           ] = ???
    final def apply[a, b, c, d, e                        ] (txMetaData: Composite_05[a, b, c, d, e                        ]): Molecule_14[A, B, C, D, E, F, G, H, I, a, b, c, d, e                        ] = ???
    final def apply[a, b, c, d, e, f                     ] (txMetaData: Composite_06[a, b, c, d, e, f                     ]): Molecule_15[A, B, C, D, E, F, G, H, I, a, b, c, d, e, f                     ] = ???
    final def apply[a, b, c, d, e, f, g                  ] (txMetaData: Composite_07[a, b, c, d, e, f, g                  ]): Molecule_16[A, B, C, D, E, F, G, H, I, a, b, c, d, e, f, g                  ] = ???
    final def apply[a, b, c, d, e, f, g, h               ] (txMetaData: Composite_08[a, b, c, d, e, f, g, h               ]): Molecule_17[A, B, C, D, E, F, G, H, I, a, b, c, d, e, f, g, h               ] = ???
    final def apply[a, b, c, d, e, f, g, h, i            ] (txMetaData: Composite_09[a, b, c, d, e, f, g, h, i            ]): Molecule_18[A, B, C, D, E, F, G, H, I, a, b, c, d, e, f, g, h, i            ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j         ] (txMetaData: Composite_10[a, b, c, d, e, f, g, h, i, j         ]): Molecule_19[A, B, C, D, E, F, G, H, I, a, b, c, d, e, f, g, h, i, j         ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k      ] (txMetaData: Composite_11[a, b, c, d, e, f, g, h, i, j, k      ]): Molecule_20[A, B, C, D, E, F, G, H, I, a, b, c, d, e, f, g, h, i, j, k      ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l   ] (txMetaData: Composite_12[a, b, c, d, e, f, g, h, i, j, k, l   ]): Molecule_21[A, B, C, D, E, F, G, H, I, a, b, c, d, e, f, g, h, i, j, k, l   ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l, m] (txMetaData: Composite_13[a, b, c, d, e, f, g, h, i, j, k, l, m]): Molecule_22[A, B, C, D, E, F, G, H, I, a, b, c, d, e, f, g, h, i, j, k, l, m] = ???
  }
}

trait Tx_10[A, B, C, D, E, F, G, H, I, J] extends TxMetaDataBase {
  object Tx {
    final def apply                                     (txMetaData: Molecule_00                                    ): Molecule_10[A, B, C, D, E, F, G, H, I, J                                    ] = ???
    final def apply[a                                 ] (txMetaData: Molecule_01[a                                 ]): Molecule_11[A, B, C, D, E, F, G, H, I, J, a                                 ] = ???
    final def apply[a, b                              ] (txMetaData: Molecule_02[a, b                              ]): Molecule_12[A, B, C, D, E, F, G, H, I, J, a, b                              ] = ???
    final def apply[a, b, c                           ] (txMetaData: Molecule_03[a, b, c                           ]): Molecule_13[A, B, C, D, E, F, G, H, I, J, a, b, c                           ] = ???
    final def apply[a, b, c, d                        ] (txMetaData: Molecule_04[a, b, c, d                        ]): Molecule_14[A, B, C, D, E, F, G, H, I, J, a, b, c, d                        ] = ???
    final def apply[a, b, c, d, e                     ] (txMetaData: Molecule_05[a, b, c, d, e                     ]): Molecule_15[A, B, C, D, E, F, G, H, I, J, a, b, c, d, e                     ] = ???
    final def apply[a, b, c, d, e, f                  ] (txMetaData: Molecule_06[a, b, c, d, e, f                  ]): Molecule_16[A, B, C, D, E, F, G, H, I, J, a, b, c, d, e, f                  ] = ???
    final def apply[a, b, c, d, e, f, g               ] (txMetaData: Molecule_07[a, b, c, d, e, f, g               ]): Molecule_17[A, B, C, D, E, F, G, H, I, J, a, b, c, d, e, f, g               ] = ???
    final def apply[a, b, c, d, e, f, g, h            ] (txMetaData: Molecule_08[a, b, c, d, e, f, g, h            ]): Molecule_18[A, B, C, D, E, F, G, H, I, J, a, b, c, d, e, f, g, h            ] = ???
    final def apply[a, b, c, d, e, f, g, h, i         ] (txMetaData: Molecule_09[a, b, c, d, e, f, g, h, i         ]): Molecule_19[A, B, C, D, E, F, G, H, I, J, a, b, c, d, e, f, g, h, i         ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j      ] (txMetaData: Molecule_10[a, b, c, d, e, f, g, h, i, j      ]): Molecule_20[A, B, C, D, E, F, G, H, I, J, a, b, c, d, e, f, g, h, i, j      ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k   ] (txMetaData: Molecule_11[a, b, c, d, e, f, g, h, i, j, k   ]): Molecule_21[A, B, C, D, E, F, G, H, I, J, a, b, c, d, e, f, g, h, i, j, k   ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l] (txMetaData: Molecule_12[a, b, c, d, e, f, g, h, i, j, k, l]): Molecule_22[A, B, C, D, E, F, G, H, I, J, a, b, c, d, e, f, g, h, i, j, k, l] = ???

    final def apply                                     (txMetaData: Composite_00                                    ): Molecule_10[A, B, C, D, E, F, G, H, I, J                                    ] = ???
    final def apply[a                                 ] (txMetaData: Composite_01[a                                 ]): Molecule_11[A, B, C, D, E, F, G, H, I, J, a                                 ] = ???
    final def apply[a, b                              ] (txMetaData: Composite_02[a, b                              ]): Molecule_12[A, B, C, D, E, F, G, H, I, J, a, b                              ] = ???
    final def apply[a, b, c                           ] (txMetaData: Composite_03[a, b, c                           ]): Molecule_13[A, B, C, D, E, F, G, H, I, J, a, b, c                           ] = ???
    final def apply[a, b, c, d                        ] (txMetaData: Composite_04[a, b, c, d                        ]): Molecule_14[A, B, C, D, E, F, G, H, I, J, a, b, c, d                        ] = ???
    final def apply[a, b, c, d, e                     ] (txMetaData: Composite_05[a, b, c, d, e                     ]): Molecule_15[A, B, C, D, E, F, G, H, I, J, a, b, c, d, e                     ] = ???
    final def apply[a, b, c, d, e, f                  ] (txMetaData: Composite_06[a, b, c, d, e, f                  ]): Molecule_16[A, B, C, D, E, F, G, H, I, J, a, b, c, d, e, f                  ] = ???
    final def apply[a, b, c, d, e, f, g               ] (txMetaData: Composite_07[a, b, c, d, e, f, g               ]): Molecule_17[A, B, C, D, E, F, G, H, I, J, a, b, c, d, e, f, g               ] = ???
    final def apply[a, b, c, d, e, f, g, h            ] (txMetaData: Composite_08[a, b, c, d, e, f, g, h            ]): Molecule_18[A, B, C, D, E, F, G, H, I, J, a, b, c, d, e, f, g, h            ] = ???
    final def apply[a, b, c, d, e, f, g, h, i         ] (txMetaData: Composite_09[a, b, c, d, e, f, g, h, i         ]): Molecule_19[A, B, C, D, E, F, G, H, I, J, a, b, c, d, e, f, g, h, i         ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j      ] (txMetaData: Composite_10[a, b, c, d, e, f, g, h, i, j      ]): Molecule_20[A, B, C, D, E, F, G, H, I, J, a, b, c, d, e, f, g, h, i, j      ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k   ] (txMetaData: Composite_11[a, b, c, d, e, f, g, h, i, j, k   ]): Molecule_21[A, B, C, D, E, F, G, H, I, J, a, b, c, d, e, f, g, h, i, j, k   ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k, l] (txMetaData: Composite_12[a, b, c, d, e, f, g, h, i, j, k, l]): Molecule_22[A, B, C, D, E, F, G, H, I, J, a, b, c, d, e, f, g, h, i, j, k, l] = ???
  }
}

trait Tx_11[A, B, C, D, E, F, G, H, I, J, K] extends TxMetaDataBase {
  object Tx {
    final def apply                                  (txMetaData: Molecule_00                                 ): Molecule_11[A, B, C, D, E, F, G, H, I, J, K                                 ] = ???
    final def apply[a                              ] (txMetaData: Molecule_01[a                              ]): Molecule_12[A, B, C, D, E, F, G, H, I, J, K, a                              ] = ???
    final def apply[a, b                           ] (txMetaData: Molecule_02[a, b                           ]): Molecule_13[A, B, C, D, E, F, G, H, I, J, K, a, b                           ] = ???
    final def apply[a, b, c                        ] (txMetaData: Molecule_03[a, b, c                        ]): Molecule_14[A, B, C, D, E, F, G, H, I, J, K, a, b, c                        ] = ???
    final def apply[a, b, c, d                     ] (txMetaData: Molecule_04[a, b, c, d                     ]): Molecule_15[A, B, C, D, E, F, G, H, I, J, K, a, b, c, d                     ] = ???
    final def apply[a, b, c, d, e                  ] (txMetaData: Molecule_05[a, b, c, d, e                  ]): Molecule_16[A, B, C, D, E, F, G, H, I, J, K, a, b, c, d, e                  ] = ???
    final def apply[a, b, c, d, e, f               ] (txMetaData: Molecule_06[a, b, c, d, e, f               ]): Molecule_17[A, B, C, D, E, F, G, H, I, J, K, a, b, c, d, e, f               ] = ???
    final def apply[a, b, c, d, e, f, g            ] (txMetaData: Molecule_07[a, b, c, d, e, f, g            ]): Molecule_18[A, B, C, D, E, F, G, H, I, J, K, a, b, c, d, e, f, g            ] = ???
    final def apply[a, b, c, d, e, f, g, h         ] (txMetaData: Molecule_08[a, b, c, d, e, f, g, h         ]): Molecule_19[A, B, C, D, E, F, G, H, I, J, K, a, b, c, d, e, f, g, h         ] = ???
    final def apply[a, b, c, d, e, f, g, h, i      ] (txMetaData: Molecule_09[a, b, c, d, e, f, g, h, i      ]): Molecule_20[A, B, C, D, E, F, G, H, I, J, K, a, b, c, d, e, f, g, h, i      ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j   ] (txMetaData: Molecule_10[a, b, c, d, e, f, g, h, i, j   ]): Molecule_21[A, B, C, D, E, F, G, H, I, J, K, a, b, c, d, e, f, g, h, i, j   ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k] (txMetaData: Molecule_11[a, b, c, d, e, f, g, h, i, j, k]): Molecule_22[A, B, C, D, E, F, G, H, I, J, K, a, b, c, d, e, f, g, h, i, j, k] = ???

    final def apply                                  (txMetaData: Composite_00                                 ): Molecule_11[A, B, C, D, E, F, G, H, I, J, K                                 ] = ???
    final def apply[a                              ] (txMetaData: Composite_01[a                              ]): Molecule_12[A, B, C, D, E, F, G, H, I, J, K, a                              ] = ???
    final def apply[a, b                           ] (txMetaData: Composite_02[a, b                           ]): Molecule_13[A, B, C, D, E, F, G, H, I, J, K, a, b                           ] = ???
    final def apply[a, b, c                        ] (txMetaData: Composite_03[a, b, c                        ]): Molecule_14[A, B, C, D, E, F, G, H, I, J, K, a, b, c                        ] = ???
    final def apply[a, b, c, d                     ] (txMetaData: Composite_04[a, b, c, d                     ]): Molecule_15[A, B, C, D, E, F, G, H, I, J, K, a, b, c, d                     ] = ???
    final def apply[a, b, c, d, e                  ] (txMetaData: Composite_05[a, b, c, d, e                  ]): Molecule_16[A, B, C, D, E, F, G, H, I, J, K, a, b, c, d, e                  ] = ???
    final def apply[a, b, c, d, e, f               ] (txMetaData: Composite_06[a, b, c, d, e, f               ]): Molecule_17[A, B, C, D, E, F, G, H, I, J, K, a, b, c, d, e, f               ] = ???
    final def apply[a, b, c, d, e, f, g            ] (txMetaData: Composite_07[a, b, c, d, e, f, g            ]): Molecule_18[A, B, C, D, E, F, G, H, I, J, K, a, b, c, d, e, f, g            ] = ???
    final def apply[a, b, c, d, e, f, g, h         ] (txMetaData: Composite_08[a, b, c, d, e, f, g, h         ]): Molecule_19[A, B, C, D, E, F, G, H, I, J, K, a, b, c, d, e, f, g, h         ] = ???
    final def apply[a, b, c, d, e, f, g, h, i      ] (txMetaData: Composite_09[a, b, c, d, e, f, g, h, i      ]): Molecule_20[A, B, C, D, E, F, G, H, I, J, K, a, b, c, d, e, f, g, h, i      ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j   ] (txMetaData: Composite_10[a, b, c, d, e, f, g, h, i, j   ]): Molecule_21[A, B, C, D, E, F, G, H, I, J, K, a, b, c, d, e, f, g, h, i, j   ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j, k] (txMetaData: Composite_11[a, b, c, d, e, f, g, h, i, j, k]): Molecule_22[A, B, C, D, E, F, G, H, I, J, K, a, b, c, d, e, f, g, h, i, j, k] = ???
  }
}

trait Tx_12[A, B, C, D, E, F, G, H, I, J, K, L] extends TxMetaDataBase {
  object Tx {
    final def apply                               (txMetaData: Molecule_00                              ): Molecule_12[A, B, C, D, E, F, G, H, I, J, K, L                              ] = ???
    final def apply[a                           ] (txMetaData: Molecule_01[a                           ]): Molecule_13[A, B, C, D, E, F, G, H, I, J, K, L, a                           ] = ???
    final def apply[a, b                        ] (txMetaData: Molecule_02[a, b                        ]): Molecule_14[A, B, C, D, E, F, G, H, I, J, K, L, a, b                        ] = ???
    final def apply[a, b, c                     ] (txMetaData: Molecule_03[a, b, c                     ]): Molecule_15[A, B, C, D, E, F, G, H, I, J, K, L, a, b, c                     ] = ???
    final def apply[a, b, c, d                  ] (txMetaData: Molecule_04[a, b, c, d                  ]): Molecule_16[A, B, C, D, E, F, G, H, I, J, K, L, a, b, c, d                  ] = ???
    final def apply[a, b, c, d, e               ] (txMetaData: Molecule_05[a, b, c, d, e               ]): Molecule_17[A, B, C, D, E, F, G, H, I, J, K, L, a, b, c, d, e               ] = ???
    final def apply[a, b, c, d, e, f            ] (txMetaData: Molecule_06[a, b, c, d, e, f            ]): Molecule_18[A, B, C, D, E, F, G, H, I, J, K, L, a, b, c, d, e, f            ] = ???
    final def apply[a, b, c, d, e, f, g         ] (txMetaData: Molecule_07[a, b, c, d, e, f, g         ]): Molecule_19[A, B, C, D, E, F, G, H, I, J, K, L, a, b, c, d, e, f, g         ] = ???
    final def apply[a, b, c, d, e, f, g, h      ] (txMetaData: Molecule_08[a, b, c, d, e, f, g, h      ]): Molecule_20[A, B, C, D, E, F, G, H, I, J, K, L, a, b, c, d, e, f, g, h      ] = ???
    final def apply[a, b, c, d, e, f, g, h, i   ] (txMetaData: Molecule_09[a, b, c, d, e, f, g, h, i   ]): Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, a, b, c, d, e, f, g, h, i   ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j] (txMetaData: Molecule_10[a, b, c, d, e, f, g, h, i, j]): Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, a, b, c, d, e, f, g, h, i, j] = ???

    final def apply                               (txMetaData: Composite_00                              ): Molecule_12[A, B, C, D, E, F, G, H, I, J, K, L                              ] = ???
    final def apply[a                           ] (txMetaData: Composite_01[a                           ]): Molecule_13[A, B, C, D, E, F, G, H, I, J, K, L, a                           ] = ???
    final def apply[a, b                        ] (txMetaData: Composite_02[a, b                        ]): Molecule_14[A, B, C, D, E, F, G, H, I, J, K, L, a, b                        ] = ???
    final def apply[a, b, c                     ] (txMetaData: Composite_03[a, b, c                     ]): Molecule_15[A, B, C, D, E, F, G, H, I, J, K, L, a, b, c                     ] = ???
    final def apply[a, b, c, d                  ] (txMetaData: Composite_04[a, b, c, d                  ]): Molecule_16[A, B, C, D, E, F, G, H, I, J, K, L, a, b, c, d                  ] = ???
    final def apply[a, b, c, d, e               ] (txMetaData: Composite_05[a, b, c, d, e               ]): Molecule_17[A, B, C, D, E, F, G, H, I, J, K, L, a, b, c, d, e               ] = ???
    final def apply[a, b, c, d, e, f            ] (txMetaData: Composite_06[a, b, c, d, e, f            ]): Molecule_18[A, B, C, D, E, F, G, H, I, J, K, L, a, b, c, d, e, f            ] = ???
    final def apply[a, b, c, d, e, f, g         ] (txMetaData: Composite_07[a, b, c, d, e, f, g         ]): Molecule_19[A, B, C, D, E, F, G, H, I, J, K, L, a, b, c, d, e, f, g         ] = ???
    final def apply[a, b, c, d, e, f, g, h      ] (txMetaData: Composite_08[a, b, c, d, e, f, g, h      ]): Molecule_20[A, B, C, D, E, F, G, H, I, J, K, L, a, b, c, d, e, f, g, h      ] = ???
    final def apply[a, b, c, d, e, f, g, h, i   ] (txMetaData: Composite_09[a, b, c, d, e, f, g, h, i   ]): Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, a, b, c, d, e, f, g, h, i   ] = ???
    final def apply[a, b, c, d, e, f, g, h, i, j] (txMetaData: Composite_10[a, b, c, d, e, f, g, h, i, j]): Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, a, b, c, d, e, f, g, h, i, j] = ???
  }
}

trait Tx_13[A, B, C, D, E, F, G, H, I, J, K, L, M] extends TxMetaDataBase {
  object Tx {
    final def apply                            (txMetaData: Molecule_00                           ): Molecule_13[A, B, C, D, E, F, G, H, I, J, K, L, M                           ] = ???
    final def apply[a                        ] (txMetaData: Molecule_01[a                        ]): Molecule_14[A, B, C, D, E, F, G, H, I, J, K, L, M, a                        ] = ???
    final def apply[a, b                     ] (txMetaData: Molecule_02[a, b                     ]): Molecule_15[A, B, C, D, E, F, G, H, I, J, K, L, M, a, b                     ] = ???
    final def apply[a, b, c                  ] (txMetaData: Molecule_03[a, b, c                  ]): Molecule_16[A, B, C, D, E, F, G, H, I, J, K, L, M, a, b, c                  ] = ???
    final def apply[a, b, c, d               ] (txMetaData: Molecule_04[a, b, c, d               ]): Molecule_17[A, B, C, D, E, F, G, H, I, J, K, L, M, a, b, c, d               ] = ???
    final def apply[a, b, c, d, e            ] (txMetaData: Molecule_05[a, b, c, d, e            ]): Molecule_18[A, B, C, D, E, F, G, H, I, J, K, L, M, a, b, c, d, e            ] = ???
    final def apply[a, b, c, d, e, f         ] (txMetaData: Molecule_06[a, b, c, d, e, f         ]): Molecule_19[A, B, C, D, E, F, G, H, I, J, K, L, M, a, b, c, d, e, f         ] = ???
    final def apply[a, b, c, d, e, f, g      ] (txMetaData: Molecule_07[a, b, c, d, e, f, g      ]): Molecule_20[A, B, C, D, E, F, G, H, I, J, K, L, M, a, b, c, d, e, f, g      ] = ???
    final def apply[a, b, c, d, e, f, g, h   ] (txMetaData: Molecule_08[a, b, c, d, e, f, g, h   ]): Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, a, b, c, d, e, f, g, h   ] = ???
    final def apply[a, b, c, d, e, f, g, h, i] (txMetaData: Molecule_09[a, b, c, d, e, f, g, h, i]): Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, a, b, c, d, e, f, g, h, i] = ???

    final def apply                            (txMetaData: Composite_00                           ): Molecule_13[A, B, C, D, E, F, G, H, I, J, K, L, M                           ] = ???
    final def apply[a                        ] (txMetaData: Composite_01[a                        ]): Molecule_14[A, B, C, D, E, F, G, H, I, J, K, L, M, a                        ] = ???
    final def apply[a, b                     ] (txMetaData: Composite_02[a, b                     ]): Molecule_15[A, B, C, D, E, F, G, H, I, J, K, L, M, a, b                     ] = ???
    final def apply[a, b, c                  ] (txMetaData: Composite_03[a, b, c                  ]): Molecule_16[A, B, C, D, E, F, G, H, I, J, K, L, M, a, b, c                  ] = ???
    final def apply[a, b, c, d               ] (txMetaData: Composite_04[a, b, c, d               ]): Molecule_17[A, B, C, D, E, F, G, H, I, J, K, L, M, a, b, c, d               ] = ???
    final def apply[a, b, c, d, e            ] (txMetaData: Composite_05[a, b, c, d, e            ]): Molecule_18[A, B, C, D, E, F, G, H, I, J, K, L, M, a, b, c, d, e            ] = ???
    final def apply[a, b, c, d, e, f         ] (txMetaData: Composite_06[a, b, c, d, e, f         ]): Molecule_19[A, B, C, D, E, F, G, H, I, J, K, L, M, a, b, c, d, e, f         ] = ???
    final def apply[a, b, c, d, e, f, g      ] (txMetaData: Composite_07[a, b, c, d, e, f, g      ]): Molecule_20[A, B, C, D, E, F, G, H, I, J, K, L, M, a, b, c, d, e, f, g      ] = ???
    final def apply[a, b, c, d, e, f, g, h   ] (txMetaData: Composite_08[a, b, c, d, e, f, g, h   ]): Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, a, b, c, d, e, f, g, h   ] = ???
    final def apply[a, b, c, d, e, f, g, h, i] (txMetaData: Composite_09[a, b, c, d, e, f, g, h, i]): Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, a, b, c, d, e, f, g, h, i] = ???
  }
}

trait Tx_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] extends TxMetaDataBase {
  object Tx {
    final def apply                         (txMetaData: Molecule_00                        ): Molecule_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N                        ] = ???
    final def apply[a                     ] (txMetaData: Molecule_01[a                     ]): Molecule_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, a                     ] = ???
    final def apply[a, b                  ] (txMetaData: Molecule_02[a, b                  ]): Molecule_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, a, b                  ] = ???
    final def apply[a, b, c               ] (txMetaData: Molecule_03[a, b, c               ]): Molecule_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, a, b, c               ] = ???
    final def apply[a, b, c, d            ] (txMetaData: Molecule_04[a, b, c, d            ]): Molecule_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, a, b, c, d            ] = ???
    final def apply[a, b, c, d, e         ] (txMetaData: Molecule_05[a, b, c, d, e         ]): Molecule_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, a, b, c, d, e         ] = ???
    final def apply[a, b, c, d, e, f      ] (txMetaData: Molecule_06[a, b, c, d, e, f      ]): Molecule_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, a, b, c, d, e, f      ] = ???
    final def apply[a, b, c, d, e, f, g   ] (txMetaData: Molecule_07[a, b, c, d, e, f, g   ]): Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, a, b, c, d, e, f, g   ] = ???
    final def apply[a, b, c, d, e, f, g, h] (txMetaData: Molecule_08[a, b, c, d, e, f, g, h]): Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, a, b, c, d, e, f, g, h] = ???

    final def apply                         (txMetaData: Composite_00                        ): Molecule_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N                        ] = ???
    final def apply[a                     ] (txMetaData: Composite_01[a                     ]): Molecule_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, a                     ] = ???
    final def apply[a, b                  ] (txMetaData: Composite_02[a, b                  ]): Molecule_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, a, b                  ] = ???
    final def apply[a, b, c               ] (txMetaData: Composite_03[a, b, c               ]): Molecule_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, a, b, c               ] = ???
    final def apply[a, b, c, d            ] (txMetaData: Composite_04[a, b, c, d            ]): Molecule_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, a, b, c, d            ] = ???
    final def apply[a, b, c, d, e         ] (txMetaData: Composite_05[a, b, c, d, e         ]): Molecule_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, a, b, c, d, e         ] = ???
    final def apply[a, b, c, d, e, f      ] (txMetaData: Composite_06[a, b, c, d, e, f      ]): Molecule_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, a, b, c, d, e, f      ] = ???
    final def apply[a, b, c, d, e, f, g   ] (txMetaData: Composite_07[a, b, c, d, e, f, g   ]): Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, a, b, c, d, e, f, g   ] = ???
    final def apply[a, b, c, d, e, f, g, h] (txMetaData: Composite_08[a, b, c, d, e, f, g, h]): Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, a, b, c, d, e, f, g, h] = ???
  }
}

trait Tx_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] extends TxMetaDataBase {
  object Tx {
    final def apply                      (txMetaData: Molecule_00                     ): Molecule_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O                     ] = ???
    final def apply[a                  ] (txMetaData: Molecule_01[a                  ]): Molecule_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, a                  ] = ???
    final def apply[a, b               ] (txMetaData: Molecule_02[a, b               ]): Molecule_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, a, b               ] = ???
    final def apply[a, b, c            ] (txMetaData: Molecule_03[a, b, c            ]): Molecule_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, a, b, c            ] = ???
    final def apply[a, b, c, d         ] (txMetaData: Molecule_04[a, b, c, d         ]): Molecule_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, a, b, c, d         ] = ???
    final def apply[a, b, c, d, e      ] (txMetaData: Molecule_05[a, b, c, d, e      ]): Molecule_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, a, b, c, d, e      ] = ???
    final def apply[a, b, c, d, e, f   ] (txMetaData: Molecule_06[a, b, c, d, e, f   ]): Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, a, b, c, d, e, f   ] = ???
    final def apply[a, b, c, d, e, f, g] (txMetaData: Molecule_07[a, b, c, d, e, f, g]): Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, a, b, c, d, e, f, g] = ???

    final def apply                      (txMetaData: Composite_00                     ): Molecule_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O                     ] = ???
    final def apply[a                  ] (txMetaData: Composite_01[a                  ]): Molecule_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, a                  ] = ???
    final def apply[a, b               ] (txMetaData: Composite_02[a, b               ]): Molecule_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, a, b               ] = ???
    final def apply[a, b, c            ] (txMetaData: Composite_03[a, b, c            ]): Molecule_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, a, b, c            ] = ???
    final def apply[a, b, c, d         ] (txMetaData: Composite_04[a, b, c, d         ]): Molecule_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, a, b, c, d         ] = ???
    final def apply[a, b, c, d, e      ] (txMetaData: Composite_05[a, b, c, d, e      ]): Molecule_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, a, b, c, d, e      ] = ???
    final def apply[a, b, c, d, e, f   ] (txMetaData: Composite_06[a, b, c, d, e, f   ]): Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, a, b, c, d, e, f   ] = ???
    final def apply[a, b, c, d, e, f, g] (txMetaData: Composite_07[a, b, c, d, e, f, g]): Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, a, b, c, d, e, f, g] = ???
  }
}

trait Tx_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] extends TxMetaDataBase {
  object Tx {
    final def apply                   (txMetaData: Molecule_00                  ): Molecule_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P                  ] = ???
    final def apply[a               ] (txMetaData: Molecule_01[a               ]): Molecule_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, a               ] = ???
    final def apply[a, b            ] (txMetaData: Molecule_02[a, b            ]): Molecule_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, a, b            ] = ???
    final def apply[a, b, c         ] (txMetaData: Molecule_03[a, b, c         ]): Molecule_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, a, b, c         ] = ???
    final def apply[a, b, c, d      ] (txMetaData: Molecule_04[a, b, c, d      ]): Molecule_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, a, b, c, d      ] = ???
    final def apply[a, b, c, d, e   ] (txMetaData: Molecule_05[a, b, c, d, e   ]): Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, a, b, c, d, e   ] = ???
    final def apply[a, b, c, d, e, f] (txMetaData: Molecule_06[a, b, c, d, e, f]): Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, a, b, c, d, e, f] = ???

    final def apply                   (txMetaData: Composite_00                  ): Molecule_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P                  ] = ???
    final def apply[a               ] (txMetaData: Composite_01[a               ]): Molecule_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, a               ] = ???
    final def apply[a, b            ] (txMetaData: Composite_02[a, b            ]): Molecule_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, a, b            ] = ???
    final def apply[a, b, c         ] (txMetaData: Composite_03[a, b, c         ]): Molecule_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, a, b, c         ] = ???
    final def apply[a, b, c, d      ] (txMetaData: Composite_04[a, b, c, d      ]): Molecule_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, a, b, c, d      ] = ???
    final def apply[a, b, c, d, e   ] (txMetaData: Composite_05[a, b, c, d, e   ]): Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, a, b, c, d, e   ] = ???
    final def apply[a, b, c, d, e, f] (txMetaData: Composite_06[a, b, c, d, e, f]): Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, a, b, c, d, e, f] = ???
  }
}

trait Tx_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] extends TxMetaDataBase {
  object Tx {
    final def apply                (txMetaData: Molecule_00               ): Molecule_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q               ] = ???
    final def apply[a            ] (txMetaData: Molecule_01[a            ]): Molecule_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, a            ] = ???
    final def apply[a, b         ] (txMetaData: Molecule_02[a, b         ]): Molecule_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, a, b         ] = ???
    final def apply[a, b, c      ] (txMetaData: Molecule_03[a, b, c      ]): Molecule_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, a, b, c      ] = ???
    final def apply[a, b, c, d   ] (txMetaData: Molecule_04[a, b, c, d   ]): Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, a, b, c, d   ] = ???
    final def apply[a, b, c, d, e] (txMetaData: Molecule_05[a, b, c, d, e]): Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, a, b, c, d, e] = ???

    final def apply                (txMetaData: Composite_00               ): Molecule_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q               ] = ???
    final def apply[a            ] (txMetaData: Composite_01[a            ]): Molecule_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, a            ] = ???
    final def apply[a, b         ] (txMetaData: Composite_02[a, b         ]): Molecule_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, a, b         ] = ???
    final def apply[a, b, c      ] (txMetaData: Composite_03[a, b, c      ]): Molecule_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, a, b, c      ] = ???
    final def apply[a, b, c, d   ] (txMetaData: Composite_04[a, b, c, d   ]): Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, a, b, c, d   ] = ???
    final def apply[a, b, c, d, e] (txMetaData: Composite_05[a, b, c, d, e]): Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, a, b, c, d, e] = ???
  }
}

trait Tx_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] extends TxMetaDataBase {
  object Tx {
    final def apply             (txMetaData: Molecule_00            ): Molecule_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R            ] = ???
    final def apply[a         ] (txMetaData: Molecule_01[a         ]): Molecule_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, a         ] = ???
    final def apply[a, b      ] (txMetaData: Molecule_02[a, b      ]): Molecule_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, a, b      ] = ???
    final def apply[a, b, c   ] (txMetaData: Molecule_03[a, b, c   ]): Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, a, b, c   ] = ???
    final def apply[a, b, c, d] (txMetaData: Molecule_04[a, b, c, d]): Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, a, b, c, d] = ???

    final def apply             (txMetaData: Composite_00            ): Molecule_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R            ] = ???
    final def apply[a         ] (txMetaData: Composite_01[a         ]): Molecule_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, a         ] = ???
    final def apply[a, b      ] (txMetaData: Composite_02[a, b      ]): Molecule_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, a, b      ] = ???
    final def apply[a, b, c   ] (txMetaData: Composite_03[a, b, c   ]): Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, a, b, c   ] = ???
    final def apply[a, b, c, d] (txMetaData: Composite_04[a, b, c, d]): Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, a, b, c, d] = ???
  }
}

trait Tx_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] extends TxMetaDataBase {
  object Tx {
    final def apply         (txMetaData: Molecule_00         ): Molecule_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S         ] = ???
    final def apply[a      ](txMetaData: Molecule_01[a      ]): Molecule_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, a      ] = ???
    final def apply[a, b   ](txMetaData: Molecule_02[a, b   ]): Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, a, b   ] = ???
    final def apply[a, b, c](txMetaData: Molecule_03[a, b, c]): Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, a, b, c] = ???

    final def apply         (txMetaData: Composite_00         ): Molecule_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S         ] = ???
    final def apply[a      ](txMetaData: Composite_01[a      ]): Molecule_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, a      ] = ???
    final def apply[a, b   ](txMetaData: Composite_02[a, b   ]): Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, a, b   ] = ???
    final def apply[a, b, c](txMetaData: Composite_03[a, b, c]): Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, a, b, c] = ???
  }
}

trait Tx_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] extends TxMetaDataBase {
  object Tx {
    final def apply       (txMetaData: Molecule_00      ): Molecule_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T      ] = ???
    final def apply[a   ] (txMetaData: Molecule_01[a   ]): Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, a   ] = ???
    final def apply[a, b] (txMetaData: Molecule_02[a, b]): Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, a, b] = ???

    final def apply       (txMetaData: Composite_00      ): Molecule_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T      ] = ???
    final def apply[a   ] (txMetaData: Composite_01[a   ]): Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, a   ] = ???
    final def apply[a, b] (txMetaData: Composite_02[a, b]): Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, a, b] = ???
  }
}

trait Tx_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] extends TxMetaDataBase {
  object Tx {
    final def apply    (txMetaData: Molecule_00   ): Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U   ] = ???
    final def apply[a] (txMetaData: Molecule_01[a]): Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, a] = ???

    final def apply    (txMetaData: Composite_00   ): Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U   ] = ???
    final def apply[a] (txMetaData: Composite_01[a]): Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, a] = ???
  }
}

trait Tx_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] extends TxMetaDataBase {
  object Tx {
    final def apply(txMetaData: Molecule_00): Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] = ???

    final def apply(txMetaData: Composite_00): Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] = ???
  }
}
