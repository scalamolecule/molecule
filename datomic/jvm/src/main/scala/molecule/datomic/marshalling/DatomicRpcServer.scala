package molecule.datomic.marshalling

import molecule.core.marshalling.MoleculeRpcServer

/**
 * Server app serving http requests/ws from client
 *
 * Run with
 *
 *    sbt datomicJVM/run
 */
object DatomicRpcServer extends MoleculeRpcServer(DatomicRpcJVM) with App
