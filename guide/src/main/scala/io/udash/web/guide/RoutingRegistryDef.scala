package io.udash.web.guide

import io.udash._
import io.udash.utils.Bidirectional

class RoutingRegistryDef extends RoutingRegistry[RoutingState] {
  def matchUrl(url: Url): RoutingState =
    url2State.applyOrElse(url.value.stripSuffix("/"), (x: String) => ErrorState)

  def matchState(state: RoutingState): Url =
    Url(state2Url.apply(state))

  private val (url2State, state2Url) = Bidirectional[String, RoutingState] {
    case "" => IntroState
    case "/bootstrapping" => BootstrappingIntroState
    case "/bootstrapping/sbt" => BootstrappingSBTState
    case "/bootstrapping/rpc" => BootstrappingRpcState
    case "/bootstrapping/backend" => BootstrappingBackendState
    case "/bootstrapping/frontend" => BootstrappingFrontendState
    case "/bootstrapping/generators" => BootstrappingGeneratorsState
    case "/frontend" => FrontendIntroState
    case "/frontend/routing" => FrontendRoutingState(None)
    case "/frontend/routing" /:/ s => FrontendRoutingState(Some(s))
    case "/frontend/mvp" => FrontendMVPState
    case "/frontend/templates" => FrontendTemplatesState
    case "/frontend/properties" => FrontendPropertiesState
    case "/frontend/bindings" => FrontendBindingsState
    case "/frontend/forms" => FrontendFormsState
    case "/frontend/files" => FrontendFilesState
    case "/rpc" => RpcIntroState
    case "/rpc/interfaces" => RpcInterfacesState
    case "/rpc/serialization" => RpcSerializationState
    case "/rpc/client-server" => RpcClientServerState
    case "/rpc/server-client" => RpcServerClientState
    case "/rest" => RestIntroState
    case "/rest/interfaces" =>RestInterfacesState
    case "/rest/client-server" => RestClientServerState
    case "/rest/server" => RestServerState
    case "/ext/i18n" => I18NExtState
    case "/ext/bootstrap" => BootstrapExtState
    case "/ext/charts" => ChartsExtState
    case "/ext/jquery" => JQueryExtState
    case "/ext/activity" => UserActivityExtState
    case "/faq" => FAQState
    case "/license" => LicenseState
  }
}