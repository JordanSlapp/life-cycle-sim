package actors

import akka.actor.{ActorRef, Props, ActorLogging, Actor}

object SimManager {

  case object CreateFox
  case object CreateRabbit

  def props: Props = {

    Props(new SimManager)

  }
}

class SimManager extends Actor with ActorLogging {

  import SimManager._

  override def receive: Receive = {
    case CreateRabbit =>
      createRabbit()
    case CreateFox =>
      createFox()
  }

  protected def createRabbit(): ActorRef =
    context.actorOf(Rabbit.props)

  protected def createFox(): ActorRef =
    context.actorOf(Fox.props)

}