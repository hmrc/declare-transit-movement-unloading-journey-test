package ctc.suites

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import org.scalatest.WordSpec
import uk.gov.hmrc.zap.ZapTest
import uk.gov.hmrc.zap.config.ZapConfiguration

class ZAPRunner extends WordSpec with ZapTest {

  val customConfig: Config = ConfigFactory.load().getConfig("zap-automation-config")

  override val zapConfiguration: ZapConfiguration = new ZapConfiguration(customConfig)

  "Kicking off the zap scan" should {
    "should complete successfully" in {
      triggerZapScan()
    }
  }
}
