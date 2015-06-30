package actors

import akka.actor.{Props, ActorLogging, Actor}

object Rabbit {

  def props: Props = {

    Props(new Rabbit)

  }

}

class Rabbit extends Actor with ActorLogging {
  println("Rabbit Created")
  import Rabbit._

  override def receive: Receive = {
    case _ => println("TODO")
  }



}