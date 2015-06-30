package actors

import akka.actor.{ActorRef, Props, ActorLogging, Actor}

object SimManager {

  case object CreateFox
  case object CreateWarren

  def props: Props = {

    Props(new SimManager)

  }
}

class SimManager extends Actor with ActorLogging {

  import SimManager._

  override def receive: Receive = {
    case CreateWarren =>
      createWarren()
    case CreateFox =>
      createFox()
  }

  protected def createWarren(): ActorRef =
    context.actorOf(Warren.props(4), "Warren")

  protected def createFox(): ActorRef =
    context.actorOf(Fox.props)

}