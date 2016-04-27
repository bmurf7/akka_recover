package com.foo.actors

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import com.foo.actors.DatabaseServiceActor.Protocol.PerformUpdate
import com.foo.repo.EcsRepo

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext

class DatabaseServiceActor(repo:EcsRepo)(implicit ctx: ExecutionContext) extends Actor with ActorLogging {

  log.debug("Initialize database service actor")
  context.system.scheduler.scheduleOnce(5 seconds, self, PerformUpdate)

  override def receive: Receive = {

    case PerformUpdate ⇒
      log.debug("Beginning of database update")

      try {
        repo.performUpdate()
        context.system.scheduler.scheduleOnce(5 seconds, self, PerformUpdate)
      } catch {
        case e:Exception =>
          log.debug(s"An error has occured: ${e.getMessage} retrying again.")
          context.system.scheduler.scheduleOnce(5 seconds, self, PerformUpdate)
      }
  }
}

object DatabaseServiceActor {

  def props(repo: EcsRepo)(implicit ctx: ExecutionContext, actorSystem: ActorSystem): Props = Props(new DatabaseServiceActor(repo))

  object Protocol {
    case object PerformUpdate
  }
}

