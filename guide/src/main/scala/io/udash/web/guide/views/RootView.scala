package io.udash.web.guide.views

import io.udash._
import io.udash.web.commons.components.Footer
import io.udash.web.commons.styles.StyleRegistry
import io.udash.web.guide.RootState
import io.udash.web.guide.components.Header
import io.udash.web.guide.styles.GuideDefaultStyles

object RootViewPresenter extends DefaultViewPresenterFactory[RootState.type](() => new RootView)

class RootView extends ViewContainer {
  import scalatags.JsDom.all._

  protected val child = div().render
  private val defaultStyles = GuideDefaultStyles.get

  private val content = div(
    StyleRegistry.styleSheet,
    Header.getTemplate,
    child,
    Footer.getTemplate
  )

  override def getTemplate: Modifier = content
}