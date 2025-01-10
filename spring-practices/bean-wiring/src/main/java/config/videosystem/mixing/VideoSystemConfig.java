package config.videosystem.mixing;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <--- JavaConfig2, JavaConfig1
 */

@Configuration
@Import({ DVDConfig.class, DVDPlayerConfig.class })
public class VideoSystemConfig {
}
